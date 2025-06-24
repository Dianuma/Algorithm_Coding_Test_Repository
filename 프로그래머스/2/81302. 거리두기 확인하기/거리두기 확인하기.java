class Solution {
    public int[] solution(String[][] places) {
        int[] answer = new int[5];
        for ( int i = 0 ; i < 5 ; i++ ) answer[i] = solve(places[i]);
        return answer;
    }
    
    private int solve(String[] p) {
        for ( int i = 0 ; i < 5 ; i++ ) {
            for ( int j = 0 ; j < 5 ; j++ ) {
                if ( p[i].charAt(j) == 'P' && check(p, i, j) == 0 ) return 0;    
            }
        }
        return 1;
    }
    
    private int check(String[] p, int r, int c) {
        int[][] dz = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        int[][] df = {{-2, 0}, {0, -2}, {0, 2}, {2, 0}};
        int[][] ds = {{-1, -1}, {-1, 1}, {1, -1}, {1, 1}};
        for ( int[] d : dz ) {
            if ( validate(r + d[0], c + d[1]) ) 
                if ( p[r + d[0]].charAt(c + d[1]) == 'P' ) return 0;
        }
        for ( int[] d : df ) {
            if ( validate(r + d[0], c + d[1]) )
                if ( p[r + d[0]].charAt(c + d[1]) == 'P' && p[r + d[0] / 2].charAt(c + d[1] / 2) != 'X' ) return 0;
        }
        for ( int[] d : ds ) {
            if ( validate(r + d[0], c + d[1]) ) {
                if ( p[r + d[0]].charAt(c + d[1]) == 'P' && 
                    ( p[r + d[0]].charAt(c) != 'X' || p[r].charAt(c + d[1]) != 'X' ) ) return 0;
            }
        }
        return 1;
    }
    
    private boolean validate(int r, int c) {
        if ( r < 0 || r > 4 || c < 0 || c > 4 ) return false;
        return true;
    }
}