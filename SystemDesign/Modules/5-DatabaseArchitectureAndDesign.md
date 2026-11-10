# MODULE 5 — DATABASE ARCHITECTURE AND DESIGN

# NAVIGATION TABLE

## A. Relational Databases

| No. | Topic | Priority |
|---|---|---|
| 1 | RDBMS Internals | ⭐⭐⭐⭐⭐ |
| 2 | MySQL and PostgreSQL Architecture | ⭐⭐⭐⭐⭐ |
| 3 | Tables, Rows and Columns | ⭐⭐⭐ |
| 4 | Primary Keys and Foreign Keys | ⭐⭐⭐⭐ |
| 5 | Normalization: 1NF, 2NF, 3NF, BCNF | ⭐⭐⭐⭐ |
| 6 | Denormalization | ⭐⭐⭐⭐ |
| 7 | Indexes | ⭐⭐⭐⭐⭐ |
| 8 | B-Tree and B+ Tree | ⭐⭐⭐⭐⭐ |
| 9 | Composite Indexes | ⭐⭐⭐⭐⭐ |
| 10 | Clustered vs Non-Clustered Indexes | ⭐⭐⭐⭐⭐ |
| 11 | Covering Indexes | ⭐⭐⭐⭐ |
| 12 | Query Execution Plans | ⭐⭐⭐⭐⭐ |
| 13 | SQL Optimization | ⭐⭐⭐⭐⭐ |
| 14 | Transactions | ⭐⭐⭐⭐⭐ |
| 15 | Isolation Levels | ⭐⭐⭐⭐⭐ |
| 16 | Dirty, Non-Repeatable and Phantom Reads | ⭐⭐⭐⭐⭐ |
| 17 | MVCC | ⭐⭐⭐⭐⭐ |
| 18 | Database Locks | ⭐⭐⭐⭐⭐ |
| 19 | Optimistic vs Pessimistic Locking | ⭐⭐⭐⭐⭐ |
| 20 | Deadlocks | ⭐⭐⭐⭐⭐ |
| 21 | Connection Pooling | ⭐⭐⭐⭐⭐ |
| 22 | Database Partitioning | ⭐⭐⭐⭐ |
| 23 | Read Replicas | ⭐⭐⭐⭐⭐ |
| 24 | Database Replication | ⭐⭐⭐⭐⭐ |
| 25 | Database Failover | ⭐⭐⭐⭐⭐ |

## B. NoSQL Databases

| No. | Topic | Priority |
|---|---|---|
| 1 | Why NoSQL? | ⭐⭐⭐⭐⭐ |
| 2 | SQL vs NoSQL | ⭐⭐⭐⭐⭐ |
| 3 | Key-Value Databases | ⭐⭐⭐⭐ |
| 4 | Document Databases | ⭐⭐⭐⭐ |
| 5 | Wide-Column Databases | ⭐⭐⭐⭐ |
| 6 | Graph Databases | ⭐⭐⭐ |
| 7 | MongoDB Architecture | ⭐⭐⭐⭐⭐ |
| 8 | Cassandra Architecture | ⭐⭐⭐⭐ |
| 9 | DynamoDB Architecture | ⭐⭐⭐⭐ |
| 10 | Google Cloud Spanner | ⭐⭐⭐⭐ |
| 11 | Redis Architecture | ⭐⭐⭐⭐⭐ |
| 12 | Data Modelling in NoSQL | ⭐⭐⭐⭐⭐ |
| 13 | Eventual Consistency | ⭐⭐⭐⭐⭐ |
| 14 | Partition Keys | ⭐⭐⭐⭐ |
| 15 | Hot Partitions | ⭐⭐⭐⭐ |
| 16 | Secondary Indexes | ⭐⭐⭐⭐ |
| 17 | Quorum Reads and Writes | ⭐⭐⭐⭐ |

## C. Database Scaling

| No. | Topic | Priority |
|---|---|---|
| 1 | Vertical Scaling | ⭐⭐⭐⭐ |
| 2 | Horizontal Scaling | ⭐⭐⭐⭐⭐ |
| 3 | Read Replicas | ⭐⭐⭐⭐⭐ |
| 4 | Sharding | ⭐⭐⭐⭐⭐ |
| 5 | Horizontal Partitioning | ⭐⭐⭐⭐ |
| 6 | Vertical Partitioning | ⭐⭐⭐⭐ |
| 7 | Consistent Hashing | ⭐⭐⭐⭐ |
| 8 | Database Routing | ⭐⭐⭐⭐⭐ |
| 9 | Multi-Region Databases | ⭐⭐⭐⭐⭐ |
| 10 | Replication Lag | ⭐⭐⭐⭐⭐ |
| 11 | Distributed Transactions | ⭐⭐⭐⭐⭐ |
| 12 | Database Migration Strategies | ⭐⭐⭐⭐ |

---

# SECTION A — RELATIONAL DATABASES

# LEVEL 1 — RDBMS Fundamentals

## 1. RDBMS Internals

### What is an RDBMS?

RDBMS stands for Relational Database Management System.

It stores data in tables consisting of rows and columns. Relationships between tables are maintained using keys and constraints.

Examples:
- MySQL
- PostgreSQL
- Oracle Database
- Microsoft SQL Server

### How does an RDBMS work internally?

When an application executes an SQL query, the database processes it through multiple internal components.

```text
         Spring Boot Application
                  |
                  v
             SQL Query
                  |
                  v
          Query Parser
                  |
                  v
       Query Analyzer / Validator
                  |
                  v
          Query Optimizer
                  |
                  v
          Execution Engine
                  |
          +-------+-------+
          |               |
          v               v
       Buffer Pool    Storage Engine
          |               |
          +-------+-------+
                  |
                  v
              Disk Storage
```

### Important internal components

**1. Query Parser**

- Receives SQL queries.
- Checks SQL syntax.
- Converts the query into an internal representation.

Example:

```sql
SELECT * FROM employees WHERE id = 101;
```

The parser checks whether the SQL statement is valid.

**2. Query Analyzer**

- Checks whether tables and columns exist.
- Validates permissions and data types.
- Resolves references.

**3. Query Optimizer**

- Determines an efficient execution strategy.
- Chooses indexes.
- Decides join order and access methods.
- Estimates the cost of different execution plans.

**4. Execution Engine**

Executes the selected query plan and coordinates data retrieval and modifications.

**5. Buffer Pool / Shared Buffers**

Caches database pages in memory.

Instead of reading every page from disk, the database can reuse pages already in memory.

**6. Storage Engine**

Manages how data is physically stored, accessed and modified.

For example, MySQL commonly uses InnoDB.

**7. Transaction and Recovery Components**

Manage transactions, logs, concurrency and crash recovery.

### Example: Employee lookup

```sql
SELECT *
FROM employees
WHERE employee_id = 101;
```

Internally:

1. Application sends the SQL query.
2. Database parses and validates it.
3. Optimizer checks available indexes.
4. Execution engine selects an access method.
5. Database reads the required pages from memory or disk.
6. Matching row is returned.

### Interview question

**What happens internally when you execute a SELECT query?**

Explain the parser, optimizer, execution plan, buffer cache, storage engine and result retrieval.

---

## 2. MySQL and PostgreSQL Architecture

Both MySQL and PostgreSQL are relational database systems, but their internal implementations differ.

### MySQL Architecture

MySQL has a server layer and a pluggable storage engine architecture.

```text
           Spring Boot Application
                    |
                    v
                MySQL Server
                    |
          +---------+---------+
          |         |         |
          v         v         v
        Parser   Optimizer  SQL Executor
          |         |         |
          +---------+---------+
                    |
                    v
             Storage Engine API
                    |
                    v
                 InnoDB
                    |
        +-----------+-----------+
        |           |           |
        v           v           v
    Buffer Pool  Redo Log   Undo Log
        |                       |
        v                       v
     Data Pages              MVCC
```

**Important InnoDB components**

- Buffer Pool: caches frequently accessed database pages.
- Redo Log: supports recovery of committed changes after a crash.
- Undo Log: stores previous versions of data for rollback and consistent reads.
- B+ Tree Indexes: used for primary and secondary indexes.
- Doublewrite Buffer: helps protect against partial page writes.

MySQL also supports other storage engines, but InnoDB is the common choice for transactional applications.

### PostgreSQL Architecture

PostgreSQL uses a process-based architecture.

```text
             Client Applications
                     |
                     v
              PostgreSQL Server
                     |
               Postmaster
                     |
          +----------+----------+
          |          |          |
          v          v          v
       Backend 1  Backend 2  Backend 3
          |          |          |
          +----------+----------+
                     |
          +----------+----------+
          |          |          |
          v          v          v
      Shared      WAL       Shared Buffers
      Buffers     Logs
          |
          v
       Data Files
```

Important components:

- Postmaster: main server process that manages connections and starts backend processes.
- Backend process: handles an individual client connection.
- Shared Buffers: caches database pages.
- WAL (Write-Ahead Logging): records changes for crash recovery and replication.
- Background Writer: writes dirty shared-buffer pages to disk.
- Checkpointer: manages checkpoints.
- Autovacuum: reclaims reusable space, updates statistics and helps manage dead tuples.

### MySQL vs PostgreSQL

| Feature | MySQL | PostgreSQL |
|---|---|---|
| Architecture | Server with pluggable storage engines | Process-based architecture |
| Common storage | InnoDB | PostgreSQL storage engine |
| Indexes | B+ Tree and other supported types | B-Tree, Hash, GiST, GIN, BRIN and others |
| MVCC | Supported by InnoDB | Core database feature |
| JSON | JSON data type | JSON and JSONB |
| Extensions | Plugin ecosystem | Rich extension ecosystem |
| Replication | Binary log based | WAL based |
| Common use | Web applications and transactional systems | Complex queries and advanced data modelling |

### When to choose?

Choose MySQL when:
- You need a widely used relational database.
- Your application has conventional transactional workloads.
- Your team already has strong MySQL operational experience.

