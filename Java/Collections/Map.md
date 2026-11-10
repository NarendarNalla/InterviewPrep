Java Collections Framework — Map, HashMap, SortedMap & NavigableMap
This guide follows the same interview-preparation format as your List and Queue notes:
1. Map interface and hierarchy
2. HashMap internal implementation
3. Capacity, load factor, hashing and collisions
4. HashMap methods and examples
5. HashMap, LinkedHashMap, TreeMap, EnumMap and WeakHashMap
6. Java Streams with Map
7. Comparable and Comparator for TreeMap
8. SortedMap and NavigableMap methods
9. ConcurrentHashMap and multithreading
10. Interview questions and revision cheat sheet
The focus is on understanding internal implementation, choosing the right collection, time complexity, and explaining concepts clearly in interviews.

1. Map interface — overview
A Map stores data as key-value pairs.

Example:
101 → "Ravi"
102 → "Anu"
103 → "Kiran"


Here, 101, 102, and 103 are keys, while the names are values.

Important characteristics:
- Each key is unique.
- Different keys can have the same value.
- A key maps to at most one value.
- Most Map implementations allow null values; whether null keys are supported depends on the implementation.
- Map is part of the Java Collections Framework, but it does not extend the Collection interface.
Java Map hierarchy
Map (interface)

HashMap

Hash table


LinkedHashMap

Predictable iteration order


EnumMap

Enum keys



SortedMap (interface)


NavigableMap (interface)


TreeMap

Sorted keys and navigation operations


Other Map implementations
WeakHashMap

Weakly referenced keys


ConcurrentHashMap

Concurrent access




SortedMap extends Map, and NavigableMap extends SortedMap. TreeMap implements NavigableMap.
Basic example
import java.util.*;

public class MapDemo {
    public static void main(String[] args) {
        Map<Integer, String> students = new HashMap<>();

        students.put(101, "Ravi");
        students.put(102, "Anu");
        students.put(103, "Kiran");

        System.out.println(students.get(102)); // Anu

        students.put(102, "Priya"); // Updates existing key

        System.out.println(students.get(102)); // Priya
        System.out.println(students.containsKey(101)); // true
        System.out.println(students.containsValue("Ravi")); // true
    }
}


Interview point: Adding a value with an existing key replaces that key's previous value. It does not create a duplicate key.

2. HashMap — internal implementation
HashMap is one of the most frequently asked Java Collections interview topics.

A HashMap stores entries in an internal array called a table. Each array position is called a bucket. A bucket may contain no entries, one entry, or multiple entries when keys collide.
How does HashMap work internally?
Suppose we insert:
map.put("Ravi", 100);
map.put("Anu", 200);
map.put("Kiran", 300);


Conceptually, the process is:
1. Calculate the key's hash

hashCode() and HashMap's hash spreading


2. Calculate the bucket index

Index derived from hash and table capacity


3. Find or insert the entry

Check keys using hash and equals()


4. Store the key-value pair

In a bucket node, or in a treeified bucket when appropriate


Step 1: hashCode()
Java uses the key's hashCode() to help determine where an entry belongs.
Step 2: Bucket index
In modern OpenJDK HashMap implementations, the hash is spread and the bucket index is calculated using a power-of-two capacity:
index = (capacity - 1) & hash;


This is a simplified representation of the indexing step. The actual implementation first spreads the hash bits.
Step 3: Collision handling
A collision occurs when different keys map to the same bucket.

For example:
Bucket 0 → Entry A
Bucket 1 → Entry B → Entry C
Bucket 2 → Empty
Bucket 3 → Entry D


Multiple entries can occupy the same bucket. HashMap typically uses a linked structure for collisions, and sufficiently large collision-heavy buckets may become balanced trees under the implementation's thresholds and capacity conditions.
Step 4: How does HashMap find a key?
When you call:
map.get("Ravi");


HashMap calculates the key's hash, locates its bucket, and checks candidate entries using the hash and key equality.

