# MODULE 4 — Load Balancing and Traffic Management

## Navigation Table

| Level | Topics | Priority |
|---|---|---|
| 1 | Load Balancer: Definition, Need and Working | ⭐⭐⭐⭐⭐ |
| 2 | Layer 4 vs Layer 7 Load Balancing | ⭐⭐⭐⭐⭐ |
| 3 | Reverse Proxy and Nginx | ⭐⭐⭐⭐⭐ |
| 4 | Cloud Load Balancers | ⭐⭐⭐⭐ |
| 5 | Load Balancing Algorithms | ⭐⭐⭐⭐⭐ |
| 6 | Consistent Hashing | ⭐⭐⭐⭐ |
| 7 | Health Checks: Active vs Passive | ⭐⭐⭐⭐⭐ |
| 8 | Sticky Sessions and Session Affinity | ⭐⭐⭐⭐ |
| 9 | Failover | ⭐⭐⭐⭐⭐ |
| 10 | Global Load Balancing | ⭐⭐⭐⭐ |
| 11 | Multi-Region Traffic Routing | ⭐⭐⭐⭐ |
| 12 | Autoscaling | ⭐⭐⭐⭐⭐ |
| 13 | Connection Draining | ⭐⭐⭐⭐ |
| 14 | Distributing 1 Million Requests Across Spring Boot Instances | ⭐⭐⭐⭐⭐ |

---

# LEVEL 1 — Load Balancer Fundamentals

## 1. What is a Load Balancer?

A **load balancer** is a component that distributes incoming client traffic across multiple backend servers or application instances.

Instead of sending every request to a single Spring Boot application, a load balancer distributes requests across multiple instances.

### Without a Load Balancer

Imagine an e-commerce application running on a single Spring Boot instance.

```text
             1 Million Requests
                     |
                     v
            Spring Boot Instance
                  Server 1
                     |
                     v
                  Database
```

Problems:

- All requests reach one application instance.
- CPU and memory utilization increase.
- Application response time increases.
- Server failure can make the application unavailable.
- Scaling becomes difficult.

### With a Load Balancer

```text
                Client Traffic
                     |
                     v
                Load Balancer
                     |
          +----------+----------+
          |          |          |
          v          v          v
       Server 1   Server 2   Server 3
       Spring     Spring     Spring
       Boot       Boot       Boot
          |          |          |
          +----------+----------+
                     |
                     v
                  Database
```

The load balancer distributes incoming requests across healthy application instances.

### Real-world example

Suppose an e-commerce website receives 30,000 requests per second.

You have three Spring Boot instances:

| Instance | Requests per second |
|---|---:|
| Server 1 | 10,000 |
| Server 2 | 10,000 |
| Server 3 | 10,000 |
| Total | 30,000 |

This is a simplified equal-distribution example. Actual distribution depends on the algorithm, instance capacity, request processing time, and existing connections.

### Important point

A load balancer does not automatically make an application scalable. You also need sufficient application capacity, database capacity, network bandwidth, and downstream service capacity.

---

## 2. Why is a Load Balancer Required?

Load balancers solve several production problems.

### A. High Availability

If one Spring Boot instance fails, the load balancer can stop sending new requests to that instance.

```text
                 Load Balancer
                      |
           +----------+----------+
           |          |          |
           v          v          v
        Server 1   Server 2   Server 3
           UP        DOWN        UP
           |                     |
           +---------------------+
              Traffic to healthy
                   instances
```

Users can continue accessing the application through healthy instances.

### B. Horizontal Scalability

Instead of increasing the CPU and RAM of one server, you can add more application instances.

```text
Before:

Load Balancer
     |
     v
  App 1

After:

          Load Balancer
                |
       +--------+--------+
       |        |        |
       v        v        v
      App 1    App 2    App 3
```

This is called horizontal scaling.

### C. Performance Improvement

Requests are distributed across multiple instances, reducing the load on individual instances.

### D. Fault Tolerance

When an instance becomes unhealthy, traffic can be redirected to other healthy instances.

### E. Maintenance Without Full Downtime

You can remove an instance from receiving new requests, allow existing requests to finish, and deploy a new version.

This is useful for rolling deployments.

### Interview answer

A load balancer distributes incoming traffic across multiple backend instances to improve availability, scalability, performance, and fault tolerance. It also performs health checks and avoids routing traffic to unhealthy instances.

---

# LEVEL 2 — Types of Load Balancing

## 3. Layer 4 vs Layer 7 Load Balancing

Load balancers can operate at different layers of the OSI networking model.

The two important types are:

- Layer 4 — Transport Layer
- Layer 7 — Application Layer

### Layer 4 Load Balancer

A Layer 4 load balancer distributes traffic based on network and transport information.

It primarily considers:

- Source IP address
- Destination IP address
- Source port
- Destination port
- TCP/UDP protocol

It does not need to inspect the HTTP URL or application-level headers.

Example:

```text
Client
  |
  v
L4 Load Balancer
  |
  +----> Server 1: 10.0.0.1:8080
  |
  +----> Server 2: 10.0.0.2:8080
  |
  +----> Server 3: 10.0.0.3:8080
```

Suppose a client sends traffic to:

```text
https://api.example.com:443
```

The Layer 4 load balancer can distribute the TCP connection to an available backend.

### Layer 7 Load Balancer

A Layer 7 load balancer understands application-level protocols such as HTTP and HTTPS.

It can route requests using:

