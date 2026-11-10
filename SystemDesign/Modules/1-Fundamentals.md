# System Design Fundamentals – Complete Guide

**Target Audience:** Java Full Stack Developers (5+ Years Experience)  
**Purpose:** Product-Based Company Interviews – System Design Fundamentals

---

# 1. What is System Design?

## Definition

System Design is the process of defining the architecture, components, technologies, databases, communication mechanisms, and interactions required to build a software system that satisfies business and technical requirements.

It explains:

- What components are required?
- How do different components communicate?
- How is data stored and retrieved?
- How will the system handle millions of users?
- How will the system remain available during failures?
- How can we improve performance and scalability?

## Example: Designing an E-commerce Application

Consider an application like Amazon.

A simplified architecture:

```text
             Users
               |
               v
          Load Balancer
               |
               v
          API Gateway
               |
       +-------+-------+
       |       |       |
       v       v       v
    User    Product   Order
   Service  Service  Service
       |       |       |
       v       v       v
      DB      DB      DB
                       |
                       v
                  Payment Service
                       |
                       v
                  Payment Gateway
```

Each component has a specific responsibility.

For example:
- User Service manages customer information.
- Product Service manages product catalogs.
- Order Service manages orders.
- Payment Service processes payments.

**Interview point:** System Design is not just about writing code. It is about deciding how different components work together to build a scalable, reliable, maintainable, and performant application.

---

# 2. Why Do We Need System Design?

System Design helps us build applications that can handle real-world business requirements and large-scale traffic.

Consider an application initially serving 100 users.

After a successful business launch, it grows to 10 million users.

A basic application might start experiencing:

- Slow API responses.
- Database overload.
- Server crashes.
- Data inconsistencies.
- Increased infrastructure costs.
- Downtime during traffic spikes.

System Design helps solve these challenges.

## Major reasons

| Reason | Explanation | Example |
|---|---|---|
| Scalability | Handle increasing users and traffic | Instagram supporting millions of users |
| Availability | Keep the system accessible | Banking applications |
| Performance | Reduce response time | Faster product search |
| Reliability | Ensure correct and consistent operations | Payment processing |
| Fault tolerance | Continue operating despite failures | Multiple application instances |
| Maintainability | Make future changes easier | Microservices architecture |
| Cost optimization | Use infrastructure efficiently | Auto-scaling cloud servers |

**Real-world example:**

Suppose 100,000 users try to book tickets simultaneously.

Without proper system design:
- The server may crash.
- Multiple users may book the same seat.
- Payment processing may fail.
- Users may experience timeouts.

With proper system design:
- Load balancers distribute traffic.
- Queues manage high-volume requests.
- Database transactions protect seat bookings.
- Multiple application instances handle requests.
- Monitoring identifies failures.

**Interview point:** We need System Design to make applications scalable, reliable, available, performant, and cost-effective as business requirements grow.

---

# 3. Functional Requirements (FR)

## Definition

Functional Requirements describe **WHAT a system should do**.

They define the business functionalities and features that the application must provide.

## Example: E-commerce Application

Functional requirements:

1. Users should be able to register.
2. Users should be able to log in.
3. Users should be able to search for products.
4. Users should be able to add products to the cart.
5. Users should be able to place orders.
6. Users should be able to make payments.
7. Users should be able to track orders.
8. Admins should be able to manage products.

## Technical implementation

| Requirement | Possible implementation |
|---|---|
| User registration | User Service |
| Login | Authentication Service |
| Product search | Product Service + Elasticsearch |
| Add to cart | Cart Service + Redis |
| Place order | Order Service |
| Payment | Payment Service |
| Order tracking | Order Service |

## Interview example

Question: Design a URL Shortener like Bitly.

Functional requirements:

- Generate a short URL from a long URL.
- Redirect users from short URLs to original URLs.
- Support custom short URLs.
- Track URL clicks.
- Allow users to deactivate URLs.

**Remember:** Functional requirements describe the features and business operations of the system.

---

# 4. Non-Functional Requirements (NFR)

## Definition

Non-Functional Requirements describe **HOW WELL a system should work**.

They define the quality attributes and operational expectations of a system.

## Examples

| NFR | Example |
|---|---|
| Performance | API response time should be under 200 ms for 95% of requests |
| Availability | System should maintain 99.99% availability |
| Scalability | Support 10 million users |
| Reliability | Payments should not be lost |
| Security | Use OAuth2/JWT authentication and encryption |
| Maintainability | Services should be independently maintainable |
| Fault tolerance | System should continue working if one server fails |

## Functional vs Non-Functional Requirements

| Functional Requirements | Non-Functional Requirements |
|---|---|
| What the system does | How well the system works |
| Business features | Quality attributes |
| User registration | Authentication response time |
| Product search | Search latency |
| Place an order | Order processing reliability |
| Make a payment | Payment availability |

**Interview point:** Always identify both functional and non-functional requirements before designing a system.

---

# 5. Scalability

## Definition

Scalability is the ability of a system to handle increasing workloads by adding resources without significantly degrading performance.

Workloads may increase because of:

- More users.
- More API requests.
- More database operations.
- More data storage.
- More concurrent transactions.

## Example

Initially:

```text
100 users
    |
    v
1 Application Server
    |
    v
Database
```

After growth:

```text
1 Million Users
       |
       v
  Load Balancer
       |
   +---+---+
   |   |   |
   v   v   v
  App App App
   1   2   3
       |
       v
   Database
```