This is why correctly implementing equals() and hashCode() matters for custom key classes.

Interview rule: If two objects are equal according to equals(), they must return the same hashCode(). The reverse is not required: different objects can have the same hash code.

3. HashMap capacity and load factor
These are important interview questions because they explain when a HashMap resizes.
What is capacity?
Capacity is the number of buckets in the internal table, not the number of key-value pairs.
What is load factor?
The load factor determines how full the table can become before HashMap resizes.

The default values in standard modern Java implementations are:
Property	Default
Initial capacity setting	16
Load factor	0.75
Threshold at capacity 16	12 entries
Typical resize behavior	Capacity doubles

The table is allocated lazily in common OpenJDK implementations, so constructing a HashMap does not necessarily allocate all 16 buckets immediately.
Example
Map<Integer, String> map = new HashMap<>(16, 0.75f);


The resize threshold is approximately:
\[
\text{Threshold}=\text{Capacity}\times\text{Load Factor}
\]

\[
16\times0.75=12
\]

When the threshold is exceeded, the table generally resizes. After capacity grows to 32, the new threshold is 24.
Why use a load factor of 0.75?
It balances memory usage and lookup performance.
- A lower load factor tends to use more memory but reduces crowding in buckets.
- A higher load factor tends to use memory more efficiently but can increase collisions.
Can we customize capacity and load factor?
Yes.
Map<String, Integer> map = new HashMap<>(64, 0.5f);


This sets the initial capacity setting to 64 and the load factor to 0.5.

Interview answer: Capacity is the bucket-array size, while load factor controls the threshold for resizing. HashMap's default load factor is 0.75.

4. HashMap time complexity
Operation	Average expected complexity	Worst-case considerations
put(key, value)	O(1)	Can be O(n) in some cases
get(key)	O(1)	Can degrade with severe collisions
remove(key)	O(1)	Can degrade with collisions
containsKey(key)	O(1)	Depends on bucket structure
containsValue(value)	O(n)	Scans entries
Iterate over entries	O(capacity + size)	Depends on table size

Modern OpenJDK HashMap can treeify sufficiently large collision-heavy buckets, improving search in those buckets in suitable circumstances. For tree bins, operations can often be O(log n), although that is an implementation detail rather than a universal guarantee for all key types and conditions.

5. HashMap methods — important examples
A. clear()
Removes all mappings from the map.
Map<String, Integer> scores = new HashMap<>();

scores.put("Ravi", 90);
scores.put("Anu", 85);

scores.clear();

System.out.println(scores); // {}
System.out.println(scores.size()); // 0


B. compute()
Computes a new value for the specified key using its existing value, or null if no mapping exists.
Map<String, Integer> scores = new HashMap<>();

scores.put("Ravi", 80);

scores.compute("Ravi", (key, value) -> value + 10);
scores.compute("Anu", (key, value) -> 50);

System.out.println(scores);
// {Ravi=90, Anu=50}


If the remapping function returns null, the mapping is removed if present.
C. computeIfAbsent()
Computes a value only if the key is currently absent or mapped to null.
Map<String, List<String>> subjects = new HashMap<>();

subjects.computeIfAbsent("Ravi", key -> new ArrayList<>())
        .add("Java");

subjects.computeIfAbsent("Ravi", key -> new ArrayList<>())
        .add("SQL");

System.out.println(subjects);
// {Ravi=[Java, SQL]}


This is especially useful when grouping multiple values under the same key.
D. computeIfPresent()
Computes a new value only if the key is present and its current value is non-null.
Map<String, Integer> scores = new HashMap<>();

scores.put("Ravi", 80);

scores.computeIfPresent("Ravi", (key, value) -> value + 10);
scores.computeIfPresent("Anu", (key, value) -> value + 10);

System.out.println(scores);
// {Ravi=90}


E. merge()
Useful for combining an incoming value with the existing value.
Map<String, Integer> counts = new HashMap<>();

counts.merge("Java", 1, Integer::sum);
counts.merge("Java", 1, Integer::sum);
counts.merge("SQL", 1, Integer::sum);