Choose PostgreSQL when:
- You need advanced SQL capabilities.
- You need complex queries and data types.
- You need powerful indexing options or extensions.

Both are excellent production choices. Workload, operational expertise and ecosystem matter more than generic claims that one is always faster.

---

## 3. Tables, Rows and Columns

An RDBMS stores data in tables.

Example:

**employees**

| employee_id | name | department | salary |
|---|---|---|---:|
| 101 | Ravi | IT | 80000 |
| 102 | Kumar | HR | 60000 |
| 103 | Anil | IT | 90000 |

- Table: employees
- Row: one employee record.
- Column: employee_id, name, department or salary.

### SQL example

```sql
CREATE TABLE employees (
    employee_id BIGINT PRIMARY KEY,
    name VARCHAR(100),
    department VARCHAR(50),
    salary DECIMAL(10,2)
);
```

Insert:

```sql
INSERT INTO employees
VALUES (101, 'Ravi', 'IT', 80000);
```

Read:

```sql
SELECT *
FROM employees;
```

### Internal architecture

Rows are stored in database pages or blocks. Pages are the basic units of data transfer between memory and storage.

The database does not necessarily store every row as a separate disk file.

---

## 4. Primary Keys and Foreign Keys

### Primary Key

A primary key uniquely identifies every row in a table.

Example:

```sql
CREATE TABLE customers (
    customer_id BIGINT PRIMARY KEY,
    name VARCHAR(100)
);
```

Properties:
- Unique.
- Cannot be NULL.
- One primary key constraint per table.
- Can contain multiple columns (composite primary key).

### Foreign Key

A foreign key creates a relationship between two tables.

Example:

```sql
CREATE TABLE orders (
    order_id BIGINT PRIMARY KEY,
    customer_id BIGINT,
    amount DECIMAL(10,2),

    FOREIGN KEY (customer_id)
    REFERENCES customers(customer_id)
);
```

Relationship:

```text
Customers
---------
customer_id (PK)
name

       |
       | 1:N
       |
       v

Orders
------
order_id (PK)
customer_id (FK)
amount
```

One customer can have multiple orders.

### Why are foreign keys important?

- Maintain referential integrity.
- Prevent references to non-existing parent records.
- Support controlled deletion and update behavior.

### ON DELETE options

```sql
FOREIGN KEY (customer_id)
REFERENCES customers(customer_id)
ON DELETE CASCADE;
```

CASCADE deletes related child rows when the parent row is deleted.

Other options include RESTRICT, NO ACTION and SET NULL, depending on database behavior.

### Interview point

A primary key identifies a record, whereas a foreign key maintains a relationship and referential integrity between tables.

---

# LEVEL 2 — Database Normalization

## 5. Normalization: 1NF, 2NF, 3NF and BCNF

Normalization organizes relational data to reduce duplication and avoid data anomalies.

### Why normalization?

Consider this table:

| order_id | customer_name | customer_phone | product |
|---|---|---|---|
| 1 | Ravi | 9999 | Laptop |
| 2 | Ravi | 9999 | Mouse |
| 3 | Kumar | 8888 | Keyboard |

Customer information is repeated.

Problems:
- Update anomaly.
- Insert anomaly.
- Delete anomaly.

Normalization separates related data into appropriate tables.

### First Normal Form (1NF)

Rules:
- Each column contains atomic values.
- No repeating groups.
- Each row is identifiable.

Incorrect:

| customer_id | phone_numbers |
|---|---|
| 1 | 9999, 8888 |

Correct:

| customer_id | phone_number |
|---|---|
| 1 | 9999 |
| 1 | 8888 |

### Second Normal Form (2NF)

Rules:
- Must satisfy 1NF.
- Every non-key attribute must depend on the whole candidate key, not just part of a composite candidate key.

Example:

```text
OrderItems
----------
order_id
product_id
product_name
quantity
```

Assume the composite primary key is:

```text
(order_id, product_id)
```

But `product_name` depends only on `product_id`, not on the full composite key.

Separate product information:

```text
Products
--------
product_id
product_name

OrderItems
----------
order_id
product_id
quantity
```

### Third Normal Form (3NF)

Rules:
- Must satisfy 2NF.
- No transitive dependency of non-key attributes on candidate keys.

Incorrect:

```text
Employees
---------
employee_id
department_id
department_name
```

Here:

```text
employee_id -> department_id
department_id -> department_name
```

Correct:

```text
Employees
---------
employee_id
department_id

Departments
-----------
department_id
department_name
```

### BCNF (Boyce-Codd Normal Form)

A relation is in BCNF when every determinant is a superkey.

BCNF is stricter than 3NF.

Consider:

```text
StudentCourseInstructor
-----------------------
student
course
instructor
```

Assume:
- A student can take several courses.
- Each instructor teaches only one course.
- Each student-course combination has one instructor.

Dependencies:

```text
(student, course) -> instructor
instructor -> course
```

The determinant `instructor` is not a superkey, so the relation violates BCNF.

It can be decomposed into:

```text
InstructorCourse
----------------
instructor
course

StudentInstructor
-----------------
student
instructor
```

The exact decomposition depends on the functional dependencies and whether a lossless join is preserved.

### Normalization comparison

| Normal Form | Main Rule |
|---|---|
| 1NF | Atomic values |
| 2NF | No partial dependency on a composite candidate key |
| 3NF | No transitive dependency of non-key attributes |
| BCNF | Every determinant must be a superkey |

### When to use normalization?

Use normalized designs for:
- Transactional systems.
- Banking.
- Order management.
- Inventory management.
- Systems with frequent updates.

---

## 6. Denormalization

Denormalization intentionally introduces duplicated or precomputed data to improve read performance.

Example:

Normalized design:

```text
Customers
---------
customer_id
name

Orders
------
order_id
customer_id
amount
```

To retrieve orders with customer names, you need a JOIN.

```sql
SELECT
    o.order_id,
    c.name,
    o.amount
FROM orders o
JOIN customers c
ON o.customer_id = c.customer_id;
```

Denormalized design:

```text
Orders
------
order_id
customer_id
customer_name
amount
```

Now a common read query may avoid the JOIN.

### Advantages

- Faster reads for specific access patterns.
- Fewer joins.
- Useful for reporting and analytical workloads.

### Disadvantages

- Data duplication.
- More complicated updates.
- Risk of inconsistent copies.
- Additional storage.

### Real-world example

An e-commerce order may store the customer's shipping address as a snapshot.

Why?

If the customer changes their address later, historical orders should retain the address used when the order was placed.

### Interview point

Normalize to maintain consistency and reduce duplication. Denormalize selectively when read performance or historical snapshots justify the additional complexity.

---

# LEVEL 3 — Indexing and Query Optimization

## 7. Indexes

An index is a data structure that helps a database find rows without scanning the entire table.

Imagine a table containing 10 million employees.

Without an index:

```sql
SELECT *
FROM employees
WHERE employee_id = 500001;
```

The database may perform a full table scan.

With an index on employee_id, it can locate the matching row much faster.

### Create an index

```sql
CREATE INDEX idx_employee_id
ON employees(employee_id);
```

### Without an index

```text
Table Scan

Row 1
Row 2
Row 3
...
Row 10,000,000
```

### With an index

```text
            Index
              |
              v
       Find employee_id
              |
              v
        Matching row
```

### Internal architecture

Indexes are separate data structures maintained by the database.

They generally contain:
- Indexed key values.
- References to rows or table locations.
- Tree or other indexing structures.

### Advantages

- Faster filtering.
- Faster joins.
- Faster sorting in suitable cases.
- Better range queries.

### Disadvantages

- Additional disk space.
- Slower INSERT operations.
- Slower UPDATE operations when indexed columns change.
- Slower DELETE operations due to index maintenance.

**Important:** More indexes do not always mean better performance.

---

## 8. B-Tree and B+ Tree

These are balanced tree data structures commonly used in database indexing.

### B-Tree

A B-Tree stores keys and associated data pointers in internal and leaf nodes.

```text
              [30 | 60]
             /    |    \
            /     |     \
       [10,20] [40,50] [70,80]
```

Properties:
- Balanced tree.
- Multiple keys per node.
- Search, insertion and deletion are efficient.
- All leaves are at the same depth.

### B+ Tree

A B+ Tree stores the actual indexed row references at leaf nodes. Internal nodes primarily contain separator keys.

Leaf nodes are linked to support efficient range scans.

```text
                 [30 | 60]
                /    |    \
               /     |     \
          [10,20] [30,40,50] [60,70,80]
              <------ linked leaves ------>
```

### Why B+ Trees are popular?

1. High fan-out reduces tree height.
2. Fewer disk page reads are generally required.
3. Leaf-level links support efficient range queries.
4. Internal nodes can contain more separator keys because they do not store full row data.

### Time complexity

| Operation | Typical complexity |
|---|---|
| Search | O(log N) |
| Insert | O(log N) |
| Delete | O(log N) |
| Range scan | O(log N + K) |

K represents the number of matching records returned.

### Interview question

**Why do databases use B+ Trees instead of ordinary binary search trees?**

Because database pages contain many keys, B+ Trees have high fan-out and remain shallow. Their linked leaf nodes also make range scans efficient.

---

## 9. Composite Indexes

A composite index contains multiple columns.

Example:

```sql
CREATE INDEX idx_dept_salary
ON employees(department, salary);
```

This index is ordered by department first, then salary within the department.

### Leftmost Prefix Rule

For an index:

```sql
(department, salary, city)
```

These queries can generally use its leftmost prefixes:

```sql
WHERE department = 'IT';

WHERE department = 'IT'
AND salary > 50000;

WHERE department = 'IT'
AND salary > 50000
AND city = 'Hyderabad';
```

This query generally cannot efficiently use the index for a lookup on city alone:

```sql
WHERE city = 'Hyderabad';
```

### Column order matters

Suppose:
- Department has 5 distinct values.
- Salary has 100,000 distinct values.

The best column order depends on query patterns, selectivity, sorting requirements and range conditions.

