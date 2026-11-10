# STACK & QUEUE — INTERVIEW CHEAT SHEET

**Purpose:** Quick revision, pattern recognition, Java implementation and interview preparation.

---

# 1. STACK & QUEUE FUNDAMENTALS

## 1.1 Stack — LIFO

**LIFO = Last In, First Out**

The last inserted element is removed first.

Example:

```text
Push: 10 → 20 → 30

Stack:
    30  ← TOP
    20
    10

Pop(): 30
Peek(): 20
```

### Core operations

| Operation | Purpose | Complexity |
|---|---|---|
| push(x) | Insert element | O(1) |
| pop() | Remove top element | O(1) |
| peek() | Get top element | O(1) |
| isEmpty() | Check empty | O(1) |
| size() | Number of elements | O(1) |

Java:

```java
Stack<Integer> stack = new Stack<>();

stack.push(10);
stack.push(20);
stack.push(30);

stack.pop();       // Removes 30
stack.peek();      // Returns 20
stack.isEmpty();   // false
stack.size();      // 2
```

**Interview tip:** In modern Java, prefer `Deque` with `ArrayDeque` over the legacy `Stack` class for ordinary stack operations.

```java
Deque<Integer> stack = new ArrayDeque<>();

stack.push(10);
stack.push(20);

stack.pop();
stack.peek();
```

## 1.2 Queue — FIFO

**FIFO = First In, First Out**

The first inserted element is removed first.

Example:

```text
Insert: 10 → 20 → 30

FRONT                  REAR
  10     20     30
   ↑              ↑

Poll(): 10
Peek(): 20
```

### Core operations

| Operation | Purpose | Complexity |
|---|---|---|
| offer(x) | Insert at rear | O(1) |
| poll() | Remove from front | O(1) |
| peek() | Get front element | O(1) |
| isEmpty() | Check empty | O(1) |

Java:

```java
Queue<Integer> queue = new ArrayDeque<>();

queue.offer(10);
queue.offer(20);
queue.offer(30);

queue.poll();       // Removes 10
queue.peek();       // Returns 20
queue.isEmpty();    // false
```

**Remember:**

- Stack: `push()`, `pop()`, `peek()`
- Queue: `offer()`, `poll()`, `peek()`

---

# 2. IMPLEMENTATION USING DIFFERENT DATA STRUCTURES

## Pattern recognition

| Problem | Main idea | Trick |
|---|---|---|
| Stack using Array | Maintain TOP | TOP starts at -1 |
| Queue using Array | Maintain FRONT and REAR | Circular array |
| Stack using Queue | Rotate queue elements | New element must come to front |
| Queue using Stack | Two stacks | Input stack + output stack |
| Stack using Linked List | Insert/delete at head | Head acts as TOP |
| Queue using Linked List | Insert at tail, delete at head | Maintain front and rear |

## 2.1 Implement Stack Using Array

**Logic:** Maintain a `top` variable.

- Initially `top = -1`.
- Push: increment top and insert.
- Pop: return element and decrement top.
- Peek: return `arr[top]`.

```java
class StackArray {

    int[] arr;
    int top;
    int capacity;

    StackArray(int size) {
        arr = new int[size];
        capacity = size;
        top = -1;
    }

    void push(int x) {

        if (top == capacity - 1) {
            throw new RuntimeException("Stack Overflow");
        }

        arr[++top] = x;
    }

    int pop() {

        if (top == -1) {
            throw new RuntimeException("Stack Underflow");
        }

        return arr[top--];
    }

    int peek() {

        if (top == -1) {
            throw new RuntimeException("Stack Empty");
        }

        return arr[top];
    }

    boolean isEmpty() {
        return top == -1;
    }
}
```

**Easy trick:** Stack array implementation = `top = -1`, push `++top`, pop `top--`.

Complexity: All operations O(1).

## 2.2 Implement Queue Using Array

A simple linear queue can waste space after removals. Use a **Circular Queue** to reuse array positions.

### Circular queue logic

- `front`: Index of first element.
- `rear`: Index of last element.
- `size`: Current number of elements.
- Next rear position: `(rear + 1) % capacity`.

```java
class CircularQueue {

    int[] arr;
    int front, rear, size, capacity;

    CircularQueue(int capacity) {
        this.capacity = capacity;
        arr = new int[capacity];

        front = 0;
        rear = -1;
        size = 0;
    }

    void offer(int x) {

        if (size == capacity) {
            throw new RuntimeException("Queue Full");
        }

        rear = (rear + 1) % capacity;
        arr[rear] = x;
        size++;
    }

    int poll() {

        if (size == 0) {
            throw new RuntimeException("Queue Empty");
        }

        int value = arr[front];

        front = (front + 1) % capacity;
        size--;

        if (size == 0) {
            front = 0;
            rear = -1;
        }

        return value;
    }

    int peek() {

        if (size == 0) {
            throw new RuntimeException("Queue Empty");
        }

        return arr[front];
    }

    boolean isEmpty() {
        return size == 0;
    }
}
```

**Easy trick:** Circular queue = modulo `% capacity`.

Complexity: O(1) for all operations.

## 2.3 Implement Stack Using Queue

**Logic:** Make the newest inserted element available at the front of the queue.

Using one queue:

1. Insert the new element.
2. Rotate all previous elements behind it.
3. Remove from the front during pop.

```java
class StackUsingQueue {

    Queue<Integer> queue = new ArrayDeque<>();

    void push(int x) {

        queue.offer(x);

        int size = queue.size();

        for (int i = 0; i < size - 1; i++) {
            queue.offer(queue.poll());
        }
    }

    int pop() {
        return queue.remove();
    }

    int top() {
        return queue.element();
    }

    boolean isEmpty() {
        return queue.isEmpty();
    }
}
```

