import java.util.Arrays;

class Solution {
    public long countIntersectingIntervals(int[][] intervals) {
        int n = intervals.length;

        int[] starts = new int[n];
        int[] ends = new int[n];

        for (int i = 0; i < n; i++) {
            starts[i] = intervals[i][0];
            ends[i] = intervals[i][1];
        }

        Arrays.sort(starts);

        long nonIntersecting = 0;

        for (int i = 0; i < n; i++) {
            int idx = upperBound(starts, ends[i]);
            nonIntersecting += n - idx;
        }

        long totalPairs = (long) n * (n - 1) / 2;
        return totalPairs - nonIntersecting;
    }

    private int upperBound(int[] arr, int target) {
        int low = 0;
        int high = arr.length;

        while (low < high) {
            int mid = low + (high - low) / 2;

            if (arr[mid] <= target) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }

        return low;
    }
}