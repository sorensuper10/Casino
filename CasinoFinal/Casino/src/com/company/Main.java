package com.company;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        Player player = new Player();
        Dealer dealer = new Dealer();
        Users users = new Users();
        WelcomeToCasinoAscii welcome = new WelcomeToCasinoAscii();


        Login login = new Login();
        Blackjack blackjack = new Blackjack();
        RouletteGame roulette = new RouletteGame();

        boolean runCasino = true;

        login.logIn(scanner, users);
        welcome.welcomAscii();
        while (runCasino) {
            System.out.println("Your current balance: " + users.getBalance());
            System.out.println("What game would you like to play?");
            System.out.print("BlackJack (1) or Roulette (2) or Exit (3): ");
            int game = scanner.nextInt();
            switch (game) {
                case 1 -> {
                    blackjack.runBlackjack(scanner, users);
                }
                case 2 -> {
                    roulette.rouletteGame(scanner, users);
                }
                case 3 -> {
                    runCasino = false;
                }
                default -> {
                    System.err.println("Wrong input! Try again.");
                }
            }
        }
    }
}
