import java.util.*;
class Solution {
    public int solution(int[] numbers, int target) {
        if ( numbers.length == 0 ) return ( target == 0 ) ? 1 : 0;
        return solution(Arrays.copyOfRange(numbers, 1, numbers.length), target - numbers[0]) +
            solution(Arrays.copyOfRange(numbers, 1, numbers.length), target + numbers[0]);
    }
}