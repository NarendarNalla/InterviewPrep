# 🌊 Java Stream API — Level 1: Fundamentals

> [!NOTE]
> **🎯 Goal:** Understand what streams are, why we use them, and how a stream pipeline works before learning individual stream methods.
>
> We will learn each concept with Java code, output, and comparisons with traditional loops.

### 🎨 Color Legend

| Marker | Meaning |
|---|---|
| 🔵 **Blue** | Definition / concept |
| 🟢 **Green** | Correct / recommended / tip |
| 🔴 **Red** | Wrong / error / danger |
| 🟡 **Yellow** | Important warning / remember this |
| 🟣 **Purple** | Interview point |
| 🟠 **Orange** | Intermediate operation |
| ⭐ | Must-know for interviews |

---

## 📑 Table of Contents

1. [What is the Stream API?](#1--what-is-the-stream-api)
2. [Why do we use streams?](#2--why-do-we-use-streams)
3. [Traditional loops vs. streams](#3--traditional-loops-vs-streams)
4. [Collection vs. Stream](#4--collection-vs-stream)
5. [Creating streams](#5--creating-streams-using-stream-and-arraysstream)
6. [Intermediate vs. terminal operations](#6--intermediate-vs-terminal-operations)
7. [Stream pipeline](#7--stream-pipeline)
8. [Lazy evaluation](#8--lazy-evaluation)
9. [A stream can be consumed only once](#9--a-stream-can-be-consumed-only-once)
10. [Method references and lambdas](#10--method-references-and-lambda-expressions--revision)
11. [Cheat sheet](#-level-1--interview-revision-cheat-sheet)

---

## 1. 🔵 What is the Stream API?

The Stream API, introduced in **Java 8**, allows us to process data from collections and arrays using a **declarative** style of programming.

We can filter, transform, sort, and collect data without writing all the iteration logic ourselves.

Suppose we have a list of numbers and want to print only the even numbers.

### 🔴 Traditional approach

```java
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<Integer> numbers =
                Arrays.asList(10, 15, 20, 25, 30);

        for (int num : numbers) {
            if (num % 2 == 0) {
                System.out.println(num);
            }
        }
    }
}
```

**📤 Output:**

```text
10
20
30
```

### 🟢 Using the Stream API

```java
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<Integer> numbers =
                Arrays.asList(10, 15, 20, 25, 30);

        numbers.stream()
               .filter(n -> n % 2 == 0)
               .forEach(System.out::println);
    }
}
```

**📤 Output:**

```text
10
20
30
```

### 🔍 Understand this expression

```java
numbers.stream()
       .filter(n -> n % 2 == 0)
       .forEach(System.out::println);
```

| 🧩 Code | 💡 Meaning |
|---|---|
| `numbers.stream()` | 🔵 Create a stream from the list |
| `filter(...)` | 🟠 Keep only elements satisfying the condition |
| `n -> n % 2 == 0` | 🟣 Lambda expression checking whether a number is even |
| `forEach(...)` | 🟢 Perform an action for each remaining element |
| `System.out::println` | 🟣 Method reference to print each element |

> [!IMPORTANT]
> **🔑 Key idea:** A stream lets you describe ***what*** you want to do with the data rather than manually managing ***how*** every iteration step happens.

---

## 2. 🔵 Why do we use streams?

Streams help make data-processing code more **readable** and **expressive**.

Consider a list of employee salaries. We want to:

1. Find salaries greater than ₹50,000.
2. Convert each salary into an annual bonus equal to 10% of the salary.
3. Print the bonuses.

### 🟢 Using streams

```java
List<Integer> salaries =
        Arrays.asList(30000, 60000, 80000, 45000, 100000);

salaries.stream()
        .filter(salary -> salary > 50000)
        .map(salary -> salary * 0.10)
        .forEach(System.out::println);
```

**📤 Output:**

```text
6000.0
8000.0
10000.0
```

Notice that we chained **three operations** without writing an explicit loop.

### ✅ Important benefits

| ✅ Benefit | 📝 Description |
|---|---|
| 🧹 **Less boilerplate** | No need to manually manage iteration |
| 📖 **Readable pipelines** | Operations can be chained in a logical sequence |
| 💪 **Powerful processing** | Filtering, mapping, sorting, grouping, and aggregation |
| 😴 **Lazy evaluation** | Intermediate operations are not executed until a terminal operation begins processing |
| ⚡ **Parallel processing** | Streams can support parallel execution when appropriate |

> [!WARNING]
> **🟡 Remember:** streams are **not automatically faster** than loops. Their main advantage is expressive data processing, and performance depends on the task.

---

## 3. 🔵 Traditional loops vs. streams

| 🔁 Traditional loops | 🌊 Stream API |
|---|---|
| Uses `for`, enhanced `for`, or `while` | Uses stream operations |
| Explicitly controls iteration | Stream pipeline handles traversal |
| Uses `if` for filtering | Uses `filter()` |
| Uses assignments to transform data | Uses `map()` |
| Often uses a separate result list | Can use `collect()` to create a result |
| Useful for complex control flow | Useful for chained data processing |

**Example:** Find all numbers greater than 10.

### 🔴 Traditional loop

```java
List<Integer> result = new ArrayList<>();

for (int n : numbers) {
    if (n > 10) {
        result.add(n);
    }
}
```

### 🟢 Stream approach

```java
List<Integer> result = numbers.stream()
        .filter(n -> n > 10)
        .toList();
```

Both produce the filtered list.

> [!TIP]
> **💡 Java version note:** `toList()` is available from **Java 16**. In earlier Java versions, use `.collect(Collectors.toList())`.

> [!IMPORTANT]
> **🟣 Interview point:** Streams are **not** a replacement for every loop. Use loops when you need explicit control over indexes, `break`, `continue`, or complex mutation.

---

## 4. 🔵 Collection vs. Stream ⭐

This is one of the **most important** fundamentals.

> [!IMPORTANT]
> ### 📦 A Collection **stores** data.  🌊 A Stream **processes** data.

Consider:

```java
List<Integer> numbers =
        Arrays.asList(10, 20, 30, 40);
```

The list stores four elements in memory.

Now:

```java
numbers.stream()
       .filter(n -> n > 20)
       .forEach(System.out::println);
```

The stream processes those elements according to the operations you specify.

| 🔎 Feature | 📦 Collection | 🌊 Stream |
|---|---|---|
| **Purpose** | Store and manage data | Process data |
| **Holds elements as a data structure** | ✅ Yes | ❌ Not as a collection of stored results |
| **Reusable** | ✅ Yes | ❌ No, a consumed stream cannot be reused |
| **Changes source data automatically** | ❌ No | ❌ Generally no; operations usually produce processing results |
| **Traversal** | 🔁 Can be repeated | 1️⃣ One traversal per stream |
| **Operations** | `add()`, `remove()`, `get()` (depending on type) | `filter()`, `map()`, `sorted()`, `reduce()` |

For example:

```java
List<Integer> numbers =
        Arrays.asList(10, 20, 30);

numbers.stream()
       .filter(n -> n > 10)
       .forEach(System.out::println);

numbers.stream()
       .forEach(System.out::println);
```

🟢 Both stream operations work because each call to `numbers.stream()` creates a **new** stream.

**📤 Output:**

```text
20
30
10
20
30
```

The original list is **not changed** by these operations.

---

## 5. 🔵 Creating streams using `stream()` and `Arrays.stream()`

There are two common ways to create streams from collections and arrays.

### 🅰️ Using `stream()` for collections

```java
List<String> names =
        Arrays.asList("Ravi", "Priya", "Arun");

names.stream()
     .forEach(System.out::println);
```

**📤 Output:**

```text
Ravi
Priya
Arun
```

**General syntax:**

```java
collection.stream()
```

### 🅱️ Using `Arrays.stream()` for arrays

```java
int[] numbers = {10, 20, 30, 40};

Arrays.stream(numbers)
      .forEach(System.out::println);
```

**📤 Output:**

```text
10
20
30
40
```

**General syntax:**

```java
Arrays.stream(array)
```

For an `int[]`, Java creates an **`IntStream`**, which provides primitive-oriented operations.

**Example:**

```java
int[] numbers = {10, 20, 30, 40};

int sum = Arrays.stream(numbers).sum();

System.out.println(sum);
```

**📤 Output:**

```text
100
```

### 📌 Remember

| 🧰 Source | ✍️ Syntax |
|---|---|
| Collection | `list.stream()` |
| Array | `Arrays.stream(array)` |
| Supplied values | `Stream.of(...)` |

---

## 6. 🟠 Intermediate vs. terminal operations ⭐

Stream operations fall into two major categories.

### 🟠 Intermediate operations

These **transform a stream into another stream**.

**Examples:**

- 🟠 `filter()`
- 🟠 `map()`
- 🟠 `sorted()`
- 🟠 `distinct()`
- 🟠 `limit()`
- 🟠 `skip()`

**Example:**

```java
numbers.stream()
       .filter(n -> n > 10)
       .map(n -> n * 2);
```

> [!CAUTION]
> 🔴 This pipeline has **no terminal operation**. It does **not** process the elements yet.

### 🟢 Terminal operations

These **trigger stream processing** and produce a result or perform an action.

**Examples:**

- 🟢 `forEach()`
- 🟢 `toList()`
- 🟢 `collect()`
- 🟢 `count()`
- 🟢 `reduce()`
- 🟢 `min()`
- 🟢 `max()`
- 🟢 `anyMatch()`

**Example:**

```java
long count = numbers.stream()
        .filter(n -> n > 10)
        .count();

System.out.println(count);
```

**📤 Output for `{10, 20, 30, 40}`:**

```text
3
```

Here, `filter()` is 🟠 intermediate and `count()` is 🟢 terminal.

| ⚙️ Operation | 🏷️ Category | ↩️ Returns |
|---|---|---|
| `filter()` | 🟠 Intermediate | `Stream` |
| `map()` | 🟠 Intermediate | `Stream` |
| `sorted()` | 🟠 Intermediate | `Stream` |
| `distinct()` | 🟠 Intermediate | `Stream` |
| `forEach()` | 🟢 Terminal | `void` |
| `count()` | 🟢 Terminal | `long` |
| `toList()` | 🟢 Terminal | `List` |
| `reduce()` | 🟢 Terminal | A reduced result, often an `Optional` |

> [!IMPORTANT]
> **🟣 Interview rule:** A stream pipeline normally needs a **terminal operation** to trigger its processing.

---

## 7. 🔵 Stream pipeline ⭐

A stream pipeline consists of **three logical parts**:

```text
┌──────────────┐     ┌──────────────────────────┐     ┌──────────────────┐
│  🔵 SOURCE   │ ──▶ │  🟠 INTERMEDIATE OPS     │ ──▶ │  🟢 TERMINAL OP  │
│              │     │  (zero or more)          │     │  (exactly one)   │
│ numbers      │     │ filter(n -> n > 10)      │     │ toList()         │
│   .stream()  │     │ map(n -> n * 2)          │     │                  │
└──────────────┘     └──────────────────────────┘     └──────────────────┘
  Where data          How elements should be            Triggers processing
  comes from          processed                         and gets the result
```

| # | Part | Example | Role |
|---|---|---|---|
| 1️⃣ | 🔵 **Source** | `numbers.stream()` | Where the data comes from |
| 2️⃣ | 🟠 **Intermediate operations** | `filter(n -> n > 10)`, `map(n -> n * 2)` | Describe how elements should be processed |
| 3️⃣ | 🟢 **Terminal operation** | `toList()` | Triggers processing and obtains the result |

Let's run the complete example:

```java
List<Integer> numbers =
        Arrays.asList(5, 10, 15, 20, 25);

List<Integer> result = numbers.stream()
        .filter(n -> n > 10)
        .map(n -> n * 2)
        .toList();

System.out.println(result);
```

### 🧪 Dry run

| 📥 Input | `n > 10` | After `map(n * 2)` |
|---|---|---|
| 5 | ❌ False | 🚫 Excluded |
| 10 | ❌ False | 🚫 Excluded |
| 15 | ✅ True | **30** |
| 20 | ✅ True | **40** |
| 25 | ✅ True | **50** |

**📤 Output:**

```text
[30, 40, 50]
```

The source is the list, `filter()` and `map()` are 🟠 intermediate operations, and `toList()` is the 🟢 terminal operation.

---

## 8. 😴 Lazy evaluation ⭐

> [!NOTE]
> **🔵 Definition:** Lazy evaluation means intermediate operations do **not** process elements until a terminal operation triggers the pipeline.

Consider:

```java
List<Integer> numbers =
        Arrays.asList(10, 20, 30);

numbers.stream()
       .filter(n -> {
           System.out.println("Filtering: " + n);
           return n > 10;
       });

System.out.println("Done");
```

**📤 Output:**

```text
Done
```

❓ Why did it not print `Filtering: 10`, `Filtering: 20`, and `Filtering: 30`?

🔴 Because there is **no terminal operation**. The `filter()` stage is defined, but no elements are processed.

### 🟢 Now add `forEach()`

```java
numbers.stream()
       .filter(n -> {
           System.out.println("Filtering: " + n);
           return n > 10;
       })
       .forEach(System.out::println);
```

**📤 Output:**

```text
Filtering: 10
Filtering: 20
20
Filtering: 30
30
```

✅ The terminal operation triggers the pipeline.

> [!TIP]
> **🔍 An important detail:** streams can process elements through the pipeline **one at a time** rather than completing the entire filtering stage before starting the mapping stage.

For example:

```java
numbers.stream()
       .filter(n -> n > 10)
       .map(n -> n * 2)
       .forEach(System.out::println);
```

The stream can filter `20`, map it to `40`, and emit it **before** moving on to `30`.

> [!IMPORTANT]
> **🟣 Interview takeaway:** 🟠 Intermediate operations are **lazy**; 🟢 terminal operations **trigger execution**.

---

## 9. 🔴 A stream can be consumed only once ⭐

> [!WARNING]
> **🟡 Rule:** Once a terminal operation has consumed a stream, you **cannot reuse** that same stream instance.

### ❌ Incorrect

```java
List<Integer> numbers =
        Arrays.asList(10, 20, 30);

Stream<Integer> stream = numbers.stream();

stream.forEach(System.out::println);

stream.count(); // ❌ IllegalStateException
```

🔴 The first terminal operation consumes the stream. The second operation attempts to reuse it and throws an **`IllegalStateException`**.

### ✅ Correct approach

```java
List<Integer> numbers =
        Arrays.asList(10, 20, 30);

long count = numbers.stream().count();

numbers.stream().forEach(System.out::println);
```

🟢 Each call to `numbers.stream()` creates a **fresh** stream.

> [!IMPORTANT]
> **🟣 Interview rule:** Reuse the **collection or array** as the source, **not** the already-consumed stream.

---

## 10. 🔵 Method references and lambda expressions — revision

Both help you **pass behavior** to stream operations.

### 🅰️ Lambda expression

A lambda expresses behavior in a compact form.

```java
numbers.stream()
       .filter(n -> n > 10)
       .forEach(n -> System.out.println(n));
```

Here:

```java
n -> n > 10
```

means *"take `n` and return whether it is greater than 10."*

And:

```java
n -> System.out.println(n)
```

means *"take `n` and print it."*

### 🅱️ Method reference

A method reference refers to an **existing method** instead of writing a lambda that calls it.

```java
numbers.stream()
       .filter(n -> n > 10)
       .forEach(System.out::println);
```

The method reference:

```java
System.out::println
```

is equivalent to the lambda:

```java
n -> System.out.println(n)
```

### 🔄 Other examples

| 🔴 Lambda | 🟢 Equivalent method reference |
|---|---|
| `s -> s.toUpperCase()` | `String::toUpperCase` |
| `s -> System.out.println(s)` | `System.out::println` |
| `s -> s.length()` | `String::length` |
| `n -> Integer.parseInt(n)` | `Integer::parseInt` |

> [!TIP]
> **💡 When to use which:** Method references are useful when an existing method already does exactly what you need. Use lambdas when you need **additional logic**.

---

## 📝 Level 1 — Interview Revision Cheat Sheet

| 🏷️ Topic | 🧠 Must remember |
|---|---|
| 🔵 **Stream API** | Processes data **declaratively** |
| 📦 **Collection vs. Stream** | Collection **stores**; stream **processes** |
| 🧰 **Creating streams** | `list.stream()`, `Arrays.stream(array)` |
| 🟠 **Intermediate operations** | Return **another stream** |
| 🟢 **Terminal operations** | **Trigger** processing |
| 🔗 **Pipeline** | Source → intermediate → terminal |
| 😴 **Lazy evaluation** | Intermediate operations execute only when needed by a terminal operation |
| 🔴 **Single-use rule** | A consumed stream **cannot be reused** |
| 🟣 **Lambda** | Expresses behavior |
| 🟣 **Method reference** | Refers to an existing method |

> [!IMPORTANT]
> ### 🏆 Top 5 things to never forget
> 1. 📦 Collection **stores**, 🌊 Stream **processes**
> 2. 🟠 Intermediate = **lazy** → 🟢 Terminal = **triggers**
> 3. 🔴 A stream is **single-use**
> 4. 🔗 Pipeline = **Source → Intermediate → Terminal**
> 5. ⚡ Streams are **not automatically faster** than loops