- URL path
- HTTP method
- Hostname
- Headers
- Cookies

Example:

```text
Client
  |
  v
L7 Load Balancer
  |
  +---- /users/**    ----> User Service
  |
  +---- /orders/**   ----> Order Service
  |
  +---- /payments/** ----> Payment Service
```

Example routing:

```text
GET /users/101
    |
    v
User Service

POST /orders
    |
    v
Order Service

POST /payments
    |
    v
Payment Service
```

### Comparison

| Feature | Layer 4 | Layer 7 |
|---|---|---|
| OSI Layer | Transport | Application |
| Protocol | TCP/UDP | HTTP/HTTPS and other application protocols |
| Routing | IP and port | URL, headers, host, cookies |
| Content inspection | No HTTP inspection | Understands HTTP requests |
| Performance overhead | Generally lower | Generally higher |
| Routing flexibility | Limited | High |
| SSL termination | Implementation-dependent | Commonly supported |
| Example | TCP load balancing | HTTP path-based routing |

### When to use which?

Use Layer 4 when:

- You need efficient TCP/UDP traffic distribution.
- You do not need HTTP-level routing.
- You need to handle high volumes of network connections.

Use Layer 7 when:

- You need URL-based routing.
- You want to route different APIs to different services.
- You need HTTP header or cookie-based routing.
- You need application-aware traffic management.

**Interview point:** Layer 4 works with transport-level information, whereas Layer 7 understands application-level requests and can make routing decisions based on HTTP content.

---

## 4. What is a Reverse Proxy?

A reverse proxy is a server that sits between clients and backend servers.

Clients send requests to the reverse proxy, and the proxy forwards those requests to the appropriate backend.

```text
Client
  |
  v
Reverse Proxy
  |
  +----> Spring Boot Instance 1
  |
  +----> Spring Boot Instance 2
  |
  +----> Spring Boot Instance 3
```

### Forward Proxy vs Reverse Proxy

#### Forward Proxy

A forward proxy acts on behalf of clients.

```text
Client
  |
  v
Forward Proxy
  |
  v
Internet
```

Example: A corporate proxy that controls employees' internet access.

#### Reverse Proxy

A reverse proxy acts on behalf of backend servers.

```text
Internet
   |
   v
Reverse Proxy
   |
   v
Backend Applications
```

Example: Nginx receiving public traffic and forwarding requests to Spring Boot applications.

### Benefits of Reverse Proxy

1. Hides internal backend server addresses.
2. Can distribute traffic.
3. Can terminate SSL/TLS.
4. Can compress responses.
5. Can cache static content.
6. Can apply security rules.
7. Can route requests based on URL paths.

### Is every reverse proxy a load balancer?

No.

A reverse proxy forwards client requests to backend servers. A load balancer distributes traffic across multiple backend servers.

A reverse proxy can also provide load-balancing functionality.

---

## 5. What is Nginx?

Nginx is a high-performance web server that can also work as:

- Reverse proxy
- Load balancer
- HTTP cache
- SSL/TLS termination point

It is commonly used in front of Spring Boot applications.

### Architecture

```text
                 Client Requests
                        |
                        v
                      Nginx
                        |
              +---------+---------+
              |         |         |
              v         v         v
           App 1     App 2     App 3
           :8081     :8082     :8083
              |         |         |
              +---------+---------+
                        |
                        v
                     Database
```

### Nginx Load Balancing Configuration

Example `nginx.conf`:

```nginx
http {

    upstream spring_backend {

        server 127.0.0.1:8081;
        server 127.0.0.1:8082;
        server 127.0.0.1:8083;

    }

    server {

        listen 80;

        server_name api.example.com;

        location / {

            proxy_pass http://spring_backend;

            proxy_set_header Host $host;

            proxy_set_header X-Real-IP $remote_addr;

            proxy_set_header X-Forwarded-For
                             $proxy_add_x_forwarded_for;

            proxy_set_header X-Forwarded-Proto $scheme;

        }

    }

}
```

### Explanation

`upstream spring_backend`

Defines a group of backend application servers.

`server 127.0.0.1:8081`

Represents one Spring Boot instance.

`proxy_pass`

Forwards incoming requests to the backend group.

`proxy_set_header`

Passes client and request information to the backend.

### Nginx Load Balancing Algorithms

Nginx supports several balancing approaches.

**Round Robin (default):**

```nginx
upstream spring_backend {
    server app1:8080;
    server app2:8080;
    server app3:8080;
}
```

**Weighted Round Robin:**

```nginx
upstream spring_backend {
    server app1:8080 weight=5;
    server app2:8080 weight=3;
    server app3:8080 weight=2;
}
```

**Least Connections:**

```nginx
upstream spring_backend {
    least_conn;

    server app1:8080;
    server app2:8080;
    server app3:8080;
}
```

**IP Hash:**

```nginx
upstream spring_backend {
    ip_hash;

    server app1:8080;
    server app2:8080;
    server app3:8080;
}
```

**Important:** Open-source Nginx has limitations around active health checks and some advanced features. Passive failure detection is available through upstream failure parameters. Nginx Plus provides additional active health-check capabilities.

---

## 6. What are Cloud Load Balancers?

Cloud load balancers are managed services provided by cloud platforms to distribute traffic across backend resources.

Instead of maintaining your own load-balancer infrastructure, the cloud provider manages much of the infrastructure, availability, and scaling.

### Major Cloud Load Balancers

