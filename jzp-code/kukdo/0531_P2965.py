class Solution(object):
    def findMissingAndRepeatedValues(self, grid):
        """
        :type grid: List[List[int]]
        :rtype: List[int]
        """
        num_sum = 0
        num_set = set()
        n = len(grid)
        res = []
        all_sum = ((1 + n*n) * n*n) / 2
        for row in grid:
            for elem in row:
                if elem in num_set:
                    res.append(elem)
                    continue
                num_sum += elem
                num_set.add(elem)
        res.append(all_sum - num_sum)
        return res
