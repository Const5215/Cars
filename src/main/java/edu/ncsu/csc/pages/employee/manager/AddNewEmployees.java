package edu.ncsu.csc.pages.employee.manager;

import edu.ncsu.csc.entity.User;
import edu.ncsu.csc.pages.AbstractPage;

public class AddNewEmployees extends AbstractPage {
  private User manager;

  public AddNewEmployees(User manager) {
    this.manager = manager;
    choices.add("Add");
    choices.add("Go Back");
  }

  @Override
  public void run() {
    System.out.println("#addNewEmployees");
    System.out.print("Enter Name:");
    String name = scanner.nextLine();
    System.out.print("Enter Address:");
    String address = scanner.nextLine();

  }
}
