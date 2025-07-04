import java.util.*;

class Solution {
    HashSet<List<Integer>> visited = new HashSet<>();
    int[] dr = new int[]{-1, 0, 1, 0}, dc = new int[]{0, -1, 0, 1};
    int MAX_ROW, MAX_COLUMN;
    
    public Object solution(String[] storage, String[] requests) {
        MAX_ROW = storage.length;
        MAX_COLUMN = storage[0].length();
        int answer = MAX_ROW * MAX_COLUMN;
        char[][] storages = new char[storage.length][storage[0].length()];
        
        
        for ( int i = 0 ; i < MAX_ROW ; i++ )
            for ( int j = 0 ; j < MAX_COLUMN ; j++ )
                storages[i][j] = storage[i].charAt(j);
        
        for ( String request : requests ) {
            if ( request.length() == 1 ) answer -= forklift(storages, request.charAt(0));
            else answer -= crane(storages, request.charAt(0));
            dfs(storages, (HashSet<List<Integer>>) visited.clone());
        }
        
        return answer;
    }
    
    int forklift(char[][] storage, char target) {
        int result = 0;
        List<int[]> list = new ArrayList<>();
        for ( int r = 0 ; r < MAX_ROW ; r++ )
            for ( int c = 0 ; c < MAX_COLUMN ; c++ ) 
                if ( storage[r][c] == target ) 
                    if ( isPickupPossible(r, c) ) {
                        result++;
                        list.add(new int[]{r, c});
                    }
        
        for ( int[] p : list ) {
            storage[p[0]][p[1]] = '.';
            visited.add(Arrays.asList(p[0], p[1]));            
        }
        return result;
    }
    
    boolean isPickupPossible(int r, int c) {
        if ( !validate(r, c, 1) ) return true;
        for ( int i = 0 ; i < 4 ; i++ ) if ( visited.contains(Arrays.asList(r + dr[i], c + dc[i])) ) return true;
        return false;
    }
    
    int crane(char[][] storage, char target) {
        int result = 0;
        for ( int r = 0 ; r < storage.length ; r++ )
            for ( int c = 0 ; c < storage[0].length ; c++ ) 
                if ( storage[r][c] == target ) {
                    result++;
                    if ( !validate(r, c, 1) ) {
                        storage[r][c] = ',';
                        visited.add(Arrays.asList(r, c));
                    }
                    else storage[r][c] = ',';
                }
        return result;
    }
    
    void dfs(char[][] storage, HashSet<List<Integer>> visit) {
        if ( visit.size() == 0 ) return;
        HashSet<List<Integer>> next = new HashSet<>();
        for ( List<Integer> p : visit ) {
            for ( int i = 0 ; i < 4 ; i++ ) {
                int r = p.get(0) + dr[i], c = p.get(1) + dc[i];
                if ( validate(r, c, 0) ) {
                    if ( storage[r][c] == ',' ) {
                        storage[r][c] = '.';   
                        visited.add(Arrays.asList(r, c));
                        next.add(Arrays.asList(r, c));
                    }
                }
            }
        }
        dfs(storage, next);
    }
    
    boolean validate(int r, int c, int flag) {
        if ( flag == 1 ) return ( r == 0 || r == MAX_ROW - 1 || c == 0 || c == MAX_COLUMN - 1 ) ? false : true;
        else return ( r < 0 || r > MAX_ROW - 1 || c < 0 || c > MAX_COLUMN - 1 ) ? false : true;
    }
}