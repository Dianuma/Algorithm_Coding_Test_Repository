import java.util.*;
class Solution {
    public int solution(int[][] info, int n, int m) {
        int answer = 0, temp = 0;
        List<Integer> index = new ArrayList<>();
        for ( int[] i : info ) temp += i[1];
        
        final int totalB = temp;
        Arrays.sort(info, (a, b) -> (totalB - a[0] * (totalB / a[1])) - (totalB - b[0] * (totalB / b[1])));
        
        for ( int i = 0 ; i < info.length ; i++ ) {
            if ( m > info[i][1] ) {
                m -= info[i][1];
                index.add(i);
            }
        }

        for ( int i = 0 ; i < info.length ; i++ ) {
            if ( index.contains(i) ) continue;
            answer += info[i][0];
            if ( n <= answer ) return -1;
        }
        return answer;
    }
}