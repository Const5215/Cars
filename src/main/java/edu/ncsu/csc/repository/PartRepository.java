package edu.ncsu.csc.repository;

import edu.ncsu.csc.entity.Part;
import edu.ncsu.csc.pages.AbstractPage;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PartRepository extends AbstractPage {
  public Part getPartByPartId(long partId) {
    Part part = null;
    try {
      connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
      preparedStatement = connection.prepareStatement("SELECT * FROM PART WHERE ID=?");
      preparedStatement.setLong(1, partId);
      resultSet = preparedStatement.executeQuery();
      if (resultSet.next()) {
        part = new Part();
        part.setName(resultSet.getString("NAME"));
        part.setId(resultSet.getLong("ID"));
        part.setMake(resultSet.getString("MAKE"));
        part.setDistributorId(resultSet.getLong("DISTRIBUTOR_ID"));
        part.setDelivery_window(resultSet.getLong("DELIVERY_WINDOW"));
        part.setUnitPrice(resultSet.getFloat("UNIT_PRICE"));
        part.setWarranty(resultSet.getLong("WARRANTY"));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      closeSqlConnection();
    }
    return part;
  }

  public List<Part> getAvailablePartList() {
    List<Part> partList = new ArrayList<>();
    try {
      connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
      preparedStatement = connection.prepareStatement("SELECT * FROM PART");
      resultSet = preparedStatement.executeQuery();
      while (resultSet.next()) {
        Part part = new Part();
        part.setId(resultSet.getLong("ID"));
        part.setName(resultSet.getString("NAME"));
        part.setMake(resultSet.getString("MAKE"));
        part.setUnitPrice(resultSet.getFloat("UNIT_PRICE"));
        part.setWarranty(resultSet.getLong("WARRANTY"));
        part.setDelivery_window(resultSet.getLong("DELIVERY_WINDOW"));
        part.setDistributorId(resultSet.getLong("DISTRIBUTOR_ID"));
        partList.add(part);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      closeSqlConnection();
    }
    return partList;
  }

}
