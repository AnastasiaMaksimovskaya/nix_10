package ua.com.alevel.layer2;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class StringValidation {

    public static boolean isValid(String input) {

        Stack<String> stackOfBrackets = new Stack<String>();
        String arrayOfBrackets[] = input.split("");
        if (arrayOfBrackets.length==0){
            return true;
        }
        if (arrayOfBrackets.length%2==1){
            return false;
        }

        for (int i = 0; i < arrayOfBrackets.length; i++) {
            if (arrayOfBrackets[i].equals("{") || arrayOfBrackets[i].equals("[") || arrayOfBrackets[i].equals("(")) {
                stackOfBrackets.push(arrayOfBrackets[i]);
            }
            else if (arrayOfBrackets[i].equals(")")) {
                if (stackOfBrackets.peek().equals("(")){
                    stackOfBrackets.pop();
                }
                else return false;
            }
            else if (arrayOfBrackets[i].equals("}")) {
                if (stackOfBrackets.peek().equals("{")){
                    stackOfBrackets.pop();
                }
                else return false;
            }
            else if (arrayOfBrackets[i].equals("]")) {
                if (stackOfBrackets.peek().equals("[")){
                    stackOfBrackets.pop();
                }
                else return false;
            }
        }
        return true;
    }

    public static void run(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter string to validate, if you want to stop press S");
        while (true){
            String line = scanner.nextLine();
            if (line.equals("S")){
                break;
            }
            if (isValid(line)==true){
                System.out.println("Input string is valid");
            }
            else System.out.println("Input string is invalid)");
        }
        scanner.close();
    }
}
