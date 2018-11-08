package edu.ncsu.csc.pages.employee.receptionist;

import edu.ncsu.csc.entity.User;
import edu.ncsu.csc.pages.AbstractPage;
import edu.ncsu.csc.pages.Page;
import edu.ncsu.csc.pages.employee.EmployeeProfile;
import edu.ncsu.csc.pages.employee.EmployeeViewCustomerProfile;
import edu.ncsu.csc.pages.employee.Profile;
import edu.ncsu.csc.pages.employee.ViewCustomerProfile;
import edu.ncsu.csc.pages.start.Home;

public class ReceptionistLanding extends AbstractPage {

  private User receptionist;

  public ReceptionistLanding(User receptionist) {

    this.receptionist = receptionist;

    choices.add("Profile");
    choices.add("View Customer Profile");
    choices.add("Register Car");
    choices.add("Service History");
    choices.add("Schedule Service");
    choices.add("Reschedule Service");
    choices.add("Invoices");
    choices.add("Daily Task-Update Inventory");
    choices.add("Logout");
  }

  @Override
  public void run() {
    // stupid way to Clear console !!! 20 newlines
    System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");

    System.out.println("# Receptionist");
    displayChoices();

    switch (getChoiceFromInput()) {
      case 1:
        profile();
        break;
      case 2:
        viewCustomerProfile();
        break;
      case 3:
        registerCar();
        break;
    }
  }

  private void profile() {
    // Case 1:  link to Employee: Profile
    Page profile = new EmployeeProfile(receptionist);
    profile.run();
  }

  private void viewCustomerProfile() {
    // Case 2:  link to Employee: View Customer Profile
    Page viewCustomerProfile = new EmployeeViewCustomerProfile(receptionist);
    viewCustomerProfile.run();
  }

  private void registerCar() {
    // Case 3: link to Receptionist: Register Car
    RecRegisterCar recRegisterCar = new RecRegisterCar(receptionist);
    recRegisterCar.run();
  }
}
