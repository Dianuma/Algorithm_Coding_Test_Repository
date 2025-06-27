import java.util.*;

class Solution {
    public Object solution(int[] nodes, int[][] edges) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        Map<Integer, Boolean> isReverse = new HashMap<>();
        Set<Integer> visited = new HashSet<>();
        List<List<Integer>> trees = new ArrayList<>();
        int[] answer = {0, 0};

        // 그래프 초기화
        for (int n : nodes) graph.put(n, new ArrayList<>());
        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }

        // 각 노드에 대해 홀짝 / 역홀짝 판단
        for (int n : nodes) isReverse.put(n, n % 2 != (graph.get(n).size() - 1) % 2);

        // DFS로 트리 분리
        for (int n : nodes) {
            if (!visited.contains(n)) {
                List<Integer> tree = new ArrayList<>();
                dfs(n, graph, visited, tree);
                trees.add(tree);
            }
        }

        // 각 트리에 대해 홀짝 / 역홀짝 판단
        for (List<Integer> tree : trees) {
            long r = tree.stream().filter(isReverse::get).count();
            if (tree.size() - r == 1) answer[1]++;
            if (r == 1) answer[0]++;
        }
        
        return answer;
    }

    void dfs(int node, Map<Integer, List<Integer>> graph, Set<Integer> visited, List<Integer> tree) {
        visited.add(node);
        tree.add(node);
        for (int next : graph.get(node)) 
            if (!visited.contains(next)) dfs(next, graph, visited, tree);
    }
}