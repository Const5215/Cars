package edu.ncsu.csc.repository;

import edu.ncsu.csc.entity.Car;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CarRepository extends AbstractRepository {

  public void add(Car car) {
    try {
      connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
      preparedStatement = connection.prepareStatement("insert into CAR values (?, ?, ?, ?, ?)");
      preparedStatement.setString(1, car.getPlate());
      preparedStatement.setLong(2, car.getCustomerId());
      preparedStatement.setLong(3, car.getCarModelId());
      preparedStatement.setInt(4, car.getYear());
      preparedStatement.setDate(5, new Date(car.getPurchaseDate().getTime()));
      preparedStatement.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      closeSqlConnection();
    }
  }

  public Car getCarByPlate(String plate) {
    Car car = null;

    try {
      connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
      preparedStatement = connection.prepareStatement("select * from CAR where PLATE=?");
      preparedStatement.setString(1, plate);
      resultSet = preparedStatement.executeQuery();
      if (resultSet.next()) {
        car = new Car(
            resultSet.getString("PLATE"),
            resultSet.getLong("CUSTOMER_ID"),
            resultSet.getLong("CAR_MODEL_ID"),
            resultSet.getInt("YEAR"),
            resultSet.getDate("PURCHASE_DATE"));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      closeSqlConnection();
    }

    return car;
  }

  public List<Car> getAllCarsByCustomerId(Long customerId) {
    List<Car> cars = new ArrayList<Car>();

    try {
      connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
      preparedStatement = connection.prepareStatement("select * from CAR where CUSTOMER_ID=?");
      preparedStatement.setLong(1, customerId);
      resultSet = preparedStatement.executeQuery();
      while (resultSet.next()) {
        cars.add(new Car(
            resultSet.getString("PLATE"),
            resultSet.getLong("CUSTOMER_ID"),
            resultSet.getLong("CAR_MODEL_ID"),
            resultSet.getInt("YEAR"),
            resultSet.getDate("PURCHASE_DATE")
        ));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      closeSqlConnection();
    }

    return cars;
  }
}
