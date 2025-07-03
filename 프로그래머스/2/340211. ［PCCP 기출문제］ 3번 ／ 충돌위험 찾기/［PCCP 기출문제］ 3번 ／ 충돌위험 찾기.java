import java.util.*;

class Solution {
    public int solution(int[][] points, int[][] routes) {
        int answer = 0;
        boolean flag = true;
        List<Robot> robots = new ArrayList<>();
        Map<String, Integer> map = new HashMap<>();
        
        for ( int[] route : routes ) robots.add(new Robot(route, points));
        for ( Robot robot : robots ) map.put(robot.getPoint(), map.getOrDefault(robot.getPoint(), 0) + 1);
        for ( Integer i : map.values() ) if ( i > 1 ) answer++;
        map.clear();
        
        while ( flag ) {
            flag = false;
            for ( Robot robot : robots ) {
                if ( robot.move() ) {
                    flag = true;
                    map.put(robot.getPoint(), map.getOrDefault(robot.getPoint(), 0) + 1);
                }
            }
            for ( Integer i : map.values() ) if ( i > 1 ) answer++;
            map.clear();
        }
        return answer;
    }
}

class Robot {
    private List<int[]> movePoints = new ArrayList<>();
    private int[] currPoint = new int[2];
    private int[] nextPoint = new int[2];
    private int count = 0;
    private boolean isGoal = false;
    
    public Robot ( int[] movePoints, int[][] points ) {
        for ( int point : movePoints ) this.movePoints.add(new int[] { points[point-1][0] , points[point-1][1] });
        this.currPoint = new int[] { points[movePoints[0]-1][0], points[movePoints[0]-1][1] };
        this.setNextPoint();
    }
    
    public String getPoint() {
        return Arrays.toString(this.currPoint);
    }
    
    public boolean setNextPoint() {
        if ( ++this.count < this.movePoints.size() ) {
            this.nextPoint = this.movePoints.get(count);
            return true;
        } else return false;
    }
    
    public boolean move() {
        boolean flag = true;
        if ( currPoint[0] == nextPoint[0] && currPoint[1] == nextPoint[1] ) flag = this.setNextPoint();
        if ( currPoint[0] < nextPoint[0] ) currPoint[0]++;
        else if ( currPoint[0] > nextPoint[0] ) currPoint[0]--;
        else if ( currPoint[1] < nextPoint[1] ) currPoint[1]++;
        else if ( currPoint[1] > nextPoint[1] ) currPoint[1]--;
        
        if ( !flag ) this.isGoal = true;
        
        return flag;
    }
}