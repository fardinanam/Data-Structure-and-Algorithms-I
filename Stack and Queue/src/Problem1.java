import java.util.Scanner;

public class Problem1 {
    private static boolean isValid(String expression) {
        //  Number of left and right parenthesis matches
        //  There can't be any sign before or after another sign
        //  If there is a number on the right of a (-) then
        //      there should be a number or a left parenthesis on the left of it too
        //  If there is a number on the left of a (-) then
        //      there should be a left parenthesis or a number on the right of it
        //  If there is a '(' on the left of a '-' then it must be a unary operator
        //      so, there should be a ')' after the adjacent digit of the '-' sign
        int leftParenthesis = 0;
        int rightParenthesis = 0;
        char lastChar = expression.charAt(0);
        String operators = "/*+-";

        for(int i=0; i<expression.length(); i++) {
            char currChar = expression.charAt(i);
            if(currChar == '(') {
                leftParenthesis++;
            } else if(currChar == ')') {
                rightParenthesis++;
            }
            try {
                if(i != 0) {
                    if(operators.contains(Character.toString(currChar))
                            && operators.contains(Character.toString(lastChar))) {
                        return false;
                    } else if((currChar == '-' && Character.isDigit(lastChar))) {
                        char nextChar = expression.charAt(i + 1);
                        if(!(Character.isDigit(nextChar) || nextChar == '(')) {
                            return false;
                        }
                    } else if(currChar == '-' && (lastChar == '(')) {
                        char nextChar = expression.charAt(i + 1);
                        char nextNextChar = expression.charAt(i + 2);
                        if(!((nextChar == '(' && Character.isDigit(nextNextChar))
                                || (Character.isDigit(nextChar) && nextNextChar == ')'))) {
                            return false;
                        }
                    } else if(currChar == '(' && !(operators.contains(Character.toString(lastChar))
                            || lastChar == '(')) {
                        return false;
                    } else if(currChar == ')' && lastChar == '(') {
                        return false;
                    }
                } else if(!(currChar == '(' || Character.isDigit(currChar))) {
                    return false;
                }
            } catch (Exception e) {
                return false;
            }

            lastChar = currChar;

        }
        if(leftParenthesis != rightParenthesis) {
            return false;
        }
        return true;
    }

    public static int calculate(char operator, int second, int first) throws ArithmeticException {
        int result;
        switch (operator) {
            case '+':
                result = first + second;
                break;
            case '-':
                result = first - second;
                break;
            case '*':
                result = first * second;
                break;
            case '/':
                result = first / second;
                break;
            default:
                System.out.println("Invalid operator");
                result = 0;
        }
        return result;
    }

    public static String evaluate(String expression) {
        MyStack<Character> operatorsStack = new MyStack<>();
        MyStack<Integer> valuesStack = new MyStack<>();
        String OPERATORS = "-+*/";

        for(int i=0; i<expression.length(); i++) {
            char currChar = expression.charAt(i);
            if(Character.isDigit(currChar)) {
                valuesStack.push(currChar - '0');
            } else if(currChar == '(') {
                operatorsStack.push(currChar);
            } else if(currChar == ')') {
                char operator = operatorsStack.pop();
                while (operator != '(') {
                    try {
                        valuesStack.push(calculate(operator, valuesStack.pop(), valuesStack.pop()));
                    } catch (ArithmeticException e) {
                        return "Not valid";
                    }

                    operator = operatorsStack.pop();
                }
            } else if(OPERATORS.contains(Character.toString(currChar))) {
                while (!operatorsStack.isEmpty()) {
                    char lastOp = operatorsStack.peek();
                    if(OPERATORS.contains(Character.toString(lastOp)) && OPERATORS.indexOf(lastOp) >= OPERATORS.indexOf(currChar)) {
                        char operator = operatorsStack.pop();
                        try {
                            valuesStack.push(calculate(operator, valuesStack.pop(), valuesStack.pop()));
                        } catch (ArithmeticException e) {
                            return "Not valid";
                        }
                    } else {
                        break;
                    }
                }
                // If the operator is '-' and there is a '(' before it then it is a unary operator
                // So, just push a 0 to the valuesStack
                if(currChar == '-' && expression.charAt(i - 1) == '(') {
                    valuesStack.push(0);
                }
                operatorsStack.push(currChar);
            }
        }
        while(!operatorsStack.isEmpty()) {
            char operator = operatorsStack.pop();
            try {
                valuesStack.push(calculate(operator, valuesStack.pop(), valuesStack.pop()));
            } catch (ArithmeticException e) {
                return "Not valid";
            }
        }
        return "Valid expression, Computed value: " + valuesStack.pop();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);


        while (true) {
            String expression = scanner.nextLine();

            if(expression.equals("")) {
                continue;
            }

            // 1. Validity check:
            if(!isValid(expression)) {
                System.out.println("Not valid");
            } else {
                System.out.println(evaluate(expression));
            }

        }

    }
}
