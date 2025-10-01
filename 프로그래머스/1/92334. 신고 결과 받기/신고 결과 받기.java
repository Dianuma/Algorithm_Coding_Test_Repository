import java.util.*;

class Solution {
    void plus(Map<String, Set<String>> m, String report) {
        String[] r = report.split(" ");
        m.get(r[1]).add(r[0]);
    }
    
    public Object solution(String[] id_list, String[] report, int k) {
        Map<String, Set<String>> reported = new HashMap<>();
        Map<String, Integer> order = new HashMap<>();
        int[] answer = new int[id_list.length];
        for ( int i = 0 ; i < id_list.length ; i++ ) {
            reported.put(id_list[i],new HashSet<>());
            order.put(id_list[i], i);
        }
        for ( String r : report ) plus(reported, r);
        for ( String id : id_list ) {
            Set<String> tmp = reported.get(id);
            if ( tmp.size() >= k ) 
                for ( String sender : tmp ) answer[order.get(sender)]++;
        }
        return answer;
    }
    
}