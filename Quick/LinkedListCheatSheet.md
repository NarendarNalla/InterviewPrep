# LINKED LIST — INTERVIEW CHEAT SHEET

**Goal:** Pattern recognition, pointer manipulation, and quick interview revision.

---

# 1. LINKED LIST FUNDAMENTALS

## 1.1 What is a Linked List?

A Linked List is a linear data structure where elements (nodes) are stored at different memory locations and connected using pointers.

### Singly Linked List

```java
class Node {
    int data;
    Node next;

    Node(int data) {
        this.data = data;
        this.next = null;
    }
}
```

Structure:

`10 → 20 → 30 → 40 → null`

- `data` stores the value.
- `next` points to the next node.
- Last node points to `null`.

### Doubly Linked List

```java
class Node {
    int data;
    Node next;
    Node prev;

    Node(int data) {
        this.data = data;
        this.next = null;
        this.prev = null;
    }
}
```

Structure:

`null ← 10 ⇄ 20 ⇄ 30 → null`

- `next` points to the next node.
- `prev` points to the previous node.
- Supports traversal in both directions.

## 1.2 Array vs Linked List

| Operation | Array | Linked List |
|---|---|---|
| Access by index | O(1) | O(n) |
| Search | O(n) | O(n) |
| Insert at head | O(n) | O(1) |
| Insert at tail | O(1)* | O(n)* |
| Delete at head | O(n) | O(1) |
| Delete at tail | O(1)* | O(n) |
| Memory | Contiguous | Non-contiguous |

*Assuming a dynamic array with available capacity and a singly linked list without a tail pointer.*

---

# 2. THE MOST IMPORTANT POINTERS

Before solving any Linked List problem, understand these pointers.

| Pointer | Purpose |
|---|---|
| `head` | First node of the list |
| `tail` | Last node, if maintained |
| `curr` | Current node being processed |
| `prev` | Previous node |
| `next` | Stores the next node before changing links |
| `slow` | Moves one step at a time |
| `fast` | Moves two steps at a time |
| `dummy` | Temporary node before head |
| `temp` | Temporary traversal pointer |

## 2.1 Understanding pointer movement

```java
Node temp = head;

while (temp != null) {
    System.out.println(temp.data);
    temp = temp.next;
}
```

Important:

`temp = temp.next` does not delete a node or change the linked list.

It only moves the local reference to the next node.

## 2.2 Why use a Dummy Node?

A dummy node simplifies operations involving the head.

```java
Node dummy = new Node(-1);
dummy.next = head;
```

Instead of handling head deletion separately, we can treat the head like any other node.

Example:

`dummy → 10 → 20 → 30`

To delete 10:

```java
dummy.next = dummy.next.next;
head = dummy.next;
```

**Use dummy nodes when:**

- Deleting the head or an unknown node.
- Removing Nth node from the end.
- Merging two sorted lists.
- Adding two numbers.
- Inserting or deleting nodes with complicated head handling.

---

# 3. LINKED LIST PATTERN RECOGNITION

This is the most important section for interviews.

| If the question mentions... | Recognize this pattern | Main technique |
|---|---|---|
| Traverse, search, count | Basic traversal | Single pointer |
| Insert/delete at position | Pointer manipulation | `prev`, `curr` |
| Insert/delete at head | Head manipulation | Update head |
| Find middle | Slow and fast pointers | Two pointers |
| Nth node from end | Two pointers | Maintain gap of N |
| Reverse linked list | Reversal | `prev`, `curr`, `next` |
| Reverse in groups of K | Group reversal | Reversal + recursion/iteration |
| Detect cycle | Cycle detection | Floyd's algorithm |
| Find cycle starting node | Cycle detection | Slow and fast |
| Find length of cycle | Cycle detection | Count loop length |
| Find intersection | Two-pointer switching | Pointer alignment |
| Check palindrome | Middle + reversal | Reverse second half |
| Rotate linked list | Length + reconnection | Circular connection |
| Merge sorted lists | Two-list traversal | Dummy + two pointers |
| Sort linked list | Divide and conquer | Merge Sort |
| Add numbers | Carry management | Digit-by-digit traversal |
| Add one to number | Reverse or recursion | Carry propagation |
| Clone random pointer list | HashMap / interweaving | Deep copy |
| Flatten linked list | Merge sorted chains | Merge operation |
| Sort 0s, 1s, 2s | Segregation | Three pointers |
| Odd/even positions | Segregation | Two separate chains |
| Doubly linked list deletion | Bidirectional manipulation | `prev`, `next` |
| Remove duplicates in DLL | Sorted traversal | Compare adjacent nodes |
| Find pairs with given sum in DLL | Two pointers | Head + tail |

