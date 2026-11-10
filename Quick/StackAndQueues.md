# STACK & QUEUE — MASTER INTERVIEW CHEAT SHEET

**Target:** Product-Based Companies | Java | 5+ YOE

---

# 1. FIRST: UNDERSTAND THE FUNDAMENTALS

## 1.1 Stack (LIFO)

**LIFO = Last In, First Out**

The last inserted element is removed first.

Example:

```text
Push: 10 → 20 → 30

Stack:
   30 ← TOP
   20
   10

Pop() → 30
Peek() → 20
```

| Operation | Description | Complexity |
|---|---|---|
| push(x) | Insert element | O(1) |
| pop() | Remove top | O(1) |
| peek() | Get top | O(1) |
| isEmpty() | Check empty | O(1) |

Java:

```java
Deque<Integer> stack = new ArrayDeque<>();

stack.push(10);
stack.push(20);

stack.pop();    // 20
stack.peek();   // 10
stack.isEmpty();
```

**Interview preference:** Use `Deque` with `ArrayDeque` instead of legacy `Stack`.

## 1.2 Queue (FIFO)

**FIFO = First In, First Out**

The first inserted element is removed first.

```text
Insert: 10 → 20 → 30

FRONT              REAR
  10  →  20  →  30

poll() → 10
peek() → 20
```

| Operation | Description | Complexity |
|---|---|---|
| offer(x) | Insert at rear | O(1) |
| poll() | Remove from front | O(1) |
| peek() | Get front | O(1) |
| isEmpty() | Check empty | O(1) |

Java:

```java
Queue<Integer> queue = new ArrayDeque<>();

queue.offer(10);
queue.offer(20);

queue.poll();    // 10
queue.peek();    // 20
queue.isEmpty();
```

## 1.3 Important Java Collection Methods

| Stack (Deque) | Queue | Meaning |
|---|---|---|
| push(x) | offer(x) | Insert |
| pop() | poll() | Remove |
| peek() | peek() | Read without removing |
| isEmpty() | isEmpty() | Check empty |

**Remember:**

- `poll()` returns null when empty.
- `remove()` throws an exception when empty.
- `peek()` returns null when empty.
- `element()` throws an exception when empty.

---

# 2. IMPLEMENTATION USING DIFFERENT DATA STRUCTURES

## 2.1 Stack Using Array

**Approach:** Maintain a `top` pointer.

Initially:

```java
top = -1;
```

Operations:

```text
PUSH:
1. Check if full.
2. Increment top.
3. Insert element.

POP:
1. Check if empty.
2. Return arr[top].
3. Decrement top.
```

Java:

```java
class StackArray {

    int[] arr;
    int top;
    int capacity;

    StackArray(int capacity) {
        this.capacity = capacity;
        arr = new int[capacity];
        top = -1;
    }

    void push(int x) {
        if (top == capacity - 1)
            throw new IllegalStateException("Stack Full");

        arr[++top] = x;
    }

    int pop() {
        if (top == -1)
            throw new IllegalStateException("Stack Empty");

        return arr[top--];
    }

    int peek() {
        if (top == -1)
            throw new IllegalStateException("Stack Empty");

        return arr[top];
    }
}
```

**Trick:** `top = -1` means empty. `++top` before insertion.

Complexity: Push O(1), Pop O(1).

## 2.2 Queue Using Array (Circular Queue)

**Approach:** Maintain `front`, `rear`, and `size`.

```java
rear = (rear + 1) % capacity;
front = (front + 1) % capacity;
```

These two statements enable circular movement.

Operations:

```text
ENQUEUE:
1. Check size == capacity.
2. rear = (rear + 1) % capacity.
3. arr[rear] = x.
4. size++.

DEQUEUE:
1. Check size == 0.
2. Get arr[front].
3. front = (front + 1) % capacity.
4. size--.
```

**Important:** Use `size` to distinguish full and empty states.

Complexity: Enqueue O(1), Dequeue O(1).

## 2.3 Stack Using Queue

**Approach: One Queue + Rotation**

Stack requires LIFO, but Queue provides FIFO.

Trick: Insert the new element, then rotate all previous elements behind it.

```java
Queue<Integer> q = new ArrayDeque<>();

void push(int x) {
    q.offer(x);

    int size = q.size();

    for (int i = 0; i < size - 1; i++) {
        q.offer(q.poll());
    }
}

int pop() {
    if (q.isEmpty()) throw new NoSuchElementException();
    return q.poll();
}

int top() {
    if (q.isEmpty()) throw new NoSuchElementException();
    return q.peek();
}
```

