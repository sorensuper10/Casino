package com.company;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Users {
    private String user;
    private double balance;
    private double totalWinRoulette;

    public void loginOrCreateUser(String navn) {
        this.user = navn;
        this.balance = 500;
        this.totalWinRoulette = 0;

        try {
            File users = new File("./src/com/company/users/" + this.user + ".txt"); // Create individual users file
            if (users.createNewFile()) {
                System.out.println("Velkommen til: " + this.user + ", we have registered you as a user!");
                try {
                    FileWriter usersWrite = new FileWriter("./src/com/company/users/" + this.user + ".txt");
                    usersWrite.write("500.0"); // Write 500 "kr" til brugeres fil
                    usersWrite.close();
                    System.out.println("500 kr have been deposited to your account!");
                } catch (IOException e) {
                    System.err.println("Something went wrong..");
                    e.printStackTrace();
                }
            } else {
                System.out.println("Welcome back: " + this.user);
                Scanner userReader = new Scanner(users);
                while (userReader.hasNextLine()) {
                    this.balance = Double.parseDouble(userReader.nextLine());
                    System.out.println("Your current balance: " + this.balance);
                }
                userReader.close();
            }
        } catch (IOException e) {
            System.err.println("Something went wrong..");
            e.printStackTrace();
        }
    }

    public double betLost (double bet) {
        double newBalance = this.balance - bet;
        return this.balance = writeBalance(newBalance);
    }

    public double betWon (double bet, boolean blackjack) {
        if (!blackjack) {
            double newBalance = this.balance + bet;
            this.balance = writeBalance(newBalance);
        } else if (blackjack) {
            double newBalance = this.balance + (bet * 1.5);
            this.balance = writeBalance(newBalance);
        }
        return this.balance;
    }

    private double writeBalance(double newBalance) {
        try { // Write new balance to individual users file
            FileWriter usersWrite = new FileWriter("./src/com/company/users/" + this.user + ".txt");
            usersWrite.write(String.valueOf(newBalance));
            usersWrite.close();
        } catch (IOException e) {
            System.err.println("Something went wrong..");
            e.printStackTrace();
        }
        return newBalance;
    }

    public String getUser() {
        return this.user;
    }

    public double getBalance() {
        return this.balance;
    }

    public void indbetal(int indbetal) {
        if (indbetal <= 500 && indbetal > this.balance) {
            this.balance = writeBalance(indbetal);
        } else if (indbetal < this.balance){
            System.out.println("You already have money in your account!");
        } else if (indbetal > 500) {
            System.out.println("You can't deposit more than 500kr!");
        }
    }

    public double betWonRouletteDozen (double bet) {
        totalWinRoulette = totalWinRoulette + bet;
        return this.balance;
    }

    public double betWonRouletteColor (double bet) {
        totalWinRoulette = totalWinRoulette + bet;
        return this.balance;
    }

    public double betLostRouletteColor (double bet) {
        totalWinRoulette = totalWinRoulette - bet;
        return this.balance;
    }

    public double getTotalWinRoulette(Users users) {
        return totalWinRoulette;
    }

    public double betWonRouletteNumber(double bet) {
        totalWinRoulette = totalWinRoulette + (bet * 35);
        return totalWinRoulette;
    }

    public double betLostRouletteNumber(double bet) {
        totalWinRoulette = totalWinRoulette - bet;
        return totalWinRoulette;
    }

    public double betWonRouletteOddEven(double bet) {
        totalWinRoulette = totalWinRoulette + bet;
        return totalWinRoulette;
    }

    public double betLostRouletteOddEven(double bet) {
        totalWinRoulette = totalWinRoulette - bet;
        return totalWinRoulette;
    }

    public double updateBalanceRoulette() {
        this.balance = this.balance + this.totalWinRoulette;
        return this.balance;
    }
}
