package com.assessment.task1;

import java.io.InputStream;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Created by A221824 on 2018/03/30.
 */
public class isDivisibleBy8 {

  private static boolean checkDivision(Integer value) {

    return value % 8 == 0;

  }

  private static boolean validateValue(Integer value) {
    return value > 0 && value <= 1000;
  }

  private static boolean validateTestCase(Integer testCase) {
    return testCase > 0 && testCase <= 100;
  }

  private static String run(InputStream in) {
    int value, testCases;
    Scanner scanner = new Scanner(in);
    StringBuilder stringbuildertest = new StringBuilder();
    testCases = Integer.parseInt(scanner.nextLine());
    if (validateTestCase(testCases)) {
      while (testCases-- > 0) {
        value = Integer.parseInt(scanner.nextLine());
        if (validateValue(value)) {
          if (checkDivision(value)) {
            stringbuildertest.append("1\n");
          } else {
            stringbuildertest.append("-1\n");
          }
        } else {
          stringbuildertest.append("Invalid: Please enter a number between 0 and 1000\n");
        }
      }
    } else {
      stringbuildertest.append("Invalid: Please enter a value between 1 and 100");
    }
    return stringbuildertest.toString();
  }

  public static void main(String[] args) throws InterruptedException{
    try {
      System.out.println(run(System.in));
    } catch (NumberFormatException e) {
      System.out.println("Please enter a valid number");
    }
  }
}