Multiple application servers can handle increased traffic.

## Types of scalability

### A. Vertical Scaling (Scale Up)

Increasing the resources of an existing server.

Examples:
- Increase RAM from 8 GB to 32 GB.
- Increase CPU from 4 cores to 16 cores.
- Upgrade storage capacity.

```text
Before:
Server
4 CPU | 8 GB RAM

After:
Server
16 CPU | 32 GB RAM
```

Advantages:
- Simple to implement.
- No major architectural changes.
- Useful for smaller systems.

Disadvantages:
- Hardware has maximum limits.
- Can become expensive.
- Server failure can affect the entire application.

### B. Horizontal Scaling (Scale Out)

Adding more servers or instances to distribute the workload.

```text
             Users
               |
               v
          Load Balancer
               |
       +-------+-------+
       |       |       |
       v       v       v
     App 1   App 2   App 3
```

Advantages:
- Handles large traffic.
- Supports redundancy.
- Better fault tolerance.
- Resources can be added dynamically.

Disadvantages:
- More architectural complexity.
- Requires load balancing.
- Requires distributed-state management.

**Real-world example:** Cloud applications running multiple Spring Boot instances behind an AWS Application Load Balancer.

**Interview point:** Horizontal scaling is generally preferred for large distributed applications because it allows the system to grow by adding instances.

---

# 6. Availability

## Definition

Availability is the percentage of time a system remains operational and accessible to users.

It measures how often users can successfully access the system.

## Availability formula

```text
Availability (%) =
(Uptime / Total Time) * 100
```

## Availability levels

| Availability | Approximate downtime per year |
|---|---:|
| 99% | 3 days 15 hours 36 minutes |
| 99.9% | 8 hours 45 minutes 36 seconds |
| 99.95% | 4 hours 22 minutes 48 seconds |
| 99.99% | 52 minutes 34 seconds |
| 99.999% | 5 minutes 15 seconds |

## Example

Suppose an application runs for 30 days.

Total time = 720 hours.

Downtime = 1 hour.

```text
Availability =
(719 / 720) * 100

= 99.86%
```

## How to improve availability?

1. Deploy multiple application instances.
2. Use load balancers.
3. Configure database replication.
4. Implement health checks.
5. Use automatic failover.
6. Deploy across multiple availability zones.
7. Monitor infrastructure continuously.

**Example:** If one Spring Boot instance crashes, the load balancer routes incoming requests to healthy instances.

**Interview point:** Availability focuses on whether users can access the system when they need it.

---

# 7. Reliability

## Definition

Reliability is the ability of a system to perform its intended operations correctly and consistently over a period of time.

A system can be available but unreliable.

## Example

Consider a banking application.

A user transfers ₹10,000.

The application returns:

```text
Payment Successful
```

But the receiver does not receive the money.

The application was available, but the operation was unreliable.

Reliability requires:

- Correct business processing.
- Accurate data.
- Consistent operations.
- Proper error handling.
- Recovery from failures.

## How to improve reliability?

1. Database transactions.
2. Data validation.
3. Idempotent APIs.
4. Retry mechanisms.
5. Message acknowledgments.
6. Automated testing.
7. Monitoring and alerting.

## Availability vs Reliability

| Availability | Reliability |
|---|---|
| System is accessible | System works correctly |
| Focuses on uptime | Focuses on correct operations |
| Measures operational availability | Measures failure-free operation |
| Example: API responds | Example: API processes payment correctly |

**Interview point:** Availability means the system is accessible; reliability means it performs its intended function correctly.

---

# 8. Fault Tolerance

## Definition

Fault Tolerance is the ability of a system to continue operating even when one or more components fail.

The goal is to prevent individual component failures from bringing down the entire system.

## Example

Consider an application with three servers.

```text
             Load Balancer
                   |
          +--------+--------+
          |        |        |
          v        v        v
        App 1    App 2    App 3
        DOWN     ACTIVE   ACTIVE
```

If App 1 fails, App 2 and App 3 continue serving requests.

The application remains operational.

## How to achieve fault tolerance?

- Multiple application instances.
- Database replication.
- Message queues.
- Automatic failover.
- Circuit breakers.
- Retry mechanisms.
- Redundant network components.

## Java Spring Boot example

Using Resilience4j Circuit Breaker:

```java
@CircuitBreaker(
    name = "paymentService",
    fallbackMethod = "paymentFallback"
)
public String processPayment() {
    return paymentClient.makePayment();
}

public String paymentFallback(Exception ex) {
    return "Payment service temporarily unavailable";
}
```

The circuit breaker can prevent repeated calls to an unhealthy downstream service.

Note: A fallback must not falsely report that a payment succeeded. Payment state should be verified or marked as pending/unknown where appropriate.

**Interview point:** Fault tolerance ensures that failures in individual components do not cause total system failure.

---

# 9. Resilience

## Definition

Resilience is the ability of a system to recover from failures and return to normal operation.

Fault tolerance focuses on continuing operation during failures, whereas resilience also emphasizes recovery and adaptation.

## Example

Consider an e-commerce application.

The payment service becomes temporarily unavailable.

A resilient system:

1. Detects payment service failure.
2. Stops sending excessive requests.
3. Marks uncertain transactions appropriately.
4. Retries eligible operations safely.
5. Recovers when the payment service becomes healthy.
6. Reconciles pending transactions.

## Important resilience patterns

