package OOP;
import java.util.*;

/*  Perform matrix addition,subtraction,multiplication and transpose and make it menu driven */
public class Matrix {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter number of rows:");
        int rows = sc.nextInt();
        System.out.println("Enter number of columns:");
        int cols = sc.nextInt();

        int[][] matrix1 = new int[rows][cols];
        int[][] matrix2 = new int[rows][cols];

        System.out.println("Enter elements of first matrix:");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix1[i][j] = sc.nextInt();
            }
        }

        System.out.println("Enter elements of second matrix:");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix2[i][j] = sc.nextInt();
            }
        }

        while (true) {
            System.out.println("Menu:");
            System.out.println("1. Addition");
            System.out.println("2. Subtraction");
            System.out.println("3. Multiplication");
            System.out.println("4. Transpose");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    int[][] sum = new int[rows][cols];
                    for (int i = 0; i < rows; i++) {
                        for (int j = 0; j < cols; j++) {
                            sum[i][j] = matrix1[i][j] + matrix2[i][j];
                        }
                    }
                    System.out.println("Sum of matrices:");
                    printMatrix(sum);
                    break;

                case 2:
                    int[][] diff = new int[rows][cols];
                    for (int i = 0; i < rows; i++) {
                        for (int j = 0; j < cols; j++) {
                            diff[i][j] = matrix1[i][j] - matrix2[i][j];
                        }
                    }
                    System.out.println("Difference of matrices:");
                    printMatrix(diff);
                    break;

                case 3:
                    if (cols != rows) {
                        System.out.println("Matrix multiplication not possible with given dimensions.");
                        break;
                    }
                    int[][] product = new int[rows][cols];
                    for (int i = 0; i < rows; i++) {
                        for (int j = 0; j < cols; j++) {
                            product[i][j] = 0;
                            for (int k = 0; k < cols; k++) {
                                product[i][j] += matrix1[i][k] * matrix2[k][j];
                            }
                        }
                    }   
                    System.out.println("Product of matrices:");
                    printMatrix(product);   
                    break;
                case 4:
                    System.out.println("Transpose of first matrix:");
                    printMatrix(transpose(matrix1));
                    System.out.println("Transpose of second matrix:");
                    printMatrix(transpose(matrix2));
                    break;
                case 5:
                    System.out.println("Exiting...");
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
    public static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            for (int val : row) {
                System.out.print(val + " ");
            }
            System.out.println();
        }
    }   
    public static int[][] transpose(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        int[][] transposed = new int[cols][rows];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                transposed[j][i] = matrix[i][j];
            }
        }
        return transposed;
    }
}

    

