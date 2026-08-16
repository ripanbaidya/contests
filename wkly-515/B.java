class Solution {
    public int minPenalty(int period, int[] lights, int[] arrivalTime) {
        int maxLight = 0;

        for (int x : lights) {
            maxLight = Math.max(maxLight, x);
        }

        int ans = 0;

        for (int time : arrivalTime) {
            int r = (int)((long) time % period);

            if (r >= maxLight) {
                int wait = period - r;
                ans = Math.max(ans, wait);
            }
        }

        return ans;
    }
}