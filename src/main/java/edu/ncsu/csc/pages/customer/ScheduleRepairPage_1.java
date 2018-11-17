package edu.ncsu.csc.pages.customer;

import edu.ncsu.csc.controller.ServiceController;
import edu.ncsu.csc.entity.*;
import edu.ncsu.csc.pages.AbstractPage;
import edu.ncsu.csc.pages.Page;
import edu.ncsu.csc.repository.InventoryRepository;
import edu.ncsu.csc.repository.OrderRepository;

import java.sql.Date;
import java.util.List;

public class ScheduleRepairPage_1 extends AbstractPage {
  private User customer;
  private Car car;
  private Integer currentMileage;
  private User preferredMechanic;
  private ServiceController serviceController;

  public ScheduleRepairPage_1(User customer, Car car, Integer currentMileage, User preferredMechanic) {
    this.customer = customer;
    this.car = car;
    this.currentMileage = currentMileage;
    this.preferredMechanic = preferredMechanic;
    this.serviceController = new ServiceController();
    choices.add("Engine knock");
    choices.add("Car drifts in a particular direction");
    choices.add("Battery does not hold charge");
    choices.add("Black/unclean exhaust");
    choices.add("A/CHeater not working");
    choices.add("Head lamps/Tail lamps not working");
    choices.add("Check engine light");
    choices.add("Go back");
  }

  @Override
  public void run() {
    System.out.println("#scheduleRepair(Page 1)");

    displayChoices();
    int choice = getChoiceFromInput();
    switch (choice) {
      case 1:
      case 2:
      case 3:
      case 4:
      case 5:
      case 6:
      case 7:
        createDiagnosticReport(choice);
        break;
      case 8:
        goBack();
    }
  }

  private void goBack() {
    Page scheduleService = new ScheduleService(customer);
    scheduleService.run();
  }

  private void displayErrorMessage(Date repairDate) {
    System.out.println("Sorry. We cannot arrange your repair right now due to insufficient part.");
    System.out.println("We have placed orders according to your demand. Please try after: " + timeFormat.format(repairDate));
  }

  private Date findRepairDate(ServiceHistory serviceHistory) {
    Date repairDate = serviceController.getTomorrow();
    List<BasicServicePart> basicServicePartList = serviceController.getAllBasicServicePartsByServiceHistory(serviceHistory);
    for (BasicServicePart basicServicePart : basicServicePartList) {
      Date basicServiceDate = serviceController.getBasicServiceDate(serviceHistory, basicServicePart);
      if (basicServiceDate.after(repairDate))
        repairDate = basicServiceDate;
    }
    return repairDate;
  }

  private void createDiagnosticReport(int choice) {
    ServiceHistory serviceHistory = createServiceHistory(choice);
    Date repairDate = findRepairDate(serviceHistory);
    if (repairDate == serviceController.getTomorrow()) {
      Page scheduleRepairPage_2 = new ScheduleRepairPage_2(customer);
      scheduleRepairPage_2.run();
    } else {
      displayErrorMessage(repairDate);
      Page scheduleService = new ScheduleService(customer);
      scheduleService.run();
    }
  }

  private ServiceHistory createServiceHistory(int choice) {
    ServiceHistory newServiceHistory = new ServiceHistory();
    newServiceHistory.setServiceStatus(ServiceStatus.Pending);
    newServiceHistory.setCarPlate(car.getPlate());
    newServiceHistory.setServiceType(ServiceType.Repair);
    newServiceHistory.setCenterId(serviceController.getCustomerServiceCenterId(customer));
    newServiceHistory.setCustomerId(customer.getId());
    newServiceHistory.setDiagnosisId((long) choice);
    newServiceHistory.setMileage(currentMileage);
    newServiceHistory.setMechanicId(preferredMechanic.getId());
    return newServiceHistory;
  }
}
