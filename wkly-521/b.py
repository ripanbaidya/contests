from collections import Counter

class Solution:
    def maxEqualAdjacentPairs(self, nums: list[int]) -> int:
        base = 0
        cnt = Counter()

        for i in range(len(nums) - 1):
            a, b = nums[i], nums[i + 1]

            if a == b:
                base += 1
            else:
                p = (min(a, b), max(a, b))
                cnt[p] += 1

        gain = max(cnt.values()) if cnt else 0

        return base + gain