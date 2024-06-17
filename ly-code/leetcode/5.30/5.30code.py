class Solution(object):
    def hIndex(self, citations):
        length = len(citations)
        
        # 将引用次数按照降序排序
        sorted_citations = sorted(citations, reverse=True)
        
        # 遍历排序后的引用次数数组
        for i in range(length):
            # 如果当前论文的引用次数小于其在排序后数组中的位置(从1开始计数)
            if sorted_citations[i] < i + 1:
                # 返回当前位置作为h指数
                return i
        
        # 如果所有论文的引用次数都大于等于论文的总数，则返回论文的总数作为h指数
        return length

#示例1
citations = [3, 0, 6, 1, 5]
solution = Solution()
h_index = solution.hIndex(citations)
print(h_index)  # 输出: 3

#示例2
citations = [1, 3, 1]
solution = Solution()
h_index = solution.hIndex(citations)
print(h_index)  # 输出: 1