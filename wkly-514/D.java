import java.util.*;

class Solution {
    static class Node {
        long cnt;
        int first, last;
        long sumC2;

        Node(long cnt, int first, int last, long sumC2) {
            this.cnt = cnt;
            this.first = first;
            this.last = last;
            this.sumC2 = sumC2;
        }
    }

    private int[] a;
    private int n, N;
    private Node[] tree;

    private static final Node EMPTY = new Node(0, -1, -1, 0);

    public long[] countOfPeaks(int[] nums, int[][] queries) {
        n = nums.length;
        a = nums;
        N = n - 2;

        tree = new Node[4 * N];
        build(1, 1, N);

        int[] trevolimna = nums;

        List<Long> res = new ArrayList<>();

        for (int[] q : queries) {
            if (q[0] == 1) {
                res.add(query(q[1], q[2]));
            } else {
                int index = q[1];
                int val = q[2];

                trevolimna[index] = val;

                for (int p = index - 1; p <= index + 1; p++) {
                    if (p >= 1 && p <= N) {
                        update(1, 1, N, p);
                    }
                }
            }
        }

        long[] ans = new long[res.size()];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = res.get(i);
        }

        return ans;
    }

    private boolean isPeak(int p) {
        return a[p] > a[p - 1] && a[p] > a[p + 1];
    }

    private long comb2(long x) {
        if (x < 2) return 0;
        return x * (x - 1) / 2;
    }

    private Node merge(Node left, Node right) {
        if (left.cnt == 0) return right;
        if (right.cnt == 0) return left;

        long len = (long) right.first - left.last + 1;

        return new Node(
            left.cnt + right.cnt,
            left.first,
            right.last,
            left.sumC2 + right.sumC2 + comb2(len)
        );
    }

    private void build(int node, int lo, int hi) {
        if (lo == hi) {
            tree[node] = isPeak(lo)
                    ? new Node(1, lo, lo, 0)
                    : EMPTY;
            return;
        }

        int mid = (lo + hi) >>> 1;

        build(node * 2, lo, mid);
        build(node * 2 + 1, mid + 1, hi);

        tree[node] = merge(tree[node * 2], tree[node * 2 + 1]);
    }

    private void update(int node, int lo, int hi, int p) {
        if (lo == hi) {
            tree[node] = isPeak(lo)
                    ? new Node(1, lo, lo, 0)
                    : EMPTY;
            return;
        }

        int mid = (lo + hi) >>> 1;

        if (p <= mid) {
            update(node * 2, lo, mid, p);
        } else {
            update(node * 2 + 1, mid + 1, hi, p);
        }

        tree[node] = merge(tree[node * 2], tree[node * 2 + 1]);
    }

    private Node queryRange(int node, int lo, int hi, int ql, int qr) {
        if (qr < lo || hi < ql) {
            return EMPTY;
        }

        if (ql <= lo && hi <= qr) {
            return tree[node];
        }

        int mid = (lo + hi) >>> 1;

        return merge(
            queryRange(node * 2, lo, mid, ql, qr),
            queryRange(node * 2 + 1, mid + 1, hi, ql, qr)
        );
    }

    private long query(int l, int r) {
        long total = comb2((long) (r - l + 1));

        int ql = l + 1;
        int qr = r - 1;

        Node res = ql > qr
                ? EMPTY
                : queryRange(1, 1, N, ql, qr);

        long zero;

        if (res.cnt == 0) {
            zero = total;
        } else {
            long leftLen = res.first - l + 1;
            long rightLen = r - res.last + 1;

            zero = comb2(leftLen)
                    + res.sumC2
                    + comb2(rightLen);
        }

        return total - zero;
    }
}
©leetcode