package edu.ncsu.csc.controller;

import edu.ncsu.csc.entity.BasicService;
import edu.ncsu.csc.entity.BasicServicePart;
import edu.ncsu.csc.entity.Car;
import edu.ncsu.csc.entity.Part;
import edu.ncsu.csc.entity.ServiceHistory;
import edu.ncsu.csc.entity.ServiceType;
import edu.ncsu.csc.repository.BasicServicePartRepository;
import edu.ncsu.csc.repository.BasicServiceRepository;
import edu.ncsu.csc.repository.CarRepository;
import edu.ncsu.csc.repository.EmployeeRepository;
import edu.ncsu.csc.repository.MaintenanceDetailRepository;
import edu.ncsu.csc.repository.PartRepository;
import edu.ncsu.csc.repository.RepairRepository;
import edu.ncsu.csc.repository.ServiceHistoryRepository;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ServiceController {

  static public List<BasicServicePart> getAllBasicServicePartsByServiceHistory(
      ServiceHistory serviceHistory) {
    BasicServicePartRepository basicServicePartRepository = new BasicServicePartRepository();
    MaintenanceDetailRepository maintenanceDetailRepository = new MaintenanceDetailRepository();
    RepairRepository repairRepository = new RepairRepository();
    CarRepository carRepository = new CarRepository();
    List<BasicServicePart> basicServiceParts = new ArrayList<BasicServicePart>();
    Car car = carRepository.getCarByPlate(serviceHistory.getCarPlate());
    List<Long> basicServiceIds;

    if (serviceHistory.getServiceType() == ServiceType.Repair) {
      basicServiceIds = repairRepository
          .getBasicServiceIdsByDiagnosisId(serviceHistory.getDiagnosisId());
      for (Long basicServiceId : basicServiceIds) {
        basicServiceParts.add(basicServicePartRepository
            .getBasicServicePartByBasicServiceIdAndCarModelId(basicServiceId, car.getCarModelId()));
      }
    } else {
      List<BasicService> basicServices = maintenanceDetailRepository
          .getAllBasicServicesByCarModelIdAndMaintenanceType(car.getCarModelId(),
              serviceHistory.getServiceType());
      for (BasicService basicService : basicServices) {
        basicServiceParts.add(basicServicePartRepository
            .getBasicServicePartByBasicServiceIdAndCarModelId(basicService.getId(),
                car.getCarModelId()));
      }
    }

    return basicServiceParts;
  }

  static public Float getTotalServiceCost(ServiceHistory serviceHistory) {
    List<BasicServicePart> basicServiceParts = getAllBasicServicePartsByServiceHistory(
        serviceHistory);
    Float totalServiceCost = 0f;

    for (BasicServicePart basicServicePart : basicServiceParts) {
      totalServiceCost += getBasicServiceCost(serviceHistory, basicServicePart);
    }

    return totalServiceCost;
  }

  static private Float getBasicServiceCost(ServiceHistory serviceHistory,
      BasicServicePart basicServicePart) {
    BasicServiceRepository basicServiceRepository = new BasicServiceRepository();
    BasicServicePartRepository basicServicePartRepository = new BasicServicePartRepository();
    CarRepository carRepository = new CarRepository();
    BasicService basicService = basicServiceRepository
        .getBasicServiceById(basicServicePart.getBasicServiceId());
    Car car = carRepository.getCarByPlate(serviceHistory.getCarPlate());

    return getPartCost(serviceHistory, basicServicePart) + getLaborCost(serviceHistory,
        basicServicePart);
  }

  static private Float getLaborCost(ServiceHistory serviceHistory,
      BasicServicePart basicServicePart) {

    BasicServicePart basicServicePart =
        basicServicePartRepository.getBasicServicePartByBasicServiceIdAndCarModelId(
            basicServiceId, car.getCarModelId());
    PartRepository partRepository = new PartRepository();
    Part part = partRepository.getPartById(basicServicePart.getPartId());
    Date latestServiceDate = getLatestServiceHistoryByCustomerIdAndStartTime(
        serviceHistory.getCustomerId(), serviceHistory.getStartTime());
    if (latestServiceDate != null) {
      if (part.getWarranty() == 0 ||
          isWarrantyValid(part.getWarranty(), serviceHistory.getStartTime(),
              latestServiceDate)) {
        //invoice cost of parts and appropriate labor charge
        return (basicService.getChargeRate() == 0 ? 50 : 65) * basicService.getLaborHour();
      }
      //else free service
      else {
        return 0;
      }
    } else {
      //invoice only the cost of parts
      return 0;
    }
  }

  static private float getPartCost(ServiceHistory serviceHistory,
      BasicServicePart basicServicePart) {
    BasicServicePartRepository basicServicePartRepository = new BasicServicePartRepository();
    CarRepository carRepository = new CarRepository();

    Car car = carRepository.getCarByPlate(serviceHistory.getCarPlate());
    BasicServicePart basicServicePart =
        basicServicePartRepository.getBasicServicePartByBasicServiceIdAndCarModelId(
            basicServiceId, car.getCarModelId()
        );
    PartRepository partRepository = new PartRepository();
    Part part = partRepository.getPartById(basicServicePart.getPartId());
    Date latestServiceDate = getLatestServiceHistoryByCustomerIdAndStartTime(
        serviceHistory.getCustomerId(), serviceHistory.getStartTime());
    if (latestServiceDate != null) {
      if (part.getWarranty() == 0 ||
          isWarrantyValid(part.getWarranty(), serviceHistory.getStartTime(),
              latestServiceDate)) {
        //invoice cost of parts and appropriate labor charge
        return part.getUnitPrice() * basicServicePart.getQuantity();
      }
      //else free service
      else {
        return 0;
      }
    } else {
      //invoice only the cost of parts
      return part.getUnitPrice() * basicServicePart.getQuantity();
    }
  }

  private Boolean isWarrantyValid(BasicServicePart basicServicePart, Integer warranty) {
    PartRepository partRepository = new PartRepository();
    Part part = partRepository.getPartById(basicServicePart.getPartId());

    if (part.getWarranty() == 0) {
      return Boolean.FALSE;
    } else {

    }

    Calendar nowServiceCal = Calendar.getInstance();
    nowServiceCal.setTime(nowServiceDate);
    Calendar lastServiceCal = Calendar.getInstance();
    lastServiceCal.setTime(LastServiceDate);
    lastServiceCal.add(Calendar.MONTH, warranty);
    return lastServiceCal.getTime().getTime() <= nowServiceCal.getTime().getTime();
  }

  public void printServiceHistoryListWithoutDetail(List<ServiceHistory> serviceHistoryList) {
    System.out.printf("Service history: %d in total.\n", serviceHistoryList.size());
    EmployeeRepository employeeRepository = new EmployeeRepository();
    for (int i = 0; i < serviceHistoryList.size(); i++) {
      ServiceHistory serviceHistory = serviceHistoryList.get(i);
      System.out.printf("Service history #%d:\n", i);
      System.out.println("Service ID: " + serviceHistory.getId());
      System.out.println("Licence Plate: " + serviceHistory.getCarPlate());
      System.out.println("Service Type: " + serviceHistory.getServiceType().toString());
      System.out.println("Mechanic Name: " +
          employeeRepository.getEmployeeNameByEmployeeId(serviceHistory.getMechanicId()));
      System.out.println("Service Start Time: " + serviceHistory.getStartTime());
      System.out.println("Service End Time: " + serviceHistory.getEndTime());
      System.out.println("Service Status: " + serviceHistory.getServiceStatus().toString());
    }
  }

  public void printServiceHistory(ServiceHistory serviceHistory) {
    EmployeeRepository employeeRepository = new EmployeeRepository();

    String mechanicName = employeeRepository
        .getEmployeeNameByEmployeeId(serviceHistory.getMechanicId());
    System.out.println("Service ID: " + serviceHistory.getId());
    System.out.println("Service Start Time: " + serviceHistory.getStartTime());
    System.out.println("Service End Time: " + serviceHistory.getEndTime());
    System.out.println("Licence Plate: " + serviceHistory.getCarPlate());
    System.out.println("Service Type: " + serviceHistory.getServiceType().toString());
    System.out.println("Mechanic Name:" + mechanicName);
    float totalServiceCost = printPartDetails(serviceHistory);
    System.out.println("Total Service Cost: " + totalServiceCost);
  }

  public float printPartDetails(ServiceHistory serviceHistory) {
    CarRepository carRepository = new CarRepository();
    BasicServiceRepository basicServiceRepository = new BasicServiceRepository();
    BasicServicePartRepository basicServicePartRepository = new BasicServicePartRepository();
    PartRepository partRepository = new PartRepository();

    float totalServiceCost = 0;
    List<Long> basicServiceIdList = basicServiceRepository
        .getBasicServiceIdListByServiceHistory(serviceHistory);
    Car car = carRepository.getCarByPlate(serviceHistory.getCarPlate());
    ServiceHistoryRepository serviceHistoryRepository = new ServiceHistoryRepository();
    for (long basicServiceId : basicServiceIdList) {
      BasicServicePart basicServicePart = basicServicePartRepository
          .getBasicServicePartByBasicServiceIdAndCarModelId(
              basicServiceId, car.getCarModelId()
          );
      BasicService basicService = basicServiceRepository
          .getBasicServiceByBasicServiceId(basicServiceId);
      Part part = partRepository.getPartById(basicServicePart.getPartId());
      float partCost = serviceHistoryRepository.getPartCost(serviceHistory, basicServiceId);
      float laborCost = serviceHistoryRepository.getLaborCost(serviceHistory, basicServiceId);
      System.out.printf("Part: %s Quantity: %d Part charge: %f/Labor hour:%f Labor charge:%f\n",
          part.getName(),
          basicServicePart.getQuantity(),
          partCost,
          basicService.getLaborHour(),
          laborCost
      );
      totalServiceCost += partCost + laborCost;
    }
    return totalServiceCost;
  }

  public List<Long> getBasicServiceIdListByServiceHistory(ServiceHistory serviceHistory) {
    List<Long> basicServiceIdList;
    CarRepository carRepository = new CarRepository();
    Car car = carRepository.getCarByPlate(serviceHistory.getCarPlate());
    if (serviceHistory.getServiceType() == ServiceType.Repair) {
      RepairRepository repairRepository = new RepairRepository();
      basicServiceIdList = repairRepository
          .getBasicServiceIdsByDiagnosisId(serviceHistory.getDiagnosisId());
    } else {
      MaintenanceDetailRepository maintenanceDetailRepository = new MaintenanceDetailRepository();
      basicServiceIdList = maintenanceDetailRepository
          .getAllBasicServicesByCarModelIdAndMaintenanceType(
              car.getCarModelId(), serviceHistory.getServiceType()
          );
    }
    return basicServiceIdList;
  }
}
