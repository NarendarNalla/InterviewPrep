# Java Stream API: Collectors — Quick Revision Cheat Sheet

> `collect()` is the **terminal operation**. `Collectors.xxx()` decides **how** results are gathered.
> Package: `java.util.stream.Collectors`

---

## 0. Setup (Reused in All Examples)

```java
import java.util.*;
import java.util.stream.Collectors;

List<Integer> nums = Arrays.asList(10, 20, 30, 40, 50, 20, 30);
List<String> names = Arrays.asList("Java", "Python", "Spring", "Java", "Microservices");

class Employee {
    private int id; private String name; private String department; private int salary;
    public Employee(int id, String name, String department, int salary) {
        this.id = id; this.name = name; this.department = department; this.salary = salary;
    }
    public int getId() { return id; }
    public String getName() { return name; }
    public String getDepartment() { return department; }
    public int getSalary() { return salary; }
    @Override public String toString() { return name + " - " + department + " - " + salary; }
}

List<Employee> employees = Arrays.asList(
    new Employee(1, "John",  "IT",      60000),
    new Employee(2, "David", "HR",      40000),
    new Employee(3, "Smith", "IT",      80000),
    new Employee(4, "Mary",  "HR",      50000),
    new Employee(5, "Peter", "Finance", 70000),
    new Employee(6, "Steve", "IT",      90000)
);
```

**Flow:** `Source → Intermediate (filter/map) → collect(Collector) → Result`

---

## 1. All Collectors at a Glance

| Collector | Returns | Purpose |
|---|---|---|
| `toList()` | `List<T>` | Collect into a list |
| `toSet()` | `Set<T>` | Unique elements |
| `toMap()` | `Map<K,V>` | Key-value map |
| `groupingBy()` | `Map<K, List<T>>` | Group by property (SQL `GROUP BY`) |
| `partitioningBy()` | `Map<Boolean, List<T>>` | Split into true/false |
| `counting()` | `Long` | Count |
| `summingInt()` | `Integer` | Sum |
| `averagingInt()` | `Double` | Average |
| `maxBy()` / `minBy()` | `Optional<T>` | Max / min via Comparator |
| `joining()` | `String` | Join strings |
| `mapping()` | Depends on downstream | Transform, then collect |
| `collectingAndThen()` | Depends on finisher | Collect, then transform result |

---

## 2. Basic Collectors: `toList()`, `toSet()`, `toCollection()`

```java
// Q: How do you collect numbers greater than 20 into a List?
List<Integer> r1 = nums.stream().filter(n -> n > 20).collect(Collectors.toList());
// [30, 40, 50, 30]

// Q: How do you convert all strings to uppercase and collect them into a List?
List<String> r2 = names.stream().map(String::toUpperCase).collect(Collectors.toList());
// [JAVA, PYTHON, SPRING, JAVA, MICROSERVICES]

// Q: How do you remove duplicates by collecting into a Set?
Set<Integer> r3 = nums.stream().collect(Collectors.toSet());
// [10, 20, 30, 40, 50]  (order not guaranteed)

// Q: How do you get unique elements while preserving insertion order?
Set<String> r4 = names.stream().collect(Collectors.toCollection(LinkedHashSet::new));
// [Java, Python, Spring, Microservices]
```

**Interview points**
- `toList()` / `toSet()` do **not** guarantee the implementation or mutability.
- Use `toUnmodifiableList()` / `toUnmodifiableSet()` for immutable results.
- Use `toCollection(LinkedHashSet::new)` to keep order + uniqueness.

---

## 3. `toMap()` ⭐

```java
Collectors.toMap(keyMapper, valueMapper)
Collectors.toMap(keyMapper, valueMapper, mergeFunction)
Collectors.toMap(keyMapper, valueMapper, mergeFunction, mapSupplier)
```

```java
// Q: How do you convert employees into a Map of ID -> Name?
Map<Integer, String> m1 = employees.stream()
    .collect(Collectors.toMap(Employee::getId, Employee::getName));
// {1=John, 2=David, 3=Smith, 4=Mary, 5=Peter, 6=Steve}

// Q: How do you convert a List of Strings into a Map of String -> length?
Map<String, Integer> m2 = names.stream().distinct()
    .collect(Collectors.toMap(n -> n, String::length));
// {Java=4, Python=6, Spring=6, Microservices=12}
```

### Duplicate keys ⚠️

Default behaviour: throws **`IllegalStateException`**. Fix with a merge function:

```java
// Q: How do you get total salary per department using toMap() without a duplicate-key error?
Map<String, Integer> total = employees.stream()
    .collect(Collectors.toMap(
        Employee::getDepartment,
        Employee::getSalary,
        (oldVal, newVal) -> oldVal + newVal));
// {IT=230000, HR=90000, Finance=70000}

// Q: How do you keep only the highest salary per department using toMap()?
Map<String, Integer> max = employees.stream()
    .collect(Collectors.toMap(
        Employee::getDepartment, Employee::getSalary, Integer::max));
// {IT=90000, HR=50000, Finance=70000}

// Q: How do you create a TreeMap (sorted keys) using toMap()?
Map<Integer, String> sorted = employees.stream()
    .collect(Collectors.toMap(
        Employee::getId, Employee::getName,
        (o, n) -> o, TreeMap::new));
// Keys in ascending order
```

---

## 4. `groupingBy()` ⭐⭐ (Foundation of Advanced Collectors)

> Think SQL: `SELECT department, COUNT(*) FROM employees GROUP BY department;`

```java
Collectors.groupingBy(classifier)                      // Map<K, List<T>>
Collectors.groupingBy(classifier, downstreamCollector) // Map<K, R>
```

```java
// Q: How do you group employees by department?
Map<String, List<Employee>> byDept = employees.stream()
    .collect(Collectors.groupingBy(Employee::getDepartment));
// IT=[John, Smith, Steve]  HR=[David, Mary]  Finance=[Peter]

// Q: How do you group strings by their length?
Map<Integer, List<String>> byLen = names.stream().distinct()
    .collect(Collectors.groupingBy(String::length));
// 4=[Java]  6=[Python, Spring]  12=[Microservices]
```

### Common downstream combinations

```java
// Q: How do you count employees in each department?
Map<String, Long> count = employees.stream()
    .collect(Collectors.groupingBy(Employee::getDepartment, Collectors.counting()));
// {IT=3, HR=2, Finance=1}

// Q: How do you calculate total salary per department?
Map<String, Integer> sum = employees.stream()
    .collect(Collectors.groupingBy(Employee::getDepartment,
             Collectors.summingInt(Employee::getSalary)));
// {IT=230000, HR=90000, Finance=70000}

// Q: How do you calculate average salary per department?
Map<String, Double> avg = employees.stream()
    .collect(Collectors.groupingBy(Employee::getDepartment,
             Collectors.averagingInt(Employee::getSalary)));
// {IT=76666.67, HR=45000.0, Finance=70000.0}

// Q: How do you find the highest-paid employee in each department?
Map<String, Optional<Employee>> top = employees.stream()
    .collect(Collectors.groupingBy(Employee::getDepartment,
             Collectors.maxBy(Comparator.comparingInt(Employee::getSalary))));
// IT=Steve  HR=Mary  Finance=Peter

// Q: How do you get only employee names per department?
Map<String, List<String>> nameList = employees.stream()
    .collect(Collectors.groupingBy(Employee::getDepartment,
             Collectors.mapping(Employee::getName, Collectors.toList())));
// {IT=[John, Smith, Steve], HR=[David, Mary], Finance=[Peter]}
```

```java
// Q: How do you count the occurrences of each string in a list?
Map<String, Long> freq = names.stream()
    .collect(Collectors.groupingBy(n -> n, Collectors.counting()));
// {Java=2, Python=1, Spring=1, Microservices=1}
```

---

## 5. `partitioningBy()`

Splits into **exactly 2 groups**: `true` and `false` (both keys always present).

```java
// Q: How do you split employees into salary >= 60000 and the rest?
Map<Boolean, List<Employee>> p = employees.stream()
    .collect(Collectors.partitioningBy(e -> e.getSalary() >= 60000));
// true=[John, Smith, Peter, Steve]  false=[David, Mary]

// Q: How do you count employees on each side of the salary condition?
Map<Boolean, Long> pc = employees.stream()
    .collect(Collectors.partitioningBy(e -> e.getSalary() >= 60000, Collectors.counting()));
// {false=2, true=4}
```

| | `groupingBy()` | `partitioningBy()` |
|---|---|---|
| Purpose | Group by property | Split by condition |
| Groups | Many | Exactly 2 |
| Key | Any type | `Boolean` |
| Example | Department-wise | Above/below threshold |

---

## 6. `counting()`, `summingInt()`, `averagingInt()`