| Cloud | Service | Typical use |
|---|---|---|
| AWS | Application Load Balancer (ALB) | HTTP/HTTPS, Layer 7 routing |
| AWS | Network Load Balancer (NLB) | Layer 4 TCP/UDP traffic |
| Azure | Application Gateway | Layer 7 routing and WAF integration |
| Azure | Load Balancer | Layer 4 traffic distribution |
| GCP | Application Load Balancer | HTTP/HTTPS application routing |
| GCP | Network Load Balancer | Network-level traffic distribution |

### Example: AWS Application Load Balancer

```text
              Users
                |
                v
           AWS ALB
                |
        +-------+-------+
        |       |       |
        v       v       v
      EC2-1   EC2-2   EC2-3
      Spring  Spring  Spring
      Boot    Boot    Boot
        |       |       |
        +-------+-------+
                |
                v
              RDS
```

The ALB can:

- Route requests based on URL paths.
- Route using hostnames.
- Perform health checks.
- Distribute traffic across availability zones.
- Integrate with autoscaling groups.

### Example: Path-based routing

```text
api.example.com/users/**   -> User Service
api.example.com/orders/**  -> Order Service
api.example.com/payments/** -> Payment Service
```

### When should you use a cloud load balancer?

Use it when:

- Your application is hosted in a cloud environment.
- You need managed high availability.
- You want health checks and backend registration.
- You need integration with autoscaling.
- You want to avoid maintaining your own load-balancer servers.

---

# LEVEL 3 — Load Balancing Algorithms

## 7. Round Robin

Round Robin distributes incoming requests sequentially across available backend instances.

Suppose there are three Spring Boot instances:

```text
App 1
App 2
App 3
```

Requests are distributed in sequence.

### Example

| Request | Assigned instance |
|---|---|
| Request 1 | App 1 |
| Request 2 | App 2 |
| Request 3 | App 3 |
| Request 4 | App 1 |
| Request 5 | App 2 |
| Request 6 | App 3 |

```text
           Load Balancer
                 |
       +---------+---------+
       |         |         |
       v         v         v
      App 1     App 2     App 3
       ^         ^         ^
       |         |         |
       +---------+---------+
          Sequential
           allocation
```

### Advantages

- Simple to implement.
- Easy to understand.
- Works well when instances have similar capacity.
- Does not require tracking active connections.

### Disadvantages

- Does not consider current server load.
- Does not consider request processing time.
- May overload a slower instance if requests have different processing costs.

### When to use?

Use Round Robin when all backend instances have similar capacity and requests have reasonably similar processing requirements.

---

## 8. Weighted Round Robin

Weighted Round Robin assigns different traffic proportions to backend instances based on their capacity.

Suppose:

| Instance | Weight |
|---|---:|
| App 1 | 5 |
| App 2 | 3 |
| App 3 | 2 |
| Total | 10 |

The approximate traffic distribution is:

| Instance | Traffic percentage |
|---|---:|
| App 1 | 50% |
| App 2 | 30% |
| App 3 | 20% |

If 10,000 requests arrive:

- App 1 receives approximately 5,000.
- App 2 receives approximately 3,000.
- App 3 receives approximately 2,000.

### Why is it required?

Imagine:

- App 1: 8 CPU cores, 16 GB RAM.
- App 2: 4 CPU cores, 8 GB RAM.
- App 3: 2 CPU cores, 4 GB RAM.

Sending equal traffic to every instance may not be efficient.

Weighted Round Robin allows stronger instances to receive more traffic.

### When to use?

Use it when backend instances have different capacities or when you want to gradually shift traffic to a new application version.

---

## 9. Least Connections

Least Connections sends a new request or connection to the backend with the fewest active connections.

Example:

| Instance | Active connections |
|---|---:|
| App 1 | 100 |
| App 2 | 50 |
| App 3 | 80 |

The next connection is assigned to App 2.

```text
             Load Balancer
                   |
       +-----------+-----------+
       |           |           |
       v           v           v
     App 1       App 2       App 3
      100          50          80
  connections  connections  connections

             New connection
                   |
                   v
                 App 2
```

### Why is it better than Round Robin?

Round Robin only considers request order.

Least Connections considers the current number of active connections.

For example:

- App 1 is processing a large report request.
- App 2 is processing small API requests.
- App 3 is processing a long-running export.

Least Connections may distribute new connections more effectively than simple Round Robin.

### Advantages

- Considers current connection count.
- Useful for long-lived connections.
- Can improve distribution when connection durations vary.

### Limitation

The number of connections is not always equal to actual CPU or memory usage. A single expensive request may consume more resources than many lightweight requests.

---

## 10. IP Hash

IP Hash uses the client's IP address to determine which backend instance receives the request.

A simplified concept is:

```text
hash(client IP) % number of backend instances
```

Example:

```text
Client A -> 192.168.1.10 -> App 1
Client B -> 192.168.1.20 -> App 2
Client C -> 192.168.1.30 -> App 3
```

If the same client IP sends another request, it is generally directed to the same backend while the backend pool remains unchanged.

### Why is it useful?

It can help keep a client's requests on the same application instance.

### Limitations

- Many users may share one public IP due to NAT.
- A large corporate network may concentrate traffic on one instance.
- Backend membership changes can change mappings.
- It does not guarantee session persistence in every topology.

### When to use?

Use IP Hash only when source-IP-based affinity is appropriate and its limitations are acceptable.

---

## 11. Consistent Hashing

