
package com.oct.play.collections;

import java.util.*;

public class SortedMapNavigableMapExample {

    public static void main(String[] args) {

        // TreeMap implements NavigableMap.
        // NavigableMap extends SortedMap, which extends Map.
        // TreeMap sorts keys in ascending order by default.

        NavigableMap<Integer, String> map = new TreeMap<>();

        map.put(10, "Ten");
        map.put(20, "Twenty");
        map.put(30, "Thirty");
        map.put(40, "Forty");
        map.put(50, "Fifty");

        // Output: {10=Ten, 20=Twenty, 30=Thirty, 40=Forty, 50=Fifty}
        System.out.println("Original Map: " + map);


        // ==================================================
        // 1. SORTEDMAP METHODS
        // ==================================================

        // comparator() - Returns the key comparator.
        // null means natural ordering is used.
        // Output: null
        System.out.println("\nComparator: " + map.comparator());

        // firstKey() - Returns the first key in sorted order.
        // Output: 10
        System.out.println("First Key: " + map.firstKey());

        // lastKey() - Returns the last key in sorted order.
        // Output: 50
        System.out.println("Last Key: " + map.lastKey());

        // headMap(key) - Returns keys strictly less than the key.
        // Output: {10=Ten, 20=Twenty}
        System.out.println("Head Map (< 30): " + map.headMap(30));

        // tailMap(key) - Returns keys greater than or equal to the key.
        // Output: {30=Thirty, 40=Forty, 50=Fifty}
        System.out.println("Tail Map (>= 30): " + map.tailMap(30));

        // subMap(fromKey, toKey) - Includes fromKey, excludes toKey.
        // Output: {20=Twenty, 30=Thirty, 40=Forty}
        System.out.println("Sub Map [20, 50): " + map.subMap(20, 50));


        // ==================================================
        // 2. NAVIGABLEMAP METHODS
        // ==================================================

        // ceilingEntry(key) - Smallest key >= the given key.
        // Output: 40=Forty
        System.out.println("\nCeiling Entry (35): " + map.ceilingEntry(35));

        // ceilingKey(key) - Smallest key >= the given key.
        // Output: 40
        System.out.println("Ceiling Key (35): " + map.ceilingKey(35));

        // floorEntry(key) - Largest key <= the given key.
        // Output: 30=Thirty
        System.out.println("Floor Entry (35): " + map.floorEntry(35));

        // floorKey(key) - Largest key <= the given key.
        // Output: 30
        System.out.println("Floor Key (35): " + map.floorKey(35));

        // higherEntry(key) - Smallest key strictly greater than the key.
        // Output: 40=Forty
        System.out.println("Higher Entry (30): " + map.higherEntry(30));

        // higherKey(key) - Smallest key strictly greater than the key.
        // Output: 40
        System.out.println("Higher Key (30): " + map.higherKey(30));

        // lowerEntry(key) - Largest key strictly less than the key.
        // Output: 20=Twenty
        System.out.println("Lower Entry (30): " + map.lowerEntry(30));

        // lowerKey(key) - Largest key strictly less than the key.
        // Output: 20
        System.out.println("Lower Key (30): " + map.lowerKey(30));

        // firstEntry() - Returns the first entry without removing it.
        // Output: 10=Ten
        System.out.println("First Entry: " + map.firstEntry());

        // lastEntry() - Returns the last entry without removing it.
        // Output: 50=Fifty
        System.out.println("Last Entry: " + map.lastEntry());

        // descendingMap() - Returns a reverse-order view of the map.
        // Output: {50=Fifty, 40=Forty, 30=Thirty, 20=Twenty, 10=Ten}
        System.out.println("Descending Map: " + map.descendingMap());

        // descendingKeySet() - Returns keys in descending order.
        // Output: [50, 40, 30, 20, 10]
        System.out.println("Descending Key Set: " + map.descendingKeySet());

        // navigableKeySet() - Returns keys in ascending order.
        // Output: [10, 20, 30, 40, 50]
        System.out.println("Navigable Key Set: " + map.navigableKeySet());

        // headMap(key, inclusive) - true includes the boundary key.
        // Output: {10=Ten, 20=Twenty, 30=Thirty}
        System.out.println("Head Map (<= 30): " + map.headMap(30, true));

        // tailMap(key, inclusive) - false excludes the boundary key.
        // Output: {40=Forty, 50=Fifty}
        System.out.println("Tail Map (> 30): " + map.tailMap(30, false));

        // subMap(fromKey, fromInclusive, toKey, toInclusive).
        // Both boundaries are included.
        // Output: {20=Twenty, 30=Thirty, 40=Forty}
        System.out.println("Sub Map [20, 40]: "
                + map.subMap(20, true, 40, true));


        // ==================================================
        // 3. POLL METHODS
        // ==================================================

        // pollFirstEntry() - Returns and removes the first entry.
        // Output: 10=Ten
        System.out.println("\nPolled First Entry: " + map.pollFirstEntry());

        // Output: {20=Twenty, 30=Thirty, 40=Forty, 50=Fifty}
        System.out.println("After pollFirstEntry(): " + map);

        // pollLastEntry() - Returns and removes the last entry.
        // Output: 50=Fifty
        System.out.println("Polled Last Entry: " + map.pollLastEntry());

        // Output: {20=Twenty, 30=Thirty, 40=Forty}
        System.out.println("After pollLastEntry(): " + map);


        // ==================================================
        // 4. BASIC INHERITED MAP METHODS
        // ==================================================

        // size() - Returns the number of entries.
        // Output: 3
        System.out.println("\nSize: " + map.size());

        // containsKey(key) - Checks whether a key exists.
        // Output: true
        System.out.println("Contains Key 30: " + map.containsKey(30));

        // containsValue(value) - Checks whether a value exists.
        // Output: true
        System.out.println("Contains Value Thirty: "
                + map.containsValue("Thirty"));

        // get(key) - Returns the value for the given key.
        // Output: Thirty
        System.out.println("Get Key 30: " + map.get(30));

        // getOrDefault(key, defaultValue) - Returns a default if absent.
        // Output: Not Found
        System.out.println("Get Key 99: "
                + map.getOrDefault(99, "Not Found"));

        // isEmpty() - Checks whether the map has no entries.
        // Output: false
        System.out.println("Is Empty: " + map.isEmpty());

        // keySet() - Returns the keys in sorted order.
        // Output: [20, 30, 40]
        System.out.println("Key Set: " + map.keySet());

        // values() - Returns values in key order.
        // Output: [Twenty, Thirty, Forty]
        System.out.println("Values: " + map.values());

        // entrySet() - Returns all key-value entries.
        // Output: [20=Twenty, 30=Thirty, 40=Forty]
        System.out.println("Entry Set: " + map.entrySet());


        // ==================================================
        // 5. putAll()
        // ==================================================

        // putAll(otherMap) - Copies all mappings from another map.
        // Existing keys are overwritten if they appear in both maps.

        Map<Integer, String> extra = new HashMap<>();
        extra.put(50, "Fifty");
        extra.put(60, "Sixty");

        map.putAll(extra);

        // Output: {20=Twenty, 30=Thirty, 40=Forty, 50=Fifty, 60=Sixty}
        System.out.println("\nAfter putAll(): " + map);


        // ==================================================
        // 6. putIfAbsent()
        // ==================================================

        // Adds the mapping only if the key is absent or mapped to null.
        // Key 70 is absent, so it is inserted.
        // Output: {20=Twenty, 30=Thirty, 40=Forty, 50=Fifty, 60=Sixty, 70=Seventy}
        map.putIfAbsent(70, "Seventy");
        System.out.println("After putIfAbsent(): " + map);


        // ==================================================
        // 7. replace()
        // ==================================================

        // replace(key, value) - Replaces the value only if the key exists.
        // Output: Seventy
        System.out.println("Old value returned by replace(): "
                + map.replace(70, "Seventy Updated"));

        // Output: {20=Twenty, 30=Thirty, 40=Forty, 50=Fifty, 60=Sixty, 70=Seventy Updated}
        System.out.println("After replace(): " + map);

        // replace(key, oldValue, newValue) - Replaces only if the
        // current value matches oldValue.
        // Output: true
        System.out.println("Conditional replace: "
                + map.replace(70, "Seventy Updated", "Seventy"));

        // Output: {20=Twenty, 30=Thirty, 40=Forty, 50=Fifty, 60=Sixty, 70=Seventy}
        System.out.println("After conditional replace(): " + map);


        // ==================================================
        // 8. replaceAll()
        // ==================================================

        // replaceAll(function) - Replaces every value using a function.
        // Here, every value is converted to uppercase.
        // Keys remain unchanged.
        map.replaceAll((key, value) -> value.toUpperCase());

        // Output: {20=TWENTY, 30=THIRTY, 40=FORTY, 50=FIFTY, 60=SIXTY, 70=SEVENTY}
        System.out.println("\nAfter replaceAll(): " + map);


        // ==================================================
        // 9. compute()
        // ==================================================

        // compute(key, remappingFunction) - Computes a new value for a key.
        // The function receives the key and its current value.
        // If the function returns null, the mapping is removed.
        // Key 30 currently maps to THIRTY.
        // Output: THIRTY UPDATED
        System.out.println("compute() result: "
                + map.compute(30, (key, value) -> value + " UPDATED"));

        // Output: {20=TWENTY, 30=THIRTY UPDATED, 40=FORTY, 50=FIFTY, 60=SIXTY, 70=SEVENTY}
        System.out.println("After compute(): " + map);


        // ==================================================
        // 10. computeIfAbsent()
        // ==================================================

        // Computes and inserts a value only when the key is absent
        // or currently mapped to null.
        // Key 80 does not exist, so the function creates its value.
        // Output: EIGHTY
        System.out.println("computeIfAbsent() result: "
                + map.computeIfAbsent(80, key -> "EIGHTY"));

        // Output: {20=TWENTY, 30=THIRTY UPDATED, 40=FORTY, 50=FIFTY, 60=SIXTY, 70=SEVENTY, 80=EIGHTY}
        System.out.println("After computeIfAbsent(): " + map);


        // ==================================================
        // 11. computeIfPresent()
        // ==================================================

        // Computes a new value only if the key exists and its value is non-null.
        // Key 40 currently maps to FORTY.
        // Output: FORTY !!!
        System.out.println("computeIfPresent() result: "
                + map.computeIfPresent(40, (key, value) -> value + " !!!"));

        // Output: {20=TWENTY, 30=THIRTY UPDATED, 40=FORTY !!!, 50=FIFTY, 60=SIXTY, 70=SEVENTY, 80=EIGHTY}
        System.out.println("After computeIfPresent(): " + map);


        // ==================================================
        // 12. merge()
        // ==================================================

        // merge(key, value, remappingFunction):
        // If key is absent or mapped to null, inserts the supplied value.
        // Otherwise, combines the old and new values using the function.
        // Key 50 has value FIFTY, so the function combines both strings.
        // Output: FIFTY + EXTRA
        System.out.println("merge() result: "
                + map.merge(50, "EXTRA", (oldValue, newValue) ->
                        oldValue + " + " + newValue));

        // Output: {20=TWENTY, 30=THIRTY UPDATED, 40=FORTY !!!, 50=FIFTY + EXTRA, 60=SIXTY, 70=SEVENTY, 80=EIGHTY}
        System.out.println("After merge(): " + map);


        // ==================================================
        // 13. remove()
        // ==================================================

        // remove(key) - Removes the mapping for the specified key.
        // Output: TWENTY
        System.out.println("Removed value: " + map.remove(20));

        // Output: {30=THIRTY UPDATED, 40=FORTY !!!, 50=FIFTY + EXTRA, 60=SIXTY, 70=SEVENTY, 80=EIGHTY}
        System.out.println("After remove(): " + map);

        // remove(key, value) - Removes only if both key and value match.
        // Key 60 maps to SIXTY, so this succeeds.
        // Output: true
        System.out.println("Conditional remove: "
                + map.remove(60, "SIXTY"));

        // Output: {30=THIRTY UPDATED, 40=FORTY !!!, 50=FIFTY + EXTRA, 70=SEVENTY, 80=EIGHTY}
        System.out.println("After conditional remove(): " + map);


        // ==================================================
        // 14. forEach()
        // ==================================================

        // forEach(action) - Processes every key-value mapping.
        // Iteration follows the map's ascending key order.
        // Output:
        // 30 -> THIRTY UPDATED
        // 40 -> FORTY !!!
        // 50 -> FIFTY + EXTRA
        // 70 -> SEVENTY
        // 80 -> EIGHTY
        System.out.println("\nUsing forEach():");

        map.forEach((key, value) ->
                System.out.println(key + " -> " + value));


        // ==================================================
        // 15. equals() AND hashCode()
        // ==================================================

        // equals(object) - Returns true if another map has the same
        // key-value mappings. The concrete map class can be different.
        Map<Integer, String> anotherMap = new TreeMap<>(map);

        // Output: true
        System.out.println("\nMaps are equal: " + map.equals(anotherMap));

        // hashCode() - Returns the map's hash code, based on its entries.
        // The numeric output depends on the map's current contents.
        System.out.println("Map hashCode: " + map.hashCode());


        // ==================================================
        // 16. clone() - TREE MAP SPECIFIC
        // ==================================================

        // clone() - Creates a shallow copy of the TreeMap.
        // This method belongs to TreeMap, not to Map, SortedMap,
        // or NavigableMap.
        TreeMap<Integer, String> treeMapCopy = new TreeMap<>(map);

        // Output: {30=THIRTY UPDATED, 40=FORTY !!!, 50=FIFTY + EXTRA, 70=SEVENTY, 80=EIGHTY}
        System.out.println("TreeMap copy: " + treeMapCopy);


        // ==================================================
        // 17. clear()
        // ==================================================

        // clear() - Removes all entries.
        // Output: {}
        map.clear();
        System.out.println("\nAfter clear(): " + map);

        // isEmpty() - Returns true when there are no entries.
        // Output: true
        System.out.println("Is Empty After clear(): " + map.isEmpty());
    }
}
