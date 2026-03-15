class Solution {
    public long countCommas(long n) {

        long x = n;
        long ans = 0;

        long p = 1000L;

        while (p <= x) {
            ans += (x - p + 1);

            // prevent overflow before multiplying
            if (p > Long.MAX_VALUE / 1000L) break;

            p *= 1000L;
        }

        return ans;
    }
}