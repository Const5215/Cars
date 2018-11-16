package edu.ncsu.csc.pages.customer;

import edu.ncsu.csc.entity.Diagnosis;
import edu.ncsu.csc.entity.ServiceHistory;
import edu.ncsu.csc.entity.User;
import edu.ncsu.csc.pages.AbstractPage;
import edu.ncsu.csc.pages.Page;
import edu.ncsu.csc.repository.DiagnosisRepository;
import edu.ncsu.csc.repository.RepairHistoryRepository;
import edu.ncsu.csc.repository.ServiceHistoryRepository;
import java.util.Date;
import java.util.List;

public class ViewServiceHistory extends AbstractPage {


  private User customer;

  ViewServiceHistory(User customer) {
    this.customer = customer;
    choices.add("Go Back");
  }

  @Override
  public void run() {
    System.out.println("# View Service History");

    ServiceHistoryRepository serviceHistoryRepository = new ServiceHistoryRepository();
    List<ServiceHistory> serviceHistories = serviceHistoryRepository
        .getServiceHistoriesByCustomerId(customer.getId());
    System.out.println(
        "Service ID\tLicense Plate\tMechanic ID\tService Start Time\tService End Time\tService Status\tService Type\n");
    Date current = new Date();

    for (ServiceHistory serviceHistory : serviceHistories) {
      System.out.printf("%d\t%s\t%d\t%s\t%s\t%s\t%s\n",
          serviceHistory.getId(),
          serviceHistory.getCarPlate(),
          serviceHistory.getMechanicId(),
          timeFormat.format(serviceHistory.getStartTime()),
          timeFormat.format(serviceHistory.getEndTime()),
          serviceHistory.getServiceStatus().toString(),
          serviceHistory.getServiceType().toString());
    }

    MaintenanceHistoryRepository maintenanceHistoryRepository = new MaintenanceHistoryRepository();
    RepairHistoryRepository repairHistoryRepository = new RepairHistoryRepository();
    DiagnosisRepository diagnosisRepository = new DiagnosisRepository();
    List<MaintenanceHistory> maintenanceHistories = maintenanceHistoryRepository
        .getAllMaintenanceHistoriesByCustomerId(customer.getId());
    List<RepairHistory> repairHistories = repairHistoryRepository
        .getAllRepairHistoryByCustomerId(customer.getId());
    Diagnosis diagnosis;

    System.out.println("Maintenance History");
    System.out.println(
        "Service ID\tLicense Plate\tMechanic ID\tService Start Time\tService End Time\tService Status\tService Type\n");

    for (MaintenanceHistory maintenanceHistory : maintenanceHistories) {
      System.out.printf("%d\t%s\t%d\t%s\t%s\t%s\t%c\n",
          maintenanceHistory.getId(),
          maintenanceHistory.getCarPlate(),
          maintenanceHistory.getMechanicId(),
          timeFormat.format(maintenanceHistory.getStartTime()),
          timeFormat.format(maintenanceHistory.getEndTime()),
          current.before(maintenanceHistory.getStartTime()) ? "Pending"
              : (current.after(maintenanceHistory.getEndTime()) ? "Complete" : "Ongoing"),
          'A' + maintenanceHistory.getMaintenanceType().ordinal());
    }

    System.out.println("Repair History");
    System.out.println(
        "Service ID\tLicense Plate\tMechanic ID\tService Start Time\tService End Time\tService Status\tProblem\n");

    for (RepairHistory repairHistory : repairHistories) {
      diagnosis = diagnosisRepository.getDiagnosisById(repairHistory.getDiagnosisId());
      System.out.printf("%d\t%s\t%d\t%s\t%s\t%s\t%s\n",
          repairHistory.getId(),
          repairHistory.getCarPlate(),
          repairHistory.getMechanicId(),
          timeFormat.format(repairHistory.getStartTime()),
          timeFormat.format(repairHistory.getEndTime()),
          current.before(repairHistory.getStartTime()) ? "Pending"
              : (current.after(repairHistory.getEndTime()) ? "Complete" : "Ongoing"),
          diagnosis.getProblem());
    }

    displayChoices();
    getChoiceFromInput();
    goBack();
  }

  private void goBack() {
    Page customerService = new Service(customer);
    customerService.run();
  }
}
