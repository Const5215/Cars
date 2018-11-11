package edu.ncsu.csc.pages.customer;

import edu.ncsu.csc.entity.*;
import edu.ncsu.csc.pages.AbstractPage;
import edu.ncsu.csc.pages.Page;
import edu.ncsu.csc.repository.*;

import java.util.List;

public class ViewInvoiceDetails extends AbstractPage {
  private User customer;
  private ServiceHistoryRepository serviceHistoryRepository;
  ViewInvoiceDetails(User customer) {
    this.customer = customer;
    serviceHistoryRepository = new ServiceHistoryRepository();
    choices.add("Go Back");
  }

  @Override
  public void run() {
    System.out.println("#viewInvoiceDetails");

    ServiceHistory serviceHistory;
    do {
      System.out.print("Enter Service ID:");
      long serviceId = Long.parseLong(scanner.nextLine());
      serviceHistory = serviceHistoryRepository.getServiceHistoryByServiceId(serviceId);
    } while (serviceHistory == null);

    printServiceHistory(serviceHistory);
    displayChoices();
    getChoiceFromInput();
    goBack();
  }

  private void printServiceHistory(ServiceHistory serviceHistory) {
    BasicServiceRepository basicServiceRepository = new BasicServiceRepository();
    EmployeeRepository employeeRepository = new EmployeeRepository();
    ServiceHistoryRepository serviceHistoryRepository = new ServiceHistoryRepository();
    CarRepository carRepository = new CarRepository();

    Car car = carRepository.getCarByCarPlate(serviceHistory.getCarPlate());
    String mechanicName = employeeRepository.getEmployeeNameByEmployeeId(serviceHistory.getMechanicId());
    float totalServiceCost = serviceHistoryRepository.getTotalServiceCost(serviceHistory);
    List<Long> basicServiceIdList = basicServiceRepository.getBasicServiceIdListByServiceHistory(serviceHistory);
    System.out.println("Service ID: " + serviceHistory.getId());
    System.out.println("Service Start Time: " + serviceHistory.getStartTime());
    System.out.println("Service End Time: " + serviceHistory.getEndTime());
    System.out.println("Licence Plate: " + serviceHistory.getCarPlate());
    System.out.println("Service Type: " + serviceHistory.getServiceType().toString());
    printPartDetails(serviceHistory, basicServiceIdList, car.getCarModelId());
    System.out.println("Mechanic Name:" + mechanicName);
    System.out.println("Total Service Cost: " + totalServiceCost);
  }

  private void printPartDetails(ServiceHistory serviceHistory, List<Long> basicServiceIdList, long carModelId) {
    BasicServicePartRepository basicServicePartRepository = new BasicServicePartRepository();
    PartRepository partRepository = new PartRepository();
    ServiceHistoryRepository serviceHistoryRepository = new ServiceHistoryRepository();

    for (long basicServiceId : basicServiceIdList) {
      BasicServicePart basicServicePart = basicServicePartRepository.getBasicServicePartByBasicServiceIdAndCarModelId(
          basicServiceId, carModelId
      );
      Part part = partRepository.getPartByPartId(basicServicePart.getPartId());
      double charge = serviceHistoryRepository.getPartCost(serviceHistory, basicServiceId);
      System.out.printf("Part: %s Quantity: %d Charge: %f\n", part.getName(), basicServicePart.getQuantity(), charge);
    }
  }

  private void goBack() {
    Page invoice = new Invoice(customer);
    invoice.run();
  }

}