System.out.println(counts);
// {Java=2, SQL=1}


If the key is absent or mapped to null, merge() associates it with the supplied value. Otherwise, it combines the existing and incoming values using the remapping function. If that function returns null, the mapping is removed.
F. clone()
HashMap implements Cloneable, and its public clone() method creates a shallow copy.
HashMap<String, Integer> original = new HashMap<>();

original.put("Java", 10);

HashMap<String, Integer> copy =
        (HashMap<String, Integer>) original.clone();

copy.put("SQL", 20);

System.out.println(original); // {Java=10}
System.out.println(copy);     // {Java=10, SQL=20}


The maps have independent internal structures, but mutable key and value objects would still be shared.

6. HashMap — other must-know methods
Method	Purpose
put(k, v)	Add or replace a mapping
putIfAbsent(k, v)	Insert if absent or mapped to null
get(k)	Retrieve value or null
getOrDefault(k, defaultValue)	Retrieve value or a fallback
containsKey(k)	Check key existence
containsValue(v)	Check value existence
remove(k)	Remove a mapping
replace(k, v)	Replace only if the key is mapped
putAll(map)	Copy mappings from another map
keySet()	Obtain a view of the keys
values()	Obtain a view of the values
entrySet()	Obtain a view of key-value entries
forEach(action)	Process each mapping
replaceAll(function)	Replace values using a function

Iterating over a Map
Map<String, Integer> scores = new HashMap<>();

scores.put("Ravi", 90);
scores.put("Anu", 85);

// Iterate over keys and values
for (Map.Entry<String, Integer> entry : scores.entrySet()) {
    System.out.println(entry.getKey() + " = " + entry.getValue());
}

// Iterate using forEach
scores.forEach((name, score) ->
        System.out.println(name + " scored " + score));


Interview point: keySet(), values(), and entrySet() return backed views. Changes to the map can be reflected in these views, and supported removals through the views can affect the map.

7. HashMap vs LinkedHashMap vs TreeMap vs EnumMap
These four implementations have different purposes.
Feature	HashMap	LinkedHashMap	TreeMap	EnumMap
Internal structure	Hash table	Hash table plus linked ordering	Red-black tree	Array-based structure indexed by enum constants
Ordering	No guaranteed order	Insertion order by default	Sorted key order	Enum declaration order
Average/basic lookup	O(1) expected	O(1) expected	O(log n)	O(1)
Null keys	One allowed	One allowed	Generally not allowed with natural ordering	Not allowed
Null values	Allowed	Allowed	Allowed	Allowed
Best use	General key-value storage	Predictable iteration order	Sorted keys and range queries	Enum-keyed maps

A. LinkedHashMap
LinkedHashMap maintains a linked ordering of entries in addition to hash-based lookup. By default, it preserves insertion order.
Map<Integer, String> map = new LinkedHashMap<>();

map.put(3, "C");
map.put(1, "A");
map.put(2, "B");

System.out.println(map);
// {3=C, 1=A, 2=B}


It can also be configured for access order, which is useful for building LRU-style caches.
B. TreeMap
TreeMap maintains keys in sorted order using a balanced search tree.
Map<Integer, String> map = new TreeMap<>();

map.put(30, "C");
map.put(10, "A");
map.put(20, "B");

System.out.println(map);
// {10=A, 20=B, 30=C}


Use it when you need sorted keys, ranges, or nearest-key lookups.
C. EnumMap
EnumMap is specialized for enum keys. It is generally compact and efficient.
enum Day {
    MONDAY, TUESDAY, WEDNESDAY
}

Map<Day, String> schedule = new EnumMap<>(Day.class);

schedule.put(Day.MONDAY, "Java");
schedule.put(Day.TUESDAY, "SQL");
schedule.put(Day.WEDNESDAY, "DSA");

System.out.println(schedule);


Iteration follows the enum's declaration order, not the insertion order.

