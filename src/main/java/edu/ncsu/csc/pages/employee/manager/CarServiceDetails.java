package edu.ncsu.csc.pages.employee.manager;

import edu.ncsu.csc.entity.*;
import edu.ncsu.csc.pages.AbstractPage;
import edu.ncsu.csc.pages.Page;
import edu.ncsu.csc.repository.*;

import java.util.List;

public class CarServiceDetails extends AbstractPage {
  private User manager;

  CarServiceDetails(User manager) {
    this.manager = manager;
    choices.add("Go Back");
  }

  @Override
  public void run() {
    System.out.println("#carServiceDetails");

    printCarServiceDetails();
    displayChoices();
    getChoiceFromInput();
    goBack();
  }

  private void printCarServiceDetails() {
    CarModelRepository carModelRepository = new CarModelRepository();
    MaintenanceRepository maintenanceRepository = new MaintenanceRepository();
    ServiceDetailRepository serviceDetailRepository = new ServiceDetailRepository();
    BasicServicePartRepository basicServicePartRepository = new BasicServicePartRepository();
    PartRepository partRepository = new PartRepository();
    List<CarModel> carModelList = carModelRepository.getCarModelList();
    System.out.printf("%d Car Model recorded in total.\n", carModelList.size());
    for (int i = 0; i < carModelList.size(); i++) {
      System.out.printf("Details for car model #%d:\n", i);
      CarModel carModel = carModelList.get(i);
      System.out.println("Make: " + carModel.getMake());
      System.out.println("Model: " + carModel.getModel());
      List<Maintenance> maintenanceList = maintenanceRepository.getMaintenanceListByCarModelId(carModel.getId());
      for (Maintenance maintenance : maintenanceList) {
        System.out.printf("%s details:\n", maintenance.getServiceType().toString());
        System.out.println("Mile: " + maintenance.getMile());
        List<BasicService> basicServiceList = serviceDetailRepository.getBasicServiceListByMakeAndModelAndMaintenanceType(
            carModel.getMake(), carModel.getModel(), maintenance.getServiceType()
        );
        for (BasicService basicService : basicServiceList) {
          BasicServicePart basicServicePart = basicServicePartRepository.getBasicServicePartByBasicServiceIdAndCarModelId(
              basicService.getId(), carModel.getId());
          Part part = partRepository.getPartByPartId(basicServicePart.getPartId());
          System.out.printf("Part Name: %s Quantity: %d\n", part.getName(), basicServicePart.getQuantity());
        }
      }
    }
  }

  private void goBack() {
    Page managerLanding = new ManagerLanding(manager);
    managerLanding.run();
  }

}
