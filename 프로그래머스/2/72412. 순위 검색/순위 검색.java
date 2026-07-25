import java.util.*;

class Solution {
    static class PL {
        HashMap<String, PS> language = new HashMap<>();

        public void addData(String lang, String pos, String hist, String food, int p) {
            PS ps = language.computeIfAbsent(lang, k -> new PS());
            ps.addData(pos, hist, food, p);
        }
        
        public void sortAll() {
            for (PS ps : language.values()) {
                ps.sortAll();
            }
        }
        
        public int getScore(String lang, String pos, String hist, String food, int p) {
            if (lang.equals("-")) {
                int sum = 0;
                for (PS ps : language.values()) {
                    sum += ps.getScore(pos, hist, food, p);
                }
                return sum;
            }
            PS ps = language.get(lang);
            return (ps == null) ? 0 : ps.getScore(pos, hist, food, p);
        }
    }

    static class PS {
        HashMap<String, PH> position = new HashMap<>();

        public void addData(String pos, String hist, String food, int p) {
            PH ph = position.computeIfAbsent(pos, k -> new PH());
            ph.addData(hist, food, p);
        }

        public void sortAll() {
            for (PH ph : position.values()) {
                ph.sortAll();
            }
        }
        
        public int getScore(String pos, String hist, String food, int p) {
            if(pos.equals("-")) {
                int sum = 0;
                for (PH ph : position.values()) {
                    sum += ph.getScore(hist, food, p);
                }
                return sum;
            }
            PH ph = position.get(pos);
            return (ph == null) ? 0 : ph.getScore(hist, food, p);
        }
    }

    static class PH {
        HashMap<String, PF> history = new HashMap<>();

        public void addData(String hist, String food, int p) {
            PF pf = history.computeIfAbsent(hist, k -> new PF());
            pf.addData(food, p);
        }
        
        public void sortAll() {
            for (PF pf : history.values()) {
                pf.sortLists();
            }
        }
        
        public int getScore(String hist, String food, int p) {
            if (hist.equals("-")) {
                int sum = 0;
                for ( PF pf : history.values() ) {
                    sum += pf.getScore(food, p);
                }
                return sum;
            }
            PF pf = history.get(hist);
            return (pf == null) ? 0 : pf.getScore(food, p); 
        }
    }

    static class PF {
        HashMap<String, List<Integer>> point = new HashMap<>();

        public void addData(String food, int p) {
            List<Integer> list = point.computeIfAbsent(food, k -> new ArrayList<>());
            list.add(p);
        }
        
        public void sortLists() {
            for (List<Integer> list : point.values()) {
                Collections.sort(list);
            }
        }
        
        public int getScore(String food, int p) {
            if (food.equals("-")) {
                int sum = 0;
                for (List<Integer> list : point.values()) {
                    sum += binarySearch(list, p);
                }
                return sum;
            }

            List<Integer> list = point.get(food);
            return (list == null) ? 0 : binarySearch(list, p);
        }
        
        private int binarySearch(List<Integer> list, int p) {
            int left = 0;
            int right = list.size();

            while (left < right) {
                int mid = (left + right) / 2;
                if (list.get(mid) >= p) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            }
            return list.size() - left;
        }

    }
    
    public int[] solution(String[] info, String[] query) {
        int[] answer = new int[query.length];
        PL manager = new PL();
        
        for ( String i : info ) {
            String[] s = i.split(" ");
            manager.addData(s[0], s[1], s[2], s[3], Integer.parseInt(s[4]));
        }
        
        manager.sortAll();
        
        for ( int i = 0 ; i < query.length ; i++ ) {
            String[] s = query[i].replace(" and ", " ").split(" ");
            answer[i] = manager.getScore(s[0], s[1], s[2], s[3], Integer.parseInt(s[4]));
        }
        
        return answer;
    }
}