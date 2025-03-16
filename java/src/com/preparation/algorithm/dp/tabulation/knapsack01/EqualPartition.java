package com.preparation.algorithm.dp.tabulation.knapsack01;

import java.util.HashMap;
import java.util.Map;

/**
 * Explanation on DP code above:
 *
 * Bottom-Up Approach (Dynamic Programming)
 *  In the bottom-up dynamic programming approach, we will fill up a table iteratively rather than using recursion. The idea is to use a 2D table (dp) where dp[i][j] represents whether a subset sum of j can be achieved using the first i elements.
 *
 *  Step-by-Step Conversion
 *  1. Define the DP Table:
 *      Let dp[i][sum] represent whether we can achieve a sum using the first i elements of the array.
        dp[i][sum] = true means we can form sum j using the first i elements.
 *      dp[0][sum] = false for all sum > 0 (we can't form any non-zero sum with 0 elements).
 *      dp[i][0] = true for all i (we can always form sum 0 with an empty subset).
 *
 * 2. Fill the Table: For each number in the array, update the DP table. If a sum j can be achieved by including or excluding the current number, update the DP table accordingly.
 *
 * 3. Final Answer: The final result is found by looking at dp[n][target], where n is the length of the array and target is half of the sum of all elements (if the total sum is even).
 *
 *
 * Explanation:
 * 1. Sum Calculation: We first calculate the total sum of the array. If it’s odd, it's impossible to partition the array into two equal parts, so we return false.
 *
 * 2. Target: We set the target sum to be half of the total sum, as this is the sum we need to form with one subset.
 *
 * 3. DP Table Initialization:
 *      We initialize a DP table dp with dimensions (n+1) x (target+1) where n is the number of elements in nums.
 *      The first column (dp[i][0]) is set to true for all rows, because we can always form the sum 0 by taking no elements.
 *      We initialize all other values to false.
 *
 * 4. Filling the DP Table:
 *      We loop over each item in the array and each possible sum from 1 to target.
 *      If the current number can be included (nums[i - 1] <= sum), we check if we can form the sum either by excluding the current number (dp[i-1][sum]) or by including it (dp[i-1][sum - nums[i - 1]]).
 *      If the current number is too large to be included in the sum (nums[i - 1] > sum), we just carry over the result from the previous row (dp[i - 1][sum]).
 *
 * 5. Final Check:
 *      The answer will be in dp[n][target]. If dp[n][target] is true, it means it’s possible to partition the array into two subsets with equal sum.
 *
 * Time and Space Complexity:
 * Time Complexity: O(n * target), where n is the number of elements in nums and target is half of the total sum.
 * Space Complexity: O(n * target) for the DP table.
 *
 */


public class EqualPartition {
    private Map<String, Boolean> memo = new HashMap<>();

    public boolean canPartitionMemoisation(int[] nums) {
        int sum = 0;

        // Calculate the total sum of the array
        for (int num : nums) {
            sum += num;
        }

        // If the total sum is odd, we cannot partition the array into two equal subsets
        if (sum % 2 != 0) {
            return false;
        }

        // The target sum for each subset is half of the total sum
        int target = sum / 2;

        // Call the recursive helper function to check if we can form the target sum
        return canPartitionMemo(nums, target, 0);
    }

    // Helper function to check if we can form the target sum from the index onward
    private boolean canPartitionMemo(int[] nums, int target, int index) {
        // Base case: If the target sum is 0, we've found a valid subset
        if (target == 0) {
            return true;
        }

        // Base case: If we've exhausted the array, return false
        if (index >= nums.length) {
            return false;
        }

        // Memoization key: Combine the target sum and current index as a unique key
        String key = target + "-" + index;

        // If the result for this subproblem is already computed, return it
        if (memo.containsKey(key)) {
            return memo.get(key);
        }

        // Recursive case: Try two possibilities:
        // 1. Include nums[index] in the subset
        // 2. Exclude nums[index] from the subset
        boolean include = false;
        if (nums[index] <= target) {
            include = canPartitionMemo(nums, target - nums[index], index + 1);
        }

        // If we included nums[index], then we found a valid subset sum
        boolean exclude = canPartitionMemo(nums, target, index + 1);

        // Store the result in the memoization table
        boolean result = include || exclude;
        memo.put(key, result);

        return result;
    }

    public boolean canPartitionDP(int[] nums) {
        int target = 0;
        for (int num : nums) {
            target += num;
        }

        // If the sum is odd, partition is not possible
        if (target % 2 != 0) {
            return false;
        }

        target = target / 2;
        int n = nums.length;

        // DP table, dp[i][j] will be true if a sum of j can be achieved using the first i elements
        boolean[][] dp = new boolean[n + 1][target + 1];

        // We can always achieve sum 0 (by taking no elements)
        for (int i = 0; i <= n; i++) {
            dp[i][0] = true;
        }

        // Fill the DP table
        for (int i = 1; i <= n; i++) {
            for (int sum = 1; sum <= target; sum++) {
                // include the element only if the element is <= sum , since we cant have negative index to handle in the tabulation.
                // Without this we will get indexOutOfBoundException in scenarios wheer the diff of sum-nums[i-1] goes less than 0.
                if (sum >= nums[i - 1]) {
                    dp[i][sum] = dp[i - 1][sum] || dp[i - 1][sum - nums[i - 1]];
                } else {
                    dp[i][sum] = dp[i - 1][sum];
                }
            }
        }

        // The answer will be in dp[n][target], which tells if we can form the target sum using all elements
        return dp[n][target];
    }



}