Example:

```text
Push 10 → [10]
Push 20 → [20, 10]
Push 30 → [30, 20, 10]

Pop() → 30
```

Complexity:

- Push: O(n)
- Pop: O(1)
- Top: O(1)

**Easy trick:** New element always comes to the front.

## 2.4 Implement Queue Using Stack

**Logic:** Use two stacks.

- `input`: Handles incoming elements.
- `output`: Handles outgoing elements.

Transfer elements from input to output only when output is empty.

```java
class QueueUsingStack {

    Stack<Integer> input = new Stack<>();
    Stack<Integer> output = new Stack<>();

    void offer(int x) {
        input.push(x);
    }

    int poll() {

        if (input.isEmpty() && output.isEmpty()) {
            throw new RuntimeException("Queue Empty");
        }

        if (output.isEmpty()) {
            while (!input.isEmpty()) {
                output.push(input.pop());
            }
        }

        return output.pop();
    }

    int peek() {

        if (output.isEmpty()) {
            while (!input.isEmpty()) {
                output.push(input.pop());
            }
        }

        if (output.isEmpty()) {
            throw new RuntimeException("Queue Empty");
        }

        return output.peek();
    }

    boolean isEmpty() {
        return input.isEmpty() && output.isEmpty();
    }
}
```

Example:

```text
Offer 10, 20, 30

Input:  [30, 20, 10]
Output: []

Transfer:

Input:  []
Output: [10, 20, 30]

Poll() → 10
Poll() → 20
```

Complexity:

- Offer: O(1)
- Poll: O(1) amortized
- Peek: O(1) amortized

**Easy trick:** Transfer only when output stack is empty. Each element moves at most once from input to output.

## 2.5 Implement Stack Using Linked List

**Logic:** Head acts as TOP.

- Push: Insert at head.
- Pop: Delete head.
- Peek: Return head data.

```java
class StackUsingLL {

    Node head;

    void push(int x) {

        Node newNode = new Node(x);

        newNode.next = head;
        head = newNode;
    }

    int pop() {

        if (head == null) {
            throw new RuntimeException("Stack Empty");
        }

        int value = head.data;
        head = head.next;

        return value;
    }

    int peek() {

        if (head == null) {
            throw new RuntimeException("Stack Empty");
        }

        return head.data;
    }

    boolean isEmpty() {
        return head == null;
    }
}
```

Complexity: O(1) for all operations.

## 2.6 Implement Queue Using Linked List

**Logic:** Maintain two references:

- `front`: First node.
- `rear`: Last node.

```java
class QueueUsingLL {

    Node front, rear;

    void offer(int x) {

        Node newNode = new Node(x);

        if (rear == null) {
            front = rear = newNode;
            return;
        }

        rear.next = newNode;
        rear = newNode;
    }

    int poll() {

        if (front == null) {
            throw new RuntimeException("Queue Empty");
        }

        int value = front.data;

        front = front.next;

        if (front == null) {
            rear = null;
        }

        return value;
    }

    int peek() {

        if (front == null) {
            throw new RuntimeException("Queue Empty");
        }

        return front.data;
    }

    boolean isEmpty() {
        return front == null;
    }
}
```

**Important:** When the last node is removed, update both `front` and `rear` to null.

Complexity: O(1) for all operations.

---

# 3. BALANCED PARENTHESES

**Recognition:** Questions involving matching brackets, opening/closing symbols, and valid expression structures.

Brackets:

- `()`
- `{}`
- `[]`

### Logic

1. If opening bracket → Push into stack.
2. If closing bracket → Check stack top.
3. If matching → Pop.
4. If not matching → Invalid.
5. At the end, stack must be empty.

```java
boolean isValid(String s) {

    Stack<Character> stack = new Stack<>();

    for (char ch : s.toCharArray()) {

        if (ch == '(' || ch == '{' || ch == '[') {
            stack.push(ch);
        }
        else {

            if (stack.isEmpty()) {
                return false;
            }

            char top = stack.pop();

            if (ch == ')' && top != '(') return false;
            if (ch == '}' && top != '{') return false;
            if (ch == ']' && top != '[') return false;
        }
    }

    return stack.isEmpty();
}
```

Time: O(n) | Space: O(n)

**Easy trick:** Opening → Push, Closing → Match and Pop.

---

# 4. PREFIX, INFIX & POSTFIX CONVERSIONS

This is one of the most important Stack interview topics.

## 4.1 Understand the Three Notations

Consider:

`A + B * C`

### Infix

Operator is between operands.

```text
A + B * C
```

### Prefix (Polish Notation)

Operator comes before operands.

```text
+ A * B C
```

### Postfix (Reverse Polish Notation)

Operator comes after operands.

```text
A B C * +
```

## 4.2 Operator Precedence

| Operator | Precedence | Associativity |
|---|---:|---|
| `^` | 3 | Right to Left |
| `*`, `/`, `%` | 2 | Left to Right |
| `+`, `-` | 1 | Left to Right |

Parentheses have the highest grouping priority.

**Easy trick:** Higher precedence operators are evaluated first.

## 4.3 Conversion Recognition Table

| Conversion | Main approach | Traversal |
|---|---|---|
| Infix → Postfix | Operator stack | Left to Right |
| Infix → Prefix | Reverse + swap brackets + postfix | Right to Left processing after reversal |
| Postfix → Infix | Operand stack | Left to Right |
| Prefix → Infix | Operand stack | Right to Left |
| Postfix → Prefix | Operand stack | Left to Right |
| Prefix → Postfix | Operand stack | Right to Left |