Consistent Hashing is a technique used to distribute keys across multiple servers while minimizing key reassignment when servers are added or removed.

It is useful in distributed caching, sharding, and some load-balancing designs.

### Problem with simple modulo hashing

Suppose you have three cache nodes:

```text
hash(key) % 3
```

A key maps to one of the three nodes.

Now add a fourth node:

```text
hash(key) % 4
```

Many keys may map to different nodes. This can cause cache misses and data movement.

### How Consistent Hashing works

Consistent hashing maps both servers and keys onto a logical hash ring.

```text
                 Node A
                   |
            .--------------.
         .                    .
       .                        .
 Node C                          Node B
       .                        .
         .                    .
            '--------------'
```

Each key is assigned to the first server encountered while moving clockwise around the ring.

### Virtual Nodes

A physical server can be represented by multiple virtual nodes on the ring.

```text
Physical Node A
   |
   +-- A1
   +-- A2
   +-- A3
   +-- A4
```

Virtual nodes improve distribution and reduce imbalance.

### Example

Initially:

```text
Node A | Node B | Node C
```

After adding Node D:

```text
Node A | Node B | Node C | Node D
```

Only keys in the affected ranges need to move, rather than remapping most keys.

### Where is it used?

- Distributed caching.
- Distributed key-value stores.
- Database sharding strategies.
- Distributed systems that partition keys across nodes.

### Interview point

Consistent hashing minimizes key redistribution when nodes are added or removed. It is not the same as Round Robin: Round Robin distributes requests sequentially, while consistent hashing maps keys to nodes.

---

# LEVEL 4 — Health Checks and Session Management

## 12. Health Checks

A health check determines whether a backend instance is healthy enough to receive traffic.

Suppose you have three Spring Boot instances:

```text
App 1 -> Healthy
App 2 -> Unhealthy
App 3 -> Healthy
```

The load balancer should stop sending new requests to App 2.

### Spring Boot Health Endpoint

Using Spring Boot Actuator:

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-actuator</artifactId>
</dependency>
```

Health endpoint:

```text
GET /actuator/health
```

Example response:

```json
{
  "status": "UP"
}
```

A load balancer can periodically call the configured health endpoint.

### Types of Health Checks

- Application health: Is the application process responding?
- Readiness: Is the instance ready to receive traffic?
- Liveness: Is the application process alive, or should it be restarted?
- Dependency health: Are important dependencies available?

### Liveness vs Readiness

| Check | Purpose |
|---|---|
| Liveness | Determines whether an application should be restarted |
| Readiness | Determines whether an application should receive traffic |

An application can be alive but not ready.

For example, Spring Boot may have started but still be warming caches or establishing required connections.

**Important:** Do not make every temporary downstream dependency issue automatically fail liveness. That can cause unnecessary restarts or cascading failures.

---

## 13. Active vs Passive Health Checks

### Active Health Checks

The load balancer actively sends periodic requests to backend instances.

Example:

```text
Every 10 seconds:

Load Balancer
     |
     +---- GET /actuator/health ---> App 1
     |
     +---- GET /actuator/health ---> App 2
     |
     +---- GET /actuator/health ---> App 3
```

If App 2 repeatedly fails the health check, it is marked unhealthy.

### Passive Health Checks

The load balancer observes actual production traffic and detects failures from request outcomes.

For example:

- Connection refused.
- Connection timeout.
- Repeated upstream errors.

If an instance repeatedly fails, the load balancer may temporarily stop routing traffic to it.

### Comparison

| Feature | Active | Passive |
|---|---|---|
| How it works | Sends health probes | Observes real traffic |
| Traffic | Separate health-check traffic | Uses actual requests |
| Detection | Can detect failure before user requests | Detects failure when requests encounter it |
| Dependency | Health endpoint or probe | Request outcomes |
| Example | `/actuator/health` | Repeated connection failures |

### When to use?

Use active checks for proactive detection and passive checks for observing real request failures. Many production systems combine both.

---

## 14. Sticky Sessions

Sticky Sessions ensure that requests from a particular client are routed to the same backend instance for a period of time.

Example:

```text
Client A
   |
   v
Load Balancer
   |
   v
App 1

Client A's next request
   |
   v
Load Balancer
   |
   v
App 1
```

Even if App 2 and App 3 are available, Client A continues to be routed to App 1 while the affinity rule remains valid.

### Why are sticky sessions required?

Consider a traditional application that stores session information in application memory.

```text
App 1:
Session A -> User login information

App 2:
No Session A
```

If the user's next request reaches App 2, the application may not find the session.

Sticky sessions can help keep the user on App 1.

### Disadvantages

- Traffic can become uneven.
- Failure of the selected instance can affect the session.
- Scaling and deployments become more complicated.
- It creates a dependency on a particular backend.

### Better approach

For horizontally scalable Spring Boot applications, prefer externalized session storage or stateless authentication where appropriate.

Example:

```text
Spring Boot App 1 ----+
                      |
Spring Boot App 2 ----+---- Redis Session Store
                      |
Spring Boot App 3 ----+
```

All instances can access the shared session data.

---

## 15. Session Affinity

Session affinity means maintaining a relationship between a client session and a particular backend instance.

Sticky sessions are a common implementation of session affinity.

### Sticky Session vs Session Affinity

| Sticky Sessions | Session Affinity |
|---|---|
| Common term for keeping a client on the same backend | Broader concept of associating a session with a backend |
| Often implemented using cookies or source IP | Can use cookies, source IP, or other identifiers |
| A specific technique | General behavior or feature |

### Cookie-based affinity

A load balancer can issue an affinity cookie.

Example:

```text
Client
  |
  | First request
  v