Example:

```text
push(10) → [10]
push(20) → [20, 10]
push(30) → [30, 20, 10]

pop() → 30
```

Complexity:

- Push: O(n)
- Pop: O(1)
- Top: O(1)

**Trick:** Make the newest element always stay at the front.

## 2.4 Queue Using Stack

**Approach: Two Stacks**

- S1 → Input stack
- S2 → Output stack

```java
Stack<Integer> s1 = new Stack<>();
Stack<Integer> s2 = new Stack<>();

void enqueue(int x) {
    s1.push(x);
}

int dequeue() {
    if (s2.isEmpty()) {
        while (!s1.isEmpty()) {
            s2.push(s1.pop());
        }
    }

    if (s2.isEmpty())
        throw new NoSuchElementException();

    return s2.pop();
}

int peek() {
    if (s2.isEmpty()) {
        while (!s1.isEmpty()) {
            s2.push(s1.pop());
        }
    }

    if (s2.isEmpty())
        throw new NoSuchElementException();

    return s2.peek();
}
```

Example:

```text
enqueue(10)
enqueue(20)
enqueue(30)

S1: [10, 20, 30]  (top = 30)

Transfer:

S2: [30, 20, 10]  (top = 10)

dequeue() → 10
dequeue() → 20
```

Complexity:

- Enqueue: O(1)
- Dequeue: Amortized O(1)
- Peek: Amortized O(1)

**Trick:** Transfer only when S2 is empty, NOT on every dequeue.

## 2.5 Stack Using LinkedList

**Approach:** Insert and delete at head.

```java
class StackLinkedList {

    Node head;

    void push(int x) {
        Node node = new Node(x);
        node.next = head;
        head = node;
    }

    int pop() {
        if (head == null)
            throw new NoSuchElementException();

        int value = head.data;
        head = head.next;
        return value;
    }

    int peek() {
        if (head == null)
            throw new NoSuchElementException();

        return head.data;
    }
}
```

Complexity: Push O(1), Pop O(1).

## 2.6 Queue Using LinkedList

**Approach:** Maintain both `front` and `rear`.

```java
class QueueLinkedList {

    Node front, rear;

    void enqueue(int x) {
        Node node = new Node(x);

        if (rear == null) {
            front = rear = node;
            return;
        }

        rear.next = node;
        rear = node;
    }

    int dequeue() {
        if (front == null)
            throw new NoSuchElementException();

        int value = front.data;
        front = front.next;

        if (front == null)
            rear = null;

        return value;
    }
}
```

Complexity: Enqueue O(1), Dequeue O(1).

**Important:** When the last node is removed, set both `front` and `rear` to null.

---

# 3. BALANCED PARENTHESES

**Problem:** Check whether brackets are properly opened and closed.

Input:

```text
{[()]}
```

Output:

```text
true
```

Input:

```text
{[(])}
```

Output:

```text
false
```

### Pattern Recognition

Whenever you see:
- Opening brackets → push.
- Closing brackets → compare with stack top.

### Algorithm

```text
1. Create an empty stack.
2. Traverse every character.
3. If opening bracket → PUSH.
4. If closing bracket:
   a. If stack empty → false.
   b. Pop and check matching pair.
5. At the end, stack must be empty.
```

Java:

```java
boolean isValid(String s) {

    Deque<Character> stack = new ArrayDeque<>();

    for (char ch : s.toCharArray()) {

        if (ch == '(' || ch == '{' || ch == '[') {
            stack.push(ch);
        } else {

            if (stack.isEmpty())
                return false;

            char top = stack.pop();

            if (ch == ')' && top != '(') return false;
            if (ch == '}' && top != '{') return false;
            if (ch == ']' && top != '[') return false;
        }
    }

    return stack.isEmpty();
}
```

Complexity: O(n) time, O(n) space.

**Trick:** Opening = PUSH, Closing = POP + MATCH.

---

# 4. PREFIX, INFIX AND POSTFIX CONVERSIONS

## 4.1 Understand the Three Notations

| Notation | Operator Position | Example |
|---|---|---|
| Infix | Between operands | A + B |
| Prefix | Before operands | + A B |
| Postfix | After operands | A B + |

One expression in all three formats:

```text
Infix:   (A + B) * C

Prefix:  * + A B C

Postfix: A B + C *
```

## 4.2 Operator Precedence

| Operator | Precedence | Associativity |
|---|---:|---|
| ^ | 3 | Right to Left |
| *, /, % | 2 | Left to Right |
| +, - | 1 | Left to Right |

