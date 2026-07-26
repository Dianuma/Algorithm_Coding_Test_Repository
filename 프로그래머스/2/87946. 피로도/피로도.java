class Solution {
    boolean[] visit;
    int answer = 0;

    public int solution(int k, int[][] dungeons) {
        visit = new boolean[dungeons.length];

        dfs(k, dungeons, 0);

        return answer;
    }
    
    void dfs(int k, int[][] dungeons, int count) {
        answer = Math.max(count, answer);
        
        for ( int i = 0 ; i < dungeons.length ; i++ ) {
            if ( visit[i] ) continue;
            
            int need = dungeons[i][0];
            int use = dungeons[i][1];
            
            if ( k < need ) continue;
            
            visit[i] = true;
            
            dfs(k - use, dungeons, count + 1);
            
            visit[i] = false;
        }
        return;
    }
}