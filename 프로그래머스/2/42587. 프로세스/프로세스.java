import java.util.*;

class Solution {
    public Object solution(int[] priorities, int location) {
        int answer = 1;
        Queue<int[]> q = new LinkedList<>();
        List<Integer> list = new ArrayList<>();
        
        for ( int i = 0 ; i < priorities.length ; i++ ) {
            q.add(new int[]{priorities[i], i});
            list.add(priorities[i]);
        }
        
        list.sort((a, b) -> b - a );
        
        while ( list.size() > 0 ) {
            int[] tmp = q.poll();
            if ( tmp[0] == list.get(0) ) {
                if ( tmp[1] == location ) return answer;
                answer++;
                list.remove(0);
            } else q.add(tmp);
        }
        
        return list;
    }
}