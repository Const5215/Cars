package edu.ncsu.csc.repository;

import edu.ncsu.csc.entity.BasicServicePart;
import edu.ncsu.csc.pages.AbstractPage;

import java.sql.DriverManager;
import java.sql.SQLException;

public class BasicServicePartRepository extends AbstractPage {
  public void addBasicServicePart(BasicServicePart basicServicePart) {
    try {
      connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
      preparedStatement = connection.prepareStatement(
          "INSERT INTO BASIC_SERVICE_PART VALUES (?, ?, ?, ?)");
      preparedStatement.setLong(1, basicServicePart.getBasicServiceId());
      preparedStatement.setLong(2, basicServicePart.getPartId());
      preparedStatement.setLong(3, basicServicePart.getCarModelId());
      preparedStatement.setLong(4, basicServicePart.getQuantity());
      preparedStatement.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      closeSqlConnection();
    }
  }

  public long getBasicServiceIdByPartId(long partId) {
    long basicServiceId = -1;
    try {
      connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
      preparedStatement = connection.prepareStatement(
          "SELECT DISTINCT BASIC_SERVICE_ID FROM BASIC_SERVICE_PART WHERE PART_ID=?");
      preparedStatement.setLong(1, partId);
      resultSet = preparedStatement.executeQuery();
      if (resultSet.next()) {
        basicServiceId = resultSet.getLong("BASIC_SERVICE_ID");
      } else {
        System.out.println("part id not found");
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      closeSqlConnection();
    }
    return basicServiceId;
  }

  public BasicServicePart getBasicServicePartByBasicServiceIdAndCarModelId(long basicServiceId, long carModelId) {
    BasicServicePart basicServicePart = new BasicServicePart();
    try {
      connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
      preparedStatement = connection.prepareStatement(
          "SELECT * FROM BASIC_SERVICE_PART WHERE BASIC_SERVICE_ID=? AND CAR_MODEL_ID=?");
      preparedStatement.setLong(1, basicServiceId);
      preparedStatement.setLong(2, carModelId);
      resultSet = preparedStatement.executeQuery();
      if (resultSet.next()) {
        basicServicePart.setBasicServiceId(resultSet.getLong("BASIC_SERVICE_ID"));
        basicServicePart.setPartId(resultSet.getLong("PART_ID"));
        basicServicePart.setCarModelId(resultSet.getLong("CAR_MODEL_ID"));
        basicServicePart.setQuantity(resultSet.getLong("QUANTITY"));
      } else {
        System.out.println("basicServiceId and carModelId not found");
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      closeSqlConnection();
    }
    return basicServicePart;
  }
}