Do not blindly put the most selective column first. Match the index to the queries you actually execute.

### Example

```sql
SELECT *
FROM employees
WHERE department = 'IT'
AND salary > 70000;
```

A composite index on `(department, salary)` can help this query.

---

## 10. Clustered vs Non-Clustered Indexes

### Clustered Index

A clustered index determines how table rows are organized in the table's primary storage structure.

In InnoDB, the primary key is the clustered index.

```text
Clustered Index (Primary Key)
             |
             v
       Employee Data
       --------------
       101 Ravi
       102 Kumar
       103 Anil
```

The leaf pages of the clustered index contain the complete row data.

### Non-Clustered Index

A non-clustered index is a separate index structure that points to the corresponding table record.

In InnoDB, secondary index leaf entries contain the secondary index key and the primary key value.

```text
Secondary Index (department)
              |
              v
       department + PK
              |
              v
        Clustered Index
              |
              v
          Actual Row
```

### Comparison

| Feature | Clustered | Non-Clustered |
|---|---|---|
| Data organization | Organizes table storage | Separate index |
| Leaf nodes | Actual row data | Key and row locator |
| Number | One clustered organization | Multiple secondary indexes |
| Lookup | Can retrieve row directly | May require another lookup |
| Storage | Part of table organization | Additional storage |

### Database-specific note

MySQL InnoDB uses a clustered primary-key index. PostgreSQL has a different implementation: a table has a heap organization, and indexes point to heap tuples. PostgreSQL's `CLUSTER` command can physically reorder a table based on an index, but this is not continuously maintained as a clustered index.

---

## 11. Covering Indexes

A covering index contains all columns required by a particular query.

Example:

```sql
SELECT employee_id, name
FROM employees
WHERE department = 'IT';
```

Create:

```sql
CREATE INDEX idx_dept_employee_name
ON employees(department, employee_id, name);
```

The index contains:
- department
- employee_id
- name

The database may answer the query directly from the index without accessing the full table row.

### Benefits

- Reduces table lookups.
- Reduces I/O.
- Can improve read performance.

### Important

A covering index is query-dependent. An index that covers one query may not cover another.

In PostgreSQL, `INCLUDE` can add non-key columns to an index:

```sql
CREATE INDEX idx_dept_cover
ON employees(department)
INCLUDE (employee_id, name);
```

---

## 12. Query Execution Plans

An execution plan explains how a database intends to execute an SQL query.

It helps identify performance bottlenecks.

### Example

```sql
SELECT *
FROM employees
WHERE department = 'IT';
```

MySQL:

```sql
EXPLAIN
SELECT *
FROM employees
WHERE department = 'IT';
```

PostgreSQL:

```sql
EXPLAIN ANALYZE
SELECT *
FROM employees
WHERE department = 'IT';
```

### Common execution operations

| Operation | Meaning |
|---|---|
| Sequential Scan | Reads table rows sequentially |
| Index Scan | Uses an index to find rows |
| Index Only Scan | Retrieves required values from index when possible |
| Nested Loop Join | Executes an inner lookup for outer rows |
| Hash Join | Uses a hash table to join datasets |
| Merge Join | Joins sorted inputs |
| Sort | Sorts intermediate results |
| Aggregate | Performs grouping or aggregation |

### Example: Full Table Scan

```text
Seq Scan on employees
  Filter: department = 'IT'
```

The database scans the table and filters rows.

### Example: Index Scan

```text
Index Scan using idx_department
  Index Cond: department = 'IT'
```

The database uses an index to find matching rows.

### What should you look for?

- Estimated rows versus actual rows.
- Expensive sequential scans.
- High execution time.
- Expensive sorts.
- Large intermediate results.
- Incorrect row estimates.
- Repeated nested-loop operations.

**Important:** A sequential scan is not always bad. For small tables or queries returning a large percentage of rows, it may be cheaper than an index scan.

---

## 13. SQL Optimization

SQL optimization means improving query execution time, database resource consumption and throughput.

### Common techniques

**1. Avoid SELECT \***

Bad:

```sql
SELECT *
FROM employees;
```

Better:

```sql
SELECT employee_id, name
FROM employees;
```

Retrieve only required columns.

**2. Add suitable indexes**

```sql
CREATE INDEX idx_employee_department
ON employees(department);
```

**3. Avoid functions on indexed columns when they prevent index use**

Potentially inefficient:

```sql
SELECT *
FROM employees
WHERE YEAR(created_at) = 2026;
```

Better:

```sql
SELECT *
FROM employees
WHERE created_at >= '2026-01-01'
AND created_at < '2027-01-01';
```

**4. Use pagination**

```sql
SELECT *
FROM employees
ORDER BY employee_id
LIMIT 20 OFFSET 0;
```

For deep pagination, keyset pagination is often more efficient:

```sql
SELECT *
FROM employees
WHERE employee_id > 1000
ORDER BY employee_id
LIMIT 20;
```

**5. Avoid unnecessary N+1 queries**

Instead of querying each employee's department individually, use a suitable JOIN or batch-fetch strategy.

**6. Avoid unnecessary joins**

Only join the tables required for the result.

**7. Use connection pooling**

Reuse database connections instead of opening a new connection for every request.

**8. Batch writes where appropriate**

Reduce round trips for bulk insert or update operations.

### Spring Boot example

```java
@Repository
public interface EmployeeRepository
        extends JpaRepository<Employee, Long> {

    List<Employee> findByDepartment(String department);

}
```

Add the database index matching the access pattern:

```sql
CREATE INDEX idx_employee_department
ON employees(department);
```

Always validate improvements using actual execution plans and performance measurements.

---

# LEVEL 4 — Transactions and Concurrency

## 14. Transactions

A transaction is a logical unit of work that must satisfy defined consistency rules.

Example: transferring ₹5,000 from Account A to Account B.

```text
Account A = 10,000
Account B = 20,000

Transfer 5,000

Account A = 5,000
Account B = 25,000
```

Two operations:

1. Debit ₹5,000 from Account A.
2. Credit ₹5,000 to Account B.

Both must succeed together.

### ACID properties

| Property | Meaning |
|---|---|
| Atomicity | All operations succeed or the transaction is rolled back |
| Consistency | Constraints and invariants remain valid |
| Isolation | Concurrent transactions are controlled |
| Durability | Committed data survives failures within the database's durability guarantees |

### SQL example

```sql
BEGIN;

UPDATE accounts
SET balance = balance - 5000
WHERE account_id = 1;

UPDATE accounts
SET balance = balance + 5000
WHERE account_id = 2;

COMMIT;
```

If something goes wrong:

```sql
ROLLBACK;
```

### Spring Boot example

```java
@Service
public class TransferService {

    private final AccountRepository repository;

    public TransferService(AccountRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public void transfer(Long from, Long to, BigDecimal amount) {

        Account source = repository.findById(from)
                .orElseThrow();

        Account destination = repository.findById(to)
                .orElseThrow();

        source.debit(amount);
        destination.credit(amount);

    }
}
```

Spring's transaction manager coordinates the transaction.

**Important:** `@Transactional` alone does not solve every concurrency problem. You still need suitable isolation, locking and consistency rules.

---

## 15. Isolation Levels

Isolation levels determine how concurrent transactions interact.

The standard SQL isolation levels are:

1. Read Uncommitted
2. Read Committed
3. Repeatable Read
4. Serializable

### Read Uncommitted

A transaction may read data modified by another uncommitted transaction.

This permits dirty reads.

### Read Committed

A transaction reads only committed data.

A repeated query may return different values if another transaction commits an update in between.

### Repeatable Read

Repeated reads of the same row are protected against non-repeatable changes within the transaction.

Phantom behavior depends on the database implementation and isolation semantics.

### Serializable

Provides the strongest standard isolation level. Concurrent transactions behave as if executed serially, possibly through blocking or transaction retries.

### Comparison

| Isolation | Dirty Read | Non-Repeatable Read | Phantom Read |
|---|---|---|---|
| Read Uncommitted | Possible | Possible | Possible |
| Read Committed | Prevented | Possible | Possible |
| Repeatable Read | Prevented | Prevented | Standard definition allows it; implementations differ |
| Serializable | Prevented | Prevented | Prevented |

### Database-specific note

MySQL InnoDB commonly defaults to Repeatable Read and uses MVCC and next-key locking for relevant locking operations.

PostgreSQL commonly defaults to Read Committed. Its Repeatable Read implementation uses snapshot isolation and prevents ordinary phantom reads, although serialization anomalies can still exist. Serializable isolation uses Serializable Snapshot Isolation and may abort transactions that need to be retried.

---

## 16. Dirty Reads, Non-Repeatable Reads and Phantom Reads

### A. Dirty Read

Transaction A updates a record but has not committed.

Transaction B reads the uncommitted value.

```text
Transaction A:
UPDATE balance = 5000
(Not committed)

Transaction B:
SELECT balance
Returns 5000

Transaction A:
ROLLBACK
```

Transaction B read data that was never committed.

This is a dirty read.

### B. Non-Repeatable Read

Transaction A reads a row twice.

Between the reads, transaction B updates and commits that row.

```text
Transaction A:
SELECT balance
Returns 10000

Transaction B:
UPDATE balance = 5000
COMMIT

Transaction A:
SELECT balance
Returns 5000
```

The same row returned different values within one transaction.

### C. Phantom Read

Transaction A executes a query returning multiple rows.

Transaction B inserts another row matching the same condition and commits.

```text
Transaction A:
SELECT * FROM employees
WHERE salary > 50000;

Returns 5 rows.

Transaction B:
INSERT employee with salary = 70000;
COMMIT;

Transaction A:
SELECT * FROM employees
WHERE salary > 50000;

May return 6 rows under an isolation level permitting phantoms.
```

The second query returns an additional matching row.

### Quick comparison

| Problem | What changes? |
|---|---|
| Dirty Read | Reads uncommitted data |
| Non-Repeatable Read | An existing row changes |
| Phantom Read | Matching rows are added or removed |

