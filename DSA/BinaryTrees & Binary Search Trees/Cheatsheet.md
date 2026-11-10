# 🌳 Binary Tree → BST → Advanced Tree — Pattern Cheat Sheet

Quick-revision table: every problem mapped to its underlying **pattern** and a one-line **trick** to jog your memory before solving.

---

## 🟢 LEVEL 1 — Binary Tree Fundamentals

| # | Problem | Pattern | Quick Trick |
|---|---|---|---|
| 1 | Count Number of Nodes | Simple recursion (post-order aggregate) | `return 1 + count(left) + count(right)` |
| 2 | Count Leaf Nodes | Simple recursion, leaf check | If `left==null && right==null` → 1, else sum children |
| 3 | Sum of All Nodes | Simple recursion (aggregate) | `return val + sum(left) + sum(right)` |
| 4 | Find Maximum Value | Simple recursion (aggregate) | `return max(val, max(left), max(right))` |
| 5 | Find Minimum Value | Simple recursion (aggregate) | `return min(val, min(left), min(right))` |
| 6 | Search for a Value | DFS early-exit | `val==target` → true, else recurse either side |

---

## 🟢 LEVEL 2 — Tree Traversals

| # | Problem | Pattern | Quick Trick |
|---|---|---|---|
| 1 | Preorder Traversal | DFS | Root → Left → Right |
| 2 | Inorder Traversal | DFS | Left → Root → Right |
| 3 | Postorder Traversal | DFS | Left → Right → Root |
| 4 | Level Order Traversal | BFS with Queue | Snapshot queue size per level |
| 5 | Reverse Level Order Traversal | BFS + reverse | Do normal BFS, reverse the final list (or prepend levels) |
| 6 | Recursive Level Order Traversal | DFS with depth param | Pass `depth`, append node to `result[depth]` |

---

## 🟢 LEVEL 3 — Basic Tree Recursion

| # | Problem | Pattern | Quick Trick |
|---|---|---|---|
| 1 | Same Tree | Structural recursion | Both null → true; both non-null & equal val → recurse both sides |
| 2 | Invert Binary Tree | Recursion + swap | Swap `left`/`right`, then recurse into both |
| 3 | Symmetric Tree | Mirror recursion | Compare `mirror(left, right)`: values equal, `left.left` vs `right.right`, `left.right` vs `right.left` |

---

## 🟡 LEVEL 4 — Height, Depth & Balanced Trees

| # | Problem | Pattern | Quick Trick |
|---|---|---|---|
| 1 | Maximum Depth | Bottom-up recursion | `1 + max(depth(left), depth(right))` |
| 2 | Minimum Depth | Bottom-up recursion (careful case) | If one child is null, don't take min through it — use the non-null side |
| 3 | Check Balanced Binary Tree | Bottom-up recursion + sentinel | Return height normally, but return `-1` the moment imbalance is found, and short-circuit |
| 4 | Find Deepest Node | BFS or DFS+track | BFS: last node dequeued; DFS: track `(node, depth)`, keep max |
| 5 | Find Depth of a Given Node | DFS with path length | Recurse passing current depth; return `-1` if never found |
| 6 | Find Height of a Given Node | Locate + measure | First find the node (DFS), then run normal height recursion from there |

---

## 🟡 LEVEL 5 — BFS / Level-Aggregation Problems

| # | Problem | Pattern | Quick Trick |
|---|---|---|---|
| 1 | Average of Levels | BFS per-level aggregate | `sum(level) / size(level)` |
| 2 | Maximum Value at Each Level | BFS per-level aggregate | Track max while draining each level's queue |
| 3 | Minimum Value at Each Level | BFS per-level aggregate | Track min while draining each level's queue |
| 4 | Maximum Level Sum | BFS per-level aggregate | Sum each level, keep the best sum + its index |
| 5 | Bottom Left Value | BFS (or DFS leftmost-first) | BFS left→right: last level's **first** node processed; or DFS: first node seen at max depth |

---

## 🟡 LEVEL 6 — Path Problems

| # | Problem | Pattern | Quick Trick |
|---|---|---|---|
| 1 | Path Sum | DFS carrying remaining target | Subtract `val` going down; check `remaining==0` at a leaf |
| 2 | Path Sum II | DFS + backtracking | Build path list going down, record copy at leaf, pop on the way back up |
| 3 | All Root-to-Leaf Paths | DFS + backtracking | Same as above, just record every leaf path (no sum condition) |
| 4 | Sum Root-to-Leaf Numbers | DFS carrying running number | `num = num*10 + val`, add to total at a leaf |
| 5 | Maximum Root-to-Leaf Sum | DFS carrying running sum | Track running sum going down, update max at each leaf |
| 6 | Minimum Root-to-Leaf Sum | DFS carrying running sum | Same as above, but track min |
| 7 | Count Paths With Target Sum | Prefix-sum + HashMap | Tree version of "subarray sum equals K" — carry a prefix-sum map down and backtrack it on the way up |

