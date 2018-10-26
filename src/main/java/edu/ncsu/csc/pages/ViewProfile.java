package edu.ncsu.csc.pages;

import edu.ncsu.csc.entity.User;

public class ViewProfile extends AbstractPage {
  private User user;

  ViewProfile(User user) {
    this.user = user;
    choices.add("Go Back");
  }

  @Override
  public void run() {
    System.out.println("#ViewProfile");
    System.out.println("user ID: " + user.getId());
    System.out.println("Password: " + user.getPassword());
    System.out.println("Name: " + user.getName());
    System.out.println("Email: " + user.getEmail());
    System.out.println("Phone: " + user.getPhone());
    System.out.println("Address: " + user.getAddress());
    do {
      displayChoices();
    } while (getChoiceFromInput() != 1);
    Page profile = new Profile(user);
    profile.run();
  }

}

