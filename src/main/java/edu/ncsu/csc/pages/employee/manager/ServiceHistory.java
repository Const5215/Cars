package edu.ncsu.csc.pages.employee.manager;

import edu.ncsu.csc.entity.User;
import edu.ncsu.csc.pages.AbstractPage;
import edu.ncsu.csc.pages.Page;
import edu.ncsu.csc.repository.CustomerRepository;
import edu.ncsu.csc.repository.EmployeeRepository;
import edu.ncsu.csc.repository.EmploymentRepository;
import edu.ncsu.csc.repository.ServiceHistoryRepository;

import java.util.List;

public class ServiceHistory extends AbstractPage {
  private User manager;

  public ServiceHistory(User manager) {
    this.manager = manager;
    choices.add("Go Back");
  }

  @Override
  public void run() {
    ServiceHistoryRepository serviceHistoryRepository = new ServiceHistoryRepository();
    EmploymentRepository employmentRepository = new EmploymentRepository();
    System.out.println("#serviceHistory");

    long centerId = employmentRepository.getCenterIdByEmployeeId(manager.getId());
    List<edu.ncsu.csc.entity.ServiceHistory> serviceHistoryList =
        serviceHistoryRepository.getServiceHistoryListByCenterId(centerId);
    printServiceHistory(serviceHistoryList);
    displayChoices();
    getChoiceFromInput();
    goBack();
  }

  private void printServiceHistory(List<edu.ncsu.csc.entity.ServiceHistory> serviceHistoryList) {
    System.out.printf("Service history: %d in total.\n", serviceHistoryList.size());
    EmployeeRepository employeeRepository = new EmployeeRepository();
    CustomerRepository customerRepository = new CustomerRepository();
    for (int i = 0; i < serviceHistoryList.size(); i++) {
      edu.ncsu.csc.entity.ServiceHistory serviceHistory = serviceHistoryList.get(i);
      System.out.printf("Service history #%d:\n", i);
      System.out.println("Service ID: " + serviceHistory.getId());
      System.out.println("Customer Name: " + customerRepository.getCustomerNameByCustomerId(
          serviceHistory.getCustomerId()
      ));
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
    Page managerLanding = new ManagerLanding(manager);
    managerLanding.run();
  }
}
