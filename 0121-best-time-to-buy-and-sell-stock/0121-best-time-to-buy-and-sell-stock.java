class Solution {
    public int maxProfit(int[] prices) {
        int minimum = Integer.MAX_VALUE;
        int maxProfit = 0;
        for(int i = 1; i < prices.length; i++) {
            minimum = Math.min(minimum,prices[i]);
            int profit = prices[i] - minimum;
            if(profit > maxProfit) {
                maxProfit = profit;
            }
        }
        return maxProfit;
    }
}