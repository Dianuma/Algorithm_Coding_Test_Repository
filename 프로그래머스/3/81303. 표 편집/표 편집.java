import java.util.*;

class Solution {
    
    public Object solution(int n, int k, String[] cmd) {
        String answer = "";
        StringBuilder state = new StringBuilder();
        Stack<Integer> deleted = new Stack<>();
        int[] nextIdx = new int[n];
        int[] preIdx = new int[n];
        int x = 0;
        
        for ( int i = 0 ; i < n ; i++ ) {
            nextIdx[i] = i + 1;
            preIdx[i] = i - 1;
            state.append("O");
        }
        
        nextIdx[n - 1] = -1;
        
        for ( String c : cmd ) {
            char action = c.charAt(0);
            
            switch ( action ) {
                case 'U' :
                    x = Integer.parseInt(c.split(" ")[1]);
                    while ( x-- > 0 ) k = preIdx[k];
                    break;
                case 'D' :
                    x = Integer.parseInt(c.split(" ")[1]);
                    while ( x-- > 0 ) k = nextIdx[k];
                    break;
                case 'C' :
                    deleted.add(k);
                    state.setCharAt(k, 'X');
                    if ( preIdx[k] != -1 ) nextIdx[preIdx[k]] = nextIdx[k];
                    if ( nextIdx[k] != -1 ) preIdx[nextIdx[k]] = preIdx[k];
                    k = ( nextIdx[k] == -1 ) ? preIdx[k] : nextIdx[k];
                    break;
                case 'Z' :
                    int recover = deleted.pop();
                    state.setCharAt(recover, 'O');
                    if ( preIdx[recover] != -1 ) nextIdx[preIdx[recover]] = recover;
                    if ( nextIdx[recover] != -1 ) preIdx[nextIdx[recover]] = recover;
                    break;
                default : break;
            }
        }
        
        return state;
    }
}