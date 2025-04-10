import java.util.*;

class Solution {
    public String solution(long n, String[] bans) {
        List<Long> list = new ArrayList<>();
        for ( String s : bans ) list.add(getIndex(s.toCharArray()));
        Collections.sort(list);
        for ( Long l : list ) n += ( n >= l ) ? 1 : 0;
        return getSpell(n);
    }
    
    Long getIndex(char[] cs) {
        int len = cs.length;
        Long result = 0L;
        for ( char c : cs ) result += (long) Math.pow(26, --len) * ( c - 'a' + 1 );
        return result;
    }
    
    String getSpell(long n) {
        long number = n;
        StringBuilder result = new StringBuilder();
        while (number-- > 0) {
            int c = 'a' + (int) (number % 26);
            result.append((char) c);
            number /= 26;
        }
        return result.reverse().toString();
    }
}