## 4.4 Infix to Postfix

Example:

```text
Infix:   A + B * C
Postfix: A B C * +
```

### Logic

1. Operand → Add directly to output.
2. `(` → Push.
3. `)` → Pop until `(`.
4. Operator → Pop higher-precedence operators.
5. Push current operator.
6. At the end, pop remaining operators.

```java
String infixToPostfix(String s) {

    Stack<Character> stack = new Stack<>();
    StringBuilder result = new StringBuilder();

    for (char ch : s.toCharArray()) {

        if (Character.isLetterOrDigit(ch)) {
            result.append(ch);
        }

        else if (ch == '(') {
            stack.push(ch);
        }

        else if (ch == ')') {

            while (!stack.isEmpty() && stack.peek() != '(') {
                result.append(stack.pop());
            }

            stack.pop();
        }

        else {

            while (!stack.isEmpty()
                    && stack.peek() != '('
                    && (precedence(stack.peek()) > precedence(ch)
                    || (precedence(stack.peek()) == precedence(ch)
                    && ch != '^'))) {

                result.append(stack.pop());
            }

            stack.push(ch);
        }
    }

    while (!stack.isEmpty()) {
        result.append(stack.pop());
    }

    return result.toString();
}
```

```java
int precedence(char ch) {

    if (ch == '^') return 3;
    if (ch == '*' || ch == '/' || ch == '%') return 2;
    if (ch == '+' || ch == '-') return 1;

    return -1;
}
```

Time: O(n) | Space: O(n)

## 4.5 Infix to Prefix

**Easy trick:** Reverse → Swap brackets → Infix to Postfix → Reverse result.

Example:

```text
Infix:  A+B*C
Prefix: +A*BC
```

```java
String infixToPrefix(String s) {

    StringBuilder reversed = new StringBuilder(s).reverse();

    for (int i = 0; i < reversed.length(); i++) {

        if (reversed.charAt(i) == '(') {
            reversed.setCharAt(i, ')');
        }
        else if (reversed.charAt(i) == ')') {
            reversed.setCharAt(i, '(');
        }
    }

    String postfix = infixToPostfixForPrefix(reversed.toString());

    return new StringBuilder(postfix).reverse().toString();
}
```

**Important:** The infix-to-postfix helper used here must handle equal-precedence operators with reversed associativity:

- While processing the reversed expression, pop on equal precedence for `^`.
- Do not pop on equal precedence for `+`, `-`, `*`, `/`, `%`.

This is different from the normal infix-to-postfix condition.

## 4.6 Postfix to Infix

Example:

```text
Postfix: AB+C*
Infix:   (A+B)*C
```

### Logic

1. Scan left to right.
2. Operand → Push.
3. Operator → Pop two operands.
4. Build `(operand1 operator operand2)`.
5. Push result.

```java
String postfixToInfix(String s) {

    Stack<String> stack = new Stack<>();

    for (char ch : s.toCharArray()) {

        if (Character.isLetterOrDigit(ch)) {
            stack.push(String.valueOf(ch));
        }
        else {

            String b = stack.pop();
            String a = stack.pop();

            stack.push("(" + a + ch + b + ")");
        }
    }

    return stack.pop();
}
```

## 4.7 Prefix to Infix

Example:

```text
Prefix: *+ABC
Infix:  ((A+B)*C)
```

### Logic

1. Scan right to left.
2. Operand → Push.
3. Operator → Pop two operands.
4. Build `(operand1 operator operand2)`.
5. Push result.

```java
String prefixToInfix(String s) {

    Stack<String> stack = new Stack<>();

    for (int i = s.length() - 1; i >= 0; i--) {

        char ch = s.charAt(i);

        if (Character.isLetterOrDigit(ch)) {
            stack.push(String.valueOf(ch));
        }
        else {

            String a = stack.pop();
            String b = stack.pop();

            stack.push("(" + a + ch + b + ")");
        }
    }

    return stack.pop();
}
```

## 4.8 Postfix to Prefix

Example:

```text
Postfix: AB+C*
Prefix:  *+ABC
```

```java
String postfixToPrefix(String s) {

    Stack<String> stack = new Stack<>();

    for (char ch : s.toCharArray()) {

        if (Character.isLetterOrDigit(ch)) {
            stack.push(String.valueOf(ch));
        }
        else {

            String b = stack.pop();
            String a = stack.pop();

            stack.push(ch + a + b);
        }
    }

    return stack.pop();
}
```

## 4.9 Prefix to Postfix

Example:

```text
Prefix:  *+ABC
Postfix: AB+C*
```

```java
String prefixToPostfix(String s) {

    Stack<String> stack = new Stack<>();

    for (int i = s.length() - 1; i >= 0; i--) {

        char ch = s.charAt(i);

        if (Character.isLetterOrDigit(ch)) {
            stack.push(String.valueOf(ch));
        }
        else {

            String a = stack.pop();
            String b = stack.pop();

            stack.push(a + b + ch);
        }
    }

    return stack.pop();
}
```

## 4.10 Conversion Quick Revision

| Conversion | Scan direction | Operator handling |
|---|---|---|
| Infix → Postfix | Left → Right | Operator stack |
| Infix → Prefix | Reverse, then process | Reverse precedence/associativity |
| Postfix → Infix | Left → Right | Pop B, then A |
| Prefix → Infix | Right → Left | Pop A, then B |
| Postfix → Prefix | Left → Right | `operator + A + B` |
| Prefix → Postfix | Right → Left | `A + B + operator` |

