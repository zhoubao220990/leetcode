package solution;

/**
 * 5. 最长回文子串
 *
 * 给你一个字符串 s，找到 s 中最长的回文子串。
 *
 * 示例 1：
 * 输入：s = "babad"
 * 输出："bab"
 * 解释："aba" 同样是符合题意的答案。
 *
 * 示例 2：
 * 输入：s = "cbbd"
 * 输出："bb"
 *
 * 提示：
 * 1 <= s.length <= 1000
 * s 仅由数字和英文字母组成
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-palindromic-substring
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author zhoubao
 */
public class S5_longestPalindrome {

    public static void main(String[] args) {
        String s = "abcdega";
        System.out.println(longestPalindrome(s));
    }
    public static String longestPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        //保存起始位置，测试了用数组似乎能比全局变量稍快一点
        int[] position = new int[2];
        char[] str = s.toCharArray();

        for (int i = 0; i < s.length(); i++) {
            //把回文看成中间的部分全是同一字符，左右部分相对称
            //找到下一个与当前字符不同的字符
            i = findLongest(str, i, position);
        }

        return s.substring(position[0], position[1] + 1);
    }

    public static int findLongest(char[] str, int low, int[] position) {
        //查找中间部分
        int high = low;

        // 如果字符相等则高位可继续向上增加, 因为相等的肯定是回文, 并且是挨着的
        while (high < str.length - 1 && str[high + 1] == str[low]) {
            high++;
        }

        //定位中间部分的最后一个字符
        int ans = high;
        //从中间向左右扩散
        while (low > 0 && high < str.length - 1 && str[low - 1] == str[high + 1]) {
            low--;
            high++;
        }

        //记录最大长度
        if (high - low > position[1] - position[0]) {
            position[0] = low;
            position[1] = high;
        }
        return ans;
    }

}