## 4.3 Infix → Postfix

**Pattern:** Stack + Operator Precedence.

Rules:

```text
1. Operand → Add to result.
2. '(' → Push.
3. ')' → Pop until '('.
4. Operator:
   - While stack top has higher precedence,
     pop and add to result.
   - If equal precedence and current operator
     is left-associative, also pop.
   - Push current operator.
5. End → Pop all remaining operators.
```

Example:

```text
Input: A + B * C
```

| Read | Action | Stack (bottom → top) | Result |
|---|---|---|---|
| A | Operand | [] | A |
| + | Push | [+] | A |
| B | Operand | [+] | AB |
| * | Higher precedence, push | [+, *] | AB |
| C | Operand | [+, *] | ABC |
| END | Pop * | [+] | ABC* |
| END | Pop + | [] | ABC*+ |

Output:

```text
A B C * +
```

**Trick:** Higher precedence → Push. Lower/equal precedence (for left-associative operators) → Pop first.

## 4.4 Infix → Prefix

**Pattern:** Reverse + Swap Brackets + Postfix + Reverse.

Steps:

```text
1. Reverse the infix expression.
2. Swap '(' with ')'.
3. Convert the modified expression to postfix.
4. Reverse the postfix result.
```

Example:

```text
Input:
A + B * C

Reverse:
C * B + A

Postfix:
C B * A +

Reverse:
+ A * B C
```

Output:

```text
+ A * B C
```

**Important:** When implementing this reverse-based method, handle equal-precedence operators using the correct reversed associativity rules. Do not blindly reuse the ordinary infix-to-postfix equality rule.

## 4.5 Prefix → Infix

**Pattern:** Traverse RIGHT → LEFT.

Rules:

```text
1. Operand → Push.
2. Operator:
   a. Pop operand1.
   b. Pop operand2.
   c. Form (operand1 operator operand2).
   d. Push expression.
3. Final stack element = Answer.
```

Example:

```text
Input:
+ A * B C

Scan:
C → Push
B → Push
* → (B * C)
A → Push
+ → (A + (B * C))
```

Output:

```text
(A + (B * C))
```

## 4.6 Prefix → Postfix

**Pattern:** Traverse RIGHT → LEFT.

Rules:

```text
1. Operand → Push.
2. Operator:
   a. Pop operand1.
   b. Pop operand2.
   c. Form operand1 + operand2 + operator.
   d. Push.
3. Final stack element = Answer.
```

Example:

```text
Input:
+ A * B C

Output:
A B C * +
```

## 4.7 Postfix → Infix

**Pattern:** Traverse LEFT → RIGHT.

Rules:

```text
1. Operand → Push.
2. Operator:
   a. Pop operand2.
   b. Pop operand1.
   c. Form (operand1 operator operand2).
   d. Push.
3. Final stack element = Answer.
```

Example:

```text
Input:
A B C * +

Output:
(A + (B * C))
```

**Important:** Pop order matters. The first popped operand is the RIGHT operand.

## 4.8 Postfix → Prefix

**Pattern:** Traverse LEFT → RIGHT.

Rules:

```text
1. Operand → Push.
2. Operator:
   a. Pop operand2.
   b. Pop operand1.
   c. Form operator + operand1 + operand2.
   d. Push.
3. Final stack element = Answer.
```

Example:

```text
Input:
A B C * +

Output:
+ A * B C
```

### Conversion Master Table

| Conversion | Direction | Main Rule |
|---|---|---|
| Infix → Postfix | L → R | Precedence + Stack |
| Infix → Prefix | Reverse trick | Reverse + Swap + Postfix |
| Prefix → Infix | R → L | Pop 2, operator in middle |
| Prefix → Postfix | R → L | Pop 2, operator at end |
| Postfix → Infix | L → R | Pop 2, operator in middle |
| Postfix → Prefix | L → R | Pop 2, operator at beginning |

**Golden trick:**

```text
PREFIX  → Scan RIGHT to LEFT
POSTFIX → Scan LEFT to RIGHT

Operator → Pop 2 → Combine → Push
```

---

# 5. MONOTONIC STACK — MOST IMPORTANT PATTERN

## 5.1 What Is Monotonic Stack?

A stack that maintains elements in a particular order.

Two types:

**Increasing Stack:**

```text
Bottom → Top

1, 2, 4, 7, 9
```

**Decreasing Stack:**

```text
Bottom → Top

9, 7, 4, 2, 1
```

### Why Use It?

When finding the next or previous greater/smaller element, brute force takes O(n²).

Monotonic stack reduces this to O(n).

