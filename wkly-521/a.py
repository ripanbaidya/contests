from collections import Counter

class Solution:
    def rearrangeArray(self, nums: list[int]) -> list[int]:
        cnt = Counter(nums)
        ans = []

        while cnt:
            for x in sorted(cnt):
                ans.append(x)
                cnt[x] -= 1

                if cnt[x] == 0:
                    del cnt[x]

        return ans