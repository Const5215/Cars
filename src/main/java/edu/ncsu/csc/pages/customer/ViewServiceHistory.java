package edu.ncsu.csc.pages.customer;

import edu.ncsu.csc.entity.ServiceHistory;
import edu.ncsu.csc.entity.User;
import edu.ncsu.csc.pages.AbstractPage;
import edu.ncsu.csc.pages.Page;
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

  private void printServiceHistory(List<ServiceHistory> serviceHistoryList) {
    ServiceHistoryRepository serviceHistoryRepository = new ServiceHistoryRepository();
    serviceHistoryRepository.printServiceHistoryListWithoutDetail(serviceHistoryList);
  }

  private List<ServiceHistory> getServiceHistoryList() {
    ServiceHistoryRepository serviceHistoryRepository = new ServiceHistoryRepository();
    return serviceHistoryRepository.getServiceHistoryListByCustomerId(customer.getId());
  }


  private void goBack() {
    Page customerService = new Service(customer);
    customerService.run();
  }

}
