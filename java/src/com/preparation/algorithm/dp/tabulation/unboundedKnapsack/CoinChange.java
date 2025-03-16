package com.preparation.algorithm.dp.tabulation.unboundedKnapsack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CoinChange {

    private Map<String, Integer> history = new HashMap<>();
    private Map<String, List<List<Integer>>> historyCombinations = new HashMap<>();

    public int coinChange(int[] arr, int sum) {
        return coinChangeMemo(arr, 0, sum);
    }

    public int coinChangeDp(int[] coins, int amount) {
        int[][] dp = new int[amount + 1][coins.length + 1];

        // Base case: There's 1 way to make amount 0 (use no coins)
        for (int idx = 0; idx < dp[0].length; idx++) {
            dp[0][idx] = 1;
        }

        for (int sum = 1; sum < dp.length; sum++) {
            for (int i = 1; i < dp[0].length; i++) {
                if (sum >= coins[i - 1]) {
                    // Case 2: Include the current coin (only if sum >= coin value)
                    // Focus that we are using i since we want to reconsider it again due to infinite availability of coins.
                    dp[sum][i] = dp[sum - coins[i - 1]][i] + dp[sum][i - 1];
                } else {
                    // Case 2: Exclude the current coin
                    dp[sum][i] = dp[sum][i - 1];
                }
            }
        }
        return dp[amount][coins.length];
    }

    public int coinChangeMemo(int[] arr, int i, int sum) {
        String key = "" + i + sum;
        if (sum < 0) {
            return 0;
        }
        if (sum == 0) {
            return 1;
        }

        if (i >= arr.length) {
            return 0;
        }

        if (history.containsKey(key)) {
            return history.get(key);
        }

        int included = 0;
        if (arr[i] <= sum) {
            included = coinChangeMemo(arr, i, sum - arr[i]);
        }
        int excluded = coinChangeMemo(arr, i + 1, sum);
        history.put(key, included + excluded);
        return included + excluded;

    }

    public static void main(String... s) {
        CoinChange cc = new CoinChange();
        System.out.println(cc.coinChange(new int[]{5, 2, 1}, 11));
    }

    public List<List<Integer>> getCombinationsMemo(int[] coins, int amount, int idx) {
        String key = idx + "-" + amount;

        // Check if the result is already in history to avoid redundant work
        if (historyCombinations.containsKey(key)) {
            return historyCombinations.get(key);
        }

        // Base case: if the amount is 0, return a list with an empty combination
        if (amount == 0) {
            List<List<Integer>> baseResult = new ArrayList<>();
            baseResult.add(new ArrayList<>()); // Empty combination
            return baseResult;
        }

        // Base case: if no more coins are left or the amount is negative, return empty
        if (idx >= coins.length) {
            return new ArrayList<>();
        }

        // Get combinations including the current coin
        List<List<Integer>> includedCombinations = new ArrayList<>();
        if (coins[idx] <= amount) {
            includedCombinations = getCombinationsMemo(coins, amount - coins[idx], idx);
            for (List<Integer> combination : includedCombinations) {
                combination.add(coins[idx]);  // Add the current coin to each combination
            }
        }

        // Get combinations excluding the current coin
        List<List<Integer>> excludedCombinations = getCombinationsMemo(coins, amount, idx + 1);

        // Combine the results (included + excluded)
        List<List<Integer>> result = new ArrayList<>();
        result.addAll(includedCombinations);
        result.addAll(excludedCombinations);

        // Store the result in the memoization map
        historyCombinations.put(key, result);
        return result;
    }
}
