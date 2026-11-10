# Level 6: Advanced Java Streams — Quick Revision Cheat Sheet

> **Goal:** Advanced Stream operations, primitive streams, `Optional`, custom sorting, debugging and parallel processing.
> **Why it matters:** Java backend interviews test whether you can solve real collection-processing problems with Streams.

---

## 0. Setup

```java
import java.util.*;
import java.util.stream.*;

List<Integer> nums  = Arrays.asList(10, 20, 30, 40, 50, 20, 30);
List<String>  names = Arrays.asList("Java", "Python", "Spring", "Java", "Microservices");

// Employee(id, name, department, salary) — same class as Level 5
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

## 1. All Methods at a Glance

| Method | Category | Purpose |
|---|---|---|
| `flatMap()` | Intermediate | Flatten nested collections |
| `mapToInt()` / `mapToLong()` / `mapToDouble()` | Intermediate | Convert to primitive stream |
| `boxed()` | Intermediate | Primitive stream → object stream |
| `range()` / `rangeClosed()` | Source | Generate numbers (exclusive / inclusive end) |
| `iterate()` | Source | Generate from the previous value |
| `generate()` | Source | Generate using a `Supplier` |
| `peek()` | Intermediate | Inspect elements (debugging) |
| `findFirst()` | Terminal | First element as `Optional` |
| `min()` / `max()` | Terminal | Min / max via `Comparator` |
| `toMap()` | Collector | Convert into `Map` |
| `thenComparing()` | Comparator | Multi-field sorting |
| `parallelStream()` / `sequential()` | Stream mode | Parallel / sequential processing |

---

## 2. `flatMap()`: Flatten Nested Collections

**Purpose:** Convert a stream of collections into a single stream of elements.

| `map()` | `flatMap()` |
|---|---|
| One input → one output | One input → zero or more outputs |
| Transforms only | Transforms **and flattens** |
| Can produce `Stream<Stream<T>>` | Produces `Stream<T>` |

```java
List<List<Integer>> numbers = Arrays.asList(
    Arrays.asList(1, 2, 3),
    Arrays.asList(4, 5, 6),
    Arrays.asList(7, 8, 9)
);

// Q: What does map() give for a List of Lists?
List<Stream<Integer>> r1 = numbers.stream().map(List::stream).collect(Collectors.toList());
// 3 separate Streams (not flattened)

// Q: How do you flatten a List of Lists into one List?
List<Integer> r2 = numbers.stream().flatMap(List::stream).collect(Collectors.toList());
// [1, 2, 3, 4, 5, 6, 7, 8, 9]
```

```java
List<String> sentences = Arrays.asList(
    "Java is powerful", "Spring Boot is popular", "Streams are useful");

// Q: How do you split sentences into individual words?
List<String> words = sentences.stream()
    .flatMap(s -> Arrays.stream(s.split(" ")))
    .collect(Collectors.toList());
// [Java, is, powerful, Spring, Boot, is, popular, Streams, are, useful]
```

```java
Map<String, List<String>> employeeSkills = new HashMap<>();
employeeSkills.put("John",  Arrays.asList("Java", "Spring", "SQL"));
employeeSkills.put("David", Arrays.asList("Python", "Django"));
employeeSkills.put("Smith", Arrays.asList("Java", "Microservices"));

// Q: How do you get all unique skills from a Map of Lists?
List<String> skills = employeeSkills.values().stream()
    .flatMap(List::stream)
    .distinct()
    .collect(Collectors.toList());
// [Java, Spring, SQL, Python, Django, Microservices]  (order depends on HashMap)
```

```java
List<List<Integer>> nested = Arrays.asList(
    Arrays.asList(10, 20, 30), Arrays.asList(20, 30, 40), Arrays.asList(30, 40, 50));

// Q: How do you find all occurrences of 30 across nested Lists?
List<Integer> thirties = nested.stream()
    .flatMap(List::stream)
    .filter(n -> n == 30)
    .collect(Collectors.toList());
// [30, 30, 30]
```

---

## 3. Primitive Streams: `mapToInt()`, `mapToLong()`, `mapToDouble()`

**Types:** `IntStream`, `LongStream`, `DoubleStream`

```java
// Q: How do you sum the lengths of all strings?
int totalLen = Stream.of("Java", "Python", "Spring", "Microservices")
    .mapToInt(String::length).sum();
