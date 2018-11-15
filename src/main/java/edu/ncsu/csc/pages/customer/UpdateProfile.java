package edu.ncsu.csc.pages.customer;

import edu.ncsu.csc.entity.MatchType;
import edu.ncsu.csc.entity.User;
import edu.ncsu.csc.pages.AbstractPage;
import edu.ncsu.csc.pages.Page;
import edu.ncsu.csc.repository.CustomerRepository;

public class UpdateProfile extends AbstractPage {

  private User customer;

  UpdateProfile(User customer) {
    this.customer = customer;
    choices.add("Name");
    choices.add("Address");
    choices.add("Phone Number");
    choices.add("Password");
    choices.add("Go back");
  }

  @Override
  public void run() {
    int choice;
    do {
      System.out.println("#Update Profile");
      displayChoices();
      choice = getChoiceFromInput();
      switch (choice) {
        case 1:
          updateName();
          break;
        case 2:
          updateAddress();
          break;
        case 3:
          updatePhone();
          break;
        case 4:
          updatePassword();
          break;
        case 5:
          goBack();
      }
    } while (choice != 5);
  }

  private void goBack() {
    Page profile = new Profile(customer);
    profile.run();
  }

  private void updateAddress() {
    System.out.print("Enter new address: ");
    customer.setAddress(scanner.nextLine());
    CustomerRepository customerRepository = new CustomerRepository();
    customerRepository.updateTable("ADDRESS", customer.getAddress(), customer.getId());
    System.out.println("Address updated.");
  }

  private void updatePhone() {
    customer.setPhone(getInfo("Enter new phone number (e.g. 123-456-7890): ", MatchType.Phone));
    CustomerRepository customerRepository = new CustomerRepository();
    customerRepository.updateTable("PHONE", customer.getPhone(), customer.getId());
    System.out.println("Phone updated.");
  }

  private void updateName() {
    System.out.print("Enter new name: ");
    customer.setName(scanner.nextLine());
    CustomerRepository customerRepository = new CustomerRepository();
    customerRepository.updateTable("NAME", customer.getName(), customer.getId());
    System.out.println("Name updated.");
  }

  private void updatePassword() {
    System.out.print("Enter new password: ");
    customer.setPassword(scanner.nextLine());
    CustomerRepository customerRepository = new CustomerRepository();
    customerRepository.updateTable("PASSWORD", customer.getPassword(), customer.getId());
    System.out.println("Password updated.");
  }

}
