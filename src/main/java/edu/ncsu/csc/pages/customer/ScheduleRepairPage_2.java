package edu.ncsu.csc.pages.customer;

import edu.ncsu.csc.controller.ServiceController;
import edu.ncsu.csc.entity.*;
import edu.ncsu.csc.pages.AbstractPage;
import edu.ncsu.csc.pages.Page;
import edu.ncsu.csc.repository.BasicServiceRepository;
import edu.ncsu.csc.repository.DiagnosisRepository;
import edu.ncsu.csc.repository.PartRepository;
import edu.ncsu.csc.repository.ServiceHistoryRepository;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static java.lang.StrictMath.ceil;


public class ScheduleRepairPage_2 extends AbstractPage {
  private User customer;
  private Car car;
  private Integer currentMileage;
  private User preferredMechanic;
  private ServiceHistory serviceHistory;
  private ServiceController serviceController;
  private DiagnosisRepository diagnosisRepository;
  private PartRepository partRepository;
  private ServiceHistoryRepository serviceHistoryRepository;

  public ScheduleRepairPage_2(User customer, Car car, Integer currentMileage, User preferredMechanic, ServiceHistory serviceHistory) {
    this.customer = customer;
    this.car = car;
    this.currentMileage = currentMileage;
    this.preferredMechanic = preferredMechanic;
    this.serviceHistory = serviceHistory;
    this.serviceController = new ServiceController();
    this.diagnosisRepository = new DiagnosisRepository();
    this.partRepository = new PartRepository();
    this.serviceHistoryRepository = new ServiceHistoryRepository();

    choices.add("Repair on Date");
    choices.add("Go Back");
  }

  @Override
  public void run() {
    System.out.println("#scheduleRepair(Page 2)");

    printDiagnosisReport();

    displayChoices();
    switch (getChoiceFromInput()) {
      case 1:
        scheduleRepair();
        break;
      case 2:
        goBack();
    }
  }

  private void scheduleRepair() {
    serviceController.repairOnDate(serviceHistory);
    Page scheduleService = new ScheduleService(customer);
    scheduleService.run();
  }

  private void printDiagnosisReport() {
    Diagnosis diagnosis = diagnosisRepository.getDiagnosisById(serviceHistory.getDiagnosisId());
    List<BasicServicePart> basicServicePartList = serviceController.getAllBasicServicePartsByServiceHistory(serviceHistory);

    System.out.println("Diagnosis Report: ");
    System.out.println("Problem: " + diagnosis.getProblem());
    System.out.println("Issue: " + diagnosis.getIssue());
    System.out.println("Diagnosis fee: " + diagnosis.getFee());
    System.out.println("Parts needed: ");
    for (BasicServicePart basicServicePart : basicServicePartList) {
      System.out.printf("Part Name: %s; Quantity: %d\n",
          partRepository.getPartById(basicServicePart.getPartId()).getName(), basicServicePart.getQuantity());
    }
  }

  private void goBack() {
    Page scheduleRepairPage_1 = new ScheduleRepairPage_1(customer, car, currentMileage, preferredMechanic);
    scheduleRepairPage_1.run();
  }

}
