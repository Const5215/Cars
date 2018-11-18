package edu.ncsu.csc.pages.customer;

import edu.ncsu.csc.controller.ServiceController;
import edu.ncsu.csc.entity.ServiceHistory;
import edu.ncsu.csc.entity.User;
import edu.ncsu.csc.pages.AbstractPage;
import edu.ncsu.csc.pages.Page;
import edu.ncsu.csc.repository.ServiceHistoryRepository;

public class ViewInvoiceDetails extends AbstractPage {
  private User customer;
  private ServiceController serviceController;
  private ServiceHistoryRepository serviceHistoryRepository;

  ViewInvoiceDetails(User customer) {
    this.customer = customer;
    this.serviceController = new ServiceController();
    this.serviceHistoryRepository = new ServiceHistoryRepository();
    choices.add("Go Back");
  }

  @Override
  public void run() {


    System.out.println("#viewInvoiceDetails");
    ServiceHistory serviceHistory;
    do {
      System.out.print("Enter Service ID:");
      long serviceId = Long.parseLong(scanner.nextLine());
      serviceHistory = serviceHistoryRepository.getServiceHistoryById(serviceId);
    } while (serviceHistory == null);

    serviceController.printServiceHistory(serviceHistory);
    displayChoices();
    getChoiceFromInput();
    goBack();
  }



  private void goBack() {
    Page invoice = new Invoice(customer);
    invoice.run();
  }

}
