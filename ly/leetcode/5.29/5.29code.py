class Solution(object):
    def jump(self, nums):
        n = len(nums)
        if n <= 1:
            return 0

        jumps = 0
        current_max_reach = 0
        next_max_reach = 0

        for i in range(n - 1):
            next_max_reach = max(next_max_reach, i + nums[i])
            if i == current_max_reach:
                jumps += 1
                current_max_reach = next_max_reach

        return jumps


# # 示例 1
# nums1 = [2, 3, 1, 1, 4]
# solution1 = Solution()
# output1 = solution1.jump(nums1)
# print(output1)  # 输出: 2

# # 示例 2
# nums2 = [2, 3, 0, 1, 4]
# solution2 = Solution()
# output2 = solution2.jump(nums2)
# print(output2)  # 输出: 2