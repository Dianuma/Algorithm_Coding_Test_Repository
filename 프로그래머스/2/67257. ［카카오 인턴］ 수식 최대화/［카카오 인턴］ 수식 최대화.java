import java.util.*;

class Solution {
    public Object solution(String expression) {
        String[][] operator = {
            {"\\+","\\-","\\*"},{"\\+","\\*","\\-"},{"\\-","\\+","\\*"},
            {"\\-","\\*","\\+"},{"\\*","\\+","\\-"},{"\\*","\\-","\\+"}
        };
        
        long answer = 0;
        
        for ( String[] ope : operator ) 
            answer = Math.max(answer, Math.abs(calc(expression, ope, 0)));
    
        return answer;
    }
    
    long calc(String expression, String[] operator, int k) {
        if ( k==3 || !( expression.contains("+") || expression.contains("-") || expression.contains("*") ) )
            return Long.parseLong(expression);

        ArrayList<Long> list = new ArrayList<>();

        for ( String exp : expression.split(operator[k]) ) list.add(calc(exp, operator, k + 1));
    
        Long result = list.get(0);
        
        for ( int i = 1 ; i < list.size() ; i++ ) result = operating(result, list.get(i), operator[k]);    
        
        return result;
    }
    
    long operating(Long a, Long b, String ope) {
        switch ( ope.charAt(1) ) {
            case '+' : return a + b;
            case '-' : return a - b;
            default : return a * b;
        }
    }
}