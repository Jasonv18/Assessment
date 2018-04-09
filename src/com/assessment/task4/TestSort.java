package com.assessment.task4;

import java.io.File;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

/**
 * Created by A221824 on 2018/04/06.
 * Run this code to view top viewed product ID
 */
public class TestSort {
  public static void main(String[] args) throws IOException {

    String inputFile = GenerateRandomFile.generateInput(1000000);
    String outputFile = "myOutput.txt";
    Comparator<String> comparator = Comparator.naturalOrder();
    List<File> l = mergeSort.sortFiles(new File(inputFile), comparator);
    mergeSort.merge(l, new File(outputFile), comparator);

    Scanner scanner = new Scanner(new File(outputFile));
    int [] testArr = new int [1000000];
    int i = 0;
    while(scanner.hasNextInt()){
      testArr[i++] = scanner.nextInt();
    }

    FrequencyCount frequencyCount = new FrequencyCount();
    int maxNum = frequencyCount.findCounts(testArr,testArr.length,100000);
    System.out.println("The max viewed product ID is: "+ maxNum);
  }

}
