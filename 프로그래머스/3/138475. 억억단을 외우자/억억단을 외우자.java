import java.util.*;

class Solution {
    public Object solution(int e, int[] starts) {
        int[] answer = new int[starts.length];
        int[][] map = new int[e][2];
        int count = 0;
        for ( int i = 1 ; i <= e ; i++ ) {
            map[i - 1][0] = i;
            for ( int j = 1 ; j <= e / i ; j++ ) {
                if ( i * j > e ) continue; 
                map[i*j - 1][1]++;
                count++;
            }
        }
    
        Arrays.sort(map, new Comparator<int[]>() {

            @Override
            public int compare(int[] o1, int[] o2) {
                int answer = o2[1] - o1[1];
                if (answer == 0) {
                    answer = o1[0] - o2[0];
                }
                return answer;
            }
        });

        for (int i = 0 ; i < starts.length ; i++) {
            int min = starts[i];

            for (int j = 0 ; j < map.length ; j++) {
                if (map[j][0] >= min) {
                    answer[i] = map[j][0];
                    break;
                }
            }

        }
        
        return answer;
    }
}