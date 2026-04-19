import java.util.*;

class C {
  public int[][] colorGrid(int n, int m, int[][] grid) {
    int[][] ans = new int[n][m];
    Queue<int[]> q = new LinkedList<>();
    for (int[] g : grid) {
      int r = g[0], c = g[1], color = g[2];
      ans[r][c] = color;
      q.offer(new int[]{r, c, color});
    }

    // all four directions
    int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    
    while (!q.isEmpty()) {
      int size = q.size();
      
      // store best color for this level
      Map<Integer, Integer> next = new HashMap<>();
      
      for (int i = 0; i < size; i ++) {
        int[] cur = q.poll();
        int r = cur[0], c = cur[1], color = cur[2];

        for(int[] d : dirs) {
          // neighbour row and col
          int nr = r + d[0];
          int nc = c + d[1];

          if (nr < 0 || nc < 0 || nr >= n || nc >= m) continue;

          if (ans[nr][nc] != 0) continue;

          int key = nr * m + nc;

          next.put(key, Math.max(next.getOrDefault(key, 0), color));
        }
      }

      // apply the updates and push it to queue
      for (Map.Entry<Integer, Integer> entry : next.entrySet()) {
        int key = entry.getKey();
        int color = entry.getValue();

        int r = key / m, c = key % m;

        ans[r][c] = color;
        q.offer(new int[]{r, c, color});
      }
    }

    return ans;
  }
}