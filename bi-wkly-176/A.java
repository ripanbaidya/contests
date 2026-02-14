class Solution {
    public String mapWordWeights(String[] words, int[] weights) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < words.length; i++) {
            String w = words[i];
            int total = 0;

            // calculate weight of current word
            for (int j = 0; j < w.length(); j++) {
                total += weights[w.charAt(j) - 'a'];
            }

            int rem = total % 26;

            // reverse mapping: 0->z, 1->y ... 25->a
            char ch = (char) ('z' - rem);

            sb.append(ch);
        }

        return sb.toString();
    }
}
