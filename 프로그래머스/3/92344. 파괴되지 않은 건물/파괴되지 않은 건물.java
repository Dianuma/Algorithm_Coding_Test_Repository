class Solution {
    public Object solution(int[][] board, int[][] skill) {
        int answer = 0;
        int[][] damage = new int[board.length + 1][board[0].length + 1];
        
        for ( int[] s : skill ) {
            int type = ( s[0] == 1 ) ? -1 : 1;
            damage[s[1]][s[2]] += type * s[5];
            damage[s[1]][s[4]+1] -= type * s[5];
            damage[s[3]+1][s[2]] -= type * s[5];
            damage[s[3]+1][s[4]+1] += type * s[5];
        }
        
        for ( int i = 0 ; i < damage.length ; i++ ) 
            for ( int j = 1 ; j < damage[0].length ; j++ ) 
                damage[i][j] += damage[i][j-1];
        
        for ( int i = 0 ; i < damage[0].length ; i++ ) 
            for ( int j = 1 ; j < damage.length ; j++ ) 
                damage[j][i] += damage[j-1][i];           
        
        for ( int i = 0 ; i < board.length ; i++ ) 
            for ( int j = 0 ; j < board[0].length ; j++) 
                if ( board[i][j] + damage[i][j] > 0 ) answer++;
        
        return answer;
    }
}