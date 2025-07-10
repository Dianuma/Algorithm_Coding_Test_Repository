import java.util.*;

class Solution {
    int maxSheep = 0;
    
    public int solution(int[] info, int[][] edges) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < info.length; i++) graph.add(new ArrayList<>());
        for (int[] edge : edges) graph.get(edge[0]).add(edge[1]);
        
        dfs(0, 0, 0, new boolean[info.length], graph, info, new ArrayList<>(List.of(0)));
        
        return maxSheep;
    }
    
    void dfs(int curr, int sheep, int wolf, boolean[] visited, List<List<Integer>> graph, int[] info, List<Integer> canVisit) {
        if ( info[curr] == 0 ) sheep++;
        else wolf++;
        
        if ( sheep == wolf ) return;
        maxSheep = Math.max(maxSheep, sheep);
        
        visited[curr] = true;
        List<Integer> nextCanVisit = new ArrayList<>(canVisit);
        nextCanVisit.remove((Integer) curr);
        
        for ( int node : graph.get(curr) ) nextCanVisit.add(node);
        
        for ( int next : nextCanVisit ) 
            if ( !visited[next] ) dfs(next, sheep, wolf, Arrays.copyOf(visited, visited.length), graph, info, nextCanVisit);
        
    }
}