package edu.ncsu.csc.pages.employee.manager;

import edu.ncsu.csc.entity.Part;
import edu.ncsu.csc.entity.User;
import edu.ncsu.csc.pages.AbstractPage;
import edu.ncsu.csc.pages.Page;
import edu.ncsu.csc.repository.EmploymentRepository;
import edu.ncsu.csc.repository.InventoryRepositoryImpl;
import edu.ncsu.csc.repository.PartRepository;

import java.util.List;

public class Inventory extends AbstractPage {
  private User manager;

  Inventory(User manager) {
    this.manager = manager;
    choices.add("Go Back");
  }

  @Override
  public void run() {
    System.out.println("#inventory");

    List<edu.ncsu.csc.entity.Inventory> inventoryList = getInventoryPartList();

    printInventoryPartList(inventoryList);
    displayChoices();
    getChoiceFromInput();
    goBack();
  }

  private void printInventoryPartList(List<edu.ncsu.csc.entity.Inventory> inventoryList) {
    System.out.printf("Total part nums:%d\n", inventoryList.size());
    PartRepository partRepository = new PartRepository();
    for (int i = 0; i < inventoryList.size(); i++) {
      Part part = partRepository.getPartByPartId(inventoryList.get(i).getPartId());
      System.out.printf("Info for part #%d:\n", i);
      System.out.println("Part ID:" + inventoryList.get(i).getPartId());
      System.out.println("Part Name:" + part.getName());
      System.out.println("Quantity:" + inventoryList.get(i).getAvailableQuantity());
      System.out.println("Unit Price:" + part.getUnitPrice());
      System.out.println("Minimum Quantity Threshold:" + inventoryList.get(i).getMinThreshold());
      System.out.println("Minimum Order Threshold:" + inventoryList.get(i).getMinOrderQuantity());
    }
  }

  private List<edu.ncsu.csc.entity.Inventory> getInventoryPartList() {
    InventoryRepositoryImpl inventoryRepository = new InventoryRepositoryImpl();
    EmploymentRepository employmentRepository = new EmploymentRepository();
    return inventoryRepository.getInventory(
        employmentRepository.getCenterIdByEmployeeId(manager.getId())
    );
  }

  private void goBack() {
    Page managerLanding = new ManagerLanding(manager);
    managerLanding.run();
  }

}