| Pattern | Purpose |
|---|---|
| Retry | Retry temporary failures |
| Circuit Breaker | Prevent repeated calls to failing services |
| Timeout | Avoid waiting indefinitely |
| Bulkhead | Isolate failures between components |
| Fallback | Provide an alternative response |
| Rate Limiting | Control excessive requests |
| Health Checks | Detect unhealthy instances |

**Interview point:** Resilience is about absorbing failures, recovering from them, and restoring normal functionality.

---

# 10. Maintainability

## Definition

Maintainability is the ease with which a software system can be modified, debugged, tested, and maintained over time.

A maintainable application allows developers to introduce changes without breaking unrelated functionality.

## Example

Imagine a Spring Boot monolithic application with 100,000 lines of code.

All business logic is placed inside a single service class.

Changing payment logic may accidentally affect order management.

This application has poor maintainability.

A better structure:

```text
src/main/java
    |
    +--- controller
    |
    +--- service
    |
    +--- repository
    |
    +--- entity
    |
    +--- dto
    |
    +--- config
    |
    +--- exception
```

## How to improve maintainability?

- Follow SOLID principles.
- Use separation of concerns.
- Apply clean code practices.
- Write unit and integration tests.
- Use meaningful naming conventions.
- Maintain proper documentation.
- Avoid unnecessary dependencies.
- Follow consistent coding standards.

**Interview point:** Maintainability reduces the effort, risk, and cost of modifying software throughout its lifecycle.

---

# 11. Extensibility

## Definition

Extensibility is the ability to add new features or capabilities to an existing system without requiring major architectural changes.

## Example

Consider an e-commerce payment system.

Initially, it supports:

- Credit Card
- Debit Card

Later, the business wants to support:

- UPI
- Net Banking
- Wallets
- International payments

A poorly designed system may require modifications across multiple classes.

A better approach is to use the Strategy Design Pattern.

```java
public interface PaymentStrategy {
    void pay(double amount);
}
```

```java
public class UpiPayment implements PaymentStrategy {

    @Override
    public void pay(double amount) {
        System.out.println("UPI payment: " + amount);
    }
}
```

```java
public class CardPayment implements PaymentStrategy {

    @Override
    public void pay(double amount) {
        System.out.println("Card payment: " + amount);
    }
}
```

New payment methods can be added by implementing the interface.

## Maintainability vs Extensibility

| Maintainability | Extensibility |
|---|---|
| Easy to modify existing code | Easy to add new functionality |
| Focuses on maintenance | Focuses on future growth |
| Fixing existing bugs | Adding new payment methods |

**Interview point:** Extensibility helps a system evolve as business requirements change.

---

# 12. Performance

## Definition

Performance describes how efficiently a system processes requests and uses resources.

Important performance metrics include:

- Response time.
- Latency.
- Throughput.
- CPU utilization.
- Memory utilization.
- Database query execution time.
- Network utilization.

## Example

Consider a product search API.

Request:

```http
GET /api/products/101
```

Response time:

```text
Database query       : 300 ms
Business logic       : 100 ms
Network overhead     : 100 ms
Total                : 500 ms
```

After optimization:

```text
Redis cache          : 10 ms
Business logic       : 20 ms
Network overhead     : 20 ms
Total                : 50 ms
```

## How to improve application performance?

1. Use Redis caching.
2. Optimize database queries.
3. Add appropriate indexes.
4. Use connection pooling.
5. Reduce unnecessary API calls.
6. Use pagination.
7. Implement asynchronous processing.
8. Optimize JVM memory and garbage collection.
9. Use CDN for static content.
10. Apply efficient algorithms and data structures.

**Interview point:** Performance optimization should be based on actual bottleneck measurements rather than assumptions.

---

# 13. Latency vs Throughput

These are two important performance metrics.

## Latency

Latency is the time taken to complete a request or operation.

Example:

```text
User sends API request
        |
        v
Server processes request
        |
        v
Response received

Total time = 100 ms
```

The latency is 100 milliseconds.

## Throughput

Throughput is the number of requests or operations a system processes within a given time.

Example:

```text
Application processes:

1,000 requests per second
```

Throughput = 1,000 requests/second.

## Comparison

| Latency | Throughput |
|---|---|
| Time taken per operation | Operations completed per unit time |
| Measured in ms or seconds | Measured in requests/sec |
| Focuses on speed of individual requests | Focuses on processing capacity |
| Lower is generally better | Higher is generally better |

## Real-world example

Suppose an API has:

- Average latency = 100 ms.
- Throughput = 5,000 requests/second.

It means individual requests take approximately 100 ms on average, while the system processes 5,000 requests every second.

**Important:** High throughput does not automatically mean low latency.

A system may process thousands of requests per second while individual requests experience high delays.

Also, Little's Law connects these metrics:

```text
L = λ × W
```

Where:

- L = Average number of requests in the system.
- λ = Average throughput.
- W = Average time spent in the system.

---

# 14. Vertical Scaling vs Horizontal Scaling

This concept is closely related to scalability.

## Vertical Scaling

Increase the capacity of an existing machine.

```text
Before:
+------------------+
| Server           |
| 4 CPU            |
| 8 GB RAM         |
+------------------+

After:
+------------------+
| Server           |
| 16 CPU           |
| 64 GB RAM        |
+------------------+
```

Advantages:
- Easy implementation.
- Less distributed-system complexity.
- No additional load balancing required in simple deployments.

