package edu.ncsu.csc.pages.employee.manager;

import edu.ncsu.csc.entity.*;
import edu.ncsu.csc.pages.AbstractPage;
import edu.ncsu.csc.pages.Page;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NewCarModel extends AbstractPage {

  private List<Part> availablePartList;
  private List<BasicServicePart> servicePartList = new ArrayList<>();
  private List<Maintenance> serviceList = new ArrayList<>();
  private User manager;

  NewCarModel(User manager) {
    this.manager = manager;
    this.availablePartList = getAvailablePartList();
    choices.add("Add Car");
    choices.add("Go Back");
  }

  @Override
  public void run() {
    System.out.println("#newCarModel");

    CarModel carModel = new CarModel();
    do {
      System.out.print("Enter make(Honda/Nissan/Toyota):");
      carModel.setMake(scanner.nextLine());
      System.out.print("Enter model:");
      carModel.setModel(scanner.nextLine());
      carModel.setYear(Long.parseLong(getInfo("Enter year:", MatchType.Number)));
      try {
        connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        preparedStatement = connection
            .prepareStatement("select ID from CAR_MODEL where MAKE=? AND MODEL=? AND YEAR=?");
        preparedStatement.setString(1, carModel.getMake());
        preparedStatement.setString(2, carModel.getModel());
        preparedStatement.setLong(3, carModel.getYear());
        resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
          System.out.println("This car model already exists.");
        } else {
          break;
        }
      } catch (SQLException e) {
        e.printStackTrace();
      } finally {
        closeSqlConnection();
      }
    } while (true);

    getServiceInfo(ServiceType.A);
    getServiceInfo(ServiceType.B);
    getServiceInfo(ServiceType.C);
    displayChoices();
    switch (getChoiceFromInput()) {
      case 1:
        newCarModel(carModel);
      case 2:
        Page managerLanding = new ManagerLanding(manager);
        managerLanding.run();
    }
  }

  private void getServiceInfo(ServiceType serviceType) {
    System.out.printf("Entering info for Service %s:\n", serviceType.toString());
    Maintenance maintenance = new Maintenance();
    maintenance.setServiceType(serviceType);
    maintenance.setMile(Long.parseLong(getInfo("Enter mile for service:", MatchType.Number)));
    maintenance.setMonth(Long.parseLong(getInfo("Enter month for service:", MatchType.Number)));
    getPartList(serviceType);
    serviceList.add(maintenance);
  }

  private void getPartList(ServiceType serviceType) {
    BasicServicePart basicServicePart = new BasicServicePart();
    basicServicePart.setName(serviceType.toString());
    System.out.println("available part id listed below:");
    for (Part part : availablePartList) {
      System.out.printf("Part id:%d, Name:%s\n", part.getId(), part.getName());
    }
    int partNumA = Integer.parseInt(getInfo("Enter total kinds of parts for service A:", MatchType.Number));
    for (int i = 0; i < partNumA; i++) {
      System.out.printf("Entering info for part #%d\n:", i);
      basicServicePart.setPartId(Long.parseLong(getInfo("Enter part id:", MatchType.Number)));
      basicServicePart.setQuantity(Long.parseLong(getInfo("Enter part quantity:", MatchType.Number)));
    }
    servicePartList.add(basicServicePart);
  }

  private List<Part> getAvailablePartList() {
    List<Part> partList = new ArrayList<>();
    try {
      connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
      preparedStatement = connection.prepareStatement("SELECT * FROM PART");
      resultSet = preparedStatement.executeQuery();
      while (resultSet.next()) {
//        partList.add(getPart());
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return partList;
  }

  private void newCarModel(CarModel carModel) {
    try {
      connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
      preparedStatement = connection.prepareStatement(
          "INSERT INTO CAR_MODEL VALUES (CAR_MODEL_ID_SEQ.nextval, ?, ?, ?)");
      preparedStatement.setString(1, carModel.getMake());
      preparedStatement.setString(2, carModel.getModel());
      preparedStatement.setLong(3, carModel.getYear());
      preparedStatement.executeUpdate();
      preparedStatement = connection.prepareStatement(
          "SELECT * FROM CAR_MODEL WHERE MAKE=? AND MODEL=? AND YEAR=?");
      preparedStatement.setString(1, carModel.getMake());
      preparedStatement.setString(2, carModel.getModel());
      preparedStatement.setLong(3, carModel.getYear());
      resultSet = preparedStatement.executeQuery();
      carModel.setId(resultSet.getLong("ID"));
      preparedStatement = connection.prepareStatement("INSERT INTO MAINTENANCE VALUES (?, ?, ?, ?)");
      for (Maintenance aServiceList : serviceList) {
        aServiceList.setCarModelId(carModel.getId());
        preparedStatement.setLong(1, aServiceList.getCarModelId());
        preparedStatement.setLong(2, aServiceList.getServiceType().ordinal());
        preparedStatement.setLong(3, aServiceList.getMile());
        preparedStatement.setLong(4, aServiceList.getMonth());
        preparedStatement.executeUpdate();
      }
      preparedStatement = connection.prepareStatement(
          "INSERT INTO BASIC_SERVICE_PART VALUES (?, ? ,? ,?)");
      for (BasicServicePart aServicePartList : servicePartList) {
        aServicePartList.setCarModelId(carModel.getId());
        preparedStatement.setLong(1, aServicePartList.getCarModelId());
        preparedStatement.setString(2, aServicePartList.getName());
        preparedStatement.setLong(3, aServicePartList.getPartId());
        preparedStatement.setLong(4, aServicePartList.getQuantity());
        preparedStatement.executeUpdate();
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      closeSqlConnection();
    }

  }
}
