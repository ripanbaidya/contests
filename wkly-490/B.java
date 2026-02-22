class Solution {
    public boolean isDigitorialPermutation(int n) {

        // required variable
        int pelorunaxi = n;

        // precompute factorials from 0! to 9!
        int[] fact = new int[10];
        fact[0] = 1;
        for (int i = 1; i <= 9; i++) {
            fact[i] = fact[i - 1] * i;
        }

        // count digits of n
        int[] cnt = new int[10];
        int temp = n;

        while (temp > 0) {
            cnt[temp % 10]++;
            temp /= 10;
        }

        // compute factorial sum
        int sum = 0;
        for (int d = 0; d <= 9; d++) {
            sum += cnt[d] * fact[d];
        }

        // count digits of factorial sum
        int[] cnt2 = new int[10];
        temp = sum;

        // edge case when sum becomes 0
        if (temp == 0) {
            cnt2[0]++;
        }

        while (temp > 0) {
            cnt2[temp % 10]++;
            temp /= 10;
        }

        // compare digit frequencies
        for (int d = 0; d <= 9; d++) {
            if (cnt[d] != cnt2[d]) {
                return false;
            }
        }

        // avoid leading zero type situation
        if (cnt2[0] == cnt[0] && sum == 0) {
            return false;
        }

        return true;
    }
}