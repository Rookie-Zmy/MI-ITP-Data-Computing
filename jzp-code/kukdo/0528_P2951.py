class Solution(object):
    def findPeaks(self, mountain):
        """
        :type mountain: List[int]
        :rtype: List[int]
        """
        res = []
        length = len(mountain)
        i = 1 
        while i < length-1:
            if mountain[i-1] < mountain[i] and mountain[i] > mountain[i+1]:
                res.append(i)
                i += 2 # if there is a peak, jump to next
            else:
                i += 1
        return res