// 29

// Q: How do you get total salary as a long?
long totalSalary = employees.stream().mapToLong(Employee::getSalary).sum();
// 390000

// Q: How do you add 18% tax to prices and sum them?
double withTax = Arrays.asList(100, 200, 300, 400).stream()
    .mapToDouble(p -> p * 1.18).sum();
// 1180.0

// Q: How do you calculate the average salary using mapToInt()?
OptionalDouble avg = employees.stream().mapToInt(Employee::getSalary).average();
// OptionalDouble[65000.0]
```

| Primitive stream method | Purpose |
|---|---|
| `sum()` | Sum all elements |
| `average()` | Average (`OptionalDouble`) |
| `min()` / `max()` | Minimum / maximum |
| `count()` | Count elements |
| `summaryStatistics()` | Count, sum, min, max, average together |
| `toArray()` | Convert to primitive array |

```java
// Q: How do you get count, sum, min, max and average in one pass?
IntSummaryStatistics stats = employees.stream()
    .mapToInt(Employee::getSalary).summaryStatistics();

stats.getCount();    // 6
stats.getSum();      // 390000
stats.getMin();      // 40000
stats.getMax();      // 90000
stats.getAverage();  // 65000.0
```

**Interview point:** Primitive streams avoid boxing and provide `sum()` / `average()` directly.

---

## 4. `boxed()`: Primitive Stream → Object Stream

| Primitive stream | `boxed()` result |
|---|---|
| `IntStream` | `Stream<Integer>` |
| `LongStream` | `Stream<Long>` |
| `DoubleStream` | `Stream<Double>` |

**Why needed?** `Collectors.toList()` collects objects, but an `IntStream` holds primitive `int` values.

```java
// Q: How do you collect an IntStream into a List<Integer>?
List<Integer> a = IntStream.of(10, 20, 30, 40).boxed().collect(Collectors.toList());
// [10, 20, 30, 40]

// Q: How do you convert a primitive int[] into a List<Integer>?
int[] arr = {10, 20, 30, 40, 50};
List<Integer> b = Arrays.stream(arr).boxed().collect(Collectors.toList());
// [10, 20, 30, 40, 50]

// Q: How do you collect a LongStream into a List<Long>?
List<Long> c = LongStream.of(100L, 200L, 300L).boxed().collect(Collectors.toList());
// [100, 200, 300]

// Q: How do you collect a DoubleStream into a List<Double>?
List<Double> d = DoubleStream.of(10.5, 20.5, 30.5).boxed().collect(Collectors.toList());
// [10.5, 20.5, 30.5]
```

> **Remember:** `mapToInt()` = object → primitive. `boxed()` = primitive → object. They are opposites.

---

## 5. `IntStream.range()` and `rangeClosed()`

| Method | Start | End |
|---|---|---|
| `range(1, 5)` | Inclusive | **Exclusive** → 1, 2, 3, 4 |
| `rangeClosed(1, 5)` | Inclusive | **Inclusive** → 1, 2, 3, 4, 5 |

```java
// Q: How do you print 1 to 4 using range()?
IntStream.range(1, 5).forEach(System.out::println);          // 1 2 3 4

// Q: How do you print 1 to 5 using rangeClosed()?
IntStream.rangeClosed(1, 5).forEach(System.out::println);    // 1 2 3 4 5

// Q: How do you generate 1..5 as a List?
List<Integer> l = IntStream.range(1, 6).boxed().collect(Collectors.toList());
// [1, 2, 3, 4, 5]

// Q: How do you calculate the sum of 1 to 100?
int sum = IntStream.rangeClosed(1, 100).sum();               // 5050

// Q: How do you print a multiplication table?
int number = 5;
IntStream.rangeClosed(1, 10)
    .forEach(i -> System.out.println(number + " x " + i + " = " + (number * i)));
// 5 x 1 = 5 ... 5 x 10 = 50

// Q: How do you generate even numbers from 1 to 20?
List<Integer> evens = IntStream.rangeClosed(1, 20)
    .filter(n -> n % 2 == 0).boxed().collect(Collectors.toList());
