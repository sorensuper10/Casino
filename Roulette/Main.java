package com.company;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws InterruptedException {


        int money = 500;
        System.out.println("You have " + money + " DKK");

        System.out.print("Enter your bet amount: ");
        Scanner betamount = new Scanner(System.in);
        betamount.nextDouble();


        System.out.print("Place your bet on a number between 0-36: ");
        Scanner placebet = new Scanner(System.in);
        placebet.nextDouble();



        Roulette roulette = new Roulette();

        roulette.printBoard();

        roulette.spinTheBall();




    }
}