---

## 🔴 LEVEL 7 — Diameter & Maximum Path

| # | Problem | Pattern | Quick Trick |
|---|---|---|---|
| 1 | Diameter of Binary Tree | Height recursion + global update | At each node: `diameter = max(diameter, leftHt + rightHt)`; return `1 + max(leftHt, rightHt)` up |
| 2 | Longest Path Between Two Nodes | Same as Diameter | Identical pattern, phrased differently |
| 3 | Maximum Path Sum | Height recursion (with negatives clipped) + global update | `gain = max(leftGain,0) + max(rightGain,0)`; update global with `val+gain`; return `val + max(leftGain,0,rightGain,0)` up |
| 4 | Longest Univalue Path | Height recursion + value check | Only extend the "arm" if child's value matches; reset to 0 otherwise |

---

## 🔴 LEVEL 8 — Lowest Common Ancestor & Node Distance

| # | Problem | Pattern | Quick Trick |
|---|---|---|---|
| 1 | LCA of Two Nodes | Recursion, "found or bubble up" | If node is `p`/`q`, return it; if both sides return non-null, current node is LCA |
| 2 | LCA When Nodes May Not Exist | LCA + existence flags | Run normal LCA, but also track boolean flags for whether `p`/`q` were actually seen |
| 3 | LCA of Deepest Leaves | Recursion returning (depth, node) | At each node compare left/right depths; equal → this node is answer; else bubble up the deeper side |
| 4 | Distance Between Two Nodes | LCA + depth formula | `dist = depth(p) + depth(q) - 2*depth(LCA)` |
| 5 | Kth Ancestor | Path-to-node / binary lifting | Simplest: get root→target path as a list, index back `K` from the end |
| 6 | Nodes at Distance K | Parent map + BFS | Build a `child→parent` map (DFS/BFS), then BFS outward from target for `K` steps (parent + children count as neighbors) |

---

## 🔵 LEVEL 9 — BST Fundamentals

| # | Problem | Pattern | Quick Trick |
|---|---|---|---|
| 1 | Search in BST | Ordering-based descent | `target<val` → left, `target>val` → right, else found |
| 2 | Insert into BST | Ordering-based descent | Descend by comparison until you hit a `null`, insert there |
| 3 | Delete from BST | 3-case handling | Leaf → remove; one child → replace with child; two children → replace with inorder successor/predecessor, then delete that |
| 4 | Find Minimum in BST | Leftmost node | Go left until `left==null` |
| 5 | Find Maximum in BST | Rightmost node | Go right until `right==null` |
| 6 | Floor in BST | Ordering-based descent + track | Track last value `≤ key`; move right when `val≤key`, else left |
| 7 | Ceil in BST | Ordering-based descent + track | Track last value `≥ key`; move left when `val≥key`, else right |
| 8 | Inorder Traversal of BST | DFS | Inorder of a BST is always sorted — this *is* the "aha" |

---

## 🔵 LEVEL 10 — BST Interview Problems

| # | Problem | Pattern | Quick Trick |
|---|---|---|---|
| 1 | Validate BST | Recursion with (min, max) bounds | Pass down allowed `(min, max)` range; tighten it as you descend — a plain parent check isn't enough |
| 2 | Kth Smallest Element in BST | Inorder traversal + counter | Inorder (Left-Root-Right), stop when count hits `k` |
| 3 | Kth Largest Element in BST | Reverse-inorder + counter | Reverse inorder (Right-Root-Left), stop when count hits `k` |
| 4 | Lowest Common Ancestor in BST | Ordering-based descent | Both `<node` → go left; both `>node` → go right; else current node is the split point |
| 5 | Inorder Predecessor | BST search + track | While searching for target, track last node where `target > node.val` |
| 6 | Inorder Successor | BST search + track | While searching for target, track last node where `target < node.val` |
| 7 | Two Sum in BST | Inorder → two-pointer | Get sorted array via inorder, then classic two-pointer for the pair sum |
| 8 | Recover BST | Inorder + violation tracking | Walk inorder with a `prev` pointer; the two out-of-order nodes are your swapped pair |
| 9 | Convert BST to Greater Tree | Reverse-inorder + running sum | Right-Root-Left, carry a running sum, assign `node.val += runningSum` |

---

## 🔴 LEVEL 11 — Construct Binary Trees

