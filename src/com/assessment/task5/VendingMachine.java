package com.assessment.task5;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Created by A221824 on 2018/04/03.
 */
public class VendingMachine {

  private static int Menu(String[] product, Double[] prices) throws InterruptedException {
    Scanner keyboard = new Scanner(System.in);
    int choice = 0;
    boolean validInput = false;
    while (!validInput) {
      System.out.println("\nPlease enter your selection:");
      for (int k = 0; k < product.length; k++) {
        System.out.println("\n" + (k + 1) + ": " + product[k] + " \t\t R" + prices[k]);
      }
      System.out.println("\n7: Exit");
      try {
        choice = keyboard.nextInt();
        validInput = true;
      } catch (InputMismatchException e) {
        System.out.println("Please enter a valid choice");
        keyboard.nextLine();
      }
    }
    keyboard.close();
    return choice;
  }

  private static double getPrice(int menuChoice, Double[] price) {
    return price[menuChoice - 1];
  }

  private static double insertMoney(double Price) {
    Scanner keyboard = new Scanner(System.in);
    double money = 0;
    System.out
        .println("Your item costs: " + Price + " Please enter the amount of money or C to cancel:");
    try {
      money = keyboard.nextDouble();
      while (money < Price) {
        System.out.println("Please insert sufficient funds");
        System.out.println("You Currently Have: R" + money);

        money = money + keyboard.nextDouble();
      }

    } catch (InputMismatchException e) {
      System.out.println("Transaction Cancelled");
    }
    keyboard.close();
    return money - Price;
  }

  private static void giveChange(double change) {
    int fiftyCent = 0;
    int oneRand = 0;
    int twoRand = 0;
    int fiveRand = 0;
    while (change >= 5.00) {
      fiveRand = fiveRand + 1;
      change = change - 5.00;
    }
    while (change >= 2.00) {
      twoRand = twoRand + 1;
      change = change - 2.00;
    }
    while (change >= 1.00) {
      oneRand = oneRand + 1;
      change = change - 1.00;
    }
    while (change >= 0.50) {
      fiftyCent = fiftyCent + 1;
      change = change - 0.50;
    }

    System.out
        .printf("\nHere's your change:\n%d Fifty Cent, %d One Rand, %d Two Rand and %d Five Rand",
            fiftyCent, oneRand, twoRand, fiveRand);
  }

  private static void deliverProduct(String[] product, int choice) {
    System.out.println("Please collect item: " + product[choice - 1]);
  }

  public static void main(String[] args) throws InterruptedException {
    String[] products = {"Kit-Kat", "Astros", "Lays", "Coca-Cola", "Peanuts", "Red Bull"};
    Double[] prices = {5.5, 6.0, 5.0, 8.5, 5.5, 10.0};
    try {
      int Food = Menu(products, prices);
      if (Food != 7) {
        double Price = getPrice(Food, prices);
        double change = insertMoney(Price);
        deliverProduct(products, Food);
        if (change >= 0) {
          giveChange(change);
        } else {
          System.out.println("Enjoy Your Day!");
        }
      } else {
        System.out.println("Enjoy Your Day!");
      }
    } catch (InputMismatchException e) {
      System.out.println("System Error");
    }
  }
}
