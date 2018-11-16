package edu.ncsu.csc.repository;

import edu.ncsu.csc.entity.BasicService;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BasicServiceRepository extends AbstractRepository {

  public BasicService getBasicServiceById(Long id) {
    BasicService basicService = null;

    try {
      connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
      preparedStatement = connection.prepareStatement("select * from BASIC_SERVICE where ID=?");
      preparedStatement.setLong(1, id);
      resultSet = preparedStatement.executeQuery();
      if (resultSet.next()) {
        basicService = new BasicService(
            resultSet.getLong("ID"),
            resultSet.getString("NAME"),
            resultSet.getFloat("LABOR_HOUR"),
            resultSet.getInt("CHARGE_RATE")
        );
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      closeSqlConnection();
    }

    return basicService;
  }
}
