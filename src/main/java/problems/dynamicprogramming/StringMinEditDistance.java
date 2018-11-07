package problems.dynamicprogramming;

public class StringMinEditDistance {
    public static void main(String[] args) {
        String str1 = "abdfe";
        String str2 = "baifl";
        int[][] minEditDistance = new int[str1.length() + 1][str2.length() + 1];
        for (int row = 0; row <= str1.length(); row++) {
            for (int column = 0; column <= str2.length(); column++) {
                if (row == 0 && column == 0) {
                    minEditDistance[row][column] = 0;
                } else if (row == 0) {
                    minEditDistance[row][column] = minEditDistance[row][column - 1] + 1;
                } else if (column == 0) {
                    minEditDistance[row][column] = minEditDistance[row - 1][column] + 1;
                } else {
                    if (str1.charAt(row - 1) == str2.charAt(column - 1)) {
                        minEditDistance[row][column] = minEditDistance[row - 1][column - 1];
                    } else {
                        int min = Math.min(minEditDistance[row][column - 1], minEditDistance[row - 1][column - 1]);
                        minEditDistance[row][column] = Math.min(min, minEditDistance[row - 1][column]) + 1;
                    }
                }
            }
        }
        printMatrix(minEditDistance);
        System.out.println(minEditDistance[str1.length()][str2.length()]);
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
