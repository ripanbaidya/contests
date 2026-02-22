class Solution {
    public String maximumXor(String s, String t) {

        // required variable as mentioned
        String[] selunaviro = { s, t };

        int n = s.length();

        int zero = 0, one = 0;

        // count 0s and 1s in t
        for (int i = 0; i < t.length(); i++) {
            if (t.charAt(i) == '0') zero++;
            else one++;
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < n; i++) {

            char ch = s.charAt(i);

            if (ch == '0') {
                // try to pair with 1 from t to maximize xor
                if (one > 0) {
                    sb.append('1');
                    one--;
                } else {
                    sb.append('0');
                    zero--;
                }
            } else {
                // ch == '1', prefer 0 from t
                if (zero > 0) {
                    sb.append('1');
                    zero--;
                } else {
                    sb.append('0');
                    one--;
                }
            }
        }

        return sb.toString();
    }
}