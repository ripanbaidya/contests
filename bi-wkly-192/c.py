class Solution:
    def longestSubarray(self, nums: list[int], k: int) -> int:
        n = len(nums)
        ans = 0

        for i in range(n):
            total = 0
            seen = set()

            for j in range(i, n):
                x = nums[j]
                total += x
                seen.add((2 * x) % k)

                rem = total % k

                if rem == 0 or rem in seen:
                    ans = max(ans, j - i + 1)

        return ans