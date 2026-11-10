## 2. DFS: Depth First Search

**Mental model:** Go as deep as possible along one path, then backtrack.

**Data structure used:** Recursion (call stack) or an explicit stack

### Recursive Template

```java
void dfs(int node) {
    visited[node] = true;

    // Process current node

    for (int neighbor : graph[node]) {
        if (!visited[neighbor]) {
            dfs(neighbor);
        }
    }
}
```

### Iterative Template (using a stack)

```java
void dfsIterative(int start) {
    Deque<Integer> stack = new ArrayDeque<>();
    stack.push(start);

    while (!stack.isEmpty()) {
        int node = stack.pop();

        if (visited[node]) continue;
        visited[node] = true;

        // Process current node

        for (int neighbor : graph[node]) {
            if (!visited[neighbor]) {
                stack.push(neighbor);
            }
        }
    }
}
```

> Use the iterative version when the graph is very deep (e.g. a 10^5-node chain) to avoid `StackOverflowError`.

**Remember:** Visit → Mark → Explore neighbors recursively → Backtrack

### Complexity

| | Time | Space |
|---|---|---|
| Adjacency list | O(V + E) | O(V) (visited + recursion stack) |
| Adjacency matrix | O(V²) | O(V) |

### Common Applications

- Detecting cycles (directed and undirected graphs)
- Topological sort
- Connected components / counting islands
- Path existence between two nodes
- Backtracking problems (permutations, subsets, N-Queens)
- Tree traversals (preorder, inorder, postorder)
- Strongly connected components (Tarjan, Kosaraju)

---

## 3. BFS: Breadth First Search

**Mental model:** Visit all immediate neighbors first, then move to the next level.

**Data structure used:** Queue (FIFO)

### Template

```java
void bfs(int start) {
    Queue<Integer> queue = new LinkedList<>();

    visited[start] = true;
    queue.offer(start);

    while (!queue.isEmpty()) {
        int node = queue.poll();

        // Process current node

        for (int neighbor : graph[node]) {
            if (!visited[neighbor]) {
                visited[neighbor] = true;
                queue.offer(neighbor);
            }
        }
    }
}
```

**Remember:** Mark start → Add to queue → Remove → Explore neighbors → Repeat

### Shortest Path in an Unweighted Graph

```java
int[] bfsDistance(int start, int n) {
    int[] dist = new int[n];
    Arrays.fill(dist, -1);

    Queue<Integer> queue = new LinkedList<>();
    dist[start] = 0;
    queue.offer(start);

    while (!queue.isEmpty()) {
        int node = queue.poll();

        for (int neighbor : graph[node]) {
            if (dist[neighbor] == -1) {
                dist[neighbor] = dist[node] + 1;
                queue.offer(neighbor);
            }
        }
    }
    return dist;
}
```

### Level-by-Level Traversal

```java
while (!queue.isEmpty()) {
    int size = queue.size();          // nodes in the current level
    for (int i = 0; i < size; i++) {
        int node = queue.poll();
        // Process node at this level
        // Add unvisited neighbors to the queue
    }
    level++;
}
```

### Complexity

| | Time | Space |
|---|---|---|
| Adjacency list | O(V + E) | O(V) (queue + visited) |
| Adjacency matrix | O(V²) | O(V) |

### Common Applications

- Shortest path in unweighted graphs
- Level order traversal of trees
- Minimum steps / minimum moves problems (grids, word ladder)
- Multi-source BFS (rotting oranges, 01 matrix)
- Bipartite graph check
- Finding nodes at distance K

---

## DFS vs BFS: Quick Comparison

| Feature | DFS | BFS |
|---|---|---|
| Data structure | Stack / recursion | Queue |
| Exploration | Deep first | Level by level |
| Shortest path (unweighted) | No | **Yes** |
| Memory | O(height) in trees, O(V) worst case | O(width), can be large |
| Best for | Cycles, backtracking, topological sort | Shortest path, nearest target |
| Implementation | Simpler (recursive) | Slightly longer |

**Rule of thumb:**
- Need the **shortest path / minimum steps**? → **BFS**
- Need to **explore all possibilities / backtrack / detect cycles**? → **DFS**

---

## Common Mistakes

1. **Forgetting to mark visited**, which causes infinite loops on cyclic graphs.
2. **Marking visited when polling (BFS) instead of when enqueuing.** This lets the same node be added to the queue multiple times.
3. **Not handling disconnected graphs.** Run the traversal from every unvisited node:
```java
   for (int i = 0; i < n; i++) {
       if (!visited[i]) dfs(i);   // or bfs(i)
   }
```
4. **Using BFS for weighted shortest path.** Use Dijkstra instead (or 0-1 BFS for weights 0/1).
5. **Stack overflow in recursive DFS** on deep graphs. Switch to the iterative version.

---

## Bonus: Grid Traversal (very common in interviews)

```java
int[][] dirs = {{1,0}, {-1,0}, {0,1}, {0,-1}};

void dfs(int[][] grid, int r, int c, boolean[][] visited) {
    int rows = grid.length, cols = grid[0].length;

    if (r < 0 || c < 0 || r >= rows || c >= cols) return;
    if (visited[r][c] || grid[r][c] == 0) return;

    visited[r][c] = true;

    for (int[] d : dirs) {
        dfs(grid, r + d[0], c + d[1], visited);
    }
}
```

---

## Practice Problems

| Problem | Pattern |
|---|---|
| Number of Islands | DFS/BFS on grid |
| Flood Fill | DFS on grid |
| Clone Graph | DFS/BFS + map |
| Course Schedule | Cycle detection / topological sort |
| Rotting Oranges | Multi-source BFS |
| Word Ladder | BFS shortest path |
| Binary Tree Level Order Traversal | Level-by-level BFS |
| Is Graph Bipartite? | BFS/DFS coloring |