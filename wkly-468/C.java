class Solution {

    private String toKey(int[] v){
        StringBuilder sb = new StringBuilder(v.length*3);
        for(int x:v){ sb.append(x); sb.append(','); }
        return sb.toString();
    }

    public int minSplitMerge(int[] nums1, int[] nums2) {
        if(java.util.Arrays.equals(nums1, nums2)) return 0;
        int n = nums1.length;
        // keep a copy (as requested)
        int[] tmp = java.util.Arrays.copyOf(nums1, n);

        String target = toKey(nums2);
        java.util.HashSet<String> seen = new java.util.HashSet<>();
        class Node { int[] a; int s; Node(int[] a,int s){this.a=a;this.s=s;} }
        java.util.Queue<Node> q = new java.util.LinkedList<>();
        q.add(new Node(java.util.Arrays.copyOf(nums1,n), 0));
        seen.add(toKey(nums1));

        while(!q.isEmpty()){
            Node cur = q.poll();
            int[] arr = cur.a; int steps = cur.s;
            for(int L=0; L<n; ++L){
                for(int R=L; R<n; ++R){
                    int mlen = R-L+1;
                    int[] mid = new int[mlen];
                    for(int i=0;i<mlen;i++) mid[i]=arr[L+i];
                    int[] rem = new int[n-mlen];
                    int idx=0;
                    for(int i=0;i<L;i++) rem[idx++]=arr[i];
                    for(int i=R+1;i<n;i++) rem[idx++]=arr[i];

                    int m = rem.length;
                    for(int pos=0; pos<=m; ++pos){
                        if(pos==L) continue;
                        int[] nxt = new int[n];
                        int p=0;
                        for(int i=0;i<pos;i++) nxt[p++]=rem[i];
                        for(int x:mid) nxt[p++]=x;
                        for(int i=pos;i<m;i++) nxt[p++]=rem[i];

                        String key = toKey(nxt);
                        if(seen.contains(key)) continue;
                        if(key.equals(target)) return steps+1;
                        seen.add(key);
                        q.add(new Node(nxt, steps+1));
                    }
                }
            }
        }
        return -1;
    }
}