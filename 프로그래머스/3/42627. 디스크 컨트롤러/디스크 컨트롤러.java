import java.util.*;

class Solution {
    Queue<int[]> jobQ = new LinkedList<>();
    
    public Object solution(int[][] jobs) {
        PriorityQueue<int[]> q = new PriorityQueue<>(
            (a, b) -> {
                if ( a[1] != b[1] ) return Integer.compare(a[1], b[1]);
                if ( a[0] != b[0] ) return Integer.compare(a[0], b[0]);
                return Integer.compare(a[2], b[2]);
        });
        
        List<int[]> list = new ArrayList<>();

        for (int i = 0; i < jobs.length; i++) {
            list.add(new int[]{jobs[i][0], jobs[i][1], i});
        }

        list.sort(
            (a, b) -> {
                if (a[0] != b[0]) return Integer.compare(a[0], b[0]);
                return Integer.compare(a[2], b[2]);
            }
        );

        for (int[] job : list) {
            jobQ.offer(job);
        }
        
        int answer = 0;
        int time = jobQ.peek()[0];
        
        while (!q.isEmpty() || !jobQ.isEmpty()) {
            add(q, time);

            if (q.isEmpty()) {
                time = jobQ.peek()[0];
                continue;
            }

            int[] curr = q.poll();

            time += curr[1];
            answer += time - curr[0];
        }

        return answer / jobs.length;
    }
    
    void add(PriorityQueue<int[]> q, int time) {
        while (!jobQ.isEmpty() && jobQ.peek()[0] <= time) {
            q.offer(jobQ.poll());
        }
    }
}