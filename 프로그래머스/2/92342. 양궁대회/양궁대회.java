class Solution {
    
    int[] ryan = new int[11];
    int[] answer = new int[11];
    int maxDiff = 0;
    
    public int[] solution(int n, int[] info) {
        dfs(0, n, info);
        return ( maxDiff > 0 ) ? answer : new int[]{-1};
    }
    
    void dfs(int index, int remaining, int[] info) {
        /*
        종료 조건 -> 현재 선택이 0 점 ( index 10 ) 까지 완료 되었을 때
        
        선택 했을 때 ( 조건, 현재 남은 화살의 수가 어피치의 것보다 많아야함 ) info[index] + 1 이상
        
        선택하지 않았을 때
        
        return
        */
        if ( index == 10 ) {
            ryan[index] = remaining;
            if ( check(info) ) answer = ryan.clone();
            ryan[index] = 0;
            return;
        }
        
        if ( remaining > info[index] ) {
            ryan[index] = info[index] + 1;
            dfs(index + 1, remaining - info[index] - 1, info);
            ryan[index] = 0;
        }
        
        dfs(index + 1, remaining, info);
        
        return;
    }
    
    boolean check(int[] info) {
        /*
        1. 점수를 내서 비교하기 ( 어피치와 라이언 둘 다 0점 아님, 동점인 경우 어피치 )
        
        2. 점수가 동점일 경우 어피치 승리 -> 라이언 패배이므로 return false;
        
        3. 점수 차이가 같을 경우 최저 점수를 비교
        
        4. return 값에 따라 true 일 경우 answer 배열 갱신
        */
        int a_s = 0;
        int r_s = 0;
        
        for ( int i = 0 ; i <= 10 ; i++ ) {
            if ( info[i] == 0 && ryan[i] == 0 ) continue;
            if ( info[i] < ryan[i] ) r_s += 10 - i;
            else a_s += 10 - i;
        }
        
        int diff = r_s - a_s;
        
        if ( diff <= 0 ) return false;
        
        if ( maxDiff < diff ) {
            maxDiff = diff;
            return true;
        }
        if ( maxDiff == diff ) {
            return isBetter();
        }
        return false;
    }
    
    boolean isBetter() {
        /*
        answer 배열과 ryan 배열의 높은 인덱스부터 비교해가면서 ryan 쪽의 수가 많으면 return true; 
        */
        for ( int i = 10 ; i >= 0 ; i-- ) {
            if ( ryan[i] > answer[i] ) {
                return true;
            } else if ( ryan[i] < answer[i] ) {
                return false;
            }
        }
        return false;
    }
}