---

## 17. MVCC (Multi-Version Concurrency Control)

MVCC allows multiple transactions to access different versions of data without requiring every reader to block every writer.

### Why MVCC?

Without suitable concurrency control, readers and writers can block one another heavily.

MVCC allows a transaction to read a consistent version of data while another transaction modifies a newer version.

### Example

Initial data:

```text
Employee salary = 50,000
```

Transaction A starts reading.

Transaction B updates salary:

```text
Employee salary = 70,000
```

Transaction A may continue reading its earlier visible version depending on its snapshot and isolation level.

### Conceptual architecture

```text
             Employee Record
                    |
          +---------+---------+
          |                   |
          v                   v
     Old Version          New Version
      50,000                70,000
          |                   |
          v                   v
     Transaction A        Transaction B
     Reads old view       Updates value
```

This is a conceptual representation. Actual version storage differs between databases.

### MySQL InnoDB

- Uses undo records to reconstruct older row versions.
- Uses transaction identifiers and read views.
- Uses redo logs for recovery.

### PostgreSQL

- Stores multiple tuple versions in the table heap.
- Uses transaction visibility information.
- Uses VACUUM to reclaim space occupied by obsolete versions when safe.

### Advantages

- Better reader/writer concurrency.
- Consistent reads.
- Reduced reader blocking.

### Disadvantages

- Version storage overhead.
- Cleanup and vacuum requirements.
- Long-running transactions can prevent old versions from being reclaimed.

### Interview question

**How does MVCC improve database performance?**

It allows readers to access a consistent version of data without necessarily blocking writers, improving concurrency.

---

## 18. Database Locks

A lock controls concurrent access to database resources.

### Shared Lock

Allows multiple transactions to read a resource while preventing conflicting writes.

### Exclusive Lock

Allows a transaction to modify a resource while preventing conflicting access.

Example:

```sql
SELECT *
FROM accounts
WHERE account_id = 101
FOR UPDATE;
```

This requests a row-level lock for the selected record in databases that support this syntax.

### Common lock types

| Lock | Purpose |
|---|---|
| Shared | Read access |
| Exclusive | Write access |
| Row-level | Lock selected rows |
| Table-level | Lock a table or table resource |
| Intention | Indicates planned lower-level locking |
| Advisory | Application-controlled logical locking in supported databases |

### Example

Two transactions attempt to update the same bank account.

```text
Transaction A ----> Locks Account 101
Transaction B ----> Waits for Account 101
```

Once A commits or rolls back, B can proceed according to the database's lock behavior.

---

## 19. Optimistic vs Pessimistic Locking

### Optimistic Locking

Assumes conflicts are uncommon.

It allows concurrent reads and checks whether a record changed before committing an update.

Common implementation: version column.

Example:

```text
Account:
id = 101
balance = 10000
version = 1
```

Transaction A reads version 1.

Transaction B reads version 1.

Transaction A updates:

```sql
UPDATE accounts
SET balance = 9000,
    version = 2
WHERE id = 101
AND version = 1;
```

One row is updated.

Transaction B attempts the same operation using version 1.

```sql
UPDATE accounts
SET balance = 8000,
    version = 2
WHERE id = 101
AND version = 1;
```

Zero rows are updated because the version has changed.

Transaction B detects a conflict and must retry or return a conflict response.

### Spring Boot implementation

```java
@Entity
public class Account {

    @Id
    private Long id;

    private BigDecimal balance;

    @Version
    private Long version;

}
```

JPA/Hibernate can use the version field for optimistic locking.

### Pessimistic Locking

Locks the database row before modification.

```java
@Lock(LockModeType.PESSIMISTIC_WRITE)
@Query("SELECT a FROM Account a WHERE a.id = :id")
Optional<Account> findByIdForUpdate(Long id);
```

The database holds a lock until transaction completion.

### Comparison

| Feature | Optimistic | Pessimistic |
|---|---|---|
| Locking | Checks for conflicts | Acquires locks |
| Conflicts | Detected during update | Other operations may wait |
| Best for | Low-conflict workloads | High-conflict workloads |
| Performance | Good when conflicts are rare | Can reduce concurrency |
| Example | Profile updates | Critical inventory updates |

### When to use?

Optimistic:
- User profile updates.
- Product descriptions.
- Low-contention records.

Pessimistic:
- High-contention inventory.
- Financial operations requiring coordinated updates.
- Critical records where concurrent modification must be serialized.

---

## 20. Deadlocks

A deadlock occurs when two or more transactions wait indefinitely for resources held by one another.

### Example

Transaction A:

```sql
UPDATE accounts
SET balance = balance - 100
WHERE account_id = 1;
```

Transaction B:

```sql
UPDATE accounts
SET balance = balance - 200
WHERE account_id = 2;
```

Then:

- A tries to lock account 2.
- B tries to lock account 1.

```text
Transaction A              Transaction B
     |                           |
Locks Account 1             Locks Account 2
     |                           |
Waits for Account 2         Waits for Account 1
     |                           |
     +-------- DEADLOCK ---------+
```

Neither can proceed.

### How databases handle deadlocks

Databases detect deadlocks and abort one transaction so the other can continue.

### How to prevent deadlocks

1. Access records in a consistent order.
2. Keep transactions short.
3. Avoid unnecessary locks.
4. Add indexes to avoid unnecessarily broad locking.
5. Retry transactions that are aborted due to deadlocks.

### Interview question

**How do you handle deadlocks in Spring Boot?**

- Keep transactional methods small.
- Access resources in consistent order.
- Handle deadlock exceptions.
- Retry safely with bounded retries and backoff.
- Ensure operations are idempotent where necessary.

---

# LEVEL 5 — Connection Management and Database Availability

## 21. Connection Pooling

Opening a database connection for every HTTP request is expensive.

Connection pooling maintains reusable database connections.

### Without Connection Pooling

```text
Request 1 -> Open Connection -> Query -> Close
Request 2 -> Open Connection -> Query -> Close
Request 3 -> Open Connection -> Query -> Close
```

Creating connections repeatedly consumes time and resources.

### With Connection Pooling

```text
              Spring Boot
                  |
                  v
             HikariCP Pool
                  |
         +--------+--------+
         |        |        |
         v        v        v
      Conn 1   Conn 2   Conn 3
         |        |        |
         +--------+--------+
                  |
                  v
               Database
```

HikariCP is a common JDBC connection pool used by Spring Boot.

### Example configuration

```properties
spring.datasource.hikari.maximum-pool-size=20

spring.datasource.hikari.minimum-idle=5

spring.datasource.hikari.connection-timeout=30000

spring.datasource.hikari.idle-timeout=600000

spring.datasource.hikari.max-lifetime=1800000
```

### Important concepts

- Maximum pool size: maximum connections in the pool.
- Minimum idle: minimum number of idle connections to maintain.
- Connection timeout: maximum time to wait for a connection.
- Idle timeout: time before an idle connection may be removed.
- Max lifetime: maximum connection lifetime.

### How to size a pool?

Do not blindly increase it.

Consider:
- Database maximum connection capacity.
- Number of application instances.
- Average query duration.
- Transaction duration.
- CPU and I/O capacity.
- Peak concurrent database usage.

Example:

If 10 Spring Boot instances each have a maximum pool size of 20, they may create up to 200 database connections.

This must fit within the database's available connection capacity.

---

## 22. Database Partitioning

Partitioning divides a large table into smaller logical or physical partitions while keeping it accessible as one logical table.

Example:

An orders table contains 500 million rows.

Partition by order date:

```text
Orders
  |
  +---- 2024 Orders
  |
  +---- 2025 Orders
  |
  +---- 2026 Orders
```

### Range Partitioning

Rows are assigned based on a range.

Example:

```sql
CREATE TABLE orders (
    order_id BIGINT,
    order_date DATE,
    amount DECIMAL(10,2)
)
PARTITION BY RANGE (YEAR(order_date)) (
    PARTITION p2024 VALUES LESS THAN (2025),
    PARTITION p2025 VALUES LESS THAN (2026),
    PARTITION p2026 VALUES LESS THAN (2027)
);
```

Exact partitioning syntax varies by database and version.

### Common partitioning strategies

| Strategy | Description |
|---|---|
| Range | Based on a range of values |
| List | Based on predefined values |
| Hash | Based on a hash function |
| Composite | Combination of strategies |

### Partition pruning

If a query filters on the partition key, the database may scan only relevant partitions.

Example:

```sql
SELECT *
FROM orders
WHERE order_date >= '2026-01-01'
AND order_date < '2027-01-01';
```

The database can skip irrelevant date partitions when partition pruning applies.

### Partitioning vs Sharding

Partitioning generally divides a table within a database system.

Sharding distributes data across separate database nodes or instances.

---

## 23. Read Replicas

A read replica is a secondary database instance that receives replicated changes from a primary database and serves read queries.

### Architecture

```text
             Spring Boot Application
                       |
                 Database Router
                       |
             +---------+---------+
             |                   |
             v                   v
        Primary DB          Read Replica
        (Writes)            (Reads)
             |
             |
             +------------------->
                   Replication
```

Example:

- INSERT orders -> Primary.
- UPDATE orders -> Primary.
- SELECT product catalog -> Read replica.
- SELECT reports -> Read replica.

### Why use read replicas?

Suppose:
- 80% of workload is read.
- 20% of workload is write.

Read replicas can distribute read traffic and reduce pressure on the primary.

### Important consideration

Replication is often asynchronous.

A recently committed write on the primary may not yet be visible on a replica.

This is called replication lag.

For read-after-write consistency, route the user's next read to the primary or use a consistency-aware routing mechanism.

---

## 24. Database Replication

Replication copies database changes from one database node to another.

### Primary-Replica Replication

```text
              Primary Database
                     |
               Replication Log
                     |
          +----------+----------+
          |                     |
          v                     v
      Replica 1             Replica 2
```

### Synchronous Replication

The primary waits for acknowledgements from required replicas before confirming a commit, depending on the configured durability policy.

