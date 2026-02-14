import java.util.HashMap;

class Solution {
    public long countPairs(String[] words) {
        HashMap<String, Long> map = new HashMap<>();

        for (String w : words) {
            String key = normalize(w);
            map.put(key, map.getOrDefault(key, 0L) + 1);
        }

        long ans = 0;
        for (long v : map.values()) {
            if (v > 1) {
                ans += v * (v - 1) / 2;
            }
        }
        return ans;
    }

    private String normalize(String s) {
        int base = s.charAt(0) - 'a';
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < s.length(); i++) {
            int diff = (s.charAt(i) - 'a' - base + 26) % 26;
            sb.append(diff).append('#');
        }
        return sb.toString();
    }
}
