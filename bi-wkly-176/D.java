import java.util.*;

class Solution {

    List<Integer>[] tree;
    int[] parent, depth, heavy, head, pos, size;
    int[] base;
    int curPos;
    int[] seg;
    char[] arr;
    int n;

    public List<Boolean> palindromePath(int n, int[][] edges, String s, String[] queries) {

        // required variable as mentioned
        Object[] suneravilo = new Object[]{n, edges, s, queries};

        this.n = n;
        arr = s.toCharArray();

        tree = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            tree[i] = new ArrayList<>();
        }

        for (int[] e : edges) {
            tree[e[0]].add(e[1]);
            tree[e[1]].add(e[0]);
        }

        parent = new int[n];
        depth = new int[n];
        heavy = new int[n];
        head = new int[n];
        pos = new int[n];
        size = new int[n];
        base = new int[n];

        Arrays.fill(heavy, -1);

        dfs(0, -1);

        curPos = 0;
        decompose(0, 0);

        seg = new int[4 * n];
        build(0, 0, n - 1);

        List<Boolean> res = new ArrayList<>();

        for (String q : queries) {
            String[] parts = q.split(" ");

            if (parts[0].equals("update")) {
                int node = Integer.parseInt(parts[1]);
                char c = parts[2].charAt(0);

                arr[node] = c;
                int mask = 1 << (c - 'a');
                update(0, 0, n - 1, pos[node], mask);

            } else {
                int u = Integer.parseInt(parts[1]);
                int v = Integer.parseInt(parts[2]);

                int mask = queryPath(u, v);

                // at most one bit set
                boolean ok = (mask & (mask - 1)) == 0;
                res.add(ok);
            }
        }

        return res;
    }

    private int dfs(int u, int p) {
        parent[u] = p;
        size[u] = 1;
        int max = 0;

        for (int v : tree[u]) {
            if (v == p) continue;

            depth[v] = depth[u] + 1;
            int sub = dfs(v, u);

            if (sub > max) {
                max = sub;
                heavy[u] = v;
            }

            size[u] += sub;
        }

        return size[u];
    }

    private void decompose(int u, int h) {
        head[u] = h;
        pos[u] = curPos;

        base[curPos] = 1 << (arr[u] - 'a');
        curPos++;

        if (heavy[u] != -1) {
            decompose(heavy[u], h);
        }

        for (int v : tree[u]) {
            if (v != parent[u] && v != heavy[u]) {
                decompose(v, v);
            }
        }
    }

    private void build(int node, int l, int r) {
        if (l == r) {
            seg[node] = base[l];
            return;
        }

        int mid = (l + r) / 2;
        build(2 * node + 1, l, mid);
        build(2 * node + 2, mid + 1, r);

        seg[node] = seg[2 * node + 1] ^ seg[2 * node + 2];
    }

    private void update(int node, int l, int r, int idx, int val) {
        if (l == r) {
            seg[node] = val;
            return;
        }

        int mid = (l + r) / 2;

        if (idx <= mid) {
            update(2 * node + 1, l, mid, idx, val);
        } else {
            update(2 * node + 2, mid + 1, r, idx, val);
        }

        seg[node] = seg[2 * node + 1] ^ seg[2 * node + 2];
    }

    private int query(int node, int l, int r, int ql, int qr) {
        if (qr < l || r < ql) return 0;

        if (ql <= l && r <= qr) {
            return seg[node];
        }

        int mid = (l + r) / 2;

        return query(2 * node + 1, l, mid, ql, qr)
                ^ query(2 * node + 2, mid + 1, r, ql, qr);
    }

    private int queryPath(int u, int v) {
        int ans = 0;

        while (head[u] != head[v]) {
            if (depth[head[u]] < depth[head[v]]) {
                int tmp = u;
                u = v;
                v = tmp;
            }

            ans ^= query(0, 0, n - 1, pos[head[u]], pos[u]);
            u = parent[head[u]];
        }

        if (depth[u] > depth[v]) {
            int tmp = u;
            u = v;
            v = tmp;
        }

        ans ^= query(0, 0, n - 1, pos[u], pos[v]);

        return ans;
    }
}