| # | Problem | Pattern | Quick Trick |
|---|---|---|---|
| 1 | Construct from Preorder + Inorder | Root-finding + split | `preorder[0]` = root; find it in inorder to split left/right subarrays |
| 2 | Construct from Postorder + Inorder | Root-finding + split | `postorder[last]` = root; find it in inorder to split left/right subarrays |
| 3 | Construct BST from Preorder | Bounds-based recursion | `preorder[0]` = root; no inorder needed — use value bounds `(min,max)` to decide split, like BST validation |
| 4 | Sorted Array to Balanced BST | Divide & conquer | Middle element = root, recurse on left/right halves |
| 5 | Sorted Linked List to BST | Divide & conquer + slow/fast pointer | Find middle with slow/fast pointers, that node = root, recurse on both halves |

---

## 🔴 LEVEL 12 — Tree Views & Special Traversals

| # | Problem | Pattern | Quick Trick |
|---|---|---|---|
| 1 | Left View of Binary Tree | BFS/DFS first-per-level | BFS: first node dequeued each level; DFS: first visit at each depth |
| 2 | Right View of Binary Tree | BFS/DFS last-per-level | BFS: last node dequeued each level; DFS: visit right child before left, first visit at each depth |
| 3 | Top View of Binary Tree | HD (horizontal distance) + first-seen | Track `(HD, level)`; keep the **first** node seen at each HD (BFS order) |
| 4 | Bottom View of Binary Tree | HD + last-seen | Track `(HD, level)`; keep the **last** node seen at each HD |
| 5 | Vertical Order Traversal | HD + level grouping | Group nodes by HD; within a column sort by level (then value/position for ties) |
| 6 | Boundary Traversal | 3 stitched walks | Left boundary (skip leaves) + all leaves (DFS) + right boundary reversed |
| 7 | Diagonal Traversal | Diagonal ID propagation | Same diagonal ID going right; new (incremented) ID going left |
| 8 | Zigzag Level Order Traversal | BFS + alternate reversal | Normal BFS, reverse every other level's list before adding to result |

---

## 🔴 LEVEL 13 — Advanced Binary Tree Problems

| # | Problem | Pattern | Quick Trick |
|---|---|---|---|
| 1 | Serialize Binary Tree | Preorder DFS + null markers | Preorder string with explicit `"null"` tokens so structure is recoverable |
| 2 | Deserialize Binary Tree | Preorder reconstruction | Consume tokens one at a time (queue/index), recursively rebuild in preorder |
| 3 | Flatten Binary Tree to Linked List | Reverse-postorder threading | Process Right → Left → Root, keep a `prev` pointer, link `node.right = prev` |
| 4 | Morris Inorder Traversal | Threaded traversal (O(1) space) | Temporarily link current node's inorder predecessor's right pointer to current, then remove the thread after visiting |
| 5 | Morris Preorder Traversal | Threaded traversal (O(1) space) | Same threading as Morris Inorder, but print/visit **before** moving left |
| 6 | Burn Binary Tree | Parent map + BFS | Same setup as "Nodes at Distance K" — BFS outward from start node, count time steps until queue empties |
| 7 | Duplicate Subtrees | Postorder + subtree hashing | Serialize each subtree to a string bottom-up, count occurrences in a HashMap |
| 8 | Maximum Width of Binary Tree | BFS + index numbering | Assign each node a heap-style index (`2i`, `2i+1`); width = `lastIndex − firstIndex + 1` per level |
| 9 | Count Complete Binary Tree Nodes | Height comparison shortcut | Compare left-height vs right-height; if equal, left subtree is full — skip counting it node-by-node |
| 10 | House Robber III | Tree DP (pair state) | Each node returns `(robThis, skipThis)`; `robThis = val + skipLeft + skipRight`, `skipThis = max(robLeft,skipLeft)+max(robRight,skipRight)` |
| 11 | Binary Tree Cameras | Tree DP (3-state, greedy bottom-up) | States: uncovered / covered-no-camera / has-camera; place a camera only when a child is uncovered |

---

## 🧠 Meta-Patterns to Remember

| Pattern | Shows up in |
|---|---|
| **Bottom-up aggregation** (combine left + right, return to parent) | Level 1, 3, 4, 7 |
| **BFS level-by-level with queue-size snapshot** | Level 2, 5, 12 (views) |
| **DFS carrying state downward + backtracking** | Level 6 |
| **"Two return values" (local update + value passed up)** | Level 7 |
| **Ordering-based descent (skip half the tree)** | Level 9, 10, 11 (BST from preorder) |
| **Horizontal Distance (HD) coordinates** | Level 12 |
| **Parent map + BFS outward** | Level 8 (distance K), Level 13 (burn tree) |
| **Tree DP (per-node state pair)** | Level 13 (rob III, cameras) |
| **Threading (Morris)** | Level 13 |