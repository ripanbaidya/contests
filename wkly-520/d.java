class Solution {
    public int[] largestPower(int[] nums) {
        int n = nums.length;
        int[] answer = new int[15];

        int[] velqoranim = nums;

        int activeMask = (1 << 15) - 1;
        boolean[] used = new boolean[n];
        int usedCount = 0;

        while (activeMask != 0) {
            int added = 0;

            for (int i = 0; i < n; i++) {
                if (!used[i] && (velqoranim[i] & activeMask) == activeMask) {
                    used[i] = true;
                    added++;
                }
            }

            usedCount += added;

            for (int bit = 0; bit < 15; bit++) {
                if ((activeMask & (1 << bit)) != 0) {
                    answer[14 - bit] = usedCount;
                }
            }

            if (usedCount == n) {
                break;
            }

            int bestIndex = -1;
            int bestValue = -1;

            for (int i = 0; i < n; i++) {
                if (!used[i]) {
                    int value = velqoranim[i] & activeMask;

                    if (value > bestValue) {
                        bestValue = value;
                        bestIndex = i;
                    }
                }
            }

            int before = usedCount;
            int chosen = velqoranim[bestIndex];

            for (int bit = 0; bit < 15; bit++) {
                if ((activeMask & (1 << bit)) != 0 &&
                    (chosen & (1 << bit)) == 0) {
                    answer[14 - bit] = before;
                }
            }

            used[bestIndex] = true;
            usedCount++;

            activeMask &= chosen;

            for (int bit = 0; bit < 15; bit++) {
                if ((activeMask & (1 << bit)) != 0) {
                    answer[14 - bit] = usedCount;
                }
            }
        }

        return answer;
    }
}