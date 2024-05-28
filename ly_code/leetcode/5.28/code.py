class Solution(object):
    def can_jump(self, nums):
        max_reach = 0  # 能到达的最远位置
        for i in range(len(nums)):
            if i > max_reach:
                # 如果当前位置已经超出了能到达的最远位置
                return False
            # 更新能到达的最远位置
            max_reach = max(max_reach, i + nums[i])
            # 如果能到达或者超出了最后一个位置
            if max_reach >= len(nums) - 1:
                return True
        return False

# 创建 Solution 类的实例
solution = Solution()

# 测试用例
nums1 = [2, 3, 1, 1, 4]
nums2 = [3, 2, 1, 0, 4]

print(solution.can_jump(nums1))  # 输出 True
print(solution.can_jump(nums2))