### Master Logic

```java
for (int i = 0; i < n; i++) {

    while (!stack.isEmpty() && condition) {
        stack.pop();
    }

    // Process answer

    stack.push(i);
}
```

**Remember:** Every element is pushed once and popped at most once.

## 5.2 Four Fundamental Patterns

| Problem | Direction | Pop Condition | Stack Order |
|---|---|---|---|
| Next Greater | R → L | top <= current | Decreasing |
| Next Smaller | R → L | top >= current | Increasing |
| Previous Greater | L → R | top <= current | Decreasing |
| Previous Smaller | L → R | top >= current | Increasing |

These rules assume you are storing values. When storing indices, compare `arr[stack.peek()]` instead.

### Easy Memory Trick

```text
GREATER → Remove smaller/equal elements.
SMALLER → Remove greater/equal elements.

NEXT     → Traverse RIGHT to LEFT.
PREVIOUS → Traverse LEFT to RIGHT.
```

## 5.3 Next Greater Element

Problem:

```text
Input:
[2, 1, 2, 4, 3]

Output:
[4, 2, 4, -1, -1]
```

Intuition:

For each element, find the first greater element on its right.

Algorithm:

```text
1. Traverse right to left.
2. Remove all elements <= current.
3. If stack empty → -1.
4. Otherwise stack top = answer.
5. Push current.
```

Java:

```java
int[] nextGreater(int[] arr) {

    int n = arr.length;
    int[] ans = new int[n];

    Deque<Integer> stack = new ArrayDeque<>();

    for (int i = n - 1; i >= 0; i--) {

        while (!stack.isEmpty() &&
               stack.peek() <= arr[i]) {
            stack.pop();
        }

        ans[i] = stack.isEmpty() ? -1 : stack.peek();

        stack.push(arr[i]);
    }

    return ans;
}
```

Complexity: O(n) time, O(n) space.

## 5.4 Next Smaller Element

Problem:

```text
Input:
[4, 8, 2, 10, 6]

Output:
[2, 2, -1, 6, -1]
```

Algorithm:

```text
1. Traverse R → L.
2. Pop while top >= current.
3. Stack top = next smaller.
4. If empty → -1.
5. Push current.
```

**Only change:** Next Greater uses `<=`; Next Smaller uses `>=`.

## 5.5 Next Greater Element II (Circular Array)

Problem:

```text
Input:
[1, 2, 1]

Output:
[2, -1, 2]
```

Why circular?

After reaching the last element, continue searching from the beginning.

Algorithm:

```text
1. Traverse from 2*n - 1 down to 0.
2. Use index = i % n.
3. Pop elements <= current.
4. Store answers only when i < n.
5. Push current index.
```

Java:

```java
int[] nextGreaterElements(int[] arr) {

    int n = arr.length;
    int[] ans = new int[n];

    Arrays.fill(ans, -1);

    Deque<Integer> stack = new ArrayDeque<>();

    for (int i = 2 * n - 1; i >= 0; i--) {

        int index = i % n;

        while (!stack.isEmpty() &&
               arr[stack.peek()] <= arr[index]) {
            stack.pop();
        }

        if (i < n && !stack.isEmpty()) {
            ans[index] = arr[stack.peek()];
        }

        stack.push(index);
    }

    return ans;
}
```

**Trick:** `2*n` traversal simulates circular movement.

## 5.6 Stock Span Problem

Problem:

For every day, find the number of consecutive previous days having price <= today's price, including today.

```text
Input:
[100, 80, 60, 70, 60, 75, 85]

Output:
[1, 1, 1, 2, 1, 4, 6]
```

Pattern:

Previous Greater Element.

Algorithm:

```text
1. Traverse L → R.
2. Pop while previous price <= current price.
3. If stack empty → span = i + 1.
4. Else → span = i - stack.peek().
5. Push current index.
```

**Trick:** Stock Span = Distance from Previous Greater Element.

## 5.7 Asteroid Collision

Problem:

Positive asteroid → moves right.

Negative asteroid → moves left.

```text
Input:
[5, 10, -5]

Output:
[5, 10]
```

Pattern: Stack simulation.

Collision happens ONLY when:

```text
stack top > 0 AND current < 0
```

Rules:

```text
1. Positive asteroid → Push.
2. Negative asteroid:
   - If no collision → Push.
   - If top smaller → Pop top and continue.
   - If top equal → Destroy both.
   - If top greater → Destroy current.
3. Return remaining stack.
```

Java:

