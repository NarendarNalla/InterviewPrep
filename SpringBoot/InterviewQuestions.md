# Spring Boot Interview Questions & Answers with Examples
## Complete Interview Preparation Guide — 5+ Years Experience

**Technology Stack:** Java 17+, Spring Boot 3.x, Spring Framework 6.x, Spring Security 6.x, Hibernate, Spring Data JPA, PostgreSQL/MySQL, Redis, Kafka, Maven, JUnit 5, Mockito.

---

# Table of Contents

1. [Spring Fundamentals](#1-spring-fundamentals)
2. [Spring Beans and Dependency Injection](#2-spring-beans-and-dependency-injection)
3. [Spring Configuration and Auto Configuration](#3-spring-configuration-and-auto-configuration)
4. [Spring Boot Internals](#4-spring-boot-internals)
5. [REST API Development](#5-rest-api-development)
6. [Validation and Exception Handling](#6-validation-and-exception-handling)
7. [DTO, Serialization and Mapping](#7-dto-serialization-and-mapping)
8. [Spring Data JPA](#8-spring-data-jpa)
9. [Hibernate and JPA Internals](#9-hibernate-and-jpa-internals)
10. [Entity Relationships](#10-entity-relationships)
11. [Transaction Management](#11-transaction-management)
12. [Database Performance Optimization](#12-database-performance-optimization)
13. [Spring Security](#13-spring-security)
14. [JWT, OAuth2 and OpenID Connect](#14-jwt-oauth2-and-openid-connect)
15. [Spring AOP](#15-spring-aop)
16. [Spring MVC Internals](#16-spring-mvc-internals)
17. [Async Processing and Scheduling](#17-async-processing-and-scheduling)
18. [Caching and Redis](#18-caching-and-redis)
19. [Kafka and Messaging](#19-kafka-and-messaging)
20. [Spring Boot Testing](#20-spring-boot-testing)
21. [Microservices](#21-microservices)
22. [Resilience and Fault Tolerance](#22-resilience-and-fault-tolerance)
23. [Distributed Systems](#23-distributed-systems)
24. [Logging and Observability](#24-logging-and-observability)
25. [Production and Deployment](#25-production-and-deployment)
26. [Advanced Spring Boot](#26-advanced-spring-boot)
27. [Modern Spring Boot and Java](#27-modern-spring-boot-and-java)
28. [Reactive Programming](#28-reactive-programming)
29. [Architecture and Design Patterns](#29-architecture-and-design-patterns)
30. [Scenario-Based Interview Questions](#30-scenario-based-interview-questions)
31. [Frequently Asked Comparison Questions](#31-frequently-asked-comparison-questions)
32. [Coding and Practical Interview Questions](#32-coding-and-practical-interview-questions)

---

# 1. Spring Fundamentals

## Q1. What is the Spring Framework?

**Answer:**

Spring is an open-source Java framework used to build enterprise applications. It provides infrastructure for dependency injection, transaction management, security, web development, data access, and application integration.

**Important features:**
- IoC (Inversion of Control).
- Dependency Injection.
- Aspect-Oriented Programming.
- Transaction Management.
- Spring MVC.
- Spring Security.
- Integration with Hibernate, JPA, Kafka, Redis, etc.

**Example:**

Without Spring:

```java
public class OrderService {
    private final OrderRepository repository =
            new OrderRepository();
}
```

With Spring:

```java
@Service
public class OrderService {

    private final OrderRepository repository;

    public OrderService(OrderRepository repository) {
        this.repository = repository;
    }
}
```

Spring manages the dependencies and injects them automatically.

## Q2. What is the difference between Spring and Spring Boot?

| Spring Framework | Spring Boot |
|---|---|
| Core Java application framework | Built on top of Spring |
| Requires more manual configuration | Provides auto-configuration |
| Dependencies are configured manually | Starter dependencies simplify setup |
| External server setup is common | Embedded server supported |
| More boilerplate configuration | Convention over configuration |
| Flexible infrastructure | Faster application development |

**Example:**

Spring Boot application:

```java
@SpringBootApplication
public class OrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(
                OrderApplication.class, args);
    }
}
```

Spring Boot automatically configures many components based on the classpath and application configuration.

## Q3. What is IoC (Inversion of Control)?

**Answer:**

IoC is a design principle in which the responsibility of creating and managing objects is transferred from application code to a framework or container.

Without IoC, developers create and manage dependencies manually.

With Spring IoC, the Spring container creates objects, manages their lifecycle, and injects dependencies.

**Example:**

```java
@Component
public class PaymentService {

    public void processPayment() {
        System.out.println("Payment processed");
    }
}
```

```java
@Service
public class OrderService {

    private final PaymentService paymentService;

    public OrderService(PaymentService paymentService) {
        this.paymentService = paymentService;
    }
}
```

Spring manages both objects.

## Q4. What is Dependency Injection?

**Answer:**

Dependency Injection is a technique where an object receives its dependencies from an external container instead of creating them itself.

Benefits:
- Loose coupling.
- Easier unit testing.
- Better maintainability.
- Easier implementation replacement.

Common types:
1. Constructor injection.
2. Setter injection.
3. Field injection.

Constructor injection is generally recommended for mandatory dependencies.

## Q5. What is ApplicationContext?

**Answer:**

`ApplicationContext` is the central Spring IoC container. It creates, configures, manages, and provides access to Spring beans.

It provides:
- Bean management.
- Dependency injection.
- Event publishing.
- Internationalization.
- Resource loading.
- Environment and property access.

Example:

```java
ApplicationContext context =
        new AnnotationConfigApplicationContext(
                AppConfig.class);

OrderService service =
        context.getBean(OrderService.class);
```

## Q6. What is the difference between BeanFactory and ApplicationContext?

| BeanFactory | ApplicationContext |
|---|---|
| Basic IoC container | Advanced IoC container |
| Supports bean creation and DI | Supports all BeanFactory features |
| Basic lifecycle management | Additional lifecycle and enterprise features |
| Lazy initialization by default | Singleton beans are generally eagerly initialized |
| Limited infrastructure features | Events, internationalization, resource loading |

**Interview point:** `ApplicationContext` extends `BeanFactory`.

## Q7. What are the advantages of Spring Boot?

- Auto-configuration.
- Starter dependencies.
- Embedded servers.
- Actuator.
- Externalized configuration.
- Production-ready application support.
- Simplified application development.
- Easy integration with databases, messaging, and security.

## Q8. What is a Spring Boot starter?

A starter is a dependency descriptor that groups commonly required dependencies for a particular functionality.

Examples:

| Starter | Purpose |
|---|---|
| `spring-boot-starter-web` | REST APIs and Spring MVC |
| `spring-boot-starter-data-jpa` | JPA and Hibernate |
| `spring-boot-starter-security` | Authentication and authorization |
| `spring-boot-starter-validation` | Bean validation |
| `spring-boot-starter-test` | Testing |
| `spring-boot-starter-actuator` | Monitoring |
| `spring-boot-starter-webflux` | Reactive web applications |

---

# 2. Spring Beans and Dependency Injection

## Q9. What is a Spring Bean?

A Spring Bean is an object managed by the Spring IoC container.

Example:

```java
@Component
public class EmailService {

    public void sendEmail() {
        System.out.println("Email sent");
    }
}
```

Spring detects the component and registers it as a bean.

## Q10. What are the different bean scopes?

| Scope | Description |
|---|---|
| singleton | One instance per bean definition per container |
| prototype | New instance whenever requested from the container |
| request | One instance per HTTP request |
| session | One instance per HTTP session |
| application | One instance per ServletContext |
| websocket | One instance per WebSocket lifecycle |

Example:

```java
@Component
@Scope("prototype")
public class OrderProcessor {
}
```

**Important:** Spring singleton scope is not the same as the Singleton design pattern. It is scoped to a bean definition within a container.

## Q11. Explain the Spring Bean lifecycle.

Typical lifecycle:

1. Bean instantiation.
2. Dependency population.
3. Aware callbacks, if applicable.
4. BeanPostProcessor before initialization.
5. Initialization callbacks.
6. BeanPostProcessor after initialization.
7. Bean is ready for use.
8. Destruction callbacks when the container shuts down.

Example:

```java
@Component
public class OrderService {

    @PostConstruct
    public void init() {
        System.out.println("Bean initialized");
    }

    @PreDestroy
    public void destroy() {
        System.out.println("Bean destroyed");
    }
}
```

Imports:

```java
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Component;
```

## Q12. What is the difference between `@Component`, `@Service`, `@Repository`, and `@Controller`?

| Annotation | Purpose |
|---|---|
| `@Component` | Generic Spring-managed component |
| `@Service` | Service or business logic layer |
| `@Repository` | Data access layer; supports persistence exception translation |
| `@Controller` | Spring MVC controller |
| `@RestController` | REST controller returning response bodies |

These annotations are specialized forms of `@Component`.

## Q13. What is component scanning?

Component scanning allows Spring to discover classes annotated with stereotypes such as `@Component`, `@Service`, `@Repository`, and `@Controller`.

Example:

```java
@SpringBootApplication(
    scanBasePackages = "com.example.orders"
)
public class OrderApplication {
}
```

## Q14. Constructor injection vs Setter injection vs Field injection?

| Constructor | Setter | Field |
|---|---|---|
| Recommended for mandatory dependencies | Useful for optional dependencies | Generally discouraged |
| Supports immutability | Dependency can be changed | Difficult to test directly |
| Easy unit testing | Useful for reconfiguration | Uses reflection |
| Makes required dependencies explicit | Can have optional dependencies | Hides dependency requirements |

**Recommended:**

```java
@Service
public class OrderService {

    private final PaymentService paymentService;

    public OrderService(PaymentService paymentService) {
        this.paymentService = paymentService;
    }
}
```

## Q15. What is `@Autowired`?

`@Autowired` tells Spring to resolve and inject a dependency.

It can be used on constructors, fields, and setter methods.

When a class has one constructor, `@Autowired` is not required.

## Q16. What is the difference between `@Primary` and `@Qualifier`?

When multiple beans implement the same interface, Spring needs to determine which one to inject.

`@Primary` defines the default candidate.

`@Qualifier` explicitly selects a bean.

Example:

```java
public interface PaymentService {
    void pay();
}
```

```java
@Service("upiPayment")
public class UpiPaymentService implements PaymentService {

    public void pay() {
        System.out.println("UPI payment");
    }
}
```

```java
@Service("cardPayment")
public class CardPaymentService implements PaymentService {

    public void pay() {
        System.out.println("Card payment");
    }
}
```

```java
@Service
public class OrderService {

    private final PaymentService paymentService;

    public OrderService(
            @Qualifier("upiPayment")
            PaymentService paymentService) {
        this.paymentService = paymentService;
    }
}
```

## Q17. Can we inject a prototype bean into a singleton bean?

Yes, but a prototype dependency injected directly into a singleton is normally resolved only when the singleton is created. It does not automatically produce a new prototype instance for every method invocation.

Possible solutions:
- `ObjectProvider<T>`.
- `Provider<T>`.
- Scoped proxy.
- Method injection.

Example:

```java
@Service
public class OrderService {

    private final ObjectProvider<OrderProcessor> provider;

    public OrderService(
            ObjectProvider<OrderProcessor> provider) {
        this.provider = provider;
    }

    public void process() {
        OrderProcessor processor = provider.getObject();
        processor.execute();
    }
}
```

Import:

```java
import org.springframework.beans.factory.ObjectProvider;
```

---

# 3. Spring Configuration and Auto Configuration

## Q18. What is `@Configuration`?

It marks a class as a source of bean definitions.

```java
@Configuration
public class AppConfig {

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }
}
```

Import:

```java
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
```

## Q19. What is the difference between `@Bean` and `@Component`?

| `@Bean` | `@Component` |
|---|---|
| Used on methods | Used on classes |
| Explicit bean registration | Component scanning |
| Useful for third-party classes | Useful for application-owned classes |
| Defined inside configuration classes typically | Defined on component classes |

## Q20. What is `@SpringBootApplication`?

It combines three important annotations:

```java
@SpringBootConfiguration
@EnableAutoConfiguration
@ComponentScan
```

It is the main entry point for a Spring Boot application.

## Q21. What is Spring Boot auto-configuration?

Auto-configuration automatically configures application components based on:
- Dependencies present on the classpath.
- Existing beans.
- Application properties.
- Conditional configuration.

For example, when Spring MVC dependencies are present, Spring Boot configures relevant web infrastructure.

## Q22. What are conditional annotations?

| Annotation | Purpose |
|---|---|
| `@ConditionalOnClass` | Matches when a class is present |
| `@ConditionalOnMissingClass` | Matches when a class is absent |
| `@ConditionalOnBean` | Matches when a bean exists |
| `@ConditionalOnMissingBean` | Matches when a bean does not exist |
| `@ConditionalOnProperty` | Matches based on a property |
| `@ConditionalOnWebApplication` | Matches a web application |
| `@ConditionalOnExpression` | Matches based on an expression |

Example:

```java
@Configuration
@ConditionalOnProperty(
    name = "payment.enabled",
    havingValue = "true"
)
public class PaymentConfiguration {

    @Bean
    public PaymentService paymentService() {
        return new DefaultPaymentService();
    }
}
```

## Q23. How do you disable a specific auto-configuration?

Example:

```java
@SpringBootApplication(
    exclude = DataSourceAutoConfiguration.class
)
public class OrderApplication {
}
```

Or configure an exclusion property:

```properties
spring.autoconfigure.exclude=org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
```

## Q24. How do you configure different environments?

Use Spring profiles.

```java
@Configuration
@Profile("dev")
public class DevConfiguration {
}
```

```properties
spring.profiles.active=dev
```

Environment-specific files:

```text
application.properties
application-dev.properties
application-qa.properties
application-prod.properties
```

## Q25. What is `@ConfigurationProperties`?

It binds external configuration to a Java object.

```java
@ConfigurationProperties(prefix = "payment")
public record PaymentProperties(
        String provider,
        int timeout,
        boolean enabled
) {
}
```

```properties
payment.provider=stripe
payment.timeout=5000
payment.enabled=true
```

Register the properties class using `@ConfigurationPropertiesScan` or `@EnableConfigurationProperties`.

It is generally preferable to use `@ConfigurationProperties` for grouped, type-safe configuration.

---

# 4. Spring Boot Internals

## Q26. Explain Spring Boot application startup flow.

Typical startup flow:

1. `main()` method executes.
2. `SpringApplication.run()` is called.
3. Environment and application configuration are prepared.
4. Application context is created.
5. Bean definitions are loaded.
6. BeanFactoryPostProcessors execute.
7. Beans are instantiated and dependencies injected.
8. BeanPostProcessors execute.
9. Embedded web server is started for a web application.
10. Application runners execute.
11. Application becomes ready.

Exact internal ordering has additional steps and varies by application type and Spring Boot version.

## Q27. What is `SpringApplication.run()`?

It bootstraps the Spring Boot application by creating and refreshing the application context, applying configuration, and starting the application.

```java
public static void main(String[] args) {
    SpringApplication.run(OrderApplication.class, args);
}
```

## Q28. What are ApplicationRunner and CommandLineRunner?

Both execute code after the application context has been initialized.

**CommandLineRunner:**

```java
@Component
public class DataInitializer implements CommandLineRunner {

    @Override
    public void run(String... args) {
        System.out.println("Application started");
    }
}
```

**ApplicationRunner:**

```java
@Component
public class StartupRunner implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) {
        System.out.println(args.getOptionNames());
    }
}
```

Difference: `ApplicationRunner` provides parsed application arguments, whereas `CommandLineRunner` receives raw strings.

## Q29. What are Spring Boot application events?

Events allow components to communicate through an event-driven mechanism.

Examples:
- `ApplicationStartingEvent`.
- `ApplicationEnvironmentPreparedEvent`.
- `ApplicationContextInitializedEvent`.
- `ApplicationPreparedEvent`.
- `ApplicationStartedEvent`.
- `ApplicationReadyEvent`.
- `ApplicationFailedEvent`.

Example:

```java
@Component
public class ApplicationListenerExample {

    @EventListener
    public void onReady(ApplicationReadyEvent event) {
        System.out.println("Application is ready");
    }
}
```

## Q30. How does Spring Boot start an embedded Tomcat server?

When web dependencies are present, auto-configuration creates the web application infrastructure and an embedded server factory.

For Servlet-based applications, Spring Boot commonly uses embedded Tomcat by default when the corresponding starter is included.

The server is started during application context refresh.

---

# 5. REST API Development

## Q31. What is REST?

REST (Representational State Transfer) is an architectural style for building network-based applications.

Common HTTP methods:

| Method | Purpose |
|---|---|
| GET | Retrieve resource |
| POST | Create resource or trigger processing |
| PUT | Replace resource |
| PATCH | Partially update resource |
| DELETE | Delete resource |

## Q32. What is the difference between `@Controller` and `@RestController`?

`@RestController` combines `@Controller` and `@ResponseBody`.

`@Controller` is commonly used for MVC views. `@RestController` is used for REST APIs where return values are serialized into response bodies.

## Q33. What is the difference between `@RequestParam` and `@PathVariable`?

**Path variable:**

```java
@GetMapping("/orders/{id}")
public Order getOrder(@PathVariable Long id) {
    return orderService.getOrder(id);
}
```

URL:

```text
GET /orders/101
```

**Request parameter:**

```java
@GetMapping("/orders")
public List<Order> getOrders(
        @RequestParam String status) {
    return orderService.findByStatus(status);
}
```

URL:

```text
GET /orders?status=PLACED
```

## Q34. What is ResponseEntity?

`ResponseEntity` provides control over:
- HTTP status.
- Headers.
- Response body.

Example:

```java
@PostMapping("/orders")
public ResponseEntity<OrderResponse> createOrder(
        @RequestBody OrderRequest request) {

    OrderResponse response =
            orderService.createOrder(request);

    return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(response);
}
```

## Q35. What are important HTTP status codes?

| Status | Meaning |
|---|---|
| 200 | OK |
| 201 | Created |
| 202 | Accepted |
| 204 | No Content |
| 400 | Bad Request |
| 401 | Unauthorized |
| 403 | Forbidden |
| 404 | Not Found |
| 409 | Conflict |
| 422 | Unprocessable Content |
| 429 | Too Many Requests |
| 500 | Internal Server Error |
| 503 | Service Unavailable |

## Q36. PUT vs PATCH?

- PUT generally replaces the complete resource representation.
- PATCH applies a partial modification.
- PUT is defined as idempotent.
- PATCH is not necessarily idempotent; it depends on the patch operation.

## Q37. What is idempotency in REST?

An operation is idempotent if repeating the same request produces the same intended server-side effect as executing it once.

GET, PUT, and DELETE are defined as idempotent in HTTP semantics. POST is not inherently idempotent.

For payment or order creation APIs, use an idempotency key to prevent duplicate processing.

Example:

```text
POST /payments
Idempotency-Key: order-payment-1001
```

The server stores the result associated with the key and returns the same logical result for retries.

## Q38. How do you version REST APIs?

Common approaches:

1. URI versioning: `/api/v1/orders`
2. Header versioning.
3. Query parameter versioning.
4. Media type versioning.

URI versioning is easy to understand and commonly used.

---

# 6. Validation and Exception Handling

## Q39. What is Bean Validation?

Bean Validation validates request and object fields using annotations.

Example:

```java
public class OrderRequest {

    @NotNull
    private Long customerId;

    @NotBlank
    private String productName;

    @Positive
    private int quantity;

    @DecimalMin("0.01")
    private BigDecimal amount;
}
```

Imports:

```java
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.DecimalMin;
import java.math.BigDecimal;
```

Controller:

```java
@PostMapping
public ResponseEntity<String> createOrder(
        @Valid @RequestBody OrderRequest request) {

    return ResponseEntity.ok("Order created");
}
```

## Q40. What is the difference between `@Valid` and `@Validated`?

| `@Valid` | `@Validated` |
|---|---|
| Jakarta Bean Validation annotation | Spring validation annotation |
| Supports cascaded validation | Supports validation groups |
| Standard Java validation | Spring-specific integration |

## Q41. How do you create custom validation?

Create a custom annotation and implement `ConstraintValidator`.

Annotation:

```java
@Documented
@Constraint(validatedBy = ProductCodeValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidProductCode {

    String message() default "Invalid product code";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
```

Validator:

```java
public class ProductCodeValidator
        implements ConstraintValidator<ValidProductCode, String> {

    @Override
    public boolean isValid(
            String value,
            ConstraintValidatorContext context) {

        return value != null &&
               value.matches("PROD-[0-9]+");
    }
}
```

Imports:

```java
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.lang.annotation.*;
```

## Q42. How do you handle global exceptions?

Use `@RestControllerAdvice` and `@ExceptionHandler`.

```java
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<String> handleNotFound(
            OrderNotFoundException ex) {

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGeneral(
            Exception ex) {

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Something went wrong");
    }
}
```

Imports:

```java
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
```

In production, use a structured error response containing timestamp, status, error code, message, and request path. Avoid exposing stack traces or sensitive internal details.

## Q43. What is the difference between `@ExceptionHandler` and `@ControllerAdvice`?

- `@ExceptionHandler` defines how a particular exception is handled.
- `@ControllerAdvice` centralizes exception handling across controllers.
- `@RestControllerAdvice` provides global REST exception handling with response-body semantics.

---

# 7. DTO, Serialization and Mapping

## Q44. What is DTO?

DTO stands for Data Transfer Object. It transfers data between application layers or across API boundaries.

Benefits:
- Hides internal entity structure.
- Avoids exposing sensitive database fields.
- Controls API request and response structure.
- Reduces unnecessary data transfer.
- Separates persistence models from API contracts.

## Q45. Why should we not expose JPA entities directly from REST APIs?

Because it can cause:
- Sensitive fields being exposed.
- Lazy-loading exceptions.
- Infinite recursion in bidirectional relationships.
- Tight coupling between API and database schema.
- Accidental over-fetching.

Use request and response DTOs instead.

## Q46. What is MapStruct?

MapStruct is a compile-time Java bean mapping library.

Example:

```java
@Mapper(componentModel = "spring")
public interface OrderMapper {

    OrderResponse toResponse(Order order);

    Order toEntity(OrderRequest request);
}
```

It generates implementation code during compilation.

## Q47. MapStruct vs ModelMapper?

| MapStruct | ModelMapper |
|---|---|
| Compile-time mapping | Runtime mapping |
| Generated Java code | Reflection-based mapping |
| Generally faster | Additional runtime overhead |
| Compile-time error detection | More dynamic |
| Good for predictable enterprise mapping | Useful for dynamic mapping scenarios |

## Q48. What is serialization and deserialization?

- Serialization: converting a Java object into JSON or another representation.
- Deserialization: converting JSON or another representation into a Java object.

Spring Boot commonly uses Jackson for JSON conversion.

Example:

```java
ObjectMapper mapper = new ObjectMapper();

String json = mapper.writeValueAsString(order);

Order order =
        mapper.readValue(json, Order.class);
```

Import:

```java
import com.fasterxml.jackson.databind.ObjectMapper;
```

---

# 8. Spring Data JPA

## Q49. What is JPA?

JPA (Jakarta Persistence API) is a specification for object-relational mapping in Java.

Hibernate is a popular implementation of JPA.

Spring Data JPA simplifies repository development on top of JPA.

## Q50. What is the difference between JPA, Hibernate, and Spring Data JPA?

| JPA | Hibernate | Spring Data JPA |
|---|---|---|
| Specification | JPA implementation and ORM framework | Repository abstraction |
| Defines persistence APIs | Implements persistence functionality | Reduces repository boilerplate |
| Provider-independent API | Provider-specific features available | Works with JPA providers |

## Q51. What is an Entity?

An entity is a Java class mapped to a database table.

```java
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String status;

    private BigDecimal amount;
}
```

Imports:

```java
import jakarta.persistence.*;
import java.math.BigDecimal;
```

## Q52. What is JpaRepository?

`JpaRepository` provides CRUD, pagination, sorting, and JPA-specific repository operations.

```java
public interface OrderRepository
        extends JpaRepository<Order, Long> {

    List<Order> findByStatus(String status);

    Optional<Order> findById(Long id);
}
```

## Q53. What are derived query methods?

Spring Data JPA generates queries from repository method names.

Examples:

```java
List<Order> findByStatus(String status);

List<Order> findByStatusAndAmountGreaterThan(
        String status, BigDecimal amount);

List<Order> findTop5ByStatusOrderByCreatedAtDesc(
        String status);

boolean existsByCustomerId(Long customerId);

long countByStatus(String status);
```

## Q54. What is JPQL?

JPQL is an object-oriented query language that operates on entity classes and their fields instead of database table names and columns.

```java
@Query("""
    SELECT o FROM Order o
    WHERE o.status = :status
""")
List<Order> findOrdersByStatus(
        @Param("status") String status);
```

## Q55. JPQL vs Native SQL?

| JPQL | Native SQL |
|---|---|
| Uses entity and property names | Uses database tables and columns |
| Database-independent in principle | Database-specific features possible |
| Works with entity model | Works with SQL |
| Easier portability | Useful for complex database-specific queries |

## Q56. What is projection in Spring Data JPA?

Projection retrieves only the required fields rather than loading the complete entity.

Interface projection:

```java
public interface OrderSummary {
    Long getId();
    String getStatus();
}
```

```java
List<OrderSummary> findByStatus(String status);
```

DTO projection:

```java
@Query("""
    SELECT new com.example.dto.OrderSummaryDto(
        o.id, o.status
    )
    FROM Order o
""")
List<OrderSummaryDto> findSummaries();
```

## Q57. What is pagination?

Pagination retrieves records in smaller chunks.

```java
Page<Order> findByStatus(
        String status, Pageable pageable);
```

Usage:

```java
Pageable pageable =
        PageRequest.of(0, 10,
                Sort.by("createdAt").descending());

Page<Order> orders =
        orderRepository.findByStatus("PLACED", pageable);
```

Imports:

```java
import org.springframework.data.domain.*;
```

## Q58. What is the difference between `save()` and `saveAndFlush()`?

- `save()` persists or merges an entity, but SQL execution may be deferred until flush or transaction commit.
- `saveAndFlush()` saves and triggers a flush immediately.
- Flush synchronizes persistence-context changes with the database; it does not necessarily commit the transaction.

---

# 9. Hibernate and JPA Internals

## Q59. What is Persistence Context?

Persistence Context is an in-memory collection of managed entity instances associated with an EntityManager.

It tracks:
- Managed entities.
- Entity changes.
- Entity identity.
- Pending database operations.

## Q60. What are entity lifecycle states?

| State | Description |
|---|---|
| Transient | New object, not managed |
| Managed | Tracked by persistence context |
| Detached | Previously managed, no longer tracked |
| Removed | Marked for deletion |

## Q61. What is dirty checking?

Hibernate automatically detects modifications to managed entities and generates SQL updates when changes are flushed.

Example:

```java
@Transactional
public void updateOrder(Long id) {

    Order order = orderRepository.findById(id)
            .orElseThrow();

    order.setStatus("SHIPPED");
}
```

No explicit `save()` is required for an already managed entity in the same transaction.

## Q62. What is the first-level cache?

First-level cache is the persistence-context cache associated with an EntityManager.

It is enabled by default.

Within the same persistence context, repeated retrieval of the same entity identity generally returns the same managed instance.

## Q63. What is second-level cache?

Second-level cache is shared across persistence contexts within a SessionFactory.

Unlike first-level cache, it is optional and requires configuration and a cache provider.

It can reduce repeated database reads.

## Q64. What is the N+1 query problem?

N+1 occurs when one query retrieves parent entities and additional queries retrieve related entities individually.

Example:

- One query fetches 100 orders.
- 100 additional queries fetch customer information.

Solutions:
- JOIN FETCH.
- EntityGraph.
- DTO projections.
- Batch fetching.
- Carefully selected fetch strategies.

Example:

```java
@Query("""
    SELECT o FROM Order o
    JOIN FETCH o.customer
""")
List<Order> findAllWithCustomer();
```

## Q65. What is the difference between `getReferenceById()` and `findById()`?

- `findById()` executes a lookup and returns an `Optional`.
- `getReferenceById()` may return a lazy reference/proxy and defer database access until the entity is accessed.
- A missing referenced entity may result in an exception when the proxy is initialized.

---

# 10. Entity Relationships

## Q66. What are the types of JPA relationships?

1. One-to-One.
2. One-to-Many.
3. Many-to-One.
4. Many-to-Many.

Example:

```java
@Entity
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;
}
```

## Q67. What is cascade?

Cascade propagates selected persistence operations from a parent entity to related entities.

Types:
- PERSIST.
- MERGE.
- REMOVE.
- REFRESH.
- DETACH.
- ALL.

Use cascade carefully. For example, deleting an order should not accidentally delete a shared customer.

## Q68. What is the difference between CascadeType and FetchType?

- Cascade controls propagation of persistence operations.
- FetchType controls when related data is loaded.

These are independent concepts.

## Q69. LAZY vs EAGER loading?

| LAZY | EAGER |
|---|---|
| Loads association when accessed | Loads association eagerly |
| Can reduce unnecessary data loading | Can cause excessive loading |
| Requires careful transaction and query design | May result in unexpected queries |

JPA defaults:
- `@ManyToOne`: EAGER.
- `@OneToOne`: EAGER.
- `@OneToMany`: LAZY.
- `@ManyToMany`: LAZY.

For performance-sensitive applications, explicitly design fetch plans instead of relying on defaults.

## Q70. What is the owning side of a relationship?

The owning side controls the foreign key mapping or join table updates.

The inverse side uses `mappedBy`.

Example:

```java
@Entity
public class Customer {

    @Id
    private Long id;

    @OneToMany(mappedBy = "customer")
    private List<Order> orders = new ArrayList<>();
}
```

---

# 11. Transaction Management

## Q71. What is `@Transactional`?

`@Transactional` defines a transaction boundary around a method or class.

Example:

```java
@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Transactional
    public void createOrder(Order order) {
        orderRepository.save(order);
    }
}
```

## Q72. What are transaction propagation types?

| Propagation | Meaning |
|---|---|
| REQUIRED | Join existing transaction or create one |
| REQUIRES_NEW | Suspend existing transaction and create a new one |
| SUPPORTS | Join if available; otherwise run without one |
| NOT_SUPPORTED | Suspend existing transaction |
| MANDATORY | Require an existing transaction |
| NEVER | Fail if a transaction exists |
| NESTED | Use nested transaction semantics if supported |

## Q73. What are transaction isolation levels?

| Isolation | Description |
|---|---|
| READ_UNCOMMITTED | Allows dirty reads where supported |
| READ_COMMITTED | Prevents dirty reads |
| REPEATABLE_READ | Prevents non-repeatable reads |
| SERIALIZABLE | Strongest standard isolation |

Database behavior and exact guarantees vary by database engine.

## Q74. What are dirty reads, non-repeatable reads, and phantom reads?

- Dirty read: Reading uncommitted changes from another transaction.
- Non-repeatable read: Re-reading a row gives a different committed value.
- Phantom read: Repeating a range query returns a different set of rows.

## Q75. What is rollback behavior in Spring?

By default, Spring rolls back on unchecked exceptions (`RuntimeException` and `Error`), but not on checked exceptions.

Example:

```java
@Transactional(rollbackFor = Exception.class)
public void createOrder() throws Exception {
    // Database operations
}
```

## Q76. Why does `@Transactional` sometimes not work?

Common reasons:
- Self-invocation within the same bean.
- Method is not invoked through the Spring proxy.
- Incorrect transaction manager.
- Transaction annotation on an inappropriate method.
- Checked exception without rollback configuration.
- Private method or proxy limitations.
- Async execution using a different thread.

**Example of self-invocation:**

```java
@Service
public class OrderService {

    public void process() {
        saveOrder();
    }

    @Transactional
    public void saveOrder() {
        // Transaction may not be applied
        // through the proxy on self-invocation.
    }
}
```

Move the transactional operation to another Spring bean or invoke it through an appropriate proxy boundary.

## Q77. What is read-only transaction?

```java
@Transactional(readOnly = true)
public Order getOrder(Long id) {
    return orderRepository.findById(id).orElseThrow();
}
```

It communicates that the transaction is intended for reads and may allow optimizations depending on the transaction manager and database. It is not a universal guarantee that writes will be blocked.

---

# 12. Database Performance Optimization

## Q78. How do you improve Spring Boot API performance?

- Optimize SQL queries.
- Add appropriate database indexes.
- Avoid N+1 queries.
- Use pagination.
- Use DTO projections.
- Implement Redis caching when appropriate.
- Configure connection pooling.
- Avoid unnecessary object creation.
- Use asynchronous processing for suitable tasks.
- Measure latency and bottlenecks before optimizing.

## Q79. What is database indexing?

An index is a data structure that improves lookup performance by reducing the amount of data the database needs to scan.

Example:

```sql
CREATE INDEX idx_orders_status
ON orders(status);
```

Trade-offs:
- Faster reads for suitable queries.
- Additional storage.
- Slower inserts and updates due to index maintenance.

## Q80. What is connection pooling?

Connection pooling reuses database connections instead of opening a new connection for every request.

Spring Boot commonly uses HikariCP by default.

Configuration:

```properties
spring.datasource.hikari.maximum-pool-size=20
spring.datasource.hikari.minimum-idle=5
spring.datasource.hikari.connection-timeout=30000
```

## Q81. How do you optimize bulk inserts?

- Use JDBC batching.
- Configure Hibernate batch size.
- Avoid unnecessary entity loading.
- Flush and clear periodically.
- Consider JDBC batch operations for very large imports.

Example:

```properties
spring.jpa.properties.hibernate.jdbc.batch_size=50
spring.jpa.properties.hibernate.order_inserts=true
```

## Q82. Offset pagination vs keyset pagination?

- Offset pagination uses page number and offset.
- Keyset pagination uses the last-seen sort key to retrieve the next page.
- Keyset pagination is often more efficient for deep pagination with suitable indexed sort keys.
- Offset pagination is simpler for small result sets and arbitrary page navigation.

---

# 13. Spring Security

## Q83. What is Spring Security?

Spring Security provides authentication, authorization, protection against common web attacks, and integration with identity providers.

## Q84. Authentication vs Authorization?

| Authentication | Authorization |
|---|---|
| Verifies identity | Checks permissions |
| Who are you? | What can you access? |
| Login and credentials | Roles and authorities |

## Q85. What is SecurityFilterChain?

It defines the HTTP security configuration and filter chain.

Example:

```java
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http) throws Exception {

        return http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/public/**").permitAll()
                        .requestMatchers("/admin/**")
                            .hasRole("ADMIN")
                        .anyRequest().authenticated()
                )
                .httpBasic(Customizer.withDefaults())
                .build();
    }
}
```

Imports:

```java
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
```

**Production note:** Do not disable CSRF indiscriminately. The correct configuration depends on whether the application uses browser sessions, cookies, or stateless bearer tokens.

## Q86. What is SecurityContextHolder?

It stores the current security context, including the authenticated principal and authorities.

```java
Authentication authentication =
        SecurityContextHolder.getContext().getAuthentication();

String username = authentication.getName();
```

## Q87. What is PasswordEncoder?

It hashes passwords before storage and verifies passwords during authentication.

Example:

```java
@Bean
public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
}
```

Never store plain-text passwords.

## Q88. What is the difference between roles and authorities?

- Authorities represent permissions.
- Roles are commonly represented as authorities with the `ROLE_` prefix.
- `hasRole("ADMIN")` typically checks for `ROLE_ADMIN`.
- `hasAuthority("ORDER_READ")` checks an exact authority.

## Q89. What is CSRF?

Cross-Site Request Forgery tricks a user's authenticated browser into submitting an unwanted request to another site.

CSRF protection is especially relevant for cookie-based browser authentication.

For stateless APIs using bearer tokens supplied explicitly in the Authorization header, CSRF exposure differs, but configuration should still be based on the actual authentication mechanism.

## Q90. What is CORS?

Cross-Origin Resource Sharing controls which origins, methods, and headers a browser is allowed to use when accessing an API.

Example:

```java
@CrossOrigin(origins = "https://example.com")
@RestController
public class OrderController {
}
```

In production, prefer explicit allowed origins rather than unrestricted wildcard configuration with credentials.

---

# 14. JWT, OAuth2 and OpenID Connect

## Q91. What is JWT?

JWT (JSON Web Token) is a compact token format used to represent claims between parties.

Structure:

```text
Header.Payload.Signature
```

Common claims:
- `sub`: Subject.
- `iss`: Issuer.
- `aud`: Audience.
- `exp`: Expiration.
- `iat`: Issued at.
- `nbf`: Not before.

## Q92. How does JWT authentication work?

1. User submits credentials.
2. Authentication server verifies credentials.
3. Server issues a signed access token.
4. Client sends the token in the Authorization header.
5. Resource server validates the token.
6. Claims and authorities are used for authorization.

Example:

```http
Authorization: Bearer eyJhbGciOi...
```

## Q93. Access token vs Refresh token?

| Access Token | Refresh Token |
|---|---|
| Used to access APIs | Used to obtain new access tokens |
| Short lifetime | Usually longer lifetime |
| Sent to resource APIs | Sent to authorization/token endpoint |
| Limited scope | Must be protected carefully |

Refresh-token rotation and revocation are important security controls.

## Q94. What is OAuth 2.0?

OAuth 2.0 is an authorization framework that allows applications to obtain limited access to protected resources.

Common grant flows:
- Authorization Code with PKCE.
- Client Credentials.
- Device Authorization.

The Resource Owner Password Credentials grant should not be used for new applications.

## Q95. What is OpenID Connect?

OpenID Connect (OIDC) is an identity layer built on OAuth 2.0.

It adds authentication and an ID token to OAuth 2.0.

| OAuth 2.0 | OIDC |
|---|---|
| Authorization framework | Authentication and identity layer |
| Access token | ID token plus OAuth tokens |
| Grants delegated access | Communicates authenticated user identity |

## Q96. What is a Resource Server?

A resource server hosts protected APIs and validates access tokens before allowing access.

Spring Security supports JWT and opaque-token resource server configurations.

---

# 15. Spring AOP

## Q97. What is AOP?

Aspect-Oriented Programming separates cross-cutting concerns from business logic.

Examples:
- Logging.
- Security.
- Transaction management.
- Performance monitoring.
- Auditing.

## Q98. What are Aspect, Advice, Pointcut and Join Point?

| Term | Meaning |
|---|---|
| Aspect | Class containing cross-cutting behavior |
| Advice | Action executed at a selected point |
| Pointcut | Expression selecting join points |
| Join Point | A point during program execution |
| Target | Object being advised |
| Proxy | Object through which advice is applied |

## Q99. What are types of advice?

- `@Before`.
- `@After`.
- `@AfterReturning`.
- `@AfterThrowing`.
- `@Around`.

## Q100. Explain AOP with an example.

```java
@Aspect
@Component
public class LoggingAspect {

    @Before("execution(* com.example.service.*.*(..))")
    public void logBefore() {
        System.out.println("Method execution started");
    }
}
```

Imports:

```java
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
```

## Q101. How does Spring AOP work internally?

Spring AOP commonly uses proxies:
- JDK dynamic proxies for interface-based proxying.
- CGLIB-based subclass proxies when appropriate.

Calls made through the proxy can trigger advice before or after the target method.

Self-invocation generally bypasses proxy advice.

---

# 16. Spring MVC Internals

## Q102. What is DispatcherServlet?

DispatcherServlet is the front controller in Spring MVC.

It receives incoming HTTP requests and coordinates request processing.

## Q103. Explain the Spring MVC request flow.

1. Client sends HTTP request.
2. DispatcherServlet receives the request.
3. HandlerMapping identifies the appropriate handler.
4. HandlerAdapter invokes the controller.
5. Controller executes business logic through services.
6. Response is processed through message converters or a view resolver.
7. DispatcherServlet sends the response to the client.

## Q104. Filter vs Interceptor?

| Filter | Interceptor |
|---|---|
| Servlet specification | Spring MVC abstraction |
| Executes before DispatcherServlet and after response processing | Executes around handler execution |
| Can operate outside Spring MVC | Works with Spring MVC handlers |
| Useful for CORS, request wrappers, security integration | Useful for request-specific application concerns |

## Q105. What are HandlerMapping and HandlerAdapter?

- HandlerMapping identifies the handler for a request.
- HandlerAdapter invokes the selected handler.

## Q106. What is HttpMessageConverter?

It converts HTTP request and response bodies between Java objects and representations such as JSON or XML.

Jackson JSON converters are commonly used in Spring Boot REST applications.

---

# 17. Async Processing and Scheduling

## Q107. What is `@Async`?

It executes a method asynchronously using a Spring-managed task executor.

```java
@Configuration
@EnableAsync
public class AsyncConfig {

    @Bean
    public Executor taskExecutor() {
        ThreadPoolTaskExecutor executor =
                new ThreadPoolTaskExecutor();

        executor.setCorePoolSize(5);
        executor.setMaxPoolSize(10);
        executor.setQueueCapacity(100);
        executor.setThreadNamePrefix("order-");

        executor.initialize();
        return executor;
    }
}
```

```java
@Service
public class NotificationService {

    @Async
    public CompletableFuture<String> sendEmail() {
        return CompletableFuture.completedFuture(
                "Email sent");
    }
}
```

Imports:

```java
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import org.springframework.context.annotation.*;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
```

## Q108. What is `@Scheduled`?

It executes methods periodically.

```java
@Component
@EnableScheduling
public class OrderScheduler {

    @Scheduled(fixedRate = 60000)
    public void processPendingOrders() {
        System.out.println("Processing pending orders");
    }
}
```

Common scheduling options:
- `fixedRate`.
- `fixedDelay`.
- `initialDelay`.
- `cron`.

## Q109. FixedRate vs FixedDelay?

- FixedRate schedules executions at a regular interval measured from start times.
- FixedDelay schedules the next execution after the previous execution finishes and the configured delay passes.

## Q110. What are the risks of async processing?

- Thread pool exhaustion.
- Lost execution during shutdown.
- Missing security or tracing context propagation.
- Exception handling complexity.
- Race conditions.
- Unbounded queues.

Use bounded executors and monitor queue sizes, active threads, and rejected tasks.

---

# 18. Caching and Redis

## Q111. What is caching?

Caching stores frequently accessed data in a faster storage layer to reduce repeated computation or database access.

Benefits:
- Lower response latency.
- Reduced database load.
- Higher throughput.

## Q112. What are Spring Cache annotations?

| Annotation | Purpose |
|---|---|
| `@EnableCaching` | Enables caching |
| `@Cacheable` | Reads from cache or computes and stores |
| `@CachePut` | Always executes method and updates cache |
| `@CacheEvict` | Removes cache entries |
| `@Caching` | Combines cache operations |

Example:

```java
@Service
public class ProductService {

    @Cacheable(value = "products", key = "#id")
    public Product getProduct(Long id) {
        return productRepository.findById(id)
                .orElseThrow();
    }

    @CacheEvict(value = "products", key = "#id")
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
```

## Q113. What is Redis?

Redis is an in-memory data store commonly used for caching, counters, distributed coordination, and other data structures.

## Q114. What is Cache Aside?

1. Application checks cache.
2. If cache hit, return cached data.
3. If cache miss, query database.
4. Store the result in cache.
5. Return result.

## Q115. What is cache invalidation?

Cache invalidation removes or updates stale cache data after the underlying data changes.

Common strategies:
- TTL.
- Explicit eviction.
- Write-through.
- Event-based invalidation.
- Versioned cache keys.

## Q116. What is cache stampede?

A large number of requests simultaneously miss an expired cache entry and hit the database.

Solutions:
- Distributed locking or request coalescing.
- Randomized TTL.
- Refresh ahead.
- Stale-while-revalidate strategies.

## Q117. What is the difference between `@Cacheable` and `@CachePut`?

- `@Cacheable` can skip method execution on a cache hit.
- `@CachePut` always executes the method and updates the cache with its result.

---

# 19. Kafka and Messaging

## Q118. What is Apache Kafka?

Kafka is a distributed event-streaming platform used for high-throughput, durable event publishing and consumption.

## Q119. Explain Kafka architecture.

Important components:
- Producer.
- Consumer.
- Broker.
- Topic.
- Partition.
- Offset.
- Consumer Group.
- Replication.

## Q120. What is a Kafka topic?

A topic is a logical category or stream where events are published.

Topics are divided into partitions for scalability and parallel consumption.

## Q121. What is a partition?

A partition is an ordered, append-only sequence of records within a topic.

Ordering is guaranteed within a partition, not across the entire topic.

## Q122. What is a consumer group?

A consumer group is a set of consumers sharing the work of consuming a topic.

Within a consumer group, a partition is assigned to at most one active consumer at a time under normal group assignment.

## Q123. What is an offset?

An offset identifies a record's position within a Kafka partition.

Consumers track offsets to manage progress.

## Q124. How do you publish a Kafka message using Spring Boot?

```java
@Service
public class OrderProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public OrderProducer(
            KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publishOrder(String message) {
        kafkaTemplate.send("orders", message);
    }
}
```

Import:

```java
import org.springframework.kafka.core.KafkaTemplate;
```

## Q125. How do you consume Kafka messages?

```java
@Component
public class OrderConsumer {

    @KafkaListener(
        topics = "orders",
        groupId = "order-service"
    )
    public void consume(String message) {
        System.out.println(message);
    }
}
```

Import:

```java
import org.springframework.kafka.annotation.KafkaListener;
```

## Q126. What is the difference between at-most-once, at-least-once and exactly-once delivery?

| Delivery | Meaning |
|---|---|
| At-most-once | A message may be lost, but is not redelivered |
| At-least-once | Messages are retried; duplicates are possible |
| Exactly-once | Processing semantics are coordinated to avoid duplicate effects within a defined transactional boundary |

For end-to-end business processing, idempotency is still essential. Kafka exactly-once semantics alone do not make external database or payment operations exactly once.

## Q127. What is a Dead Letter Topic?

A dead letter topic stores messages that could not be processed successfully after configured retries or recovery handling.

It allows investigation and controlled replay.

## Q128. How do you handle duplicate Kafka messages?

- Use an idempotent consumer.
- Store processed event IDs.
- Use unique business keys.
- Apply database uniqueness constraints.
- Use transactional processing where appropriate.

---

# 20. Spring Boot Testing

## Q129. What is the difference between unit testing and integration testing?

| Unit Testing | Integration Testing |
|---|---|
| Tests isolated units | Tests component interactions |
| Dependencies are usually mocked | Real infrastructure may be used |
| Fast | Usually slower |
| Mockito commonly used | Spring context, Testcontainers, MockMvc |

## Q130. What is JUnit 5?

JUnit 5 is a Java testing framework consisting of the JUnit Platform, JUnit Jupiter, and JUnit Vintage.

## Q131. What is Mockito?

Mockito is used to create mocks, stubs, and verify interactions in unit tests.

Example:

```java
@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @Mock
    private OrderRepository repository;

    @InjectMocks
    private OrderService orderService;

    @Test
    void shouldFindOrder() {

        Order order = new Order();

        when(repository.findById(1L))
                .thenReturn(Optional.of(order));

        Order result = orderService.getOrder(1L);

        assertNotNull(result);
        verify(repository).findById(1L);
    }
}
```

## Q132. What is `@SpringBootTest`?

It loads the Spring application context for integration testing.

```java
@SpringBootTest
class OrderIntegrationTest {

    @Test
    void contextLoads() {
    }
}
```

## Q133. What is `@WebMvcTest`?

It loads a focused Spring MVC test slice, commonly for testing controllers.

```java
@WebMvcTest(OrderController.class)
class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;
}
```

## Q134. What is MockMvc?

MockMvc tests Spring MVC request handling without starting a real HTTP server.

## Q135. What is Testcontainers?

Testcontainers provides disposable containerized dependencies for tests, such as PostgreSQL, Kafka, and Redis.

It helps test against realistic infrastructure instead of relying entirely on mocks or in-memory substitutes.

---

# 21. Microservices

## Q136. What are microservices?

Microservices architecture decomposes an application into independently deployable services organized around business capabilities.

Example:
- Order Service.
- Payment Service.
- Inventory Service.
- Customer Service.
- Notification Service.

## Q137. Monolithic vs Microservices?

| Monolithic | Microservices |
|---|---|
| One deployable application | Multiple deployable services |
| Simpler initial deployment | More infrastructure complexity |
| Often shared process and deployment | Independent deployment possible |
| In-process communication | Network communication |
| Easier local transactions | Distributed consistency challenges |

## Q138. What is API Gateway?

API Gateway provides a single entry point for client requests.

Responsibilities:
- Routing.
- Authentication integration.
- Rate limiting.
- Request/response transformation.
- Load balancing integration.
- Monitoring.

## Q139. What is Service Discovery?

Service discovery allows services to locate other service instances dynamically.

Examples include Eureka and Consul.

In Kubernetes, service discovery is commonly provided by Kubernetes Services and DNS.

## Q140. RestClient vs WebClient vs OpenFeign?

| Client | Characteristics |
|---|---|
| RestClient | Synchronous, modern Spring HTTP client |
| WebClient | Non-blocking reactive HTTP client |
| OpenFeign | Declarative HTTP client |

Choose based on the application's execution model, ecosystem, and requirements.

## Q141. How do microservices communicate?

**Synchronous:**
- REST.
- gRPC.

**Asynchronous:**
- Kafka.
- RabbitMQ.
- Event-driven messaging.

Use synchronous communication when an immediate response is required; use asynchronous communication when decoupling, buffering, and event-driven workflows are valuable.

---

# 22. Resilience and Fault Tolerance

## Q142. What is a Circuit Breaker?

A circuit breaker prevents repeated calls to a failing dependency.

States:
- CLOSED.
- OPEN.
- HALF_OPEN.

## Q143. What is Retry?

Retry attempts an operation again after a failure.

Use retries for transient failures and apply backoff and jitter. Avoid retrying non-idempotent operations without safeguards.

## Q144. What is Rate Limiting?

Rate limiting restricts the number of requests accepted within a time window.

It protects services from overload and abuse.

## Q145. What is Bulkhead?

Bulkhead isolates resources so that a failure or overload in one part of the system does not exhaust resources for all other parts.

## Q146. What is Resilience4j?

Resilience4j is a fault-tolerance library supporting:
- Circuit breaker.
- Retry.
- Rate limiter.
- Bulkhead.
- Time limiter.

## Q147. What is Timeout?

A timeout limits how long an operation is allowed to wait for a response.

Always configure sensible timeouts for remote calls.

---

# 23. Distributed Systems

## Q148. What is a distributed transaction?

A distributed transaction coordinates changes across multiple independent resources or services.

Traditional database transactions do not automatically provide atomicity across independent microservices.

## Q149. What is the Saga pattern?

Saga coordinates a long-running business transaction as a sequence of local transactions.

If a step fails, compensating actions can reverse the business effects of earlier steps.

Example:

1. Create order.
2. Reserve inventory.
3. Process payment.
4. Confirm order.

If payment fails, release the reserved inventory and cancel the order.

## Q150. Choreography vs Orchestration?

| Choreography | Orchestration |
|---|---|
| Services react to events | Central coordinator controls workflow |
| Less centralized | Explicit workflow control |
| Can become difficult to trace | Easier centralized visibility |
| Useful for loosely coupled event flows | Useful for complex workflows |

## Q151. What is the Outbox Pattern?

The Outbox Pattern ensures a database update and event record are stored in the same local database transaction.

A separate publisher reads outbox records and publishes events to a message broker.

This avoids the dual-write problem where a database commit succeeds but message publishing fails, or vice versa.

## Q152. What is Eventual Consistency?

Eventual consistency means replicas or services may temporarily observe different values but converge when updates propagate successfully.

## Q153. What is Idempotency?

Idempotency ensures repeated processing of the same logical operation does not produce duplicate business effects.

Example: Processing the same payment event multiple times should not charge the customer multiple times.

---

# 24. Logging and Observability

## Q154. What is Spring Boot Actuator?

Actuator provides production monitoring and management endpoints.

Common endpoints:
- `/actuator/health`
- `/actuator/info`
- `/actuator/metrics`
- `/actuator/prometheus`
- `/actuator/loggers`

Expose only required endpoints and secure sensitive management endpoints.

## Q155. What is Micrometer?

Micrometer provides a vendor-neutral instrumentation facade for application metrics.

It integrates with monitoring systems such as Prometheus.

## Q156. What is distributed tracing?

Distributed tracing follows a request as it travels across multiple services.

It helps identify:
- Slow services.
- Latency bottlenecks.
- Failed downstream calls.
- Request execution paths.

## Q157. What are logs, metrics, and traces?

| Type | Purpose |
|---|---|
| Logs | Detailed events |
| Metrics | Numerical measurements over time |
| Traces | Request flow across services |

## Q158. What is correlation ID?

A correlation ID identifies related operations across services and helps trace a request through logs.

Use trace IDs and span IDs from distributed tracing systems where possible.

---

# 25. Production and Deployment

## Q159. How do you deploy a Spring Boot application?

Typical process:

1. Build the application using Maven.
2. Run automated tests.
3. Package the executable JAR.
4. Build a Docker image.
5. Push the image to a container registry.
6. Deploy using Kubernetes or another platform.
7. Configure environment-specific properties and secrets.
8. Configure health checks and monitoring.

## Q160. What is Docker?

Docker packages an application and its dependencies into a container image.

Example Dockerfile:

```dockerfile
FROM eclipse-temurin:17-jre
WORKDIR /app
COPY target/order-service.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
```

## Q161. What is Kubernetes?

Kubernetes orchestrates containerized applications.

Core concepts:
- Pod.
- Deployment.
- Service.
- ConfigMap.
- Secret.
- Ingress.
- Horizontal Pod Autoscaler.
- Liveness probe.
- Readiness probe.

## Q162. Liveness vs Readiness?

- Liveness: Indicates whether the application should be restarted.
- Readiness: Indicates whether the application is ready to receive traffic.

A dependency outage should not automatically make the application fail its liveness check unless the application itself is unrecoverable.

## Q163. What is graceful shutdown?

Graceful shutdown allows an application to stop accepting new requests and finish in-flight work within a configured period.

Example:

```properties
server.shutdown=graceful
spring.lifecycle.timeout-per-shutdown-phase=30s
```

## Q164. How do you externalize configuration?

Use:
- Environment variables.
- Configuration files.
- Configuration servers.
- Container orchestration configuration.
- Secret management solutions.

Never hardcode production credentials in source code.

---

# 26. Advanced Spring Boot

## Q165. How do you create a custom Spring Boot starter?

Typical steps:

1. Create an auto-configuration module.
2. Implement configuration classes.
3. Use conditional annotations.
4. Define beans.
5. Register auto-configuration metadata.
6. Package reusable dependencies and configuration as a starter.

For Spring Boot 3, auto-configuration classes are typically registered in:

`META-INF/spring/org.springframework.boot.autoconfigure.AutoConfiguration.imports`

## Q166. What is a custom annotation?

A custom annotation defines reusable metadata that can be interpreted by application code or Spring infrastructure.

Example:

```java
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface TrackExecution {
}
```

It can be combined with AOP to implement execution tracking.

## Q167. What is BeanPostProcessor?

A BeanPostProcessor allows custom processing of bean instances before and after initialization.

It is an important Spring extension point and is used by Spring infrastructure for features such as proxy creation.

## Q168. What is BeanFactoryPostProcessor?

It modifies bean definitions or configuration metadata before regular beans are instantiated.

## Q169. What is the difference between BeanPostProcessor and BeanFactoryPostProcessor?

| BeanPostProcessor | BeanFactoryPostProcessor |
|---|---|
| Works with bean instances | Works with bean definitions and factory metadata |
| Runs around bean initialization | Runs before normal bean instantiation |
| Can wrap beans with proxies | Can modify bean definitions |

---

# 27. Modern Spring Boot and Java

## Q170. What are the major Spring Boot 3 changes?

Important changes include:
- Java 17 minimum baseline.
- Spring Framework 6.
- Jakarta EE namespace migration.
- AOT processing improvements.
- Native image support.
- Observability improvements.
- Updated dependency ecosystem.

## Q171. What is the Jakarta migration?

Many Java EE APIs moved from `javax.*` to `jakarta.*`.

Example:

```java
// Older namespace
import javax.persistence.Entity;

// Jakarta namespace
import jakarta.persistence.Entity;
```

## Q172. What are GraalVM Native Images?

Native images compile Java applications ahead of time into native executables.

Benefits:
- Faster startup.
- Potentially lower memory usage.

Trade-offs:
- Build complexity.
- Reflection and dynamic features may require configuration.
- Not every library is equally compatible.

## Q173. What are Java Virtual Threads?

Virtual threads are lightweight threads introduced as a standard feature in Java 21.

They make thread-per-request blocking workloads easier to scale by reducing the cost of managing large numbers of concurrent threads.

They do not automatically make blocking code non-blocking or eliminate database connection limits.

## Q174. What is AOT processing?

Ahead-of-Time processing performs certain application analysis and code generation before runtime.

Spring uses AOT processing to support optimized startup and native-image generation.

---

# 28. Reactive Programming

## Q175. What is Reactive Programming?

Reactive programming is a programming approach centered around asynchronous data streams and non-blocking processing.

## Q176. What is Project Reactor?

Project Reactor is a reactive programming library used by Spring WebFlux.

It provides:
- Mono.
- Flux.
- Reactive operators.
- Backpressure support.

## Q177. What is Mono?

Mono represents a reactive publisher that emits zero or one item, followed by completion or an error.

Example:

```java
Mono<String> result =
        Mono.just("Order created");
```

## Q178. What is Flux?

Flux represents a reactive publisher that can emit zero to many items.

Example:

```java
Flux<String> orders =
        Flux.just("Order1", "Order2", "Order3");
```

## Q179. What is backpressure?

Backpressure allows consumers to control how much data they receive from publishers.

It helps prevent fast producers from overwhelming slower consumers.

## Q180. Spring MVC vs Spring WebFlux?

| Spring MVC | Spring WebFlux |
|---|---|
| Servlet-based | Reactive architecture |
| Commonly thread-per-request | Non-blocking event-loop model |
| Blocking APIs are natural | Non-blocking APIs are preferred |
| Good for traditional applications | Good for suitable high-concurrency I/O workloads |

Reactive programming is not automatically faster. Choose it when the full request-processing stack can benefit from non-blocking execution.

---

# 29. Architecture and Design Patterns

## Q181. What is layered architecture?

Typical layers:

1. Controller.
2. Service.
3. Repository.
4. Database.

Each layer has a distinct responsibility.

## Q182. What is Hexagonal Architecture?

Hexagonal Architecture separates core business logic from external infrastructure using ports and adapters.

Benefits:
- Better testability.
- Reduced infrastructure coupling.
- Easier technology replacement.

## Q183. What is SOLID?

| Principle | Meaning |
|---|---|
| S | Single Responsibility |
| O | Open/Closed |
| L | Liskov Substitution |
| I | Interface Segregation |
| D | Dependency Inversion |

## Q184. What design patterns are commonly used in Spring Boot?

- Singleton.
- Factory.
- Proxy.
- Strategy.
- Builder.
- Template Method.
- Observer.
- Adapter.
- Decorator.
- Chain of Responsibility.

Examples:
- Spring beans commonly use singleton scope.
- Spring AOP uses proxies.
- Strategy pattern can select payment providers.
- Template patterns appear in infrastructure abstractions.

## Q185. What is the Repository Pattern?

The Repository Pattern abstracts persistence operations behind an interface.

Spring Data JPA provides repository abstractions that reduce boilerplate.

## Q186. What is the Service Layer Pattern?

It encapsulates business operations and coordinates repositories and other services.

Controllers should not contain complex business logic.

---

# 30. Scenario-Based Interview Questions

## Q187. An API is taking 5 seconds to respond. How do you troubleshoot it?

**Answer:**

I would investigate systematically rather than immediately increasing resources.

1. Check API latency metrics.
2. Identify whether time is spent in application code, database, or downstream calls.
3. Check distributed traces.
4. Analyze SQL execution plans.
5. Look for N+1 queries.
6. Check connection pool utilization.
7. Inspect thread pool saturation.
8. Check cache hit ratio.
9. Apply targeted optimization.
10. Compare performance before and after the change.

## Q188. Your Spring Boot application is running out of memory. What would you do?

- Inspect heap usage.
- Capture and analyze heap dumps.
- Check for memory leaks.
- Identify large collections and retained objects.
- Inspect caches.
- Review object creation patterns.
- Check thread counts and buffers.
- Review JVM garbage collection logs.

## Q189. How do you prevent duplicate orders?

- Require an idempotency key.
- Store the key with a unique database constraint.
- Check whether the operation was already processed.
- Return the existing result for duplicate requests.
- Use transactional persistence for the business operation.

## Q190. How do you handle payment failure after inventory reservation?

Use a Saga or equivalent workflow:
1. Create order.
2. Reserve inventory.
3. Attempt payment.
4. If payment fails, execute inventory-release compensation.
5. Mark the order as failed or cancelled.
6. Publish relevant business events.

## Q191. How do you handle a downstream service outage?

- Set connection and read timeouts.
- Use circuit breakers.
- Retry only transient failures.
- Use exponential backoff and jitter.
- Provide fallback behavior where valid.
- Consider asynchronous messaging.
- Monitor failure rates and recovery.

## Q192. How do you handle a large database result set?

- Use pagination or keyset pagination.
- Retrieve only necessary columns.
- Use streaming or batch processing where appropriate.
- Avoid holding huge entity graphs in memory.
- Process in chunks.
- Monitor transaction duration and memory.

## Q193. How do you secure a Spring Boot REST API?

- Use Spring Security.
- Authenticate users with a suitable mechanism.
- Enforce authorization at endpoint and business-operation levels.
- Validate all incoming requests.
- Protect secrets.
- Configure CORS and CSRF appropriately.
- Use HTTPS.
- Rate-limit sensitive endpoints.
- Avoid leaking internal exceptions.
- Audit sensitive actions.

## Q194. How do you handle Kafka consumer failures?

- Configure bounded retries.
- Use backoff.
- Route permanently failing messages to a dead letter topic.
- Make consumers idempotent.
- Monitor lag and failures.
- Implement safe replay procedures.

## Q195. How do you maintain consistency across microservices?

Use appropriate distributed-systems patterns:
- Saga.
- Transactional Outbox.
- Idempotent consumers.
- Event-driven communication.
- Compensation operations.
- Reconciliation jobs.

Do not assume a single local `@Transactional` annotation can make multiple services atomic.

## Q196. How do you design a high-throughput order creation API?

Consider:
- Stateless application instances.
- Input validation.
- Idempotency.
- Database indexing.
- Connection pool sizing.
- Short transaction boundaries.
- Asynchronous notifications.
- Inventory concurrency control.
- Kafka for suitable downstream events.
- Caching for read-heavy data.
- Metrics and distributed tracing.
- Load testing.

## Q197. What happens if a transaction method calls an external REST API and then fails?

The database transaction cannot automatically roll back the external API's side effect.

Better approaches:
- Keep database transactions short.
- Use an outbox or Saga.
- Use idempotency.
- Use compensating operations.
- Avoid long-running remote calls inside database transactions.

## Q198. How do you prevent race conditions when two users purchase the last product?

Possible approaches:
- Optimistic locking with `@Version`.
- Pessimistic locking.
- Atomic conditional database updates.
- Inventory reservation with expiration.

Example:

```java
@Entity
public class Inventory {

    @Id
    private Long productId;

    private int availableQuantity;

    @Version
    private Long version;
}
```

Optimistic locking detects conflicting updates and allows the application to retry or reject the operation.

---

# 31. Frequently Asked Comparison Questions

| Comparison | Key Difference |
|---|---|
| Spring vs Spring Boot | Framework vs opinionated application platform |
| BeanFactory vs ApplicationContext | Basic container vs advanced container |
| `@Component` vs `@Bean` | Component scanning vs explicit bean registration |
| `@Primary` vs `@Qualifier` | Default bean vs explicit selection |
| Constructor vs Setter injection | Mandatory vs optional dependency injection |
| `@Controller` vs `@RestController` | MVC view handling vs REST response body |
| `@RequestParam` vs `@PathVariable` | Query parameter vs URI template variable |
| PUT vs PATCH | Replacement vs partial modification |
| `@Valid` vs `@Validated` | Standard validation vs Spring validation groups |
| JPA vs Hibernate | Specification vs implementation |
| JPQL vs Native SQL | Entity-oriented query vs database SQL |
| `save()` vs `saveAndFlush()` | Save/merge vs save followed by flush |
| First-level vs Second-level cache | Persistence-context cache vs shared provider cache |
| LAZY vs EAGER | Deferred vs eager association loading |
| Cascade vs FetchType | Operation propagation vs loading strategy |
| `REQUIRED` vs `REQUIRES_NEW` | Join/create vs independent transaction |
| Authentication vs Authorization | Identity verification vs permission checking |
| OAuth2 vs OIDC | Authorization vs identity layer |
| Filter vs Interceptor | Servlet-level vs Spring MVC handler-level |
| `@Cacheable` vs `@CachePut` | Cache hit can skip execution vs always execute |
| `@Async` vs `CompletableFuture` | Spring async execution vs Java async composition |
| Kafka vs RabbitMQ | Distributed event streaming vs message broker |
| Monolith vs Microservices | Single deployable unit vs independently deployable services |
| RestClient vs WebClient | Synchronous client vs reactive non-blocking client |
| Circuit Breaker vs Retry | Stop calls to failing dependency vs repeat failed operation |
| Saga vs 2PC | Compensating distributed workflow vs coordinated commit |
| Logs vs Metrics vs Traces | Events vs measurements vs request execution paths |
| Spring MVC vs WebFlux | Servlet-based vs reactive web stack |

---

# 32. Coding and Practical Interview Questions

## Q199. Create a simple Order REST API.

```java
package com.example.orders.controller;

import com.example.orders.dto.OrderRequest;
import com.example.orders.dto.OrderResponse;
import com.example.orders.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

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
                orderService.getOrder(id));
    }
}
```

## Q200. How do you implement pagination and sorting?

```java
@GetMapping
public Page<OrderResponse> getOrders(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size,
        @RequestParam(defaultValue = "createdAt") String sortBy) {

    Pageable pageable = PageRequest.of(
            page,
            size,
            Sort.by(sortBy).descending()
    );

    return orderService.getOrders(pageable);
}
```

Production note: Validate page size and sort fields. Do not allow arbitrary untrusted sort expressions.

## Q201. How do you implement optimistic locking?

```java
@Entity
public class Product {

    @Id
    private Long id;

    private int quantity;

    @Version
    private Long version;
}
```

When concurrent updates conflict, the persistence provider detects the version mismatch and raises an optimistic locking exception.

## Q202. How do you implement a scheduled order cleanup?

```java
@Component
public class OrderCleanupScheduler {

    private final OrderRepository repository;

    public OrderCleanupScheduler(OrderRepository repository) {
        this.repository = repository;
    }

    @Scheduled(cron = "0 0 2 * * *")
    public void cleanup() {
        repository.deleteExpiredOrders();
    }
}
```

For multiple application instances, ensure the scheduled task is coordinated if it must run only once across the cluster.

## Q203. How do you implement a REST client using RestClient?

```java
@Configuration
public class ClientConfig {

    @Bean
    public RestClient paymentRestClient(
            RestClient.Builder builder) {

        return builder
                .baseUrl("http://payment-service")
                .build();
    }
}
```

```java
@Service
public class PaymentClient {

    private final RestClient restClient;

    public PaymentClient(RestClient paymentRestClient) {
        this.restClient = paymentRestClient;
    }

    public PaymentResponse getPayment(Long id) {

        return restClient.get()
                .uri("/api/payments/{id}", id)
                .retrieve()
                .body(PaymentResponse.class);
    }
}
```

Imports:

```java
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
```

Configure connection/read timeouts, error handling, and resilience for production use.

---

# Final Interview Preparation Checklist

## Must-Prepare Topics

| Priority | Topics |
|---|---|
| Critical | Spring IoC, DI, Bean Lifecycle, REST APIs |
| Critical | Spring Boot Auto Configuration and Internals |
| Critical | Spring Data JPA, Hibernate, Entity Relationships |
| Critical | Transactions, Propagation, Isolation |
| Critical | Spring Security, JWT, OAuth2 |
| Critical | Microservices and REST communication |
| High | Redis, Caching, Kafka |
| High | SQL Optimization, N+1, Indexing |
| High | JUnit, Mockito, Integration Testing |
| High | Exception Handling, Validation |
| High | AOP, Proxies, Spring MVC Internals |
| High | Resilience4j and Distributed Systems |
| Medium | Actuator, Micrometer, Observability |
| Medium | Docker, Kubernetes, Deployment |
| Medium | WebFlux, Reactor, Native Images |
| Medium | Custom Starters and Advanced Spring Boot |

## Interview Readiness Goals

By the end of this preparation, I should be able to:

- Explain Spring Boot internals rather than just annotations.
- Write complete REST APIs independently.
- Design entity relationships and optimize database queries.
- Explain transaction behavior and troubleshoot failures.
- Implement secure authentication and authorization.
- Build event-driven applications using Kafka.
- Implement caching using Redis.
- Design resilient microservices.
- Troubleshoot production performance issues.
- Explain architecture decisions with real project examples.
- Write unit and integration tests.
- Handle scenario-based questions expected from a 5+ years experienced Java developer.

**Preparation strategy:** Understand the fundamentals, practice the code, explain the internal working, and prepare real project examples for every major topic. Do not rely only on memorizing interview answers.