```java
// Q: How do you count all elements in a stream?
Long c = nums.stream().collect(Collectors.counting());          // 7
long c2 = nums.stream().count();                                // 7 (simpler)

// Q: How do you calculate the total salary of all employees?
Integer total = employees.stream()
    .collect(Collectors.summingInt(Employee::getSalary));       // 390000

// Q: How do you sum only the numbers greater than 20?
Integer s = nums.stream().filter(n -> n > 20)
    .collect(Collectors.summingInt(Integer::intValue));         // 150

// Q: How do you calculate the average salary of all employees?
Double a = employees.stream()
    .collect(Collectors.averagingInt(Employee::getSalary));     // 65000.0

// Q: How do you calculate the average of a list of numbers?
Double a2 = nums.stream()
    .collect(Collectors.averagingInt(Integer::intValue));       // 28.571428571428573
```

**Interview points**
- Use `count()` for a plain count. Use `Collectors.counting()` when **composing** (e.g. inside `groupingBy`).
- Variants: `summingLong/Double`, `averagingLong/Double`.
- All `averagingXxx()` return **`Double`**. `summingInt()` returns `Integer`.

---

## 7. `maxBy()` / `minBy()`

Return `Optional<T>` (stream may be empty).

```java
// Q: How do you find the employee with the highest salary?
Optional<Employee> max = employees.stream()
    .collect(Collectors.maxBy(Comparator.comparingInt(Employee::getSalary)));
// Steve - IT - 90000

// Q: How do you find the employee with the lowest salary?
Optional<Employee> min = employees.stream()
    .collect(Collectors.minBy(Comparator.comparingInt(Employee::getSalary)));
// David - HR - 40000

// Q: How do you find the maximum number in a list?
Optional<Integer> maxNum = nums.stream()
    .collect(Collectors.maxBy(Comparator.naturalOrder()));      // Optional[50]
```

**Interview point:** use `stream.max()` / `stream.min()` for direct use; use `maxBy()` / `minBy()` when composing with `groupingBy()`.

---

## 8. `joining()`

Works on a stream of `CharSequence`. For objects, `map()` to String first.

```java
// Q: How do you join strings with no separator?
names.stream().distinct().collect(Collectors.joining());
// JavaPythonSpringMicroservices

// Q: How do you join strings with a delimiter?
names.stream().distinct().collect(Collectors.joining(", "));
// Java, Python, Spring, Microservices

// Q: How do you join strings with a delimiter, prefix and suffix?
names.stream().distinct().collect(Collectors.joining(", ", "[", "]"));
// [Java, Python, Spring, Microservices]

// Q: How do you join all employee names into one String?
employees.stream().map(Employee::getName).collect(Collectors.joining(" | "));
// John | David | Smith | Mary | Peter | Steve
```

---

## 9. `mapping()` (Downstream Collector)

```java
Collectors.mapping(mapper, downstreamCollector)
```

Transforms each element **before** the downstream collector gathers it. Used mainly inside `groupingBy()`.

```java
// Q: How do you group employee names (not objects) by department into a List?
Collectors.groupingBy(Employee::getDepartment,
    Collectors.mapping(Employee::getName, Collectors.toList()))
// {IT=[John, Smith, Steve], HR=[David, Mary], Finance=[Peter]}

// Q: How do you collect unique employee names per department?
Collectors.groupingBy(Employee::getDepartment,
    Collectors.mapping(Employee::getName, Collectors.toSet()))

// Q: How do you get comma-separated employee names per department?
Collectors.groupingBy(Employee::getDepartment,
    Collectors.mapping(Employee::getName, Collectors.joining(", ")))
// IT=John, Smith, Steve   HR=David, Mary   Finance=Peter

// Q: How do you collect only the salaries per department?
Collectors.groupingBy(Employee::getDepartment,
    Collectors.mapping(Employee::getSalary, Collectors.toList()))
// IT=[60000, 80000, 90000]  HR=[40000, 50000]  Finance=[70000]
```

---

## 10. `collectingAndThen()`

```java
Collectors.collectingAndThen(downstreamCollector, finisherFunction)
```

Step 1: collect → Step 2: apply a finishing function to the result.

```java
// Q: How do you collect into a List and make it unmodifiable?
List<String> u = names.stream().distinct()
    .collect(Collectors.collectingAndThen(Collectors.toList(), Collections::unmodifiableList));
// u.add("JavaScript") -> UnsupportedOperationException

// Q: How do you get the count of unique elements?
Integer uniq = names.stream()
    .collect(Collectors.collectingAndThen(Collectors.toSet(), Set::size));   // 4

// Q: How do you return the employee count via the collected list's size?
Integer n = employees.stream()
    .collect(Collectors.collectingAndThen(Collectors.toList(), List::size)); // 6

// Q: How do you make each department's employee list unmodifiable?
Map<String, List<Employee>> g = employees.stream()
    .collect(Collectors.groupingBy(Employee::getDepartment,
        Collectors.collectingAndThen(Collectors.toList(), Collections::unmodifiableList)));
```

