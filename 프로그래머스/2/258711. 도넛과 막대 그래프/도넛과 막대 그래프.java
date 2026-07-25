
class Solution {
    public int[] solution(int[][] edges) {
        int N = 1_000_000;
        int[] input = new int[N], output = new int[N];
        int[] answer = new int[4];
        
        for ( int[] edge : edges ) {
            output[edge[0]-1]++;
            input[edge[1]-1]++;
        }
        
        for ( int i = 0 ; i < N ; i++ ) {
            if ( output[i] >= 2 ) {
                if ( input[i] == 0 ) answer[0] = i + 1;
                else {
                    answer[3]++;
                }
            } 
            if ( input[i] >= 1 && output[i] == 0 ) answer[2]++;
        }
        
        answer[1] = output[answer[0]-1] - answer[2] - answer[3];
        return answer;
    }
} 