// [2, 4, 6, 8, 10, 12, 14, 16, 18, 20]
```

**Use cases:** number sequences, index loops, multiplication tables, range sums, array problems.

---

## 6. `Stream.iterate()` and `Stream.generate()`

| Feature | `iterate()` | `generate()` |
|---|---|---|
| Input | Seed + function | `Supplier` |
| Next element | Depends on the previous one | Independent |
| Functional interface | `UnaryOperator` | `Supplier` |
| Example | 1, 2, 3, 4, 5 | Random numbers |
| Behaviour | Potentially infinite | Potentially infinite |

> ⚠️ Both can be **infinite**. Use `limit()` or another short-circuiting operation.

### `iterate()`

```java
// Q: How do you generate 1 to 10?
Stream.iterate(1, n -> n + 1).limit(10).forEach(System.out::println);

// Q: How do you generate the first 10 even numbers?
List<Integer> e = Stream.iterate(2, n -> n + 2).limit(10).collect(Collectors.toList());
// [2, 4, 6, 8, 10, 12, 14, 16, 18, 20]

// Q: How do you generate powers of 2?
List<Integer> p = Stream.iterate(1, n -> n * 2).limit(8).collect(Collectors.toList());
// [1, 2, 4, 8, 16, 32, 64, 128]

// Q: How do you generate values until a condition fails (Java 9+)?
List<Integer> c = Stream.iterate(1, n -> n <= 10, n -> n + 1).collect(Collectors.toList());
// [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
```

### `generate()`

```java
// Q: How do you generate a constant value 5 times?
Stream.generate(() -> "Java").limit(5).collect(Collectors.toList());
// [Java, Java, Java, Java, Java]

// Q: How do you generate 5 random doubles?
Stream.generate(Math::random).limit(5).collect(Collectors.toList());

// Q: How do you generate 5 random integers (0-99)?
Stream.generate(() -> new Random().nextInt(100)).limit(5).collect(Collectors.toList());

// Q: How do you generate 3 UUID strings?
Stream.generate(() -> UUID.randomUUID().toString()).limit(3).collect(Collectors.toList());
```

---

## 7. `peek()`: Debugging Streams

**Purpose:** Run an action on elements as they flow through the pipeline **without changing them**.

```java
// Q: How do you see elements before and after a filter?
List<Integer> r = nums.stream()
    .peek(n -> System.out.println("Original: " + n))
    .filter(n -> n > 20)
    .peek(n -> System.out.println("After filter: " + n))
    .collect(Collectors.toList());
// Original: 10 / Original: 20 / Original: 30 / After filter: 30 / ...
// [30, 40, 50, 30]

// Q: How do you debug filter -> map step by step?
List<String> s = names.stream()
    .peek(n -> System.out.println("1. Original: " + n))
    .filter(n -> n.length() > 4)
    .peek(n -> System.out.println("2. After filter: " + n))
    .map(String::toUpperCase)
    .peek(n -> System.out.println("3. After map: " + n))
    .collect(Collectors.toList());

// Q: What is the execution order across filter, peek and map?
nums.stream()
    .filter(n -> { System.out.println("Filtering: " + n); return n > 20; })
    .peek(n -> System.out.println("Peeking: " + n))
    .map(n -> { System.out.println("Mapping: " + n); return n * 2; })
    .collect(Collectors.toList());
// Each element goes through the whole pipeline one at a time:
// Filtering: 10 -> Filtering: 20 -> Filtering: 30 -> Peeking: 30 -> Mapping: 30 -> ...
// [60, 80, 100, 60]
```

**Interview points**
- `peek()` is **intermediate** and **lazy**; it does nothing without a terminal operation.
- It does not transform elements. Use it only for debugging.
- Do **not** use it for business logic or to modify external state.

---

## 8. `Optional` with `findFirst()`, `min()`, `max()`

`Optional<T>` holds a non-null value or is empty, which avoids direct access to a missing result.

```java
// Q: How do you get the first element of a stream?
Optional<Integer> f1 = nums.stream().findFirst();                         // Optional[10]

// Q: How do you find the first even number?
nums.stream().filter(n -> n % 2 == 0).findFirst().ifPresent(System.out::println);   // 10

// Q: What does findFirst() return when nothing matches?
Optional<Integer> f2 = nums.stream().filter(n -> n > 100).findFirst();    // Optional.empty

