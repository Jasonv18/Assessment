package com.assessment.task4;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by A221824 on 2018/04/05.
 */
class BinaryFileBuffer {

  private static int BUFFERSIZE = 2048;
  BufferedReader fileBufferedReader;
  File originalFile;
  private String cache;
  private boolean empty;

  BinaryFileBuffer(File file) throws IOException {
    originalFile = file;
    fileBufferedReader = new BufferedReader(new FileReader(file), BUFFERSIZE);
    reload();
  }

  boolean empty() {
    return empty;
  }

  private void reload() throws IOException {
    try {
      if ((this.cache = fileBufferedReader.readLine()) == null) {
        empty = true;
        cache = null;
      } else {
        empty = false;
      }
    } catch (EOFException oef) {
      empty = true;
      cache = null;
    }
  }

  void close() throws IOException {
    fileBufferedReader.close();
  }


  String peek() {
    if (empty()) {
      return null;
    }
    return cache;
  }

  String pop() throws IOException {
    String answer = peek();
    reload();
    return answer;
  }
}
