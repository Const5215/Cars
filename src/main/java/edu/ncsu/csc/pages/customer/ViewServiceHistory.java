package edu.ncsu.csc.pages.customer;

import edu.ncsu.csc.entity.ServiceHistory;
import edu.ncsu.csc.entity.User;
import edu.ncsu.csc.pages.AbstractPage;
import edu.ncsu.csc.pages.Page;
import edu.ncsu.csc.repository.EmployeeRepository;
import edu.ncsu.csc.repository.ServiceHistoryRepository;

import java.util.List;

public class ViewServiceHistory extends AbstractPage {
  private User customer;

  ViewServiceHistory(User customer) {
    this.customer = customer;
    choices.add("Go Back");
  }

  @Override
  public void run() {
    System.out.println("#viewServiceHistory");
    List<ServiceHistory> serviceHistoryList = getServiceHistoryList();
    printServiceHistory(serviceHistoryList);
    displayChoices();
    getChoiceFromInput();
    goBack();
  }

  private List<ServiceHistory> getServiceHistoryList() {
    ServiceHistoryRepository serviceHistoryRepository = new ServiceHistoryRepository();
    return serviceHistoryRepository.getServiceHistoryListByCustomerId(customer.getId());
  }

  private void printServiceHistory(List<ServiceHistory> serviceHistoryList) {
    System.out.printf("Service history: %d in total.\n", serviceHistoryList.size());
    EmployeeRepository employeeRepository = new EmployeeRepository();
    for (int i = 0; i < serviceHistoryList.size(); i++) {
      ServiceHistory serviceHistory = serviceHistoryList.get(i);
      System.out.printf("Service history #%d:\n", i);
      System.out.println("Service ID: " + serviceHistory.getId());
      System.out.println("Licence Plate: " + serviceHistory.getCarPlate());
      System.out.println("Service Type: " + serviceHistory.getServiceType().toString());
      System.out.println("Mechanic Name: " +
          employeeRepository.getEmployeeNameByEmployeeId(serviceHistory.getMechanicId()));
      System.out.println("Service Start Time: " + serviceHistory.getStartTime());
      System.out.println("Service End Time: " + serviceHistory.getEndTime());
      System.out.println("Service Status: " + serviceHistory.getServiceStatus().toString());
    }
  }

  private void goBack() {
    Page customerService = new Service(customer);
    customerService.run();
  }

}
