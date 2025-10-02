import java.util.*;

class Solution {
    List<int[]> list = new ArrayList<>();
    int maxDiff = 0;
    
    public Object solution(int n, int[] info) {
        int idx = 10, answer[] = {};
        dfs(n, -1, info, new int[11]);
        
        getLastScoreIdx:
        for (; idx >= 0 ; idx-- ) for ( int[] ryan : list ) if ( ryan[idx] != 0 ) break getLastScoreIdx; 
        
        if ( idx < 0 ) return new int[]{-1};
        final int lastIdx = idx;
        list.sort((a, b) -> Integer.compare(b[lastIdx], a[lastIdx]));
        answer = list.get(0);
        for ( int j : answer ) n -= j;
        answer[10] = n;
        return answer;
    }
    
    void dfs(int n, int curr, int[] info, int[] ryan) {
        if ( n <= 0 || curr == 10 ) {
            int diff = 0;
            for ( int i = 0 ; i <= 10 ; i++ ) {
                diff += ( ryan[i] > 0 ) ? 10 - i : 0;
                diff -= ( info[i] > 0 ) ? 10 - i : 0;
            }
            
            if  ( diff > 0 ) {
                if ( diff > maxDiff ) {
                    list = new ArrayList<>();
                    list.add(ryan);
                    maxDiff = diff;
                } else if ( diff == maxDiff ) list.add(ryan);
            }
            
            return;
        }
        
        curr++;
        
        dfs(n, curr, info.clone(), ryan.clone());
        
        if ( n > info[curr] ) {
            ryan[curr] = info[curr] + 1;
            info[curr] = 0;
            dfs(n - (ryan[curr]), curr, info.clone(), ryan.clone());            
        }
    }
}