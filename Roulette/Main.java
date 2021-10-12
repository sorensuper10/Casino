package com.company;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        Roulette roulette = new Roulette();

        System.out.println("You have " + roulette.getBalance() + " DKK");

        System.out.print("Enter your bet amount: ");
        int betAmount = scanner.nextInt();
        int money = roulette.getBalance() - betAmount;
        roulette.setBalance(money);

        System.out.print("Place your bet choose a number between 0-36: ");
        int userNumber = scanner.nextInt();

        roulette.printBoard();

        roulette.spinTheBall();

        roulette.checkMatch(userNumber);
        System.out.println(roulette.checkMatch(userNumber));
        if (roulette.checkMatch(userNumber)) {
            roulette.gevinst(userNumber);
        } else {
            System.out.println("You did not win");
            System.out.println("Your balance is now: " + roulette.getBalance());
        }
    }
}
