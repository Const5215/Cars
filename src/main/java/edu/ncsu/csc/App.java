package edu.ncsu.csc;

import edu.ncsu.csc.pages.Page;
import edu.ncsu.csc.pages.start.Home;

public class App {

  public static void main(String[] args) {
    Page home = new Home();
    home.run();
  }
}
