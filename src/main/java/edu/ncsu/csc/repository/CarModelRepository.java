package edu.ncsu.csc.repository;

import edu.ncsu.csc.entity.CarModel;
import edu.ncsu.csc.pages.AbstractPage;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CarModelRepository extends AbstractPage {
  public void addCarModel(CarModel carModel) {
    try {
      connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
      preparedStatement = connection.prepareStatement(
          "INSERT INTO CAR_MODEL VALUES (CAR_MODEL_ID_SEQ.nextval, ?, ?)");
      preparedStatement.setString(1, carModel.getMake());
      preparedStatement.setString(2, carModel.getModel());
      preparedStatement.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      closeSqlConnection();
    }
  }

  CarModel getCarModelByCarModelId(long carModelId) {
    CarModel carModel = null;
    try {
      connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
      preparedStatement = connection.prepareStatement(
          "select * FROM CAR_MODEL WHERE ID=?");
      preparedStatement.setLong(1, carModelId);
      resultSet = preparedStatement.executeQuery();
      if (resultSet.next()) {
        carModel = new CarModel();
        carModel.setId(resultSet.getLong("ID"));
        carModel.setMake(resultSet.getString("MAKE"));
        carModel.setModel(resultSet.getString("MODEL"));
      } else {
        System.out.println("car model id not found.");
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      closeSqlConnection();
    }
    return carModel;
  }

  public long getCarModelIdByMakeAndModel(String make, String model) {
    long carModelId = -1;
    try {
      connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
      preparedStatement = connection.prepareStatement(
          "SELECT * FROM CAR_MODEL WHERE MAKE=? AND MODEL=?");
      preparedStatement.setString(1, make);
      preparedStatement.setString(2, model);
      resultSet = preparedStatement.executeQuery();
      if (resultSet.next()) {
        carModelId = resultSet.getLong("ID");
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      closeSqlConnection();
    }
    return carModelId;
  }

  public List<CarModel> getCarModelList() {
    List<CarModel> carModelList = new ArrayList<>();
    try {
      connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
      preparedStatement = connection.prepareStatement(
          "SELECT * FROM CAR_MODEL");
      resultSet = preparedStatement.executeQuery();
      while (resultSet.next()) {
        CarModel carModel = new CarModel();
        carModel.setId(resultSet.getLong("ID"));
        carModel.setMake(resultSet.getString("MAKE"));
        carModel.setModel(resultSet.getString("MODEL"));
        carModelList.add(carModel);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      closeSqlConnection();
    }
    return carModelList;
  }
}
