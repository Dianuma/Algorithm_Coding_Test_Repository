import java.util.*;
class Solution {
    public int solution(int[][] targets) {
        Arrays.sort(targets, (a, b) -> a[1] - b[1]);
        int answer = 0;
        double lastPosition = -1.0;
        for (int[] target : targets) 
            if (lastPosition < target[0]) {
                lastPosition = target[1] - 0.5;
                answer++;
            }
        return answer;
    }
}