**Important interview limitation:** These compact conversion implementations assume single-character operands and binary operators. For multi-digit numbers, identifiers, unary operators, or whitespace-separated expressions, tokenize the expression first. Always validate stack size and the final number of operands in production-quality implementations.

---

# 5. MONOTONIC STACK — MOST IMPORTANT PATTERN

This is the highest-value pattern in your Stack & Queue problem list.

## 5.1 What is a Monotonic Stack?

A stack that maintains elements in a particular order.

Two types:

| Type | Stack order (bottom → top) | Used for |
|---|---|---|
| Monotonic Increasing | Increasing | Next Smaller Element |
| Monotonic Decreasing | Decreasing | Next Greater Element |

**Easy trick:**

- Greater element questions → Maintain decreasing stack.
- Smaller element questions → Maintain increasing stack.

More precisely, choose the pop condition based on the required answer and equality handling.

## 5.2 Next Greater Element (NGE)

**Recognition:** Find the first element greater than the current element on its right.

Example:

```text
Array:       4  5  2  10  8
Next Greater:5 10 10 -1 -1
```

### Logic

1. Traverse from right to left.
2. Remove all elements smaller than or equal to current.
3. Stack top is the next greater element.
4. If stack empty → -1.
5. Push current element.

```java
int[] nextGreater(int[] arr) {

    int n = arr.length;
    int[] ans = new int[n];

    Stack<Integer> stack = new Stack<>();

    for (int i = n - 1; i >= 0; i--) {

        while (!stack.isEmpty() && stack.peek() <= arr[i]) {
            stack.pop();
        }

        ans[i] = stack.isEmpty() ? -1 : stack.peek();

        stack.push(arr[i]);
    }

    return ans;
}
```

Time: O(n) | Space: O(n)

**Why O(n)?** Every element enters and leaves the stack at most once.

## 5.3 Next Smaller Element (NSE)

**Recognition:** Find the first smaller element on the right.

Example:

```text
Array:       4  8  2  10  6
Next Smaller:-1  2 -1  6 -1
```

```java
int[] nextSmaller(int[] arr) {

    int n = arr.length;
    int[] ans = new int[n];

    Stack<Integer> stack = new Stack<>();

    for (int i = n - 1; i >= 0; i--) {

        while (!stack.isEmpty() && stack.peek() >= arr[i]) {
            stack.pop();
        }

        ans[i] = stack.isEmpty() ? -1 : stack.peek();

        stack.push(arr[i]);
    }

    return ans;
}
```

Time: O(n) | Space: O(n)

## 5.4 Next Greater Element II — Circular Array

**Recognition:** The array is circular. Elements at the beginning can be greater elements for positions near the end.

Example:

```text
Array: [1, 2, 1]
Answer:[2,-1, 2]
```

**Easy trick:** Traverse the array twice using modulo `% n`.

```java
int[] nextGreaterElements(int[] arr) {

    int n = arr.length;
    int[] ans = new int[n];

    Arrays.fill(ans, -1);

    Stack<Integer> stack = new Stack<>();

    for (int i = 2 * n - 1; i >= 0; i--) {

        int index = i % n;

        while (!stack.isEmpty() && stack.peek() <= arr[index]) {
            stack.pop();
        }

        if (i < n && !stack.isEmpty()) {
            ans[index] = stack.peek();
        }

        stack.push(arr[index]);
    }

    return ans;
}
```

Time: O(n) | Space: O(n)

## 5.5 Asteroid Collision

**Recognition:** Positive and negative values represent moving asteroids.

- Positive → Moving right.
- Negative → Moving left.
- Collision happens when a positive asteroid is on the left and a negative asteroid is on the right.

Example:

```text
Input:  [5, 10, -5]
Output: [5, 10]
```

### Logic

Use stack to maintain surviving asteroids.

Collision condition:

`stack.peek() > 0 && current < 0`

```java
int[] asteroidCollision(int[] asteroids) {

    Stack<Integer> stack = new Stack<>();

    for (int asteroid : asteroids) {

        boolean alive = true;

        while (alive && asteroid < 0
                && !stack.isEmpty() && stack.peek() > 0) {

            if (stack.peek() < -asteroid) {
                stack.pop();
            }
            else if (stack.peek() == -asteroid) {
                stack.pop();
                alive = false;
            }
            else {
                alive = false;
            }
        }

        if (alive) {
            stack.push(asteroid);
        }
    }

    int[] ans = new int[stack.size()];

    for (int i = ans.length - 1; i >= 0; i--) {
        ans[i] = stack.pop();
    }

    return ans;
}
```

Time: O(n) | Space: O(n)

**Easy trick:** Only `positive → negative` can collide.

## 5.6 Sum of Subarray Minimums

**Recognition:** Find the sum of minimum elements of every possible subarray.

Example:

```text
Array: [3, 1, 2, 4]

Subarrays:
[3]       → 3
[1]       → 1
[2]       → 2
[4]       → 4
[3,1]     → 1
[1,2]     → 1
[2,4]     → 2
[3,1,2]   → 1
[1,2,4]   → 1
[3,1,2,4] → 1

Answer = 17
```

### Main trick: Contribution technique

Instead of generating every subarray, calculate how many subarrays consider each element as their minimum.

For each element:

```text
Contribution = arr[i] × leftChoices × rightChoices
```

Where:

- `leftChoices` = Distance to previous smaller element.
- `rightChoices` = Distance to next smaller or equal element.

For duplicates, use asymmetric comparisons to avoid counting the same subarray multiple times.

