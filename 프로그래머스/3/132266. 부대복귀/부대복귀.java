import java.util.*;
class Solution {
    public Object solution(int n, int[][] roads, int[] sources, int destination) {
        int[] answer = new int[sources.length], dist = new int[n + 1];
        Arrays.fill(dist, -1);
        List<List<Integer>> graph = new ArrayList<>();
        
        for (int i = 0; i <= n; i++) graph.add(new ArrayList<>());
        for (int[] road : roads) {
            graph.get(road[0]).add(road[1]);
            graph.get(road[1]).add(road[0]);
        }
        
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(destination);
        dist[destination] = 0;

        while (!queue.isEmpty()) {
            int curr = queue.poll();
            for (int next : graph.get(curr)) {
                if (dist[next] == -1) {
                    dist[next] = dist[curr] + 1;
                    queue.offer(next);
                }
            }
        }
        
        for (int i = 0; i < sources.length; i++) answer[i] = dist[sources[i]];
        
        return answer;
    }
}