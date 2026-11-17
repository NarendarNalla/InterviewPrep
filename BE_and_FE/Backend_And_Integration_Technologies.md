# Java Backend & Integration Technologies — Interview Preparation Notes

**Target:** 5+ Years Java Full Stack Developer  
**Technology Stack:** Java 17+, Spring Boot 3, Microservices  
**Project:** E-commerce Order Management System

---

# TABLE OF CONTENTS

| # | Topic | Navigation |
|---|---|---|
| 1 | REST API | [Go to REST API](#1-rest-api-representational-state-transfer) |
| 2 | RPC | [Go to RPC](#2-rpc-remote-procedure-call) |
| 3 | Apache Kafka | [Go to Kafka](#3-apache-kafka) |
| 4 | Docker | [Go to Docker](#4-docker) |
| 5 | MQTT | [Go to MQTT](#5-mqtt-message-queuing-telemetry-transport) |
| 6 | WildFly | [Go to WildFly](#6-wildfly) |
| 7 | Apache Tomcat | [Go to Tomcat](#7-apache-tomcat) |
| 8 | WebSocket | [Go to WebSocket](#8-websocket) |
| 9 | Webhooks | [Go to Webhooks](#9-webhooks) |
| 10 | Git | [Go to Git](#10-git) |
| 11 | Postman | [Go to Postman](#11-postman) |
| 12 | Git Worktree | [Go to Git Worktree](#12-git-worktree) |
| 13 | Real-World Architecture | [Go to Architecture](#13-real-world-architecture--how-everything-works-together) |
| 14 | Interview Comparisons | [Go to Comparisons](#14-important-interview-comparisons) |
| 15 | Interview Questions | [Go to Questions](#15-top-interview-questions--5-years-experience) |
| 16 | Preparation Checklist | [Go to Checklist](#16-final-interview-preparation-checklist) |
| 17 | Self-Evaluation | [Go to Evaluation](#17-self-evaluation-of-these-notes) |

---

**Study Navigation:** Start with REST API → RPC → Kafka → Docker → Tomcat → WebSocket → Webhooks → Git.

*Note: These are standard Markdown heading anchors. Navigation support depends on the Markdown viewer.*

---

# 1. REST API (Representational State Transfer)

## 1.1 What is REST?

REST is an architectural style used to design network-based applications.

It allows different applications or services to communicate over HTTP using standard HTTP methods.

Example:

An e-commerce application has:

- User Service
- Product Service
- Order Service
- Payment Service
- Notification Service

When a customer places an order, the frontend communicates with the Order Service using a REST API.

Example:

`POST /api/orders`

REST is not a protocol. It is an architectural style that uses HTTP in most practical implementations.

## 1.2 REST Architectural Principles

1. Client-Server Architecture
   - Frontend and backend are independent.
   - Frontend communicates with backend through APIs.

2. Statelessness
   - Every request contains the information needed to process it.
   - Server does not depend on previous request state.

3. Cacheability
   - Responses can be cached when appropriate.
   - Example: Product information.

4. Uniform Interface
   - Consistent resource-based URLs and HTTP methods.

5. Layered System
   - Client may communicate through API Gateway, Load Balancer, etc.

6. Code on Demand (Optional)
   - Server can provide executable code to clients.

## 1.3 HTTP Methods

| Method | Purpose | Example |
|---|---|---|
| GET | Retrieve data | Get products |
| POST | Create resource or trigger processing | Create order |
| PUT | Replace resource | Update entire profile |
| PATCH | Partially update resource | Update order status |
| DELETE | Delete resource | Delete product |

### PUT vs PATCH

PUT:

```http
PUT /api/users/101
Content-Type: application/json

{
  "name": "Naren",
  "email": "naren@example.com"
}
```

Replaces the resource representation.

PATCH:

```http
PATCH /api/users/101
Content-Type: application/json

{
  "email": "new@example.com"
}
```

Updates only the specified fields, depending on the patch contract.

### POST vs PUT

- POST is generally used to create a resource under a collection.
- PUT is generally used to replace a resource at a known URI.
- PUT is idempotent.
- POST is not inherently idempotent.

## 1.4 HTTP Status Codes

| Status | Meaning | Use Case |
|---|---|---|
| 200 | OK | Successful GET |
| 201 | Created | Resource created |
| 202 | Accepted | Asynchronous processing accepted |
| 204 | No Content | Successful DELETE |
| 400 | Bad Request | Invalid request |
| 401 | Unauthorized | Authentication missing/invalid |
| 403 | Forbidden | Insufficient permissions |
| 404 | Not Found | Resource unavailable |
| 409 | Conflict | Duplicate order or version conflict |
| 422 | Unprocessable Content | Semantic validation failure |
| 429 | Too Many Requests | Rate limit exceeded |
| 500 | Internal Server Error | Unexpected server error |
| 502 | Bad Gateway | Invalid upstream response |
| 503 | Service Unavailable | Service temporarily unavailable |
| 504 | Gateway Timeout | Upstream timeout |

## 1.5 REST API Implementation Using Spring Boot

### Step 1: Create Entity

### Java import and package note

The imports are listed at the top of each Java snippet. Framework/JDK imports are explicit. Application-specific types such as `Order`, `OrderRequest`, `OrderRepository`, and `OrderService` are assumed to be in the same package for these focused examples; add their project-specific imports when you place the classes in separate packages. The gRPC generated classes use the illustrative package `com.example.grpc` from the `.proto` configuration.

```java
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String customerName;

    private Double amount;

    private String status;

    // Getters and setters
}
```

### Step 2: Create DTO

DTO means Data Transfer Object.

It separates API request/response models from database entities.

```java
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record OrderRequest(

    @NotBlank
    String customerName,

    @NotNull
    @Positive
    Double amount

) {}
```

```java
// No imports required: this record uses only java.lang types.

public record OrderResponse(
    Long id,
    String customerName,
    Double amount,
    String status
) {}
```

### Step 3: Repository

```java
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository
        extends JpaRepository<Order, Long> {
}
```

### Step 4: Service Layer

```java
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository repository;

    public OrderResponse createOrder(OrderRequest request) {

        Order order = new Order();

        order.setCustomerName(request.customerName());
        order.setAmount(request.amount());
        order.setStatus("CREATED");

        Order saved = repository.save(order);

        return new OrderResponse(
            saved.getId(),
            saved.getCustomerName(),
            saved.getAmount(),
            saved.getStatus()
        );
    }

    public OrderResponse getOrder(Long id) {

        Order order = repository.findById(id)
            .orElseThrow(() ->
                new RuntimeException("Order not found"));

        return new OrderResponse(
            order.getId(),
            order.getCustomerName(),
            order.getAmount(),
            order.getStatus()
        );
    }
}
```

### Step 5: Controller

```java
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderResponse> createOrder(
            @Valid @RequestBody OrderRequest request) {

        OrderResponse response =
            orderService.createOrder(request);

        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponse> getOrder(
            @PathVariable Long id) {

        return ResponseEntity.ok(
            orderService.getOrder(id)
        );
    }
}
```

## 1.6 Global Exception Handling

Avoid handling every exception independently inside controllers.

Use `@RestControllerAdvice`.

```java
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> handleNotFound(
            EntityNotFoundException ex) {

        return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body(ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleValidation(
            MethodArgumentNotValidException ex) {

        return ResponseEntity
            .badRequest()
            .body("Invalid request");
    }
}
```

In production, use a consistent error response containing:

- Timestamp
- HTTP status
- Error code
- Error message
- Request path
- Correlation ID

## 1.7 REST API Pagination and Sorting

```java
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@GetMapping
public Page<Order> getOrders(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size) {

    Pageable pageable =
        PageRequest.of(page, size,
            Sort.by("id").descending());

    return repository.findAll(pageable);
}
```

Example:

`GET /api/orders?page=0&size=10`

## 1.8 Authentication and Security

Common authentication approaches:

- Basic Authentication
- Session-based Authentication
- JWT
- OAuth 2.0
- OpenID Connect

For microservices, JWT and OAuth 2.0 are frequently used.

Important distinction:

- Authentication: Who are you?
- Authorization: What are you allowed to access?

## 1.9 REST API Production Considerations

- Request validation
- Proper HTTP status codes
- API versioning
- Pagination
- Rate limiting
- Authentication and authorization
- Idempotency for critical POST operations
- Logging and distributed tracing
- Timeouts and retries
- API documentation using OpenAPI/Swagger
- Avoid exposing database entities directly

## 1.10 Common Interview Questions

1. What is REST?
2. Explain REST architectural constraints.
3. Difference between PUT and PATCH?
4. Difference between POST and PUT?
5. What is idempotency?
6. What is statelessness?
7. Difference between 401 and 403?
8. How do you implement global exception handling?
9. How do you secure REST APIs?
10. How do you handle API versioning?
11. How do you improve REST API performance?
12. How do you handle duplicate requests?

---

# 2. RPC (Remote Procedure Call)

## 2.1 What is RPC?

RPC stands for Remote Procedure Call.

It allows one application to invoke a method or procedure on another application as if it were calling a local method.

Example:

Order Service needs customer information from User Service.

Conceptually:

```java
// Import your generated/client-side Customer DTO if it is in another package, e.g.:
// import com.example.user.dto.Customer;
// userService is assumed to be an injected UserService client.

Customer customer =
    userService.getCustomerById(101L);
```

If `userService` is running in another machine, RPC handles the communication, serialization, network transport and response.

The method looks like a local call, but internally it involves network communication.

## 2.2 How RPC Works

1. Client invokes a remote method.
2. Client-side stub receives the invocation.
3. Request data is serialized.
4. Request travels over the network.
5. Server receives the request.
6. Server deserializes the request.
7. Remote method executes.
8. Response is serialized.
9. Response returns to the client.
10. Client receives the result.

## 2.3 RPC Architecture

```text
Order Service
     |
     | Remote Method Call
     v
Client Stub
     |
     | Serialization
     v
Network
     |
     v
Server Stub
     |
     v
User Service
     |
     v
Database
```

## 2.4 RPC vs REST

| Feature | REST | RPC |
|---|---|---|
| Communication model | Resource-based | Method-based |
| Typical transport | HTTP | HTTP/2, TCP, etc. |
| Payload | Commonly JSON | Protobuf, JSON, etc. |
| Contract | HTTP/API contract | Method or service contract |
| Coupling | Resource-oriented | Operation-oriented |
| Common use | Public APIs | Internal service communication |

REST can also be implemented over HTTP/2. RPC is not limited to HTTP/2.

## 2.5 Types of RPC

- gRPC
- XML-RPC
- JSON-RPC
- Apache Thrift
- Java RMI (legacy Java technology)

For modern microservices, gRPC is an important RPC implementation.

## 2.6 What is gRPC?

gRPC is a high-performance RPC framework originally developed by Google.

It commonly uses:

- HTTP/2
- Protocol Buffers
- Strongly typed service contracts
- Generated client and server code
- Streaming support

Advantages:

1. Efficient binary serialization.
2. Strongly typed contracts.
3. Lower payload overhead than typical JSON.
4. Supports streaming.
5. Supports multiple languages.
6. Suitable for internal microservice communication.

## 2.7 Protocol Buffers

A `.proto` file defines the request, response and service contract.

Example:

```protobuf
syntax = "proto3";

option java_multiple_files = true;
option java_package = "com.example.grpc";

service UserService {

    rpc GetUser (UserRequest)
        returns (UserResponse);
}

message UserRequest {
    int64 userId = 1;
}

message UserResponse {
    int64 userId = 1;
    string name = 2;
    string email = 3;
}
```

The gRPC compiler generates Java classes and service stubs from this contract.

## 2.8 gRPC Server Using Java

Conceptual implementation using generated classes:

```java
import com.example.grpc.UserRequest;
import com.example.grpc.UserResponse;
import com.example.grpc.UserServiceGrpc;
import io.grpc.stub.StreamObserver;

public class UserGrpcService
        extends UserServiceGrpc.UserServiceImplBase {

    @Override
    public void getUser(
            UserRequest request,
            StreamObserver<UserResponse> responseObserver) {

        UserResponse response =
            UserResponse.newBuilder()
                .setUserId(request.getUserId())
                .setName("Naren")
                .setEmail("naren@example.com")
                .build();

        responseObserver.onNext(response);

        responseObserver.onCompleted();
    }
}
```

The gRPC server must register this service and start listening on its configured port.

## 2.9 gRPC Communication Types

1. Unary RPC
   - One request and one response.

2. Server Streaming
   - One request and multiple responses.

3. Client Streaming
   - Multiple requests and one response.

4. Bidirectional Streaming
   - Both client and server continuously exchange messages.

## 2.10 When to Use REST vs gRPC?

Use REST when:

- Frontend communicates with backend.
- External partners consume your APIs.
- Browser compatibility is important.
- Simple JSON-based APIs are sufficient.

Use gRPC when:

- Internal microservices communicate frequently.
- Low-latency communication is important.
- Strong contracts are required.
- Streaming is needed.
- Multiple programming languages are involved.

## 2.11 Interview Questions

1. What is RPC?
2. Explain RPC architecture.
3. What is gRPC?
4. What is Protocol Buffer?
5. Why does gRPC use HTTP/2?
6. What are gRPC streaming types?
7. REST vs RPC?
8. Why use gRPC between microservices?
9. What is a client stub?
10. How do you handle RPC timeouts and failures?

---

# 3. Apache Kafka

## 3.1 What is Kafka?

Apache Kafka is a distributed event streaming platform.

It enables applications to publish, store and consume events in a scalable and fault-tolerant manner.

Example:

When a customer places an order:

1. Order Service creates the order.
2. Order Service publishes an OrderCreated event.
3. Payment Service consumes the event.
4. Inventory Service consumes the event.
5. Notification Service consumes the event.

The Order Service does not need to synchronously call every downstream service.

## 3.2 Why Kafka?

Without Kafka:

```text
Order Service
    |
    +----> Payment Service
    |
    +----> Inventory Service
    |
    +----> Notification Service
```

Problems:

- Tight coupling
- Service availability dependencies
- Increased response time
- Difficult scalability
- Failure propagation

With Kafka:

```text
                 Kafka Topic
                      |
             OrderCreated Event
                      |
        +-------------+-------------+
        |             |             |
        v             v             v
     Payment      Inventory     Notification
     Service       Service       Service
```

Benefits:

- Loose coupling
- Asynchronous communication
- High throughput
- Scalability
- Fault tolerance
- Event replay
- Multiple independent consumers

## 3.3 Kafka Core Components

### 1. Producer

Publishes messages to Kafka topics.

Example: Order Service.

### 2. Consumer

Reads messages from Kafka topics.

Example: Payment Service.

### 3. Topic

Logical category or stream of events.

Examples:

- order-created
- payment-completed
- inventory-updated

### 4. Partition

A topic is divided into partitions.

Partitions allow parallel processing and scalability.

### 5. Broker

A Kafka server that stores and serves partitions.

### 6. Consumer Group

A group of consumers that work together to process topic partitions.

Within a consumer group, one partition is assigned to at most one consumer at a time.

### 7. Offset

A sequential position of a record within a partition.

Kafka tracks consumer progress using offsets.

## 3.4 Kafka Architecture

```text
Order Service
     |
     | Producer
     v
Kafka Broker Cluster
     |
     +--------------------+
     |                    |
     v                    v
order-created         payment-events
     |
     +-------------------------+
     |                         |
     v                         v
Payment Service         Inventory Service
Consumer                Consumer
```

## 3.5 Kafka Message Structure

A Kafka record contains:

- Key
- Value
- Headers
- Timestamp
- Offset
- Partition

Example event:

```json
{
  "eventId": "EVT-101",
  "eventType": "ORDER_CREATED",
  "orderId": 1001,
  "customerId": 101,
  "amount": 2500,
  "status": "CREATED"
}
```

The key can be `orderId`, helping records with the same key to be routed to the same partition under the default partitioning strategy.

## 3.6 Kafka Integration with Spring Boot

Dependencies:

```xml
<dependency>
    <groupId>org.springframework.kafka</groupId>
    <artifactId>spring-kafka</artifactId>
</dependency>
```

### application.yml

```yaml
spring:
  kafka:
    bootstrap-servers: localhost:9092

    producer:
      key-serializer: org.apache.kafka.common.serialization.StringSerializer
      value-serializer: org.springframework.kafka.support.serializer.JsonSerializer

    consumer:
      group-id: payment-group
      auto-offset-reset: earliest
      key-deserializer: org.apache.kafka.common.serialization.StringDeserializer
      value-deserializer: org.springframework.kafka.support.serializer.JsonDeserializer
      properties:
        spring.json.trusted.packages: "com.example"
```

### Event DTO

```java
// No imports required: this record uses only java.lang types.

public record OrderEvent(
    Long orderId,
    Long customerId,
    Double amount,
    String status
) {}
```

### Producer

```java
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderProducer {

    private final KafkaTemplate<String, OrderEvent> kafkaTemplate;

    public void publishOrder(OrderEvent event) {

        kafkaTemplate.send(
            "order-created",
            event.orderId().toString(),
            event
        );
    }
}
```

### Consumer

```java
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class PaymentConsumer {

    @KafkaListener(
        topics = "order-created",
        groupId = "payment-group"
    )
    public void consume(OrderEvent event) {

        System.out.println(
            "Processing payment for order: "
            + event.orderId()
        );
    }
}
```

In production, configure deserialization, trusted packages, error handling and retry behavior explicitly.

## 3.7 Kafka Delivery Semantics

### At Most Once

A message is processed zero or one time.

Possible message loss.

### At Least Once

A message is delivered one or more times.

Duplicate processing is possible.

### Exactly Once

Kafka supports exactly-once processing in supported transactional processing workflows.

Important: This does not automatically guarantee exactly-once effects in external databases or third-party systems.

## 3.8 Kafka Acknowledgment

Consumer acknowledgment determines when offsets are committed.

Common approaches:

- Automatic commit
- Manual acknowledgment
- Record acknowledgment
- Batch acknowledgment

Example:

```java
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;

@KafkaListener(topics = "order-created")
public void consume(
        OrderEvent event,
        Acknowledgment acknowledgment) {

    try {
        processOrder(event);
        acknowledgment.acknowledge();

    } catch (Exception ex) {
        // Handle failure through configured error strategy
        throw ex;
    }
}
```

Manual acknowledgment requires an appropriate acknowledgment mode in consumer configuration.

## 3.9 Important Kafka Concepts for Interviews

### Replication

Kafka replicates partitions across brokers to improve fault tolerance.

### Leader and Follower

- Leader handles normal read/write operations.
- Followers replicate the leader's data.

### Consumer Rebalancing

When consumers join or leave a group, Kafka redistributes partitions.

### Retention

Kafka retains records based on configured time or storage policies, even if consumers have already read them.

### Dead Letter Topic (DLT)

Messages that cannot be successfully processed after configured retries can be routed to a dead-letter topic.

### Retry Mechanism

Retry temporary failures using configured retry policies.

Avoid endlessly retrying permanent failures.

### Idempotent Consumer

A consumer should safely handle duplicate events.

Example:

Maintain a processed-event table with a unique event ID.

## 3.10 Kafka vs REST

| Feature | REST | Kafka |
|---|---|---|
| Communication | Synchronous request-response | Asynchronous event streaming |
| Coupling | Request-time dependency | Loose coupling |
| Response | Immediate response | No direct consumer response |
| Data storage | Not inherently a message log | Durable event log |
| Use case | Fetch customer details | Notify multiple services |

## 3.11 Kafka Production Best Practices

- Choose partition count carefully.
- Use meaningful message keys.
- Make consumers idempotent.
- Configure retry and DLT.
- Monitor consumer lag.
- Use schema evolution strategies.
- Avoid unnecessarily large messages.
- Configure replication.
- Use TLS and authentication.
- Avoid blocking consumer threads.
- Consider the transactional outbox pattern for reliable database-to-Kafka publishing.

## 3.12 Interview Questions

1. What is Kafka?
2. Explain Kafka architecture.
3. What is a partition?
4. What is a consumer group?
5. What is an offset?
6. What happens when a consumer goes down?
7. What is consumer rebalancing?
8. Kafka vs RabbitMQ?
9. What is message ordering?
10. How does Kafka achieve fault tolerance?
11. What is consumer lag?
12. How do you handle duplicate messages?
13. What is a Dead Letter Topic?
14. What is the difference between at-most-once and at-least-once?
15. How do you ensure reliable database and Kafka publishing?

---

# 4. Docker

## 4.1 What is Docker?

Docker is a containerization platform that packages an application and its dependencies into a portable container image.

It ensures that the application runs consistently across different environments.

Example:

Your Spring Boot application requires:

- Java 17
- Application JAR
- Configuration
- Required libraries

Docker packages the runtime and application into an image.

## 4.2 Why Docker?

Without Docker:

- Different Java versions across environments.
- Dependency conflicts.
- Environment-specific configuration.
- Difficult deployment.
- Works on my machine problems.

With Docker:

- Consistent runtime.
- Portable application.
- Faster deployment.
- Better resource utilization.
- Easy scaling.

## 4.3 Docker Core Components

### Docker Image

A read-only template containing application and dependencies.

### Docker Container

A running instance of an image.

### Dockerfile

A file containing instructions to build an image.

### Docker Engine

Runs and manages containers.

### Docker Registry

Stores Docker images.

Example: Docker Hub.

### Docker Volume

Persists data outside the container's writable layer.

### Docker Network

Allows containers to communicate.

## 4.4 Docker Architecture

```text
Developer
    |
    v
Dockerfile
    |
    v
Docker Image
    |
    v
Docker Container
    |
    +---- Spring Boot Application
    |
    +---- Java Runtime
    |
    +---- Application Dependencies
```

## 4.5 Dockerize a Spring Boot Application

### Step 1: Build Application

```bash
mvn clean package -DskipTests
```

This generates:

`target/order-service.jar`

### Step 2: Create Dockerfile

```dockerfile
FROM eclipse-temurin:17-jre

WORKDIR /app

COPY target/order-service.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
```

### Step 3: Build Docker Image

```bash
docker build -t order-service:1.0 .
```

### Step 4: Run Container

```bash
docker run -d \
  --name order-service \
  -p 8080:8080 \
  order-service:1.0
```

Application:

`http://localhost:8080`

### Step 5: Check Running Containers

```bash
docker ps
```

### Step 6: View Logs

```bash
docker logs order-service
```

### Step 7: Stop Container

```bash
docker stop order-service
```

### Step 8: Remove Container

```bash
docker rm order-service
```

## 4.6 Docker Compose

Docker Compose manages multiple related containers using a YAML configuration.

Example:

Our application needs:

- Order Service
- MySQL
- Kafka

`compose.yaml`

```yaml
services:

  mysql:
    image: mysql:8.4
    environment:
      MYSQL_ROOT_PASSWORD: root
      MYSQL_DATABASE: orders
    ports:
      - "3306:3306"
    volumes:
      - mysql-data:/var/lib/mysql

  order-service:
    build: .
    ports:
      - "8080:8080"
    depends_on:
      - mysql

volumes:
  mysql-data:
```

Start:

```bash
docker compose up -d
```

Stop:

```bash
docker compose down
```

Note: `depends_on` alone does not guarantee database readiness. Use health checks and application-level retry mechanisms.

## 4.7 Docker Networking

Common network types:

- Bridge
- Host
- None
- Overlay

For local development, bridge networking is commonly used.

Containers in the same Compose network can communicate using service names.

For example:

`jdbc:mysql://mysql:3306/orders`

Inside the container, `localhost` refers to that container, not the MySQL container.

## 4.8 Docker Volume vs Bind Mount

| Volume | Bind Mount |
|---|---|
| Managed by Docker | Uses host filesystem path |
| Good for persistent application data | Good for development file sharing |
| Docker manages storage location | Developer specifies host path |

## 4.9 Docker Best Practices

- Use small and trusted base images.
- Use multi-stage builds.
- Do not run as root unnecessarily.
- Never hardcode passwords.
- Use environment variables or secrets.
- Add health checks.
- Scan images for vulnerabilities.
- Keep images minimal.
- Use `.dockerignore`.
- Pin image versions.
- Keep containers stateless where possible.

## 4.10 Docker vs Virtual Machine

| Docker Container | Virtual Machine |
|---|---|
| Shares host kernel | Runs a guest OS |
| Lightweight | Comparatively heavy |
| Faster startup | Slower startup |
| Lower overhead | Higher overhead |
| Process isolation | Hardware-level virtualization |

## 4.11 Interview Questions

1. What is Docker?
2. Image vs container?
3. Docker vs VM?
4. What is Dockerfile?
5. What is Docker Compose?
6. What is a Docker volume?
7. How do containers communicate?
8. How do you troubleshoot a failed container?
9. What is a multi-stage build?
10. How do you reduce Docker image size?

---

# 5. MQTT (Message Queuing Telemetry Transport)

## 5.1 What is MQTT?

MQTT is a lightweight publish-subscribe messaging protocol designed for communication between devices and applications.

It is especially useful for IoT applications and environments with:

- Limited bandwidth
- Low-power devices
- Unstable networks
- Small messages
- Intermittent connectivity

Example:

A smart delivery vehicle sends its GPS location every few seconds.

The vehicle publishes messages to an MQTT broker.

The Order Tracking Service subscribes to the location topic.

## 5.2 MQTT Architecture

```text
GPS Device
    |
    | Publish
    v
MQTT Broker
    |
    | Subscribe
    v
Tracking Service
    |
    v
Database / Dashboard
```

## 5.3 MQTT Core Components

### Publisher

Sends messages.

Example: Vehicle GPS device.

### Subscriber

Receives messages.

Example: Tracking Service.

### Broker

Receives messages and forwards them to interested subscribers.

Example: Mosquitto broker.

### Topic

A hierarchical name identifying the message channel.

Example:

`vehicles/101/location`

### Payload

The actual message data.

Example:

```json
{
  "vehicleId": 101,
  "latitude": 17.385,
  "longitude": 78.486,
  "speed": 45
}
```

## 5.4 MQTT QoS Levels

Quality of Service determines delivery guarantees.

| QoS | Guarantee | Description |
|---|---|---|
| 0 | At most once | No acknowledgment guarantee |
| 1 | At least once | Duplicate delivery possible |
| 2 | Exactly once | Protocol-level exactly-once delivery |

QoS 2 has more overhead.

Remember: MQTT QoS 2 does not automatically guarantee exactly-once business processing in your database.

## 5.5 MQTT Topic Wildcards

Single-level wildcard:

`+`

Example:

`vehicles/+/location`

Matches:

- vehicles/101/location
- vehicles/102/location

Multi-level wildcard:

`#`

Example:

`vehicles/#`

Matches all nested topics under vehicles.

## 5.6 MQTT vs Kafka

| Feature | MQTT | Kafka |
|---|---|---|
| Primary purpose | Lightweight messaging | Event streaming |
| Common use | IoT | Microservices and data pipelines |
| Architecture | Broker-based pub/sub | Distributed partitioned log |
| Message storage | Broker/session-dependent | Durable retained log |
| Bandwidth | Designed for constrained networks | Optimized for high-throughput systems |
| Consumers | Topic subscribers | Consumer groups |

## 5.7 Spring Boot MQTT Integration

Use Eclipse Paho Java client.

Dependency:

```xml
<dependency>
    <groupId>org.eclipse.paho</groupId>
    <artifactId>org.eclipse.paho.client.mqttv3</artifactId>
    <version>1.2.5</version>
</dependency>
```

### MQTT Publisher

```java
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.stereotype.Service;

@Service
public class MqttPublisher {

    private MqttClient client;

    public void connect() throws MqttException {

        client = new MqttClient(
            "tcp://localhost:1883",
            "order-publisher"
        );

        MqttConnectOptions options =
            new MqttConnectOptions();

        options.setAutomaticReconnect(true);
        options.setCleanSession(true);

        client.connect(options);
    }

    public void publish(String topic, String message)
            throws MqttException {

        MqttMessage mqttMessage =
            new MqttMessage(message.getBytes());

        mqttMessage.setQos(1);

        client.publish(topic, mqttMessage);
    }
}
```

### MQTT Subscriber

```java
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.stereotype.Service;

@Service
public class MqttSubscriber {

    private MqttClient client;

    public void connect() throws MqttException {

        client = new MqttClient(
            "tcp://localhost:1883",
            "tracking-service"
        );

        client.connect();

        client.subscribe(
            "vehicles/+/location",
            (topic, message) -> {

                String payload =
                    new String(message.getPayload());

                System.out.println(
                    topic + " : " + payload
                );
            }
        );
    }
}
```

For a production Spring Boot application, manage the MQTT client lifecycle using application lifecycle hooks, configure authentication/TLS, and implement reconnect and error handling.

## 5.8 When to Use MQTT?

- Smart home applications.
- Vehicle tracking.
- Industrial sensors.
- Smart agriculture.
- Remote monitoring.
- IoT device communication.

## 5.9 Interview Questions

1. What is MQTT?
2. Explain MQTT architecture.
3. What is an MQTT broker?
4. What are QoS levels?
5. MQTT vs Kafka?
6. What is a retained message?
7. What is a Last Will and Testament?
8. What are MQTT topic wildcards?
9. How does MQTT handle unstable networks?
10. Where would you use MQTT in a real-world application?

---

# 6. WildFly

## 6.1 What is WildFly?

WildFly is an open-source application server based on the Jakarta EE platform.

It provides an enterprise runtime for Java applications.

It supports capabilities such as:

- Servlet containers
- Jakarta EE APIs
- Dependency injection
- Transaction management
- Security
- Connection pooling
- Messaging integrations
- Enterprise application deployment

WildFly was previously known as JBoss AS.

## 6.2 Why Application Servers?

Enterprise applications may need more than just an HTTP server.

They often require:

- Transaction management
- Database connection pooling
- Security management
- Messaging
- Resource management
- Application deployment management

An application server provides these services.

## 6.3 WildFly Architecture

```text
Client
  |
  v
HTTP Request
  |
  v
WildFly Application Server
  |
  +---- Web Container
  |
  +---- Security
  |
  +---- Transaction Manager
  |
  +---- Connection Pool
  |
  +---- Application
  |
  v
Database
```

## 6.4 Standalone vs Domain Mode

### Standalone Mode

One independently managed WildFly server instance.

Useful for:

- Development
- Testing
- Small deployments

### Domain Mode

Multiple WildFly server instances managed centrally through a domain controller.

Useful for:

- Centralized configuration
- Multiple server instances
- Enterprise deployments

## 6.5 WildFly Deployment Formats

Common deployment formats:

- WAR: Web Application Archive.
- EAR: Enterprise Application Archive.
- JAR: Java Archive, depending on deployment and supported subsystem.

WAR contains web application components and dependencies.

EAR can package multiple enterprise modules.

## 6.6 Deploying a Spring Boot WAR to WildFly

Spring Boot normally runs as an executable JAR with an embedded server.

For traditional application server deployment, you can package a WAR and use the external server's servlet container.

### Step 1: Configure WAR Packaging

```xml
<packaging>war</packaging>
```

### Step 2: Extend SpringBootServletInitializer

```java
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class OrderApplication
        extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder
        configure(SpringApplicationBuilder builder) {

        return builder.sources(OrderApplication.class);
    }

    public static void main(String[] args) {

        SpringApplication.run(
            OrderApplication.class, args
        );
    }
}
```

### Step 3: Configure Embedded Tomcat as Provided

For a traditional Spring Boot WAR deployment, configure the appropriate embedded servlet container dependency with provided scope.

For example:

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-tomcat</artifactId>
    <scope>provided</scope>
</dependency>
```

Ensure your Spring Boot and WildFly versions support compatible Jakarta Servlet APIs.

### Step 4: Build WAR

```bash
mvn clean package
```

### Step 5: Deploy WAR

Copy the generated WAR into:

`WILDFLY_HOME/standalone/deployments/`

Alternatively, deploy through the management CLI.

## 6.7 WildFly Management CLI

WildFly provides a command-line interface for administration.

Example:

```bash
jboss-cli.sh --connect
```

Check server status:

```bash
:read-attribute(name=server-state)
```

Deploy application:

```bash
deploy order-service.war
```

Undeploy:

```bash
undeploy order-service.war
```

## 6.8 WildFly Configuration

Important directories:

- `standalone/configuration`
- `standalone/deployments`
- `standalone/log`
- `modules`

Common configuration file:

`standalone.xml`

Contains server subsystem and resource configuration.

## 6.9 WildFly vs Spring Boot Embedded Server

| WildFly | Spring Boot Embedded Server |
|---|---|
| Full application server | Application-focused runtime |
| Enterprise deployment features | Lightweight deployment |
| Can deploy WAR/EAR | Usually executable JAR |
| Centralized server configuration | Configuration commonly packaged or externalized |
| Useful in traditional enterprise environments | Common in microservices |

## 6.10 Interview Questions

1. What is WildFly?
2. WildFly vs JBoss?
3. What is an application server?
4. What is standalone mode?
5. What is domain mode?
6. How do you deploy WAR files?
7. How do you troubleshoot deployment failures?
8. What is a datasource?
9. How do you configure database connection pooling?
10. How do you deploy Spring Boot applications to WildFly?

---

# 7. Apache Tomcat

## 7.1 What is Tomcat?

Apache Tomcat is an open-source Java web server and Servlet container.

It executes Java web applications that use technologies such as:

- Servlets
- Jakarta Server Pages
- WebSocket APIs

Spring Boot commonly uses embedded Tomcat as its default servlet container.

## 7.2 What Does Tomcat Do?

When a request arrives:

1. Tomcat accepts the HTTP request.
2. It processes the request through its connector.
3. It identifies the appropriate web application.
4. It routes the request to the servlet.
5. Spring's DispatcherServlet handles the request.
6. Controller processes the request.
7. Response returns to the client.

## 7.3 Tomcat Architecture

```text
Browser / Postman
       |
       v
Tomcat Connector
       |
       v
Tomcat Engine
       |
       v
Host
       |
       v
Context
       |
       v
DispatcherServlet
       |
       v
Spring Controller
       |
       v
Service Layer
```

## 7.4 DispatcherServlet

DispatcherServlet is the front controller in Spring MVC.

It receives incoming requests and delegates them to appropriate components.

Important flow:

```text
HTTP Request
     |
     v
DispatcherServlet
     |
     v
HandlerMapping
     |
     v
Controller
     |
     v
Service
     |
     v
Repository
     |
     v
Database
```

## 7.5 Embedded Tomcat in Spring Boot

Spring Boot simplifies deployment by embedding Tomcat into the application.

Dependency:

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
```

By default, Spring Boot's web starter uses embedded Tomcat unless you configure another servlet container.

Run:

```bash
mvn spring-boot:run
```

Or:

```bash
java -jar order-service.jar
```

Default port:

`8080`

## 7.6 Change Tomcat Port

`application.yml`

```yaml
server:
  port: 9090
```

## 7.7 Tomcat Thread Pool

Tomcat processes incoming requests using worker threads.

Important settings include:

- Maximum threads
- Minimum spare threads
- Connection timeout
- Maximum connections
- Accept count

Example:

```yaml
server:
  tomcat:
    threads:
      max: 200
      min-spare: 10
    max-connections: 8192
    accept-count: 100
```

These are illustrative values, not universal production defaults.

## 7.8 Tomcat vs WildFly

| Tomcat | WildFly |
|---|---|
| Servlet container | Full application server |
| Lightweight | More enterprise capabilities |
| Servlets and WebSocket support | Broader Jakarta EE support |
| Common in Spring Boot | Common in enterprise deployments |
| No complete Jakarta EE application server stack | Provides a broader enterprise runtime |

## 7.9 Tomcat Performance Considerations

- Thread pool sizing
- Connection pool configuration
- Database connection pool
- Request timeout
- JVM heap configuration
- GC tuning
- Connection limits
- Monitoring and thread dump analysis

Important: Increasing thread count blindly may reduce performance because of CPU contention, memory pressure and downstream resource saturation.

## 7.10 Interview Questions

1. What is Tomcat?
2. Tomcat vs WildFly?
3. What is DispatcherServlet?
4. How does Tomcat process HTTP requests?
5. What is a servlet?
6. What is a servlet container?
7. How do you change the Tomcat port?
8. What is a Tomcat thread pool?
9. How do you troubleshoot high response times?
10. How does embedded Tomcat work in Spring Boot?

---

# 8. WebSocket

## 8.1 What is WebSocket?

WebSocket is a communication protocol that provides full-duplex communication between a client and server over a persistent connection.

In normal REST communication:

- Client sends a request.
- Server sends a response.
- Connection is typically not maintained as a continuous application-level conversation.

In WebSocket:

- Client establishes a connection.
- Connection remains open.
- Client and server can send messages independently.

## 8.2 Why WebSocket?

Consider a live order-tracking application.

A customer wants to see:

- Order confirmed
- Payment completed
- Order packed
- Shipped
- Out for delivery
- Delivered

Using REST polling:

The browser repeatedly calls the server to check the status.

This creates unnecessary requests.

Using WebSocket:

The server can push status updates as soon as they are available.

## 8.3 WebSocket Architecture

```text
Frontend Application
        |
        | WebSocket Handshake
        v
Spring Boot Server
        |
        | Persistent Connection
        v
WebSocket Session
        |
        v
Real-Time Messages
```

## 8.4 WebSocket vs REST

| Feature | REST | WebSocket |
|---|---|---|
| Communication | Request-response | Full duplex |
| Connection | Typically short-lived HTTP requests | Persistent connection |
| Server push | Not native to ordinary request-response | Supported |
| Overhead | Repeated HTTP requests | Lower repeated-request overhead |
| Use case | CRUD APIs | Live notifications |

## 8.5 WebSocket Integration with Spring Boot

Dependency:

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-websocket</artifactId>
</dependency>
```

### Step 1: Configure WebSocket

```java
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig
        implements WebSocketMessageBrokerConfigurer {

    @Override
    public void registerStompEndpoints(
            StompEndpointRegistry registry) {

        registry.addEndpoint("/ws")
            .setAllowedOrigins("http://localhost:4200");
    }

    @Override
    public void configureMessageBroker(
            MessageBrokerRegistry registry) {

        registry.enableSimpleBroker("/topic", "/queue");

        registry.setApplicationDestinationPrefixes("/app");
    }
}
```

For production, configure appropriate authentication, origin restrictions and a scalable broker when necessary.

### Step 2: Create Message Controller

```java
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class OrderWebSocketController {

    @MessageMapping("/order")
    @SendTo("/topic/orders")
    public String sendOrderUpdate(String message) {

        return "Order Update: " + message;
    }
}
```

### Step 3: Send Message from Backend

Use `SimpMessagingTemplate`:

```java
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderNotificationService {

    private final SimpMessagingTemplate messagingTemplate;

    public void notifyOrderStatus(
            Long orderId,
            String status) {

        messagingTemplate.convertAndSend(
            "/topic/orders/" + orderId,
            status
        );
    }
}
```

## 8.6 WebSocket Communication Using STOMP

STOMP is a messaging protocol that can run over WebSocket.

Important concepts:

- CONNECT
- SUBSCRIBE
- SEND
- MESSAGE
- DISCONNECT

Example destinations:

Client sends:

`/app/order`

Client subscribes:

`/topic/orders`

Spring routes messages to the configured controller and broker.

## 8.7 WebSocket vs Server-Sent Events

| WebSocket | SSE |
|---|---|
| Full duplex | Server to client |
| Client and server send messages | Client receives server events |
| Supports bidirectional messaging | Uses HTTP event stream |
| Good for chat and collaboration | Good for live feeds and notifications |

## 8.8 WebSocket Production Considerations

- Authentication
- Authorization
- Heartbeats
- Connection lifecycle
- Reconnection logic
- Message ordering
- Scaling across multiple instances
- Sticky sessions or shared messaging infrastructure where appropriate
- Connection limits
- Monitoring active sessions

## 8.9 Interview Questions

1. What is WebSocket?
2. WebSocket vs REST?
3. What is a WebSocket handshake?
4. What is STOMP?
5. What is a full-duplex connection?
6. WebSocket vs SSE?
7. How do you implement WebSocket in Spring Boot?
8. How do you authenticate WebSocket connections?
9. How do you scale WebSocket applications?
10. How do you handle reconnections?

---

# 9. Webhooks

## 9.1 What is a Webhook?

A webhook is an HTTP callback mechanism through which one application notifies another application when a specific event occurs.

Example:

An e-commerce application integrates with a payment gateway.

When a payment succeeds:

1. Customer makes payment.
2. Payment Gateway processes it.
3. Payment Gateway sends an HTTP POST request to the application's webhook endpoint.
4. Application verifies the event.
5. Application updates payment status.
6. Application publishes a Kafka event if required.

## 9.2 Webhook Architecture

```text
Payment Gateway
      |
      | Payment Successful
      |
      | HTTP POST
      v
Spring Boot Webhook API
      |
      v
Signature Validation
      |
      v
Payment Processing
      |
      v
Database
```

## 9.3 Webhook vs REST API

| REST API | Webhook |
|---|---|
| Client calls server | External service calls your endpoint |
| Usually request-response | Event notification |
| Client controls request timing | Event triggers notification |
| Pull model | Push model |

## 9.4 Spring Boot Webhook Implementation

### Step 1: Webhook DTO

```java
// No imports required: this record uses only java.lang types.

public record PaymentWebhookRequest(
    String eventId,
    String eventType,
    Long orderId,
    String paymentStatus
) {}
```

### Step 2: Webhook Controller

```java
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/webhooks/payment")
@RequiredArgsConstructor
public class PaymentWebhookController {

    private final PaymentService paymentService;

    @PostMapping
    public ResponseEntity<Void> handleWebhook(
            @RequestBody PaymentWebhookRequest request) {

        paymentService.processWebhook(request);

        return ResponseEntity.ok().build();
    }
}
```

This is a simplified example. A real integration must validate the provider's signature against the raw request body before trusting the payload.

## 9.5 Webhook Signature Validation

Webhooks are exposed endpoints. Never trust incoming requests blindly.

Common security approach:

- Shared secret
- HMAC signature
- Timestamp validation
- Replay protection
- HTTPS

Example HMAC SHA-256 implementation:

```java
import java.nio.charset.StandardCharsets;
import java.util.HexFormat;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class WebhookSignatureValidator {

    public static String calculateSignature(
            String payload,
            String secret) throws Exception {

        Mac mac = Mac.getInstance("HmacSHA256");

        SecretKeySpec key =
            new SecretKeySpec(
                secret.getBytes(StandardCharsets.UTF_8),
                "HmacSHA256"
            );

        mac.init(key);

        byte[] hash = mac.doFinal(
            payload.getBytes(StandardCharsets.UTF_8)
        );

        return HexFormat.of().formatHex(hash);
    }
}
```

Compare signatures using a constant-time comparison method such as `MessageDigest.isEqual`.

Important: Use the payment provider's exact signature specification. Some providers sign timestamps and raw request bytes together, rather than signing only the payload.

## 9.6 Webhook Reliability

A production webhook implementation should handle:

### Duplicate Events

Payment gateways may retry webhook delivery.

Solution:

Maintain an event ID with a unique database constraint.

### Failed Processing

If processing fails, the provider may retry.

Solution:

- Return an appropriate HTTP status.
- Persist accepted events.
- Process asynchronously.
- Implement retry handling.

### Idempotency

The same webhook should not update payment state multiple times incorrectly.

### Fast Response

Do not perform long-running business operations before acknowledging the webhook.

A better approach:

1. Validate signature.
2. Store event.
3. Return success.
4. Process asynchronously.
5. Retry failures safely.

## 9.7 Webhook vs Kafka vs WebSocket

| Feature | Webhook | Kafka | WebSocket |
|---|---|---|---|
| Communication | HTTP callback | Event streaming | Persistent connection |
| Direction | Provider to consumer | Producer to consumer | Bidirectional |
| Main use | Third-party notifications | Internal event communication | Real-time communication |
| Protocol | HTTP | Kafka protocol | WebSocket |
| Example | Payment confirmation | OrderCreated event | Live order status |

## 9.8 Interview Questions

1. What is a webhook?
2. Webhook vs REST API?
3. Webhook vs Kafka?
4. How do you secure webhook endpoints?
5. What is HMAC?
6. How do you handle duplicate webhook events?
7. How do you implement webhook retries?
8. What happens when your server is down?
9. Why should webhook processing be asynchronous?
10. How do you ensure idempotency?

---

# 10. Git

## 10.1 What is Git?

Git is a distributed version control system.

It helps developers track code changes, collaborate and maintain different versions of a project.

Every developer can have a complete local repository containing the project's history.

## 10.2 Git Architecture

```text
Working Directory
       |
       | git add
       v
Staging Area
       |
       | git commit
       v
Local Repository
       |
       | git push
       v
Remote Repository
```

### Working Directory

Where developers modify files.

### Staging Area

Contains changes selected for the next commit.

### Local Repository

Stores committed changes and history.

### Remote Repository

Shared repository hosted on GitHub, GitLab, Bitbucket, etc.

## 10.3 Important Git Commands

### Configuration

```bash
git config --global user.name "Developer"
git config --global user.email "developer@example.com"
```

### Clone

```bash
git clone <repository-url>
```

### Check Status

```bash
git status
```

### Create Branch

```bash
git branch feature/order-api
```

### Switch Branch

```bash
git switch feature/order-api
```

### Create and Switch

```bash
git switch -c feature/order-api
```

### Stage Changes

```bash
git add .
```

### Commit

```bash
git commit -m "Implement order creation API"
```

### Push

```bash
git push origin feature/order-api
```

### Pull

```bash
git pull origin main
```

### Fetch

```bash
git fetch origin
```

## 10.4 Git Fetch vs Pull

Fetch:

Downloads changes from the remote repository without merging them into the current branch.

```bash
git fetch origin
```

Pull:

Fetches and integrates changes into the current branch.

```bash
git pull origin main
```

## 10.5 Git Merge vs Rebase

### Merge

Combines changes from one branch into another.

```bash
git switch main
git merge feature/order-api
```

Advantages:

- Preserves branch history.
- Does not rewrite existing commits.

### Rebase

Moves or replays commits onto another base.

```bash
git switch feature/order-api
git rebase main
```

Advantages:

- Creates a more linear history.
- Helps keep feature branches updated.

Important: Avoid rebasing shared branches if doing so would rewrite commits other developers depend on.

## 10.6 Git Reset vs Revert

### Reset

Moves the current branch reference to another commit.

```bash
git reset --soft HEAD~1
```

Modes:

- --soft: Keeps changes staged.
- --mixed: Keeps changes in working directory but unstaged.
- --hard: Discards changes in working directory and staging area.

Be careful with `--hard`.

### Revert

Creates a new commit that reverses an earlier commit.

```bash
git revert <commit-id>
```

Preferred for undoing changes in shared history.

## 10.7 Git Stash

Temporarily saves uncommitted changes.

```bash
git stash
```

List:

```bash
git stash list
```

Apply:

```bash
git stash apply
```

Apply and remove:

```bash
git stash pop
```

## 10.8 Git Cherry-Pick

Applies a specific commit from another branch to the current branch.

```bash
git cherry-pick <commit-id>
```

Useful when only one specific fix needs to be moved.

## 10.9 Git Conflict Resolution

Conflicts occur when Git cannot automatically combine changes.

Typical workflow:

1. Run merge or rebase.
2. Identify conflicting files.
3. Open conflicting files.
4. Resolve conflict markers.
5. Stage resolved files.
6. Continue merge or rebase.

During rebase:

```bash
git add .
git rebase --continue
```

Abort:

```bash
git rebase --abort
```

## 10.10 Common Git Interview Questions

1. What is Git?
2. Git vs GitHub?
3. Fetch vs Pull?
4. Merge vs Rebase?
5. Reset vs Revert?
6. What is Cherry-Pick?
7. What is Git Stash?
8. How do you resolve merge conflicts?
9. What is HEAD?
10. What is a detached HEAD?
11. What is a fast-forward merge?
12. What is an interactive rebase?

---

# 11. Postman

## 11.1 What is Postman?

Postman is an API development and testing platform.

It helps developers create, execute, test and document API requests.

It is commonly used for testing REST APIs.

## 11.2 Why Postman?

Consider the Order Management System.

We have:

```http
POST /api/orders
GET /api/orders/101
PUT /api/orders/101
DELETE /api/orders/101
```

Postman allows us to test these APIs without building the frontend.

## 11.3 Important Postman Components

### Collections

Group related API requests.

Example:

Order Management Collection.

### Environments

Store environment-specific variables.

Examples:

- Local
- Development
- QA
- Production

### Variables

Examples:

```text
baseUrl = http://localhost:8080
token = eyJ...
orderId = 101
```

### Authorization

Supports:

- Basic Auth
- Bearer Token
- OAuth 2.0
- API Key

### Headers

Examples:

```http
Content-Type: application/json
Authorization: Bearer <token>
```

### Body

Contains request payload.

Example:

```json
{
  "customerName": "Naren",
  "amount": 2500
}
```

## 11.4 Testing REST API Using Postman

Request:

```http
POST http://localhost:8080/api/orders
```

Body:

```json
{
  "customerName": "Naren",
  "amount": 2500
}
```

Expected response:

```json
{
  "id": 101,
  "customerName": "Naren",
  "amount": 2500,
  "status": "CREATED"
}
```

Expected status:

`201 Created`

## 11.5 Postman Tests

You can write JavaScript-based tests.

Example:

```javascript
pm.test("Status code should be 201", function () {
    pm.response.to.have.status(201);
});

pm.test("Response contains order ID", function () {

    const response = pm.response.json();

    pm.expect(response.id).to.exist;
});
```

Save generated order ID:

```javascript
const response = pm.response.json();

pm.environment.set("orderId", response.id);
```

Use it in the next request:

```http
GET {{baseUrl}}/api/orders/{{orderId}}
```

## 11.6 Postman Collection Runner

Collection Runner executes multiple API requests in sequence.

Useful for:

- Regression testing
- API workflows
- Repeated test execution
- Data-driven testing

## 11.7 Newman

Newman is a command-line collection runner for Postman collections.

Example:

```bash
npm install -g newman
```

Execute:

```bash
newman run orders.postman_collection.json
```

This can be integrated into CI/CD pipelines.

## 11.8 Postman vs Swagger

| Postman | Swagger / OpenAPI |
|---|---|
| API testing and collaboration platform | API specification and documentation ecosystem |
| Sends and validates requests | Describes API contracts |
| Supports test scripts and collections | Can generate interactive API documentation |
| Useful for manual and automated testing | Useful for API design and documentation |

## 11.9 Interview Questions

1. What is Postman?
2. How do you test REST APIs?
3. What are collections?
4. What are environments?
5. How do you pass JWT tokens?
6. What are Postman variables?
7. What is Collection Runner?
8. What is Newman?
9. How do you automate API testing?
10. Postman vs Swagger?

---

# 12. Git Worktree

## 12.1 What is Git Worktree?

Git Worktree is a Git feature that allows you to check out multiple branches of the same repository into separate working directories.

Normally, developers switch branches in one directory.

Using worktrees, developers can work on multiple branches simultaneously without repeatedly switching branches.

## 12.2 Why Git Worktree?

Real-world scenario:

You are working on:

`feature/order-api`

Suddenly, your manager asks you to fix a critical production bug.

You need to:

1. Stop your current work.
2. Save or stash your changes.
3. Switch to the production fix branch.
4. Fix the bug.
5. Commit and push.
6. Return to your original feature.

With Git Worktree, you can create a separate working directory for the hotfix.

Your original work remains available in its own directory.

## 12.3 Git Worktree Architecture

```text
                 Git Repository
                       |
          +------------+------------+
          |                         |
          v                         v
    Worktree 1                 Worktree 2
          |                         |
          v                         v
   feature/order-api           hotfix/payment
          |                         |
          v                         v
    Developer A                Developer B
```

Both worktrees share the same underlying Git repository and object database.

## 12.4 Create a Worktree

First, check branches:

```bash
git branch
```

Create a new branch and worktree:

```bash
git worktree add -b hotfix/payment ../payment-hotfix main
```

This:

- Creates `hotfix/payment`.
- Uses `main` as its starting point.
- Creates a separate directory named `payment-hotfix`.

## 12.5 List Worktrees

```bash
git worktree list
```

## 12.6 Switch Between Worktrees

Move to the respective directory:

```bash
cd ../payment-hotfix
```

Original directory:

```bash
cd ../order-service
```

No branch switching is required between these directories.

## 12.7 Remove a Worktree

```bash
git worktree remove ../payment-hotfix
```

Remove stale worktree information:

```bash
git worktree prune
```

## 12.8 Git Branch vs Git Worktree

| Git Branch | Git Worktree |
|---|---|
| Represents a line of development | Represents a separate working directory |
| One working directory usually checks out one branch | Multiple branches can be checked out simultaneously in separate directories |
| Requires switching to change branches in the same directory | Move between directories |
| Same repository history | Shares repository data |

Important:

A branch cannot normally be checked out in multiple worktrees simultaneously.

## 12.9 Real-World Developer Workflow

```bash
# Current feature work
git switch feature/order-api

# Create a separate hotfix workspace
git worktree add -b hotfix/payment ../payment-hotfix main

# Work on hotfix
cd ../payment-hotfix

# Commit the fix
git add .
git commit -m "Fix payment processing issue"

# Return to feature development
cd ../order-service
```

## 12.10 Interview Questions

1. What is Git Worktree?
2. Why do we use Git Worktree?
3. Git Worktree vs Git Branch?
4. Can multiple worktrees share one repository?
5. Can the same branch be checked out in multiple worktrees?
6. How do you list worktrees?
7. How do you remove a worktree?
8. How does worktree help during production hotfixes?

---

# 13. REAL-WORLD ARCHITECTURE — HOW EVERYTHING WORKS TOGETHER

Now let's connect all 12 technologies using our E-commerce Order Management System.

## 13.1 Application Architecture

```text
                  Angular Frontend
                         |
                         |
                      REST API
                         |
                         v
                 API Gateway / Load Balancer
                         |
                         v
                    Order Service
                   Spring Boot
                         |
             +-----------+-----------+
             |                       |
             v                       v
          MySQL                  Kafka Producer
                                     |
                                     v
                                Kafka Broker
                                     |
                  +------------------+------------------+
                  |                  |                  |
                  v                  v                  v
             Payment Service   Inventory Service  Notification
                  |                  |                  |
                  v                  v                  v
             Payment DB         Inventory DB       Email Service

Additional integrations:

REST / gRPC:
Order Service <----> User Service

MQTT:
GPS Devices ----> MQTT Broker ----> Tracking Service

WebSocket:
Order Service ----> Angular Customer Dashboard

Webhook:
Payment Gateway ----> Payment Webhook API

Docker:
Containerizes Spring Boot services, Kafka and MySQL.

Tomcat / WildFly:
Provides the application runtime depending on deployment architecture.

Git:
Manages source code and version control.

Git Worktree:
Allows simultaneous feature and hotfix development.

Postman:
Tests REST APIs and webhook endpoints.
```

## 13.2 Complete Order Placement Workflow

### Step 1: Customer Places Order

Angular calls:

```http
POST /api/orders
```

Order Service receives the request through Spring Boot REST API.

### Step 2: Validate Customer

Order Service communicates with User Service.

Depending on architecture, this can use:

- REST API
- gRPC

### Step 3: Save Order

Order Service saves order details into MySQL.

### Step 4: Publish Event

Order Service publishes:

`OrderCreated`

to Kafka.

For reliable production publishing, consider the transactional outbox pattern.

### Step 5: Payment Processing

Payment Service consumes the event and initiates payment processing.

### Step 6: Payment Gateway Notification

Payment Gateway calls our webhook endpoint.

### Step 7: Update Order Status

Payment Service validates the webhook, updates payment status and publishes `PaymentCompleted` or `PaymentFailed`.

### Step 8: Inventory Update

Inventory Service consumes the payment event and updates inventory according to the business workflow.

### Step 9: Real-Time Notification

Order Service or a notification component pushes order status to the frontend using WebSocket.

### Step 10: Vehicle Tracking

The delivery vehicle sends location updates using MQTT.

Tracking Service receives these updates.

### Step 11: Deployment

Services are packaged into Docker images and deployed in the target environment.

### Step 12: Testing and Development

Developers use:

- Postman for API testing.
- Git for source control.
- Git Worktree for parallel development.
- Tomcat or WildFly depending on deployment requirements.

---

# 14. IMPORTANT INTERVIEW COMPARISONS

| Comparison | Key Difference |
|---|---|
| REST vs RPC | Resource-based vs method-based communication |
| REST vs Kafka | Synchronous request-response vs event streaming |
| Kafka vs MQTT | Distributed event log vs lightweight IoT messaging |
| REST vs WebSocket | Request-response vs full-duplex |
| WebSocket vs SSE | Bidirectional vs server-to-client |
| Webhook vs Kafka | HTTP event callback vs distributed event streaming |
| Docker vs VM | Containerization vs hardware virtualization |
| Tomcat vs WildFly | Servlet container vs full application server |
| Git Merge vs Rebase | Merge histories vs replay commits |
| Git Reset vs Revert | Move branch reference vs create reversal commit |
| Git Branch vs Worktree | Development reference vs separate working directory |
| Postman vs Swagger | API testing vs API specification/documentation |

---

# 15. TOP INTERVIEW QUESTIONS — 5+ YEARS EXPERIENCE

These are the questions you should be able to answer without memorizing definitions.

## REST API

1. How do you design a scalable REST API?
2. How do you implement idempotency?
3. How do you secure APIs using JWT and OAuth 2.0?
4. How do you handle global exceptions?
5. How do you improve API performance?
6. How do you implement pagination?
7. How do you handle concurrent updates?

## RPC

1. Why choose gRPC over REST?
2. How does gRPC use HTTP/2?
3. What happens when a remote service times out?
4. How do you handle backward-compatible protobuf changes?

## Kafka

1. How do you guarantee message processing reliability?
2. How do you handle duplicate messages?
3. What happens when a consumer fails?
4. How do you handle poison messages?
5. How do you monitor consumer lag?
6. How do you maintain ordering?
7. How do you prevent data loss between MySQL and Kafka?

## Docker

1. How do you optimize Docker image size?
2. How do you troubleshoot containers?
3. What is a multi-stage build?
4. How do containers communicate?
5. How do you manage application secrets?

## MQTT

1. When would you choose MQTT over Kafka?
2. What are QoS levels?
3. How do you handle intermittent device connectivity?
4. What is retained messaging?

## WildFly and Tomcat

1. What happens when a request reaches Tomcat?
2. How does DispatcherServlet work?
3. How do you troubleshoot deployment failures?
4. How do you tune the Tomcat thread pool?
5. When would you deploy a WAR instead of an executable JAR?

## WebSocket

1. How do you establish a WebSocket connection?
2. How do you scale WebSocket applications?
3. How do you implement authentication?
4. WebSocket vs SSE?

## Webhooks

1. How do you validate webhook signatures?
2. How do you avoid duplicate processing?
3. How do you handle retries?
4. What happens if your application is down?

## Git and Git Worktree

1. Explain your feature branch workflow.
2. How do you resolve merge conflicts?
3. When do you prefer merge over rebase?
4. How do you handle production hotfixes?
5. How does Git Worktree help with parallel development?

## Postman

1. How do you automate API testing?
2. How do you manage environments?
3. How do you test authentication?
4. How do you integrate Newman into CI/CD?

---

# 16. FINAL INTERVIEW PREPARATION CHECKLIST

## Must Know — Level 1

- [ ] REST architecture and HTTP methods
- [ ] REST API implementation using Spring Boot
- [ ] Exception handling and validation
- [ ] REST vs RPC
- [ ] Kafka producer and consumer
- [ ] Kafka partitions and consumer groups
- [ ] Kafka delivery semantics
- [ ] Dockerfile and Docker Compose
- [ ] Tomcat request lifecycle
- [ ] Git branching, merge and rebase
- [ ] Postman API testing

## Important — Level 2

- [ ] gRPC implementation
- [ ] Kafka retries and DLT
- [ ] Kafka idempotency
- [ ] Docker networking and volumes
- [ ] WebSocket with Spring Boot
- [ ] Webhook signature validation
- [ ] WildFly deployment
- [ ] Git Worktree
- [ ] Tomcat thread pool tuning

## Advanced — Level 3

- [ ] Kafka transactions and outbox pattern
- [ ] Distributed tracing
- [ ] gRPC streaming
- [ ] MQTT QoS and retained messages
- [ ] WebSocket horizontal scaling
- [ ] Webhook replay protection
- [ ] Docker multi-stage builds
- [ ] JVM and Tomcat performance troubleshooting
- [ ] Git interactive rebase

---

# 17. SELF-EVALUATION OF THESE NOTES

**Evaluation criteria**

| Criteria | Assessment |
|---|---|
| Conceptual coverage | Strong |
| Java/Spring Boot examples | Good |
| Real-world architecture | Strong |
| Interview-oriented comparisons | Strong |
| Production considerations | Good |
| Advanced implementation depth | Needs further practical exercises |
| Version-specific configuration | Must be verified against the project's versions |

**Overall assessment: 8/10 as a first-pass interview study guide.**

### What is covered well?

- All 12 requested technologies.
- Practical Spring Boot code examples.
- Core architectural differences.
- Production use cases.
- Important interview questions.
- Integration of technologies into one business workflow.

### What should be strengthened before interviews?

1. Build a complete runnable Spring Boot + Kafka project.
2. Implement gRPC communication between two microservices.
3. Run MySQL and Kafka using Docker Compose.
4. Implement WebSocket notifications.
5. Implement webhook signature verification and idempotency.
6. Deploy a Spring Boot WAR to WildFly.
7. Practice Git conflict resolution and worktree workflows.
8. Perform API testing using Postman and Newman.

### Final recommendation

Do not memorize all 12 technologies at the same depth.

Prioritize REST API, Kafka, Docker, Tomcat, Git and WebSocket first. Understand RPC, MQTT, WildFly, Webhooks, Postman and Git Worktree with practical examples.

For a 5+ years Java Full Stack Developer interviewing for product-based companies, the most important skill is explaining **why you selected a particular technology, how you implemented it, what problems you encountered, and how you solved them in production.**

**End of Notes.**