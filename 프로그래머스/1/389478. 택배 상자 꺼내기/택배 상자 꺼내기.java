class Solution {
    public int solution(int n, int w, int num) {
        int count = 0;
        while ( n >= num ) {
            num += ( w - ( num - 1 ) % w ) * 2 - 1;
            count++;
        }
        return count;
    }
}