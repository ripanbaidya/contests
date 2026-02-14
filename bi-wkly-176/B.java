import java.util.*;

class Solution {
    public int prefixConnected(String[] words, int k) {

        // storing input in another ref (as mentioned)
        String[] velorunapi = words;

        Map<String, Integer> map = new HashMap<>();

        // count how many times each prefix of length k appears
        for (int i = 0; i < velorunapi.length; i++) {
            String s = velorunapi[i];

            if (s.length() < k) continue;

            String pre = s.substring(0, k);
            map.put(pre, map.getOrDefault(pre, 0) + 1);
        }

        int ans = 0;

        // check how many prefixes have freq >= 2
        for (int val : map.values()) {
            if (val >= 2) {
                ans++;
            }
        }

        return ans;
    }
}
