package solution.array;

import java.util.HashMap;
import java.util.Map;

/**
 * 1.两数之和
 *
 * 给定一个整数数组 nums和一个整数目标值 target，请你在该数组中找出 和为目标值 target 的那两个整数，并返回它们的数组下标。
 *
 * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。
 *
 * 你可以按任意顺序返回答案。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/two-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author zhoubao
 */
public class S1_TwoSum {

    public static void main(String[] args) {
        int[] nums = new int[]{1,2,5,9,34};
        int[] indexList = twoSum_1(nums, 7);
        System.out.println(indexList);
    }

    /**
     * 补数法
     * @param nums
     * @param target
     * @return
     */
    public static int[] twoSum_1(int[] nums, int target) {
        int[] indexList = new int[2];
        // 补数map, k->补数 v->补数下标
        Map<Integer, Integer> complementMap = new HashMap<>(nums.length);
        for(int i=0; i<nums.length; i++){
            if (complementMap.containsKey(nums[i])) {
                indexList[0] = i;
                indexList[1] = complementMap.get(nums[i]);
                return indexList;
            }
            // 将数据存入 key为补数 ，value为下标
            complementMap.put(target - nums[i], i);
        }
        return indexList;
    }

    /**
     * 穷举法
     * @param nums
     * @param target
     * @return
     */
    public static int[] twoSum_2(int[] nums, int target){
        int[] indexList = new int[2];
        for (int i=0; i<nums.length-1; i++) {
            for (int j=i+1; j<nums.length; j++) {
                if (nums[i]+nums[j]==target) {
                    indexList[0]=i;
                    indexList[1]=j;
                    return indexList;
                }
            }
        }
        return indexList;
    }
}
