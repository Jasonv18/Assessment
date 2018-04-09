package com.assessment.task4;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Created by A221824 on 2018/04/06.
 * If memory was an issue for this problem, we could use an
 * external merge sort. Assuming we had the frequency distribution of the views of the product ids
 * saved to a file, if not we can simply write the array to a file and use external merge to sort
 * through the array by breaking the file up into smaller files, sorting them and then merging them
 * back together. If we can load it in memory without overflow we can use the Array.sort(int[])
 * method. However for the example below I am using the external merge sort. Once we have the sorted
 * file, we run a frequency count on the array. the top counted ID will be your most viewed.
 */
class mergeSort {

  private static long getBestSizeforBlocks(File filetobesorted) {
    long fileSize = filetobesorted.length();
    final int maxTmpFiles = 1024;
    long sizeOfBlock = fileSize / maxTmpFiles;

    long freeMemory = Runtime.getRuntime().freeMemory();
    if (sizeOfBlock < freeMemory / 2) {
      sizeOfBlock = freeMemory / 2;
    } else {
      if (sizeOfBlock >= freeMemory) {
        System.err.println("Not enough free memory");
      }
    }
    return sizeOfBlock;
  }

  static List<File> sortFiles(File input, Comparator<String> compare) throws IOException {
    List<File> listOfFiles = new ArrayList<>();
    long blockSize = getBestSizeforBlocks(input);
    BufferedReader bufferedReader = new BufferedReader(new FileReader(input));
    try {
      List<String> temporaryList = new ArrayList<>();
      String var = "";
      try {
        while (var != null) {
          long thisBlock = 0;
          while ((thisBlock < blockSize)
              && ((var = bufferedReader.readLine()) != null)) {
            temporaryList.add(var);
            thisBlock += var
                .length();
          }
          listOfFiles.add(sortFilesandSave(temporaryList, compare));
          temporaryList.clear();
        }
      } catch (EOFException oef) {
        if (temporaryList.size() > 0) {
          listOfFiles.add(sortFilesandSave(temporaryList, compare));
          temporaryList.clear();
        }
      }
    } finally {
      bufferedReader.close();
    }
    return listOfFiles;
  }


  public static File sortFilesandSave(List<String> tempList, Comparator<String> compare)
      throws IOException {
    Collections.sort(tempList, compare);  //
    File newTempFile = File.createTempFile("sortInBatch", "flatfile");
    newTempFile.deleteOnExit();
    BufferedWriter fileBufferedWriter = new BufferedWriter(new FileWriter(newTempFile));
    try {
      for (String line : tempList) {
        fileBufferedWriter.write(line);
        fileBufferedWriter.newLine();
      }
    } finally {
      fileBufferedWriter.close();
    }
    return newTempFile;
  }


  static int merge(List<File> files, File outputfile,
      final Comparator<String> comp) throws IOException {
    PriorityQueue<BinaryFileBuffer> priorityQueue = new PriorityQueue<>(11,
        (i, j) -> comp.compare(i.peek(), j.peek())
    );
    for (File f : files) {
      BinaryFileBuffer binaryFileBuffer = new BinaryFileBuffer(f);
      priorityQueue.add(binaryFileBuffer);
    }
    BufferedWriter fileBufferedWriter = new BufferedWriter(new FileWriter(outputfile));
    int count = 0;
    try {
      while (priorityQueue.size() > 0) {
        BinaryFileBuffer binaryFileBuffer = priorityQueue.poll();
        String r = binaryFileBuffer.pop();
        fileBufferedWriter.write(r);
        fileBufferedWriter.newLine();
        ++count;
        if (binaryFileBuffer.empty()) {
          binaryFileBuffer.fileBufferedReader.close();
          binaryFileBuffer.originalFile.delete();
        } else {
          priorityQueue.add(binaryFileBuffer);
        }
      }
    } finally {
      fileBufferedWriter.close();
      for (BinaryFileBuffer binaryFileBuffer : priorityQueue) {
        binaryFileBuffer.close();
      }
    }
    return count;
  }

}





