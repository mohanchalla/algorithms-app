package problems.dynamicprogramming;

/* There are operations allowed
1. Insert -->
2. Remove (down arrow)

3. Replace (diagonal arrow)

Two find min operations there are two rules
1. If minOperationMatrix[row][column - 1] == minOperationMatrix[row - 1][column] (row == column)
      then get the values from diagonal cell
          minOperationMatrix[row][column] = minOperationMatrix[row -1][column - 1];
2. If row != column
       then get the value as Math.min(insert, replace, remove) + 1

*/
public class FindMinimumEditDistanceOneStringtoAnother {
    public static void main(String[] args) {
        String str1 = "acfik", str2 = "bcfak";
        int[][] minOperationMatrix = new int[str1.length() + 1][str2.length() + 1];

        for (int row = 0; row <= str1.length(); row++) {
            for (int column = 0; column <= str2.length(); column++) {
                // This is for null && null box case
                if (row == 0 && column == 0) {
                    minOperationMatrix[row][column] = 0;
                } else if (row == 0) {// This is for null row case
                    minOperationMatrix[row][column] = minOperationMatrix[row][column - 1] + 1;
                } else if (column == 0) {// This is for null column case
                    minOperationMatrix[row][column] = minOperationMatrix[row - 1][column] + 1;
                } else {
                    // compare row character in str1 with column character in str2
                    if (str1.charAt(row - 1) == str2.charAt(column - 1)) {
                        minOperationMatrix[row][column] = minOperationMatrix[row - 1][column - 1];
                    } else {
                        int minAmongInsertAndReplace = Math.min(minOperationMatrix[row][column - 1], minOperationMatrix[row - 1][column - 1]);
                        minOperationMatrix[row][column] = Math.min(minAmongInsertAndReplace, minOperationMatrix[row -1][column]) + 1;
                    }
                }
            }
        }
        printMatrix(minOperationMatrix);
        System.out.println(minOperationMatrix[str1.length()][str2.length()]);
    }

    private static void printMatrix(int[][] minOperationMatrix) {
        for (int i = 0; i < minOperationMatrix.length; i++) {
            for (int j = 0; j < minOperationMatrix[i].length; j++) {
                System.out.printf("%d ", minOperationMatrix[i][j]);
            }
            System.out.println();
        }
    }
}
