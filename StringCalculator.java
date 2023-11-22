//Nurdanish "Trigger"
package StringCalculator;
import java.util.Stack;

public class StringCalculator {
    private String inputString;
    private int invalidSymbolCount;

    // Constructor
    public StringCalculator(String input) {
        this.inputString = input;
    }

    public double calculate() {
        // Split the input string into tokens
        String[] tokens = inputString.split(" ");
        // Create stacks for numbers and operations
        Stack<Double> numbers = new Stack<>();
        Stack<Character> operations = new Stack<>();
    
        int numberCount = 0;
        int mathSymbolCount = 0;
        int invalidSymbolCount = 0;
    
        for (String token : tokens) {
            if (isNumber(token)) { // If the token is a number, push it onto the numbers stack
                numbers.push(Double.parseDouble(token));
                numberCount++;
            } else if (isMathSymbol(token.charAt(0))) { // If the token is a math symbol, process operations until the stack is empty or the top of the stack has lower precedence than the token
                while (!operations.isEmpty() && hasPrecedence(token.charAt(0), operations.peek())) {
                    processOperation(numbers, operations);
                }
                operations.push(token.charAt(0));
                mathSymbolCount++;
            } else {
                invalidSymbolCount++;
            }
        }
    
        while (!operations.isEmpty()) { // Process any remaining operations
            processOperation(numbers, operations);
        }        
        
        System.out.println("Number count: " + numberCount);
        System.out.println("Math symbol count: " + mathSymbolCount);
        System.out.println("Invalid symbol count: " + invalidSymbolCount);
        
        if (invalidSymbolCount > 0){ // If there are invalid symbols, return NaN (Not a Number)
            return Double.NaN;
            } else {
            return numbers.pop();
            }     
        
    }
    private boolean isNumber(String token) {
        // Try to parse the token as a double
        // If it succeeds, the token is a number
        // If it fails, the token is not a number
        try {
            Double.parseDouble(token);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public boolean isMathSymbol(char c) { // Check if the character is a math symbol
        return c == '+' || c == '-' || c == '*' || c == '/' || c == '%';
    }

    public boolean hasPrecedence(char op1, char op2) { // Check if the first operation has higher precedence than the second operation
        if ((op1 == '*' || op1 == '/' || op1 == '%') && (op2 == '+' || op2 == '-')) {
            return false;
        } else {
            return true;
        }
    }

    public int getInvalidSymbolCount() { // Return the number of invalid symbols
        return invalidSymbolCount;
    }

    public void processOperation(Stack<Double> numbers, Stack<Character> operations) {
        // Pop the top operation and perform it on the top two numbers
        char operation = operations.pop();
        double number2 = numbers.pop();
        double number1 = numbers.pop();
        
        switch (operation) {
            case '+':
                numbers.push(number1 + number2);
                break;
            case '-':
                numbers.push(number1 - number2);
                break;
            case '*':
                numbers.push(number1 * number2);
                break;
            case '/':
                if (number2 != 0) {
                    numbers.push(number1 / number2);
                } else {
                    System.out.println("Error: Division by zero");
                    System.exit(1);
                }
                break;
            case '%':
                numbers.push(number1 % number2);
                break;
        }
    }
}