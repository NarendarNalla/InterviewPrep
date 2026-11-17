# Java Sorting Algorithms Reference Guide

---

## 1. Selection Sort

**Selection Sort** repeatedly finds the minimum element from the unsorted portion of the array and places it at the beginning.

### Java Implementation

```java
public static void selectionSort(int[] arr) {
    int n = arr.length;

    // Move the boundary of the unsorted subarray one by one
    for (int i = 0; i < n - 1; i++) {
        int minIdx = i;

        // Find the index of the minimum element in the remaining unsorted array
        for (int j = i + 1; j < n; j++) {
            if (arr[j] < arr[minIdx]) {
                minIdx = j;
            }
        }

        // Swap the found minimum element with the first unsorted element
        if (minIdx != i) {
            int temp = arr[minIdx];
            arr[minIdx] = arr[i];
            arr[i] = temp;
        }
    }
}
```

### How It Works (Step-by-Step)
Given `[64, 25, 12, 22, 11]`:

1. **Pass 1 (`i = 0`):** Minimum in `[64, 25, 12, 22, 11]` is `11` $\rightarrow$ Swap with `64` $\rightarrow$ `[11, 25, 12, 22, 64]`
2. **Pass 2 (`i = 1`):** Minimum in `[25, 12, 22, 64]` is `12` $\rightarrow$ Swap with `25` $\rightarrow$ `[11, 12, 25, 22, 64]`
3. **Pass 3 (`i = 2`):** Minimum in `[25, 22, 64]` is `22` $\rightarrow$ Swap with `25` $\rightarrow$ `[11, 12, 22, 25, 64]`
4. **Pass 4 (`i = 3`):** Minimum in `[25, 64]` is `25` $\rightarrow$ Already in place $\rightarrow$ `[11, 12, 22, 25, 64]`

### Complexity & Properties

| Metric / Property | Value | Notes |
| :--- | :--- | :--- |
| **Time (Best / Avg / Worst)** | $O(n^2)$ | Always scans the full remaining unsorted subarray, even if already sorted |
| **Space Complexity** | $O(1)$ | In-place sorting algorithm |
| **Number of Swaps** | $O(n)$ (at most $n - 1$) | Useful when write/swap operations to memory are expensive |
| **Stability** | **Unstable** | Long-distance swaps can change the relative order of equal elements |

---

## 2. Bubble Sort

**Bubble Sort** repeatedly steps through the array, compares adjacent elements, and swaps them if they are in the wrong order, causing the largest unsorted element to "bubble" to the end on each pass.

### Java Implementation (Optimized)

```java
public static void bubbleSort(int[] arr) {
    int n = arr.length;

    for (int i = 0; i < n - 1; i++) {
        boolean swapped = false;

        // Last i elements are already in their final sorted positions
        for (int j = 0; j < n - 1 - i; j++) {
            if (arr[j] > arr[j + 1]) {
                // Swap adjacent elements
                int temp = arr[j];
                arr[j] = arr[j + 1];
                arr[j + 1] = temp;
                swapped = true;
            }
        }

        // If no swaps occurred in this pass, the array is already sorted
        if (!swapped) {
            break;
        }
    }
}
```

### How It Works (Step-by-Step)
Given `[5, 1, 4, 2, 8]`:

1. **Pass 1 (`i = 0`):**
   * Compare `(5, 1)` $\rightarrow$ Swap $\rightarrow$ `[1, 5, 4, 2, 8]`
   * Compare `(5, 4)` $\rightarrow$ Swap $\rightarrow$ `[1, 4, 5, 2, 8]`
   * Compare `(5, 2)` $\rightarrow$ Swap $\rightarrow$ `[1, 4, 2, 5, 8]`
   * Compare `(5, 8)` $\rightarrow$ No swap $\rightarrow$ `[1, 4, 2, 5, 8]` (`8` is now locked at the end)
2. **Pass 2 (`i = 1`):**
   * Swaps `(4, 2)` $\rightarrow$ `[1, 2, 4, 5, 8]` (`5` is locked)
3. **Pass 3 (`i = 2`):**
   * No swaps occur (`swapped == false`) $\rightarrow$ Terminates early.

### Complexity & Properties

| Metric / Property | Value | Notes |
| :--- | :--- | :--- |
| **Time (Best Case)** | $O(n)$ | Achieved when the array is already sorted (due to the `swapped` flag) |
| **Time (Avg / Worst)** | $O(n^2)$ | Occurs when elements are in random or reverse order |
| **Space Complexity** | $O(1)$ | In-place sorting algorithm |
| **Stability** | **Stable** | Only swaps strictly greater elements (`>`), preserving relative order of duplicates |

---

## 3. Insertion Sort

**Insertion Sort** builds the final sorted array one item at a time by taking each unsorted element and inserting it into its correct position within the already-sorted left portion of the array.