// Q: How do you provide a default when the result is empty?
Integer f3 = nums.stream().filter(n -> n > 100).findFirst().orElse(0);    // 0
```

```java
// Q: How do you find the minimum number?
nums.stream().min(Comparator.naturalOrder());                              // Optional[10]

// Q: How do you find the employee with the minimum salary?
employees.stream().min(Comparator.comparingInt(Employee::getSalary));      // David - HR - 40000

// Q: How do you find the maximum number?
nums.stream().max(Comparator.naturalOrder());                              // Optional[50]

// Q: How do you find the employee with the maximum salary?
employees.stream().max(Comparator.comparingInt(Employee::getSalary));      // Steve - IT - 90000
```

| Optional method | Purpose |
|---|---|
| `isPresent()` | Value exists? |
| `isEmpty()` | Value absent? (Java 11+) |
| `get()` | Get value; **throws if empty** |
| `orElse()` | Default value if empty |
| `orElseGet()` | Lazily generated default |
| `orElseThrow()` | Throw exception if empty |
| `ifPresent()` | Run action if present |
| `ifPresentOrElse()` | Handle present and empty (Java 9+) |
| `map()` / `filter()` | Transform / filter the value |

```java
// Q: What is the difference between orElse() and orElseGet()?
nums.stream().filter(n -> n > 100).findFirst().orElse(0);           // default evaluated EAGERLY
nums.stream().filter(n -> n > 100).findFirst().orElseGet(() -> 100); // Supplier called ONLY if empty
```

**Interview point:** Avoid `get()` without checking. Prefer `orElse()`, `orElseGet()`, `orElseThrow()` or `ifPresent()`.

---

## 9. `toMap()` with Merge Functions

```java
Collectors.toMap(keyMapper, valueMapper, mergeFunction)
// mergeFunction receives (oldValue, newValue) for the same key
```

```java
List<String> words = Arrays.asList("Java", "Python", "Java", "Spring", "Python", "Java");

// Q: How do you count occurrences of each word using toMap()?
Map<String, Integer> count = words.stream()
    .collect(Collectors.toMap(w -> w, w -> 1, Integer::sum));
// {Java=3, Python=2, Spring=1}

// Q: How do you keep the first value for duplicate keys?
words.stream().collect(Collectors.toMap(w -> w, String::length, (oldV, newV) -> oldV));
// {Java=4, Python=6, Spring=6}

// Q: How do you keep the latest value for duplicate keys?
words.stream().collect(Collectors.toMap(w -> w, String::length, (oldV, newV) -> newV));
// {Java=4, Python=6, Spring=6}  (same values here, but the incoming one is kept)

// Q: How do you keep the maximum salary per department?
employees.stream().collect(Collectors.toMap(
    Employee::getDepartment, Employee::getSalary, Integer::max));
// {IT=90000, HR=50000, Finance=70000}

// Q: How do you keep the minimum salary per department?
employees.stream().collect(Collectors.toMap(
    Employee::getDepartment, Employee::getSalary, Integer::min));
// {IT=60000, HR=40000, Finance=70000}

// Q: How do you handle duplicates AND get sorted keys?
employees.stream().collect(Collectors.toMap(
    Employee::getDepartment, Employee::getSalary, Integer::max, TreeMap::new));
// Keys sorted alphabetically: Finance, HR, IT
```

**How the merge works** (counting example)

| Key | Existing | Incoming | Result |
|---|---|---|---|
| Java | 1 | 1 | 2 |
| Java | 2 | 1 | 3 |
| Python | 1 | 1 | 2 |

**Merge functions to remember**

```java
Integer::sum                // Add values
Integer::max                // Keep maximum
Integer::min                // Keep minimum
(old, current) -> old       // Keep first
(old, current) -> current   // Keep latest
```

> For parallel streams, the merge function should be **associative and stateless**.

---

## 10. Custom Comparators and Multi-field Sorting ⭐

```java
// Q: How do you sort employees by salary ascending?
employees.stream().sorted(Comparator.comparingInt(Employee::getSalary))
// David 40000, Mary 50000, John 60000, Peter 70000, Smith 80000, Steve 90000

// Q: How do you sort employees by salary descending?
employees.stream().sorted(Comparator.comparingInt(Employee::getSalary).reversed())
// Steve 90000, Smith 80000, Peter 70000, John 60000, Mary 50000, David 40000

