package edu.ncsu.csc.pages.employee.receptionist;

import edu.ncsu.csc.entity.User;
import edu.ncsu.csc.pages.AbstractPage;
import edu.ncsu.csc.pages.Page;
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
    choices.add("Daily Task-Record Deliveries");
    choices.add("Logout");
  }

  @Override
  public void run() {
    System.out.println("#receptionistLanding");

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
      case 4:
        serviceHistory();
        break;
      case 5:
        scheduleService();
        break;
      case 6:
        rescheduleService();
        break;
      case 7:
        invoices();
        break;
      case 8:
        dailyTaskUpdateInventory();
        break;
      case 9:
        dailyTaskRecordDeliveries();
        break;
      case 10:
        logout();
    }
  }

  private void logout() {
    Page home = new Home();
    home.run();
  }

  private void dailyTaskRecordDeliveries() {
    Page dailyTaskRecordDeliveries = new DailyTaskRecordDeliveries(receptionist);
    dailyTaskRecordDeliveries.run();
  }

  private void dailyTaskUpdateInventory() {
    Page dailyTaskUpdateInventory = new DailyTaskUpdateInventory(receptionist);
    dailyTaskUpdateInventory.run();
  }

  private void invoices() {
    Page invoices = new Invoices(receptionist);
    invoices.run();
  }

  private void rescheduleService() {
    Page rescheduleService = new RescheduleServicePage_1(receptionist);
    rescheduleService.run();
  }

  private void scheduleService() {
    Page scheduleService = new ScheduleService(receptionist);
    scheduleService.run();
  }

  private void serviceHistory() {
    Page serviceHistory = new ServiceHistory(receptionist);
    serviceHistory.run();
  }

  private void registerCar() {
    Page registerCar = new RegisterCar(receptionist);
    registerCar.run();
  }

  private void viewCustomerProfile() {
    Page viewCustomerProfile = new ViewCustomerProfile(receptionist);
    viewCustomerProfile.run();
  }

  private void profile() {
    Page profile = new Profile(receptionist);
    profile.run();
  }
}
