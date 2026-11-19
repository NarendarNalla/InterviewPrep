# Spring Boot — Complete Developer & Interview Preparation Guide

# https://www.baeldung.com/spring-tutorial
# https://medium.com/@midejoseph24/building-and-deploying-a-java-crud-rest-api-with-spring-boot-hibernate-mysql-docker-docker-5b7d1847345b

**Target:** 5+ Years Java Backend / Full Stack Developer  
**Stack:** Java 17+, Spring Boot 3.x, Spring Framework 6, Maven, Spring MVC, Spring Data JPA, Hibernate, Spring Security, SQL, Redis, Kafka  
**Objective:** Product-based company interviews + real-world enterprise application development

---

# TABLE OF CONTENTS

## LEVEL 1 — Spring & Spring Boot Fundamentals

1. [Introduction to Spring Framework](#1-introduction-to-spring-framework)
2. [Spring Boot Architecture and Internals](#2-spring-boot-architecture-and-internals)
3. [Spring IoC, Dependency Injection and Beans](#3-spring-ioc-dependency-injection-and-beans)
4. [Bean Lifecycle and Scopes](#4-bean-lifecycle-and-scopes)
5. [Spring Configuration and Properties](#5-spring-configuration-and-properties)
6. [Auto-Configuration and Starters](#6-auto-configuration-and-starters)
7. [Profiles and Environment Management](#7-profiles-and-environment-management)

## LEVEL 2 — REST API Development

8. [Building REST APIs with Spring MVC](#8-building-rest-apis-with-spring-mvc)
9. [Request Parameters, DTOs and Data Binding](#9-request-parameters-dtos-and-data-binding)
10. [Bean Validation](#10-bean-validation)
11. [Global Exception Handling](#11-global-exception-handling)
12. [REST API Design and Best Practices](#12-rest-api-design-and-best-practices)
13. [Filters, Interceptors and AOP](#13-filters-interceptors-and-aop)

## LEVEL 3 — Database and Persistence

14. [Spring Data JPA and Hibernate](#14-spring-data-jpa-and-hibernate)
15. [Entity Relationships and Mapping](#15-entity-relationships-and-mapping)
16. [JPQL, Native Queries and Specifications](#16-jpql-native-queries-and-specifications)
17. [Transactions and Transaction Management](#17-transactions-and-transaction-management)
18. [Pagination, Sorting and Auditing](#18-pagination-sorting-and-auditing)
19. [Database Performance Optimization](#19-database-performance-optimization)
20. [Database Migrations with Flyway and Liquibase](#20-database-migrations-with-flyway-and-liquibase)

## LEVEL 4 — Security

21. [Spring Security Fundamentals](#21-spring-security-fundamentals)
22. [Authentication and Authorization](#22-authentication-and-authorization)
23. [JWT Authentication](#23-jwt-authentication)
24. [OAuth 2.0 and OpenID Connect](#24-oauth-20-and-openid-connect)
25. [CORS, CSRF, Password Encoding and Method Security](#25-cors-csrf-password-encoding-and-method-security)

## LEVEL 5 — Advanced Spring Boot

26. [Spring AOP](#26-spring-aop)
27. [Caching with Spring Cache and Redis](#27-caching-with-spring-cache-and-redis)
28. [Asynchronous Processing](#28-asynchronous-processing)
29. [Scheduling and Background Jobs](#29-scheduling-and-background-jobs)
30. [Spring Events and Event-Driven Design](#30-spring-events-and-event-driven-design)
31. [Messaging with Apache Kafka](#31-messaging-with-apache-kafka)
32. [Resilience and Fault Tolerance](#32-resilience-and-fault-tolerance)
33. [Spring WebClient and External API Integration](#33-spring-webclient-and-external-api-integration)

## LEVEL 6 — Testing and Production Readiness

34. [Testing Spring Boot Applications](#34-testing-spring-boot-applications)
35. [Logging and Monitoring](#35-logging-and-monitoring)
36. [Spring Boot Actuator and Observability](#36-spring-boot-actuator-and-observability)
37. [API Documentation with OpenAPI](#37-api-documentation-with-openapi)
38. [Build, Packaging and Deployment](#38-build-packaging-and-deployment)
39. [Spring Boot Performance Optimization](#39-spring-boot-performance-optimization)
40. [Production-Ready Application Architecture](#40-production-ready-application-architecture)

## LEVEL 7 — Architecture and Interview Preparation

41. [Microservices with Spring Boot](#41-microservices-with-spring-boot)
42. [Design Patterns in Spring Boot](#42-design-patterns-in-spring-boot)
43. [Common Spring Boot Annotations](#43-common-spring-boot-annotations)
44. [Frequently Asked Interview Questions](#44-frequently-asked-interview-questions)
45. [Scenario-Based Interview Questions](#45-scenario-based-interview-questions)
46. [Hands-on Coding Exercises](#46-hands-on-coding-exercises)
47. [Final Revision Checklist](#47-final-revision-checklist)

---

# LEVEL 1 — Spring & Spring Boot Fundamentals

# 1. Introduction to Spring Framework

## 1.1 What is Spring Framework?

Spring is a Java application development framework that simplifies enterprise application development through:

- Dependency Injection (DI)
- Inversion of Control (IoC)
- Aspect-Oriented Programming (AOP)
- Transaction Management
- Data Access Integration
- Web Application Development
- Security Integration

Without Spring, developers often create objects and manage their dependencies manually.

### Without Spring

```java
public class OrderService {

    private final PaymentService paymentService =
            new PaymentService();

    public void placeOrder() {
        paymentService.processPayment();
    }
}
```

Problems:

- Tight coupling.
- Difficult to replace implementations.
- Difficult to unit test.
- Object creation is controlled by application code.

### With Spring

```java
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    public void processPayment() {
        System.out.println("Payment processed");
    }
}
```

```java
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private final PaymentService paymentService;

    public OrderService(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    public void placeOrder() {
        paymentService.processPayment();
    }
}
```

Spring creates and manages the objects and injects their dependencies.

## 1.2 What is Spring Boot?

Spring Boot is built on top of the Spring Framework. It simplifies application development by providing:

- Auto-configuration.
- Starter dependencies.
- Embedded servers.
- Production-ready Actuator support.
- Externalized configuration.
- Simplified application startup.

### Spring Framework vs Spring Boot

| Spring Framework | Spring Boot |
|---|---|
| Core Java application framework | Simplifies Spring application setup |
| More manual configuration in many setups | Auto-configuration |
| Server configuration often external | Embedded server by default |
| Dependencies selected individually | Starter dependencies |
| Production monitoring configured separately | Actuator integration |
| Flexible configuration | Convention over configuration |

## 1.3 Spring ecosystem

| Module | Purpose |
|---|---|
| Spring Core | IoC and Dependency Injection |
| Spring Context | ApplicationContext and beans |
| Spring AOP | Cross-cutting concerns |
| Spring MVC | REST APIs and web applications |
| Spring Data JPA | Database access |
| Spring Security | Authentication and authorization |
| Spring Cache | Caching abstraction |
| Spring Batch | Large-scale batch processing |
| Spring Cloud | Microservices infrastructure |
| Spring Integration | Enterprise integration |
| Spring WebFlux | Reactive application development |

## 1.4 Spring vs Spring Boot vs Spring MVC vs Spring Data

- Spring: Core framework.
- Spring Boot: Simplifies Spring configuration and application startup.
- Spring MVC: Web framework for HTTP request handling.
- Spring Data JPA: Simplifies database repository operations.
- Hibernate: JPA implementation and ORM provider.

**Interview questions**

1. What is Spring Framework?
2. What problems does Spring solve?
3. What is the difference between Spring and Spring Boot?
4. Is Spring Boot a replacement for Spring?
5. What is Spring MVC?
6. What is the difference between JPA and Hibernate?

---

# 2. Spring Boot Architecture and Internals

## 2.1 Typical Spring Boot architecture

```text
Client
  |
  v
HTTP Request
  |
  v
Embedded Server (Tomcat)
  |
  v
Servlet Filters
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
Hibernate / JPA
  |
  v
Database
```

### Responsibilities

| Layer | Responsibility |
|---|---|
| Controller | Handles HTTP requests and responses |
| Service | Business logic |
| Repository | Database operations |
| Entity | Database representation |
| DTO | API request and response representation |
| Configuration | Application configuration |
| Exception Handler | Centralized error handling |

## 2.2 Spring Boot application entry point

```java
package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
}
```

## 2.3 What does @SpringBootApplication do?

It combines three annotations:

```java
@SpringBootConfiguration
@EnableAutoConfiguration
@ComponentScan
```

### @SpringBootConfiguration

Indicates that the class provides Spring configuration. It is a specialized form of `@Configuration`.

### @EnableAutoConfiguration

Enables Spring Boot's automatic configuration based on the classpath, properties and application context.

### @ComponentScan

Scans the package and its subpackages for Spring components.

## 2.4 Application startup flow

1. JVM invokes `main()`.
2. `SpringApplication.run()` is executed.
3. SpringApplication prepares the environment.
4. ApplicationContext is created.
5. Bean definitions are registered.
6. Configuration classes are processed.
7. Auto-configurations are evaluated.
8. Beans are instantiated and dependencies injected.
9. Embedded server starts for web applications.
10. Application becomes ready to accept requests.

## 2.5 ApplicationContext vs BeanFactory

| BeanFactory | ApplicationContext |
|---|---|
| Basic IoC container | Advanced IoC container |
| Supports bean creation and DI | Adds enterprise application features |
| Basic lifecycle management | Events, internationalization and resource loading |
| Typically lazy retrieval | Many singleton beans initialized eagerly by default |

`ApplicationContext` is commonly used in Spring Boot applications.

## 2.6 ApplicationContext example

```java
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;

public class ContextExample {

    public static void main(String[] args) {

        ApplicationContext context =
                SpringApplication.run(DemoApplication.class, args);

        String[] beanNames = context.getBeanDefinitionNames();

        for (String beanName : beanNames) {
            System.out.println(beanName);
        }
    }
}
```

**Interview questions**

1. What happens internally when Spring Boot starts?
2. What is ApplicationContext?
3. What is the difference between BeanFactory and ApplicationContext?
4. What is DispatcherServlet?
5. How does component scanning work?
6. What is the role of the embedded Tomcat server?

---

# 3. Spring IoC, Dependency Injection and Beans

## 3.1 What is Inversion of Control?

IoC means the responsibility for creating and managing objects is transferred from application code to a container.

Instead of manually creating dependencies, Spring manages them.

## 3.2 What is Dependency Injection?

Dependency Injection is a technique in which an object receives its dependencies from an external source.

Three types:

1. Constructor Injection.
2. Setter Injection.
3. Field Injection.

## 3.3 Constructor Injection — Recommended

```java
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    public void sendEmail(String message) {
        System.out.println("Email: " + message);
    }
}
```

```java
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final EmailService emailService;

    public UserService(EmailService emailService) {
        this.emailService = emailService;
    }

    public void registerUser() {
        emailService.sendEmail("Welcome!");
    }
}
```

Advantages:

- Dependencies are mandatory.
- Supports immutability with `final`.
- Easy to unit test.
- Avoids partially initialized objects.
- Supports constructor-based object creation.

A single constructor does not require `@Autowired` in modern Spring.

## 3.4 Setter Injection

```java
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    private EmailService emailService;

    @Autowired
    public void setEmailService(EmailService emailService) {
        this.emailService = emailService;
    }
}
```

Use when a dependency is optional or can be changed after object construction.

## 3.5 Field Injection

```java
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private EmailService emailService;
}
```

Field injection is supported but generally discouraged in production code because it hides dependencies and makes isolated unit testing harder.

## 3.6 What is a Spring Bean?

A bean is an object managed by the Spring IoC container.

### Registering beans using @Component

```java
import org.springframework.stereotype.Component;

@Component
public class PaymentGateway {

    public void pay() {
        System.out.println("Payment successful");
    }
}
```

### Registering beans using @Bean

```java
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {

    @Bean
    public PaymentGateway paymentGateway() {
        return new PaymentGateway();
    }
}
```

Use `@Bean` when you need to create or configure objects from third-party libraries or objects that cannot be annotated with Spring annotations.

## 3.7 @Component vs @Service vs @Repository vs @Controller

| Annotation | Usage |
|---|---|
| @Component | Generic Spring-managed component |
| @Service | Business logic layer |
| @Repository | Persistence layer |
| @Controller | MVC controller |
| @RestController | REST API controller |
| @Configuration | Configuration class |
| @Bean | Explicit bean registration method |

`@Service`, `@Repository` and `@Controller` are specialized component annotations.

`@Repository` also participates in persistence exception translation.

## 3.8 Multiple implementations and @Primary

```java
public interface PaymentService {
    void pay();
}
```

```java
import org.springframework.stereotype.Service;

@Service("upiPayment")
public class UpiPaymentService implements PaymentService {

    @Override
    public void pay() {
        System.out.println("UPI payment");
    }
}
```

```java
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Primary
@Service("cardPayment")
public class CardPaymentService implements PaymentService {

    @Override
    public void pay() {
        System.out.println("Card payment");
    }
}
```

```java
import org.springframework.stereotype.Service;

@Service
public class CheckoutService {

    private final PaymentService paymentService;

    public CheckoutService(PaymentService paymentService) {
        this.paymentService = paymentService;
    }
}
```

Spring injects the primary implementation.

## 3.9 @Qualifier

Use `@Qualifier` when you need a specific implementation.

```java
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class CheckoutService {

    private final PaymentService paymentService;

    public CheckoutService(
            @Qualifier("upiPayment") PaymentService paymentService) {
        this.paymentService = paymentService;
    }
}
```

## 3.10 Injecting all implementations

```java
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;

@Service
public class PaymentManager {

    private final List<PaymentService> paymentServices;
    private final Map<String, PaymentService> paymentServiceMap;

    public PaymentManager(
            List<PaymentService> paymentServices,
            Map<String, PaymentService> paymentServiceMap) {

        this.paymentServices = paymentServices;
        this.paymentServiceMap = paymentServiceMap;
    }
}
```

Useful for plugin-based architectures and strategy selection.

**Interview questions**

1. What is IoC?
2. What is Dependency Injection?
3. Which DI method is recommended and why?
4. What is a Spring Bean?
5. Difference between `@Component` and `@Bean`?
6. Difference between `@Service` and `@Repository`?
7. How do you handle multiple implementations?
8. What is `@Primary`?
9. What is `@Qualifier`?
10. How do you inject all implementations of an interface?

---

# 4. Bean Lifecycle and Scopes

## 4.1 Spring Bean lifecycle

```text
Bean Definition
      |
      v
Instantiation
      |
      v
Dependency Injection
      |
      v
Aware Callbacks
      |
      v
BeanPostProcessor Before Initialization
      |
      v
@PostConstruct
      |
      v
InitializingBean.afterPropertiesSet()
      |
      v
Custom init method
      |
      v
BeanPostProcessor After Initialization
      |
      v
Bean Ready for Use
      |
      v
Application Context Shutdown
      |
      v
@PreDestroy
      |
      v
DisposableBean.destroy()
```

## 4.2 Lifecycle example

```java
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Component;

@Component
public class DatabaseConnectionManager {

    @PostConstruct
    public void initialize() {
        System.out.println("Initializing resources");
    }

    public void connect() {
        System.out.println("Connected");
    }

    @PreDestroy
    public void cleanup() {
        System.out.println("Releasing resources");
    }
}
```

## 4.3 Bean scopes

| Scope | Description |
|---|---|
| singleton | One bean instance per Spring container |
| prototype | New instance each time requested from the container |
| request | One instance per HTTP request |
| session | One instance per HTTP session |
| application | One instance per ServletContext |
| websocket | One instance per WebSocket session |

Singleton is the default scope.

## 4.4 Singleton example

```java
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("singleton")
public class ApplicationService {
}
```

## 4.5 Prototype example

```java
import org.springframework.context.annotation.Scope;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.stereotype.Component;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class ReportGenerator {
}
```

Important: injecting a prototype bean into a singleton bean normally resolves the prototype dependency when the singleton is created. It does not automatically produce a new prototype instance on every method call. Use `ObjectProvider`, a scoped proxy or another provider mechanism when repeated instances are required.

**Interview questions**

1. Explain the Spring Bean lifecycle.
2. What is the default bean scope?
3. Singleton vs prototype?
4. Does Spring singleton mean one instance per JVM?
5. What happens when a prototype bean is injected into a singleton?
6. What is the use of `@PostConstruct` and `@PreDestroy`?

---

# 5. Spring Configuration and Properties

## 5.1 application.properties

```properties
server.port=8081

spring.application.name=order-service

spring.datasource.url=jdbc:postgresql://localhost:5432/orders
spring.datasource.username=postgres
spring.datasource.password=${DB_PASSWORD}

spring.jpa.hibernate.ddl-auto=validate
spring.jpa.show-sql=false

logging.level.root=INFO
logging.level.com.example=DEBUG
```

## 5.2 application.yml

```yaml
server:
  port: 8081

spring:
  application:
    name: order-service

  datasource:
    url: jdbc:postgresql://localhost:5432/orders
    username: postgres
    password: ${DB_PASSWORD}

  jpa:
    hibernate:
      ddl-auto: validate
    show-sql: false

logging:
  level:
    root: INFO
    com.example: DEBUG
```

## 5.3 @Value

Use for injecting individual property values.

```java
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class PaymentConfig {

    @Value("${payment.gateway.url}")
    private String gatewayUrl;

    @Value("${payment.timeout:5000}")
    private int timeout;
}
```

## 5.4 @ConfigurationProperties — Recommended for grouped settings

```java
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "payment")
public class PaymentProperties {

    private String gatewayUrl;
    private int timeout;
    private boolean enabled;

    public String getGatewayUrl() {
        return gatewayUrl;
    }

    public void setGatewayUrl(String gatewayUrl) {
        this.gatewayUrl = gatewayUrl;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
```

Enable scanning:

```java
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
}
```

Configuration:

```yaml
payment:
  gateway-url: https://payment.example.com
  timeout: 5000
  enabled: true
```

## 5.5 @PropertySource

```java
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:custom.properties")
public class CustomConfig {
}
```

Use this when you need to load an additional properties file. Spring Boot automatically loads its standard application configuration files.

**Interview questions**

1. `@Value` vs `@ConfigurationProperties`?
2. How do you read environment variables?
3. How do you externalize configuration?
4. How do you avoid committing credentials?
5. How does Spring Boot resolve property precedence?

---

# 6. Auto-Configuration and Starters

## 6.1 What is Auto-Configuration?

Spring Boot automatically configures application components based on:

- Dependencies available on the classpath.
- Existing beans.
- Configuration properties.
- Conditional annotations.

Example: adding `spring-boot-starter-web` brings in Spring MVC and an embedded servlet container by default.

## 6.2 Common conditional annotations

| Annotation | Purpose |
|---|---|
| @ConditionalOnClass | Configure if a class exists |
| @ConditionalOnMissingBean | Configure if a bean is missing |
| @ConditionalOnBean | Configure if a bean exists |
| @ConditionalOnProperty | Configure based on a property |
| @ConditionalOnResource | Configure if a resource exists |
| @ConditionalOnWebApplication | Configure for web applications |

## 6.3 Custom auto-configuration example

```java
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;

@AutoConfiguration
@ConditionalOnProperty(
        prefix = "feature.audit",
        name = "enabled",
        havingValue = "true"
)
public class AuditAutoConfiguration {

    @Bean
    public AuditService auditService() {
        return new AuditService();
    }
}
```

For a reusable auto-configuration library, register the configuration in:

`META-INF/spring/org.springframework.boot.autoconfigure.AutoConfiguration.imports`

with the fully qualified class name:

```text
com.example.audit.AuditAutoConfiguration
```

## 6.4 What are Spring Boot Starters?

Starters are dependency bundles that simplify dependency management.

| Starter | Usage |
|---|---|
| spring-boot-starter-web | REST APIs |
| spring-boot-starter-data-jpa | JPA and Hibernate |
| spring-boot-starter-security | Security |
| spring-boot-starter-validation | Bean validation |
| spring-boot-starter-test | Testing |
| spring-boot-starter-actuator | Monitoring |
| spring-boot-starter-cache | Caching |
| spring-boot-starter-webflux | Reactive web |
| spring-kafka | Kafka integration |

## 6.5 Debugging auto-configuration

Run with:

```bash
java -jar application.jar --debug
```

This displays the auto-configuration condition evaluation report.

**Interview questions**

1. How does auto-configuration work?
2. What is `@ConditionalOnMissingBean`?
3. What are Spring Boot starters?
4. How do you override an auto-configured bean?
5. How do you create a custom auto-configuration?

---

# 7. Profiles and Environment Management

## 7.1 What are Spring Profiles?

Profiles allow different configurations for different environments.

Typical environments:

- Local
- Development
- QA
- Staging
- Production

## 7.2 Profile-specific configuration

`application-dev.yml`

```yaml
server:
  port: 8081

spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/devdb
```

`application-prod.yml`

```yaml
server:
  port: 8080

spring:
  datasource:
    url: ${PROD_DB_URL}
```

Activate a profile:

```properties
spring.profiles.active=dev
```

Or:

```bash
java -jar application.jar --spring.profiles.active=prod
```

## 7.3 @Profile

```java
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("dev")
public class MockPaymentService implements PaymentService {

    @Override
    public void pay() {
        System.out.println("Mock payment");
    }
}
```

**Interview questions**

1. What are Spring profiles?
2. How do you activate multiple profiles?
3. How do you configure environment-specific beans?
4. How do you keep secrets outside application configuration?

---

# LEVEL 2 — REST API Development

# 8. Building REST APIs with Spring MVC

## 8.1 What is REST?

REST is an architectural style for building network-based applications.

Common HTTP methods:

| Method | Purpose | Example |
|---|---|---|
| GET | Retrieve | Get customer |
| POST | Create | Create customer |
| PUT | Replace/update | Update customer |
| PATCH | Partial update | Update customer email |
| DELETE | Delete | Delete customer |

## 8.2 @RestController

`@RestController` combines `@Controller` and `@ResponseBody`.

```java
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "Hello Spring Boot";
    }
}
```

## 8.3 CRUD API example

### Entity

```java
package com.example.customer.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
```

### Repository

```java
package com.example.customer.repository;

import com.example.customer.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository
        extends JpaRepository<Customer, Long> {
}
```

### Service

```java
package com.example.customer.service;

import com.example.customer.entity.Customer;
import com.example.customer.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    private final CustomerRepository repository;

    public CustomerService(CustomerRepository repository) {
        this.repository = repository;
    }

    public Customer create(Customer customer) {
        return repository.save(customer);
    }

    public List<Customer> findAll() {
        return repository.findAll();
    }

    public Customer findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Customer not found: " + id));
    }

    public void delete(Long id) {
        Customer customer = findById(id);
        repository.delete(customer);
    }
}
```

### Controller

```java
package com.example.customer.controller;

import com.example.customer.entity.Customer;
import com.example.customer.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerService service;

    public CustomerController(CustomerService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Customer create(@RequestBody Customer customer) {
        return service.create(customer);
    }

    @GetMapping
    public List<Customer> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Customer findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
```

This is a learning example. Production APIs should use DTOs, validation, proper exception handling, and avoid exposing persistence entities directly.

## 8.4 @Controller vs @RestController

| @Controller | @RestController |
|---|---|
| Commonly used for MVC views | Used for REST APIs |
| Returns view names by default | Returns response bodies by default |
| Can use `@ResponseBody` | Includes `@ResponseBody` semantics |

## 8.5 ResponseEntity

Use `ResponseEntity` when you need control over status, headers and body.

```java
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

    @PostMapping("/orders")
    public ResponseEntity<String> createOrder() {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .header("X-Order-Source", "API")
                .body("Order created");
    }
}
```

## 8.6 HTTP status codes

| Status | Meaning |
|---|---|
| 200 | OK |
| 201 | Created |
| 202 | Accepted |
| 204 | No Content |
| 400 | Bad Request |
| 401 | Unauthorized / unauthenticated |
| 403 | Forbidden |
| 404 | Not Found |
| 409 | Conflict |
| 422 | Unprocessable Content |
| 500 | Internal Server Error |
| 503 | Service Unavailable |

**Interview questions**

1. What is `@RestController`?
2. `@Controller` vs `@RestController`?
3. `@RequestMapping` vs `@GetMapping`?
4. What is `ResponseEntity`?
5. PUT vs PATCH?
6. What is idempotency?
7. What happens internally when a REST request reaches Spring MVC?

---

# 9. Request Parameters, DTOs and Data Binding

## 9.1 @PathVariable

Used to extract values from the URL path.

```java
@GetMapping("/customers/{id}")
public Customer getCustomer(@PathVariable Long id) {
    return service.findById(id);
}
```

Request:

```text
GET /customers/101
```

## 9.2 @RequestParam

Used to extract query parameters.

```java
@GetMapping("/search")
public String search(
        @RequestParam String name,
        @RequestParam(defaultValue = "0") int page) {

    return name + " - " + page;
}
```

Request:

```text
GET /search?name=Naren&page=1
```

## 9.3 @RequestBody

Maps JSON request data to a Java object using HTTP message converters.

```java
@PostMapping
public Customer create(@RequestBody Customer customer) {
    return service.create(customer);
}
```

Example JSON:

```json
{
  "name": "John",
  "email": "john@example.com"
}
```

## 9.4 DTO — Data Transfer Object

DTOs separate API contracts from database entities.

### Request DTO

```java
package com.example.customer.dto;

public class CustomerRequest {

    private String name;
    private String email;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
```

### Response DTO

```java
package com.example.customer.dto;

public record CustomerResponse(
        Long id,
        String name,
        String email
) {
}
```

### Service mapping

```java
public CustomerResponse create(CustomerRequest request) {

    Customer customer = new Customer();
    customer.setName(request.getName());
    customer.setEmail(request.getEmail());

    Customer saved = repository.save(customer);

    return new CustomerResponse(
            saved.getId(),
            saved.getName(),
            saved.getEmail()
    );
}
```

## 9.5 Why use DTOs?

- Avoid exposing internal entity fields.
- Protect sensitive information.
- Decouple API contracts from database design.
- Support different request and response structures.
- Make API versioning easier.

## 9.6 @ModelAttribute

Binds form data and request parameters to an object, especially in MVC applications.

```java
import org.springframework.web.bind.annotation.ModelAttribute;

@GetMapping("/customer-form")
public String submit(@ModelAttribute CustomerRequest request) {
    return request.getName();
}
```

**Interview questions**

1. `@PathVariable` vs `@RequestParam`?
2. `@RequestBody` vs `@ModelAttribute`?
3. Why should we use DTOs?
4. How does JSON convert into a Java object?
5. What is Jackson?
6. What are HTTP message converters?

---

# 10. Bean Validation

## 10.1 What is Bean Validation?

Bean Validation validates incoming data before business logic executes.

Spring Boot commonly integrates with Jakarta Bean Validation using Hibernate Validator.

Dependency:

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-validation</artifactId>
</dependency>
```

## 10.2 Common validation annotations

| Annotation | Purpose |
|---|---|
| @NotNull | Value must not be null |
| @NotBlank | String must not be null or whitespace |
| @NotEmpty | String/collection must not be empty |
| @Size | Length or collection size |
| @Min | Minimum numeric value |
| @Max | Maximum numeric value |
| @Email | Email format |
| @Pattern | Regex validation |
| @Positive | Positive number |
| @Past | Past date |
| @Future | Future date |
| @Valid | Triggers nested object validation |

## 10.3 Request validation example

```java
package com.example.customer.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CustomerRequest {

    @NotBlank(message = "Name is required")
    @Size(min = 3, max = 50)
    private String name;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email")
    private String email;

    // Getters and setters
}
```

Controller:

```java
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@PostMapping
public CustomerResponse create(
        @Valid @RequestBody CustomerRequest request) {

    return service.create(request);
}
```

## 10.4 Custom validation

Create a custom annotation and validator when built-in constraints are insufficient.

```java
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = EmployeeIdValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidEmployeeId {

    String message() default "Invalid employee ID";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
```

Validator:

```java
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class EmployeeIdValidator
        implements ConstraintValidator<ValidEmployeeId, String> {

    @Override
    public boolean isValid(
            String value,
            ConstraintValidatorContext context) {

        return value != null && value.matches("EMP-[0-9]{5}");
    }
}
```

## 10.5 Validation groups

Use validation groups when different operations require different validation rules, such as create vs update.

**Interview questions**

1. What is `@Valid`?
2. Difference between `@NotNull`, `@NotEmpty` and `@NotBlank`?
3. How do you create custom validation?
4. How do you validate nested DTOs?
5. How do you handle validation errors globally?

---

# 11. Global Exception Handling

## 11.1 Why centralized exception handling?

Without centralized handling, each controller may return different error formats.

Use:

- `@ControllerAdvice`
- `@RestControllerAdvice`
- `@ExceptionHandler`

## 11.2 Custom exception

```java
package com.example.exception;

public class CustomerNotFoundException extends RuntimeException {

    public CustomerNotFoundException(Long id) {
        super("Customer not found with ID: " + id);
    }
}
```

## 11.3 Standard error response

```java
package com.example.exception;

import java.time.LocalDateTime;

public record ErrorResponse(
        LocalDateTime timestamp,
        int status,
        String error,
        String message,
        String path
) {
}
```

## 11.4 Global exception handler

```java
package com.example.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(
            CustomerNotFoundException ex,
            HttpServletRequest request) {

        ErrorResponse response = new ErrorResponse(
                LocalDateTime.now(),
                404,
                "NOT_FOUND",
                ex.getMessage(),
                request.getRequestURI()
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(response);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidation(
            MethodArgumentNotValidException ex,
            HttpServletRequest request) {

        String message = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error ->
                        error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.joining(", "));

        ErrorResponse response = new ErrorResponse(
                LocalDateTime.now(),
                400,
                "VALIDATION_FAILED",
                message,
                request.getRequestURI()
        );

        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneral(
            Exception ex,
            HttpServletRequest request) {

        ErrorResponse response = new ErrorResponse(
                LocalDateTime.now(),
                500,
                "INTERNAL_SERVER_ERROR",
                "An unexpected error occurred",
                request.getRequestURI()
        );

        return ResponseEntity.internalServerError().body(response);
    }
}
```

Production note: do not expose stack traces, SQL errors, internal hostnames or sensitive exception messages to API clients. Log the original exception internally with a correlation ID.

## 11.5 ProblemDetail

Spring Framework 6 supports RFC 9457-style problem details.

```java
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class ProblemHandler {

    @ExceptionHandler(CustomerNotFoundException.class)
    public ProblemDetail handle(CustomerNotFoundException ex) {

        ProblemDetail problem =
                ProblemDetail.forStatus(HttpStatus.NOT_FOUND);

        problem.setTitle("Customer not found");
        problem.setDetail(ex.getMessage());

        return problem;
    }
}
```

**Interview questions**

1. `@ControllerAdvice` vs `@RestControllerAdvice`?
2. How do you handle exceptions globally?
3. What is `@ExceptionHandler`?
4. How do you handle validation exceptions?
5. What is ProblemDetail?
6. How do you maintain a consistent API error response?

---

# 12. REST API Design and Best Practices

## 12.1 REST resource naming

Prefer nouns and resource-oriented paths.

Good:

```text
GET    /api/customers
GET    /api/customers/101
POST   /api/customers
PUT    /api/customers/101
PATCH  /api/customers/101
DELETE /api/customers/101
```

Avoid:

```text
GET /api/getCustomer
POST /api/createCustomer
```

## 12.2 API versioning

Possible approaches:

### URI versioning

```text
/api/v1/customers
/api/v2/customers
```

### Header versioning

```text
X-API-Version: 2
```

### Media type versioning

```text
Accept: application/vnd.example.v2+json
```

Choose a consistent strategy based on client compatibility and organizational standards.

## 12.3 Idempotency

An operation is idempotent if repeating it produces the same intended effect as executing it once.

| Method | Idempotent by HTTP semantics? |
|---|---|
| GET | Yes |
| PUT | Yes |
| DELETE | Yes |
| POST | Not necessarily |
| PATCH | Depends on operation |

For payment creation, an idempotency key can prevent duplicate processing after client retries.

## 12.4 API pagination

```text
GET /api/customers?page=0&size=20&sort=name,asc
```

## 12.5 API security best practices

- Use HTTPS.
- Validate request payloads.
- Avoid exposing entity internals.
- Enforce authentication and authorization.
- Rate-limit sensitive endpoints.
- Avoid logging passwords and tokens.
- Apply consistent error responses.
- Use request correlation IDs.
- Avoid exposing stack traces.

**Interview questions**

1. How do you design a REST API?
2. What is idempotency?
3. How do you version APIs?
4. What is the difference between PUT and PATCH?
5. How do you prevent duplicate payment requests?
6. How do you design backward-compatible APIs?

---

# 13. Filters, Interceptors and AOP

## 13.1 Servlet Filter

A filter operates at the servlet layer and can inspect or modify HTTP requests and responses.

Typical use cases:

- Request logging.
- Correlation ID creation.
- Security-related processing.
- CORS processing.

Example:

```java
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class RequestLoggingFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain)
            throws ServletException, IOException {

        long start = System.currentTimeMillis();

        try {
            filterChain.doFilter(request, response);
        } finally {
            long duration = System.currentTimeMillis() - start;

            System.out.println(
                    request.getMethod() + " " +
                    request.getRequestURI() + " " +
                    duration + "ms"
            );
        }
    }
}
```

## 13.2 HandlerInterceptor

An interceptor operates around Spring MVC handler execution.

```java
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class RequestInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler) {

        System.out.println("Before controller execution");
        return true;
    }

    @Override
    public void afterCompletion(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler,
            Exception ex) {

        System.out.println("After request completion");
    }
}
```

Register:

```java
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final RequestInterceptor interceptor;

    public WebConfig(RequestInterceptor interceptor) {
        this.interceptor = interceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(interceptor)
                .addPathPatterns("/api/**");
    }
}
```

## 13.3 Filter vs Interceptor vs AOP

| Filter | Interceptor | AOP |
|---|---|---|
| Servlet layer | Spring MVC layer | Spring proxy/method layer |
| Before servlet processing and around filter chain | Around controller handler execution | Around matched method execution |
| Request/response processing | Handler-related logic | Cross-cutting business/application concerns |
| Servlet API | Spring MVC API | AspectJ pointcut model with Spring AOP |

**Important:** Spring Security's servlet filter chain is separate from MVC interceptors. Security checks should generally be implemented through Spring Security, not a custom MVC interceptor.

**Interview questions**

1. Filter vs Interceptor?
2. Interceptor vs AOP?
3. Where would you implement request logging?
4. Where should authentication be handled?
5. What is `OncePerRequestFilter`?

---

# LEVEL 3 — Database and Persistence

# 14. Spring Data JPA and Hibernate

## 14.1 What is ORM?

Object Relational Mapping maps Java objects to relational database tables.

Example:

```text
Java Class       Database Table
-----------      --------------
Customer    ->   customers
Order       ->   orders
Product     ->   products
```

## 14.2 JPA vs Hibernate vs Spring Data JPA

| Technology | Role |
|---|---|
| JPA | Java persistence specification |
| Hibernate | Common JPA implementation |
| Spring Data JPA | Repository abstraction built on JPA |
| JDBC | Lower-level database connectivity API |

## 14.3 Entity

```java
import jakarta.persistence.*;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private double price;

    // Getters and setters
}
```

## 14.4 JpaRepository

```java
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository
        extends JpaRepository<Product, Long> {
}
```

Common methods:

| Method | Purpose |
|---|---|
| save() | Insert or update |
| findById() | Find by ID |
| findAll() | Retrieve all |
| deleteById() | Delete by ID |
| existsById() | Check existence |
| count() | Count records |
| flush() | Synchronize persistence context |

## 14.5 Derived query methods

```java
import java.util.List;
import java.util.Optional;

public interface ProductRepository
        extends JpaRepository<Product, Long> {

    Optional<Product> findByName(String name);

    List<Product> findByPriceGreaterThan(double price);

    List<Product> findByNameContainingIgnoreCase(String keyword);

    boolean existsByName(String name);

    long countByPriceGreaterThan(double price);
}
```

## 14.6 save() vs saveAndFlush()

- `save()` persists or merges an entity; SQL may execute later during flush or transaction commit.
- `saveAndFlush()` saves and requests an immediate persistence-context flush.
- Flush does not mean the transaction has committed.

## 14.7 Entity states

```text
Transient
   |
   | persist()
   v
Managed
   |
   | detach() / clear() / close()
   v
Detached

Managed
   |
   | remove()
   v
Removed
```

**Interview questions**

1. What is ORM?
2. JPA vs Hibernate?
3. What is JpaRepository?
4. How does Spring Data generate repository implementations?
5. What are entity states?
6. `save()` vs `saveAndFlush()`?
7. What is the persistence context?
8. What is dirty checking?

---

# 15. Entity Relationships and Mapping

## 15.1 Relationship types

| Relationship | Example |
|---|---|
| One-to-One | User and UserProfile |
| One-to-Many | Customer and Orders |
| Many-to-One | Many Orders belong to one Customer |
| Many-to-Many | Students and Courses |

## 15.2 One-to-One

```java
import jakarta.persistence.*;

@Entity
public class UserProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String address;
}
```

```java
import jakarta.persistence.*;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "profile_id")
    private UserProfile profile;
}
```

## 15.3 One-to-Many and Many-to-One

```java
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "customer",
               cascade = CascadeType.ALL,
               orphanRemoval = true)
    private List<CustomerOrder> orders = new ArrayList<>();
}
```

```java
import jakarta.persistence.*;

@Entity
public class CustomerOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double amount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;
}
```

The `CustomerOrder` side owns the foreign-key relationship because it declares `@JoinColumn`. `mappedBy` identifies the inverse side.

## 15.4 Many-to-Many

```java
import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany
    @JoinTable(
        name = "student_course",
        joinColumns = @JoinColumn(name = "student_id"),
        inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    private Set<Course> courses = new HashSet<>();
}
```

```java
import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany(mappedBy = "courses")
    private Set<Student> students = new HashSet<>();
}
```

For real-world many-to-many relationships with extra fields such as enrollment date or status, prefer an explicit join entity.

## 15.5 Fetch types

| Fetch type | Behavior |
|---|---|
| LAZY | Loads relationship when accessed |
| EAGER | Loads relationship eagerly as part of entity retrieval semantics |

Use lazy relationships where practical, and fetch the data required by a particular use case deliberately.

## 15.6 Cascade types

| Cascade | Behavior |
|---|---|
| PERSIST | Persist operation cascades |
| MERGE | Merge operation cascades |
| REMOVE | Delete operation cascades |
| REFRESH | Refresh operation cascades |
| DETACH | Detach operation cascades |
| ALL | All above |

Avoid using `CascadeType.ALL` blindly. It can accidentally delete or propagate operations to entities that should have independent lifecycles.

## 15.7 N+1 query problem

Suppose one query loads 100 orders and then a separate query is executed for each order's customer.

```text
1 query: Fetch orders
100 queries: Fetch customers
Total: 101 queries
```

Solutions:

- JOIN FETCH.
- EntityGraph.
- DTO projection.
- Batch fetching.
- Query redesign.

**Interview questions**

1. Explain JPA relationships.
2. `mappedBy` vs `@JoinColumn`?
3. LAZY vs EAGER?
4. What is cascade?
5. What is orphanRemoval?
6. What is the N+1 query problem?
7. How do you solve N+1?
8. What is the owning side of a relationship?

---

# 16. JPQL, Native Queries and Specifications

## 16.1 JPQL

JPQL queries entity objects and their fields rather than directly querying table names.

```java
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProductRepository
        extends JpaRepository<Product, Long> {

    @Query("SELECT p FROM Product p WHERE p.price > :price")
    List<Product> findExpensiveProducts(
            @Param("price") double price);
}
```

## 16.2 Native SQL

Use native queries when database-specific SQL or a particular SQL feature is required.

```java
@Query(
    value = "SELECT * FROM products WHERE price > :price",
    nativeQuery = true
)
List<Product> findProductsUsingNativeQuery(
        @Param("price") double price);
```

## 16.3 Update query

```java
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

@Modifying
@Transactional
@Query("UPDATE Product p SET p.price = :price WHERE p.id = :id")
int updatePrice(
        @Param("id") Long id,
        @Param("price") double price);
```

## 16.4 Projection

Interface-based projection:

```java
public interface ProductSummary {
    Long getId();
    String getName();
}
```

```java
List<ProductSummary> findByPriceGreaterThan(double price);
```

Projection reduces the amount of data fetched when full entities are unnecessary.

## 16.5 Specifications

Useful for dynamic filtering.

```java
import org.springframework.data.jpa.domain.Specification;

public class ProductSpecifications {

    public static Specification<Product> priceGreaterThan(double price) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.greaterThan(root.get("price"), price);
    }
}
```

Repository:

```java
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ProductRepository
        extends JpaRepository<Product, Long>,
                JpaSpecificationExecutor<Product> {
}
```

Usage:

```java
List<Product> products = repository.findAll(
        ProductSpecifications.priceGreaterThan(1000)
);
```

**Interview questions**

1. JPQL vs native query?
2. What are projections?
3. What is `@Modifying`?
4. What are JPA Specifications?
5. When should you use native SQL?
6. How do you build dynamic search APIs?

---

# 17. Transactions and Transaction Management

## 17.1 What is a transaction?

A transaction is a group of database operations treated as one logical unit.

Example: money transfer.

```text
Account A: Debit 1000
       |
       v
Account B: Credit 1000
```

Both operations must succeed or the transaction should roll back.

## 17.2 ACID properties

| Property | Meaning |
|---|---|
| Atomicity | All operations succeed or none |
| Consistency | Data remains valid |
| Isolation | Concurrent transactions are controlled |
| Durability | Committed changes survive failures |

## 17.3 @Transactional example

```java
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TransferService {

    private final AccountRepository repository;

    public TransferService(AccountRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public void transfer(Long fromId, Long toId, double amount) {

        Account from = repository.findById(fromId).orElseThrow();
        Account to = repository.findById(toId).orElseThrow();

        from.setBalance(from.getBalance() - amount);
        to.setBalance(to.getBalance() + amount);
    }
}
```

## 17.4 Transaction propagation

| Propagation | Meaning |
|---|---|
| REQUIRED | Join existing transaction or create one |
| REQUIRES_NEW | Always create a new transaction; suspend existing one |
| SUPPORTS | Join if present; otherwise execute without a transaction |
| NOT_SUPPORTED | Execute without a transaction; suspend existing one |
| MANDATORY | Existing transaction required |
| NEVER | Fail if a transaction exists |
| NESTED | Nested transaction if supported by transaction manager |

## 17.5 Isolation levels

| Isolation | Meaning |
|---|---|
| READ_UNCOMMITTED | May allow dirty reads |
| READ_COMMITTED | Prevents dirty reads |
| REPEATABLE_READ | Prevents non-repeatable reads |
| SERIALIZABLE | Strongest standard isolation; limits concurrency |

Actual behavior and supported isolation levels depend on the database.

## 17.6 Rollback rules

By default, Spring rolls back on unchecked exceptions (`RuntimeException` and `Error`), not all checked exceptions.

```java
@Transactional(rollbackFor = Exception.class)
public void processOrder() throws Exception {
    // Transactional work
}
```

## 17.7 Common @Transactional pitfalls

- Calling a transactional method from another method in the same object (self-invocation).
- Applying it to methods that cannot be intercepted as expected.
- Swallowing exceptions.
- Performing long external API calls inside a database transaction.
- Assuming it automatically covers multiple independent databases.
- Assuming it commits immediately after `save()`.

Spring's common proxy-based transaction mechanism intercepts calls that pass through the proxy. Self-invocation generally bypasses that proxy.

**Interview questions**

1. What is `@Transactional`?
2. Explain transaction propagation.
3. Explain transaction isolation.
4. What is dirty read?
5. What is non-repeatable read?
6. What is phantom read?
7. Why does `@Transactional` sometimes not work?
8. Checked vs unchecked exception rollback?
9. How do you handle distributed transactions?

---

# 18. Pagination, Sorting and Auditing

## 18.1 Pagination

```java
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public Page<Customer> getCustomers(int page, int size) {

    Pageable pageable = PageRequest.of(
            page,
            size,
            Sort.by("name").ascending()
    );

    return repository.findAll(pageable);
}
```

## 18.2 Page vs Slice vs List

| Type | Description |
|---|---|
| Page | Content + total elements + total pages |
| Slice | Content + whether another slice exists |
| List | Only the returned data |

`Page` commonly requires a count query. `Slice` can avoid the total-count requirement.

## 18.3 Sorting

```java
Sort sort = Sort.by(
        Sort.Order.desc("createdAt"),
        Sort.Order.asc("name")
);
```

## 18.4 Auditing

Enable auditing:

```java
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing
public class JpaConfig {
}
```

Entity:

```java
import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;
}
```

**Interview questions**

1. Page vs Slice?
2. How do you implement pagination?
3. How do you implement sorting?
4. What is JPA auditing?
5. Why might count queries become expensive?

---

# 19. Database Performance Optimization

## 19.1 Common database performance issues

- Missing indexes.
- N+1 queries.
- Full table scans.
- Fetching unnecessary columns.
- Large result sets.
- Poor joins.
- Connection pool exhaustion.
- Long-running transactions.
- Inefficient pagination.

## 19.2 Indexing

An index helps the database find rows faster for suitable query patterns.

```sql
CREATE INDEX idx_customer_email
ON customers(email);
```

Composite index:

```sql
CREATE INDEX idx_customer_status_created
ON customers(status, created_at);
```

Indexes improve certain reads but add storage and write overhead. Choose them based on actual query plans and access patterns.

## 19.3 Connection pooling

Spring Boot commonly uses HikariCP as its JDBC connection pool.

```yaml
spring:
  datasource:
    hikari:
      maximum-pool-size: 20
      minimum-idle: 5
      connection-timeout: 30000
```

Do not choose pool sizes arbitrarily. Consider database capacity, application instances, concurrent workload and connection usage.

## 19.4 Batch processing

Hibernate batch configuration:

```yaml
spring:
  jpa:
    properties:
      hibernate:
        jdbc:
          batch_size: 50
        order_inserts: true
        order_updates: true
```

For large imports, consider periodic flush and clear operations to avoid excessive persistence-context memory usage.

## 19.5 Performance investigation approach

1. Measure request latency.
2. Identify slow endpoints.
3. Inspect application logs and traces.
4. Analyze SQL execution time.
5. Check execution plans.
6. Review indexes.
7. Inspect connection pool metrics.
8. Review N+1 queries and ORM fetch behavior.
9. Optimize and benchmark again.

**Interview questions**

1. How do you improve a slow Spring Boot API?
2. What is connection pooling?
3. How do indexes improve query performance?
4. How do you identify slow SQL?
5. What is batch processing?
6. How do you solve connection pool exhaustion?

---

# 20. Database Migrations with Flyway and Liquibase

## 20.1 Why database migrations?

Database schema changes must be version-controlled and repeatable across environments.

## 20.2 Flyway

Dependency:

```xml
<dependency>
    <groupId>org.flywaydb</groupId>
    <artifactId>flyway-core</artifactId>
</dependency>
```

Migration file:

```text
src/main/resources/db/migration/V1__create_customer_table.sql
```

SQL:

```sql
CREATE TABLE customers (
    id BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(150) NOT NULL UNIQUE
);
```

Flyway tracks executed migrations in its schema history table.

## 20.3 Liquibase

Liquibase uses changelogs, commonly in XML, YAML, JSON or SQL.

Example YAML:

```yaml
databaseChangeLog:
  - changeSet:
      id: 1
      author: developer
      changes:
        - createTable:
            tableName: customers
            columns:
              - column:
                  name: id
                  type: BIGINT
                  constraints:
                    primaryKey: true
              - column:
                  name: name
                  type: VARCHAR(100)
```

## 20.4 Flyway vs Liquibase

| Flyway | Liquibase |
|---|---|
| SQL-first workflow is common | Structured changelog formats |
| Versioned migrations | Changesets and changelogs |
| Simple migration workflow | Rich schema change abstractions |
| Supports SQL and Java migrations | Supports multiple changelog formats |

**Interview questions**

1. Why use Flyway?
2. Flyway vs Liquibase?
3. How do you manage schema changes across environments?
4. What happens if a migration fails?
5. Why should production schema changes be version-controlled?

---

# LEVEL 4 — Security

# 21. Spring Security Fundamentals

## 21.1 What is Spring Security?

Spring Security provides authentication, authorization and protection against common web security threats.

## 21.2 Authentication vs Authorization

| Authentication | Authorization |
|---|---|
| Who are you? | What can you access? |
| Verifies identity | Verifies permissions |
| Login | Role/permission checks |

## 21.3 Security filter chain

```text
HTTP Request
    |
    v
Servlet Container
    |
    v
DelegatingFilterProxy
    |
    v
FilterChainProxy
    |
    v
Spring Security Filters
    |
    v
Authentication / Authorization
    |
    v
DispatcherServlet
    |
    v
Controller
```

## 21.4 Security configuration

Dependency:

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-security</artifactId>
</dependency>
```

Configuration:

```java
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http) throws Exception {

        return http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/public/**").permitAll()
                        .anyRequest().authenticated()
                )
                .httpBasic(Customizer.withDefaults())
                .build();
    }
}
```

This enables HTTP Basic authentication for a simple demonstration. For production APIs, choose an appropriate authentication mechanism and enforce HTTPS.

**Interview questions**

1. What is Spring Security?
2. Authentication vs authorization?
3. What is SecurityFilterChain?
4. How does Spring Security process a request?
5. What is SecurityContext?
6. What is the difference between 401 and 403?

---

# 22. Authentication and Authorization

## 22.1 UserDetails

`UserDetails` represents the authenticated user's information.

```java
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class CustomUserDetails implements UserDetails {

    private final String username;
    private final String password;
    private final Collection<? extends GrantedAuthority> authorities;

    public CustomUserDetails(
            String username,
            String password,
            Collection<? extends GrantedAuthority> authorities) {

        this.username = username;
        this.password = password;
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
```

## 22.2 UserDetailsService

```java
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {

        AppUser user = userRepository.findByUsername(username)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User not found"));

        return new CustomUserDetails(
                user.getUsername(),
                user.getPassword(),
                user.getAuthorities()
        );
    }
}
```

`AppUser` and `UserRepository` here represent application-specific persistence classes.

## 22.3 PasswordEncoder

```java
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class PasswordConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
```

Always store password hashes, not plaintext passwords.

## 22.4 Role-based authorization

```java
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @GetMapping("/dashboard")
    @PreAuthorize("hasRole('ADMIN')")
    public String dashboard() {
        return "Admin Dashboard";
    }
}
```

Enable method security:

```java
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

@Configuration
@EnableMethodSecurity
public class MethodSecurityConfig {
}
```

## 22.5 hasRole vs hasAuthority

- `hasRole("ADMIN")` normally checks for `ROLE_ADMIN`.
- `hasAuthority("CUSTOMER_READ")` checks for that exact authority.

**Interview questions**

1. What is UserDetails?
2. What is UserDetailsService?
3. What is PasswordEncoder?
4. BCrypt vs plain-text password storage?
5. Role vs authority?
6. How does method-level security work?

---

# 23. JWT Authentication

## 23.1 What is JWT?

JWT stands for JSON Web Token. It is a compact token format used to carry claims between parties.

A JWT commonly has three parts:

```text
Header.Payload.Signature
```

Example structure:

```text
xxxxx.yyyyy.zzzzz
```

## 23.2 JWT authentication flow

```text
Client
  |
  | POST /login
  v
Authentication Controller
  |
  v
AuthenticationManager
  |
  v
UserDetailsService
  |
  v
Database User Validation
  |
  v
Generate Signed JWT
  |
  v
Return Token
  |
  v
Client Stores Token Securely
  |
  | Authorization: Bearer <token>
  v
JWT Authentication Filter
  |
  v
Validate Signature / Expiry / Claims
  |
  v
Set SecurityContext
  |
  v
Controller
```

## 23.3 JWT dependencies

A JWT library is needed. One common option is JJWT:

```xml
<dependency>
    <groupId>io.jsonwebtoken</groupId>
    <artifactId>jjwt-api</artifactId>
    <version>${jjwt.version}</version>
</dependency>

<dependency>
    <groupId>io.jsonwebtoken</groupId>
    <artifactId>jjwt-impl</artifactId>
    <version>${jjwt.version}</version>
    <scope>runtime</scope>
</dependency>

<dependency>
    <groupId>io.jsonwebtoken</groupId>
    <artifactId>jjwt-jackson</artifactId>
    <version>${jjwt.version}</version>
    <scope>runtime</scope>
</dependency>
```

Use a compatible, current library version managed centrally in your build.

## 23.4 Token generation

```java
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Service
public class JwtService {

    private final SecretKey secretKey;

    public JwtService(JwtProperties properties) {
        this.secretKey = Keys.hmacShaKeyFor(
                properties.getSecret().getBytes(StandardCharsets.UTF_8)
        );
    }

    public String generateToken(String username) {

        long now = System.currentTimeMillis();

        return Jwts.builder()
                .subject(username)
                .issuedAt(new Date(now))
                .expiration(new Date(now + 15 * 60 * 1000))
                .signWith(secretKey)
                .compact();
    }

    public String extractUsername(String token) {
        return Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }
}
```

`JwtProperties` is an application configuration class holding a strong secret from a secret manager or environment variable. Never hardcode a signing secret in source control.

## 23.5 JWT filter

```java
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    public JwtAuthenticationFilter(
            JwtService jwtService,
            UserDetailsService userDetailsService) {

        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain)
            throws ServletException, IOException {

        String header = request.getHeader("Authorization");

        if (header == null || !header.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = header.substring(7);

        try {
            String username = jwtService.extractUsername(token);

            if (username != null &&
                    SecurityContextHolder.getContext().getAuthentication() == null) {

                UserDetails user =
                        userDetailsService.loadUserByUsername(username);

                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(
                                user,
                                null,
                                user.getAuthorities()
                        );

                authentication.setDetails(
                        new WebAuthenticationDetailsSource()
                                .buildDetails(request)
                );

                SecurityContextHolder.getContext()
                        .setAuthentication(authentication);
            }

        } catch (RuntimeException ex) {
            SecurityContextHolder.clearContext();
        }

        filterChain.doFilter(request, response);
    }
}
```

A production implementation should return a consistent authentication error for invalid or expired tokens and ensure that token validation includes the required issuer, audience, expiry and signature checks.

## 23.6 Stateless security configuration

```java
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class JwtSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http,
            JwtAuthenticationFilter jwtFilter) throws Exception {

        return http
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session ->
                        session.sessionCreationPolicy(
                                SessionCreationPolicy.STATELESS
                        )
                )
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/auth/**").permitAll()
                        .anyRequest().authenticated()
                )
                .addFilterBefore(
                        jwtFilter,
                        UsernamePasswordAuthenticationFilter.class
                )
                .build();
    }
}
```

Disabling CSRF is appropriate for a stateless bearer-token API when credentials are not automatically attached by the browser. Reassess this if using cookies or browser-managed authentication.

## 23.7 Access token vs refresh token

| Access Token | Refresh Token |
|---|---|
| Short-lived | Longer-lived |
| Sent to APIs | Used to obtain a new access token |
| Limits exposure window | Must be protected carefully |
| Contains authorization claims as appropriate | Usually not used as an API access credential |

## 23.8 JWT security considerations

- Use strong signing keys.
- Keep access tokens short-lived.
- Validate issuer and audience.
- Validate expiry and signature.
- Plan revocation and refresh-token rotation.
- Avoid putting secrets or sensitive personal data in claims.
- Protect tokens in storage and transport.
- Use HTTPS.

**Interview questions**

1. What is JWT?
2. Explain JWT structure.
3. How does JWT authentication work?
4. What is a stateless application?
5. Access token vs refresh token?
6. How do you revoke a JWT?
7. What happens when a token expires?
8. Where do you store JWT on the client?
9. How does a JWT filter work?

---

# 24. OAuth 2.0 and OpenID Connect

## 24.1 What is OAuth 2.0?

OAuth 2.0 is an authorization framework that allows a client application to obtain limited access to a protected resource.

## 24.2 What is OpenID Connect?

OpenID Connect (OIDC) is an identity layer built on OAuth 2.0. It provides authentication and identity information through an ID token.

| OAuth 2.0 | OpenID Connect |
|---|---|
| Authorization framework | Authentication and identity layer |
| Access token | ID token + OAuth tokens |
| Grants access to resources | Establishes user identity |
| Does not itself define a standard ID token | Defines ID token and user identity claims |

## 24.3 Important roles

| Role | Description |
|---|---|
| Resource Owner | User |
| Client | Application requesting access |
| Authorization Server | Issues tokens |
| Resource Server | Hosts protected APIs |

## 24.4 Authorization Code Flow with PKCE

```text
User
 |
 v
Client Application
 |
 | Redirect to authorization server
 v
Authorization Server
 |
 | User authenticates and consents
 v
Client receives authorization code
 |
 | Exchanges code + PKCE verifier
 v
Authorization Server
 |
 | Access Token + ID Token (OIDC)
 v
Client
 |
 | Access Token
 v
Resource Server
```

PKCE protects authorization-code exchanges, particularly for public clients such as browser and mobile applications.

## 24.5 OAuth 2.0 grant types

| Flow | Typical usage |
|---|---|
| Authorization Code + PKCE | User-facing applications |
| Client Credentials | Service-to-service access |
| Device Authorization | Devices with limited input |
| Refresh Token | Obtain new access tokens when supported |

The Resource Owner Password Credentials grant should not be used for new applications.

## 24.6 Spring Boot OAuth2 resource server

Dependencies:

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-security</artifactId>
</dependency>

<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-oauth2-resource-server</artifactId>
</dependency>
```

Configuration:

```yaml
spring:
  security:
    oauth2:
      resourceserver:
        jwt:
          issuer-uri: https://identity.example.com/issuer
```

Security configuration:

```java
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ResourceServerConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http) throws Exception {

        return http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/public/**").permitAll()
                        .anyRequest().authenticated()
                )
                .oauth2ResourceServer(oauth ->
                        oauth.jwt(jwt -> {})
                )
                .build();
    }
}
```

Spring Security can discover signing keys and validate JWTs using issuer metadata. Production systems should also configure audience validation and map token claims to authorities according to the identity provider's token format.

**Interview questions**

1. OAuth 2.0 vs OpenID Connect?
2. What is an authorization server?
3. What is a resource server?
4. What is PKCE?
5. Authorization Code vs Client Credentials?
6. Access token vs ID token?
7. How does Spring Boot validate an externally issued JWT?

---

# 25. CORS, CSRF, Password Encoding and Method Security

## 25.1 CORS

Cross-Origin Resource Sharing controls which browser origins can access a server's resources.

Example:

```java
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.List;

@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter() {

        CorsConfiguration config = new CorsConfiguration();

        config.setAllowedOrigins(List.of("http://localhost:4200"));
        config.setAllowedMethods(
                List.of("GET", "POST", "PUT", "PATCH", "DELETE")
        );
        config.setAllowedHeaders(List.of("Authorization", "Content-Type"));
        config.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source =
                new UrlBasedCorsConfigurationSource();

        source.registerCorsConfiguration("/**", config);

        return new CorsFilter(source);
    }
}
```

Do not combine wildcard origins with credentialed requests. Use explicit trusted origins.

## 25.2 CSRF

Cross-Site Request Forgery tricks a browser into making an authenticated request using automatically attached credentials.

CSRF protection is particularly important for cookie/session-based browser authentication.

Do not disable CSRF indiscriminately. For stateless bearer-token APIs, evaluate whether the browser automatically attaches credentials.

## 25.3 Password encoding

Use a password hashing algorithm such as BCrypt or a supported adaptive password encoder.

Never store passwords using plain text or reversible encryption.

## 25.4 Method security

```java
@PreAuthorize("hasAuthority('ORDER_READ')")
public OrderResponse getOrder(Long id) {
    // ...
}
```

**Interview questions**

1. What is CORS?
2. What is CSRF?
3. CORS vs CSRF?
4. Why should passwords be hashed?
5. When is disabling CSRF acceptable?
6. How do you implement method-level authorization?

---

# LEVEL 5 — Advanced Spring Boot

# 26. Spring AOP

## 26.1 What is AOP?

Aspect-Oriented Programming separates cross-cutting concerns from business logic.

Examples:

- Logging.
- Auditing.
- Metrics.
- Authorization checks.
- Performance measurement.

## 26.2 Important AOP terms

| Term | Meaning |
|---|---|
| Aspect | Cross-cutting functionality |
| Advice | Code executed at a selected join point |
| Join Point | A point during program execution |
| Pointcut | Expression selecting join points |
| Weaving | Applying aspects to target objects |
| Target | Object being advised |
| Proxy | Object through which advice is applied |

## 26.3 Advice types

- `@Before`
- `@After`
- `@AfterReturning`
- `@AfterThrowing`
- `@Around`

## 26.4 AOP logging example

Dependency:

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-aop</artifactId>
</dependency>
```

Aspect:

```java
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class PerformanceAspect {

    @Around("execution(* com.example.service..*(..))")
    public Object measureExecutionTime(
            ProceedingJoinPoint joinPoint) throws Throwable {

        long start = System.currentTimeMillis();

        try {
            return joinPoint.proceed();
        } finally {
            long duration = System.currentTimeMillis() - start;

            System.out.println(
                    joinPoint.getSignature().getName()
                    + " executed in "
                    + duration + " ms"
            );
        }
    }
}
```

## 26.5 Spring AOP proxy types

- JDK dynamic proxies: commonly used when interfaces are available.
- CGLIB proxies: subclass-based proxies.

Spring AOP uses proxies and supports method-execution join points. It does not intercept every possible Java operation.

**Interview questions**

1. What is AOP?
2. What is an aspect?
3. Advice vs pointcut?
4. What are the advice types?
5. JDK proxy vs CGLIB?
6. Why can self-invocation bypass AOP?
7. How is `@Transactional` related to AOP proxies?

---

# 27. Caching with Spring Cache and Redis

## 27.1 What is caching?

Caching stores frequently accessed data in a faster storage layer to reduce repeated database or external service calls.

## 27.2 Cache annotations

| Annotation | Purpose |
|---|---|
| @EnableCaching | Enables caching |
| @Cacheable | Reads from cache or executes method and caches result |
| @CachePut | Executes method and updates cache |
| @CacheEvict | Removes cache entries |
| @Caching | Combines multiple cache operations |

## 27.3 Enable caching

```java
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableCaching
public class CacheConfig {
}
```

## 27.4 @Cacheable

```java
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    @Cacheable(value = "products", key = "#id")
    public Product getProduct(Long id) {
        return repository.findById(id).orElseThrow();
    }
}
```

## 27.5 @CachePut

```java
@CachePut(value = "products", key = "#result.id")
public Product updateProduct(Product product) {
    return repository.save(product);
}
```

## 27.6 @CacheEvict

```java
@CacheEvict(value = "products", key = "#id")
public void deleteProduct(Long id) {
    repository.deleteById(id);
}
```

## 27.7 Redis configuration

Dependency:

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-redis</artifactId>
</dependency>
```

Configuration:

```yaml
spring:
  data:
    redis:
      host: localhost
      port: 6379
      timeout: 2s
```

Spring Boot configures Redis connectivity through its Redis starter. A production cache should also define appropriate serialization, TTL and operational settings.

## 27.8 Cache eviction strategies

- TTL-based expiration.
- LRU.
- LFU.
- Explicit eviction.
- Size-based eviction.

## 27.9 Common caching problems

| Problem | Meaning |
|---|---|
| Cache stampede | Many requests simultaneously recompute expired data |
| Cache penetration | Repeated requests for nonexistent data |
| Cache avalanche | Many cache entries expire around the same time |
| Stale data | Cached values do not reflect recent updates |
| Hot key | One cache key receives unusually high traffic |

**Interview questions**

1. What is caching?
2. `@Cacheable` vs `@CachePut`?
3. `@CacheEvict` vs `@CachePut`?
4. Why Redis?
5. What is cache invalidation?
6. What is cache stampede?
7. How do you handle stale cache data?
8. What happens if Redis becomes unavailable?

---

# 28. Asynchronous Processing

## 28.1 What is asynchronous processing?

Asynchronous processing allows a caller to initiate work without waiting for the full operation to complete.

Example:

```text
Customer places order
       |
       v
Order saved
       |
       +----> Return response
       |
       +----> Send email asynchronously
```

## 28.2 Enable async

```java
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
}
```

## 28.3 @Async example

```java
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class EmailService {

    @Async
    public CompletableFuture<String> sendEmail(String email) {

        System.out.println(
                "Executing on: " +
                Thread.currentThread().getName()
        );

        // Simulate email processing
        return CompletableFuture.completedFuture("Email sent");
    }
}
```

## 28.4 Configure an executor

```java
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@Configuration
public class AsyncConfig {

    @Bean(name = "taskExecutor")
    public Executor taskExecutor() {

        ThreadPoolTaskExecutor executor =
                new ThreadPoolTaskExecutor();

        executor.setCorePoolSize(5);
        executor.setMaxPoolSize(10);
        executor.setQueueCapacity(100);
        executor.setThreadNamePrefix("async-");

        executor.initialize();

        return executor;
    }
}
```

Specify the executor:

```java
@Async("taskExecutor")
public CompletableFuture<String> process() {
    return CompletableFuture.completedFuture("Done");
}
```

## 28.5 Important limitations

- `@Async` uses proxy-based interception by default.
- Self-invocation does not normally trigger asynchronous execution.
- Exceptions in asynchronous work need deliberate handling.
- Thread pools must be sized and monitored.
- Asynchronous execution is not automatically durable.
- Use messaging for work that must survive application restarts or support distributed consumers.

**Interview questions**

1. What is `@Async`?
2. How does Spring execute asynchronous methods?
3. What is ThreadPoolTaskExecutor?
4. Future vs CompletableFuture?
5. Why is self-invocation a problem?
6. When would you use Kafka instead of `@Async`?

---

# 29. Scheduling and Background Jobs

## 29.1 Enable scheduling

```java
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
}
```

## 29.2 @Scheduled

```java
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ReportScheduler {

    @Scheduled(fixedRate = 60000)
    public void generateReport() {
        System.out.println("Generating report");
    }
}
```

## 29.3 Scheduling options

| Attribute | Meaning |
|---|---|
| fixedRate | Runs at a fixed interval measured from start times |
| fixedDelay | Waits a fixed duration after previous completion |
| initialDelay | Delays the first execution |
| cron | Runs according to cron expression |

Example:

```java
@Scheduled(cron = "0 0 9 * * MON-FRI")
public void sendDailyReport() {
    // Every weekday at 9 AM
}
```

## 29.4 Production considerations

- Multiple application replicas can execute the same scheduled job.
- Use distributed locking or a dedicated scheduler where needed.
- Ensure jobs are idempotent.
- Handle retries and failures.
- Monitor job duration and completion.
- Consider Spring Batch for large, restartable jobs.

**Interview questions**

1. `fixedRate` vs `fixedDelay`?
2. What is cron scheduling?
3. How do you avoid duplicate scheduled jobs across instances?
4. When should you use Spring Batch?

---

# 30. Spring Events and Event-Driven Design

## 30.1 What are Spring application events?

Spring events allow application components to communicate without directly depending on one another.

## 30.2 Publish an event

```java
public record OrderCreatedEvent(Long orderId, String customerEmail) {
}
```

## 30.3 Event publisher

```java
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private final ApplicationEventPublisher publisher;

    public OrderService(ApplicationEventPublisher publisher) {
        this.publisher = publisher;
    }

    public void createOrder(Long orderId, String email) {

        // Save order first

        publisher.publishEvent(
                new OrderCreatedEvent(orderId, email)
        );
    }
}
```

## 30.4 Event listener

```java
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class OrderEventListener {

    @EventListener
    public void handleOrderCreated(OrderCreatedEvent event) {
        System.out.println(
                "Send email to: " + event.customerEmail()
        );
    }
}
```

## 30.5 Transaction-bound events

```java
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;
import org.springframework.transaction.event.TransactionPhase;

@Component
public class OrderNotificationListener {

    @TransactionalEventListener(
            phase = TransactionPhase.AFTER_COMMIT
    )
    public void afterOrderCommitted(OrderCreatedEvent event) {
        // Perform post-commit action
    }
}
```

Important: Spring application events are in-process events. They are not automatically durable across restarts and are not a replacement for Kafka or another message broker when reliable distributed delivery is required.

**Interview questions**

1. What are Spring application events?
2. `@EventListener` vs `@TransactionalEventListener`?
3. What does AFTER_COMMIT mean?
4. Spring event vs Kafka event?
5. Are Spring events durable?

---

# 31. Messaging with Apache Kafka

## 31.1 What is Kafka?

Apache Kafka is a distributed event-streaming platform used for asynchronous communication and event-driven systems.

## 31.2 Kafka architecture

| Component | Meaning |
|---|---|
| Producer | Publishes messages |
| Consumer | Reads messages |
| Topic | Logical message category |
| Partition | Ordered log within a topic |
| Broker | Kafka server |
| Consumer Group | Group of consumers sharing work |
| Offset | Position of a message within a partition |

## 31.3 Kafka workflow

```text
Order Service
     |
     v
Kafka Producer
     |
     v
Kafka Topic: orders
     |
     v
Kafka Broker
     |
     v
Consumer Group
     |
     +----> Email Service
     |
     +----> Inventory Service
     |
     +----> Analytics Service
```

## 31.4 Dependency

```xml
<dependency>
    <groupId>org.springframework.kafka</groupId>
    <artifactId>spring-kafka</artifactId>
</dependency>
```

## 31.5 Configuration

```yaml
spring:
  kafka:
    bootstrap-servers: localhost:9092

    producer:
      key-serializer: org.apache.kafka.common.serialization.StringSerializer
      value-serializer: org.springframework.kafka.support.serializer.JsonSerializer

    consumer:
      group-id: order-group
      auto-offset-reset: earliest
      key-deserializer: org.apache.kafka.common.serialization.StringDeserializer
      value-deserializer: org.springframework.kafka.support.serializer.JsonDeserializer
```

For JSON consumers, configure trusted packages and target type according to the application's security and serialization strategy. Do not blindly trust arbitrary deserialized types.

## 31.6 Producer

```java
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class OrderProducer {

    private final KafkaTemplate<String, OrderEvent> kafkaTemplate;

    public OrderProducer(
            KafkaTemplate<String, OrderEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publish(OrderEvent event) {

        kafkaTemplate.send(
                "orders",
                event.orderId().toString(),
                event
        );
    }
}
```

Example event:

```java
public record OrderEvent(
        Long orderId,
        String customerEmail,
        double amount
) {
}
```

## 31.7 Consumer

```java
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class OrderConsumer {

    @KafkaListener(
            topics = "orders",
            groupId = "order-group"
    )
    public void consume(OrderEvent event) {
        System.out.println("Received: " + event.orderId());
    }
}
```

## 31.8 Retry and dead-letter topics

Production consumers should define:

- Retry policy.
- Backoff.
- Dead-letter topic (DLT).
- Error handler.
- Offset acknowledgment strategy.
- Idempotent processing.

## 31.9 Kafka vs RabbitMQ

| Kafka | RabbitMQ |
|---|---|
| Distributed event streaming | Message broker |
| Partitioned event logs | Queues and exchanges |
| Replay through offsets and retention | Message acknowledgment and queue semantics |
| High-throughput event pipelines | Flexible routing and task queues |
| Consumer offsets | Message acknowledgment |

## 31.10 Important Kafka concepts for interviews

- Partition ordering.
- Consumer group rebalancing.
- Offset commits.
- At-most-once delivery.
- At-least-once delivery.
- Exactly-once processing semantics and their limitations.
- Idempotent producer.
- Transactional producer.
- Outbox pattern.
- Schema evolution.
- Retry and DLT.

**Interview questions**

1. How does Kafka work?
2. Topic vs partition?
3. What is a consumer group?
4. How does Kafka maintain ordering?
5. What is an offset?
6. What happens when a consumer fails?
7. At-most-once vs at-least-once?
8. How do you avoid duplicate message processing?
9. What is the Outbox pattern?
10. Kafka vs RabbitMQ?

---

# 32. Resilience and Fault Tolerance

## 32.1 Why resilience?

Distributed applications depend on other services, databases and infrastructure. Failures must be handled without cascading across the system.

## 32.2 Common resilience patterns

| Pattern | Purpose |
|---|---|
| Timeout | Prevent waiting indefinitely |
| Retry | Retry transient failures |
| Circuit Breaker | Stop calls to a failing dependency temporarily |
| Bulkhead | Isolate resources |
| Rate Limiter | Limit request rate |
| Fallback | Provide an alternative response |

## 32.3 Resilience4j dependency

```xml
<dependency>
    <groupId>io.github.resilience4j</groupId>
    <artifactId>resilience4j-spring-boot3</artifactId>
</dependency>
```

Use a compatible version managed through the Resilience4j BOM or dependency management.

## 32.4 Circuit breaker example

```java
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.stereotype.Service;

@Service
public class PaymentClient {

    @CircuitBreaker(
            name = "paymentService",
            fallbackMethod = "fallback"
    )
    public String processPayment() {

        // Call external payment API
        return "Payment successful";
    }

    public String fallback(Exception ex) {
        return "Payment service temporarily unavailable";
    }
}
```

Configuration:

```yaml
resilience4j:
  circuitbreaker:
    instances:
      paymentService:
        sliding-window-size: 10
        failure-rate-threshold: 50
        wait-duration-in-open-state: 10s
```

## 32.5 Circuit breaker states

```text
CLOSED
  |
  | Failure threshold exceeded
  v
OPEN
  |
  | Wait duration elapsed
  v
HALF_OPEN
  |
  +---- Success ----> CLOSED
  |
  +---- Failure ----> OPEN
```

## 32.6 Retry example

```java
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.stereotype.Service;

@Service
public class ExternalService {

    @Retry(name = "externalApi")
    public String callApi() {
        return "Success";
    }
}
```

Do not blindly retry non-idempotent operations such as payment creation. Use idempotency keys and carefully select retryable exceptions.

**Interview questions**

1. What is a circuit breaker?
2. Explain circuit breaker states.
3. Retry vs circuit breaker?
4. What is a bulkhead?
5. What is fallback?
6. How do you prevent cascading failures?
7. Why should retries use backoff?

---

# 33. Spring WebClient and External API Integration

## 33.1 RestClient vs RestTemplate vs WebClient

| Client | Usage |
|---|---|
| RestClient | Modern synchronous HTTP client |
| RestTemplate | Older synchronous client; maintenance mode |
| WebClient | Reactive, non-blocking HTTP client |

## 33.2 WebClient dependency

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-webflux</artifactId>
</dependency>
```

A Spring MVC application can use WebClient without becoming a WebFlux application. Be aware that this starter also brings reactive web infrastructure; choose dependencies according to the application architecture.

## 33.3 WebClient example

```java
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class InventoryClient {

    private final WebClient webClient;

    public InventoryClient(WebClient.Builder builder) {
        this.webClient = builder
                .baseUrl("http://inventory-service")
                .build();
    }

    public Mono<String> getInventory(Long productId) {

        return webClient.get()
                .uri("/api/inventory/{id}", productId)
                .retrieve()
                .bodyToMono(String.class);
    }
}
```

## 33.4 Error handling

```java
return webClient.get()
        .uri("/api/products/{id}", id)
        .retrieve()
        .onStatus(
                status -> status.is4xxClientError(),
                response -> response.createException()
        )
        .onStatus(
                status -> status.is5xxServerError(),
                response -> response.createException()
        )
        .bodyToMono(ProductResponse.class);
```

## 33.5 Timeout

Configure connection and response timeouts at the client/connector level, and apply an appropriate reactive timeout where required.

## 33.6 Blocking vs non-blocking

- Blocking: the calling thread waits for the response.
- Non-blocking: the calling thread can perform other work while I/O is pending.

Do not call `.block()` on a reactive event-loop thread. In a traditional MVC application, choose deliberately whether to keep an integration synchronous or use reactive composition.

**Interview questions**

1. WebClient vs RestTemplate?
2. What is non-blocking I/O?
3. What is Mono?
4. What is Flux?
5. How do you handle external API errors?
6. How do you configure HTTP timeouts?

---

# LEVEL 6 — Testing and Production Readiness

# 34. Testing Spring Boot Applications

## 34.1 Testing types

| Test type | Purpose |
|---|---|
| Unit test | Tests one class in isolation |
| Integration test | Tests collaboration between components |
| Web layer test | Tests controller behavior |
| Repository test | Tests persistence behavior |
| End-to-end test | Tests a complete application workflow |
| Contract test | Tests API compatibility between services |

## 34.2 JUnit 5 unit test

```java
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CalculatorTest {

    @Test
    void shouldAddNumbers() {

        int result = 10 + 20;

        assertEquals(30, result);
    }
}
```

## 34.3 Mockito

```java
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {

    @Mock
    private CustomerRepository repository;

    @InjectMocks
    private CustomerService service;

    @Test
    void shouldReturnCustomer() {

        Customer customer = new Customer();
        customer.setId(1L);
        customer.setName("John");

        when(repository.findById(1L))
                .thenReturn(Optional.of(customer));

        Customer result = service.findById(1L);

        assertEquals("John", result.getName());

        verify(repository).findById(1L);
    }
}
```

## 34.4 @SpringBootTest

Loads the application context.

```java
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ApplicationIntegrationTest {

    @Test
    void contextLoads() {
    }
}
```

## 34.5 @WebMvcTest

Tests MVC controller behavior without loading the full application.

```java
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CustomerController.class)
class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldReturnOk() throws Exception {

        mockMvc.perform(get("/api/customers"))
                .andExpect(status().isOk());
    }
}
```

In this example, provide a mocked `CustomerService` bean when the controller requires it.

## 34.6 @DataJpaTest

Tests repository and JPA behavior.

```java
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
class CustomerRepositoryTest {

    @Autowired
    private CustomerRepository repository;

    @Test
    void shouldSaveCustomer() {

        Customer customer = new Customer();
        customer.setName("John");
        customer.setEmail("john@example.com");

        Customer saved = repository.save(customer);

        assertTrue(saved.getId() != null);
    }
}
```

## 34.7 Testcontainers

Testcontainers starts real dependencies such as PostgreSQL, Redis or Kafka in containers for integration tests.

Use it when you need confidence that code works against the actual infrastructure rather than only an in-memory substitute.

## 34.8 Testing best practices

- Unit-test business rules.
- Mock external dependencies in unit tests.
- Use integration tests for important persistence behavior.
- Test security rules.
- Test error scenarios.
- Use realistic database engines where behavior matters.
- Avoid excessive mocking of internal implementation details.

**Interview questions**

1. Unit test vs integration test?
2. Mockito vs JUnit?
3. `@SpringBootTest` vs `@WebMvcTest`?
4. What is MockMvc?
5. What is Testcontainers?
6. How do you test a repository?
7. How do you test secured REST endpoints?

---

# 35. Logging and Monitoring

## 35.1 Logging levels

| Level | Usage |
|---|---|
| TRACE | Very detailed diagnostic information |
| DEBUG | Development/debug information |
| INFO | Important application events |
| WARN | Potentially problematic conditions |
| ERROR | Failures requiring attention |

## 35.2 SLF4J logging

```java
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private static final Logger log =
            LoggerFactory.getLogger(OrderService.class);

    public void createOrder(Long orderId) {

        log.info("Creating order with ID: {}", orderId);

        try {
            // Business logic
        } catch (RuntimeException ex) {
            log.error("Failed to create order {}", orderId, ex);
            throw ex;
        }
    }
}
```

## 35.3 Logging best practices

- Use parameterized logging.
- Avoid logging credentials and access tokens.
- Include correlation IDs.
- Use structured logs where possible.
- Avoid excessive DEBUG logs in production.
- Include exception stack traces in internal logs.
- Configure retention and access controls.

**Interview questions**

1. Why use SLF4J?
2. What are logging levels?
3. How do you trace a request across services?
4. What should never be logged?
5. What is structured logging?

---

# 36. Spring Boot Actuator and Observability

## 36.1 What is Actuator?

Spring Boot Actuator provides production-ready endpoints for monitoring and managing an application.

Dependency:

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-actuator</artifactId>
</dependency>
```

## 36.2 Common Actuator endpoints

| Endpoint | Purpose |
|---|---|
| /actuator/health | Application health |
| /actuator/info | Application information |
| /actuator/metrics | Metrics |
| /actuator/prometheus | Prometheus metrics, when supported/configured |
| /actuator/loggers | Logger configuration |
| /actuator/env | Environment information |
| /actuator/beans | Bean information |
| /actuator/mappings | Request mappings |

## 36.3 Configuration

```yaml
management:
  endpoints:
    web:
      exposure:
        include: health,info,metrics,prometheus

  endpoint:
    health:
      show-details: when-authorized
```

Do not expose sensitive management endpoints publicly. Secure them and restrict network access.

## 36.4 Observability pillars

| Pillar | Meaning |
|---|---|
| Logs | What happened? |
| Metrics | How much / how often? |
| Traces | Where did the request spend time? |

Common ecosystem:

- Micrometer.
- Prometheus.
- Grafana.
- OpenTelemetry.
- Distributed tracing backends.

**Interview questions**

1. What is Spring Boot Actuator?
2. What is the health endpoint?
3. What are metrics?
4. Logs vs metrics vs traces?
5. How do you monitor a production Spring Boot application?

---

# 37. API Documentation with OpenAPI

## 37.1 What is OpenAPI?

OpenAPI is a specification for describing HTTP APIs. Swagger UI can render interactive API documentation from an OpenAPI description.

## 37.2 Springdoc dependency

```xml
<dependency>
    <groupId>org.springdoc</groupId>
    <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
    <version>${springdoc.version}</version>
</dependency>
```

Use a version compatible with your Spring Boot release.

## 37.3 API annotations

```java
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
@Tag(name = "Products", description = "Product APIs")
public class ProductController {

    @GetMapping("/{id}")
    @Operation(
            summary = "Get product",
            description = "Fetch product details using product ID"
    )
    public String getProduct(@PathVariable Long id) {
        return "Product " + id;
    }
}
```

## 37.4 Typical URLs

```text
/v3/api-docs
/swagger-ui/index.html
```

**Interview questions**

1. What is OpenAPI?
2. What is Swagger UI?
3. How do you document request and response DTOs?
4. How do you document API security?

---

# 38. Build, Packaging and Deployment

## 38.1 Maven lifecycle

Important phases:

| Phase | Purpose |
|---|---|
| validate | Validate project |
| compile | Compile source code |
| test | Run tests |
| package | Create artifact |
| verify | Run verification checks |
| install | Install artifact locally |
| deploy | Publish artifact |

Commands:

```bash
mvn clean
mvn compile
mvn test
mvn package
mvn clean install
```

## 38.2 Spring Boot executable JAR

```bash
java -jar target/application.jar
```

## 38.3 Dockerfile

```dockerfile
FROM eclipse-temurin:21-jre

WORKDIR /app

COPY target/application.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
```

Use a supported Java runtime matching your build target. Production images should be patched and scanned, and should run with a non-root user where practical.

## 38.4 Docker Compose example

```yaml
services:
  postgres:
    image: postgres:16
    environment:
      POSTGRES_DB: orders
      POSTGRES_USER: postgres
      POSTGRES_PASSWORD: ${DB_PASSWORD}
    ports:
      - "5432:5432"

  redis:
    image: redis:7
    ports:
      - "6379:6379"
```

## 38.5 Deployment considerations

- Externalize configuration.
- Store secrets securely.
- Configure health checks.
- Use graceful shutdown.
- Configure connection pools.
- Set resource limits.
- Centralize logs.
- Monitor metrics.
- Automate builds and deployment.
- Use rolling or canary deployment where appropriate.

**Interview questions**

1. How do you package Spring Boot?
2. JAR vs WAR?
3. What is an embedded server?
4. How do you dockerize an application?
5. How do you configure environments in deployment?
6. What is graceful shutdown?

---

# 39. Spring Boot Performance Optimization

## 39.1 API performance

- Reduce unnecessary database calls.
- Use pagination.
- Use caching.
- Avoid unnecessary object mapping.
- Avoid blocking external calls where suitable.
- Configure connection pools.
- Use efficient JSON serialization.
- Measure before optimizing.

## 39.2 JVM performance

Understand:

- Heap and stack.
- Garbage collection.
- Object allocation.
- Thread pools.
- CPU profiling.
- Memory leaks.
- JVM flags.

## 39.3 Database performance

- Proper indexing.
- Query plan analysis.
- N+1 query prevention.
- Batch inserts and updates.
- Fetch only required fields.
- Tune transaction boundaries.

## 39.4 Concurrency performance

- Avoid unbounded thread pools.
- Avoid holding locks for long periods.
- Use timeouts.
- Handle backpressure.
- Measure thread contention.
- Separate CPU-bound and I/O-bound work.

## 39.5 Production troubleshooting checklist

| Symptom | Possible investigation |
|---|---|
| High response time | Traces, SQL, downstream APIs |
| High CPU | Profiling, hot methods, GC |
| High memory | Heap dump, retained objects |
| DB connection timeout | Hikari metrics, DB capacity |
| Thread pool exhaustion | Thread dump, blocked threads |
| High error rate | Logs, dependency health |
| Kafka lag | Consumer throughput, partition assignment |

**Interview questions**

1. How do you optimize a slow API?
2. How do you investigate memory leaks?
3. What is a thread dump?
4. What is a heap dump?
5. How do you investigate high CPU?
6. How do you tune connection pools?
7. What is backpressure?

---

# 40. Production-Ready Application Architecture

## 40.1 Recommended package structure

```text
com.example.order
|
+-- OrderApplication.java
|
+-- controller
|   +-- OrderController.java
|
+-- service
|   +-- OrderService.java
|   +-- PaymentService.java
|
+-- repository
|   +-- OrderRepository.java
|
+-- entity
|   +-- Order.java
|
+-- dto
|   +-- OrderRequest.java
|   +-- OrderResponse.java
|
+-- mapper
|   +-- OrderMapper.java
|
+-- exception
|   +-- OrderNotFoundException.java
|   +-- GlobalExceptionHandler.java
|
+-- config
|   +-- SecurityConfig.java
|   +-- RedisConfig.java
|
+-- client
|   +-- PaymentClient.java
|
+-- event
|   +-- OrderCreatedEvent.java
|
+-- messaging
|   +-- OrderProducer.java
|   +-- OrderConsumer.java
```

## 40.2 End-to-end order processing

```text
Client
  |
  v
OrderController
  |
  v
Request Validation
  |
  v
OrderService
  |
  +----> OrderRepository
  |
  +----> PaymentService
  |
  +----> Event Publisher
  |
  v
Order Response
```

## 40.3 Service layer example

```java
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final PaymentService paymentService;

    public OrderService(
            OrderRepository orderRepository,
            PaymentService paymentService) {

        this.orderRepository = orderRepository;
        this.paymentService = paymentService;
    }

    @Transactional
    public OrderResponse placeOrder(OrderRequest request) {

        Order order = new Order();
        order.setCustomerId(request.customerId());
        order.setAmount(request.amount());
        order.setStatus("PENDING");

        Order saved = orderRepository.save(order);

        // A real payment integration should not be treated
        // as part of the local database transaction.
        // Use a payment workflow / saga / outbox design.

        return new OrderResponse(
                saved.getId(),
                saved.getStatus()
        );
    }
}
```

## 40.4 Production design considerations

- Keep controllers thin.
- Keep business logic in services.
- Use DTOs at API boundaries.
- Use constructor injection.
- Define transaction boundaries deliberately.
- Avoid remote calls inside long-running DB transactions.
- Use idempotency for retryable operations.
- Use the Outbox pattern for reliable event publication.
- Add logs, metrics and traces.
- Handle failures explicitly.
- Secure every endpoint appropriately.

---

# LEVEL 7 — Architecture and Interview Preparation

# 41. Microservices with Spring Boot

## 41.1 What are microservices?

Microservices architecture structures an application as independently deployable services that communicate over defined contracts.

Example:

```text
                 API Gateway
                      |
        +-------------+-------------+
        |             |             |
        v             v             v
   User Service  Order Service  Product Service
        |             |             |
        v             v             v
    User DB        Order DB      Product DB
                      |
                      v
                    Kafka
                      |
              +-------+-------+
              |               |
              v               v
        Inventory Service  Email Service
```

## 41.2 Common microservices components

| Component | Purpose |
|---|---|
| API Gateway | Entry point for clients |
| Service Discovery | Locate service instances |
| Config Server | Centralized configuration |
| Load Balancer | Distribute traffic |
| Circuit Breaker | Fault isolation |
| Kafka | Asynchronous messaging |
| Distributed Tracing | Request tracking |
| Centralized Logging | Unified logs |
| Container Platform | Deployment and scaling |

## 41.3 Synchronous vs asynchronous communication

| Synchronous | Asynchronous |
|---|---|
| HTTP / REST | Kafka / RabbitMQ |
| Caller waits for response | Caller does not need immediate response |
| Simple request-response | Event-driven communication |
| Runtime dependency | Looser temporal coupling |

## 41.4 Service-to-service communication

Common choices:

- RestClient.
- WebClient.
- OpenFeign, where used.
- Kafka.
- gRPC.

## 41.5 Distributed transactions

Traditional `@Transactional` does not automatically span independent services and databases.

Common approaches:

- Saga pattern.
- Transactional Outbox.
- Eventual consistency.
- Compensating transactions.
- Idempotent consumers.

## 41.6 Saga pattern

A saga is a sequence of local transactions. If a step fails, compensating actions can reverse or mitigate earlier business effects.

Example:

```text
Create Order
    |
    v
Reserve Inventory
    |
    v
Process Payment
    |
    +---- Success ----> Confirm Order
    |
    +---- Failure ----> Release Inventory
```

## 41.7 Microservices vs Monolith

| Monolith | Microservices |
|---|---|
| Single deployable application | Multiple deployable services |
| Simpler initial operations | More infrastructure complexity |
| Often simpler local transactions | Distributed consistency concerns |
| Easier local debugging | Requires distributed tracing |
| Can scale the whole application | Can scale services independently |

**Interview questions**

1. What are microservices?
2. Monolith vs microservices?
3. What is API Gateway?
4. What is service discovery?
5. How do microservices communicate?
6. What is eventual consistency?
7. What is Saga?
8. What is the Outbox pattern?
9. How do you handle distributed transactions?
10. How do you prevent cascading failures?

---

# 42. Design Patterns in Spring Boot

## 42.1 Singleton Pattern

Spring beans are singleton-scoped by default.

```java
import org.springframework.stereotype.Service;

@Service
public class ConfigurationService {
}
```

This means one instance per Spring application context, not one instance across all JVMs.

## 42.2 Factory Pattern

Spring's `BeanFactory` and factory methods are examples of factory-based object creation.

Application example:

```java
public interface PaymentProcessor {
    void pay();
}
```

```java
import org.springframework.stereotype.Component;

@Component("card")
public class CardProcessor implements PaymentProcessor {

    @Override
    public void pay() {
        System.out.println("Card payment");
    }
}
```

```java
import org.springframework.stereotype.Component;

@Component("upi")
public class UpiProcessor implements PaymentProcessor {

    @Override
    public void pay() {
        System.out.println("UPI payment");
    }
}
```

```java
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class PaymentFactory {

    private final Map<String, PaymentProcessor> processors;

    public PaymentFactory(Map<String, PaymentProcessor> processors) {
        this.processors = processors;
    }

    public PaymentProcessor getProcessor(String type) {

        PaymentProcessor processor = processors.get(type);

        if (processor == null) {
            throw new IllegalArgumentException(
                    "Unsupported payment type: " + type
            );
        }

        return processor;
    }
}
```

## 42.3 Strategy Pattern

Different algorithms are encapsulated behind one interface.

Examples:

- Payment strategies.
- Discount calculation.
- Shipping calculation.
- Authentication strategies.

## 42.4 Proxy Pattern

Spring AOP, transactions and method security commonly use proxies.

## 42.5 Template Method Pattern

Spring provides template abstractions such as `JdbcTemplate` and `RestTemplate` to encapsulate common workflow steps.

## 42.6 Repository Pattern

Spring Data repositories abstract persistence operations.

## 42.7 Observer Pattern

Spring application events and event listeners implement an observer-style communication mechanism.

## 42.8 Adapter Pattern

Adapters translate one interface into another. They are useful when integrating third-party APIs with internal domain interfaces.

## 42.9 Decorator Pattern

Decorators add behavior around an existing implementation without changing its interface.

**Interview questions**

1. Which design patterns are used by Spring?
2. How does Spring use the Proxy pattern?
3. What is the Factory pattern?
4. What is the Strategy pattern?
5. How is `JdbcTemplate` related to Template Method?
6. What is the Repository pattern?

---

# 43. Common Spring Boot Annotations

## Core annotations

| Annotation | Purpose |
|---|---|
| @SpringBootApplication | Main application configuration |
| @Configuration | Configuration class |
| @Bean | Register bean |
| @Component | Register component |
| @Service | Service-layer component |
| @Repository | Persistence component |
| @Autowired | Dependency injection |
| @Qualifier | Select bean |
| @Primary | Preferred bean |
| @Scope | Bean scope |
| @Lazy | Lazy initialization |
| @Profile | Environment-specific bean |
| @Value | Inject property |
| @ConfigurationProperties | Bind configuration properties |

## Web annotations

| Annotation | Purpose |
|---|---|
| @RestController | REST controller |
| @Controller | MVC controller |
| @RequestMapping | Base request mapping |
| @GetMapping | GET mapping |
| @PostMapping | POST mapping |
| @PutMapping | PUT mapping |
| @PatchMapping | PATCH mapping |
| @DeleteMapping | DELETE mapping |
| @PathVariable | Extract URL variable |
| @RequestParam | Extract query parameter |
| @RequestBody | Bind request body |
| @ResponseBody | Write return value to response |
| @ResponseStatus | Set response status |
| @ModelAttribute | Bind request parameters/form data |
| @ExceptionHandler | Handle exception |
| @RestControllerAdvice | Global REST exception handling |

## JPA annotations

| Annotation | Purpose |
|---|---|
| @Entity | Entity class |
| @Table | Table mapping |
| @Id | Primary key |
| @GeneratedValue | ID generation |
| @Column | Column mapping |
| @OneToOne | One-to-one relationship |
| @OneToMany | One-to-many relationship |
| @ManyToOne | Many-to-one relationship |
| @ManyToMany | Many-to-many relationship |
| @JoinColumn | Foreign-key column |
| @JoinTable | Join table |
| @Transient | Non-persistent field |
| @Version | Optimistic locking |

## Transaction annotations

| Annotation | Purpose |
|---|---|
| @Transactional | Transaction boundary |
| @EnableTransactionManagement | Enable transaction management where needed |
| @Modifying | Mark modifying repository query |

## Security annotations

| Annotation | Purpose |
|---|---|
| @EnableMethodSecurity | Enable method-level security |
| @PreAuthorize | Check access before execution |
| @PostAuthorize | Check access after execution |
| @Secured | Role-based method security |
| @AuthenticationPrincipal | Access authenticated principal |

## Advanced annotations

| Annotation | Purpose |
|---|---|
| @Aspect | Define aspect |
| @Around | Around advice |
| @Before | Before advice |
| @After | After advice |
| @Async | Asynchronous method |
| @EnableAsync | Enable async |
| @Scheduled | Scheduled task |
| @EnableScheduling | Enable scheduling |
| @EventListener | Application event listener |
| @TransactionalEventListener | Transaction-bound event listener |
| @Cacheable | Cache result |
| @CachePut | Update cache |
| @CacheEvict | Evict cache |
| @EnableCaching | Enable caching |
| @KafkaListener | Kafka listener |

---

# 44. Frequently Asked Interview Questions

## Spring Fundamentals

1. What is Spring Framework?
2. What is Spring Boot?
3. Spring vs Spring Boot?
4. What is IoC?
5. What is Dependency Injection?
6. What is ApplicationContext?
7. What is BeanFactory?
8. What is a Spring Bean?
9. What is component scanning?
10. How does auto-configuration work?

## Dependency Injection and Beans

11. Constructor vs setter vs field injection?
12. `@Component` vs `@Bean`?
13. `@Service` vs `@Repository`?
14. `@Primary` vs `@Qualifier`?
15. What are bean scopes?
16. Explain bean lifecycle.
17. What is circular dependency?
18. How do you resolve circular dependencies?
19. What is lazy initialization?
20. What happens when multiple beans implement one interface?

## REST APIs

21. `@Controller` vs `@RestController`?
22. `@RequestParam` vs `@PathVariable`?
23. `@RequestBody` vs `@ModelAttribute`?
24. What is ResponseEntity?
25. What is DispatcherServlet?
26. How do you handle exceptions globally?
27. Why use DTOs?
28. How do you validate request payloads?
29. What is API versioning?
30. What is idempotency?

## JPA and Database

31. JPA vs Hibernate?
32. `CrudRepository` vs `JpaRepository`?
33. What is persistence context?
34. What is dirty checking?
35. What is the N+1 problem?
36. How do you solve N+1?
37. Lazy vs eager loading?
38. What is cascade?
39. What is orphan removal?
40. JPQL vs native query?
41. What is pagination?
42. What is optimistic locking?
43. What is pessimistic locking?
44. What is `@Transactional`?
45. Explain transaction propagation.
46. Explain isolation levels.
47. Why does `@Transactional` sometimes fail?
48. How do you optimize slow database queries?

## Security

49. Authentication vs authorization?
50. What is Spring Security?
51. What is SecurityFilterChain?
52. What is UserDetailsService?
53. What is JWT?
54. How does JWT authentication work?
55. OAuth 2.0 vs OIDC?
56. What is PKCE?
57. What is CORS?
58. What is CSRF?
59. How do you implement role-based authorization?

## Advanced Concepts

60. What is AOP?
61. What is a Spring proxy?
62. How does caching work?
63. Redis vs in-memory cache?
64. `@Cacheable` vs `@CachePut`?
65. What is `@Async`?
66. How does scheduling work?
67. Spring events vs Kafka?
68. What is a circuit breaker?
69. What is WebClient?
70. What is Actuator?
71. How do you monitor Spring Boot?
72. How do you test a Spring Boot application?

---

# 45. Scenario-Based Interview Questions

## Scenario 1: API takes 5 seconds to respond

**Question:** How do you troubleshoot?

Approach:

1. Measure controller latency.
2. Check distributed traces.
3. Inspect SQL queries.
4. Check N+1 issues.
5. Inspect external API calls.
6. Review connection pool metrics.
7. Check CPU, memory and GC.
8. Optimize the actual bottleneck.
9. Compare latency before and after.

## Scenario 2: Database receives duplicate orders

**Question:** How do you prevent duplicate creation?

Possible solutions:

- Idempotency key.
- Unique database constraint.
- Transactional validation.
- Safe retry behavior.
- Duplicate event detection.

Do not rely only on checking the database before insert; concurrent requests can pass that check simultaneously.

## Scenario 3: Redis is unavailable

**Question:** What happens?

Consider:

- Whether cache access is critical.
- Cache failure handling.
- Database fallback.
- Increased database load.
- Timeouts and circuit breakers.
- Recovery behavior.
- Cache stampede prevention.

## Scenario 4: Kafka consumer processes a message twice

**Question:** How do you prevent duplicate business effects?

Use:

- Idempotent consumers.
- Unique event IDs.
- Processed-message tracking.
- Database constraints.
- Transactional offset handling where appropriate.

## Scenario 5: One downstream service is down

**Question:** How do you protect your application?

Use:

- Connection and read timeouts.
- Circuit breaker.
- Bounded retries.
- Fallback where meaningful.
- Bulkheads.
- Monitoring and alerts.

## Scenario 6: `@Transactional` does not roll back

Check:

- Exception type.
- Rollback rules.
- Whether exception was swallowed.
- Whether method was called through Spring proxy.
- Self-invocation.
- Transaction manager configuration.
- Whether the work actually participates in that transaction.

## Scenario 7: Application starts locally but fails in production

Check:

- Environment-specific configuration.
- Database connectivity.
- Secret injection.
- Java runtime version.
- Port configuration.
- Missing dependencies.
- Container resources.
- Database migrations.
- Network access and certificates.

## Scenario 8: API works for admin but not customer

Check:

- Authentication principal.
- Roles and authorities.
- `hasRole` vs `hasAuthority`.
- Security filter chain.
- Method-level security.
- Token claims and authority mapping.

## Scenario 9: Database connection pool exhausted

Check:

- Long-running transactions.
- Unclosed connections in custom JDBC code.
- Slow SQL.
- Pool size vs database capacity.
- Thread concurrency.
- Connection leak detection.
- Downstream delays while holding DB connections.

## Scenario 10: How do you design an order service?

Suggested design:

- Order Controller.
- Order Service.
- Order Repository.
- Payment integration.
- Inventory integration.
- Kafka events.
- Idempotency.
- Outbox pattern.
- Retry and compensation.
- Observability.

---

# 46. Hands-on Coding Exercises

## Beginner

- Build a Spring Boot REST API.
- Create CRUD APIs for Employee.
- Implement constructor injection.
- Create a custom exception.
- Add global exception handling.
- Add request validation.
- Implement DTO mapping.
- Configure multiple profiles.

## Intermediate

- Implement pagination and sorting.
- Implement JPA relationships.
- Write JPQL and native queries.
- Solve an N+1 problem.
- Implement a transaction.
- Add optimistic locking.
- Add Redis caching.
- Implement async email notification.
- Create scheduled reports.
- Write JUnit and Mockito tests.

## Advanced

- Implement JWT authentication.
- Implement OAuth2 resource server.
- Implement role-based authorization.
- Integrate Kafka producer and consumer.
- Implement retry and circuit breaker.
- Implement WebClient integration.
- Implement Flyway migrations.
- Add Actuator and Prometheus metrics.
- Build a Docker image.
- Implement transactional Outbox.
- Design a Saga workflow.
- Build a production-ready order management system.

## End-to-end project: Order Management System

### Functional requirements

1. Customer registration.
2. Customer login.
3. Product management.
4. Inventory management.
5. Order creation.
6. Payment integration.
7. Order status tracking.
8. Email notification.
9. Admin authorization.
10. Order history.

### Technical requirements

| Requirement | Technology |
|---|---|
| Backend | Spring Boot |
| Language | Java 17+ |
| API | Spring MVC |
| Database | PostgreSQL |
| ORM | Spring Data JPA |
| Security | Spring Security + JWT or OIDC |
| Cache | Redis |
| Messaging | Kafka |
| Validation | Jakarta Validation |
| Testing | JUnit + Mockito + Testcontainers |
| Migrations | Flyway |
| Monitoring | Actuator + Micrometer |
| Deployment | Docker |
| Documentation | OpenAPI |

### Suggested implementation order

1. Create the Spring Boot project.
2. Configure PostgreSQL.
3. Create entities and repositories.
4. Implement service and controller layers.
5. Add DTOs and validation.
6. Add global exception handling.
7. Implement authentication and authorization.
8. Add pagination and filtering.
9. Integrate Redis.
10. Integrate Kafka.
11. Add retries and circuit breakers.
12. Write unit and integration tests.
13. Add Actuator and metrics.
14. Add Docker and deployment configuration.

---

# 47. Final Revision Checklist

## Spring Fundamentals

- [ ] Spring Framework vs Spring Boot
- [ ] IoC and Dependency Injection
- [ ] ApplicationContext and BeanFactory
- [ ] Bean lifecycle
- [ ] Bean scopes
- [ ] Auto-configuration
- [ ] Profiles and configuration properties

## REST API Development

- [ ] REST architecture
- [ ] HTTP methods and status codes
- [ ] Controller and ResponseEntity
- [ ] Request mapping
- [ ] DTOs and data binding
- [ ] Bean validation
- [ ] Exception handling
- [ ] Filters and interceptors

## Database

- [ ] JPA and Hibernate
- [ ] Entity relationships
- [ ] Fetch types and cascade
- [ ] JPQL and native queries
- [ ] N+1 problem
- [ ] Transactions and propagation
- [ ] Isolation levels
- [ ] Pagination and sorting
- [ ] Query optimization
- [ ] Flyway

## Security

- [ ] Authentication and authorization
- [ ] Security filter chain
- [ ] UserDetailsService
- [ ] Password encoding
- [ ] JWT
- [ ] OAuth 2.0
- [ ] OpenID Connect
- [ ] CORS and CSRF
- [ ] Method security

## Advanced

- [ ] AOP
- [ ] Redis caching
- [ ] Async processing
- [ ] Scheduling
- [ ] Spring events
- [ ] Kafka
- [ ] Circuit breaker
- [ ] WebClient
- [ ] Testing
- [ ] Actuator
- [ ] Observability
- [ ] Docker
- [ ] Microservices
- [ ] Design patterns

---

# Recommended Learning Priority for 5+ Years Experience

| Priority | Topics | Interview importance |
|---|---|---|
| Must master | DI, IoC, Beans, REST, JPA, Transactions | Very High |
| Must master | Spring Security, JWT, OAuth2, Exception Handling | Very High |
| Must master | Performance, SQL, N+1, Caching | Very High |
| Must master | Testing, AOP, Spring internals | High |
| Strong knowledge | Kafka, Redis, Resilience4j | High |
| Strong knowledge | Microservices, Saga, Outbox | High |
| Working knowledge | Actuator, Observability, Flyway | Medium–High |
| Working knowledge | WebFlux, Batch, Custom Auto-Configuration | Medium |

## Suggested study order

1. Master Modules 1–13: Spring fundamentals and REST.
2. Master Modules 14–20: JPA, SQL and transaction management.
3. Master Modules 21–25: Security.
4. Study Modules 26–33: Advanced Spring Boot and distributed systems.
5. Practise Modules 34–40: Testing and production readiness.
6. Complete Modules 41–47: Architecture, scenarios and interview revision.

**Important interview strategy:** For each topic, be able to explain what it is, why it is needed, how it works internally, when to use it, alternatives, trade-offs, and a real production scenario. Do not stop at memorizing annotations.