Load Balancer
  |
  v
App 1
  |
  v
Affinity Cookie returned
```

On subsequent requests, the cookie helps the load balancer select App 1.

### Interview point

Session affinity is useful for legacy stateful applications, but stateless services with shared session storage are generally easier to scale and recover.

---

# LEVEL 5 — Failure Handling and Global Traffic

## 16. Failover

Failover is the process of switching traffic or workload from a failed component to a healthy alternative.

A load balancer can perform backend failover by removing unhealthy instances from the available pool.

### Example

Initially:

```text
           Load Balancer
                 |
        +--------+--------+
        |        |        |
        v        v        v
       App 1    App 2    App 3
        UP       UP       UP
```

App 2 fails:

```text
           Load Balancer
                 |
        +--------+--------+
        |                 |
        v                 v
       App 1             App 3
        UP                UP

       App 2 -> DOWN
```

New traffic is routed to App 1 and App 3.

### Types of Failover

**1. Instance-level failover**

Traffic moves from one application instance to another.

**2. Availability-zone failover**

Traffic is routed to healthy instances in another availability zone.

**3. Region-level failover**

Traffic is redirected to another geographical region.

### Important considerations

- Health-check detection time.
- Failover decision time.
- Existing connection handling.
- Database availability.
- Data replication and consistency.
- Recovery and failback strategy.

Failover does not automatically guarantee that in-flight requests succeed. Clients may need safe retries, and write operations need idempotency protection.

---

## 17. Global Load Balancing

Global load balancing distributes client traffic across application deployments in different geographical regions.

It makes routing decisions using factors such as:

- Geographic location.
- Network latency.
- Regional health.
- Traffic policies.
- Regional capacity.

### Example

Suppose an application is deployed in three regions:

```text
             Global Load Balancer
                      |
          +-----------+-----------+
          |           |           |
          v           v           v
       US Region   Europe       India
          |           |           |
          v           v           v
        App A       App B       App C
```

Users may be routed to a suitable healthy region.

Example:

| User location | Preferred region |
|---|---|
| India | Asia region |
| Germany | Europe region |
| USA | US region |

The nearest region is not always selected. Health, capacity, routing policies, and data residency requirements can affect the decision.

### Benefits

- Lower latency for geographically distributed users.
- Regional fault tolerance.
- Better global availability.
- Regional traffic management.

---

## 18. Multi-Region Traffic Routing

Multi-region architecture deploys application instances in multiple geographical regions.

Example:

```text
                    Users
                      |
                      v
              Global Traffic Manager
                      |
             +--------+--------+
             |                 |
             v                 v
          Region A          Region B
          Hyderabad         Singapore
             |                 |
        +----+----+       +----+----+
        |         |       |         |
        v         v       v         v
      App 1     App 2   App 3     App 4
        |         |       |         |
        +----+----+       +----+----+
             |                 |
             v                 v
         Database A <----> Database B
                Replication
```

### Routing strategies

**1. Latency-based routing**

Route users to a region with lower measured network latency.

**2. Geo-based routing**

Route users based on geographic location or policy.

**3. Weighted routing**

Send a specified percentage of traffic to each region.

Example:

| Region | Traffic |
|---|---:|
| Region A | 70% |
| Region B | 30% |

**4. Failover routing**

Send traffic to the secondary region when the primary region becomes unhealthy.

### Active-active vs Active-passive

| Active-active | Active-passive |
|---|---|
| Multiple regions serve traffic | Primary region serves normal traffic |
| Can improve capacity and latency | Secondary region is reserved for failover |
| More complex data consistency | Can be simpler operationally |
| Both regions need production readiness | Secondary must be ready to take over |

### Important database consideration

Running applications in multiple regions is easier than maintaining strongly consistent writes across regions.

You must plan:

- Replication lag.
- Conflict resolution.
- Write ownership.
- Data consistency.
- Failover and recovery.
- Regional data residency.

---

# LEVEL 6 — Autoscaling and Connection Management

## 19. Autoscaling

Autoscaling automatically increases or decreases the number of application instances based on workload and configured policies.

Instead of manually starting more Spring Boot instances during peak traffic, an autoscaling platform can add instances.

### Without Autoscaling

```text
Traffic increases
       |
       v
Existing 3 instances
       |
       v
CPU reaches 95%
       |
       v
Response time increases
```

### With Autoscaling

```text
Traffic increases
       |
       v
Autoscaling detects demand
       |
       v
Adds more instances
       |
       v
Load Balancer registers
new healthy instances
       |
       v
Traffic distributed
```

### Example

Suppose your application starts with three instances.

| Traffic condition | Instance count |
|---|---:|
| Low traffic | 3 |
| Medium traffic | 5 |
| High traffic | 10 |
| Very high traffic | 15 |

These are illustrative numbers, not fixed scaling rules.

### Common scaling metrics

- CPU utilization.
- Memory utilization.
- Requests per second.
- Average response time.
- Queue length.
- Number of active connections.
- Custom application metrics.

### Example: Kubernetes Horizontal Pod Autoscaler

A simplified deployment:

```yaml
apiVersion: apps/v1
kind: Deployment
metadata:
  name: spring-boot-app
