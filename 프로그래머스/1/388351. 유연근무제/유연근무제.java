class Solution {
    public int solution(int[] schedules, int[][] timelogs, int startday) {
        int answer = 0;
        int m = 0;
        Loop:
        for ( int i = 0 ; i < schedules.length ; i++ ) {
            m = toM(schedules[i]);
            for ( int j = 0 ; j < timelogs[i].length ; j++ ) {
                if ( ( startday + j ) % 7 == 6 || ( startday + j ) % 7 == 0 ) continue;
                if ( m + 10 < toM(timelogs[i][j]) ) continue Loop;
            }
            answer++;
        }
        return answer;
    }
    
    int toM( int t ) {
        return t % 100 + (int) Math.floor( t / 100 ) * 60;
    }
}