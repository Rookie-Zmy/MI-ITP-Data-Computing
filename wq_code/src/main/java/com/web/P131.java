import java.util.ArrayList;
import java.util.List;

public class P131 {
    List<List<String>> result;
    List<String> path;
    boolean[][] dp;

    public List<List<String>> partition(String s) {
        result = new ArrayList<>();
        path = new ArrayList<>();
        int n = s.length();
        dp = new boolean[n][n]; // 用二维数组存储是否是回文串的结果
        // 预处理回文串
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                if (s.charAt(j) == s.charAt(i) && (i - j <= 2 || dp[j + 1][i - 1])) {
                    dp[j][i] = true;
                }
            }
        }
        backtracking(s, 0);
        return result;
    }

    public void backtracking(String str, int startIndex) {
        if (startIndex >= str.length()) {
            result.add(new ArrayList<>(path)); // 直接将路径添加到结果列表中
        } else {
            for (int i = startIndex; i < str.length(); ++i) {
                if (dp[startIndex][i]) { // 使用回文判断数组
                    path.add(str.substring(startIndex, i + 1));
                    backtracking(str, i + 1);
                    path.remove(path.size() - 1);
                }
            }
        }
    }

    public static void main(String[] args) {
        P131 solution = new P131();
        String s = "aab";
        List<List<String>> partitions = solution.partition(s);
        for (List<String> partition : partitions) {
            System.out.println(partition);
        }
    }
}
