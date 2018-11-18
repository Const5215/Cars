package edu.ncsu.csc.controller;

import edu.ncsu.csc.entity.BasicService;
import edu.ncsu.csc.entity.BasicServicePart;
import edu.ncsu.csc.entity.Car;
import edu.ncsu.csc.entity.Part;
import edu.ncsu.csc.entity.ServiceHistory;
import edu.ncsu.csc.entity.ServiceType;
import edu.ncsu.csc.pages.AbstractPage;
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

public class ServiceController extends AbstractPage {

  public List<BasicServicePart> getAllBasicServicePartsByServiceHistory(
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

  public List<BasicService> getAllBasicServiceByServiceHistory(ServiceHistory serviceHistory) {
    BasicServiceRepository basicServiceRepository = new BasicServiceRepository();
    MaintenanceDetailRepository maintenanceDetailRepository = new MaintenanceDetailRepository();
    RepairRepository repairRepository = new RepairRepository();
    CarRepository carRepository = new CarRepository();
    List<BasicService> basicServices = new ArrayList<BasicService>();
    Car car = carRepository.getCarByPlate(serviceHistory.getCarPlate());
    List<Long> basicServiceIds;

    if (serviceHistory.getServiceType() == ServiceType.Repair) {
      basicServiceIds = repairRepository
          .getBasicServiceIdsByDiagnosisId(serviceHistory.getDiagnosisId());
      for (Long basicServiceId : basicServiceIds) {
        basicServices.add(basicServiceRepository.getBasicServiceById(basicServiceId));
      }
    } else {
      basicServices = maintenanceDetailRepository
          .getAllBasicServicesByCarModelIdAndMaintenanceType(car.getCarModelId(),
              serviceHistory.getServiceType());
    }

    return basicServices;
  }

  public Float getTotalServiceCost(ServiceHistory serviceHistory) {
    BasicServicePartRepository basicServicePartRepository = new BasicServicePartRepository();
    CarRepository carRepository = new CarRepository();
    Float totalServiceCost = 0f;

    List<BasicService> basicServiceList = getAllBasicServiceByServiceHistory(serviceHistory);
    Car car = carRepository.getCarByPlate(serviceHistory.getCarPlate());
    for (BasicService basicService : basicServiceList) {
      BasicServicePart basicServicePart =
          basicServicePartRepository.getBasicServicePartByBasicServiceIdAndCarModelId(
              basicService.getId(), car.getCarModelId()
          );
      totalServiceCost += getBasicServiceCost(serviceHistory, basicServicePart);
    }

    return totalServiceCost;
  }

  public Float getBasicServiceCost(ServiceHistory serviceHistory, BasicServicePart basicServicePart) {
    return getPartCost(serviceHistory, basicServicePart) + getLaborCost(serviceHistory,
        basicServicePart);
  }

  public Float getPartCost(ServiceHistory serviceHistory, BasicServicePart basicServicePart) {
    PartRepository partRepository = new PartRepository();

    Part part = partRepository.getPartById(basicServicePart.getPartId());

    Date lastServiceDate = getLastServiceDate(serviceHistory, basicServicePart);
    if (lastServiceDate != null) {
      if (checkPartWarranty(serviceHistory.getEndTime(), lastServiceDate, part.getWarranty())) {
        //free service
        return 0f;
      }
      else {
        //invoice cost of parts and appropriate labor charge
        return part.getUnitPrice() * basicServicePart.getQuantity();
      }
    } else {
      //invoice only the cost of parts
      return part.getUnitPrice() * basicServicePart.getQuantity();
    }
  }

  public Float getLaborCost(ServiceHistory serviceHistory, BasicServicePart basicServicePart) {
    PartRepository partRepository = new PartRepository();
    BasicServiceRepository basicServiceRepository = new BasicServiceRepository();

    Part part = partRepository.getPartById(basicServicePart.getPartId());

    Date lastServiceDate = getLastServiceDate(serviceHistory, basicServicePart);
    if (lastServiceDate != null) {
      if (checkPartWarranty(serviceHistory.getEndTime(), lastServiceDate, part.getWarranty())) {
        //free service
        return 0f;
      }
      else {
        //invoice cost of parts and appropriate labor charge
        BasicService basicService = basicServiceRepository.getBasicServiceById(basicServicePart.getBasicServiceId());
        return (basicService.getChargeRate() == 0 ? 50 : 65) * basicService.getLaborHour();
      }
    } else {
      //invoice only the cost of parts
      return 0f;
    }
  }

  private Date getLastServiceDate(ServiceHistory targetServiceHistory, BasicServicePart targetBasicServicePart) {
    ServiceHistoryRepository serviceHistoryRepository = new ServiceHistoryRepository();
    Date lastServiceDate = null;

//    List<ServiceHistory> serviceHistoryList = serviceHistoryRepository.getServiceHistoriesByCustomerIdAndCarPlate(
//        targetServiceHistory.getCustomerId(), targetServiceHistory.getCarPlate()
//    );
//    for (ServiceHistory serviceHistory : serviceHistoryList) {
//      //skip later service and service without this part
//      if (!serviceHistory.getEndTime().before(targetServiceHistory.getEndTime()) ||
//          checkNotContainBasicService(serviceHistory, targetBasicServicePart) ) continue;
//      if (lastServiceDate == null || lastServiceDate.before(serviceHistory.getEndTime()))
//        lastServiceDate = serviceHistory.getEndTime();
//    }
    return lastServiceDate;
  }

  private boolean checkNotContainBasicService(ServiceHistory serviceHistory, BasicServicePart targetBasicServicePart) {
    List<BasicServicePart> basicServicePartList = getAllBasicServicePartsByServiceHistory(serviceHistory);
    for (BasicServicePart basicServicePart : basicServicePartList) {
      if (basicServicePart.getPartId().equals(targetBasicServicePart.getPartId())) return false;
    }
    return true;
  }


  private boolean checkPartWarranty(Date nowServiceDate, Date LastServiceDate, long warranty) {
    Calendar lastServiceCal = Calendar.getInstance();
    lastServiceCal.setTime(LastServiceDate);
    lastServiceCal.add(Calendar.MONTH, (int) warranty);
    return lastServiceCal.getTime().after(nowServiceDate);
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
          employeeRepository.getNameById(serviceHistory.getMechanicId()));
      System.out.println("Service Start Time: " + timeFormat.format(serviceHistory.getStartTime()));
      System.out.println("Service End Time: " + timeFormat.format(serviceHistory.getEndTime()));
      System.out.println("Service Status: " + serviceHistory.getServiceStatus().toString());
    }
  }

  public void printServiceHistory(ServiceHistory serviceHistory) {
    EmployeeRepository employeeRepository = new EmployeeRepository();

    String mechanicName = employeeRepository
        .getNameById(serviceHistory.getMechanicId());
    System.out.println("Service ID: " + serviceHistory.getId());
    System.out.println("Service Start Time: " + timeFormat.format(serviceHistory.getStartTime()));
    System.out.println("Service End Time: " + timeFormat.format(serviceHistory.getEndTime()));
    System.out.println("Licence Plate: " + serviceHistory.getCarPlate());
    System.out.println("Service Type: " + serviceHistory.getServiceType().toString());
    System.out.println("Mechanic Name:" + mechanicName);
    float totalServiceCost = printPartDetails(serviceHistory);
    System.out.println("Total Service Cost: " + totalServiceCost);
  }

  public float printPartDetails(ServiceHistory serviceHistory) {
    CarRepository carRepository = new CarRepository();
    BasicServicePartRepository basicServicePartRepository = new BasicServicePartRepository();
    PartRepository partRepository = new PartRepository();

    float totalServiceCost = 0;
    List<BasicService> basicServiceList = getAllBasicServiceByServiceHistory(serviceHistory);
    Car car = carRepository.getCarByPlate(serviceHistory.getCarPlate());

    System.out.println("Part\t\tQuantity\t\tPart charge\t\tLabor hour\t\tLabor charge");
    for (BasicService basicService : basicServiceList) {
      BasicServicePart basicServicePart = basicServicePartRepository
          .getBasicServicePartByBasicServiceIdAndCarModelId(
              basicService.getId(), car.getCarModelId()
          );
      Part part = partRepository.getPartById(basicServicePart.getPartId());
      float partCost = getPartCost(serviceHistory, basicServicePart);
      float laborCost = getLaborCost(serviceHistory, basicServicePart);
      System.out.printf("%s\t%d\t\t%f\t\t%f\t\t%f\n",
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

}
