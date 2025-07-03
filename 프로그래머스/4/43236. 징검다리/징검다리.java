import java.util.*;

class Solution {
    public int solution(int distance, int[] rocks, int n) {
        Arrays.sort(rocks);
        int left = 1, right = distance, answer = 0;
        while (left <= right) {
            int mid = (left + right) / 2, prev = 0, remove = 0;
            for (int rock : rocks) {
                if (rock - prev < mid) remove++;
                else prev = rock;
            }
            if (distance - prev < mid) remove++;
            if (remove <= n) {
                answer = mid;
                left = mid + 1;
            } else right = mid - 1;
        }
        return answer;
    }
}