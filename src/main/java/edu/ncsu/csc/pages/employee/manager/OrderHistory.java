package edu.ncsu.csc.pages.employee.manager;

import edu.ncsu.csc.entity.*;
import edu.ncsu.csc.pages.AbstractPage;
import edu.ncsu.csc.pages.Page;
import edu.ncsu.csc.repository.*;

import java.text.SimpleDateFormat;
import java.util.List;

public class OrderHistory extends AbstractPage {
  private User manager;
  private IExternalOrderRepository externalOrderRepository = new ExternalOrderRepositoryImpl();
  private IInternalOrderRepository internalOrderRepository = new InternalOrderReposotoryImpl();
  private IEmploymentRepository employmentRepository = new EmploymentRepositoryImpl();
  private PartRepository partRepository = new PartRepository();
  private IDistributorRepository distributorRepository = new DistributorRepositoryImpl();
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
    Long centerId = employmentRepository.getCenterId(manager);
    System.out.println("#ExternalOder");
    printExternalOrder(centerId);
    System.out.println("#InternalOder");
    printInternalOrder(centerId);
  }

  private void printExternalOrder(Long centerId){
    List<ExternalOrder> externalOrderList = externalOrderRepository.getExternalOrder(centerId);
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-DD HH:mm:SS");
    for(ExternalOrder externalOrder : externalOrderList) {
      Part part = partRepository.getPartByPartId(externalOrder.getPartId());
      Distributor distributor = distributorRepository.getDistributor(externalOrder.getDistributorId());
      System.out.println("Order Id : " + externalOrder.getId());
      System.out.println("Date : " + simpleDateFormat.format(externalOrder.getOrderDate()));
      System.out.println("Part Name : " + part.getName());
      System.out.println("Supplier Name : " + distributor.getName());
      System.out.println("Purchaser Name : " + centerRepository.getCenterNameByCenterId(centerId));
      System.out.println("Quantity : " + externalOrder.getQuantity());
      System.out.println("Unit Price : " + part.getUnitPrice());
      System.out.println("Total Cost : " + externalOrder.getTotal());
      System.out.println("Order Status : " + externalOrder.getStatus());
    }
  }

  private void printInternalOrder(Long centerId){
    List<InternalOrder> internalOrderList = internalOrderRepository.getInternalOrder(centerId);
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-DD HH:mm:SS");
    for(InternalOrder internalOrder : internalOrderList){
      Part part = partRepository.getPartByPartId(internalOrder.getPartId());
      System.out.println("Order Id : " + internalOrder.getId());
      System.out.println("Date : " + simpleDateFormat.format(internalOrder.getOrderDate()));
      System.out.println("Part Name : " + part.getName());
      System.out.println("Supplier Name : " + centerRepository.getCenterNameByCenterId(internalOrder.getFromId()));
      System.out.println("Purchaser Name : " + centerRepository.getCenterNameByCenterId(internalOrder.getToId()));
      System.out.println("Quantity : " + internalOrder.getQuantity());
      System.out.println("Unit Price : " + part.getUnitPrice());
      System.out.println("Total Cost : " + internalOrder.getTotal());
      System.out.println("Order Status : " + internalOrder.getStatus());
    }
  }
}
