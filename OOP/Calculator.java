package OOP;
import java.util.*;
/*
 * Simple calculator to perform basic arithmetic operations
 * Uses exception handling to manage invalid inputs and division by zero
 * Menu driven
 */
public class Calculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double result;

        try {
            System.out.print("Enter first number: ");
            double num1 = scanner.nextDouble();

            System.out.print("Enter second number: ");
            double num2 = scanner.nextDouble();

            System.out.print("Enter an operator (+, -, *, /): ");
            char operator = scanner.next().charAt(0);

            switch (operator) {
                case '+':
                    result = num1 + num2;
                    break;
                case '-':
                    result = num1 - num2;
                    break;
                case '*':
                    result = num1 * num2;
                    break;
                case '/':
                    if (num2 == 0.0) {
                        throw new ArithmeticException("Division by zero is not allowed.");
                    }
                    result = num1 / num2;
                    break;
                default:
                    throw new IllegalArgumentException("Invalid operator: " + operator);
            }

            System.out.printf("The result is: %.2f\n", result);

        } catch (ArithmeticException ae) {
            System.out.println("Error: " + ae.getMessage());
        } catch (IllegalArgumentException ie) {
            System.out.println("Error: " + ie.getMessage());
        } catch (Exception e) {
            System.out.println("Error: Invalid input. Please enter numbers and a valid operator.");
        } finally {
            System.out.println("Calculation attempt completed.");
            scanner.close();
        }
    }
}