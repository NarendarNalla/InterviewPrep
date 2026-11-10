
# 🌳 Binary Tree → BST → Advanced Tree — Problem Count Summary

| Level | Topic | Problems |
|---|---|---|
| Level 1 | Binary Tree Fundamentals | 6 |
| Level 2 | Tree Traversals | 6 |
| Level 3 | Basic Tree Recursion | 3 |
| Level 4 | Height, Depth & Balanced Trees | 6 |
| Level 5 | BFS / Level-Aggregation Problems | 5 |
| Level 6 | Path Problems | 7 |
| Level 7 | Diameter & Maximum Path | 4 |
| Level 8 | LCA & Node Distance | 6 |
| Level 9 | BST Fundamentals | 8 |
| Level 10 | BST Interview Problems | 9 |
| Level 11 | Construct Binary Trees | 5 |
| Level 12 | Tree Views & Special Traversals | 8 |
| Level 13 | Advanced Binary Tree Problems | 11 |
| **Total** | | **84** |

*Level 0 (Prerequisites) excluded — it's conceptual topics, not practice problems.*

# 🌳 Binary Tree → BST → Advanced Tree — Level Index

High-level view of every level: goal, core idea, and the problem list (titles only). Based on the deduplicated Level 0–13 structure.

---

## 🟢 LEVEL 0 — Prerequisites ⭐
Get the building blocks solid before touching trees.

```
class → object → reference
   Stack (DFS)   Queue (BFS)
```

**Topics**
1. Java Classes & Objects
2. References
3. Recursion
4. Stack
5. Queue
6. Big-O

🎯 **Level Goal**
Be comfortable with recursion, and know when a tree problem calls for a Stack (DFS) vs a Queue (BFS).

---

## 🟢 LEVEL 1 — Binary Tree Fundamentals ⭐⭐
Learn to walk a tree via `node.left` / `node.right` and combine results from both sides.

```
        Node
       /    \
    left    right
      ↓        ↓
   process   process
        combine
```

**Problems**
1. Count Number of Nodes
2. Count Leaf Nodes
3. Sum of All Nodes
4. Find Maximum Value
5. Find Minimum Value
6. Search for a Value

🎯 **Level Goal**
Understand: how do I aggregate a value from my left and right subtrees into one answer?

---

## 🟢 LEVEL 2 — Tree Traversals ⭐⭐
Learn the fixed visiting orders — every later problem builds on these.

```
Preorder    : Root → Left  → Right
Inorder     : Left → Root  → Right
Postorder   : Left → Right → Root
Level Order : BFS, level by level (Queue)
```

**Problems**
1. Preorder Traversal
2. Inorder Traversal
3. Postorder Traversal
4. Level Order Traversal
5. Reverse Level Order Traversal
6. Recursive Level Order Traversal

🎯 **Level Goal**
Know all three DFS orders and BFS cold, and recognize which one a problem secretly wants.

---

## 🟢 LEVEL 3 — Basic Tree Recursion ⭐⭐⭐
Now focus on the recursive tree pattern.

```
             Node
            /    \
         Left    Right
           ↓       ↓
        answer   answer
             \   /
             combine
```

**Problems**
1. Same Tree
2. Invert Binary Tree
3. Symmetric Tree

🎯 **Level Goal**
Understand: what should my function return to its parent?

---

## 🟡 LEVEL 4 — Height, Depth & Balanced Trees ⭐⭐⭐
Concepts

* Height
* Depth
* Height of a node
* Height of the tree
* Balanced Tree
* Skewed Tree

**Problems**
1. Maximum Depth
2. Minimum Depth
3. Check Balanced Binary Tree
4. Find Deepest Node
5. Find Depth of a Given Node
6. Find Height of a Given Node

🎯 **Level Goal**
Stop confusing height and depth — height counts up from a node, depth counts down from the root.

---

## 🟡 LEVEL 5 — BFS / Level-Aggregation Problems ⭐⭐⭐
Concepts

* Level-by-level Queue processing
* Tracking level size before popping
* Aggregating within a level (avg / max / min / sum) vs across levels

```
Queue: [root]
 → snapshot level size
 → pop that many, process, push children
 → repeat
```

**Problems**
1. Average of Levels
2. Maximum Value at Each Level
3. Minimum Value at Each Level
4. Maximum Level Sum
5. Bottom Left Value

🎯 **Level Goal**
Get comfortable computing per-level statistics using the "snapshot queue size" BFS pattern.

---

## 🟡 LEVEL 6 — Path Problems ⭐⭐⭐⭐
Concepts

* Root-to-leaf vs any-node-to-any-node paths
* Carrying running state (sum / path list) downward
* Deciding when to "commit" a path (usually at a leaf)

```
        Node
         │
   path so far + node.val
         │
    recurse into children
```

**Problems**
1. Path Sum
2. Path Sum II
3. All Root-to-Leaf Paths
4. Sum Root-to-Leaf Numbers
5. Maximum Root-to-Leaf Sum
6. Minimum Root-to-Leaf Sum
7. Count Paths With Target Sum

🎯 **Level Goal**
Learn to carry state down the recursion and decide the right moment to evaluate it.

---

## 🔴 LEVEL 7 — Diameter & Maximum Path ⭐⭐⭐⭐
Concepts

* A node can produce two different answers: one that **passes through** it, one that **returns upward**
* Diameter / max path = best "pass-through" value seen anywhere

