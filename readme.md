# 📘 Divide-and-Conquer Algorithms — Detailed Report

## Project Overview
This project implements and analyzes four classical **divide-and-conquer algorithms** in Java, with a focus on safe recursion patterns, performance metrics, and validation against theoretical complexity bounds.

The work also integrates **JUnit5 testing**, **Maven build system**, and **GitHub Actions CI** for automated validation.

---

## 📂 Project Structure
```
DAA/
├── .github/workflows/ci.yml # CI pipeline
├── src/main/java/com/example/assignment1
│ ├── features/ # Algorithms
│ │ ├── MergeSort.java
│ │ ├── QuickSort.java
│ │ ├── DeterministicSelect.java
│ │ └── ClosestPair.java
│ ├── metrics/ # Metrics system
│ │ └── Metrics.java
│ └── Main.java # Entry point (runs benchmarks, exports CSV)
├── src/test/java/com/example/assignment1
│ ├── MergeSortTest.java
│ ├── QuickSortTest.java
│ ├── DeterministicSelectTest.java
│ └── ClosestPairTest.java
├── pom.xml # Maven config (Java 21, JUnit 5, plugins)
├── metrics.csv # Example exported metrics
└── readme.md # Report
```

---

## ⚙️ Implemented Algorithms

### 1. MergeSort
- Implemented with **recursive divide-and-conquer**.
- **Reusable buffer** to avoid repeated allocations during merging.
- **Insertion sort cutoff** for small arrays improves performance.
- Complexity: `O(n log n)` time, `O(n)` space.
- Metrics: comparisons in merge, allocations (1 buffer), recursion depth ≈ log n.

---

### 2. QuickSort (Robust)
- Uses a **randomized pivot** for balanced partitions.
- Always recurses on the smaller partition, iterates over the larger → stack bounded by `O(log n)`.
- Falls back to insertion sort for very small subarrays.
- Complexity: average `O(n log n)`, worst-case `O(n^2)` (rare with random pivot).
- Metrics: comparisons in partition, recursion depth bounded.

---

### 3. Deterministic Select (Median-of-Medians)
- Divides array into groups of 5, sorts each with insertion sort.
- Collects medians and recursively selects pivot.
- Partitions in-place and recurses only into the necessary side.
- Guarantees linear `O(n)` worst-case performance.
- Metrics: comparisons in group sorting and partitioning, recursion depth small, allocations minimal.

---

### 4. Closest Pair of Points (2D)
- Points initially sorted by **x-coordinate**.
- Recursively splits into halves.
- Merge step builds a vertical **strip sorted by y** and checks each point against ≤7 neighbors.
- Uses brute force for very small subsets.
- Complexity: `O(n log n)` time, `O(n)` extra space.
- Metrics: distance comparisons, allocations for temporary arrays, recursion depth ≈ log n.

---

## 📊 Metrics System

**File:** `Metrics.java`

### Features
- Counts:
    - **Comparisons** (sorting, partitioning, distance checks).
    - **Allocations** (buffers, auxiliary arrays).
    - **Recursion depth** (tracks current and maximum).
- Provides **CSV export**:
```
Algorithm,Time(ms),Comparisons,Allocations,MaxDepth
MergeSort,4,127042,1,11
QuickSort,3,160155,0,9
DeterministicSelect,2,29220,0,1
ClosestPair,27,13624,1,13
```

---

## 🧪 Testing

- **MergeSortTest**: validates sorted order, edge cases, buffer allocation.
- **QuickSortTest**: validates correctness on random and adversarial arrays; ensures recursion depth is bounded.
- **DeterministicSelectTest**: validates k-th element against `Arrays.sort()` for multiple random trials.
- **ClosestPairTest**: validates result against naive `O(n^2)` algorithm on small inputs (n ≤ 2000).

Testing ensures correctness and confirms metric counters behave as expected.

---

## 🛠️ Maven and CI

### Maven
- Java 21 compiler.
- Dependencies:
- **JUnit 5** (`junit-jupiter`).
- Plugins:
- `maven-compiler-plugin`
- `maven-surefire-plugin`
- `exec-maven-plugin` (to run `Main` directly).

### Continuous Integration (CI)
- Workflow: `.github/workflows/ci.yml`
- Runs on each push/pull request:
1. Checkout repository.
2. Set up JDK 21.
3. Run `mvn clean verify`.
4. Ensures compilation and tests pass.

---

## 📈 Results and Analysis

- **MergeSort** and **Closest Pair** both follow the expected `n log n` scaling.
- **QuickSort** outperforms MergeSort on random data due to cache locality and smaller constants.
- **Deterministic Select** shows linear scaling but with larger constants than QuickSort-based selection.
- Small cutoffs to insertion sort significantly reduce runtime overhead.
- Recursion depth matches theoretical predictions:
- MergeSort: ≈ log n
- QuickSort: ≤ 2*log₂(n)
- Deterministic Select: small constant depth
- Closest Pair: ≈ log n

---

## ✅ Summary
- Implemented four classical divide-and-conquer algorithms.
- Verified theoretical complexity bounds with experimental measurements.
- Designed metrics system (comparisons, allocations, recursion depth, runtime).
- Integrated Maven, JUnit 5, and GitHub Actions CI.
---

## ✍️ Author
**Vyacheslav Yakupov**