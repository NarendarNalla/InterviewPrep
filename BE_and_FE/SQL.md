# SQL Interview Preparation Notes
## Complete Practical Guide for Java Full Stack Developers (5+ Years)

**SQL Dialect:** PostgreSQL-style SQL. Most concepts apply to MySQL, Oracle, and SQL Server, but some syntax and database behavior differ.

**How to use these notes:** First execute the sample database setup in Section 2. Use those tables and records to practice the queries in the remaining sections.

---

# Table of Contents

1. [SQL Fundamentals](#1-sql-fundamentals)
2. [Sample Database: Tables and Data](#2-sample-database-tables-and-data)
3. [SELECT and Filtering](#3-select-and-filtering)
4. [NULL Values and Operators](#4-null-values-and-operators)
5. [Aggregate Functions and GROUP BY](#5-aggregate-functions-and-group-by)
6. [SQL Joins](#6-sql-joins)
7. [Subqueries and CTEs](#7-subqueries-and-ctes)
8. [Set Operators](#8-set-operators)
9. [CASE and Built-in Functions](#9-case-and-built-in-functions)
10. [Window Functions](#10-window-functions)
11. [DDL and DML Operations](#11-ddl-and-dml-operations)
12. [Keys and Constraints](#12-keys-and-constraints)
13. [Database Normalization](#13-database-normalization)
14. [Transactions and ACID Properties](#14-transactions-and-acid-properties)
15. [Indexes](#15-indexes)
16. [SQL Query Optimization](#16-sql-query-optimization)
17. [Locks, Deadlocks and MVCC](#17-locks-deadlocks-and-mvcc)
18. [Views, Materialized Views and Triggers](#18-views-materialized-views-and-triggers)
19. [SQL Pagination](#19-sql-pagination)
20. [Top SQL Interview Questions](#20-top-sql-interview-questions)
21. [SQL with Java, JDBC, Spring and Hibernate](#21-sql-with-java-jdbc-spring-and-hibernate)
22. [SQL Revision Cheat Sheet](#22-sql-revision-cheat-sheet)
23. [Interview Preparation Checklist](#23-interview-preparation-checklist)

---

# 1. SQL Fundamentals

## 1.1 What is SQL? ⭐⭐⭐⭐⭐

SQL stands for Structured Query Language. It is used to communicate with relational database management systems (RDBMS).

Examples:
- PostgreSQL
- MySQL
- Oracle
- Microsoft SQL Server

SQL is used to:
- Create database objects.
- Insert, update and delete records.
- Retrieve information.
- Manage transactions.
- Control database permissions.

A relational database stores information in tables containing rows and columns.

## 1.2 SQL Command Categories ⭐⭐⭐⭐⭐

| Category | Full Name | Primary Purpose | Key Commands |
| :--- | :--- | :--- | :--- |
| **DDL** | Data Definition Language | Defines and modifies database structures and schemas. | `CREATE`, `ALTER`, `DROP`, `TRUNCATE`, `RENAME` |
| **DQL** | Data Query Language | Retrieves and reads data from the database. | `SELECT` |
| **DML** | Data Manipulation Language | Adds, updates, and removes data inside tables. | `INSERT`, `UPDATE`, `DELETE`, `MERGE` |
| **DCL** | Data Control Language | Manages user access rights and security permissions. | `GRANT`, `REVOKE` |
| **TCL** | Transaction Control Language | Manages transaction changes and data integrity. | `COMMIT`, `ROLLBACK`, `SAVEPOINT` |



## 1.3 Logical Execution Order of SQL ⭐⭐⭐⭐⭐

Consider:

```sql
SELECT department_id, COUNT(*) AS employee_count
FROM employees
WHERE salary > 50000
GROUP BY department_id
HAVING COUNT(*) >= 2
ORDER BY employee_count DESC
LIMIT 10;
```

The logical processing order is:

1. FROM and JOIN
2. WHERE
3. GROUP BY
4. HAVING
5. SELECT
6. DISTINCT
7. ORDER BY
8. LIMIT / OFFSET

**Interview question: Why can't we use aggregate functions directly in WHERE?**

Because WHERE filters individual rows before grouping. Aggregate functions are calculated after grouping. Use HAVING to filter aggregated results.

## 1.4 SQL vs PostgreSQL vs MySQL

- SQL is the language and standard.
- PostgreSQL is an RDBMS that implements SQL and provides additional features.
- MySQL is another RDBMS with its own syntax and implementation details.

SQL is declarative: we describe the result we need, and the database optimizer determines how to retrieve it.

---

# 2. Sample Database: Tables and Data

We will use a company database throughout these notes.

## 2.1 Database Relationships

```text
departments 1 -------- many employees

employees   1 -------- many employees
                  (manager relationship)

customers   1 -------- many orders

orders      1 -------- many order_items

products    1 -------- many order_items
```

The sample data intentionally includes:
- An employee without a department.
- A department without employees.
- A customer without orders.
- A product that has never been ordered.
- Customers with multiple orders.
- Different order statuses.

These cases help us practice LEFT JOIN, NULL handling, NOT EXISTS and aggregation.

## 2.2 Create Tables

```sql
CREATE TABLE departments (
    department_id INT PRIMARY KEY,
    department_name VARCHAR(50) NOT NULL UNIQUE,
    location VARCHAR(50)
);

CREATE TABLE employees (
    employee_id INT PRIMARY KEY,
    employee_name VARCHAR(80) NOT NULL,
    email VARCHAR(120) UNIQUE,
    department_id INT REFERENCES departments(department_id),
    manager_id INT REFERENCES employees(employee_id),
    salary NUMERIC(10,2) CHECK (salary >= 0),
    hire_date DATE NOT NULL,
    city VARCHAR(50),
    status VARCHAR(20) DEFAULT 'ACTIVE'
);

CREATE TABLE customers (
    customer_id INT PRIMARY KEY,
    customer_name VARCHAR(80) NOT NULL,
    email VARCHAR(120) UNIQUE,
    city VARCHAR(50),
    created_at DATE NOT NULL
);

CREATE TABLE products (
    product_id INT PRIMARY KEY,
    product_name VARCHAR(100) NOT NULL,
    category VARCHAR(50),
    price NUMERIC(10,2) NOT NULL CHECK (price >= 0),
    stock INT NOT NULL CHECK (stock >= 0)
);

CREATE TABLE orders (
    order_id INT PRIMARY KEY,
    customer_id INT NOT NULL REFERENCES customers(customer_id),
    order_date DATE NOT NULL,
    status VARCHAR(20) NOT NULL,
    total_amount NUMERIC(10,2) NOT NULL CHECK (total_amount >= 0)
);

CREATE TABLE order_items (
    order_item_id INT PRIMARY KEY,
    order_id INT NOT NULL REFERENCES orders(order_id),
    product_id INT NOT NULL REFERENCES products(product_id),
    quantity INT NOT NULL CHECK (quantity > 0),
    unit_price NUMERIC(10,2) NOT NULL CHECK (unit_price >= 0)
);
```

## 2.3 Insert Department Data

```sql
INSERT INTO departments VALUES
(1, 'Engineering', 'Hyderabad'),
(2, 'HR', 'Bengaluru'),
(3, 'Finance', 'Hyderabad'),
(4, 'Sales', 'Pune'),
(5, 'Support', 'Chennai');
```

## 2.4 Insert Employee Data

```sql
INSERT INTO employees VALUES
(101, 'Aarav', 'aarav@company.com', 1, NULL, 120000, '2020-01-15', 'Hyderabad', 'ACTIVE'),
(102, 'Diya', 'diya@company.com', 1, 101, 90000, '2021-03-10', 'Hyderabad', 'ACTIVE'),
(103, 'Kabir', 'kabir@company.com', 1, 101, 75000, '2022-07-01', 'Pune', 'ACTIVE'),
(104, 'Meera', 'meera@company.com', 2, NULL, 80000, '2019-11-20', 'Bengaluru', 'ACTIVE'),
(105, 'Ishaan', 'ishaan@company.com', 3, NULL, 110000, '2018-05-05', 'Hyderabad', 'ACTIVE'),
(106, 'Sara', 'sara@company.com', 3, 105, 70000, '2023-02-14', 'Hyderabad', 'ACTIVE'),
(107, 'Vikram', 'vikram@company.com', 4, NULL, 95000, '2020-09-09', 'Pune', 'INACTIVE'),
(108, 'Anya', 'anya@company.com', 4, 107, 65000, '2022-12-12', 'Pune', 'ACTIVE'),
(109, 'Rohan', 'rohan@company.com', NULL, NULL, 60000, '2024-01-08', 'Chennai', 'ACTIVE');
```

## 2.5 Insert Customer Data

```sql
INSERT INTO customers VALUES
(1, 'Acme Ltd', 'acme@example.com', 'Hyderabad', '2024-01-10'),
(2, 'Bright Tech', 'bright@example.com', 'Bengaluru', '2024-02-15'),
(3, 'Cloud Nine', 'cloud@example.com', 'Pune', '2024-03-20'),
(4, 'Delta Corp', 'delta@example.com', 'Hyderabad', '2024-04-05'),
(5, 'Echo Stores', 'echo@example.com', 'Chennai', '2024-05-18'),
(6, 'Fresh Mart', 'fresh@example.com', 'Mumbai', '2024-06-01');
```

## 2.6 Insert Product Data

```sql
INSERT INTO products VALUES
(201, 'Laptop', 'Electronics', 70000, 10),
(202, 'Monitor', 'Electronics', 15000, 25),
(203, 'Keyboard', 'Accessories', 2000, 50),
(204, 'Mouse', 'Accessories', 1000, 80),
(205, 'Office Chair', 'Furniture', 12000, 15),
(206, 'Desk', 'Furniture', 18000, 8),
(207, 'Webcam', 'Electronics', 5000, 0);
```

## 2.7 Insert Orders Data

```sql
INSERT INTO orders VALUES
(1001, 1, '2024-07-01', 'DELIVERED', 74000),
(1002, 2, '2024-07-03', 'DELIVERED', 17000),
(1003, 1, '2024-07-10', 'CANCELLED', 12000),
(1004, 3, '2024-07-12', 'SHIPPED', 19000),
(1005, 4, '2024-08-01', 'DELIVERED', 70000),
(1006, 2, '2024-08-05', 'PENDING', 3000),
(1007, 5, '2024-08-10', 'DELIVERED', 18000);
```

## 2.8 Insert Order Items

```sql
INSERT INTO order_items VALUES
(1, 1001, 201, 1, 70000),
(2, 1001, 203, 2, 2000),
(3, 1002, 202, 1, 15000),
(4, 1002, 204, 2, 1000),
(5, 1003, 205, 1, 12000),
(6, 1004, 206, 1, 18000),
(7, 1004, 204, 1, 1000),
(8, 1005, 201, 1, 70000),
(9, 1006, 203, 1, 2000),
(10, 1006, 204, 1, 1000),
(11, 1007, 206, 1, 18000);
```

## 2.9 Verify Sample Data

```sql
SELECT * FROM departments;
SELECT * FROM employees;
SELECT * FROM customers;
SELECT * FROM products;
SELECT * FROM orders;
SELECT * FROM order_items;
```

---

# 3. SELECT and Filtering

## 3.1 SELECT ⭐⭐⭐⭐⭐

Retrieve all columns:

```sql
SELECT * FROM employees;
```

Retrieve specific columns:

```sql
SELECT employee_id, employee_name, salary
FROM employees;
```

**Best practice:** Prefer explicit columns over SELECT * in application code.

## 3.2 Column Aliases

```sql
SELECT
    employee_name AS name,
    salary AS monthly_salary,
    salary * 12 AS annual_salary
FROM employees;
```

## 3.3 DISTINCT ⭐⭐⭐⭐⭐

Retrieve unique cities:

```sql
SELECT DISTINCT city
FROM employees;
```

Retrieve unique combinations:

```sql
SELECT DISTINCT department_id, city
FROM employees;
```

DISTINCT applies to the complete selected column combination.

## 3.4 WHERE ⭐⭐⭐⭐⭐

```sql
SELECT *
FROM employees
WHERE salary > 80000;
```

Multiple conditions:

```sql
SELECT *
FROM employees
WHERE salary > 70000
AND city = 'Hyderabad';
```

## 3.5 BETWEEN

BETWEEN includes both boundary values.

```sql
SELECT *
FROM employees
WHERE salary BETWEEN 70000 AND 100000;
```

## 3.6 IN

```sql
SELECT *
FROM employees
WHERE city IN ('Hyderabad', 'Pune');
```

## 3.7 LIKE

| Pattern | Meaning |
|---|---|
| 'A%' | Starts with A |
| '%a' | Ends with a |
| '%a%' | Contains a |
| '_a%' | Second character is a |

```sql
SELECT * FROM employees
WHERE employee_name LIKE 'A%';

SELECT * FROM employees
WHERE employee_name LIKE '%a%';
```

PostgreSQL supports ILIKE for case-insensitive pattern matching.

## 3.8 ORDER BY ⭐⭐⭐⭐⭐

```sql
SELECT employee_name, salary
FROM employees
ORDER BY salary DESC;
```

Multiple sorting conditions:

```sql
SELECT employee_name, salary
FROM employees
ORDER BY salary DESC, employee_id ASC;
```

## 3.9 LIMIT and OFFSET

```sql
SELECT *
FROM employees
ORDER BY employee_id
LIMIT 5 OFFSET 2;
```

LIMIT and OFFSET syntax differs between database products.

---

# 4. NULL Values and Operators

## 4.1 What is NULL? ⭐⭐⭐⭐⭐

NULL represents missing, unknown or unavailable information.

NULL is not:
- Zero.
- Empty string.
- False.
- A normal value that can be compared with `=`.

Incorrect:

```sql
SELECT *
FROM employees
WHERE department_id = NULL;
```

Correct:

```sql
SELECT *
FROM employees
WHERE department_id IS NULL;
```

Find non-NULL values:

```sql
SELECT *
FROM employees
WHERE department_id IS NOT NULL;
```

## 4.2 COALESCE ⭐⭐⭐⭐

Returns the first non-NULL expression.

```sql
SELECT
    employee_name,
    COALESCE(CAST(department_id AS VARCHAR), 'Unassigned') AS department
FROM employees;
```

## 4.3 NULLIF ⭐⭐⭐⭐

Returns NULL when two expressions are equal.

```sql
SELECT 100.0 / NULLIF(stock, 0)
FROM products;
```

Useful for avoiding division by zero.

## 4.4 NOT IN vs NOT EXISTS ⭐⭐⭐⭐⭐

NOT IN can produce unexpected results when the subquery contains NULL.

Prefer NOT EXISTS for checking missing relationships:

```sql
SELECT c.*
FROM customers c
WHERE NOT EXISTS (
    SELECT 1
    FROM orders o
    WHERE o.customer_id = c.customer_id
);
```

---

# 5. Aggregate Functions and GROUP BY

## 5.1 Aggregate Functions ⭐⭐⭐⭐⭐

| Function | Description |
|---|---|
| COUNT(*) | Counts rows |
| COUNT(column) | Counts non-NULL values |
| COUNT(DISTINCT column) | Counts unique non-NULL values |
| SUM() | Calculates sum |
| AVG() | Calculates average |
| MIN() | Minimum value |
| MAX() | Maximum value |

Example:

```sql
SELECT
    COUNT(*) AS total_employees,
    COUNT(department_id) AS assigned_employees,
    AVG(salary) AS average_salary,
    MIN(salary) AS minimum_salary,
    MAX(salary) AS maximum_salary,
    SUM(salary) AS total_payroll
FROM employees;
```

**Important:** COUNT(*) includes rows containing NULL values. COUNT(column) ignores NULL values in that column.

## 5.2 GROUP BY ⭐⭐⭐⭐⭐

Find employee count per department:

```sql
SELECT department_id, COUNT(*) AS employee_count
FROM employees
GROUP BY department_id;
```

Find average salary per department:

```sql
SELECT department_id, AVG(salary) AS average_salary
FROM employees
GROUP BY department_id;
```

## 5.3 HAVING ⭐⭐⭐⭐⭐

Find departments with at least two employees:

```sql
SELECT department_id, COUNT(*) AS employee_count
FROM employees
GROUP BY department_id
HAVING COUNT(*) >= 2;
```

Difference:

- WHERE filters individual records before grouping.
- HAVING filters groups after aggregation.

## 5.4 Conditional Aggregation ⭐⭐⭐⭐⭐

Count active and inactive employees:

```sql
SELECT
    department_id,
    COUNT(*) AS total_employees,
    SUM(CASE WHEN status = 'ACTIVE' THEN 1 ELSE 0 END) AS active_count,
    SUM(CASE WHEN status = 'INACTIVE' THEN 1 ELSE 0 END) AS inactive_count
FROM employees
GROUP BY department_id;
```

## 5.5 LEFT JOIN and COUNT Trap ⭐⭐⭐⭐⭐

```sql
SELECT
    d.department_name,
    COUNT(e.employee_id) AS employee_count
FROM departments d
LEFT JOIN employees e
    ON d.department_id = e.department_id
GROUP BY d.department_id, d.department_name;
```

Use COUNT(e.employee_id), not COUNT(*), because an unmatched LEFT JOIN still returns one NULL-extended row.

---

# 6. SQL Joins

Joins combine rows from multiple tables based on a relationship or join condition.

## 6.1 INNER JOIN ⭐⭐⭐⭐⭐

Returns only matching rows.

```sql
SELECT
    e.employee_name,
    d.department_name
FROM employees e
INNER JOIN departments d
    ON e.department_id = d.department_id;
```

Employee 109 is excluded because the department does not match.

## 6.2 LEFT JOIN ⭐⭐⭐⭐⭐

Returns all records from the left table and matching records from the right table.

```sql
SELECT
    e.employee_name,
    d.department_name
FROM employees e
LEFT JOIN departments d
    ON e.department_id = d.department_id;
```

Find departments without employees:

```sql
SELECT d.*
FROM departments d
LEFT JOIN employees e
    ON d.department_id = e.department_id
WHERE e.employee_id IS NULL;
```

## 6.3 RIGHT JOIN ⭐⭐⭐⭐

Returns all rows from the right table and matching rows from the left table.

```sql
SELECT
    e.employee_name,
    d.department_name
FROM employees e
RIGHT JOIN departments d
    ON e.department_id = d.department_id;
```

A RIGHT JOIN can usually be rewritten as a LEFT JOIN by reversing table order.

## 6.4 FULL OUTER JOIN ⭐⭐⭐⭐

Returns matching rows and unmatched rows from both tables.

```sql
SELECT
    e.employee_name,
    d.department_name
FROM employees e
FULL OUTER JOIN departments d
    ON e.department_id = d.department_id;
```

MySQL does not directly support FULL OUTER JOIN; a UNION-based alternative is required.

## 6.5 CROSS JOIN ⭐⭐⭐

Returns the Cartesian product.

```sql
SELECT
    d.department_name,
    p.product_name
FROM departments d
CROSS JOIN products p;
```

5 departments × 7 products = 35 rows.

## 6.6 SELF JOIN ⭐⭐⭐⭐⭐

A table joins with itself.

Find employees and their managers:

```sql
SELECT
    e.employee_name AS employee,
    m.employee_name AS manager
FROM employees e
LEFT JOIN employees m
    ON e.manager_id = m.employee_id;
```

## 6.7 ON vs WHERE in LEFT JOIN ⭐⭐⭐⭐⭐

Keep every customer, but attach only delivered orders:

```sql
SELECT c.customer_name, o.order_id
FROM customers c
LEFT JOIN orders o
    ON c.customer_id = o.customer_id
    AND o.status = 'DELIVERED';
```

Compare:

```sql
SELECT c.customer_name, o.order_id
FROM customers c
LEFT JOIN orders o
    ON c.customer_id = o.customer_id
WHERE o.status = 'DELIVERED';
```

The second query removes customers without a delivered order. The WHERE condition filters out the NULL-extended rows.

## 6.8 Multiple Table Joins ⭐⭐⭐⭐⭐

```sql
SELECT
    o.order_id,
    c.customer_name,
    p.product_name,
    oi.quantity,
    oi.unit_price,
    oi.quantity * oi.unit_price AS line_total
FROM orders o
JOIN customers c
    ON o.customer_id = c.customer_id
JOIN order_items oi
    ON o.order_id = oi.order_id
JOIN products p
    ON oi.product_id = p.product_id
ORDER BY o.order_id, oi.order_item_id;
```

## 6.9 JOIN vs EXISTS ⭐⭐⭐⭐

Use EXISTS when you only need to know whether a matching row exists.

```sql
SELECT c.*
FROM customers c
WHERE EXISTS (
    SELECT 1
    FROM orders o
    WHERE o.customer_id = c.customer_id
);
```

EXISTS avoids multiplying parent rows when multiple matching child rows exist.

---

# 7. Subqueries and CTEs

## 7.1 What is a Subquery? ⭐⭐⭐⭐⭐

A query nested inside another SQL query.

### Scalar subquery

Returns one value.

```sql
SELECT employee_name, salary
FROM employees
WHERE salary > (
    SELECT AVG(salary)
    FROM employees
);
```

### Multi-row subquery

```sql
SELECT employee_name
FROM employees
WHERE department_id IN (
    SELECT department_id
    FROM departments
    WHERE location = 'Hyderabad'
);
```

## 7.2 Correlated Subquery ⭐⭐⭐⭐⭐

A correlated subquery references a column from the outer query.

Find employees earning more than their department average:

```sql
SELECT e.employee_name, e.salary, e.department_id
FROM employees e
WHERE e.department_id IS NOT NULL
AND e.salary > (
    SELECT AVG(x.salary)
    FROM employees x
    WHERE x.department_id = e.department_id
);
```

The optimizer may transform correlated subqueries into joins or other execution strategies.

## 7.3 CTE (Common Table Expression) ⭐⭐⭐⭐⭐

A CTE creates a named query block using WITH.

```sql
WITH department_average AS (
    SELECT department_id, AVG(salary) AS avg_salary
    FROM employees
    GROUP BY department_id
)
SELECT
    e.employee_name,
    e.salary,
    d.avg_salary
FROM employees e
JOIN department_average d
    ON e.department_id = d.department_id
WHERE e.salary > d.avg_salary;
```

CTEs improve readability and help organize complex queries. They are not automatically faster than subqueries.

## 7.4 Multiple CTEs

```sql
WITH delivered_orders AS (
    SELECT *
    FROM orders
    WHERE status = 'DELIVERED'
),
customer_totals AS (
    SELECT
        customer_id,
        SUM(total_amount) AS total_spent
    FROM delivered_orders
    GROUP BY customer_id
)
SELECT
    c.customer_name,
    t.total_spent
FROM customer_totals t
JOIN customers c
    ON t.customer_id = c.customer_id
ORDER BY t.total_spent DESC;
```

## 7.5 Recursive CTE ⭐⭐⭐

Useful for hierarchical data such as employee-manager relationships.

```sql
WITH RECURSIVE employee_hierarchy AS (
    SELECT
        employee_id,
        employee_name,
        manager_id,
        1 AS level
    FROM employees
    WHERE manager_id IS NULL

    UNION ALL

    SELECT
        e.employee_id,
        e.employee_name,
        e.manager_id,
        h.level + 1
    FROM employees e
    JOIN employee_hierarchy h
        ON e.manager_id = h.employee_id
)
SELECT *
FROM employee_hierarchy
ORDER BY level, employee_id;
```

For arbitrary hierarchical data, guard against cycles.

---

# 8. Set Operators

Set operators combine the results of multiple SELECT statements.

The queries must return the same number of compatible columns.

## 8.1 UNION ⭐⭐⭐⭐⭐

Combines results and removes duplicates.

```sql
SELECT city FROM employees
UNION
SELECT city FROM customers;
```

## 8.2 UNION ALL ⭐⭐⭐⭐⭐

Combines results without removing duplicates.

```sql
SELECT city FROM employees
UNION ALL
SELECT city FROM customers;
```

UNION ALL is generally less expensive because it does not need duplicate elimination.

## 8.3 INTERSECT

Returns rows common to both queries.

```sql
SELECT city FROM employees
INTERSECT
SELECT city FROM customers;
```

## 8.4 EXCEPT

Returns rows from the first query that are absent from the second.

```sql
SELECT city FROM employees
EXCEPT
SELECT city FROM customers;
```

Oracle uses MINUS for EXCEPT-like behavior. Availability varies by database version.

---

# 9. CASE and Built-in Functions

## 9.1 CASE ⭐⭐⭐⭐⭐

CASE implements conditional logic inside SQL.

```sql
SELECT
    employee_name,
    salary,
    CASE
        WHEN salary >= 100000 THEN 'HIGH'
        WHEN salary >= 70000 THEN 'MEDIUM'
        ELSE 'LOW'
    END AS salary_band
FROM employees;
```

Conditional aggregation:

```sql
SELECT
    SUM(
        CASE
            WHEN status = 'DELIVERED' THEN total_amount
            ELSE 0
        END
    ) AS delivered_revenue
FROM orders;
```

## 9.2 String Functions ⭐⭐⭐⭐

```sql
SELECT
    UPPER(employee_name),
    LOWER(email),
    LENGTH(employee_name),
    TRIM(employee_name),
    SUBSTRING(employee_name FROM 1 FOR 3)
FROM employees;
```

## 9.3 Numeric Functions ⭐⭐⭐⭐

```sql
SELECT
    ROUND(AVG(salary), 2),
    CEIL(AVG(salary)),
    FLOOR(AVG(salary)),
    ABS(-25)
FROM employees;
```

## 9.4 Date Functions ⭐⭐⭐⭐

```sql
SELECT CURRENT_DATE, CURRENT_TIMESTAMP;
```

Extract year:

```sql
SELECT
    employee_name,
    EXTRACT(YEAR FROM hire_date) AS hire_year
FROM employees;
```

Find employees hired during 2022:

```sql
SELECT *
FROM employees
WHERE hire_date >= DATE '2022-01-01'
AND hire_date < DATE '2023-01-01';
```

Prefer date ranges instead of applying functions to indexed date columns.

Date arithmetic syntax differs between PostgreSQL, MySQL and SQL Server.

---

# 10. Window Functions

**Very important for 5+ years of experience.**

Window functions perform calculations across related rows without collapsing rows like GROUP BY.

## 10.1 Window Function Syntax ⭐⭐⭐⭐⭐

```sql
function_name(expression)
OVER (
    PARTITION BY column
    ORDER BY column
)
```

- OVER defines the window.
- PARTITION BY divides rows into groups.
- ORDER BY determines the ordering within a window.
- Frame clauses control which rows participate in the calculation.

## 10.2 GROUP BY vs Window Function ⭐⭐⭐⭐⭐

GROUP BY returns one row per group:

```sql
SELECT department_id, AVG(salary)
FROM employees
GROUP BY department_id;
```

Window function preserves individual employee rows:

```sql
SELECT
    employee_name,
    department_id,
    salary,
    AVG(salary) OVER (
        PARTITION BY department_id
    ) AS department_average
FROM employees;
```

## 10.3 ROW_NUMBER vs RANK vs DENSE_RANK ⭐⭐⭐⭐⭐

```sql
SELECT
    employee_name,
    department_id,
    salary,
    ROW_NUMBER() OVER (
        PARTITION BY department_id
        ORDER BY salary DESC
    ) AS row_num,
    RANK() OVER (
        PARTITION BY department_id
        ORDER BY salary DESC
    ) AS rank_num,
    DENSE_RANK() OVER (
        PARTITION BY department_id
        ORDER BY salary DESC
    ) AS dense_rank_num
FROM employees;
```

Difference:

| Salary values | ROW_NUMBER | RANK | DENSE_RANK |
|---|---:|---:|---:|
| 100000 | 1 | 1 | 1 |
| 90000 | 2 | 2 | 2 |
| 90000 | 3 | 2 | 2 |
| 80000 | 4 | 4 | 3 |

- ROW_NUMBER: always assigns a unique sequence.
- RANK: same rank for ties, leaving gaps.
- DENSE_RANK: same rank for ties, without gaps.

## 10.4 Second-Highest Salary ⭐⭐⭐⭐⭐

```sql
WITH ranked AS (
    SELECT
        salary,
        DENSE_RANK() OVER (
            ORDER BY salary DESC
        ) AS rnk
    FROM employees
)
SELECT DISTINCT salary
FROM ranked
WHERE rnk = 2;
```

## 10.5 Top N Employees Per Department ⭐⭐⭐⭐⭐

```sql
WITH ranked AS (
    SELECT
        employee_id,
        employee_name,
        department_id,
        salary,
        ROW_NUMBER() OVER (
            PARTITION BY department_id
            ORDER BY salary DESC, employee_id
        ) AS rn
    FROM employees
)
SELECT *
FROM ranked
WHERE rn <= 2;
```

Use ROW_NUMBER for at most N rows. Use DENSE_RANK when ties at the Nth rank should also be included.

## 10.6 LAG and LEAD ⭐⭐⭐⭐

LAG retrieves a value from a previous row. LEAD retrieves a value from a following row.

```sql
SELECT
    order_id,
    customer_id,
    order_date,
    total_amount,
    LAG(total_amount) OVER (
        PARTITION BY customer_id
        ORDER BY order_date, order_id
    ) AS previous_order_amount,
    LEAD(total_amount) OVER (
        PARTITION BY customer_id
        ORDER BY order_date, order_id
    ) AS next_order_amount
FROM orders;
```

## 10.7 Running Total ⭐⭐⭐⭐⭐

```sql
SELECT
    customer_id,
    order_id,
    order_date,
    total_amount,
    SUM(total_amount) OVER (
        PARTITION BY customer_id
        ORDER BY order_date, order_id
        ROWS BETWEEN UNBOUNDED PRECEDING AND CURRENT ROW
    ) AS running_total
FROM orders;
```

Specify a ROWS frame for row-by-row running totals. Default frames with ORDER BY can include peer rows.

## 10.8 FIRST_VALUE and LAST_VALUE ⭐⭐⭐

```sql
SELECT
    employee_name,
    department_id,
    salary,
    FIRST_VALUE(employee_name) OVER (
        PARTITION BY department_id
        ORDER BY salary DESC
    ) AS highest_paid_employee
FROM employees;
```

For LAST_VALUE over the entire partition, specify:

```sql
ROWS BETWEEN UNBOUNDED PRECEDING AND UNBOUNDED FOLLOWING
```

Otherwise, the default frame may end at the current row or its peer group.

---

# 11. DDL and DML Operations

## 11.1 INSERT ⭐⭐⭐⭐⭐

```sql
INSERT INTO departments (
    department_id,
    department_name,
    location
)
VALUES (6, 'Legal', 'Hyderabad');
```

Insert multiple rows:

```sql
INSERT INTO products (
    product_id,
    product_name,
    category,
    price,
    stock
)
VALUES
(208, 'USB Hub', 'Accessories', 1500, 30),
(209, 'Headset', 'Electronics', 3500, 20);
```

## 11.2 UPDATE ⭐⭐⭐⭐⭐

```sql
UPDATE employees
SET salary = salary * 1.10
WHERE department_id = 1;
```

Always verify the WHERE condition before running an UPDATE.

## 11.3 DELETE ⭐⭐⭐⭐⭐

```sql
DELETE FROM orders
WHERE status = 'CANCELLED';
```

Without WHERE, all rows are deleted.

## 11.4 UPSERT ⭐⭐⭐⭐

PostgreSQL:

```sql
INSERT INTO products (
    product_id,
    product_name,
    category,
    price,
    stock
)
VALUES (208, 'USB Hub', 'Accessories', 1500, 30)
ON CONFLICT (product_id)
DO UPDATE SET
    price = EXCLUDED.price,
    stock = EXCLUDED.stock;
```

MySQL uses ON DUPLICATE KEY UPDATE. MERGE syntax and behavior vary between products.

## 11.5 ALTER TABLE ⭐⭐⭐⭐

```sql
ALTER TABLE employees
ADD COLUMN phone_number VARCHAR(20);
```

```sql
ALTER TABLE employees
DROP COLUMN phone_number;
```

## 11.6 DELETE vs TRUNCATE vs DROP ⭐⭐⭐⭐⭐

| Feature | DELETE | TRUNCATE | DROP |
|---|---|---|---|
| Removes selected rows | Yes | No | Removes object |
| Removes all rows | Yes | Yes | Yes, with object |
| WHERE support | Yes | No | No |
| Keeps table definition | Yes | Yes | No |
| Typical purpose | Selective deletion | Empty table | Remove object |

Transaction behavior, triggers, logging, identity reset, locks and foreign-key restrictions vary by database. PostgreSQL TRUNCATE is transactional.

---

# 12. Keys and Constraints

## 12.1 Primary Key ⭐⭐⭐⭐⭐

- Uniquely identifies each row.
- Cannot contain NULL.
- A table has one primary key constraint.
- It can contain multiple columns (composite primary key).

```sql
CREATE TABLE students (
    student_id INT PRIMARY KEY,
    student_name VARCHAR(100)
);
```

## 12.2 Foreign Key ⭐⭐⭐⭐⭐

Maintains referential integrity between tables.

```sql
CREATE TABLE orders_example (
    order_id INT PRIMARY KEY,
    customer_id INT,
    FOREIGN KEY (customer_id)
        REFERENCES customers(customer_id)
);
```

## 12.3 UNIQUE Constraint

Ensures values are unique. NULL handling differs between database products.

```sql
CREATE TABLE users (
    user_id INT PRIMARY KEY,
    email VARCHAR(150) UNIQUE
);
```

## 12.4 NOT NULL

```sql
CREATE TABLE employees_example (
    employee_id INT PRIMARY KEY,
    employee_name VARCHAR(100) NOT NULL
);
```

## 12.5 CHECK

```sql
CREATE TABLE accounts (
    account_id INT PRIMARY KEY,
    balance NUMERIC(12,2) CHECK (balance >= 0)
);
```

## 12.6 DEFAULT

```sql
CREATE TABLE application_users (
    user_id INT PRIMARY KEY,
    status VARCHAR(20) DEFAULT 'ACTIVE'
);
```

## 12.7 Composite Key

```sql
CREATE TABLE enrollments (
    student_id INT,
    course_id INT,
    enrolled_on DATE,
    PRIMARY KEY (student_id, course_id)
);
```

The combination of student_id and course_id must be unique.

## 12.8 Foreign Key Actions

Common actions:
- CASCADE
- SET NULL
- RESTRICT
- NO ACTION

Example:

```sql
FOREIGN KEY (customer_id)
REFERENCES customers(customer_id)
ON DELETE CASCADE
```

Use cascading deletion carefully because deleting a parent can delete related child rows.

---

# 13. Database Normalization

Normalization reduces duplicate data and helps avoid data anomalies.

## 13.1 First Normal Form (1NF)

- Each column contains atomic values.
- No repeating groups.
- Rows can be uniquely identified.

Avoid storing multiple phone numbers as comma-separated values in one column.

## 13.2 Second Normal Form (2NF)

- Must satisfy 1NF.
- Every non-key attribute depends on the whole candidate key.
- No partial dependency on part of a composite key.

## 13.3 Third Normal Form (3NF)

- Must satisfy 2NF.
- No inappropriate transitive dependency of non-key attributes on a key.

## 13.4 BCNF

Boyce-Codd Normal Form is stricter than 3NF. Every determinant must be a candidate key.

## 13.5 Denormalization

Intentionally stores duplicated or precomputed data to improve read performance.

Trade-offs:
- Faster reads in suitable workloads.
- More storage.
- More complicated writes.
- Risk of inconsistent duplicated values.

Use denormalization based on measured workload, not by default.

---

# 14. Transactions and ACID Properties

## 14.1 What is a Transaction? ⭐⭐⭐⭐⭐

A transaction is a logical unit of database work.

```sql
BEGIN;

UPDATE bank_accounts
SET balance = balance - 1000
WHERE account_id = 1;

UPDATE bank_accounts
SET balance = balance + 1000
WHERE account_id = 2;

COMMIT;
```

In production, validate sufficient balance and verify that the expected number of rows was updated. Roll back if a business condition fails.

## 14.2 ACID Properties ⭐⭐⭐⭐⭐

| Property | Meaning | Example |
|---|---|---|
| Atomicity | All or nothing | Both sides of a transfer succeed |
| Consistency | Valid database state is preserved | Constraints remain satisfied |
| Isolation | Concurrent transactions are controlled | Concurrent updates do not corrupt state |
| Durability | Committed changes survive failures according to DB guarantees | Committed transfer persists |

## 14.3 COMMIT and ROLLBACK

```sql
BEGIN;

UPDATE products
SET stock = stock - 1
WHERE product_id = 201;

COMMIT;
```

Rollback:

```sql
BEGIN;

UPDATE products
SET stock = stock - 1
WHERE product_id = 201;

ROLLBACK;
```

## 14.4 SAVEPOINT

```sql
BEGIN;

UPDATE products
SET stock = stock - 1
WHERE product_id = 201;

SAVEPOINT after_stock_update;

UPDATE orders
SET status = 'SHIPPED'
WHERE order_id = 1001;

ROLLBACK TO SAVEPOINT after_stock_update;

COMMIT;
```

A SAVEPOINT does not commit the transaction.

## 14.5 Isolation Levels ⭐⭐⭐⭐⭐

| Isolation level | Dirty reads | Non-repeatable reads | Phantoms |
|---|---|---|---|
| Read Uncommitted | Possible in standard model | Possible | Possible |
| Read Committed | Prevented | Possible | Possible |
| Repeatable Read | Prevented | Prevented | Standard model may allow |
| Serializable | Prevented | Prevented | Prevented through serial-equivalent execution |

Database implementations differ. PostgreSQL treats READ UNCOMMITTED as READ COMMITTED. PostgreSQL REPEATABLE READ uses snapshot isolation; serialization anomalies can still occur, so SERIALIZABLE may be needed for serial-equivalent behavior.

## 14.6 Concurrency Problems ⭐⭐⭐⭐⭐

**Dirty Read:** A transaction reads data written by another transaction that has not committed.

**Non-repeatable Read:** The same row is read twice, but its committed value changes between reads.

**Phantom Read:** A repeated range query returns a different set of rows.

**Lost Update:** One transaction overwrites another transaction's update.

**Write Skew:** Concurrent transactions make individually valid decisions that jointly violate a business invariant.

## 14.7 Optimistic Locking ⭐⭐⭐⭐

Uses a version column to detect concurrent modifications.

```sql
UPDATE inventory
SET quantity = quantity - 1,
    version = version + 1
WHERE product_id = 201
AND version = 5;
```

If zero rows are updated, the version may have changed. Reload, retry or report a conflict.

## 14.8 Pessimistic Locking ⭐⭐⭐⭐

Locks rows before updating them.

PostgreSQL-style example:

```sql
BEGIN;

SELECT stock
FROM products
WHERE product_id = 201
FOR UPDATE;

UPDATE products
SET stock = stock - 1
WHERE product_id = 201
AND stock > 0;

COMMIT;
```

Keep locking transactions short.

---

# 15. Indexes

## 15.1 What is an Index? ⭐⭐⭐⭐⭐

An index is an auxiliary data structure that helps the database locate rows efficiently.

Without a useful index, the database may scan many or all table rows.

With a useful index, it may locate matching rows without scanning the entire table.

Advantages:
- Faster suitable SELECT queries.
- Faster joins on indexed keys.
- Can help ORDER BY and range queries.

Disadvantages:
- Extra storage.
- Slower INSERT, UPDATE and DELETE in many cases.
- Index maintenance overhead.

A sequential scan is not necessarily bad. It may be cheaper for small tables or queries that return a large percentage of rows.

## 15.2 Create an Index ⭐⭐⭐⭐⭐

```sql
CREATE INDEX idx_employees_department
ON employees(department_id);
```

Index on order lookup:

```sql
CREATE INDEX idx_orders_customer_date
ON orders(customer_id, order_date);
```

## 15.3 Common Index Types ⭐⭐⭐⭐

| Index type | Typical usage |
|---|---|
| B-tree | Equality, range queries and sorting |
| Hash | Equality lookups |
| GIN | PostgreSQL JSONB, arrays and full-text search |
| GiST | PostgreSQL ranges, geometric and extensible searches |
| BRIN | Large tables with physical correlation |

Supported types and implementation details depend on the database.

## 15.4 Composite Index ⭐⭐⭐⭐⭐

An index on multiple columns:

```sql
CREATE INDEX idx_orders_customer_status_date
ON orders(customer_id, status, order_date);
```

A B-tree composite index is generally most useful when queries filter using the leading column, optionally followed by the next columns.

For example:

```sql
SELECT *
FROM orders
WHERE customer_id = 1
AND status = 'DELIVERED';
```

A query filtering only on order_date may not benefit efficiently from this index because order_date is not the leading column.

This is commonly called the leftmost-prefix principle.

## 15.5 Covering Index ⭐⭐⭐⭐

An index that contains all columns needed by a query may avoid additional table lookups.

PostgreSQL example:

```sql
CREATE INDEX idx_orders_customer_date_amount
ON orders(customer_id, order_date)
INCLUDE(total_amount);
```

Query:

```sql
SELECT order_date, total_amount
FROM orders
WHERE customer_id = 1
ORDER BY order_date;
```

An index-only scan may be possible depending on visibility and storage conditions.

## 15.6 Partial Index ⭐⭐⭐

PostgreSQL example:

```sql
CREATE INDEX idx_active_employee_department
ON employees(department_id)
WHERE status = 'ACTIVE';
```

Useful when queries frequently target a stable subset of rows.

## 15.7 When an Index May Not Help ⭐⭐⭐⭐⭐

- Very small tables.
- Queries returning most of the table.
- Low-selectivity conditions.
- Applying a function to an indexed column.
- Leading wildcard searches with a normal B-tree.
- Implicit type conversion on an indexed column.
- Composite index order does not match the query.
- Stale database statistics.

**Interview point:** Never say "add an index" without first checking the execution plan and the actual workload.

---

# 16. SQL Query Optimization

**One of the most important sections for experienced backend interviews.**

For a 5+ YOE Java developer, interviewers expect you to explain how you diagnose and optimize slow database queries.

## 16.1 Query Optimization Workflow ⭐⭐⭐⭐⭐

1. Identify the slow API or database query.
2. Capture the exact SQL and representative parameter values.
3. Measure baseline execution time and workload.
4. Inspect the execution plan.
5. Check estimated rows versus actual rows.
6. Check indexes, joins, filters, sorts and database IO.
7. Reduce unnecessary columns and rows.
8. Consider query rewrites and indexes.
9. Compare before and after performance.
10. Validate correctness and monitor after deployment.

## 16.2 EXPLAIN ⭐⭐⭐⭐⭐

EXPLAIN shows the query execution plan.

```sql
EXPLAIN
SELECT *
FROM orders
WHERE customer_id = 1;
```

It helps identify:
- Sequential scans.
- Index scans.
- Join strategies.
- Sort operations.
- Estimated row counts.
- Estimated costs.

## 16.3 EXPLAIN ANALYZE ⭐⭐⭐⭐⭐

PostgreSQL example:

```sql
EXPLAIN (ANALYZE, BUFFERS)
SELECT
    order_id,
    order_date,
    total_amount
FROM orders
WHERE customer_id = 1;
```

EXPLAIN ANALYZE actually executes the query and reports actual rows and timings.

BUFFERS provides PostgreSQL buffer activity information.

**Important:** EXPLAIN ANALYZE on UPDATE or DELETE really executes the modification. Use a safe test environment or a transaction that you roll back.

## 16.4 Sequential Scan vs Index Scan

**Sequential Scan:** Reads the table's rows.

**Index Scan:** Uses an index to locate relevant rows.

An index scan is not always faster. If a query returns a large percentage of the table, a sequential scan may be more efficient.

## 16.5 Avoid SELECT * ⭐⭐⭐⭐⭐

Less intentional:

```sql
SELECT *
FROM orders
WHERE customer_id = 1;
```

Better:

```sql
SELECT
    order_id,
    order_date,
    status,
    total_amount
FROM orders
WHERE customer_id = 1;
```

Benefits:
- Less data transferred.
- Lower application memory usage.
- Smaller result sets.
- Clearer query contract.

## 16.6 Write Sargable Queries ⭐⭐⭐⭐⭐

Sargable predicates allow an optimizer to use an index efficiently when appropriate.

Less index-friendly:

```sql
SELECT *
FROM orders
WHERE EXTRACT(YEAR FROM order_date) = 2024;
```

Prefer:

```sql
SELECT *
FROM orders
WHERE order_date >= DATE '2024-01-01'
AND order_date < DATE '2025-01-01';
```

This avoids applying a function to the indexed date column.

If function-based filtering is essential, consider an appropriate expression index.

## 16.7 LIKE and Index Optimization ⭐⭐⭐⭐

Prefix search:

```sql
SELECT *
FROM customers
WHERE customer_name LIKE 'Bright%';
```

A B-tree may support this depending on collation and operator class.

Leading wildcard:

```sql
SELECT *
FROM customers
WHERE customer_name LIKE '%Tech';
```

A normal B-tree generally cannot efficiently seek for arbitrary substring matches.

Consider trigram indexes, full-text search or a dedicated search system when appropriate.

## 16.8 Use Appropriate Composite Indexes ⭐⭐⭐⭐⭐

Consider:

```sql
SELECT
    order_id,
    order_date,
    total_amount
FROM orders
WHERE customer_id = 1
AND status = 'DELIVERED'
ORDER BY order_date DESC;
```

A candidate PostgreSQL index to test:

```sql
CREATE INDEX idx_orders_customer_status_date
ON orders(customer_id, status, order_date DESC)
INCLUDE(total_amount);
```

Do not add it blindly. Check the execution plan, selectivity, write frequency and existing indexes.

## 16.9 Avoid Unnecessary DISTINCT ⭐⭐⭐⭐

DISTINCT may require sorting or hashing to eliminate duplicate rows.

If duplicates are caused by an incorrect join, fix the join instead of hiding the problem with DISTINCT.

## 16.10 EXISTS for Existence Checks ⭐⭐⭐⭐

```sql
SELECT c.customer_id
FROM customers c
WHERE EXISTS (
    SELECT 1
    FROM orders o
    WHERE o.customer_id = c.customer_id
);
```

EXISTS is useful when you need to check whether matching rows exist without returning child records.

## 16.11 Eliminate N+1 Queries ⭐⭐⭐⭐⭐

A common Java/Hibernate performance problem.

Suppose an API fetches 100 customers.

Bad approach:
1. One query retrieves 100 customers.
2. One query per customer retrieves orders.
3. Total: 101 queries.

Better approach:

```sql
SELECT
    c.customer_id,
    c.customer_name,
    o.order_id,
    o.order_date
FROM customers c
LEFT JOIN orders o
    ON c.customer_id = o.customer_id
WHERE c.customer_id IN (1, 2, 3);
```

In Java/Hibernate, consider:
- DTO projections.
- Fetch joins.
- Entity graphs.
- Batch fetching.
- Two-step batch loading.

For paginated parent records with one-to-many collections, joins can duplicate parent rows and interfere with pagination. One approach is to page parent IDs first and then batch-fetch child records.

## 16.12 LIMIT and Early Stop ⭐⭐⭐⭐

```sql
SELECT
    order_id,
    order_date
FROM orders
WHERE customer_id = 1
ORDER BY order_date DESC, order_id DESC
LIMIT 10;
```

With a suitable index, the database may stop after finding enough matching rows.

## 16.13 Batch Inserts and Updates ⭐⭐⭐⭐

Instead of executing many separate INSERT statements, batch operations where appropriate.

```sql
INSERT INTO products (
    product_id,
    product_name,
    category,
    price,
    stock
)
VALUES
(210, 'Cable', 'Accessories', 500, 100),
(211, 'Dock', 'Electronics', 8000, 20),
(212, 'Stand', 'Accessories', 1200, 40);
```

In Java, JDBC batching reduces network round trips and transaction overhead. Tune batch size based on the driver, database and application memory.

## 16.14 Database Statistics ⭐⭐⭐⭐

The query optimizer uses statistics to estimate row counts and choose plans.

PostgreSQL:

```sql
ANALYZE employees;
```

Stale statistics can cause poor execution plans. PostgreSQL autovacuum normally maintains statistics; manual ANALYZE can help after large data changes or during diagnosis.

## 16.15 Partitioning ⭐⭐⭐

Partitioning splits a large logical table into smaller physical partitions.

Common approaches:
- Range partitioning.
- List partitioning.
- Hash partitioning.

Example use case: partition orders by order_date.

Benefits:
- Partition pruning can skip irrelevant data.
- Helps manage large tables.
- Can improve maintenance operations.

Partitioning is not a substitute for indexes and good SQL. It adds operational complexity.

## 16.16 SQL Optimization Interview Answer ⭐⭐⭐⭐⭐

**Question: How do you optimize a slow SQL query?**

Suggested answer:

"I first capture the slow SQL and representative parameter values, then inspect the actual execution plan. I compare estimated and actual row counts and look for full scans, inefficient joins, sorts, excessive IO, locks and missing or unsuitable indexes.

Then I check whether the query retrieves unnecessary columns or rows, whether its predicates are sargable, and whether there are N+1 round trips from the application.

Based on evidence, I may add a suitable composite index, rewrite a date filter, reduce the projection, use EXISTS, batch database operations or change the fetching strategy. Finally, I compare execution time, rows read, buffer activity and write overhead, validate the result and monitor after deployment."

---

# 17. Locks, Deadlocks and MVCC

## 17.1 What is a Lock? ⭐⭐⭐⭐⭐

A database lock controls concurrent access to data.

Common concepts:
- Shared locks.
- Exclusive locks.
- Row-level locks.
- Table-level locks.

Exact lock modes differ by database.

## 17.2 Deadlock ⭐⭐⭐⭐⭐

A deadlock occurs when two or more transactions wait for each other in a cycle.

Example:

```text
Transaction 1:
Locks Row A
Requests Row B

Transaction 2:
Locks Row B
Requests Row A
```

Neither can continue until the database detects the deadlock and aborts one transaction.

Prevention:
- Acquire locks in a consistent order.
- Keep transactions short.
- Avoid remote API calls while holding database locks.
- Index update/delete predicates appropriately.
- Implement safe retries for retryable deadlock failures.

## 17.3 MVCC ⭐⭐⭐⭐

Multi-Version Concurrency Control maintains multiple versions of rows.

It allows readers to see a consistent snapshot while reducing conflicts between readers and writers.

PostgreSQL and other database systems use MVCC-like approaches, but their implementations and cleanup mechanisms differ.

---

# 18. Views, Materialized Views and Triggers

## 18.1 View ⭐⭐⭐⭐

A view is a named SQL query.

```sql
CREATE VIEW active_employees AS
SELECT
    employee_id,
    employee_name,
    department_id,
    salary
FROM employees
WHERE status = 'ACTIVE';
```

Use it:

```sql
SELECT *
FROM active_employees;
```

Advantages:
- Reuse queries.
- Simplify complex joins.
- Provide abstraction.
- Support controlled access to selected columns or rows.

A normal view generally stores the query definition, not the result rows.

## 18.2 Materialized View ⭐⭐⭐⭐

A materialized view stores query results physically.

```sql
CREATE MATERIALIZED VIEW department_summary AS
SELECT
    department_id,
    AVG(salary) AS average_salary,
    COUNT(*) AS employee_count
FROM employees
GROUP BY department_id;
```

Refresh:

```sql
REFRESH MATERIALIZED VIEW department_summary;
```

Useful for expensive reporting queries where some data staleness is acceptable.

## 18.3 Stored Procedures and Functions ⭐⭐⭐

Stored procedures/functions execute logic inside the database. Syntax and transaction behavior vary between products.

## 18.4 Triggers ⭐⭐⭐

Triggers execute automatically when specified database events occur, such as INSERT, UPDATE or DELETE.

Typical use cases:
- Audit logging.
- Maintaining derived information.
- Enforcing specialized database rules.

Triggers can introduce hidden side effects and make debugging more complicated. Use them intentionally.

---

# 19. SQL Pagination

## 19.1 Offset Pagination ⭐⭐⭐⭐

```sql
SELECT
    order_id,
    order_date,
    total_amount
FROM orders
ORDER BY order_date DESC, order_id DESC
LIMIT 20 OFFSET 100;
```

Returns 20 records after skipping the first 100.

Advantages:
- Simple.
- Supports page numbers.
- Easy to implement.

Disadvantages:
- Deep offsets can be expensive.
- Changes to data between requests can cause records to shift between pages.

Always use deterministic ordering, including a unique tie-breaker.

## 19.2 Keyset / Cursor Pagination ⭐⭐⭐⭐⭐

Uses the last seen sort key to retrieve the next page.

```sql
SELECT
    order_id,
    order_date,
    total_amount
FROM orders
WHERE (order_date, order_id) < (DATE '2024-08-01', 1005)
ORDER BY order_date DESC, order_id DESC
LIMIT 20;
```

Potential supporting index:

```sql
CREATE INDEX idx_orders_date_id
ON orders(order_date DESC, order_id DESC);
```

Advantages:
- Efficient for deep pagination.
- More stable navigation when rows are inserted before the current cursor.
- Avoids skipping a large number of rows.

The cursor must include all sort columns. Nullable sort keys need special handling.

---

# 20. Top SQL Interview Questions

Practice these questions without looking at the solutions first.

All queries use the sample database from Section 2.

## Q1. Find the second-highest salary ⭐⭐⭐⭐⭐

```sql
SELECT MAX(salary)
FROM employees
WHERE salary < (
    SELECT MAX(salary)
    FROM employees
);
```

Alternative using DENSE_RANK:

```sql
WITH ranked AS (
    SELECT
        salary,
        DENSE_RANK() OVER (
            ORDER BY salary DESC
        ) AS rnk
    FROM employees
)
SELECT DISTINCT salary
FROM ranked
WHERE rnk = 2;
```

## Q2. Find the Nth-highest distinct salary ⭐⭐⭐⭐⭐

Replace 3 with the required rank:

```sql
WITH ranked AS (
    SELECT
        salary,
        DENSE_RANK() OVER (
            ORDER BY salary DESC
        ) AS rnk
    FROM employees
)
SELECT DISTINCT salary
FROM ranked
WHERE rnk = 3;
```

## Q3. Find the highest-paid employee in each department ⭐⭐⭐⭐⭐

```sql
WITH ranked AS (
    SELECT
        e.*,
        DENSE_RANK() OVER (
            PARTITION BY department_id
            ORDER BY salary DESC
        ) AS rnk
    FROM employees e
)
SELECT *
FROM ranked
WHERE rnk = 1;
```

DENSE_RANK includes ties.

## Q4. Find the top two employees per department ⭐⭐⭐⭐⭐

```sql
WITH ranked AS (
    SELECT
        e.*,
        ROW_NUMBER() OVER (
            PARTITION BY department_id
            ORDER BY salary DESC, employee_id
        ) AS rn
    FROM employees e
)
SELECT *
FROM ranked
WHERE rn <= 2;
```

## Q5. Find employees earning more than their department average ⭐⭐⭐⭐⭐

```sql
SELECT e.*
FROM employees e
WHERE e.department_id IS NOT NULL
AND e.salary > (
    SELECT AVG(x.salary)
    FROM employees x
    WHERE x.department_id = e.department_id
);
```

## Q6. Find departments without employees ⭐⭐⭐⭐⭐

```sql
SELECT d.*
FROM departments d
WHERE NOT EXISTS (
    SELECT 1
    FROM employees e
    WHERE e.department_id = d.department_id
);
```

## Q7. Find customers who never placed an order ⭐⭐⭐⭐⭐

```sql
SELECT c.*
FROM customers c
WHERE NOT EXISTS (
    SELECT 1
    FROM orders o
    WHERE o.customer_id = c.customer_id
);
```

Expected customer: Fresh Mart.

## Q8. Find customers who placed more than one order ⭐⭐⭐⭐⭐

```sql
SELECT
    c.customer_id,
    c.customer_name,
    COUNT(o.order_id) AS order_count
FROM customers c
JOIN orders o
    ON c.customer_id = o.customer_id
GROUP BY c.customer_id, c.customer_name
HAVING COUNT(o.order_id) > 1;
```

## Q9. Find delivered order amount per customer ⭐⭐⭐⭐⭐

```sql
SELECT
    c.customer_id,
    c.customer_name,
    SUM(o.total_amount) AS delivered_total
FROM customers c
JOIN orders o
    ON c.customer_id = o.customer_id
WHERE o.status = 'DELIVERED'
GROUP BY c.customer_id, c.customer_name
ORDER BY delivered_total DESC;
```

## Q10. Find the latest order per customer ⭐⭐⭐⭐⭐

```sql
WITH ranked AS (
    SELECT
        o.*,
        ROW_NUMBER() OVER (
            PARTITION BY customer_id
            ORDER BY order_date DESC, order_id DESC
        ) AS rn
    FROM orders o
)
SELECT *
FROM ranked
WHERE rn = 1;
```

## Q11. Find products that have never been ordered ⭐⭐⭐⭐

```sql
SELECT p.*
FROM products p
WHERE NOT EXISTS (
    SELECT 1
    FROM order_items oi
    WHERE oi.product_id = p.product_id
);
```

Expected product: Webcam.

## Q12. Find the most expensive product in each category ⭐⭐⭐⭐

```sql
WITH ranked AS (
    SELECT
        p.*,
        DENSE_RANK() OVER (
            PARTITION BY category
            ORDER BY price DESC
        ) AS rnk
    FROM products p
)
SELECT *
FROM ranked
WHERE rnk = 1;
```

## Q13. Find duplicate employee emails ⭐⭐⭐⭐⭐

```sql
SELECT
    email,
    COUNT(*)
FROM employees
GROUP BY email
HAVING COUNT(*) > 1;
```

The sample data has no duplicate non-NULL emails.

## Q14. Find employees hired in the last three years ⭐⭐⭐⭐

```sql
SELECT *
FROM employees
WHERE hire_date >= CURRENT_DATE - INTERVAL '3 years';
```

Use a fixed reference date if the interviewer expects deterministic sample output.

## Q15. Find the first order date per customer ⭐⭐⭐⭐

```sql
SELECT
    customer_id,
    MIN(order_date) AS first_order_date
FROM orders
GROUP BY customer_id;
```

## Q16. Calculate a running total per customer ⭐⭐⭐⭐⭐

```sql
SELECT
    customer_id,
    order_id,
    order_date,
    total_amount,
    SUM(total_amount) OVER (
        PARTITION BY customer_id
        ORDER BY order_date, order_id
        ROWS BETWEEN UNBOUNDED PRECEDING AND CURRENT ROW
    ) AS running_total
FROM orders;
```

## Q17. Find employees sharing the same salary ⭐⭐⭐⭐

```sql
SELECT e.*
FROM employees e
WHERE EXISTS (
    SELECT 1
    FROM employees x
    WHERE x.salary = e.salary
    AND x.employee_id <> e.employee_id
);
```

## Q18. Calculate each department's percentage of total payroll ⭐⭐⭐⭐

```sql
SELECT
    department_id,
    SUM(salary) AS department_payroll,
    ROUND(
        100.0 * SUM(salary) /
        NULLIF(SUM(SUM(salary)) OVER (), 0),
        2
    ) AS payroll_percentage
FROM employees
GROUP BY department_id;
```

## Q19. Find the second order per customer ⭐⭐⭐⭐

```sql
WITH ranked AS (
    SELECT
        o.*,
        ROW_NUMBER() OVER (
            PARTITION BY customer_id
            ORDER BY order_date, order_id
        ) AS rn
    FROM orders o
)
SELECT *
FROM ranked
WHERE rn = 2;
```

## Q20. Find orders whose total differs from the sum of line items ⭐⭐⭐⭐

```sql
SELECT
    o.order_id,
    o.total_amount,
    COALESCE(
        SUM(oi.quantity * oi.unit_price),
        0
    ) AS calculated_total
FROM orders o
LEFT JOIN order_items oi
    ON o.order_id = oi.order_id
GROUP BY o.order_id, o.total_amount
HAVING o.total_amount <> COALESCE(
    SUM(oi.quantity * oi.unit_price),
    0
);
```

This is a reconciliation query. Inspect the returned rows rather than assuming all totals match.

## Q21. Find the department with the highest average salary ⭐⭐⭐⭐

```sql
SELECT
    d.department_name,
    AVG(e.salary) AS average_salary
FROM departments d
JOIN employees e
    ON d.department_id = e.department_id
GROUP BY d.department_id, d.department_name
ORDER BY average_salary DESC
LIMIT 1;
```

Use DENSE_RANK on grouped averages if ties must be returned.

## Q22. Find consecutive dates or gaps-and-islands ⭐⭐⭐

Use LAG() to compare each row with the previous row. Partition by the relevant entity, order by date, identify where the gap exceeds the allowed interval, and use a running sum to assign groups.

Clarify what constitutes a consecutive sequence before writing the query.

## Q23. Difference between WHERE and HAVING ⭐⭐⭐⭐⭐

- WHERE filters rows before grouping.
- HAVING filters grouped results after aggregation.

## Q24. Difference between UNION and UNION ALL ⭐⭐⭐⭐⭐

- UNION removes duplicates.
- UNION ALL preserves duplicates and is generally cheaper.

## Q25. Difference between DELETE, TRUNCATE and DROP ⭐⭐⭐⭐⭐

- DELETE removes selected or all rows.
- TRUNCATE empties the table.
- DROP removes the database object.

Transaction, logging, trigger, identity and locking behavior depends on the DBMS.

## Q26. Clustered vs Nonclustered Index ⭐⭐⭐⭐

Terminology and storage behavior are database-specific.

- SQL Server clustered index defines the row storage order at the leaf level; a table can have one clustered index.
- InnoDB organizes table data by primary key.
- PostgreSQL does not automatically maintain a clustered index; CLUSTER rewrites physical order once, and later writes do not preserve it.

## Q27. What is a covering index? ⭐⭐⭐⭐

An index that contains all columns required by a query may avoid additional table lookups. Exact implementation depends on the database.

## Q28. What is a correlated subquery? ⭐⭐⭐⭐

A subquery that references a column from the outer query. The optimizer may transform it into a join or another execution strategy.

## Q29. What is a deadlock? ⭐⭐⭐⭐⭐

Two or more transactions wait on each other's locks in a cycle. The database detects the deadlock and aborts one transaction.

## Q30. What is the N+1 problem? ⭐⭐⭐⭐⭐

One query fetches parent records, followed by one child query per parent. Use suitable fetch joins, entity graphs, DTO projections, batch fetching or two-step batch loading.

## Q31. How do you optimize a slow SQL query? ⭐⭐⭐⭐⭐

Explain the workflow:
1. Measure baseline.
2. Inspect actual execution plan.
3. Compare estimated and actual rows.
4. Check indexes, joins, IO, sorts and locks.
5. Reduce unnecessary data.
6. Make a targeted change.
7. Compare before and after.
8. Validate correctness and write impact.

---

# 21. SQL with Java, JDBC, Spring and Hibernate

This section is particularly important for Java Full Stack interviews.

## 21.1 JDBC PreparedStatement ⭐⭐⭐⭐⭐

```java
String sql =
    "SELECT employee_id, employee_name, salary " +
    "FROM employees WHERE department_id = ? AND status = ?";

try (Connection con = dataSource.getConnection();
     PreparedStatement ps = con.prepareStatement(sql)) {

    ps.setInt(1, departmentId);
    ps.setString(2, "ACTIVE");

    try (ResultSet rs = ps.executeQuery()) {
        while (rs.next()) {
            int id = rs.getInt("employee_id");
            String name = rs.getString("employee_name");
            BigDecimal salary = rs.getBigDecimal("salary");
        }
    }
}
```

Benefits:
- Parameter binding.
- Protection against SQL injection when used correctly.
- Correct type handling.
- Potential statement/plan reuse depending on driver and database.

Parameters cannot replace table or column identifiers. Use allow-lists for dynamic identifiers.

## 21.2 SQL Injection ⭐⭐⭐⭐⭐

Unsafe:

```java
String sql =
    "SELECT * FROM users WHERE email = '" + email + "'";
```

Safe:

```java
String sql =
    "SELECT * FROM users WHERE email = ?";

PreparedStatement ps = con.prepareStatement(sql);
ps.setString(1, email);
```

## 21.3 Hibernate and JPA ⭐⭐⭐⭐⭐

Important concepts:

- Entity lifecycle.
- Persistence context.
- Lazy loading.
- Eager loading.
- Dirty checking.
- Cascades.
- Orphan removal.
- First-level cache.
- Optional second-level cache.
- JPQL vs native SQL.
- Flush vs commit.
- JDBC batching.
- N+1 queries.
- Pagination with collection fetch joins.

**Interview question: How do you solve N+1 in Hibernate?**

Possible solutions:
- DTO projections.
- Fetch joins.
- Entity graphs.
- Batch fetching.
- Two-step batch loading.

Avoid changing every relationship to EAGER. It can produce huge joins and unnecessary data retrieval.

## 21.4 Spring @Transactional ⭐⭐⭐⭐⭐

```java
@Service
public class TransferService {

    @Transactional
    public void transfer(
        long fromId,
        long toId,
        BigDecimal amount
    ) {
        // Load accounts.
        // Validate sufficient balance.
        // Debit source account.
        // Credit destination account.
    }
}
```

Important interview points:
- Spring generally rolls back for unchecked exceptions 

# 22. SQL Revision Cheat Sheet

A quick revision reference for SQL concepts frequently asked in Java Full Stack interviews.

## 22.1 SQL Command Categories ⭐⭐⭐⭐⭐

| Category | Commands                      | Purpose                   |
| -------- | ----------------------------- | ------------------------- |
| DDL      | CREATE, ALTER, DROP, TRUNCATE | Define database structure |
| DQL      | SELECT                        | Retrieve data             |
| DML      | INSERT, UPDATE, DELETE, MERGE | Modify data               |
| DCL      | GRANT, REVOKE                 | Manage permissions        |
| TCL      | COMMIT, ROLLBACK, SAVEPOINT   | Manage transactions       |

## 22.2 SQL Query Execution Order ⭐⭐⭐⭐⭐

Remember this logical execution order:

1. FROM and JOIN
2. WHERE
3. GROUP BY
4. HAVING
5. SELECT
6. DISTINCT
7. ORDER BY
8. LIMIT / OFFSET

**Interview point:** WHERE filters rows before aggregation; HAVING filters groups after aggregation.

## 22.3 WHERE vs HAVING ⭐⭐⭐⭐⭐

| WHERE                                   | HAVING                           |
| --------------------------------------- | -------------------------------- |
| Filters individual rows                 | Filters grouped results          |
| Applied before GROUP BY                 | Applied after GROUP BY           |
| Cannot directly use aggregate functions | Can use aggregate functions      |
| Reduces rows before aggregation         | Reduces groups after aggregation |

```sql
SELECT department_id, AVG(salary) AS avg_salary
FROM employees
WHERE status = 'ACTIVE'
GROUP BY department_id
HAVING AVG(salary) > 70000;
```

## 22.4 JOIN Types ⭐⭐⭐⭐⭐

| JOIN            | Result                                          |
| --------------- | ----------------------------------------------- |
| INNER JOIN      | Only matching rows                              |
| LEFT JOIN       | All left rows and matching right rows           |
| RIGHT JOIN      | All right rows and matching left rows           |
| FULL OUTER JOIN | All matching and unmatched rows from both sides |
| CROSS JOIN      | Cartesian product                               |
| SELF JOIN       | A table joined with itself                      |

**Important:** A condition on the right table in the WHERE clause can eliminate unmatched rows from a LEFT JOIN.

## 22.5 DELETE vs TRUNCATE vs DROP ⭐⭐⭐⭐⭐

| Feature                 | DELETE             | TRUNCATE      | DROP                   |
| ----------------------- | ------------------ | ------------- | ---------------------- |
| Removes rows            | Yes                | All rows      | Removes table          |
| WHERE support           | Yes                | No            | No                     |
| Table structure remains | Yes                | Yes           | No                     |
| Typical use             | Selective deletion | Empty a table | Remove database object |

Transaction behavior, triggers, identity reset and locking vary by database.

## 22.6 UNION vs UNION ALL ⭐⭐⭐⭐⭐

| UNION                             | UNION ALL                              |
| --------------------------------- | -------------------------------------- |
| Removes duplicate rows            | Keeps duplicate rows                   |
| May require duplicate elimination | Does not require duplicate elimination |
| Usually more expensive            | Usually less expensive                 |

Both queries must return the same number of columns with compatible data types.

## 22.7 COUNT(\*) vs COUNT(column) ⭐⭐⭐⭐⭐

```sql
SELECT
    COUNT(*) AS total_rows,
    COUNT(department_id) AS assigned_department_rows,
    COUNT(DISTINCT department_id) AS unique_departments
FROM employees;
```

- COUNT(\*) counts all rows.
- COUNT(column) ignores NULL values.
- COUNT(DISTINCT column) counts unique non-NULL values.

## 22.8 NULL Handling ⭐⭐⭐⭐⭐

| Expression     | Purpose                                    |
| -------------- | ------------------------------------------ |
| IS NULL        | Checks NULL                                |
| IS NOT NULL    | Checks non-NULL                            |
| COALESCE(a, b) | Returns first non-NULL value               |
| NULLIF(a, b)   | Returns NULL if both expressions are equal |

```sql
SELECT
    employee_name,
    COALESCE(CAST(department_id AS VARCHAR), 'Unassigned')
FROM employees;
```

**Important:** NULL is not equal to zero, an empty string or another NULL. Use IS NULL rather than = NULL.

## 22.9 Subquery vs CTE ⭐⭐⭐⭐

| Subquery                                | CTE                            |
| --------------------------------------- | ------------------------------ |
| Nested inside another query             | Named query block using WITH   |
| Can be less readable when deeply nested | Helps organize complex queries |
| Can be correlated                       | Can be recursive               |
| Performance depends on the optimizer    | Not automatically faster       |

## 22.10 GROUP BY vs Window Functions ⭐⭐⭐⭐⭐

| GROUP BY                      | Window Function                  |
| ----------------------------- | -------------------------------- |
| Combines rows into groups     | Preserves individual rows        |
| Produces one result per group | Calculates over related rows     |
| Uses aggregate functions      | Uses OVER()                      |
| Useful for summary reports    | Useful for ranking and analytics |

```sql
SELECT
    employee_name,
    department_id,
    salary,
    AVG(salary) OVER (
        PARTITION BY department_id
    ) AS department_avg
FROM employees;
```

## 22.11 ROW_NUMBER vs RANK vs DENSE_RANK ⭐⭐⭐⭐⭐

| Salary | ROW_NUMBER | RANK | DENSE_RANK |
| -----: | ---------: | ---: | ---------: |
| 100000 |          1 |    1 |          1 |
|  90000 |          2 |    2 |          2 |
|  90000 |          3 |    2 |          2 |
|  80000 |          4 |    4 |          3 |

- ROW_NUMBER: Unique sequential number.
- RANK: Same rank for ties, leaves gaps.
- DENSE_RANK: Same rank for ties, no gaps.

## 22.12 Indexing Quick Revision ⭐⭐⭐⭐⭐

| Concept         | Remember                                    |
| --------------- | ------------------------------------------- |
| Index           | Helps locate rows efficiently               |
| B-tree          | Common index for equality and range queries |
| Composite index | Index on multiple columns                   |
| Covering index  | Contains all columns needed by a query      |
| Partial index   | Indexes only rows satisfying a condition    |
| Unique index    | Enforces uniqueness                         |
| Index scan      | Uses an index to locate rows                |
| Sequential scan | Reads table rows                            |

**Remember:**

- Indexes improve suitable read queries.
- Indexes add storage and write-maintenance overhead.
- Column order matters in composite indexes.
- Check execution plans before creating indexes.
- An index is not always faster than a sequential scan.

## 22.13 ACID Properties ⭐⭐⭐⭐⭐

| Property    | Meaning                                                             |
| ----------- | ------------------------------------------------------------------- |
| Atomicity   | All operations succeed or none do                                   |
| Consistency | Database constraints and invariants remain valid                    |
| Isolation   | Concurrent transactions are controlled                              |
| Durability  | Committed changes survive failures according to database guarantees |

## 22.14 Transaction Isolation Levels ⭐⭐⭐⭐⭐

| Isolation Level  | Key Idea                                    |
| ---------------- | ------------------------------------------- |
| Read Uncommitted | Lowest standard isolation                   |
| Read Committed   | Reads committed data                        |
| Repeatable Read  | Repeated reads of a row remain consistent   |
| Serializable     | Transactions behave as if executed serially |

Database implementations differ. PostgreSQL, for example, treats READ UNCOMMITTED as READ COMMITTED.

## 22.15 Common Concurrency Problems ⭐⭐⭐⭐⭐

| Problem             | Meaning                                              |
| ------------------- | ---------------------------------------------------- |
| Dirty Read          | Reading uncommitted changes                          |
| Non-repeatable Read | Same row returns a different committed value         |
| Phantom Read        | Repeated range query returns a different set of rows |
| Lost Update         | One update overwrites another                        |
| Write Skew          | Concurrent decisions jointly violate a business rule |
| Deadlock            | Transactions wait on each other's locks              |

## 22.16 Query Optimization Checklist ⭐⭐⭐⭐⭐

- Avoid SELECT \* when only a few columns are needed.
- Filter rows as early as appropriate.
- Use indexes based on actual query patterns.
- Avoid applying functions to indexed columns in predicates when a range predicate can work.
- Use appropriate composite indexes.
- Check join conditions and unnecessary joins.
- Avoid unnecessary DISTINCT and sorting.
- Use pagination for large result sets.
- Inspect EXPLAIN and EXPLAIN ANALYZE.
- Compare actual execution time and correctness before and after changes.

## 22.17 Important SQL Patterns ⭐⭐⭐⭐⭐

**Find the second-highest distinct salary:**

```sql
SELECT DISTINCT salary
FROM (
    SELECT
        salary,
        DENSE_RANK() OVER (ORDER BY salary DESC) AS rnk
    FROM employees
) ranked
WHERE rnk = 2;
```

**Find duplicate email addresses:**

```sql
SELECT email, COUNT(*) AS duplicate_count
FROM employees
WHERE email IS NOT NULL
GROUP BY email
HAVING COUNT(*) > 1;
```

**Find employees without a department:**

```sql
SELECT *
FROM employees
WHERE department_id IS NULL;
```

**Find departments without employees:**

```sql
SELECT d.*
FROM departments d
WHERE NOT EXISTS (
    SELECT 1
    FROM employees e
    WHERE e.department_id = d.department_id
);
```

**Find the highest-paid employee in each department:**

```sql
WITH ranked AS (
    SELECT
        employee_id,
        employee_name,
        department_id,
        salary,
        DENSE_RANK() OVER (
            PARTITION BY department_id
            ORDER BY salary DESC
        ) AS rnk
    FROM employees
    WHERE department_id IS NOT NULL
)
SELECT *
FROM ranked
WHERE rnk = 1;
```

**Find the top three salaries in each department:**

```sql
WITH ranked AS (
    SELECT
        employee_name,
        department_id,
        salary,
        DENSE_RANK() OVER (
            PARTITION BY department_id
            ORDER BY salary DESC
        ) AS rnk
    FROM employees
)
SELECT *
FROM ranked
WHERE rnk <= 3;
```

**Find customers who have never placed an order:**

```sql
SELECT c.*
FROM customers c
WHERE NOT EXISTS (
    SELECT 1
    FROM orders o
    WHERE o.customer_id = c.customer_id
);
```

**Calculate a running order total per customer:**

```sql
SELECT
    customer_id,
    order_id,
    order_date,
    total_amount,
    SUM(total_amount) OVER (
        PARTITION BY customer_id
        ORDER BY order_date, order_id
        ROWS BETWEEN UNBOUNDED PRECEDING AND CURRENT ROW
    ) AS running_total
FROM orders;
```

## 22.18 SQL Performance Troubleshooting ⭐⭐⭐⭐⭐

| Symptom               | What to investigate                            |
| --------------------- | ---------------------------------------------- |
| Slow SELECT           | Execution plan, indexes, row count             |
| Slow JOIN             | Join keys, indexes, cardinality                |
| Slow pagination       | Large OFFSET, consider keyset pagination       |
| Duplicate result rows | One-to-many joins and join conditions          |
| High database CPU     | Expensive scans, sorts, aggregations           |
| High database memory  | Large result sets, sorts, hash operations      |
| Slow INSERT/UPDATE    | Index maintenance, locks, constraints          |
| Lock wait             | Blocking transactions and transaction duration |

**Golden interview approach:**

1. Identify the slow query.
2. Capture representative parameters.
3. Inspect the execution plan.
4. Identify the bottleneck.
5. Optimize the query or indexing strategy.
6. Measure the improvement.
7. Verify correctness and monitor production behavior.

---

# 23. Interview Preparation Checklist

Use this checklist to track your SQL preparation for Java Full Stack Developer interviews with 5+ years of experience.

## 23.1 SQL Fundamentals

- [ ] Understand SQL and RDBMS.
- [ ] Understand DDL, DML, DQL, DCL and TCL.
- [ ] Understand SQL logical execution order.
- [ ] Understand SQL vs PostgreSQL vs MySQL.
- [ ] Understand primary and foreign keys.
- [ ] Understand constraints and data types.

## 23.2 SQL Query Writing

- [ ] SELECT and column aliases.
- [ ] DISTINCT.
- [ ] WHERE and filtering conditions.
- [ ] BETWEEN, IN and LIKE.
- [ ] ORDER BY.
- [ ] LIMIT and OFFSET.
- [ ] NULL handling.
- [ ] COALESCE and NULLIF.
- [ ] Aggregate functions.
- [ ] GROUP BY and HAVING.
- [ ] Conditional aggregation.
- [ ] CASE expressions.

## 23.3 Joins and Subqueries

- [ ] INNER JOIN.
- [ ] LEFT JOIN.
- [ ] RIGHT JOIN.
- [ ] FULL OUTER JOIN.
- [ ] CROSS JOIN.
- [ ] SELF JOIN.
- [ ] Multiple table joins.
- [ ] ON vs WHERE in LEFT JOIN.
- [ ] JOIN vs EXISTS.
- [ ] Scalar and multi-row subqueries.
- [ ] Correlated subqueries.
- [ ] CTEs and multiple CTEs.
- [ ] Recursive CTEs.
- [ ] UNION vs UNION ALL.
- [ ] INTERSECT and EXCEPT.

## 23.4 Advanced SQL

- [ ] ROW_NUMBER.
- [ ] RANK and DENSE_RANK.
- [ ] LAG and LEAD.
- [ ] FIRST_VALUE and LAST_VALUE.
- [ ] Running totals.
- [ ] Top N records per group.
- [ ] Second-highest salary.
- [ ] Window frames.
- [ ] Pagination strategies.
- [ ] Views and materialized views.
- [ ] Triggers.

## 23.5 Database Design

- [ ] Primary and foreign keys.
- [ ] Composite keys.
- [ ] Unique, NOT NULL, CHECK and DEFAULT constraints.
- [ ] Foreign key actions.
- [ ] 1NF, 2NF and 3NF.
- [ ] BCNF.
- [ ] Normalization vs denormalization.
- [ ] One-to-one, one-to-many and many-to-many relationships.
- [ ] Database schema design.

## 23.6 Transactions and Concurrency

- [ ] ACID properties.
- [ ] COMMIT and ROLLBACK.
- [ ] SAVEPOINT.
- [ ] Transaction isolation levels.
- [ ] Dirty reads.
- [ ] Non-repeatable reads.
- [ ] Phantom reads.
- [ ] Lost updates.
- [ ] Write skew.
- [ ] Optimistic locking.
- [ ] Pessimistic locking.
- [ ] Deadlocks.
- [ ] MVCC.

## 23.7 Indexing and Performance

- [ ] Understand indexes and their internal purpose.
- [ ] B-tree indexes.
- [ ] Composite indexes.
- [ ] Covering indexes.
- [ ] Partial indexes.
- [ ] Unique indexes.
- [ ] Index selectivity.
- [ ] Leftmost-prefix principle.
- [ ] When indexes are not useful.
- [ ] EXPLAIN.
- [ ] EXPLAIN ANALYZE.
- [ ] Query execution plans.
- [ ] Sargable queries.
- [ ] Query optimization workflow.
- [ ] Slow-query troubleshooting.

## 23.8 Java, Spring Boot and Hibernate Integration

- [ ] JDBC and PreparedStatement.
- [ ] ResultSet and resource management.
- [ ] SQL injection prevention.
- [ ] Connection pooling.
- [ ] Spring Data JPA.
- [ ] Hibernate entity lifecycle.
- [ ] Persistence context.
- [ ] Lazy vs eager loading.
- [ ] Dirty checking.
- [ ] Flush vs commit.
- [ ] First-level and second-level cache.
- [ ] JPQL vs native SQL.
- [ ] N+1 query problem.
- [ ] Fetch joins and entity graphs.
- [ ] JDBC batching.
- [ ] @Transactional propagation and isolation.
- [ ] Rollback rules.
- [ ] Database pagination.

## 23.9 Must-Practice SQL Coding Questions

- [ ] Find the second-highest salary.
- [ ] Find the Nth-highest salary.
- [ ] Find the highest salary per department.
- [ ] Find the top three employees per department.
- [ ] Find duplicate records.
- [ ] Delete duplicate records while retaining one.
- [ ] Find employees without departments.
- [ ] Find departments without employees.
- [ ] Find customers without orders.
- [ ] Find customers with more than one order.
- [ ] Find the department with the highest average salary.
- [ ] Find employees earning more than their department average.
- [ ] Find the latest order for each customer.
- [ ] Calculate a running total.
- [ ] Find consecutive records or dates.
- [ ] Find gaps in a sequence.
- [ ] Calculate monthly revenue.
- [ ] Find the top-selling product.
- [ ] Find products that have never been ordered.
- [ ] Implement pagination queries.

## 23.10 Real-World Backend Scenarios

- [ ] Design tables for an e-commerce application.
- [ ] Design an employee and department relationship.
- [ ] Implement a money transfer transaction.
- [ ] Prevent overselling during concurrent orders.
- [ ] Handle duplicate API requests safely.
- [ ] Diagnose a slow Spring Boot API caused by SQL.
- [ ] Resolve Hibernate N+1 queries.
- [ ] Handle concurrent updates using optimistic locking.
- [ ] Choose indexes for frequently executed API queries.
- [ ] Investigate database connection pool exhaustion.
- [ ] Implement efficient pagination for large datasets.
- [ ] Explain how to investigate production database performance issues.

## 23.11 Final Preparation Priority

| Priority     | Topics                                                | Preparation Target                         |
| ------------ | ----------------------------------------------------- | ------------------------------------------ |
| Must Master  | Joins, GROUP BY, HAVING, subqueries, window functions | Write queries independently                |
| Must Master  | Indexes, query optimization, execution plans          | Explain practical performance improvements |
| Must Master  | Transactions, ACID, isolation, locking                | Explain concurrency scenarios              |
| Must Master  | JDBC, JPA, Hibernate, Spring transactions             | Connect SQL concepts to Java applications  |
| Important    | Normalization, constraints, CTEs, pagination          | Explain with examples                      |
| Good to Know | Recursive CTEs, advanced index types, triggers        | Understand practical use cases             |

## 23.12 Self-Assessment

Before attending interviews, verify that you can:

- [ ] Write complex SQL queries without looking at notes.
- [ ] Explain why a query is slow and how to investigate it.
- [ ] Read and interpret an execution plan.
- [ ] Explain composite index column ordering.
- [ ] Explain transaction isolation with real examples.
- [ ] Troubleshoot deadlocks and lock contention.
- [ ] Explain Hibernate N+1 and how to resolve it.
- [ ] Explain how @Transactional works in Spring.
- [ ] Design relational tables for a real-world application.
- [ ] Solve SQL coding questions using joins, CTEs and window functions.

**Final goal:** Be able to write SQL independently, explain database internals at a practical level, and connect database performance and transaction concepts to real Spring Boot production applications.
