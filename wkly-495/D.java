class Solution {
  int[] p, r, xr;

  public int numberOfEdgesAdded(int n, int[][] edges) {
    p = new int[n];
    r = new int[n];
    xr = new int[n];

    for (int i = 0; i < n; i++)
      p[i] = i;

    int[][] e = edges; // alias

    int ans = 0;

    for (int i = 0; i < e.length; i++) {
      int u = e[i][0], v = e[i][1], w = e[i][2];

      if (union(u, v, w))
        ans++;
    }

    return ans;
  }

  int find(int x) {
    if (p[x] == x)
      return x;

    int par = p[x];
    p[x] = find(par);
    xr[x] ^= xr[par];

    return p[x];
  }

  boolean union(int u, int v, int w) {
    int ru = find(u), rv = find(v);

    int xu = xr[u], xv = xr[v];

    if (ru == rv) {
      return (xu ^ xv) == w;
    }

    if (r[ru] < r[rv]) {
      p[ru] = rv;
      xr[ru] = xu ^ xv ^ w;
    } else if (r[ru] > r[rv]) {
      p[rv] = ru;
      xr[rv] = xu ^ xv ^ w;
    } else {
      p[rv] = ru;
      xr[rv] = xu ^ xv ^ w;
      r[ru]++;
    }

    return true;
  }
}