Disadvantages:
- Hardware limitations.
- Expensive upgrades.
- Single-server failure risk.

## Horizontal Scaling

Add more machines.

```text
             Load Balancer
                   |
        +----------+----------+
        |          |          |
        v          v          v
     Server 1  Server 2  Server 3
```

Advantages:
- Supports growing traffic.
- Better fault tolerance.
- Supports distributed workloads.
- Easy to add more instances.

Disadvantages:
- Requires distributed coordination.
- Requires load balancing.
- Session management becomes important.
- Database scaling may become challenging.

## Comparison

| Vertical | Horizontal |
|---|---|
| Scale Up | Scale Out |
| Increase machine resources | Add more machines |
| Hardware limit exists | Can add machines as required |
| Simpler architecture | More complex architecture |
| Potential single point of failure | Supports redundancy when properly designed |

**Interview point:** Stateless services behind a load balancer are particularly suitable for horizontal scaling.

---

# 15. Stateless vs Stateful Applications

## Stateless Application

A stateless application does not store client-specific session information in its application instance between requests.

Each request contains the information required to process it, or retrieves the required state from an external shared system.

Example:

```text
Request 1 --> Server 1
Request 2 --> Server 2
Request 3 --> Server 3
```

Any server can process the request.

### Example: Spring Boot REST API

```java
@RestController
@RequestMapping("/users")
public class UserController {

    @GetMapping("/{id}")
    public String getUser(@PathVariable Long id) {
        return "User ID: " + id;
    }
}
```

The controller does not maintain client-specific session state between requests.

## Stateful Application

A stateful application maintains session or interaction state across requests.

Example:

```text
User Session A --> Server 1
User Session A --> Server 1
User Session A --> Server 1
```

The user may need to reach the same server if session state is stored locally.

### Example

An application storing shopping cart data inside an HTTP session on one server.

## Comparison

| Stateless | Stateful |
|---|---|
| No client session state in application instance | Maintains session state |
| Easier horizontal scaling | Requires session management |
| Any healthy server can process a request | May depend on session location |
| Easier recovery | State recovery is more complicated |
| Example: Stateless REST API | Example: Server-local HTTP session |

## How to manage state in distributed applications?

Use shared external storage:

- Redis.
- Database.
- Distributed cache.
- Shared session store.

Example:

```text
          Load Balancer
                |
        +-------+-------+
        |               |
        v               v
     Server 1         Server 2
        |               |
        +-------+-------+
                |
                v
              Redis
                |
                v
              Session
```

**Interview point:** Stateless application instances are easier to scale horizontally because session data is not tied to a particular server.

---

# 16. Single Point of Failure (SPOF)

## Definition

A Single Point of Failure is a component whose failure can cause the entire system or a critical part of the system to stop functioning.

## Example

Consider:

```text
          Users
            |
            v
       Application
            |
            v
        Database
```

If the database crashes, the application cannot process database-dependent operations.

The database is a potential single point of failure.

## Other examples

- One application server.
- One load balancer without redundancy.
- One database primary without failover.
- One network gateway.
- One availability zone.
- One centralized authentication service.

## How to eliminate SPOF?

1. Deploy multiple application instances.
2. Use redundant load balancers.
3. Configure database replicas and failover.
4. Deploy across multiple availability zones.
5. Use redundant network infrastructure.
6. Implement disaster recovery.

Improved architecture:

```text
             Users
               |
               v
        Redundant Load Balancer
               |
         +-----+-----+
         |           |
         v           v
       App 1       App 2
         |           |
         +-----+-----+
               |
               v
        Database Cluster
          |         |
          v         v
        Primary   Replica
```

**Interview point:** Identify and remove single points of failure when designing highly available systems.

---

# 17. Bottlenecks

## Definition

A bottleneck is a component that limits the overall performance or throughput of a system.

Even if other components are fast, one slow component can reduce the performance of the entire application.

## Example

Consider a Spring Boot application.

```text
Client
  |
  v
Load Balancer
  |
  v
Spring Boot API
  |
  v
Database
```

Assume:

| Component | Processing Time |
|---|---:|
| Network | 10 ms |
| Spring Boot logic | 20 ms |
| Database query | 800 ms |
| Serialization | 10 ms |

The database query is the major bottleneck.

## Common bottlenecks

| Component | Possible bottleneck |
|---|---|
| CPU | Expensive calculations |
| Memory | Memory leaks, excessive allocation |
| Database | Slow queries, missing indexes |
| Network | High latency, limited bandwidth |
| Thread pool | Thread exhaustion |
| Connection pool | Database connection exhaustion |
| Cache | Low cache hit ratio |
| External API | Slow downstream service |

## How to identify bottlenecks?

1. Monitor API response times.
2. Analyze application logs.
3. Use distributed tracing.
4. Analyze database execution plans.
5. Monitor CPU and memory.
6. Monitor JVM garbage collection.
7. Check thread pool utilization.
8. Use load testing tools such as JMeter or Gatling.

**Interview point:** Measure the system, identify the actual bottleneck, optimize it, and measure again.

---

# 18. CAP Theorem

## Definition

The CAP theorem explains trade-offs in distributed data systems during a network partition.

CAP stands for:

- C – Consistency
- A – Availability
- P – Partition Tolerance

## 1. Consistency

Every successful read receives the latest committed value, according to the system's consistency guarantee.

Example:

A bank account has ₹10,000.