**Interview shortcut:** Most Linked List problems are variations of these 8 major patterns.

1. Traversal and manipulation.
2. Slow and fast pointers.
3. Reversal.
4. Dummy node.
5. Two-pointer gap/alignment.
6. Merge and sorting.
7. Segregation and rearrangement.
8. Doubly linked list pointer manipulation.

---

# 4. PATTERN 1 — BASIC TRAVERSAL & MANIPULATION

**Recognition:** Questions involving counting, searching, insertion, deletion, or accessing a particular position.

## 4.1 Find Length

Logic: Traverse every node and increment the counter.

```java
int count = 0;
Node temp = head;

while (temp != null) {
    count++;
    temp = temp.next;
}
```

Time: O(n) | Space: O(1)

## 4.2 Search for an Element

Logic: Compare each node's value with the target.

```java
int target = 20;
Node temp = head;

while (temp != null) {
    if (temp.data == target) {
        return true;
    }
    temp = temp.next;
}

return false;
```

Time: O(n) | Space: O(1)

## 4.3 Insert at Head

Logic: New node points to current head. Update head.

```java
Node newNode = new Node(value);

newNode.next = head;
head = newNode;
```

Time: O(1)

## 4.4 Insert at Tail

Logic: Traverse to the last node and attach the new node.

```java
Node newNode = new Node(value);

if (head == null) {
    head = newNode;
    return head;
}

Node temp = head;

while (temp.next != null) {
    temp = temp.next;
}

temp.next = newNode;
```

Time: O(n)

## 4.5 Delete Head

```java
if (head == null) return null;

head = head.next;
```

Time: O(1)

## 4.6 Delete Tail

```java
if (head == null || head.next == null) {
    return null;
}

Node temp = head;

while (temp.next.next != null) {
    temp = temp.next;
}

temp.next = null;
```

Time: O(n)

## 4.7 Delete Kth Node (1-based indexing)

Logic: Reach the node immediately before Kth node.

```java
if (head == null) return null;

if (k == 1) {
    return head.next;
}

Node temp = head;

for (int i = 1; i < k - 1 && temp != null; i++) {
    temp = temp.next;
}

if (temp != null && temp.next != null) {
    temp.next = temp.next.next;
}

return head;
```

Time: O(n)

## 4.8 Delete Node with Value X

Logic: Find the previous node of the target and skip the target node.

Use dummy node to handle head deletion.

```java
Node dummy = new Node(-1);
dummy.next = head;

Node prev = dummy;

while (prev.next != null) {
    if (prev.next.data == x) {
        prev.next = prev.next.next;
        break;
    }
    prev = prev.next;
}

return dummy.next;
```

## 4.9 Insert at Kth Position (1-based indexing)

Logic: Reach K-1 position and insert the new node.

```java
Node newNode = new Node(value);

if (k == 1) {
    newNode.next = head;
    return newNode;
}

Node temp = head;

for (int i = 1; i < k - 1 && temp != null; i++) {
    temp = temp.next;
}

if (temp == null) return head;

newNode.next = temp.next;
temp.next = newNode;

return head;
```

## 4.10 Insert Before Value X

Logic: Find the node whose next node contains X.

```java
Node dummy = new Node(-1);
dummy.next = head;

Node temp = dummy;

while (temp.next != null) {

    if (temp.next.data == x) {
        Node newNode = new Node(value);

        newNode.next = temp.next;
        temp.next = newNode;
        break;
    }

    temp = temp.next;
}

return dummy.next;
```

