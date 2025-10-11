class Solution {
    public int maxPartitionFactor(int[][] points) {
        int n = points.length;
        if(n==2) return 0;

        int[][] mat = points;
        int maxD=0;
        for(int i=0;i<n;i++){
            for(int j=i+1;j<n;j++){
                int d = manhattan(mat[i], mat[j]);
                if(d>maxD) maxD=d;
            }
        }

        int lo=0, hi=maxD;
        while(lo<hi){
            int mid = lo + (hi-lo+1)/2;
            if(canPartition(mat, mid)) lo=mid;
            else hi=mid-1;
        }
        return lo;
    }

    private boolean canPartition(int[][] pts, int D){
        int n=pts.length;
        ArrayList<Integer>[] g = new ArrayList[n];
        for(int i=0;i<n;i++) g[i]=new ArrayList<>();
        for(int i=0;i<n;i++){
            for(int j=i+1;j<n;j++){
                if(manhattan(pts[i], pts[j])<D){
                    g[i].add(j);
                    g[j].add(i);
                }
            }
        }
        int[] col = new int[n];
        Arrays.fill(col, -1);
        ArrayDeque<Integer> q = new ArrayDeque<>();
        for(int i=0;i<n;i++){
            if(col[i]!=-1) continue;
            col[i]=0;
            q.add(i);
            while(!q.isEmpty()){
                int u=q.poll();
                for(int v:g[u]){
                    if(col[v]==-1){
                        col[v]=col[u]^1;
                        q.add(v);
                    }else if(col[v]==col[u]) return false;
                }
            }
        }
        return true;
    }

    private int manhattan(int[] a, int[] b){
        return Math.abs(a[0]-b[0])+Math.abs(a[1]-b[1]);
    }
}