After withdrawing ₹2,000:

Every read should return ₹8,000 once the transaction has successfully committed under strong consistency.

## 2. Availability

Every request received by a non-failing node receives a non-error response, even if it cannot guarantee the latest data.

## 3. Partition Tolerance

The system continues operating despite communication failures between distributed nodes.

Example:

Two database nodes are located in different data centers.

A network failure prevents them from communicating.

This is a network partition.

## CAP trade-off

During a network partition, a distributed system must choose between:

- Consistency (C)
- Availability (A)

Partition tolerance (P) is generally necessary in distributed systems that must operate despite network failures.

### CP System

Prioritizes consistency over availability during a partition.

Example:

A distributed database may reject or delay some requests if it cannot guarantee consistent data.

### AP System

Prioritizes availability over strong consistency during a partition.

Example:

A distributed shopping-cart system may accept updates in different regions and reconcile them later.

## Diagram

```text
              CAP Theorem
                  |
          Network Partition
                  |
          +-------+-------+
          |               |
          v               v
       CP System        AP System
          |               |
    Consistency       Availability
    prioritized       prioritized
```

**Important interview clarification:**

CAP does NOT mean you can choose any two properties at all times.

The trade-off becomes relevant when a network partition occurs.

Without a partition, a system may provide both consistency and availability.

**Interview point:** CAP describes the trade-off between consistency and availability during network partitions in distributed systems.

---

# 19. PACELC Theorem

## Definition

PACELC extends the CAP theorem by explaining trade-offs that exist even when there is no network partition.

PACELC stands for:

- P – Partition
- A – Availability
- C – Consistency
- E – Else
- L – Latency
- C – Consistency

## Explanation

PACELC says:

```text
If Partition (P):
    Choose between Availability (A)
    and Consistency (C)

Else (E):
    Choose between Latency (L)
    and Consistency (C)
```

## During network partition

The system must decide whether to prioritize:

- Availability.
- Consistency.

## During normal operation

The system may choose between:

- Lower latency.
- Stronger consistency.

## Example

Consider a distributed database with nodes in India and the USA.

A user updates a record in India.

To guarantee strong consistency, the system may need to coordinate with another replica.

This coordination can increase latency.

Alternatively, the system may return quickly using a nearby replica, potentially providing temporarily stale data.

## CAP vs PACELC

| CAP | PACELC |
|---|---|
| Focuses on partition scenarios | Covers partition and normal operation |
| A vs C during partition | A vs C during partition |
| Does not explicitly describe normal-operation trade-offs | Explains latency vs consistency when no partition exists |

**Interview point:** PACELC provides a more complete way to reason about distributed database trade-offs.

---

# 20. ACID Properties

## Definition

ACID properties ensure reliable transaction processing in relational databases.

ACID stands for:

- Atomicity
- Consistency
- Isolation
- Durability

## 1. Atomicity

A transaction must either complete entirely or not happen at all.

Example:

Bank transfer of ₹1,000 from Account A to Account B.

```text
Account A = ₹5,000
Account B = ₹2,000

Transfer ₹1,000

Account A = ₹4,000
Account B = ₹3,000
```

Both operations must succeed together.

If the credit operation fails, the debit must be rolled back.

## 2. Consistency

A transaction must preserve database rules and constraints.

Example:

A bank account balance cannot become negative if the database/business rules prohibit it.

## 3. Isolation

Concurrent transactions should not interfere with each other in a way that violates the configured isolation guarantees.

Example:

Two users attempt to purchase the last available ticket.

The system must prevent both transactions from successfully booking the same seat.

Important isolation concepts:

- Dirty Read.
- Non-repeatable Read.
- Phantom Read.
- Serializable execution.

Common SQL isolation levels:

1. Read Uncommitted.
2. Read Committed.
3. Repeatable Read.
4. Serializable.

## 4. Durability

Once a transaction is successfully committed, its changes should survive failures such as a database restart.

Database systems use transaction logs and persistent storage mechanisms to provide durability.

## Java Spring Boot example

```java
@Service
public class TransferService {

    @Transactional
    public void transfer(
            Long fromAccount,
            Long toAccount,
            double amount) {

        debit(fromAccount, amount);

        credit(toAccount, amount);
    }
}
```

If an eligible runtime exception occurs, Spring rolls back the transaction by default.

Production implementations should use appropriate decimal types, such as BigDecimal, for monetary values.

## ACID summary

| Property | Meaning | Example |
|---|---|---|
| Atomicity | All or nothing | Debit and credit together |
| Consistency | Valid database state | Balance constraints |
| Isolation | Safe concurrent execution | Prevent double booking |
| Durability | Committed data persists | Transaction survives restart |

**Interview point:** ACID properties are fundamental to reliable transactional operations in relational databases.

---

# 21. BASE Properties

## Definition

BASE is a model commonly associated with highly available distributed systems that prioritize availability and scalability while allowing temporary data inconsistency.

BASE stands for:

- Basically Available.
- Soft State.
- Eventual Consistency.

## 1. Basically Available

The system attempts to remain available even when some components fail.

Example:

A social media application allows users to view cached posts even if one backend service is temporarily unavailable.

## 2. Soft State

The system's state may change over time even without new user input because distributed updates and background synchronization can happen asynchronously.

Example:

A product inventory value may temporarily differ between two replicas while synchronization is pending.

## 3. Eventual Consistency

If no new updates occur and replication continues successfully, all replicas will eventually converge to the same value.

