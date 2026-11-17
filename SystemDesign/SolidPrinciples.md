# SOLID Principles in Java — Complete Interview Preparation Guide

**Target Experience:** 5+ Years  
**Technology:** Java 17+, Spring Boot  
**Focus:** Object-Oriented Design, Clean Code, Maintainability, Extensibility and Interview Preparation

---

# Table of Contents

1. [Introduction to SOLID Principles](#1-introduction-to-solid-principles)
2. [Single Responsibility Principle (SRP)](#2-single-responsibility-principle-srp)
3. [Open/Closed Principle (OCP)](#3-openclosed-principle-ocp)
4. [Liskov Substitution Principle (LSP)](#4-liskov-substitution-principle-lsp)
5. [Interface Segregation Principle (ISP)](#5-interface-segregation-principle-isp)
6. [Dependency Inversion Principle (DIP)](#6-dependency-inversion-principle-dip)
7. [SOLID Principles with Spring Boot](#7-solid-principles-with-spring-boot)
8. [SOLID Principles Working Together](#8-solid-principles-working-together)
9. [SOLID vs Design Patterns](#9-solid-vs-design-patterns)
10. [Common SOLID Violations](#10-common-solid-violations)
11. [SOLID Interview Questions](#11-solid-interview-questions)
12. [Practical Coding Exercises](#12-practical-coding-exercises)
13. [Final Revision Cheat Sheet](#13-final-revision-cheat-sheet)

---

# 1. Introduction to SOLID Principles

## 1.1 What is SOLID? ⭐⭐⭐⭐⭐

SOLID is a collection of five object-oriented design principles that help developers write maintainable, flexible, reusable and extensible software.

These principles were popularized by Robert C. Martin (Uncle Bob).

| Letter | Principle | Main Objective |
|---|---|---|
| S | Single Responsibility Principle | One responsibility per class |
| O | Open/Closed Principle | Open for extension, closed for modification |
| L | Liskov Substitution Principle | Subtypes should be substitutable for base types |
| I | Interface Segregation Principle | Prefer smaller, focused interfaces |
| D | Dependency Inversion Principle | Depend on abstractions, not concrete implementations |

## 1.2 Why SOLID Principles Are Important?

Without SOLID:

- Classes become tightly coupled.
- Changes in one module affect multiple modules.
- Code becomes difficult to test.
- New features require modifying existing code repeatedly.
- Inheritance creates unexpected behavior.
- Interfaces become unnecessarily large.
- Maintenance becomes expensive.

With SOLID:

- Better separation of concerns.
- Lower coupling.
- Higher cohesion.
- Easier unit testing.
- Easier feature extension.
- Improved maintainability.
- Better application architecture.

## 1.3 Understand These Two Concepts First

**Cohesion:** How closely related the responsibilities of a class or module are.

High cohesion means a class focuses on a well-defined purpose.

**Coupling:** How strongly one class depends on another class.

Low coupling means classes depend less on implementation details of other classes.

| Concept | Preferred Design |
|---|---|
| Cohesion | High |
| Coupling | Low |

---

# 2. Single Responsibility Principle (SRP)

<badge color="success">S — Principle 1</badge> ⭐⭐⭐⭐⭐

## 2.1 Definition

A class should have only one reason to change.

In other words, a class should have one well-defined responsibility.

**Important:** Single Responsibility does not mean a class should contain only one method. It means its methods should belong to one cohesive responsibility.

## 2.2 Real-World Example

Consider an Employee Management application.

An Employee class is responsible for employee information.

However, suppose the same class also:

- Saves employees to the database.
- Generates salary reports.
- Sends email notifications.
- Exports employee details to PDF.

Now the class has multiple responsibilities.

A change in email logic, database logic or reporting logic can require modifying the same class.

This violates SRP.

## 2.3 Bad Implementation — SRP Violation

```java
public class Employee {

    private Long id;
    private String name;
    private double salary;

    public void saveEmployee() {
        System.out.println("Saving employee to database");
    }

    public void generateSalaryReport() {
        System.out.println("Generating salary report");
    }

    public void sendEmail() {
        System.out.println("Sending employee email");
    }
}
```

### Why is this wrong?

The Employee class is handling multiple unrelated responsibilities:

1. Employee data management.
2. Database persistence.
3. Report generation.
4. Email notification.

If the database implementation changes, Employee must change.

If the email provider changes, Employee must change.

If the report format changes, Employee must change.

This creates unnecessary coupling.

## 2.4 Good Implementation — Following SRP

Separate responsibilities into different classes.

**Employee.java**

```java
public class Employee {

    private Long id;
    private String name;
    private double salary;

    public Employee(Long id, String name, double salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getSalary() {
        return salary;
    }
}
```

**EmployeeRepository.java**

```java
public class EmployeeRepository {

    public void save(Employee employee) {
        System.out.println("Saving employee to database");
    }
}
```

**SalaryReportService.java**

```java
public class SalaryReportService {

    public void generateReport(Employee employee) {
        System.out.println(
            "Generating salary report for " + employee.getName()
        );
    }
}
```

**EmailService.java**

```java
public class EmailService {

    public void sendEmail(Employee employee) {
        System.out.println(
            "Sending email to " + employee.getName()
        );
    }
}
```

### How does this follow SRP?

| Class | Responsibility |
|---|---|
| Employee | Employee information |
| EmployeeRepository | Database persistence |
| SalaryReportService | Salary report generation |
| EmailService | Email notifications |

Each class has a focused responsibility.

## 2.5 SRP in Spring Boot

Typical Spring Boot architecture:

```text
EmployeeController
        |
        v
EmployeeService
        |
        v
EmployeeRepository
        |
        v
Database
```

Responsibilities:

- Controller: HTTP request and response handling.
- Service: Business logic.
- Repository: Database operations.
- DTO: API request and response representation.
- Entity: Database persistence model.

Example:

```java
@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping
    public ResponseEntity<EmployeeResponse> create(
            @RequestBody EmployeeRequest request) {

        return ResponseEntity.ok(
            employeeService.createEmployee(request)
        );
    }
}
```

The controller does not implement database queries or complex business logic.

## 2.6 When Should You Apply SRP?

Apply SRP when:

- A class handles unrelated operations.
- A class changes for multiple independent business reasons.
- Unit testing requires too many unrelated dependencies.
- A class becomes excessively large.
- Changes in one responsibility frequently break another.

## 2.7 Interview Questions — SRP

**Q1. What is the Single Responsibility Principle?**

A class should have one reason to change. It should focus on one cohesive responsibility.

**Q2. Does SRP mean one method per class?**

No. A class can contain multiple methods as long as they belong to the same responsibility.

**Q3. How do you implement SRP in Spring Boot?**

Separate controller, service, repository, DTO and entity responsibilities.

**Q4. What is the advantage of SRP?**

Improved maintainability, testability, readability and reduced coupling.

---

# 3. Open/Closed Principle (OCP)

<badge color="success">O — Principle 2</badge> ⭐⭐⭐⭐⭐

## 3.1 Definition

Software entities such as classes, modules and functions should be:

- Open for extension.
- Closed for modification.

This means we should be able to add new functionality without repeatedly changing existing, stable code.

## 3.2 Real-World Example

Consider a payment processing system.

Initially, the application supports:

- Credit Card.
- Debit Card.

Later, the business requests:

- UPI.
- PayPal.
- Net Banking.

If every new payment method requires modifying the same payment service, the design becomes difficult to maintain.

OCP encourages extension through abstractions and polymorphism.

## 3.3 Bad Implementation — OCP Violation

```java
public class PaymentService {

    public void processPayment(String type, double amount) {

        if (type.equals("CREDIT_CARD")) {
            System.out.println("Processing credit card payment");
        }
        else if (type.equals("UPI")) {
            System.out.println("Processing UPI payment");
        }
        else if (type.equals("PAYPAL")) {
            System.out.println("Processing PayPal payment");
        }
    }
}
```

### Why is this wrong?

Every time a new payment method is introduced:

- Existing PaymentService must be modified.
- More conditional statements are added.
- The class becomes increasingly complex.
- Existing behavior may accidentally break.

## 3.4 Good Implementation — Following OCP

Use an interface and separate implementations.

**PaymentProcessor.java**

```java
public interface PaymentProcessor {

    void processPayment(double amount);
}
```

**CreditCardPayment.java**

```java
public class CreditCardPayment implements PaymentProcessor {

    @Override
    public void processPayment(double amount) {
        System.out.println(
            "Processing credit card payment: " + amount
        );
    }
}
```

**UPIPayment.java**

```java
public class UPIPayment implements PaymentProcessor {

    @Override
    public void processPayment(double amount) {
        System.out.println(
            "Processing UPI payment: " + amount
        );
    }
}
```

**PayPalPayment.java**

```java
public class PayPalPayment implements PaymentProcessor {

    @Override
    public void processPayment(double amount) {
        System.out.println(
            "Processing PayPal payment: " + amount
        );
    }
}
```

**PaymentService.java**

```java
public class PaymentService {

    private final PaymentProcessor paymentProcessor;

    public PaymentService(PaymentProcessor paymentProcessor) {
        this.paymentProcessor = paymentProcessor;
    }

    public void pay(double amount) {
        paymentProcessor.processPayment(amount);
    }
}
```

### Usage

```java
public class Main {

    public static void main(String[] args) {

        PaymentProcessor processor =
            new UPIPayment();

        PaymentService service =
            new PaymentService(processor);

        service.pay(5000);
    }
}
```

### Output

```text
Processing UPI payment: 5000.0
```

### How does this follow OCP?

To support a new payment method, create another implementation.

```java
public class NetBankingPayment implements PaymentProcessor {

    @Override
    public void processPayment(double amount) {
        System.out.println(
            "Processing Net Banking payment: " + amount
        );
    }
}
```

PaymentService does not need modification.

## 3.5 OCP with Strategy Design Pattern

The previous example uses the Strategy pattern.

Strategy pattern:

- Defines a common interface.
- Provides multiple interchangeable implementations.
- Allows behavior to change without changing the client.

```text
           PaymentProcessor
                  |
        +---------+---------+
        |         |         |
      UPI      Credit     PayPal
               Card
```

## 3.6 OCP in Spring Boot

Spring can inject multiple implementations.

```java
@Component("upi")
public class UpiPaymentProcessor
        implements PaymentProcessor {

    @Override
    public void processPayment(double amount) {
        System.out.println("UPI payment: " + amount);
    }
}
```

```java
@Component("card")
public class CardPaymentProcessor
        implements PaymentProcessor {

    @Override
    public void processPayment(double amount) {
        System.out.println("Card payment: " + amount);
    }
}
```

```java
@Service
public class PaymentService {

    private final Map<String, PaymentProcessor> processors;

    public PaymentService(
            Map<String, PaymentProcessor> processors) {
        this.processors = processors;
    }

    public void pay(String type, double amount) {

        PaymentProcessor processor = processors.get(type);

        if (processor == null) {
            throw new IllegalArgumentException(
                "Unsupported payment type: " + type
            );
        }

        processor.processPayment(amount);
    }
}
```

Spring automatically injects the available implementations into the map using their bean names as keys.

Example:

```java
paymentService.pay("upi", 5000);
```

## 3.7 When Should You Apply OCP?

- When new business variants are expected.
- When conditional logic grows with each new feature.
- When multiple implementations share a common contract.
- When existing stable functionality should remain unchanged.

**Important:** OCP does not mean never modifying existing code. Bug fixes and legitimate requirement changes may require modification. The objective is to isolate likely extensions behind stable abstractions.

## 3.8 Interview Questions — OCP

**Q1. What is OCP?**

Software should allow new functionality through extension without requiring repeated modifications to existing stable code.

**Q2. How does polymorphism help OCP?**

Different implementations can provide different behavior through a common abstraction.

**Q3. Which design pattern commonly supports OCP?**

Strategy, Factory and Template Method patterns can help, depending on the problem.

**Q4. Is adding an interface always necessary for OCP?**

No. Use abstractions where they provide a real extension point. Avoid unnecessary interfaces.

---

# 4. Liskov Substitution Principle (LSP)

<badge color="success">L — Principle 3</badge> ⭐⭐⭐⭐⭐

## 4.1 Definition

Objects of a superclass should be replaceable with objects of its subclasses without breaking the correctness of the application.

In simple words:

A child class should behave in a way that respects the contract expected from its parent class.

## 4.2 Real-World Example

Consider a Bird class.

Some birds can fly:

- Sparrow.
- Eagle.
- Pigeon.

But some birds cannot fly:

- Penguin.
- Ostrich.

If the parent Bird class provides a fly() method, a Penguin subclass may be forced to implement behavior it cannot support.

This violates LSP.

## 4.3 Bad Implementation — LSP Violation

```java
class Bird {

    public void fly() {
        System.out.println("Bird is flying");
    }
}
```

```java
class Sparrow extends Bird {

    @Override
    public void fly() {
        System.out.println("Sparrow is flying");
    }
}
```

```java
class Penguin extends Bird {

    @Override
    public void fly() {
        throw new UnsupportedOperationException(
            "Penguins cannot fly"
        );
    }
}
```

Client code:

```java
public class BirdService {

    public void makeBirdFly(Bird bird) {
        bird.fly();
    }
}
```

Usage:

```java
makeBirdFly(new Sparrow()); // Works

makeBirdFly(new Penguin()); // Throws exception
```

### Why is this wrong?

The client expects every Bird to support flying.

Penguin cannot satisfy that expectation.

The subclass breaks the behavioral contract of the parent.

## 4.4 Good Implementation — Following LSP

Separate the general Bird abstraction from flying behavior.

**Bird.java**

```java
public abstract class Bird {

    public abstract void eat();
}
```

**FlyingBird.java**

```java
public interface FlyingBird {

    void fly();
}
```

**Sparrow.java**

```java
public class Sparrow extends Bird
        implements FlyingBird {

    @Override
    public void eat() {
        System.out.println("Sparrow is eating");
    }

    @Override
    public void fly() {
        System.out.println("Sparrow is flying");
    }
}
```

**Penguin.java**

```java
public class Penguin extends Bird {

    @Override
    public void eat() {
        System.out.println("Penguin is eating");
    }
}
```

**BirdService.java**

```java
public class BirdService {

    public void feedBird(Bird bird) {
        bird.eat();
    }

    public void makeBirdFly(FlyingBird bird) {
        bird.fly();
    }
}
```

Now only flying birds are passed to makeBirdFly().

## 4.5 Important LSP Rules

A valid subtype should respect the behavioral contract of its parent.

Important considerations:

1. Do not strengthen preconditions.
2. Do not weaken postconditions.
3. Preserve required invariants.
4. Do not introduce unexpected exceptions for valid parent operations.
5. Preserve expected behavior.

### Preconditions

Conditions that must be true before a method executes.

A subtype should not require stricter input conditions than the parent contract.

### Postconditions

Conditions guaranteed after a method executes.

A subtype should not weaken guarantees made by the parent.

## 4.6 Another Example — Rectangle and Square

This is a classic LSP violation when Rectangle exposes independent setters for width and height.

**Bad Implementation**

```java
class Rectangle {

    protected int width;
    protected int height;

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getArea() {
        return width * height;
    }
}
```

```java
class Square extends Rectangle {

    @Override
    public void setWidth(int width) {
        this.width = width;
        this.height = width;
    }

    @Override
    public void setHeight(int height) {
        this.width = height;
        this.height = height;
    }
}
```

Client code:

```java
Rectangle rectangle = new Square();

rectangle.setWidth(5);
rectangle.setHeight(10);

System.out.println(rectangle.getArea());
```

The client may expect area = 50, but gets 100.

The Square subclass changes the behavior expected from Rectangle.

**Better Design**

Model common behavior without forcing an invalid inheritance relationship.

```java
interface Shape {

    int calculateArea();
}
```

```java
class Rectangle implements Shape {

    private final int width;
    private final int height;

    public Rectangle(int width, int height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public int calculateArea() {
        return width * height;
    }
}
```

```java
class Square implements Shape {

    private final int side;

    public Square(int side) {
        this.side = side;
    }

    @Override
    public int calculateArea() {
        return side * side;
    }
}
```

Both classes satisfy the Shape contract.

## 4.7 When Should You Apply LSP?

- When designing inheritance hierarchies.
- When subclasses override parent methods.
- When a subclass throws unsupported-operation exceptions.
- When subclasses change expected behavior.
- When client code behaves differently depending on the concrete subtype.

## 4.8 Interview Questions — LSP

**Q1. What is LSP?**

A subtype should be usable wherever its parent type is expected without breaking correctness.

**Q2. Give a real-world example of LSP violation.**

A Penguin subclass of a Bird class that requires every bird to fly.

**Q3. How do you prevent LSP violations?**

Design meaningful abstractions, avoid inappropriate inheritance and preserve parent behavioral contracts.

**Q4. Is method overriding a violation of LSP?**

No. Overriding is valid when the new behavior respects the original contract.

---

# 5. Interface Segregation Principle (ISP)

<badge color="success">I — Principle 4</badge> ⭐⭐⭐⭐⭐

## 5.1 Definition

Clients should not be forced to depend on methods they do not use.

In simple words:

Prefer multiple small, focused interfaces instead of one large interface containing unrelated operations.

## 5.2 Real-World Example

Consider a machine interface.

It provides:

- Print.
- Scan.
- Fax.

A basic printer only supports printing.

If it implements the complete interface, it must provide meaningless implementations for scanning and faxing.

This violates ISP.

## 5.3 Bad Implementation — ISP Violation

```java
interface Machine {

    void print();

    void scan();

    void fax();
}
```

```java
class BasicPrinter implements Machine {

    @Override
    public void print() {
        System.out.println("Printing document");
    }

    @Override
    public void scan() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void fax() {
        throw new UnsupportedOperationException();
    }
}
```

### Why is this wrong?

BasicPrinter does not need scan() or fax().

However, the interface forces it to implement those methods.

This creates unnecessary dependencies and meaningless implementations.

## 5.4 Good Implementation — Following ISP

Split the interface into smaller interfaces.

**Printer.java**

```java
public interface Printer {

    void print();
}
```

**Scanner.java**

```java
public interface Scanner {

    void scan();
}
```

**FaxMachine.java**

```java
public interface FaxMachine {

    void fax();
}
```

**BasicPrinter.java**

```java
public class BasicPrinter implements Printer {

    @Override
    public void print() {
        System.out.println("Printing document");
    }
}
```

**MultiFunctionPrinter.java**

```java
public class MultiFunctionPrinter
        implements Printer, Scanner, FaxMachine {

    @Override
    public void print() {
        System.out.println("Printing document");
    }

    @Override
    public void scan() {
        System.out.println("Scanning document");
    }

    @Override
    public void fax() {
        System.out.println("Sending fax");
    }
}
```

### How does this follow ISP?

Each implementation depends only on the capabilities it actually supports.

## 5.5 ISP in Real Java Applications

Consider an employee service.

Bad design:

```java
public interface EmployeeService {

    void createEmployee();

    void updateEmployee();

    void deleteEmployee();

    void exportEmployeeReport();

    void sendEmployeeNotification();

    void generateSalarySlip();
}
```

Not every client or implementation needs all these operations.

A more focused design could be:

```java
public interface EmployeeReader {

    Employee getEmployee(Long id);
}
```

```java
public interface EmployeeWriter {

    Employee createEmployee(Employee employee);

    Employee updateEmployee(Employee employee);
}
```

```java
public interface EmployeeReportGenerator {

    byte[] generateReport();
}
```

The interfaces express separate capabilities.

## 5.6 ISP vs SRP

| SRP | ISP |
|---|---|
| Focuses on class responsibilities | Focuses on interface responsibilities |
| A class should have one reason to change | Clients should not depend on unused methods |
| Avoids classes with unrelated behavior | Avoids large, unfocused interfaces |

## 5.7 When Should You Apply ISP?

- Interfaces contain many unrelated methods.
- Implementations frequently throw UnsupportedOperationException.
- Classes implement methods they do not need.
- Changes to an interface force unrelated classes to change.
- Different consumers need different subsets of functionality.

## 5.8 Interview Questions — ISP

**Q1. What is ISP?**

Clients should not be forced to depend on methods they do not use.

**Q2. How do you implement ISP in Java?**

Create small, focused interfaces based on specific capabilities.

**Q3. What is the difference between SRP and ISP?**

SRP focuses on class responsibilities, while ISP focuses on interface design and client dependencies.

**Q4. Can a class implement multiple interfaces?**

Yes. Java supports implementing multiple interfaces, making ISP practical.

---

# 6. Dependency Inversion Principle (DIP)

<badge color="success">D — Principle 5</badge> ⭐⭐⭐⭐⭐

## 6.1 Definition

High-level modules should not depend directly on low-level modules.

Both should depend on abstractions.

Abstractions should not depend on details. Details should depend on abstractions.

In simple words:

A business service should depend on an interface rather than directly creating a concrete implementation.

## 6.2 Understand High-Level and Low-Level Modules

**High-level module:**

Contains important business logic.

Example: OrderService.

**Low-level module:**

Handles implementation details.

Example: EmailService, SmsService, DatabaseRepository.

## 6.3 Bad Implementation — DIP Violation

Consider an order service that directly creates an email notification service.

```java
public class EmailService {

    public void sendEmail(String message) {
        System.out.println("Email sent: " + message);
    }
}
```

```java
public class OrderService {

    private final EmailService emailService =
        new EmailService();

    public void placeOrder() {

        System.out.println("Order placed");

        emailService.sendEmail(
            "Order confirmation"
        );
    }
}
```

### Why is this wrong?

OrderService directly depends on EmailService.

If the business wants SMS notifications instead:

- OrderService must be modified.
- The class becomes tightly coupled.
- Unit testing becomes harder.
- Notification implementation cannot be easily replaced.

## 6.4 Good Implementation — Following DIP

Introduce an abstraction.

**NotificationService.java**

```java
public interface NotificationService {

    void sendNotification(String message);
}
```

**EmailNotificationService.java**

```java
public class EmailNotificationService
        implements NotificationService {

    @Override
    public void sendNotification(String message) {
        System.out.println("Email sent: " + message);
    }
}
```

**SmsNotificationService.java**

```java
public class SmsNotificationService
        implements NotificationService {

    @Override
    public void sendNotification(String message) {
        System.out.println("SMS sent: " + message);
    }
}
```

**OrderService.java**

```java
public class OrderService {

    private final NotificationService notificationService;

    public OrderService(
            NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    public void placeOrder() {

        System.out.println("Order placed");

        notificationService.sendNotification(
            "Order confirmation"
        );
    }
}
```

### Usage

```java
public class Main {

    public static void main(String[] args) {

        NotificationService notification =
            new EmailNotificationService();

        OrderService orderService =
            new OrderService(notification);

        orderService.placeOrder();
    }
}
```

To use SMS, inject SmsNotificationService instead.

OrderService remains unchanged.

## 6.5 Dependency Injection vs Dependency Inversion ⭐⭐⭐⭐⭐

These two concepts are related but not identical.

| Dependency Inversion | Dependency Injection |
|---|---|
| Design principle | Technique/pattern |
| Encourages dependency on abstractions | Supplies dependencies from outside |
| Reduces coupling | Helps implement loose coupling |
| Part of SOLID | Commonly used to implement DIP |

## 6.6 Types of Dependency Injection in Spring

### 1. Constructor Injection — Recommended

```java
@Service
public class OrderService {

    private final PaymentService paymentService;

    public OrderService(PaymentService paymentService) {
        this.paymentService = paymentService;
    }
}
```

Advantages:

- Supports immutable dependencies.
- Makes required dependencies explicit.
- Easier unit testing.
- Prevents construction without required dependencies.

### 2. Setter Injection

```java
@Service
public class OrderService {

    private PaymentService paymentService;

    @Autowired
    public void setPaymentService(
            PaymentService paymentService) {
        this.paymentService = paymentService;
    }
}
```

Useful for optional or replaceable dependencies.

### 3. Field Injection

```java
@Service
public class OrderService {

    @Autowired
    private PaymentService paymentService;
}
```

It is generally discouraged for application code because dependencies are hidden and direct unit testing becomes harder.

## 6.7 DIP in Spring Boot — Complete Example

**PaymentGateway.java**

```java
public interface PaymentGateway {

    void processPayment(double amount);
}
```

**RazorpayGateway.java**

```java
@Component
public class RazorpayGateway implements PaymentGateway {

    @Override
    public void processPayment(double amount) {
        System.out.println(
            "Processing payment through Razorpay: " + amount
        );
    }
}
```

**StripeGateway.java**

```java
@Component
public class StripeGateway implements PaymentGateway {

    @Override
    public void processPayment(double amount) {
        System.out.println(
            "Processing payment through Stripe: " + amount
        );
    }
}
```

**PaymentService.java**

```java
@Service
public class PaymentService {

    private final PaymentGateway paymentGateway;

    public PaymentService(PaymentGateway paymentGateway) {
        this.paymentGateway = paymentGateway;
    }

    public void makePayment(double amount) {
        paymentGateway.processPayment(amount);
    }
}
```

**Important Spring Boot note:**

Both gateway classes are registered as beans. Injecting PaymentGateway by type is ambiguous unless you choose one implementation.

Use @Primary:

```java
@Primary
@Component
public class RazorpayGateway implements PaymentGateway {
    // Implementation
}
```

Or use @Qualifier:

```java
@Service
public class PaymentService {

    private final PaymentGateway paymentGateway;

    public PaymentService(
        @Qualifier("stripeGateway")
        PaymentGateway paymentGateway) {
        this.paymentGateway = paymentGateway;
    }
}
```

## 6.8 When Should You Apply DIP?

- Business logic depends directly on infrastructure classes.
- Unit testing requires real external dependencies.
- Implementations need to be interchangeable.
- Database or third-party integrations may change.
- You want to isolate business rules from infrastructure.

## 6.9 Interview Questions — DIP

**Q1. What is DIP?**

High-level modules and low-level modules should depend on abstractions rather than directly depending on each other.

**Q2. What is the difference between DIP and DI?**

DIP is a design principle; DI is a technique for providing dependencies from outside.

**Q3. How does Spring implement dependency injection?**

Spring creates and manages beans in its IoC container and injects required dependencies.

**Q4. Why is constructor injection preferred?**

It makes dependencies explicit, supports immutability and simplifies testing.

---

# 7. SOLID Principles with Spring Boot

## 7.1 How SOLID Is Applied in Spring Boot

| Principle | Spring Boot Application |
|---|---|
| SRP | Separate Controller, Service and Repository |
| OCP | Use interfaces and multiple implementations |
| LSP | Ensure implementations honor interface contracts |
| ISP | Create focused service and repository interfaces |
| DIP | Use constructor injection and abstractions |

## 7.2 Example Application Structure

```text
employee-management/
│
├── controller/
│   └── EmployeeController.java
│
├── service/
│   ├── EmployeeService.java
│   ├── EmployeeServiceImpl.java
│   └── NotificationService.java
│
├── repository/
│   └── EmployeeRepository.java
│
├── entity/
│   └── Employee.java
│
├── dto/
│   ├── EmployeeRequest.java
│   └── EmployeeResponse.java
│
├── notification/
│   ├── EmailNotificationService.java
│   └── SmsNotificationService.java
│
└── exception/
    └── GlobalExceptionHandler.java
```

## 7.3 Complete Flow

```text
Angular Frontend
       |
       v
EmployeeController
       |
       v
EmployeeService
       |
       +------------------+
       |                  |
       v                  v
EmployeeRepository  NotificationService
       |                  |
       v                  v
   Database        Email / SMS Provider
```

This structure supports:

- SRP through separation of responsibilities.
- OCP through interchangeable implementations.
- LSP through valid implementation contracts.
- ISP through focused interfaces.
- DIP through abstraction-based dependencies.

---

# 8. SOLID Principles Working Together

## 8.1 Real-World Example — Order Processing System

Requirement:

Develop an order processing application that supports:

- Multiple payment methods.
- Email and SMS notifications.
- Order persistence.
- Order confirmation.

A possible design:

```text
                  OrderController
                         |
                         v
                    OrderService
                         |
             +-----------+-----------+
             |           |           |
             v           v           v
        OrderRepository PaymentGateway NotificationService
             |           |           |
             v      +----+----+   +---+----+
          Database  |         |   |        |
                    v         v   v        v
                   UPI      Card Email     SMS
```

## 8.2 Mapping SOLID Principles

| Principle | Implementation |
|---|---|
| SRP | OrderService coordinates orders; repositories handle persistence |
| OCP | Add a new payment gateway without changing OrderService |
| LSP | Every payment gateway honors the PaymentGateway contract |
| ISP | Separate payment and notification interfaces |
| DIP | OrderService depends on abstractions |

## 8.3 Example — Core Abstractions

```java
public interface PaymentGateway {

    void processPayment(double amount);
}
```

```java
public interface NotificationService {

    void notifyCustomer(String message);
}
```

```java
public interface OrderRepository {

    void save(Order order);
}
```

## 8.4 Example — Order Service

```java
@Service
public class OrderService {

    private final PaymentGateway paymentGateway;
    private final NotificationService notificationService;
    private final OrderRepository orderRepository;

    public OrderService(
            PaymentGateway paymentGateway,
            NotificationService notificationService,
            OrderRepository orderRepository) {

        this.paymentGateway = paymentGateway;
        this.notificationService = notificationService;
        this.orderRepository = orderRepository;
    }

    public void placeOrder(Order order) {

        orderRepository.save(order);

        paymentGateway.processPayment(
            order.getAmount()
        );

        notificationService.notifyCustomer(
            "Order placed successfully"
        );
    }
}
```

This example illustrates dependency structure. A production payment workflow also needs transaction boundaries, idempotency, payment status handling, retries, and compensation for partial failures.

---

# 9. SOLID vs Design Patterns

## 9.1 SOLID vs Design Patterns

| SOLID | Design Patterns |
|---|---|
| Object-oriented design principles | Reusable solutions to recurring design problems |
| Guides class and dependency design | Provides implementation structures |
| Helps reduce coupling | Helps organize collaboration between objects |
| Example: DIP | Example: Strategy |

## 9.2 Design Patterns That Support SOLID

| Design Pattern | Related Principle | How It Helps |
|---|---|---|
| Strategy | OCP, DIP | Add interchangeable behaviors |
| Factory | OCP, DIP | Encapsulate object creation |
| Adapter | OCP, DIP | Integrate incompatible interfaces |
| Decorator | OCP | Extend behavior without modifying original class |
| Observer | OCP, DIP | Notify subscribers through abstractions |
| Template Method | OCP, LSP | Customize defined algorithm steps |
| Repository | SRP, DIP | Separate data access from business logic |

A design pattern does not automatically guarantee SOLID compliance. The implementation still matters.

---

# 10. Common SOLID Violations

| Violation | Example | Correct Approach |
|---|---|---|
| SRP | One class handles DB, email and reports | Separate responsibilities |
| OCP | Large if-else for every payment type | Use strategy implementations |
| LSP | Subclass throws unsupported exceptions for parent behavior | Redesign inheritance contract |
| ISP | One interface contains unrelated methods | Split into focused interfaces |
| DIP | Service directly creates concrete dependencies | Inject abstractions |

## 10.1 Warning Signs in Code

**SRP:**
- Class has too many unrelated methods.
- Multiple unrelated reasons to change.

**OCP:**
- Repeated modifications to a central switch statement for new variants.

**LSP:**
- Subclasses cannot safely replace parent types.
- Overrides break documented expectations.

**ISP:**
- Implementations contain empty or unsupported methods.

**DIP:**
- Business logic directly creates database, email or payment clients.

---

# 11. SOLID Interview Questions

## 11.1 Basic Questions

1. What are SOLID principles?
2. Who popularized SOLID?
3. Why are SOLID principles important?
4. Explain all five SOLID principles.
5. What is the difference between coupling and cohesion?
6. How does SOLID improve maintainability?

## 11.2 SRP Questions

7. What is Single Responsibility Principle?
8. Does SRP mean one method per class?
9. How do you apply SRP in Spring Boot?
10. What is the difference between SRP and separation of concerns?
11. Can a service class contain multiple methods?

## 11.3 OCP Questions

12. What is Open/Closed Principle?
13. How does polymorphism help OCP?
14. How can Strategy pattern implement OCP?
15. How do you add a new payment method without changing existing code?
16. Does OCP mean existing code should never change?

## 11.4 LSP Questions

17. What is Liskov Substitution Principle?
18. Explain the Bird and Penguin example.
19. Explain the Rectangle and Square problem.
20. What are preconditions and postconditions?
21. How can inheritance violate LSP?
22. Is method overriding a violation of LSP?

## 11.5 ISP Questions

23. What is Interface Segregation Principle?
24. Why are large interfaces problematic?
25. How do you split a large interface?
26. What is the difference between SRP and ISP?
27. Can one class implement multiple interfaces?

## 11.6 DIP Questions

28. What is Dependency Inversion Principle?
29. What is Dependency Injection?
30. DIP vs DI?
31. What is IoC?
32. Why is constructor injection preferred?
33. How does Spring implement dependency injection?
34. How do you resolve multiple bean implementations?

## 11.7 Scenario-Based Questions

35. How would you design a payment processing system using SOLID?
36. How would you add a new notification provider?
37. How would you refactor a large service class?
38. How would you design a system with multiple storage implementations?
39. How would you avoid tightly coupled business logic?
40. How would you test a service that depends on external APIs?

---

# 12. Practical Coding Exercises

Practice these problems to understand how to apply SOLID in actual Java applications.

## Exercise 1 — SRP

**Problem:** Refactor a class that handles employee data, database persistence, email and report generation.

Expected design:

- Employee.
- EmployeeRepository.
- EmailService.
- ReportService.

## Exercise 2 — OCP

**Problem:** Design a discount system that supports:

- Regular customer discount.
- Premium customer discount.
- Festival discount.
- Employee discount.

Expected approach:

- DiscountStrategy interface.
- Separate implementations.
- DiscountService using the abstraction.

## Exercise 3 — LSP

**Problem:** Design a notification hierarchy with:

- Email notification.
- SMS notification.
- Push notification.

Ensure each implementation satisfies the notification contract.

## Exercise 4 — ISP

**Problem:** Design a document management system with:

- Read.
- Write.
- Print.
- Scan.

A read-only client should not be forced to implement write or print functionality.

## Exercise 5 — DIP

**Problem:** Design an order service that supports:

- MySQL database.
- PostgreSQL database.
- Email notification.
- SMS notification.

Business logic should depend on abstractions rather than directly creating implementations.

## Exercise 6 — Complete SOLID Design

Build an e-commerce order processing module containing:

- OrderController.
- OrderService.
- PaymentGateway.
- NotificationService.
- OrderRepository.
- Multiple payment implementations.
- Multiple notification implementations.

Apply all five SOLID principles and write unit tests using JUnit and Mockito.

---

# 13. Final Revision Cheat Sheet

## 13.1 SOLID in One Table

| Principle | Meaning | Main Question |
|---|---|---|
| SRP | One reason to change | Does this class have unrelated responsibilities? |
| OCP | Open for extension, closed for modification | Can I add behavior without repeatedly modifying stable code? |
| LSP | Subtypes must be substitutable | Can a subtype replace its parent without breaking behavior? |
| ISP | Small, focused interfaces | Are clients forced to implement unused methods? |
| DIP | Depend on abstractions | Does business logic depend directly on concrete implementations? |

## 13.2 Quick Java Examples

| Principle | Java Technique |
|---|---|
| SRP | Separate classes by responsibility |
| OCP | Interfaces and polymorphism |
| LSP | Correct inheritance and behavioral contracts |
| ISP | Multiple focused interfaces |
| DIP | Constructor injection and abstractions |

## 13.3 Priority for 5+ Years Experience

| Priority | Topic | Expected Knowledge |
|---|---|---|
| Must Master | All five SOLID principles | Explain definitions with Java examples |
| Must Master | SRP and OCP | Refactor large classes and conditional logic |
| Must Master | LSP | Explain inheritance and behavioral contract violations |
| Must Master | ISP | Design focused interfaces |
| Must Master | DIP and DI | Explain Spring IoC and constructor injection |
| Important | SOLID with Spring Boot | Apply principles in layered architecture |
| Important | SOLID with design patterns | Connect Strategy, Factory and Adapter |
| Important | Scenario-based design | Design payment, order and notification systems |

## 13.4 Self-Assessment

- [ ] Explain SOLID without looking at notes.
- [ ] Identify violations in Java code.
- [ ] Refactor a class using SRP.
- [ ] Implement OCP using Strategy pattern.
- [ ] Explain LSP with Bird/Penguin and Rectangle/Square examples.
- [ ] Split large interfaces using ISP.
- [ ] Implement DIP using constructor injection.
- [ ] Explain DIP vs DI vs IoC.
- [ ] Apply SOLID in a Spring Boot application.
- [ ] Design an order processing system using all five principles.

---

# Final Interview Tip

For a 5+ years Java Developer interview, don't just memorize the five definitions.

For every principle, prepare these four things:

1. Definition in your own words.
2. A Java example showing the violation.
3. A refactored implementation following the principle.
4. A real-world Spring Boot use case.

**Golden rule:** SOLID is not about creating more interfaces or classes everywhere. It is about choosing boundaries and dependencies that make software easier to change, test and maintain.