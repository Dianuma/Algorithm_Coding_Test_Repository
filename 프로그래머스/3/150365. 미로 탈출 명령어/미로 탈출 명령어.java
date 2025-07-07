class Solution {
    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        StringBuilder answer = new StringBuilder();
        int dr = r - x, dc = c - y;
        int dk = k - ( Math.abs(dr) + Math.abs(dc) );
        int[] curr = new int[]{x, y};  
        
        if ( dk < 0 || dk % 2 == 1 ) return "impossible";

        dk /= 2;
   
        if ( dr > 0 ) {
            answer.append("d".repeat(dr));
            curr[0] += dr;
            dr = 0;
        }
        if ( curr[0] < n && dk > 0 ) {
            int move = Math.min(n-curr[0], dk);
            dk -= move;
            dr -= move;
            answer.append("d".repeat(move));
        }
        
        if ( dc < 0 ) {
            answer.append("l".repeat(dc * -1));
            curr[1] += dc;
            dc = 0;
        }
        if ( curr[1] > 1 && dk > 0 ) {
            int move = Math.min(curr[1]-1, dk);
            dk -= move;
            dc += move;
            answer.append("l".repeat(move));
        }
        
        if ( dk > 0 ) answer.append("rl".repeat(dk));
        if ( dc > 0 ) answer.append("r".repeat(dc));
        if ( dr < 0 ) answer.append("u".repeat(dr * -1));
        
        return answer.toString();
    }
}