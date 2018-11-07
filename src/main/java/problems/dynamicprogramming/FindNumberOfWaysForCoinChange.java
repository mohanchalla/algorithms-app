package problems.dynamicprogramming;

public class FindNumberOfWaysForCoinChange {
    public static void main(String[] args) {
/*
        int[] coins = {1, 3};
        int amount = 5;
*/
        int amount = 250;
        int[] coins = {41, 34, 46, 9, 37, 32, 42, 21, 7, 13, 1, 24, 3, 43, 2, 23, 8, 45, 19, 30, 29, 18, 35, 11};

        long[][] coinChangeWays = new long[coins.length + 1][amount + 1];
        coinChangeWays[0][0] = 1;
        for (int row = 0; row < 1; row++) {
            for (int column = 1; column <= amount; column++) {
                coinChangeWays[row][column] = 0;
            }
        }
        for (int row = 1; row <= coins.length; row++) {
            for (int column = 0; column <= amount; column++) {
                // If the coin value is greater than amount get the no.Of ways from above cell
                if (coins[row - 1] > column) {
                    coinChangeWays[row][column] = coinChangeWays[row - 1][column];
                } else {
                    /* Otherwise we have to follow 3 steps
                    1. Exclude the new coin
                    2. Include the new coin
                    3. add the no.of ways from 1 + 2
                    */
                                            // Exclude new coin   +      //Include the new coin
                    coinChangeWays[row][column] = coinChangeWays[row - 1][column] + coinChangeWays[row][column - coins[row - 1]];
                    // Add the no.of ways from 1 + 2
                }
                //}
            }
        }
        printMatrix(coinChangeWays);
        System.out.println(coinChangeWays[coins.length][amount]);
    }

    private static void printMatrix(long[][] coinChangeWays) {
        for (int i = 0; i < coinChangeWays.length; i++) {
            for (int j = 0; j < coinChangeWays[i].length; j++) {
                System.out.printf("%d ", coinChangeWays[i][j]);
            }
            System.out.println();
        }
    }
}
