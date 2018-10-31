package edu.ncsu.csc.pages.employee.receptionist;

import edu.ncsu.csc.entity.User;
import edu.ncsu.csc.pages.AbstractPage;
import edu.ncsu.csc.pages.Page;

import java.awt.*;

public class DailyTaskRecordDeliveries extends AbstractPage {
  private User receptionist;
  public DailyTaskRecordDeliveries(User receptionist) {
    this.receptionist = receptionist;
    choices.add("Enter Order ID(CSV");
    choices.add("Go Back");
  }

  @Override
  public void run() {
    System.out.println("#dailyTaskRecordDeliveries");

    displayChoices();
    switch (getChoiceFromInput()){
      case 1:
        updateOrderList();
        break;
      case 2:
        goBack();
    }
  }

  private void goBack() {
    Page receptionistLanding = new ReceptionistLanding(receptionist);
    receptionistLanding.run();
  }

  private void updateOrderList() {

  }
}
