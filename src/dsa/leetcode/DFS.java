package dsa.leetcode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DFS {
    public static void main(String[] args) {

        //
        NumebrOfIslands numebrOfIslands = new NumebrOfIslands();
        char[][] grid = new char[][] {
                { '1', '1', '1', '1', '0' },
                { '1', '1', '0', '1', '0' },
                { '1', '1', '0', '0', '0' },
                { '0', '0', '0', '0', '0' }
        };
        System.out.println(numebrOfIslands.numIslands(grid));

        //
        MaxAreaOfIsland maxAreaOfIsland = new MaxAreaOfIsland();
        int[][] grid1 = new int[][] {
                { 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0 },
                { 0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 0, 0 },
                { 0, 1, 0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0 }
        };
        System.out.println(maxAreaOfIsland.maxAreaOfIsland(grid1));

    }
}

// https://leetcode.com/problems/number-of-islands/

class NumebrOfIslands {
    private boolean[][] visited;

    private void dfs(int i, int j, char[][] grid) {
        int r = grid.length;
        int c = grid[0].length;
        if (i >= r || j >= c || i < 0 || j < 0)
            return;

        if (grid[i][j] == '0' || visited[i][j])
            return;
        visited[i][j] = true;

        dfs(i - 1, j, grid);
        dfs(i + 1, j, grid);
        dfs(i, j + 1, grid);
        dfs(i, j - 1, grid);

    }

    public int numIslands(char[][] grid) {
        int r = grid.length;
        int c = grid[0].length;
        visited = new boolean[r][c];
        int cnt = 0;
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (grid[i][j] == '1' && !visited[i][j]) {
                    dfs(i, j, grid);
                    cnt++;
                }
            }
        }
        return cnt;
    }
}

// https://leetcode.com/problems/max-area-of-island/

class MaxAreaOfIsland {
    private boolean[][] visited;
    private int max;
    private int curr_mx;

    private void dfs(int i, int j, int[][] grid) {
        int r = grid.length;
        int c = grid[0].length;
        if (i >= r || j >= c || i < 0 || j < 0)
            return;

        if (grid[i][j] == 0 || visited[i][j])
            return;
        visited[i][j] = true;
        curr_mx++;

        dfs(i - 1, j, grid);
        dfs(i + 1, j, grid);
        dfs(i, j + 1, grid);
        dfs(i, j - 1, grid);

    }

    public int maxAreaOfIsland(int[][] grid) {
        int r = grid.length;
        int c = grid[0].length;
        visited = new boolean[r][c];
        max = 0;
        curr_mx = 0;
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (grid[i][j] == 1 && !visited[i][j]) {
                    dfs(i, j, grid);
                    max = Math.max(max, curr_mx);
                    curr_mx = 0;
                }
            }
        }
        return max;
    }
}

// https://leetcode.com/problems/clone-graph/description/
class CloneGraph {
    class Node {
        public int val;
        public List<Node> neighbors;

        public Node() {
            val = 0;
            neighbors = new ArrayList<Node>();
        }

        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<Node>();
        }

        public Node(int _val, ArrayList<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }

    // original node, cloned node
    private Map<Node, Node> map = new HashMap<>();

    private Node dfs(Node node) {

        if (map.containsKey(node))
            return map.get(node);

        Node clone = new Node(node.val);
        map.put(node, clone);

        for (Node _node : node.neighbors) {
            clone.neighbors.add(dfs(_node));
        }
        return clone;

    }

    public Node cloneGraph(Node node) {

        if (node == null)
            return null;

        return dfs(node);
    }

    /*
     * BFS Method -------------------
     * Push start node into the queue.
     * 
     * While queue not empty:
     * 
     * Pop a node (call it curr).
     * 
     * For each neighbor of curr:
     * 
     * If not cloned yet → clone it, store in map, and push neighbor into the queue.
     * 
     * Always connect map[curr] to map[neighbor].
     * 
     * Repeat until queue is empty.
     * 
     * So the cycle is: push → pop → explore neighbors → push them → repeat.
     * 
     * It’s just like BFS in trees, except instead of left/right children, you have
     * an arbitrary list of neighbors.
     * 
     * 
     */
}

class RottingOranges {
    class Ord {
        private int i, j;

        public Ord(int i, int j) {
            this.i = i;
            this.j = j;
        }
    }

    public int orangesRotting(int[][] grid) {
        int fresh = 0;
        int minutes = 0;
        Deque<Ord> queue = new ArrayDeque<>();
        int r = grid.length;
        int c = grid[0].length;
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (grid[i][j] == 2) {
                    queue.add(new Ord(i, j));
                } else if (grid[i][j] == 1) {
                    fresh++;
                }
            }
        }

        if (fresh == 0)
            return 0;

        while (!queue.isEmpty()) {

            int queueSize = queue.size();
            int old_fresh = fresh;
            int[][] dirs = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

            for (int k = 0; k < queueSize; k++) {
                Ord ord = queue.poll();
                int i = ord.i;
                int j = ord.j;

                for (int[] dir : dirs) {
                    int x = i + dir[0], y = j + dir[1];

                    if (x >= 0 && x < r && y >= 0 && y < c && grid[x][y] == 1) {
                        grid[x][y] = 2;
                        queue.add(new Ord(x, y));
                        fresh--;
                    }
                }
            }

            if (fresh < old_fresh)
                minutes++;

        }

        return fresh > 0 ? -1 : minutes;
    }

}