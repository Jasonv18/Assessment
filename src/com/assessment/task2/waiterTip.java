package com.assessment.task2;

import java.io.InputStream;
import java.util.Arrays;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Created by A221824 on 2018/04/03.
 */

public class waiterTip {

  private static String waiterTip(InputStream in) {
    Scanner scanner = new Scanner(in);
    int testCases = scanner.nextInt();
    int testCount = 0;
    StringBuilder stringbuilder = new StringBuilder();
    while (testCount < testCases) {
      Scanner scannerLoop = new Scanner(System.in);
      int firstInt = scannerLoop.nextInt();
      int secondInt = scannerLoop.nextInt();
      int thirdInt = scannerLoop.nextInt();
      int[][] array = new int[firstInt][3];

      for (int j = 0; j < firstInt; j++) {
        array[j][0] = scanner.nextInt();
      }

      for (int j = 0; j < firstInt; j++) {
        array[j][1] = scanner.nextInt();
      }

      for (int j = 0; j < firstInt; j++) {
        array[j][2] = Math.abs(array[j][0] - array[j][1]);
      }

      stringbuilder.append(waiterTipCalc(secondInt, thirdInt, array)).append("\n");
      testCount++;
      scannerLoop.next();
      //scanner.close();
    }
    return stringbuilder.toString();
  }

  private static String waiterTipCalc(int firstint, int secondint, int[][] array) {

    Arrays.sort(array, Comparator.comparingInt(o -> o[2]));

    long maximum = 0;
    int testNum = array.length - 1;
    while (firstint > 0 && secondint > 0 && testNum >= 0) {
      if (array[testNum][0] >= array[testNum][1]) {
        maximum += array[testNum][0];
        firstint--;
      } else {
        maximum += array[testNum][1];
        secondint--;
      }
      testNum--;
    }
    while (testNum >= 0) {
      if (firstint > 0) {
        maximum += array[testNum][0];
      } else {
        maximum += array[testNum][1];
      }
      testNum--;
    }
    return Long.toString(maximum);
  }

  public static void main(String[] args) throws InterruptedException {
    try {
      System.out.println(waiterTip(System.in));
    } catch (InputMismatchException e) {
      System.out.println("Please enter a valid number");
    }
  }
}