/* Leetcode 399: "Evaluate Division"
You are given an array of variable pairs equations and an array of real numbers values, where equations[i] = [Ai, Bi] and values[i] represent the equation Ai / Bi = values[i]. Each Ai or Bi is a string that represents a single variable.
You are also given some queries, where queries[j] = [Cj, Dj] represents the jth query where you must find the answer for Cj / Dj = ?.
Return the answers to all queries. If a single answer cannot be determined, return -1.0.
Note: The input is always valid. You may assume that evaluating the queries will not result in division by zero and that there is no contradiction.

Example 1:
    Input: equations = [["a","b"],["b","c"]], values = [2.0,3.0], queries = [["a","c"],["b","a"],["a","e"],["a","a"],["x","x"]]
    Output: [6.00000,0.50000,-1.00000,1.00000,-1.00000]
    Explanation: 
    Given: a / b = 2.0, b / c = 3.0
    queries are: a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ?
    return: [6.0, 0.5, -1.0, 1.0, -1.0 ]

Example 2:
    Input: equations = [["a","b"],["b","c"],["bc","cd"]], values = [1.5,2.5,5.0], queries = [["a","c"],["c","b"],["bc","cd"],["cd","bc"]]
    Output: [3.75000,0.40000,5.00000,0.20000]

Example 3:
    Input: equations = [["a","b"]], values = [0.5], queries = [["a","b"],["b","a"],["a","c"],["x","y"]]
    Output: [0.50000,2.00000,-1.00000,-1.00000] */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

class EvaluateDivision {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int size = sc.nextInt();
        List<List<String>> equations = new ArrayList<>();
        double[] values = new double[size];
        
        for (int i = 0; i < size; ++i) {
            List<String> eqn = new ArrayList<>();
            eqn.add(sc.next());
            eqn.add(sc.next());
            equations.add(eqn);
            values[i] = sc.nextDouble();
        }

        int qsize = sc.nextInt();
        List<List<String>> queries = new ArrayList<>();
        for (int i = 0; i < qsize; ++i) {
            List<String> q = new ArrayList<>();
            q.add(sc.next());
            q.add(sc.next());
            
            queries.add(q);
        }
        sc.close();

        double[] res = calcEquation(equations, values, queries);
        for (double ans : res)
            System.out.print(ans + " ");
        System.out.println();
    }

    // Using DFS
    // normal recursion with cache/dynamic-programming will result in a stack overflow
    private static double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        Map<String, Map<String, Double>> graph = makeGraph(equations, values);

        double[] ans = new double[queries.size()];

        // check for every Querie
        // store it in ans array
        for (int i = 0; i < queries.size(); i++) {
            ans[i] = dfs(queries.get(i).get(0), queries.get(i).get(1), new HashSet<>(), graph);
        }
        return ans;
    }

    private static Map<String, Map<String, Double>> makeGraph(List<List<String>> e, double[] values) {
        // build a graph
        // like a -> b = values[i]
        // and b -> a  = 1.0 / values[i]
        Map<String, Map<String, Double>> graph = new HashMap<>();
        String u, v;

        for (int i = 0; i < e.size(); i++) {
            u = e.get(i).get(0);
            v = e.get(i).get(1);

            graph.putIfAbsent(u, new HashMap<>());
            graph.get(u).put(v, values[i]);

            graph.putIfAbsent(v, new HashMap<>());
            graph.get(v).put(u, 1 / values[i]);

        }
        return graph;
    }

    private static double dfs(String src, String dest, Set<String> visited, Map<String, Map<String, Double>> graph) {
        // check the terminated Case
        // if string is not present in graph return -1.0
        // like [a, e] or [x, x]
        if (graph.containsKey(src) == false)
            return -1.0;

        // store it in weight varaible
        // case like [a,a] also gets handled
        if (graph.get(src).containsKey(dest)) {
            return graph.get(src).get(dest);
        }

        visited.add(src);

        // for every neighbour
        for (Map.Entry<String, Double> nbr : graph.get(src).entrySet()) {
            if (visited.contains(nbr.getKey()) == false) {
                double weight = dfs(nbr.getKey(), dest, visited, graph);

                // if weight is not -1.0 (terminate case)
                // then mutliply it 
                // like in querie   a -> c => 2 * 3 = 6
                if (weight != -1.0) {
                    return nbr.getValue() * weight;
                }
            }
        }
        return -1.0;
    }
}