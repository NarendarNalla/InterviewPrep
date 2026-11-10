# Graphs — Start With the Basics

## 1. What is a Graph?

A **Graph** is a collection of:

- **Vertices / Nodes** → objects
- **Edges** → connections between objects

Example:

```
    1
   / \
  2   3
   \ /
    4
```

Here:

- Nodes = `{1, 2, 3, 4}`
- Edges = `{1-2, 1-3, 2-4, 3-4}`

Think of:

| Real World | Graph |
|---|---|
| People | Nodes |
| Friendship | Edge |
| Cities | Nodes |
| Roads | Edges |
| Web pages | Nodes |
| Links | Edges |
| Courses | Nodes |
| Prerequisites | Edges |
| Computers | Nodes |
| Network connections | Edges |

---

## 2. Graph Terminology

These are the terms you should know first.

| Term | Meaning |
|---|---|
| Vertex / Node | An individual object |
| Edge | Connection between two nodes |
| Degree | Number of edges connected to a node |
| Path | Sequence of connected nodes |
| Cycle | Path that returns to starting node |
| Connected Graph | Every node can reach every other node |
| Component | Separate connected portion of a graph |
| Neighbor | Directly connected node |
| Source | Starting node |
| Destination | Target node |

Example:

```
      1
     / \
    2   3
    |
    4
```

For node 2:

- Neighbors = 1, 4
- Degree = 2

---

## 3. The Most Important Classification

Before solving graph problems, learn these categories.

```
                    GRAPH
                      |
          +-----------+-----------+
          |                       |
       Directed               Undirected
          |                       |
      A → B                    A — B
          |
     +----+----+
     |         |
  Weighted   Unweighted
```

But these properties can combine. For example:

- Directed + Weighted
- Undirected + Weighted
- Directed + Unweighted
- Undirected + Unweighted

---

## 4. Undirected Graph

An edge works in both directions.

```
1 ---- 2
```

means:

```
1 → 2
2 → 1
```

Example:

```
1 ---- 2
|      |
|      |
3 ---- 4
```

If you can travel from 1 to 2, you can also travel from 2 to 1.

Typical examples:

- Friendship
- Two-way roads
- Network connections

---

## 5. Directed Graph

An edge has a specific direction.

```
1 → 2
```

This means:

- 1 can go to 2
- but 2 cannot necessarily go to 1

Example:

```
1 → 2 → 3
    ↓
    4
```

Typical examples:

- Instagram follows
- Twitter/X follows
- Course prerequisites
- One-way roads

---

## 6. Weighted Graph

Edges have a value/cost.

```
    5
1 ------ 2
 \      /
  10   3
    \ /
     3
```

For example:

```
1 --5-- 2
```

could mean:

- City 1 → City 2, Distance = 5 km
- or Cost = ₹5
- or Time = 5 minutes

The edge weight represents some measurable value.

---

## 7. Unweighted Graph

Edges don't have a cost.

```
1 ---- 2
|      |
|      |
3 ---- 4
```

Every edge is essentially treated equally.

This becomes very important for **shortest path in an unweighted graph** — that's where BFS becomes extremely useful.

---

## 8. How Do We Represent a Graph?

This is extremely important for interviews. You should know three common representations:

1. Edge List
2. Adjacency Matrix
3. Adjacency List

> For DSA interviews, **Adjacency List** is the most important.

---

## 9. Edge List

Suppose we have:

```
1 ---- 2
|      |
|      |
3 ---- 4
```

Edges:

```
1 - 2
1 - 3
2 - 4
3 - 4
```

We can store:

```java
int[][] edges = {
    {1, 2},
    {1, 3},
    {2, 4},
    {3, 4}
};
```

Simple, but not usually the best representation for traversal.

---

## 10. Adjacency Matrix

We create a 2D matrix. For:

```
1 ---- 2
|      |
|      |
3 ---- 4
```

we can represent:

```
    1 2 3 4
  ----------
1 | 0 1 1 0
2 | 1 0 0 1
3 | 1 0 0 1
4 | 0 1 1 0
```

In Java:

