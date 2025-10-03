import java.util.*;
import java.util.stream.Collectors;

class Solution {
    int max = 0;
    public Object solution(int[] info, int[][] edges) {
        Map<Integer, List<int[]>> edgeMap = Arrays.stream(edges).collect(Collectors.groupingBy(arr -> arr[0]));
        Set<Integer> set = new HashSet<>();
        set.add(0);
        dfs(info, edgeMap, new int[info.length], set, 0, 0, 0);
        return max;
    }
    
    void dfs (int[] info, Map<Integer, List<int[]>> edgeMap, int[] visited, Set<Integer> visit, int curr, int sheep, int wolf) {
        if ( info[curr] == 0 ) sheep++; else wolf++;
        max = Math.max ( sheep, max ); 
        if ( sheep - wolf <= 0 || visit.isEmpty() ) return;
        
        visit.remove(curr);
        visited[curr] = 1;      

        if ( edgeMap.containsKey(curr) ) {
            for ( int[] edge : edgeMap.get(curr) ) if ( visited[edge[1]] == 0 ) visit.add(edge[1]);
        }

        for ( int next : visit ) dfs(info, edgeMap, visited.clone(), new HashSet<>(visit), next, sheep, wolf);

    }
}