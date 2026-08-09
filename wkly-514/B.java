class Solution {
    public long weightedSum(int[] parent, int[] nums) {
        int n = parent.length;
        int[] depth = new int[n];

        int maxHeight = 0;

        for (int i = 0; i < n; i++) {
            depth[i] = getDepth(i, parent, depth);
            maxHeight = Math.max(maxHeight, depth[i]);
        }

        long totalSum = 0;

        for (int i = 0; i < n; i++) {
            long weight = (long) maxHeight - depth[i] + 1;
            totalSum += (long) nums[i] * weight;
        }

        return totalSum;
    }

    private int getDepth(int node, int[] parent, int[] depth) {
        if (node == 0) {
            return 1;
        }

        if (depth[node] != 0) {
            return depth[node];
        }

        depth[node] = 1 + getDepth(parent[node], parent, depth);
        return depth[node];
    }
}
