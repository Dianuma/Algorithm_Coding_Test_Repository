import java.util.*;

class Solution {
    public Object solution(int n, int[][] paths, int[] gates, int[] summits) {
        int[] answer = new int[2];
        HashMap<Integer, List<int[]>> map = new HashMap<>();
        PriorityQueue<int[]> q = new PriorityQueue<>(
            (a, b) -> {
                if (a[1] == b[1]) {
                    return Integer.compare(a[0], b[0]);
                }

                return Integer.compare(a[1], b[1]);
            }
        );
        HashSet<Integer> target = new HashSet<>();
        int[] intensity = new int[n + 1];
        
        Arrays.fill(intensity, 10_000_001);
        for ( int s : summits ) target.add(s);
        
        for ( int[] path : paths ) {
            map.computeIfAbsent(path[0], k -> new ArrayList<>() ).add(new int[]{path[1], path[2]});
            map.computeIfAbsent(path[1], k -> new ArrayList<>() ).add(new int[]{path[0], path[2]});
        }
        
        for ( int gate : gates ) {
            q.offer(new int[]{gate, 0});
            intensity[gate] = 0;
        }
        
        while ( !q.isEmpty() ) {
            int[] curr = q.poll();
            
            int currNode = curr[0];
            int currDist = curr[1];
            
            if ( target.contains(currNode) ) continue;
            if ( currDist > intensity[currNode] ) continue;

            for ( int[] next : map.get(currNode) ) {
                int nextCurr = next[0];
                int nextDist = Math.max(currDist, next[1]);

                if (nextDist < intensity[nextCurr]) {
                    intensity[nextCurr] = nextDist;
                    q.offer(new int[]{nextCurr, nextDist});
                }
            }
        }
        
        int min = 10_000_001;
        Arrays.sort(summits);
        
        for ( int summit : summits ){
            if ( intensity[summit] < min ) {
                min = intensity[summit];
                answer[0] = summit;
                answer[1] = min;
            }
        }
        
        return answer;
    }
}