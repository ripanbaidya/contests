class Solution {
public:

    int countBinaryPalindromes(long long n) {
        if (n == 0) return 1;

        int len = 0;
        long long x = n;
        while (x) { len++; x >>= 1; }

        long long ans = 0;
        for (int i = 1; i < len; i++) {
            int half = (i + 1) / 2;
            ans += (1LL << (half - 1));
        }

        auto makePal = [](long long left, bool odd) {
            long long res = left, t = odd ? (left >> 1) : left;
            while (t) {
                res = (res << 1) | (t & 1);
                t >>= 1;
            }
            return res;
        };

        int half = (len + 1) / 2;
        long long start = 1LL << (half - 1);
        long long left = n >> (len - half);

        if (left > start) ans += (left - start);

        long long pal = makePal(left, len % 2);
        if (pal <= n) ans++;

        return ans + 1;
    }
};