**Remember:** For insertion and deletion, always identify whether you need the target node or the previous node.

---

# 5. PATTERN 2 — DOUBLY LINKED LIST POINTER MANIPULATION

**Recognition:** When the problem allows forward and backward traversal, use both `prev` and `next`.

## 5.1 Core Pointer Rules

### Insert node between A and B

Before:

`A ⇄ B`

After:

`A ⇄ NEW ⇄ B`

```java
newNode.prev = A;
newNode.next = B;

A.next = newNode;
B.prev = newNode;
```

### Delete a node

Before:

`A ⇄ X ⇄ B`

After:

`A ⇄ B`

```java
if (X.prev != null) {
    X.prev.next = X.next;
}

if (X.next != null) {
    X.next.prev = X.prev;
}
```

Remember to update head/tail when deleting boundary nodes.

## 5.2 DLL Operations

| Problem | Main logic | Complexity |
|---|---|---|
| Convert array to DLL | Create nodes and connect both directions | O(n) |
| Insert before head | New node becomes head | O(1) |
| Insert before tail | Update tail and previous links | O(1)* |
| Insert before Kth node | Traverse to Kth node and connect four links | O(n) |
| Insert before given node | Update four links | O(1) |
| Delete head | `head = head.next`, clear new head.prev | O(1) |
| Delete tail | `tail = tail.prev`, clear new tail.next | O(1) |
| Delete Kth node | Reach Kth and reconnect neighbors | O(n) |
| Remove given node | Reconnect previous and next | O(1) |
| Reverse DLL | Swap `next` and `prev` for every node | O(n) |

*Assuming head and tail references are maintained.*

## 5.3 Reverse a Doubly Linked List

Recognition: Swap the previous and next pointers for every node.

```java
Node current = head;
Node temp = null;

while (current != null) {

    temp = current.prev;
    current.prev = current.next;
    current.next = temp;

    current = current.prev;
}

if (temp != null) {
    head = temp.prev;
}

return head;
```

**Important:** After swapping, move using `current.prev`, because the original next pointer is now stored there.

## 5.4 Delete All Occurrences of X in DLL

Logic: Traverse and delete every matching node.

```java
Node curr = head;

while (curr != null) {

    Node next = curr.next;

    if (curr.data == x) {

        if (curr.prev != null) {
            curr.prev.next = curr.next;
        } else {
            head = curr.next;
        }

        if (curr.next != null) {
            curr.next.prev = curr.prev;
        }
    }

    curr = next;
}

return head;
```

**Why store next first?**

Deleting the current node changes its links. Save the next reference before deletion.

## 5.5 Remove Duplicates from Sorted DLL

Logic: Compare adjacent nodes. If equal, delete the duplicate.

```java
Node curr = head;

while (curr != null && curr.next != null) {

    if (curr.data == curr.next.data) {

        Node duplicate = curr.next;
        curr.next = duplicate.next;

        if (duplicate.next != null) {
            duplicate.next.prev = curr;
        }

    } else {
        curr = curr.next;
    }
}

return head;
```

Time: O(n) | Space: O(1)

## 5.6 Find Pairs with Given Sum in Sorted DLL

Recognition: Sorted DLL + target sum = Two-pointer approach.

```java
Node left = head;
Node right = tail;

while (left != null && right != null
        && left != right && left.prev != right) {

    int sum = left.data + right.data;

    if (sum == target) {
        // Store pair
        left = left.next;
        right = right.prev;
    }
    else if (sum < target) {
        left = left.next;
    }
    else {
        right = right.prev;
    }
}
```

Time: O(n) | Space: O(1) excluding result storage.

---

# 6. PATTERN 3 — SLOW & FAST POINTERS

**Recognition:** Questions involving middle node, cycle detection, loop starting point, or palindrome.

| Problem | Slow movement | Fast movement |
|---|---|---|
| Find middle | 1 step | 2 steps |
| Detect cycle | 1 step | 2 steps |
| Find cycle start | 1 step | 1 step after meeting |
| Find loop length | Count after meeting | — |
| Palindrome | Find middle | Find middle |

