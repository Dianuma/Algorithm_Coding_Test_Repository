class Solution {
    static int N = 1_000_000;

    public int[] solution(int[][] edges) {
        int[] in = new int[N];
        int[] out = new int[N];
        for(int[] edge : edges) {
            out[edge[0]-1]++;
            in[edge[1]-1]++;
        }
        int start = 0;
        int eight = 0;
        int stick = 0;
        for(int i=0; i<N; i++) {
            if(out[i] >= 2) {
                if(in[i] == 0) {
                    start = i;
                }else {
                    eight++;
                }
            }else if(out[i] == 0 && in[i] > 0){
                stick++;
            }
        }
        return new int[] {start+1,out[start]-eight-stick,stick,eight};
    }
}