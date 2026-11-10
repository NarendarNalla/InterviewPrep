# 🌳 Complete Binary Tree → BST → Advanced Tree Practice Sheet

Merged and deduplicated master sheet, Level 0 → Level 13.
Format for every problem: **Problem Statement → Input → Output → Example(s)** (no solutions/code — practice only).

```
        1
      /   \
     2     3
    / \     \
   4   5     6
```

---

## 🎯 Complete Learning Order (map of this sheet)

```
LEVEL 0  — Prerequisites
LEVEL 1  — Binary Tree Fundamentals (count, sum, min/max, search)
LEVEL 2  — Tree Traversals (pre/in/post/level order)
LEVEL 3  — Basic Tree Recursion Patterns (same tree, invert, symmetric)
LEVEL 4  — Height, Depth & Balanced Trees
LEVEL 5  — BFS / Level-Aggregation Problems
LEVEL 6  — Path Problems
LEVEL 7  — Diameter & Maximum Path
LEVEL 8  — Lowest Common Ancestor & Node Distance
        ↓  (recursion + traversal foundations now solid — move into ordering-based trees)
LEVEL 9  — BST Fundamentals
LEVEL 10 — BST Interview Problems
LEVEL 11 — Construct Binary Trees
LEVEL 12 — Tree Views & Special Traversals
LEVEL 13 — Advanced Binary Tree Problems
```

**Key insight to carry from Level 9 onward:** in a BST, ordering lets you eliminate half the tree at every decision. For Level 13, finish Levels 9–10 first — don't jump straight to advanced problems.

---

# 🟢 LEVEL 0 — Prerequisites

Conceptual, not a coding problem set.

**1. Java Classes & Objects**
```java
class TreeNode {
    int data;
    TreeNode left, right;
    TreeNode(int data) { this.data = data; }
}
```
```java
TreeNode root = new TreeNode(10);
```

**2. References**
```java
root.left = new TreeNode(20);
root.right = new TreeNode(30);
```
```
      10
     /  \
    20    30
```

**3. Recursion**
```
function() → smaller problem → base case → return result
```

**4. Stack** — `push()` `pop()` `peek()` `isEmpty()` (iterative DFS)

**5. Queue** — `offer()` `poll()` `peek()` `isEmpty()` (BFS)

**6. Big-O** — `O(1)` `O(log n)` `O(n)` `O(n log n)` `O(n²)`

---

# 🟢 LEVEL 1 — Binary Tree Fundamentals

## 1. Count Number of Nodes
Given the root of a binary tree, return the total number of nodes.

**Sample 1**
```
      1
     / \
    2   3
   / \
  4   5
```
Output: **5**

**Sample 2**
```
    10
   /
  20
 /
30
```
Output: **3**

**Sample 3**
```
        1
       / \
      2   3
         / \
        4   5
       /
      6
```
Output: **6**

**Sample 4**: `10` → Output: **1**
**Sample 5**: `null` → Output: **0**

---

## 2. Count Leaf Nodes
A leaf has no left **and** no right child.

**Sample 1**
```
      1
     / \
    2   3
   / \
  4   5
```
Output: **3** (leaves: 4, 5, 3)

**Sample 2**
```
    10
   /
  20
 /
30
```
Output: **1**

**Sample 3**
```
      1
     / \
    2   3
   /   / \
  4   5   6
```
Output: **3**

**Sample 4**: `10` → Output: **1**

---

## 3. Sum of All Nodes

**Sample 1**
```
      1
     / \
    2   3
   / \
  4   5
```
Output: **15**

**Sample 2**
```
    10
   /  \
  20    30
```
Output: **60**

**Sample 3**
```
       5
      / \
    -2   8
    / \
   3   1
```
Output: **15**

**Sample 4**: `null` → Output: **0**

---

## 4. Find Maximum Value

**Sample 1**
```
        10
       /  \
      5    20
     / \   / \
    2   8 15 25
```
Output: **25**

**Sample 2**
```
     -10
     /  \
   -20  -5
```
Output: **-5**

**Sample 3**
```
50
 \
  30
   \
    10
```
Output: **50**

**Sample 4**: `100` → Output: **100**

---

## 5. Find Minimum Value
Same trees as Samples 1–3 above.
- Sample 1 → Output: **2**
- Sample 2 → Output: **-20**
- Sample 3 → Output: **10**

---

## 6. Search for a Value
Return `true`/`false` if `target` exists.

**Sample 1**
```
        10
       /  \
      5    20
     / \   / \
    2   8 15 25
```
target = 15 → Output: **true**

