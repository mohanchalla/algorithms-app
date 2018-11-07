package problems.dynamicprogramming;

/*
A man has to travel for given number of days by bus. He can buy either:

1 day ticket for 2Rs (valid for 1 day)
7 days ticket for 7Rs (valid for 7 consecutive days)
30 days ticket for 25Rs (valid for 30 consecutive days)
 */
public class MinimumCostOfBusTravel {
    public static void main(String[] args) {
        //int[] arr = {1,4,6,7, 28, 30};
        //int[] arr = {2, 7, 15, 19, 23, 27, 29};
        //int[] arr = {2, 7, 15, 16, 18, 22, 23, 24, 25, 30};
        //int[] arr = {1, 7, 8, 9, 10};
        int[] arr = {1, 7, 8, 9, 10, 15};
        int minimumCoast = minimumCostOfBusTravel(arr);
        System.out.println(minimumCoast);
    }

    /**
     * Let MC(d) denote the minimum cost that will pay for all trips on days 1 through d. The desired answer is then MC(30).
     *
     * To calculate MC(d), observe the following:
     *
     * 1. If there's no trip on day d, then MC(d) = MC(d − 1).
     * As a special case, MC(d) = 0 for all d ≤ 0.
     * 2. Otherwise, the minimum cost involves one of the following:
     *  A. A 1-day pass on day d. In this case, MC(d) = MC(d − 1) + 2.
     *  B. A 7-day pass ending on or after day d. In this case, MC(d) = min(MC(d − 7), MC(d − 6), …, MC(d − 1)) + 7.
     *      Of which we can actually rule out the values with MC(d − 3), MC(d − 2), and MC(d − 1), because day passes would necessarily be cheaper.
     *  C. A 30-day pass covering the whole period. In this case, MC(d) = 25.
     */
    private static int minimumCostOfBusTravel(int[] arr) {
        boolean[] isDayWithTrip = new boolean[31];
        // Find all days with trips. set all trip days to `true`
        for (int i=0; i < arr.length; i++)
            isDayWithTrip[arr[i]] = true;
        int[] minCostUpThroughDay = new int[31];
        minCostUpThroughDay[0] = 0; // for d <= 0 case
        for (int d=1; d <= 30; d++) {
            if (!isDayWithTrip[d]) {
                // If there is no trip on a day update its cost for no trip day with previous day.
                minCostUpThroughDay[d] = minCostUpThroughDay[d-1];
                continue;
            }
            //Possible 1 day pass which would be cheaper
            int minCost = minCostUpThroughDay[d - 1] + 2;
            // Possible of 7 day pass if the day is ending on 7 or after that.
            for (int prevD = Math.max(0, d-7); prevD <= d - 4; prevD++) {
                minCost = Math.min(minCost, minCostUpThroughDay[prevD] + 7);
            }
            // Possible if it is for whole period
            minCost = Math.min(minCost, 25);
            minCostUpThroughDay[d] = minCost;
        }
        return minCostUpThroughDay[30];
    }
}
