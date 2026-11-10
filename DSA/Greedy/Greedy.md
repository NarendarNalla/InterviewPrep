Greedy Usually Has a "Sorting Signal"

A huge number of greedy problems contain a sorting step.

For example:

sort by end time
sort by price
sort by profit
sort by deadline
sort by ratio
sort by weight

Then:

for each item:
    make best possible choice

So when you see:

"Sort the input and then select elements..."


===================================================================
8. How Do We Identify a Greedy Problem?

This is extremely useful in interviews.

When you see a problem, ask these questions.

Question 1: Is it an optimization problem?

Look for words like:

Maximum
Minimum
Largest
Smallest
Fewest
Most
Earliest
Latest
Best

Example:

Find the maximum number of activities.

Potential greedy.

Question 2: Can I make a decision one step at a time?

For example:

Choose one activity
Choose another
Choose another
...

If yes, greedy might be possible.

===================================================================
13. General Greedy Template

A typical greedy algorithm looks like:

1. Understand what we're optimizing.

2. Identify the locally optimal choice.

3. Sort the candidates if necessary.

4. Start with an empty solution.

5. Repeatedly:
       choose the best available option
       check whether it is valid
       take it if valid

6. Return the solution.

In Java, it often becomes something like:

Arrays.sort(arr, comparator);

for (Item item : arr) {
    if (canTake(item)) {
        take(item);
    }
}

The hard part isn't the loop.

The hard part is:

Why is this choice safe?

===================================================================
18. One Important Warning

Don't conclude:

"Whenever I see intervals, sort by end time."

That's not always correct.

For example:

Merge Intervals → usually sort by start
Meeting Rooms → sort by start
Activity Selection → sort by end
Non-overlapping Intervals → sort by end

The correct sorting criterion depends on what you're optimizing.

That's why we learn the reasoning rather than memorizing patterns.

19. Your Greedy Thinking Checklist

For every problem, go through:

1. What am I optimizing?
        ↓
2. What is my local best choice?
        ↓
3. Why is that choice safe?
        ↓
4. Can I commit to it permanently?
        ↓
5. What smaller problem remains?
        ↓
6. Repeat