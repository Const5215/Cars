package edu.ncsu.csc;

import edu.ncsu.csc.pages.Page;
import edu.ncsu.csc.pages.start.Home;
import edu.ncsu.csc.pages.start.Test;

public class App {

  public static void main(String[] args) {
    Page home = new Home();
    home.run();
//    Test test = new Test();
//    test.run();
  }
}