Interview recommendation: Use HashMap for general-purpose lookup, LinkedHashMap for predictable ordering, TreeMap for sorted keys, and EnumMap when all keys belong to one enum type.

8. Java Streams with HashMap
Streams are useful for filtering, transforming, sorting, and aggregating Map data.

A Map does not directly expose stream(). Instead, stream its entrySet(), keySet(), or values().
Map<String, Integer> scores = new HashMap<>();

scores.put("Ravi", 90);
scores.put("Anu", 75);
scores.put("Kiran", 85);
scores.put("Priya", 95);


Example 1: Iterate over a Map using Streams
scores.entrySet()
      .stream()
      .forEach(entry ->
          System.out.println(
              entry.getKey() + " = " + entry.getValue()
          )
      );


A HashMap does not guarantee the output order.
Example 2: Filter entries by value
Find students who scored at least 85.
scores.entrySet()
      .stream()
      .filter(entry -> entry.getValue() >= 85)
      .forEach(System.out::println);


Possible output:
Ravi=90
Kiran=85
Priya=95


Example 3: Filter keys
scores.keySet()
      .stream()
      .filter(name -> name.startsWith("R"))
      .forEach(System.out::println);


Output:
Ravi


Example 4: Transform Map values
Increase each score by 5 and collect the result into a new Map.
Map<String, Integer> updatedScores =
        scores.entrySet()
              .stream()
              .collect(Collectors.toMap(
                  Map.Entry::getKey,
                  entry -> entry.getValue() + 5
              ));


The original map is unchanged. The result is a new map; its iteration order is not guaranteed by Collectors.toMap().
Example 5: Sort entries by value
scores.entrySet()
      .stream()
      .sorted(Map.Entry.comparingByValue())
      .forEach(System.out::println);


For descending score order:
scores.entrySet()
      .stream()
      .sorted(Map.Entry.<String, Integer>comparingByValue()
                       .reversed())
      .forEach(System.out::println);


Example 6: Find the maximum value entry
Optional<Map.Entry<String, Integer>> highest =
        scores.entrySet()
              .stream()
              .max(Map.Entry.comparingByValue());

highest.ifPresent(System.out::println);


If multiple entries share the maximum value, this returns one of the maximum-valued entries; it does not guarantee which tied entry is selected.
Example 7: Calculate the total of all values
int total = scores.values()
                  .stream()
                  .mapToInt(Integer::intValue)
                  .sum();

System.out.println(total); // 345


Example 8: Count entries matching a condition
long count = scores.values()
                   .stream()
                   .filter(score -> score >= 85)
                   .count();

System.out.println(count); // 3


Example 9: Group words by their first character
List<String> words = Arrays.asList(
        "apple", "ant", "banana", "bat", "cat"
);

Map<Character, List<String>> grouped =
        words.stream()
             .collect(Collectors.groupingBy(
                 word -> word.charAt(0)
             ));

System.out.println(grouped);


Conceptually, the result is:
a → [apple, ant]
b → [banana, bat]
c → [cat]


The default map type returned by groupingBy() does not guarantee a particular iteration order.
Example 10: Count occurrences using merge()
This is especially useful in coding interviews.
List<String> words = Arrays.asList(
        "Java", "SQL", "Java", "Python", "SQL", "Java"
);

Map<String, Integer> frequency = new HashMap<>();

for (String word : words) {
    frequency.merge(word, 1, Integer::sum);
}

System.out.println(frequency);
// {Java=3, SQL=2, Python=1}


This pattern is a concise way to build a frequency map.

Interview takeaway: Practice streaming entrySet(), filtering by keys and values, sorting entries, collecting to a Map, finding min/max, and using merge() for frequency counting.

9. WeakHashMap — weak references and garbage collection
WeakHashMap is a Map implementation whose keys are held through weak references. It can be useful when you want mappings to disappear when their keys are no longer strongly reachable elsewhere.
How does it work?
Consider:
import java.util.WeakHashMap;