**Sample 2**
```
      10
     /  \
    5    20
   / \
  2   8
```
target = 100 → Output: **false**

**Sample 3**
```
    5
   / \
  5   5
```
target = 5 → Output: **true**

**Sample 4**
```
-10
  \
  -20
```
target = -20 → Output: **true**

---

# 🟢 LEVEL 2 — Tree Traversals

## 1. Preorder Traversal (Root → Left → Right)

**Sample 1**
```
      1
     / \
    2   3
   / \
  4   5
```
→ `[1, 2, 4, 5, 3]`

**Sample 2**
```
     10
    /  \
   20    30
  /
 40
```
→ `[10, 20, 40, 30]`

**Sample 3**
```
10
  \
   20
     \
      30
```
→ `[10, 20, 30]`

---

## 2. Inorder Traversal (Left → Root → Right)

**Sample 1**
```
      1
     / \
    2   3
   / \
  4   5
```
→ `[4, 2, 5, 1, 3]`

**Sample 2**
```
     10
    /  \
   20    30
  /
 40
```
→ `[40, 20, 10, 30]`

**Sample 3**
```
      5
     / \
    3   7
   / \
  1   4
```
→ `[1, 3, 4, 5, 7]`

---

## 3. Postorder Traversal (Left → Right → Root)

**Sample 1**
```
      1
     / \
    2   3
   / \
  4   5
```
→ `[4, 5, 2, 3, 1]`

**Sample 2**
```
     10
    /  \
   20    30
  /
 40
```
→ `[40, 20, 30, 10]`

**Sample 3**
```
1
 /
2
 /
3
```
→ `[3, 2, 1]`

---

## 4. Level Order Traversal

**Sample 1**
```
      1
     / \
    2   3
   / \   \
  4   5   6
```
→ `[[1], [2, 3], [4, 5, 6]]`

**Sample 2**
```
      10
     /  \
    20    30
   /     /  \
  40    50   60
```
→ `[[10], [20, 30], [40, 50, 60]]`

**Sample 3**
```
1
 \
  2
   \
    3
```
→ `[[1], [2], [3]]`

---

## 5. Reverse Level Order Traversal

**Sample 1**
```
      1
     / \
    2   3
   / \
  4   5
```
→ `[[4, 5], [2, 3], [1]]`

**Sample 2**
```
      10
     /  \
    20    30
   / \
  40  50
```
→ `[[40, 50], [20, 30], [10]]`

---

## 6. Recursive Level Order Traversal
Same result as Level Order Traversal, computed **without** a Queue.
```
      1
     / \
    2   3
   / \   \
  4   5   6
```
→ `[[1], [2, 3], [4, 5, 6]]`

---

# 🟢 LEVEL 3 — Basic Tree Recursion Patterns

*(Count/Sum/Min/Max/Search recursion is exercised again here implicitly, but as it's the same problem as Level 1, it isn't restated — only genuinely new recursion patterns follow.)*

## 1. Same Binary Tree
Are two trees identical in structure and values?

**Sample 1**
```
 Tree 1:      Tree 2:
    1            1
   / \          / \
  2   3        2   3
```
Output: **true**

**Sample 2**
```
 Tree 1:      Tree 2:
    1            1
   / \          / \
  2   3        2   4
```
Output: **false**

**Sample 3**
```
 Tree 1:      Tree 2:
    1            1
   /            / \
  2            2   3
```
Output: **false**

---

## 2. Invert Binary Tree
Swap left/right children of every node.

**Sample 1**
```
Original:      Inverted:
    1              1
   / \            / \
  2   3          3   2
 / \                / \
4   5              5   4
```

**Sample 2**
```
Original:        Inverted:
    10                10
   /  \              /  \
  20    30          30    20
 /        \        /        \
40         50     50         40
```

**Sample 3**
```
Original:      Inverted:
1                  1
 \                /
  2              2
   \            /
    3          3
```

---

## 3. Symmetric Binary Tree
Is the tree a mirror of itself around the center?

**Sample 1**
```
      1
     / \
    2   2
   / \ / \
  3  4 4  3
```
Output: **true**

**Sample 2**
```
      1
     / \
    2   2
     \   \
      3   3
```
Output: **false**

**Sample 3**
```
      1
     / \
    2   2
   /     \
  3       3
```
Output: **true**

---

# 🟡 LEVEL 4 — Height, Depth & Balanced Trees

## 1. Maximum Depth

**Sample 1**
```
    1
   / \
  2   3
 /
4
/
5
```
Output: **5**

