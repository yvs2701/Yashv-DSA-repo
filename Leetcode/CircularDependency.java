/* Leetcode 207: "Course Schedule"
There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1. You are given an array prerequisites where prerequisites[i] = [ai, bi] indicates that you must take course bi first if you want to take course ai.

For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.
Return true if you can finish all courses. Otherwise, return false.

Example 1:
    Input: numCourses = 2, prerequisites = [[1,0]]
    Output: true
Example 2:
    Input: numCourses = 2, prerequisites = [[1,0],[0,1]]
    Output: false
*/

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class CircularDependency {
    public static void main(String[] args) {
        int[][] prerequisites = { { 1, 0 }, { 0, 1 } };
        System.out.println(canFinish(2, prerequisites));
    }

    // Time Complexity: O(M + N) where M is the size of prerequisites and N is the number of courses.
    // Space Complexity: O(M + N) where M is the size of prerequisites and N is the number of courses.
    static boolean canFinish(int numCourses, int[][] prerequisites) {
        /* USES TOPOLOGICAL SORT */
        // create a dependency/prerequisite graph
        // To complete course[0] we need to complete course[1]
        // this can be represented as a directed edge from course[0] to course[1]
        List<List<Integer>> graph = new ArrayList<>(numCourses); // adjacency list representation of graph: O(m) space
        int[] indegree = new int[numCourses]; // stores indegree of each node: O(n) space

        for (int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] dependency : prerequisites) {
            graph.get(dependency[1]).add(dependency[0]);
            indegree[dependency[0]]++;
        }

        /* Topologically we will traverse the graph, starting from the nodes with indegree 0
         * Topological sort can happen only in DAG (Directed Acyclic Graph)
         * As in topological sorting node A comes before node B if there is a directed edge from A to B
         * However, if there is a cycle then we won't be able to rank the nodes in an order
         * AND WE WILL NOT VISIT SUCH NODES (which form a cycle) IN THIS ALGORITHM
        */

        // The nodes with indegree zero don't have any dependencies/prerequisites
        // Push all these independent nodes to the queue
        Queue<Integer> topology = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] == 0) {
                topology.offer(i);
            }
        }

        int nodesVisited = 0;
        while (!topology.isEmpty()) {
            // Travel from Independent Nodes, and delete them from the graph
            // If there is a cycle in the graph then we will never be able to delete all the nodes
            int node = topology.poll();
            nodesVisited++;

            for (int neighbor : graph.get(node)) {
                // Delete the edge "node -> neighbor" from the graph
                indegree[neighbor]--;

                if (indegree[neighbor] == 0) // this node has become independent
                    topology.offer(neighbor);
            }
        }

        // if we visited all the nodes in topologically ascending order
        // then there is no circular dependency
        return nodesVisited == numCourses;
    }
    

    /* // Time Limit Exceeded: 48 / 52 test cases passed.
    static boolean canFinish(int numCourses, int[][] prerequisites) {
        // create a dependency/prerequisite graph
        // To complete course[0] we need to complete course[1]
        // this can be represented as a directed edge from course[0] to course[1]
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int[] course : prerequisites) {
            if (!graph.containsKey(course[0]))
                graph.put(course[0], new LinkedList<>());

            graph.get(course[0]).add(course[1]);
        }

        // if the graph contains a cycle then its not possible
        // to complete the given set of courses
        for (int toComplete : graph.keySet())
            if (!dfs(toComplete, graph, new HashSet<>()))
                return false;

        return true;
    }

    static boolean dfs(int toComplete, Map<Integer, List<Integer>> graph, Set<Integer> path) {
        if (path.contains(toComplete))
            return false;

        path.add(toComplete);
        List<Integer> required = graph.get(toComplete);

        for (int course : required)
            if (graph.containsKey(course) && !dfs(course, graph, path))
                return false;

        path.remove(toComplete);
        return true;
    }
    */

    /* // Same as above but using array instead of map: Time limit exceeded: 48 / 52 test cases passed.
    static boolean canFinish (int numCourses, int[][] prerequisites) {
        // create a dependency/prerequisite graph
        // To complete course[0] we need to complete course[1]
        // this can be represented as a directed edge from course[0] to course[1]
        boolean[][] graph = new boolean[numCourses][numCourses];
        for (int[] course : prerequisites)
            graph[course[0]][course[1]] = true;
        
        // if the graph contains a cycle then its not possible
        // to complete the given set of courses
        boolean[] visited = new boolean[numCourses];
        for (int toComplete = 0; toComplete < numCourses; toComplete++)
            if (!dfs(toComplete, graph, visited))
                return false;

        return true;
    }

    static boolean dfs(int toComplete, boolean[][] graph, boolean[] visited) {
        if (visited[toComplete])
            return false;

        visited[toComplete] = true;
        for (int course = 0; course < graph[toComplete].length; course++)
            if (graph[toComplete][course] && !dfs(course, graph, visited))
                return false;

        visited[toComplete] = false;
        return true;
    }
    */
}
