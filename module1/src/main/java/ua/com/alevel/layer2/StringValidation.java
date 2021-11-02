package ua.com.alevel.layer2;

import java.util.*;

public class StringValidation {

    private static boolean isValid(String input) {
        Stack stack = new Stack();
        String[] symbols = input.split("");
        for (String symb : symbols
        ) {
            if ((symb.equals("(")) || (symb.equals(")")) || (symb.equals("{"))
                    || (symb.equals("}")) || (symb.equals("[")) || (symb.equals("]"))) {
                if ((symb.equals("(")) || (symb.equals("{")) || (symb.equals("["))) {
                    stack.push(symb);
                } else {
                    switch (symb) {
                        case ")":
                            symb = "(";
                            break;
                        case "}":
                            symb = "{";
                            break;
                        case "]":
                            symb = "[";
                            break;
                    }
                    if (stack.empty()) {
                        return false;
                    }
                    if (stack.lastElement().equals(symb)) {
                        stack.pop();
                    } else {
                        return false;
                    }
                }
            }
        }
        return stack.empty();
    }

    public static void run(Scanner scanner) {
        System.out.println("Enter string to validate, if you want to stop press S");
        while (true) {
            String line = scanner.nextLine();
            if (line.equals("S")) {
                break;
            }
            if (isValid(line) == true) {
                System.out.println("Input string is valid");
            } else System.out.println("Input string is invalid)");
        }
    }
}
