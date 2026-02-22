class Solution {
    public int scoreDifference(int[] nums) {

        int p1 = 0;
        int p2 = 0;

        // true मतलब first player की turn
        boolean firstTurn = true;

        for (int i = 0; i < nums.length; i++) {

            // if number is odd, toggle turn
            if (nums[i] % 2 != 0) {
                firstTurn = !firstTurn;
            }

            // every 6th index (5, 11, 17, ...)
            if (i % 6 == 5) {
                firstTurn = !firstTurn;
            }

            if (firstTurn) {
                p1 += nums[i];
            } else {
                p2 += nums[i];
            }
        }

        return p1 - p2;
    }
}