spec:
  replicas: 3
  selector:
    matchLabels:
      app: spring-boot-app
  template:
    metadata:
      labels:
        app: spring-boot-app
    spec:
      containers:
        - name: spring-boot-app
          image: my-spring-app:1.0
          ports:
            - containerPort: 8080
```

A simplified HPA:

```yaml
apiVersion: autoscaling/v2
kind: HorizontalPodAutoscaler
metadata:
  name: spring-boot-hpa
spec:
  scaleTargetRef:
    apiVersion: apps/v1
    kind: Deployment
    name: spring-boot-app
  minReplicas: 3
  maxReplicas: 15
  metrics:
    - type: Resource
      resource:
        name: cpu
        target:
          type: Utilization
          averageUtilization: 70
```

When the configured average CPU utilization exceeds the target, Kubernetes can increase the number of pods, subject to its scaling behavior and available resources.

### Scale-out vs Scale-in

| Scale-out | Scale-in |
|---|---|
| Adds instances | Removes instances |
| Used during traffic increase | Used during traffic decrease |
| Increases available capacity | Reduces infrastructure cost |

### Important considerations

- Set minimum and maximum instance counts.
- Use readiness checks before routing traffic to new instances.
- Avoid aggressive scaling that causes frequent instance creation and removal.
- Configure scale-in stabilization.
- Monitor downstream database capacity.
- Ensure application instances are stateless or use shared state.

**Interview point:** Autoscaling changes the number of instances. Load balancing distributes traffic among the available healthy instances. They complement each other.

---

## 20. Connection Draining

Connection draining is the process of stopping new traffic to a backend instance while allowing existing connections or requests to complete before the instance is removed.

It is especially useful during:

- Rolling deployments.
- Autoscaling scale-in.
- Instance maintenance.
- Backend replacement.

### Without Connection Draining

```text
Client request
      |
      v
Load Balancer
      |
      v
Spring Boot App
      |
      v
Instance terminated immediately
```

An in-flight request may fail.

### With Connection Draining

```text
             Load Balancer
                   |
                   v
                App 1
                   |
          Stop accepting new
             connections
                   |
          Existing requests
             complete
                   |
                   v
            Instance removed
```

### Example

Suppose App 1 is processing a payment request that takes 8 seconds.

During deployment, the platform starts removing App 1.

With connection draining:

1. App 1 is marked as unavailable for new traffic.
2. New requests go to App 2 and App 3.
3. The payment request already running on App 1 gets time to finish.
4. App 1 is terminated after the drain period or when its connections complete, according to platform configuration.

### Connection draining vs Health checks

| Health checks | Connection draining |
|---|---|
| Detect whether an instance is healthy | Supports graceful removal of an instance |
| Controls whether traffic should be sent | Controls how existing traffic is handled during removal |
| Used for failure detection | Used for maintenance and scale-in |

### Spring Boot graceful shutdown

Spring Boot supports graceful shutdown.

Example configuration:

```yaml
server:
  shutdown: graceful

spring:
  lifecycle:
    timeout-per-shutdown-phase: 30s
```

This allows the application to stop accepting new requests and gives existing requests time to complete, within the configured shutdown period.

The load balancer or orchestration platform must also coordinate backend deregistration and its own draining timeout.

---

# Load Balancer – Quick Interview Revision Sheet

## 1. Quick Interview Revision

| # | Topic | Key Point to Remember |
|---:|---|---|
| 1 | Load Balancer | Distributes traffic across backend instances |
| 2 | L4 vs L7 | IP/port routing vs HTTP content-based routing |
| 3 | Reverse Proxy | Sits between clients and backend servers |
| 4 | Nginx | Uses upstream groups and routing algorithms |
| 5 | Round Robin | Sequential request distribution |
| 6 | Weighted Round Robin | Distribution based on server weights |
| 7 | Least Connections | Selects server with fewer active connections |
| 8 | Consistent Hashing | Minimizes key redistribution |
| 9 | Health Checks | Detect unhealthy backend instances |
| 10 | Active vs Passive | Dedicated probes vs production traffic monitoring |
| 11 | Sticky Sessions | Maintains client-to-server affinity |
| 12 | Failover | Redirects traffic after component failure |
| 13 | Active-Active | Multiple regions serve traffic |
| 14 | Active-Passive | Primary region with standby region |
| 15 | Autoscaling | Dynamically adds or removes instances |
| 16 | Connection Draining | Allows existing requests to finish |
| 17 | Database Protection | Cache, pooling, replicas, backpressure |
| 18 | Slow Backend | Timeout, circuit breaker, bulkhead |

---

## 2. Important Concepts to Connect Together

These are the combinations interviewers frequently explore in scenario-based questions.

| # | Interview Scenario | Concepts to Explain Together |
|---:|---|---|
| 1 | Application instance crashes | Health Check + Failover |
| 2 | Traffic suddenly increases | Load Balancer + Autoscaling |
| 3 | Database becomes overloaded | HikariCP + Redis + Read Replicas |
| 4 | One backend becomes slow | Timeout + Circuit Breaker + Bulkhead |
| 5 | Application deployment starts | Readiness + Connection Draining + Graceful Shutdown |
| 6 | User gets logged out randomly | Sticky Sessions + Redis Session Store |
| 7 | Entire region goes down | Multi-Region + Global Load Balancer + RTO/RPO |
| 8 | Millions of requests arrive | Horizontal Scaling + Caching + Kafka + Rate Limiting |

---

## 3. Important Interview Keywords

- Load Balancing
- Reverse Proxy
- Nginx
- Round Robin
- Weighted Round Robin
- Least Connections
- Consistent Hashing
- Health Checks
- Failover
- High Availability
- Fault Tolerance
- Sticky Sessions
- Redis Session Management
- Active-Active Architecture
- Active-Passive Architecture
- Autoscaling
- Connection Draining
- Graceful Shutdown
- Circuit Breaker
- Bulkhead Pattern
- Database Connection Pooling
- Read Replicas
- Rate Limiting
- Horizontal Scaling
- RTO and RPO

---

# LEVEL 7 — Distributing 1 Million Requests Across Spring Boot Instances

## 21. How Does a Load Balancer Distribute 1 Million Requests Across Multiple Spring Boot Instances?

This is an important system design interview scenario.

Let's design an e-commerce backend that receives 1 million requests.

### Step 1: Understand the traffic

Assume:

- Total incoming requests: 1,000,000.
- Time period: 10 seconds.
- Average request processing time: 100 milliseconds.
- Spring Boot instance capacity: approximately 5,000 requests per second under the tested workload.

The average incoming request rate is:

\[
\frac{1,000,000}{10} = 100,000 \text{ requests/second}
\]

The exact capacity of an instance must be established through load testing. It depends on endpoint complexity, database access, CPU, memory, network, and concurrency.

### Step 2: Calculate the number of instances

Assume one instance can safely handle 5,000 requests per second.

\[
\text{Instances} =
\frac{100,000}{5,000}
= 20
\]

So, 20 instances are the theoretical minimum under these assumptions.

In production, add headroom for spikes, failures, uneven request costs, and autoscaling delays.

For example, you might start with 25 instances rather than operating all 20 at their assumed maximum capacity.

### Step 3: Design the architecture

```text
                  1 Million Requests
                          |
                          v
                   DNS / Global LB
                          |
                          v
                  Cloud Load Balancer
                          |
                          v
                    API Gateway
                          |
             +------------+------------+
             |            |            |
             v            v            v
          App 1         App 2        App 3
             |            |            |
             +------------+------------+
                          |
               ... More Spring Boot
                    Instances
                          |
             +------------+------------+
             |            |            |
             v            v            v
           Redis       Database       Kafka
                     Read Replicas
