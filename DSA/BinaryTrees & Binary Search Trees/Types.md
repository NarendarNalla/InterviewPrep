# 🌳 Balanced vs Skewed vs Degenerate vs Full vs Complete vs Perfect Binary Trees

These terms are easy to mix up because **Balanced/Skewed/Degenerate** describe the *shape*, while **Full/Complete/Perfect** describe *structural properties*.

---

## 🌳 1. Balanced Binary Tree

A binary tree is balanced when the heights of the left and right subtrees are approximately equal at every node.

A common definition used in DSA is:

> For every node, `|height(left) - height(right)| ≤ 1`.

**Example:**
```
        1
       / \
      2   3
     / \   \
    4   5   6
```

At each node, the left and right subtree heights differ by at most 1.

### Key Point
```
Balanced ≠ Full ≠ Complete ≠ Perfect
```
A balanced tree can have nodes with only one child.

---

## 🌳 2. Skewed Binary Tree

A skewed tree is a tree where nodes predominantly extend in one direction.

It looks similar to a linked list.

```
1
 \
  2
   \
    3
     \
      4
       \
        5
```
This is a **right-skewed** tree.

Or:
```
        1
       /
      2
     /
    3
   /
  4
 /
5
```
This is a **left-skewed** tree.

### Key Point
A skewed tree is generally highly unbalanced.

---

## 🌳 3. Degenerate Binary Tree

A degenerate tree is a binary tree where every node has at most one child.

**Example:**
```
1
 \
  2
   \
    3
     \
      4
```
or
```
    1
   /
  2
 /
3
/
4
```

It behaves exactly like a linked list.

### Relationship
```
Degenerate Tree
      ↓
Every node has ≤ 1 child
      ↓
Can be left-skewed or right-skewed
```

> Every skewed tree is degenerate, and a degenerate tree is essentially a skewed tree.

---

## 🌳 4. Left-Skewed vs Right-Skewed

### Left-Skewed
Every node has only a left child.
```
        10
       /
      8
     /
    6
   /
  4
 /
2
```

### Right-Skewed
Every node has only a right child.
```
10
  \
   8
    \
     6
      \
       4
        \
         2
```

---

## 🌳 5. Full Binary Tree

A **Full Binary Tree** is a tree where every node has either:
- 0 children, or
- 2 children

There is no node with exactly 1 child.

**Example:**
```
          1
        /   \
       2     3
      / \   / \
     4   5 6   7
```
This is Full.

Another Full tree:
```
        1
       / \
      2   3
     / \
    4   5
```
Also Full.

### Remember
```
Full → 0 or 2 children
```

---

## 🌳 6. Complete Binary Tree

A **Complete Binary Tree** follows this rule:

> Every level is completely filled except possibly the last level, and the last level is filled from left to right.

**Example:**
```
          1
        /   \
       2     3
      / \   /
     4   5 6
```
This is Complete.

Last level:
```
4  5  6
← left to right
```

### Not Complete
```
          1
        /   \
       2     3
      /       \
     4         7
```
Why? Because the last level has a gap:
```
4    7
↑ gap
```

> Complete Binary Tree is especially important for Heaps.

---

## 🌳 7. Perfect Binary Tree

A **Perfect Binary Tree** has:
- Every internal node has exactly 2 children
- All leaf nodes are at the same level

**Example:**
```
              1
          /       \
         2         3
       /  \       /  \
      4    5     6    7
```
Every level is completely filled.

For height `h`:
```
Number of nodes = 2^(h+1) - 1
```

If height = 2:
```
2^(2+1) - 1
= 8 - 1
= 7
```

---

## 🔥 Main Differences

| Type | Main Rule | Example Shape |
|------|-----------|----------------|
| **Balanced** | Left/right subtree heights are close | 🌳 roughly even |
| **Skewed** | Nodes extend mainly in one direction | `1 → 2 → 3 → 4` |
| **Degenerate** | Every node has at most 1 child | Linked-list-like |
| **Left Skewed** | Every node has only left child | `1 ← 2 ← 3` |
| **Right Skewed** | Every node has only right child | `1 → 2 → 3` |
| **Full** | Every node has 0 or 2 children | No single-child nodes |
| **Complete** | Last level filled left → right | Heap structure |
| **Perfect** | All levels completely filled | Perfect pyramid |