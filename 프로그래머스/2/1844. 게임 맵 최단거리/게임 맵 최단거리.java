import java.util.*;

class Solution {
    public Object solution(int[][] maps) {
        int answer = 0, maxRow = maps.length, maxCol = maps[0].length;
        int[][] dir = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        boolean[][] visit = new boolean[maxRow][maxCol];
        Deque<int[]> q = new ArrayDeque<>();
        
        q.offer(new int[]{0, 0, 1});
        visit[0][0] = true;
        
        while ( !q.isEmpty() ) {
            int[] curr = q.poll();
            
            if ( curr[0] == maxRow - 1 && curr[1] == maxCol - 1 ) return curr[2];
            
            for ( int[] d : dir ) {
                int next_r = curr[0] + d[0];
                int next_c = curr[1] + d[1];
                
                if ( next_r >= 0 && next_r < maxRow
                    && next_c >= 0 && next_c < maxCol
                    && !visit[next_r][next_c] 
                    && maps[next_r][next_c] == 1 ){
                    visit[next_r][next_c] = true;
                    q.offer(new int[]{next_r, next_c, curr[2] + 1});
                }
            }
        }
        
        return -1;
    }
}