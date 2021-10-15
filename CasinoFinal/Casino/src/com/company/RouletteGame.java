package com.company;

import java.util.HashMap;
import java.util.Scanner;

public class RouletteGame {

    public void rouletteGame(Scanner scanner, Users users) throws InterruptedException {
        Roulette spin = new Roulette();
        boolean playRoulette = true;
        double userTotalBetSize;
        HashMap<String, Double> playerColorAndBet = new HashMap<String, Double>();
        double playerBetOdd = 0;
        double playerBetEven = 0;
        double playerBetFirstDozen;
        double playerBetSecondDozen;
        double playerBetThirdDozen;
        boolean getOut = false;

        System.out.println("Welcome to Roulette!");
        while (!getOut) {
            playRoulette = true;
            while (playRoulette) {
                System.out.println("Current balance: " + users.getBalance());
                System.out.println("What bet are you looking to make?");
                System.out.println("1. Bet on number");
                System.out.println("2. Bet on color");
                System.out.println("3. Bet on odd/even");
                System.out.println("4. Bet on dozen");
                System.out.println("5. SPIN THE BALL!");
                System.out.println("6. Exit");
                System.out.println("Write number for desired action.");
                int action = scanner.nextInt();
                switch (action) {
                    case 1 -> {
                        System.out.println("How many numbers would you like to bet on?: ");
                        int numberOfBets = scanner.nextInt();
                        for (int i = 1; i <= numberOfBets; i++) {
                            System.out.println("Enter your number: ");
                            int tempNum = scanner.nextInt();
                            System.out.println("How much do you want to bet on this number?: ");
                            double tempBet = scanner.nextDouble();
                            spin.playerNumberAndBet.put(tempNum, tempBet);
                        }
                    }
                    case 2 -> {
                        System.out.println("What color would you like to bet on (BLACK, RED, GREEN): ");
                        String color = scanner.next();
                        spin.setColorChoice(color);
                        System.out.println("How much would you like to bet on " + spin.getColorChoice() + "?: ");
                        double playerColorBet = scanner.nextDouble();
                        spin.playerColorAndBet.put(spin.getColorChoice(), playerColorBet);
                    }
                    case 3 -> {
                        System.out.println("Wanna bet on odd or even?: ");
                        if (scanner.next().equalsIgnoreCase("odd")) {
                            System.out.println("How much do you want to bet on odd?: ");
                            playerBetOdd = scanner.nextDouble();
                            spin.playerOddEven.put("odd", playerBetOdd);
                        } else {
                            System.out.println("How much do you want to bet on even?: ");
                            playerBetEven = scanner.nextDouble();
                            spin.playerOddEven.put("even", playerBetEven);
                        }
                    }
                    case 4 -> {
                        System.out.println("Choose which dozen you would like to place bet on: ");
                        System.out.println("1. 1st 12(1-12)");
                        System.out.println("2. 2nd 12(13-24)");
                        System.out.println("3. 3rd 12(25-36)");
                        if ((scanner.nextLine().equalsIgnoreCase("1"))) {
                            System.out.println("How much would you like to bet on this dozen?: ");
                            spin.setFirstDozen(scanner.nextDouble());
                        } else if (scanner.nextLine().equalsIgnoreCase("2")) {
                            System.out.println("How much would you like to bet on this dozen?: ");
                            spin.setSecondDozen(scanner.nextDouble());
                        } else if (scanner.nextLine().equalsIgnoreCase("3")) {
                            System.out.println("How much would you like to bet on this dozen?: ");
                            spin.setThirdDozen(scanner.nextDouble());
                        } else {
                            System.out.println("That is not an option.");
                        }
                    }
                    case 5 -> {
                        spin.spinTheBall();
                        playRoulette = false;
                        spin.ballNumberValues();
                    }
                    case 6 -> {
                        playRoulette = false;
                        getOut = true;
                    }
                }
            }
            spin.didOddEvenHit(users, spin, playerBetEven, playerBetOdd);
            spin.didNumberHit(users, spin);
            spin.didColorHit(users, spin);
            users.updateBalanceRoulette();
        }
    }
}
