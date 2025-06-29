import java.util.*;

class Solution {
    public Object solution(String s) {
        int[] answer = {};
        int c = 0;
        s = s.substring(2, s.length() - 2);
        Map<String, Integer> map = new HashMap<>();
        List<int[]> list = new ArrayList<>();
        for ( String dic : s.split("\\},\\{") ) for ( String e : dic.split(",") ) map.put(e, map.getOrDefault(e, 0) + 1);
        for ( Map.Entry<String, Integer> entry : map.entrySet() ) list.add( new int[]{Integer.parseInt(entry.getKey()), entry.getValue()});
        list.sort((a, b) -> b[1] - a[1]);
        answer = new int[list.size()];
        for ( int i = 0 ; i < list.size() ; i++ ) answer[i] = list.get(i)[0];
        return answer;
    }
}