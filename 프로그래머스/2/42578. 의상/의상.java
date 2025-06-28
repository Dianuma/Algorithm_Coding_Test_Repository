import java.util.HashMap;
import java.util.Iterator;
import java.util.Collection;

class Solution {
    public Integer solution(String[][] clothes) {
        Integer result = 1;
        HashMap<String, Integer> cloth = new HashMap<>();
        for (String[] oneSet : clothes) cloth.put(oneSet[1],cloth.getOrDefault(oneSet[1], 0)+1);
        for ( Integer value : cloth.values() ) result *= value + 1;
        return result-1;
    }
}