## 6.1 Find Middle of Linked List

```java
Node slow = head;
Node fast = head;

while (fast != null && fast.next != null) {
    slow = slow.next;
    fast = fast.next.next;
}

return slow;
```

Time: O(n) | Space: O(1)

**Important:** For an even-length list, this returns the second middle node.

## 6.2 Detect Cycle — Floyd's Cycle Detection

```java
Node slow = head;
Node fast = head;

while (fast != null && fast.next != null) {

    slow = slow.next;
    fast = fast.next.next;

    if (slow == fast) {
        return true;
    }
}

return false;
```

Time: O(n) | Space: O(1)

**Recognition:** If a fast pointer catches a slow pointer, a cycle exists.

## 6.3 Find Starting Point of Cycle

Three steps:

1. Move slow by one and fast by two.
2. If they meet, a cycle exists.
3. Reset one pointer to head. Move both one step at a time. Their next meeting is the cycle start.

```java
Node slow = head;
Node fast = head;

while (fast != null && fast.next != null) {

    slow = slow.next;
    fast = fast.next.next;

    if (slow == fast) {

        slow = head;

        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }

        return slow;
    }
}

return null;
```

Time: O(n) | Space: O(1)

## 6.4 Find Length of Cycle

After detecting a meeting point:

```java
int count = 1;

Node temp = slow.next;

while (temp != slow) {
    count++;
    temp = temp.next;
}

return count;
```

Time: O(n) | Space: O(1)

## 6.5 Delete Middle Node

Use slow and fast pointers, but maintain `prev`.

```java
if (head == null || head.next == null) {
    return null;
}

Node slow = head;
Node fast = head;
Node prev = null;

while (fast != null && fast.next != null) {

    prev = slow;
    slow = slow.next;
    fast = fast.next.next;
}

prev.next = slow.next;

return head;
```

Time: O(n) | Space: O(1)

---

# 7. PATTERN 4 — TWO POINTERS / GAP TECHNIQUE

**Recognition:** The question asks for the Nth node from the end or requires comparing two lists.

## 7.1 Remove Nth Node from End

Approach: Maintain a gap of N nodes between fast and slow.

Steps:

1. Create dummy node.
2. Move fast N steps.
3. Move fast and slow together until fast reaches the last node.
4. Delete `slow.next`.

```java
Node dummy = new Node(-1);
dummy.next = head;

Node slow = dummy;
Node fast = dummy;

for (int i = 0; i < n; i++) {
    fast = fast.next;
}

while (fast.next != null) {
    slow = slow.next;
    fast = fast.next;
}

slow.next = slow.next.next;

return dummy.next;
```

Time: O(n) | Space: O(1)

## 7.2 Find Intersection Point of Two Linked Lists

Recognition: Two linked lists merge at some node and share the remaining nodes.

Optimal approach: Pointer switching.

```java
Node a = headA;
Node b = headB;

while (a != b) {

    a = (a == null) ? headB : a.next;
    b = (b == null) ? headA : b.next;
}

return a;
```

Why it works:

- Pointer A travels list A + list B.
- Pointer B travels list B + list A.
- Both travel equal total distances.
- They meet at the intersection or both become null.

Time: O(n + m) | Space: O(1)

**Important:** Compare node references (`a == b`), not node values.

---

# 8. PATTERN 5 — REVERSAL

**Recognition:** Reverse a list, reverse part of a list, reverse in groups, or compare a list with its reversed half.

## 8.1 Reverse a Singly Linked List — Iterative

This is one of the most important interview templates.

Three pointers:

- `prev`: Previous node.
- `curr`: Current node.
- `next`: Save next node.

```java
Node prev = null;
Node curr = head;

while (curr != null) {

    Node next = curr.next;

    curr.next = prev;

    prev = curr;
    curr = next;
}

return prev;
```

Time: O(n) | Space: O(1)

**Mental model:**

`10 → 20 → 30 → null`

After reversal:

`30 → 20 → 10 → null`

Remember the order:

1. Save next.
2. Reverse link.
3. Move prev.
4. Move curr.

