class Solution(object):
    def maximumLength(self, s):
        """
        尝试从索引数量与其内部元素相邻次数中推导与最长长度 - 失败
        测试用例："abcaba"
        """
        index_list = [[] for _ in range(26)]
        max_len = -1

        for i in range(len(s)):
            index_list[ord(s[i]) - ord('a')].append(i)
        print(index_list) # [[0, 3, 5], [1, 4], [2], [], ..., []]
        for indices in index_list:
            count = len(indices)
            if count < 3:
                continue 
            else:
                interval = 0
                for j in range(count-1):
                    if indices[j] + 1 == indices[j+1]:
                        interval += 1
                incre = 1 if interval >= count // 3 * 3 else 0 # incre不知道如何与interval进行映射
                max_len = count // 3 + incre
        return max_len
  
class Solution(object):
    def maximumLength(self, s):
        """
        暴力解法
        """
        n = len(s)
        if n == 0:
            return -1
        # 记录所有可能的特殊子字符串及其出现的次数
        count = {}
        for length in range(1, n + 1):
            for i in range(n - length + 1):
                substring = s[i:i+length]
                if len(set(substring)) == 1:  # 确保是特殊子字符串
                    if substring in count:
                        count[substring] += 1
                    else:
                        count[substring] = 1       
        # 找到出现次数至少为三次的最长特殊子字符串
        max_length = -1
        for substring, c in count.items():
            if c >= 3:
                max_length = max(max_length, len(substring))
        
        return max_length
 