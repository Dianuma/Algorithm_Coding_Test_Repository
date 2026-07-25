import java.util.*;

class Solution {
    public Object solution(int[][] points, int[][] routes) {
        int answer = 0;
        HashMap<Integer, List<int[]>> map = new HashMap<>();
        HashMap<Integer, Deque<int[]>> routeMap = new HashMap<>();
        for ( int i = 0 ; i < routes.length ; i++ ) {
            for ( int pointIndex : routes[i] ) {
                map.computeIfAbsent(i, k -> new ArrayList<>())
                    .add( new int[]{points[pointIndex - 1][0] - 1, points[pointIndex - 1][1] - 1} );
            }
        }
        
        for ( Integer key : map.keySet() ) {
            routeMap.put(key, getRoute(map.get(key)));
        }
        
//         HashMap<String, Set<Integer>> countMap = new HashMap<>();
//         Set<Integer> countedSet = new HashMap<>();
        
//         Set<Integer> set;
//         for ( Integer key : routeMap.keySet() ) {
//             for ( int i = 0 ; i < routeMap.get(key).size() ; i++ ) {
//                 set = countMap.computeIfAbsent(routeMap.get(key).get(i)[0] + ", " + routeMap.get(key).get(i)[1], k -> new HashSet<>());
//                 if ( set.contains(i) ) answer++;
//                 else set.add(i);
//                 System.out.println(key + " : " + routeMap.get(key).get(i)[0] + ", " + routeMap.get(key).get(i)[1] + " : " + set.toString());
//             }
//         }
        
        while ( true ) {
            Set<String> set = new HashSet<>();
            Set<String> copy = new HashSet<>();
            int count = 0;
            for ( Integer key : routeMap.keySet() ) {
                if ( routeMap.get(key).isEmpty() ) {
                    count++;
                    continue;
                }
                int[] p = routeMap.get(key).poll();
                if ( set.contains(SP(p)) ) copy.add(SP(p));
                else set.add(SP(p));
            }
            
            answer += copy.size();
            if ( routeMap.size() == count ) break;
        }
        
        return answer;
    }
    
    String SP(int[] p) {
        return p[0] + ", " + p[1];
    }
    
    Deque<int[]> getRoute(List<int[]> points) {
        Deque<int[]> route = new ArrayDeque<>();
        
        for ( int i = 1 ; i < points.size() ; i++ ) {
            if ( !route.isEmpty() ) route.pollLast();
            for ( int[] point : pointToPoint(points.get(i - 1), points.get(i) ) ) {
                route.offer(point);
            }
        }
        return route;
    }
    
    List<int[]> pointToPoint(int[] x, int[] y) {
        List<int[]> points = new ArrayList<>();

        int r = x[0];
        int c = x[1];

        points.add(new int[]{r, c});

        while (r != y[0]) {
            if (r < y[0]) r++;
            else r--;

            points.add(new int[]{r, c});
        }

        while (c != y[1]) {
            if (c < y[1]) c++;
            else c--;

            points.add(new int[]{r, c});
        }

        return points;
    }
        
}

/*
1. 장애물 없음
2. 무조건 r 좌표를 c 좌표보다 먼저
3. routes 의 Index 를 로봇으로 두고 Map 으로 좌표
{"1" : [[2,2],[1,3]],
} ...
---
4. time 단위로 경로상의 좌표 List 만드는 함수 구현

*/