**Sample 2**
```
      1
     / \
    2   3
       / \
      4   5
```
Output: **3**

**Sample 3**
```
10
  \
   20
     \
      30
```
Output: **3**

---

## 2. Minimum Depth
Root-to-nearest-leaf depth.

**Sample 1**
```
      1
     / \
    2   3
   /     \
  4       5
 /
6
```
Output: **3** (path `1 → 3 → 5`)

**Sample 2**
```
    1
   /
  2
 /
3
```
Output: **3**

**Sample 3**
```
  1
 / \
2   3
```
Output: **2**

---

## 3. Check Balanced Binary Tree
`true` if `|height(left) − height(right)| ≤ 1` at every node.

**Sample 1**
```
      1
     / \
    2   3
   / \  / \
  4  5 6  7
```
Output: **true**

**Sample 2**
```
1
 /
2
 /
3
/
4
```
Output: **false**

**Sample 3**
```
      1
     / \
    2   3
   /
  4
```
Output: **true**

---

## 4. Find Deepest Node
Rightmost node at the deepest level.

**Sample 1**
```
      1
     / \
    2   3
   / \   \
  4   5   6
```
Output: **6**

**Sample 2**
```
      10
     /  \
    20    30
   / \
  40  50
```
Output: **50**

---

## 5. Find Depth of a Given Node
Root depth = 0.

**Sample 1**
```
      1
     / \
    2   3
   / \
  4   5
```
target = 5 → Output: **2**

**Sample 2**
```
    10
   /
  20
 /
30
```
target = 30 → Output: **2**

**Sample 3**
```
  1
 / \
2   3
```
target = 100 → Output: **-1**

---

## 6. Find Height of a Given Node
Edges to farthest leaf below it.

**Sample 1**
```
      1
     / \
    2   3
   / \
  4   5
```
target = 2 → Output: **1**

**Sample 2**
```
    1
   / \
  2   3
 /
4
/
5
```
target = 2 → Output: **2**

**Sample 3**
```
  1
 / \
2   3
```
target = 3 → Output: **0**

---

# 🟡 LEVEL 5 — BFS / Level-Aggregation Problems

*(Level Order, Reverse Level Order, Left View, Right View, and Zigzag are the same problems as Level 2 and Level 12 — not repeated here.)*

## 1. Average of Levels
```
      1
     / \
    2   3
   / \   \
  4   5   6
```
Levels: `[1]`, `[2, 3]`, `[4, 5, 6]` → `[1.0, 2.5, 5.0]`

---

## 2. Maximum Value at Each Level
```
      1
     / \
    7   3
   / \   \
  4   9   6
```
→ `[1, 7, 9]`

---

## 3. Minimum Value at Each Level
```
        10
       /  \
      5    20
     / \   / \
    2   8 15 25
```
→ `[10, 5, 2]`

---

## 4. Maximum Level Sum
Root = level 1.

**Sample 1**
```
      1
     / \
    2   3
   / \   \
  4   5   6
```
Level sums: `1`, `5`, `15` → Output: **3**

**Sample 2**
```
      10
     /  \
    20    5
   / \
  30  40
```
Level sums: `10`, `25`, `70` → Output: **3**

---

## 5. Bottom Left Value

**Sample 1**
```
      1
     / \
    2   3
   /   /
  4   5
     /
    7
```
Output: **7**

**Sample 2**
```
      1
     / \
    2   3
   / \
  4   5
```
Output: **4**

---

# 🟡 LEVEL 6 — Path Problems

## 1. Path Sum
Does a root-to-leaf path sum to `target`?

**Sample 1**
```
        5
       / \
      4   8
     /   / \
    11  13  4
   /  \       \
  7    2       1
```
target = 22 → Path `5 → 4 → 11 → 2` → Output: **true**

**Sample 2**
```
  1
 / \
2   3
```
target = 5 → Output: **false**

---

## 2. Path Sum II
Return all root-to-leaf paths summing to `target` (same tree as above, target = 22):
```
[
  [5, 4, 11, 2],
  [5, 8, 4, 5]
]
```

---

## 3. All Root-to-Leaf Paths
```
    1
   / \
  2   3
   \   \
    5   4
```
→ `[[1, 2, 5], [1, 3, 4]]`

---

## 4. Sum Root-to-Leaf Numbers

**Sample 1**
```
  1
 / \
2   3
```
Paths: `12`, `13` → Output: **25**

**Sample 2**
```
      4
     / \
    9   0
   / \
  5   1
```
Numbers: `495`, `491`, `40` → Output: **1026**

---

