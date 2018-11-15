package edu.ncsu.csc.repository;

import edu.ncsu.csc.pages.AbstractPage;

import java.sql.DriverManager;
import java.sql.SQLException;

public class CenterRepository extends AbstractPage {
  public String getCenterNameByCenterId(long centerId) {
    String centerName = "";
    try {
      connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
      preparedStatement = connection.prepareStatement(
          "SELECT * FROM CENTER WHERE ID=?");
      preparedStatement.setLong(1, centerId);
      resultSet = preparedStatement.executeQuery();
      if (resultSet.next()) {
        centerName = resultSet.getString("NAME");
      } else {
        System.out.println("Center Id not found");
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      closeSqlConnection();
    }
    return centerName;
  }
}
