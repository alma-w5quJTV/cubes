package com.almasoft.cubes;

import java.io.PrintWriter;
import java.util.Deque;
import java.util.LinkedList;

public class Main {
    /**
    Solves the task for the following Sides:
    
      |  o  |o o o|  o  |
      | ooo |ooooo| oooo|
      |ooooo| ooo |oooo |
      | ooo |ooooo| oooo|
      |  o  |o o o|  o  |
          
      | o o | o o | o o |
      |oooo |ooooo| oooo|
      | oooo| ooo |oooo |
      |oooo |ooooo| oooo|
      |oo o |o o  |oo oo|
      
   * @param args
   */
  public static void main(String[] args) {
      Deque<Side> list = new LinkedList<Side>();
      
      list.add(new Side().encode(
              "  o  ",
              " ooo ",
              "ooooo",
              " ooo ",
              "  o  "));
      list.add(new Side().encode(
              "o o o",
              "ooooo",
              " ooo ",
              "ooooo",
              "o o o"));
      list.add(new Side().encode(
              "  o  ",
              " oooo",
              "oooo ",
              " oooo",
              "  o  "));
      
      list.add(new Side().encode(
              " o o ",
              "oooo ",
              " oooo",
              "oooo ",
              "oo o "));
      
      list.add(new Side().encode(
              " o o ",
              "ooooo",
              " ooo ",
              "ooooo",
              "o o  "));
      list.add(new Side().encode(
              " o o ",
              " oooo",
              "oooo ",
              " oooo",
              "oo oo"));
      
      Cube cube = new Cube();
      
      try(PrintWriter w = new PrintWriter(System.out)){
          new Game(w).calculate(list, cube);
      }
  }
}
