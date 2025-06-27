import java.util.*;

class Solution {
    Map<Integer, List<Integer>> nodeMap = new HashMap<>();
    Map<Integer, Boolean> isReverse = new HashMap<>();
    List<List<Integer>> trees = new ArrayList<>();
    public Object solution(int[] nodes, int[][] edges) {
        List<Integer> node = new ArrayList<>();
        int[] answer = {0, 0};
        for ( int n : nodes ) {
            nodeMap.put(n, new ArrayList<>());
            node.add(n);
        }
        for ( int[] edge : edges ) {
            nodeMap.get(edge[0]).add(edge[1]);
            nodeMap.get(edge[1]).add(edge[0]);
        }
        for ( Map.Entry<Integer, List<Integer>> entry : nodeMap.entrySet() ) {
            isReverse.put(entry.getKey(), entry.getKey() % 2 != ( entry.getValue().size() - 1 ) % 2 );
        }
        
        dfs(node, new ArrayList<>(), new HashSet<>(), 0);
        
        for ( List<Integer> tree : trees ) {
            int f = 0, r = 0;
            for ( Integer n : tree ) {
                if ( isReverse.get(n) ) r++;
                else f++;
            }
            if ( f == 1 ) answer[1]++;
            if ( r == 1 ) answer[0]++;
        }
        
        return answer;
    }
    
    void dfs(List<Integer> keys, List<Integer> tree, HashSet<Integer> visited, int k) {
        for ( Integer key : keys ) {
            if ( visited.contains(key) ) continue;
            visited.add(key);
            tree.add(key);
            dfs(nodeMap.get(key), tree, visited, k+1);
            if ( k == 0 ) {
                trees.add(tree);
                tree = new ArrayList<>();
            }
        }
    }
}

/* 
홀짝 -> ( edge - 1 ) % 2 != node % 2 인 노드가 단 한개
역홀짝 -> ( edge - 1 ) % 2 == node % 2 인 노드가 단 한개

노드 구성

*/ 