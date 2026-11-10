# 🕸️ Graph Algorithms — Problem Set (with Explanations)

A structured, level-by-level practice sheet that takes you from **graph fundamentals** to **traversals, BFS/DFS problems, cycle detection and topological sorting**.

Every problem ends with a **🧠 Explanation** section showing how each example's output follows from its input.

## 📑 Table of Contents

| Level | Topic | Problems |
|:-----:|-------|:--------:|
| [**0**](#level-0--graph-fundamentals) | Graph Fundamentals | 0.1 – 0.6 |
| [**1**](#level-1--traversal) | Traversal | 1.1 – 1.6 |
| [**2**](#level-2--bfs--dfs-problems) | BFS / DFS Problems | 2.1 – 2.11 |
| [**3**](#level-3--cycle-detection--topological-sort) | Cycle Detection & Topological Sort | 3.1 – 3.9 |

---

# Level 0 — Graph Fundamentals

> 💡 These are **concept exercises**, not coding problems.

## 0.1 Graph Terminology

**Problem:** Given a graph with `V` vertices and `E` edges, identify its vertices, edges, the degree of each vertex, and whether a path exists between two given vertices.

**Example 1**

```text
V = 4
edges = [[0, 1], [1, 2], [2, 3]]
```

| | |
|---|---|
| **Vertices** | 0, 1, 2, 3 |
| **Edges** | 3 |
| **Degree** | deg(0)=1, deg(1)=2, deg(2)=2, deg(3)=1 |
| **Path from 0 to 3** | ✅ Yes |

**Example 2**

```text
V = 4
edges = [[0, 1], [2, 3]]
```

| | |
|---|---|
| **Path from 0 to 3** | ❌ No |

> The graph has **two connected components**.

### 🧠 Explanation


**Example 1**
- There are 3 edges: `0-1`, `1-2`, `2-3`.
- Vertex 0 is in 1 edge, vertex 1 is in 2 edges, vertex 2 is in 2 edges, vertex 3 is in 1 edge. So the degrees are 1, 2, 2, 1.
- `0 → 1 → 2 → 3` uses only given edges, so a path from 0 to 3 exists → **Yes**.

**Example 2**
- The edges `0-1` and `2-3` form two separate groups, `{0, 1}` and `{2, 3}`.
- No edge links the groups, so there is no path from 0 to 3 → **No**.

---

## 0.2 Directed vs. Undirected Graph

**Problem:** Given `V`, a list of edges, and a graph type, determine which vertices are reachable from a specified source. In a directed graph, follow edges **only in their given direction**.

**Example 1 — Undirected**

```text
V = 3
edges = [[0, 1], [1, 2]]
source = 2
```

```text
Reachable vertices: [0, 1, 2]
```

**Example 2 — Directed**

```text
V = 3
edges = [[0, 1], [1, 2]]
source = 2
```

```text
Reachable vertices: [2]
```

### 🧠 Explanation


**Example 1 — Undirected**
- Edge `1-2` works both ways, so from 2 we get to 1.
- Edge `0-1` works both ways, so from 1 we get to 0.
- Reachable: `[0, 1, 2]`.

**Example 2 — Directed**
- The edges are `0 → 1` and `1 → 2`. Vertex 2 has no edge going out of it.
- Reachable: `[2]`.

---

## 0.3 Weighted vs. Unweighted Graph

**Problem:** Given a graph, determine the number of edges in a path and, for a weighted graph, the total weight of that path.

**Example 1 — Unweighted**

```text
V = 3
edges = [[0, 1], [1, 2]]
path = 0 -> 1 -> 2
```

```text
Number of edges: 2
```

**Example 2 — Weighted**

```text
V = 3
weightedEdges = [[0, 1, 4], [1, 2, 7]]
path = 0 -> 1 -> 2
```

```text
Total path weight: 11
```

### 🧠 Explanation


**Example 1 — Unweighted**
- Path `0 -> 1 -> 2` uses edges `0-1` and `1-2` → **2 edges**.

**Example 2 — Weighted**
- Edge `0-1` has weight 4 and edge `1-2` has weight 7.
- `4 + 7 =` **11**.

---

## 0.4 Connected vs. Disconnected Graph

**Problem:** Given an undirected graph, determine whether every vertex is reachable from every other vertex.

**Example 1**

```text
V = 4
edges = [[0, 1], [1, 2], [2, 3]]
```

```text
Output: true
```

**Example 2**

```text
V = 5
edges = [[0, 1], [1, 2], [3, 4]]
```

```text
Output: false
```

### 🧠 Explanation


**Example 1**
- The edges `0-1`, `1-2`, `2-3` link all four vertices in one line, so every vertex can reach every other → **true**.

**Example 2**
- Edges `0-1`, `1-2` link `{0, 1, 2}`; edge `3-4` links `{3, 4}`.
- Nothing connects the two groups → **false**.

---

## 0.5 Cyclic vs. Acyclic Graph

**Problem:** Given an undirected graph, determine whether it contains a cycle.

**Example 1**

```text
V = 3
edges = [[0, 1], [1, 2], [2, 0]]
```

```text
Output: true
```

**Example 2**

```text
V = 4
edges = [[0, 1], [1, 2], [2, 3]]
```

```text
Output: false
```

### 🧠 Explanation


**Example 1**
- Edges `0-1`, `1-2`, `2-0` form a triangle. Going `0 → 1 → 2 → 0` returns to the start → **true**.

**Example 2**
- Edges `0-1`, `1-2`, `2-3` form a straight line. No route returns to a vertex already visited → **false**.

---

## 0.6 Graph Representations

**Problem:** Given `V` and an edge list, construct the graph in all three formats: **edge list**, **adjacency matrix**, and **adjacency list**.

**Example 1**

```text
V = 3
edges = [[0, 1], [1, 2]]
```

**Edge List**

```text
[[0, 1], [1, 2]]
```

**Adjacency Matrix**

```text
[
  [0, 1, 0],
  [1, 0, 1],
  [0, 1, 0]
]
```

**Adjacency List**

```text
0 -> [1]
1 -> [0, 2]
2 -> [1]
```

**Example 2**

```text
V = 4
edges = [[0, 2], [1, 3]]
```

**Adjacency List**

```text
0 -> [2]
1 -> [3]
2 -> [0]
3 -> [1]
```

> 📌 For an **undirected** graph, add each edge to **both** endpoints' adjacency lists.

### 🧠 Explanation


**Example 1** (`V = 3`, `edges = [[0,1],[1,2]]`)
- **Edge list:** same as the input.
- **Adjacency matrix:** edge `0-1` puts a 1 at row 0, column 1 and at row 1, column 0. Edge `1-2` puts a 1 at row 1, column 2 and at row 2, column 1. All other cells are 0.
- **Adjacency list:** vertex 0 touches only 1 → `[1]`; vertex 1 touches 0 and 2 → `[0, 2]`; vertex 2 touches only 1 → `[1]`.

**Example 2** (`V = 4`, `edges = [[0,2],[1,3]]`)
- Edge `0-2` → 0 lists 2, and 2 lists 0.
- Edge `1-3` → 1 lists 3, and 3 lists 1.

---

# Level 1 — Traversal

> 📌 **Convention:** For traversal examples, the graph is **undirected** and the adjacency list is built from the given edges. If more than one traversal order is possible, the outputs below assume neighbors are processed in **ascending order**.

## 1.1 BFS Traversal

**Problem:** Given an undirected graph and a starting vertex `S`, return the order in which vertices are visited using **Breadth-First Search**. Visit each vertex at most once.

**Example 1**

```text
V = 5
edges = [[0, 1], [0, 2], [1, 3], [1, 4]]
S = 0
```

```text
Output: [0, 1, 2, 3, 4]
```

**Example 2**

```text
V = 4
edges = [[0, 1], [1, 2], [2, 3]]
S = 2
```

```text
Output: [2, 1, 3, 0]
```

### 🧠 Explanation


**Example 1** (start 0)
- Start with `0`.
- Next come the neighbors of 0: `1`, `2`.
- Next come the new neighbors of 1: `3`, `4`. (2 has no new neighbors.)
- Output: `[0, 1, 2, 3, 4]`.

**Example 2** (start 2, line `0-1-2-3`)
- Start with `2`.
- Neighbors of 2 are `1` and `3`.
- The new neighbor of 1 is `0`.
- Output: `[2, 1, 3, 0]`.

---

## 1.2 DFS Traversal

**Problem:** Given an undirected graph and a starting vertex `S`, return the order in which vertices are visited using **Depth-First Search**. Visit each vertex at most once.

**Example 1**

```text
V = 5
edges = [[0, 1], [0, 2], [1, 3], [1, 4]]
S = 0
```

```text
Output: [0, 1, 3, 4, 2]
```

**Example 2**

```text
V = 4
edges = [[0, 1], [1, 2], [2, 3]]
S = 2
```

```text
Output: [2, 1, 0, 3]
```

### 🧠 Explanation


**Example 1** (start 0)
- Start with `0`, then go to its first neighbor `1`.
- From 1 go to `3`. Nothing new beyond 3, so return to 1.
- From 1 go to `4`. Return to 0.
- From 0 go to its other neighbor `2`.
- Output: `[0, 1, 3, 4, 2]`.

**Example 2** (start 2, line `0-1-2-3`)
- Start with `2`, go to its first neighbor `1`, then to `0`.
- Return to 2 and go to its other neighbor `3`.
- Output: `[2, 1, 0, 3]`.

---

## 1.3 Visited Array

**Problem:** Given a graph and a source vertex, use a **visited array** to report which vertices can be reached from the source. Each vertex must be processed only once, even if multiple paths lead to it.

**Example 1**

```text
V = 4
edges = [[0, 1], [0, 2], [1, 2], [2, 3]]
source = 0
```

```text
Output: [true, true, true, true]
```

**Example 2**

```text
V = 5
edges = [[0, 1], [2, 3]]
source = 0
```

```text
Output: [true, true, false, false, false]
```

### 🧠 Explanation


**Example 1** (source 0)
- From 0 we can reach 1 and 2. From 2 we can reach 3.
- All four vertices are reachable → `[true, true, true, true]`.

**Example 2** (source 0)
- From 0 we can reach only 1.
- Vertices 2 and 3 are linked only to each other, and 4 has no edges, so they are not reachable.
- Output: `[true, true, false, false, false]`.

---

## 1.4 Connected Components

**Problem:** Given an undirected graph with `V` vertices and `E` edges, count the number of connected components. Isolated vertices each form a component.

**Example 1**

```text
V = 5
edges = [[0, 1], [1, 2], [3, 4]]
```

```text
Output: 2
```

**Example 2**

```text
V = 6
edges = [[0, 1], [2, 3]]
```

```text
Output: 4
```

> The components are `{0,1}`, `{2,3}`, `{4}`, and `{5}`.

### 🧠 Explanation


**Example 1** (`V = 5`)
- `{0, 1, 2}` are linked together → 1 component.
- `{3, 4}` are linked together → 1 component.
- Total: **2**.

**Example 2** (`V = 6`)
- `{0, 1}` and `{2, 3}` are two components.
- Vertices 4 and 5 have no edges, so each is a component by itself.
- Total: **4**.

---

## 1.5 Traversing a Disconnected Graph

**Problem:** Given an undirected graph, return a traversal that covers **every** vertex, including vertices in disconnected components. Start a new BFS or DFS from each unvisited vertex, in increasing vertex order.

**Example 1**

```text
V = 6
edges = [[0, 1], [1, 2], [3, 4]]
```

```text
Output (BFS component by component): [0, 1, 2, 3, 4, 5]
```

**Example 2**

```text
V = 5
edges = [[1, 2], [3, 4]]
```

```text
Output (BFS component by component): [0, 1, 2, 3, 4]
```

### 🧠 Explanation


**Example 1** (`V = 6`)
- Starting at 0 covers `0, 1, 2`.
- Vertex 3 is the next one not yet covered; it gives `3, 4`.
- Vertex 5 is the last one left.
- Output: `[0, 1, 2, 3, 4, 5]`.

**Example 2** (`V = 5`)
- Vertex 0 has no edges, so it comes alone: `0`.
- Next uncovered vertex is 1, giving `1, 2`.
- Next uncovered vertex is 3, giving `3, 4`.
- Output: `[0, 1, 2, 3, 4]`.

---

## 1.6 BFS vs. DFS

**Problem:** For a given graph and source, perform **both** BFS and DFS and return their traversal orders. Use the same ascending-neighbor convention.

**Example 1**

```text
V = 5
edges = [[0, 1], [0, 2], [1, 3], [1, 4]]
source = 0
```

| Traversal | Output |
|-----------|--------|
| **BFS** | `[0, 1, 2, 3, 4]` |
| **DFS** | `[0, 1, 3, 4, 2]` |

**Example 2**

```text
V = 4
edges = [[0, 1], [1, 2], [2, 3]]
source = 0
```

| Traversal | Output |
|-----------|--------|
| **BFS** | `[0, 1, 2, 3]` |
| **DFS** | `[0, 1, 2, 3]` |

### 🧠 Explanation


**Example 1**
- **BFS:** `0`, then its neighbors `1, 2`, then the neighbors of 1: `3, 4` → `[0, 1, 2, 3, 4]`.
- **DFS:** `0`, then `1`, then `3`, then `4` (still inside 1's branch), and only then `2` → `[0, 1, 3, 4, 2]`.

**Example 2** (line `0-1-2-3`)
- Each vertex has just one new neighbor, so both give `[0, 1, 2, 3]`.

---

# Level 2 — BFS / DFS Problems

## 2.1 Number of Provinces

**Problem:** There are `n` cities. A connection matrix `isConnected` is given, where `isConnected[i][j] = 1` means city `i` and city `j` are directly connected. Connections are bidirectional.

A **province** is a group of cities connected directly or indirectly. Return the number of provinces.

**Example 1**

```text
Input:
isConnected = [
  [1, 1, 0],
  [1, 1, 0],
  [0, 0, 1]
]

Output: 2
```

**Example 2**

```text
Input:
isConnected = [
  [1, 0, 0],
  [0, 1, 0],
  [0, 0, 1]
]

Output: 3
```

**Example 3**

```text
Input:
isConnected = [
  [1, 1, 0, 0],
  [1, 1, 1, 0],
  [0, 1, 1, 0],
  [0, 0, 0, 1]
]

Output: 2
```

### 🧠 Explanation


**Example 1**
- Cities 0 and 1 are linked, so they are one province.
- City 2 is linked to no one else → its own province.
- Total: **2**.

**Example 2**
- Only the diagonal has 1s, so no two cities are linked → each city is a province.
- Total: **3**.

**Example 3**
- 0–1 are linked and 1–2 are linked, so 0, 1, 2 form one province.
- City 3 is linked to no one else → its own province.
- Total: **2**.

---

## 2.2 Number of Islands

**Problem:** Given an `m × n` grid containing `'1'` (land) and `'0'` (water), count the number of islands.

An island is a group of land cells connected **horizontally or vertically**. Diagonal connections do not count. Cells outside the grid are water.

**Example 1**

```text
Input:
grid = [
  ["1", "1", "0", "0"],
  ["1", "1", "0", "1"],
  ["0", "0", "0", "1"],
  ["1", "0", "0", "0"]
]

Output: 3
```

**Example 2**

```text
Input:
grid = [
  ["1", "0", "1"],
  ["0", "1", "0"],
  ["1", "0", "1"]
]

Output: 5
```

**Example 3**

```text
Input:
grid = [
  ["0", "0"],
  ["0", "0"]
]

Output: 0
```

### 🧠 Explanation


**Example 1**
- The four `1`s in the top-left corner touch each other → island 1.
- `(1,3)` and `(2,3)` are directly above and below each other → island 2.
- `(3,0)` is surrounded by water → island 3.
- Total: **3**.

**Example 2**
- The `1`s are at `(0,0)`, `(0,2)`, `(1,1)`, `(2,0)`, `(2,2)`. They only touch diagonally, which does not count, so each is its own island.
- Total: **5**.

**Example 3**
- There is no `'1'` → **0**.

---

## 2.3 Flood Fill Algorithm

**Problem:** Given an `m × n` image, a starting cell `(sr, sc)`, and a new color, change the starting cell and all **4-directionally connected** cells of the same original color to the new color. Return the modified image.

**Example 1**

```text
Input:
image = [
  [1, 1, 1],
  [1, 1, 0],
  [1, 0, 1]
]
sr = 1, sc = 1, newColor = 2

Output:
[
  [2, 2, 2],
  [2, 2, 0],
  [2, 0, 1]
]
```

**Example 2**

```text
Input:
image = [
  [0, 0, 0],
  [0, 0, 0]
]
sr = 0, sc = 0, newColor = 0

Output:
[
  [0, 0, 0],
  [0, 0, 0]
]
```

### 🧠 Explanation


**Example 1** (start `(1,1)`, new color 2)
- The start cell has color 1. The `1`s touching it, directly or through other `1`s, are `(0,0)`, `(0,1)`, `(0,2)`, `(1,0)`, `(1,1)`, `(2,0)`. These become 2.
- `(1,2)` and `(2,1)` are 0, so they stay.
- `(2,2)` is a 1 but it only touches 0s, so it is not connected and stays 1.

**Example 2**
- The start cell is already 0 and the new color is 0, so nothing changes.

---

## 2.4 Clone Graph

**Problem:** Given a reference to a node in a connected undirected graph, create and return a **deep copy** of the graph.

Each node contains an integer value and a list of its neighbors. The clone must contain **new nodes**, while preserving the original graph's connections.

**Example 1**

```text
Input adjacency list:
1 -> [2, 4]
2 -> [1, 3]
3 -> [2, 4]
4 -> [1, 3]

Output adjacency list of clone:
1 -> [2, 4]
2 -> [1, 3]
3 -> [2, 4]
4 -> [1, 3]
```

**Example 2**

```text
Input adjacency list:
1 -> []

Output adjacency list of clone:
1 -> []
```

> ⚠️ The output graph must use **newly created nodes**, not the original nodes.

### 🧠 Explanation


**Example 1**
- The input graph: 1 is linked to 2 and 4; 2 to 1 and 3; 3 to 2 and 4; 4 to 1 and 3.
- The clone has the same four values with the same links, but built from new nodes. So the printed adjacency list looks identical to the input.

**Example 2**
- The input is a single node `1` with no neighbors.
- The clone is a single new node `1` with no neighbors.

---

## 2.5 Number of Enclaves

**Problem:** Given an `m × n` grid of `0`s and `1`s, a `1` represents land. A land cell can move to adjacent land cells horizontally or vertically.

Count the land cells from which it is **impossible** to reach the boundary of the grid.

**Example 1**

```text
Input:
grid = [
  [0, 0, 0, 0],
  [1, 0, 1, 0],
  [0, 1, 1, 0],
  [0, 0, 0, 0]
]

Output: 3
```

**Example 2**

```text
Input:
grid = [
  [0, 1, 0],
  [1, 1, 1],
  [0, 1, 0]
]

Output: 0
```

> All land cells can reach the boundary.

### 🧠 Explanation


**Example 1**
- Land cells: `(1,0)`, `(1,2)`, `(2,1)`, `(2,2)`.
- `(1,0)` is in the first column, so it is on the boundary.
- `(1,2)`, `(2,1)`, `(2,2)` are linked only to each other and are not on the edge, so they cannot reach the boundary.
- Total: **3**.

**Example 2**
- `(0,1)`, `(1,0)`, `(1,2)`, `(2,1)` are on the edge. The center `(1,1)` touches them, so it can reach the edge too.
- Every land cell can reach the boundary → **0**.

---

## 2.6 Surrounded Regions

**Problem:** Given an `m × n` board containing `'X'` and `'O'`, capture all `'O'` regions that are completely surrounded by `'X'`.

An `'O'` connected to a boundary `'O'` must remain unchanged. Connections are horizontal and vertical only.

**Example 1**

```text
Input:
board = [
  ["X", "X", "X", "X"],
  ["X", "O", "O", "X"],
  ["X", "X", "O", "X"],
  ["X", "O", "X", "X"]
]

Output:
[
  ["X", "X", "X", "X"],
  ["X", "X", "X", "X"],
  ["X", "X", "X", "X"],
  ["X", "O", "X", "X"]
]
```

**Example 2**

```text
Input:
board = [
  ["O", "O", "O"],
  ["O", "O", "O"],
  ["O", "O", "O"]
]

Output:
[
  ["O", "O", "O"],
  ["O", "O", "O"],
  ["O", "O", "O"]
]
```

### 🧠 Explanation


**Example 1**
- `(3,1)` is an `'O'` on the bottom edge, so it stays.
- `(1,1)`, `(1,2)`, `(2,2)` are `'O'`s linked to each other, with `'X'` all around and none on the edge, so they become `'X'`.

**Example 2**
- Every cell is `'O'`. The outer ring is on the edge and the center touches it, so no `'O'` is surrounded → unchanged.

---

## 2.7 Number of Distinct Islands

**Problem:** Given a binary grid, count the number of **distinct island shapes**. An island consists of horizontally or vertically connected `1`s.

Two islands have the same shape if one can be **translated** to match the other. Rotation and reflection do **not** count as the same shape.

**Example 1**

```text
Input:
grid = [
  [1, 1, 0, 0, 0],
  [1, 0, 0, 1, 1],
  [0, 0, 0, 1, 0],
  [1, 1, 0, 0, 0],
  [1, 0, 0, 0, 0]
]

Output: 1
```

> ✏️ **Correction:** the original sheet listed `2` here, but all three islands are the same “L” shape, so the correct answer is `1`.

**Example 2**

```text
Input:
grid = [
  [1, 1, 0],
  [1, 0, 0],
  [0, 0, 1]
]

Output: 2
```

### 🧠 Explanation


**Example 1**
- Island A: `(0,0)`, `(0,1)`, `(1,0)` → an L shape.
- Island B: `(1,3)`, `(1,4)`, `(2,3)` → the same L shape, just placed elsewhere.
- Island C: `(3,0)`, `(3,1)`, `(4,0)` → the same L shape again.
- Only one distinct shape → **1**.

**Example 2**
- Island 1: `(0,0)`, `(0,1)`, `(1,0)` → an L shape.
- Island 2: `(2,2)` → a single cell.
- Two different shapes → **2**.

---

## 2.8 Rotten Oranges

**Problem:** Given an `m × n` grid:

| Value | Meaning |
|:-----:|---------|
| `0` | Empty cell |
| `1` | Fresh orange 🍊 |
| `2` | Rotten orange 🤢 |

Every minute, each rotten orange makes its fresh neighbors rotten (up, down, left, right). Return the **minimum number of minutes** until no fresh oranges remain. If some fresh oranges can never rot, return `-1`.

**Example 1**

```text
Input:
grid = [
  [2, 1, 1],
  [1, 1, 0],
  [0, 1, 1]
]

Output: 4
```

**Example 2**

```text
Input:
grid = [
  [2, 1, 1],
  [0, 1, 1],
  [1, 0, 1]
]

Output: -1
```

**Example 3**

```text
Input:
grid = [
  [0, 2]
]

Output: 0
```

### 🧠 Explanation


**Example 1** (rotten orange at `(0,0)`)

| Minute | Oranges that turn rotten |
|:------:|--------------------------|
| 1 | `(0,1)`, `(1,0)` |
| 2 | `(0,2)`, `(1,1)` |
| 3 | `(2,1)` |
| 4 | `(2,2)` |

All fresh oranges are rotten after minute 4 → **4**.

**Example 2**
- All fresh oranges rot except `(2,0)`: its neighbors `(1,0)` and `(2,1)` are empty, so rot never reaches it → **-1**.

**Example 3**
- There are no fresh oranges → **0**.

---

## 2.9 Distance of Nearest Cell Having One

**Problem:** Given a binary grid, return a distance matrix where each cell contains the shortest distance to any cell containing `1`.

Movement is allowed horizontally and vertically. The distance is the number of steps. Assume the grid contains at least one `1`.

**Example 1**

```text
Input:
grid = [
  [0, 0, 0],
  [0, 1, 0],
  [0, 0, 0]
]

Output:
[
  [2, 1, 2],
  [1, 0, 1],
  [2, 1, 2]
]
```

**Example 2**

```text
Input:
grid = [
  [1, 0, 1],
  [0, 0, 0],
  [0, 0, 0]
]

Output:
[
  [0, 1, 0],
  [1, 2, 1],
  [2, 3, 2]
]
```

### 🧠 Explanation


**Example 1**
- The only `1` is in the center → distance 0.
- Up, down, left, right of the center are 1 step away.
- The four corners are 2 steps away (for example `(0,0) → (0,1) → (1,1)`).

**Example 2**
- `(0,0)` and `(0,2)` are `1`s → 0.
- `(0,1)`, `(1,0)`, `(1,2)` are 1 step from a `1`.
- `(1,1)`, `(2,0)`, `(2,2)` are 2 steps away.
- `(2,1)` is 3 steps from the nearest `1`.

---

## 2.10 Cycle Detection in an Undirected Graph

**Problem:** Given an undirected graph, determine whether it contains at least one cycle. The graph may be disconnected.

**Example 1**

```text
Input:
V = 4
edges = [[0, 1], [1, 2], [2, 0], [2, 3]]

Output: true
```

**Example 2**

```text
Input:
V = 4
edges = [[0, 1], [1, 2], [2, 3]]

Output: false
```

**Example 3**

```text
Input:
V = 5
edges = [[0, 1], [2, 3], [3, 4], [4, 2]]

Output: true
```

### 🧠 Explanation


**Example 1**
- Edges `0-1`, `1-2`, `2-0` form a triangle → a cycle. Edge `2-3` does not change that → **true**.

**Example 2**
- Edges `0-1`, `1-2`, `2-3` form a straight line → **false**.

**Example 3**
- `{0, 1}` is a single edge, no cycle.
- Edges `2-3`, `3-4`, `4-2` form a triangle → a cycle → **true**.

---

## 2.11 Bipartite Graph — BFS and DFS

**Problem:** Given an undirected graph, determine whether its vertices can be divided into **two groups** such that every edge connects vertices from different groups.

The graph may be disconnected.

**Example 1**

```text
Input:
V = 4
edges = [[0, 1], [1, 2], [2, 3], [3, 0]]

Output: true
```

**Example 2**

```text
Input:
V = 3
edges = [[0, 1], [1, 2], [2, 0]]

Output: false
```

**Example 3**

```text
Input:
V = 5
edges = [[0, 1], [2, 3]]

Output: true
```

### 🧠 Explanation


**Example 1**
- Edges form the square `0-1-2-3-0`. Put 0 and 2 in one group, and 1 and 3 in the other.
- Every edge joins one vertex from each group → **true**.

**Example 2**
- Edges form a triangle. If 0 is in group A, then 1 must be in B, and then 2 must be in A (because of edge `1-2`).
- But edge `2-0` then joins two vertices of group A → **false**.

**Example 3**
- Edges `0-1` and `2-3` each join two vertices that can go in different groups; vertex 4 has no edges → **true**.

---

# Level 3 — Cycle Detection & Topological Sort

## 3.1 Cycle Detection in an Undirected Graph — DFS + Parent / BFS + Parent

**Problem:** Given an undirected graph, return `true` if any connected component contains a cycle; otherwise return `false`.

You may solve it using either **DFS** or **BFS**. During traversal, track the **parent vertex** to avoid treating the edge back to the parent as a cycle.

**Example 1**

```text
Input:
V = 3
edges = [[0, 1], [1, 2], [2, 0]]

Output: true
```

**Example 2**

```text
Input:
V = 4
edges = [[0, 1], [1, 2], [2, 3]]

Output: false
```

**Example 3**

```text
Input:
V = 6
edges = [[0, 1], [2, 3], [3, 4], [4, 2]]

Output: true
```

### 🧠 Explanation


**Example 1**
- Edges `0-1`, `1-2`, `2-0` form a triangle → **true**.

**Example 2**
- Edges `0-1`, `1-2`, `2-3` form a straight line → **false**.

**Example 3** (`V = 6`)
- `{0, 1}` is a single edge, no cycle.
- Edges `2-3`, `3-4`, `4-2` form a triangle → a cycle.
- Vertex 5 has no edges.
- Output: **true**.

---

## 3.2 Cycle Detection in a Directed Graph — DFS + Recursion Stack

**Problem:** Given a **directed** graph, determine whether it contains a directed cycle.

**Example 1**

```text
Input:
V = 3
edges = [[0, 1], [1, 2], [2, 0]]

Output: true
```

**Example 2**

```text
Input:
V = 4
edges = [[0, 1], [1, 2], [2, 3]]

Output: false
```

**Example 3**

```text
Input:
V = 4
edges = [[0, 1], [2, 3], [3, 2]]

Output: true
```

### 🧠 Explanation


**Example 1**
- Following the arrows `0 → 1 → 2 → 0` returns to 0 → **true**.

**Example 2**
- Following the arrows `0 → 1 → 2 → 3` ends at 3, which has no outgoing arrow. Nothing leads back → **false**.

**Example 3**
- `0 → 1` leads nowhere else.
- `2 → 3` and `3 → 2` point at each other, which is a loop → **true**.

---

## 3.3 Cycle Detection in a Directed Graph — Kahn's Algorithm

**Problem:** Given a directed graph, determine whether it contains a cycle using **Kahn's topological sorting algorithm**.

Return `true` if the graph contains a cycle. Otherwise, return `false`. The graph may be disconnected.

**Example 1**

```text
Input:
V = 3
edges = [[0, 1], [1, 2], [2, 0]]

Output: true
```

**Example 2**

```text
Input:
V = 4
edges = [[0, 1], [1, 2], [2, 3]]

Output: false
```

### 🧠 Explanation


**Example 1**
- `0 → 1 → 2 → 0` loops back to the start → a cycle → **true**.

**Example 2**
- `0 → 1 → 2 → 3` is a one-way chain with no loop → **false**.

---

## 3.4 Topological Sort — DFS + Stack

**Problem:** Given a directed acyclic graph (DAG), return a topological ordering of its vertices. In a topological ordering, for every directed edge `u -> v`, `u` must appear **before** `v`.

If multiple valid orders exist, return any one of them.

**Example 1**

```text
Input:
V = 4
edges = [[0, 1], [0, 2], [1, 3], [2, 3]]

One valid output: [0, 2, 1, 3]
```

**Example 2**

```text
Input:
V = 3
edges = [[0, 1], [1, 2]]

Output: [0, 1, 2]
```

### 🧠 Explanation


**Example 1** (`0→1, 0→2, 1→3, 2→3`)
- 0 must come before 1 and 2. Both 1 and 2 must come before 3.
- `[0, 2, 1, 3]` follows all of these rules, so it is valid. (`[0, 1, 2, 3]` is also valid.)

**Example 2** (`0→1, 1→2`)
- 0 before 1, and 1 before 2, leaves only one order → `[0, 1, 2]`.

---

## 3.5 Topological Sort — Kahn's Algorithm

**Problem:** Given a directed acyclic graph, return any valid topological ordering using **Kahn's algorithm** (BFS based on indegrees).

If the graph contains a cycle, no topological ordering exists; return an empty list.

**Example 1**

```text
Input:
V = 4
edges = [[0, 1], [0, 2], [1, 3], [2, 3]]

One valid output: [0, 1, 2, 3]
```

**Example 2**

```text
Input:
V = 3
edges = [[0, 1], [1, 2], [2, 0]]

Output: []
```

### 🧠 Explanation


**Example 1** (`0→1, 0→2, 1→3, 2→3`)
- 0 must come before 1 and 2, and both of those must come before 3.
- Output `[0, 1, 2, 3]` follows these rules.

**Example 2** (`0→1, 1→2, 2→0`)
- The three vertices form a loop, so no vertex can be placed before the others → `[]`.

---

## 3.6 Course Schedule I

**Problem:** There are `numCourses` courses numbered from `0` to `numCourses - 1`. Each prerequisite pair `[a, b]` means you must complete course `b` **before** taking course `a`.

Return `true` if it is possible to finish all courses; otherwise return `false`.

**Example 1**

```text
Input:
numCourses = 2
prerequisites = [[1, 0]]

Output: true
```

**Example 2**

```text
Input:
numCourses = 2
prerequisites = [[1, 0], [0, 1]]

Output: false
```

**Example 3**

```text
Input:
numCourses = 4
prerequisites = [[1, 0], [2, 0], [3, 1], [3, 2]]

Output: true
```

### 🧠 Explanation


A pair `[a, b]` means course `b` must be taken before course `a`.

**Example 1**
- `[1, 0]` → take 0, then 1. No conflict → **true**.

**Example 2**
- `[1, 0]` needs 0 before 1, and `[0, 1]` needs 1 before 0. Both cannot hold → **false**.

**Example 3**
- `[1,0]`, `[2,0]` → 0 comes before 1 and 2.
- `[3,1]`, `[3,2]` → 1 and 2 come before 3.
- The order `0, 1, 2, 3` satisfies all of them → **true**.

---

## 3.7 Course Schedule II

**Problem:** Given `numCourses` and prerequisite pairs `[a, b]`, return an ordering of courses that allows you to finish all courses.

If it is impossible to finish all courses due to a cycle, return an empty array.

**Example 1**

```text
Input:
numCourses = 2
prerequisites = [[1, 0]]

Output: [0, 1]
```

**Example 2**

```text
Input:
numCourses = 4
prerequisites = [[1, 0], [2, 0], [3, 1], [3, 2]]

One valid output: [0, 2, 1, 3]
```

**Example 3**

```text
Input:
numCourses = 2
prerequisites = [[1, 0], [0, 1]]

Output: []
```

### 🧠 Explanation


A pair `[a, b]` means course `b` must be taken before course `a`.

**Example 1**
- `[1, 0]` → 0 before 1 → `[0, 1]`.

**Example 2**
- 0 comes before 1 and 2; 1 and 2 come before 3.
- So 0 is first, 3 is last, and 1, 2 fill the middle. `[0, 2, 1, 3]` is one valid order.

**Example 3**
- 0 must come before 1, and 1 must come before 0. Impossible → `[]`.

---

## 3.8 Find Eventual Safe States

**Problem:** Given a directed graph, return all **eventual safe vertices** in ascending order.

A vertex is **safe** if every possible path starting from it eventually ends at a **terminal vertex** (a vertex with no outgoing edges). A vertex that can reach a directed cycle is **unsafe**.

**Example 1**

```text
Input:
V = 5
edges = [[0, 1], [1, 2], [2, 3], [3, 4]]

Output: [0, 1, 2, 3, 4]
```

**Example 2**

```text
Input:
V = 4
edges = [[0, 1], [1, 2], [2, 1], [3, 0]]

Output: []
```

**Example 3**

```text
Input:
V = 7
edges = [[0, 1], [1, 2], [2, 1], [3, 4], [4, 5], [5, 4], [6, 5]]

Output: []
```

### 🧠 Explanation


**Example 1** (chain `0→1→2→3→4`)
- Vertex 4 has no outgoing edge, so it is safe.
- Every other vertex has one path, and it ends at 4 → all are safe.
- Output: `[0, 1, 2, 3, 4]`.

**Example 2** (`0→1, 1→2, 2→1, 3→0`)
- 1 and 2 point at each other forever, so both are unsafe.
- 0 leads to 1, and 3 leads to 0, so both reach that loop and are unsafe.
- Output: `[]`.

**Example 3** (`0→1, 1→2, 2→1, 3→4, 4→5, 5→4, 6→5`)
- `1 ↔ 2` is a loop and `4 ↔ 5` is a loop.
- 0 leads into the first loop; 3 and 6 lead into the second.
- Every vertex is unsafe → `[]`.

---

## 3.9 Alien Dictionary

**Problem:** You are given a **sorted** list of words from an unknown language. The words use lowercase English letters, but the order of letters is unknown.

Infer a valid ordering of the distinct letters. If the input order is inconsistent (for example, a longer word appears before its own prefix), return an empty string. If multiple orders are valid, return any one.

**Example 1**

```text
Input:
words = ["baa", "abcd", "abca", "cab", "cad"]

One valid output: "bdac"
```

**Example 2**

```text
Input:
words = ["caa", "aaa", "aab"]

One valid output: "cab"
```

**Example 3**

```text
Input:
words = ["abc", "ab"]

Output: ""
```

### 🧠 Explanation


**Example 1** (`baa, abcd, abca, cab, cad`)
- `baa` comes before `abcd` → `b` comes before `a`.
- `abcd` comes before `abca` → `d` comes before `a`.
- `abca` comes before `cab` → `a` comes before `c`.
- `cab` comes before `cad` → `b` comes before `d`.
- Putting these together: `b`, then `d`, then `a`, then `c` → **`"bdac"`**.

**Example 2** (`caa, aaa, aab`)
- `caa` comes before `aaa` → `c` comes before `a`.
- `aaa` comes before `aab` → `a` comes before `b`.
- Together: **`"cab"`**.

**Example 3** (`abc, ab`)
- `ab` is the beginning of `abc`, so `ab` should come first. Here `abc` is listed first, which is not possible → **`""`**.

---

<p align="center"><em>Happy graphing! 🚀</em></p>