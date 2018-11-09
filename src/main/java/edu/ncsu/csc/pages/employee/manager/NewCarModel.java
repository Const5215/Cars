package edu.ncsu.csc.pages.employee.manager;

import edu.ncsu.csc.entity.*;
import edu.ncsu.csc.pages.AbstractPage;
import edu.ncsu.csc.pages.Page;
import edu.ncsu.csc.repository.BasicServicePartRepository;
import edu.ncsu.csc.repository.CarModelRepository;
import edu.ncsu.csc.repository.PartRepository;
import javafx.util.Pair;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

class NewCarModel extends AbstractPage {

  private List<Part> availablePartList;
  private List<Maintenance> serviceList = new ArrayList<>();
  private User manager;

  NewCarModel(User manager) {
    this.manager = manager;
    PartRepository partRepository = new PartRepository();
    this.availablePartList = partRepository.getAvailablePartList();
    choices.add("Add Car");
    choices.add("Go Back");
  }


  @Override
  public void run() {
    System.out.println("#newCarModel");

    CarModel carModel = getCarModel();
    List<Pair<Maintenance, List<BasicServicePart>>> serviceDetailList = getServiceDetailList();
    displayChoices();
    switch (getChoiceFromInput()) {
      case 1:
        addCar(carModel, serviceDetailList);
      case 2:
        goBack();
    }
  }

  private void addCar(CarModel carModel, List<Pair<Maintenance, List<BasicServicePart>>> serviceDetailList) {
    try {
      connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
      preparedStatement = connection.prepareStatement(
          "INSERT INTO CAR_MODEL VALUES (CAR_MODEL_ID_SEQ.nextval, ?, ?, ?)");
      preparedStatement.setString(1, carModel.getMake());
      preparedStatement.setString(2, carModel.getModel());
      preparedStatement.executeUpdate();
      CarModelRepository carModelRepository = new CarModelRepository();
      long carModelId = carModelRepository.getCarModelIdByMakeAndModel(carModel.getMake(), carModel.getModel());
      carModel.setId(carModelId);

      preparedStatement = connection.prepareStatement(
          "INSERT INTO MAINTENANCE VALUES (?, ? ,?)");
      for (Pair<Maintenance, List<BasicServicePart>> serviceDetail : serviceDetailList) {
        serviceDetail.getKey().setCarModelId(carModelId);
        preparedStatement.setLong(1, serviceDetail.getKey().getCarModelId());
        preparedStatement.setString(2, serviceDetail.getKey().getServiceType().toString());
        preparedStatement.setLong(3, serviceDetail.getKey().getMile());
        preparedStatement.executeUpdate();
        for (int i = 0; i < serviceDetail.getValue().size(); i++) {
          BasicServicePartRepository basicServicePartRepository = new BasicServicePartRepository();
          basicServicePartRepository.addBasicServicePart(serviceDetail.getValue().get(i));
        }
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      closeSqlConnection();
    }
  }

  private void goBack() {
    Page managerLanding = new ManagerLanding(manager);
    managerLanding.run();
  }

  private CarModel getCarModel() {
    CarModel carModel = new CarModel();
    do {
      System.out.print("Enter make(Honda/Nissan/Toyota):");
      carModel.setMake(scanner.nextLine());
      System.out.print("Enter model:");
      carModel.setModel(scanner.nextLine());
      getInfo("Enter year:", MatchType.Number);
      CarModelRepository carModelRepository = new CarModelRepository();
      if (carModelRepository.getCarModelIdByMakeAndModel(carModel.getMake(), carModel.getModel()) == -1) {
        break;
      }
      else {
        System.out.println("This car model already exists");
      }
    } while (true);
    return carModel;
  }

  private List<BasicServicePart> getBasicServicePartList() {
    List<BasicServicePart> basicServicePartList = new ArrayList<>();
    System.out.println("available part id listed below:");
    for (Part part : availablePartList) {
      System.out.printf("Part id:%d, Name:%s\n", part.getId(), part.getName());
    }
    int partNum = Integer.parseInt(
        getInfo("Enter total kinds of (parts)/(basic services) for service:", MatchType.Number));
    for (int i = 0; i < partNum; i++) {
      BasicServicePart basicServicePart = new BasicServicePart();
      System.out.printf("Entering info for part #%d\n:", i + 1);
      basicServicePart.setPartId(Long.parseLong(getInfo("Enter part id:", MatchType.Number)));
      basicServicePart.setQuantity(Long.parseLong(getInfo("Enter part quantity:", MatchType.Number)));
      basicServicePartList.add(basicServicePart);
    }
    return basicServicePartList;
  }

  private List<Pair<Maintenance, List<BasicServicePart>>> getServiceDetailList() {
    List<Pair<Maintenance, List<BasicServicePart>>> serviceDetailList = new ArrayList<>();
    for (int i = 0; i < 3; i++) {
      Maintenance maintenance = new Maintenance();
      ServiceType serviceType = ServiceType.values()[i];
      System.out.printf("Entering info for %s:\n", serviceType.toString());
      maintenance.setMile(Long.parseLong(getInfo("Enter mile for maintenance:", MatchType.Number)));
      getInfo("Enter month for maintenance:", MatchType.Number);
      List<BasicServicePart> basicServicePartList = getBasicServicePartList();
      Pair<Maintenance, List<BasicServicePart>> serviceDetail = new Pair<>(maintenance, basicServicePartList);
      serviceDetailList.add(serviceDetail);
    }
    return serviceDetailList;
  }

}
