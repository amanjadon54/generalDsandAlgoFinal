package com.preparation.algorithm.dp.tabulation.knapsack01;

import java.util.HashMap;
import java.util.Map;

public class CoinChange {

    private Map<String, Integer> history = new HashMap<>();

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
                    // Case 1: Include the current coin (only if sum >= coin value)
                    dp[sum][i] = dp[sum - coins[i - 1]][i - 1] + dp[sum][i - 1];
                }
                // Case 2: Exclude the current coin
                dp[sum][i] += dp[sum][i - 1];
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
            included = coinChangeMemo(arr, i + 1, sum - arr[i]);
        }
        int excluded = coinChangeMemo(arr, i + 1, sum);
        history.put(key, included + excluded);
        return included + excluded;

    }

    public static void main(String... s) {
        CoinChange cc = new CoinChange();
        System.out.println(cc.coinChange(new int[]{1, 2, 5, 3, 3}, 11));
    }
}
