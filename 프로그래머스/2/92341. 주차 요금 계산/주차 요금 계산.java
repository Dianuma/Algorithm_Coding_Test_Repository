import java.util.*;
class Solution {
    public Object solution(int[] fees, String[] records) {
        TreeMap<String, Integer> timeMap = new TreeMap<>();
        for ( String r : records ) {
            String[] data = r.split(" ");
            int diff = data[2].equals("IN") ? -timeToInt(data[0]) : timeToInt(data[0]);
            timeMap.merge(data[1], diff, Integer::sum);
        }
        int idx = 0, answer[] = new int[timeMap.size()];
        for (Integer time : timeMap.values()) {
            if ( time <= 0 ) time += timeToInt("23:59");
            answer[idx++] = (time > fees[0]) ? fees[1] + (int) Math.ceil( (time - fees[0]) * 1.0 / fees[2] ) * fees[3]: fees[1];
        }
        return answer;
    }
    int timeToInt(String time){
        String[] parts = time.split(":");
        return Integer.parseInt(parts[0]) * 60 + Integer.parseInt(parts[1]);
    }
}