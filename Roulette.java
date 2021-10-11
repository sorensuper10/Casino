package com.company;
import java.util.Random;

public class Roulette {
    private int[] rouletteNumbers;

    public Roulette() {
        this.rouletteNumbers = new int[]{0, 32, 15, 19, 4, 21, 2, 25, 17, 34, 6, 27, 13, 36, 11, 30, 8, 23, 10, 5, 24, 16, 33, 1, 20, 14, 31, 9, 22, 18, 29, 7, 28, 12, 35, 3, 26};
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
        System.out.println("The number is: " + rouletteNumbers[startRandom]);
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
}


