package edu.ncsu.csc.pages.employee.manager;

import edu.ncsu.csc.entity.Part;
import edu.ncsu.csc.entity.User;
import edu.ncsu.csc.pages.AbstractPage;
import edu.ncsu.csc.pages.Page;
import edu.ncsu.csc.repository.EmploymentRepository;
import edu.ncsu.csc.repository.InventoryRepository;
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
    System.out.println("# Inventory");

    PartRepository partRepository = new PartRepository();
    InventoryRepository inventoryRepository = new InventoryRepository();
    EmploymentRepository employmentRepository = new EmploymentRepository();
    Long centerId = employmentRepository.getCenterIdByEmployeeId(manager.getId());
    List<Part> parts = partRepository.getAllParts();
    edu.ncsu.csc.entity.Inventory inventory;

    System.out.println(
        "Part ID\tPart Name\tQuantity\tUnit Price\tMinimum Quantity Threshold\tMinimum Order Threshold\n");

    for (Part part : parts) {
      inventory = inventoryRepository.getInventoryByCenterIdAndPartId(centerId, part.getId());
      System.out.printf("%d\t%s\t%d\t%f\t%d\t%d\n",
          part.getId(), part.getName(), inventory.getCurrentQuantity(), part.getUnitPrice(),
          inventory.getMinThreshold(), inventory.getMinOrderQuantity());
    }

    displayChoices();
    getChoiceFromInput();
    goBack();
  }

  private void goBack() {
    Page managerLanding = new ManagerLanding(manager);
    managerLanding.run();
  }

}