public class WeakHashMapDemo {
    public static void main(String[] args) {
        WeakHashMap<Object, String> map = new WeakHashMap<>();

        Object key1 = new Object();
        Object key2 = new Object();

        map.put(key1, "Value 1");
        map.put(key2, "Value 2");

        key2 = null;

        System.gc();

        System.out.println(map.size());
    }
}


The intention is to demonstrate that the entry associated with key2 may be removed after the key becomes weakly reachable and garbage collection processes it.

Important: This program is not guaranteed to print 1. System.gc() is only a request, and garbage collection timing is nondeterministic. A key might also remain strongly reachable elsewhere.
HashMap vs WeakHashMap
Feature	HashMap	WeakHashMap
Key reference	Strong	Weak
Can a key's entry disappear after GC?	Not merely because the key has no other references	Yes
Predictable entry lifetime	Generally, until explicit removal or map replacement	Not guaranteed
Typical use	General key-value storage	Certain caches and metadata associations

When is WeakHashMap useful?
For mappings where the map should not itself keep a key alive indefinitely, such as some kinds of metadata associated with objects.

However, WeakHashMap is not a general-purpose cache with predictable eviction. If a value strongly references its own key, the value can keep the key reachable and prevent the expected collection.

10. Cloneable and Serializable in Map implementations
These are marker interfaces with different purposes.
Cloneable
Cloneable indicates that Object.clone() is permitted to make a field-by-field copy of an instance.

For HashMap, the public clone() method creates a shallow copy.
HashMap<String, Integer> original = new HashMap<>();

original.put("Java", 10);

HashMap<String, Integer> copy =
        (HashMap<String, Integer>) original.clone();

copy.put("SQL", 20);

System.out.println(original); // {Java=10}
System.out.println(copy);     // {Java=10, SQL=20}


Serializable
Serializable is a marker interface used by Java's serialization mechanism to indicate that an object can participate in standard object serialization.
import java.io.Serializable;
import java.util.HashMap;

HashMap<String, Integer> map = new HashMap<>();

map.put("Java", 10);
map.put("SQL", 20);

System.out.println(map instanceof Serializable); // true


HashMap implements both Cloneable and Serializable.

Interview distinction:
- Cloneable relates to the behavior of Object.clone().
- Serializable relates to object serialization.
- Neither interface means that the object is automatically thread-safe.
- Cloning a map does not deep-copy mutable keys and values.

11. SortedMap — sorted keys
SortedMap extends Map and maintains keys in sorted order according to their natural ordering or a supplied Comparator.

TreeMap is the standard general-purpose implementation of SortedMap.
Important SortedMap methods
Method	Purpose
comparator()	Returns the comparator, or null for natural ordering
firstKey()	Returns the smallest key
lastKey()	Returns the largest key
headMap(toKey)	Returns a view of keys strictly less than toKey
tailMap(fromKey)	Returns a view of keys greater than or equal to fromKey
subMap(fromKey, toKey)	Returns a view from inclusive fromKey to exclusive toKey

Example
import java.util.*;

public class SortedMapDemo {
    public static void main(String[] args) {
        SortedMap<Integer, String> map = new TreeMap<>();

        map.put(30, "C");
        map.put(10, "A");
        map.put(20, "B");
        map.put(40, "D");

        System.out.println(map);
        // {10=A, 20=B, 30=C, 40=D}

        System.out.println(map.firstKey()); // 10
        System.out.println(map.lastKey());  // 40

        System.out.println(map.headMap(30));
        // {10=A, 20=B}

        System.out.println(map.tailMap(20));
        // {20=B, 30=C, 40=D}

        System.out.println(map.subMap(10, 30));
        // {10=A, 20=B}

        System.out.println(map.comparator()); // null
    }
}


Important interview point: views
The range methods headMap(), tailMap(), and subMap() return backed views rather than independent copies.
SortedMap<Integer, String> map = new TreeMap<>();

map.put(10, "A");
map.put(20, "B");
map.put(30, "C");

SortedMap<Integer, String> range = map.subMap(10, 30);

