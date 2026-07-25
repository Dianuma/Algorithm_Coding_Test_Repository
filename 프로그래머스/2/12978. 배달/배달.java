import java.util.*;

class Solution {
    public int solution(int N, int[][] road, int K) {
        int answer = 0;
        HashMap<Integer, List<int[]>> map = new HashMap<>();
        PriorityQueue<int[]> q = new PriorityQueue<>((a, b) -> Integer.compare(a[1], b[1]));
        int[] dist = new int[N + 1];
        
        Arrays.fill(dist, 1_000_000_000);

        for ( int i = 1 ; i <= N ; i++ ) {
            map.put(i, new ArrayList<>() );
        }
        
        for ( int[] r : road ) {
            map.get(r[0]).add(new int[]{r[1], r[2]});
            map.get(r[1]).add(new int[]{r[0], r[2]});
        }
        
        q.offer(new int[]{1, 0});
        dist[1] = 0;
        
        while ( !q.isEmpty() ) {
            int[] curr = q.poll();
            
            int currNode = curr[0];
            int currDist = curr[1];
            
            for ( int[] next : map.get(currNode) ) {
                int nextNode = next[0];
                int nextDist = next[1] + currDist;
                
                if ( nextDist <= K && nextDist < dist[nextNode] ) {
                    q.offer(new int[]{nextNode, nextDist});
                    dist[nextNode] = nextDist;
                }
            }
        }
        
        for ( int d : dist ) 
            if ( d <= K ) answer++;
        
        return answer;
    }
}