### Java Implementation

```java
public static void insertionSort(int[] arr) {
    int n = arr.length;

    // Start from index 1 since a single element at index 0 is trivially sorted
    for (int i = 1; i < n; i++) {
        int key = arr[i];
        int j = i - 1;

        // Shift elements in the sorted portion that are greater than key to the right
        while (j >= 0 && arr[j] > key) {
            arr[j + 1] = arr[j];
            j--;
        }

        // Place key into its correct sorted position
        arr[j + 1] = key;
    }
}
```

### How It Works (Step-by-Step)
Given `[12, 11, 13, 5, 6]`:

1. **Pass 1 (`i = 1`, `key = 11`):** `12 > 11` $\rightarrow$ Shift `12` right, insert `11` $\rightarrow$ `[11, 12, 13, 5, 6]`
2. **Pass 2 (`i = 2`, `key = 13`):** `12 < 13` $\rightarrow$ No shifts needed $\rightarrow$ `[11, 12, 13, 5, 6]`
3. **Pass 3 (`i = 3`, `key = 5`):** `13, 12, 11 > 5` $\rightarrow$ Shift all three right, insert `5` $\rightarrow$ `[5, 11, 12, 13, 6]`
4. **Pass 4 (`i = 4`, `key = 6`):** `13, 12, 11 > 6` $\rightarrow$ Shift all three right, insert `6` $\rightarrow$ `[5, 6, 11, 12, 13]`

### Complexity & Properties

| Metric / Property | Value | Notes |
| :--- | :--- | :--- |
| **Time (Best Case)** | $O(n)$ | Achieved when the array is already sorted (`while` loop never executes) |
| **Time (Avg / Worst)** | $O(n^2)$ | Worst case occurs when the array is sorted in reverse order |
| **Space Complexity** | $O(1)$ | In-place sorting algorithm |
| **Stability** | **Stable** | Shifts only strictly greater elements (`>`), preserving duplicate order |
| **Best Use Case** | Small or nearly sorted arrays | Used as the base-case fallback in hybrid algorithms like Timsort (`Arrays.sort`) |

---

## 4. Merge Sort

**Merge Sort** is a divide-and-conquer algorithm that recursively splits the array into halves until each subarray has one element, then merges those sorted subarrays back together in order.

### Java Implementation

```java
public static void mergeSort(int[] arr) {
    if (arr == null || arr.length <= 1) {
        return;
    }
    mergeSortHelper(arr, 0, arr.length - 1);
}

private static void mergeSortHelper(int[] arr, int left, int right) {
    if (left >= right) {
        return;
    }

    int mid = left + (right - left) / 2;

    // 1. Divide: Recursively sort left and right halves
    mergeSortHelper(arr, left, mid);
    mergeSortHelper(arr, mid + 1, right);

    // 2. Conquer: Merge the two sorted halves
    merge(arr, left, mid, right);
}

private static void merge(int[] arr, int left, int mid, int right) {
    int n1 = mid - left + 1;
    int n2 = right - mid;

    int[] leftArr = new int[n1];
    int[] rightArr = new int[n2];

    // Copy data into temporary arrays
    for (int i = 0; i < n1; i++) {
        leftArr[i] = arr[left + i];
    }
    for (int j = 0; j < n2; j++) {
        rightArr[j] = arr[mid + 1 + j];
    }

    // Merge the temporary arrays back into arr[left..right]
    int i = 0, j = 0, k = left;

    while (i < n1 && j < n2) {
        // Use <= to preserve stability (left element wins ties)
        if (leftArr[i] <= rightArr[j]) {
            arr[k++] = leftArr[i++];
        } else {
            arr[k++] = rightArr[j++];
        }
    }

    // Copy any remaining elements
    while (i < n1) {
        arr[k++] = leftArr[i++];
    }
    while (j < n2) {
        arr[k++] = rightArr[j++];
    }
}
```

### How It Works (Step-by-Step)
Given `[38, 27, 43, 3]`:

1. **Divide:**
   * `[38, 27, 43, 3]` $\rightarrow$ `[38, 27]` and `[43, 3]`
   * `[38, 27]` $\rightarrow$ `[38]` and `[27]`
   * `[43, 3]` $\rightarrow$ `[43]` and `[3]`
2. **Merge:**
   * Merge `[38]` and `[27]` $\rightarrow$ `[27, 38]`
   * Merge `[43]` and `[3]` $\rightarrow$ `[3, 43]`
   * Merge `[27, 38]` and `[3, 43]` $\rightarrow$ `[3, 27, 38, 43]`

### Complexity & Properties

