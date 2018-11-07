package problems.dynamicprogramming;

public class MinimumNumberOfCoinsChangeRequired {
    public static void main(String[] args) {
        int amount = 5;
        int coins[] = {1, 2, 3};
        int[] coinsReq = new int[amount + 1];
        coinsReq[0] = 0;
        for (int amt = 1; amt <= amount; amt++) {
            coinsReq[amt] = Integer.MAX_VALUE;
            for (int j = 0; j < coins.length; j++) {
                if (coins[j] <= amt)
                    coinsReq[amt] = Math.min(coinsReq[amt - coins[j]] + 1, coinsReq[amt]);
            }
        }
        System.out.println(coinsReq[amount]);
    }
}
