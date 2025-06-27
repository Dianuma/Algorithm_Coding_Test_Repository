class Solution {
    public String solution(String video_len, String pos, String op_start, String op_end, String[] commands) {
        int videoEnd = toMinutes(video_len), curr = toMinutes(pos);
        int opStart = toMinutes(op_start), opEnd = toMinutes(op_end);

        for ( String cmd : commands ) {
            if ( curr >= opStart && curr < opEnd ) curr = opEnd;
            curr = ( cmd.equals("next") ) ? Math.min(curr + 10, videoEnd) : Math.max(curr - 10, 0);
        }

        return (curr >= opStart && curr < opEnd) ? op_end : toTimeString(curr);
    }

    private int toMinutes(String time) {
        return Integer.parseInt(time.split(":")[0]) * 60 + Integer.parseInt(time.split(":")[1]);
    }

    private String toTimeString(int minutes) {
        return String.format("%02d:%02d", minutes / 60, minutes % 60);
    }
}