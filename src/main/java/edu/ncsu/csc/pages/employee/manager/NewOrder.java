package edu.ncsu.csc.pages.employee.manager;

import edu.ncsu.csc.entity.ExternalOrder;
import edu.ncsu.csc.entity.OrderStatus;
import edu.ncsu.csc.entity.Part;
import edu.ncsu.csc.entity.User;
import edu.ncsu.csc.pages.AbstractPage;
import edu.ncsu.csc.pages.Page;
import edu.ncsu.csc.repository.*;

import java.util.Calendar;
import java.util.List;

public class NewOrder extends AbstractPage {
  private User manager;
  private PartRepository partRepository = new PartRepository();
  private ExternalOrder externalOrder = new ExternalOrder();
  private IExternalOrderRepository externalOrderRepository = new ExternalOrderRepositoryImpl();
  private IEmploymentRepository employmentRepository = new EmploymentRepositoryImpl();

  NewOrder(User manager) {
    this.manager = manager;
    choices.add("Place Order");
    choices.add("Go Back");
  }

  @Override
  public void run() {
    inputOrder(printPart());
    displayChoices();
    switch (getChoiceFromInput()) {
      case 1:
        placeOrder();
      case 2:
        goBack();
    }
  }

  private void goBack() {
    Page orders = new Orders(manager);
    orders.run();
  }

  private int printPart(){
    List<Part> partList = partRepository.getAvailablePartList();
    System.out.println("=======================");
    System.out.println("   Part Information");
    for (Part part : partList){
      System.out.println("Id : " + part.getId() + "\tName :" + part.getName());
    }
    System.out.println("=======================");
    return partList.size();
  }

  private void inputOrder(int partNum) {
    String partId;
    do {
      System.out.println("Please enter part id:");
      partId = scanner.nextLine();
    } while (!partId.matches("^[1-9][0-9]*$") && Integer.valueOf(partId) <= partNum);
    String quantity;
    do {
      System.out.println("please input quantity:");
      quantity = scanner.nextLine();
    } while(!quantity.matches("^[1-9][0-9]*$"));
    externalOrder.setPartId(Long.parseLong(partId));
    externalOrder.setQuantity(Integer.parseInt(quantity));
  }

  private void placeOrder(){
    Part part = partRepository.getPartByPartId(externalOrder.getPartId());
    externalOrder.setTotal(externalOrder.getQuantity() * part.getUnitPrice());
    externalOrder.setDistributorId(part.getDistributorId());
    externalOrder.setCenterId(employmentRepository.getCenterId(manager));
    Calendar calendar = Calendar.getInstance();
    calendar.add(Calendar.DATE, (int) part.getDelivery_window());
    externalOrder.setExpectDeliveryDate(calendar.getTime());
    externalOrder.setStatus(OrderStatus.Pending.getStatus());
    externalOrderRepository.saveExternalOrder(externalOrder);
  }

  public static void main(String[] args) {
    User user = new User();
    user.setId(1L);
    NewOrder newOrder = new NewOrder(user);
    newOrder.run();
  }
}
