import java.util.*;

class Solution {
    public Object solution(String[] expressions) {
        String[] answer = {};
        return resolve(expressions, getNumeral(expressions));
    }
    
    private List<Integer> getNumeral(String[] expressions) {
        int flag = 1;
        List<Integer> result = new ArrayList<>();
        for ( int i = minimum(expressions) ; i <= 9 ; i++ ) {
            flag = 1;
            for ( String ex : expressions ) {
                String[] list = ex.split(" ");
                if ( list[4].equals("X") ) continue;
                else if ( list[1].equals("+") ) {
                    if ( Integer.parseInt(list[0], i) + Integer.parseInt(list[2], i) != Integer.parseInt(list[4], i) ){
                        flag = 0;
                        break;
                    }
                } else if ( list[1].equals("-") ) {
                    if ( Integer.parseInt(list[0], i) - Integer.parseInt(list[2], i) != Integer.parseInt(list[4], i) ){
                        flag = 0;
                        break;
                    }
                }
            }
            if ( flag == 1 ) result.add(i);
        }
        return result;
    }
    
    private List<String> resolve(String[] expressions, List<Integer> candidate) {
        List<String> result = new ArrayList<>();
        for ( String ex : expressions ) {
            String[] list = ex.split(" ");
            if ( list[4].equals("X") ) 
                result.add(list[0] + " " + list[1] + " " + list[2] + " = " + discoverX(list[0], list[2], list[1], candidate));
        }
        return result;
    }
    
    private String discoverX(String ft, String st, String operator, List<Integer> candidate) {
        String pre = "X", curr = "";
        for ( Integer c : candidate ) {
            int tmp = ( operator.equals("+") ) ? Integer.parseInt(ft, c) + Integer.parseInt(st, c) 
                : Integer.parseInt(ft, c) - Integer.parseInt(st, c);
            curr = Integer.toString(tmp, c);
            if ( !pre.equals("X") && !pre.equals(curr) ) return "?";
            pre = curr;
        }
        return curr;
    }
    
    private int minimum(String[] expressions) {
        int curr = 0, max = -1;
        for ( String ex : expressions ) {
            String[] list = ex.split(" ");
            for ( int i = 0 ; i <= 4 ; i += 2 ) {
                if ( !list[i].equals("X") ) {
                    curr = Integer.parseInt(list[i]) % 10;
                    max = ( max < curr ) ? curr : max;            
                }
            }
        }
        return max + 1;
    }   
}