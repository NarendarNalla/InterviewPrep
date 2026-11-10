Source Chatgpt :
https://chatgpt.com/c/6a85d8f0-a348-83e8-8d0e-974bd875c852


Heap Property
------------------
Rule 1 : Complete Binary Tree - all levels must have left and right node, except last level but must start from left to right
Rule 2 : Min & Max heaps

MIN Heap : parent node <= left & right
MAX Heap : parent node >= left & right

Binary Search Tree ( BST) : left < parent < right

Heaps values are stored in array form

parent = (i-1)/2;
left = 2*i + 1;
right = 2*i + 2;

Heaps are used to find the repeated max || min values
Can perform - peek, insert, delete, build heap

To Satify Min Heap/ Max Heap
--------------------------
Insert - Insert at end  ( heap property voilates) -  perform heapify up/ bubble up - O(logn)
Delete - move last node to top ( heap property voilates ) - perform hepaify down / bubble down

Heaps are used to implement the Priority Queue
By default Priority Queue  - Min Heap

Works on Priority Order
---------------------------------
PriorityQueue<Integer> pq = new PriorityQueue<Integer>(Collections.reverseOrder()) - Max Heap
or
PriorityQueue<Integer> pq = new PriorityQueue<Integer>((a, b) - b-a); - Max Heap 


Important distinction:
Heap is the underlying data structure.

PriorityQueue is an abstract data structure / ADT that can be implemented using a heap.


Where Heaps are used in DSA
---------------------------------
Top K Problems - Kth largest & smallest elements, top k elemenets, k largest and smallest elements
Scheduling
Merging - Merge k sorted array or linked lists
Graphs - Djkrishtra etccc


-------------------------
The Mental Model You Should Remember

Think of Heap as:

             HEAP
               |
       ┌───────┴───────┐
       |               |
    MIN HEAP        MAX HEAP
       |               |
   smallest          largest
      root             root

And operations:
---------------------------------
INSERT
   ↓
Add at end
   ↓
Heapify Up
   ↓
O(log n)
---------------------------------
DELETE ROOT
   ↓
Move last → root
   ↓
Heapify Down
   ↓
O(log n)
---------------------------------
PEEK
   ↓
Root
   ↓
O(1)
---------------------------------

Heap can also be used for sorting.

For ascending order, commonly:

Build Max Heap
     ↓
Move maximum to end
     ↓
Heapify remaining elements
     ↓
Repeat

-------------------------------------------------------
13. Insert + Delete Together

You should now understand this complete picture:

INSERT
  |
  ↓
Add at END
  |
  ↓
Heapify UP
  |
  ↓
Compare with parent
  |
  ↓
Swap if necessary

And:

DELETE ROOT
  |
  ↓
Move LAST → ROOT
  |
  ↓
Heapify DOWN
  |
  ↓
Compare children
  |
  ↓
Choose appropriate child
  |
  ↓
Swap if necessary

This is the core of Heap.
-----------------------------------------------------------------

Bottom Up Heapify - O(n) ..?

=======================================================================
Insert => add → end → heapify up

Delete root => last → root → heapify down

Build Heap
-----------
last non-leaf → 0
heapify down
O(n)

Top K
-------------
K largest  → Min Heap
K smallest → Max Heap