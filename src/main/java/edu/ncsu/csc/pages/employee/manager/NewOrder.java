package edu.ncsu.csc.pages.employee.manager;

import edu.ncsu.csc.entity.Order;
import edu.ncsu.csc.entity.OrderStatus;
import edu.ncsu.csc.entity.Part;
import edu.ncsu.csc.entity.User;
import edu.ncsu.csc.pages.AbstractPage;
import edu.ncsu.csc.pages.Page;
import edu.ncsu.csc.repository.*;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class NewOrder extends AbstractPage {
  private User manager;
  private PartRepository partRepository = new PartRepository();
  private Order externalOrder = new Order();
  private ExternalOrderRepository externalOrderRepository = new ExternalOrderRepository();
  private EmploymentRepository employmentRepository = new EmploymentRepository();

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
    List<Part> partList = partRepository.getAllParts();
    System.out.println("=======================");
    System.out.println("   Part Information");
    for (Part part : partList){
      System.out.println(part.getId() + ": " + part.getName());
    }
    System.out.println("=======================");
    return partList.size();
  }

  private void inputOrder(int partNum) {
    String partId;
    do {
      System.out.print("Please enter part id:");
      partId = scanner.nextLine();
    } while (!partId.matches("^[1-9][0-9]*$") || Integer.valueOf(partId) > partNum);
    String quantity;
    do {
      System.out.print("please input quantity:");
      quantity = scanner.nextLine();
    } while(!quantity.matches("^[1-9][0-9]*$"));
    externalOrder.setPartId(Long.parseLong(partId));
    externalOrder.setQuantity(Integer.parseInt(quantity));
  }

  private void placeOrder(){
    Part part = partRepository.getPartById(externalOrder.getPartId());
    Long centerId = employmentRepository.getCenterIdByEmployeeId(manager.getId());
    externalOrder.setTotal(externalOrder.getQuantity() * part.getUnitPrice());
    externalOrder.setDistributorId(part.getDistributorId());
    externalOrder.setCenterId(centerId);
    Calendar calendar = Calendar.getInstance();
    calendar.add(Calendar.DATE, part.getDeliveryWindow());
    externalOrder.setExpectedDeliveryDate(calendar.getTime());
    externalOrder.setStatus(OrderStatus.Pending.getStatus());
    int result_status = externalOrderRepository.saveExternalOrder(externalOrder);
    if (result_status != 0) {
      Long id = externalOrderRepository.lastExternalOrderId(centerId);
      System.out.println("Order ID: " + id);
      System.out.println("Estimated date of fulfillment: " +
              new SimpleDateFormat("YYYY-MM-dd").format(calendar.getTime()));
    }
  }
}
