package edu.ncsu.csc.repository;

import edu.ncsu.csc.pages.AbstractPage;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RepairRepository extends AbstractPage {
  protected List<Long> getBasicServiceIdListByDiagnosisId(long diagnosisId) {
    List<Long> basicServiceIdList = new ArrayList<>();
    try {
      connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
      preparedStatement = connection.prepareStatement("SELECT * FROM REPAIR WHERE DIAGNOSIS_ID=?");
      preparedStatement.setLong(1, diagnosisId);
      resultSet = preparedStatement.executeQuery();
      while (resultSet.next()) {
        long basicServiceId = resultSet.getLong("BASIC_SERVICE_ID");
        basicServiceIdList.add(basicServiceId);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      closeSqlConnection();
    }
    return basicServiceIdList;
  }
}
