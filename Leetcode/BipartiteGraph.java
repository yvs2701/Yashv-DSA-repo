
/* Leetcode 785: "Is Graph Bipartite?"
There is an undirected graph with n nodes, where each node is numbered between 0 and n - 1. You are given a 2D array graph, where graph[u] is an array of nodes that node u is adjacent to. More formally, for each v in graph[u], there is an undirected edge between node u and node v. The graph has the following properties:
- There are no self-edges (graph[u] does not contain u).
- There are no parallel edges (graph[u] does not contain duplicate values).
- If v is in graph[u], then u is in graph[v] (the graph is undirected).
- The graph may not be connected, meaning there may be two nodes u and v such that there is no path between them.
A graph is bipartite if the nodes can be partitioned into two independent sets A and B such that every edge in the graph connects a node in set A and a node in set B.
Return true if and only if it is bipartite.

Example 1:
    Input: graph = [[1,2,3],[0,2],[0,1,3],[0,2]]
    Output: false
    Explanation: There is no way to partition the nodes into two independent sets such that every edge connects a node in one and a node in the other.
Example 2:
    Input: graph = [[1,3],[0,2],[1,3],[0,2]]
    Output: true
    Explanation: We can partition the nodes into two sets: {0, 2} and {1, 3}. */

import java.util.LinkedList;

class BipartiteGraph {

    public static void main(String[] args) {
        int[][] graph = {{1,2,3}, {0,2}, {0,1,3}, {0,2}}; // input

        System.out.println("Breadth first traversal: " + isBipartite_bfs(graph));
        System.out.println("Depth first traversal: " + isBipartite_dfs(graph));
    }

    private static boolean isBipartite_bfs(int[][] graph) {
        int V = graph.length, color[] = new int[V];
        // for (int i=0; i < V; ++i)
        //     color[i] = 0;

        // USING BFS
        for (int v = 0; v < V; ++v) {
            if (color[v] != 0) // if the node is colored then ignore it
                continue;

            LinkedList<Integer> q = new LinkedList<Integer>();
            q.add(v);
            color[v] = 1;

            while (!q.isEmpty()) {
                int u = q.poll();

                for (int _v : graph[u]) { // for each _v connected to node u
                    if (color[_v] == 0) {
                        color[_v] = -color[u]; // color _v opposite to u
                        q.push(_v);
                    } else if (color[_v] == color[u]) {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    private static boolean isBipartite_dfs(int[][] graph) {
        int n = graph.length;
        int[] color = new int[n];

        for (int vertex = 0; vertex < n; vertex++) {
            if (color[vertex] == 0 && !validColouring(graph, color, vertex, 1))
                return false;
        }

        return true;
    }

    private static boolean validColouring(int[][] graph, int[] colour, int u, int col) {
        if (colour[u] != 0)
            return (colour[u] == col);

        colour[u] = col;
        for (int v : graph[u]) {
            if (!validColouring(graph, colour, v, -col))
                return false;
        }

        return true;
    }
}
