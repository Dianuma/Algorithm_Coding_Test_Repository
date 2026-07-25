import java.util.*;

class Solution {
    public long solution(int n, int[] times) {
        long max = 0;
        
        for ( int time : times ) {
            max = Math.max(max, time);
        }
        
        long left = 1, right = max * n;
        
        while ( true ) {
            if ( left >= right ) break;
            
            long mid = ( left + right ) / 2;
            
            long person = 0;
            
            for ( int time : times ) {
                person += mid / time;
            }
            
            if ( person < n ) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return right;
    }
}