```java
int[] asteroidCollision(int[] asteroids) {

    Deque<Integer> stack = new ArrayDeque<>();

    for (int asteroid : asteroids) {

        boolean alive = true;

        while (alive && asteroid < 0 &&
               !stack.isEmpty() && stack.peek() > 0) {

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

        if (alive)
            stack.push(asteroid);
    }

    int[] ans = new int[stack.size()];

    for (int i = ans.length - 1; i >= 0; i--) {
        ans[i] = stack.pop();
    }

    return ans;
}
```

**Trick:** Right-moving positive + left-moving negative = possible collision.

## 5.8 Remove K Digits

Problem:

Remove exactly K digits to form the smallest possible number.

```text
Input:
num = "1432219"
k = 3

Output:
"1219"
```

Pattern: Greedy + Monotonic Increasing Stack.

Algorithm:

```text
1. Traverse digits left to right.
2. While stack is not empty AND
   top > current AND k > 0:
       Pop.
3. Push current.
4. If k remains, remove digits from the end.
5. Remove leading zeros.
6. If empty, return "0".
```

Java:

```java
String removeKdigits(String num, int k) {

    Deque<Character> stack = new ArrayDeque<>();

    for (char digit : num.toCharArray()) {

        while (!stack.isEmpty() &&
               k > 0 &&
               stack.peek() > digit) {
            stack.pop();
            k--;
        }

        stack.push(digit);
    }

    while (k > 0) {
        stack.pop();
        k--;
    }

    StringBuilder sb = new StringBuilder();

    while (!stack.isEmpty()) {
        sb.append(stack.removeLast());
    }

    int i = 0;

    while (i < sb.length() && sb.charAt(i) == '0') {
        i++;
    }

    String result = sb.substring(i);

    return result.isEmpty() ? "0" : result;
}
```

**Trick:** Remove larger digits before smaller digits to minimize the number.

## 5.9 Sum of Subarray Minimums

Problem:

Find the sum of minimum elements of every possible subarray.

```text
Input:
[3, 1, 2, 4]

Output:
17
```

Explanation:

```text
Subarrays:

[3]          → 3
[1]          → 1
[2]          → 2
[4]          → 4

[3,1]        → 1
[1,2]        → 1
[2,4]        → 2

[3,1,2]      → 1
[1,2,4]      → 1

[3,1,2,4]    → 1

Total = 17
```

Pattern: Contribution Technique + Monotonic Stack.

Instead of generating all subarrays, calculate how many subarrays consider each element as their minimum.

For each index i:

```text
Left = Distance to Previous Smaller Element
Right = Distance to Next Smaller or Equal Element

Contribution =
arr[i] * Left * Right
```

Use a consistent duplicate-handling convention:

```text
Previous Smaller:       strictly smaller
Next Smaller or Equal:  smaller or equal
```

Formula:

```text
Sum =
Σ arr[i] * (i - PSE[i]) * (NSE[i] - i)
```

Example:

```text
Input = [3, 1, 2, 4]

For element 1:
Left distance  = 1
Right distance = 3

Contribution = 1 * 1 * 3 = 3
```

Apply modulo `1_000_000_007` to avoid overflow.

**Trick:** Number of subarrays containing index i where it is the minimum = left choices × right choices.

## 5.10 Sum of Subarray Ranges

Problem:

Find the sum of (maximum - minimum) for every subarray.

```text
Input:
[1, 2, 3]

Output:
4
```

Explanation:

```text
[1]       → 0
[2]       → 0
[3]       → 0
[1,2]     → 1
[2,3]     → 1
[1,2,3]   → 2

Total = 4
```

Pattern: Contribution Technique + Two Monotonic Stacks.

Formula:

```text
Sum of Subarray Ranges =
Sum of Subarray Maximums
-
Sum of Subarray Minimums
```

For minimum contribution:

```text
arr[i] * (i - PSE[i]) * (NSE[i] - i)
```

For maximum contribution:

```text
arr[i] * (i - PGE[i]) * (NGE[i] - i)
```

Use asymmetric strict/non-strict comparisons to handle duplicates.

**Trick:** Range = Maximum contribution - Minimum contribution.

---

# 6. IMPORTANT STACK & QUEUE INTERVIEW PROBLEMS

## 6.1 Implement Min Stack

Problem:

Implement a stack supporting:

```text
push()
pop()
top()
getMin()
```

All operations should take O(1).

Pattern: Auxiliary Stack.

Approach:

- Main stack stores values.
- Min stack stores the minimum at each level.

