<a id="top"></a>

# 📚 Java Collections Framework — Interview Preparation Guide

This guide covers the `List` interface, `ArrayList`, `LinkedList`, `Vector`, `Stack`, internal implementation, differences, important methods, conversions, copying, and interview questions with Java examples.

The goal is to understand **three things** for each topic:

1. **What** is it?
2. **How** does it work internally?
3. **When and why** should we use it?

---

## 🧭 Table of Contents

*Click any item to jump straight to that section. Every section has a "Back to top" link at its end.*

| # | Section | Type |
|---|---|---|
| 1 | [Java Collection Framework overview](#s1) | 🔵 Concept |
| 2 | [List interface — important methods](#s2) | 🔵 Concept |
| 3 | [ArrayList — internal implementation, usage and complexity](#s3) | ⭐ Core |
| 4 | [LinkedList — internal implementation and usage](#s4) | ⭐ Core |
| 5 | [ArrayList vs LinkedList vs Vector vs Stack](#s5) | ⭐ Core |
| 6 | [Stack extends Vector — why is Stack synchronized?](#s6) | 🔵 Concept |
| 7 | [Sorting a List — `sort()`](#s7) | 🟢 Practical |
| 8 | [Converting ArrayList to Array and Array to ArrayList](#s8) | ⭐ Core |
| 9 | [`clear()` vs `removeAll()`](#s9) | 🟣 Interview |
| 10 | [`retainAll()` — keep common elements](#s10) | 🟢 Practical |
| 11 | [`ensureCapacity()` and `trimToSize()`](#s11) | 🟢 Practical |
| 12 | [`subList(start, end)` — a view, not an independent list](#s12) | 🟣 Interview |
| 13 | [`replaceAll()` and `removeIf()`](#s13) | 🟢 Practical |
| 14 | [`forEach()` — why does changing `e` not update the list?](#s14) | 🟣 Interview |
| 15 | [`clone()`, `Cloneable`, shallow copy and deep copy](#s15) | ⭐ Core |
| 16 | [`ConcurrentModificationException` (CME)](#s16) | ⭐ Core |
| 17 | [Additional important List interview questions](#s17) | 🟣 Interview |
| 18 | [Interview revision cheat sheet](#s18) | 📝 Revision |
| 19 | [Practice questions for interview preparation](#s19) | 📝 Revision |

### 🔖 Quick jumps

[⚡ Cheat sheet](#s18) · [❓ Practice questions](#s19) · [⚖️ Comparison table](#s5) · [🪤 Interview traps: remove()](#remove-trap) · [🪤 CME](#s16) · [🪤 Shallow vs deep copy](#s15)

---

<a id="s1"></a>

## 1. Java Collection Framework overview

The Java Collections Framework provides interfaces and classes to store, retrieve, manipulate, and process groups of objects.

```text
Iterable
   │
   ▼
Collection (interface)
   │
   ├── List    →  Ordered, duplicates allowed
   ├── Set     →  Unique elements
   └── Queue   →  Processing elements
```

### Common List implementations

```text
List
 ├── ArrayList    →  Resizable array
 ├── LinkedList   →  Doubly linked nodes
 ├── Vector       →  Synchronized resizable array
 └── Stack        →  LIFO, extends Vector
```

> [!IMPORTANT]
> **🟣 Interview point:** `List` is an **interface**, not a concrete class. `ArrayList`, `LinkedList`, and `Vector` implement `List`; `Stack` extends `Vector`.

```java
List<String> names = new ArrayList<>();

names.add("Java");
names.add("Python");
names.add("Java");

System.out.println(names); // [Java, Python, Java]
```

> [!TIP]
> **Why use `List` as the reference type?** It reduces dependency on a particular implementation, making it easier to change `ArrayList` to `LinkedList` when appropriate.

[⬆️ Back to top](#top)

---

<a id="s2"></a>

## 2. List interface — important methods

The `List` interface represents an **ordered** collection. It supports duplicates, positional access, and insertion at a specified index.

### Must-know methods

| Method | Purpose | Example |
|---|---|---|
| `add(e)` | Add an element | `list.add("Java")` |
| `add(index, e)` | Insert at an index | `list.add(1, "SQL")` |
| `get(index)` | Retrieve an element | `list.get(0)` |
| `set(index, e)` | Replace an element | `list.set(0, "Spring")` |
| `remove(index)` | Remove by position | `list.remove(0)` |
| `remove(object)` | Remove a matching object | `list.remove("Java")` |
| `contains(e)` | Check existence | `list.contains("Java")` |
| `indexOf(e)` | First matching index | `list.indexOf("Java")` |
| `lastIndexOf(e)` | Last matching index | `list.lastIndexOf("Java")` |
| `size()` | Number of elements | `list.size()` |
| `isEmpty()` | Check if empty | `list.isEmpty()` |
| `clear()` | Remove all elements | `list.clear()` |
| `sort(comparator)` | Sort elements | `list.sort(null)` |
| `subList(from, to)` | Get a range view | `list.subList(1, 3)` |
| `replaceAll(operator)` | Transform elements | `list.replaceAll(String::toUpperCase)` |
| `removeIf(predicate)` | Remove matching elements | `list.removeIf(x -> x < 0)` |

### Example

```java
import java.util.*;

public class ListDemo {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();

        list.add("Java");
        list.add("Python");
        list.add("Java");
        list.add(1, "SQL");

        System.out.println(list);
        // [Java, SQL, Python, Java]

        System.out.println(list.get(0));          // Java
        System.out.println(list.indexOf("Java")); // 0
        System.out.println(list.lastIndexOf("Java")); // 3

        list.set(0, "Spring");
        System.out.println(list);
        // [Spring, SQL, Python, Java]

        list.remove("Java");
        System.out.println(list);
        // [Spring, SQL, Python]
    }
}
```

<a id="remove-trap"></a>

### 🪤 Interview trap: `remove()` with integers

```java
List<Integer> numbers =
        new ArrayList<>(Arrays.asList(10, 20, 30));

numbers.remove(1);                  // Removes index 1: value 20
numbers.remove(Integer.valueOf(30)); // Removes the value 30
```

> [!WARNING]
> The first call uses `remove(int index)`. The second explicitly uses `remove(Object o)`.

[⬆️ Back to top](#top)

---

<a id="s3"></a>

## 3. ArrayList — internal implementation, usage and complexity

`ArrayList` is a **resizable-array** implementation of the `List` interface.

### How does it work internally?

An `ArrayList` stores its elements in an internal array. When that array runs out of capacity, Java creates a **larger array** and **copies** the existing elements into it.

**Example:**

```text
Before adding another element (array is full in this illustration):

┌──────┬─────┬────────┬───┐
│ Java │ SQL │ Python │ — │
└──────┴─────┴────────┴───┘

        │  Allocate a larger array and copy elements
        ▼

After resizing:

┌──────┬─────┬────────┬────────┬───┬───┐
│ Java │ SQL │ Python │ Spring │ — │ — │
└──────┴─────┴────────┴────────┴───┴───┘
```

> *Illustrative capacity only; actual growth depends on the JDK implementation.*

### ⏱️ Time complexity

| Operation | Typical complexity |
|---|---|
| `get(index)` | **O(1)** |
| `set(index, value)` | **O(1)** |
| `add(element)` at the end | **Amortized O(1)** |
| Insert at the beginning or middle | **O(n)** |
| Remove from the beginning or middle | **O(n)** |
| Search using `contains()` | **O(n)** |
| Sort | **O(n log n)** |

> **Why is insertion in the middle O(n)?** Elements after the insertion position must be shifted to make room.

### ✅ When should you use `ArrayList`?

- When you frequently access elements by index.
- When you frequently iterate through elements.
- When you mostly add elements at the end.
- When you need a general-purpose list for business applications.

```java
List<String> languages = new ArrayList<>();

languages.add("Java");
languages.add("Python");
languages.add("JavaScript");

System.out.println(languages.get(1)); // Python
```

> [!IMPORTANT]
> **🟣 Interview answer:** `ArrayList` offers fast random access because elements are stored in an array. Insertions and removals in the middle can be expensive because they require shifting elements.

[⬆️ Back to top](#top)

---

<a id="s4"></a>

## 4. LinkedList — internal implementation and usage

`LinkedList` implements both `List` and `Deque`. Internally, it uses a **doubly linked list**.

Each node stores:

- A reference to the **previous** node.
- The **element** itself.
- A reference to the **next** node.

### Doubly linked list

```text
   ┌────────┐      ┌────────┐      ┌────────┐
   │ Node A │ ◄──► │ Node B │ ◄──► │ Node C │
   └────────┘      └────────┘      └────────┘
```

Each node links to both its predecessor and successor.

Unlike an array, linked-list elements are **not** stored in one contiguous array. To access an index, Java traverses nodes from an end of the list.

### ⏱️ Time complexity

| Operation | Complexity |
|---|---|
| `get(index)` | **O(n)** |
| `addFirst(e)` / `addLast(e)` | **O(1)** |
| `removeFirst()` / `removeLast()` | **O(1)** |
| Search using `contains()` | **O(n)** |
| Insert/remove at a known node | **O(1)** |
| Insert/remove at an index | **O(n)** |

> [!WARNING]
> **Important distinction:** insertion or deletion at a **known node** can be O(1), but **finding** that node by index usually costs O(n).

### Example

```java
import java.util.*;

public class LinkedListDemo {
    public static void main(String[] args) {
        LinkedList<String> list = new LinkedList<>();

        list.add("B");
        list.addFirst("A");
        list.addLast("C");

        System.out.println(list); // [A, B, C]

        System.out.println(list.getFirst()); // A
        System.out.println(list.getLast());  // C

        list.removeFirst();
        list.removeLast();

        System.out.println(list); // [B]
    }
}
```

### ✅ When should you use `LinkedList`?

- When you need a `Deque` implementation with frequent operations at both ends.
- When you need queue or deque behavior and do not want to use an array-backed deque.

However, `ArrayDeque` is often the better choice for a queue or stack because it generally has lower memory overhead and better cache locality.

> [!IMPORTANT]
> **🟣 Interview answer:** `LinkedList` supports efficient insertion and removal at its ends, but indexed access is slower than `ArrayList`. Do not assume it is always faster for frequent insertions and deletions: locating the position still takes time.

[⬆️ Back to top](#top)

---

<a id="s5"></a>

## 5. ArrayList vs LinkedList vs Vector vs Stack

| Feature | ArrayList | LinkedList | Vector | Stack |
|---|---|---|---|---|
| **Data structure** | Resizable array | Doubly linked list | Resizable array | Resizable array via `Vector` |
| **Implements `List`** | Yes | Yes | Yes | Inherited through `Vector` |
| **Synchronized methods** | No | No | Yes, for its synchronized methods | Inherited synchronized methods |
| **Indexed access** | Fast, O(1) | O(n) | Fast, O(1) | Fast, O(1) |
| **Add at end** | Amortized O(1) | O(1) | Amortized O(1) | Amortized O(1) |
| **Add/remove at front** | O(n) | O(1) | O(n) | O(n) |
| **Duplicates** | Allowed | Allowed | Allowed | Allowed |
| **Null elements** | Allowed | Allowed | Allowed | Allowed |
| **Modern recommendation** | General-purpose list | Specialized deque/list cases | Legacy cases | Prefer `Deque` |

### 🎯 What should you choose?

| Choice | When |
|---|---|
| 🟢 **`ArrayList`** | Default choice for most list-based tasks |
| 🟡 **`LinkedList`** | When linked-list behavior is specifically useful |
| 🟠 **`Vector`** | Primarily when maintaining legacy code that uses it |
| 🔴 **`Stack`** | Avoid for new stack implementations; prefer `Deque` |

### ❓ Does Vector synchronization prevent `ConcurrentModificationException`?

**No.** These are different concepts.

`Vector` synchronizes many individual methods, such as `add()` and `get()`. This does not make a compound operation or an entire iteration automatically thread-safe.

```java
Vector<Integer> numbers =
        new Vector<>(Arrays.asList(1, 2, 3));

for (Integer number : numbers) {
    numbers.add(4); // May throw ConcurrentModificationException
}
```

The iterator is **fail-fast** and may throw `ConcurrentModificationException` when it detects a structural modification outside the iterator's supported operations.

For safe iteration with mutation, consider `Iterator.remove()`, appropriate external synchronization, or a suitable concurrent collection. Fail-fast behavior is a best-effort bug-detection mechanism, not a concurrency guarantee.

[⬆️ Back to top](#top)

---

<a id="s6"></a>

## 6. Stack extends Vector — why is Stack synchronized?

Yes. The `Stack` class extends `Vector`, so it inherits `Vector`'s synchronized method behavior.

```java
Stack<Integer> stack = new Stack<>();

stack.push(10);
stack.push(20);
stack.push(30);

System.out.println(stack.peek()); // 30
System.out.println(stack.pop());  // 30
System.out.println(stack.pop());  // 20
```

`Stack` follows **LIFO**: Last In, First Out.

| Method | Purpose |
|---|---|
| `push(e)` | Add an element to the top |
| `pop()` | Remove and return the top element |
| `peek()` | Read the top without removing it |
| `empty()` | Check whether the stack is empty |
| `search(o)` | Return the 1-based position from the top, or `-1` |

### Why prefer `Deque` over `Stack`?

The modern recommendation is to use `Deque`, commonly implemented by `ArrayDeque`.

```java
Deque<Integer> stack = new ArrayDeque<>();

stack.push(10);
stack.push(20);
stack.push(30);

System.out.println(stack.peek()); // 30
System.out.println(stack.pop());  // 30
```

**Reasons:**

1. `ArrayDeque` is designed for stack and queue operations.
2. It avoids the legacy `Stack` inheritance design.
3. It is generally efficient for end operations.
4. It does not synchronize every operation unnecessarily in single-threaded use.

> [!WARNING]
> **Important:** `ArrayDeque` does not permit `null` elements. Also, neither `Stack` nor `ArrayDeque` automatically makes a multi-step sequence of operations atomic across threads.

[⬆️ Back to top](#top)

---

<a id="s7"></a>

## 7. Sorting a List — `sort()`

The `List` interface provides `sort(Comparator)`.

```java
List<Integer> numbers =
        new ArrayList<>(Arrays.asList(40, 10, 30, 20));

numbers.sort(null);

System.out.println(numbers);
// [10, 20, 30, 40]
```

Passing `null` uses the elements' **natural ordering**.

### Descending order

```java
numbers.sort(Comparator.reverseOrder());
```

### Sorting strings

```java
List<String> names =
        new ArrayList<>(Arrays.asList("Ravi", "Anu", "Kiran"));

names.sort(null);
System.out.println(names);
// [Anu, Kiran, Ravi]
```

### Sorting custom objects

```java
employees.sort(Comparator.comparing(Employee::getSalary));
```

This sorts by salary in ascending order, assuming `Employee` has a `getSalary()` method.

> [!IMPORTANT]
> **🟣 Interview point:** `Collections.sort(list)` is another common way to sort a list. For ordinary lists, it delegates to the list's sorting mechanism.

[⬆️ Back to top](#top)

---

<a id="s8"></a>

## 8. Converting ArrayList to Array and Array to ArrayList

These are common Java interview questions. Remember the distinction between **object arrays**, **primitive arrays**, and **fixed-size list views**.

### 🅰️ ArrayList to array

Use `toArray()`.

```java
List<String> list =
        new ArrayList<>(Arrays.asList("Java", "SQL", "Python"));

Object[] array1 = list.toArray();

String[] array2 = list.toArray(new String[0]);

System.out.println(Arrays.toString(array2));
// [Java, SQL, Python]
```

**Which approach should you use?**

`toArray(new String[0])` is a common, type-safe way to obtain a `String[]`. The no-argument version returns `Object[]`.

### 🅱️ Array to ArrayList

For a reference-type array:

```java
String[] array = {"Java", "SQL", "Python"};

List<String> list = new ArrayList<>(Arrays.asList(array));

list.add("Spring"); // Allowed

System.out.println(list);
// [Java, SQL, Python, Spring]
```

**Why create an `ArrayList` around `Arrays.asList()`?** Because `Arrays.asList()` returns a **fixed-size** list backed by the original array.

```java
List<String> list = Arrays.asList(array);

list.set(0, "Spring"); // Allowed
list.add("Java");      // UnsupportedOperationException
```

### 🅲 Primitive arrays — a common trap

```java
int[] numbers = {10, 20, 30};

List<int[]> list1 = Arrays.asList(numbers);
```

> [!CAUTION]
> This creates a list with **one element**: the entire `int[]` array. It does **not** create a `List<Integer>`.

For primitive integers, use a loop or a stream:

```java
List<Integer> list2 = Arrays.stream(numbers)
        .boxed()
        .collect(Collectors.toList());
```

### 🅳 Create and initialize an ArrayList in one line

```java
List<String> names =
        new ArrayList<>(Arrays.asList("Anu", "Ravi", "Kiran"));
```

For an immutable list in modern Java:

```java
List<String> names = List.of("Anu", "Ravi", "Kiran");
```

`List.of()` returns an unmodifiable list and rejects `null` elements.

### 🅴 Create an ArrayList using the List interface

```java
List<Integer> numbers = new ArrayList<>();
```

This is preferred over:

```java
ArrayList<Integer> numbers = new ArrayList<>();
```

when you do not need `ArrayList`-specific methods. It allows you to change the implementation with fewer code changes.

### 🅵 Convert an ArrayList to a String

```java
List<String> names =
        Arrays.asList("Anu", "Ravi", "Kiran");

String result = String.join(", ", names);

System.out.println(result);
// Anu, Ravi, Kiran
```

For a generic list:

```java
List<Integer> numbers = Arrays.asList(10, 20, 30);

String result = numbers.stream()
        .map(String::valueOf)
        .collect(Collectors.joining(", "));

System.out.println(result);
// 10, 20, 30
```

> **Remember:** `list.toString()` returns a display representation such as `[10, 20, 30]`; it does not perform a custom conversion or serialization.

[⬆️ Back to top](#top)

---

<a id="s9"></a>

## 9. `clear()` vs `removeAll()`

This is an important interview comparison.

| Feature | `clear()` | `removeAll(collection)` |
|---|---|---|
| **Purpose** | Remove every element | Remove elements also present in another collection |
| **Argument** | None | A collection |
| **Return type** | `void` | `boolean` |
| **Example** | `list.clear()` | `list.removeAll(other)` |

### Example

```java
List<Integer> numbers =
        new ArrayList<>(Arrays.asList(10, 20, 30, 40));

numbers.removeAll(Arrays.asList(20, 40));

System.out.println(numbers);
// [10, 30]

numbers.clear();

System.out.println(numbers);
// []
```

`removeAll()` removes every occurrence of an element if it matches an element in the supplied collection. It returns `true` if the list changed.

> [!WARNING]
> **🪤 Interview trap:** `clear()` does not guarantee that the backing array capacity is reduced. An `ArrayList` may retain its allocated storage for reuse. Use `trimToSize()` if you explicitly want to request capacity reduction.

[⬆️ Back to top](#top)

---

<a id="s10"></a>

## 10. `retainAll()` — keep common elements

`retainAll()` removes elements from the calling collection that are **not** present in the supplied collection.

```java
List<Integer> numbers =
        new ArrayList<>(Arrays.asList(10, 20, 30, 40));

numbers.retainAll(Arrays.asList(20, 40, 60));

System.out.println(numbers);
// [20, 40]
```

Use it when you need the **intersection** of two collections, while preserving the calling list's order and any matching duplicates.

**Compare:**

| Method | Behavior |
|---|---|
| `removeAll()` | 🔴 Remove matching elements |
| `retainAll()` | 🟢 Keep matching elements |

[⬆️ Back to top](#top)

---

<a id="s11"></a>

## 11. `ensureCapacity()` and `trimToSize()`

These methods are specific to `ArrayList`, **not** methods of the `List` interface.

### `ensureCapacity()`

Requests enough internal capacity to accommodate a specified number of elements without requiring additional growth before that capacity is reached.

```java
ArrayList<Integer> numbers = new ArrayList<>();

numbers.ensureCapacity(1000);

for (int i = 0; i < 1000; i++) {
    numbers.add(i);
}
```

**Advantage:** Can reduce repeated internal resizing and copying when you know in advance that many elements will be added.

It does **not** change `size()`; the list is still empty until you add elements.

### `trimToSize()`

Requests that the backing array capacity be reduced to the current number of elements.

```java
ArrayList<Integer> numbers =
        new ArrayList<>(Arrays.asList(10, 20, 30));

numbers.trimToSize();
```

**Advantage:** Can reduce unused backing-array memory.

**Trade-off:** If you add many more elements later, the list may need to grow again.

> [!NOTE]
> **Interview note:** both methods are capacity-management optimizations. Use them when the workload justifies them, not automatically on every list.

[⬆️ Back to top](#top)

---

<a id="s12"></a>

## 12. `subList(start, end)` — a view, not an independent list

`subList(fromIndex, toIndex)` returns a **view** of a range of the original list.

- The start index is **inclusive**.
- The end index is **exclusive**.

```java
List<Integer> numbers =
        new ArrayList<>(Arrays.asList(10, 20, 30, 40, 50));

List<Integer> sub = numbers.subList(1, 4);

System.out.println(sub);
// [20, 30, 40]
```

### Important behavior

```java
sub.set(0, 99);

System.out.println(numbers);
// [10, 99, 30, 40, 50]
```

Changing the view changes the original list.

If you want an **independent** list:

```java
List<Integer> copy =
        new ArrayList<>(numbers.subList(1, 4));
```

This creates a separate list structure, although the element objects themselves are still shared.

> [!WARNING]
> **🪤 Interview trap:** Structural modifications to the original list outside the returned view can make further use of the view undefined by the `List` contract, commonly resulting in `ConcurrentModificationException` for `ArrayList`-backed sublists.

[⬆️ Back to top](#top)

---

<a id="s13"></a>

## 13. `replaceAll()` and `removeIf()`

Both methods accept a functional interface and are useful with lambdas.

### 🅰️ `replaceAll()`

Applies an operator to every element and replaces the list elements with the results.

```java
List<String> names =
        new ArrayList<>(Arrays.asList("java", "sql", "spring"));

names.replaceAll(e -> e.toUpperCase());

System.out.println(names);
// [JAVA, SQL, SPRING]
```

The original list is modified.

You can also use a method reference:

```java
names.replaceAll(String::toLowerCase);
```

### 🅱️ `removeIf()`

Removes all elements that satisfy the predicate.

```java
List<Integer> numbers =
        new ArrayList<>(Arrays.asList(10, 15, 20, 25, 30));

numbers.removeIf(e -> e % 2 == 0);

System.out.println(numbers);
// [15, 25]
```

Here, every even number is removed.

| Method | Functional interface | Purpose |
|---|---|---|
| `replaceAll()` | `UnaryOperator<E>` | Transform each element |
| `removeIf()` | `Predicate<E>` | Remove elements that match a condition |

> [!IMPORTANT]
> **🟣 Interview point:** `replaceAll()` changes elements but does **not** change the list's size. `removeIf()` can reduce the size of the list.

[⬆️ Back to top](#top)

---

<a id="s14"></a>

## 14. `forEach()` — why does changing `e` not update the list?

Consider your example:

```java
List<Integer> numbers =
        new ArrayList<>(Arrays.asList(10, 20, 30));

numbers.forEach((e) -> {
    e = e * 10;
    System.out.print(e + " ");
});
```

**Output:**

```text
100 200 300
```

But the original list remains:

```text
[10, 20, 30]
```

### ❓ Why?

The lambda parameter `e` receives the current element **value**. Reassigning `e` only changes the **local parameter**; it does not replace the element stored in the list.

### 🟢 To update the elements, use `replaceAll()`

```java
numbers.replaceAll(e -> e * 10);

System.out.println(numbers);
// [100, 200, 300]
```

**Remember the difference:**

| Method | What it does |
|---|---|
| `forEach()` | Performs an action for each element |
| `replaceAll()` | Transforms and replaces the elements |
| `removeIf()` | Removes elements that match a condition |

> [!IMPORTANT]
> This distinction is frequently tested in Java interviews.

[⬆️ Back to top](#top)

---

<a id="s15"></a>

## 15. `clone()`, `Cloneable`, shallow copy and deep copy

### What is `Cloneable`?

`Cloneable` is a **marker interface**. It does not declare a `clone()` method; it signals that `Object.clone()` is permitted to make a field-by-field copy of an instance.

The `Object.clone()` method is `protected`. Classes such as `ArrayList` expose a public `clone()` method.

### Example: cloning an ArrayList

```java
ArrayList<Integer> original =
        new ArrayList<>(Arrays.asList(10, 20, 30));

ArrayList<Integer> copy =
        (ArrayList<Integer>) original.clone();

copy.add(40);

System.out.println(original); // [10, 20, 30]
System.out.println(copy);     // [10, 20, 30, 40]
```

The two lists have separate list structures. Adding an element to one does not add it to the other.

### 🟡 Shallow copy

A shallow copy creates a new container but does **not** recursively copy its elements. Both lists reference the **same element objects**.

```java
class Employee {
    String name;

    Employee(String name) {
        this.name = name;
    }
}
```

```java
ArrayList<Employee> original = new ArrayList<>();
original.add(new Employee("Ravi"));

ArrayList<Employee> copy =
        (ArrayList<Employee>) original.clone();

copy.get(0).name = "Anu";

System.out.println(original.get(0).name); // Anu
```

**Why did the original change?** Both lists refer to the same `Employee` object.

```text
Shallow copy: shared elements

  Original list ──┐
                  ├──► [ Shared Employee object ]
  Cloned list  ───┘
```

### 🟢 Deep copy

A deep copy creates independent copies of the mutable objects as well as the collection structure, to the depth required by the application.

For the simple `Employee` example:

```java
ArrayList<Employee> deepCopy = new ArrayList<>();

for (Employee employee : original) {
    deepCopy.add(new Employee(employee.name));
}

deepCopy.get(0).name = "Kiran";

System.out.println(original.get(0).name); // Anu
System.out.println(deepCopy.get(0).name); // Kiran
```

This is a deep copy for the simple example because `Employee` contains only a `String` reference, and strings are immutable. In a real object graph containing mutable nested objects, those objects would also need copying.

### ⚖️ Shallow vs deep copy

| Feature | 🟡 Shallow copy | 🟢 Deep copy |
|---|---|---|
| **New collection/container** | Yes | Yes |
| **Element objects copied** | No | Yes, as required |
| **Mutable elements shared** | Yes | No, for the objects deeply copied |
| **Cost** | Usually lower | Usually higher |
| **Typical use** | Copy collection structure | Fully independent mutable data |

> [!IMPORTANT]
> **🟣 Interview answer:** `ArrayList.clone()` performs a **shallow copy**, not a deep copy.

> [!TIP]
> Also, `Cloneable` is not a general-purpose copying API. In application code, copy constructors or explicit copy methods are often clearer and safer.

[⬆️ Back to top](#top)

---

<a id="s16"></a>

## 16. `ConcurrentModificationException` (CME)

`ConcurrentModificationException` is **not limited to multiple threads**. It can also happen when you structurally modify a collection while iterating through it using an iterator that does not support that modification.

### ❌ Incorrect approach

```java
List<Integer> numbers =
        new ArrayList<>(Arrays.asList(10, 20, 30));

for (Integer number : numbers) {
    if (number == 20) {
        numbers.remove(number);
    }
}
```

This may throw `ConcurrentModificationException` because the enhanced `for` loop uses an iterator, and the list is modified directly.

### ✅ Correct approach 1: `removeIf()`

```java
numbers.removeIf(number -> number == 20);
```

### ✅ Correct approach 2: Iterator's `remove()`

```java
Iterator<Integer> iterator = numbers.iterator();

while (iterator.hasNext()) {
    Integer number = iterator.next();

    if (number == 20) {
        iterator.remove();
    }
}
```

The iterator's own `remove()` method supports this operation.

### ❓ Interview question: Is `ArrayList` thread-safe?

**No.** `ArrayList` is not synchronized by default.

If multiple threads access and modify a list, you must choose an appropriate concurrency strategy. For example, `Collections.synchronizedList()` provides synchronized wrapper methods, but iteration still requires synchronization on the wrapper.

```java
List<Integer> numbers =
        Collections.synchronizedList(new ArrayList<>());

synchronized (numbers) {
    for (Integer number : numbers) {
        System.out.println(number);
    }
}
```

This is the documented iteration pattern for a synchronized-list wrapper.

[⬆️ Back to top](#top)

---

<a id="s17"></a>

## 17. Additional important List interview questions

### Q1. Does `ArrayList` allow duplicates and `null`?

Yes. An `ArrayList` allows duplicate elements and multiple `null` values.

```java
List<String> values =
        Arrays.asList("Java", "Java", null, null);
```

### Q2. Is `ArrayList` synchronized?

No. Its ordinary methods are not synchronized. Synchronization can be added using a wrapper or another suitable concurrency mechanism.

### Q3. What is the difference between `size()` and capacity?

- `size()` is the number of elements currently stored.
- Capacity is the size of the backing array allocated to store elements.

For example:

```java
ArrayList<Integer> numbers = new ArrayList<>(100);

System.out.println(numbers.size()); // 0
```

The constructor specifies **initial capacity**, not the number of elements.

### Q4. Can we instantiate an interface?

No. You cannot instantiate `List` directly.

```java
List<Integer> numbers = new ArrayList<>(); // Valid

// List<Integer> numbers = new List<>();   // Invalid
```

The interface defines a contract; a concrete implementation creates the object.

### Q5. Why does `ArrayList` generally perform better than `LinkedList` for iteration?

`ArrayList` stores references in a contiguous backing array, which generally provides better cache locality and less per-element memory overhead. `LinkedList` nodes require additional references and pointer traversal.

### Q6. What is the difference between `List.of()` and `Arrays.asList()`?

| Feature | `List.of()` | `Arrays.asList()` |
|---|---|---|
| **Modification** | Unmodifiable | Fixed size; elements can be replaced |
| **Allows null** | No | Yes |
| **Backed by supplied array** | No | Yes, for reference-type arrays |
| **Add/remove** | Unsupported | Unsupported |

[⬆️ Back to top](#top)

---

<a id="s18"></a>

## 18. Interview revision cheat sheet

Use this as your quick revision before interviews.

| Topic | Must-remember answer |
|---|---|
| **List** | Ordered collection that permits duplicates |
| **ArrayList** | Resizable array; fast indexed access |
| **LinkedList** | Doubly linked list; efficient operations at both ends |
| **Vector** | Legacy synchronized resizable array |
| **Stack** | Extends `Vector`; follows LIFO |
| **Preferred stack** | `Deque` with `ArrayDeque` |
| **`clear()`** | Removes all elements |
| **`removeAll()`** | Removes elements present in another collection |
| **`retainAll()`** | Keeps only elements present in another collection |
| **`ensureCapacity()`** | Requests more backing-array capacity |
| **`trimToSize()`** | Requests capacity reduction to the current size |
| **`subList()`** | Returns a backed range view |
| **`replaceAll()`** | Replaces elements using an operator |
| **`removeIf()`** | Removes elements matching a predicate |
| **`forEach()`** | Executes an action; does not replace list elements |
| **`clone()` on ArrayList** | Shallow copy |
| **`Cloneable`** | Marker interface |
| **CME** | Fail-fast iteration detects certain unsupported modifications |
| **ArrayList thread safety** | Not synchronized by default |
| **ArrayList to array** | `list.toArray(new String[0])` |
| **Array to modifiable list** | `new ArrayList<>(Arrays.asList(array))` |

[⬆️ Back to top](#top)

---

<a id="s19"></a>

## 19. Practice questions for interview preparation

Try answering these **without looking at the notes**, then expand the answer to check yourself.

**1. Why is `ArrayList` faster than `LinkedList` for `get(index)`?**

<details>
<summary>Answer</summary>

`ArrayList` stores elements in a contiguous array, so the position can be computed directly: O(1). `LinkedList` must traverse nodes from an end of the list: O(n).

</details>

**2. Why is `Stack` synchronized but `ArrayDeque` is not?**

<details>
<summary>Answer</summary>

`Stack` extends `Vector`, so it inherits `Vector`'s synchronized methods. `ArrayDeque` is designed for stack and queue operations without synchronizing every operation unnecessarily in single-threaded use.

</details>

**3. Does `clone()` create a deep copy of an `ArrayList`?**

<details>
<summary>Answer</summary>

No. `ArrayList.clone()` is a shallow copy: the list structure is new, but both lists reference the same element objects.

</details>

**4. What is the difference between `subList()` and a copied list?**

<details>
<summary>Answer</summary>

`subList()` returns a backed **view**: changes to the view change the original list. A copy such as `new ArrayList<>(numbers.subList(1, 4))` has an independent list structure (though the element objects are still shared).

</details>

**5. Why does assigning `e = e * 10` inside `forEach()` not update the list?**

<details>
<summary>Answer</summary>

The lambda parameter `e` holds the element's value. Reassigning it only changes the local parameter, not the element stored in the list. Use `replaceAll()` to update the elements.

</details>

**6. Does `Vector` prevent `ConcurrentModificationException`?**

<details>
<summary>Answer</summary>

No. `Vector` synchronizes individual methods, but its iterator is still fail-fast and may throw CME if the vector is structurally modified during iteration outside the iterator's supported operations.

</details>

**7. What is the difference between `remove(index)` and `remove(value)` for `List<Integer>`?**

<details>
<summary>Answer</summary>

`remove(int index)` removes the element at that position; `remove(Object o)` removes the matching value. To remove a value, use `remove(Integer.valueOf(30))`.

</details>

**8. Why can you not add elements to `Arrays.asList(array)`?**

<details>
<summary>Answer</summary>

`Arrays.asList()` returns a fixed-size list backed by the original array. Elements can be replaced with `set()`, but `add()` and `remove()` throw `UnsupportedOperationException`.

</details>

---

> [!TIP]
> **Recommended study order:** List fundamentals → ArrayList internals and complexity → LinkedList → Vector and Stack → conversion methods → `subList`, `replaceAll`, and `removeIf` → copying → concurrency and CME.

For interview preparation, prioritize understanding the **reasoning** behind each answer over memorizing every method signature.

[⬆️ Back to top](#top)