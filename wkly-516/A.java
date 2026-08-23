class Solution {
    public boolean isPalindromic(String s) {
        StringBuilder sb = new StringBuilder();

        for (char c : s.toCharArray()) {
            String b = Integer.toBinaryString(c);
            while (b.length() < 8) b = "0" + b;
            sb.append(b);
        }

        String str = sb.toString();

        int l = 0, r = str.length() - 1;
        while (l < r) {
            if (str.charAt(l) != str.charAt(r)) return false;
            l++;
            r--;
        }

        return true;
    }
}