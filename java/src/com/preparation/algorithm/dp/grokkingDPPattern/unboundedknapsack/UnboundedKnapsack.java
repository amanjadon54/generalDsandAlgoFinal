package com.preparation.algorithm.dp.grokkingDPPattern.unboundedknapsack;

import java.util.HashMap;

/**
 * Unbounded Knapsack (Repetition of items allowed)
 * Given a knapsack weight W and a set of n items with certain value vali and weight wti, we need to calculate minimum amount
 * that could make up this quantity exactly. This is different from classical Knapsack problem, here we are allowed to use
 * unlimited number of instances of an item.
 * <p>
 * <p>
 * NOTE:  For unbounded : Always use DFs approach and not include or exclude. It may result in wrong answer
 * Difference between 01 an dunbounded:
 * *
 * *  here we will either take 1 item and can come back to it again and not take the item and inc the index. This is the only
 * *  code chnage.
 * *
 */
public class UnboundedKnapsack {

    static int solveKnapSackWithoutDfs01(int weight[], int value[], int k, int sum, int index,
                                         HashMap<String, Integer> memo, int vSum) {
        if (index >= weight.length) {
            return vSum;
        }

        int included = 0;
        if (sum + weight[index] <= k) {
            //consider the current, hence we can include it again
            included = solveKnapSackWithoutDfs01(weight, value, k, sum + weight[index], index, memo, vSum + value[index]);
        }

        //not cconsidering it at all , hence increased the index as it doesnt make sense to reconsider it if we have said no.
        int excluded = solveKnapSackWithoutDfs01(weight, value, k, sum, index + 1, memo, vSum);

        return Math.max(included, excluded);
    }

    static int solveKnapSackTopDownMemo(int weight[], int value[], int k, int sum, int index,
                                        HashMap<String, Integer> memo, int vSum) {

        if (index >= weight.length - 1) {
            return vSum;
        }

        if (memo.containsKey(index + "-" + sum)) {
            return memo.get(index + "-" + sum);
        }

        int maxSum = 0;
        int currWeight = 0;
        for (int i = index; i < weight.length; i++) {
            if (sum + weight[i] <= k) {
                currWeight = solveKnapSackTopDownMemo(weight, value, k, sum + weight[i], i, memo, vSum + value[i]);
            } else {
                currWeight = vSum;
            }
            if (currWeight > maxSum) {
                maxSum = currWeight;
            }
        }

        memo.put(index + "-" + sum, maxSum);
        return maxSum;
    }


    public static void main(String... s) {
        int weight[] = new int[]{1, 3, 4, 5};
        int value[] = new int[]{10, 40, 50, 70};
        int k = 8;

        System.out.println(solveKnapSackTopDownMemo(weight, value, k, 0, 0, new HashMap(), 0));
        System.out.println(solveKnapSackWithoutDfs01(weight, value, k, 0, 0, new HashMap(), 0));
    }
}
