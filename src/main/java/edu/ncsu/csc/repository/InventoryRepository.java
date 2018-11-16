package edu.ncsu.csc.repository;

import edu.ncsu.csc.entity.Inventory;
import java.sql.DriverManager;
import java.sql.SQLException;

public class InventoryRepository extends AbstractRepository {

  public void update(Inventory inventory) {
    try {
      connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
      preparedStatement = connection.prepareStatement(
          "update INVENTORY set CURRENT_QUANTITY=?, AVAILABLE_QUANTITY=? where CENTER_ID=? and PART_ID=?");
      preparedStatement.setInt(1, inventory.getCurrentQuantity());
      preparedStatement.setInt(2, inventory.getAvailableQuantity());
      preparedStatement.setLong(3, inventory.getCenterId());
      preparedStatement.setLong(4, inventory.getPartId());
      preparedStatement.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      closeSqlConnection();
    }
  }

  public Inventory getInventoryByCenterIdAndPartId(Long centerId, Long partId) {
    Inventory inventory = null;

    try {
      connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
      preparedStatement = connection
          .prepareStatement("select * from INVENTORY where CENTER_ID=? and PART_ID=?");
      preparedStatement.setLong(1, centerId);
      preparedStatement.setLong(2, partId);
      resultSet = preparedStatement.executeQuery();
      if (resultSet.next()) {
        inventory = new Inventory(
            resultSet.getLong("CENTER_ID"),
            resultSet.getLong("PART_ID"),
            resultSet.getInt("CURRENT_QUANTITY"),
            resultSet.getInt("AVAILABLE_QUANTITY"),
            resultSet.getInt("MIN_THRESHOLD"),
            resultSet.getInt("MIN_ORDER_QUANTITY")
        );
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      closeSqlConnection();
    }

    return inventory;
  }
}
