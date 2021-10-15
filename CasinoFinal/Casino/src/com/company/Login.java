package com.company;

import java.util.Scanner;

public class Login {
    boolean manageAccount = true;

    public void logIn(Scanner scanner, Users users) {
        System.out.println("----------");
        System.out.println("| Login: |");
        System.out.println("-------------------------------------------");
        System.out.print("| Username: ");
        String navn = scanner.next();
        System.out.println("-------------------------------------------\n");
        System.out.println("-------------------------------------------");
        users.loginOrCreateUser(navn);
        System.out.println("-------------------------------------------\n");
        System.out.println("-------------------------------------------");
        manageAccount(scanner, users, manageAccount);
    }

    private static void manageAccount(Scanner scanner, Users users, boolean manageAccount) {
        while (manageAccount) {
            System.out.print("Indebetal eller vælg spil? (I/S): ");
            String depositOrPlay = scanner.next();
            if (depositOrPlay.equalsIgnoreCase("i")) {
                System.out.println("-------------------------------------------");
                System.out.println("Your current balance: " + users.getBalance());
                System.out.print("What how much would you like to deposit into your account? (Max 500kr) ");
                int indbetal = scanner.nextInt();
                users.indbetal(indbetal);
                System.out.println("\nYour new balance is:  " + users.getBalance());
                System.out.println("-------------------------------------------");
            } else if (depositOrPlay.equalsIgnoreCase("s")) {
                manageAccount = false;
                System.out.println("-------------------------------------------");
            } else {
                System.out.println("Wrong input, try again!");
            }
        }
    }
}
