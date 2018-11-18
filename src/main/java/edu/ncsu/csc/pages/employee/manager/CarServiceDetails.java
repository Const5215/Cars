package edu.ncsu.csc.pages.employee.manager;

import edu.ncsu.csc.entity.BasicService;
import edu.ncsu.csc.entity.BasicServicePart;
import edu.ncsu.csc.entity.CarModel;
import edu.ncsu.csc.entity.Maintenance;
import edu.ncsu.csc.entity.User;
import edu.ncsu.csc.pages.AbstractPage;
import edu.ncsu.csc.pages.Page;
import edu.ncsu.csc.repository.BasicServicePartRepository;
import edu.ncsu.csc.repository.CarModelRepository;
import edu.ncsu.csc.repository.MaintenanceDetailRepository;
import edu.ncsu.csc.repository.MaintenanceRepository;
import java.util.List;

public class CarServiceDetails extends AbstractPage {

  private User manager;

  CarServiceDetails(User manager) {
    this.manager = manager;
    choices.add("Go Back");
  }

  @Override
  public void run() {
    System.out.println("# Car Service Details");

    CarModelRepository carModelRepository = new CarModelRepository();
    MaintenanceRepository maintenanceRepository = new MaintenanceRepository();
    MaintenanceDetailRepository maintenanceDetailRepository = new MaintenanceDetailRepository();
    BasicServicePartRepository basicServicePartRepository = new BasicServicePartRepository();
    List<CarModel> carModels = carModelRepository.getAllCarModels();
    List<Maintenance> maintenances;
    List<BasicService> basicServices;
    BasicServicePart basicServicePart;

    for (CarModel carModel : carModels) {
      System.out.printf("%s %s\n", carModel.getMake(), carModel.getModel());
      maintenances = maintenanceRepository.getAllMaintenanceByCarModelId(carModel.getId());
      for (Maintenance maintenance : maintenances) {
        System.out
            .printf("Service %c Mileage: %d miles", 'A' + maintenance.getServiceType().ordinal(),
                maintenance.getMileage());
        basicServices = maintenanceDetailRepository
            .getAllBasicServicesByCarModelIdAndMaintenanceType(carModel.getId(),
                maintenance.getServiceType());
        for (BasicService basicService : basicServices) {
          basicServicePart = basicServicePartRepository
              .getBasicServicePartByBasicServiceIdAndCarModelId(basicService.getId(),
                  carModel.getId());
          System.out.printf("\tPart ID: %d\tQuantity: %d\n", basicServicePart.getPartId(),
              basicServicePart.getQuantity());
        }
      }
    }

    displayChoices();
    getChoiceFromInput();
    goBack();
  }

  private void goBack() {
    Page managerLanding = new ManagerLanding(manager);
    managerLanding.run();
  }

}
