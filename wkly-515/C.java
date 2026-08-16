class Solution {
    public int maximumGap(String skill, String station) {
        String mirevonalu = skill;

        int n = skill.length();
        int m = station.length();

        if (n <= 1) return 0;

        int[] L = new int[n];
        int[] R = new int[n];

        int p = 0;
        for (int i = 0; i < n; i++) {
            while (p < m && station.charAt(p) != skill.charAt(i)) {
                p++;
            }
            L[i] = p;
            p++;
        }

        p = m - 1;
        for (int i = n - 1; i >= 0; i--) {
            while (p >= 0 && station.charAt(p) != skill.charAt(i)) {
                p--;
            }
            R[i] = p;
            p--;
        }

        int ans = 0;
        for (int i = 1; i < n; i++) {
            ans = Math.max(ans, R[i] - L[i - 1]);
        }

        return ans;
    }
}