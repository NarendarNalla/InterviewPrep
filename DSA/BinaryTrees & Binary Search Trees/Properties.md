# Basic Binary Tree Properties

These are useful for interviews.

### Property 1
A binary tree node has at most:
```
2 children
```

### Property 2
For `N` nodes:
```
Edges = N - 1
```

### Property 3
Maximum nodes at level `L`:
```
2^L
```
assuming root is level `0`. So:
```
Level 0 → 1
Level 1 → 2
Level 2 → 4
Level 3 → 8
```

### Property 4
Maximum nodes in a binary tree of height `h`:
```
2^(h+1) - 1
```

### Property 5
Minimum height for `N` nodes is approximately:
```
log₂(N)
```
when the tree is balanced.