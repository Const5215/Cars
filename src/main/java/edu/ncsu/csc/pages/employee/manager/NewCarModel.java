package edu.ncsu.csc.pages.employee.manager;

import edu.ncsu.csc.entity.CarModel;
import edu.ncsu.csc.entity.Maintenance;
import edu.ncsu.csc.entity.MaintenanceDetail;
import edu.ncsu.csc.entity.ServiceType;
import edu.ncsu.csc.entity.User;
import edu.ncsu.csc.pages.AbstractPage;
import edu.ncsu.csc.pages.Page;
import edu.ncsu.csc.repository.CarModelRepository;
import edu.ncsu.csc.repository.MaintenanceDetailRepository;
import java.util.ArrayList;
import java.util.List;

class NewCarModel extends AbstractPage {

  private CarModelRepository carModelRepository;
  private MaintenanceDetailRepository maintenanceDetailRepository;
  private User manager;
  private CarModel carModel;
  private List<MaintenanceDetail> maintenanceDetails;

  NewCarModel(User manager) {
    carModelRepository = new CarModelRepository();
    maintenanceDetailRepository = new MaintenanceDetailRepository();
    this.manager = manager;
    carModel = new CarModel();
    maintenanceDetails = new ArrayList<MaintenanceDetail>();
    choices.add("Add Car");
    choices.add("Go Back");
  }


  @Override
  public void run() {
    System.out.println("# New Car Model");

    while (true) {
      while (true) {
        System.out.print("Enter make (Honda/Nissan/Toyota): ");
        String make = scanner.nextLine();

        if (make.equals("Honda") || make.equals("Nissan") || make.equals("Toyota")) {
          carModel.setMake(make);
          break;
        } else {
          System.out.println("Make must be Honda, Nissan, or Toyota");
        }
      }

      System.out.print("Enter model: ");
      String model = scanner.nextLine();

      if (carModelRepository.getIdByMakeAndModel(carModel.getMake(), model) == null) {
        carModel.setModel(model);
        break;
      } else {
        System.out.println("This car model already exists");
      }
    }

    addMaintenanceDetail(ServiceType.ServiceA);
    addMaintenanceDetail(ServiceType.ServiceB);
    addMaintenanceDetail(ServiceType.ServiceC);

    displayChoices();
    switch (getChoiceFromInput()) {
      case 1:
        addCar();
      case 2:
        goBack();
    }
  }

  private void addCar() {
    carModelRepository.add(carModel);
    carModel.setId(carModelRepository.getIdByMakeAndModel(carModel.getMake(), carModel.getModel()));
    for (MaintenanceDetail maintenanceDetail : maintenanceDetails) {
      maintenanceDetail.setCarModelId(carModel.getId());
      maintenanceDetailRepository.add(maintenanceDetail);
    }
  }

  private void goBack() {
    Page managerLanding = new ManagerLanding(manager);
    managerLanding.run();
  }

  private void addMaintenanceDetail(ServiceType serviceType) {
    Maintenance maintenance = new Maintenance();

    while (true) {
      System.out.printf("Enter %s mileage: ", serviceType.toString());

      try {
        maintenance.setMileage(Integer.parseInt(scanner.nextLine()));
      } catch (NumberFormatException e) {
        System.out.println("Invalid mileage");
        continue;
      }

      break;
    }

    System.out
        .printf("Enter %s basic service IDs (white space separated): ", serviceType.toString());
    String[] ids = scanner.nextLine().split(" ");
    for (String id : ids) {
      Long basicServiceId;
      basicServiceId = Long.parseLong(id);
      maintenanceDetails.add(new MaintenanceDetail(null, serviceType, basicServiceId));
    }
  }
}
