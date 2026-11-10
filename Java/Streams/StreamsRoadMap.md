# Java Streams Roadmap — Collections + Arrays

This roadmap is designed for Java interview preparation and hands-on coding practice, starting from the fundamentals and progressing to interview-level problems.

The goal is to understand how to use the Stream API with `List`, `Set`, `Map`, and arrays, rather than just memorizing methods.

---

## Level 1 — Stream API Fundamentals

**Goal:** Understand what streams are and how they work.

- What is the Stream API?
- Why do we use streams?
- Traditional loops vs. streams
- Collection vs. Stream
- Creating streams using `stream()` and `Arrays.stream()`
- Intermediate vs. terminal operations
- Stream pipeline: source → intermediate operations → terminal operation
- Lazy evaluation
- A stream can be consumed only once
- Method references and lambda expressions (revision)

**Example:**

```java
import java.util.Arrays;
import java.util.List;

public class StreamBasics {
    public static void main(String[] args) {
        List<Integer> nums = Arrays.asList(10, 15, 20, 25, 30);

        nums.stream()
            .filter(n -> n > 20)
            .forEach(System.out::println);
    }
}
```

**Output:**

```text
25
30
```

**Practice:** Convert 5 traditional `for`-loop examples into streams.

---

## Level 2 — Must-Know Stream Methods

Learn these methods in the following order.

| Method | Purpose |
|---|---|
| `filter()` | Select elements matching a condition |
| `map()` | Transform each element |
| `sorted()` | Sort elements |
| `distinct()` | Remove duplicates |
| `limit()` | Keep the first N elements |
| `skip()` | Skip the first N elements |
| `forEach()` | Perform an action on each element |
| `count()` | Count elements |
| `min()` / `max()` | Find minimum or maximum |
| `findFirst()` / `findAny()` | Find an element |
| `anyMatch()` | Check whether any element matches |
| `allMatch()` | Check whether all elements match |
| `noneMatch()` | Check whether no elements match |
| `reduce()` | Combine elements into one result |
| `collect()` | Gather results into a collection or another result |

### Practice problems

Use this list for the exercises:

```java
List<Integer> nums =
    Arrays.asList(10, 5, 20, 5, 30, 15, 40, 10);
```

1. Filter all even numbers.
2. Find numbers greater than 15.
3. Square every number using `map()`.
4. Remove duplicates.
5. Sort in ascending order.
6. Sort in descending order.
7. Find the first three elements.
8. Skip the first two elements.
9. Count even numbers.
10. Find minimum and maximum.
11. Check whether any number is greater than 35.
12. Check whether all numbers are positive.
13. Find the first number divisible by 3.
14. Calculate the sum using `reduce()`.
15. Collect even numbers into a new list.

---

## Level 3 — Streams with Collections

**Goal:** Become comfortable applying streams to Java collection types.

### 3.1 List

- Filter and transform a list
- Convert a list of strings to uppercase
- Extract a property from a list of objects
- Sort a list using natural and custom ordering
- Remove duplicates
- Convert a list into a `Set`
- Convert a stream result into a `List`

### 3.2 Set

- Create streams from `HashSet` and `TreeSet`
- Filter set elements
- Transform set elements
- Find common elements between two sets
- Find elements present in one set but not another

### 3.3 Map

> **Remember:** a `Map` does not directly provide a `stream()` method. Stream its entries, keys, or values.

```java
Map<String, Integer> marks = new HashMap<>();

marks.put("Ravi", 85);
marks.put("Priya", 92);
marks.put("Amit", 70);

// Stream map entries
marks.entrySet().stream()
     .filter(e -> e.getValue() > 80)
     .forEach(System.out::println);

// Stream keys
marks.keySet().stream()
     .forEach(System.out::println);

// Stream values
marks.values().stream()
     .filter(m -> m >= 80)
     .forEach(System.out::println);
```

**Practice:**

- Filter map entries by value.
- Find the student with the highest marks.
- Sort entries by key.
- Sort entries by value.
- Find entries whose values exceed a threshold.
- Convert a map's values into a list.
- Count map entries matching a condition.

---

## Level 4 — Streams with Arrays

**Goal:** Use streams with primitive arrays and object arrays.

### 4.1 Primitive arrays

Use `Arrays.stream()` for `int[]`, `long[]`, and `double[]`.

```java
int[] arr = {10, 5, 20, 5, 30, 15};

int sum = Arrays.stream(arr).sum();

int max = Arrays.stream(arr).max().orElse(0);

long count = Arrays.stream(arr)
                   .filter(n -> n % 2 == 0)
                   .count();

System.out.println(sum);
System.out.println(max);
System.out.println(count);
```

**Learn:**

- `Arrays.stream(arr)`
- `IntStream`, `LongStream`, `DoubleStream`
- `sum()`, `average()`, `min()`, `max()`
- `filter()`, `map()`, `distinct()`, `sorted()`
- `boxed()` — convert primitive stream elements to wrapper objects
- `toArray()` — collect stream elements into an array

### 4.2 Object arrays

```java
String[] names = {"Ravi", "Priya", "Amit", "Ravi"};

String[] result = Arrays.stream(names)
                        .filter(n -> n.length() > 3)
                        .distinct()
                        .toArray(String[]::new);
```

