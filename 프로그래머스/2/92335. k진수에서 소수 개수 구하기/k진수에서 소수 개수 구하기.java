import java.util.*;
class Solution {
    public int solution(int n, int k) {
        int answer = 0;
        for ( String i : Integer.toString(n,k).split("0") ) {
            if ( i.length() < 1 ) continue;
            if ( isPrime(Long.parseLong(i)) ) answer++;
        }
        return answer;
    }
    
    boolean isPrime(Long n) {
        if ( n == 1 ) return false;
        if ( n == 2 ) return true;
        long k = (long) Math.sqrt(n);
        for ( long i = 2L ; i <= k ; i++ ) 
            if ( n % i == 0 ) return false;
        return true;
    }
}