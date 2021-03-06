package edu.ncsu.csc.repository;

import edu.ncsu.csc.entity.Center;
import java.sql.DriverManager;
import java.sql.SQLException;

public class CenterRepository extends AbstractRepository {

  public Center getCenterById(Long id) {
    Center center = null;

    try {
      connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
      preparedStatement = connection.prepareStatement("select * from CENTER where ID=?");
      preparedStatement.setLong(1, id);
      resultSet = preparedStatement.executeQuery();
      if (resultSet.next()) {
        center = new Center(
            resultSet.getLong("ID"),
            resultSet.getString("NAME"),
            resultSet.getString("TELEPHONE"),
            resultSet.getString("ADDRESS")
        );
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      closeSqlConnection();
    }

    return center;
  }
}