```

### Step 4: How requests are distributed

Suppose 20 healthy instances are registered with the load balancer and the workload is evenly distributed.

| Instance group | Number of instances | Requests per second per instance |
|---|---:|---:|
| Group 1 | 5 | 5,000 |
| Group 2 | 5 | 5,000 |
| Group 3 | 5 | 5,000 |
| Group 4 | 5 | 5,000 |
| Total | 20 | 5,000 |

Total:

\[
20 \times 5,000 = 100,000 \text{ requests/second}
\]

This is a capacity-planning model, not a guarantee that the load balancer will produce perfectly equal request counts.

### Step 5: What happens if an instance fails?

Suppose App 5 becomes unhealthy.

```text
Before:

Load Balancer
    |
    +-- App 1  UP
    +-- App 2  UP
    +-- App 3  UP
    +-- App 4  UP
    +-- App 5  UP

After:

Load Balancer
    |
    +-- App 1  UP
    +-- App 2  UP
    +-- App 3  UP
    +-- App 4  UP
    +-- App 5  DOWN (removed from routing)
```

The load balancer stops sending new traffic to App 5 after its health-check or failure-detection policy marks it unhealthy.

The remaining instances must have enough spare capacity to absorb the traffic. Otherwise, autoscaling or overload protection is required.

### Step 6: What happens when traffic increases?

Suppose traffic increases from 100,000 to 150,000 requests per second.

If each instance can safely handle 5,000 requests per second:

\[
\frac{150,000}{5,000} = 30
\]

You need approximately 30 instances at that assumed capacity.

The autoscaler can add instances, and the load balancer can register them after they become healthy.

### Step 7: Avoid database bottlenecks

Scaling Spring Boot instances does not mean the database can handle unlimited traffic.

For example:

```text
20 Spring Boot Instances
          |
          v
     Connection Pools
          |
          v
      PostgreSQL
```

If every application instance opens 100 database connections:

\[
20 \times 100 = 2,000
\]

The database may not support 2,000 concurrent connections efficiently.

You should:

- Configure connection pools carefully.
- Optimize SQL queries.
- Add indexes for important access patterns.
- Use Redis for suitable cacheable data.
- Use read replicas for read-heavy workloads.
- Use Kafka for suitable asynchronous processing.
- Apply rate limiting and backpressure.
- Monitor database CPU, connections, locks, and query latency.

### Step 8: Make Spring Boot instances stateless

A scalable application should avoid depending on local instance memory for critical user session state.

Prefer:

- JWT-based authentication where appropriate.
- Shared session storage such as Redis when server-side sessions are needed.
- Shared object storage for uploaded files.
- Externalized configuration.
- Shared distributed caching where appropriate.

This allows requests to reach any healthy instance.

### Step 9: Add production monitoring

Monitor:

| Metric | Why it matters |
|---|---|
| Requests per second | Understand incoming traffic |
| P95/P99 latency | Detect slow requests |
| HTTP 5xx rate | Identify application failures |
| CPU utilization | Identify compute bottlenecks |
| Memory and GC | Detect memory pressure |
| Active connections | Understand connection usage |
| Database connection pool | Detect database saturation |
| Queue lag | Detect asynchronous processing delays |
| Instance health | Detect unhealthy instances |

### Final architecture

```text
                       Users
                         |
                         v
                   DNS / Global LB
                         |
                         v
                  Cloud Load Balancer
                         |
                         v
                     API Gateway
                         |
              +----------+----------+
              |          |          |
              v          v          v
            App 1      App 2      App 3
              |          |          |
              +----------+----------+
                         |
                More Spring Boot Apps
                         |
          +--------------+--------------+
          |              |              |
          v              v              v
        Redis        PostgreSQL       Kafka
                    + Read Replicas
