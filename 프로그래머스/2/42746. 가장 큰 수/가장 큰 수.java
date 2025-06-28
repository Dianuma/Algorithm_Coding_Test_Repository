import java.util.*;

class Solution {
    public String solution(int[] numbers) {
        List<int[]> intermediateSalt = new ArrayList<>();
        for ( int i = 0 ; i < numbers.length ; i++ ) {
            // 배열의 숫자를 4회 ( numbers 원소는 최대 1000 ) 반복 후 4자리까지 끊어서 인덱스와 함께 저장
            // ex - [2, 23, 221] => [[2222, 0], [2323, 1], [2212, 2]]
            String strInt = Integer.toString(numbers[i]).repeat(4).substring(0, 4);
            intermediateSalt.add(new int[]{Integer.parseInt(strInt),i});
        }
        
        // 첫번째 원소를 기준으로 리스트를 내림차순 정렬
        // ex - [[2323, 1], [2222, 0], [2212, 2]]
        intermediateSalt.sort((a, b) -> Integer.compare(b[0], a[0]));
        StringBuilder answer = new StringBuilder();
        
        // answer 에 저장 후 answer 의 첫 글자가 0 -> "0" 반환 / 아니면 answer 반환
        for ( int[] salted : intermediateSalt )
            answer.append(Integer.toString(numbers[salted[1]]));
        
        return (answer.charAt(0) == '0') ? "0" : answer.toString();
    }
}