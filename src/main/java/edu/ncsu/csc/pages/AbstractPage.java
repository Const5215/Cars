package edu.ncsu.csc.pages;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AbstractPage implements Page {

  protected static final String URL = "jdbc:oracle:thin:@orca.csc.ncsu.edu:1521:orcl01";
  protected static final String USERNAME = "";
  protected static final String PASSWORD = "";
  protected Connection connection;
  protected PreparedStatement preparedStatement;
  protected ResultSet resultSet;
  protected Scanner scanner;
  protected List<String> choices;

  protected AbstractPage() {
    scanner = new Scanner(System.in);
    choices = new ArrayList<>();
  }

  public void run() {
  }

  protected void displayChoices() {
    for (int i = 0; i < choices.size(); i++) {
      System.out.printf("%d.\t%s\n", i + 1, choices.get(i));
    }
  }

  protected Integer getChoiceFromInput() {
    Integer choice;

    do {
      System.out.print("Enter choice: ");
      String input = scanner.nextLine();

      try {
        choice = Integer.parseInt(input);
      } catch (NumberFormatException e) {
        System.out.println("Invalid choice");
        continue;
      }

      if (choice < 1 || choice > choices.size()) {
        System.out.println("Invalid choice");
        continue;
      }

      return choice;
    } while (true);
  }

  protected void closeSqlConnection() {
    if (connection != null) {
      try {
        connection.close();
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }

    if (preparedStatement != null) {
      try {
        preparedStatement.close();
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }

    if (resultSet != null) {
      try {
        resultSet.close();
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }
}
