package com.company;

import java.util.ArrayList;
import java.util.Scanner;

public class Blackjack {
        public void runBlackjack(Scanner scanner, Users users) {
        Player player = new Player();
        Dealer dealer = new Dealer();

        boolean runGame = true;

        while (runGame) {
            boolean blackJack = false;

            Card.reset();
            player.reset();
            dealer.reset();

            ArrayList<ArrayList> finalDeck = Card.BlackjackDeck();
            boolean playerStay = false;
            boolean dealerStay = false;
            boolean dealCards = true;
            boolean betValid = false;
            double bet = 0;

            System.out.println("\n*****************************");
            System.out.println("*---------------------------*");
            System.out.println("*| Velkommen to BlackJack! |*");
            System.out.println("*---------------------------*");
            System.out.println("*****************************");
            System.out.println("\n-------------------------------------------");
            while (!betValid) {
                System.out.print("How much would you like to bet?: ");
                bet = scanner.nextDouble();
                if (bet > users.getBalance()) {
                    System.out.println("You can't bet more than what's on your account!");
                } else if (bet == 0) {
                    System.out.println("You have to bet something?!");
                } else {
                    betValid = true;
                }
            }
            System.out.println("-------------------------------------------");



            // Del 2 kort til både player og dealer
            for (int i = 0; i < 4; i++) {
                if (i % 2 == 0) {
                    Card card = Card.drawRandomCard(finalDeck);
                    player.setCardsOnHand(card);
                } else {
                    Card card = Card.drawRandomCard(finalDeck);
                    dealer.setCardsOnHand(card);
                }
            }

            // Vis kort og score
            System.out.println("Your cards: ");
            for (int i = 0; i < player.getCardsOnHand().size(); i++) {
                ASCII.printCard(player.getCardsOnHand().get(i));
            }
            System.out.println("You have: " + player.checkScore());
            System.out.println("------------------------------");
            System.out.println("Dealers card: ");
            printAscii(dealer);

            // Tjek om der er blackjack efter de første 4 kort er delt.
            if (player.checkScore() == 21) {
                blackJack = true;
            } else if (dealer.checkScore() == 21) {
                blackJack = true;
            }

            // Spiller trækker kort først til stay, så trækker dealer til stay, bust eller score over 16
            while (dealCards) {
                if (!playerStay) {
                    // Draw or stay?
                    System.out.print("Draw or stay? (D/S): ");
                    String playerInput = scanner.next();
                    if (playerInput.equalsIgnoreCase("s")) { // Stay * set playerStay = true
                        playerStay = true;
                    } else if (playerInput.equalsIgnoreCase("d")) { // Draw another card
                        System.out.println("------------------------------");
                        System.out.println("Your cards: ");
                        Card card = Card.drawRandomCard(finalDeck); // Draw random card from deck
                        player.setCardsOnHand(card); // Set card in players hand
                        for (int i = 0; i < player.getCardsOnHand().size(); i++) { // Print card ASCII format
                            ASCII.printCard(player.getCardsOnHand().get(i));
                        }
                        System.out.println("You have: " + player.checkScore()); // Players score
                        System.out.println("------------------------------");
                        if (player.checkScore() > 21) {
                            dealCards = false;
                            player.setBust(true);
                        }
                    } else {
                        System.err.println("Wrong input try again!");
                    }
                }
                if (!dealerStay && !player.getBust() && !dealer.getBust() && dealer.checkScore() <= 16 && dealCards && playerStay) { // Draw card if under 16
                    Card card = Card.drawRandomCard(finalDeck);
                    dealer.setCardsOnHand(card);
                    printAsciiVisible(dealer);
                } else if (dealer.checkScore() >= 16 && dealer.checkScore() <= 21) { // Dealer score between 16-21 = stay
                    dealerStay = true;
                } else if (dealer.checkScore() > 21) { // Dealer score < 21 Skip draw card loop and set busted status to true.
                    dealCards = false;
                    dealer.setBust(true);
                }

                if (player.getBust()) {
                    System.out.println("Busted and lost!");
                    users.betLost(bet);
                    dealCards = false;
                    System.out.println("Your new balance is: " + users.getBalance() + "\n\n");
                }
                if (dealer.getBust()) {
                    System.out.println("Dealer busted, with: " + dealer.getScore() + ", you won!");
                    users.betWon(bet, blackJack);
                    dealCards = false;
                    System.out.println("Your new balance is: " + users.getBalance() + "\n\n");
                }

                if (dealerStay && playerStay) { // If both player and dealer stay exit draw card loop
                    dealCards = false;
                }
            }

            if (!player.getBust() && !dealer.getBust()) { // Run if, if player and dealer not busted
                if (player.checkScore() > dealer.checkScore()) {
                    if (dealer.getCardsOnHand().size() <= 2) {
                        System.out.println("↓-↓-↓-↓-↓-↓-↓ Dealers cards ↓-↓-↓-↓-↓-↓-↓");
                        printAsciiVisible(dealer);
                    }
                    System.out.println("You won!");
                    System.out.print("You have: " + player.checkScore() + " and dealer has: " + dealer.getScore() + "\n");
                    users.betWon(bet, blackJack);
                    System.out.println("Your new balance is: " + users.getBalance() + "\n\n");
                } else if (player.checkScore() < dealer.checkScore()) {
                    if (dealer.getCardsOnHand().size() <= 2) {
                        System.out.println("↓-↓-↓-↓-↓-↓-↓ Dealers cards ↓-↓-↓-↓-↓-↓-↓");
                        printAsciiVisible(dealer);
                    }
                    System.out.println("You lost!");
                    System.out.print("you have: " + player.checkScore() + " and dealer has: " + dealer.getScore() + "\n");
                    users.betLost(bet);
                    System.out.println("Your new balance is: " + users.getBalance() + "\n\n");
                } else if (player.checkScore() == dealer.checkScore()) {
                    if (dealer.getCardsOnHand().size() <= 2) {
                        System.out.println("↓-↓-↓-↓-↓-↓-↓ Dealers cards ↓-↓-↓-↓-↓-↓-↓");
                        printAsciiVisible(dealer);
                    }
                    System.out.println("Its a draw!\n\n");
                }
            }
            System.out.print("Do you want to try again? (Y/N): ");
            String goAgain = scanner.next();
            if (goAgain.equalsIgnoreCase("n")) {
                runGame = false;
            }
        }
    }

    private static void printAsciiVisible(Dealer dealer) {
        for (int i = 0; i < dealer.getCardsOnHand().size(); i++) { // Print card ASCII format
            ASCII.printCard(dealer.getCardsOnHand().get(i));
        }
    }

    private static void printAscii(Dealer dealer) {
        for (int i = 0; i < dealer.getCardsOnHand().size(); i++) { // Print card ASCII format
            if (i == 0) {
                ASCII.printCard(dealer.getCardsOnHand().get(i)); // Only show first card
            } else {
                ASCIIBlank.printCard(dealer.getCardsOnHand().get(i)); // Hide all other cards than the first.
            }
        }
        //System.out.println("Dealer har: " + dealer.checkScore()); // Players score
        //System.out.println("------------------------------");
    }
}

