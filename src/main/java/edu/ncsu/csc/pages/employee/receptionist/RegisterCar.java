package edu.ncsu.csc.pages.employee.receptionist;

import edu.ncsu.csc.entity.User;
import edu.ncsu.csc.pages.AbstractPage;
import edu.ncsu.csc.pages.Page;

public class RegisterCar extends AbstractPage {
  private User receptionist;

  RegisterCar(User receptionist) {
    this.receptionist = receptionist;
    choices.add("Register");
    choices.add("Cancel");
  }

  @Override
  public void run() {
    System.out.println("#registerCar");

    displayChoices();
    switch (getChoiceFromInput()) {
      case 1:
        register();
        break;
      case 2:
        cancel();
    }
  }

  private void cancel() {
    Page receptionistLanding = new ReceptionistLanding(receptionist);
    receptionistLanding.run();
  }

  private void register() {
    //work here

    Page receptionistLanding = new ReceptionistLanding(receptionist);
    receptionistLanding.run();
  }
}