// Q: How do you sort employees by name alphabetically?
employees.stream().sorted(Comparator.comparing(Employee::getName))
// David, John, Mary, Peter, Smith, Steve

// Q: How do you sort employees by department?
employees.stream().sorted(Comparator.comparing(Employee::getDepartment))
// Finance, HR, HR, IT, IT, IT  (stable: equal elements keep original order)
```

```java
// Q: How do you sort by department ascending, then salary descending?
employees.stream().sorted(
    Comparator.comparing(Employee::getDepartment)
        .thenComparing(Comparator.comparingInt(Employee::getSalary).reversed()))
// Peter - Finance - 70000
// Mary  - HR      - 50000
// David - HR      - 40000
// Steve - IT      - 90000
// Smith - IT      - 80000
// John  - IT      - 60000

// Q: How do you sort by department, then salary, then name (all ascending)?
employees.stream().sorted(
    Comparator.comparing(Employee::getDepartment)
        .thenComparingInt(Employee::getSalary)
        .thenComparing(Employee::getName))

// Q: How do you write a custom lambda comparator (salary descending)?
employees.stream().sorted((e1, e2) -> e2.getSalary() - e1.getSalary())
// Prefer comparingInt(): subtraction can overflow for extreme values.

// Q: How do you sort a list containing nulls (nulls last)?
Arrays.asList("Java", null, "Spring", "Python").stream()
    .sorted(Comparator.nullsLast(Comparator.naturalOrder()))
    .collect(Collectors.toList());
// [Java, Python, Spring, null]
```

| Comparator method | Purpose |
|---|---|
| `comparing()` | Compare by a property |
| `comparingInt()` / `comparingLong()` / `comparingDouble()` | Compare by a primitive property |
| `thenComparing()` | Secondary sort |
| `reversed()` | Reverse order |
| `naturalOrder()` / `reverseOrder()` | Natural ascending / descending |
| `nullsFirst()` / `nullsLast()` | Null handling |

**Priority:** master `comparing()`, `comparingInt()`, `thenComparing()`, `reversed()`.

---

## 11. Primitive Streams vs Object Streams

| Feature | Object Stream | Primitive Stream |
|---|---|---|
| Types | `Stream<Integer>`, `Stream<String>`, `Stream<Employee>` | `IntStream`, `LongStream`, `DoubleStream` |
| Memory | Objects and references | Raw primitive values |
| Boxing | May box/unbox | Avoids wrapper boxing |
| `sum()` / `average()` | Not available directly | Available |
| `min()` / `max()` | Needs `Comparator` | Direct methods |
| Collecting | Collect directly | Usually `boxed()` first |

```java
// Q: How do you sum an Object Stream (using reduce)?
nums.stream().reduce(0, Integer::sum);                       // Integer

// Q: How do you sum using a Primitive Stream?
nums.stream().mapToInt(Integer::intValue).sum();             // int

// Q: How do you convert Object Stream -> Primitive Stream?
IntStream is = nums.stream().mapToInt(Integer::intValue);

// Q: How do you convert Primitive Stream -> Object Stream?
Stream<Integer> os = IntStream.of(10, 20, 30, 40).boxed();

// Q: How do you sum an int[] vs an Integer[]?
Arrays.stream(new int[]{10, 20, 30, 40}).sum();                                  // 100
Arrays.stream(new Integer[]{10, 20, 30, 40}).mapToInt(Integer::intValue).sum();  // 100
```

**Takeaway:** Primitive streams for numeric calculations. Object streams for objects and collecting into collections.

---

## 12. Parallel Streams: When to Use and When NOT To ⭐

```java
List<Integer> list = IntStream.rangeClosed(1, 10).boxed().collect(Collectors.toList());

// Q: Which thread runs a sequential stream?
list.stream().forEach(n -> System.out.println(Thread.currentThread().getName() + " : " + n));
// main : 1 ...

// Q: Which threads run a parallel stream?
list.parallelStream().forEach(n -> System.out.println(Thread.currentThread().getName() + " : " + n));
// main / ForkJoinPool.commonPool-worker-1 / worker-2 ... (order NOT guaranteed)
```

```java
// Q: Why does summing 1..100000 with int give a wrong result?
int wrong = IntStream.rangeClosed(1, 100000).parallel().sum();
// 705082704  (int overflow)