**Practice:**

1. Find the sum of array elements.
2. Find the second-largest distinct number.
3. Find even and odd numbers.
4. Remove duplicate elements.
5. Sort an array in ascending and descending order.
6. Find common elements between two arrays.
7. Convert `int[]` to `List<Integer>`.
8. Convert `List<Integer>` to `int[]`.
9. Find the average of array elements.
10. Find the top three distinct numbers.

---

## Level 5 — Collectors (Very Important)

**Goal:** Learn how stream results are grouped, counted, and converted.

| Collector | Purpose |
|---|---|
| `Collectors.toList()` | Collect into a list |
| `Collectors.toSet()` | Collect into a set |
| `Collectors.toMap()` | Create a map |
| `Collectors.groupingBy()` | Group elements by a property |
| `Collectors.partitioningBy()` | Divide into true/false groups |
| `Collectors.counting()` | Count grouped elements |
| `Collectors.summingInt()` | Sum values |
| `Collectors.averagingInt()` | Calculate average |
| `Collectors.maxBy()` / `minBy()` | Find extrema within a collection or group |
| `Collectors.joining()` | Join strings |
| `Collectors.mapping()` | Transform values within another collector |
| `Collectors.collectingAndThen()` | Apply a finishing operation |

### Practice problems

Given:

```java
List<String> names =
    Arrays.asList("Ravi", "Amit", "Ravi", "Priya", "Amit", "Ravi");
```

1. Convert the list into a set.
2. Count the occurrences of each name.
3. Group names by their length.
4. Find the most frequently occurring name.
5. Partition names based on whether their length is greater than 4.
6. Join all names using commas.
7. Create a map of name to name length.
8. Handle duplicate keys while using `toMap()`.

**Example — frequency counting:**

```java
Map<String, Long> frequency = names.stream()
    .collect(Collectors.groupingBy(
        name -> name,
        Collectors.counting()
    ));
```

**Result:**

```text
Ravi=3, Amit=2, Priya=1
```

---

## Level 6 — Advanced Stream Operations

Study these after you are comfortable with Levels 1–5.

- `flatMap()` — flatten nested collections
- `mapToInt()`, `mapToLong()`, `mapToDouble()`
- `boxed()`
- `IntStream.range()` and `rangeClosed()`
- `Stream.iterate()` and `Stream.generate()`
- `peek()` and its debugging use
- `Optional` with `findFirst()`, `min()`, `max()`
- `Collectors.toMap()` with merge functions
- Custom comparators and multi-field sorting
- Primitive streams vs. object streams
- Parallel streams and when not to use them

**Example — `flatMap()`:**

```java
List<List<Integer>> nested = Arrays.asList(
    Arrays.asList(1, 2),
    Arrays.asList(3, 4),
    Arrays.asList(5, 6)
);

List<Integer> result = nested.stream()
    .flatMap(List::stream)
    .collect(Collectors.toList());

System.out.println(result);
```

**Output:**

```text
[1, 2, 3, 4, 5, 6]
```

---

## Level 7 — Interview-Level Stream Problems

Practice without looking at solutions first.

| No. | Problem |
|---|---|
| 1 | Find duplicate elements in a list |
| 2 | Find unique elements |
| 3 | Find the first non-repeating character |
| 4 | Find the first repeating character |
| 5 | Find the second-highest number |
| 6 | Find the top three highest distinct numbers |
| 7 | Count the frequency of each element |
| 8 | Find the most frequent element |
| 9 | Find common elements between two lists |
| 10 | Find elements that exist in one list but not another |
| 11 | Sort employees by salary |
| 12 | Sort employees by department, then salary |
| 13 | Group employees by department |
| 14 | Find the highest-paid employee in each department |
| 15 | Calculate average salary by department |
| 16 | Partition employees by salary threshold |
| 17 | Find duplicate words in a sentence |
| 18 | Count word frequency in a sentence |
| 19 | Find the longest string in a list |
| 20 | Convert a list of objects into a map |
| 21 | Flatten a list of lists |
| 22 | Find the sum of squares of even numbers |
| 23 | Find the second-largest number in an array |
| 24 | Group strings by their first character |
| 25 | Handle duplicate keys while creating a map |

> For employee problems, define a simple `Employee` class with fields such as `id`, `name`, `department`, and `salary`.

---

## Level 8 — Common Interview Traps

Understand these before interviews:

- A stream does not store elements; the source collection or array does.
- A stream pipeline is generally lazy until a terminal operation executes.
- A consumed stream cannot be reused.
- `map()` transforms one element into one result; `flatMap()` flattens nested results.
- `filter()` selects elements but does not modify the source collection.
- `distinct()` uses equality semantics; custom objects may need correct `equals()` and `hashCode()`.
- `findFirst()`, `min()`, and `max()` may return `Optional` values.
- `Collectors.toMap()` throws an exception when duplicate keys occur unless a merge function is supplied.
- `Stream<Integer>` and `IntStream` are different.
- `peek()` should not be used as the primary way to perform required side effects.
- Avoid modifying a collection while streaming over it.
- Parallel streams are not automatically faster.