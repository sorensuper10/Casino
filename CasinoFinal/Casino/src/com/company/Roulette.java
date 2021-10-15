package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;

public class Roulette {
    private int[] rouletteNumbers;
    private int[] isNumberBlack = {2, 4, 6, 8, 10, 11, 13, 15, 17, 20, 22, 24, 26, 28, 29, 31, 33, 35};
    private int ballNumberOnSpin;
    private String numberColor;
    private String numberOddEven;
    private String whatDozen;
    private double firstDozen;
    HashMap<String, Double> playerColorAndBet = new HashMap<String, Double>();
    HashMap<Integer, Double> playerNumberAndBet = new HashMap<Integer,Double>();
    HashMap<String, Double> playerOddEven = new HashMap<String, Double>();



    public String getColorChoice() {
        return colorChoice;
    }

    public void setColorChoice(String colorChoice) {
        this.colorChoice = colorChoice;
    }

    private String colorChoice;


    public double getFirstDozen() {
        return firstDozen;
    }

    public void setFirstDozen(double firstDozen) {
        this.firstDozen = firstDozen;
    }

    public double getSecondDozen() {
        return secondDozen;
    }

    public void setSecondDozen(double secondDozen) {
        this.secondDozen = secondDozen;
    }

    public double getThirdDozen() {
        return thirdDozen;
    }

    public void setThirdDozen(double thirdDozen) {
        this.thirdDozen = thirdDozen;
    }

    private double secondDozen;
    private double thirdDozen;

    public Roulette() {
        this.rouletteNumbers = new int[]{0, 32, 15, 19, 4, 21, 2, 25, 17, 34, 6, 27, 13, 36, 11, 30, 8, 23, 10, 5, 24, 16, 33, 1, 20, 14, 31, 9, 22, 18, 29, 7, 28, 12, 35, 3, 26};
    }

    public double spinTheBall() throws InterruptedException {
        Random random = new Random();
        int startRandom = random.nextInt(36);
        int spinTime = random.nextInt(3) + 3;
        System.out.println(spinTime);
        System.out.println(startRandom);
        int sleeptime = 1;
        while (sleeptime < 200) {
            Thread.sleep(sleeptime);
            sleeptime = sleeptime + spinTime;
            if (startRandom <= 36) {
                System.out.println(rouletteNumbers[startRandom]);
                startRandom = startRandom + 1;
            } else {
                startRandom = 0;
            }
        }
        if (startRandom == 37) startRandom = 36;
        System.out.println("Your number is: " + rouletteNumbers[startRandom]);
        ballNumberOnSpin = rouletteNumbers[startRandom];
        return ballNumberOnSpin;
    }

    public void printBoard() {
        for (int i = 0; i < rouletteNumbers.length; i++) {
            if (i % 3 == 0) {
                System.out.print(rouletteNumbers[i]);
                System.out.println("\n");
            } else {
                System.out.print(rouletteNumbers[i]);
            }
        }
    }

    public void ballNumberValues() {
        String ballColor;
        if (ballNumberOnSpin == 0) {
            numberColor = "Green";
            System.out.println("Green");
        } else if (Arrays.asList(isNumberBlack).contains(ballNumberOnSpin)) {
            numberColor = "Red";
            System.out.println("Red");
        } else {
            numberColor = "Black";
            System.out.println("Black");
        }
        if (ballNumberOnSpin == 0) {
            numberOddEven = null;
        } else if (ballNumberOnSpin % 2 == 0) {
            System.out.println("Even");
            numberOddEven = "Even";
        } else {
            System.out.println("Odd");
            numberOddEven = "Odd";
        }
        if (ballNumberOnSpin == 0) {
            whatDozen = "No Dozen";
        } else if (ballNumberOnSpin > 0 && ballNumberOnSpin < 13) {
            whatDozen = "1st 12";
        } else if (ballNumberOnSpin >= 13 && ballNumberOnSpin < 25) {
            whatDozen = "2nd 12";
        } else {
            whatDozen = "3rd 12";
        }

    }
    void didNumberHit(Users users, Roulette spin){
        for (int number : playerNumberAndBet.keySet()){
            if (number == ballNumberOnSpin) {
                users.betWonRouletteNumber(playerNumberAndBet.get(number));
            } else {
                users.betLostRouletteNumber(playerNumberAndBet.get(number));
            }
        }
    }
    void didOddEvenHit(Users users, Roulette spin, double playerBetEven, double playerBetOdd){
        int sizeForFor = playerOddEven.size();
        for (int i = 0; i < sizeForFor; i++) {
            if (ballNumberOnSpin != 0) {
                if (playerOddEven.containsKey("even") && ballNumberOnSpin % 2 == 0) {
                    users.betWonRouletteOddEven(playerOddEven.get("even"));
                    playerOddEven.remove("even");
                } else if (playerOddEven.containsKey("odd") && ballNumberOnSpin % 2 != 0) {
                    users.betWonRouletteOddEven(playerOddEven.get("odd"));
                    playerOddEven.remove("odd");
                } else if (playerOddEven.containsKey("even") && ballNumberOnSpin % 2 != 0) {
                    users.betLostRouletteOddEven(playerOddEven.get("even"));
                    playerOddEven.remove("even");
                } else if (playerOddEven.containsKey("odd") && ballNumberOnSpin % 2 == 0) {
                    users.betLostRouletteOddEven(playerOddEven.get("odd"));
                    playerOddEven.remove("odd");
                }
            }
        }
    }

    void didColorHit(Users users, Roulette spin) {
        //numberColor
        for (String color : playerColorAndBet.keySet()) {
            if (color.equalsIgnoreCase(numberColor)) {
                users.betWonRouletteColor(playerColorAndBet.get(color));
            } else {
                users.betLostRouletteColor(playerColorAndBet.get(color));
            }
        }
    }

    void didDozenHit(Users users) {
        if (whatDozen.equalsIgnoreCase("1st 12")) {
            if (firstDozen != 0) {
                users.betWonRouletteDozen(firstDozen);
            }
        } else if (whatDozen.equalsIgnoreCase("2nd 12")) {
            if (secondDozen != 0) {
                users.betWonRouletteDozen(secondDozen);
            }
        } else if (whatDozen.equalsIgnoreCase("3rd 12")) {
            if (thirdDozen != 0) {
                users.betWonRouletteDozen(thirdDozen);
            }
        }
    }
}
