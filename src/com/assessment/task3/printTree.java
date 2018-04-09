package com.assessment.task3;

import java.io.InputStream;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * Created by A221824 on 2018/03/30.
 */

public class printTree {

  private Node root;

  static class Node {

    int data;
    Node left;
    Node right;

    Node(int data) {
      this.data = data;
      left = null;
      right = null;
    }
  }

  private Node createBinaryTree(int[] nodearray, Node root,
      int index) {

    if (index < nodearray.length) {
      root = new Node(nodearray[index]);
      root.left = createBinaryTree(nodearray, root.left,
          2 * index + 1);
      root.right = createBinaryTree(nodearray, root.right,
          2 * index + 2);
    }
    return root;
  }

  private static String printBinaryTree(Node node) {

    StringBuilder stringbuilder = new StringBuilder();

    if (node == null) {
      return "Empty Array";
    }

    Queue<Node> nodeQueue = new LinkedList<>();
    nodeQueue.add(node);

    while (true) {
      int count = nodeQueue.size();
      if (count == 0) {
        break;
      }
      int [] testArray = new int[count];
      while (count > 0) {
        Node newNode = nodeQueue.peek();
        testArray[count-1] = newNode.data;
        nodeQueue.remove();
        if (newNode.left != null) {
          nodeQueue.add(newNode.left);
        }
        if (newNode.right != null) {
          nodeQueue.add(newNode.right);
        }
        count--;
      }

      Arrays.sort(testArray);

      for(int i =0; i < testArray.length;i++){
        stringbuilder.append(testArray[i]+" ");
      }
      stringbuilder.append("\n");
    }
    return stringbuilder.toString();
  }

  private static String run(InputStream in) {
    Scanner scanner = new Scanner(in);
    StringBuilder stringbuildertest = new StringBuilder();
    int testCases = Integer.parseInt(scanner.nextLine());
    while (testCases-- > 0) {
      int arrSize = Integer.parseInt(scanner.nextLine());
      int[] nodeArr = new int[arrSize];
      String input = scanner.nextLine();
      String[] tokens = input.split(" ");
      for (int i = 0; i < tokens.length; i++) {
        nodeArr[i] = Integer.parseInt(tokens[i]);
      }
      printTree tree = new printTree();
      tree.root = tree.createBinaryTree(nodeArr, tree.root, 0);
      stringbuildertest.append(printBinaryTree(tree.root));
    }
    scanner.close();
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
