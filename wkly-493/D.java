import java.util.*;

class Solution {

    static class DSU {
        int[] p, r;

        DSU(int n) {
            p = new int[n];
            r = new int[n];
            for (int i = 0; i < n; i++) p[i] = i;
        }

        int find(int x) {
            if (p[x] != x) p[x] = find(p[x]);
            return p[x];
        }

        void union(int a, int b) {
            a = find(a);
            b = find(b);
            if (a == b) return;

            if (r[a] < r[b]) {
                p[a] = b;
            } else if (r[b] < r[a]) {
                p[b] = a;
            } else {
                p[b] = a;
                r[a]++;
            }
        }
    }

    public int maxActivated(int[][] points) {
        int n = points.length;

        HashMap<Long, Integer> mapX = new HashMap<>();
        HashMap<Long, Integer> mapY = new HashMap<>();

        int xCnt = 0, yCnt = 0;

        for (int[] pt : points) {
            long x = pt[0], y = pt[1];

            if (!mapX.containsKey(x)) mapX.put(x, xCnt++);
            if (!mapY.containsKey(y)) mapY.put(y, yCnt++);
        }

        int[][] arr = points;   // keeping the input stored

        int total = xCnt + yCnt;
        DSU dsu = new DSU(total);

        for (int[] pt : arr) {
            int xi = mapX.get((long) pt[0]);
            int yi = mapY.get((long) pt[1]) + xCnt;
            dsu.union(xi, yi);
        }

        int[] cnt = new int[total];

        for (int[] pt : arr) {
            int xi = mapX.get((long) pt[0]);
            int root = dsu.find(xi);
            cnt[root]++;
        }

        int max1 = 0, max2 = 0;

        for (int v : cnt) {
            if (v <= 0) continue;

            if (v > max1) {
                max2 = max1;
                max1 = v;
            } else if (v > max2) {
                max2 = v;
            }
        }

        if (max1 == 0) return 1;

        int ans = Math.max(max1 + 1, max1 + max2 + 1);

        if (ans > n + 1) ans = n + 1;

        return ans;
    }
}