Advantages:
- Stronger durability guarantees across participating nodes.

Disadvantages:
- Higher write latency.
- Reduced availability when required replicas are unavailable.

### Asynchronous Replication

The primary commits without waiting for all replicas to receive the change.

Advantages:
- Lower write latency.
- Less dependence on replica availability.

Disadvantages:
- Replicas may be behind.
- Recent committed data may be lost during certain failover scenarios.

### Semi-synchronous Replication

A primary waits for acknowledgement from at least one replica to receive the relevant transaction data, depending on implementation and configuration.

It does not necessarily mean the replica has fully applied the transaction.

### Replication comparison

| Type | Write Latency | Durability Across Nodes | Risk of Replica Lag |
|---|---|---|---|
| Synchronous | Higher | Stronger, based on quorum/ack policy | Reduced |
| Asynchronous | Lower | Weaker at failover boundary | Higher |
| Semi-synchronous | Moderate | Intermediate | Possible |

---

## 25. Database Failover

Failover means switching database operations from a failed primary to another database node.

### Example

```text
Before Failure:

Application
     |
     v
Primary DB (UP)
     |
     v
Replica DB (UP)

After Primary Failure:

Application
     |
     v
Replica DB (Promoted)
     |
     v
New Primary
```

### Failover process

1. Monitor the primary database.
2. Detect failure.
3. Select an eligible replica.
4. Promote the replica.
5. Update connection routing or endpoint.
6. Ensure old primary cannot continue accepting conflicting writes.
7. Rebuild replication topology.

### Important terms

- RTO: Recovery Time Objective — acceptable recovery duration.
- RPO: Recovery Point Objective — acceptable data-loss window.
- Split brain: two nodes incorrectly operate as independent primaries.

### Interview point

Failover requires more than promoting a replica. You must consider data consistency, fencing the old primary, routing updates, replication state and application reconnection.

---

# SECTION B — NOSQL DATABASES

# LEVEL 6 — NoSQL Fundamentals

## 1. Why NoSQL?

NoSQL means Not Only SQL.

NoSQL databases are designed to support different data models and workload patterns, often with flexible schemas and distributed scaling capabilities.

### Why use NoSQL?

Traditional relational databases are excellent for structured data and transactional consistency.

However, certain workloads need:
- Massive horizontal scalability.
- High write throughput.
- Flexible document structures.
- Low-latency key-based access.
- Distributed data across regions.
- Graph relationship traversal.

NoSQL databases address some of these requirements through different architectures.

### Example

Suppose an e-commerce application stores:

- Customers.
- Orders.
- Product catalog.
- Shopping cart.
- User sessions.
- Product recommendations.

A possible architecture:

```text
                 E-Commerce Application
                           |
       +-------------------+------------------+
       |                   |                  |
       v                   v                  v
  PostgreSQL            MongoDB             Redis
  Customers             Product Catalog     Sessions
  Orders                Flexible Data       Cart Cache
  Payments              Product Details     Fast Lookup
```

Not every application needs all three databases. Choose based on actual requirements.

---

## 2. SQL vs NoSQL

| Feature | SQL | NoSQL |
|---|---|---|
| Data model | Relational tables | Key-value, document, wide-column, graph |
| Schema | Usually predefined | Often flexible or model-specific |
| Relationships | Foreign keys and JOINs | Often embedded or application-managed |
| Transactions | Strong transactional support | Varies by database |
| Scaling | Vertical and horizontal options | Many designed for horizontal distribution |
| Query language | SQL | Database-specific APIs/query languages |
| Consistency | Often strong transactional consistency | Strong or eventual depending on system |
| Examples | MySQL, PostgreSQL | MongoDB, Cassandra, Redis |

### When to choose SQL?

- Banking.
- Financial transactions.
- Order management.
- Inventory management.
- Complex joins.
- Strong relational constraints.

### When to choose NoSQL?

- Large-scale key-value lookups.
- Flexible document data.
- High-volume distributed writes.
- Time-series or wide-column workloads.
- Graph relationship queries.
- Caching and sessions.

### When to use both?

When different parts of an application have different workload requirements.

Example:

```text
              E-Commerce Application
                       |
          +------------+------------+
          |            |            |
          v            v            v
      PostgreSQL     Redis       MongoDB
       Orders        Cache       Catalog
       Payments      Sessions    Product Data
```

**Interview point:** SQL versus NoSQL is not a competition. Choose the database according to consistency, query patterns, data model, scale and operational requirements.

---

# LEVEL 7 — Types of NoSQL Databases

## 3. Key-Value Databases

A key-value database stores data as a key and its corresponding value.

```text
Key                 Value
---------------------------------
user:101            Ravi
user:102            Kumar
cart:101            Cart JSON
session:abc123      Session Data
```

Example:

```text
SET user:101 "Ravi"

GET user:101
```

### Architecture

```text
Application
    |
    v
Key-Value Database
    |
    +---- Key -> Value
    |
    +---- Key -> Value
    |
    +---- Key -> Value
```

### Use cases

- Caching.
- User sessions.
- Shopping carts.
- Temporary tokens.
- Fast configuration lookup.

Example database: Redis.

---

## 4. Document Databases

A document database stores records as documents, commonly JSON-like structures.

Example:

```json
{
  "customerId": 101,
  "name": "Ravi",
  "address": {
    "city": "Hyderabad",
    "state": "Telangana"
  },
  "orders": [
    {
      "orderId": 1001,
      "amount": 5000
    }
  ]
}
```

### Architecture

```text
Application
    |
    v
Document Database
    |
    +---- Document 1
    |
    +---- Document 2
    |
    +---- Document 3
```

### Advantages

- Flexible document structure.
- Natural representation of nested data.
- Can retrieve related information together.
- Useful for rapidly evolving data models.

### Disadvantages

- Duplication can increase.
- Cross-document relationships may be harder.
- Complex joins are not always as natural as in relational systems.

Example: MongoDB.

---

## 5. Wide-Column Databases

Wide-column databases organize data using rows and column families, allowing different rows to have different columns.

They are designed for distributed storage and large-scale workloads.

Example:

```text
Partition Key: customer_101

Column Family:
--------------------------------
order_1 -> 5000
order_2 -> 7000
order_3 -> 9000
```

### Architecture

```text
Application
    |
    v
Wide-Column Database
    |
    +---- Node 1
    |
    +---- Node 2
    |
    +---- Node 3
```

### Characteristics

- Partition-key-oriented data access.
- Distributed replication.
- High write throughput.
- Designed around known query patterns.

Example: Apache Cassandra.

---

## 6. Graph Databases

Graph databases store data as nodes, edges and properties.

They are useful when relationships are central to queries.

Example:

```text
       Ravi
        |
      FRIEND
        |
       Kumar
        |
      WORKS_AT
        |
      Google
```

### Components

- Node: entity.
- Edge: relationship.
- Property: information associated with a node or edge.

### Use cases

- Social networks.
- Fraud detection.
- Recommendation engines.
- Knowledge graphs.
- Network topology.

Example: Neo4j.

### Why not use a relational database?

Relational databases can model relationships, but graph databases can make deep relationship traversal more natural and efficient for graph-oriented workloads.

---

# LEVEL 8 — Important NoSQL Database Architectures

## 7. MongoDB Architecture

MongoDB is a document-oriented database.

It stores data in BSON documents.

### Basic architecture

```text
            Spring Boot Application
                      |
                      v
                   MongoDB
                      |
          +-----------+-----------+
          |                       |
          v                       v
      Replica Set             Sharded Cluster
      (HA)                    (Horizontal Scale)
          |                       |
    +-----+-----+          +------+------+
    |     |     |          |      |      |
    v     v     v          v      v      v
  Primary Secondary ...   Shard1 Shard2 Shard3
```

### Important components

**Collections**

Similar to relational tables.

**Documents**

Similar to rows, but represented as BSON documents.

**Replica Sets**

Maintain multiple copies of data for availability and failover.

**Primary**

Accepts writes in a replica set.

**Secondary**

Replicates data from the primary and may serve eligible reads depending on read preference.

**Sharding**

Distributes data across multiple shards.

### Example document

```json
{
  "_id": 101,
  "name": "Ravi",
  "department": "IT",
  "skills": ["Java", "Spring Boot", "Kafka"]
}
```

### When to use MongoDB?

- Product catalogs with variable attributes.
- Content management.
- Flexible document structures.
- Applications that benefit from document-oriented queries.

### When not to choose it?

If your workload heavily depends on complex relational joins and constraints, a relational database may be a better fit.

---

## 8. Cassandra Architecture

Apache Cassandra is a distributed wide-column database designed for high availability and horizontal scalability.

### Architecture

```text
             Application
                  |
                  v
            Cassandra Cluster
                  |
       +----------+----------+
       |          |          |
       v          v          v
     Node 1     Node 2     Node 3
       |          |          |
       +----------+----------+
              Replication
```

Cassandra uses a peer-to-peer architecture.

There is no traditional single primary node for the entire cluster.

### Important components

**1. Partition Key**

Determines which node owns the data partition.

**2. Token Ring**

Nodes own ranges of token values.

**3. Replication Factor**

Determines how many copies of each partition are stored.

**4. Commit Log**

Records writes for recovery.

**5. Memtable**

An in-memory structure that holds recent writes.

**6. SSTable**

An immutable on-disk data structure.

### Write path

```text
Application
    |
    v
Coordinator Node
    |
    v
Commit Log
    |
    v
Memtable
    |
    v
SSTable (Disk)
```

### Read path

Cassandra checks relevant in-memory and on-disk structures, including memtables, SSTables, indexes and Bloom filters, to locate the requested data.

### Use cases

- High-volume event data.
- Time-series workloads.
- Distributed telemetry.
- Large-scale write-heavy workloads.

### Important point

Cassandra data modelling is query-driven. Design partition keys and clustering columns around expected queries.

---

## 9. DynamoDB Architecture

Amazon DynamoDB is a fully managed, distributed NoSQL database.

