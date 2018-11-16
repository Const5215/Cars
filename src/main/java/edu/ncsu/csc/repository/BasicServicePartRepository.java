package edu.ncsu.csc.repository;

import edu.ncsu.csc.entity.BasicServicePart;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BasicServicePartRepository extends AbstractRepository {

  public BasicServicePart getBasicServicePartByBasicServiceIdAndCarModelId(Long basicServiceId,
      Long carModelId) {
    BasicServicePart basicServicePart = null;

    try {
      connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
      preparedStatement = connection.prepareStatement(
          "select * from BASIC_SERVICE_PART where BASIC_SERVICE_ID=? and CAR_MODEL_ID=?");
      preparedStatement.setLong(1, basicServiceId);
      preparedStatement.setLong(2, carModelId);
      resultSet = preparedStatement.executeQuery();
      if (resultSet.next()) {
        basicServicePart = new BasicServicePart(
            resultSet.getLong("BASIC_SERVICE_ID"),
            resultSet.getLong("PART_ID"),
            resultSet.getLong("CAR_MODEL_ID"),
            resultSet.getInt("QUANTITY")
        );
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      closeSqlConnection();
    }

    return basicServicePart;
  }
}