range.put(15, "X");

System.out.println(map);
// {10=A, 15=X, 20=B, 30=C}


The original map reflects the change.

12. NavigableMap — navigation methods
NavigableMap extends SortedMap and adds methods for finding keys and entries relative to a specified key.

It is commonly implemented by TreeMap.
Four essential navigation methods
Suppose the map contains keys 10, 20, 30, 40.
Method	Meaning for key 25	Result
lowerKey(25)	Greatest key strictly less than 25	20
floorKey(25)	Greatest key less than or equal to 25	20
ceilingKey(25)	Smallest key greater than or equal to 25	30
higherKey(25)	Smallest key strictly greater than 25	30

The most important distinction is strict vs inclusive:
- lower() and higher() are strict.
- floor() and ceiling() are inclusive.
Complete example
import java.util.*;

public class NavigableMapDemo {
    public static void main(String[] args) {
        NavigableMap<Integer, String> map = new TreeMap<>();

        map.put(10, "A");
        map.put(20, "B");
        map.put(30, "C");
        map.put(40, "D");

        System.out.println(map.lowerKey(20));   // 10
        System.out.println(map.floorKey(20));   // 20
        System.out.println(map.ceilingKey(25)); // 30
        System.out.println(map.higherKey(30));  // 40

        System.out.println(map.firstKey()); // 10
        System.out.println(map.lastKey());  // 40
    }
}


Entry-returning methods
The Entry methods return key-value entries rather than just keys.
Method	Purpose
lowerEntry(key)	Entry with greatest key strictly less than the given key
floorEntry(key)	Entry with greatest key less than or equal to the given key
ceilingEntry(key)	Entry with smallest key greater than or equal to the given key
higherEntry(key)	Entry with smallest key strictly greater than the given key
firstEntry()	Entry with smallest key
lastEntry()	Entry with largest key
pollFirstEntry()	Remove and return entry with smallest key
pollLastEntry()	Remove and return entry with largest key

If no matching entry exists, these navigation methods return null.
Example
NavigableMap<Integer, String> map = new TreeMap<>();

map.put(10, "A");
map.put(20, "B");
map.put(30, "C");
map.put(40, "D");

System.out.println(map.floorEntry(25));
// 20=B

System.out.println(map.ceilingEntry(25));
// 30=C

System.out.println(map.firstEntry());
// 10=A

System.out.println(map.pollLastEntry());
// 40=D

System.out.println(map);
// {10=A, 20=B, 30=C}


Descending order and key views
NavigableMap<Integer, String> map = new TreeMap<>();

map.put(10, "A");
map.put(20, "B");
map.put(30, "C");

System.out.println(map.descendingMap());
// {30=C, 20=B, 10=A}

System.out.println(map.descendingKeySet());
// [30, 20, 10]


Note: The actual method is descendingKeySet(), not descendingKeyMap().

Other useful methods include navigableKeySet(), headMap(), tailMap(), and subMap() with inclusive/exclusive boundary options.

Interview answer: SortedMap provides sorted ordering and range views; NavigableMap adds nearest-key searches, inclusive/exclusive navigation, descending views, and entry-removal operations.

13. Comparable and Comparator with TreeMap
Like PriorityQueue, TreeMap needs an ordering for its keys.
A. Natural ordering with Comparable
Map<Integer, String> map = new TreeMap<>();

map.put(30, "C");
map.put(10, "A");
map.put(20, "B");

System.out.println(map);
// {10=A, 20=B, 30=C}


Integers have natural ascending order.
B. Custom ordering with Comparator
Map<Integer, String> map =
        new TreeMap<>(Comparator.reverseOrder());

map.put(30, "C");
map.put(10, "A");
map.put(20, "B");

System.out.println(map);
// {30=C, 20=B, 10=A}


C. TreeMap with custom objects
class Employee {
    int id;
    String name;

    Employee(int id, String name) {
        this.id = id;
        this.name = name;
    }
}