```java
class MinStack {

    Deque<Integer> stack = new ArrayDeque<>();
    Deque<Integer> minStack = new ArrayDeque<>();

    void push(int x) {

        stack.push(x);

        if (minStack.isEmpty() || x <= minStack.peek()) {
            minStack.push(x);
        }
    }

    void pop() {

        int removed = stack.pop();

        if (removed == minStack.peek()) {
            minStack.pop();
        }
    }

    int top() {
        return stack.peek();
    }

    int getMin() {
        return minStack.peek();
    }
}
```

**Trick:** Push into minStack when `x <= currentMin`, not just `<`, to handle duplicates.

Complexity: All operations O(1).

## 6.2 Sliding Window Maximum

Problem:

Find maximum element in every window of size K.

```text
Input:
arr = [1, 3, -1, -3, 5, 3, 6, 7]
k = 3

Output:
[3, 3, 5, 5, 6, 7]
```

Pattern: Monotonic Decreasing Deque.

Algorithm:

```text
1. Remove indices outside the window.
2. Remove indices whose values are <= current.
3. Add current index.
4. Once window is formed, deque front = maximum.
```

**Trick:** Store indices, not values, because you need to identify expired elements.

Complexity: O(n) time, O(k) space.

## 6.3 Trapping Rainwater

Problem:

Find total water trapped between bars.

```text
Input:
[4, 2, 0, 3, 2, 5]

Output:
9
```

Pattern: Monotonic Stack OR Two Pointers.

Stack approach:

```text
1. Traverse left to right.
2. While current height > stack top:
   - Pop the bottom.
   - If stack empty → break.
   - Calculate bounded height.
   - Calculate width.
   - Add trapped water.
3. Push current index.
```

Formula:

```text
Width =
rightIndex - leftIndex - 1

Bounded Height =
min(leftHeight, rightHeight) - bottomHeight

Water =
Width * Bounded Height
```

**Trick:** Water is trapped only when there are boundaries on both sides.

Complexity: O(n) time, O(n) space.

## 6.4 Largest Rectangle in Histogram

Problem:

Find the largest rectangular area in a histogram.

```text
Input:
[2, 1, 5, 6, 2, 3]

Output:
10
```

Pattern: Previous Smaller + Next Smaller.

For each bar:

```text
Width =
NSI - PSI - 1

Area =
height[i] * width
```

Where:

- PSI = Previous Smaller Index.
- NSI = Next Smaller Index.

Algorithm:

```text
1. Find previous smaller index.
2. Find next smaller index.
3. Calculate width.
4. Calculate area.
5. Track maximum area.
```

**Trick:** A bar can extend until a strictly smaller bar appears on either side.

Use consistent boundaries for duplicates, e.g. pop `>= current` when finding nearest strictly smaller boundaries.

Complexity: O(n) time, O(n) space.

## 6.5 Maximal Rectangle

Problem:

Find the maximum rectangle containing only 1s in a binary matrix.

```text
Input:

1 0 1 0 0
1 0 1 1 1
1 1 1 1 1
1 0 0 1 0

Output:
6
```

Pattern: Convert Matrix Rows into Histograms + Largest Rectangle in Histogram.

Algorithm:

```text
1. Create heights[] initialized to 0.
2. Traverse each matrix row.
3. If cell = 1 → heights[j]++.
4. If cell = 0 → heights[j] = 0.
5. Find largest histogram rectangle.
6. Update maximum area.
```

**Trick:** Every matrix row becomes a new histogram.

Complexity: O(rows × columns) time.

## 6.6 Celebrity Problem

Problem:

Find a person who:

- Knows nobody.
- Is known by everybody else.

Pattern: Stack elimination OR Two Pointers.

Matrix rule:

```text
M[a][b] = 1 → a knows b
M[a][b] = 0 → a does not know b
```

Two-pointer approach:

```text
1. Start i = 0, j = n - 1.
2. If i knows j → i cannot be celebrity; i++.
3. Otherwise → j cannot be celebrity; j--.
4. Remaining candidate must be verified.
5. Check candidate knows nobody.
6. Check everybody knows candidate.
```

**Trick:** Eliminate one candidate per comparison, then verify the survivor.

Complexity: O(n) time, O(1) space.

## 6.7 LRU Cache

LRU = Least Recently Used.

Problem:

Remove the least recently used entry when cache capacity is reached.

Pattern:

```text
HashMap + Doubly LinkedList
```

Why?

- HashMap → O(1) lookup.
- Doubly LinkedList → O(1) removal and insertion.

Operations:

```text
GET:
1. Find key in HashMap.
2. If absent → -1.
3. Move node to head.
4. Return value.

PUT:
1. If key exists → update value and move to head.
2. Otherwise insert at head.
3. If capacity exceeded → remove tail.
```

