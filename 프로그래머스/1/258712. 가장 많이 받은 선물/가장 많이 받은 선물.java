import java.util.*;

class Solution {
    public int solution(String[] friends, String[] gifts) {
        int answer = 0;
        Map<String, Map<String, Integer>> map = new HashMap<>();
        Map<String, Integer> score = new HashMap<>();
        Map<String, Integer> count = new HashMap<>();    
        
        for ( int i = 0 ; i < friends.length ; i++ ) {
            Map<String, Integer> tmp = new HashMap<>();
            for ( int j = 0 ; j < friends.length ; j++ ) {
                if ( i == j ) continue;
                tmp.put(friends[j], 0);
            }
            map.put(friends[i], tmp);
            score.put(friends[i], 0);
            count.put(friends[i], 0);
        }
        
        for ( String gift : gifts ) {
            String sender = gift.split(" ")[0], receiver = gift.split(" ")[1];
            Map<String, Integer> tmp = map.get(sender);
            tmp.put(receiver, tmp.get(receiver) + 1);
            score.put(sender, score.get(sender) + 1);
            score.put(receiver, score.get(receiver) - 1);
        }
        
        for ( Map.Entry<String, Map<String, Integer>> senderEntry : map.entrySet() ) {
            String sender = senderEntry.getKey();
            for ( Map.Entry<String, Integer> receiverEntry : senderEntry.getValue().entrySet() ) {
                String receiver = receiverEntry.getKey();
                if ( map.get(sender).get(receiver) > map.get(receiver).get(sender) ) count.put(sender, count.get(sender) + 1);
                else if ( map.get(sender).get(receiver) == map.get(receiver).get(sender) )
                    if ( score.get(sender) > score.get(receiver) ) count.put(sender, count.get(sender) + 1);
            }
        }
        
        for ( Integer c : count.values() ) answer = Math.max(answer, c);
        
        return answer;
    }
    
}