Never overwrite `curr.next` before saving it.

## 8.2 Reverse Using Recursion

```java
Node reverse(Node head) {

    if (head == null || head.next == null) {
        return head;
    }

    Node newHead = reverse(head.next);

    head.next.next = head;
    head.next = null;

    return newHead;
}
```

Time: O(n) | Space: O(n) recursion stack.

## 8.3 Reverse Linked List in Groups of K

Recognition: Reverse K nodes at a time.

Logic:

1. Check whether K nodes are available.
2. Reverse the current group.
3. Connect the previous group to the reversed group.
4. Continue with the remaining nodes.

```java
Node dummy = new Node(-1);
dummy.next = head;

Node groupPrev = dummy;

while (true) {

    Node kth = groupPrev;

    for (int i = 0; i < k && kth != null; i++) {
        kth = kth.next;
    }

    if (kth == null) break;

    Node groupNext = kth.next;

    Node prev = groupNext;
    Node curr = groupPrev.next;

    while (curr != groupNext) {

        Node next = curr.next;

        curr.next = prev;
        prev = curr;
        curr = next;
    }

    Node oldGroupHead = groupPrev.next;

    groupPrev.next = kth;
    groupPrev = oldGroupHead;
}

return dummy.next;
```

Time: O(n) | Space: O(1)

**Important:** This version leaves the last group unchanged if it contains fewer than K nodes.

## 8.4 Check if Linked List is Palindrome

Recognition: Compare the first half with the reversed second half.

Steps:

1. Find the middle using slow and fast pointers.
2. Reverse the second half.
3. Compare both halves.
4. Optionally restore the original list.

```java
if (head == null || head.next == null) {
    return true;
}

Node slow = head;
Node fast = head;

while (fast.next != null && fast.next.next != null) {
    slow = slow.next;
    fast = fast.next.next;
}

Node second = reverse(slow.next);

Node first = head;
Node temp = second;

boolean palindrome = true;

while (temp != null) {

    if (first.data != temp.data) {
        palindrome = false;
        break;
    }

    first = first.next;
    temp = temp.next;
}

slow.next = reverse(second); // Restore list

return palindrome;
```

Time: O(n) | Space: O(1)

---

# 9. PATTERN 6 — LENGTH, RECONNECTION & ROTATION

## 9.1 Rotate Linked List by K Places

Recognition: Move the last K nodes to the front (right rotation).

Example:

`1 → 2 → 3 → 4 → 5`, K = 2

Result:

`4 → 5 → 1 → 2 → 3`

Logic:

1. Find length.
2. Calculate `k = k % length`.
3. Connect tail to head, making a circular list.
4. Find the new tail at position `length - k`.
5. Break the circle.

```java
if (head == null || head.next == null) {
    return head;
}

int len = 1;
Node tail = head;

while (tail.next != null) {
    tail = tail.next;
    len++;
}

k = k % len;

if (k == 0) return head;

tail.next = head;

int steps = len - k;
Node newTail = head;

for (int i = 1; i < steps; i++) {
    newTail = newTail.next;
}

Node newHead = newTail.next;
newTail.next = null;

return newHead;
```

Time: O(n) | Space: O(1)

**Interview trap:** Always apply `k % length`, otherwise large rotations cause incorrect results.

---

# 10. PATTERN 7 — MERGING & SORTING

## 10.1 Merge Two Sorted Linked Lists

Recognition: Two sorted lists need to become one sorted list.

Use a dummy node and a tail pointer.

```java
Node dummy = new Node(-1);
Node tail = dummy;

while (list1 != null && list2 != null) {

    if (list1.data <= list2.data) {
        tail.next = list1;
        list1 = list1.next;
    } else {
        tail.next = list2;
        list2 = list2.next;
    }

    tail = tail.next;
}

tail.next = (list1 != null) ? list1 : list2;

return dummy.next;
```

Time: O(n + m) | Space: O(1)

## 10.2 Sort a Linked List — Merge Sort

Recognition: Sort a linked list efficiently.

Why Merge Sort?

- Linked lists do not support O(1) random access.
- Merge Sort works efficiently using sequential traversal.
- It does not require shifting elements.

