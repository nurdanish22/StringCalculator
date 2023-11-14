//Nurdanish "Trigger" Effendi bin Roestam Effendi (2224875)
package StringCalculator;
import java.util.Scanner;

public class main {
    public static void main(String[] args) {
    
        //get input from user
        System.out.println("Enter your input (please include space after every number and symbol): ");
        Scanner input = new Scanner(System.in);
        String userInput = input.nextLine();
        input.close();

        //create a new StringCalculator object
        StringCalculator calculator = new StringCalculator(userInput);
        
        //perform the calculation
        double result = calculator.calculate();

        //print the result if the input is valid, otherwise print "Invalid"
        if (Double.isNaN(result)) {
            System.out.println("Invalid");
        } else {
            System.out.printf("Result: %.2f", result);
        }
    }
}