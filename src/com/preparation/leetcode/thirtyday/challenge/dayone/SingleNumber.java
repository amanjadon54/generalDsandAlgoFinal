package com.preparation.leetcode.thirtyday.challenge.dayone;

/**
 * Given a non-empty array of integers, every element appears twice except for one. Find that single one.
 * <p>
 * Note:
 * <p>
 * Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?
 * <p>
 * Example 1:
 * <p>
 * Input: [2,2,1]
 * Output: 1
 * Example 2:
 * <p>
 * Input: [4,1,2,1,2]
 * Output: 4
 */
public class SingleNumber {
    public int singleNumber(int[] nums) {
        int result = -1;
        for (int i : nums) {
            result = result ^ i;
        }
        return result ^ -1;

    }
}