// Q: How do you get the correct sum?
long right = IntStream.rangeClosed(1, 100000).asLongStream().parallel().sum();
// 5000050000

// Q: Does a parallel stream keep order when collecting?
IntStream.rangeClosed(1, 20).parallel()
    .filter(n -> n % 2 == 0).map(n -> n * n).boxed().collect(Collectors.toList());
// [4, 16, 36, 64, 100, 144, 196, 256, 324, 400]  (encounter order preserved)

// Q: What is the difference between forEach() and forEachOrdered()?
IntStream.rangeClosed(1, 10).parallel().forEach(System.out::println);         // order not guaranteed
IntStream.rangeClosed(1, 10).parallel().forEachOrdered(System.out::println);  // 1 2 3 ... 10 (may be slower)
```

### Common mistakes

```java
// Q: Why is adding to a shared ArrayList inside a parallel stream wrong?
List<Integer> bad = new ArrayList<>();
list.parallelStream().forEach(bad::add);        // ❌ ArrayList is not thread-safe

// Q: What is the safe alternative?
List<Integer> good = list.parallelStream().collect(Collectors.toList());   // ✅ collector handles it

// Q: Why is parallelStream() on a small list a bad idea?
list.parallelStream().filter(n -> n > 20).collect(Collectors.toList());
// Overhead: thread scheduling, splitting/combining work, contention
```

| ✅ Use parallel when | ❌ Avoid parallel when |
|---|---|
| Large datasets | Small datasets |
| CPU-intensive, independent operations | Very simple operations |
| Elements processed independently | Operations depend on previous results |
| Source splits efficiently | Source is hard to split |
| Stateless operations | Operations modify shared state |
| Order not important | Strict ordering is essential and costly |

**Interview points**
1. `parallelStream()` does **not** guarantee a speedup.
2. It commonly uses the **ForkJoin common pool**.
3. `forEach()` order is not guaranteed; `forEachOrdered()` preserves encounter order.
4. Avoid shared mutable state.
5. Benchmark before choosing parallel.
6. Blocking I/O is a poor fit for the common ForkJoin pool.
7. It does **not** create one thread per element.

---

## 13. Interview Questions with Quick Answers

### Level A: Must know

| # | Question | Quick answer |
|---|---|---|
| 1 | `map()` vs `flatMap()`? | `map` is 1→1; `flatMap` is 1→many and flattens |
| 2 | `Stream<Integer>` vs `IntStream`? | Boxed objects vs primitives; `IntStream` has `sum()`, `average()` |
| 3 | Why `boxed()`? | Converts primitive stream to object stream so it can be collected |
| 4 | `range()` vs `rangeClosed()`? | End exclusive vs inclusive |
| 5 | `iterate()` vs `generate()`? | Depends on previous element vs independent `Supplier` |
| 6 | Purpose of `peek()`? | Debug pipeline without changing elements |
| 7 | What is `Optional`? | Container for a value that may be absent; avoids null checks |
| 8 | `findFirst()` vs `findAny()`? | `findFirst` always returns the first element; `findAny` may return any (useful in parallel streams) |
| 9 | `min()` vs `max()`? | Smallest / largest according to a `Comparator`; both return `Optional` |
| 10 | Duplicate keys in `toMap()`? | Default throws `IllegalStateException`; pass a merge function |

### Level B: Important

| # | Question | Quick answer |
|---|---|---|
| 11 | Sort by multiple fields? | `comparing(...).thenComparing(...)` |
| 12 | Salary descending, name ascending? | `comparingInt(getSalary).reversed().thenComparing(getName)` |
| 13 | Primitive vs object streams? | See section 11 |
| 14 | Advantages of primitive streams? | No boxing overhead, built-in numeric methods |
| 15 | How do parallel streams work? | Source is split and processed by ForkJoin common pool threads, then results are combined |
| 16 | `forEach()` vs `forEachOrdered()`? | Unordered vs encounter order |
| 17 | Multiple threads modifying a shared `ArrayList`? | Race conditions: lost updates, exceptions, corrupted data |
| 18 | When to avoid parallel streams? | Small data, shared state, order-sensitive, blocking I/O |
| 19 | Are streams reusable? | **No.** A consumed stream throws `IllegalStateException` |
| 20 | Why are intermediate operations lazy? | Nothing runs until a terminal operation; enables short-circuiting and one-pass processing |