```java
int[][] graph = new int[5][5];

graph[1][2] = 1;
graph[2][1] = 1;

graph[1][3] = 1;
graph[3][1] = 1;

graph[2][4] = 1;
graph[4][2] = 1;

graph[3][4] = 1;
graph[4][3] = 1;
```

**Advantage**

Checking whether an edge exists, `graph[1][2]`, takes **O(1)**.

**Disadvantage**

It requires **O(V²)** space. For a graph with millions of nodes, that's expensive.

---

## 11. Adjacency List ⭐⭐⭐⭐⭐

This is the representation you should become comfortable with. For:

```
1 ---- 2
|      |
|      |
3 ---- 4
```

we store:

```
1 → [2, 3]
2 → [1, 4]
3 → [1, 4]
4 → [2, 3]
```

In Java:

```java
List<List<Integer>> graph = new ArrayList<>();
```

Initialize:

```java
int n = 5;

for(int i = 0; i < n; i++) {
    graph.add(new ArrayList<>());
}
```

Add edges:

```java
graph.get(1).add(2);
graph.get(2).add(1);

graph.get(1).add(3);
graph.get(3).add(1);

graph.get(2).add(4);
graph.get(4).add(2);

graph.get(3).add(4);
graph.get(4).add(3);
```

Now `graph.get(1)` gives:

```
[2, 3]
```

---

## 12. Important Difference: Directed vs Undirected

This is a common interview mistake.

### Undirected

For `1 ---- 2` you must add both:

```java
graph.get(1).add(2);
graph.get(2).add(1);
```

### Directed

For `1 → 2` only:

```java
graph.get(1).add(2);
```

Don't add:

```java
graph.get(2).add(1);
```

---

## 13. Graph Traversal

Once graph representation is clear, the next major concept is: *How do we visit all the nodes?*

There are two fundamental algorithms:

```
Graph Traversal
      |
      +---- BFS
      |
      +---- DFS
```

These are the foundation of graph DSA.

---

## 14. BFS — Breadth First Search

BFS explores level by level.

Example:

```
        1
      /   \
     2     3
    / \     \
   4   5     6
```

BFS:

```
1
↓
2 3
↓
4 5 6
```

Traversal:

```
1 → 2 → 3 → 4 → 5 → 6
```

BFS uses a **Queue**. The key idea:

```
Queue
  ↓
First In First Out
```

---

## 15. DFS — Depth First Search

DFS goes as deep as possible first. Using the same graph:

```
        1
      /   \
     2     3
    / \     \
   4   5     6
```

One possible DFS:

```
1 → 2 → 4 → 5 → 3 → 6
```

DFS can be implemented using **recursion** or a **stack**.

---

## 16. BFS vs DFS

This is worth memorizing.

| BFS | DFS |
|---|---|
| Breadth first | Depth first |
| Uses Queue | Uses Stack / Recursion |
| Level by level | Goes deep |
| Good for shortest path in unweighted graph | Good for connectivity/cycle/path exploration |
| Often iterative | Often recursive |
| O(V + E) | O(V + E) |

Both generally run in:

```
Time = O(V + E)
```

where:

- `V` = number of vertices
- `E` = number of edges

---

## 17. The `visited[]` Array ⭐⭐⭐⭐⭐

One of the most important graph concepts. Consider:

```
1 ---- 2
|      |
|      |
3 ---- 4
```

If we traverse:

```
1 → 2 → 4 → 3
```

we may encounter already visited nodes. Therefore:

```java
boolean[] visited = new boolean[n];
```

When visiting:

```java
visited[node] = true;
```

Before visiting:

```java
if(!visited[node]) {
    // visit
}
```

This prevents:

- infinite loops
- processing the same node repeatedly

---

## 18. First Graph Problem You Should Learn

Don't start with Dijkstra or Topological Sort yet. Start with:

### Problem 1 — Print BFS Traversal

Given:

```
        1
      /   \
     2     3
    / \     \
   4   5     6
```

Return:

```
1 2 3 4 5 6
```

Concepts:

```
Graph
↓
Adjacency List
↓
Queue
↓
Visited[]
↓
BFS
```

Then learn:

1. Problem 2 → DFS Traversal
2. Problem 3 → Number of Connected Components
3. Problem 4 → Detect Cycle
4. Problem 5 → Shortest Path