It supports key-value and document data models.

### Architecture

```text
Spring Boot Application
          |
          v
       DynamoDB
          |
   +------+------+
   |             |
   v             v
Partition 1   Partition 2
   |             |
   v             v
Data Items    Data Items
```

### Important concepts

- Partition key.
- Sort key.
- Global Secondary Index (GSI).
- Local Secondary Index (LSI).
- On-demand and provisioned capacity modes.
- Streams.
- Conditional writes.
- Strongly consistent and eventually consistent reads, depending on operation and configuration.

### Example table

```text
Orders
--------------------------------
Partition Key: customer_id
Sort Key: order_date
```

This supports queries such as retrieving orders for a specific customer ordered by date.

### When to use DynamoDB?

- Serverless applications on AWS.
- Predictable key-based access patterns.
- Large-scale managed workloads.
- Applications that need low operational overhead.

---

## 10. Google Cloud Spanner

Cloud Spanner is a globally distributed relational database.

It combines relational data modelling and SQL with horizontal distribution and strong consistency.

### Architecture

```text
             Application
                  |
                  v
              Spanner
                  |
       +----------+----------+
       |          |          |
       v          v          v
     Node 1     Node 2     Node 3
       |          |          |
       +----------+----------+
           Distributed Data
           and Replication
```

### Important capabilities

- Relational schema.
- SQL support.
- Horizontal scaling.
- Strong consistency.
- Distributed transactions.
- Synchronous replication across configured replicas.
- TrueTime-based coordination for externally consistent transactions.

### What is TrueTime?

TrueTime is Google's time API that provides a bounded uncertainty interval for time.

Spanner uses it as part of its distributed transaction ordering and commit protocol.

### When to use Spanner?

- Globally distributed relational applications.
- High availability requirements.
- Strong consistency across regions.
- Large-scale transactional workloads.

### Comparison

| Database | Main Strength |
|---|---|
| PostgreSQL | Relational features and advanced SQL |
| MongoDB | Flexible document data |
| Cassandra | Distributed, write-heavy workloads |
| DynamoDB | Managed key-value/document workloads |
| Cloud Spanner | Globally distributed relational transactions |
| Redis | Low-latency in-memory access |

---

## 11. Redis Architecture

Redis is an in-memory data store commonly used as a cache, key-value database, session store and messaging component.

### Architecture

```text
             Spring Boot
                  |
                  v
                Redis
                  |
        +---------+---------+
        |         |         |
        v         v         v
      String    Hash      List
        |         |         |
        +---------+---------+
                  |
                  v
             Memory Storage
```

### Common Redis data structures

| Data Structure | Example Use Case |
|---|---|
| String | Cache values, counters |
| Hash | User profiles |
| List | Queues and lists |
| Set | Unique values |
| Sorted Set | Leaderboards |
| Stream | Event streams |
| Bitmap | Compact boolean tracking |
| HyperLogLog | Approximate cardinality |

### Spring Boot example

```java
@Service
public class ProductService {

    private final RedisTemplate<String, Product> redisTemplate;

    public ProductService(
            RedisTemplate<String, Product> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public Product getProduct(Long id) {

        String key = "product:" + id;

        Product cached = redisTemplate.opsForValue()
                .get(key);

        if (cached != null) {
            return cached;
        }

        Product product = loadProductFromDatabase(id);

        redisTemplate.opsForValue()
                .set(key, product, Duration.ofMinutes(10));

        return product;
    }
}
```

### Cache-aside pattern

1. Application checks Redis.
2. If data exists, return cached data.
3. If not, query the database.
4. Store the result in Redis.
5. Return the result.

### Important Redis concepts

- Persistence: RDB snapshots and AOF logs.
- Replication: primary-replica.
- Sentinel: monitoring and automatic failover.
- Cluster: distributed sharding.
- TTL: expiration.
- Eviction policies: remove keys when memory limits are reached.

### When to use Redis?

- Caching.
- Sessions.
- Rate limiting.
- Leaderboards.
- Distributed counters.
- Temporary application state.

**Important:** Redis is not automatically a replacement for a relational database. Persistence, durability, eviction and data recovery must be configured according to the use case.

---

# LEVEL 9 — NoSQL Data Modelling and Consistency

## 12. Data Modelling in NoSQL

In relational databases, modelling often begins with entities and relationships.

In NoSQL, modelling should begin with access patterns.

### Example: E-commerce orders

Required queries:

1. Find orders by customer.
2. Find a specific order.
3. Find orders by status.
4. Find all orders for a particular date range.

A document model might be:

```json
{
  "orderId": "ORD101",
  "customerId": "C101",
  "status": "CONFIRMED",
  "items": [
    {
      "productId": "P101",
      "quantity": 2,
      "price": 1000
    }
  ],
  "totalAmount": 2000
}
```

### Embed vs Reference

**Embedding**

Store related data inside the same document.

Good when:
- Related data is usually read together.
- Child data has a bounded size.
- Data is owned by the parent.

**Referencing**

Store an identifier pointing to another document.

Good when:
- Data is shared across multiple records.
- Child collections can grow significantly.
- Data changes independently.

### SQL vs NoSQL modelling

| Requirement | Suggested Approach |
|---|---|
| Complex relationships | SQL |
| Variable product attributes | Document DB |
| High-volume event writes | Wide-column DB |
| Session storage | Key-value DB |
| Deep relationship traversal | Graph DB |
| Globally consistent relational transactions | Cloud Spanner or another suitable distributed SQL system |

---

## 13. Eventual Consistency

Eventual consistency means that if no new updates occur, replicas will eventually converge to the same value.

### Example

```text
Write to Primary:
Customer Name = Ravi

Primary:
Ravi

Replica 1:
Ravi

Replica 2:
Old value (temporarily)

After replication:
All replicas = Ravi
```

During replication delay, different replicas may return different values.

### Why eventual consistency?

It can allow distributed systems to prioritize availability and lower write latency while replicating asynchronously.

### Example use cases

- Social media likes.
- View counts.
- Product recommendation data.
- Analytics dashboards.
- Distributed caches.

### When not to use eventual consistency alone?

For operations such as:
- Bank balance transfers.
- Inventory reservation.
- Payment state transitions.

These may require stronger transactional guarantees or carefully designed consistency controls.

---

## 14. Partition Keys

A partition key determines how records are distributed across partitions in a distributed database.

Example:

```text
Customer Table

customer_id | name
------------------
101         | Ravi
102         | Kumar
103         | Anil
```

A database may hash the customer ID to determine the partition.

```text
hash(customer_id)
        |
        v
   Partition Mapping
        |
    +---+---+
    |       |
    v       v
Partition 1 Partition 2
```

### Good partition key characteristics

- High cardinality.
- Even distribution.
- Matches common query patterns.
- Avoids concentration of traffic on a few keys.

### Poor partition key

```text
country = India
```

If most records belong to India, one partition may receive too much data or traffic.

---

## 15. Hot Partitions

A hot partition occurs when a disproportionate amount of traffic or data is concentrated on one partition.

### Example

Suppose a shopping application partitions by country.

```text
India       -> 80% traffic
USA         -> 10% traffic
UK          -> 5% traffic
Other       -> 5% traffic
```

The India partition becomes a bottleneck.

### Solutions

1. Choose a better partition key.
2. Use a composite partition key.
3. Add a shard suffix where appropriate.
4. Distribute high-volume writes.
5. Monitor partition-level throughput.

Example:

Instead of:

```text
partition_key = product_id
```

For a very hot product, use:

```text
partition_key = product_id + shard_number
```

This distributes writes, but reads may need to query multiple shards and merge the results.

---

## 16. Secondary Indexes

A secondary index enables queries using attributes other than the primary key.

Example:

```text
Orders

order_id | customer_id | status
--------------------------------
1        | 101         | PAID
2        | 102         | PENDING
3        | 101         | PAID
```

Primary lookup:

```text
order_id = 1
```

Secondary lookup:

```text
customer_id = 101
```

### Different databases

- MongoDB: secondary indexes on document fields.
- Cassandra: secondary indexes and other indexing approaches, with workload limitations.
- DynamoDB: Global Secondary Index and Local Secondary Index.
- Relational databases: indexes on non-primary-key columns.

### Important point

Secondary indexes can add write and storage overhead. In distributed NoSQL systems, the cost and query behavior depend heavily on the index implementation.

---

## 17. Quorum Reads and Writes

Quorum is a distributed consistency mechanism based on the number of replicas that must acknowledge an operation.

Suppose:
- Replication factor N = 3.
- Read quorum R = 2.
- Write quorum W = 2.

The intersection condition:

```text
R + W > N
```

Here:

```text
2 + 2 > 3
```

This means the read and write replica sets must overlap under the simplified quorum model.

### Example

```text
             3 Replicas
          +------+------+------+
          |      |      |      |
          v      v      v      |
        Node 1 Node 2 Node 3
```

Write quorum = 2.

At least two replicas must acknowledge the write.

Read quorum = 2.

The read operation consults two replicas according to the database's consistency protocol.

### Important

Quorum intersection alone does not guarantee full linearizability. Conflict resolution, versioning, timestamps, repair behavior and the database's consistency model also matter.

### When to use?

- Distributed databases.
- Replicated key-value systems.
- Systems requiring configurable consistency/availability trade-offs.

---

# SECTION C — DATABASE SCALING

# LEVEL 10 — Scaling Fundamentals

## 1. Vertical Scaling

Vertical scaling means increasing the resources of an existing database server.

Example:

```text
Before:
8 CPU
32 GB RAM
500 GB Storage

After:
32 CPU
128 GB RAM
2 TB Storage
```

### Advantages

- Simple architecture.
- No need to distribute data across database nodes.
- Easier operational management.

### Disadvantages

- Hardware limits.
- Higher cost at larger sizes.
- A single server may remain a failure bottleneck.
- Upgrades may require downtime or maintenance.

### When to use?

- Small and medium applications.
- Early-stage systems.
- Workloads that fit comfortably on one database server.