Three steps:

1. Find middle.
2. Divide into two halves.
3. Recursively sort and merge.

```java
Node sortList(Node head) {

    if (head == null || head.next == null) {
        return head;
    }

    Node slow = head;
    Node fast = head.next;

    while (fast != null && fast.next != null) {
        slow = slow.next;
        fast = fast.next.next;
    }

    Node second = slow.next;
    slow.next = null;

    Node left = sortList(head);
    Node right = sortList(second);

    return merge(left, right);
}
```

Time: O(n log n) | Space: O(log n) recursion stack, excluding merge's constant auxiliary space.

## 10.3 Flatten a Linked List

Recognition: Each node has:

- `next` pointer connecting horizontal nodes.
- `child` or `bottom` pointer connecting vertical sorted lists.

Approach:

1. Flatten the smaller sublists recursively.
2. Merge two sorted vertical lists.
3. Continue until one sorted list remains.

**Key pattern:** Flattening sorted linked lists = Recursive merge + merge two sorted lists.

Time: Typically O(N log k) with balanced divide-and-conquer merging, where N is total nodes and k is the number of horizontal lists.

---

# 11. PATTERN 8 — NUMBER REPRESENTATION & CARRY

## 11.1 Add Two Numbers Represented by Linked Lists

Recognition: Each node represents one digit; calculate digit sum with carry.

Example:

`2 → 4 → 3`

`5 → 6 → 4`

Result:

`7 → 0 → 8`

(342 + 465 = 807; digits are stored in reverse order.)

```java
Node dummy = new Node(-1);
Node curr = dummy;

int carry = 0;

while (l1 != null || l2 != null || carry != 0) {

    int sum = carry;

    if (l1 != null) {
        sum += l1.data;
        l1 = l1.next;
    }

    if (l2 != null) {
        sum += l2.data;
        l2 = l2.next;
    }

    carry = sum / 10;

    curr.next = new Node(sum % 10);
    curr = curr.next;
}

return dummy.next;
```

Time: O(max(n,m)) | Space: O(max(n,m)) for the output.

## 11.2 Add One to Number Represented by Linked List

Recognition: Add 1 to the number and propagate carry.

Two common approaches:

**Approach 1: Reverse → Add carry → Reverse back**

1. Reverse the list.
2. Add 1 to the first digit.
3. Propagate carry.
4. Reverse back.

**Approach 2: Recursive carry propagation**

Start from the last node using recursion, propagate carry towards the head.

**Important:** Handle all 9s.

Example:

`9 → 9 → 9`

Result:

`1 → 0 → 0 → 0`

---

# 12. PATTERN 9 — SEGREGATION & REARRANGEMENT

## 12.1 Segregate Odd and Even Nodes

Recognition: Rearrange nodes based on their positions, not their values.

Example:

`1 → 2 → 3 → 4 → 5`

Result:

`1 → 3 → 5 → 2 → 4`

Use two chains:

- Odd-position chain.
- Even-position chain.

```java
Node odd = head;
Node even = head.next;
Node evenHead = even;

while (even != null && even.next != null) {

    odd.next = even.next;
    odd = odd.next;

    even.next = odd.next;
    even = even.next;
}

odd.next = evenHead;

return head;
```

Time: O(n) | Space: O(1)

**Important:** Odd/even refers to node positions (1st, 2nd, 3rd...), not whether node values are odd or even.

## 12.2 Sort Linked List of 0s, 1s and 2s

Recognition: Three possible values = Three separate chains.

Approach: Maintain three dummy heads and three tail pointers.

- Zero chain.
- One chain.
- Two chain.

Connect all three chains at the end.

Time: O(n) | Space: O(1) auxiliary space.

Alternative: Count the number of 0s, 1s and 2s, then overwrite values in a second traversal.

**Interview tip:** If changing node values is allowed, counting is simpler. If node rearrangement is required, use three separate chains.

---

# 13. PATTERN 10 — RANDOM POINTER / DEEP COPY

## Clone a Linked List with Random and Next Pointer

Each node contains:

