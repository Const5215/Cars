package edu.ncsu.csc.pages.employee.manager;

import edu.ncsu.csc.entity.*;
import edu.ncsu.csc.pages.AbstractPage;
import edu.ncsu.csc.pages.Page;
import edu.ncsu.csc.repository.*;

import java.text.SimpleDateFormat;
import java.util.List;

public class OrderHistory extends AbstractPage {
  private User manager;
  private ExternalOrderRepository externalOrderRepository = new ExternalOrderRepository();
  private InternalOrderRepository internalOrderRepository = new InternalOrderRepository();
  private EmploymentRepository employmentRepository = new EmploymentRepository();
  private PartRepository partRepository = new PartRepository();
  private DistributorRepository distributorRepository = new DistributorRepository();
  private CenterRepository centerRepository = new CenterRepository();
  OrderHistory(User manager) {
    this.manager = manager;
    choices.add("Go Back");
  }

  @Override
  public void run() {
    System.out.println("#orderHistory");
    printOrder();
    displayChoices();
    getChoiceFromInput();
    goBack();
  }

  private void goBack() {
    Page orders = new Orders(manager);
    orders.run();
  }

  private void printOrder() {
    Long centerId = employmentRepository.getCenterIdByEmployeeId(manager.getId());
    System.out.println("#ExternalOder");
    System.out.println("----------------");
    printExternalOrder(centerId);
    System.out.println("#InternalOder");
    System.out.println("----------------");
    printInternalOrder(centerId);
  }

  private void printExternalOrder(Long centerId){
    List<Order> externalOrderList = externalOrderRepository.getExternalOrder(centerId);
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-dd HH:mm:SS");
    for(Order externalOrder : externalOrderList) {
      Part part = partRepository.getPartById(externalOrder.getPartId());
      Distributor distributor = distributorRepository.getDistributor(externalOrder.getDistributorId());
      System.out.println("Order Id : " + externalOrder.getId());
      System.out.println("Date : " + simpleDateFormat.format(externalOrder.getOrderDate()));
      System.out.println("Part Name : " + part.getName());
      System.out.println("Supplier Name : " + distributor.getName());
      System.out.println("Purchaser Name : " + centerRepository.getCenterById(centerId).getName());
      System.out.println("Quantity : " + externalOrder.getQuantity());
      System.out.println("Unit Price : " + part.getUnitPrice());
      System.out.println("Total Cost : " + externalOrder.getTotal());
      System.out.println("Order Status : " + externalOrder.getStatus());
      System.out.println("----------------");
    }
  }

  private void printInternalOrder(Long centerId){
    List<Order> internalOrderList = internalOrderRepository.getInternalOrder(centerId);
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-dd HH:mm:SS");
    for(Order internalOrder : internalOrderList){
      Part part = partRepository.getPartById(internalOrder.getPartId());
      System.out.println("Order Id : " + internalOrder.getId());
      System.out.println("Date : " + simpleDateFormat.format(internalOrder.getOrderDate()));
      System.out.println("Part Name : " + part.getName());
      System.out.println("Supplier Name : " + centerRepository.getCenterById(internalOrder.getFromId()).getName());
      System.out.println("Purchaser Name : " + centerRepository.getCenterById(internalOrder.getToId()).getName());
      System.out.println("Quantity : " + internalOrder.getQuantity());
      System.out.println("Unit Price : " + part.getUnitPrice());
      System.out.println("Total Cost : " + internalOrder.getTotal());
      System.out.println("Order Status : " + internalOrder.getStatus());
      System.out.println("----------------");
    }
  }

  public static void main(String[] args) {
    User user = new User();
    user.setId(911639633L);
    OrderHistory newOrder = new OrderHistory(user);
    newOrder.run();
  }
}
