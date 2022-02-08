package solution;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class S3_lengthOfLongestSubstring {
    public static void main(String[] args) {
        String s = "比好你好的";
        final int i = lengthOfLongestSubstring_2(s);
        System.out.println(i);

        /*String strs="你好";
        int index = strs.charAt(0);
        System.out.println(index);
        System.out.println();
        System.out.println(strs.charAt(0));
        System.out.println(strs.charAt(0) == 97);
        System.out.println((char)97);*/
    }

    /**
     * 字符数组方式
     * 仅支持128位字符
     * @param s
     * @return
     */
    public static int lengthOfLongestSubstring(String s) {
        // 记录字符上一次出现的位置
        int[] last = new int[128];
        for(int i = 0; i < 128; i++) {
            last[i] = -1;
        }
        int n = s.length();
        int res = 0;
        // 窗口开始位置
        int start = 0;
        for(int i = 0; i < n; i++) {
            int index = s.charAt(i);
            start = Math.max(start, last[index] + 1);
            res   = Math.max(res, i - start + 1);
            last[index] = i;
        }
        return res;
    }

    /**
     * hash方式, 不仅支持字符, 还支持任何字符串
     * @param s
     * @return
     */
    public static int lengthOfLongestSubstring_2(String s) {
        // 记录字符上一次出现的位置
        Map<Character, Integer> last = new HashMap<>(s.length());
        int res = 0;

        // 窗口开始位置
        int start = 0;
        for(int i = 0; i < s.length(); i++) {
            start = Math.max(start, last.getOrDefault(s.charAt(i), -1) + 1);
            res   = Math.max(res, i - start + 1);
            // 记录字符最新出现的位置
            last.put(s.charAt(i), i);
        }
        return res;
    }

    /**
     * 迭代方式
     * @param s
     * @return
     */
    public static int lengthOfLongestSubstring_1(String s) {
        if (null == s || s.length() == 0) {
            return 0;
        }
        int maxLength = 0;
        char[] chars = s.toCharArray();
        outer : for (int i=0; i< chars.length; i++) {
            List<Character> indexSet = new ArrayList<>();
            int currentLength = 0;
            inner : for (int j=i; j<s.length(); j++) {
                if (indexSet.contains(chars[j])) {
                    currentLength = indexSet.size();
                    if (currentLength > maxLength) {
                        maxLength = currentLength;
                        i = j;
                    }
                    break inner;
                }
                indexSet.add(chars[j]);
                currentLength = indexSet.size();
                if (currentLength > maxLength) {
                    maxLength = currentLength;
                }
                i = indexSet.size();
            }
        }

        return maxLength;
    }
}
