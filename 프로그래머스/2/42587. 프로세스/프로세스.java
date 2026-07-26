import java.util.*;

class Solution {
    public int solution(int[] priorities, int location) {
        Deque<int[]> q = new ArrayDeque<>();
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> Integer.compare(b, a));
        int answer = 0;
        
        for ( int i = 0 ; i < priorities.length ; i++ ) {
            q.offer(new int[]{priorities[i], i});
            pq.offer(priorities[i]);
        }
        
        while ( !pq.isEmpty() ) {
            answer++;
            int currPri = pq.poll();
            
            while ( true ) {
                int[] curr = q.poll();
                if ( curr[0] == currPri && curr[1] == location ) return answer;
                if ( curr[0] != currPri ) {
                    q.offer(curr);
                    continue;
                } else {
                    break;
                }
            }
        }
        
        
        return answer;
    }
}