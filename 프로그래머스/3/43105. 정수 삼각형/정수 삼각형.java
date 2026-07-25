import java.util.*;
class Solution {
    public int solution(int[][] triangle) {
        int answer = 0;
        for ( int i = 0 ; i < triangle.length - 1 ; i++ ) {
            int n = triangle[i + 1].length;
            int[] right = new int[n], left = new int[n];
            for ( int j = 0 ; j < n - 1 ; j++ ) {
                left[j] = triangle[i+1][j] + triangle[i][j];
                right[j+1] = triangle[i+1][j+1] + triangle[i][j];
            }
            for ( int k = 0 ; k < n ; k++ ) {
                triangle[i + 1][k] = Math.max(left[k], right[k]);
            }
        }
        
        int[] target = triangle[triangle.length - 1];
        int max = target[0];
        for ( int t : target ) {
            max = Math.max(max, t);
        }
        return max;
    }
}