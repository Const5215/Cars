package edu.ncsu.csc.pages.customer;

import edu.ncsu.csc.entity.ServiceHistory;
import edu.ncsu.csc.entity.User;
import edu.ncsu.csc.pages.AbstractPage;
import edu.ncsu.csc.pages.Page;
import edu.ncsu.csc.repository.ServiceHistoryRepository;
import java.util.Date;
import java.util.List;

public class ViewServiceHistory extends AbstractPage {


  private User customer;

  ViewServiceHistory(User customer) {
    this.customer = customer;
    choices.add("Go Back");
  }

  @Override
  public void run() {
    System.out.println("# View Service History");

    ServiceHistoryRepository serviceHistoryRepository = new ServiceHistoryRepository();
    List<ServiceHistory> serviceHistories = serviceHistoryRepository
        .getAllServiceHistoriesByCustomerId(customer.getId());
    System.out.println(
        "Service ID\tLicense Plate\tMechanic ID\tService Start Time\tService End Time\tService Status\tService Type\n");
    Date current = new Date();

    for (ServiceHistory serviceHistory : serviceHistories) {
      System.out.printf("%d\t%s\t%d\t%s\t%s\t%s\t%s\n",
          serviceHistory.getId(),
          serviceHistory.getCarPlate(),
          serviceHistory.getMechanicId(),
          timeFormat.format(serviceHistory.getStartTime()),
          timeFormat.format(serviceHistory.getEndTime()),
          serviceHistory.getServiceStatus().toString(),
          serviceHistory.getServiceType().toString());
    }

    displayChoices();
    getChoiceFromInput();
    goBack();
  }

  private void goBack() {
    Page customerService = new Service(customer);
    customerService.run();
  }
}
