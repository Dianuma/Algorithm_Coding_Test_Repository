import java.util.*;
class Solution {
    public int[] solution(int[][] dice) {
        List<Integer> list = new ArrayList<>();
        int len = dice.length, index = 0, point = 0, win = 0;
        int[] answer = new int[len/2];
        for ( int i = 0 ; i < len ; i++ ) list.add(i);
        List<List<Integer>> combination = getCombination(list, len/2);
        List<List<Integer>> reversCombination = new ArrayList<>();
        getReverseCombination(combination, reversCombination);
        list.clear();

        for ( int i = 0 ; i < combination.size() ; i++ ) {
            int[] currPoint = getPoint(combination.get(i), reversCombination.get(i), dice );
            if ( point < currPoint[1] ) {
                win = currPoint[0];
                point = currPoint[1];
                index = i;
            }
        }
        combination = ( win > 1 ) ? reversCombination : combination;
        for ( int i = 0 ; i < len/2 ; i++ ) {
            answer[i] = combination.get(index).get(i)+1;
        }
        return answer;
    }

    int[] getPoint(List<Integer> combination, List<Integer> reversCombination, int[][] dice) {
        int winPoint = 0, losePoint = 0;

        List<Integer> selectedSum = getEachPoint(dice, combination);
        List<Integer> remainSum = getEachPoint(dice, reversCombination);

        Collections.sort(selectedSum);
        Collections.sort(remainSum);

        for(int num : selectedSum){
            int winIndex = binarySearch(remainSum, num);
            winPoint += winIndex;
        }

        for(int num : remainSum){
            int loseIndex = binarySearch(selectedSum, num);
            losePoint += loseIndex;
        }
        return ( winPoint >= losePoint ) ? new int[]{1, winPoint} : new int[]{2, losePoint};
    }

    private int binarySearch(List<Integer> list, int target) {
        int left = 0, right = list.size();
        while(left < right) {
            int mid = left + (right - left) / 2;
            if(list.get(mid) < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return right;
    }

    List<Integer> getEachPoint(int[][] dice, List<Integer> indice) {
        List<Integer> sum = new ArrayList<>();
        calculateSum(dice, indice, 0, 0, sum);
        return sum;
    }

    void calculateSum(int[][] dice, List<Integer> indice, int index, int currentSum, List<Integer> sums) {
        if (index == indice.size()) {
            sums.add(currentSum);
            return;
        }

        int diceIndex = indice.get(index);
        for (int i = 0; i < dice[diceIndex].length; i++) {
            calculateSum(dice, indice, index + 1, currentSum + dice[diceIndex][i], sums);
        }
    }


    void getReverseCombination(List<List<Integer>> combination, List<List<Integer>> reversCombination) {
        int count = 0;
        for ( int i = combination.size() - 1 ; i >= combination.size()/2 ; i-- ) {
            reversCombination.add(combination.get(i));
        }
        int[] removePoint = new int[combination.size()];
        for ( int i = 0 ; i < combination.size() ; i++ ) if ( combination.get(i).get(0) != 0 ) removePoint[i]=i;
        for ( int removeIndex : removePoint ) if ( removeIndex != 0 ) combination.remove(removeIndex - count++);

    }

    List<List<Integer>> getCombination(List<Integer> list, int r) {
        List<List<Integer>> result = new ArrayList<>();
        generateCombination(result, new ArrayList<>(), list, 0, r);

        return result;
    }

    void generateCombination(List<List<Integer>> result, List<Integer> current, List<Integer> list, int start, int r) {
        if ( current.size() == r ) {
            result.add(new ArrayList<>(current));
            return;
        }
        for ( int i = start ; i < list.size() ; i++ ) {
            current.add(list.get(i));
            generateCombination(result, current, list, i + 1, r);
            current.remove(current.size() - 1);
        }
    }
}