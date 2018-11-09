package edu.ncsu.csc.repository;

import edu.ncsu.csc.entity.Car;
import edu.ncsu.csc.entity.CarModel;
import edu.ncsu.csc.pages.AbstractPage;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CarRepository extends AbstractPage {
  private CarModelRepository carModelRepository;

  public CarRepository() {
    this.carModelRepository = new CarModelRepository();
  }

  public long getCustomerIdByCarPlate(String carPlate) {
    long customerId = -1;
    try {
      connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
      preparedStatement = connection.prepareStatement("SELECT * FROM CAR WHERE PLATE=?");
      preparedStatement.setString(1, carPlate);
      resultSet = preparedStatement.executeQuery();
      if (resultSet.next()) {
        customerId = resultSet.getLong("CUSTOMER_ID");
      } else {
        System.out.println("car plate not found.");
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      closeSqlConnection();
    }
    return customerId;
  }

  public List<Car> getCarListByCustomerId(Long customerId) {
    List<Car> carList = new ArrayList<>();
    try {
      connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
      preparedStatement = connection.prepareStatement("SELECT * FROM CAR WHERE CUSTOMER_ID=?");
      preparedStatement.setLong(1, customerId);
      resultSet = preparedStatement.executeQuery();
      while (resultSet.next()) {
        Car car = new Car();
        car.setPlate(resultSet.getString("PLATE"));
        car.setCustomerId(resultSet.getLong("CUSTOMER_ID"));
        car.setCarModelId(resultSet.getLong("CAR_MODEL_ID"));
        car.setYear(resultSet.getLong("YEAR"));
        car.setPurchaseDate(resultSet.getDate("PURCHASE_DATE"));
        carList.add(car);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      closeSqlConnection();
    }
    return carList;
  }

  public void printCarList(List<Car> carList) {
    for (Car car : carList) {
      System.out.println("Licence Plate: " + car.getPlate());
      System.out.println("Purchase Date: " + car.getPurchaseDate());
      CarModel carModel = carModelRepository.getCarModelByCarModelId(car.getCarModelId());
      System.out.println("Make: " + carModel.getMake());
      System.out.println("Model: " + carModel.getModel());
      System.out.println("Year: " + car.getYear());
    }
  }
}