- `next`: Next node.
- `random`: Points to any node or null.

Recognition: Deep-copy a structure containing arbitrary references.

### Approach 1: HashMap

1. Create a copy of every original node and store original → copy in a HashMap.
2. Traverse again.
3. Set copied `next` and `random` using the map.

Time: O(n) | Space: O(n)

### Approach 2: Interweaving (O(1) auxiliary space)

Three steps:

1. Insert each copied node immediately after its original node.
2. Set copied random pointers using `original.random.next`.
3. Separate the original and copied lists.

**Important:**

```java
copy.random = original.random == null
        ? null
        : original.random.next;
```

Why?

Because every original node's copy is immediately next to it during interweaving.

Time: O(n) | Space: O(1) auxiliary space, excluding the copied output.

---

# 14. INTERVIEW COMPLEXITY CHEAT SHEET

| Problem | Time | Auxiliary Space |
|---|---:|---:|
| Traversal / Search / Length | O(n) | O(1) |
| Insert/Delete Head | O(1) | O(1) |
| Insert/Delete Tail (SLL) | O(n) | O(1) |
| Insert/Delete Kth | O(n) | O(1) |
| DLL insertion/deletion given node | O(1) | O(1) |
| Reverse SLL | O(n) | O(1) |
| Reverse DLL | O(n) | O(1) |
| Find Middle | O(n) | O(1) |
| Detect Cycle | O(n) | O(1) |
| Find Cycle Start | O(n) | O(1) |
| Find Cycle Length | O(n) | O(1) |
| Remove Nth from End | O(n) | O(1) |
| Find Intersection | O(n + m) | O(1) |
| Palindrome | O(n) | O(1) |
| Reverse in K Groups | O(n) | O(1) |
| Rotate List | O(n) | O(1) |
| Merge Sorted Lists | O(n + m) | O(1) |
| Sort Linked List | O(n log n) | O(log n) |
| Add Two Numbers | O(max(n,m)) | O(max(n,m)) output |
| Clone Random List | O(n) | O(n) HashMap / O(1) interweaving |
| Remove Duplicates in DLL | O(n) | O(1) |
| Pair Sum in Sorted DLL | O(n) | O(1) excluding output |

---

# 15. MOST IMPORTANT JAVA TEMPLATES

Memorize these templates. They solve or form the foundation of many Linked List problems.

## Template 1: Basic Traversal

```java
Node temp = head;

while (temp != null) {
    // Process current node
    temp = temp.next;
}
```

## Template 2: Previous + Current

```java
Node prev = null;
Node curr = head;

while (curr != null) {

    // Process or modify current node

    prev = curr;
    curr = curr.next;
}
```

## Template 3: Dummy + Tail

```java
Node dummy = new Node(-1);
Node tail = dummy;

// Attach nodes using tail.next
tail = tail.next;

return dummy.next;
```

## Template 4: Slow + Fast

```java
Node slow = head;
Node fast = head;

while (fast != null && fast.next != null) {

    slow = slow.next;
    fast = fast.next.next;
}
```

## Template 5: Reverse Linked List

```java
Node prev = null;
Node curr = head;

while (curr != null) {

    Node next = curr.next;
    curr.next = prev;

    prev = curr;
    curr = next;
}

return prev;
```

## Template 6: Two Pointers with Gap

```java
Node slow = head;
Node fast = head;

// Move fast N positions ahead

while (fast != null && fast.next != null) {
    slow = slow.next;
    fast = fast.next;
}
```

## Template 7: DLL Reconnection

```java
newNode.prev = prevNode;
newNode.next = nextNode;

prevNode.next = newNode;
nextNode.prev = newNode;
```

Always handle null boundaries separately.

---

# 16. COMMON INTERVIEW MISTAKES

