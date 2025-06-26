import java.util.*;

class Solution {
    public String solution(int[] numbers, String hand) {
        StringBuilder answer = new StringBuilder();
        int[][] keyPad = {{3,1},{0,0},{0,1},{0,2},{1,0},{1,1},{1,2},{2,0},{2,1},{2,2}};
        int[] left = {3, 0};
        int[] right = {3, 2};

        for (int number : numbers) {
            int[] target = keyPad[number];

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
