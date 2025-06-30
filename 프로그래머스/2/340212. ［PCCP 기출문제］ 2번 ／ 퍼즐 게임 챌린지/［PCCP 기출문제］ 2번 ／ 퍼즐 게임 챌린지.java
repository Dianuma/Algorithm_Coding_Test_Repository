class Solution {
    public Object solution(int[] diffs, int[] times, long limit) {
        int answer = 0;
        int[] sum = new int[diffs.length];
        sum[0] = times[0];
        for ( int i = 1 ; i < times.length ; i++ ) sum[i] = times[i] + times[i-1];
        return binarySearch(sum, diffs, times, limit);
    }
    
    long binarySearch(int[] sum, int[] diffs, int[] times, long limit) {
        int high = 200000, low = 1,level = 0;
        long time = 0;
        
        while ( true ) {
            level = ( high + low ) / 2;
            time = getTime(sum, diffs, times, level);
            if ( time <= limit ) high = level;
            else low = level + 1;
            if ( high == low ) break;            
        }
        
        level++;
        
        return ( getTime(sum, diffs, times, level - 1) <= limit ) ? level - 1 : level;
    }
    
    long getTime(int[] sum, int[] diffs, int[] times, int level) {
        long result = 0;
        for ( int i = 0 ; i < diffs.length ; i++ ) {
            if ( diffs[i] <= level ) result += times[i];
            else result += sum[i] * ( diffs[i] - level ) + times[i];
        }
        return result;
    }
}