Sort employees by ID:
TreeMap<Employee, String> employees =
        new TreeMap<>(Comparator.comparingInt(e -> e.id));

employees.put(new Employee(103, "Kiran"), "Developer");
employees.put(new Employee(101, "Ravi"), "Manager");
employees.put(new Employee(102, "Anu"), "Tester");


The map orders its keys by employee ID.

Important interview trap: TreeMap uses its comparison result to determine key ordering and uniqueness. If the comparator returns 0 for two different keys, TreeMap treats them as the same key for map purposes, and the new value replaces the old mapping.

For example, if the comparator compares employees only by ID, two distinct employees with the same ID cannot coexist as separate keys in that TreeMap.

14. ConcurrentHashMap — thread-safe Map
ConcurrentHashMap implements ConcurrentMap, which extends Map.

It is designed to support concurrent access and updates by multiple threads without requiring the entire map to be locked for every ordinary operation.
Why use ConcurrentHashMap?
Suppose multiple threads update a shared frequency map.

With a plain HashMap, unsynchronized concurrent modifications can lead to incorrect behavior and are not safe.

ConcurrentHashMap provides thread-safe operations designed for concurrent workloads.
Example
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class ConcurrentMapDemo {
    public static void main(String[] args) {
        ConcurrentMap<String, Integer> map =
                new ConcurrentHashMap<>();

        map.put("Java", 1);
        map.putIfAbsent("Java", 2);

        map.merge("Java", 1, Integer::sum);

        System.out.println(map);
        // {Java=2}
    }
}


Important characteristics
- Thread-safe for concurrent access.
- Does not permit null keys or null values.
- Supports atomic methods such as putIfAbsent(), compute(), and merge().
- Iterators are weakly consistent: they can tolerate concurrent updates and do not generally throw ConcurrentModificationException merely because another thread modifies the map.
- It does not make every multi-step application operation atomic automatically.
Does ConcurrentHashMap use 16 segments?
This is an outdated interview explanation for modern Java.

Older implementations used a segmented design. Modern OpenJDK implementations do not use the old fixed 16-segment structure. They use a combination of techniques such as CAS operations, synchronization on individual bins when needed, and cooperative resizing.

Multiple threads can access and update different parts of the map concurrently, but there is no general guarantee that exactly 16 threads can modify the map at once. Concurrency depends on the implementation, workload, and contention.

Interview answer: ConcurrentHashMap is a thread-safe map designed for high concurrency. Unlike a single globally synchronized map, it permits concurrent operations on different parts of the map where possible.

15. ConcurrentHashMap — search(), forEach() and reduce()
Besides the ordinary Map methods, ConcurrentHashMap provides bulk operations designed to work across entries concurrently.

These include forEach(), search(), and reduce().
A. forEach()
Performs an action for each mapping.
ConcurrentHashMap<String, Integer> map =
        new ConcurrentHashMap<>();

map.put("Java", 10);
map.put("SQL", 20);
map.put("Python", 30);

map.forEach((key, value) ->
        System.out.println(key + " = " + value));


The iteration order is not guaranteed.
B. search()
Searches the map using a search function and returns the first non-null result found.
String result = map.search(
        1,
        (key, value) -> value >= 20 ? key : null
);

System.out.println(result);


The result may be "SQL" or "Python" because the search is concurrent and the iteration order is unspecified. The threshold parameter controls when parallel processing may be used; 1 makes parallel execution eligible for a nonempty map.
C. reduce()
Combines values or transformed entries into a single result.
Integer total = map.reduceValues(
        1,
        Integer::sum
);

System.out.println(total); // 60


reduceValues() combines the values into a total. ConcurrentHashMap also provides other reduction variants for keys, entries, and transformed values.

Interview point: Use these bulk operations when concurrent processing of map entries is useful. The functions should be designed to tolerate concurrent execution, and the results should not depend on a guaranteed iteration order.

16. ConcurrentHashMap vs synchronizedMap
A common interview follow-up is why ConcurrentHashMap is preferred over a synchronized HashMap for many concurrent workloads.
Map<String, Integer> synchronizedMap =
        Collections.synchronizedMap(new HashMap<>());