Example:

A user updates their profile picture.

One server immediately displays the new picture.

Another server may display the old picture temporarily.

After synchronization, both display the new picture.

## ACID vs BASE

| ACID | BASE |
|---|---|
| Strong transactional guarantees | Relaxed consistency model |
| Common in relational databases | Common in distributed systems |
| Transaction-focused | Availability and scalability-focused |
| Immediate consistency within transaction guarantees | Temporary inconsistency may be accepted |
| Example: Bank transfer | Example: Social media feed |

**Important:** BASE does not mean that a system has no consistency guarantees. It means the system may accept temporary inconsistency in exchange for other distributed-system properties.

**Interview point:** ACID is suitable when strict transactional guarantees are required; BASE-style approaches are useful when availability and distributed scalability are important.

---

# 22. Consistency Models

## Definition

Consistency models define the rules that determine when updates become visible to different users or nodes in a distributed system.

They specify what a client can expect when reading data after updates.

## Important consistency models

### 1. Strong Consistency

Every read returns the latest committed write according to the system's consistency guarantee.

Example:

A banking account is updated from ₹10,000 to ₹8,000.

A subsequent strongly consistent read returns ₹8,000.

### 2. Eventual Consistency

Updates may take time to propagate across replicas, but replicas eventually converge if no further updates occur.

Example:

A social media profile picture may take a few seconds to update everywhere.

### 3. Read-Your-Writes Consistency

A user always sees their own successful updates in subsequent reads.

Example:

A user changes their name from Naren to Arun.

When the same user refreshes their profile, they should see the updated name.

### 4. Monotonic Reads Consistency

Once a user has seen a particular version of data, subsequent reads should not return an older version.

Example:

A user sees version 5 of a post.

Subsequent reads should not show version 4.

### 5. Causal Consistency

Operations that are causally related must be observed in the correct order.

Example:

A user creates a post.

Another user comments on that post.

A reader should not see the comment before the post it refers to.

## Comparison

| Model | Main guarantee | Example |
|---|---|---|
| Strong | Latest committed data | Bank balance |
| Eventual | Replicas eventually converge | Social media likes |
| Read-your-writes | User sees own updates | Profile update |
| Monotonic reads | No going backward in versions | Feed updates |
| Causal | Related events preserve order | Post and comment |

**Interview point:** Consistency models help architects choose the appropriate data guarantees for different business requirements.

---

# 23. Strong Consistency vs Eventual Consistency

## Strong Consistency

After a successful write, subsequent reads under the consistency guarantee return the latest committed value.

Example:

```text
Initial balance = ₹10,000

Write:
Balance = ₹8,000

Read from Node 1 = ₹8,000
Read from Node 2 = ₹8,000
```

The system coordinates updates to provide strong consistency.

Advantages:
- Easier reasoning about data.
- Suitable for financial operations.
- Reduces stale reads.

Disadvantages:
- Coordination may increase latency.
- Availability may be reduced during network partitions.

## Eventual Consistency

Updates propagate asynchronously across replicas.

Example:

```text
Initial value = 100

Write:
Value = 200

Node 1 = 200
Node 2 = 100
Node 3 = 100

After replication:

Node 1 = 200
Node 2 = 200
Node 3 = 200
```

Advantages:
- Supports distributed availability.
- Can reduce coordination overhead.
- Suitable for geographically distributed systems.

Disadvantages:
- Temporary stale reads.
- Conflict resolution may be required.
- Application logic becomes more complex.

## Comparison

| Strong Consistency | Eventual Consistency |
|---|---|
| Latest committed data is visible | Temporary stale data is possible |
| Requires stronger coordination | Often uses asynchronous replication |
| Can increase latency | Can reduce coordination latency |
| Suitable for financial operations | Suitable for social feeds and analytics |
| More restrictive during partitions | Can prioritize availability during partitions |

**Interview point:** Choose consistency based on business requirements. A payment system and a social media like counter do not necessarily need the same consistency model.

---

# 24. Durability

## Definition

Durability ensures that once data is successfully committed, it remains persisted even after failures.

It is one of the ACID properties.

## Example

A user transfers ₹5,000.

The banking application confirms:

```text
Transaction Successful
```

Immediately after confirmation, the database server restarts.

After recovery, the committed transaction should still exist.

## How databases provide durability

1. Write-Ahead Logging (WAL).
2. Transaction logs.
3. Persistent storage.
4. Database replication.
5. Backups.
6. Recovery mechanisms.

## Durability vs Reliability

| Durability | Reliability |
|---|---|
| Focuses on persistence of committed data | Focuses on correct system operation |
| Transaction-level guarantee | Broader system-level quality |
| Example: Committed payment survives restart | Payment is processed correctly |

**Interview point:** Durability protects committed data against failures and is essential for transactional applications.

---

# 25. Redundancy

## Definition

Redundancy means maintaining duplicate or additional components, resources, or infrastructure so that a backup is available when the primary component fails.

It helps eliminate single points of failure.

## Example

Without redundancy:

```text
Users
  |
  v
Single Server
```

If the server fails, the application becomes unavailable.

With redundancy:

```text
             Load Balancer
                   |
          +--------+--------+
          |                 |
          v                 v
       Server 1          Server 2
       Primary            Backup
```

If Server 1 fails, Server 2 can continue processing requests.

## Types of redundancy