**Trick:**

```text
HEAD = Most Recently Used
TAIL = Least Recently Used
```

Complexity: Get O(1), Put O(1).

Java alternative: `LinkedHashMap` with access order, but understand the HashMap + DLL implementation for interviews.

## 6.8 LFU Cache

LFU = Least Frequently Used.

Problem:

Remove the least frequently accessed entry.

If multiple entries have the same frequency, remove the least recently used among them.

Pattern:

```text
1. HashMap<Key, Node>
2. HashMap<Frequency, Doubly LinkedList>
3. minFrequency variable
```

Operations:

```text
GET:
1. Find node.
2. Increase frequency.
3. Move node to next frequency list.
4. Update minFrequency if necessary.

PUT:
1. Update existing node OR insert new node.
2. If capacity exceeded:
   - Remove LRU node from minFrequency list.
3. New node starts at frequency 1.
```

**Trick:**

```text
LRU → Least Recently Used
LFU → Least Frequently Used
```

LFU uses LRU as a tie-breaker.

Complexity: O(1) average for Get and Put.

## 6.9 Number of Greater Elements to the Right

Problem:

For each query index, count how many elements to its right are greater than the current element.

Example:

```text
arr = [3, 4, 2, 7, 5]

Query index = 2
Current = 2

Elements to right = [7, 5]
Greater elements = 2

Output = 2
```

Pattern:

For a single query: Brute Force O(n) per query.

For multiple queries: Coordinate Compression + Fenwick Tree (BIT), or an order-statistics structure.

**Important:** A normal monotonic stack finds the next greater element, NOT the count of all greater elements to the right.

---

# 7. PATTERN RECOGNITION — MOST IMPORTANT REVISION TABLE

When you see a problem in an interview, identify the pattern first.

| Problem Clue | Pattern | Data Structure |
|---|---|---|
| Last inserted, first removed | LIFO | Stack |
| First inserted, first removed | FIFO | Queue |
| Circular insertion/deletion | Circular Queue | Array |
| Stack using FIFO | Rotation | Queue |
| Queue using LIFO | Two-stack reversal | Stack |
| Matching brackets | Pair matching | Stack |
| Next greater element | NGE | Monotonic Stack |
| Next smaller element | NSE | Monotonic Stack |
| Previous greater element | PGE | Monotonic Stack |
| Previous smaller element | PSE | Monotonic Stack |
| Circular next greater | Circular traversal | Monotonic Stack |
| Consecutive previous smaller/equal | Stock Span | Monotonic Stack |
| Moving asteroids | Collision simulation | Stack |
| Remove digits to minimize number | Greedy | Monotonic Stack |
| Sum of subarray minimums | Contribution | Monotonic Stack |
| Sum of subarray ranges | Max - Min contribution | Monotonic Stack |
| Minimum in O(1) | Auxiliary Stack | Two Stacks |
| Maximum in sliding window | Sliding window | Deque |
| Water trapped between bars | Boundary calculation | Stack / Two Pointers |
| Largest histogram rectangle | Previous + Next Smaller | Stack |
| Maximum rectangle in matrix | Histogram conversion | Stack |
| Find celebrity | Candidate elimination | Two Pointers |
| Least recently used | Cache eviction | HashMap + DLL |
| Least frequently used | Frequency-based eviction | HashMap + DLL |
| Count all greater elements right | Range/frequency queries | Fenwick Tree |

---

# 8. MONOTONIC STACK — QUICK DECISION GUIDE

Memorize this table.

| What are you finding? | Traverse | Pop Condition |
|---|---|---|
| Next Greater | R → L | top <= current |
| Next Smaller | R → L | top >= current |
| Previous Greater | L → R | top <= current |
| Previous Smaller | L → R | top >= current |
| Stock Span | L → R | previous <= current |
| Remove K Digits | L → R | previous > current |
| Sliding Window Maximum | L → R | previous <= current |

**Duplicate handling matters:** Strict vs non-strict comparisons depend on the exact problem and how equal elements should be assigned.

---

# 9. COMPLEXITY CHEAT SHEET

