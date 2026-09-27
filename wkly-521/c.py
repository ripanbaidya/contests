class Solution:
    def maxSubarray(self, nums: list[int]) -> int:
        dravolenti = nums

        n = len(nums)
        cnt = [0] * 501
        l = 0
        ans = 0

        def bad(x):
            for a in range(1, x // 2 + 1):
                b = x - a
                if a == b:
                    if cnt[a] >= 2:
                        return True
                elif cnt[a] and cnt[b]:
                    return True

            for a in range(1, 501):
                if cnt[a]:
                    b = x + a
                    if b <= 500 and cnt[b]:
                        return True

            return False

        for r in range(n):
            x = nums[r]

            while bad(x):
                cnt[nums[l]] -= 1
                l += 1

            cnt[x] += 1
            ans = max(ans, r - l + 1)

        return ans