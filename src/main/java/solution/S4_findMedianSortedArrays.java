package solution;

import java.util.Arrays;

/**
 * 4. 寻找两个正序数组的中位数
 * 给定两个大小分别为 m 和 n 的正序（从小到大）数组nums1 和nums2。请你找出并返回这两个正序数组的 中位数 。
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/median-of-two-sorted-arrays
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author zhoubao
 */
public class S4_findMedianSortedArrays {

    public static void main(String[] args) {
        int[] num1 = new int[]{2,4,6};
        int[] num2 = new int[]{1,3,5};
        System.out.println(findKth(num1, 0, num2, 0, 4));
    }

    /**
     * 二分查找方式, 时间复杂度O(log(m+n))
     * @param nums1
     * @param nums2
     * @return
     */
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        int left = (m + n + 1) / 2;
        int right = (m + n + 2) / 2;

        int leftVal = findKth(nums1, 0, nums2, 0, left);
        int rightVal = findKth(nums1, 0, nums2, 0, right);

        return (leftVal + rightVal) / 2.0;
    }

    /**
     * i: nums1的起始位置 j: nums2的起始位置
     */
    public static int findKth(int[] nums1, int i, int[] nums2, int j, int k){
        //nums1为空数组
        if( i >= nums1.length) {
            return nums2[j + k - 1];
        }
        //nums2为空数组
        if( j >= nums2.length) {
            return nums1[i + k - 1];
        }
        if(k == 1){
            return Math.min(nums1[i], nums2[j]);
        }
        int midVal1 = (i + k / 2 - 1 < nums1.length) ? nums1[i + k / 2 - 1] : Integer.MAX_VALUE;
        int midVal2 = (j + k / 2 - 1 < nums2.length) ? nums2[j + k / 2 - 1] : Integer.MAX_VALUE;
        if(midVal1 < midVal2){
            return findKth(nums1, i + k / 2, nums2, j , k - k / 2);
        }else{
            return findKth(nums1, i, nums2, j + k / 2 , k - k / 2);
        }
    }



    /**
     * 两个数组合成一个数组后排序, 计算中位数, 但是时间复杂度不符合要求
     * @param nums1
     * @param nums2
     * @return
     */
    public static double findMedianSortedArrays_1(int[] nums1, int[] nums2) {
        // 两个数组合并成一个
        int[] nums = new int[nums1.length + nums2.length];
        int count = 0;
        for (int i = 0; i <nums1.length; i++) {
            nums[i] = nums1[i];
            count++;
        }
        for (int j = 0; j < nums2.length; j++) {
            nums[count++] = nums2[j];
        }
        // 数组元素排序
        Arrays.sort(nums);

        if(nums.length==0){
            return 0;
        }
        if(nums.length==1){
            return nums[0];
        }
        // 计算中位数
        int quotient = nums.length / 2;
        int remainder = nums.length % 2;
        if (remainder > 0) {
            return nums[quotient];
        } else {
            return (nums[quotient-1] + nums[quotient])/2d;
        }
    }
}
