import java.util.*;

class D{

    public long countGoodIntegersOnPath(long l, long r, String directions) {
        int[] path = buildPath(directions);

        int[] where = new int[16];
        Arrays.fill(where, -1);

        for (int i = 0; i < 7; i++) {
            where[path[i]] = i;
        }

        long ans = count(r, where) - count(l - 1, where);
        return ans;
    }

    // build path in 4x4 grid
    private int[] buildPath(String dir) {
        int[] path = new int[7];
        int r = 0, c = 0;

        path[0] = 0;

        for (int i = 0; i < 6; i++) {
            char ch = dir.charAt(i);
            if (ch == 'D') r++;
            else c++;

            path[i + 1] = r * 4 + c;
        }

        return path;
    }

    private long count(long x, int[] where) {
        if (x < 0) return 0;

        char[] digits = to16(x);
        int[] seq = new int[7];

        return generate(0, 0, seq, where, digits);
    }

    // generate non-decreasing sequence
    private long generate(int idx, int prev, int[] seq, int[] where, char[] digits) {
        if (idx == 7) {
            return countValid(seq, where, digits);
        }

        long res = 0;

        for (int d = prev; d <= 9; d++) {
            seq[idx] = d;
            res += generate(idx + 1, d, seq, where, digits);
        }

        return res;
    }

    // dp check
    private long countValid(int[] seq, int[] where, char[] lim) {
        long tight = 1, loose = 0;

        for (int pos = 0; pos < 16; pos++) {
            int limit = lim[pos] - '0';
            int idx = where[pos];

            long nt = 0, nl = 0;

            if (idx != -1) {
                int d = seq[idx];

                if (loose > 0) nl += loose;

                if (tight > 0) {
                    if (d < limit) nl += tight;
                    else if (d == limit) nt += tight;
                }
            } else {
                if (loose > 0) nl += loose * 10;

                if (tight > 0) {
                    nt += tight;          // equal
                    nl += tight * limit;  // smaller
                }
            }

            tight = nt;
            loose = nl;
        }

        return tight + loose;
    }

    private char[] to16(long x) {
        char[] res = new char[16];

        for (int i = 15; i >= 0; i--) {
            res[i] = (char) ('0' + (x % 10));
            x /= 10;
        }

        return res;
    }
}