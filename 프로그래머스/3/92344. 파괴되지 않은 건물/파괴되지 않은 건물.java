class Solution {
    public Object solution(int[][] board, int[][] skill) {
        int answer = 0;
        int[][] sum = new int[board.length + 1][board[0].length + 1];
        for ( int[] s : skill ) {
            int degree = ( s[0] == 1 ) ? -s[5] : s[5];
            sum[s[1]][s[2]] += degree;
            sum[s[1]][s[4] + 1] -= degree;
            sum[s[3] + 1][s[2]] -= degree;
            sum[s[3] + 1][s[4] + 1] += degree;
        }
        
        for ( int i = 0 ; i < board.length + 1 ; i++ ) 
            for ( int j = 1 ; j < board[0].length + 1 ; j++ ) 
                sum[i][j] += sum[i][j - 1];
        
        for ( int j = 0 ; j < board[0].length + 1 ; j++ ) 
            for ( int i = 1 ; i < board.length + 1 ; i++ ) 
                sum[i][j] += sum[i - 1][j];

        for ( int i = 0 ; i < board.length ; i++ )
            for ( int j = 0 ; j < board[0].length ; j++ )
                if ( board[i][j] + sum[i][j] > 0 ) answer++;
        
        return answer;
    }
}