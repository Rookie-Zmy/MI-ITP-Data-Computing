class Solution(object):
    def canCompleteCircuit(self, gas, cost):
        n = len(gas)
        total_surplus = 0
        current_surplus = 0
        start_station = 0

        for i in range(n):
            total_surplus += gas[i] - cost[i]
            current_surplus += gas[i] - cost[i]

            if current_surplus < 0:
                start_station = i + 1
                current_surplus = 0

        if total_surplus >= 0:
            return start_station
        else:
            return -1


# #示例一
# gas=[1,2,3,4,5]
# cost=[3,4,5,1,2]
# #示例二
# gas = [2,3,4]
# cost = [3,4,3]

# solution = Solution()
# result = solution.canCompleteCircuit(gas, cost)
# print(result)