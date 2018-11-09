package edu.ncsu.csc.pages;

import edu.ncsu.csc.entity.MatchType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AbstractPage implements Page {

  protected static final String URL = "jdbc:oracle:thin:@orca.csc.ncsu.edu:1521:orcl01";
  protected static final String USERNAME = "hwang75";
  protected static final String PASSWORD = "-swap255";
  protected Connection connection;
  protected PreparedStatement preparedStatement;
  protected ResultSet resultSet;
  protected Scanner scanner;
  protected List<String> choices;
  protected SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
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

  protected String getInfo(String info, MatchType matchType) {
    String result;
    do {
      System.out.print(info);
      result = scanner.nextLine();
      boolean matched = true;
      String targetRegex;
      Pattern pattern;
      Matcher matcher;
      switch (matchType) {
        case Email:
          targetRegex = "^[\\w.%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$";
          pattern = Pattern.compile(targetRegex);
          matcher = pattern.matcher(result);
          matched = matcher.matches();
          break;
        case Phone:
          targetRegex = "^\\d{3}-\\d{3}-\\d{4}$";
          pattern = Pattern.compile(targetRegex);
          matcher = pattern.matcher(result);
          matched = matcher.matches();
          break;
        case Date:
          targetRegex = "^[0-9][0-9][0-9][0-9]-[0-3][0-9]-[0-3][0-9]$";
          pattern = Pattern.compile(targetRegex);
          matcher = pattern.matcher(result);
          matched = matcher.matches();
          break;
        case Number:
          try {
            Long num = Long.parseLong(scanner.nextLine());
          } catch (NumberFormatException e) {
            matched = false;
          }
      }
      if (matched) {
        break;
      } else {
        switch (matchType) {
          case Email:
            System.out.println("Invalid email");
            break;
          case Phone:
            System.out.println("Invalid phone");
            break;
          case Date:
            System.out.println("Invalid date");
            break;
          case Number:
            System.out.println("Invalid number");
        }
      }
    } while (true);
    return result;
  }

}
