class Solution {
    public int accountBalanceAfterPurchase(int purchaseAmount) {
        float roundedAmount = purchaseAmount;
        roundedAmount = Math.round (roundedAmount / 10);
        return 100 - (int) roundedAmount * 10;
    }
}