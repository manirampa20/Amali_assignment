import java.util.Scanner;

public class PeakArray {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        //Input matrix dimensions
        System.out.println("Enter number of rows :");
        int rows = scanner.nextInt();

        System.out.println("Enter number of columns");
        int cols = scanner.nextInt();

        //we are going to enter elements
        int[][] arr = new int[rows][cols];

        //input matrix element
        System.out.println("Enter elements of the matrix");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                arr[i][j] = scanner.nextInt();
            }
        }
        scanner.close();

        //Find and print peak-columns
        findPeakColumns(arr);

        //Find and print peak-columns
    }

    public static void findPeakColumns(int[][] matrix) {

        int rows = matrix.length;
        int cols = matrix[0].length;

        // Arrays to keep track of max values
        int[] rowMax = new int[rows];
        int[] colMin = new int[cols];

        //Find max values in each row
        for (int i = 0; i < rows; i++) {
            rowMax[i] = matrix[i][0];
            for (int j = 1; j < cols; j++) {
                if (matrix[i][j] > rowMax[i]) {
                    rowMax[i] = matrix[i][j];
                }
            }
        }

        // Find min values in each column
        for (int j = 0; j < cols; j++) {
            colMin[j] = matrix[0][j];
            for (int i = 1; i < rows; i++) {
                if (matrix[i][j] < colMin[j]) {
                    colMin[j] = matrix[i][j];
                }
            }
        }

        // Check for peak-columns and print them
        boolean foundPeakColumn = false;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (matrix[i][j] == rowMax[i] && matrix[i][j] == colMin[j]) {
                    System.out.println("(" + (i + 1) + "," + (j + 1) + ") = " + matrix[i][j]);
                    foundPeakColumn = true;
                    return;
                }
            }
        }

        // If no peak-column found, print message
        if (!foundPeakColumn) {
            System.out.println("No peak-columns found in the matrix.");
        }
    }
}









