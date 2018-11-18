package edu.ncsu.csc.repository;

import edu.ncsu.csc.entity.Part;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PartRepository extends AbstractRepository {

  public Part getPartById(Long id) {
    Part part = null;

    try {
      connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
      preparedStatement = connection.prepareStatement("select * from PART where ID=?");
      preparedStatement.setLong(1, id);
      resultSet = preparedStatement.executeQuery();
      if (resultSet.next()) {
        part = new Part(
            resultSet.getLong("ID"),
            resultSet.getString("NAME"),
            resultSet.getString("MAKE"),
            resultSet.getFloat("UNIT_PRICE"),
            resultSet.getInt("WARRANTY"),
            resultSet.getInt("DELIVERY_WINDOW"),
            resultSet.getLong("DISTRIBUTOR_ID")
        );
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      closeSqlConnection();
    }

    return part;
  }

  public List<Part> getAllParts() {
    List<Part> parts = new ArrayList<Part>();

    try {
      connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
      preparedStatement = connection.prepareStatement("select * from PART");
      resultSet = preparedStatement.executeQuery();
      while (resultSet.next()) {
        parts.add(new Part(
            resultSet.getLong("ID"),
            resultSet.getString("NAME"),
            resultSet.getString("MAKE"),
            resultSet.getFloat("UNIT_PRICE"),
            resultSet.getInt("WARRANTY"),
            resultSet.getInt("DELIVERY_WINDOW"),
            resultSet.getLong("DISTRIBUTOR_ID")
        ));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      closeSqlConnection();
    }

    return parts;
  }

  public List<Part> getPartsByMake(String make) {
    List<Part> parts = new ArrayList<Part>();

    try {
      connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
      preparedStatement = connection.prepareStatement("select * from PART where MAKE=?");
      preparedStatement.setString(1, make);
      resultSet = preparedStatement.executeQuery();
      while (resultSet.next()) {
        parts.add(new Part(
            resultSet.getLong("ID"),
            resultSet.getString("NAME"),
            resultSet.getString("MAKE"),
            resultSet.getFloat("UNIT_PRICE"),
            resultSet.getInt("WARRANTY"),
            resultSet.getInt("DELIVERY_WINDOW"),
            resultSet.getLong("DISTRIBUTOR_ID")
        ));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      closeSqlConnection();
    }

    return parts;
  }
}
