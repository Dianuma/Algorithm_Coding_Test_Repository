import java.util.*;

class Solution {
    public Object solution(int N, int number) {
        List<HashSet<Integer>> list = new ArrayList<>();
        
        for(int i=0; i<=8; i++)
        	list.add(new HashSet<Integer>());
        
        if(number==N) return 1;
        
        list.get(1).add(N);
        
        for ( int i = 2 ; i <= 8 ; i++ ) {
            HashSet<Integer> set = list.get(i);
            
            for ( int j = 1 ; j < i ; j++ ) {
                HashSet<Integer> a = list.get(j);
                HashSet<Integer> b = list.get(i - j);
                
                for ( int x : a ){
                    for ( int y : b ) {
                        set.add(x + y);
                        set.add(x - y);
                        set.add(x * y);
                        if ( y != 0 ) set.add(x / y);
                    }
                }
            }
            set.add(Integer.parseInt(String.valueOf(N).repeat(i)));
            
            if ( set.contains(number) ) return i;
        }
        
        return -1;
    }
}