One valid convention:

- Previous Smaller Element (strictly smaller): pop `>=`.
- Next Smaller or Equal Element: pop `>`.

```java
int sumSubarrayMins(int[] arr) {

    int n = arr.length;
    long mod = 1_000_000_007L;

    int[] left = new int[n];
    int[] right = new int[n];

    Stack<Integer> stack = new Stack<>();

    // Previous strictly smaller
    for (int i = 0; i < n; i++) {

        while (!stack.isEmpty() && arr[stack.peek()] >= arr[i]) {
            stack.pop();
        }

        left[i] = stack.isEmpty() ? i + 1 : i - stack.peek();

        stack.push(i);
    }

    stack.clear();

    // Next smaller or equal
    for (int i = n - 1; i >= 0; i--) {

        while (!stack.isEmpty() && arr[stack.peek()] > arr[i]) {
            stack.pop();
        }

        right[i] = stack.isEmpty() ? n - i : stack.peek() - i;

        stack.push(i);
    }

    long ans = 0;

    for (int i = 0; i < n; i++) {
        ans = (ans + (long) arr[i] * left[i] * right[i]) % mod;
    }

    return (int) ans;
}
```

Time: O(n) | Space: O(n)

## 5.7 Sum of Subarray Ranges

**Recognition:** For every subarray, calculate `maximum - minimum`, then sum all ranges.

### Formula

```text
Sum of Subarray Ranges
    = Sum of all subarray maximums
    - Sum of all subarray minimums
```

Use contribution technique twice:

- Minimum contribution → Previous Smaller + Next Smaller.
- Maximum contribution → Previous Greater + Next Greater.

```text
Contribution of minimum:
arr[i] × leftMin × rightMin

Contribution of maximum:
arr[i] × leftMax × rightMax

Answer:
TotalMaximum - TotalMinimum
```

**Easy trick:** If a question asks for sum of subarray minimums AND maximums, think contribution technique instead of generating subarrays.

Time: O(n) | Space: O(n)

For duplicates, use consistent asymmetric strict/non-strict comparisons for each pair of boundaries.

## 5.8 Remove K Digits

**Recognition:** Remove K digits to obtain the smallest possible number.

Example:

```text
Number = 1432219
K = 3

Answer = 1219
```

### Logic

Use a monotonic increasing stack.

1. Traverse digits from left to right.
2. If current digit is smaller than stack top, remove the larger digit.
3. Continue until K removals are completed.
4. If removals remain, remove from the end.
5. Remove leading zeros.

```java
String removeKdigits(String num, int k) {

    Deque<Character> stack = new ArrayDeque<>();

    for (char digit : num.toCharArray()) {

        while (k > 0 && !stack.isEmpty()
                && stack.peekLast() > digit) {

            stack.removeLast();
            k--;
        }

        stack.addLast(digit);
    }

    while (k > 0 && !stack.isEmpty()) {
        stack.removeLast();
        k--;
    }

    StringBuilder result = new StringBuilder();

    while (!stack.isEmpty()) {
        result.append(stack.removeFirst());
    }

    while (result.length() > 0 && result.charAt(0) == '0') {
        result.deleteCharAt(0);
    }

    return result.length() == 0 ? "0" : result.toString();
}
```

Time: O(n) | Space: O(n)

**Easy trick:** To make the number smaller, remove a larger previous digit when a smaller current digit arrives.

---

# 6. ADVANCED STACK & QUEUE INTERVIEW PROBLEMS

## 6.1 Implement Min Stack

**Recognition:** Stack must support retrieving the minimum element in O(1).

Operations:

- push()
- pop()
- top()
- getMin()

### Approach: Two stacks

- Main stack stores values.
- Min stack stores the minimum at each corresponding position.

```java
class MinStack {

    Stack<Integer> stack = new Stack<>();
    Stack<Integer> minStack = new Stack<>();

    void push(int val) {

        stack.push(val);

        if (minStack.isEmpty()) {
            minStack.push(val);
        }
        else {
            minStack.push(Math.min(val, minStack.peek()));
        }
    }

    void pop() {
        stack.pop();
        minStack.pop();
    }

    int top() {
        return stack.peek();
    }

    int getMin() {
        return minStack.peek();
    }
}
```

Complexity: O(1) for every operation.

**Easy trick:** Every push also pushes the minimum so far.

## 6.2 Sliding Window Maximum

**Recognition:** Find maximum in every window of size K.

Example:

```text
Array: [1, 3, -1, -3, 5, 3, 6, 7]
K = 3

Answer: [3, 3, 5, 5, 6, 7]
```

### Pattern: Monotonic Deque

Maintain indices in decreasing order of their array values.

Logic:

1. Remove indices outside the current window.
2. Remove smaller or equal elements from the rear.
3. Add current index.
4. Once the window is complete, front gives maximum.

```java
int[] maxSlidingWindow(int[] nums, int k) {

    int n = nums.length;
    int[] ans = new int[n - k + 1];

    Deque<Integer> deque = new ArrayDeque<>();

    for (int i = 0; i < n; i++) {

        // Remove expired indices
        while (!deque.isEmpty() && deque.peekFirst() <= i - k) {
            deque.pollFirst();
        }

        // Maintain decreasing values
        while (!deque.isEmpty()
                && nums[deque.peekLast()] <= nums[i]) {

            deque.pollLast();
        }

        deque.offerLast(i);

        if (i >= k - 1) {
            ans[i - k + 1] = nums[deque.peekFirst()];
        }
    }

    return ans;
}
```

Time: O(n) | Space: O(k)

