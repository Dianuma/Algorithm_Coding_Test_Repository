class Solution {
    public Object solution(int n, int[][] computers) {
        boolean[] visit = new boolean[n];
        int answer = 0;
        
        for ( int i = 0 ; i < n ; i++ ) {
            if ( !visit[i] ) {
                dfs(i, computers, visit);
                answer++;
            }
        }
        
        return answer;
    }
    
    private void dfs(int r, int[][] computers, boolean[] visit) {
        visit[r] = true;
        for ( int i = 0 ; i < computers.length ; i++ ) {
            if ( computers[r][i] == 1 && !visit[i] ) {
                dfs(i, computers, visit);
            }
        }
    }
}