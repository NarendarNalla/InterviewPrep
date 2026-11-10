# 🌊 Java Stream API — Level 2: Must-Know Methods

We'll solve all 15 problems using your given `nums` list, explore different approaches where useful, and then combine everything into one complete Java program with basic explanations and expected outputs in comments.

```java
List<Integer> nums =
    Arrays.asList(10, 5, 20, 5, 30, 15, 40, 10);
```

> [!IMPORTANT]
> Some stream operations, such as `count()`, `min()`, `max()`, and `findFirst()`, are **terminal operations**. Each independent solution should create a **fresh stream** using `nums.stream()`.

---

## 📑 Table of Contents

1. [Quick reference: methods and return types](#1-quick-reference-methods-and-return-types)
2. [Solve each problem in different ways](#2-solve-each-problem-in-different-ways)
3. [Final complete Java program — all 15 problems](#3-final-complete-java-program--all-15-problems)
4. [How to practise these methods for interviews](#4-how-to-practise-these-methods-for-interviews)

---

## 1. Quick reference: methods and return types

| Method | Return type | Category |
|---|---|---|
| `filter()` | `Stream<T>` | 🟠 Intermediate |
| `map()` | `Stream<R>` | 🟠 Intermediate |
| `sorted()` | `Stream<T>` | 🟠 Intermediate |
| `distinct()` | `Stream<T>` | 🟠 Intermediate |
| `limit()` / `skip()` | `Stream<T>` | 🟠 Intermediate |
| `forEach()` | `void` | 🟢 Terminal |
| `count()` | `long` | 🟢 Terminal |
| `min()` / `max()` | `Optional<T>` | 🟢 Terminal |
| `findFirst()` / `findAny()` | `Optional<T>` | 🟢 Terminal |
| `anyMatch()` / `allMatch()` / `noneMatch()` | `boolean` | 🟢 Terminal |
| `reduce()` | Depends on overload | 🟢 Terminal |
| `collect()` | Depends on collector | 🟢 Terminal |

---

## 2. Solve each problem in different ways

### Problem 1 — Filter all even numbers

**Approach 1: Using `filter()`**

```java
List<Integer> result = nums.stream()
        .filter(n -> n % 2 == 0)
        .collect(Collectors.toList());

// Output: [10, 20, 30, 40, 10]
```

**Approach 2: Using a traditional loop**

```java
List<Integer> result = new ArrayList<>();

for (int n : nums) {
    if (n % 2 == 0) {
        result.add(n);
    }
}

// Output: [10, 20, 30, 40, 10]
```

**Explanation:** `n % 2 == 0` checks whether a number is divisible by 2. `filter()` keeps only elements for which the condition is true.

---

### Problem 2 — Find numbers greater than 15

**Approach 1: Using `filter()`**

```java
List<Integer> result = nums.stream()
        .filter(n -> n > 15)
        .collect(Collectors.toList());

// Output: [20, 30, 40]
```

**Approach 2: Using a loop**

```java
List<Integer> result = new ArrayList<>();

for (int n : nums) {
    if (n > 15) {
        result.add(n);
    }
}

// Output: [20, 30, 40]
```

**Explanation:** `filter()` selects elements without changing their values or the original list.

---

### Problem 3 — Square every number using `map()`

**Approach 1: Using `map()`**

```java
List<Integer> result = nums.stream()
        .map(n -> n * n)
        .collect(Collectors.toList());

// Output: [100, 25, 400, 25, 900, 225, 1600, 100]
```

**Approach 2: Using a loop**

```java
List<Integer> result = new ArrayList<>();

for (int n : nums) {
    result.add(n * n);
}

// Output: [100, 25, 400, 25, 900, 225, 1600, 100]
```

**Explanation:** Unlike `filter()`, which selects elements, `map()` transforms **every** element.

For example, `10` becomes `100`, and `5` becomes `25`.

---

### Problem 4 — Remove duplicates

**Approach 1: Using `distinct()`**

```java
List<Integer> result = nums.stream()
        .distinct()
        .collect(Collectors.toList());

// Output: [10, 5, 20, 30, 15, 40]
```

**Approach 2: Using a `LinkedHashSet`**

```java
List<Integer> result = new ArrayList<>(
        new LinkedHashSet<>(nums));

// Output: [10, 5, 20, 30, 15, 40]
```

`LinkedHashSet` removes duplicates while preserving insertion order.

> [!IMPORTANT]
> **🟣 Interview point:** `distinct()` uses equality to identify duplicates. For custom objects, implement `equals()` and `hashCode()` appropriately.

---

### Problem 5 — Sort in ascending order

**Approach 1: Natural ordering**

```java
List<Integer> result = nums.stream()
        .sorted()
        .collect(Collectors.toList());

// Output: [5, 5, 10, 10, 15, 20, 30, 40]
```

**Approach 2: Explicit comparator**

```java
List<Integer> result = nums.stream()
        .sorted(Comparator.naturalOrder())
        .collect(Collectors.toList());

// Output: [5, 5, 10, 10, 15, 20, 30, 40]
```

Both use ascending numerical order for integers.

---

### Problem 6 — Sort in descending order

**Approach 1: Reverse order comparator**

```java
List<Integer> result = nums.stream()
        .sorted(Comparator.reverseOrder())
        .collect(Collectors.toList());

// Output: [40, 30, 20, 15, 10, 10, 5, 5]
```

**Approach 2: Reverse the comparator**

```java
List<Integer> result = nums.stream()
        .sorted(Comparator.<Integer>naturalOrder().reversed())
        .collect(Collectors.toList());

// Output: [40, 30, 20, 15, 10, 10, 5, 5]
```

> [!IMPORTANT]
> **🟣 Interview point:** For descending order, `Comparator.reverseOrder()` is usually the simplest choice.

---

### Problem 7 — Find the first three elements

**Approach 1: Using `limit()`**

```java
List<Integer> result = nums.stream()
        .limit(3)
        .collect(Collectors.toList());

// Output: [10, 5, 20]
```

**Approach 2: Using a loop**

```java
List<Integer> result = new ArrayList<>();

for (int i = 0; i < nums.size() && i < 3; i++) {
    result.add(nums.get(i));
}

// Output: [10, 5, 20]
```

`limit(3)` retains at most three elements from the beginning of the stream.

---

### Problem 8 — Skip the first two elements

**Approach 1: Using `skip()`**

```java
List<Integer> result = nums.stream()
        .skip(2)
        .collect(Collectors.toList());

// Output: [20, 5, 30, 15, 40, 10]
```

**Approach 2: Using a loop**

```java
List<Integer> result = new ArrayList<>();

for (int i = 2; i < nums.size(); i++) {
    result.add(nums.get(i));
}

// Output: [20, 5, 30, 15, 40, 10]
```

> [!WARNING]
> `skip(2)` skips the **first two elements**, not all elements with values less than or equal to 2.

---

### Problem 9 — Count even numbers

**Approach 1: Using `filter()` and `count()`**

```java
long count = nums.stream()
        .filter(n -> n % 2 == 0)
        .count();

// Output: 5
```

**Approach 2: Using `mapToInt()` and `sum()`**

```java
int count = nums.stream()
        .mapToInt(n -> n % 2 == 0 ? 1 : 0)
        .sum();

// Output: 5
```

The second approach converts each even number into `1` and each odd number into `0`, then adds the results.

> [!TIP]
> **Preferred approach:** `filter(...).count()` is clearer for counting matching elements.

---

### Problem 10 — Find minimum and maximum

**Approach 1: Using `min()` and `max()`**

```java
int min = nums.stream()
        .min(Comparator.naturalOrder())
        .orElseThrow();

int max = nums.stream()
        .max(Comparator.naturalOrder())
        .orElseThrow();

// Output:
// Minimum: 5
// Maximum: 40
```

**Approach 2: Using `mapToInt()`**

```java
int min = nums.stream()
        .mapToInt(Integer::intValue)
        .min()
        .orElseThrow();

int max = nums.stream()
        .mapToInt(Integer::intValue)
        .max()
        .orElseThrow();

// Output:
// Minimum: 5
// Maximum: 40
```

Both approaches return the same result. The `min()` and `max()` methods return `Optional` because a stream may be empty. `orElseThrow()` throws an exception if there is no result.

---

### Problem 11 — Check whether any number is greater than 35

**Approach 1: Using `anyMatch()`**

```java
boolean result = nums.stream()
        .anyMatch(n -> n > 35);

// Output: true
```

**Approach 2: Using a loop**

```java
boolean result = false;

for (int n : nums) {
    if (n > 35) {
        result = true;
        break;
    }
}

// Output: true
```

`anyMatch()` returns `true` as soon as a matching element is found. In our list, `40` satisfies the condition.

---

### Problem 12 — Check whether all numbers are positive

**Approach 1: Using `allMatch()`**

```java
boolean result = nums.stream()
        .allMatch(n -> n > 0);

// Output: true
```

**Approach 2: Using a loop**

```java
boolean result = true;

for (int n : nums) {
    if (n <= 0) {
        result = false;
        break;
    }
}

// Output: true
```

`allMatch()` returns `true` only if every element satisfies the condition.

> [!WARNING]
> Note that `0` is **not** positive.

---

### Problem 13 — Find the first number divisible by 3

**Approach 1: Using `filter()` and `findFirst()`**

```java
int result = nums.stream()
        .filter(n -> n % 3 == 0)
        .findFirst()
        .orElseThrow();

// Output: 30
```

**Approach 2: Using a loop**

```java
int result = -1;

for (int n : nums) {
    if (n % 3 == 0) {
        result = n;
        break;
    }
}

// Output: 30
```

`findFirst()` returns an `Optional<Integer>` because there might not be a matching element. Here, `30` is the first number divisible by 3 in encounter order.

---

### Problem 14 — Calculate the sum using `reduce()`

**Approach 1: Using `reduce()`**

```java
int sum = nums.stream()
        .reduce(0, (a, b) -> a + b);

// Output: 135
```

**Approach 2: Using a method reference**

```java
int sum = nums.stream()
        .reduce(0, Integer::sum);

// Output: 135
```

**Approach 3: Using `mapToInt().sum()`**

```java
int sum = nums.stream()
        .mapToInt(Integer::intValue)
        .sum();

// Output: 135
```

**How `reduce()` works:**

- `0` is the initial value.
- `a` is the accumulated result.
- `b` is the next number.
- `a + b` combines them into a single result.

For example, the accumulation begins with `0 + 10`, then adds `5`, then `20`, and continues until the total is `135`.

---

### Problem 15 — Collect even numbers into a new list

**Approach 1: Using `collect()`**

```java
List<Integer> result = nums.stream()
        .filter(n -> n % 2 == 0)
        .collect(Collectors.toList());

// Output: [10, 20, 30, 40, 10]
```

**Approach 2: Using `toList()` (Java 16+)**

```java
List<Integer> result = nums.stream()
        .filter(n -> n % 2 == 0)
        .toList();

// Output: [10, 20, 30, 40, 10]
```

Both collect the matching elements into a list.

> [!NOTE]
> **One important difference:** `Stream.toList()` returns an **unmodifiable** list, whereas `Collectors.toList()` does not guarantee a particular mutability contract.

---

## 3. Final complete Java program — all 15 problems

This version is compatible with **Java 8 and later**, uses `Collectors.toList()`, and includes comments explaining each method and its output.

```java
package com.oct.play.streams;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class StreamMethodsPractice {

    public static void main(String[] args) {

        // Input list containing duplicate numbers
        List<Integer> nums =
                Arrays.asList(10, 5, 20, 5, 30, 15, 40, 10);

        System.out.println("Original List: " + nums);
        // Output: [10, 5, 20, 5, 30, 15, 40, 10]


        // 1. filter() - Select all even numbers
        List<Integer> evenNumbers = nums.stream()
                .filter(n -> n % 2 == 0)
                .collect(Collectors.toList());

        System.out.println("1. Even Numbers: " + evenNumbers);
        // Output: [10, 20, 30, 40, 10]


        // 2. filter() - Find numbers greater than 15
        List<Integer> greaterThan15 = nums.stream()
                .filter(n -> n > 15)
                .collect(Collectors.toList());

        System.out.println("2. Greater Than 15: " + greaterThan15);
        // Output: [20, 30, 40]


        // 3. map() - Square every number
        List<Integer> squares = nums.stream()
                .map(n -> n * n)
                .collect(Collectors.toList());

        System.out.println("3. Squares: " + squares);
        // Output: [100, 25, 400, 25, 900, 225, 1600, 100]


        // 4. distinct() - Remove duplicate elements
        List<Integer> uniqueNumbers = nums.stream()
                .distinct()
                .collect(Collectors.toList());

        System.out.println("4. Distinct Numbers: " + uniqueNumbers);
        // Output: [10, 5, 20, 30, 15, 40]


        // 5. sorted() - Sort in ascending order
        List<Integer> ascending = nums.stream()
                .sorted()
                .collect(Collectors.toList());

        System.out.println("5. Ascending: " + ascending);
        // Output: [5, 5, 10, 10, 15, 20, 30, 40]


        // 6. sorted() - Sort in descending order
        List<Integer> descending = nums.stream()
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());

        System.out.println("6. Descending: " + descending);
        // Output: [40, 30, 20, 15, 10, 10, 5, 5]


        // 7. limit() - Keep the first three elements
        List<Integer> firstThree = nums.stream()
                .limit(3)
                .collect(Collectors.toList());

        System.out.println("7. First Three: " + firstThree);
        // Output: [10, 5, 20]


        // 8. skip() - Skip the first two elements
        List<Integer> skippedTwo = nums.stream()
                .skip(2)
                .collect(Collectors.toList());

        System.out.println("8. Skip First Two: " + skippedTwo);
        // Output: [20, 5, 30, 15, 40, 10]


        // 9. count() - Count even numbers
        long evenCount = nums.stream()
                .filter(n -> n % 2 == 0)
                .count();

        System.out.println("9. Even Count: " + evenCount);
        // Output: 5


        // 10. min() and max() - Find minimum and maximum
        int min = nums.stream()
                .min(Comparator.naturalOrder())
                .orElseThrow();

        int max = nums.stream()
                .max(Comparator.naturalOrder())
                .orElseThrow();

        System.out.println("10. Minimum: " + min);
        // Output: 5

        System.out.println("10. Maximum: " + max);
        // Output: 40


        // 11. anyMatch() - Is any number greater than 35?
        boolean anyGreaterThan35 = nums.stream()
                .anyMatch(n -> n > 35);

        System.out.println("11. Any Greater Than 35: "
                + anyGreaterThan35);
        // Output: true


        // 12. allMatch() - Are all numbers positive?
        boolean allPositive = nums.stream()
                .allMatch(n -> n > 0);

        System.out.println("12. All Positive: " + allPositive);
        // Output: true


        // 13. findFirst() - Find the first number divisible by 3
        Optional<Integer> firstDivisibleBy3 = nums.stream()
                .filter(n -> n % 3 == 0)
                .findFirst();

        System.out.println("13. First Divisible By 3: "
                + firstDivisibleBy3.orElseThrow());
        // Output: 30


        // 14. reduce() - Calculate the sum of all numbers
        int sum = nums.stream()
                .reduce(0, Integer::sum);

        System.out.println("14. Sum Using Reduce: " + sum);
        // Output: 135


        // 15. collect() - Collect even numbers into a new list
        List<Integer> collectedEvens = nums.stream()
                .filter(n -> n % 2 == 0)
                .collect(Collectors.toList());

        System.out.println("15. Collected Even Numbers: "
                + collectedEvens);
        // Output: [10, 20, 30, 40, 10]


        // Additional: noneMatch() - Are there no negative numbers?
        boolean noNegativeNumbers = nums.stream()
                .noneMatch(n -> n < 0);

        System.out.println("16. No Negative Numbers: "
                + noNegativeNumbers);
        // Output: true


        // Additional: findAny() - Find any number divisible by 5
        // For this sequential stream, the result is commonly 10.
        // Unlike findFirst(), findAny() does not promise the first element.
        Optional<Integer> anyDivisibleBy5 = nums.stream()
                .filter(n -> n % 5 == 0)
                .findAny();

        System.out.println("17. Any Number Divisible By 5: "
                + anyDivisibleBy5.orElseThrow());
        // Output for this sequential stream: 10
    }
}
```

---

## 4. How to practise these methods for interviews

Don't just memorize the syntax. Practise **recognizing the problem type**.

| If the question asks you to… | Think of… |
|---|---|
| Select elements by condition | `filter()` |
| Change each element | `map()` |
| Order the elements | `sorted()` |
| Remove duplicates | `distinct()` |
| Take the first N elements | `limit()` |
| Ignore the first N elements | `skip()` |
| Count matching elements | `filter().count()` |
| Find the smallest or largest | `min()` / `max()` |
| Check whether any or all match | `anyMatch()` / `allMatch()` |
| Find a matching element | `findFirst()` / `findAny()` |
| Combine all values into one | `reduce()` |
| Create a result list | `collect()` |

> [!TIP]
> **Recommended next step:** Practise combining methods instead of using only one method at a time. For example:

```java
// Find the squares of unique even numbers greater than 10,
// sorted in descending order.

List<Integer> result = nums.stream()
        .filter(n -> n > 10)
        .filter(n -> n % 2 == 0)
        .distinct()
        .map(n -> n * n)
        .sorted(Comparator.reverseOrder())
        .collect(Collectors.toList());

System.out.println(result);
// Output: [1600, 900, 400]
```

> [!IMPORTANT]
> This is the key skill for stream-based interview problems: understanding how to **chain intermediate operations** and finish with the appropriate **terminal operation**.