| Type | Example |
|---|---|
| Server redundancy | Multiple application instances |
| Database redundancy | Primary and replica |
| Network redundancy | Multiple network paths |
| Storage redundancy | RAID |
| Geographic redundancy | Multiple regions |
| Power redundancy | Backup power supply |

## Active-Active vs Active-Passive

### Active-Active

Multiple components process requests simultaneously.

```text
          Load Balancer
                |
         +------+------+
         |             |
         v             v
       Server 1      Server 2
       ACTIVE        ACTIVE
```

### Active-Passive

One component handles normal traffic while another waits for failover.

```text
       Server 1
        ACTIVE
           |
       Server 2
        STANDBY
```

**Interview point:** Redundancy improves availability and fault tolerance, but it introduces infrastructure and operational costs.

---

# 26. Disaster Recovery (DR)

## Definition

Disaster Recovery is the process of restoring applications, infrastructure, and data after a major disruption.

Disasters may include:

- Data center failures.
- Regional cloud outages.
- Cyberattacks.
- Ransomware.
- Database corruption.
- Accidental deletion.
- Hardware failures.

## Example

Consider a banking application hosted in the Mumbai region.

A major regional outage occurs.

The organization needs to recover the application in another region, such as Hyderabad.

```text
          Primary Region
             Mumbai
                |
         Application + DB
                |
                v
          Data Replication
                |
                v
          DR Region
          Hyderabad
                |
          Standby System
```

## Disaster recovery strategies

### 1. Backup and Restore

- Regularly back up data.
- Restore infrastructure when disaster occurs.
- Usually has higher recovery time.

### 2. Pilot Light

- Keep essential infrastructure running.
- Scale up additional resources during disaster.

### 3. Warm Standby

- Maintain a smaller but functional duplicate environment.
- Scale it up during failover.

### 4. Hot Standby / Multi-Site

- Maintain a fully operational secondary environment.
- Supports faster recovery.
- More expensive.

## Comparison

| Strategy | Cost | Recovery Speed |
|---|---|---|
| Backup and Restore | Low | Slow |
| Pilot Light | Moderate | Moderate |
| Warm Standby | Higher | Faster |
| Hot Standby | High | Very fast |

**Interview point:** Disaster recovery protects business continuity when major failures make the primary infrastructure unavailable.

---

# 27. RTO and RPO

These are two critical disaster recovery metrics.

## RTO – Recovery Time Objective

RTO defines the maximum acceptable time required to restore a system after a failure.

Example:

A banking application has an RTO of 30 minutes.

If the system goes down at 10:00 AM, it should be restored by 10:30 AM.

## RPO – Recovery Point Objective

RPO defines the maximum acceptable amount of data loss measured in time.

Example:

A system has an RPO of 5 minutes.

If a disaster occurs at 10:00 AM, the organization should be able to recover data up to at least 9:55 AM.

This means up to 5 minutes of recent data loss is acceptable.

## Visual example

```text
Timeline:

9:55 AM          10:00 AM          10:30 AM
   |                 |                 |
   v                 v                 v
Last recoverable   Disaster         System
data point         occurs           restored

       RPO = 5 minutes
       RTO = 30 minutes
```

## RTO vs RPO

| RTO | RPO |
|---|---|
| Recovery Time Objective | Recovery Point Objective |
| Maximum acceptable downtime | Maximum acceptable data loss |
| Measured in time to restore service | Measured in age of recoverable data |
| Example: 30 minutes | Example: 5 minutes |

## Real-world example

Consider an e-commerce application.

- RTO = 1 hour.
- RPO = 15 minutes.

This means:
- The system should be restored within one hour.
- The organization can tolerate losing up to 15 minutes of recent data.

**Interview point:** RTO answers "How quickly must we recover?" RPO answers "How much data can we afford to lose?"

---

# 28. SLI, SLO and SLA

These three concepts are important in Site Reliability Engineering (SRE).

## 1. SLI – Service Level Indicator

SLI is a measurable metric used to evaluate the actual performance or reliability of a service.

Examples:

- Availability.
- Request latency.
- Error rate.
- Throughput.
- Successful request percentage.

Example:

An API receives 100,000 requests.

99,000 requests succeed.

```text
SLI =
Successful Requests / Total Requests * 100

= 99%
```

The API availability/success-rate indicator is 99% for this measurement window.

## 2. SLO – Service Level Objective

SLO is the target value that a service aims to achieve for a particular SLI.

Example:

```text
SLI:
Actual API success rate = 99.5%

SLO:
Target API success rate = 99.9%
```

The team must improve the service to meet its objective.

## 3. SLA – Service Level Agreement

SLA is a formal agreement between a service provider and its customer that defines expected service levels and potentially the consequences or remedies if those levels are not met.

Example:

A cloud provider guarantees:

```text
Monthly availability = 99.9%

If the availability falls below
the agreed threshold, customers
may qualify for service credits.
```

The actual terms depend on the contract.

## SLI vs SLO vs SLA

| Concept | Meaning | Example |
|---|---|---|
| SLI | Actual measured performance | 99.95% availability |
| SLO | Internal/service target | 99.99% availability |
| SLA | Contractual commitment | 99.9% availability with service credits |

## Real-world example

Consider a Spring Boot payment service.

```text
SLI:
Actual successful payment API requests = 99.95%

SLO:
Target successful payment API requests = 99.99%

SLA:
Contractually guaranteed availability = 99.9%
```

The engineering team monitors SLI metrics to determine whether the SLO is being achieved.

