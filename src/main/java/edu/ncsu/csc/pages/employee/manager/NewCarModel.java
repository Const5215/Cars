package edu.ncsu.csc.pages.employee.manager;

import edu.ncsu.csc.entity.*;
import edu.ncsu.csc.pages.AbstractPage;
import edu.ncsu.csc.pages.Page;
import edu.ncsu.csc.repository.BasicServicePartRepository;
import edu.ncsu.csc.repository.CarModelRepository;
import edu.ncsu.csc.repository.MaintenanceRepository;
import edu.ncsu.csc.repository.PartRepository;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;

class NewCarModel extends AbstractPage {

  private List<Maintenance> serviceList = new ArrayList<>();
  private User manager;

  NewCarModel(User manager) {
    this.manager = manager;
    choices.add("Add Car");
    choices.add("Go Back");
  }


  @Override
  public void run() {
    System.out.println("#newCarModel");

    CarModel carModel = getCarModel();
    List<Pair<Maintenance, List<BasicServicePart>>> serviceDetailList = getServiceDetailList(carModel);
    displayChoices();
    switch (getChoiceFromInput()) {
      case 1:
        addCar(carModel, serviceDetailList);
      case 2:
        goBack();
    }
  }

  private void addCar(CarModel carModel, List<Pair<Maintenance, List<BasicServicePart>>> serviceDetailList) {
      CarModelRepository carModelRepository = new CarModelRepository();
    carModelRepository.addCarModel(carModel);
      long carModelId = carModelRepository.getCarModelIdByMakeAndModel(carModel.getMake(), carModel.getModel());
      carModel.setId(carModelId);
      for (Pair<Maintenance, List<BasicServicePart>> serviceDetail : serviceDetailList) {
        serviceDetail.getKey().setCarModelId(carModelId);
        MaintenanceRepository maintenanceRepository = new MaintenanceRepository();
        maintenanceRepository.addMaintenance(serviceDetail.getKey());
        for (BasicServicePart basicServicePart : serviceDetail.getValue()) {
          BasicServicePartRepository basicServicePartRepository = new BasicServicePartRepository();
          basicServicePart.setBasicServiceId(
              basicServicePartRepository.getBasicServiceIdByPartId(basicServicePart.getPartId())
          );
          basicServicePart.setCarModelId(carModelId);
          basicServicePartRepository.addBasicServicePart(basicServicePart);
        }
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

  private List<BasicServicePart> getBasicServicePartList(CarModel carModel) {
    PartRepository partRepository = new PartRepository();
    List<Part> partList = partRepository.getPartListByMake(carModel.getMake());
    List<BasicServicePart> basicServicePartList = new ArrayList<>();
    System.out.println("available part id listed below:");
    for (Part part : partList) {
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

  private List<Pair<Maintenance, List<BasicServicePart>>> getServiceDetailList(CarModel carModel) {
    List<Pair<Maintenance, List<BasicServicePart>>> serviceDetailList = new ArrayList<>();
    for (int i = 0; i < 3; i++) {
      Maintenance maintenance = new Maintenance();
      ServiceType serviceType = ServiceType.values()[i];
      System.out.printf("Entering info for %s:\n", serviceType.toString());
      maintenance.setServiceType(serviceType);
      maintenance.setMile(Long.parseLong(getInfo("Enter mile for maintenance:", MatchType.Number)));
      getInfo("Enter month for maintenance:", MatchType.Number);
      List<BasicServicePart> basicServicePartList = getBasicServicePartList(carModel);
      Pair<Maintenance, List<BasicServicePart>> serviceDetail = new Pair<>(maintenance, basicServicePartList);
      serviceDetailList.add(serviceDetail);
    }
    return serviceDetailList;
  }

}