**Easy trick:** Sliding window maximum = Deque + indices + decreasing order.

## 6.3 Trapping Rainwater

**Recognition:** Calculate water trapped between bars.

Example:

```text
Height: [4, 2, 0, 3, 2, 5]
Answer: 9
```

### Formula

```text
Water at index i =
min(maxLeft, maxRight) - height[i]
```

### Approach 1: Prefix and Suffix Maximum

- Calculate maximum to the left.
- Calculate maximum to the right.
- Calculate trapped water at every position.

Time: O(n) | Space: O(n)

### Approach 2: Two Pointers — Optimal

```java
int trap(int[] height) {

    int left = 0;
    int right = height.length - 1;

    int leftMax = 0;
    int rightMax = 0;

    int water = 0;

    while (left < right) {

        if (height[left] <= height[right]) {

            if (height[left] >= leftMax) {
                leftMax = height[left];
            }
            else {
                water += leftMax - height[left];
            }

            left++;
        }
        else {

            if (height[right] >= rightMax) {
                rightMax = height[right];
            }
            else {
                water += rightMax - height[right];
            }

            right--;
        }
    }

    return water;
}
```

Time: O(n) | Space: O(1)

**Easy trick:** Process the side with the smaller boundary. That side's trapped water is already determined.

## 6.4 Largest Rectangle in Histogram

**Recognition:** Find maximum rectangular area using histogram heights.

Example:

```text
Heights: [2, 1, 5, 6, 2, 3]
Answer: 10
```

### Formula

```text
Area = height × width

Width = rightSmallerIndex - leftSmallerIndex - 1
```

Use a monotonic increasing stack of indices.

```java
int largestRectangleArea(int[] heights) {

    int n = heights.length;
    int maxArea = 0;

    Stack<Integer> stack = new Stack<>();

    for (int i = 0; i <= n; i++) {

        int currHeight = (i == n) ? 0 : heights[i];

        while (!stack.isEmpty()
                && heights[stack.peek()] > currHeight) {

            int height = heights[stack.pop()];

            int right = i;
            int left = stack.isEmpty() ? -1 : stack.peek();

            int width = right - left - 1;

            maxArea = Math.max(maxArea, height * width);
        }

        stack.push(i);
    }

    return maxArea;
}
```

Time: O(n) | Space: O(n)

**Easy trick:** When a smaller bar arrives, calculate the area of bars that can no longer extend to the right.

## 6.5 Maximum Rectangle in Binary Matrix

**Recognition:** Find the largest rectangle containing only 1s in a matrix.

### Main pattern

Maximum Rectangle = Largest Rectangle in Histogram + Row-wise heights.

Logic:

1. Consider every matrix row.
2. Maintain cumulative heights of consecutive 1s.
3. For each row, run Largest Rectangle in Histogram.
4. Track maximum area.

```java
int maximalRectangle(char[][] matrix) {

    if (matrix.length == 0) return 0;

    int cols = matrix[0].length;

    int[] heights = new int[cols];

    int maxArea = 0;

    for (int i = 0; i < matrix.length; i++) {

        for (int j = 0; j < cols; j++) {

            if (matrix[i][j] == '1') {
                heights[j]++;
            }
            else {
                heights[j] = 0;
            }
        }

        maxArea = Math.max(maxArea, largestRectangleArea(heights));
    }

    return maxArea;
}
```

Time: O(rows × cols) | Space: O(cols)

**Easy trick:** Convert each row into a histogram and reuse the previous problem's solution.

## 6.6 Stock Span Problem

**Recognition:** For every day, count consecutive previous days where stock price was less than or equal to today's price.

Example:

```text
Prices: [100, 80, 60, 70, 60, 75, 85]

Span:   [1,   1,  1,  2,  1,  4,  6]
```

### Pattern: Previous Greater Element

Use a decreasing stack of indices.

```java
int[] stockSpan(int[] prices) {

    int n = prices.length;
    int[] span = new int[n];

    Stack<Integer> stack = new Stack<>();

    for (int i = 0; i < n; i++) {

        while (!stack.isEmpty()
                && prices[stack.peek()] <= prices[i]) {

            stack.pop();
        }

        span[i] = stack.isEmpty()
                ? i + 1
                : i - stack.peek();

        stack.push(i);
    }

    return span;
}
```

Time: O(n) | Space: O(n)

**Easy trick:** Stock Span = Distance from previous strictly greater element.

## 6.7 Celebrity Problem

**Recognition:** Find a person who:

- Is known by everyone.
- Knows nobody.

If a celebrity exists:

```text
Matrix[i][celebrity] = 1, for i != celebrity

Matrix[celebrity][j] = 0, for j != celebrity
```

### Approach: Two Pointers

1. Start with `top = 0`, `bottom = n - 1`.
2. If top knows bottom → top cannot be celebrity.
3. Otherwise → bottom cannot be celebrity.
4. Verify the remaining candidate.

```java
int celebrity(int[][] mat) {

    int n = mat.length;

    int top = 0;
    int bottom = n - 1;

    while (top < bottom) {

        if (mat[top][bottom] == 1) {
            top++;
        }
        else {
            bottom--;
        }
    }

    int candidate = top;

    for (int i = 0; i < n; i++) {

        if (i == candidate) continue;

        if (mat[candidate][i] == 1 ||
            mat[i][candidate] == 0) {

            return -1;
        }
    }

    return candidate;
}
```

Time: O(n) | Space: O(1)

**Easy trick:** Eliminate candidates first, verify only one candidate at the end.

## 6.8 LRU Cache

**LRU = Least Recently Used**

