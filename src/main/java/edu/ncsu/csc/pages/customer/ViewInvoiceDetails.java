package edu.ncsu.csc.pages.customer;

import edu.ncsu.csc.entity.ServiceHistory;
import edu.ncsu.csc.entity.User;
import edu.ncsu.csc.pages.AbstractPage;
import edu.ncsu.csc.pages.Page;
import edu.ncsu.csc.repository.ServiceHistoryRepository;

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

  }

  private void goBack() {
    Page invoice = new Invoice(customer);
    invoice.run();
  }

}
