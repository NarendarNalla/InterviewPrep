# Hibernate, JPA and Spring Data JPA — Complete Guide

**Level:** 5+ Years Experience  
**Technology:** Java 17+, Spring Boot 3.x, Hibernate 6.x, Jakarta Persistence, Spring Data JPA, MySQL

---

# Table of Contents

1. [Introduction to ORM](#1-introduction-to-orm)
2. [JDBC vs ORM vs JPA vs Hibernate vs Spring Data JPA](#2-jdbc-vs-orm-vs-jpa-vs-hibernate-vs-spring-data-jpa)
3. [How Spring Boot, JPA and Hibernate Work Together](#3-how-spring-boot-jpa-and-hibernate-work-together)
4. [Project Setup](#4-project-setup)
5. [Understanding Database Tables](#5-understanding-database-tables)
6. [Entity Mapping and Annotations](#6-entity-mapping-and-annotations)
7. [Primary Key Generation Strategies](#7-primary-key-generation-strategies)
8. [Entity Relationships](#8-entity-relationships)
9. [Persistence Context](#9-persistence-context)
10. [Entity Lifecycle](#10-entity-lifecycle)
11. [CRUD Operations](#11-crud-operations)
12. [Dirty Checking and Flush](#12-dirty-checking-and-flush)
13. [First-Level and Second-Level Cache](#13-first-level-and-second-level-cache)
14. [Fetch Types and Loading Strategies](#14-fetch-types-and-loading-strategies)
15. [N+1 Query Problem](#15-n1-query-problem)
16. [Cascade Types and Orphan Removal](#16-cascade-types-and-orphan-removal)
17. [JPQL, Native SQL and Derived Queries](#17-jpql-native-sql-and-derived-queries)
18. [Spring Data JPA Repository](#18-spring-data-jpa-repository)
19. [Pagination and Sorting](#19-pagination-and-sorting)
20. [Projection](#20-projection)
21. [Transaction Management](#21-transaction-management)
22. [Locking and Concurrency](#22-locking-and-concurrency)
23. [Auditing](#23-auditing)
24. [Advanced Hibernate Concepts](#24-advanced-hibernate-concepts)
25. [Performance Optimization](#25-performance-optimization)
26. [Real-World Implementation](#26-real-world-implementation)
27. [Testing JPA Applications](#27-testing-jpa-applications)
28. [Common Exceptions](#28-common-exceptions)
29. [Interview Questions and Answers](#29-interview-questions-and-answers)
30. [Important Interview Revision Points](#30-important-interview-revision-points)

---

# 1. Introduction to ORM

## 1.1 What is ORM?

ORM stands for **Object Relational Mapping**.

It is a technique that maps Java objects to relational database tables.

In Java, we work with objects, classes and fields.

In relational databases, we work with tables, rows and columns.

ORM acts as a bridge between these two worlds.

### Without ORM

Suppose we have an employee table:

```sql
CREATE TABLE employees (
    id BIGINT PRIMARY KEY,
    name VARCHAR(100),
    salary DOUBLE
);
```

To retrieve an employee using JDBC:

```java
String sql = "SELECT * FROM employees WHERE id = ?";

PreparedStatement ps = connection.prepareStatement(sql);
ps.setLong(1, 1L);

ResultSet rs = ps.executeQuery();

while (rs.next()) {
    Employee employee = new Employee();

    employee.setId(rs.getLong("id"));
    employee.setName(rs.getString("name"));
    employee.setSalary(rs.getDouble("salary"));
}
```

Problems:

- We manually write SQL queries.
- We manually map database columns to Java fields.
- We need to manage connections.
- We need to manage PreparedStatements and ResultSets.
- Repeated boilerplate code.
- Relationship mapping becomes complicated.

### With ORM

Create a Java entity:

```java
@Entity
@Table(name = "employees")
public class Employee {

    @Id
    private Long id;

    private String name;

    private Double salary;
}
```

Retrieve the employee:

```java
Employee employee = entityManager.find(Employee.class, 1L);
```

Hibernate internally generates SQL and maps the database result to a Java object.

### ORM Mapping

| Java | Database |
|---|---|
| Class | Table |
| Object | Row |
| Field | Column |
| Object ID | Primary Key |
| @ManyToOne | Foreign Key Relationship |
| @OneToMany | One-to-Many Relationship |

**Why do we need ORM?**

To reduce boilerplate database code, manage object relationships, track changes and simplify database operations.

---

# 2. JDBC vs ORM vs JPA vs Hibernate vs Spring Data JPA

These terms are related but are not the same.

## 2.1 JDBC

JDBC stands for Java Database Connectivity.

It is a Java API used to communicate with databases.

It provides:

- Connection
- PreparedStatement
- ResultSet
- SQL execution
- Transaction handling

Example:

```java
Connection connection = dataSource.getConnection();

PreparedStatement ps = connection.prepareStatement(
    "SELECT * FROM employees WHERE id = ?"
);

ps.setLong(1, 1L);

ResultSet rs = ps.executeQuery();
```

JDBC gives direct control over SQL execution.

## 2.2 JPA

JPA stands for Java Persistence API. It is now commonly referred to as Jakarta Persistence.

JPA is a specification, not an implementation.

It defines:

- Entity management
- Entity relationships
- Persistence Context
- EntityManager
- JPQL
- Transaction-related persistence behavior
- Mapping annotations

It tells providers how persistence should work.

## 2.3 Hibernate

Hibernate is an ORM framework and a popular implementation of the JPA specification.

It implements JPA interfaces and provides additional features.

Examples:

- Automatic SQL generation
- Dirty checking
- First-level cache
- Second-level cache
- Lazy loading
- Query optimization
- Batch processing

Hibernate can also be used through its native APIs.

## 2.4 Spring Data JPA

Spring Data JPA is a Spring abstraction built on top of JPA.

It simplifies repository implementation.

Instead of manually writing CRUD implementations, we define interfaces.

```java
public interface EmployeeRepository
        extends JpaRepository<Employee, Long> {

    List<Employee> findByName(String name);

}
```

Spring Data JPA generates the repository implementation at runtime.

## 2.5 Comparison

| Technology | Purpose |
|---|---|
| JDBC | Low-level database communication |
| ORM | Object-relational mapping technique |
| JPA | Persistence specification |
| Hibernate | ORM framework and JPA implementation |
| Spring Data JPA | Repository abstraction over JPA |
| Spring JDBC | Spring abstraction over JDBC |

### Important Interview Question

**Is JPA a framework?**

No. JPA is a specification. Hibernate is one of its implementations.

**Is Spring Data JPA an implementation of JPA?**

No. Spring Data JPA simplifies working with JPA providers such as Hibernate.

---

# 3. How Spring Boot, JPA and Hibernate Work Together

This is one of the most important concepts.

Consider this architecture:

```text
Controller
    |
    v
Service
    |
    v
Repository
    |
    v
Spring Data JPA
    |
    v
JPA EntityManager
    |
    v
Hibernate
    |
    v
JDBC
    |
    v
MySQL Database
```

### Responsibilities

| Layer | Responsibility |
|---|---|
| Controller | Receives HTTP requests |
| Service | Business logic and transactions |
| Repository | Database operations |
| Spring Data JPA | Generates repository implementations |
| EntityManager | Manages entity persistence |
| Hibernate | ORM implementation and SQL generation |
| JDBC | Executes SQL against the database |
| MySQL | Stores actual records |

### Example

```java
employeeRepository.save(employee);
```

What happens internally?

1. Spring Data JPA receives the save request.
2. It checks whether the entity is new or existing.
3. It invokes the appropriate EntityManager operation.
4. Hibernate manages the entity inside its Persistence Context.
5. Hibernate generates an INSERT or UPDATE statement.
6. JDBC executes the generated SQL.
7. MySQL stores the record.
8. The entity is returned to the caller.

**Important:** SQL execution may be delayed until flush or transaction commit. Calling save does not always mean SQL executes immediately.

---

# 4. Project Setup

## 4.1 Maven Dependencies

Create a Spring Boot project with these dependencies:

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

    <!-- MySQL Driver -->
    <dependency>
        <groupId>com.mysql</groupId>
        <artifactId>mysql-connector-j</artifactId>
        <scope>runtime</scope>
    </dependency>

    <!-- Validation -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-validation</artifactId>
    </dependency>

    <!-- Lombok (Optional) -->
    <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
        <optional>true</optional>
    </dependency>

</dependencies>
```

## 4.2 application.properties

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/company_db
spring.datasource.username=root
spring.datasource.password=root

spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

# Hibernate SQL generation strategy
spring.jpa.hibernate.ddl-auto=update

# Display generated SQL
spring.jpa.show-sql=true

# Format SQL
spring.jpa.properties.hibernate.format_sql=true

# Display SQL parameter values (Hibernate 6)
logging.level.org.hibernate.orm.jdbc.bind=TRACE
```

### ddl-auto values

| Value | Behavior |
|---|---|
| none | No automatic schema management |
| create | Drops and recreates tables at startup |
| create-drop | Creates tables at startup and drops them at shutdown |
| update | Updates schema where possible without intentionally dropping existing data |
| validate | Validates entity mappings against existing schema |

**Production recommendation:** Prefer Flyway or Liquibase for database schema migrations. Use `ddl-auto=validate` or `none` in production.

---

# 5. Understanding Database Tables

We will use an Employee Management System throughout these notes.

Consider three tables:

1. departments
2. employees
3. projects

## 5.1 Department Table

```sql
CREATE TABLE departments (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    location VARCHAR(100)
);
```

Sample data:

| id | name | location |
|---|---|---|
| 1 | Engineering | Hyderabad |
| 2 | HR | Bangalore |
| 3 | Finance | Chennai |

## 5.2 Employee Table

```sql
CREATE TABLE employees (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100),
    email VARCHAR(150),
    salary DOUBLE,
    department_id BIGINT,

    FOREIGN KEY (department_id)
        REFERENCES departments(id)
);
```

Sample data:

| id | name | email | salary | department_id |
|---|---|---|---:|---:|
| 101 | Ravi | ravi@gmail.com | 80000 | 1 |
| 102 | Kiran | kiran@gmail.com | 90000 | 1 |
| 103 | Priya | priya@gmail.com | 70000 | 2 |
| 104 | Arun | arun@gmail.com | 60000 | 3 |

## 5.3 Projects Table

```sql
CREATE TABLE projects (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100),
    budget DOUBLE
);
```

Sample data:

| id | name | budget |
|---|---|---:|
| 201 | Banking Application | 500000 |
| 202 | Employee Portal | 300000 |
| 203 | Payment System | 700000 |

## 5.4 Employee Project Mapping

An employee can work on multiple projects, and a project can have multiple employees.

This is a Many-to-Many relationship.

```sql
CREATE TABLE employee_projects (
    employee_id BIGINT,
    project_id BIGINT,

    PRIMARY KEY (employee_id, project_id),

    FOREIGN KEY (employee_id) REFERENCES employees(id),
    FOREIGN KEY (project_id) REFERENCES projects(id)
);
```

Sample data:

| employee_id | project_id |
|---|---|
| 101 | 201 |
| 101 | 202 |
| 102 | 201 |
| 103 | 203 |
| 104 | 202 |

Keep these tables in mind. We will use them to understand entity mapping and relationships.

---

# 6. Entity Mapping and Annotations

## 6.1 What is an Entity?

An entity is a Java class mapped to a database table.

Every entity must have a primary key.

Example:

```java
import jakarta.persistence.*;

@Entity
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(unique = true)
    private String email;

    private Double salary;

    // Constructors, getters and setters
}
```

Hibernate maps this class to the employees table.

## 6.2 Important Entity Annotations

| Annotation | Purpose |
|---|---|
| @Entity | Marks a class as a JPA entity |
| @Table | Specifies the database table |
| @Id | Defines primary key |
| @GeneratedValue | Configures primary key generation |
| @Column | Configures a column |
| @Transient | Excludes a field from persistence |
| @Enumerated | Maps enum values |
| @Temporal | Legacy mapping for Date/Calendar |
| @Lob | Maps large objects |
| @Embedded | Embeds a value object |
| @Embeddable | Defines an embeddable class |
| @Version | Enables optimistic locking |

## 6.3 @Entity

```java
@Entity
public class Employee {
    @Id
    private Long id;
}
```

Why?

Hibernate identifies this class as a persistent entity.

Without @Entity, Hibernate does not treat the class as a normal JPA entity.

## 6.4 @Table

```java
@Entity
@Table(
    name = "employees",
    schema = "company"
)
public class Employee {
}
```

Used to customize table mapping.

You can also configure:

- Unique constraints
- Indexes
- Schema
- Catalog

Example:

```java
@Table(
    name = "employees",
    indexes = {
        @Index(name = "idx_employee_email", columnList = "email")
    },
    uniqueConstraints = {
        @UniqueConstraint(columnNames = "email")
    }
)
```

## 6.5 @Column

```java
@Column(
    name = "employee_name",
    nullable = false,
    length = 100,
    unique = true
)
private String name;
```

Important properties:

| Property | Meaning |
|---|---|
| name | Database column name |
| nullable | Whether null is allowed |
| unique | Whether values must be unique |
| length | String column length |
| precision | Decimal precision |
| scale | Decimal scale |
| insertable | Include column in INSERT |
| updatable | Include column in UPDATE |

Note: JPA mapping constraints are not a substitute for database migrations and database constraints.

## 6.6 @Transient

```java
@Transient
private Double annualSalary;
```

This field will not be persisted by JPA.

It can be calculated at runtime.

```java
public Double getAnnualSalary() {
    return salary * 12;
}
```

The Java `transient` keyword is different: it relates to Java serialization, although JPA providers also exclude transient fields from persistent state.

## 6.7 @Enumerated

```java
public enum EmployeeStatus {
    ACTIVE,
    INACTIVE,
    RESIGNED
}
```

Entity:

```java
@Enumerated(EnumType.STRING)
private EmployeeStatus status;
```

Database value:

| id | name | status |
|---|---|---|
| 101 | Ravi | ACTIVE |
| 102 | Kiran | INACTIVE |

Two approaches:

**ORDINAL**

Stores numeric positions:

```text
ACTIVE   -> 0
INACTIVE -> 1
RESIGNED -> 2
```

**STRING**

Stores enum names:

```text
ACTIVE
INACTIVE
RESIGNED
```

Recommendation: Prefer STRING for most business applications because changing enum order will not silently change existing database meanings.

For more customized mappings, use an AttributeConverter.

## 6.8 @Embedded and @Embeddable

Used when multiple fields logically belong to one reusable value object.

Example:

```java
@Embeddable
public class Address {

    private String city;

    private String state;

    private String pincode;
}
```

Employee:

```java
@Entity
public class Employee {

    @Id
    private Long id;

    private String name;

    @Embedded
    private Address address;
}
```

Resulting table:

| id | name | city | state | pincode |
|---|---|---|---|---|
| 101 | Ravi | Hyderabad | Telangana | 500001 |

There is no separate address table by default.

Why use it?

To reuse related value fields without introducing another entity or table.

---

# 7. Primary Key Generation Strategies

JPA provides four main generation strategies.

## 7.1 IDENTITY

```java
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;
```

MySQL:

```sql
id BIGINT AUTO_INCREMENT PRIMARY KEY
```

Database generates the ID during INSERT.

Example:

```sql
INSERT INTO employees(name, email, salary, department_id)
VALUES ('Ravi', 'ravi@gmail.com', 80000, 1);
```

MySQL generates the ID.

## 7.2 SEQUENCE

```java
@Id
@GeneratedValue(strategy = GenerationType.SEQUENCE)
private Long id;
```

Uses a database sequence.

Common in PostgreSQL and Oracle.

Example:

```sql
SELECT nextval('employee_seq');
```

Hibernate can use sequence allocation optimizations.

## 7.3 TABLE

```java
@Id
@GeneratedValue(strategy = GenerationType.TABLE)
private Long id;
```

Uses a separate database table to generate IDs.

Usually less efficient than native identity or sequence strategies.

## 7.4 AUTO

```java
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
private Long id;
```

Provider selects a suitable strategy based on the database and configuration.

### Comparison

| Strategy | ID generated by | Typical usage |
|---|---|---|
| IDENTITY | Database auto-increment | MySQL |
| SEQUENCE | Database sequence | PostgreSQL, Oracle |
| TABLE | ID generator table | Less common |
| AUTO | Provider chooses | Database-independent mapping |

---

# 8. Entity Relationships

This is one of the most important JPA topics.

There are four major relationships:

1. One-to-One
2. One-to-Many
3. Many-to-One
4. Many-to-Many

## 8.1 Many-to-One

Many employees belong to one department.

Database relationship:

```text
departments
----------------
id | name
1  | Engineering
2  | HR

employees
--------------------------------
id  | name  | department_id
101 | Ravi  | 1
102 | Kiran | 1
103 | Priya | 2
```

Multiple employees can reference the same department.

Entity:

```java
@Entity
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    private Department department;
}
```

Department:

```java
@Entity
@Table(name = "departments")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
}
```

Important:

- @ManyToOne is placed on the employee side.
- @JoinColumn identifies the foreign key column.
- Employee is the owning side.
- Foreign key exists in employees table.

### Saving an Employee

```java
Department department =
    entityManager.find(Department.class, 1L);

Employee employee = new Employee();

employee.setName("Ravi");
employee.setDepartment(department);

employeeRepository.save(employee);
```

Expected SQL:

```sql
INSERT INTO employees(name, department_id)
VALUES ('Ravi', 1);
```

No new department is created.

The existing department is referenced.

## 8.2 One-to-Many

One department has many employees.

```java
@Entity
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "department")
    private List<Employee> employees = new ArrayList<>();
}
```

Here:

```java
mappedBy = "department"
```

means the Employee entity owns the relationship through its `department` field.

Important:

`mappedBy` refers to the Java entity property, not the database column name.

### Bidirectional Relationship

```java
public void addEmployee(Employee employee) {
    employees.add(employee);
    employee.setDepartment(this);
}

public void removeEmployee(Employee employee) {
    employees.remove(employee);
    employee.setDepartment(null);
}
```

Why maintain both sides?

Because JPA relationship updates are controlled by the owning side.

If you only add an employee to the Department collection but don't set the Employee.department property, the foreign key might not be updated as expected.

## 8.3 One-to-One

One employee has one employee profile.

Tables:

```sql
CREATE TABLE employee_profiles (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    address VARCHAR(200),
    phone VARCHAR(20),
    employee_id BIGINT UNIQUE,
    FOREIGN KEY (employee_id) REFERENCES employees(id)
);
```

EmployeeProfile entity:

```java
@Entity
@Table(name = "employee_profiles")
public class EmployeeProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String address;

    private String phone;

    @OneToOne
    @JoinColumn(name = "employee_id", unique = true)
    private Employee employee;
}
```

The UNIQUE constraint ensures one profile per employee.

## 8.4 Many-to-Many

Multiple employees work on multiple projects.

Employee:

```java
@ManyToMany
@JoinTable(
    name = "employee_projects",
    joinColumns = @JoinColumn(name = "employee_id"),
    inverseJoinColumns = @JoinColumn(name = "project_id")
)
private Set<Project> projects = new HashSet<>();
```

Project:

```java
@ManyToMany(mappedBy = "projects")
private Set<Employee> employees = new HashSet<>();
```

Result:

```text
employees
    |
    | Many-to-Many
    |
employee_projects
    |
    | Many-to-Many
    |
projects
```

### Important Real-World Recommendation

If the join table contains additional information, such as:

- Assigned date
- Project role
- Allocation percentage
- Project status

Do not use a simple @ManyToMany mapping.

Create a separate entity such as EmployeeProject.

This provides better control over business data.

---

# 9. Persistence Context

This is one of the most important internal Hibernate concepts.

## 9.1 What is Persistence Context?

Persistence Context is a managed environment where JPA tracks entity objects.

It maintains the relationship between database records and Java entity instances.

It is also commonly described as an identity map.

Example:

```java
Employee employee1 =
    entityManager.find(Employee.class, 101L);

Employee employee2 =
    entityManager.find(Employee.class, 101L);
```

What happens?

The first call:

```sql
SELECT * FROM employees WHERE id = 101;
```

Hibernate retrieves the record and stores the managed object in the Persistence Context.

The second call:

Hibernate finds the same entity in the Persistence Context and returns it without executing another SELECT.

```java
System.out.println(employee1 == employee2);

// true
```

This is the identity guarantee within the same Persistence Context.

## 9.2 Why do we need Persistence Context?

It provides:

1. First-level caching.
2. Dirty checking.
3. Identity management.
4. Entity lifecycle tracking.
5. Deferred SQL execution.
6. Coordination of database changes.

## 9.3 Example: Persistence Context Behavior

Initial database:

| id | name | salary |
|---|---|---:|
| 101 | Ravi | 80000 |

Code:

```java
Employee employee =
    entityManager.find(Employee.class, 101L);

employee.setSalary(100000.0);
```

What happens?

The database is still:

| id | name | salary |
|---|---|---:|
| 101 | Ravi | 80000 |

But the managed Java object is:

```text
Employee {
    id = 101,
    name = Ravi,
    salary = 100000
}
```

Hibernate tracks the modification.

When flush occurs:

```sql
UPDATE employees
SET salary = 100000
WHERE id = 101;
```

Database becomes:

| id | name | salary |
|---|---|---:|
| 101 | Ravi | 100000 |

This is Dirty Checking.

---

# 10. Entity Lifecycle

JPA entities have four important states.

1. Transient
2. Managed
3. Detached
4. Removed

## 10.1 Transient State

A newly created Java object that is not managed by JPA.

```java
Employee employee = new Employee();

employee.setName("Ravi");
```

No database record exists.

## 10.2 Managed State

An entity associated with the Persistence Context.

```java
Employee employee =
    entityManager.find(Employee.class, 101L);
```

Hibernate tracks changes to this entity.

## 10.3 Detached State

An entity that was previously managed but is no longer associated with the Persistence Context.

```java
entityManager.detach(employee);
```

Changes made afterward are not automatically synchronized.

## 10.4 Removed State

An entity marked for deletion.

```java
entityManager.remove(employee);
```

The database DELETE normally happens during flush or commit.

### Lifecycle Diagram

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

Detached
    |
    | merge()
    v
Managed (returned managed instance)
```

## 10.5 persist() vs merge()

This is a frequently asked interview question.

### persist()

```java
entityManager.persist(employee);
```

- Makes a new entity managed.
- Schedules INSERT.
- Returns void.
- Does not return an updated entity reference.

### merge()

```java
Employee managedEmployee =
    entityManager.merge(employee);
```

- Copies the state of the supplied entity into a managed instance.
- Returns the managed instance.
- Commonly used when working with detached entities.
- The supplied detached object itself remains detached.

Example:

```java
Employee detachedEmployee = getDetachedEmployee();

detachedEmployee.setSalary(120000.0);

Employee managedEmployee =
    entityManager.merge(detachedEmployee);
```

Important:

Always use the object returned by merge if you need to continue modifying the managed entity.

---

# 11. CRUD Operations

CRUD means:

- Create
- Read
- Update
- Delete

Let's implement these operations using Spring Data JPA.

## 11.1 Repository

```java
@Repository
public interface EmployeeRepository
        extends JpaRepository<Employee, Long> {

}
```

JpaRepository provides CRUD and pagination functionality.

## 11.2 Create

```java
Employee employee = new Employee();

employee.setName("Ravi");
employee.setEmail("ravi@gmail.com");
employee.setSalary(80000.0);

employeeRepository.save(employee);
```

Possible SQL:

```sql
INSERT INTO employees(name, email, salary)
VALUES ('Ravi', 'ravi@gmail.com', 80000);
```

For a new entity, Spring Data JPA generally uses persist.

## 11.3 Read

```java
Optional<Employee> employee =
    employeeRepository.findById(101L);
```

SQL:

```sql
SELECT *
FROM employees
WHERE id = 101;
```

## 11.4 Update

```java
Employee employee =
    employeeRepository.findById(101L)
        .orElseThrow();

employee.setSalary(100000.0);

employeeRepository.save(employee);
```

Important:

If this entity is already managed within the transaction, dirty checking can synchronize the change without explicitly calling save again.

SQL:

```sql
UPDATE employees
SET salary = 100000
WHERE id = 101;
```

## 11.5 Delete

```java
employeeRepository.deleteById(101L);
```

SQL behavior may involve loading the entity before deletion, depending on the repository implementation and provider.

The deletion is executed when the persistence context flushes.

## 11.6 findById() vs getReferenceById()

### findById()

```java
Employee employee =
    employeeRepository.findById(101L)
        .orElseThrow();
```

Generally retrieves the entity immediately.

### getReferenceById()

```java
Employee employee =
    employeeRepository.getReferenceById(101L);
```

Returns a reference, often a lazy proxy.

The SELECT may happen when non-ID properties are accessed.

Use it when you need to assign an existing entity reference without loading all its fields, but remember that a nonexistent ID can cause an exception later.

---

# 12. Dirty Checking and Flush

## 12.1 What is Dirty Checking?

Hibernate automatically identifies changes made to managed entities.

Example:

```java
@Transactional
public void updateSalary(Long id) {

    Employee employee =
        employeeRepository.findById(id)
            .orElseThrow();

    employee.setSalary(150000.0);

}
```

Notice that we did not call:

```java
employeeRepository.save(employee);
```

Yet Hibernate updates the database.

Why?

Because the entity is managed and Hibernate tracks its state.

## 12.2 How Dirty Checking Works

Conceptually:

1. Hibernate loads the entity.
2. It maintains the entity's original state.
3. Application changes entity fields.
4. During flush, Hibernate compares the current state with the tracked state.
5. If changes exist, Hibernate generates UPDATE SQL.

Note: Hibernate 6 also supports bytecode-enhanced dirty tracking, which can optimize change detection.

## 12.3 What is Flush?

Flush synchronizes pending changes in the Persistence Context with the database.

```java
entityManager.flush();
```

Example:

```java
@Transactional
public void updateEmployee() {

    Employee employee =
        entityManager.find(Employee.class, 101L);

    employee.setSalary(150000.0);

    entityManager.flush();

    // SQL UPDATE executes before this point completes.
}
```

## 12.4 Flush vs Commit

| Flush | Commit |
|---|---|
| Synchronizes persistence changes with DB | Completes the transaction |
| Does not commit transaction | Commits transaction |
| Can happen multiple times | Normally once per transaction |
| Changes may still be rolled back | Makes transaction changes durable |

### Important

Flush does not mean commit.

Example:

```java
@Transactional
public void updateEmployee() {

    Employee employee =
        entityManager.find(Employee.class, 101L);

    employee.setSalary(150000.0);

    entityManager.flush();

    throw new RuntimeException("Something failed");
}
```

The UPDATE may already have executed, but the transaction is rolled back.

The database retains the original salary.

## 12.5 Flush Modes

- AUTO: Flushes when necessary, commonly before queries affected by pending changes and before commit.
- COMMIT: Flushes at transaction commit, though providers may flush earlier in some circumstances.

JPA also provides explicit flush control through EntityManager.

---

# 13. First-Level and Second-Level Cache

Caching reduces unnecessary database access.

## 13.1 First-Level Cache

First-level cache belongs to the Persistence Context.

It is enabled by default.

Example:

```java
Employee e1 =
    entityManager.find(Employee.class, 101L);

Employee e2 =
    entityManager.find(Employee.class, 101L);
```

Only one SELECT is normally executed.

```java
System.out.println(e1 == e2); // true
```

Characteristics:

- Enabled by default.
- Associated with the Persistence Context.
- Not shared across independent Persistence Contexts.
- Cleared when the Persistence Context is cleared or closed.

## 13.2 Second-Level Cache

Second-level cache is associated with the Hibernate SessionFactory and can be shared across sessions.

It is optional.

Typical providers include:

- Ehcache
- Infinispan
- Other compatible caching implementations

Example:

```java
@Entity
@Cacheable
@org.hibernate.annotations.Cache(
    usage = CacheConcurrencyStrategy.READ_WRITE
)
public class Department {

    @Id
    private Long id;

    private String name;
}
```

The actual cache must also be configured and enabled.

### Comparison

| First-Level Cache | Second-Level Cache |
|---|---|
| Enabled by default | Optional |
| Persistence Context level | SessionFactory level |
| Not shared across contexts | Shared across sessions |
| Managed by JPA/Hibernate | Configured with cache provider |
| Entity identity management | Reuses cached entity data |

## 13.3 Query Cache

Hibernate also supports query caching.

It caches query result information, subject to query and entity cache configuration.

Query cache is not a replacement for the second-level entity cache.

---

# 14. Fetch Types and Loading Strategies

Fetch strategy controls when related entities are loaded.

Two major fetch types:

- LAZY
- EAGER

## 14.1 LAZY

Related data is loaded when it is accessed.

```java
@ManyToOne(fetch = FetchType.LAZY)
private Department department;
```

Example:

```java
Employee employee =
    employeeRepository.findById(101L)
        .orElseThrow();
```

Hibernate may execute:

```sql
SELECT * FROM employees WHERE id = 101;
```

It may not load the department immediately.

When accessing:

```java
employee.getDepartment().getName();
```

Hibernate may execute:

```sql
SELECT * FROM departments WHERE id = 1;
```

## 14.2 EAGER

Related data is required to be fetched eagerly.

```java
@ManyToOne(fetch = FetchType.EAGER)
private Department department;
```

The provider must make the related data available when the entity is loaded, but it is not guaranteed to use a JOIN. It may use one or multiple SQL statements.

## 14.3 Default Fetch Types

| Relationship | Default |
|---|---|
| @OneToOne | EAGER |
| @ManyToOne | EAGER |
| @OneToMany | LAZY |
| @ManyToMany | LAZY |

Recommendation:

Prefer LAZY associations and explicitly fetch what a use case needs.

JPA's default EAGER settings for to-one associations can cause unnecessary loading.

## 14.4 LazyInitializationException

Example:

```java
public Employee getEmployee(Long id) {

    Employee employee =
        employeeRepository.findById(id)
            .orElseThrow();

    return employee;
}
```

Later, outside the persistence context:

```java
employee.getDepartment().getName();
```

This may throw:

```text
LazyInitializationException:
could not initialize proxy - no Session
```

Why?

The associated Department was not loaded, and the Persistence Context is already closed.

Solutions:

1. Fetch the association inside a transaction.
2. Use JOIN FETCH.
3. Use EntityGraph.
4. Use DTO projections.

Do not solve every lazy-loading issue by making every relationship EAGER.

---

# 15. N+1 Query Problem

This is a very important Hibernate performance interview question.

## 15.1 What is N+1?

Suppose we have 100 employees.

Each employee belongs to one department.

We execute:

```java
List<Employee> employees =
    employeeRepository.findAll();

for (Employee employee : employees) {

    System.out.println(
        employee.getDepartment().getName()
    );

}
```

Hibernate may execute:

First query:

```sql
SELECT * FROM employees;
```

Then one query for each distinct unloaded department:

```sql
SELECT * FROM departments WHERE id = 1;

SELECT * FROM departments WHERE id = 2;

SELECT * FROM departments WHERE id = 3;
```

If there are 100 employees and 10 distinct departments, this can result in:

```text
1 employee query + 10 department queries = 11 queries
```

The actual number depends on distinct associations, cache state and fetching strategy.

## 15.2 Solution 1: JOIN FETCH

Repository:

```java
@Query("""
    SELECT e
    FROM Employee e
    JOIN FETCH e.department
    """)
List<Employee> findAllWithDepartment();
```

SQL generated conceptually:

```sql
SELECT e.*, d.*
FROM employees e
JOIN departments d
ON e.department_id = d.id;
```

Now both employee and department data are fetched together.

## 15.3 Solution 2: EntityGraph

```java
@EntityGraph(attributePaths = {"department"})
List<Employee> findAll();
```

This tells Spring Data JPA to fetch the department association for this operation.

The provider controls the SQL strategy.

## 15.4 Solution 3: Batch Fetching

Hibernate supports batch fetching.

```java
@BatchSize(size = 20)
@ManyToOne(fetch = FetchType.LAZY)
private Department department;
```

Or globally:

```properties
spring.jpa.properties.hibernate.default_batch_fetch_size=20
```

Instead of individual department queries, Hibernate can retrieve multiple associations using IN queries.

Example:

```sql
SELECT *
FROM departments
WHERE id IN (1, 2, 3, 4, 5);
```

### Comparison

| Solution | Behavior |
|---|---|
| JOIN FETCH | Loads association using join |
| EntityGraph | Defines fetch plan for a query |
| Batch Fetching | Loads multiple associations in batches |
| DTO Projection | Retrieves only required columns |

Important:

JOIN FETCH on collection relationships can produce duplicate parent rows at SQL level. Pagination over collection fetch joins requires special care.

---

# 16. Cascade Types and Orphan Removal

## 16.1 What is Cascade?

Cascade defines whether persistence operations should propagate from one entity to associated entities.

Example:

Department has multiple employees.

```java
@OneToMany(
    mappedBy = "department",
    cascade = CascadeType.ALL
)
private List<Employee> employees = new ArrayList<>();
```

When we save a Department, persistence operations can cascade to its employees.

## 16.2 Cascade Types

| Type | Behavior |
|---|---|
| PERSIST | Propagates persist |
| MERGE | Propagates merge |
| REMOVE | Propagates remove |
| REFRESH | Propagates refresh |
| DETACH | Propagates detach |
| ALL | Includes all above |

## 16.3 Cascade Example

```java
Department department = new Department();

department.setName("Engineering");

Employee employee1 = new Employee();
employee1.setName("Ravi");

Employee employee2 = new Employee();
employee2.setName("Kiran");

department.addEmployee(employee1);
department.addEmployee(employee2);

departmentRepository.save(department);
```

With appropriate cascade configuration and relationship ownership:

Hibernate inserts:

```sql
INSERT INTO departments(name)
VALUES ('Engineering');

INSERT INTO employees(name, department_id)
VALUES ('Ravi', 1);

INSERT INTO employees(name, department_id)
VALUES ('Kiran', 1);
```

## 16.4 What is orphanRemoval?

Orphan removal deletes a child entity when it is removed from its parent's relationship.

```java
@OneToMany(
    mappedBy = "department",
    cascade = CascadeType.ALL,
    orphanRemoval = true
)
private List<Employee> employees = new ArrayList<>();
```

Example:

```java
department.getEmployees().remove(employee);
```

When the persistence context flushes, the removed child is scheduled for DELETE.

```sql
DELETE FROM employees
WHERE id = 101;
```

### Cascade REMOVE vs orphanRemoval

| Cascade REMOVE | orphanRemoval |
|---|---|
| Propagates parent removal to children | Deletes a child removed from the relationship |
| Triggered by removing parent | Triggered by dissociating child |
| Relationship operation propagation | Lifecycle ownership behavior |

Important:

Do not use CascadeType.ALL blindly.

For example, deleting a Department should not necessarily delete all Employees if employees are business records that must remain.

---

# 17. JPQL, Native SQL and Derived Queries

Spring Data JPA supports multiple query approaches.

## 17.1 Derived Query Methods

Spring Data generates queries based on method names.

```java
List<Employee> findByName(String name);

List<Employee> findBySalaryGreaterThan(Double salary);

List<Employee> findByDepartmentId(Long departmentId);

List<Employee> findByNameContaining(String name);

Optional<Employee> findByEmail(String email);
```

Example:

```java
employeeRepository.findBySalaryGreaterThan(70000.0);
```

Generated SQL conceptually:

```sql
SELECT *
FROM employees
WHERE salary > 70000;
```

## 17.2 JPQL

JPQL stands for Java Persistence Query Language.

It operates on entities and their properties, not directly on table and column names.

Example:

```java
@Query("""
    SELECT e
    FROM Employee e
    WHERE e.salary > :salary
    """)
List<Employee> findEmployeesWithSalaryGreaterThan(
    @Param("salary") Double salary
);
```

Notice:

```java
Employee
```

is the entity name.

```java
salary
```

is the Java property.

Not the table name or column name.

### Join Example

```java
@Query("""
    SELECT e
    FROM Employee e
    JOIN e.department d
    WHERE d.name = :departmentName
    """)
List<Employee> findByDepartmentName(
    @Param("departmentName") String departmentName
);
```

## 17.3 Native SQL

Native SQL allows writing database-specific SQL.

```java
@Query(
    value = """
        SELECT *
        FROM employees
        WHERE salary > :salary
        """,
    nativeQuery = true
)
List<Employee> findBySalaryNative(
    @Param("salary") Double salary
);
```

## 17.4 JPQL vs Native SQL

| JPQL | Native SQL |
|---|---|
| Uses entities | Uses database tables |
| Uses Java properties | Uses database columns |
| Database-independent query language | Can be database-specific |
| Provider generates SQL | Developer writes SQL |
| Supports entity-oriented operations | Supports database-specific operations |

## 17.5 Named Parameters

Prefer named parameters:

```java
@Query("""
    SELECT e
    FROM Employee e
    WHERE e.salary BETWEEN :minSalary AND :maxSalary
    """)
List<Employee> findBySalaryRange(
    @Param("minSalary") Double minSalary,
    @Param("maxSalary") Double maxSalary
);
```

This is more readable and less error-prone than positional parameters.

## 17.6 Update and Delete Queries

```java
@Modifying
@Query("""
    UPDATE Employee e
    SET e.salary = :salary
    WHERE e.id = :id
    """)
int updateSalary(
    @Param("id") Long id,
    @Param("salary") Double salary
);
```

Call it from a transaction:

```java
@Transactional
public void updateSalary(Long id, Double salary) {
    employeeRepository.updateSalary(id, salary);
}
```

Important:

Bulk JPQL UPDATE and DELETE operations bypass normal entity-by-entity dirty checking and can leave already-managed entities stale.

Use:

```java
@Modifying(
    clearAutomatically = true,
    flushAutomatically = true
)
```

when appropriate.

---

# 18. Spring Data JPA Repository

Spring Data JPA provides different repository abstractions.

## 18.1 Repository

Base marker interface.

```java
public interface EmployeeRepository
        extends Repository<Employee, Long> {
}
```

## 18.2 CrudRepository

Provides basic CRUD methods.

```java
public interface EmployeeRepository
        extends CrudRepository<Employee, Long> {
}
```

Examples:

- save()
- findById()
- findAll()
- deleteById()
- count()
- existsById()

## 18.3 PagingAndSortingRepository

Provides paging and sorting functionality.

## 18.4 JpaRepository

Provides JPA-specific functionality in addition to CRUD and paging/sorting operations.

```java
public interface EmployeeRepository
        extends JpaRepository<Employee, Long> {
}
```

Important:

Modern Spring Data versions compose repository capabilities through several interfaces. JpaRepository is generally the convenient choice for ordinary JPA applications.

## 18.5 save() Internally

Spring Data JPA uses entity information to determine whether an entity is new.

Conceptually:

```java
if (entityIsNew) {
    entityManager.persist(entity);
} else {
    entityManager.merge(entity);
}
```

For typical generated-ID entities, a null ID indicates a new entity.

However, Spring Data JPA also supports Persistable.isNew() and other entity-newness strategies.

## 18.6 save() vs saveAndFlush()

```java
employeeRepository.save(employee);
```

Schedules persistence work within the transaction.

```java
employeeRepository.saveAndFlush(employee);
```

Saves and explicitly flushes pending changes.

Flush is not commit.

---

# 19. Pagination and Sorting

Useful when retrieving large datasets.

## 19.1 Without Pagination

```java
List<Employee> employees =
    employeeRepository.findAll();
```

If the table has 1 million rows, loading everything can create memory and performance problems.

## 19.2 Pagination

```java
Page<Employee> findAll(Pageable pageable);
```

Service:

```java
Pageable pageable =
    PageRequest.of(0, 10);

Page<Employee> employees =
    employeeRepository.findAll(pageable);
```

SQL conceptually:

```sql
SELECT *
FROM employees
LIMIT 10 OFFSET 0;
```

Page information:

```java
employees.getContent();
employees.getTotalElements();
employees.getTotalPages();
employees.getNumber();
employees.getSize();
employees.hasNext();
```

## 19.3 Sorting

```java
Sort sort = Sort.by("salary").descending();

List<Employee> employees =
    employeeRepository.findAll(sort);
```

## 19.4 Pagination with Sorting

```java
Pageable pageable =
    PageRequest.of(
        0,
        10,
        Sort.by("salary").descending()
    );
```

### Page vs Slice

| Page | Slice |
|---|---|
| Provides total element count | Does not provide total count |
| May execute count query | Usually avoids count query |
| Useful for numbered pages | Useful for next/previous navigation |
| More expensive in some cases | Often more efficient |

For very large datasets, consider keyset pagination instead of deep OFFSET pagination.

---

# 20. Projection

Projection means retrieving only the fields required by the application instead of loading the complete entity.

Suppose we only need:

- Employee name
- Employee email
- Salary

## 20.1 Interface-Based Projection

```java
public interface EmployeeProjection {

    String getName();

    String getEmail();

    Double getSalary();
}
```

Repository:

```java
List<EmployeeProjection>
findByDepartmentId(Long departmentId);
```

## 20.2 DTO Projection

DTO:

```java
public record EmployeeDTO(
    Long id,
    String name,
    Double salary
) {}
```

JPQL:

```java
@Query("""
    SELECT new com.example.dto.EmployeeDTO(
        e.id,
        e.name,
        e.salary
    )
    FROM Employee e
    WHERE e.department.id = :departmentId
    """)
List<EmployeeDTO> findEmployeeDTOs(
    @Param("departmentId") Long departmentId
);
```

### Why use Projection?

Suppose the employees table contains 30 columns, but the API needs only 3.

Entity loading may retrieve unnecessary fields and may trigger relationship loading.

Projection reduces data transfer and can improve performance.

---

# 21. Transaction Management

## 21.1 What is a Transaction?

A transaction is a group of database operations that should execute as one logical unit.

Example:

Transfer ₹1,000 from Account A to Account B.

Two operations:

1. Debit ₹1,000 from A.
2. Credit ₹1,000 to B.

If debit succeeds but credit fails, the entire operation must roll back.

## 21.2 ACID Properties

| Property | Meaning |
|---|---|
| Atomicity | All operations succeed or none do |
| Consistency | Database remains valid |
| Isolation | Concurrent transactions are controlled |
| Durability | Committed data survives failures according to database guarantees |

## 21.3 @Transactional

```java
@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(
        EmployeeRepository employeeRepository
    ) {
        this.employeeRepository = employeeRepository;
    }

    @Transactional
    public void updateEmployee(Long id) {

        Employee employee =
            employeeRepository.findById(id)
                .orElseThrow();

        employee.setSalary(150000.0);

    }
}
```

Spring creates a transactional boundary around the method through transaction infrastructure.

At method completion:

- If successful, transaction is committed.
- If a rollback-triggering exception occurs, transaction is rolled back.

By default, Spring rolls back on unchecked exceptions and Errors, not every checked exception.

Use:

```java
@Transactional(rollbackFor = Exception.class)
```

when checked exceptions should also trigger rollback.

## 21.4 Transaction Propagation

Defines how a method participates in an existing transaction.

| Propagation | Behavior |
|---|---|
| REQUIRED | Uses existing transaction or creates one |
| REQUIRES_NEW | Suspends existing transaction and creates a new one |
| SUPPORTS | Uses existing transaction if available |
| NOT_SUPPORTED | Executes without a transaction |
| MANDATORY | Requires an existing transaction |
| NEVER | Fails if an existing transaction exists |
| NESTED | Uses a nested transaction/savepoint when supported |

### REQUIRED

```java
@Transactional(
    propagation = Propagation.REQUIRED
)
public void saveEmployee() {
}
```

Default propagation.

### REQUIRES_NEW

```java
@Transactional(
    propagation = Propagation.REQUIRES_NEW
)
public void saveAuditLog() {
}
```

Creates a separate transaction.

Useful for independent operations such as certain audit records, depending on the required business semantics.

Important: REQUIRES_NEW may require another database connection while the outer transaction is suspended.

## 21.5 Transaction Isolation

| Level | Dirty Read | Non-Repeatable Read | Phantom Read |
|---|---|---|---|
| READ_UNCOMMITTED | Possible | Possible | Possible |
| READ_COMMITTED | Prevented | Possible | Possible |
| REPEATABLE_READ | Prevented | Prevented | Database-dependent behavior |
| SERIALIZABLE | Prevented | Prevented | Prevented |

Spring example:

```java
@Transactional(
    isolation = Isolation.READ_COMMITTED
)
public void updateEmployee() {
}
```

Actual isolation behavior also depends on the database engine.

## 21.6 Important: Spring Transaction Proxy

Consider:

```java
@Service
public class EmployeeService {

    public void process() {
        updateEmployee();
    }

    @Transactional
    public void updateEmployee() {
        // Database operations
    }
}
```

The transaction may not be applied when updateEmployee is called from process within the same object.

Why?

Spring's default proxy-based transaction management intercepts external calls through the proxy. Self-invocation bypasses that proxy.

Solutions:

- Move the transactional method into another Spring bean.
- Call through an appropriate proxy.
- Redesign the transaction boundary.

---

# 22. Locking and Concurrency

When multiple users update the same record simultaneously, data consistency becomes important.

Two major locking approaches:

1. Optimistic Locking
2. Pessimistic Locking

## 22.1 Optimistic Locking

Uses a version field.

```java
@Entity
public class Employee {

    @Id
    private Long id;

    private String name;

    private Double salary;

    @Version
    private Long version;
}
```

Database:

| id | name | salary | version |
|---|---|---:|---:|
| 101 | Ravi | 80000 | 0 |

Suppose two transactions read the same employee.

Transaction A:

```text
salary = 80000
version = 0
```

Transaction B:

```text
salary = 80000
version = 0
```

Transaction A updates salary to 90000.

SQL:

```sql
UPDATE employees
SET salary = 90000,
    version = 1
WHERE id = 101
AND version = 0;
```

One row is updated.

Transaction B attempts to update using version 0.

```sql
UPDATE employees
SET salary = 95000,
    version = 1
WHERE id = 101
AND version = 0;
```

Zero rows are updated.

Hibernate detects the version mismatch and raises an optimistic locking failure.

**Why use it?**

For applications where conflicts are relatively uncommon and long-held database locks should be avoided.

## 22.2 Pessimistic Locking

Locks the database record while the transaction is working with it.

Repository:

```java
@Lock(LockModeType.PESSIMISTIC_WRITE)
@Query("SELECT e FROM Employee e WHERE e.id = :id")
Optional<Employee> findByIdForUpdate(
    @Param("id") Long id
);
```

Database may execute:

```sql
SELECT *
FROM employees
WHERE id = 101
FOR UPDATE;
```

Other transactions attempting conflicting locks may need to wait.

Use it when concurrent modification conflicts must be controlled more strictly.

### Comparison

| Optimistic | Pessimistic |
|---|---|
| Uses version checking | Uses database locks |
| No long-held write lock during normal reads | Can hold locks |
| Detects conflicting updates | Restricts conflicting operations |
| Good for low-contention systems | Useful for high-contention critical records |

---

# 23. Auditing

Auditing tracks who created or modified a record and when.

Spring Data JPA provides auditing support.

## 23.1 Enable Auditing

```java
@Configuration
@EnableJpaAuditing
public class JpaConfig {
}
```

## 23.2 Auditable Entity

```java
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    @CreatedBy
    private String createdBy;

    @LastModifiedBy
    private String updatedBy;
}
```

For @CreatedBy and @LastModifiedBy, configure AuditorAware:

```java
@Bean
public AuditorAware<String> auditorProvider() {

    return () -> Optional.of("system-user");

}
```

In real applications, retrieve the authenticated username from the security context.

### Database Example

| id | name | created_at | updated_at | created_by |
|---|---|---|---|---|
| 101 | Ravi | 2026-11-18 | 2026-11-18 | admin |

---

# 24. Advanced Hibernate Concepts

## 24.1 EntityManager vs Session

EntityManager is the standard JPA interface.

Session is Hibernate's native API.

```java
EntityManager entityManager;
```

Hibernate Session:

```java
Session session =
    entityManager.unwrap(Session.class);
```

| EntityManager | Session |
|---|---|
| JPA standard | Hibernate-specific |
| Provider independent | Provider dependent |
| Portable | Provides Hibernate-specific features |

## 24.2 JPQL vs HQL

JPQL is the standard query language defined by JPA.

HQL is Hibernate Query Language.

Hibernate HQL extends JPQL with Hibernate-specific capabilities.

For portable applications, prefer JPQL.

## 24.3 @DynamicUpdate

Hibernate normally generates UPDATE statements based on its SQL generation strategy and mapping.

```java
@Entity
@org.hibernate.annotations.DynamicUpdate
public class Employee {
}
```

This tells Hibernate to generate UPDATE statements containing only modified columns.

Example:

```sql
UPDATE employees
SET salary = 90000
WHERE id = 101;
```

Instead of updating all mapped columns.

Trade-off: Dynamic SQL can increase the number of distinct SQL statement shapes, potentially reducing statement-cache reuse.

Use only when beneficial.

## 24.4 @Formula

Hibernate-specific annotation that maps a calculated SQL expression to a read-only entity property.

```java
@Formula("salary * 12")
private Double annualSalary;
```

Hibernate retrieves this calculated value using the SQL expression.

It is not a normal writable database column.

## 24.5 @Where vs @SQLRestriction

Hibernate supports SQL restrictions on entity mappings.

In Hibernate 6, `@SQLRestriction` can define a static SQL restriction.

Example:

```java
@Entity
@SQLRestriction("status = 'ACTIVE'")
public class Employee {
}
```

This can restrict records loaded through that entity mapping.

For dynamic and parameterized filtering, Hibernate also provides other mechanisms such as filters.

## 24.6 Soft Delete

Soft delete means marking a record as deleted instead of physically deleting it.

Example database:

| id | name | deleted |
|---|---|---|
| 101 | Ravi | false |
| 102 | Kiran | true |

Entity:

```java
@Entity
public class Employee {

    @Id
    private Long id;

    private String name;

    private boolean deleted;
}
```

Instead of:

```sql
DELETE FROM employees WHERE id = 101;
```

Execute:

```sql
UPDATE employees
SET deleted = true
WHERE id = 101;
```

Hibernate 6.4+ also provides native `@SoftDelete` support. Check the Hibernate version and migration requirements before using it.

## 24.7 Inheritance Mapping

JPA supports three main inheritance strategies.

### SINGLE_TABLE

All classes are stored in one table.

```java
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Employee {
}
```

Example:

| id | name | employee_type | hourly_rate | monthly_salary |
|---|---|---|---:|---:|
| 1 | Ravi | PERMANENT | NULL | 80000 |
| 2 | Kiran | CONTRACT | 500 | NULL |

Advantages:
- Simple mapping.
- Fewer joins.
- Efficient polymorphic queries.

Disadvantages:
- Many nullable columns.
- Database constraints can be harder.

### JOINED

Each class has a separate table.

```java
@Inheritance(strategy = InheritanceType.JOINED)
```

Tables:

```text
employees
permanent_employees
contract_employees
```

Advantages:
- Normalized structure.
- Fewer unnecessary nullable columns.

Disadvantages:
- Joins required to retrieve subclass data.

### TABLE_PER_CLASS

Each concrete class has its own complete table.

```java
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
```

Advantages:
- Independent subclass tables.

Disadvantages:
- Polymorphic queries can require UNION operations.
- Shared data is duplicated.

### Comparison

| Strategy | Tables | Main Advantage | Main Disadvantage |
|---|---|---|---|
| SINGLE_TABLE | One | Fast reads | Nullable columns |
| JOINED | Multiple | Normalized | Joins |
| TABLE_PER_CLASS | One per concrete class | Independent tables | Data duplication and UNION |

## 24.8 Composite Primary Key

Sometimes a table uses multiple columns as its primary key.

Example:

```sql
CREATE TABLE employee_projects (
    employee_id BIGINT,
    project_id BIGINT,
    PRIMARY KEY(employee_id, project_id)
);
```

Using @EmbeddedId:

```java
@Embeddable
public class EmployeeProjectId
        implements Serializable {

    private Long employeeId;

    private Long projectId;

    // equals() and hashCode()
}
```

Entity:

```java
@Entity
public class EmployeeProject {

    @EmbeddedId
    private EmployeeProjectId id;

    private LocalDate assignedDate;
}
```

Another option is @IdClass.

For composite identifiers, correctly implement equals() and hashCode().

---

# 25. Performance Optimization

This section is especially important for 5+ years of experience.

## 25.1 Avoid N+1 Queries

Use:

- JOIN FETCH
- EntityGraph
- Batch fetching
- DTO projections

## 25.2 Use Pagination

Avoid loading millions of records into memory.

```java
PageRequest.of(0, 50);
```

For very large datasets, evaluate keyset pagination.

## 25.3 Use Batch Inserts

Hibernate batch configuration:

```properties
spring.jpa.properties.hibernate.jdbc.batch_size=50

spring.jpa.properties.hibernate.order_inserts=true

spring.jpa.properties.hibernate.order_updates=true
```

Example:

```java
@Transactional
public void saveEmployees(List<Employee> employees) {

    int batchSize = 50;

    for (int i = 0; i < employees.size(); i++) {

        entityManager.persist(employees.get(i));

        if ((i + 1) % batchSize == 0) {
            entityManager.flush();
            entityManager.clear();
        }
    }

}
```

Why flush and clear?

- Flush sends pending INSERT statements to the database.
- Clear detaches managed entities.
- Prevents the Persistence Context from growing excessively.

Important:

With IDENTITY generation, Hibernate may need to execute inserts individually to obtain generated IDs, which can limit JDBC insert batching.

## 25.4 Avoid Unnecessary EAGER Fetching

EAGER associations can cause unnecessary queries and large result sets.

Prefer LAZY and explicit fetching.

## 25.5 Use DTO Projections

Load only the required columns.

## 25.6 Avoid Large Persistence Contexts

For batch jobs:

```java
entityManager.flush();
entityManager.clear();
```

## 25.7 Use Appropriate Indexes

Example:

```sql
CREATE INDEX idx_employee_department
ON employees(department_id);
```

Useful for queries filtering or joining through department_id.

## 25.8 Avoid Unnecessary save() Calls

Within a transaction:

```java
@Transactional
public void updateEmployee(Long id) {

    Employee employee =
        employeeRepository.findById(id)
            .orElseThrow();

    employee.setSalary(100000.0);

}
```

Dirty checking handles the update.

## 25.9 Monitor Generated SQL

```properties
spring.jpa.show-sql=true

logging.level.org.hibernate.SQL=DEBUG

logging.level.org.hibernate.orm.jdbc.bind=TRACE
```

Use SQL logging carefully in production because parameter values may contain sensitive information and excessive logging can affect performance.

---

# 26. Real-World Implementation

Let's combine the concepts into a simple REST API.

## 26.1 Entity

```java
@Entity
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(unique = true)
    private String email;

    private Double salary;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    private Department department;

    @Version
    private Long version;

    // Getters and setters
}
```

## 26.2 Repository

```java
public interface EmployeeRepository
        extends JpaRepository<Employee, Long> {

    Optional<Employee> findByEmail(String email);

    List<Employee> findBySalaryGreaterThan(Double salary);

    @EntityGraph(attributePaths = "department")
    Optional<Employee> findWithDepartmentById(Long id);

}
```

## 26.3 DTO

```java
public record EmployeeRequest(
    String name,
    String email,
    Double salary,
    Long departmentId
) {}
```

Response:

```java
public record EmployeeResponse(
    Long id,
    String name,
    String email,
    Double salary,
    String departmentName
) {}
```

## 26.4 Service

```java
@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    private final DepartmentRepository departmentRepository;

    public EmployeeService(
        EmployeeRepository employeeRepository,
        DepartmentRepository departmentRepository
    ) {
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
    }

    @Transactional
    public EmployeeResponse create(EmployeeRequest request) {

        Department department =
            departmentRepository.findById(request.departmentId())
                .orElseThrow(() ->
                    new RuntimeException("Department not found")
                );

        Employee employee = new Employee();

        employee.setName(request.name());
        employee.setEmail(request.email());
        employee.setSalary(request.salary());
        employee.setDepartment(department);

        Employee saved = employeeRepository.save(employee);

        return new EmployeeResponse(
            saved.getId(),
            saved.getName(),
            saved.getEmail(),
            saved.getSalary(),
            department.getName()
        );
    }

    @Transactional(readOnly = true)
    public EmployeeResponse getById(Long id) {

        Employee employee =
            employeeRepository.findWithDepartmentById(id)
                .orElseThrow(() ->
                    new RuntimeException("Employee not found")
                );

        return new EmployeeResponse(
            employee.getId(),
            employee.getName(),
            employee.getEmail(),
            employee.getSalary(),
            employee.getDepartment().getName()
        );
    }

    @Transactional
    public void updateSalary(Long id, Double salary) {

        Employee employee =
            employeeRepository.findById(id)
                .orElseThrow();

        employee.setSalary(salary);

        // Dirty checking automatically updates the entity.
    }

    @Transactional
    public void delete(Long id) {

        Employee employee =
            employeeRepository.findById(id)
                .orElseThrow();

        employeeRepository.delete(employee);
    }
}
```

## 26.5 Controller

```java
@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping
    public EmployeeResponse create(
        @RequestBody EmployeeRequest request
    ) {
        return employeeService.create(request);
    }

    @GetMapping("/{id}")
    public EmployeeResponse getById(
        @PathVariable Long id
    ) {
        return employeeService.getById(id);
    }

    @PutMapping("/{id}/salary")
    public void updateSalary(
        @PathVariable Long id,
        @RequestParam Double salary
    ) {
        employeeService.updateSalary(id, salary);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        employeeService.delete(id);
    }
}
```

### API Examples

Create:

```http
POST /api/employees
Content-Type: application/json
```

```json
{
    "name": "Ravi",
    "email": "ravi@gmail.com",
    "salary": 80000,
    "departmentId": 1
}
```

Get:

```http
GET /api/employees/101
```

Update:

```http
PUT /api/employees/101/salary?salary=100000
```

Delete:

```http
DELETE /api/employees/101
```

### Important Production Improvements

For a real production API:

- Use Bean Validation on request DTOs.
- Use appropriate HTTP response statuses.
- Add centralized exception handling.
- Avoid exposing entities directly from controllers.
- Use BigDecimal for financial amounts instead of Double.
- Add database constraints and indexes.
- Use Flyway or Liquibase.
- Validate ownership and authorization.
- Use appropriate transaction boundaries.

---

# 27. Testing JPA Applications

## 27.1 @DataJpaTest

Used for testing repository and JPA functionality.

```java
@DataJpaTest
class EmployeeRepositoryTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    void shouldSaveEmployee() {

        Employee employee = new Employee();

        employee.setName("Ravi");
        employee.setEmail("ravi@gmail.com");
        employee.setSalary(80000.0);

        Employee saved =
            employeeRepository.save(employee);

        assertNotNull(saved.getId());

    }
}
```

By default, @DataJpaTest configures a focused JPA test slice and uses an embedded database when available.

For MySQL-specific behavior, use Testcontainers with MySQL.

## 27.2 @SpringBootTest

Loads the full Spring application context.

```java
@SpringBootTest
class EmployeeServiceTest {
}
```

Use it for integration tests involving multiple application layers.

### Comparison

| @DataJpaTest | @SpringBootTest |
|---|---|
| Focused JPA test | Full application test |
| Repository and persistence layer | All configured Spring beans |
| Faster in many cases | Usually heavier |
| Useful for query testing | Useful for integration testing |

---

# 28. Common Exceptions

| Exception | Reason | Typical Solution |
|---|---|---|
| LazyInitializationException | Accessing an unloaded association outside an open Persistence Context | Fetch inside transaction or use DTO |
| EntityNotFoundException | Referenced entity does not exist | Validate entity reference |
| NonUniqueResultException | Query expected one result but returned multiple | Fix query or use collection |
| OptimisticLockException | Version conflict | Retry or handle conflict |
| DataIntegrityViolationException | Database constraint violation | Validate input and relationships |
| TransientPropertyValueException | Entity references an unsaved transient entity | Persist referenced entity or configure correct cascade |
| PersistentObjectException | Incorrect attempt to persist an already-managed/detached entity | Use correct lifecycle operation |
| ConstraintViolationException | Database constraint violated | Correct data or mapping |

---

# 29. Interview Questions and Answers

## Basic Questions

### Q1. What is Hibernate?

Hibernate is an ORM framework that maps Java objects to relational database tables. It implements JPA and provides additional ORM capabilities such as caching, dirty checking and lazy loading.

### Q2. What is JPA?

JPA is a Java persistence specification that defines how Java objects are mapped to relational databases.

### Q3. Difference between Hibernate and JPA?

JPA defines the standard interfaces and behavior. Hibernate implements that specification and provides additional features.

### Q4. What is Spring Data JPA?

It is a Spring abstraction that simplifies JPA database operations through repository interfaces and generated implementations.

### Q5. What is ORM?

ORM maps Java objects to relational database tables and handles object-relational conversion.

### Q6. What is EntityManager?

EntityManager is the JPA interface used to manage entity lifecycle and persistence operations.

### Q7. What is Persistence Context?

It is the environment that manages entity instances, tracks changes and provides first-level caching.

### Q8. What is Dirty Checking?

Hibernate detects modifications to managed entities and generates UPDATE statements during flush.

### Q9. What is the difference between persist and merge?

persist makes a new entity managed. merge copies entity state into a managed instance and returns that managed instance.

### Q10. What is the difference between save and saveAndFlush?

save schedules persistence work. saveAndFlush also synchronizes pending changes with the database.

## Intermediate Questions

### Q11. What is the difference between LAZY and EAGER?

LAZY loads associated data when accessed. EAGER requires associated data to be available when the owning entity is loaded.

### Q12. What is N+1 query problem?

It happens when one query loads parent records and additional queries are executed to load associated records individually or in batches.

### Q13. How do you solve N+1?

Use JOIN FETCH, EntityGraph, batch fetching or DTO projections.

### Q14. What is CascadeType.ALL?

It propagates all JPA cascade operations: persist, merge, remove, refresh and detach.

### Q15. What is orphanRemoval?

It deletes a child entity when it is removed from its parent relationship.

### Q16. What is the difference between mappedBy and JoinColumn?

@JoinColumn identifies the foreign key column on the owning side. mappedBy identifies the owning Java property on the inverse side.

### Q17. What is the difference between JPQL and Native SQL?

JPQL works with entities and Java properties. Native SQL works directly with database tables and columns.

### Q18. What is the difference between first-level and second-level cache?

First-level cache belongs to the Persistence Context and is enabled by default. Second-level cache is optional and shared across sessions.

### Q19. What is the difference between flush and commit?

Flush synchronizes pending persistence changes with the database. Commit completes the transaction.

### Q20. What is @Transactional?

It defines a transactional boundary managed by Spring's transaction infrastructure.

## Advanced Questions

### Q21. What is optimistic locking?

It uses a version field to detect concurrent modifications and prevent silent overwriting of updates.

### Q22. What is pessimistic locking?

It uses database locks to control concurrent access to records.

### Q23. What is @Version?

It identifies the version property used for optimistic locking.

### Q24. What is EntityGraph?

It defines which associations should be fetched for a particular entity operation.

### Q25. What is @BatchSize?

It configures Hibernate batch fetching to load multiple associations together.

### Q26. What is the difference between Page and Slice?

Page includes total count information and may execute a count query. Slice focuses on whether another page exists without requiring a total count.

### Q27. What is the difference between Entity and DTO?

Entity represents persistent domain state. DTO transfers selected data between application layers or across API boundaries.

### Q28. What is the difference between @OneToMany and @ManyToOne?

@OneToMany represents one parent associated with many children. @ManyToOne represents many children associated with one parent.

### Q29. Why is @ManyToOne EAGER by default?

JPA defines EAGER as the default for ManyToOne. It ensures the related entity is available, but it can lead to unnecessary data retrieval. Explicit LAZY fetching is usually preferable.

### Q30. Why should we avoid EAGER fetching everywhere?

It can load unnecessary data, cause extra SQL queries, increase memory usage and create performance issues.

### Q31. What happens when we update an entity without calling save()?

If the entity is managed within an active transaction, Hibernate detects changes through dirty checking and updates the database during flush.

### Q32. Can we use Hibernate without JPA?

Yes. Hibernate provides its own native Session API and can be used without the JPA abstraction.

### Q33. Can we use JPA without Hibernate?

Yes. Other providers, such as EclipseLink, implement the JPA specification.

### Q34. What happens if we call entityManager.clear()?

All managed entities become detached. Pending changes must be flushed first if they need to be synchronized with the database.

### Q35. What is the difference between detach() and clear()?

detach() removes one entity from the Persistence Context. clear() detaches all managed entities.

### Q36. What is the difference between getReference() and find()?

find() retrieves the entity if it exists. getReference() may return a proxy and defer database access until the entity is accessed.

### Q37. What is the purpose of @Modifying?

It marks repository queries that execute modifying statements such as JPQL UPDATE and DELETE.

### Q38. Why should we not expose JPA entities directly from REST APIs?

It can expose internal persistence structure, cause lazy-loading issues, trigger recursive JSON serialization and create tight coupling between database and API contracts.

### Q39. What is the difference between IDENTITY and SEQUENCE?

IDENTITY uses database-generated auto-increment identifiers. SEQUENCE uses a database sequence and can support identifier allocation optimizations.

### Q40. How do you optimize a slow Hibernate application?

- Identify generated SQL.
- Analyze query execution plans.
- Fix N+1 queries.
- Add appropriate indexes.
- Use pagination.
- Use DTO projections.
- Configure batch operations.
- Avoid unnecessary entity loading.
- Review transaction boundaries.
- Monitor connection pool usage.

---

# 30. Important Interview Revision Points

| Concept | Must Remember |
|---|---|
| JPA | Specification |
| Hibernate | JPA implementation and ORM framework |
| Spring Data JPA | Repository abstraction |
| Entity | Java class mapped to table |
| EntityManager | Manages persistence |
| Persistence Context | First-level cache and entity tracking |
| Dirty Checking | Automatically detects changes |
| Flush | Synchronizes changes with DB |
| Commit | Completes transaction |
| LAZY | Loads association on demand |
| EAGER | Association must be fetched eagerly |
| N+1 | Excessive additional association queries |
| JOIN FETCH | Fetches association with join |
| EntityGraph | Defines fetch plan |
| Cascade | Propagates persistence operations |
| orphanRemoval | Deletes dissociated child |
| JPQL | Queries entities |
| Native SQL | Queries database tables |
| @Transactional | Defines transaction boundary |
| @Version | Optimistic locking |
| @Lock | Configures locking behavior |
| Projection | Retrieves selected data |
| Pagination | Retrieves data in manageable chunks |
| Batch Processing | Groups database operations |

---

# Recommended Learning Order

For strong conceptual understanding and product-company interviews, follow this order:

## Level 1 — Fundamentals

- JDBC vs ORM vs JPA vs Hibernate
- Spring Data JPA architecture
- Entity and table mapping
- JPA annotations
- Primary key strategies

## Level 2 — Relationships

- One-to-One
- One-to-Many
- Many-to-One
- Many-to-Many
- Owning side
- mappedBy
- JoinColumn
- JoinTable

## Level 3 — Hibernate Internals

- Persistence Context
- Entity lifecycle
- persist vs merge
- Dirty Checking
- Flush vs Commit
- First-level cache
- Second-level cache

## Level 4 — Querying and Fetching

- Derived queries
- JPQL
- Native SQL
- LAZY vs EAGER
- JOIN FETCH
- EntityGraph
- N+1 problem
- DTO projections

## Level 5 — Transactions and Advanced Concepts

- @Transactional
- Propagation
- Isolation
- Rollback
- Optimistic locking
- Pessimistic locking
- Auditing
- Composite keys
- Inheritance mapping

## Level 6 — Performance and Production

- Batch inserts and updates
- Pagination
- Query optimization
- Indexing
- Connection pooling
- Persistence Context management
- Soft delete
- Flyway/Liquibase
- Integration testing

---

# Final Interview Preparation Checklist

- [ ] Explain the difference between JPA, Hibernate and Spring Data JPA.
- [ ] Explain the complete internal flow of repository.save().
- [ ] Explain Persistence Context with an example.
- [ ] Demonstrate all four entity lifecycle states.
- [ ] Explain persist() vs merge().
- [ ] Demonstrate Dirty Checking without save().
- [ ] Explain flush() vs commit().
- [ ] Implement all four entity relationships.
- [ ] Explain owning side and mappedBy.
- [ ] Explain LAZY vs EAGER with generated SQL.
- [ ] Demonstrate the N+1 query problem and fix it.
- [ ] Explain cascade types and orphan removal.
- [ ] Write derived queries, JPQL and native queries.
- [ ] Implement pagination and projections.
- [ ] Explain Spring transaction propagation and isolation.
- [ ] Implement optimistic locking with @Version.
- [ ] Explain first-level and second-level caching.
- [ ] Demonstrate batch inserts.
- [ ] Explain how to identify and optimize slow Hibernate queries.

**Target:** Be able to explain not only what each annotation does, but also what SQL it generates, how entity state changes, when database operations execute, and why a particular approach is suitable in a production application.