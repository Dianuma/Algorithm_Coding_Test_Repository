import java.util.*;
class Solution {
    public int solution(int n, int[][] computers) {
        List<int[]> mapList = new ArrayList<>();
        int[] parent = new int[n];
        Set<Integer> answer = new HashSet<>();
        for (int i = 0; i < n; i++) parent[i] = i;
        for ( int i = 0 ; i < computers.length ; i++ )
            for ( int j = 0 ; j < computers[i].length ; j++ ) 
                if ( i!=j && computers[i][j] == 1 ) mapList.add(new int[]{Math.min(i,j), Math.max(i,j)});
        for (int[] map : mapList) {
            int a = find(parent, map[0]), b = find(parent, map[1]);
            if (a != b) parent[b] = a;   
        }
        for (int i = 0; i < n; i++) answer.add(find(parent, i));
        return answer.size();
    }
    private int find(int[] parent, int x) {
        return parent[x] == x ? x : (parent[x] = find(parent, parent[x]));
    }
}