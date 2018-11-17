package edu.ncsu.csc.pages.customer;

import edu.ncsu.csc.controller.ServiceController;
import edu.ncsu.csc.entity.Car;
import edu.ncsu.csc.entity.ServiceHistory;
import edu.ncsu.csc.entity.User;
import edu.ncsu.csc.pages.AbstractPage;
import edu.ncsu.csc.pages.Page;
import edu.ncsu.csc.repository.CarRepository;
import edu.ncsu.csc.repository.EmployeeRepository;
import edu.ncsu.csc.repository.ServiceHistoryRepository;

import java.util.List;

public class ScheduleService extends AbstractPage {
  private User customer;
  private CarRepository carRepository;
  private EmployeeRepository employeeRepository;
  private ServiceController serviceController;
  ScheduleService(User customer) {
    this.customer = customer;
    this.carRepository = new CarRepository();
    this.employeeRepository = new EmployeeRepository();
    choices.add("Schedule Maintenance");
    choices.add("Schedule Repair");
    choices.add("Go Back");
  }

  @Override
  public void run() {
    System.out.println("# Schedule Service");

    Car car = getCar();
    Integer currentMileage = getCurrentMileage();
    User preferredMechanic = getPreferredMechanic();
    displayChoices();
    switch (getChoiceFromInput()) {
      case 1:
        scheduleMaintenance(car, currentMileage, preferredMechanic);
        break;
      case 2:
        scheduleRepair(car, currentMileage, preferredMechanic);
        break;
      case 3:
        goBack();
    }
  }

  private User getPreferredMechanic() {
    User preferredMechanic = null;

    System.out.print("Do you have a preferred mechanic name?(y/n):");
    String choice = scanner.nextLine();
    if (choice.equals("y")) {
      do{
        System.out.println("Enter preferred Mechanic Name: ");
        String name = scanner.nextLine();
        preferredMechanic = employeeRepository.getMechanicByName(name);
        if (preferredMechanic == null) {
          System.out.println("mechanic not found.");
        }
      }while(preferredMechanic == null);
    }
    else {
      preferredMechanic = serviceController.getRandomMechanicByCenter(
          serviceController.getCustomerServiceCenterId(customer));
    }

    return preferredMechanic;
  }

  private Integer getCurrentMileage() {
    System.out.print("Enter Current Mileage: ");
    return Integer.parseInt(scanner.nextLine());
  }

  private Car getCar() {
    Car car = null;
    do {
      System.out.println("Enter Licence Plate: ");
      String plate = scanner.nextLine();
      car = carRepository.getCarByPlate(plate);
      if (car == null || !car.getCustomerId().equals(customer.getId())) {
        System.out.println("You do not have such a car.");
      }
    }while(car == null);

    return car;
  }

  private void scheduleRepair(Car car,Integer currentMileage, User preferredMechanic) {
    Page scheduleRepairPage_1 = new ScheduleRepairPage_1(customer, car, currentMileage, preferredMechanic);
    scheduleRepairPage_1.run();
  }

  private void scheduleMaintenance(Car car,Integer currentMileage, User preferredMechanic) {
    Page scheduleMaintenancePage_1 = new ScheduleMaintenancePage_1(customer, car, currentMileage, preferredMechanic);
    scheduleMaintenancePage_1.run();
  }

  private void goBack() {
    Page customerService = new Service(customer);
    customerService.run();
  }
}
