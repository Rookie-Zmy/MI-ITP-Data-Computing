from collections import defaultdict

class Solution(object):
    def maximumLength(self, s):
        """
        :type s: str
        :rtype: int
        """
        str_group = defaultdict(list)
        str_len = 0
        for i, ch in enumerate(s):
            str_len += 1
            if i == len(s)-1 or ch != s[i+1]:
                str_group[ch].append(str_len)
                str_len = 0
        res = 0
        for substr in str_group.values():
            substr.sort(reverse=True)
            substr.extend([0,0])
            res = max(res, substr[0]-2, min(substr[0]-1, substr[1]), substr[2])
        
        return res if res else -1
