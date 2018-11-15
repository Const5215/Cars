package edu.ncsu.csc.pages.employee.receptionist;

import edu.ncsu.csc.entity.*;
import edu.ncsu.csc.pages.AbstractPage;
import edu.ncsu.csc.pages.Page;
import edu.ncsu.csc.repository.CarModelRepository;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * RecRegisterCar class used for Receptionist to register car for customer
 * NOTICE: differ from {@link edu.ncsu.csc.pages.customer.RegisterCar}
 *
 */
public class RecRegisterCar extends AbstractPage {
  private User user;

  RecRegisterCar(User user) {
    this.user = user;
    choices.add("Register");
    choices.add("Cancel");
  }

  @Override
  public void run() {

    System.out.println("#Register Car by Receptionist");

    // ask to input and check email
    // if customer's email does not exist, re-input
    String cusEmail;
    Long customerId;
    do {
      System.out.print("Enter Customer's email address");
      cusEmail = scanner.nextLine().toLowerCase();
      Pattern pattern = Pattern
              .compile("^[\\w.%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$");
      Matcher matcher = pattern.matcher(cusEmail);

      if (!matcher.matches()) {
        System.out.println("Invalid email address");
        continue;
      }

      try {
        connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        preparedStatement = connection.prepareStatement("select ID from CUSTOMER where EMAIL=?");
        preparedStatement.setString(1, cusEmail);
        resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
          // customer exist, keep going
          customerId = resultSet.getLong("ID");
          break;
        } else {
          // customer not exist
          System.out.println("Customer with this email address does not exit, please check email address");
        }
      } catch (SQLException e) {
        e.printStackTrace();
      } finally {
        closeSqlConnection();
      }
    } while (true);

    System.out.println("Enter Licence plate(ABC-1234): ");
    String licencePlate = scanner.nextLine();

    Date purchaseDate = getADate("Enter Purchase date(MM/DD/YYYY): ");

    // ask to input and check if car make in database
    Long carModelId;
    CarModelRepository  carModelRepository = new CarModelRepository();
    String carMake;
    String carModel;
    do {
      System.out.println("Enter car make: ");
      carMake = scanner.nextLine();
      System.out.println("Enter car model: ");
      carModel = scanner.nextLine();

    } while (carModelRepository.getCarModelIdByMakeAndModel(carMake, carModel) == -1);
    carModelId = carModelRepository.getCarModelIdByMakeAndModel(carMake, carModel);

    Long carYear;
    System.out.println("Enter car year: ");
    carYear = scanner.nextLong();

//    Date lastServiceDate;
//    do {
//      System.out.println("Do you want to Enter last service date?(y/n)");
//      String choice = scanner.nextLine();
//      if (choice.equals("y") || choice.equals("Y")) {
//        lastServiceDate = getADate("Enter last Service Date(MM/DD/YYYY): ");
//        break;
//      } else if (choice.equals("n") || choice.equals("Y")) {
//        lastServiceDate = null;
//        break;
//      }
//    } while (true);

    displayChoices();
    switch (getChoiceFromInput()) {
      case 1:
        Car car = new Car();
        car.setPlate(licencePlate);
        car.setCustomerId(customerId);
        car.setCarModelId(carModelId);
        car.setYear(carYear);
        car.setPurchaseDate(purchaseDate);
//        car.setLastMileage(curMileage);
//        if (lastServiceDate == null) {
//          car.setLastServiceDate(null);
//          car.setLastServiceType(null);
//        } else {
//          car.setLastServiceDate(lastServiceDate);
//          car.setLastServiceType(null);
//        }

        register(car);
        break;
      case 2:
        break;
    }
    Page receptionistLanding = new ReceptionistLanding(user);
    receptionistLanding.run();
  }

  private Date getADate(String msg) {
    Date tempDate;
    do {

      System.out.println(msg);
      String tempStr = scanner.nextLine();

      SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
      simpleDateFormat.setLenient(false);

      try {
        tempDate = simpleDateFormat.parse(tempStr);
        break;
      } catch (ParseException e) {
        System.out.println("Invalid date format, please re-input");
      }
    } while (true);

    return tempDate;
  }

  private void register(Car car) {
    try {
      connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
      preparedStatement =
          connection.prepareStatement("insert into CAR values (?, ?, ?, ?, ?)");
      preparedStatement.setString(1, car.getPlate());
      preparedStatement.setLong(2, car.getCustomerId());
      preparedStatement.setLong(3, car.getCarModelId());
      preparedStatement.setLong(4, car.getYear());
      preparedStatement.setDate(5, new java.sql.Date(car.getPurchaseDate().getTime()));
//      preparedStatement.setLong(5, car.getLastMileage());
//      preparedStatement.setLong(6, car.getLastServiceType());
//      preparedStatement.setDate(7, new java.sql.Date(car.getLastServiceDate().getTime()));
      preparedStatement.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      System.out.println("Register Success.");
      closeSqlConnection();
    }
  }
}
