import java.util.Scanner;

public class MatriceMulti {

    public static void main(String[] args) {

        Scanner num = new Scanner(System.in);

        //input the first dimensions of matrix
        System.out.println("Matrix A :");

        int rowsA = num.nextInt();
        int columnsA = num.nextInt();


        //Enter elements in matrix A and print them out
        int [][] matrixA = new int[rowsA][columnsA];


        System.out.println("Enter elements of matrix A : ");

        for (int i = 0; i < rowsA; i++) {
            for (int j = 0; j < columnsA; j++) {
                matrixA[i][j] = num.nextInt();

                System.out.print(matrixA[i][j] + " ");
            }
            System.out.println();

        }

        // Here is the part of matrix B

        //First we are going to input the dimensions of matrix B
        System.out.println("Matrix B :");

        int rowsB = num.nextInt();
        int columnsB = num.nextInt();

        //We are entering elements in matrix B and print them out

        int [][] matrixB = new int [rowsB][columnsB];

        System.out.println("Enter elements of matrix B : ");

        for(int i=0; i < rowsB; i++ ){
            for(int j = 0; j < columnsB; j++){
                matrixB[i][j] = num.nextInt();

                System.out.print(matrixB[i][j] + " ");
            }
            System.out.println();
        }

        // After Enter the elements and their printing next step is the multiplication of them

        // we will need a counter where we use "r" as counter to count the movement in the matrix
        // number of columnsA defines the counter size that's why we considered columnsA


        int [][] matrixC = new int[rowsA][columnsB];

        System.out.println("Matrix c values");

        for (int i= 0; i < rowsA; i++){
            for(int j=0; j < columnsB; j++){

                int sum = 0;
                for(int r = 0; r < columnsA; r++){
                    sum = sum + matrixA[i][r] * matrixB[r][j];
                }
                matrixC[i][j] = sum;
            }
        }
        //after we output the matrix


        for(int i = 0; i < rowsA; i++){
            for(int j = 0; j < columnsB; j++){
                System.out.print(matrixC[i][j] + " ");
            }
            System.out.println();
        }
    }

    }



