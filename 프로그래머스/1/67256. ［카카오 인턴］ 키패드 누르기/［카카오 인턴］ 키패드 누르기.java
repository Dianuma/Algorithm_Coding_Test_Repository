import java.util.*;

class Solution {
    public String solution(int[] numbers, String hand) {
        StringBuilder answer = new StringBuilder();
        Map<Integer, int[]> keyPad = Map.of(
            1, new int[]{0, 0}, 2, new int[]{0, 1}, 3, new int[]{0, 2}, 4, new int[]{1, 0}, 5, new int[]{1, 1}, 
            6, new int[]{1, 2}, 7, new int[]{2, 0}, 8, new int[]{2, 1}, 9, new int[]{2, 2}, 0, new int[]{3, 1});
        int[] left = {3, 0};
        int[] right = {3, 2};

        for (int number : numbers) {
            int[] target = keyPad.get(number);

            if (number == 1 || number == 4 || number == 7) {
                move(left, target);
                answer.append("L");
            } else if (number == 3 || number == 6 || number == 9) {
                move(right, target);
                answer.append("R");
            } else {
                int leftDist = distance(left, target);
                int rightDist = distance(right, target);

                if (leftDist < rightDist) {
                    move(left, target);
                    answer.append("L");
                } else if (rightDist < leftDist) {
                    move(right, target);
                    answer.append("R");
                } else {
                    if (hand.equals("right")) {
                        move(right, target);
                        answer.append("R");
                    } else {
                        move(left, target);
                        answer.append("L");
                    }
                }
            }
        }

        return answer.toString();
    }

    private void move(int[] curr, int[] target) {
        curr[0] = target[0];
        curr[1] = target[1];
    }

    private int distance(int[] a, int[] b) {
        return Math.abs(a[0] - b[0]) + Math.abs(a[1] - b[1]);
    }
}
