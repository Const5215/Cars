package edu.ncsu.csc.pages;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AbstractPage implements Page {

  protected Scanner scanner;
  protected List<String> choices;
  protected DateFormat dateFormat;
  protected DateFormat timeFormat;

  protected AbstractPage() {
    scanner = new Scanner(System.in);
    choices = new ArrayList<String>();
    dateFormat = new SimpleDateFormat("MM/dd/yyyy");
    timeFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
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

  protected String getStringFromInput(String prompt) {
    System.out.print(prompt);
    return scanner.nextLine();
  }

  protected Date getDateFromInput(String prompt) {
    Date date;

    while (true) {
      System.out.print(prompt);

      try {
        date = dateFormat.parse(scanner.nextLine());
      } catch (ParseException e) {
        System.out.println("Invalid date");
        continue;
      }

      return date;
    }
  }

  protected String getPhoneFromInput(String prompt) {
    while (true) {
      String phone = getStringFromInput(prompt);
      Pattern pattern = Pattern.compile("^\\d{3}-\\d{3}-\\d{4}$");
      Matcher matcher = pattern.matcher(phone);

      if (matcher.matches()) {
        return phone;
      } else {
        System.out.println("Invalid phone number");
      }
    }
  }

  protected String getEmailFromInput(String prompt) {
    while (true) {
      String email = getStringFromInput(prompt).toLowerCase();
      Pattern pattern = Pattern.compile("^[\\w.%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$");
      Matcher matcher = pattern.matcher(email);

      if (matcher.matches()) {
        return email;
      } else {
        System.out.println("Invalid email address");
      }
    }
  }


}
