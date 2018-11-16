package edu.ncsu.csc.repository;

import edu.ncsu.csc.entity.CarModel;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CarModelRepository extends AbstractRepository {

  public void add(CarModel carModel) {
    try {
      connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
      preparedStatement = connection
          .prepareStatement("insert into CAR_MODEL values (CAR_MODEL_ID_SEQ.nextval, ?, ?)");
      preparedStatement.setString(1, carModel.getMake());
      preparedStatement.setString(2, carModel.getModel());
      preparedStatement.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      closeSqlConnection();
    }
  }

  public CarModel getCarModelById(Long id) {
    CarModel carModel = null;

    try {
      connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
      preparedStatement = connection.prepareStatement("select * from CAR_MODEL where ID=?");
      preparedStatement.setLong(1, id);
      resultSet = preparedStatement.executeQuery();
      if (resultSet.next()) {
        carModel = new CarModel(
            resultSet.getLong("ID"),
            resultSet.getString("MAKE"),
            resultSet.getString("MODEL")
        );
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      closeSqlConnection();
    }

    return carModel;
  }

  public Long getIdByMakeAndModel(String make, String model) {
    Long id = null;

    try {
      connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
      preparedStatement = connection
          .prepareStatement("select * from CAR_MODEL where MAKE=? and MODEL=?");
      preparedStatement.setString(1, make);
      preparedStatement.setString(2, model);
      resultSet = preparedStatement.executeQuery();
      if (resultSet.next()) {
        id = resultSet.getLong("ID");
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      closeSqlConnection();
    }

    return id;
  }

  public List<CarModel> getAllCarModels() {
    List<CarModel> carModels = new ArrayList<CarModel>();

    try {
      connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
      preparedStatement = connection.prepareStatement("select * from CAR_MODEL");
      resultSet = preparedStatement.executeQuery();
      while (resultSet.next()) {
        carModels.add(new CarModel(
            resultSet.getLong("ID"),
            resultSet.getString("MAKE"),
            resultSet.getString("MODEL")
        ));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      closeSqlConnection();
    }

    return carModels;
  }
}
