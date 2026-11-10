Common Greedy Patterns

As you learn greedy, you'll repeatedly encounter these patterns:

| Pattern                       | Typical idea                         |
| ----------------------------- | ------------------------------------ |
| **Sorting + Greedy**          | Sort items and make optimal choices  |
| **Intervals**                 | Usually sort by start/end time       |
| **Two Pointer + Greedy**      | Make best pairing/selection          |
| **Priority Queue + Greedy**   | Always choose current best           |
| **Minimum/Maximum selection** | Pick cheapest/largest useful option  |
| **Scheduling**                | Choose based on deadline/end time    |
| **Fractional problems**       | Choose highest value/weight          |
| **Resource allocation**       | Give resources to best candidate     |
| **Huffman Coding**            | Repeatedly combine smallest values   |
| **Jump problems**             | Maintain furthest reachable position |



Greedy Patterns You Should Learn

As we move forward, you'll see these repeatedly.

Pattern 1 — Sort + Select
Sort
↓
Choose best
↓
Continue

Examples:

Activity Selection
Assign Cookies
Non-overlapping Intervals
Pattern 2 — Sort + Two Pointers
Sort
↓
left / right
↓
Make best pairing

Examples:

Boats to Save People
Assign Cookies
Pattern 3 — Priority Queue + Greedy
Put candidates into heap
        ↓
Take best candidate
        ↓
Update heap
        ↓
Repeat

Examples:

Meeting Rooms II
IPO
Huffman Coding
Pattern 4 — Greedy + Intervals

Usually:

Sort intervals
↓
Compare current interval
↓
Take / reject

Examples:

Activity Selection
Merge Intervals
Non-overlapping Intervals
Minimum Arrows
Pattern 5 — Greedy + Reachability

Maintain the furthest point we can reach.

Examples:

Jump Game
Jump Game II
