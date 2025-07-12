import java.util.*;

public class Solution {
    public int solution(String begin, String target, String[] words) {
        int result = dfs(begin, target, "", new ArrayList(List.of(words)), 0);
        return ( result != Integer.MAX_VALUE ) ? result : 0;
    }
    
    private int dfs(String curr, String target, String delete, List<String> words, int dept) {
        int result = Integer.MAX_VALUE;
        List<String> list = new ArrayList(words);
        list.remove(delete);
        if ( curr.equals(target) ) return dept;
        for( int i = 0 ; i < list.size() ; i++ ) {
            String word = list.get(i);
            if ( canConvert(curr, word) ) {
                result = Math.min(result, dfs(word, target, word, list, dept+1));
            }
        }
        return result;
    }
    
    private boolean canConvert(String word, String target) {
        int diffCount = 0;
        for ( int i = 0 ; i < word.length() ; i++ ) {
            if ( word.charAt(i) != target.charAt(i) ) diffCount++;
            if ( diffCount > 1 ) return false;
        }
        return true;
    }
}