## 5. Maximum Root-to-Leaf Sum
```
      10
     /  \
    20    30
   / \      \
  5  25      40
```
Paths: `35`, `55`, `80` → Output: **80**

---

## 6. Minimum Root-to-Leaf Sum
```
      10
     /  \
    20    5
   /       \
  30        2
```
Paths: `60`, `17` → Output: **17**

---

## 7. Count Paths With Target Sum
Path may start/end at any node, moving downward only.
```
        10
       /  \
      5   -3
     / \    \
    3   2   11
   / \   \
  3  -2   1
```
target = 8 → Output: **3**

---

# 🔴 LEVEL 7 — Diameter & Maximum Path

## 1. Diameter of Binary Tree
Longest path between any two nodes (edge count).

**Sample 1**
```
      1
     / \
    2   3
   / \
  4   5
```
Path `4 → 2 → 1 → 3` → Output: **3**

**Sample 2**
```
1
 /
2
 /
3
/
4
```
Output: **3**

**Sample 3**
```
      1
     / \
    2   3
       / \
      4   5
```
Output: **3**

---

## 2. Longest Path Between Two Nodes
```
        10
       /  \
      5    20
     / \   / \
    2   7 15 25
           \
            17
```
Path: `2 → 5 → 10 → 20 → 15 → 17` → Output: **5**

---

## 3. Maximum Path Sum
Path can start/end anywhere; a node visited at most once.

**Sample 1**
```
  1
 / \
2   3
```
Path `2 → 1 → 3` → Output: **6**

**Sample 2**
```
    -10
    /  \
   9   20
       /  \
      15   7
```
Path `15 → 20 → 7` → Output: **42**

**Sample 3**
```
-3
```
Output: **-3**

---

## 4. Longest Univalue Path
Longest path where every node has the same value.

**Sample 1**
```
      5
     / \
    4   5
   / \   \
  1   1   5
```
Path `5 → 5 → 5` → Output: **2**

**Sample 2**
```
      1
     / \
    1   1
   / \
  1   1
```
Path `1 → 1 → 1 → 1` → Output: **3**

---

# 🔴 LEVEL 8 — Lowest Common Ancestor & Node Distance

## 1. LCA of Two Nodes

**Sample 1**
```
          3
         / \
        5   1
       / \ / \
      6  2 0  8
        / \
       7   4
```
p = 5, q = 1 → Output: **3**

**Sample 2**
```
          3
         / \
        5   1
       / \
      6   2
         / \
        7   4
```
p = 6, q = 4 → Output: **5**

**Sample 3**
```
          3
         / \
        5   1
       / \
      6   2
```
p = 5, q = 6 → Output: **5**

---

## 2. LCA When Nodes May Not Exist
Return `null` if either node doesn't exist.

**Sample 1**
```
      1
     / \
    2   3
   / \
  4   5
```
p = 4, q = 5 → Output: **2**

**Sample 2**
```
  1
 / \
2   3
```
p = 2, q = 100 → Output: **null**

---

## 3. LCA of Deepest Leaves

**Sample 1**
```
      1
     / \
    2   3
   / \   \
  4   5   6
```
Deepest leaves: `4, 5, 6` → Output: **1**

**Sample 2**
```
      1
     / \
    2   3
   / \
  4   5
```
Deepest leaves: `4, 5` → Output: **2**

---

## 4. Distance Between Two Nodes

**Sample 1**
```
          3
         / \
        5   1
       / \ / \
      6  2 0  8
        / \
       7   4
```
p = 6, q = 4 → Path `6 → 5 → 2 → 4` → Output: **3**

**Sample 2**
```
      1
     / \
    2   3
   / \
  4   5
```
p = 4, q = 5 → Output: **2**

---

## 5. Kth Ancestor

**Sample 1**
```
1
 \
  2
   \
    3
     \
      4
```
target = 4, K = 2 → Path `4 → 3 → 2` → Output: **2**

**Sample 2**
```
      1
     / \
    2   3
   /
  4
```
target = 4, K = 1 → Output: **2**