---

## 2. Horizontal Scaling

Horizontal scaling means adding more database nodes and distributing workload or data among them.

```text
           Application
                |
          Database Layer
                |
       +--------+--------+
       |        |        |
       v        v        v
      DB 1     DB 2     DB 3
```

### Advantages

- More total capacity.
- Better distribution of data and traffic.
- Can improve fault tolerance.
- Supports large-scale systems.

### Disadvantages

- More operational complexity.
- Distributed transactions are harder.
- Data routing becomes necessary.
- Replication and consistency need careful design.

### Important distinction

Adding read replicas is horizontal scaling of read capacity.

Sharding is horizontal scaling of data and write capacity.

They solve different bottlenecks.

---

# LEVEL 11 — Replicas, Sharding and Partitioning

## 3. Read Replicas

Read replicas distribute read queries across secondary database nodes.

```text
          Spring Boot
               |
         Read/Write Router
               |
        +------+------+
        |             |
        v             v
     Primary       Replicas
     (Writes)      (Reads)
                      |
                +-----+-----+
                |           |
                v           v
              Read 1      Read 2
```

### Example

A product catalog receives:
- 10,000 read requests/sec.
- 1,000 write requests/sec.

Read replicas can distribute read workload while writes continue to go to the primary.

### Limitation

Read replicas do not automatically distribute primary write load.

---

## 4. Sharding

Sharding distributes a dataset across multiple independent database nodes.

Each shard stores only a portion of the complete dataset.

### Example

Suppose you have 100 million customers.

Instead of storing all customers in one database:

```text
              Customer Database
                     |
          +----------+----------+
          |          |          |
          v          v          v
        Shard 1    Shard 2    Shard 3
        1-33M      33-66M     66-100M
```

### Sharding strategies

**A. Range-Based Sharding**

```text
Customer ID:
1 - 1,000,000       -> Shard 1
1,000,001 - 2M      -> Shard 2
2,000,001 - 3M      -> Shard 3
```

Risk: sequential inserts may concentrate traffic on the latest range.

**B. Hash-Based Sharding**

```text
shard = hash(customer_id) % number_of_shards
```

Distributes records based on hash values.

**C. Geographic Sharding**

```text
India customers -> India database
US customers    -> US database
Europe customers -> Europe database
```

Useful for data locality and regulatory requirements, but introduces cross-region query complexity.

### Challenges of sharding

- Cross-shard joins.
- Cross-shard transactions.
- Rebalancing.
- Hot shards.
- Global uniqueness.
- Distributed query execution.

---

## 5. Horizontal Partitioning

Horizontal partitioning divides a table into subsets of rows.

Example:

```text
Orders Table
    |
    +---- Orders 2024
    |
    +---- Orders 2025
    |
    +---- Orders 2026
```

All partitions have the same columns but contain different rows.

### Use cases

- Time-series data.
- Large order tables.
- Historical records.
- Retention and archival.

### Difference from sharding

Horizontal partitioning can occur within one database.

Sharding distributes partitions across multiple database instances or nodes.

---

## 6. Vertical Partitioning

Vertical partitioning divides a table by columns.

Original:

```text
Employees
---------
id
name
email
salary
address
profile_photo
```

Split into:

```text
Employee_Core
-------------
id
name
email

Employee_Private
----------------
id
salary
address

Employee_Media
--------------
id
profile_photo
```

### Benefits

- Frequently accessed columns stay in a smaller table.
- Large or sensitive data can be separated.
- Different access patterns can be optimized.

### Disadvantages

- Additional joins.
- More complex updates.
- Requires careful data ownership.

### When to use?

When different groups of columns have different access frequencies, security requirements or storage needs.

---

## 7. Consistent Hashing

Consistent hashing distributes keys across nodes using a hash ring.

### Why is it needed?

With simple modulo hashing:

```text
node = hash(key) % N
```

If the number of nodes changes, many keys may map to different nodes.

Consistent hashing reduces the number of keys that need to move when nodes are added or removed.

### Hash ring

```text
                Node A
                  |
          +-------+-------+
          |               |
       Key 1            Key 2
          |               |
       Node B           Node C
          |               |
          +---------------+
```

Conceptually:

1. Hash each node to a position on a ring.
2. Hash each key to a position on the same ring.
3. Assign each key to the next node clockwise.
4. When a node is added or removed, only a subset of keys needs reassignment.

### Virtual Nodes

A physical node can have multiple positions on the ring.

Benefits:
- Better load distribution.
- Less imbalance.
- Easier rebalancing.

### Use cases

- Distributed caching.
- Distributed key-value systems.
- Some sharding architectures.

---

# LEVEL 12 — Database Routing and Multi-Region Design

## 8. Database Routing

Database routing determines which database instance should handle a particular query.

Example:

```text
             Spring Boot
                  |
             DB Router
                  |
          +-------+-------+
          |               |
          v               v
       Primary          Replica
       Writes           Reads
```

### Routing rules

| Operation | Destination |
|---|---|
| INSERT | Primary |
| UPDATE | Primary |
| DELETE | Primary |
| Read after write | Primary when required |
| General read | Replica |
| Strongly consistent read | Primary or appropriate consistency-aware endpoint |

### Spring Boot implementation approach

You can implement routing using:
- Separate read/write repositories.
- `AbstractRoutingDataSource`.
- Transaction-aware routing.
- Database proxies.
- Cloud-managed database endpoints.

### Important

Do not route every `SELECT` to a replica blindly. A query immediately following a write may need the primary to avoid stale reads.

---

## 9. Multi-Region Databases

A multi-region database stores or replicates data across geographically distributed regions.

Example:

```text
             Global Application
                     |
           Global Traffic Routing
                     |
        +------------+------------+
        |            |            |
        v            v            v
      India         USA         Europe
        |            |            |
        v            v            v
      Region DB    Region DB    Region DB
```

### Why multi-region?

- Lower latency for users.
- Disaster recovery.
- Regional availability.
- Data locality requirements.

### Architectures

**A. Single-Region Primary, Cross-Region Replicas**

Writes go to one primary region; replicas receive data elsewhere.

**B. Multi-Region Synchronous Replication**

Writes coordinate with replicas across regions according to the database's consistency and quorum design.

**C. Multi-Region Distributed Database**

The database distributes data and coordinates transactions across regions.

### Challenges

- Network latency.
- Cross-region write coordination.
- Replication lag.
- Conflict resolution.
- Data residency and compliance.
- Regional failover.

---

## 10. Replication Lag

Replication lag is the delay between a change being committed on the primary and becoming visible on a replica.

### Example

```text
Primary:
Balance = 5000

Transaction:
Balance = 4000

Primary:
Balance = 4000

Replica:
Balance = 5000  (temporarily)

After replication:
Balance = 4000
```

### Why does replication lag occur?

- Network latency.
- High write volume.
- Slow replica storage.
- Large transactions.
- Replica resource limitations.

### How to handle it?

1. Monitor replication delay.
2. Route critical reads to the primary.
3. Use session consistency where needed.
4. Avoid assuming replicas are immediately current.
5. Scale or tune replicas based on workload.

### Real-world example

A user updates their profile and immediately opens it again.

If the read is routed to a lagging replica, the user may see old information.

Solution: read-after-write routing to the primary or a mechanism that waits for the relevant replication position.

---

# LEVEL 13 — Distributed Transactions and Migration

## 11. Distributed Transactions

A distributed transaction involves operations across multiple databases or services.

Example:

An e-commerce checkout involves:

```text
Order Service
      |
      v
Payment Service
      |
      v
Inventory Service
```

A successful checkout requires:
1. Create order.
2. Process payment.
3. Reserve inventory.

What happens if payment succeeds but inventory reservation fails?

This is a distributed consistency problem.

### Two-Phase Commit (2PC)

2PC coordinates multiple participants through two phases.

**Phase 1: Prepare**

Coordinator asks participants whether they can commit.

```text
Coordinator
    |
    +----> Order DB: PREPARE
    |
    +----> Payment DB: PREPARE
    |
    +----> Inventory DB: PREPARE
```

Participants prepare their transaction and vote.

**Phase 2: Commit or Abort**

If all participants vote yes, coordinator tells them to commit.

Otherwise, it tells them to abort.

### Limitations of 2PC

- Coordinator can become a bottleneck.
- Participants may hold locks while waiting.
- Failures can leave transactions blocked.
- Cross-service operation is operationally complex.

### Saga Pattern

Saga coordinates a long-running business transaction through multiple local transactions.

If a step fails, compensating actions are executed.

Example:

```text
Create Order
     |
     v
Process Payment
     |
     v
Reserve Inventory
     |
     v
Send Confirmation
```

If inventory reservation fails:

```text
Create Order
     |
     v
Process Payment
     |
     v
Inventory Failure
     |
     v
Refund Payment
     |
     v
Cancel Order
```

### Saga approaches

**Choreography**

Services communicate through events.

**Orchestration**

A central orchestrator coordinates the steps.

### When to use?

Use Saga for long-running business processes across microservices where local transactions and compensating actions are appropriate.

---

## 12. Database Migration Strategies

Database migration means moving data or changing database schemas, database engines or deployment environments.

### A. Schema Migration

Example:

Adding a column:

```sql
ALTER TABLE employees
ADD COLUMN experience INT;
```

For production systems, consider table size, lock behavior, deployment compatibility and rollback strategy.

### B. Database Engine Migration

Example:

MySQL to PostgreSQL.

Steps:

1. Analyze schema compatibility.
2. Identify data type differences.
3. Convert SQL queries.
4. Migrate data.
5. Validate row counts and checksums.
6. Test application behavior.
7. Switch traffic.
8. Retain a rollback strategy.

### C. Big Bang Migration

Move the entire dataset and application to the new database at one time.

Advantages:
- Simpler architecture after migration.

Disadvantages:
- Downtime risk.
- Large cutover.
- Difficult rollback.

### D. Incremental Migration

Move data and workloads gradually.

Example:

```text
Old Database
     |
     v
Initial Data Copy
     |
     v
Change Data Capture
     |
     v
New Database
     |
     v
Validation
     |
     v
Traffic Cutover
```

### E. Dual Write

The application writes to both old and new databases.

This can be risky because one write may succeed while the other fails.

Use durable event/outbox patterns and reconciliation if dual writes are unavoidable.

### F. Change Data Capture (CDC)

CDC captures database changes and sends them to another system.

It can be used for:
- Database migration.
- Search indexing.
- Analytics.
- Event-driven architectures.

Tools include Debezium and database-native replication/logical decoding mechanisms.

---

# LEVEL 14 — SQL VS NOSQL VS HYBRID: ARCHITECTURE SELECTION

## 1. When to choose SQL?

Choose SQL when you need:

- Relational data.
- ACID transactions.
- Referential integrity.
- Complex JOINs.
- Structured querying.
- Strong consistency within transactional boundaries.

Examples:
- Banking.
- Payments.
- Orders.
- Inventory.
- Employee management.

## 2. When to choose NoSQL?

Choose NoSQL when you need:

- Flexible document structures.
- Large-scale key-based access.
- Distributed write throughput.
- High-volume event storage.
- Graph traversal.
- Low-latency caching.

Examples:
- Product catalogs.
- Sessions.
- Shopping carts.
- Distributed telemetry.
- Recommendations.

## 3. When to choose a Hybrid Architecture?

A hybrid architecture uses multiple databases for different workloads.

### E-commerce architecture

```text
                  API Gateway
                       |
                       v
               Spring Boot Services
                       |
       +---------------+----------------+
       |               |                |
       v               v                v
   Order Service   Catalog Service   User Service
       |               |                |
       v               v                v
   PostgreSQL       MongoDB          PostgreSQL
       |
       v
   Payment DB / Payment Provider

       +--------------------------------+
       |
       v
     Redis
   Cache / Session
```

### Database selection

| Use Case | Database | Reason |
|---|---|---|
| Orders | PostgreSQL | Transactions and relationships |
| Payments | Relational DB | Financial consistency and constraints |
| Product catalog | MongoDB or PostgreSQL JSONB | Flexible product attributes |
| Sessions | Redis | Fast key-based lookup |
| Product cache | Redis | Reduce database reads |
| Analytics events | Cassandra or analytical storage | High-volume event workloads |
| Social relationships | Graph DB | Relationship traversal |

### Important design principle

Avoid selecting multiple databases just because they are popular.

Every additional database adds:
- Operational complexity.
- Monitoring requirements.
- Backup and recovery responsibilities.
- Security and access management.
- Data consistency challenges.

Use polyglot persistence only when different workloads justify it.

---

# LEVEL 15 — PRACTICAL SYSTEM DESIGN EXAMPLE

## Design a Database Architecture for an E-Commerce Application

### Requirements

- 10 million customers.
- 100 million orders.
- 1 million daily active users.
- High read traffic.
- Reliable order and payment processing.
- Product catalog with varying attributes.
- Fast product search.
- High availability.

### Proposed architecture

```text
                  Clients
                     |
                     v
                 API Gateway
                     |
                     v
              Spring Boot Services
                     |
       +-------------+-------------+
       |             |             |
       v             v             v
   User Service  Order Service  Catalog Service
       |             |             |
       v             v             v
   PostgreSQL    PostgreSQL      MongoDB
       |             |             |
       |             v             |
       |        Read Replicas      |
       |                           |
       +-------------+-------------+
                     |
                     v
                   Redis
              Cache / Sessions
                     |
                     v
               Message Broker
                     |
                     v
              Analytics Pipeline
```

### Database decisions

**User Service — PostgreSQL**

Reason:
- Structured customer data.
- Relationships.
- Constraints.
- Transactional updates.

**Order Service — PostgreSQL**

Reason:
- ACID transactions.
- Order state management.
- Relational integrity.
- Reliable updates.

**Catalog Service — MongoDB or PostgreSQL**

Reason:
- Different product categories may have different attributes.
- Document structure can be useful.
- PostgreSQL JSONB is also a viable option if relational capabilities are important.

**Redis**

Reason:
- Product caching.
- Session storage.
- Rate limiting.
- Frequently accessed data.

**Read Replicas**

Reason:
- Distribute read workload.
- Reduce primary database load.

**Sharding**

Consider when one database instance can no longer handle the required dataset or workload, and partitioning, query optimization and replicas are insufficient.

### Order placement flow

```text
Client
  |
  v
Order Service
  |
  v
PostgreSQL Transaction
  |
  +---- Create Order
  |
  +---- Store Order Items
  |
  +---- Store Outbox Event
  |
  v
Commit
  |
  v
Outbox Publisher
  |
  v
Message Broker
  |
  +----> Payment Service
  |
  +----> Inventory Service
  |
  +----> Notification Service
```

This architecture uses a local database transaction and an outbox event to reliably publish business events.

---

# LEVEL 16 — IMPORTANT INTERVIEW QUESTIONS

## RDBMS and SQL

1. Explain the internal architecture of MySQL.
2. Explain the difference between MySQL and PostgreSQL.
3. What happens internally when an SQL query executes?
4. What is a database index?
5. Why do databases use B+ Trees?
6. What is a composite index?
7. Explain the leftmost prefix rule.
8. Clustered vs non-clustered index?
9. What is a covering index?
10. How do you identify a slow SQL query?
11. What is an execution plan?
12. Explain normalization and denormalization.
13. What are ACID properties?
14. Explain transaction isolation levels.
15. Dirty read vs non-repeatable read vs phantom read?
16. What is MVCC?
17. Optimistic vs pessimistic locking?
18. How do you prevent deadlocks?
19. How does connection pooling work?
20. What happens when a database primary fails?

## NoSQL

1. Why do we need NoSQL databases?
2. SQL vs NoSQL?
3. Explain key-value and document databases.
4. Explain MongoDB architecture.
5. How does Cassandra distribute data?
6. What is a partition key?
7. What causes hot partitions?
8. Explain eventual consistency.
9. What are quorum reads and writes?
10. What is a secondary index?
11. Why use Redis?
12. How does Redis caching work?
13. When would you choose MongoDB over PostgreSQL?
14. What is Cloud Spanner?
15. What is polyglot persistence?

## Database Scaling

1. Vertical vs horizontal scaling?
2. Read replicas vs sharding?
3. What is database partitioning?
4. Horizontal vs vertical partitioning?
5. What is consistent hashing?
6. How does database routing work?
7. What is replication lag?
8. How would you design a multi-region database?
9. Explain distributed transactions.
10. What is the Saga pattern?
11. How would you migrate MySQL to PostgreSQL?
12. How would you scale a database handling millions of requests?

---

# LEVEL 17 — PRACTICAL HANDS-ON EXERCISES

Complete these exercises to connect the concepts to real implementation.

| No. | Exercise | Concepts |
|---|---|---|
| 1 | Create an employee and department schema | Tables, PK, FK |
| 2 | Normalize an order management schema | 1NF, 2NF, 3NF |
| 3 | Add indexes and compare query plans | Indexes, EXPLAIN |
| 4 | Create a composite index | Index ordering |
| 5 | Demonstrate transaction rollback | ACID |
| 6 | Test isolation levels | Concurrency |
| 7 | Implement optimistic locking with JPA | MVCC, versioning |
| 8 | Implement pessimistic locking | Database locks |
| 9 | Configure HikariCP | Connection pooling |
| 10 | Implement Redis cache-aside | NoSQL, caching |
| 11 | Configure read/write routing | Replicas |
| 12 | Design a sharding strategy | Horizontal scaling |
| 13 | Implement an outbox pattern | Distributed consistency |
| 14 | Design a database failover strategy | High availability |

---

# FINAL REVISION CHEAT SHEET

| Concept | Remember |
|---|---|
| RDBMS | Relational tables and SQL |
| Query Optimizer | Selects an efficient query execution plan |
| Index | Speeds up data retrieval |
| B+ Tree | Balanced, high-fan-out index structure |
| Composite Index | Index on multiple columns |
| Covering Index | Contains all columns needed by a query |
| Normalization | Reduces duplication and anomalies |
| Denormalization | Improves selected read patterns |
| ACID | Atomicity, Consistency, Isolation, Durability |
| MVCC | Multiple visible versions of data |
| Optimistic Locking | Detects conflicting updates |
| Pessimistic Locking | Acquires locks before modification |
| Deadlock | Transactions wait on each other's locks |
| HikariCP | JDBC connection pooling |
| Read Replica | Distributes read traffic |
| Replication | Copies data between database nodes |
| Failover | Switches to another database node |
| NoSQL | Non-relational data models |
| MongoDB | Document database |
| Cassandra | Distributed wide-column database |
| DynamoDB | Managed key-value/document database |
| Redis | In-memory data store |
| Spanner | Distributed relational database |
| Partitioning | Divides a table into partitions |
| Sharding | Distributes data across database nodes |
| Consistent Hashing | Reduces key movement during node changes |
| Replication Lag | Delay in replica updates |
| Saga | Coordinates distributed business transactions |
| CDC | Captures database changes |

---

# RECOMMENDED STUDY ORDER

1. RDBMS internals and MySQL/PostgreSQL architecture.
2. Normalization and denormalization.
3. Indexes, B+ Trees and composite indexes.
4. Query execution plans and SQL optimization.
5. Transactions, isolation levels and MVCC.
6. Locks, optimistic/pessimistic locking and deadlocks.
7. Connection pooling and database availability.
8. NoSQL fundamentals and database types.
9. MongoDB, Cassandra, Redis and Spanner architecture.
10. NoSQL data modelling and consistency.
11. Vertical/horizontal scaling, replicas and sharding.
12. Multi-region architecture and distributed transactions.

**Preparation target:** Understand each concept, explain its internal working, identify when to use it, and connect it to a real Spring Boot application. Prioritize practical SQL, indexing, transaction management, concurrency and scaling over memorizing every vendor-specific implementation detail.