ConcurrentHashMap<String, Integer> concurrentMap =
        new ConcurrentHashMap<>();


Feature	synchronizedMap()	ConcurrentHashMap
Thread-safe methods	Yes	Yes
Locking approach	Synchronizes wrapper operations on a common mutex	Uses finer-grained concurrent techniques
Concurrent reads and updates	More contention may occur	Better concurrency in many workloads
Null keys/values	Depends on backing map	Not allowed
Iteration	Synchronize on wrapper during iteration	Weakly consistent iterators
Atomic operations	Synchronization can be used around a whole operation	Provides atomic compound methods such as compute() and merge()

Example: atomic frequency counting
For a shared frequency map, prefer:
ConcurrentHashMap<String, Integer> frequency =
        new ConcurrentHashMap<>();

frequency.merge("Java", 1, Integer::sum);


The merge operation for the key is atomic. By contrast, a separate get() followed by put() is not automatically an atomic compound operation.

For a synchronized-map wrapper, iteration must be protected by synchronizing on the wrapper itself.

Interview takeaway: Thread safety of individual methods does not imply that an arbitrary sequence of calls is atomic. Use collection-provided atomic methods when they match the operation you need.

17. Map interview revision cheat sheet
Topic	Must-remember answer
Map	Stores unique keys mapped to values
HashMap	Hash table; no guaranteed iteration order
HashMap default load factor	0.75
HashMap collisions	Multiple entries can occupy one bucket
hashCode() and equals()	Both are important for hash-based key lookup
LinkedHashMap	Predictable insertion order by default
TreeMap	Sorted keys, typically O(log n) operations
EnumMap	Specialized for enum keys
WeakHashMap	Weakly referenced keys; entries may disappear after GC
compute()	Computes a new value using the existing value or null
computeIfAbsent()	Computes when the key is absent or mapped to null
computeIfPresent()	Computes when the key exists with a non-null value
merge()	Combines existing and incoming values
clone()	Shallow copy for HashMap
Serializable	Marker interface for Java serialization
SortedMap	Sorted keys and range views
NavigableMap	Nearest-key lookups and descending views
lowerKey()	Strictly lower key
floorKey()	Lower or equal key
ceilingKey()	Greater or equal key
higherKey()	Strictly higher key
ConcurrentHashMap	Thread-safe map supporting concurrent operations
ConcurrentHashMap nulls	Neither null keys nor null values allowed
ConcurrentHashMap segments	Fixed 16-segment explanation is outdated for modern OpenJDK
Streams with Map	Stream entrySet(), keySet(), or values()

18. Practice questions for interviews
Try to explain these aloud in two or three sentences each.
1. How does HashMap work internally?
2. What is the difference between capacity and load factor?
3. What happens when two keys have the same hash?
4. Why must equal objects have equal hash codes?
5. Why does LinkedHashMap preserve iteration order?
6. Why is TreeMap slower than HashMap for basic lookups?
7. What is the difference between computeIfAbsent() and computeIfPresent()?
8. When would you use merge() instead of put()?
9. Why can a WeakHashMap entry disappear after garbage collection?
10. What is the difference between SortedMap and NavigableMap?
11. Explain lowerKey(), floorKey(), ceilingKey(), and higherKey().
12. Why is ConcurrentHashMap preferable for many concurrent workloads?
13. Why is the old 16-segment explanation outdated?
14. How do you sort a Map by value using Streams?
15. How do you count word frequencies using a Map?
Recommended learning order: HashMap internals → capacity and load factor → Map methods → LinkedHashMap and TreeMap → Streams → SortedMap and NavigableMap → WeakHashMap → ConcurrentHashMap.

For interviews, prioritize HashMap internals, equals()/hashCode(), compute()/merge(), Comparator with TreeMap, nearest-key navigation, and concurrent map operations. These concepts also connect directly to Java coding problems and backend development.