| Metric / Property | Value | Notes |
| :--- | :--- | :--- |
| **Time (Best / Avg / Worst)** | $O(n \log n)$ | Guaranteed logarithmic split depth ($\log n$) with linear merge work ($n$) at each level |
| **Space Complexity** | $O(n)$ | Requires auxiliary temporary arrays during merging (not in-place) |
| **Stability** | **Stable** | `leftArr[i] <= rightArr[j]` keeps equal elements in their original relative order |
| **Best Use Case** | Linked lists & external sorting | Ideal when predictable $O(n \log n)$ worst-case performance or stability is required (used in Java's `Arrays.sort(Object[])`) |

---

## 5. Quick Sort

**Quick Sort** is a divide-and-conquer algorithm that selects a **pivot** element and partitions the array so that all elements smaller than the pivot move to its left and all elements greater move to its right, then recursively sorts the subarrays.

### Java Implementation (Lomuto Partition Scheme)

```java
public static void quickSort(int[] arr) {
    if (arr == null || arr.length <= 1) {
        return;
    }
    quickSortHelper(arr, 0, arr.length - 1);
}

private static void quickSortHelper(int[] arr, int low, int high) {
    if (low < high) {
        // Partition the array and get the pivot's final sorted index
        int pivotIdx = partition(arr, low, high);

        // Recursively sort elements before and after the pivot
        quickSortHelper(arr, low, pivotIdx - 1);
        quickSortHelper(arr, pivotIdx + 1, high);
    }
}

private static int partition(int[] arr, int low, int high) {
    int pivot = arr[high]; // Choose the rightmost element as pivot
    int i = low - 1;       // Index of the smaller element boundary

    for (int j = low; j < high; j++) {
        if (arr[j] <= pivot) {
            i++;
            swap(arr, i, j);
        }
    }

    // Place the pivot right after the last smaller element
    swap(arr, i + 1, high);
    return i + 1;
}

private static void swap(int[] arr, int i, int j) {
    int temp = arr[i];
    arr[i] = arr[j];
    arr[j] = temp;
}
```

### How It Works (Step-by-Step)
Given `[10, 80, 30, 90, 40]` with `pivot = 40`:

1. **Scan & Partition:**
   * `j = 0` (`10 <= 40`): `i` becomes `0`, swap `arr[0]` with `arr[0]` $\rightarrow$ `[10, 80, 30, 90, 40]`
   * `j = 1` (`80 > 40`): skip
   * `j = 2` (`30 <= 40`): `i` becomes `1`, swap `arr[1]` (`80`) with `arr[2]` (`30`) $\rightarrow$ `[10, 30, 80, 90, 40]`
   * `j = 3` (`90 > 40`): skip
2. **Place Pivot:**
   * Swap `arr[i + 1]` (`80`) with `pivot` (`40`) $\rightarrow$ `[10, 30, 40, 90, 80]`
   * `40` is now in its final sorted position (index `2`).
3. **Recurse:**
   * Left subarray `[10, 30]` $\rightarrow$ `[10, 30]`
   * Right subarray `[90, 80]` $\rightarrow$ `[80, 90]`
   * Final result: `[10, 30, 40, 80, 90]`

### Complexity & Properties

| Metric / Property | Value | Notes |
| :--- | :--- | :--- |
| **Time (Best / Average)** | $O(n \log n)$ | Occurs when the pivot divides the array into roughly equal halves |
| **Time (Worst Case)** | $O(n^2)$ | Occurs when the pivot is consistently the smallest or largest element (e.g., already sorted array with last-element pivot) |
| **Space Complexity** | $O(\log n)$ | In-place partitioning, using only recursion stack space ($O(n)$ in worst case) |
| **Stability** | **Unstable** | Non-adjacent swaps during partitioning can reorder equal elements |
| **Best Use Case** | General-purpose in-memory arrays | Excellent cache locality and low memory overhead (Java's `Arrays.sort(int[])` uses Dual-Pivot QuickSort) |

*(Tip: To prevent $O(n^2)$ worst-case behavior on sorted inputs, pick a **random pivot** or use the **median-of-three** (`low`, `mid`, `high`) and swap it with `arr[high]` before partitioning.)*

---

## 6. Summary Comparison Table

| Algorithm | Best Time | Average Time | Worst Time | Space | Stable? |
| :--- | :--- | :--- | :--- | :--- | :--- |
| **Selection Sort** | $O(n^2)$ | $O(n^2)$ | $O(n^2)$ | $O(1)$ | No |
| **Bubble Sort** | $O(n)$ | $O(n^2)$ | $O(n^2)$ | $O(1)$ | Yes |
| **Insertion Sort** | $O(n)$ | $O(n^2)$ | $O(n^2)$ | $O(1)$ | Yes |
| **Merge Sort** | $O(n \log n)$ | $O(n \log n)$ | $O(n \log n)$ | $O(n)$ | Yes |
| **Quick Sort** | $O(n \log n)$ | $O(n \log n)$ | $O(n^2)$ | $O(\log n)$ | No |