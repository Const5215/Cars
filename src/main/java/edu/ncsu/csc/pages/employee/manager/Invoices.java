package edu.ncsu.csc.pages.employee.manager;

import edu.ncsu.csc.controller.ServiceController;
import edu.ncsu.csc.entity.ServiceHistory;
import edu.ncsu.csc.entity.ServiceStatus;
import edu.ncsu.csc.entity.User;
import edu.ncsu.csc.pages.AbstractPage;
import edu.ncsu.csc.pages.Page;
import edu.ncsu.csc.repository.CustomerRepository;
import edu.ncsu.csc.repository.EmployeeRepository;
import edu.ncsu.csc.repository.EmploymentRepository;
import edu.ncsu.csc.repository.ServiceHistoryRepository;

import java.util.List;

class Invoices extends AbstractPage {
  private User manager;
  private CustomerRepository customerRepository;
  private EmployeeRepository employeeRepository;
  private ServiceHistoryRepository serviceHistoryRepository;
  private ServiceController serviceController;
  Invoices(User manager) {
    this.manager = manager;
    customerRepository = new CustomerRepository();
    employeeRepository = new EmployeeRepository();
    serviceHistoryRepository = new ServiceHistoryRepository();
    serviceController = new ServiceController();
    choices.add("Go Back");
  }

  public void run() {
    System.out.println("#invoices");
    List<edu.ncsu.csc.entity.ServiceHistory> serviceHistoryList = getServiceHistoryList();
    //filter by status:complete
    for (ServiceHistory serviceHistory : serviceHistoryList) {
      if (serviceHistory.getServiceStatus() != ServiceStatus.Complete)
        serviceHistoryList.remove(serviceHistory);
    }
    printServiceHistoryList(serviceHistoryList);
    displayChoices();
    getChoiceFromInput();
    goBack();
  }

  private void printServiceHistoryList(List<edu.ncsu.csc.entity.ServiceHistory> serviceHistoryList) {

    System.out.printf("Your Center has %d completed service history in total.\n", serviceHistoryList.size());
    for (int i = 0; i < serviceHistoryList.size(); i++) {
      ServiceHistory serviceHistory = serviceHistoryList.get(i);
      System.out.printf("Service #%d details:\n", i);
      System.out.println("Service ID: " + serviceHistory.getId());
      System.out.println("Customer Name: " + customerRepository.getCustomerById(serviceHistory.getCustomerId()).getName());
      System.out.println("Service Start Time: " + serviceHistory.getStartTime());
      System.out.println("Service End Time: " + serviceHistory.getEndTime());
      System.out.println("Licence Plate: " + serviceHistory.getCarPlate());
      System.out.println("Service Type: " + serviceHistory.getServiceType().toString());
      System.out.println("Mechanic Name: " + employeeRepository.getNameById(serviceHistory.getMechanicId()));
      float totalServiceCost = serviceController.printPartDetails(serviceHistory);
      System.out.println("Total Service Cost: " + totalServiceCost);
    }
  }

  private List<edu.ncsu.csc.entity.ServiceHistory> getServiceHistoryList() {
    ServiceHistoryRepository serviceHistoryRepository = new ServiceHistoryRepository();
    EmploymentRepository employmentRepository = new EmploymentRepository();
    List<edu.ncsu.csc.entity.ServiceHistory> serviceHistoryList;

    long centerId = employmentRepository.getCenterIdByEmployeeId(manager.getId());
    serviceHistoryList = serviceHistoryRepository.getAllServiceHistoriesByCenterId(centerId);
    return serviceHistoryList;
  }

  private void goBack() {
    Page managerLanding = new ManagerLanding(manager);
    managerLanding.run();
  }
}
