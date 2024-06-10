class Solution {
<<<<<<< HEAD
=======
 //给你一个仅由小写英文字母组成的字符串 s 。如果一个字符串仅由单一字符组成，那么它被称为 特殊 字符串。例如，字符串 "abc" 不是特殊字符串，而字符串 "ddd"、"zz" 和 "f" 是特殊字符串。
 返回在 s 中出现 至少三次 的 最长特殊子字符串 的长度，如果不存在出现至少三次的特殊子字符串，则返回 -1 。子字符串 是字符串中的一个连续 非空 字符序列。
>>>>>>> 8a22dddc8517e9bbe4916cc391991ad5b2fa012f
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
<<<<<<< HEAD
}
=======
}
>>>>>>> 8a22dddc8517e9bbe4916cc391991ad5b2fa012f
