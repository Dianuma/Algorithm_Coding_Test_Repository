class Solution {
    public long solution(long r1, long r2) {
        long high = 0, low = 0, answer = 0;
        for ( long x = 1 ; x <= r2 ; x++ ) {
            high = (long) Math.floor(Math.sqrt(r2*r2-x*x));
            low = (long) Math.ceil(Math.sqrt(r1*r1-x*x));
            if ( high >= low ) answer += high - low + 1;
        }
        return answer*4;
    }
}