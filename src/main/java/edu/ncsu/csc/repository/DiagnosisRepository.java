package edu.ncsu.csc.repository;

import edu.ncsu.csc.entity.Diagnosis;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DiagnosisRepository extends AbstractRepository {

  public Diagnosis getDiagnosisById(Long id) {
    Diagnosis diagnosis = null;

    try {
      connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
      preparedStatement = connection.prepareStatement("select * from DIAGNOSIS where ID=?");
      preparedStatement.setLong(1, id);
      resultSet = preparedStatement.executeQuery();
      if (resultSet.next()) {
        diagnosis = new Diagnosis(
            resultSet.getLong("ID"),
            resultSet.getString("PROBLEM"),
            resultSet.getString("ISSUE"),
            resultSet.getFloat("FEE")
        );
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      closeSqlConnection();
    }

    return diagnosis;
  }
}
