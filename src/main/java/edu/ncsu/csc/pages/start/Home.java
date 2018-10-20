package edu.ncsu.csc.pages.start;

import edu.ncsu.csc.pages.AbstractPage;
import edu.ncsu.csc.pages.Page;

public class Home extends AbstractPage {

  public Home() {
    choices.add("Login");
    choices.add("Sign Up");
    choices.add("Exit");
  }

  @Override
  public void run() {
    System.out.println("# Home");
    displayChoices();

    switch (getChoiceFromInput()) {
      case 1:
        login();
        break;
      case 2:
        signUp();
        break;
      case 3:
        exit();
    }
  }

  private void login() {
    Page login = new Login();
    login.run();
  }

  private void signUp() {
    Page signUp = new SignUp();
    signUp.run();
  }

  private void exit() {
    System.exit(0);
  }
}