---

## 11. Must-Know Combinations

| Combination | Use case |
|---|---|
| `groupingBy` + `counting` | Employee count per department |
| `groupingBy` + `summingInt` | Total salary per department |
| `groupingBy` + `averagingInt` | Average salary per department |
| `groupingBy` + `maxBy` / `minBy` | Highest / lowest paid per department |
| `groupingBy` + `mapping` + `toList` | Names per department |
| `groupingBy` + `mapping` + `joining` | Comma-separated names per department |
| `partitioningBy` + `counting` | Count by condition |
| `toMap` + merge function | Handle duplicate keys |
| `collectingAndThen` + `toList` | Immutable result |

### Bonus patterns (frequently asked)

```java
// Q: How do you get only the highest-paid employee's NAME per department?
Map<String, String> topName = employees.stream()
    .collect(Collectors.groupingBy(Employee::getDepartment,
        Collectors.collectingAndThen(
            Collectors.maxBy(Comparator.comparingInt(Employee::getSalary)),
            opt -> opt.get().getName())));
// {IT=Steve, HR=Mary, Finance=Peter}

// Q: How do you get sorted employee names per department?
Map<String, List<String>> sortedNames = employees.stream()
    .collect(Collectors.groupingBy(Employee::getDepartment,
        Collectors.mapping(Employee::getName,
            Collectors.collectingAndThen(Collectors.toList(), l -> { Collections.sort(l); return l; }))));

// Q: How do you get salary statistics (count, sum, min, avg, max) per department?
Map<String, IntSummaryStatistics> stats = employees.stream()
    .collect(Collectors.groupingBy(Employee::getDepartment,
             Collectors.summarizingInt(Employee::getSalary)));

// Q: How do you control the map type / key order in groupingBy()?
Map<String, Long> ordered = employees.stream()
    .collect(Collectors.groupingBy(Employee::getDepartment, TreeMap::new, Collectors.counting()));
```

---

## 12. Interview Quick Facts

- `groupingBy()` returns a `HashMap` by default → **order not guaranteed**. Pass a map supplier (`TreeMap::new`, `LinkedHashMap::new`) to control it.
- `toMap()` throws `IllegalStateException` on duplicate keys and `NullPointerException` on `null` values.
- `partitioningBy()` always contains both `true` and `false` keys, even if one group is empty.
- `maxBy()` / `minBy()` → `Optional`; `counting()` → `Long`; `averagingXxx()` → `Double`.
- `joining()` needs `Stream<CharSequence>` → use `map()` first.
- `mapping()` and `collectingAndThen()` are mostly used as **downstream** collectors.
- `Collectors.toList()` gives no mutability guarantee. Prefer `Stream.toList()` (Java 16+) for an unmodifiable list.

---

## 13. Priority Guide

| Priority | Topics |
|---|---|
| 🔴 Must know | `toList`, `toSet`, `toMap` |
| 🔴 Must know | `groupingBy`, `partitioningBy` |
| 🔴 Must know | `counting`, `summingInt`, `averagingInt` |
| 🔴 Must know | `maxBy`, `minBy` |
| 🟡 Important | `joining`, `mapping` |
| 🟡 Important | `collectingAndThen` |

---

## 14. Practice Questions

### Level 1: Basic Collectors
1. Convert a `List` into a `Set`.
2. Convert a `List<String>` into a `Map` of String → length.
3. Find duplicate elements and their occurrence counts.
4. Calculate the sum and average of numbers.
5. Find the maximum and minimum salary.

### Level 2: Grouping and Partitioning
6. Group employees by department.
7. Count employees in each department.
8. Calculate total salary department-wise.
9. Find average salary department-wise.
10. Find the highest-paid employee in each department.
11. Partition employees by salary > ₹60,000.
12. Group strings by length.

### Level 3: Advanced Combinations
13. Group employees by department and collect only names.
14. Group employees by department and join names with commas.
15. Group employees by department and collect unique names.
16. Find the highest-paid employee per department and return only the name.
17. Convert employee data into a `Map` and handle duplicate keys.
18. Group employees by department and sort their names.
19. Calculate department-wise salary statistics.
20. Collect a `List` and return an unmodifiable result.

> **Next step:** Solve all 20 without looking at the examples. Focus on Level 3. They mirror real interview questions.


