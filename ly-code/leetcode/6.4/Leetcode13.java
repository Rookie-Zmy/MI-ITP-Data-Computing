public class Leetcode13 {
    public int romanToInt(String s) {
        int result = 0;
        int n = s.length();

        for (int i = 0; i < n; i++) {
            int value = valueC(s.charAt(i));

            // 检查当前值是否小于其右侧的值，这表明是一个特殊的罗马数字组合
            if (i + 1 < n && value < valueC(s.charAt(i + 1))) {
                result -= value;
            } else {
                result += value;
            }
        }

        return result;
    }

    public int valueC(char c) {
        switch (c) {
            case 'I': return 1;
            case 'V': return 5;
            case 'X': return 10;
            case 'L': return 50;
            case 'C': return 100;
            case 'D': return 500;
            case 'M': return 1000;
            default: return 0; // 如果输入非法字符，这里返回0
        }
    }
}
