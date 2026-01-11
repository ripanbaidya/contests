class Solution {
    public int residuePrefixes(String s) {
        boolean seen[] = new boolean[26];
        int cnt = 0;
        int ans = 0;

        for (int i = 0; i < s.length(); i++) {
            int c = s.charAt(i) - 'a';

            if (!seen[c]) {
                seen[c] = true;
                cnt++;
            }

            int len = i + 1;
            if (cnt == len % 3) {
                ans++;
            }
        }
        return ans;
    }
}
