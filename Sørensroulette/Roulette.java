package com.company;
import java.util.Random;

public class Roulette {
    private final int[] rouletteNumbers;
    private int pickedNumber;
    private int balance;
    private final int[] rednumber;
    private final int[] blacknumber;
    private final int[] ligetal;
    private final int[] uligetal;

    public Roulette() {
        this.rouletteNumbers = new int[]{0, 32, 15, 19, 4, 21, 2, 25, 17, 34, 6, 27, 13, 36, 11, 30, 8, 23, 10, 5, 24, 16, 33, 1, 20, 14, 31, 9, 22, 18, 29, 7, 28, 12, 35, 3, 26};
        this.balance = 500;
        this.rednumber = new int []{1, 3, 5, 7, 9, 12, 14, 16, 18, 19, 21, 23, 25, 27, 30, 32, 34,36};
        this.blacknumber = new int[]{2, 4, 6, 8, 10, 11, 13, 15, 17, 20, 22, 24, 26, 28, 29, 31, 33, 35};
        this.ligetal = new int []{ 2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24, 26, 28, 30, 32, 34, 36};
        this.uligetal = new int []{ 1, 3, 5, 7, 9, 11, 13, 15, 17, 19, 21, 23, 25, 27, 29, 31, 33, 35};
    }

    public boolean checkRed(){
        for (int j : this.rednumber) {
            if (j == this.pickedNumber) {
                return true;
            }
        }
        return false;
    }

    public boolean checkBlack(){
        for (int j : this.blacknumber) {
            if (j == this.pickedNumber) {
                return true;
            }
        }
        return false;
    }

    public boolean checkMatch(int userNumber) {
        return userNumber == this.pickedNumber;
    }

    public boolean checkEqual (){
        for (int j : this.ligetal) {
            if (j == this.pickedNumber) {
                return true;
            }
        }
        return false;
    }

    public boolean checkOdd (){
        for (int j : this.uligetal) {
            if (j == this.pickedNumber) {
                return true;
            }
        }
            return false;

    }

    public void spinTheBall() throws InterruptedException {
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
        this.pickedNumber = rouletteNumbers[startRandom];
        System.out.println("The number is: " + rouletteNumbers[startRandom]);
    }


    public void gevinst(int bet) {
        this.balance = this.balance + (bet * 36);
        System.out.println("You won your new balance is: " + this.balance);
    }


    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }
}
