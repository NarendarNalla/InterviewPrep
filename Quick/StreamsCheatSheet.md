# Java Streams: Complete Interview Cheat Sheet

**Collections + Arrays + Strings | Java 8+ | Quick revision format**

> **How to read:** every item follows **Q → Purpose → Solution**. ⭐ = asked very often. Outputs are in comments.
> **Short on time?** Read [Section 0](#0-ten-minute-revision-path) first, then Sections 8, 9 and 12.

---

## Table of Contents

0. [Ten-Minute Revision Path](#0-ten-minute-revision-path)
1. [Stream Fundamentals](#1-stream-fundamentals)
2. [Core Stream Methods](#2-core-stream-methods)
3. [Streams with Collections (List, Set, Map)](#3-streams-with-collections)
4. [Streams with Arrays](#4-streams-with-arrays)
5. [Collectors](#5-collectors)
6. [Advanced Stream Operations](#6-advanced-stream-operations)
7. [Interview Coding Problems](#7-interview-coding-problems)
8. [Output Prediction and Tricky Cases](#8-output-prediction-and-tricky-cases)
9. [Common Traps and Mistakes](#9-common-traps-and-mistakes)
10. [Method Selection Cheat Sheet](#10-method-selection-cheat-sheet)
11. [Syntax Templates](#11-syntax-templates)
12. [Rapid-Fire Theory Q&A](#12-rapid-fire-theory-qa)
13. [Final Revision Checklist](#13-final-revision-checklist)

---

## 0. Ten-Minute Revision Path

| Minutes | Revise | Key takeaway |
|---|---|---|
| 1 | Pipeline shape | `Source → intermediate (lazy) → terminal (triggers run)` |
| 2 | Core methods | `filter` select, `map` transform, `sorted`, `distinct`, `limit`, `skip`, `reduce` |
| 2 | Collectors | `groupingBy` is the foundation. `groupingBy + counting / mapping / summingInt / averagingInt / maxBy` |
| 1 | `toMap` | Duplicate key → `IllegalStateException` unless you pass a **merge function** |
| 1 | `flatMap`, `boxed`, `mapToInt` | Flatten / primitive → object / object → primitive |
| 1 | `Optional` | Never call `get()` blindly. Use `orElse`, `orElseGet`, `orElseThrow`, `ifPresent` |
| 1 | Sorting | `comparing(...).thenComparing(...)`, `.reversed()` |
| 1 | Parallel | Not always faster. No shared mutable state |

**Five sentences to say out loud:**
1. A stream does not store data; it processes a source.
2. Intermediate operations are **lazy**; a terminal operation triggers execution.
3. A stream can be consumed **only once**.
4. `map` is 1→1; `flatMap` is 1→many and flattens.
5. `peek()` is for debugging only.

---

## Setup: Data Used in Examples

```java
import java.util.*;
import java.util.function.*;
import java.util.stream.*;

List<Integer> nums  = Arrays.asList(10, 5, 20, 5, 30, 15, 40, 10);
List<String>  names = Arrays.asList("Ravi", "Amit", "Ravi", "Priya", "Amit", "Ravi");

Map<String, Integer> marks = new HashMap<>();
marks.put("Ravi", 85); marks.put("Priya", 92); marks.put("Amit", 70);

class Employee {
    private int id; private String name; private String department; private int salary;
    Employee(int id, String name, String department, int salary) {
        this.id = id; this.name = name; this.department = department; this.salary = salary;
    }
    int getId() { return id; }
    String getName() { return name; }
    String getDepartment() { return department; }
    int getSalary() { return salary; }
    @Override public String toString() { return name + "-" + department + "-" + salary; }
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

---

## 1. Stream Fundamentals

### Q1 ⭐ What is the Stream API?
**Purpose:** Process data from collections/arrays in a functional style without manual loops.

```java
Arrays.asList(10, 15, 20, 25, 30).stream()
    .filter(n -> n > 20)
    .forEach(System.out::println);
// 25
// 30
```

### Q2 ⭐ Collection vs Stream?

| Collection | Stream |
|---|---|
| Stores elements | Processes elements (stores nothing) |
| Can be reused | Consumed **only once** |
| Add / remove supported | Does not modify the source |
| Eager | Intermediate operations are **lazy** |

### Q3 ⭐ Intermediate vs Terminal operations?
**Purpose:** Know what returns a Stream and what triggers execution.

| Intermediate (return a Stream, lazy) | Terminal (return result / void, triggers run) |
|---|---|
| `filter()` `map()` `flatMap()` | `forEach()` `collect()` `count()` |
| `sorted()` `distinct()` | `reduce()` `min()` `max()` |
| `limit()` `skip()` `peek()` | `findFirst()` `findAny()` |
| `mapToInt()` `boxed()` | `anyMatch()` `allMatch()` `noneMatch()` `toArray()` |

### Q4 ⭐ What is lazy evaluation?
**Purpose:** Intermediate operations run only when a terminal operation is invoked.

```java
List<Integer> l = Arrays.asList(10, 20, 30);

l.stream().filter(n -> { System.out.println("Filtering: " + n); return n > 10; });
// No output: no terminal operation

l.stream().filter(n -> { System.out.println("Filtering: " + n); return n > 10; })
          .forEach(System.out::println);
// Filtering: 10
// Filtering: 20
// 20
// Filtering: 30
// 30
```

### Q5 ⭐ Can a Stream be reused?
**Purpose:** Know the `IllegalStateException` trap.

```java
Stream<Integer> s = nums.stream();
s.forEach(System.out::println);
// s.count();   // IllegalStateException: stream has already been operated upon or closed
```
**Fix:** create a new stream from the source each time.

### Q6 How do you create Streams?

```java
list.stream();                      // List
set.stream();                       // Set
Arrays.stream(arr);                 // Array (int[] -> IntStream)
Stream.of(10, 20, 30);              // Values
map.entrySet().stream();            // Map entries
map.keySet().stream();              // Map keys
map.values().stream();              // Map values
"hello".chars();                    // String -> IntStream of chars
IntStream.range(1, 5);              // Number range
Stream.iterate(1, n -> n + 1);      // Infinite (use limit)
Stream.generate(Math::random);      // Infinite (use limit)
Stream.empty();                     // Empty stream
```

### Q7 Lambda vs Method Reference?
**Purpose:** Method reference is shorthand when an existing method matches the functional interface.

```java
nums.forEach(n -> System.out.println(n));   // Lambda
nums.forEach(System.out::println);          // Method reference
```

| Type | Syntax | Example |
|---|---|---|
| Static method | `Class::staticMethod` | `Integer::parseInt` |
| Instance method (on a given object) | `obj::method` | `System.out::println` |
| Instance method (on the parameter) | `Class::method` | `String::length` |
| Constructor | `Class::new` | `ArrayList::new` |

### Q8 ⭐ Which functional interfaces does Stream use?

| Interface | Method | Used in |
|---|---|---|
| `Predicate<T>` | `boolean test(T)` | `filter`, `anyMatch`, `partitioningBy` |
| `Function<T,R>` | `R apply(T)` | `map`, `flatMap`, `groupingBy` classifier |
| `Consumer<T>` | `void accept(T)` | `forEach`, `peek` |
| `Supplier<T>` | `T get()` | `generate`, `orElseGet`, `toCollection` |
| `UnaryOperator<T>` | `T apply(T)` | `iterate` |
| `BinaryOperator<T>` | `T apply(T,T)` | `reduce`, `toMap` merge function |
| `Comparator<T>` | `int compare(T,T)` | `sorted`, `min`, `max` |

### Q9 Stateless vs Stateful, and Short-circuiting operations?
**Purpose:** Understand performance and infinite-stream behaviour.

| Category | Operations |
|---|---|
| **Stateless** (each element independent) | `filter`, `map`, `flatMap`, `peek` |
| **Stateful** (needs other elements) | `sorted`, `distinct`, `limit`, `skip` |
| **Short-circuiting** (can stop early) | `limit`, `findFirst`, `findAny`, `anyMatch`, `allMatch`, `noneMatch`, `takeWhile` |

---

## 2. Core Stream Methods

Input for this section: `nums = [10, 5, 20, 5, 30, 15, 40, 10]`

### 2.1 ⭐ `filter()`: select elements
**Purpose:** Keep only elements matching a `Predicate`.

```java
// Q: Find all even numbers
nums.stream().filter(n -> n % 2 == 0).collect(Collectors.toList());
// [10, 20, 30, 40, 10]

// Q: Find numbers greater than 15
nums.stream().filter(n -> n > 15).collect(Collectors.toList());
// [20, 30, 40]
```

### 2.2 ⭐ `map()`: transform elements
**Purpose:** Convert each element into another value (1 → 1).

```java
// Q: Square every number
nums.stream().map(n -> n * n).collect(Collectors.toList());
// [100, 25, 400, 25, 900, 225, 1600, 100]

// Q: Convert names to uppercase
Arrays.asList("ravi", "amit", "priya").stream()
      .map(String::toUpperCase).collect(Collectors.toList());
// [RAVI, AMIT, PRIYA]
```

### 2.3 ⭐ `sorted()`: sort elements
**Purpose:** Natural order by default, or pass a `Comparator`.

```java
// Q: Sort ascending
nums.stream().sorted().collect(Collectors.toList());
// [5, 5, 10, 10, 15, 20, 30, 40]

// Q: Sort descending
nums.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
// [40, 30, 20, 15, 10, 10, 5, 5]
```

### 2.4 `distinct()`: remove duplicates
**Purpose:** Unique elements. For custom objects it uses `equals()` and `hashCode()`.

```java
// Q: Find unique numbers
nums.stream().distinct().collect(Collectors.toList());
// [10, 5, 20, 30, 15, 40]
```

### 2.5 `limit()` and `skip()`
**Purpose:** `limit(n)` takes the first n. `skip(n)` drops the first n.

```java
// Q: Get the first three elements
nums.stream().limit(3).collect(Collectors.toList());     // [10, 5, 20]

// Q: Skip the first two elements
nums.stream().skip(2).collect(Collectors.toList());      // [20, 5, 30, 15, 40, 10]
```

### 2.6 `count()`
**Purpose:** Number of elements (returns `long`).

```java
// Q: Count even numbers
nums.stream().filter(n -> n % 2 == 0).count();   // 5
```

### 2.7 ⭐ `min()` / `max()`
**Purpose:** Smallest / largest using a `Comparator`. Returns `Optional`.

```java
// Q: Find minimum and maximum
int min = nums.stream().min(Integer::compareTo).orElse(0);   // 5
int max = nums.stream().max(Integer::compareTo).orElse(0);   // 40
```

### 2.8 ⭐ `findFirst()` vs `findAny()`
**Purpose:** Return one matching element as `Optional`.

```java
// Q: Find the first number greater than 15
nums.stream().filter(n -> n > 15).findFirst();   // Optional[20]
nums.stream().filter(n -> n > 15).findAny();     // Optional with a matching element
```
- `findFirst()` → first in **encounter order** (always).
- `findAny()` → **any** match; useful for parallel streams (faster, order-free).

### 2.9 ⭐ `anyMatch()`, `allMatch()`, `noneMatch()`
**Purpose:** Boolean checks. All are **short-circuiting**.

```java
// Q: Is any number greater than 35?
nums.stream().anyMatch(n -> n > 35);     // true

// Q: Are all numbers positive?
nums.stream().allMatch(n -> n > 0);      // true

// Q: Are there no negative numbers?
nums.stream().noneMatch(n -> n < 0);     // true
```

| Method | Meaning | On empty stream |
|---|---|---|
| `anyMatch` | At least one matches | `false` |
| `allMatch` | Every element matches | `true` |
| `noneMatch` | No element matches | `true` |

### 2.10 ⭐ `reduce()`: combine into one result
**Purpose:** Fold all elements into a single value.

```java
// Q: Find the sum of all numbers
nums.stream().reduce(0, (a, b) -> a + b);          // 135
nums.stream().reduce(0, Integer::sum);             // 135

// Q: Product of 2, 3, 4
Arrays.asList(2, 3, 4).stream().reduce(1, (a, b) -> a * b);   // 24

// Q: Maximum using reduce
nums.stream().reduce(Integer.MIN_VALUE, Integer::max);        // 40
```

| Form | Returns | Use |
|---|---|---|
| `reduce(identity, BinaryOperator)` | `T` | Safe on empty stream (returns identity) |
| `reduce(BinaryOperator)` | `Optional<T>` | No identity, may be empty |
| `reduce(identity, accumulator, combiner)` | `U` | Result type differs from element type / parallel |

### 2.11 `forEach()`
**Purpose:** Perform an action per element. Terminal, returns nothing.

```java
nums.stream().forEach(System.out::println);
```

### 2.12 Java 9+ extras: `takeWhile()`, `dropWhile()`, `Stream.concat()`
**Purpose:** Conditional slicing and merging.

```java
// Q: Take elements while condition holds (stops at first failure)
Stream.of(1, 2, 3, 10, 4).takeWhile(n -> n < 5).collect(Collectors.toList());   // [1, 2, 3]

// Q: Drop elements while condition holds, keep the rest
Stream.of(1, 2, 3, 10, 4).dropWhile(n -> n < 5).collect(Collectors.toList());   // [10, 4]

// Q: Merge two lists
Stream.concat(Arrays.asList(1, 2).stream(), Arrays.asList(3, 4).stream())
      .collect(Collectors.toList());                                            // [1, 2, 3, 4]
```

---

## 3. Streams with Collections

### 3.1 List

```java
// Q: Filter then transform (numbers > 10, doubled)
nums.stream().filter(n -> n > 10).map(n -> n * 2).collect(Collectors.toList());
// [40, 60, 30, 80]

// Q: Sort strings alphabetically
names.stream().distinct().sorted().collect(Collectors.toList());
// [Amit, Priya, Ravi]

// Q: Sort strings by length
names.stream().distinct().sorted(Comparator.comparingInt(String::length)).collect(Collectors.toList());

// Q: Convert List to Set
nums.stream().collect(Collectors.toSet());

// Q: Extract a property from objects
employees.stream().map(Employee::getName).collect(Collectors.toList());
// [John, David, Smith, Mary, Peter, Steve]
```

### 3.2 Set

```java
Set<Integer> set1 = new HashSet<>(Arrays.asList(10, 20, 30, 40));
Set<Integer> set2 = new HashSet<>(Arrays.asList(30, 40, 50, 60));

// Q: Filter Set elements (> 20)
set1.stream().filter(n -> n > 20).collect(Collectors.toSet());           // [30, 40]

// Q ⭐: Find common elements (intersection)
set1.stream().filter(set2::contains).collect(Collectors.toSet());        // [30, 40]

// Q ⭐: Find elements in set1 but not in set2 (difference)
set1.stream().filter(n -> !set2.contains(n)).collect(Collectors.toSet()); // [10, 20]

// Q: Union of two sets
Stream.concat(set1.stream(), set2.stream()).collect(Collectors.toSet()); // [10, 20, 30, 40, 50, 60]
```
`HashSet` = no guaranteed order. `TreeSet` = sorted. Both support `stream()`.

### 3.3 ⭐ Map

> **Map has no `stream()` method.** Use `entrySet()`, `keySet()` or `values()`.
> `HashMap` output order is not guaranteed. Input: `marks = {Ravi=85, Priya=92, Amit=70}`

```java
// Q: Stream entries / keys / values
marks.entrySet().stream().forEach(System.out::println);
marks.keySet().stream().forEach(System.out::println);
marks.values().stream().forEach(System.out::println);

// Q ⭐: Filter entries by value (> 80)
Map<String, Integer> r1 = marks.entrySet().stream()
    .filter(e -> e.getValue() > 80)
    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
// {Ravi=85, Priya=92}

// Q ⭐: Student with highest marks
Optional<Map.Entry<String, Integer>> top = marks.entrySet().stream()
    .max(Map.Entry.comparingByValue());
// Priya=92

// Q: Sort Map by key
marks.entrySet().stream().sorted(Map.Entry.comparingByKey()).forEach(System.out::println);
// Amit=70, Priya=92, Ravi=85

// Q: Sort Map by value ascending
marks.entrySet().stream().sorted(Map.Entry.comparingByValue()).forEach(System.out::println);
// Amit=70, Ravi=85, Priya=92

// Q: Sort Map by value descending
marks.entrySet().stream()
    .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
    .forEach(System.out::println);
// Priya=92, Ravi=85, Amit=70

// Q ⭐: Sort Map by value descending and KEEP the order in a new Map
Map<String, Integer> sorted = marks.entrySet().stream()
    .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                              (a, b) -> a, LinkedHashMap::new));
// {Priya=92, Ravi=85, Amit=70}

// Q: Convert Map values into a List
marks.values().stream().collect(Collectors.toList());

// Q: Count entries matching a condition
marks.entrySet().stream().filter(e -> e.getValue() > 80).count();   // 2

// Q: Invert a Map (value -> key)
marks.entrySet().stream()
    .collect(Collectors.toMap(Map.Entry::getValue, Map.Entry::getKey));
// {85=Ravi, 92=Priya, 70=Amit}   (duplicate values would throw: add a merge function)

// Q: Increase every value by 5 (new Map)
marks.entrySet().stream()
    .collect(Collectors.toMap(Map.Entry::getKey, e -> e.getValue() + 5));
```

---

## 4. Streams with Arrays

Input: `int[] arr = {10, 5, 20, 5, 30, 15};`

```java
// Q ⭐: Sum of array elements
Arrays.stream(arr).sum();                               // 85

// Q ⭐: Max and min
Arrays.stream(arr).max().orElse(0);                     // 30
Arrays.stream(arr).min().orElse(0);                     // 5

// Q: Average
Arrays.stream(arr).average().orElse(0.0);               // 14.166...

// Q: Even and odd numbers
Arrays.stream(arr).filter(n -> n % 2 == 0).toArray();   // [10, 20, 30]
Arrays.stream(arr).filter(n -> n % 2 != 0).toArray();   // [5, 5, 15]

// Q: Remove duplicates
Arrays.stream(arr).distinct().toArray();                // [10, 5, 20, 30, 15]

// Q: Sort ascending
Arrays.stream(arr).sorted().toArray();                  // [5, 5, 10, 15, 20, 30]

// Q ⭐: Sort descending (IntStream cannot take a Comparator -> boxed first)
Arrays.stream(arr).boxed()
    .sorted(Comparator.reverseOrder())
    .mapToInt(Integer::intValue).toArray();             // [30, 20, 15, 10, 5, 5]

// Q ⭐: Second-largest distinct number
Arrays.stream(arr).distinct().boxed()
    .sorted(Comparator.reverseOrder()).skip(1).findFirst()
    .orElseThrow(() -> new NoSuchElementException());   // 20

// Q ⭐: Top three distinct numbers
Arrays.stream(arr).boxed().distinct()
    .sorted(Comparator.reverseOrder()).limit(3)
    .collect(Collectors.toList());                      // [30, 20, 15]

// Q ⭐: int[] -> List<Integer>
Arrays.stream(arr).boxed().collect(Collectors.toList());

// Q ⭐: List<Integer> -> int[]
nums.stream().mapToInt(Integer::intValue).toArray();

// Q: Reverse an array
int n = arr.length;
IntStream.range(0, n).map(i -> arr[n - 1 - i]).toArray();    // [15, 30, 5, 20, 5, 10]

// Q: Merge two int arrays
IntStream.concat(Arrays.stream(new int[]{1, 2}), Arrays.stream(new int[]{3, 4})).toArray();  // [1, 2, 3, 4]

// Q: Missing number in 1..5 from {1, 2, 4, 5}
int[] a = {1, 2, 4, 5};
int missing = (5 * 6) / 2 - Arrays.stream(a).sum();     // 3

// Q: Array of Strings -> sorted by length
String[] words = {"Spring", "Java", "Microservices"};
Arrays.stream(words).sorted(Comparator.comparingInt(String::length)).toArray(String[]::new);
```

```java
// Q ⭐: Common elements between two arrays
int[] arr1 = {10, 20, 30, 40};
int[] arr2 = {30, 40, 50, 60};

Set<Integer> set = Arrays.stream(arr2).boxed().collect(Collectors.toSet());
int[] common = Arrays.stream(arr1).filter(set::contains).distinct().toArray();   // [30, 40]
```
**Tip:** a `Set` gives O(1) membership checks instead of scanning the second array.

> ⚠️ **Array traps**
> - `Stream.of(intArray)` gives `Stream<int[]>` (one element!). Use `Arrays.stream(intArray)`.
> - `Arrays.asList(intArray)` gives `List<int[]>`. Use `Arrays.stream(arr).boxed().collect(toList())`.
> - `Arrays.stream(int[])` → `IntStream`; `Arrays.stream(Integer[])` → `Stream<Integer>`.

---

## 5. Collectors

### Quick reference

| Collector | Returns | Purpose |
|---|---|---|
| `toList()` / `toSet()` | `List` / `Set` | Collect elements |
| `toMap()` | `Map<K,V>` | Key-value map |
| `groupingBy()` | `Map<K, List<T>>` | Group by property (SQL `GROUP BY`) |
| `partitioningBy()` | `Map<Boolean, List<T>>` | Split true / false |
| `counting()` | `Long` | Count |
| `summingInt()` | `Integer` | Sum |
| `averagingInt()` | `Double` | Average |
| `maxBy()` / `minBy()` | `Optional<T>` | Max / min |
| `joining()` | `String` | Join strings |
| `mapping()` | depends | Transform, then collect |
| `collectingAndThen()` | depends | Collect, then finish |
| `summarizingInt()` | `IntSummaryStatistics` | Count, sum, min, avg, max |
| `toCollection()` | any `Collection` | Choose the implementation |

Input: `names = [Ravi, Amit, Ravi, Priya, Amit, Ravi]`

### 5.1 `toList()` and `toSet()`
```java
// Q: Stream -> List
names.stream().collect(Collectors.toList());         // [Ravi, Amit, Ravi, Priya, Amit, Ravi]

// Q: Remove duplicates into a Set
names.stream().collect(Collectors.toSet());          // [Ravi, Amit, Priya] (order not guaranteed)

// Q: Unique elements, keep insertion order
names.stream().collect(Collectors.toCollection(LinkedHashSet::new));   // [Ravi, Amit, Priya]
```
`toList()` / `toSet()` give **no guarantee** of implementation or mutability. Use `toUnmodifiableList()` / `toUnmodifiableSet()` for immutable results.

### 5.2 ⭐ `toMap()`
**Purpose:** Convert elements into a Map.
```java
Collectors.toMap(keyMapper, valueMapper)
Collectors.toMap(keyMapper, valueMapper, mergeFunction)
Collectors.toMap(keyMapper, valueMapper, mergeFunction, mapSupplier)
```

```java
// Q: Name -> length
names.stream().distinct().collect(Collectors.toMap(n -> n, String::length));
// {Ravi=4, Amit=4, Priya=5}

// Q ⭐: Handle duplicate keys (keep the old value)
names.stream().collect(Collectors.toMap(n -> n, String::length, (oldV, newV) -> oldV));

// Q ⭐: Count occurrences with toMap
names.stream().collect(Collectors.toMap(n -> n, n -> 1, Integer::sum));
// {Ravi=3, Amit=2, Priya=1}

// Q: Max salary per department
employees.stream().collect(Collectors.toMap(
    Employee::getDepartment, Employee::getSalary, Integer::max));
// {IT=90000, HR=50000, Finance=70000}

// Q: Result as a sorted Map
employees.stream().collect(Collectors.toMap(
    Employee::getDepartment, Employee::getSalary, Integer::sum, TreeMap::new));
// {Finance=70000, HR=90000, IT=230000}
```

**Merge function:** `(oldValue, newValue) -> value to keep`

```java
Integer::sum                // add
Integer::max / Integer::min // keep max / min
(old, cur) -> old           // keep first
(old, cur) -> cur           // keep latest
```
> ⚠️ Duplicate key without merge function → `IllegalStateException`. A `null` value → `NullPointerException`.

### 5.3 ⭐⭐ `groupingBy()`: the foundation
**Purpose:** Group elements by a classifier. Supports a **downstream collector**.

```java
// Q: Group names by length
names.stream().collect(Collectors.groupingBy(String::length));
// {4=[Ravi, Amit, Ravi, Amit, Ravi], 5=[Priya]}

// Q ⭐: Count occurrences of each name (frequency)
names.stream().collect(Collectors.groupingBy(n -> n, Collectors.counting()));
// {Ravi=3, Amit=2, Priya=1}

// Q: Group names by first character
names.stream().collect(Collectors.groupingBy(n -> n.charAt(0)));
```

**Employee grouping (all use the downstream pattern)**

```java
// Q ⭐: Group employees by department
Map<String, List<Employee>> byDept = employees.stream()
    .collect(Collectors.groupingBy(Employee::getDepartment));
// IT=[John, Smith, Steve]  HR=[David, Mary]  Finance=[Peter]

// Q ⭐: Employee count per department
employees.stream().collect(Collectors.groupingBy(Employee::getDepartment, Collectors.counting()));
// {IT=3, HR=2, Finance=1}

// Q ⭐: Total salary per department
employees.stream().collect(Collectors.groupingBy(Employee::getDepartment,
    Collectors.summingInt(Employee::getSalary)));
// {IT=230000, HR=90000, Finance=70000}

// Q ⭐: Average salary per department
employees.stream().collect(Collectors.groupingBy(Employee::getDepartment,
    Collectors.averagingInt(Employee::getSalary)));
// {IT=76666.67, HR=45000.0, Finance=70000.0}

// Q ⭐: Highest-paid employee per department
employees.stream().collect(Collectors.groupingBy(Employee::getDepartment,
    Collectors.maxBy(Comparator.comparingInt(Employee::getSalary))));
// IT=Optional[Steve], HR=Optional[Mary], Finance=Optional[Peter]

// Q ⭐: Only names per department
employees.stream().collect(Collectors.groupingBy(Employee::getDepartment,
    Collectors.mapping(Employee::getName, Collectors.toList())));
// {IT=[John, Smith, Steve], HR=[David, Mary], Finance=[Peter]}

// Q: Group + sorted keys (TreeMap) + count
employees.stream().collect(Collectors.groupingBy(Employee::getDepartment, TreeMap::new, Collectors.counting()));
// {Finance=1, HR=2, IT=3}
```
`groupingBy()` returns a `HashMap` by default. Pass a map supplier (`TreeMap::new`, `LinkedHashMap::new`) to control order.

### 5.4 ⭐ `partitioningBy()`
**Purpose:** Split into exactly two groups (`true` / `false`); both keys always present.

```java
// Q: Partition names by length > 4
names.stream().collect(Collectors.partitioningBy(n -> n.length() > 4));
// {false=[Ravi, Amit, Ravi, Amit, Ravi], true=[Priya]}

// Q: Partition employees by salary > 60000
employees.stream().collect(Collectors.partitioningBy(e -> e.getSalary() > 60000));
// {false=[John, David, Mary], true=[Smith, Peter, Steve]}

// Q: Count each side
employees.stream().collect(Collectors.partitioningBy(e -> e.getSalary() > 60000, Collectors.counting()));
// {false=3, true=3}
```

| | `groupingBy` | `partitioningBy` |
|---|---|---|
| Uses | Classifier function | Predicate |
| Groups | Many | Always 2 |
| Key | Any type | `Boolean` |

### 5.5 `counting()`, `summingInt()`, `averagingInt()`
```java
nums.stream().collect(Collectors.counting());                    // 8 (Long)
employees.stream().collect(Collectors.summingInt(Employee::getSalary));    // 390000
nums.stream().collect(Collectors.averagingInt(n -> n));          // 16.875 (Double)
```
- Use `count()` for a plain total. Use `Collectors.counting()` when **composing**.
- `summingInt` → `Integer`. `averagingXxx` → always `Double`. Variants: `summingLong/Double`, `averagingLong/Double`.

### 5.6 `maxBy()` / `minBy()`
```java
nums.stream().collect(Collectors.maxBy(Integer::compareTo));   // Optional[40]
nums.stream().collect(Collectors.minBy(Integer::compareTo));   // Optional[5]
```
Use `stream.max()` / `min()` directly. Use `maxBy()` / `minBy()` when **composing** (e.g. inside `groupingBy`).

### 5.7 `joining()`
**Purpose:** Join a stream of `CharSequence`. Use `map()` first for objects.

```java
names.stream().distinct().collect(Collectors.joining());                 // RaviAmitPriya
names.stream().distinct().collect(Collectors.joining(", "));             // Ravi, Amit, Priya
names.stream().distinct().collect(Collectors.joining(", ", "[", "]"));   // [Ravi, Amit, Priya]
```

### 5.8 `mapping()`
**Purpose:** Transform elements **before** the downstream collector. Mainly used inside `groupingBy`.

```java
// Q: Names per department as a comma-separated String
employees.stream().collect(Collectors.groupingBy(Employee::getDepartment,
    Collectors.mapping(Employee::getName, Collectors.joining(", "))));
// {IT=John, Smith, Steve, HR=David, Mary, Finance=Peter}

// Q: Unique names per department as a Set
employees.stream().collect(Collectors.groupingBy(Employee::getDepartment,
    Collectors.mapping(Employee::getName, Collectors.toSet())));
```

### 5.9 `collectingAndThen()`
**Purpose:** Collect first, then apply a finishing function.

```java
// Q: Collect into an unmodifiable List
names.stream().collect(Collectors.collectingAndThen(Collectors.toList(), Collections::unmodifiableList));

// Q: Count of unique elements
names.stream().collect(Collectors.collectingAndThen(Collectors.toSet(), Set::size));   // 3

// Q ⭐: Highest-paid employee NAME per department
employees.stream().collect(Collectors.groupingBy(Employee::getDepartment,
    Collectors.collectingAndThen(
        Collectors.maxBy(Comparator.comparingInt(Employee::getSalary)),
        opt -> opt.get().getName())));
// {IT=Steve, HR=Mary, Finance=Peter}
```

### 5.10 `summarizingInt()` and `teeing()` (bonus)
```java
// Q: Count, sum, min, avg, max in one pass
IntSummaryStatistics st = employees.stream().collect(Collectors.summarizingInt(Employee::getSalary));
st.getCount(); st.getSum(); st.getMin(); st.getMax(); st.getAverage();
// 6, 390000, 40000, 90000, 65000.0

// Q: Two collectors at once, then combine (Java 12+)
nums.stream().collect(Collectors.teeing(
    Collectors.minBy(Integer::compare),
    Collectors.maxBy(Integer::compare),
    (mn, mx) -> mn.get() + " - " + mx.get()));   // 5 - 40

// Q: Filter inside a group (keeps empty groups, unlike filter() before groupingBy)
employees.stream().collect(Collectors.groupingBy(Employee::getDepartment,
    Collectors.filtering(e -> e.getSalary() > 60000, Collectors.toList())));
```

### 5.11 Must-know combinations

| Combination | Use case |
|---|---|
| `groupingBy` + `counting` | Count per group / frequency |
| `groupingBy` + `summingInt` | Total per group |
| `groupingBy` + `averagingInt` | Average per group |
| `groupingBy` + `maxBy` / `minBy` | Top / bottom per group |
| `groupingBy` + `mapping` + `toList` | Names per group |
| `groupingBy` + `mapping` + `joining` | Comma-separated names per group |
| `groupingBy` + `collectingAndThen(maxBy, ...)` | Name of top item per group |
| `partitioningBy` + `counting` | Count by condition |
| `toMap` + merge function | Handle duplicate keys |
| `collectingAndThen` + `toList` | Immutable result |

---

## 6. Advanced Stream Operations

### 6.1 ⭐ `flatMap()`
**Purpose:** Flatten a stream of collections into one stream (1 → 0..many).

```java
List<List<Integer>> nested = Arrays.asList(
    Arrays.asList(1, 2), Arrays.asList(3, 4), Arrays.asList(5, 6));

// Q: What does map() give for nested lists?
nested.stream().map(List::stream).collect(Collectors.toList());     // 3 separate Streams (not flattened)

// Q ⭐: Flatten a List of Lists
nested.stream().flatMap(List::stream).collect(Collectors.toList()); // [1, 2, 3, 4, 5, 6]

// Q: Split sentences into words
Arrays.asList("Java is powerful", "Spring Boot is popular").stream()
    .flatMap(s -> Arrays.stream(s.split(" ")))
    .collect(Collectors.toList());
// [Java, is, powerful, Spring, Boot, is, popular]

// Q: All unique skills from a Map<String, List<String>>
Map<String, List<String>> skills = new HashMap<>();
skills.put("John",  Arrays.asList("Java", "Spring", "SQL"));
skills.put("David", Arrays.asList("Python", "Django"));
skills.put("Smith", Arrays.asList("Java", "Microservices"));

skills.values().stream().flatMap(List::stream).distinct().collect(Collectors.toList());
// [Java, Spring, SQL, Python, Django, Microservices] (order depends on HashMap)
```

| `map()` | `flatMap()` |
|---|---|
| 1 input → 1 output | 1 input → 0 or more outputs |
| Transforms | Transforms **and flattens** |
| Can give `Stream<Stream<T>>` | Gives `Stream<T>` |

### 6.2 ⭐ `mapToInt()`, `mapToLong()`, `mapToDouble()`
**Purpose:** Object stream → primitive stream (no boxing, gives `sum()`, `average()`, etc.).

```java
// Q: Sum numbers stored as Strings
Arrays.asList("10", "20", "30").stream().mapToInt(Integer::parseInt).sum();   // 60

// Q: Total salary as long
employees.stream().mapToLong(Employee::getSalary).sum();                      // 390000

// Q: Prices with 18% tax
Arrays.asList(100, 200, 300, 400).stream().mapToDouble(p -> p * 1.18).sum();  // 1180.0

// Q: Average salary
employees.stream().mapToInt(Employee::getSalary).average();   // OptionalDouble[65000.0]

// Q: Statistics in one pass
IntSummaryStatistics s = employees.stream().mapToInt(Employee::getSalary).summaryStatistics();
// count=6, sum=390000, min=40000, max=90000, average=65000.0
```

| Method | Returns |
|---|---|
| `mapToInt()` | `IntStream` |
| `mapToLong()` | `LongStream` |
| `mapToDouble()` | `DoubleStream` |

**Primitive stream methods:** `sum()` `average()` `min()` `max()` `count()` `summaryStatistics()` `toArray()` `boxed()`
`min()` / `max()` / `average()` return `OptionalInt` / `OptionalDouble`. `sum()` of an empty stream is `0`.

### 6.3 ⭐ `boxed()`
**Purpose:** Primitive stream → object stream. Needed to `collect()` into a `List`.

```java
IntStream.of(10, 20, 30).boxed().collect(Collectors.toList());           // [10, 20, 30]
LongStream.of(100L, 200L).boxed().collect(Collectors.toList());          // [100, 200]
DoubleStream.of(10.5, 20.5).boxed().collect(Collectors.toList());        // [10.5, 20.5]
```
`mapToInt()` = object → primitive. `boxed()` = primitive → object. They are opposites.

### 6.4 `mapToObj()`
**Purpose:** `IntStream` → any object stream (very common with `chars()` and `range()`).

```java
// Q: Convert a String into a List of Characters
"java".chars().mapToObj(c -> (char) c).collect(Collectors.toList());     // [j, a, v, a]
```

### 6.5 ⭐ `IntStream.range()` vs `rangeClosed()`

| Method | Start | End |
|---|---|---|
| `range(1, 5)` | inclusive | **exclusive** → 1 2 3 4 |
| `rangeClosed(1, 5)` | inclusive | **inclusive** → 1 2 3 4 5 |

```java
// Q: Sum 1 to 100
IntStream.rangeClosed(1, 100).sum();                                              // 5050

// Q: Even numbers from 1 to 20
IntStream.rangeClosed(1, 20).filter(n -> n % 2 == 0).boxed().collect(Collectors.toList());
// [2, 4, 6, 8, 10, 12, 14, 16, 18, 20]

// Q: Multiplication table of 5
IntStream.rangeClosed(1, 10).forEach(i -> System.out.println("5 x " + i + " = " + (5 * i)));
```

### 6.6 ⭐ `Stream.iterate()` vs `Stream.generate()`

| | `iterate()` | `generate()` |
|---|---|---|
| Input | Seed + function | `Supplier` |
| Next value | Depends on previous | Independent |
| Behaviour | Potentially infinite | Potentially infinite |

> Always add `limit()` (or use the 3-argument `iterate`) to stop an infinite stream.

```java
// Q: First 10 even numbers
Stream.iterate(2, n -> n + 2).limit(10).collect(Collectors.toList());     // [2, 4, ..., 20]

// Q: Powers of 2
Stream.iterate(1, n -> n * 2).limit(8).collect(Collectors.toList());      // [1, 2, 4, 8, 16, 32, 64, 128]

// Q: Numbers until a condition fails (Java 9+)
Stream.iterate(1, n -> n <= 10, n -> n + 1).collect(Collectors.toList()); // [1..10]

// Q: Repeat a value 5 times
Stream.generate(() -> "Java").limit(5).collect(Collectors.toList());     // [Java x5]

// Q: 5 random integers (0-99)
Stream.generate(() -> new Random().nextInt(100)).limit(5).collect(Collectors.toList());

// Q: 3 UUIDs
Stream.generate(() -> UUID.randomUUID().toString()).limit(3).collect(Collectors.toList());
```

### 6.7 ⭐ `peek()`
**Purpose:** Inspect elements mid-pipeline for **debugging**. Does not change elements. Intermediate and lazy.

```java
nums.stream()
    .filter(n -> n > 10)
    .peek(n -> System.out.println("After filter: " + n))
    .map(n -> n * 2)
    .peek(n -> System.out.println("After map: " + n))
    .collect(Collectors.toList());
```
- Does nothing without a terminal operation.
- Do **not** use for business logic or to modify external state.

### 6.8 ⭐ `Optional`
**Purpose:** Container for a value that may be absent; avoids `null` checks.

```java
Optional<Integer> r = nums.stream().filter(n -> n > 100).findFirst();   // Optional.empty
int v = r.orElse(0);                                                    // 0
```

| Method | Purpose |
|---|---|
| `isPresent()` / `isEmpty()` (Java 11) | Check presence |
| `get()` | Value, **throws if empty** (avoid) |
| `orElse(x)` | Default (evaluated **eagerly**) |
| `orElseGet(() -> x)` | Default (evaluated **lazily**) |
| `orElseThrow(...)` | Throw if empty |
| `ifPresent(...)` | Run action if present |
| `ifPresentOrElse(...)` (Java 9) | Present / empty handlers |
| `map()` / `filter()` | Transform / filter the value |
| `Optional.of(x)` | `x` must be non-null |
| `Optional.ofNullable(x)` | `x` may be null |

`findFirst()`, `findAny()`, `min()`, `max()` return `Optional` (primitive streams return `OptionalInt` / `OptionalDouble`).

### 6.9 ⭐ Custom Comparators and multi-field sorting

```java
// Q: Salary ascending
employees.stream().sorted(Comparator.comparingInt(Employee::getSalary));

// Q: Salary descending
employees.stream().sorted(Comparator.comparingInt(Employee::getSalary).reversed());

// Q: Name alphabetically
employees.stream().sorted(Comparator.comparing(Employee::getName));

// Q ⭐: Department ascending, then salary descending
employees.stream().sorted(
    Comparator.comparing(Employee::getDepartment)
        .thenComparing(Comparator.comparingInt(Employee::getSalary).reversed()));
// Peter-Finance-70000, Mary-HR-50000, David-HR-40000, Steve-IT-90000, Smith-IT-80000, John-IT-60000

// Q ⭐: Salary descending, then name ascending
employees.stream().sorted(
    Comparator.comparingInt(Employee::getSalary).reversed()
        .thenComparing(Employee::getName));

// Q: Null-safe sort (nulls last)
Arrays.asList("Java", null, "Spring", "Python").stream()
    .sorted(Comparator.nullsLast(Comparator.naturalOrder()))
    .collect(Collectors.toList());
// [Java, Python, Spring, null]
```

| Comparator method | Purpose |
|---|---|
| `comparing()` / `comparingInt()` / `comparingDouble()` | Compare by property |
| `thenComparing()` / `thenComparingInt()` | Secondary sort |
| `reversed()` | Reverse |
| `naturalOrder()` / `reverseOrder()` | Natural ascending / descending |
| `nullsFirst()` / `nullsLast()` | Null handling |

- `sorted()` is **stable** (equal elements keep their original order).
- Avoid `(a, b) -> b.getSalary() - a.getSalary()`: subtraction can **overflow**. Use `comparingInt`.

### 6.10 Primitive Streams vs Object Streams

| Feature | Object Stream | Primitive Stream |
|---|---|---|
| Types | `Stream<Integer>`, `Stream<String>` | `IntStream`, `LongStream`, `DoubleStream` |
| Boxing | May box / unbox | No boxing |
| `sum()` / `average()` | Not available | Available |
| `min()` / `max()` | Needs `Comparator` | Direct |
| Collecting | Directly | Usually `boxed()` first |

### 6.11 ⭐ Parallel Streams

```java
List<Integer> r = nums.parallelStream().filter(n -> n > 10).collect(Collectors.toList());
```

| `stream()` | `parallelStream()` |
|---|---|
| Sequential | Multiple threads (ForkJoin common pool) |
| Predictable order | Processing order may differ |
| Good default | Use for suitable workloads |

```java
// Q: forEach vs forEachOrdered in parallel
IntStream.rangeClosed(1, 10).parallel().forEach(System.out::println);          // order NOT guaranteed
IntStream.rangeClosed(1, 10).parallel().forEachOrdered(System.out::println);   // 1..10 in order (slower)

// Q ⭐: Why is this wrong?
List<Integer> bad = new ArrayList<>();
nums.parallelStream().forEach(bad::add);      // ❌ ArrayList is not thread-safe

// Fix
List<Integer> good = nums.parallelStream().collect(Collectors.toList());   // ✅
```

| ✅ Use parallel when | ❌ Avoid when |
|---|---|
| Large dataset | Small dataset |
| CPU-heavy, independent tasks | Very simple operations |
| Stateless operations | Shared mutable state |
| Source splits easily (`ArrayList`, arrays, ranges) | Source splits poorly (`LinkedList`) |
| Order unimportant | Strict order needed |

Parallel streams are **not automatically faster**. Benchmark first. Blocking I/O is a poor fit for the common ForkJoin pool. Summing large ranges with `int` can overflow; use `long`.

---

## 7. Interview Coding Problems

### 7A. Numbers and Lists

Input: `nums = [10, 5, 20, 5, 30, 15, 40, 10]`

**Q ⭐ Find duplicate elements**
*Purpose:* Elements that occur more than once.
```java
List<Integer> data = Arrays.asList(10, 20, 30, 10, 40, 20, 50);

// Frequency approach (safe)
data.stream().collect(Collectors.groupingBy(n -> n, Collectors.counting()))
    .entrySet().stream().filter(e -> e.getValue() > 1)
    .map(Map.Entry::getKey).collect(Collectors.toList());          // [20, 10]

// Set.add approach (short, sequential only: stateful lambda)
Set<Integer> seen = new HashSet<>();
data.stream().filter(n -> !seen.add(n)).collect(Collectors.toSet());   // [10, 20]
```
`HashSet.add()` returns `false` when the element already exists.

**Q ⭐ Find unique elements (occur exactly once)**
```java
data.stream().collect(Collectors.groupingBy(n -> n, Collectors.counting()))
    .entrySet().stream().filter(e -> e.getValue() == 1)
    .map(Map.Entry::getKey).collect(Collectors.toList());          // [50, 40, 30]
```

**Q ⭐ Frequency of each element**
```java
data.stream().collect(Collectors.groupingBy(n -> n, Collectors.counting()));
// {50=1, 20=2, 40=1, 10=2, 30=1}
```

**Q Most frequent element**
```java
data.stream().collect(Collectors.groupingBy(n -> n, Collectors.counting()))
    .entrySet().stream().max(Map.Entry.comparingByValue()).map(Map.Entry::getKey);
```
With ties, which tied element is returned is not guaranteed.

**Q ⭐ Second-highest number**
```java
Arrays.asList(10, 30, 20, 50, 40, 50).stream()
    .distinct().sorted(Comparator.reverseOrder()).skip(1).findFirst().orElse(null);   // 40
```
Nth highest: change `skip(1)` to `skip(n - 1)`.

**Q ⭐ Top three highest distinct numbers**
```java
nums.stream().distinct().sorted(Comparator.reverseOrder()).limit(3).collect(Collectors.toList());
// [40, 30, 20]
```

**Q ⭐ Common elements between two lists**
```java
List<Integer> list1 = Arrays.asList(10, 20, 30, 40);
List<Integer> list2 = Arrays.asList(30, 40, 50, 60);

Set<Integer> lookup = new HashSet<>(list2);                      // O(1) lookups
list1.stream().filter(lookup::contains).distinct().collect(Collectors.toList());   // [30, 40]
```

**Q ⭐ Elements in list1 but not in list2**
```java
list1.stream().filter(n -> !lookup.contains(n)).collect(Collectors.toList());      // [10, 20]
```

**Q Merge two lists, unique and sorted**
```java
Stream.concat(list1.stream(), list2.stream()).distinct().sorted().collect(Collectors.toList());
// [10, 20, 30, 40, 50, 60]
```

**Q Sum of squares of even numbers**
```java
nums.stream().filter(n -> n % 2 == 0).map(n -> n * n).reduce(0, Integer::sum);   // 3100
```

**Q Average of even numbers**
```java
nums.stream().filter(n -> n % 2 == 0).mapToInt(Integer::intValue).average().orElse(0);   // 22.0
```

**Q Does a list contain duplicates?**
```java
nums.size() != nums.stream().distinct().count();     // true
```

**Q Convert List<Integer> to comma-separated String**
```java
nums.stream().map(String::valueOf).collect(Collectors.joining(","));   // 10,5,20,5,30,15,40,10
```

**Q Flatten a List of Lists**
```java
nested.stream().flatMap(List::stream).collect(Collectors.toList());
```

**Q Prime numbers up to 30**
```java
IntStream.rangeClosed(2, 30)
    .filter(n -> IntStream.rangeClosed(2, (int) Math.sqrt(n)).noneMatch(i -> n % i == 0))
    .boxed().collect(Collectors.toList());     // [2, 3, 5, 7, 11, 13, 17, 19, 23, 29]
```

**Q Factorial of 5**
```java
IntStream.rangeClosed(1, 5).reduce(1, (a, b) -> a * b);     // 120
```

**Q First 10 Fibonacci numbers**
```java
Stream.iterate(new int[]{0, 1}, f -> new int[]{f[1], f[0] + f[1]})
      .limit(10).map(f -> f[0]).collect(Collectors.toList());   // [0, 1, 1, 2, 3, 5, 8, 13, 21, 34]
```

**Q Sum of digits of 12345**
```java
String.valueOf(12345).chars().map(c -> c - '0').sum();      // 15
```

---

### 7B. Strings and Characters

**Q ⭐ Character frequency of "swiss" (keep order)**
```java
Map<Character, Long> freq = "swiss".chars().mapToObj(c -> (char) c)
    .collect(Collectors.groupingBy(c -> c, LinkedHashMap::new, Collectors.counting()));
// {s=3, w=1, i=1}
```

**Q ⭐ First non-repeating character**
```java
freq.entrySet().stream().filter(e -> e.getValue() == 1)
    .map(Map.Entry::getKey).findFirst().orElse(null);       // w
```
`LinkedHashMap` preserves insertion order, which is what makes "first" meaningful.

**Q ⭐ First repeating character**
```java
Set<Character> seen = new HashSet<>();
"swiss".chars().mapToObj(c -> (char) c).filter(c -> !seen.add(c)).findFirst().orElse(null);   // s
```

**Q ⭐ Word frequency**
```java
String sentence = "java spring java microservices spring";

Arrays.stream(sentence.split("\\s+"))
    .collect(Collectors.groupingBy(w -> w, LinkedHashMap::new, Collectors.counting()));
// {java=2, spring=2, microservices=1}
```

**Q ⭐ Duplicate words in a sentence**
```java
Arrays.stream(sentence.split("\\s+"))
    .collect(Collectors.groupingBy(w -> w, LinkedHashMap::new, Collectors.counting()))
    .entrySet().stream().filter(e -> e.getValue() > 1)
    .map(Map.Entry::getKey).collect(Collectors.toList());     // [java, spring]
```

**Q Longest string**
```java
Arrays.asList("Java", "SpringBoot", "Microservices").stream()
    .max(Comparator.comparingInt(String::length)).orElse(null);    // Microservices
```

**Q Group strings by first character**
```java
Arrays.asList("Apple", "Ant", "Ball", "Bat", "Cat").stream()
    .collect(Collectors.groupingBy(w -> w.charAt(0)));
// {A=[Apple, Ant], B=[Ball, Bat], C=[Cat]}
```

**Q Count vowels in "java streams"**
```java
"java streams".chars().filter(c -> "aeiou".indexOf(c) != -1).count();    // 4
```

**Q Remove duplicate characters from "programming"**
```java
"programming".chars().distinct().mapToObj(c -> String.valueOf((char) c)).collect(Collectors.joining());
// progamin
```

**Q Reverse a String using streams**
```java
String s = "stream";
IntStream.range(0, s.length()).mapToObj(i -> String.valueOf(s.charAt(s.length() - 1 - i)))
    .collect(Collectors.joining());                                  // maerts
```

**Q Is a String a palindrome?**
```java
String p = "madam";
IntStream.range(0, p.length() / 2).allMatch(i -> p.charAt(i) == p.charAt(p.length() - 1 - i));   // true
```

**Q Reverse each word in a sentence**
```java
Arrays.stream("java is fun".split(" "))
    .map(w -> new StringBuilder(w).reverse().toString())
    .collect(Collectors.joining(" "));          // avaj si nuf
```

**Q Capitalise the first letter of each word**
```java
Arrays.stream("java is fun".split(" "))
    .map(w -> w.substring(0, 1).toUpperCase() + w.substring(1))
    .collect(Collectors.joining(" "));          // Java Is Fun
```

**Q Sort strings by length, then alphabetically**
```java
Arrays.asList("Spring", "Java", "Go", "Kotlin").stream()
    .sorted(Comparator.comparingInt(String::length).thenComparing(Comparator.naturalOrder()))
    .collect(Collectors.toList());              // [Go, Java, Kotlin, Spring]
```

**Q ⭐ Group anagrams**
```java
Arrays.asList("listen", "silent", "enlist", "google", "gogole").stream()
    .collect(Collectors.groupingBy(w -> {
        char[] c = w.toCharArray(); Arrays.sort(c); return new String(c);
    }))
    .values();
// [[listen, silent, enlist], [google, gogole]]  (group order may vary)
```

**Q Is a String made only of digits?**
```java
"12345".chars().allMatch(Character::isDigit);     // true
```

---

### 7C. Employee Problems (very common)

**Q Sort employees by salary**
```java
employees.stream().sorted(Comparator.comparingInt(Employee::getSalary)).collect(Collectors.toList());
// David, Mary, John, Peter, Smith, Steve
```

**Q ⭐ Sort by department, then salary**
```java
employees.stream().sorted(Comparator.comparing(Employee::getDepartment)
    .thenComparingInt(Employee::getSalary)).collect(Collectors.toList());
```

**Q ⭐ Second-highest salary**
```java
employees.stream().map(Employee::getSalary).distinct()
    .sorted(Comparator.reverseOrder()).skip(1).findFirst().orElse(null);     // 80000
```

**Q ⭐ Employee with the second-highest salary**
```java
employees.stream().sorted(Comparator.comparingInt(Employee::getSalary).reversed())
    .skip(1).findFirst().orElse(null);                                       // Smith-IT-80000
```

**Q Top 3 highest-paid employee names**
```java
employees.stream().sorted(Comparator.comparingInt(Employee::getSalary).reversed())
    .limit(3).map(Employee::getName).collect(Collectors.toList());           // [Steve, Smith, Peter]
```

**Q Names of employees with salary > 60000**
```java
employees.stream().filter(e -> e.getSalary() > 60000).map(Employee::getName)
    .collect(Collectors.toList());                                           // [Smith, Peter, Steve]
```

**Q Names starting with "S"**
```java
employees.stream().map(Employee::getName).filter(n -> n.startsWith("S")).collect(Collectors.toList());
// [Smith, Steve]
```

**Q ⭐ Group employees by department**
```java
employees.stream().collect(Collectors.groupingBy(Employee::getDepartment));
```

**Q ⭐ Highest-paid employee in each department**
```java
employees.stream().collect(Collectors.groupingBy(Employee::getDepartment,
    Collectors.maxBy(Comparator.comparingInt(Employee::getSalary))));
```

**Q ⭐ Average salary by department**
```java
employees.stream().collect(Collectors.groupingBy(Employee::getDepartment,
    Collectors.averagingInt(Employee::getSalary)));
```

**Q Department with the highest total salary**
```java
employees.stream()
    .collect(Collectors.groupingBy(Employee::getDepartment, Collectors.summingInt(Employee::getSalary)))
    .entrySet().stream().max(Map.Entry.comparingByValue()).map(Map.Entry::getKey).orElse(null);   // IT
```

**Q Department with the most employees**
```java
employees.stream()
    .collect(Collectors.groupingBy(Employee::getDepartment, Collectors.counting()))
    .entrySet().stream().max(Map.Entry.comparingByValue()).map(Map.Entry::getKey).orElse(null);   // IT
```

**Q Partition employees by salary**
```java
employees.stream().collect(Collectors.partitioningBy(e -> e.getSalary() > 50000));
// true = salary > 50000, false = salary <= 50000
```

**Q Department-wise names, joined**
```java
employees.stream().collect(Collectors.groupingBy(Employee::getDepartment,
    Collectors.mapping(Employee::getName, Collectors.joining(", "))));
```

**Q ⭐ Convert List<Employee> into Map (id → employee), handle duplicate ids**
```java
employees.stream().collect(Collectors.toMap(Employee::getId, e -> e, (oldV, newV) -> newV));
```

**Q Salary statistics (min, max, avg, sum)**
```java
employees.stream().mapToInt(Employee::getSalary).summaryStatistics();
```

**Q Total salary of all employees**
```java
employees.stream().mapToInt(Employee::getSalary).sum();      // 390000
```

**Q Highest-paid employee overall**
```java
employees.stream().max(Comparator.comparingInt(Employee::getSalary)).orElse(null);   // Steve-IT-90000
```

---

## 8. Output Prediction and Tricky Cases

**T1 ⭐ Vertical (element-by-element) processing**
```java
Stream.of("one", "two", "three")
    .filter(s -> { System.out.println("filter: " + s); return s.length() == 3; })
    .map(s -> { System.out.println("map: " + s); return s.toUpperCase(); })
    .forEach(s -> System.out.println("forEach: " + s));
// filter: one  -> map: one  -> forEach: ONE
// filter: two  -> map: two  -> forEach: TWO
// filter: three            (filtered out, stops there)
```
Each element goes through the **whole pipeline** before the next one starts. Exception: `sorted()` is a barrier and must see all elements first.

**T2 `limit` before or after `filter`**
```java
Stream.iterate(1, n -> n + 1).filter(n -> n % 2 == 0).limit(3).collect(Collectors.toList());   // [2, 4, 6]
Stream.iterate(1, n -> n + 1).limit(3).filter(n -> n % 2 == 0).collect(Collectors.toList());   // [2]
```
Without `limit`, an infinite stream never ends.

**T3 No terminal operation**
```java
nums.stream().peek(System.out::println).map(n -> n * 2);     // prints nothing
```

**T4 Reusing a stream**
```java
Stream<Integer> s = nums.stream();
s.count();
s.count();     // IllegalStateException
```

**T5 `count()` can skip the pipeline (Java 9+)**
```java
List.of(1, 2, 3).stream().peek(System.out::println).count();   // prints nothing: size known from source
```
Add a `filter()` and `peek()` runs again.

**T6 Duplicate keys in `toMap`**
```java
Stream.of("a", "a").collect(Collectors.toMap(s -> s, s -> 1));   // IllegalStateException: Duplicate key a
```

**T7 Modifying the source while streaming**
```java
List<Integer> src = new ArrayList<>(Arrays.asList(1, 2, 3));
src.stream().forEach(n -> src.add(n));    // ConcurrentModificationException
```

**T8 Empty-stream results**
```java
Stream.<Integer>empty().mapToInt(Integer::intValue).sum();       // 0
Stream.<Integer>empty().mapToInt(Integer::intValue).average();   // OptionalDouble.empty
Stream.<Integer>empty().max(Integer::compare);                   // Optional.empty
Stream.<Integer>empty().allMatch(n -> n > 0);                    // true
Stream.<Integer>empty().anyMatch(n -> n > 0);                    // false
```

**T9 Mutability of results**
```java
Collectors.toList()      // no guarantee about mutability
Stream.toList()          // Java 16+: unmodifiable list
```

**T10 Lambdas need effectively-final variables**
```java
int count = 0;
nums.forEach(n -> count++);     // ❌ compile error: count must be effectively final
```
Use `AtomicInteger`, or better, `count()` / `reduce()`.

---

## 9. Common Traps and Mistakes

| Question | Correct answer |
|---|---|
| Does a Stream store data? | No. The source collection / array does. |
| Can a Stream be reused? | No → `IllegalStateException`. |
| Are intermediate operations eager? | No, lazy. |
| Does `filter()` / `sorted()` modify the source? | No. |
| `map()` vs `flatMap()`? | Transform vs transform + flatten. |
| Does `distinct()` work on custom objects? | Yes, via `equals()` + `hashCode()`. |
| What does `findFirst()` return? | `Optional`. |
| Duplicate keys in `toMap()`? | `IllegalStateException` without a merge function. |
| `Stream<Integer>` vs `IntStream`? | Wrapper objects vs primitives. |
| Is `peek()` for business logic? | No, debugging only. |
| Modify the source while streaming? | Avoid. `ConcurrentModificationException` / undefined behaviour. |
| Are parallel streams always faster? | No. |
| Is `Map` directly streamable? | No: `entrySet()`, `keySet()`, `values()`. |
| `reduce()` purpose? | Combine elements into one result. |
| `boxed()` purpose? | Primitive stream → wrapper object stream. |
| `Arrays.asList(int[])`? | Gives `List<int[]>`, not `List<Integer>`. |
| `Stream.of(int[])`? | One element (the array). Use `Arrays.stream`. |
| `get()` on Optional? | Throws `NoSuchElementException` if empty. |
| `orElse` vs `orElseGet`? | Eager vs lazy default. |
| `forEach` vs `forEachOrdered`? | No order guarantee vs encounter order (parallel). |
| Comparator by subtraction? | Can overflow. Use `comparingInt`. |

---

## 10. Method Selection Cheat Sheet

| Requirement | Think of |
|---|---|
| Select matching elements | `filter()` |
| Transform elements | `map()` |
| Sort | `sorted()` |
| Remove duplicates | `distinct()` |
| First N / skip N | `limit()` / `skip()` |
| Count | `count()` |
| Sum | `sum()` / `reduce()` |
| Min / max | `min()` / `max()` |
| First match / any match | `findFirst()` / `findAny()` |
| Check at least one / all / none | `anyMatch()` / `allMatch()` / `noneMatch()` |
| Create List / Set / Map | `toList()` / `toSet()` / `toMap()` |
| Group by property | `groupingBy()` |
| Split in two | `partitioningBy()` |
| Count occurrences | `groupingBy()` + `counting()` |
| Join strings | `joining()` |
| Flatten nested collections | `flatMap()` |
| Object → primitive stream | `mapToInt()` / `mapToLong()` / `mapToDouble()` |
| Primitive → object stream | `boxed()` / `mapToObj()` |
| Multi-field sort | `thenComparing()` |
| Handle missing value | `Optional` |
| Number sequence | `range()` / `rangeClosed()` / `iterate()` |
| Merge two streams | `Stream.concat()` |
| Slice while condition holds | `takeWhile()` / `dropWhile()` |
| Debug pipeline | `peek()` |
| Statistics in one pass | `summaryStatistics()` / `summarizingInt()` |

---

## 11. Syntax Templates

```java
// 1. Filter + collect
list.stream().filter(n -> cond).collect(Collectors.toList());

// 2. Filter + map + collect
list.stream().filter(n -> cond).map(n -> transform).collect(Collectors.toList());

// 3. Distinct + sort + limit (top N)
list.stream().distinct().sorted(Comparator.reverseOrder()).limit(3).collect(Collectors.toList());

// 4. Frequency
list.stream().collect(Collectors.groupingBy(v -> v, Collectors.counting()));

// 5. Group + mapping
emps.stream().collect(Collectors.groupingBy(Employee::getDepartment,
    Collectors.mapping(Employee::getName, Collectors.toList())));

// 6. Group + max
emps.stream().collect(Collectors.groupingBy(Employee::getDepartment,
    Collectors.maxBy(Comparator.comparingInt(Employee::getSalary))));

// 7. Array -> List
Arrays.stream(arr).boxed().collect(Collectors.toList());

// 8. List -> array
list.stream().mapToInt(Integer::intValue).toArray();

// 9. Second highest
list.stream().distinct().sorted(Comparator.reverseOrder()).skip(1).findFirst().orElse(null);

// 10. Map entries filter -> Map
map.entrySet().stream().filter(e -> cond)
   .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

// 11. Sort Map by value desc, keep order
map.entrySet().stream().sorted(Map.Entry.<K, V>comparingByValue().reversed())
   .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (a, b) -> a, LinkedHashMap::new));

// 12. Most frequent element
list.stream().collect(Collectors.groupingBy(v -> v, Collectors.counting()))
    .entrySet().stream().max(Map.Entry.comparingByValue()).map(Map.Entry::getKey);

// 13. Character stream from String
str.chars().mapToObj(c -> (char) c)

// 14. Flatten
nested.stream().flatMap(List::stream).collect(Collectors.toList());
```

---

## 12. Rapid-Fire Theory Q&A

| # | Question | Short answer |
|---|---|---|
| 1 ⭐ | What is a Stream? | A sequence of elements supporting functional-style operations. It does not store data. |
| 2 ⭐ | Stream vs Collection? | Collection stores; Stream processes. Stream is single-use and lazy. |
| 3 ⭐ | Intermediate vs terminal? | Intermediate returns a Stream (lazy). Terminal triggers execution and ends the pipeline. |
| 4 ⭐ | Why lazy? | Nothing runs until a terminal operation. Enables short-circuiting and single-pass processing. |
| 5 ⭐ | `map` vs `flatMap`? | 1→1 vs 1→many flattened. |
| 6 ⭐ | `filter` vs `map`? | Select vs transform. |
| 7 ⭐ | `findFirst` vs `findAny`? | First in encounter order vs any match (better for parallel). |
| 8 ⭐ | `Stream<Integer>` vs `IntStream`? | Boxed objects vs primitives. `IntStream` has `sum()`, `average()`. |
| 9 ⭐ | Why `boxed()`? | Primitive stream → object stream so it can be collected. |
| 10 ⭐ | `range` vs `rangeClosed`? | End exclusive vs inclusive. |
| 11 | `iterate` vs `generate`? | Depends on previous element vs independent `Supplier`. |
| 12 ⭐ | Purpose of `peek()`? | Debugging; does not change elements. |
| 13 ⭐ | What is `Optional`? | Container that may or may not hold a value; avoids null checks. |
| 14 | `Optional.of` vs `ofNullable`? | `of` throws NPE on null; `ofNullable` allows null. |
| 15 ⭐ | `orElse` vs `orElseGet`? | Eager default vs lazy `Supplier`. |
| 16 ⭐ | Duplicate keys in `toMap()`? | `IllegalStateException`; pass a merge function. |
| 17 ⭐ | `groupingBy` vs `partitioningBy`? | Many groups, any key vs exactly two groups, Boolean key. |
| 18 | `groupingBy` vs `toMap`? | `groupingBy` collects values into collections; `toMap` maps one key to one value. |
| 19 ⭐ | `reduce` vs `collect`? | `reduce` = immutable fold to a single value; `collect` = mutable accumulation (lists, maps, strings). |
| 20 | `count()` vs `Collectors.counting()`? | `long` terminal op vs `Long` collector for composing. |
| 21 ⭐ | `max()` vs `maxBy()`? | Direct terminal op vs collector for use inside `groupingBy`. |
| 22 ⭐ | Sort by multiple fields? | `Comparator.comparing(...).thenComparing(...)`. |
| 23 | Is `sorted()` stable? | Yes for ordered streams. |
| 24 ⭐ | How do parallel streams work? | The source is split, processed by ForkJoin common pool threads, and results are combined. |
| 25 ⭐ | When to avoid parallel streams? | Small data, shared mutable state, order-sensitive, blocking I/O. |
| 26 | `forEach` vs `forEachOrdered`? | No order guarantee vs encounter order. |
| 27 ⭐ | What if threads modify a shared `ArrayList`? | Race conditions: lost updates, exceptions, corrupted data. |
| 28 ⭐ | Are Streams reusable? | No. A consumed stream throws `IllegalStateException`. |
| 29 | What are short-circuiting operations? | `limit`, `findFirst`, `findAny`, `anyMatch`, `allMatch`, `noneMatch`, `takeWhile`. |
| 30 | Stateless vs stateful ops? | Stateless: `filter`, `map`. Stateful: `sorted`, `distinct`. |
| 31 | Can a stream be infinite? | Yes (`iterate`, `generate`). Use `limit` or a short-circuit op. |
| 32 | Why can't lambdas modify local variables? | They must be effectively final. Use collectors / `reduce` instead. |
| 33 | `Collectors.toList()` vs `Stream.toList()`? | Unspecified mutability vs unmodifiable (Java 16+). |
| 34 | Stream vs for-loop? | Declarative, composable, parallelisable; loops can be faster for trivial work and allow `break` / mutation. |
| 35 | Can we `break` out of a stream? | No. Use `limit`, `takeWhile`, `findFirst` or `anyMatch`. |

---

## 13. Final Revision Checklist

Tick a topic only when you can **explain it and write the code without looking**.

**Fundamentals**
- [ ] Stream vs Collection, pipeline, lazy evaluation, single use
- [ ] Intermediate vs terminal, stateless vs stateful, short-circuiting
- [ ] Lambda, method reference, functional interfaces

**Core methods**
- [ ] `filter` `map` `sorted` `distinct` `limit` `skip`
- [ ] `count` `min` `max` `findFirst` `findAny` `forEach`
- [ ] `anyMatch` `allMatch` `noneMatch`
- [ ] `reduce` (all 3 forms) and `collect`

**Collections and arrays**
- [ ] List / Set operations, set intersection and difference
- [ ] Map: `entrySet`, filter, sort by key / value, sorted `LinkedHashMap`
- [ ] `Arrays.stream`, `boxed`, `mapToInt`, array ↔ list
- [ ] Second-largest, top-N, common elements

**Collectors**
- [ ] `toList` `toSet` `toMap` (merge function, `TreeMap`)
- [ ] `groupingBy` + `counting` / `summingInt` / `averagingInt` / `maxBy` / `mapping`
- [ ] `partitioningBy`, `joining`, `collectingAndThen`, `summarizingInt`

**Advanced**
- [ ] `flatMap`, `mapToObj`, `range` / `rangeClosed`, `iterate` / `generate`
- [ ] `peek`, `Optional` methods, `Comparator` chains
- [ ] Parallel streams: when, when not, thread safety
- [ ] `takeWhile`, `dropWhile`, `Stream.concat`, `teeing`

**Interview coding**
- [ ] Duplicates, unique, frequency, most frequent
- [ ] First repeating / non-repeating character
- [ ] Palindrome, reverse, anagrams, vowel count
- [ ] Second-highest salary, top-N, highest per department
- [ ] Department stats: count, total, average, names
- [ ] Output-prediction cases (Section 8)

---

**Final strategy:** Revise Sections 0, 1, 5, 6 and 9 first. Then practise Sections 7 and 8 **without looking at the solutions**, and finish with the Rapid-Fire Q&A (Section 12).