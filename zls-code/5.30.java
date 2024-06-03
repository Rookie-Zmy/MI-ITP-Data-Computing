class Solution {
     public int maximumLength(String s) {
        // 特判
        if (s.isEmpty())
            return 0;

        List<Integer>[] groups = new ArrayList[26];
        for (int i = 0; i < 26; i++) {
            groups[i] = new ArrayList<>();
        }
        int n = s.length();
        int count = 0;
        for (int i = 0; i < n; i++) {
            count++;
            if (i + 1 == n || s.charAt(i) != s.charAt(i + 1)) {
                groups[s.charAt(i) - 'a'].add(count); // 统计连续字符长度
                count = 0;
            }
        }

        int mx = 0;
        for (List<Integer> group : groups) {
            for (int x : group) {
                mx = Math.max(mx, x);
            }
        }
        // 枚举
        for (int ans = mx; ans >= mx - 2 && ans > 0; ans--) {
            // 枚举字母，计算该字母长度为 ans 的特殊子串有几个
            for (int i = 0; i < 26; i++) {
                int count = 0;
                for (int len : groups[i]) {
                    if (len >= ans)
                        count += len - ans + 1;
                }
                if (count >= 3)
                    return ans;
            }
        }
        return -1;
    }
}