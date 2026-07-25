import java.util.*;

class Solution {
    
    private final int INF = 30001;
    
    public int solution(int alp, int cop, int[][] problems) {
        int answer = 0;
        int targetAlp = 0;
        int targetCop = 0;

        for (int[] problem : problems) {
            targetAlp = Math.max(targetAlp, problem[0]);
            targetCop = Math.max(targetCop, problem[1]);
        }

        if (alp >= targetAlp && cop >= targetCop) return 0;
        
        targetAlp = Math.max(targetAlp, alp);
        targetCop = Math.max(targetCop, cop);
        
        int[][] dp = new int[targetAlp + 1][targetCop + 1];
        
        for (int[] arr : dp) Arrays.fill(arr, INF);
        dp[alp][cop] = 0;
        
        for ( int a = alp ; a <= targetAlp ; a++ ) {
            for ( int c = cop ; c <= targetCop ; c++ ) {
                int curr = dp[a][c];
                
                if ( a + 1 <= targetAlp )
                    dp[a+1][c] = Math.min(dp[a+1][c], curr + 1);
                
                if ( c + 1 <= targetCop )
                    dp[a][c+1] = Math.min(dp[a][c+1], curr + 1);
                
                for ( int[] p : problems ) {
                    if ( a >= p[0] && c >= p[1] ) {
                        int na = Math.min(targetAlp, a+p[2]);
                        int nc = Math.min(targetCop, c+p[3]);
                        dp[na][nc] = Math.min(dp[na][nc], curr + p[4]); 
                    }
                }
            }
        }        
        
        return dp[targetAlp][targetCop];
    }
}