The SLA defines the commitment made to customers.

## Error Budget

An error budget is the amount of unreliability allowed by an SLO over a specific period.

Example:

SLO = 99.9%.

Allowed error budget = 0.1%.

For a 30-day period:

```text
Total minutes = 43,200

Error budget =
43,200 * 0.001

= 43.2 minutes
```

The service can experience approximately 43.2 minutes of unavailability while still meeting its 99.9% availability objective.

**Interview point:** SLI measures actual performance, SLO defines the target, and SLA defines the contractual commitment.

---

# Final Revision – All 28 Concepts

| # | Concept | One-Line Explanation |
|---|---|---|
| 1 | System Design | Designing the architecture and interactions of a software system |
| 2 | Why System Design | Building scalable, reliable, available applications |
| 3 | Functional Requirements | What the system should do |
| 4 | Non-Functional Requirements | How well the system should work |
| 5 | Scalability | Ability to handle increasing workloads |
| 6 | Availability | Percentage of time the system remains accessible |
| 7 | Reliability | Ability to perform operations correctly |
| 8 | Fault Tolerance | Continue working despite component failures |
| 9 | Resilience | Recover from failures and restore normal operation |
| 10 | Maintainability | Ease of modifying and maintaining software |
| 11 | Extensibility | Ease of adding new functionality |
| 12 | Performance | Efficiency of processing requests |
| 13 | Latency vs Throughput | Time per operation vs operations per second |
| 14 | Vertical vs Horizontal Scaling | Increase machine resources vs add machines |
| 15 | Stateless vs Stateful | Application-local session state vs maintained session state |
| 16 | SPOF | Component whose failure disrupts the system |
| 17 | Bottleneck | Component limiting overall system performance |
| 18 | CAP | Consistency vs availability during network partition |
| 19 | PACELC | CAP trade-offs plus latency vs consistency during normal operation |
| 20 | ACID | Transactional database guarantees |
| 21 | BASE | Basically Available, Soft State, Eventual Consistency |
| 22 | Consistency Models | Rules governing data visibility |
| 23 | Strong vs Eventual Consistency | Latest committed reads vs eventual replica convergence |
| 24 | Durability | Committed data survives failures |
| 25 | Redundancy | Duplicate resources for backup and failover |
| 26 | Disaster Recovery | Restoring services after major disruption |
| 27 | RTO and RPO | Maximum recovery time vs maximum acceptable data loss |
| 28 | SLI, SLO and SLA | Measurement vs target vs contractual agreement |

---

# Important Product-Based Interview Questions

Make sure you can answer these questions without looking at the notes.

1. What is the difference between scalability and performance?
2. What is the difference between availability and reliability?
3. What is the difference between fault tolerance and resilience?
4. Why is horizontal scaling preferred for large distributed applications?
5. How do you make a Spring Boot application stateless?
6. What happens if the primary database fails?
7. How do you identify a single point of failure?
8. Explain CAP theorem with a real-world example.
9. What is the difference between CAP and PACELC?
10. Explain ACID properties with a bank transfer example.
11. What is the difference between ACID and BASE?
12. When would you choose eventual consistency?
13. What is the difference between strong and eventual consistency?
14. How do Redis and caching improve performance?
15. What is the difference between RTO and RPO?
16. Explain SLI, SLO, SLA and error budget.
17. How would you design a highly available Spring Boot application?
18. How would you handle 100,000 concurrent requests?
19. How would you prevent database overload?
20. How would you design a system that continues operating when a downstream service fails?

---

# Recommended Learning Order

For System Design interviews, learn the concepts in this order:

**Phase 1 – Foundations**
1. System Design
2. Functional and Non-Functional Requirements
3. Scalability
4. Availability
5. Reliability
6. Performance
7. Latency and Throughput

**Phase 2 – Architecture and Failure Handling**
8. Vertical and Horizontal Scaling
9. Stateless and Stateful Applications
10. SPOF
11. Bottlenecks
12. Fault Tolerance
13. Resilience
14. Redundancy
15. Disaster Recovery

**Phase 3 – Distributed Systems and Databases**
16. CAP Theorem
17. PACELC
18. ACID
19. BASE
20. Consistency Models
21. Strong vs Eventual Consistency
22. Durability

**Phase 4 – Production and Reliability Engineering**
23. Maintainability
24. Extensibility
25. RTO and RPO
26. SLI, SLO and SLA

---

# Practical Implementation for Java Developers

Try connecting these concepts to the following technologies:

| Concept | Technology / Implementation |
|---|---|
| Scalability | Kubernetes, Docker, AWS Auto Scaling |
| Load Distribution | NGINX, AWS ALB |
| Stateless Services | Spring Boot REST APIs |
| Caching | Redis |
| Database Performance | MySQL, PostgreSQL, Indexing |
| Fault Tolerance | Resilience4j |
| Asynchronous Processing | Kafka, RabbitMQ |
| Database Transactions | Spring @Transactional |
| Monitoring | Prometheus, Grafana |
| Distributed Tracing | OpenTelemetry |
| API Rate Limiting | Redis, Bucket4j |
| Disaster Recovery | Database Backups, Multi-Region Deployment |

**Final preparation advice:** Do not just memorize definitions. For every concept, prepare one real-world example, one architectural diagram, and one Java/Spring Boot implementation scenario. This is what helps you move from theoretical knowledge to a strong 5+ YOE System Design interview answer.