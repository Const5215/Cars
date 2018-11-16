package edu.ncsu.csc.pages.customer;

import edu.ncsu.csc.entity.User;
import edu.ncsu.csc.pages.AbstractPage;
import edu.ncsu.csc.pages.Page;
import edu.ncsu.csc.repository.CustomerRepository;

public class UpdateProfile extends AbstractPage {

  private CustomerRepository customerRepository;
  private User customer;

  UpdateProfile(User customer) {
    customerRepository = new CustomerRepository();
    this.customer = customer;
    choices.add("Name");
    choices.add("Address");
    choices.add("Phone Number");
    choices.add("Password");
    choices.add("Go back");
  }

  @Override
  public void run() {
    System.out.println("# Update Profile");

    while (true) {
      displayChoices();
      switch (getChoiceFromInput()) {
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
    }
  }

  private void updateName() {
    customer.setName(getStringFromInput("Enter new name: "));
  }

  private void updateAddress() {
    customer.setAddress(getStringFromInput("Enter new address: "));
  }

  private void updatePhone() {
    customer.setPhone(getPhoneFromInput());
  }

  private void updatePassword() {
    customer.setPassword(getStringFromInput("Enter new password: "));
  }

  private void goBack() {
    customerRepository.update(customer);

    Page profile = new Profile(customer);
    profile.run();
  }
}