| Mistake | Correct approach |
|---|---|
| Forgetting empty list | Check `head == null` |
| Ignoring single node | Check `head.next == null` |
| Losing remaining nodes during reversal | Save `next` before changing links |
| Incorrect head deletion | Return/update the new head |
| Incorrect tail deletion | Reach the second-last node |
| NullPointerException | Validate pointers before accessing `.next` |
| Incorrect cycle detection | Use `fast != null && fast.next != null` |
| Wrong middle for even length | Understand which middle the loop returns |
| Incorrect Nth from end | Maintain exactly N nodes of gap |
| Wrong intersection comparison | Compare references, not values |
| Incorrect rotation | Apply `k % length` |
| Breaking DLL links | Update both `next` and `prev` |
| Losing nodes in segregation | Save or maintain the original chain references |
| Incorrect carry | Continue while carry is non-zero |
| Random pointer clone errors | Use copied references, not original references |

---

# 17. EDGE CASE CHECKLIST

Before submitting any Linked List solution, test:

1. Empty list: `null`
2. Single node: `10`
3. Two nodes: `10 → 20`
4. Multiple nodes: `10 → 20 → 30 → 40`
5. Target at head.
6. Target at tail.
7. Target not present.
8. Duplicate values.
9. K = 1.
10. K = list length.
11. K greater than list length.
12. Even-length and odd-length lists.
13. All values identical.
14. All values are 9 (number problems).
15. Cycle exists / cycle does not exist.

---

# 18. FINAL INTERVIEW QUICK REVISION — PATTERN MAPPING

| No. | Pattern | Problems to recall |
|---|---|---|
| 1 | Traversal | Length, Search |
| 2 | Pointer manipulation | Insert/Delete SLL, Insert/Delete DLL |
| 3 | Slow & Fast | Middle, Delete Middle, Cycle Detection |
| 4 | Two-pointer gap | Remove Nth from End |
| 5 | Pointer switching | Intersection of Y LL |
| 6 | Reversal | Reverse SLL, Reverse DLL, Reverse K Groups |
| 7 | Middle + Reversal | Palindrome |
| 8 | Cycle mathematics | Cycle Start, Cycle Length |
| 9 | Length + Reconnection | Rotate LL |
| 10 | Dummy + Two Lists | Merge Sorted Lists |
| 11 | Divide and Conquer | Sort LL, Flatten LL |
| 12 | Carry Propagation | Add Two Numbers, Add One |
| 13 | Segregation | Odd/Even Nodes, Sort 0/1/2 |
| 14 | Deep Copy | Clone Random Pointer LL |
| 15 | DLL Two Pointers | Find Pairs with Given Sum |
| 16 | Sorted Traversal | Remove Duplicates, Delete All Occurrences |

---

# 19. HOW TO APPROACH ANY LINKED LIST INTERVIEW QUESTION

Use this 6-step process:

**Step 1 — Identify the operation**

Is it insertion, deletion, searching, reversing, rearranging, merging, or detecting?

**Step 2 — Identify the required pointers**

Do you need `prev`, `curr`, `slow`, `fast`, `dummy`, or two independent pointers?

**Step 3 — Draw a small example**

Use 3–5 nodes and manually move the pointers.

**Step 4 — Identify boundary conditions**

Empty list, one node, head, tail, and invalid positions.

**Step 5 — Write the pointer updates**

Always save the next reference before modifying links when necessary.

**Step 6 — Analyze complexity**

State time and auxiliary space separately. Mention recursion stack and output storage where applicable.

---

## FINAL THINGS TO MEMORIZE

If you have only 15 minutes before a Linked List interview, revise these in order:

1. Singly and Doubly Linked List structure.
2. Insert/Delete head, tail and Kth node.
3. Dummy node technique.
4. Reverse Linked List (iterative).
5. Slow and Fast pointer template.
6. Floyd's Cycle Detection + Cycle Start.
7. Find Middle + Palindrome.
8. Remove Nth Node from End.
9. Intersection of Two Linked Lists.
10. Reverse in K Groups.
11. Merge Two Sorted Lists.
12. Merge Sort on Linked List.
13. Rotate Linked List.
14. Add Two Numbers.
15. Odd/Even Segregation.
16. Clone Random Pointer Linked List.
17. DLL pointer manipulation and pair sum.

**Core interview principle:** Linked List problems are primarily about maintaining references correctly. Focus on understanding how pointers change, rather than memorizing individual solutions.