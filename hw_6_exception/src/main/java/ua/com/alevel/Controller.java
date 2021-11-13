//package ua.com.alevel;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//
//public class Controller {
//
//    public void run() {
//        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//        System.out.println("select your option");
//        String position;
//        try {
//            runNavigation();
//            while ((position = reader.readLine()) != null) {
//                crud(position, reader);
//                position = reader.readLine();
//                if (position.equals("0")) {
//                    System.exit(0);
//                }
//                crud(position, reader);
//            }
//        } catch (IOException e) {
//            System.out.println("problem: = " + e.getMessage());
//        }
//    }
//
//    private void runNavigation() {
//        System.out.println();
//        System.out.println("if you want create user, please enter 1");
//        System.out.println("if you want update user, please enter 2");
//        System.out.println("if you want delete user, please enter 3");
//        System.out.println("if you want findById user, please enter 4");
//        System.out.println("if you want findAll user, please enter 5");
//        System.out.println("if you want exit, please enter 0");
//        System.out.println();
//    }
//
//    private void crud(String position, BufferedReader reader) {
//        switch (position) {
//            case "1":
//                create(reader);
//                break;
//            case "2":
//                update(reader);
//                break;
//            case "3":
//                delete(reader);
//                break;
//            case "4":
//                findById(reader);
//                break;
//            case "5":
//                findAll(reader);
//                break;
//            case "0":
//                System.exit(0);
//        }
//        runNavigation();
//    }
//
//    private void create(BufferedReader reader) {
//        int age = -1;
//        try {
//            System.out.println("Please, enter your name");
//            String name = reader.readLine();
//            System.out.println("Please, enter your email");
//            String email = reader.readLine();
//            System.out.println("Please, enter your age");
//            String ageString = reader.readLine();
//            try {
//                age = Integer.parseInt(ageString);
//            } catch (Exception e) {
//                System.out.println("invalid data format");
//                return;
//            }
//            User user = new User();
//            user.setAge(age);
//            user.setName(name);
//            user.setEmail(email);
//            userService.create(user);
//        } catch (IOException e) {
//            System.out.println("problem: = " + e.getMessage());
//        }
//    }
//}
