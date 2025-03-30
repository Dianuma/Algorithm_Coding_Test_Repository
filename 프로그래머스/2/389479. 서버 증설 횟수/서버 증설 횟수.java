import java.util.*;
class Solution {    
    public int solution(int[] players, int m, int k) {
        List<Integer> list = new ArrayList<>();
        int answer = 0, server = 0;
        for ( int t = 0 ; t < players.length ; t++ ) {
            while(list.contains(t)) list.remove(0);
            server = (int) Math.floor(players[t] / m) - list.size();
            for ( int i = 0 ; i < server ; i++ ) {
                list.add(t+k);
                answer++;
            }
        }
        return answer;
    }
}
