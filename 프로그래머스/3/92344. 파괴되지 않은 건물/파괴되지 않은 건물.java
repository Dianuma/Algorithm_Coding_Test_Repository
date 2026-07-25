class Solution {
    public int solution(int[][] board, int[][] skill) {
        int answer = board.length * board[0].length;
        int[][] damage = new int[board.length + 1][board[0].length + 1];
        
        for ( int[] s : skill ) {
            int degree = ( s[0] == 1 ) ? -s[5] : s[5];
            damage[s[1]][s[2]] += degree;
            damage[s[1]][s[4]+1] -= degree;
            damage[s[3] + 1][s[2]] -= degree;
            damage[s[3] + 1][s[4] + 1] += degree;
        }
        
        for ( int r = 0 ; r <= board.length ; r++ ) {            
            for ( int c = 1 ; c <= board[0].length ; c++ ) {
                damage[r][c] += damage[r][c-1];
            }
        }
        
        for ( int c = 0 ; c <= board[0].length ; c++ ) {            
            for ( int r = 1 ; r <= board.length ; r++ ) {
                damage[r][c] += damage[r-1][c];
            }
        }        
        
         for ( int r = 0 ; r < board.length ; r++ ) {            
            for ( int c = 0 ; c < board[0].length ; c++ ) {
                if ( board[r][c] + damage[r][c] <= 0 ) answer--;
            }
        }       
        
        return answer;
    }
}
