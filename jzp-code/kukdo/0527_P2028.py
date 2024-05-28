''' 自己的题解 '''
class Solution(object):
    def missingRolls(self, rolls, mean, n):
        """
        :type rolls: List[int]
        :type mean: int
        :type n: int
        :rtype: List[int]
        """
        m = len(rolls) # the length of m_rolls
        m_sum = sum(rolls) # the sum of m_rolls
        n_list = [] # the list of n_rolls
        all_sum = mean * (m+n) # the sum of rolls

        if m_sum + 6 * n < all_sum: # n_rolls can't satisfied the result
            return []
        else:
            remain = all_sum - m_sum # the sum of n_rolls
            elem = remain // n # base number of n_rolls
            extra = remain % n # the number needed to be supply
            if elem <= 0: # not legal range
                return []
            for i in range(n): # create base number list of n_rolls
                n_list.append(elem)
            if extra == 0: # do not need extra steps
                return n_list
            else:
                for j in range(extra): # add the number needed to each base number until finsih
                    n_list[j] += 1
                return n_list

''' 官方题解 '''
class Solution(object):
    def missingRolls(self, rolls, mean, n):
        """
        :type rolls: List[int]
        :type mean: int
        :type n: int
        :rtype: List[int]
        """
        n_sum = mean * (n + len(rolls)) - sum(rolls) # the sum of n_rolls
        if not n <= n_sum <= n * 6: # check n_rolls is legal or not
            return []
        quotient = n_sum // n # base number of n_rolls
        remainder = n_sum % n # the number needed to be supply
        return [quotient + 1] * remainder + [quotient] * (n-remainder) # create the list of n_rolls