| Problem | Time | Space |
|---|---:|---:|
| Stack Using Array | O(1) | O(n) |
| Queue Using Circular Array | O(1) | O(n) |
| Stack Using Queue | Push O(n) | O(n) |
| Queue Using Stack | Amortized O(1) | O(n) |
| Stack/Queue Using LinkedList | O(1) | O(n) |
| Balanced Parentheses | O(n) | O(n) |
| Expression Conversions | O(n) | O(n) |
| Next Greater/Smaller | O(n) | O(n) |
| Next Greater II | O(n) | O(n) |
| Asteroid Collision | O(n) | O(n) |
| Sum of Subarray Minimums | O(n) | O(n) |
| Sum of Subarray Ranges | O(n) | O(n) |
| Remove K Digits | O(n) | O(n) |
| Min Stack | O(1) per operation | O(n) |
| Sliding Window Maximum | O(n) | O(k) |
| Trapping Rainwater | O(n) | O(n) |
| Largest Histogram Rectangle | O(n) | O(n) |
| Maximal Rectangle | O(R × C) | O(C) |
| Stock Span | O(n) | O(n) |
| Celebrity | O(n) | O(1) |
| LRU Cache | O(1) average | O(capacity) |
| LFU Cache | O(1) average | O(capacity) |
| Greater Elements to Right | O(n) per query brute force | O(1) extra |

---

# 10. INTERVIEW TRICKS — NEVER FORGET THESE

### Trick 1: Store indices when distance or position matters.

Examples:

- Daily Temperatures
- Stock Span
- Sliding Window Maximum
- Largest Rectangle in Histogram
- Next Greater Element II

### Trick 2: Store values when only the answer value matters.

Example:

- Basic Next Greater Element.

### Trick 3: Always ask these questions:

```text
1. Am I finding NEXT or PREVIOUS?
2. Am I finding GREATER or SMALLER?
3. Should I traverse LEFT → RIGHT or RIGHT → LEFT?
4. Do I need VALUE or INDEX?
5. Should I use <, <=, > or >=?
6. What should I return if no answer exists?
```

### Trick 4: Why is Monotonic Stack O(n)?

Each element:

- Enters the stack once.
- Leaves the stack at most once.

Total operations are linear.

### Trick 5: For subarray contribution problems

```text
Total contribution =
Value × Number of valid left choices ×
Number of valid right choices
```

### Trick 6: For histogram problems

```text
Width = Right Boundary - Left Boundary - 1
Area = Height × Width
```

### Trick 7: For circular arrays

```java
index = i % n;
```

### Trick 8: For sliding window

Always remove expired indices before using the deque front.

---

# 11. YOUR FINAL REVISION CHECKLIST

Use this before attending interviews.

## Implementation

- [ ] Stack using Array
- [ ] Queue using Circular Array
- [ ] Stack using Queue
- [ ] Queue using Stack
- [ ] Stack using LinkedList
- [ ] Queue using LinkedList

## Fundamentals

- [ ] Balanced Parentheses
- [ ] Infix to Postfix
- [ ] Infix to Prefix
- [ ] Prefix to Infix
- [ ] Prefix to Postfix
- [ ] Postfix to Infix
- [ ] Postfix to Prefix

## Monotonic Stack

- [ ] Next Greater Element
- [ ] Next Greater Element II
- [ ] Next Smaller Element
- [ ] Asteroid Collision
- [ ] Stock Span
- [ ] Sum of Subarray Minimums
- [ ] Sum of Subarray Ranges
- [ ] Remove K Digits

## Advanced Stack & Queue

- [ ] Min Stack
- [ ] Sliding Window Maximum
- [ ] Trapping Rainwater
- [ ] Largest Rectangle in Histogram
- [ ] Maximal Rectangle
- [ ] Celebrity Problem
- [ ] LRU Cache
- [ ] LFU Cache
- [ ] Number of Greater Elements to the Right

---

# 12. HOW TO REVISE BEFORE INTERVIEWS

**First revision — Understand**

Understand the intuition and solve each problem without looking at the solution.

**Second revision — Implement**

Write Java code from scratch. Focus on conditions, loop direction, and stack operations.

**Third revision — Pattern recognition**

Look at the problem statement and identify the pattern without coding.

**Fourth revision — Interview simulation**

Explain:

1. Brute force approach.
2. Why brute force is inefficient.
3. Optimized approach.
4. Dry run.
5. Time and space complexity.

**Golden Rule:**

Don't memorize 30 different solutions.

Master these core patterns:

1. LIFO / FIFO implementation
2. Stack-based expression processing
3. Monotonic Increasing Stack
4. Monotonic Decreasing Stack
5. Contribution Technique
6. Deque / Sliding Window
7. Stack Simulation
8. HashMap + Doubly LinkedList

Once you understand these patterns, most Stack & Queue interview problems become variations of what you already know.

**Target: Be able to recognize the pattern within 2–3 minutes and explain the optimized approach confidently.**