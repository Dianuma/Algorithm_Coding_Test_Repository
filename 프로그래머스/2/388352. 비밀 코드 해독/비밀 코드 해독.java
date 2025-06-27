import java.util.*;

class Solution {
    
    public Object solution(int n, int[][] q, int[] ans) {
        int answer = 0;
        List<Set<Integer>> input = new ArrayList<>();
        
        for ( int[] i : q ) {
            Set<Integer> tmp = new HashSet<>();
            for ( int j : i ) tmp.add(j);
            input.add(tmp);
        }
        
        for ( int i = 1 ; i <= n ; i++ ) 
            for ( int j = i+1 ; j <= n ; j++ )
                for ( int k = j+1 ; k <= n ; k++ ) 
                    for ( int l = k+1 ; l <= n ; l++ ) 
                        for ( int m = l+1 ; m <= n ; m++ ) answer += check(new int[]{i,j,k,l,m},input,ans);
        
        return answer;
    }
    
    int check(int[] i, List<Set<Integer>> q, int[] ans) {
        int result = 0;
        for ( int a = 0 ; a < q.size() ; a++ ) {
            int y = 0;
            for ( int j : i ) if ( q.get(a).contains(j) ) y++;
            if ( y == ans[a] ) result++;
        }
        
        return ( result == q.size() ) ? 1 : 0;
    }
}