**Recognition:** Cache with fixed capacity. When full, remove the least recently used item.

Operations:

- get(key)
- put(key, value)

Both should be O(1).

### Data structures

1. HashMap: Key → Node.
2. Doubly Linked List: Maintains usage order.

```text
HEAD ⇄ Most Recently Used ⇄ ... ⇄ Least Recently Used ⇄ TAIL
```

### Logic

- GET: Find node using HashMap, move node to front.
- PUT: Insert/update node at front.
- If capacity exceeded: Remove node before tail and delete its key from HashMap.

**Easy trick:** HashMap gives O(1) lookup + DLL gives O(1) insertion/deletion.

Time: O(1) for get and put.

## 6.9 LFU Cache

**LFU = Least Frequently Used**

**Recognition:** When cache is full, remove the item used the fewest times.

If multiple items have the same frequency, remove the least recently used among them.

### Data structures

| Structure | Purpose |
|---|---|
| `keyToNode` | Find node by key |
| `freqToList` | Maintain DLL for each frequency |
| `minFreq` | Track minimum frequency |

### Logic

1. GET: Increase frequency and move node to the corresponding frequency list.
2. PUT: Insert new node with frequency 1.
3. If full: Remove LRU node from the minimum-frequency list.
4. Update `minFreq`.

**Easy trick:**

- LRU → Least recently used.
- LFU → Least frequently used.
- LRU needs one DLL.
- LFU needs multiple DLLs grouped by frequency + HashMap.

Time: O(1) average for get and put with the standard HashMap + DLL design.

## 6.10 Number of Greater Elements to the Right

**Recognition:** For each query index, count elements strictly greater than the current element to its right.

Example:

```text
Array: [3, 4, 2, 7, 5]

For index 0 (value 3):
Greater elements to right = 4, 7, 5
Count = 3
```

### Important distinction

This problem is not the same as Next Greater Element.

- NGE: Find the first greater element.
- Greater Elements to Right: Count all greater elements.

For arbitrary query indices, use a **Fenwick Tree (BIT) with coordinate compression** or an order-statistics data structure.

Logic:

1. Process from right to left.
2. Query how many already-seen values are greater than current.
3. Insert current value into the frequency structure.

With coordinate compression and Fenwick Tree:

Time: O(n log n) | Space: O(n)

---

# 7. MONOTONIC STACK — QUICK PATTERN TABLE

| Problem | Direction | Pop condition | Main idea |
|---|---|---|---|
| Next Greater Right | Right to Left | `top <= curr` | Find greater |
| Next Smaller Right | Right to Left | `top >= curr` | Find smaller |
| Next Greater Circular | 2n to 0 | `top <= curr` | Circular NGE |
| Stock Span | Left to Right | `top <= curr` | Previous greater |
| Remove K Digits | Left to Right | `top > curr` | Increasing stack |
| Largest Histogram | Left to Right | `top height > curr` | Calculate area |
| Subarray Minimums | Both directions | Boundary-specific | Contribution |
| Subarray Maximums | Both directions | Boundary-specific | Contribution |

**One rule to remember:** Always ask what element invalidates the current stack top. That determines the pop condition.

---

# 8. IMPORTANT DIFFERENCES — INTERVIEW QUESTIONS

## Stack vs Queue

| Stack | Queue |
|---|---|
| LIFO | FIFO |
| Insert/remove at same end | Insert at rear, remove at front |
| push/pop | offer/poll |
| DFS | BFS |

## Stack vs Monotonic Stack

| Normal Stack | Monotonic Stack |
|---|---|
| Maintains insertion order | Maintains sorted order |
| Used for matching brackets | Used for next greater/smaller |
| Expression conversions | Histogram, subarray contribution |

## Stack vs Deque

| Stack | Deque |
|---|---|
| One-end operations | Both-end operations |
| LIFO | Supports both stack and queue |
| push/pop/peek | addFirst/addLast/pollFirst/pollLast |

## LRU vs LFU

| LRU | LFU |
|---|---|
| Least Recently Used | Least Frequently Used |
| Tracks recent access | Tracks access frequency |
| One DLL + HashMap | Frequency DLLs + HashMaps |
| Evicts oldest usage | Evicts least-used item |

## NGE vs Stock Span

| NGE | Stock Span |
|---|---|
| Finds next greater on right | Finds previous greater on left |
| Usually right-to-left | Left-to-right |
| Returns value/index | Returns distance |

---

# 9. JAVA COLLECTIONS QUICK REVISION

| Data structure | Java implementation | Important methods |
|---|---|---|
| Stack | `ArrayDeque` | push, pop, peek |
| Queue | `ArrayDeque` | offer, poll, peek |
| Deque | `ArrayDeque` | offerFirst, offerLast, pollFirst, pollLast |
| Priority Queue | `PriorityQueue` | offer, poll, peek |
| Linked List | `LinkedList` | addFirst, addLast, removeFirst |

### Common declarations

```java
// Stack
Deque<Integer> stack = new ArrayDeque<>();

// Queue
Queue<Integer> queue = new ArrayDeque<>();

// Deque
Deque<Integer> deque = new ArrayDeque<>();

// Min Heap
PriorityQueue<Integer> minHeap = new PriorityQueue<>();

// Max Heap
PriorityQueue<Integer> maxHeap =
        new PriorityQueue<>(Collections.reverseOrder());
```

### Important complexity

| Operation | ArrayDeque | PriorityQueue |
|---|---:|---:|
| Insert | O(1) amortized | O(log n) |
| Remove | O(1) amortized | O(log n) |
| Peek | O(1) | O(1) |

---

