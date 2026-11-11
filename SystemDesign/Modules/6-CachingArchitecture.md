# MODULE 6 — Caching Architecture

## Navigation

| # | Topic | What you will learn |
|---|---|---|
| 1 | [Caching Fundamentals](#1-caching-fundamentals) | Definition, need, benefits, trade-offs |
| 2 | [Cache Hierarchy](#2-cache-hierarchy) | CPU, browser, CDN, application and distributed cache |
| 3 | [Redis Architecture](#3-redis-architecture) | Redis internals, data structures and architecture |
| 4 | [Caching Strategies](#4-caching-strategies) | Cache-aside, read-through, write-through, write-behind, write-around |
| 5 | [Cache Invalidation and TTL](#5-cache-invalidation-and-ttl) | Expiration, invalidation and consistency |
| 6 | [Eviction Policies](#6-eviction-policies) | LRU, LFU, FIFO and Redis eviction |
| 7 | [Caching Problems](#7-common-caching-problems) | Stampede, penetration, avalanche and hot keys |
| 8 | [Cache Consistency](#8-cache-consistency) | Stale data, consistency and distributed invalidation |
| 9 | [Distributed Caching](#9-distributed-caching) | Shared cache, Redis Cluster and scaling |
| 10 | [Redis Replication](#10-redis-replication) | Primary-replica architecture and failover |
| 11 | [Redis Persistence](#11-redis-persistence) | RDB, AOF and recovery |
| 12 | [Redis Pub/Sub](#12-redis-pubsub) | Messaging and cache invalidation |
| 13 | [Spring Boot + Redis Implementation](#13-spring-boot--redis-implementation) | Configuration, CRUD, caching and TTL |
| 14 | [Production Architecture](#14-production-caching-architecture) | End-to-end example and best practices |
| 15 | [Interview Questions](#15-interview-questions) | Frequently asked questions for 5+ YOE |

---

# 1. Caching Fundamentals

## 1.1 What is caching?

Caching is the process of storing frequently accessed data in a faster storage layer so that future requests can retrieve it without repeatedly accessing the original data source.

A cache stores a temporary copy of data that is expensive or time-consuming to retrieve.

**Example: E-commerce product API**

Suppose an application has an API:

`GET /products/101`

Without caching:

1. User requests product details.
2. Spring Boot receives the request.
3. Application queries MySQL.
4. MySQL executes the query.
5. Database returns the product.
6. Application returns the response.

If 10,000 users request the same product, the database may execute the same query thousands of times.

With caching:

1. User requests product details.
2. Spring Boot checks Redis.
3. If the product exists in Redis, return it.
4. Otherwise, query MySQL.
5. Store the result in Redis.
6. Return the result.

The next request can retrieve the product from Redis.

## 1.2 Why is caching required?

Caching is primarily used to reduce latency and reduce the workload on slower or expensive systems.

| Problem without caching | How caching helps |
|---|---|
| Repeated database queries | Reuses previously retrieved data |
| High database CPU utilization | Reduces database query load |
| Slow API response | Returns data from faster storage |
| Database connection exhaustion | Reduces database connections and query execution |
| High infrastructure cost | Reduces the need for additional database capacity |
| Traffic spikes | Absorbs repeated reads |
| External API latency | Stores frequently requested external responses |

### Example: API latency comparison

Illustrative values only; actual performance depends on hardware, network, data size and configuration.

| Operation | Example latency |
|---|---:|
| Application local memory lookup | Microseconds |
| Redis lookup | Around 1 ms |
| Database query | 10–50 ms |
| External API call | 100–500 ms |

If an API takes 100 ms because of a database query and the same data can be served from Redis in 1 ms, the response can become significantly faster.

**Important:** Caching does not automatically make every application faster. Cache lookup, serialization, network communication, invalidation and memory usage also have costs.

## 1.3 When should you use caching?

Good candidates:

- Frequently accessed reference data.
- Product catalog information.
- User profile information.
- Configuration and feature flags.
- Frequently used search results.
- Session information.
- Expensive aggregation results.
- External API responses.

Avoid or carefully evaluate caching when:

- Data changes continuously.
- Every request needs strongly consistent, latest data.
- Data is rarely accessed.
- The data is too large for the available cache.
- Cache invalidation is more expensive than querying the original source.

## 1.4 Cache hit and cache miss

**Cache hit:** Requested data is present in the cache.

**Cache miss:** Requested data is not present in the cache.

Example:

```text
Request 1: GET /products/101

Redis:
  Product:101 -> NOT FOUND

Database:
  Fetch product 101

Redis:
  Store Product:101

Result: Cache MISS
```

```text
Request 2: GET /products/101

Redis:
  Product:101 -> FOUND

Result: Cache HIT
```

### Cache hit ratio

Cache hit ratio measures how frequently requests are served from the cache.

Formula:

```text
Cache Hit Ratio = Cache Hits / Total Cache Requests * 100
```

Example:

```text
Total requests = 10,000
Cache hits     = 8,500
Cache misses   = 1,500

Hit ratio = 8,500 / 10,000 * 100
          = 85%
```

A high hit ratio generally indicates that the cache is serving a useful portion of the traffic. However, a high hit ratio alone does not guarantee good performance or correct data.

---

# 2. Cache Hierarchy

Caching can exist at multiple levels of an application and infrastructure.

A typical hierarchy:

```text
User / Client
     |
     v
Browser Cache
     |
     v
CDN Cache
     |
     v
Load Balancer
     |
     v
Spring Boot Application
     |
     +----> Local Application Cache (L1)
     |
     +----> Redis Distributed Cache (L2)
     |
     v
Database
```

The closer the cache is to the consumer, the lower its potential access latency. However, different cache levels have different consistency and sharing characteristics.

## 2.1 CPU Cache

CPU cache is hardware-level memory used by the processor to store frequently accessed instructions and data.

It reduces the time required to access main memory (RAM).

### CPU cache levels

| Level | Description |
|---|---|
| L1 | Smallest and fastest cache, typically private to a CPU core |
| L2 | Larger than L1, generally slower |
| L3 | Larger shared cache, often shared across CPU cores |

```text
CPU Core
   |
   +---- L1 Cache
   |
   +---- L2 Cache
   |
   +---- L3 Cache
   |
   v
Main Memory (RAM)
```

### Why does CPU caching matter?

Accessing CPU cache is significantly faster than accessing main memory.

The CPU uses cache locality:

- Temporal locality: recently accessed data is likely to be accessed again.
- Spatial locality: nearby memory locations are likely to be accessed.

**Interview point:** CPU caching is hardware-managed and differs from application caching using Redis or Caffeine.

## 2.2 Browser Cache

Browser caching stores resources locally in the user's browser.

Examples:

- CSS files.
- JavaScript files.
- Images.
- Fonts.
- Static assets.
- Certain API responses, depending on HTTP caching headers and request behavior.

### Example

```http
GET /static/logo.png
```

Server response:

```http
HTTP/1.1 200 OK
Cache-Control: public, max-age=86400
ETag: "logo-v1"
```

The browser can reuse the cached resource for 24 hours, subject to HTTP caching rules.

### Important HTTP caching headers

| Header | Purpose |
|---|---|
| Cache-Control | Defines caching rules |
| Expires | Defines an expiration date |
| ETag | Identifies a resource version |
| Last-Modified | Indicates when the resource was modified |
| If-None-Match | Validates using an ETag |
| If-Modified-Since | Validates using modification time |
| Vary | Defines which request headers affect the cached representation |

### Cache-Control examples

```http
Cache-Control: public, max-age=3600
```

Publicly cacheable for one hour.

```http
Cache-Control: private, max-age=600
```

Can be cached by a private cache such as the user's browser, but not by shared caches.

```http
Cache-Control: no-store
```

Do not store the response.

```http
Cache-Control: no-cache
```

Storage may be allowed, but the cached response must be validated before reuse.

**Important interview distinction:** `no-cache` does not mean "never cache." `no-store` is the directive that prevents storage.

## 2.3 CDN Cache

CDN stands for Content Delivery Network.

A CDN stores copies of content at geographically distributed edge locations.

Example:

An e-commerce application is hosted in Hyderabad, but users access it from:

- India
- USA
- Europe
- Singapore

Without CDN:

```text
User in USA
    |
    v
Origin Server in India
```

With CDN:

```text
User in USA
    |
    v
Nearest CDN Edge
    |
    +---- Cache HIT ---> Return content
    |
    +---- Cache MISS --> Origin Server
```

### CDN use cases

- Images.
- Videos.
- JavaScript and CSS.
- Downloadable files.
- Public static content.
- Cacheable API responses.

### CDN cache invalidation

When content changes, the CDN may continue serving an older copy until its TTL expires.

Solutions:

- Set suitable TTL.
- Use versioned asset names.
- Purge or invalidate CDN content.
- Use cache-control headers.

Example:

```text
logo-v1.png
logo-v2.png
```

Using versioned filenames makes it easier to avoid serving outdated static assets.

## 2.4 Application Cache

Application cache stores frequently used data inside the application process.

Example: Spring Boot using Caffeine.

```java
Cache<String, Product> cache = Caffeine.newBuilder()
        .maximumSize(10_000)
        .expireAfterWrite(Duration.ofMinutes(10))
        .build();
```

Advantages:

- Very fast access.
- No network call.
- Useful for frequently accessed reference data.

Disadvantages:

- Each application instance has its own cache.
- Data may differ between instances.
- Cache is lost when the application restarts.
- Requires invalidation or expiration.

Example with multiple instances:

```text
             Load Balancer
                  |
          +-------+-------+
          |               |
          v               v
      App-1             App-2
      Cache             Cache
       A                 B
```

If product 101 is updated through App-1, App-2 may still have the old value.

This is one reason distributed caching is useful.

## 2.5 Distributed Cache

A distributed cache is a cache shared by multiple application instances.

Redis is a common example.

```text
             Load Balancer
                  |
          +-------+-------+
          |               |
          v               v
       App-1             App-2
          |               |
          +-------+-------+
                  |
                  v
              Redis
                  |
                  v
               MySQL
```

Advantages:

- Shared cache across application instances.
- Centralized cache management.
- Supports distributed deployments.
- Can reduce database load across multiple application instances.

Disadvantages:

- Requires network communication.
- Redis can become a bottleneck or failure dependency.
- Requires high availability and capacity planning.
- Requires cache invalidation and consistency management.

---

# 3. Redis Architecture

## 3.1 What is Redis?

Redis stands for Remote Dictionary Server.

It is an in-memory data store commonly used as:

- Distributed cache.
- Key-value database.
- Session store.
- Real-time counter.
- Rate-limiting store.
- Message-passing mechanism through Pub/Sub.
- Temporary data store.

Redis stores data primarily in memory and supports persistence mechanisms to recover data after restarts.

## 3.2 Why Redis?

| Feature | Benefit |
|---|---|
| In-memory storage | Fast data access |
| Key-value model | Simple lookup |
| Data structures | Supports more than simple strings |
| TTL | Automatic key expiration |
| Atomic operations | Useful for counters and coordination |
| Replication | High availability support |
| Redis Cluster | Horizontal partitioning |
| Persistence | Data recovery after restart |
| Pub/Sub | Message distribution |

## 3.3 Redis data structures

Redis supports multiple data structures.

| Data structure | Example use case |
|---|---|
| String | User details, tokens, counters |
| Hash | User profile fields |
| List | Queues and ordered collections |
| Set | Unique users, tags |
| Sorted Set | Leaderboards, rankings |
| Stream | Persistent event log and consumer groups |
| Bitmap | User activity tracking |
| HyperLogLog | Approximate unique counts |
| Geospatial | Location-based searches |

### String

```redis
SET user:101 "Naren"
GET user:101
```

Output:

```text
"Naren"
```

### Hash

```redis
HSET user:101 name "Naren" age 25 city "Hyderabad"

HGET user:101 name

HGETALL user:101
```

Useful for storing fields associated with an entity.

### List

```redis
LPUSH notifications "Order Created"
LPUSH notifications "Payment Completed"

LRANGE notifications 0 -1
```

Useful for ordered collections and some queue patterns.

### Set

```redis
SADD product:101:tags "electronics" "mobile" "5g"

SMEMBERS product:101:tags
```

Stores unique values.

### Sorted Set

```redis
ZADD leaderboard 100 "user101"
ZADD leaderboard 250 "user102"
ZADD leaderboard 180 "user103"

ZREVRANGE leaderboard 0 -1 WITHSCORES
```

Useful for leaderboards and ranking systems.

## 3.4 How Redis works internally

At a high level:

1. A client sends a command to Redis.
2. Redis processes the command.
3. The command reads or modifies the in-memory data structures.
4. Redis sends a response to the client.

Redis uses an event-driven architecture and processes commands primarily through a main execution thread. Some background work, such as persistence and I/O operations, can use additional threads depending on configuration and version.

This means Redis commands are generally executed sequentially, helping provide atomic command execution.

**Important:** A Redis command being atomic does not mean a sequence of multiple commands is automatically atomic. Use transactions, Lua scripts or appropriate atomic commands when a multi-step operation must be indivisible.

### Example

```redis
INCR order:counter
```

This atomically increments a counter.

---

# 4. Caching Strategies

Caching strategy determines how data moves between the cache and the original database.

The five important strategies are:

1. Cache-aside.
2. Read-through.
3. Write-through.
4. Write-behind.
5. Write-around.

## 4.1 Cache-aside (Lazy Loading)

In cache-aside, the application is responsible for checking the cache and loading data into it.

This is one of the most common caching patterns in Spring Boot applications.

### Read flow

```text
Client
  |
  v
Application
  |
  v
Check Redis
  |
  +---- Cache HIT ----> Return data
  |
  +---- Cache MISS
           |
           v
        Database
           |
           v
        Fetch data
           |
           v
        Store in Redis
           |
           v
        Return data
```

### Example

```java
public Product getProduct(Long id) {

    String key = "product:" + id;

    Product cached = redisTemplate.opsForValue().get(key);

    if (cached != null) {
        return cached;
    }

    Product product = productRepository.findById(id)
            .orElseThrow(() ->
                    new RuntimeException("Product not found"));

    redisTemplate.opsForValue()
            .set(key, product, Duration.ofMinutes(10));

    return product;
}
```

### Write flow

A common cache-aside write approach:

1. Update the database.
2. Delete the old cache entry.
3. Let the next read reload the updated value.

```java
@Transactional
public Product updateProduct(Long id, ProductRequest request) {

    Product product = productRepository.findById(id)
            .orElseThrow();

    product.setName(request.getName());
    product.setPrice(request.getPrice());

    Product saved = productRepository.save(product);

    redisTemplate.delete("product:" + id);

    return saved;
}
```

The database is updated first. The cache is invalidated afterward.

**Advantages:**

- Simple implementation.
- Cache only data that is requested.
- Database remains the source of truth.
- Suitable for read-heavy workloads.

**Disadvantages:**

- First request after a cache miss is slower.
- Application code must manage cache loading and invalidation.
- Concurrent updates and cache misses can cause stale-data races.

## 4.2 Read-through Cache

In read-through caching, the application requests data from the cache, and the cache layer is responsible for loading missing data from the underlying data source.

```text
Application
    |
    v
Cache Layer
    |
    +---- HIT ----> Return data
    |
    +---- MISS
             |
             v
          Database
             |
             v
       Store in Cache
             |
             v
       Return data
```

The main difference is responsibility.

| Cache-aside | Read-through |
|---|---|
| Application checks cache | Application asks cache layer |
| Application loads on miss | Cache layer loads on miss |
| Application manages loading logic | Cache provider manages loading logic |

Read-through support depends on the caching library or infrastructure. Redis itself does not automatically know how to query your MySQL database.

## 4.3 Write-through Cache

In write-through caching, the cache layer updates the cache and underlying database as part of the write operation.

```text
Application
    |
    v
Cache Layer
    |
    +----> Update Cache
    |
    +----> Update Database
```

The goal is to keep cache and database synchronized during writes.

**Advantages:**

- Cache is updated immediately.
- Subsequent reads can use the new value.
- Useful when data is read frequently after writes.

**Disadvantages:**

- Write latency increases.
- Both systems must be handled correctly.
- Partial failures need recovery logic.

## 4.4 Write-behind (Write-back)

In write-behind caching, the application updates the cache first, and the cache layer writes changes to the database asynchronously or in batches.

```text
Application
    |
    v
Cache
    |
    v
Return response
    |
    v
Async database write
```

**Advantages:**

- Very fast write response.
- Database writes can be batched.
- Useful for high-volume write workloads where eventual persistence is acceptable.

**Disadvantages:**

- Data loss risk if the cache fails before persistence.
- More complex recovery.
- Database may temporarily contain stale data.
- Requires reliable background processing.

Example use cases:

- Analytics counters.
- High-volume activity tracking.
- Non-critical telemetry.

For important business transactions such as payments and orders, do not rely on an ordinary Redis write-behind flow without a durable event or recovery mechanism.

## 4.5 Write-around Cache

In write-around caching, writes go directly to the database. The cache is not updated immediately.

```text
Application
    |
    v
Database
    |
    v
Cache remains unchanged
```

On the next read:

1. Cache miss.
2. Fetch data from database.
3. Store the data in cache.

**Advantages:**

- Avoids filling cache with rarely read data.
- Useful for write-heavy workloads.

**Disadvantages:**

- First read after a write can be slower.
- Cache may contain stale data if invalidation is not handled correctly.

## 4.6 Strategy comparison

| Strategy | Read behavior | Write behavior | Main advantage |
|---|---|---|---|
| Cache-aside | Application loads on miss | Usually update DB, invalidate cache | Simple and flexible |
| Read-through | Cache layer loads on miss | Depends on write strategy | Centralized loading |
| Write-through | Reads from cache | Cache and DB updated together | Fresh cache after successful writes |
| Write-behind | Reads from cache | Cache first, DB asynchronously | Fast writes |
| Write-around | Loads cache on read miss | Direct DB write | Avoids caching unused writes |

**Interview recommendation:** Be able to explain cache-aside in detail and compare it with write-through and write-behind. Cache-aside is a common choice for Spring Boot + Redis read-heavy services.

---

# 5. Cache Invalidation and TTL

## 5.1 What is cache invalidation?

Cache invalidation means removing or updating cached data when the original data changes.

Example:

```text
Redis:
product:101 = {
    "name": "iPhone",
    "price": 70000
}
```

Database update:

```text
price = 75000
```

If Redis still contains 70000, users may receive stale data.

The application must invalidate or update the cache.

### Common invalidation approaches

**1. TTL-based invalidation**

Allow cached data to expire automatically.

**2. Explicit deletion**

Delete the key when the database record changes.

```redis
DEL product:101
```

**3. Update cache**

Update the cached value after the database update.

**4. Event-based invalidation**

Publish an event when data changes. Other application instances or cache consumers invalidate their local cache entries.

## 5.2 What is TTL?

TTL stands for Time To Live.

It defines how long a cache entry should remain valid.

Example:

```redis
SET product:101 "iPhone"
EXPIRE product:101 600
```

The key expires after 600 seconds (10 minutes).

Or:

```redis
SET product:101 "iPhone" EX 600
```

Check remaining TTL:

```redis
TTL product:101
```

### Choosing TTL

| Data type | Example TTL |
|---|---|
| Product catalog | 5–30 minutes |
| User profile | 5–15 minutes |
| Static configuration | 30 minutes–several hours |
| OTP | 1–5 minutes, depending on security requirements |
| Search results | 1–5 minutes |
| Frequently changing stock | Very short TTL or no caching, depending on correctness needs |

These are illustrative values. Actual TTL should be based on freshness requirements, traffic patterns and business rules.

**Important:** TTL is not a complete cache consistency strategy. A value may become stale before its TTL expires.

## 5.3 TTL versus invalidation

| TTL | Explicit invalidation |
|---|---|
| Automatic expiration | Application or event-driven action |
| Simple to implement | More precise |
| Data can remain stale until expiration | Can remove stale data immediately |
| Good for bounded staleness | Useful when updates must propagate quickly |

A production system often combines both.

---

# 6. Eviction Policies

## 6.1 What is cache eviction?

Cache eviction is the process of removing existing entries when the cache reaches its configured memory limit or needs to reclaim memory.

Suppose Redis has a memory limit of 1 GB and memory usage reaches the limit.

Redis needs a policy to decide which keys can be removed.

## 6.2 LRU — Least Recently Used

LRU removes the key that has not been accessed for the longest time.

Example:

```text
Cache entries:

A -> Last accessed 10:00
B -> Last accessed 10:05
C -> Last accessed 10:10

Current time: 10:20
```

A is the least recently used key.

**Use case:** When recently accessed data is likely to be accessed again.

## 6.3 LFU — Least Frequently Used

LFU removes the key with the lowest access frequency.

Example:

```text
A -> Accessed 100 times
B -> Accessed 5 times
C -> Accessed 50 times
```

B is the least frequently used.

**Use case:** When frequently accessed data should remain in cache even if it was not accessed very recently.

## 6.4 FIFO — First In, First Out

FIFO removes the oldest inserted entry, regardless of how frequently or recently it was accessed.

Example:

```text
Inserted:
A -> 10:00
B -> 10:05
C -> 10:10
```

A is evicted first.

**Use case:** Simple queue-like eviction requirements.

## 6.5 Comparison

| Policy | Eviction criteria |
|---|---|
| LRU | Least recently accessed |
| LFU | Least frequently accessed |
| FIFO | Oldest inserted |
 
### Redis eviction policies

Redis supports policies including:

| Redis policy | Meaning |
|---|---|
| noeviction | Reject writes that require additional memory when the limit is reached |
| allkeys-lru | Evict least recently used keys across all keys |
| allkeys-lfu | Evict least frequently used keys across all keys |
| allkeys-random | Evict random keys across all keys |
| volatile-lru | Evict least recently used keys among keys with an expiration |
| volatile-lfu | Evict least frequently used keys among keys with an expiration |
| volatile-random | Evict random keys among keys with an expiration |
| volatile-ttl | Evict keys with the shortest remaining TTL among keys with an expiration |

Redis uses approximations for LRU and LFU rather than maintaining a perfect global ordering of every key.

### Example Redis configuration

```redis
maxmemory 2gb
maxmemory-policy allkeys-lru
```

This sets a 2 GB memory limit and an LRU-style eviction policy.

**Interview point:** TTL and eviction are different.

- TTL removes a key because its expiration time has passed.
- Eviction removes a key to free memory under a configured policy.

---

# 7. Common Caching Problems

These are important system design and production interview topics.

## 7.1 Cache Stampede

Cache stampede occurs when a popular cache entry expires or becomes unavailable and many requests simultaneously try to load the same data from the database.

Example:

```text
Product:101 is cached.

10,000 users request Product:101.

Cache entry expires.

All 10,000 requests see a cache miss.

All 10,000 requests query MySQL.
```

This can overload the database.

### Solutions

**1. Locking**

Allow one request to load the data while others wait or use a fallback.

```text
Request 1 -> Acquires lock -> DB query -> Populate cache
Request 2 -> Waits
Request 3 -> Waits
```

A distributed lock may be required when multiple application instances are involved.

**2. Request coalescing / single-flight**

Combine concurrent requests for the same key into one database load within an application instance.

**3. TTL jitter**

Add a random amount to the TTL so many keys do not expire simultaneously.

```java
long ttlSeconds = 600 + ThreadLocalRandom.current()
        .nextLong(0, 120);
```

**4. Background refresh**

Refresh popular cache entries before they expire.

**5. Stale-while-revalidate**

Serve an acceptable stale value while refreshing it in the background.

## 7.2 Cache Penetration

Cache penetration occurs when requests repeatedly query keys that do not exist in the database.

Example:

```text
GET /products/999999
GET /products/999999
GET /products/999999
```

If product 999999 does not exist:

1. Redis returns cache miss.
2. Database returns not found.
3. Application does not cache the missing result.
4. Next request repeats the database query.

An attacker can exploit this with many random IDs.

### Solutions

**1. Cache negative results**

Cache a marker representing "not found."

```text
product:999999 = NOT_FOUND
TTL = 60 seconds
```

The application must distinguish a cached negative result from an actual cache miss.

**2. Bloom filter**

A Bloom filter can quickly identify values that are definitely absent from a known set.

It can produce false positives, but not false negatives when used correctly.

**3. Input validation**

Reject malformed IDs and invalid requests before querying the database.

## 7.3 Cache Avalanche

Cache avalanche occurs when a large number of cache entries expire or become unavailable at approximately the same time.

Example:

```text
10:00 AM

Product cache expires.
User cache expires.
Category cache expires.
Search cache expires.

Thousands of requests go to MySQL.
```

This can overload the database.

### Solutions

- Use randomized TTL.
- Avoid assigning identical expiration times to large groups of keys.
- Use cache warming for critical data.
- Use rate limiting.
- Use fallback responses where appropriate.
- Use database capacity protection and circuit breakers.
- Monitor cache availability and database load.

## 7.4 Hot Keys

A hot key is a cache key receiving a disproportionately large number of requests.

Example:

```text
product:iphone-17
```

A promotional product receives 100,000 requests per second.

All requests access the same Redis key.

This can create a bottleneck on one Redis shard or node.

### Solutions

**1. Local caching**

Cache the hot value in each application instance for a very short period.

**2. Key replication**

For read-heavy workloads, maintain multiple copies using different keys and distribute reads among them.

Example:

```text
product:101:replica:1
product:101:replica:2
product:101:replica:3
```

This requires a mechanism to keep copies synchronized.

**3. Request coalescing**

Combine concurrent requests for the same key.

**4. Pre-warming**

Load popular data into cache before a high-traffic event.

### Difference between stampede and hot key

| Cache stampede | Hot key |
|---|---|
| Many requests hit DB after a cache miss or expiry | Many requests access one popular cache key |
| Usually caused by expiration or cache failure | Usually caused by uneven request distribution |
| Database gets overloaded | A cache node or shard may become overloaded |
| Locking, TTL jitter and refresh help | Local cache, replication and request distribution help |

---

# 8. Cache Consistency

## 8.1 What is cache consistency?

Cache consistency means ensuring that the data served from the cache does not violate the application's freshness and correctness requirements relative to the database.

Consider:

```text
MySQL:
product:101 = price 75000

Redis:
product:101 = price 70000
```

Redis contains stale data.

This is a cache consistency problem.

## 8.2 Cache-aside invalidation race condition

Consider the following sequence:

```text
Initial state:
DB = 100
Cache = 100

Thread A:
1. Reads DB = 100

Thread B:
1. Updates DB = 200
2. Deletes cache

Thread A:
2. Writes old DB value (100) into cache
```

Final state:

```text
DB = 200
Cache = 100
```

Even though the update deleted the cache, a concurrent old read repopulated it.

### Possible solutions

- Use versioned cache entries.
- Use carefully designed locking for critical keys.
- Use short TTLs to bound stale data.
- Use update events and invalidation.
- Design write and cache-refresh flows around the required consistency level.

No single solution is perfect for every workload. Locking adds complexity and can reduce throughput.

## 8.3 Cache and database update ordering

Two common approaches:

**Approach A: Update database, then delete cache**

```text
Update DB
   |
   v
Delete cache
```

This is a common cache-aside approach.

**Approach B: Delete cache, then update database**

```text
Delete cache
   |
   v
Update DB
```

This can allow a concurrent reader to fetch old database data and repopulate the cache before the database update completes.

For many applications, updating the database first and then invalidating the cache is the preferred starting point. However, it does not eliminate all race conditions.

## 8.4 Transactional Outbox Pattern

A difficult problem occurs when the database update succeeds but cache invalidation or event publication fails.

The Transactional Outbox Pattern helps make event publication reliable.

```text
Spring Boot
    |
    v
Database Transaction
    |
    +---- Update Product
    |
    +---- Insert Outbox Event
    |
    v
Commit Transaction
    |
    v
Outbox Publisher
    |
    v
Message Broker
    |
    v
Cache Invalidation Consumer
    |
    v
Redis DEL product:101
```

The business update and outbox event are saved in the same database transaction.

A separate process publishes the event to a message broker. Consumers invalidate or refresh cache entries.

This is useful when cache invalidation must be reliable across multiple application instances or services.

---

# 9. Distributed Caching

## 9.1 What is distributed caching?

Distributed caching stores cached data in a shared cache infrastructure that can be accessed by multiple application instances.

Example:

```text
                Load Balancer
                     |
          +----------+----------+
          |                     |
          v                     v
      Spring Boot 1         Spring Boot 2
          |                     |
          +----------+----------+
                     |
                     v
                   Redis
                     |
                     v
                   MySQL
```

Both application instances access the same Redis deployment.

## 9.2 Local cache vs distributed cache

| Local cache | Distributed cache |
|---|---|
| Stored in application memory | Stored in a separate cache infrastructure |
| Very low lookup latency | Network lookup required |
| Each instance has separate data | Shared across instances |
| Can become inconsistent between instances | Easier to share common values |
| Lost when application restarts | Can survive application restarts, depending on Redis persistence |
| Suitable for small, frequently accessed data | Suitable for shared data and larger cache capacity |

## 9.3 Two-level caching: L1 + L2

A production application may use two cache levels.

- L1: Local cache such as Caffeine.
- L2: Distributed cache such as Redis.

```text
Request
   |
   v
L1 Cache (Caffeine)
   |
   +---- HIT ----> Return data
   |
   +---- MISS
          |
          v
       L2 Cache (Redis)
          |
          +---- HIT ----> Populate L1 -> Return
          |
          +---- MISS
                 |
                 v
              Database
                 |
                 v
          Populate Redis
                 |
                 v
          Populate L1
                 |
                 v
             Return
```

Advantages:

- Very fast local reads for frequently accessed data.
- Shared Redis cache across application instances.
- Reduced Redis traffic.

Challenges:

- L1 and L2 can become inconsistent.
- Invalidation must reach every application instance.
- Requires short TTLs or event-based invalidation.

---

# 10. Redis Replication

## 10.1 What is Redis replication?

Redis replication allows data from a primary node to be copied to one or more replica nodes.

```text
             Primary Redis
                  |
          +-------+-------+
          |               |
          v               v
       Replica 1       Replica 2
```

Primary:

- Accepts writes.
- Sends replication data to replicas.

Replicas:

- Maintain copies of primary data.
- Can serve reads when configured by the application or proxy.
- Can be promoted during failover by a high-availability mechanism.

## 10.2 Why replication?

- High availability.
- Redundancy.
- Read scaling in suitable workloads.
- Recovery from primary-node failure.

## 10.3 Replication lag

Replication is generally asynchronous.

This means a write may reach the primary before it reaches a replica.

Example:

```text
Primary:
product:101 = 75000

Replica:
product:101 = 70000
```

If the application reads immediately from the replica, it may get stale data.

This is called replication lag.

**Interview point:** Replication does not automatically guarantee strong consistency.

## 10.4 Redis Sentinel

Redis Sentinel provides monitoring and automatic failover for non-clustered Redis primary-replica deployments.

Responsibilities:

- Monitor Redis nodes.
- Detect failures.
- Coordinate failover.
- Promote a replica to primary.
- Inform clients about the new primary.

```text
          Sentinel Cluster
                |
                v
          Redis Primary
                |
          +-----+-----+
          |           |
          v           v
       Replica 1   Replica 2
```

Sentinel is different from Redis Cluster.

---

# 11. Redis Persistence

Redis is primarily an in-memory data store, but it supports persistence mechanisms to recover data after a restart.

Two important mechanisms:

1. RDB.
2. AOF.

## 11.1 RDB — Redis Database Backup

RDB creates point-in-time snapshots of Redis data.

Example:

```text
Redis Memory
     |
     v
Snapshot
     |
     v
dump.rdb
```

The snapshot is stored on disk.

### Advantages

- Compact backup file.
- Efficient for backup and recovery.
- Generally faster restart recovery than replaying a large command log.

### Disadvantages

- Changes after the last snapshot may be lost if Redis crashes.
- Snapshot generation consumes resources.

## 11.2 AOF — Append Only File

AOF records write operations in a log.

Example:

```redis
SET user:101 "Naren"
INCR order:counter
DEL user:101
```

These write commands are recorded so they can be replayed during recovery.

### AOF fsync options

| Setting | Behavior |
|---|---|
| always | Sync after every write operation; strongest durability but higher overhead |
| everysec | Sync approximately once per second; common performance/durability compromise |
| no | Let the operating system decide when to flush |

### Advantages

- Can provide better durability than periodic snapshots.
- Replays logged write operations during recovery.

### Disadvantages

- AOF can consume more disk space.
- Recovery may take longer depending on file size and configuration.
- Requires rewrite/compaction management.

## 11.3 RDB vs AOF

| RDB | AOF |
|---|---|
| Point-in-time snapshots | Records write operations |
| Compact backup | Can be larger |
| Periodic snapshots | Continuous logging |
| May lose changes since last snapshot | Durability depends on fsync policy |
| Useful for backups | Useful for better write-history durability |

Redis supports using both RDB and AOF.

**Important:** Redis persistence is not a substitute for a durable primary database for critical business records. Choose persistence based on whether Redis is being used as a disposable cache or as a data store whose contents must survive restarts.

---

# 12. Redis Pub/Sub

## 12.1 What is Redis Pub/Sub?

Pub/Sub stands for Publish/Subscribe.

It allows publishers to send messages to channels and subscribers to receive messages from those channels.

Publisher does not need to know the identity of subscribers.

```text
             Publisher
                 |
                 v
           Redis Channel
                 |
         +-------+-------+
         |               |
         v               v
     Subscriber 1    Subscriber 2
```

## 12.2 Redis Pub/Sub example

Publisher:

```redis
PUBLISH product-events "product:101:updated"
```

Subscriber:

```redis
SUBSCRIBE product-events
```

The subscriber receives messages published to the channel.

## 12.3 Use cases

- Cache invalidation notifications.
- Real-time notifications.
- Lightweight event broadcasting.
- Refreshing local application caches.
- Application coordination signals.

### Cache invalidation example

Suppose App-1 updates product 101.

```text
App-1
  |
  v
Update MySQL
  |
  v
Delete Redis key
  |
  v
Publish product-updated event
  |
  v
Redis Pub/Sub
  |
  +---------> App-1
  |
  +---------> App-2
  |
  +---------> App-3
```

Each application instance can invalidate its local Caffeine cache.

### Important limitation

Redis Pub/Sub is not a durable message queue.

If a subscriber is disconnected when a message is published, it does not receive that missed message later.

For reliable event delivery, consider:

- Redis Streams.
- Kafka.
- RabbitMQ.

### Pub/Sub vs Redis Streams

| Pub/Sub | Redis Streams |
|---|---|
| Fire-and-forget messaging | Persistent stream of messages |
| Disconnected subscribers miss messages | Messages can be read later |
| No consumer-group acknowledgement model | Supports consumer groups and acknowledgements |
| Lightweight broadcasting | More suitable for durable event processing |

---

# 13. Spring Boot + Redis Implementation

We will implement a practical Product API using:

- Java 17+
- Spring Boot 3
- Spring Data Redis
- Redis
- Spring Data JPA
- MySQL

## 13.1 Architecture

```text
Client
  |
  v
ProductController
  |
  v
ProductService
  |
  +----> Redis Cache
  |          |
  |          +---- Cache HIT --> Return
  |
  +----> ProductRepository
               |
               v
             MySQL
```

## 13.2 Add dependencies

Maven `pom.xml`:

```xml
<dependencies>

    <!-- Spring Web -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>

    <!-- Spring Data JPA -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-jpa</artifactId>
    </dependency>

    <!-- Redis -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-redis</artifactId>
    </dependency>

    <!-- MySQL -->
    <dependency>
        <groupId>com.mysql</groupId>
        <artifactId>mysql-connector-j</artifactId>
        <scope>runtime</scope>
    </dependency>

</dependencies>
```

## 13.3 Configure Redis

`application.yml`

```yaml
spring:
  data:
    redis:
      host: localhost
      port: 6379
      timeout: 2s

  datasource:
    url: jdbc:mysql://localhost:3306/productdb
    username: root
    password: root

  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true
```

For production, use environment variables or a secrets manager for credentials rather than hardcoding passwords.

## 13.4 Start Redis using Docker

```bash
docker run -d \
  --name redis-server \
  -p 6379:6379 \
  redis:7
```

Check Redis:

```bash
docker exec -it redis-server redis-cli
```

Test:

```redis
PING
```

Expected:

```text
PONG
```

## 13.5 Create Product Entity

```java
@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Double price;

    // Constructors, getters and setters
}
```

## 13.6 Create Product Repository

```java
public interface ProductRepository
        extends JpaRepository<Product, Long> {
}
```

## 13.7 Configure RedisTemplate

`RedisConfig.java`

```java
@Configuration
public class RedisConfig {

    @Bean
    public RedisTemplate<String, Product> redisTemplate(
            RedisConnectionFactory connectionFactory) {

        RedisTemplate<String, Product> template =
                new RedisTemplate<>();

        template.setConnectionFactory(connectionFactory);

        template.setKeySerializer(
                new StringRedisSerializer());

        template.setValueSerializer(
                new Jackson2JsonRedisSerializer<>(
                        Product.class));

        template.afterPropertiesSet();

        return template;
    }
}
```

This example uses JSON serialization for Product values. For more complex production models, configure and test serialization carefully, including compatibility across deployments. Avoid unsafe polymorphic deserialization configurations.

## 13.8 Implement Cache-aside in Service

```java
@Service
public class ProductService {

    private static final String CACHE_PREFIX = "product:";

    private static final Duration CACHE_TTL =
            Duration.ofMinutes(10);

    private final ProductRepository productRepository;

    private final RedisTemplate<String, Product> redisTemplate;

    public ProductService(
            ProductRepository productRepository,
            RedisTemplate<String, Product> redisTemplate) {

        this.productRepository = productRepository;
        this.redisTemplate = redisTemplate;
    }

    public Product getProduct(Long id) {

        String key = CACHE_PREFIX + id;

        // 1. Check Redis
        Product cachedProduct =
                redisTemplate.opsForValue().get(key);

        if (cachedProduct != null) {
            System.out.println("Cache HIT");
            return cachedProduct;
        }

        System.out.println("Cache MISS");

        // 2. Fetch from database
        Product product = productRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Product not found"));

        // 3. Store in Redis with TTL
        redisTemplate.opsForValue().set(
                key,
                product,
                CACHE_TTL
        );

        // 4. Return result
        return product;
    }

    public Product createProduct(Product product) {

        Product saved = productRepository.save(product);

        String key = CACHE_PREFIX + saved.getId();

        // Invalidate if an entry exists
        redisTemplate.delete(key);

        return saved;
    }

    public Product updateProduct(
            Long id,
            Product request) {

        Product existing = productRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Product not found"));

        existing.setName(request.getName());
        existing.setPrice(request.getPrice());

        Product updated = productRepository.save(existing);

        // Invalidate cache
        redisTemplate.delete(CACHE_PREFIX + id);

        return updated;
    }

    public void deleteProduct(Long id) {

        productRepository.deleteById(id);

        redisTemplate.delete(CACHE_PREFIX + id);
    }
}
```

**Production note:** This is an introductory cache-aside implementation. In a real application, consider transaction boundaries, negative caching, concurrent cache misses, invalidation failures, serialization, and observability. A database commit and Redis invalidation are not one atomic transaction.

## 13.9 Create REST Controller

```java
@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{id}")
    public Product getProduct(@PathVariable Long id) {
        return productService.getProduct(id);
    }

    @PostMapping
    public Product createProduct(
            @RequestBody Product product) {

        return productService.createProduct(product);
    }

    @PutMapping("/{id}")
    public Product updateProduct(
            @PathVariable Long id,
            @RequestBody Product product) {

        return productService.updateProduct(id, product);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
    }
}
```

## 13.10 Test the cache

First request:

```http
GET http://localhost:8080/products/101
```

Console:

```text
Cache MISS
```

Second request:

```http
GET http://localhost:8080/products/101
```

Console:

```text
Cache HIT
```

Check Redis:

```redis
KEYS product:*
```

Inspect the entry:

```redis
GET product:101
```

Check TTL:

```redis
TTL product:101
```

Avoid using `KEYS *` or broad `KEYS` scans in production because they can block Redis while scanning a large keyspace. Use `SCAN` for incremental inspection.

---

## 13.11 Using Spring Cache Abstraction

Spring provides annotations that simplify cache integration.

Enable caching:

```java
@SpringBootApplication
@EnableCaching
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
```

### @Cacheable

Checks the cache first. If the value is absent, invokes the method and stores its result.

```java
@Cacheable(
    value = "products",
    key = "#id"
)
public Product getProduct(Long id) {

    return productRepository.findById(id)
            .orElseThrow();
}
```

Flow:

```text
Call getProduct(101)
        |
        v
Check cache
        |
        +---- HIT ----> Return cached value
        |
        +---- MISS
                |
                v
          Execute method
                |
                v
          Store result
                |
                v
          Return result
```

### @CachePut

Always executes the method and updates the cache with its returned value.

```java
@CachePut(
    value = "products",
    key = "#id"
)
public Product updateProduct(
        Long id,
        ProductRequest request) {

    Product product = productRepository.findById(id)
            .orElseThrow();

    product.setName(request.getName());
    product.setPrice(request.getPrice());

    return productRepository.save(product);
}
```

### @CacheEvict

Removes an entry from the cache.

```java
@CacheEvict(
    value = "products",
    key = "#id"
)
public void deleteProduct(Long id) {

    productRepository.deleteById(id);
}
```

Clear all entries in a cache:

```java
@CacheEvict(
    value = "products",
    allEntries = true
)
public void clearProductCache() {
}
```

### Annotation comparison

| Annotation | Behavior |
|---|---|
| @Cacheable | Read from cache; execute and cache on miss |
| @CachePut | Always execute and update cache |
| @CacheEvict | Remove cache entries |

### Important Spring caching considerations

1. Spring's cache annotations generally use proxy-based interception.
2. Self-invocation within the same class may bypass the proxy and therefore bypass caching.
3. By default, cache keys are derived from method arguments unless explicitly configured.
4. Cache annotations do not automatically make database and cache writes one atomic operation.
5. Configure serialization and TTL explicitly for your cache provider.
6. Avoid caching sensitive data without considering access control and data exposure.

## 13.12 Configure TTL with RedisCacheManager

For Spring Cache Abstraction, configure Redis cache expiration centrally.

```java
@Configuration
@EnableCaching
public class CacheConfig {

    @Bean
    public RedisCacheManager cacheManager(
            RedisConnectionFactory connectionFactory) {

        RedisCacheConfiguration configuration =
                RedisCacheConfiguration.defaultCacheConfig()
                        .entryTtl(Duration.ofMinutes(10))
                        .disableCachingNullValues()
                        .serializeKeysWith(
                                RedisSerializationContext.SerializationPair
                                        .fromSerializer(
                                                new StringRedisSerializer()
                                        )
                        );

        return RedisCacheManager.builder(connectionFactory)
                .cacheDefaults(configuration)
                .build();
    }
}
```

Now `@Cacheable("products")` uses the configured default TTL.

For different cache types, configure different TTLs using named cache configurations.

Example:

```java
RedisCacheConfiguration productConfig =
        RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(Duration.ofMinutes(10));

RedisCacheConfiguration userConfig =
        RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(Duration.ofMinutes(5));
```

A complete multi-cache manager can register these configurations using `withInitialCacheConfigurations(...)`.

## 13.13 Negative caching example

A simple negative-caching approach stores a marker for a missing product.

```java
public Product getProduct(Long id) {

    String key = "product:" + id;

    Object cached = redisTemplate.opsForValue().get(key);

    if ("NOT_FOUND".equals(cached)) {
        throw new RuntimeException("Product not found");
    }

    if (cached instanceof Product product) {
        return product;
    }

    Optional<Product> result = productRepository.findById(id);

    if (result.isEmpty()) {
        redisTemplate.opsForValue().set(
                key,
                "NOT_FOUND",
                Duration.ofSeconds(60)
        );

        throw new RuntimeException("Product not found");
    }

    Product product = result.get();

    redisTemplate.opsForValue().set(
            key,
            product,
            Duration.ofMinutes(10)
    );

    return product;
}
```

This is illustrative. The Redis serializer must support the mixed value types, or a dedicated cache value DTO should be used. A cleaner production design is to use a consistent cache envelope with an explicit `found` flag.

## 13.14 Redis atomic counter

Redis supports atomic counter operations.

```java
public Long incrementViews(Long productId) {

    String key = "product:views:" + productId;

    return redisTemplate.opsForValue().increment(key);
}
```

Redis command:

```redis
INCR product:views:101
```

This is useful for:

- Page views.
- API counters.
- Rate limiting.
- Temporary metrics.

For business-critical counters that must be durable and reconciled with a database, design a persistence and recovery strategy.

---

# 14. Production Caching Architecture

## 14.1 Example: E-commerce Product Service

Requirements:

- 1 million product API requests per day.
- Product details change occasionally.
- High read traffic.
- Multiple Spring Boot instances.
- MySQL is the source of truth.

Architecture:

```text
                    Users
                      |
                      v
                CDN (Static Data)
                      |
                      v
                Load Balancer
                      |
             +--------+--------+
             |                 |
             v                 v
         App-1              App-2
             |                 |
          Caffeine          Caffeine
             |                 |
             +--------+--------+
                      |
                      v
                 Redis Cluster
                      |
                      v
                    MySQL
```

### Read flow

1. User requests a product.
2. Application checks local cache.
3. If local cache misses, check Redis.
4. If Redis hits, populate local cache and return.
5. If Redis misses, query MySQL.
6. Populate Redis.
7. Populate local cache.
8. Return response.

### Update flow

1. User updates a product.
2. Application updates MySQL.
3. After successful database commit, invalidate Redis.
4. Publish an invalidation event.
5. Other application instances invalidate local caches.
6. Next read loads fresh data.

For reliable invalidation, use an outbox or another durable event-publishing mechanism where required.

## 14.2 Production best practices

### Key design

Use meaningful, namespaced keys.

```text
product:101
user:501
order:1001
category:electronics
```

For more complex keys:

```text
product:101:details
product:101:reviews
product:101:inventory
```

### TTL

- Set TTL for temporary cache data.
- Choose TTL based on freshness requirements.
- Use TTL jitter for large groups of keys.
- Avoid indefinite caching unless there is a clear invalidation plan.

### Memory management

- Configure maxmemory.
- Select an eviction policy.
- Monitor memory fragmentation and usage.
- Avoid storing unnecessarily large objects.
- Avoid unbounded key growth.

### Failure handling

Decide what happens if Redis is unavailable.

For a cache-aside application, a common fallback is:

```text
Redis unavailable
       |
       v
Application reads from DB
       |
       v
Return response
```

But this fallback must be protected. If every application instance bypasses Redis during an outage, the database may receive an overwhelming traffic spike.

Use:

- Connection and command timeouts.
- Circuit breakers where appropriate.
- Database concurrency limits.
- Rate limiting.
- Controlled fallback behavior.

### Security

- Use authentication and network restrictions.
- Enable TLS where required.
- Do not expose Redis directly to the public internet.
- Avoid putting secrets or sensitive personal data in cache keys.
- Configure access controls and least privilege.
- Keep Redis and its clients updated.

### Monitoring

Monitor:

| Metric | Why it matters |
|---|---|
| Cache hit ratio | Measures cache effectiveness |
| Cache miss rate | Helps identify inefficient caching |
| Redis memory usage | Detects memory pressure |
| Evicted keys | Shows whether memory limits are being reached |
| Expired keys | Helps understand TTL behavior |
| Command latency | Measures Redis response time |
| Connected clients | Detects connection growth |
| Rejected connections | Indicates connection pressure |
| Replication lag | Helps identify stale replica reads |
| CPU utilization | Detects node saturation |
| Network throughput | Detects network bottlenecks |

---

# 15. Interview Questions

## Fundamentals

**1. What is caching?**

Caching stores frequently accessed data in a faster storage layer so that repeated requests can avoid expensive operations such as database queries.

**2. Why do we need caching?**

To reduce latency, database load, repeated computation and infrastructure cost.

**3. What is a cache hit and cache miss?**

A hit means the requested data exists in cache. A miss means it must be loaded from another source.

**4. What is cache hit ratio?**

The percentage of cache requests served successfully from the cache.

**5. What are the different cache levels?**

CPU, browser, CDN, application-local and distributed cache.

## Caching Strategies

**6. Explain cache-aside.**

The application checks the cache, loads from the database on a miss, and stores the result in cache.

**7. Difference between cache-aside and read-through?**

In cache-aside, application code loads data on a miss. In read-through, the cache layer performs the loading.

**8. Difference between write-through and write-behind?**

Write-through updates the cache and database during the write operation. Write-behind updates the cache first and persists to the database asynchronously.

**9. What is write-around?**

Writes go directly to the database. Cache is populated when a subsequent read misses.

**10. Which caching strategy is commonly used with Redis?**

Cache-aside is a common choice because the application controls loading and invalidation.

## Cache Management

**11. What is TTL?**

Time To Live defines how long a cached entry remains valid.

**12. What is cache invalidation?**

Removing or updating cached data when the underlying data changes.

**13. Difference between TTL and eviction?**

TTL expires data after a configured time. Eviction removes keys to reclaim memory under a memory policy.

**14. Explain LRU and LFU.**

LRU removes least recently accessed data. LFU removes least frequently accessed data.

**15. What happens when Redis reaches maxmemory?**

Redis follows its configured eviction policy. With `noeviction`, writes requiring additional memory are rejected.

## Production Problems

**16. What is cache stampede?**

Many requests simultaneously query the database after a popular cache entry expires or becomes unavailable.

**17. How do you prevent cache stampede?**

Use request coalescing, distributed locking, TTL jitter, background refresh or stale-while-revalidate.

**18. What is cache penetration?**

Repeated requests for nonexistent data bypass the cache and repeatedly hit the database.

**19. How do you prevent cache penetration?**

Use negative caching, Bloom filters and input validation.

**20. What is cache avalanche?**

Many cache entries expire or become unavailable together, causing a sudden load spike on the database.

**21. What is a hot key?**

A cache key that receives a disproportionately high number of requests.

**22. How do you solve hot-key problems?**

Use local caching, request coalescing, key replication for reads and traffic distribution.

**23. How do you handle Redis failure?**

Use timeouts, controlled database fallback, circuit breakers, rate limits and high availability. Avoid uncontrolled cache bypass.

## Redis Architecture

**24. What is Redis?**

An in-memory data store that supports key-value operations and multiple data structures. It is commonly used for caching, sessions and real-time workloads.

**25. What is Redis replication?**

Copying data from a primary Redis node to replica nodes.

**26. What is Redis Sentinel?**

A monitoring and failover mechanism for Redis primary-replica deployments.

**27. What is Redis Cluster?**

A distributed Redis deployment that partitions keys across multiple primary nodes and supports replicas.

**28. Difference between Redis Sentinel and Redis Cluster?**

Sentinel provides high availability for a primary-replica setup. Redis Cluster provides data sharding across multiple primaries as well as replica-based failover.

**29. What is Redis persistence?**

Saving Redis data to disk using mechanisms such as RDB snapshots and AOF logs.

**30. Difference between RDB and AOF?**

RDB stores point-in-time snapshots. AOF records write operations for replay during recovery.

**31. What is Redis Pub/Sub?**

A messaging mechanism where publishers send messages to channels and subscribers receive them.

**32. Is Redis Pub/Sub reliable?**

It is not durable. Disconnected subscribers miss messages. Use Redis Streams or a message broker when durable delivery is required.

## Spring Boot

**33. How do you integrate Redis with Spring Boot?**

Add Spring Data Redis, configure the Redis connection, configure serializers and use RedisTemplate or Spring Cache Abstraction.

**34. Difference between RedisTemplate and @Cacheable?**

RedisTemplate provides direct Redis operations and fine-grained control. `@Cacheable` provides declarative method-level caching.

**35. What is @Cacheable?**

It checks the configured cache before executing the method and stores the result on a cache miss.

**36. What is @CachePut?**

It executes the method and updates the cache with the returned result.

**37. What is @CacheEvict?**

It removes a cache entry or all entries from a configured cache.

**38. How do you configure TTL in Spring Boot?**

Use RedisCacheConfiguration with `entryTtl()` for Spring Cache or pass a Duration to RedisTemplate operations.

**39. How do you invalidate cache after an update?**

Update the database and then evict or update the corresponding cache entry. For distributed applications, propagate invalidation reliably when necessary.

**40. What happens if Redis is down?**

The application must follow a controlled fallback strategy. For cache-aside, database reads may be used, but concurrency and load protection are essential.

---

# 16. Practical Interview Scenarios

## Scenario 1: Your Spring Boot API receives 10,000 requests per second for the same product. How do you design caching?

Expected discussion:

1. Cache product details in Redis.
2. Use cache-aside.
3. Set an appropriate TTL.
4. Add local caching for extremely hot data if useful.
5. Prevent cache stampede using request coalescing or locking.
6. Monitor hot keys and Redis latency.
7. Invalidate cache after product updates.

## Scenario 2: Redis contains outdated product prices. How do you fix it?

Expected discussion:

1. Identify the cache key.
2. Update the database.
3. Invalidate or update the cache.
4. Propagate invalidation to other application instances.
5. Use an outbox or reliable event flow if invalidation must not be lost.
6. Set a suitable TTL as a safety mechanism.

## Scenario 3: Redis goes down. How will your application work?

Expected discussion:

1. Detect the Redis failure quickly using timeouts.
2. Use controlled fallback to the database.
3. Protect the database with concurrency limits and rate limiting.
4. Apply circuit breaker behavior if appropriate.
5. Restore Redis availability.
6. Allow cache entries to repopulate safely.
7. Monitor database load during recovery.

## Scenario 4: Millions of cache keys expire at the same time. What happens?

Expected discussion:

- Cache avalanche.
- Sudden database traffic increase.
- Increased database latency.
- Possible database connection exhaustion.

Solutions:

- TTL jitter.
- Cache warming.
- Controlled refresh.
- Rate limiting.
- Database protection.

## Scenario 5: Multiple Spring Boot instances use Caffeine and Redis. How do you keep caches consistent?

Expected discussion:

1. Caffeine is the local L1 cache.
2. Redis is the shared L2 cache.
3. Update the database.
4. Invalidate the Redis entry.
5. Publish a cache invalidation event.
6. All application instances evict their local entry.
7. Use TTL as a fallback.
8. Use durable event delivery if missing an invalidation event is unacceptable.

---

# 17. What You Must Be Able to Explain in an Interview

| Priority | Concept | Expected depth |
|---|---|---|
| Very High | Cache-aside | Explain read/write flow and implement in Spring Boot |
| Very High | Redis architecture | Explain data structures and how commands work |
| Very High | TTL and invalidation | Explain freshness and stale data |
| Very High | Cache stampede | Explain the problem and practical solutions |
| Very High | Cache penetration and avalanche | Compare and solve |
| Very High | LRU and LFU | Explain eviction |
| Very High | Redis replication and Cluster | Explain high availability and scaling |
| Very High | Spring Boot Redis | Implement RedisTemplate and @Cacheable |
| High | Distributed caching | Explain multi-instance architecture |
| High | Cache consistency | Explain invalidation races and event-based solutions |
| High | Redis persistence | Compare RDB and AOF |
| High | Redis Pub/Sub | Explain use cases and limitations |
| Medium | CPU cache | Understand hierarchy and locality |
| Medium | Browser and CDN cache | Understand HTTP headers and edge caching |
| Medium | Write-through / write-behind | Compare with cache-aside |
| Medium | Hot keys | Explain and mitigate |

---

# 18. Final Revision Checklist

Before moving to the next system design module, make sure you can:

- [ ] Explain why caching is required.
- [ ] Draw the cache hierarchy.
- [ ] Compare local cache and distributed cache.
- [ ] Explain Redis architecture and data structures.
- [ ] Draw cache-aside read and write flows.
- [ ] Compare all five caching strategies.
- [ ] Explain TTL and cache invalidation.
- [ ] Compare LRU, LFU and FIFO.
- [ ] Explain cache stampede, penetration and avalanche.
- [ ] Explain hot keys and cache consistency.
- [ ] Draw a distributed caching architecture.
- [ ] Explain Redis Cluster and Sentinel.
- [ ] Compare RDB and AOF.
- [ ] Explain Redis Pub/Sub and Redis Streams.
- [ ] Implement cache-aside in Spring Boot.
- [ ] Use @Cacheable, @CachePut and @CacheEvict.
- [ ] Configure Redis TTL in Spring Boot.
- [ ] Explain how to handle Redis failure in production.

**Interview target:** You should be able to design a Spring Boot + Redis caching solution for a read-heavy e-commerce API, explain its failure scenarios, and justify your consistency, TTL and eviction choices.