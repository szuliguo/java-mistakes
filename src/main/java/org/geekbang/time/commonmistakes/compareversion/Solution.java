package org.geekbang.time.commonmistakes.compareversion;


import java.util.HashMap;

/**
 * @author Legal
 * @date 2020/12/10
 */
class Solution {
    private static boolean compareVersion(String version1, String version2) {
        String[] nums1 = version1.split("\\.");
        String[] nums2 = version2.split("\\.");
        int n1 = nums1.length, n2 = nums2.length;

        // compare versions
        int i1, i2;
        for (int i = 0; i < Math.max(n1, n2); ++i) {
            i1 = i < n1 ? Integer.parseInt(nums1[i]) : 0;
            i2 = i < n2 ? Integer.parseInt(nums2[i]) : 0;
            if (i1 != i2) {
                return i1 > i2;
            }
        }
        return true;
    }

    public static void main(String[] args) {

        String version1 = "0.2.1";
        String version2 = "0.1.2";

        System.out.println(compareVersion(version1, version2));
    }
}