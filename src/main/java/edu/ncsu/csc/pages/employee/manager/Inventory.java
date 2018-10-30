package edu.ncsu.csc.pages.employee.manager;

import edu.ncsu.csc.entity.Part;
import edu.ncsu.csc.entity.User;
import edu.ncsu.csc.pages.AbstractPage;
import edu.ncsu.csc.pages.Page;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
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

    long centerId = getCenterId(manager.getId());
    List<InventoryPart> inventoryPartList = new ArrayList<>();
    try {
      connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
      preparedStatement = connection.prepareStatement(
          "SELECT * FROM PART P, INVENTORY I WHERE I.CENTER_ID=? AND I.PART_ID = P.ID");
      preparedStatement.setLong(1, centerId);
      resultSet = preparedStatement.executeQuery();
      while (resultSet.next()) {
        InventoryPart inventoryPart = new InventoryPart();
        inventoryPart.setId(resultSet.getLong("PART_ID"));
        inventoryPart.setAvailableQuantity(resultSet.getLong("AVAILABLE_QUANTITY"));
        inventoryPart.setCurrentQuantity(resultSet.getLong("CURRENT_QUANTITY"));
        inventoryPart.setMinOrderQuantity(resultSet.getLong("MIN_ORDER_QUANTITY"));
        inventoryPart.setMinThreshold(resultSet.getLong("MIN_THRESHOLD"));
        inventoryPart.setDistributorId(resultSet.getLong("DISTRIBUTOR_ID"));
        inventoryPart.setName(resultSet.getString("NAME"));
        inventoryPart.setUnitPrice(resultSet.getLong("UNIT_PRICE"));
        inventoryPartList.add(inventoryPart);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      closeSqlConnection();
    }
    System.out.printf("Total part nums:%d\n", inventoryPartList.size());
    for (int i = 0; i < inventoryPartList.size(); i++) {
      System.out.printf("Info for part #%d:\n", i);
      System.out.println("Part ID:" + inventoryPartList.get(i).getId());
      System.out.println("Part Name:" + inventoryPartList.get(i).getName());
      System.out.println("Quantity:" + inventoryPartList.get(i).getAvailableQuantity());
      System.out.println("Unit Price:" + inventoryPartList.get(i).getUnitPrice());
      System.out.println("Minimum Quantity Threshold:" + inventoryPartList.get(i).getMinThreshold());
      System.out.println("Minimum Order Threshold:" + inventoryPartList.get(i).getMinOrderQuantity());
    }
    displayChoices();
    getChoiceFromInput();
    Page managerLanding = new ManagerLanding(manager);
    managerLanding.run();
  }

  private class InventoryPart extends Part {
    private long currentQuantity;
    private long availableQuantity;
    private long minThreshold;
    private long minOrderQuantity;

    InventoryPart() {
    }

    public InventoryPart(long id, String name, long unitPrice, long distributorId, long currentQuantity,
                         long availableQuantity, long minThreshold, long minOrderQuantity) {
      super(id, name, unitPrice, distributorId);
      this.currentQuantity = currentQuantity;
      this.availableQuantity = availableQuantity;
      this.minThreshold = minThreshold;
      this.minOrderQuantity = minOrderQuantity;
    }

    public long getCurrentQuantity() {
      return currentQuantity;
    }

    void setCurrentQuantity(long currentQuantity) {
      this.currentQuantity = currentQuantity;
    }

    long getAvailableQuantity() {
      return availableQuantity;
    }

    void setAvailableQuantity(long availableQuantity) {
      this.availableQuantity = availableQuantity;
    }

    long getMinThreshold() {
      return minThreshold;
    }

    void setMinThreshold(long minThreshold) {
      this.minThreshold = minThreshold;
    }

    long getMinOrderQuantity() {
      return minOrderQuantity;
    }

    void setMinOrderQuantity(long minOrderQuantity) {
      this.minOrderQuantity = minOrderQuantity;
    }
  }
}
