package edu.ncsu.csc.repository;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RepairRepository extends AbstractRepository {

  public List<Long> getBasicServiceIdsByDiagnosisId(Long diagnosisId) {
    List<Long> basicServiceIds = new ArrayList<Long>();

    try {
      connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
      preparedStatement = connection.prepareStatement("select * from REPAIR where DIAGNOSIS_ID=?");
      preparedStatement.setLong(1, diagnosisId);
      resultSet = preparedStatement.executeQuery();
      while (resultSet.next()) {
        basicServiceIds.add(resultSet.getLong("BASIC_SERVICE_ID"));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      closeSqlConnection();
    }

    return basicServiceIds;
  }
}
