package edu.ncsu.csc.pages.start;

import edu.ncsu.csc.entity.User;
import edu.ncsu.csc.pages.AbstractPage;
import edu.ncsu.csc.pages.Page;
import edu.ncsu.csc.repository.CustomerRepository;

public class SignUp extends AbstractPage {

  private CustomerRepository customerRepository;
  private User customer;

  SignUp() {
    customerRepository = new CustomerRepository();
    customer = new User();
    choices.add("Sign Up");
    choices.add("Go Back");
  }

  @Override
  public void run() {
    System.out.println("# Sign Up");

    while (true) {
      String email = getEmailFromInput("Enter email address: ");
      if (customerRepository.getCustomerByEmail(email) == null) {
        customer.setEmail(email);
        break;
      } else {
        System.out.println("This email address is already used");
      }
    }

    customer.setPassword(getStringFromInput("Enter password: "));
    customer.setName(getStringFromInput("Enter name: "));
    customer.setAddress(getStringFromInput("Enter address: "));
    customer.setPhone(getPhoneFromInput("Enter phone number (e.g. 123-456-7890): "));

    displayChoices();

    switch (getChoiceFromInput()) {
      case 1:
        signUp();
        break;
      case 2:
        goBack();
    }
  }

  private void signUp() {
    customerRepository.add(customer);
    System.out.printf("Successfully signed up, your ID is %d\n",
        customerRepository.getIdByEmail(customer.getEmail()));
    Page login = new Login();
    login.run();
  }

  private void goBack() {
    Page home = new Home();
    home.run();
  }
}
