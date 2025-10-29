
## 1. **Adjacency Matrix in Java**

In C++, you might use a 2D vector `vector<vector<int>> matrix(n, vector<int>(n, 0));`.

In Java, the equivalent is a **2D array**:

```java
int n = 5; // number of nodes
int[][] matrix = new int[n][n];  // all initialized to 0 by default

// Add edge (u, v) for undirected graph:
matrix[u][v] = 1;
matrix[v][u] = 1;
```

* `matrix[i][j] == 1` means there’s an edge between node `i` and `j`.
* Works well for dense graphs.
* Space: **O(n²)**.

---

## 2. **Adjacency List in Java**

In C++, you’d do `vector<vector<int>> adj(n);`.

In Java, the idiomatic way is to use a **list of lists**:

```java
int n = 5; // number of nodes
List<List<Integer>> adj = new ArrayList<>();

// Initialize adjacency list
for (int i = 0; i < n; i++) {
    adj.add(new ArrayList<>());
}

// Add edge (u, v) for undirected graph:
adj.get(u).add(v);
adj.get(v).add(u);
```

* `adj.get(u)` gives the list of neighbors of `u`.
* Works well for sparse graphs.
* Space: **O(n + e)** where `e` = number of edges.

---

## 3. **Object-Oriented Graph (like in LeetCode 133)**

LeetCode’s `Node` definition is different:
Each `Node` directly stores its neighbors, not an external list/matrix.

```java
class Node {
    public int val;
    public List<Node> neighbors;
    
    public Node() {
        neighbors = new ArrayList<>();
    }
    
    public Node(int val) {
        this.val = val;
        neighbors = new ArrayList<>();
    }
}
```

* Instead of `adj.get(u)`, you directly do `node.neighbors`.
* This is more flexible when working with graph problems where the graph is already constructed for you.

---

✨ Quick comparison:

| Representation       | Best for         | Space Complexity | Access time                         |
| -------------------- | ---------------- | ---------------- | ----------------------------------- |
| **Adjacency Matrix** | Dense graphs     | O(n²)            | O(1) check if edge exists           |
| **Adjacency List**   | Sparse graphs    | O(n + e)         | O(degree) to iterate neighbors      |
| **Node Class (OO)**  | Problem-specific | Depends          | Very intuitive in LeetCode problems |

---
