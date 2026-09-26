class Solution:
    def minQueenMoves(self, source: list[int], target: list[int]) -> int:
        sr, sc = source
        tr, tc = target

        if sr == tr and sc == tc:
            return 0

        if sr == tr or sc == tc:
            return 1

        if abs(sr - tr) == abs(sc - tc):
            return 1

        return 2