# 10. FINAL INTERVIEW PATTERN RECOGNITION SHEET

This is the section to revise just before an interview.

| Question keywords | Immediately think |
|---|---|
| Last in, first out | Stack |
| First in, first out | Queue |
| Circular queue | Modulo |
| Stack using Queue | Rotate elements |
| Queue using Stack | Two stacks |
| Matching brackets | Stack |
| Infix/Postfix/Prefix | Operator or operand stack |
| Next greater element | Monotonic decreasing stack |
| Next smaller element | Monotonic increasing stack |
| Circular next greater | Two traversals + modulo |
| Collision of positive/negative | Stack |
| Sum of subarray minimums | Contribution technique |
| Sum of subarray ranges | Max contribution - Min contribution |
| Smallest number after removing K digits | Monotonic increasing stack |
| Minimum in O(1) | Min Stack |
| Maximum in every window | Monotonic Deque |
| Water between bars | Two pointers / Monotonic Stack |
| Largest histogram rectangle | Previous/Next Smaller |
| Maximum rectangle in matrix | Histogram + Monotonic Stack |
| Consecutive previous smaller prices | Stock Span |
| Knows nobody, known by everyone | Celebrity elimination |
| Least recently used | HashMap + DLL |
| Least frequently used | HashMap + Frequency DLL |
| Count greater elements to right | Fenwick Tree / Order Statistics |

---

# 11. COMPLEXITY QUICK REVISION

| Problem | Time | Space |
|---|---:|---:|
| Stack using Array | O(1) | O(n) |
| Queue using Circular Array | O(1) | O(n) |
| Stack using Queue | Push O(n), Pop O(1) | O(n) |
| Queue using Stack | Amortized O(1) | O(n) |
| Stack using LL | O(1) | O(n) |
| Queue using LL | O(1) | O(n) |
| Balanced Parentheses | O(n) | O(n) |
| All expression conversions | O(n) | O(n) |
| Next Greater / Smaller | O(n) | O(n) |
| Next Greater II | O(n) | O(n) |
| Asteroid Collision | O(n) | O(n) |
| Sum of Subarray Minimums | O(n) | O(n) |
| Sum of Subarray Ranges | O(n) | O(n) |
| Remove K Digits | O(n) | O(n) |
| Min Stack | O(1) per operation | O(n) |
| Sliding Window Maximum | O(n) | O(k) |
| Trapping Rainwater | O(n) | O(1) two pointers |
| Largest Histogram | O(n) | O(n) |
| Maximum Rectangle | O(rows × cols) | O(cols) |
| Stock Span | O(n) | O(n) |
| Celebrity | O(n) | O(1) |
| LRU Cache | O(1) average | O(capacity) |
| LFU Cache | O(1) average | O(capacity) |
| Greater Elements to Right | O(n log n) | O(n) |

---

# 12. COMMON INTERVIEW MISTAKES

1. Stack underflow: Always check empty before pop/peek.
2. Queue underflow: Check empty before poll/remove.
3. Circular queue: Use modulo to wrap indices.
4. Queue using stacks: Transfer only when output is empty.
5. Expression conversion: Respect precedence and associativity.
6. Prefix conversion: Remember to reverse the scan direction.
7. Monotonic stack: Use indices when you need distance or window boundaries.
8. Duplicate values: Handle strict versus non-strict comparisons carefully.
9. Subarray contribution: Use asymmetric boundaries to avoid duplicate counting.
10. Sliding window: Remove expired indices before reading the maximum.
11. Histogram: Calculate width using the previous smaller index and current right boundary.
12. Rainwater: Do not confuse boundary height with water height.
13. LRU: Every successful GET must update recency.
14. LFU: Handle equal frequencies using LRU ordering within that frequency.
15. Celebrity: A candidate must be verified after elimination.

---

# 13. FINAL 15-MINUTE REVISION PLAN

| Time | Revise |
|---|---|
| 0–2 min | Stack, Queue, Deque, implementations |
| 2–4 min | Balanced parentheses + expression conversions |
| 4–7 min | NGE, NSE, Circular NGE, Asteroid Collision |
| 7–9 min | Subarray Minimums, Ranges, Remove K Digits |
| 9–12 min | Min Stack, Sliding Window, Rainwater, Histogram |
| 12–14 min | Stock Span, Celebrity, LRU/LFU |
| 14–15 min | Complexity + pattern recognition |

## FINAL THINGS TO MEMORIZE

**Must-know templates:**

1. Stack using Array.
2. Circular Queue using Array.
3. Queue using Two Stacks.
4. Balanced Parentheses.
5. Infix to Postfix.
6. Postfix to Infix.
7. Prefix to Infix.
8. Next Greater Element.
9. Next Smaller Element.
10. Circular Next Greater.
11. Monotonic Stack Contribution.
12. Sliding Window Maximum.
13. Largest Rectangle in Histogram.
14. Stock Span.
15. Min Stack.

**Advanced problems to understand deeply:**

- Sum of Subarray Minimums.
- Sum of Subarray Ranges.
- Trapping Rainwater.
- Maximum Rectangle.
- LRU Cache.
- LFU Cache.
- Celebrity Problem.

**Golden interview rule:**

Whenever a Stack & Queue question appears, ask:

1. Do I need LIFO or FIFO?
2. Do I need to maintain an increasing/decreasing order?
3. Do I need the nearest greater/smaller element?
4. Do I need to maintain a window?
5. Do I need to calculate the contribution of each element?
6. Can a HashMap + Doubly Linked List provide O(1) operations?

If you can answer these six questions, you can recognize the majority of Stack & Queue interview patterns.