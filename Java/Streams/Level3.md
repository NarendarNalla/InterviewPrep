<a id="top"></a>

# 🌊 Java Stream API — Level 3: Streams with Collections

> [!NOTE]
> **🎯 Goal:** Learn to apply streams to `List`, `Set`, and `Map` with practical examples, different approaches, expected outputs, and interview-focused explanations.
>
> We will use Java 8-compatible code for most examples, so you can practise with your existing Java setup.

---

## 🧭 Table of Contents

*Click any item to jump to it. Every section ends with a "Back to top" link.*

### 📋 [3.1 List — Stream operations](#s31)

1. [Filter and transform a list](#l1)
2. [Convert a list of strings to uppercase](#l2)
3. [Extract a property from a list of objects](#l3)
4. [Sort a list using natural and custom ordering](#l4)
5. [Remove duplicates](#l5)
6. [Convert a list into a Set](#l6)
7. [Convert a stream result into a List](#l7)

### 🔷 [3.2 Set — Stream operations](#s32)

1. [Create streams from HashSet and TreeSet](#st1)
2. [Filter set elements](#st2)
3. [Transform set elements](#st3)
4. [Find common elements between two sets](#st4)
5. [Find elements present in one set but not another](#st5)

### 🗺️ [3.3 Map — Stream operations](#s33)

1. [Filter map entries by value](#m1)
2. [Find the student with the highest marks](#m2)
3. [Sort entries by key](#m3)
4. [Sort entries by value](#m4)
5. [Find entries whose values exceed a threshold](#m5)
6. [Convert a map's values into a list](#m6)
7. [Count map entries matching a condition](#m7)

### 📝 [4. Interview cheat sheet — List, Set, and Map](#s4)

### 🔖 Quick jumps

[⚡ Cheat sheet](#s4) · [🪤 Set transform duplicates](#st3) · [🔀 Set intersection/difference](#st4) · [🏆 Highest map value](#m2) · [↕️ Sort map by value](#m4)

---

<a id="s31"></a>

## 3.1 List — Stream operations

A `List` preserves **encounter order** and can contain **duplicates**. You create a stream using `list.stream()`.

<a id="l1"></a>

### 1. Filter and transform a list

**Problem:** Find even numbers and multiply each by 2.

```java
List<Integer> nums = Arrays.asList(10, 15, 20, 25, 30);

List<Integer> result = nums.stream()
        .filter(n -> n % 2 == 0)
        .map(n -> n * 2)
        .collect(Collectors.toList());

System.out.println(result);
// Output: [20, 40, 60]
```

**Explanation:**

- `filter()` selects even numbers: `10, 20, 30`.
- `map()` transforms them: `20, 40, 60`.
- `collect()` gathers the results into a new list.

**Traditional loop approach:**

```java
List<Integer> result = new ArrayList<>();

for (int n : nums) {
    if (n % 2 == 0) {
        result.add(n * 2);
    }
}

System.out.println(result);
// Output: [20, 40, 60]
```

<a id="l2"></a>

### 2. Convert a list of strings to uppercase

```java
List<String> names = Arrays.asList("ravi", "priya", "amit");

List<String> result = names.stream()
        .map(String::toUpperCase)
        .collect(Collectors.toList());

System.out.println(result);
// Output: [RAVI, PRIYA, AMIT]
```

Here, `String::toUpperCase` is a method reference equivalent to `name -> name.toUpperCase()`.

The original list remains unchanged.

<a id="l3"></a>

### 3. Extract a property from a list of objects

Suppose you have a list of employees and want to extract their names.

```java
class Employee {
    private final int id;
    private final String name;
    private final double salary;

    Employee(int id, String name, double salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }

    public int getId() {
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

**Usage:**

```java
List<Employee> employees = Arrays.asList(
        new Employee(101, "Ravi", 50000),
        new Employee(102, "Priya", 75000),
        new Employee(103, "Amit", 60000)
);

List<String> names = employees.stream()
        .map(Employee::getName)
        .collect(Collectors.toList());

System.out.println(names);
// Output: [Ravi, Priya, Amit]
```

> [!IMPORTANT]
> **🟣 Interview concept:** `map(Employee::getName)` transforms each `Employee` into a `String`.

You can extract other properties similarly:

```java
List<Double> salaries = employees.stream()
        .map(Employee::getSalary)
        .collect(Collectors.toList());

// Output: [50000.0, 75000.0, 60000.0]
```

<a id="l4"></a>

### 4. Sort a list using natural and custom ordering

**Natural ordering — ascending numbers:**

```java
List<Integer> nums = Arrays.asList(40, 10, 30, 20);

List<Integer> result = nums.stream()
        .sorted()
        .collect(Collectors.toList());

System.out.println(result);
// Output: [10, 20, 30, 40]
```

**Custom ordering — descending numbers:**

```java
List<Integer> result = nums.stream()
        .sorted(Comparator.reverseOrder())
        .collect(Collectors.toList());

System.out.println(result);
// Output: [40, 30, 20, 10]
```

**Sort employees by salary:**

```java
List<Employee> sortedEmployees = employees.stream()
        .sorted(Comparator.comparingDouble(Employee::getSalary))
        .collect(Collectors.toList());

// Employee order: Ravi, Amit, Priya
```

**To sort salary in descending order:**

```java
List<Employee> sortedEmployees = employees.stream()
        .sorted(Comparator.comparingDouble(Employee::getSalary)
                .reversed())
        .collect(Collectors.toList());

// Employee order: Priya, Amit, Ravi
```

<a id="l5"></a>

### 5. Remove duplicates

```java
List<Integer> nums = Arrays.asList(10, 20, 10, 30, 20, 40);

List<Integer> result = nums.stream()
        .distinct()
        .collect(Collectors.toList());

System.out.println(result);
// Output: [10, 20, 30, 40]
```

`distinct()` preserves the encounter order for an ordered stream.

<a id="l6"></a>

### 6. Convert a list into a Set

**Approach 1 — Collect directly into a `Set`:**

```java
List<Integer> nums = Arrays.asList(10, 20, 10, 30, 20);

Set<Integer> result = nums.stream()
        .collect(Collectors.toSet());

System.out.println(result);
// Contains: 10, 20, 30
// Set iteration order is not guaranteed.
```

**Approach 2 — Collect into a `TreeSet`:**

```java
Set<Integer> result = nums.stream()
        .collect(Collectors.toCollection(TreeSet::new));

System.out.println(result);
// Output: [10, 20, 30]
```

A `TreeSet` keeps elements sorted according to natural ordering by default. A regular `HashSet` does not guarantee iteration order.

<a id="l7"></a>

### 7. Convert a stream result into a List

**Java 8+:**

```java
List<Integer> result = nums.stream()
        .filter(n -> n > 15)
        .collect(Collectors.toList());
```

**Java 16+:**

```java
List<Integer> result = nums.stream()
        .filter(n -> n > 15)
        .toList();
```

> [!WARNING]
> **Remember the difference:** `Stream.toList()` returns an **unmodifiable** list, while `Collectors.toList()` does not promise a particular mutability contract.

[⬆️ Back to top](#top)

---

<a id="s32"></a>

## 3.2 Set — Stream operations

A `Set` stores **unique elements**. Unlike a `List`, it does not allow duplicate elements.

You can create streams from both `HashSet` and `TreeSet` using `.stream()`.

<a id="st1"></a>

### 1. Create streams from HashSet and TreeSet

```java
Set<Integer> hashSet = new HashSet<>(
        Arrays.asList(30, 10, 20, 10));

hashSet.stream().forEach(System.out::println);
// Contains: 10, 20, 30
// HashSet iteration order is not guaranteed.

Set<Integer> treeSet = new TreeSet<>(hashSet);

treeSet.stream().forEach(System.out::println);
// Output:
// 10
// 20
// 30
```

**Key difference:**

| Set type | Ordering |
|---|---|
| `HashSet` | ❌ Does not guarantee ordering |
| `TreeSet` | ✅ Sorts elements according to natural ordering by default |

Both support `.stream()`.

<a id="st2"></a>

### 2. Filter set elements

**Problem:** Find numbers greater than 20.

```java
Set<Integer> numbers = new HashSet<>(
        Arrays.asList(10, 20, 30, 40, 50));

List<Integer> result = numbers.stream()
        .filter(n -> n > 20)
        .collect(Collectors.toList());

System.out.println(result);
// Contains: 30, 40, 50
// List order is not guaranteed because the source is a HashSet.
```

If sorted output is required, add `.sorted()`:

```java
List<Integer> result = numbers.stream()
        .filter(n -> n > 20)
        .sorted()
        .collect(Collectors.toList());

// Output: [30, 40, 50]
```

<a id="st3"></a>

### 3. Transform set elements

**Problem:** Multiply each number by 10.

```java
Set<Integer> numbers = new HashSet<>(
        Arrays.asList(1, 2, 3, 4));

List<Integer> result = numbers.stream()
        .map(n -> n * 10)
        .collect(Collectors.toList());

// Contains: 10, 20, 30, 40
// Order is not guaranteed for a HashSet source.
```

Notice that `map()` transforms elements, but the resulting stream is **not automatically a `Set`**.

If you want a `Set` result:

```java
Set<Integer> result = numbers.stream()
        .map(n -> n * 10)
        .collect(Collectors.toSet());
```

> [!IMPORTANT]
> **🟣 Interview point:** Transformations can produce **duplicate values** even when the source is a set.

For example:

```java
Set<Integer> numbers = new HashSet<>(
        Arrays.asList(1, 2, 3));

List<Integer> result = numbers.stream()
        .map(n -> n % 2)
        .collect(Collectors.toList());

System.out.println(result);
// Contains: 0, 1, 1
// The two odd numbers both become 1.
```

Use `.distinct()` or collect into a set if you need unique transformed values.

<a id="st4"></a>

### 4. Find common elements between two sets

This is the **intersection** of two sets.

**Approach 1 — Using `filter()` and `contains()`:**

```java
Set<Integer> set1 = new HashSet<>(
        Arrays.asList(10, 20, 30, 40));

Set<Integer> set2 = new HashSet<>(
        Arrays.asList(30, 40, 50, 60));

Set<Integer> common = set1.stream()
        .filter(set2::contains)
        .collect(Collectors.toSet());

System.out.println(common);
// Contains: 30, 40
```

**Approach 2 — Using `retainAll()`:**

```java
Set<Integer> common = new HashSet<>(set1);

common.retainAll(set2);

System.out.println(common);
// Contains: 30, 40
```

> [!NOTE]
> `retainAll()` **modifies** the set on which it is called. Here, we copy `set1` first so that the original remains unchanged.

<a id="st5"></a>

### 5. Find elements present in one set but not another

This is **set difference**: $A - B$.

**Approach 1 — Using streams:**

```java
Set<Integer> set1 = new HashSet<>(
        Arrays.asList(10, 20, 30, 40));

Set<Integer> set2 = new HashSet<>(
        Arrays.asList(30, 40, 50, 60));

Set<Integer> difference = set1.stream()
        .filter(n -> !set2.contains(n))
        .collect(Collectors.toSet());

System.out.println(difference);
// Contains: 10, 20
```

**Approach 2 — Using `removeAll()`:**

```java
Set<Integer> difference = new HashSet<>(set1);

difference.removeAll(set2);

System.out.println(difference);
// Contains: 10, 20
```

**The difference between the two approaches:**

| Operation | Meaning |
|---|---|
| `filter(set2::contains)` | 🟢 Retain elements that exist in **both** sets |
| `filter(n -> !set2.contains(n))` | 🔴 Retain elements in the **first** set but **not** the second |
| `retainAll()` | Mutate a set to keep only common elements |
| `removeAll()` | Mutate a set to remove elements found in another collection |

[⬆️ Back to top](#top)

---

<a id="s33"></a>

## 3.3 Map — Stream operations

A `Map` stores key-value pairs. It does **not** directly provide a `stream()` method.

Instead, stream one of these views:

```java
marks.entrySet().stream(); // Key-value entries
marks.keySet().stream();   // Keys
marks.values().stream();   // Values
```

Let's use this map for all the examples:

```java
Map<String, Integer> marks = new HashMap<>();

marks.put("Ravi", 85);
marks.put("Priya", 92);
marks.put("Amit", 70);
marks.put("Neha", 92);
marks.put("Kiran", 65);
```

<a id="m1"></a>

### 1. Filter map entries by value

Find students with marks greater than 80.

```java
marks.entrySet().stream()
        .filter(e -> e.getValue() > 80)
        .forEach(System.out::println);

// Contains:
// Ravi=85
// Priya=92
// Neha=92
// Entry order is not guaranteed for HashMap.
```

**Explanation:**

- `entrySet()` returns the map's key-value entries.
- `getKey()` accesses a student's name.
- `getValue()` accesses the student's marks.
- `filter()` retains entries meeting the condition.

To collect the result into a new map:

```java
Map<String, Integer> result = marks.entrySet().stream()
        .filter(e -> e.getValue() > 80)
        .collect(Collectors.toMap(
                Map.Entry::getKey,
                Map.Entry::getValue
        ));
```

The new map contains only students with marks greater than 80.

<a id="m2"></a>

### 2. Find the student with the highest marks

**Approach 1 — Using `max()`:**

```java
Optional<Map.Entry<String, Integer>> topper =
        marks.entrySet().stream()
                .max(Map.Entry.comparingByValue());

topper.ifPresent(e ->
        System.out.println(e.getKey() + " = " + e.getValue()));

// Output: Priya = 92
// Neha also has 92, so either tied entry may be returned.
```

> [!WARNING]
> Since two students have the maximum marks, `max()` may return **either** entry.

**Approach 2 — Using `max()` with a comparator:**

```java
Optional<Map.Entry<String, Integer>> topper =
        marks.entrySet().stream()
                .max(Comparator.comparingInt(
                        e -> e.getValue()));

topper.ifPresent(System.out::println);

// May output: Priya=92 or Neha=92
```

Both approaches find the entry with the highest value.

<a id="m3"></a>

### 3. Sort entries by key

Sort student names alphabetically.

```java
marks.entrySet().stream()
        .sorted(Map.Entry.comparingByKey())
        .forEach(System.out::println);

// Output:
// Amit=70
// Kiran=65
// Neha=92
// Priya=92
// Ravi=85
```

`comparingByKey()` uses the keys' natural ordering. For strings, this is lexicographical ordering.

<a id="m4"></a>

### 4. Sort entries by value

Sort marks in **ascending** order.

```java
marks.entrySet().stream()
        .sorted(Map.Entry.comparingByValue())
        .forEach(System.out::println);

// Output:
// Kiran=65
// Amit=70
// Ravi=85
// Priya=92
// Neha=92
// Priya and Neha can appear in either order.
```

**For descending order:**

```java
marks.entrySet().stream()
        .sorted(Map.Entry.<String, Integer>comparingByValue()
                .reversed())
        .forEach(System.out::println);

// Output starts with:
// Priya=92 or Neha=92
// Neha=92 or Priya=92
// Ravi=85
// Amit=70
// Kiran=65
```

The comparator sorts by value; **ties do not automatically receive a secondary alphabetical sort**.

If you need descending marks **and** alphabetical names for ties:

```java
marks.entrySet().stream()
        .sorted(
                Map.Entry.<String, Integer>comparingByValue()
                        .reversed()
                        .thenComparing(Map.Entry.comparingByKey())
        )
        .forEach(System.out::println);

// Output:
// Neha=92
// Priya=92
// Ravi=85
// Amit=70
// Kiran=65
```

<a id="m5"></a>

### 5. Find entries whose values exceed a threshold

**Approach 1 — Using `filter()`:**

```java
int threshold = 75;

marks.entrySet().stream()
        .filter(e -> e.getValue() > threshold)
        .forEach(System.out::println);

// Contains:
// Ravi=85
// Priya=92
// Neha=92
```

**Approach 2 — Collect the matching entries into a list:**

```java
List<Map.Entry<String, Integer>> result =
        marks.entrySet().stream()
                .filter(e -> e.getValue() > 75)
                .collect(Collectors.toList());
```

The first approach **prints** the matching entries. The second **saves** them for further processing.

<a id="m6"></a>

### 6. Convert a map's values into a list

```java
List<Integer> values = marks.values().stream()
        .collect(Collectors.toList());

System.out.println(values);
// Contains: 85, 92, 70, 92, 65
// Order is not guaranteed for HashMap.
```

Alternatively, create a list directly from the values collection:

```java
List<Integer> values = new ArrayList<>(marks.values());
```

If you need ascending order:

```java
List<Integer> values = marks.values().stream()
        .sorted()
        .collect(Collectors.toList());

// Output: [65, 70, 85, 92, 92]
```

Notice that the values list can contain **duplicates** because different keys may have the same value.

<a id="m7"></a>

### 7. Count map entries matching a condition

Count students scoring at least 80.

```java
long count = marks.entrySet().stream()
        .filter(e -> e.getValue() >= 80)
        .count();

System.out.println(count);
// Output: 3
```

The matching students are Ravi, Priya, and Neha.

You can also count students whose names start with `A`:

```java
long count = marks.keySet().stream()
        .filter(name -> name.startsWith("A"))
        .count();

System.out.println(count);
// Output: 1
```

[⬆️ Back to top](#top)

---

<a id="s4"></a>

## 4. Interview cheat sheet — List, Set, and Map

| Requirement | Recommended stream approach |
|---|---|
| **Filter a list** | `list.stream().filter(...)` |
| **Transform a list** | `list.stream().map(...)` |
| **Extract object properties** | `employees.stream().map(Employee::getName)` |
| **Sort a list** | `.sorted()` |
| **Custom sorting** | `.sorted(Comparator.comparing(...))` |
| **Remove duplicates** | `.distinct()` |
| **List to Set** | `.collect(Collectors.toSet())` |
| **Set intersection** | `set1.stream().filter(set2::contains)` |
| **Set difference** | `set1.stream().filter(x -> !set2.contains(x))` |
| **Stream map entries** | `map.entrySet().stream()` |
| **Stream map keys** | `map.keySet().stream()` |
| **Stream map values** | `map.values().stream()` |
| **Highest map value** | `.max(Map.Entry.comparingByValue())` |
| **Sort map by key** | `.sorted(Map.Entry.comparingByKey())` |
| **Sort map by value** | `.sorted(Map.Entry.comparingByValue())` |
| **Count matching entries** | `.filter(...).count()` |

[⬆️ Back to top](#top)