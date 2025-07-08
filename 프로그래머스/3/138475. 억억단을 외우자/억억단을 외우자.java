class Solution {
    public Object solution(int e, int[] starts) {        
        int[] answer = new int[starts.length], map = new int[e + 1], max = new int[e + 1];
        
        max[e] = e;
        
        for ( int i = 1 ; i <= e ; i++ ) 
            for ( int j = 1 ; j <= e / i ; j++ )  map[i * j]++;
        
        for ( int i = e - 1 ; i >= 0 ; i-- ) 
            if ( map[i] >= map[max[i + 1]] ) max[i] = i;
            else max[i] = max[i + 1];
        
        for ( int i = 0 ; i < starts.length ; i++ ) answer[i] = max[starts[i]];
        
        return answer;
    }
}
