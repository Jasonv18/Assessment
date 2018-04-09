package com.assessment.task4;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

/**
 * Created by A221824 on 2018/04/05.
 */
public class GenerateRandomFile {

  static String generateInput(int n)
  {
    String fileName = "external-sort.txt";
    Random random = new Random();

    try
    {
      FileWriter filewriter = new FileWriter(fileName);
      PrintWriter printwriter = new PrintWriter(filewriter);

      for (int i = 0; i < n; i++)
        printwriter.println(random.nextInt(1000000));

      printwriter.close();
    }

    catch (IOException e)
    {
      e.printStackTrace();
    }

    return fileName;
  }

}
