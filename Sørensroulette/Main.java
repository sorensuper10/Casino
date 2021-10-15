package com.company;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        Roulette roulette = new Roulette();
        Scanner in = new Scanner(System.in);
        int userNumber;

        System.out.println("You have " + roulette.getBalance() + " DKK");

        System.out.print("Enter your bet amount: ");
        int betAmount = scanner.nextInt();
        int money = roulette.getBalance() - betAmount;
        roulette.setBalance(money);

        System.out.print("Do you want to bet on a color, number or equal/odd (C/N/E)");
        String betOn = scanner.next();
        if (betOn.equalsIgnoreCase("c")) {
            System.out.println("What color do you want to bet on, Red og Black (R/B)?");
            String color = in.next();
            if (color.equalsIgnoreCase("r")) {
                roulette.spinTheBall();
                     if (roulette.checkRed())
                        System.out.println("True");
                     else if (!roulette.checkRed())
                        System.out.println("False");
                }

            else if (color.equalsIgnoreCase("b")) {
                roulette.spinTheBall();
                if (roulette.checkBlack())
                    System.out.println("True");
                else if (!roulette.checkBlack())
                    System.out.println("False");

            }
        } else if (betOn.equalsIgnoreCase("n")) {
            System.out.println("Choose a number between 0-36: ");
            userNumber = scanner.nextInt();
            roulette.checkMatch(userNumber);
            roulette.spinTheBall();
            if (roulette.checkMatch(userNumber)) {
                roulette.gevinst(userNumber);
            } else {
                System.out.println("You did not win");
                System.out.println("Your balance is now: " + roulette.getBalance());

            }

        } else if (betOn.equalsIgnoreCase("e")) {
            System.out.println("Do you want to bet on equal or odd (E/O)");
            String equalOrOdd = scanner.next();
            if (equalOrOdd.equalsIgnoreCase("e")) {
                roulette.spinTheBall();
                System.out.println(roulette.checkEqual());
            } else if (equalOrOdd.equalsIgnoreCase("o")) {
                roulette.spinTheBall();
                System.out.println(roulette.checkOdd());
                }
            }
        }
    }
