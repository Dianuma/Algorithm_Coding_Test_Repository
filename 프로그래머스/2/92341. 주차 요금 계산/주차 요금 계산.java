import java.util.*;
import java.time.*;

class Solution {
    public Object solution(int[] fees, String[] records) {
        int[] answer = {};
        int i = 0;
        Map<String, String> carMap = new HashMap<>();
        TreeMap<String, Integer> timeMap = new TreeMap<>();
        
        for ( String r : records ) timeMap.put(r.split(" ")[1], 0);
        
        for ( String r : records ) {
            String[] data = r.split(" ");
            if ( data[2].equals("IN") ) carMap.put(data[1], data[0]);
            else {
                calcTime(timeMap, data[1], carMap.get(data[1]), data[0]);
                carMap.remove(data[1]);
            }
        }
        
        for ( String key : carMap.keySet() ) calcTime(timeMap, key, carMap.get(key), "23:59");
        
        answer = new int[timeMap.size()];
        for (Integer time : timeMap.values()) {
            answer[i] = (time > fees[0]) ? fees[1] + (int) Math.ceil( (time - fees[0]) * 1.0 / fees[2] ) * fees[3]: fees[1];
            i++;
        }
        
        return answer;
    }
    
    void calcTime(TreeMap<String, Integer> timeMap, String carNum, String in, String out) {
        LocalTime inTime = LocalTime.parse(in);
        LocalTime outTime = LocalTime.parse(out);
        
        int diff = (int) Duration.between(inTime, outTime).toMinutes();
            
        timeMap.compute(carNum, (k, v) -> v + diff);
    }
}