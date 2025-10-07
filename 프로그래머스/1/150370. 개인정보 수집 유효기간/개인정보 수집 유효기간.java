import java.util.*;

class Solution {
    public Object solution(String today, String[] terms, String[] privacies) {
        List<int[]> list = new ArrayList<>();
        list.add(new int[]{1, 5});
        list.add(new int[]{-1, 3});
        list.add(new int[]{-1, 1});
        list.add(new int[]{1, 10});
        list.add(new int[]{1, 2});
        
        if ( !list.isEmpty() )
            list.sort( (a, b) -> {
                if (a[0] != b[0]) return Integer.compare(b[0], a[0]);
                if (a[0] == 1) return Integer.compare(a[1], b[1]);
                return Integer.compare(b[1], a[1]);
            });
        return list;
    }
}