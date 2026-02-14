struct DSU {
    vector<int> p;
    DSU(int n = 0) { init(n); }
    void init(int n) { p.resize(n); iota(p.begin(), p.end(), 0); }
    int find(int x) { return p[x] == x ? x : p[x] = find(p[x]); }
    void unite(int a, int b) {
        a = find(a), b = find(b);
        if (a != b) p[a] = b;
    }
};

class Solution {
public:
    int sortPermutation(vector<int> &a) {
        int n = a.size();
        if (is_sorted(a.begin(), a.end())) return 0;

        vector<int> pos(n);
        for (int i = 0; i < n; i++) pos[a[i]] = i;

        int maxv = n - 1, B = 0;
        while ((1 << B) <= maxv) B++;
        if (!B) return 0;

        int kmask = (1 << B) - 1;
        bool moved = false;
        for (int v = 0; v < n; v++) {
            if (pos[v] != v) {
                moved = true;
                kmask &= v;
            }
        }
        if (!moved) return 0;

        for (int k = kmask;; k = (k - 1) & kmask) {
            if (ok(a, pos, n, B, k)) return k;
            if (!k) break;
        }
        return 0;
    }

private:
    bool ok(const vector<int> &a, const vector<int> &pos, int n, int B, int k) {
        for (int v = 0; v < n; v++)
            if (pos[v] != v && ((v & k) != k)) return false;

        vector<int> bitIdx(B, -1);
        int r = 0;
        for (int b = 0; b < B; b++)
            if (((k >> b) & 1) == 0) bitIdx[b] = r++;

        int R = 1 << r, all = (1 << B) - 1;
        vector<int> rep(R, -1);
        for (int v = 0; v < n; v++) {
            if ((v & k) != k) continue;
            int extra = v & (~k & all), small = 0;
            if (r) {
                for (int b = 0; b < B; b++)
                    if (bitIdx[b] != -1 && ((extra >> b) & 1))
                        small |= 1 << bitIdx[b];
            }
            rep[small] = v;
        }

        vector<int> dp = rep;
        DSU dsu(n);

        for (int bit = 0; bit < r; bit++) {
            for (int mask = 0; mask < R; mask++) {
                if (mask & (1 << bit)) {
                    int x = dp[mask], y = dp[mask ^ (1 << bit)];
                    if (x != -1 && y != -1) dsu.unite(x, y);
                    if (dp[mask] == -1) dp[mask] = dp[mask ^ (1 << bit)];
                }
            }
        }

        int full = R - 1;
        for (int m = 0; m < R; m++) {
            if (rep[m] == -1) continue;
            int oth = dp[full ^ m];
            if (oth != -1) dsu.unite(rep[m], oth);
        }

        vector<char> seen(n, 0);
        for (int s = 0; s < n; s++) {
            if (seen[s]) continue;
            int cur = s;
            vector<int> cyc;
            while (!seen[cur]) {
                seen[cur] = 1;
                cyc.push_back(cur);
                cur = a[cur];
            }
            if (cyc.size() <= 1) continue;
            int root = dsu.find(cyc[0]);
            for (int v : cyc)
                if (dsu.find(v) != root) return false;
        }
        return true;
    }
};