```
           Node
          /    \
      leftHt   rightHt
          \    /
   passThrough = leftHt + rightHt (or leftSum+val+rightSum)
   return max(leftHt, rightHt) + 1   ← goes up to parent
```

**Problems**
1. Diameter of Binary Tree
2. Longest Path Between Two Nodes
3. Maximum Path Sum
4. Longest Univalue Path

🎯 **Level Goal**
Master the "two return values" trick: one bubbles up, one just updates a global best.

---

## 🔴 LEVEL 8 — Lowest Common Ancestor & Node Distance ⭐⭐⭐⭐
Concepts

* LCA = the node where the search paths to `p` and `q` split apart
* `distance(p, q) = depth(p) + depth(q) − 2·depth(LCA)`
* "Distance K" problems need to travel **upward** too → parent map or BFS from target

**Problems**
1. LCA of Two Nodes
2. LCA When Nodes May Not Exist
3. LCA of Deepest Leaves
4. Distance Between Two Nodes
5. Kth Ancestor
6. Nodes at Distance K

🎯 **Level Goal**
Reframe ancestor/distance questions as "where do the paths merge?" or "BFS outward from a point, including upward."

---

## 🔵 LEVEL 9 — BST Fundamentals ⭐⭐⭐
The core BST idea: ordering lets you skip half the tree at every step.

```
        Node
       /    \
   smaller  bigger
   (go left) (go right)
```

**Problems**
1. Search in BST
2. Insert into BST
3. Delete from BST
4. Find Minimum in BST
5. Find Maximum in BST
6. Floor in BST
7. Ceil in BST
8. Inorder Traversal of BST

🎯 **Level Goal**
Stop doing full tree traversals — use `value < node.val` / `value > node.val` to move in O(h), not O(n).

---

## 🔵 LEVEL 10 — BST Interview Problems ⭐⭐⭐⭐
Concepts

* Inorder traversal of a BST = sorted array — many problems reduce to "sorted array" thinking
* Validate BST needs a (min, max) bound passed down, not just a local parent check
* Predecessor/Successor and Kth Smallest/Largest are all inorder-adjacent

**Problems**
1. Validate BST
2. Kth Smallest Element in BST
3. Kth Largest Element in BST
4. Lowest Common Ancestor in BST
5. Inorder Predecessor
6. Inorder Successor
7. Two Sum in BST
8. Recover BST
9. Convert BST to Greater Tree

🎯 **Level Goal**
Combine "inorder = sorted" with BST ordering to solve ranking, validation, and lookup problems without extra sorting.

---

## 🔴 LEVEL 11 — Construct Binary Trees ⭐⭐⭐⭐
Concepts

* Preorder → first element is always the root
* Postorder → last element is always the root
* Inorder → splits everything into left-subtree values and right-subtree values
* For BSTs, ordering alone (no inorder needed) is enough to reconstruct

**Problems**
1. Construct Binary Tree from Preorder and Inorder
2. Construct Binary Tree from Postorder and Inorder
3. Construct BST from Preorder
4. Sorted Array to Balanced BST
5. Sorted Linked List to BST

🎯 **Level Goal**
Learn to use one traversal to locate the root and another to find where the left/right subtrees split.

---

## 🔴 LEVEL 12 — Tree Views & Special Traversals ⭐⭐⭐⭐
Concepts

* Assign every node a **horizontal distance (HD)** from the root (left = −1, right = +1)
* Views/vertical/diagonal problems are really about grouping by `(level, HD)` or `HD` alone
* Boundary traversal = three separate walks (left edge, leaves, right edge) stitched together

```
HD:  -2  -1   0   1   2
           (root = 0)
```

**Problems**
1. Left View of Binary Tree
2. Right View of Binary Tree
3. Top View of Binary Tree
4. Bottom View of Binary Tree
5. Vertical Order Traversal
6. Boundary Traversal
7. Diagonal Traversal
8. Zigzag Level Order Traversal

🎯 **Level Goal**
Think in coordinates — `(level, horizontal distance)` — instead of pure recursion for every view problem.

---

## 🔴 LEVEL 13 — Advanced Binary Tree Problems ⭐⭐⭐⭐⭐
Concepts

* Morris Traversal: temporarily "thread" the tree via `null` right pointers to get O(1) space
* Tree DP: a node's answer depends on choices made at its children (rob / don't rob, cover / don't cover)
* Structural hashing: serialize each subtree to a string/key to detect duplicates

**Problems**
1. Serialize Binary Tree
2. Deserialize Binary Tree
3. Flatten Binary Tree to Linked List
4. Morris Inorder Traversal
5. Morris Preorder Traversal
6. Burn Binary Tree
7. Duplicate Subtrees
8. Maximum Width of Binary Tree
9. Count Complete Binary Tree Nodes
10. House Robber III
11. Binary Tree Cameras

🎯 **Level Goal**
Combine everything so far — recursion, BFS, and state-passing — into single techniques like Morris traversal and tree DP.

---

### Note
Problem lists here reflect the deduplicated set from the merged practice sheet (e.g. Max/Min Depth appears once under Level 4, "Nodes at Distance K" once under Level 8, BST Search/Insert/Delete/Floor/Ceil once under Level 9). Let me know if you'd instead like the original, non-deduplicated lists per level.