**Sample 3**
```
1
 \
  2
```
target = 2, K = 2 → Output: **-1** (doesn't exist)

---

## 6. Nodes at Distance K
Given a binary tree, a target node, and an integer `K`, return all nodes exactly `K` edges away from the target — in **any direction** (up through parents or down through children). This needs a parent-map/BFS approach rather than pure downward recursion, which is why it belongs here with LCA/distance problems rather than with the basic path problems.

**Sample 1**
```
          3
         / \
        5   1
       / \ / \
      6  2 0  8
        / \
       7   4
```
target = 5, K = 2 → Output: `[7, 4, 1]`

**Sample 2**
```
        3
       / \
      5   1
```
target = 3, K = 1 → Output: `[5, 1]`

---

# 🔵 LEVEL 9 — BST Fundamentals

## 1. Search in BST

Given the root of a Binary Search Tree and an integer `target`, determine whether the target value exists in the BST.

**Example 1**
```text
        8
       / \
      3   10
     / \    \
    1   6    14

target = 6
```
Output: **true**

**Example 2**
```text
        8
       / \
      3   10
     / \    \
    1   6    14

target = 12
```
Output: **false**

---

## 2. Insert into BST

Given the root of a BST and an integer `value`, insert the value into the BST while maintaining the BST property.

**Example 1**
```text
        8
       / \
      3   10
     / \
    1   6

value = 4
```
Output:
```text
        8
       / \
      3   10
     / \
    1   6
       /
      4
```

**Example 2**
```text
        8
       / \
      3   10

value = 15
```
Output:
```text
        8
       / \
      3   10
             \
              15
```

---

## 3. Delete from BST

Given the root of a BST and a value `key`, delete the node containing the key while maintaining the BST property.

Consider all three cases:
```text
1. Node is a leaf
2. Node has one child
3. Node has two children (replace with inorder successor/predecessor)
```

**Example 1 — node with two children**
```text
        8
       / \
      3   10
     / \
    1   6
       / \
      4   7

key = 3
```
Output:
```text
        8
       / \
      4   10
     / \
    1   6
         \
          7
```

**Example 2 — node with one child**
```text
        8
       / \
      3   10

key = 10
```
Output:
```text
        8
       /
      3
```

**Example 3 — leaf node**
```text
        8
       / \
      3   10
     / \
    1   6
       / \
      4   7

key = 1
```
Output:
```text
        8
       / \
      3   10
       \
        6
       / \
      4   7
```

---

## 4. Find Minimum in BST

Given the root of a BST, return the minimum value present in the tree.

**Example 1**
```text
        8
       / \
      3   10
     /
    1
```
Output: **1**

**Example 2**
```text
        5
       /
      2
       \
        3
```
Output: **2**

---

## 5. Find Maximum in BST

Given the root of a BST, return the maximum value present in the tree.

**Example 1**
```text
        8
       / \
      3   10
           \
            14
```
Output: **14**

**Example 2**
```text
        5
       / \
      2   9
```
Output: **9**

---

## 6. Floor in BST

Given a BST and an integer `key`, find the **largest value in the BST that is less than or equal to `key`**.

**Example 1**
```text
        8
       / \
      3   10
     / \
    1   6

key = 7
```
Output: **6**

**Example 2**
```text
        8
       / \
      3   10
     / \
    1   6

key = 0
```
Output: **None (no value ≤ 0)**

---

## 7. Ceil in BST

Given a BST and an integer `key`, find the **smallest value in the BST that is greater than or equal to `key`**.

**Example 1**
```text
        8
       / \
      3   10
     / \
    1   6

key = 7
```
Output: **8**

**Example 2**
```text
        8
       / \
      3   10
     / \
    1   6

key = 11
```
Output: **None (no value ≥ 11)**

---

## 8. Inorder Traversal of BST

Given a BST, return its inorder traversal.

**Example 1**
```text
        8
       / \
      3   10
     / \
    1   6
```
Output: **[1, 3, 6, 8, 10]**

**Example 2**
```text
        5
       / \
      2   9
     /   /
    1   7
```
Output: **[1, 2, 5, 7, 9]**

### 🎯 Key Observation
```text
BST → Inorder → Sorted Order
```

---

# 🔵 LEVEL 10 — BST Interview Problems

## 1. Validate BST

Given a binary tree, determine whether it is a valid BST.

**Example 1**
```text
        8
       / \
      3   10
     / \
    1   6
```
Output: **true**

**Example 2**
```text
        8
       / \
      3   10
         /
        6
```
Output: **false**

**Example 3**
```text
        5
       / \
      1   4
         / \
        3   6
```
Output: **false**
Explanation: Node 3 is in the right subtree of 5 but 3 < 5, violating the BST property.

---

## 2. Kth Smallest Element in BST

Given a BST and an integer `k`, return the kth smallest value.

**Example 1**
```text
        8
       / \
      3   10
     / \
    1   6
       / \
      4   7

k = 3
```
Sorted order: `[1, 3, 4, 6, 7, 8, 10]` → Output: **4**

**Example 2**
```text
        5
       / \
      2   9
```
k = 1 → Output: **2**

---

## 3. Kth Largest Element in BST

Given a BST and an integer `k`, return the kth largest value.

**Example 1**
```text
        8
       / \
      3   10
     / \
    1   6
       / \
      4   7

k = 2
```
Sorted order: `[1, 3, 4, 6, 7, 8, 10]` → Output: **8**

**Example 2**
```text
        5
       / \
      2   9
```
k = 1 → Output: **9**

---

## 4. Lowest Common Ancestor in BST

Given a BST and two nodes `p` and `q`, find their Lowest Common Ancestor. (Compare against the general-tree LCA in Level 8 — here you can use ordering to decide direction instead of searching both subtrees.)

**Example 1**
```text
        8
       / \
      3   10
     / \    \
    1   6    14
       / \
      4   7

p = 4
q = 7
```
Output: **6**

**Example 2**
```text
        8
       / \
      3   10
     / \    \
    1   6    14

p = 1
q = 14
```
Output: **8**

---

## 5. Inorder Predecessor

Given a BST and a target node, find its inorder predecessor — the **largest value smaller than the target**.

**Example 1**
```text
        8
       / \
      3   10
     / \
    1   6
       / \
      4   7

target = 6
```
Output: **4**

**Example 2**
```text
        8
       / \
      3   10
     / \
    1   6
       / \
      4   7

target = 8
```
Output: **7**

---

## 6. Inorder Successor

Given a BST and a target node, find its inorder successor — the **smallest value greater than the target**.

**Example 1**
```text
        8
       / \
      3   10
     / \
    1   6
       / \
      4   7

target = 6
```
Output: **7**

**Example 2**
```text
        8
       / \
      3   10
     / \
    1   6
       / \
      4   7

target = 7
```
Output: **8**

---

## 7. Two Sum in BST

Given a BST and an integer `target`, determine whether there are two different nodes whose values add up to the target.

**Example 1**
```text
        5
       / \
      3   7
     / \   \
    2   4   8

target = 9
```
Output: **true** (2 + 7 = 9)

**Example 2**
```text
        5
       / \
      3   7
     / \   \
    2   4   8

target = 20
```
Output: **false**

---

## 8. Recover BST

Two nodes in a BST have been swapped by mistake. Recover the BST without changing its structure.

**Example 1**
```text
        3
       / \
      1   4
         /
        2
```
The values `2` and `3` were swapped.
Output:
```text
        2
       / \
      1   4
         /
        3
```

**Example 2**
```text
        1
       / \
      2   3
```
The values `1` and `2` were swapped.
Output:
```text
        2
       / \
      1   3
```

---

## 9. Convert BST to Greater Tree

Given a BST, replace every node's value with the sum of all values greater than or equal to that node.

**Example 1**
```text
        4
       / \
      1   6
     / \ / \
    0  2 5  7
```
Output:
```text
        22
       /  \
      27   13
     / \  / \
    28 25 18 7
```

**Example 2**
```text
        2
       / \
      0   3
```
Output:
```text
        5
       / \
      5   3
```

---

# 🔴 LEVEL 11 — Construct Binary Trees

## 1. Construct Binary Tree from Preorder and Inorder

**Example 1**
```text
preorder = [3, 9, 20, 15, 7]
inorder  = [9, 3, 15, 20, 7]
```
Output:
```text
        3
       / \
      9   20
         /  \
        15   7
```

**Example 2**
```text
preorder = [1, 2, 3]
inorder  = [2, 1, 3]
```
Output:
```text
        1
       / \
      2   3
```

---

## 2. Construct Binary Tree from Postorder and Inorder

**Example 1**
```text
inorder   = [9, 3, 15, 20, 7]
postorder = [9, 15, 7, 20, 3]
```
Output:
```text
        3
       / \
      9   20
         /  \
        15   7
```

**Example 2**
```text
inorder   = [2, 1, 3]
postorder = [2, 3, 1]
```
Output:
```text
        1
       / \
      2   3
```

---

## 3. Construct BST from Preorder

**Example 1**
```text
preorder = [8, 5, 1, 7, 10, 12]
```
Output:
```text
        8
       / \
      5   10
     / \    \
    1   7    12
```

**Example 2**
```text
preorder = [4, 2, 1, 3, 6, 5]
```
Output:
```text
        4
       / \
      2   6
     / \  /
    1  3 5
```

---

## 4. Sorted Array to Balanced BST

**Example 1**
```text
nums = [-10, -3, 0, 5, 9]
```
Output:
```text
        0
       / \
     -10   5
       \    \
       -3    9
```

**Example 2**
```text
nums = [1, 2, 3, 4, 5]
```
Output:
```text
        3
       / \
      1   4
       \   \
        2   5
```

---

## 5. Sorted Linked List to BST

**Example 1**
```text
head = [-10, -3, 0, 5, 9]
```
Output:
```text
        0
       / \
     -10   5
       \    \
       -3    9
```

**Example 2**
```text
head = [1, 2, 3]
```
Output:
```text
        2
       / \
      1   3
```

---

# 🔴 LEVEL 12 — Tree Views & Special Traversals

## 1. Left View of Binary Tree
Nodes visible when looking at the tree from the left side.

**Example 1**
```text
        1
       / \
      2   3
       \   \
        5   4
```
Output: **[1, 2, 5]**

**Example 2**
```text
        1
       / \
      2   3
     /
    4
```
Output: **[1, 2, 4]**

---

## 2. Right View of Binary Tree
Nodes visible when looking at the tree from the right side.

**Example 1**
```text
        1
       / \
      2   3
       \   \
        5   4
```
Output: **[1, 3, 4]**

**Example 2**
```text
        1
       / \
      2   3
     /
    4
```
Output: **[1, 3, 4]**

---

## 3. Top View of Binary Tree
Nodes visible when looking at the tree from the top.

**Example 1**
```text
        1
       / \
      2   3
       \ / \
        5 6  7
```
Output: **[2, 1, 3, 7]**

**Example 2**
```text
        1
       / \
      2   3
     / \
    4   5
```
Output: **[4, 2, 1, 3]**

---

## 4. Bottom View of Binary Tree
Nodes visible when looking at the tree from the bottom.

**Example 1**
```text
        1
       / \
      2   3
       \ / \
        5 6  7
```
Output: **[2, 5, 6, 7]**

**Example 2**
```text
        1
       / \
      2   3
     / \
    4   5
```
Output: **[4, 5, 3]**

---

## 5. Vertical Order Traversal
Group nodes into vertical columns, left to right. When two nodes land in the same column **and** the same row, order them left-to-right by original position (or by value, if the variant requires tie-breaking by value — state which rule you're using).

**Example 1**
```text
        1
       / \
      2   3
     / \   \
    4   5   6
```
Output: **[[4], [2], [1, 5], [3], [6]]**

**Example 2**
```text
        3
       / \
      9   8
     / \ / \
    4  0 1  7
```
Output: **[[4], [9], [3, 0, 1], [8], [7]]**

**Example 3 — same column, same row**
```text
        1
       / \
      2   3
     / \ / \
    4  5 6  7
```
Output: **[[4], [2], [1, 5, 6], [3], [7]]**

---

## 6. Boundary Traversal
Return the boundary of a binary tree in this order:
```text
Root → Left Boundary → Leaf Nodes → Right Boundary (in reverse)
```

**Example 1**
```text
        1
       / \
      2   3
     / \   \
    4   5   6
       / \
      7   8
```
Output: **[1, 2, 4, 7, 8, 6, 3]**

**Example 2**
```text
        1
       / \
      2   3
```
Output: **[1, 2, 3]**

---

## 7. Diagonal Traversal
Return the nodes grouped according to their diagonal position.

**Example 1**
```text
        1
       / \
      2   3
     / \   \
    4   5   6
       / \
      7   8
```
Output: **[[1, 3, 6], [2, 5, 8], [4, 7]]**

**Example 2**
```text
        8
       / \
      3   10
     / \
    1   6
```
Output: **[[8, 10], [3, 6], [1]]**

---

## 8. Zigzag Level Order Traversal
Return the level-order traversal of a binary tree, alternating left-to-right and right-to-left.

**Example 1**
```text
        1
       / \
      2   3
     / \ / \
    4  5 6  7
```
Output: **[[1], [3, 2], [4, 5, 6, 7]]**

**Example 2**
```text
      10
     /  \
    20    30
   / \      \
  40 50      60
```
Output: **[[10], [30, 20], [40, 50, 60]]**

---

# 🔴 LEVEL 13 — Advanced Binary Tree Problems

## 1. Serialize Binary Tree
Convert a binary tree into a string representation that can later reconstruct the same tree.

**Example 1**
```text
        1
       / \
      2   3
         / \
        4   5
```
Output: **"1,2,null,null,3,4,null,null,5,null,null"**

**Example 2**
```text
        1
```
Output: **"1,null,null"**

---

## 2. Deserialize Binary Tree
Given a serialized binary tree, reconstruct the original binary tree.

**Example 1**
```text
"1,2,null,null,3,4,null,null,5,null,null"
```
Output:
```text
        1
       / \
      2   3
         / \
        4   5
```

**Example 2**
```text
"1,null,null"
```
Output: **1**

---

## 3. Flatten Binary Tree to Linked List
Flatten a binary tree into a linked list using the tree's right pointers, in **preorder** order.

**Example 1**
```text
        1
       / \
      2   5
     / \   \
    3   4   6
```
Output:
```text
1 → 2 → 3 → 4 → 5 → 6   (all via right pointers)
```

**Example 2**
```text
        1
       /
      2
```
Output: **1 → 2**

---

## 4. Morris Inorder Traversal
Given a binary tree, return its inorder traversal using **O(1) extra space**, excluding the output list.

**Example 1**
```text
        1
       / \
      2   3
     / \
    4   5
```
Output: **[4, 2, 5, 1, 3]**

**Example 2**
```text
        2
       /
      1
```
Output: **[1, 2]**

---

## 5. Morris Preorder Traversal
Given a binary tree, return its preorder traversal using **O(1) extra space**, excluding the output list.

**Example 1**
```text
        1
       / \
      2   3
     / \
    4   5
```
Output: **[1, 2, 4, 5, 3]**

**Example 2**
```text
        2
       /
      1
```
Output: **[2, 1]**

---

## 6. Burn Binary Tree
A fire starts at a given node. Every adjacent node takes one unit of time to burn. Return the minimum time required to burn the entire tree.

**Example 1**
```text
        1
       / \
      2   3
     / \
    4   5

start = 2
```
Burning: Time 0 → {2}, Time 1 → {1, 4, 5}, Time 2 → {3}
Output: **2**

**Example 2**
```text
        1
       / \
      2   3

start = 1
```
Output: **1**

---

## 7. Duplicate Subtrees
Given a binary tree, find all duplicate subtrees — two subtrees are duplicates if they have the same structure and node values.

**Example 1**
```text
        1
       / \
      2   3
     /   / \
    4   2   4
       /
      4
```
Output: **Duplicate subtrees: [4], [2,4]**

**Example 2**
```text
        2
       / \
      1   1
```
Output: **Duplicate subtrees: [1]**

---

## 8. Maximum Width of Binary Tree
Return the maximum width among all levels. Width includes the positions of null nodes between the leftmost and rightmost non-null nodes.

**Example 1**
```text
        1
       / \
      3   2
     /     \
    5       9
```
Output: **4**

**Example 2**
```text
        1
       / \
      2   3
```
Output: **2**

---

## 9. Count Complete Binary Tree Nodes
Given a complete binary tree, return the number of nodes.

**Example 1**
```text
        1
       / \
      2   3
     / \  /
    4  5 6
```
Output: **6**

**Example 2**
```text
        1
       / \
      2   3
```
Output: **3**

### ⭐ Important
For this problem, learn the **optimized approach** (using height comparisons to skip subtrees), not just the plain `O(N)` traversal.

---

## 10. House Robber III
Each node in a binary tree represents money stored in a house. You cannot rob two directly connected houses. Return the maximum amount of money you can rob.

**Example 1**
```text
        3
       / \
      2   3
       \   \
        3   1
```
Output: **7** (rob 3 + 3 + 1)

**Example 2**
```text
        3
       / \
      4   5
     / \   \
    1   3   1
```
Output: **9** (rob 5 + 3 + 1)

---

## 11. Binary Tree Cameras
Place the minimum number of cameras so that every node is monitored. A camera at a node monitors: itself, its parent, its left child, and its right child.

**Example 1**
```text
        0
       /
      0
     /
    0
   /
  0
```
Output: **2**

**Example 2**
```text
        0
       / \
      0   0
```
Output: **1**

---

## 📋 Duplicates removed in this merge (for reference)

| Removed from | Kept in | Problem |
|---|---|---|
| Level 3 | Level 1 | Count Nodes, Sum, Min, Max, Search |
| Level 3 | Level 4 | Maximum Depth, Minimum Depth |
| Level 5 | Level 2 | Level Order, Reverse Level Order |
| Level 5 | Level 12 | Left View, Right View, Zigzag |
| Level 6 | Level 8 | Nodes at Distance K |
| Level 13 | Level 8 | Nodes at Distance K |
| Level 10 (old) | Level 9 | Search, Insert, Delete, Floor, Ceil in BST |
| Level 12 (old "Vertical Traversal") | Level 12 (Vertical Order Traversal) | Same problem, merged |