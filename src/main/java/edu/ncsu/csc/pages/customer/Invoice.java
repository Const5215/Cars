package edu.ncsu.csc.pages.customer;

import edu.ncsu.csc.entity.ServiceHistory;
import edu.ncsu.csc.entity.ServiceHistoryDetail;
import edu.ncsu.csc.entity.User;
import edu.ncsu.csc.pages.AbstractPage;
import edu.ncsu.csc.pages.Page;

import java.util.List;

public class Invoice extends AbstractPage {
  private User customer;

  Invoice(User customer) {
    this.customer = customer;
    choices.add("View Invoice Details");
    choices.add("Go Back");
  }

  @Override
  public void run() {
    System.out.println("#Invoice");

    List<ServiceHistory> serviceHistoryList = getServiceHistoryListByCustomerId(customer.getId());
    printServiceHistoryList(serviceHistoryList);
    displayChoices();
    switch (getChoiceFromInput()) {
      case 1:
        viewInvoiceDetails();
        break;
      case 2:
        goBack();
    }
  }

  private void printServiceHistoryList(List<ServiceHistory> serviceHistoryList) {
    System.out.printf("You have %d completed service(s) in total.\n\n", serviceHistoryList.size());
    for (int i = 0; i < serviceHistoryList.size(); i++) {
      ServiceHistory serviceHistory = serviceHistoryList.get(i);
      List<ServiceHistoryDetail> serviceHistoryDetailList =
          getServiceHistoryDetailListByServiceHistoryId(serviceHistory.getId());
      float totalServiceCost = 0;
      for (ServiceHistoryDetail serviceHistoryDetail : serviceHistoryDetailList) {
        long quantity = serviceHistoryDetail.getQuantity();
        long unitPrice = getPartUnitPriceByPartId(serviceHistoryDetail.getPartId());
        totalServiceCost += quantity * unitPrice;
      }
      System.out.printf("Details for service #%d:\n", i);
      totalServiceCost += getMechanicHourlyWageByEmployeeId(serviceHistory.getMechanicId())
          * serviceHistory.getTotalLaborHour();
      System.out.println("Service ID: " + serviceHistory.getId());
      System.out.println("Service Start Time: " + serviceHistory.getStartTime());
      System.out.println("Service End Time: " + serviceHistory.getEndTime());
      System.out.println("Licence Plate: " + serviceHistory.getCarPlate());
      System.out.println("Service Type: " + serviceHistory.getServiceType().toString());
      System.out.println("Mechanic Name:" + getEmployeeNameByEmployeeId(serviceHistory.getMechanicId()));
      System.out.println("Total Service Cost: " + totalServiceCost);
    }
  }

  private void goBack() {
    Page customerLanding = new CustomerLanding(customer);
    customerLanding.run();
  }

  private void viewInvoiceDetails() {
    Page viewInvoiceDetails = new ViewInvoiceDetails(customer);
    viewInvoiceDetails.run();
  }
}