```

### Interview-ready answer

When an application receives one million requests, I first calculate the incoming requests per second and estimate the safe throughput of a single Spring Boot instance through load testing.

I then deploy multiple stateless Spring Boot instances behind a Layer 7 cloud load balancer. The load balancer distributes requests using an appropriate algorithm and health checks prevent traffic from reaching unhealthy instances.

I use autoscaling to add or remove instances based on CPU, request rate, latency, or other relevant metrics. I also protect downstream systems such as PostgreSQL using connection pooling, query optimization, caching, read replicas, rate limiting, and asynchronous processing through Kafka where appropriate.

Finally, I monitor throughput, P95/P99 latency, error rates, database connections, and resource utilization to ensure the complete system—not just the application tier—can handle the traffic.

---

# LEVEL 8 — Interview Revision

## Important Differences

| Concept | Key difference |
|---|---|
| Layer 4 vs Layer 7 | Transport-level routing vs application-level routing |
| Reverse proxy vs Load balancer | Request forwarding vs traffic distribution; one component can do both |
| Round Robin vs Weighted Round Robin | Equal sequential allocation vs capacity-based allocation |
| Round Robin vs Least Connections | Request order vs current active connection count |
| IP Hash vs Consistent Hashing | Client-IP-based selection vs stable key-to-node mapping |
| Active vs Passive Health Checks | Independent probes vs observing real traffic |
| Liveness vs Readiness | Restart decision vs traffic eligibility |
| Sticky Sessions vs Stateless | Backend affinity vs any healthy instance can handle the request |
| Failover vs Autoscaling | Replace failed capacity vs change instance count based on demand |
| Global vs Regional Load Balancing | Cross-region routing vs routing within a deployment region |
| Autoscaling vs Load Balancing | Changes instance count vs distributes traffic |
| Health Checks vs Connection Draining | Detects health vs gracefully removes an instance |

## Common Interview Questions

1. What is a load balancer and why do we need it?
2. Explain Layer 4 and Layer 7 load balancing with examples.
3. What is the difference between a reverse proxy and a load balancer?
4. How does Nginx distribute requests?
5. Explain Round Robin and Weighted Round Robin.
6. When would you choose Least Connections over Round Robin?
7. What is consistent hashing? Why is it useful in distributed systems?
8. How does a load balancer identify an unhealthy Spring Boot instance?
9. What is the difference between active and passive health checks?
10. What are sticky sessions? Why can they become a scaling problem?
11. How does failover work when one application instance crashes?
12. Explain active-active and active-passive multi-region architectures.
13. How does autoscaling work with a load balancer?
14. What is connection draining and why is it required during deployment?
15. Design a system that handles one million requests.
16. How do you avoid overloading the database when application instances scale out?
17. How would you prevent one slow backend instance from affecting the entire application?
18. How do you handle long-running requests during rolling deployments?

## Practical Implementation Checklist

- [ ] Run three Spring Boot instances on different ports.
- [ ] Configure Nginx as a reverse proxy.
- [ ] Configure Round Robin.
- [ ] Test Weighted Round Robin.
- [ ] Test Least Connections.
- [ ] Configure Spring Boot Actuator health endpoints.
- [ ] Simulate an unhealthy backend.
- [ ] Observe how the load balancer handles failures.
- [ ] Implement a stateless REST API.
- [ ] Run a load test using JMeter or k6.
- [ ] Observe CPU, memory, throughput, and P95/P99 latency.
- [ ] Deploy the application on Kubernetes.
- [ ] Configure a Horizontal Pod Autoscaler.
- [ ] Test graceful shutdown and connection draining.

## Recommended Learning Order

| Day | Topics |
|---|---|
| Day 1 | Load balancer fundamentals, Layer 4 vs Layer 7, reverse proxy |
| Day 2 | Nginx and cloud load balancers |
| Day 3 | Round Robin, Weighted Round Robin, Least Connections, IP Hash |
| Day 4 | Consistent hashing, health checks, active vs passive |
| Day 5 | Sticky sessions, session affinity, failover |
| Day 6 | Global load balancing, multi-region routing |
| Day 7 | Autoscaling, connection draining |
| Day 8 | One-million-request architecture and interview questions |

## Final Takeaways

For a Java Full Stack Developer with 5+ years of experience, focus especially on:

1. How a load balancer routes traffic to Spring Boot instances.
2. Layer 4 vs Layer 7 and reverse proxy concepts.
3. Nginx configuration and cloud load balancers.
4. Round Robin, Weighted Round Robin, Least Connections, and consistent hashing.
5. Health checks, failover, and connection draining.
6. Stateless application design and session management.
7. Autoscaling and multi-region architecture.
8. End-to-end capacity planning, including database and downstream services.

**Core design principle:** A load balancer distributes traffic, autoscaling adjusts capacity, health checks identify unhealthy instances, and connection draining enables graceful instance removal. All four work together to build a highly available and scalable application.