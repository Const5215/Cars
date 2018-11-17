package edu.ncsu.csc.controller;

import edu.ncsu.csc.entity.*;
import edu.ncsu.csc.pages.AbstractPage;
import edu.ncsu.csc.repository.*;

import java.util.*;

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

    List<ServiceHistory> serviceHistoryList = serviceHistoryRepository.getServiceHistoriesByCustomerIdAndCarPlate(
        targetServiceHistory.getCustomerId(), targetServiceHistory.getCarPlate()
    );
    for (ServiceHistory serviceHistory : serviceHistoryList) {
      //skip later service and service without this part
      if (!serviceHistory.getEndTime().before(targetServiceHistory.getEndTime()) ||
          checkNotContainBasicService(serviceHistory, targetBasicServicePart) ) continue;
      if (lastServiceDate == null || lastServiceDate.before(serviceHistory.getEndTime()))
        lastServiceDate = serviceHistory.getEndTime();
    }
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

  public long getCustomerServiceCenterId(User customer) {
    String[] addressParts = customer.getAddress().split(" ");
    String post = addressParts[addressParts.length-1].substring(0,2);
    if (post.equals("27")) {
      return 1;
    } else if (post.equals("28")) {
      return 2;
    } else {
      System.out.println("Error: do not find customer service center");
      return -1;
    }
  }

  public User getRandomMechanicByCenter(Long centerId) {
    EmploymentRepository employmentRepository = new EmploymentRepository();
    EmployeeRepository employeeRepository = new EmployeeRepository();

    List<Long> mechanicIdList = employmentRepository.getMechanicsByCenterId(centerId);
    long pickId = mechanicIdList.get((int) (new Random().nextInt(mechanicIdList.size()-1)));
    return employeeRepository.getEmployeeById(pickId);
  }

  public java.sql.Date getTomorrow() {
    Calendar cal = Calendar.getInstance();
    cal.add(Calendar.DAY_OF_MONTH, 1);
    cal.set(Calendar.HOUR_OF_DAY, 8);
    cal.set(Calendar.MINUTE, 0);
    cal.set(Calendar.SECOND, 0);
    cal.set(Calendar.MILLISECOND, 0);
    return new java.sql.Date(cal.getTimeInMillis());
  }

  public java.sql.Date getBasicServiceDate(ServiceHistory serviceHistory, BasicServicePart basicServicePart) {
    java.sql.Date basicServiceDate;
    InventoryRepository inventoryRepository = new InventoryRepository();

    //first see if we can do without orders - both internal and external ones - by checking local inventory and ongoing orders
    basicServiceDate = getNoOrderDate(serviceHistory, basicServicePart);
    if (!basicServiceDate.equals(null)) {
      return basicServiceDate;
    }

    //else place orders
    Inventory otherInventory = inventoryRepository.getInventoryByCenterIdAndPartId(
        3-serviceHistory.getCenterId(), basicServicePart.getPartId()
    );


    return basicServiceDate;
  }

  private java.sql.Date getNoOrderDate(ServiceHistory serviceHistory, BasicServicePart basicServicePart) {
    InventoryRepository inventoryRepository = new InventoryRepository();
    OrderRepository orderRepository = new OrderRepository();

    //demand quantity is this request plus all unfinished request concerning this part
    Integer demandQuantity = getDemandQuantity(serviceHistory, basicServicePart);
    Inventory localInventory = inventoryRepository.getInventoryByCenterIdAndPartId(
        serviceHistory.getCenterId(), basicServicePart.getPartId()
    );
    if (localInventory.getAvailableQuantity() >= demandQuantity) {
      //we have enough right now
      localInventory.setAvailableQuantity(
          localInventory.getAvailableQuantity() - basicServicePart.getQuantity()
      );
      inventoryRepository.update(localInventory);
      return getTomorrow();
    }

    Integer baseQuantity = localInventory.getAvailableQuantity();
    List<Order> orderList = orderRepository.getOrdersByToIdAndPartIdAndStatus(
        serviceHistory.getCenterId(), basicServicePart.getPartId(), ServiceStatus.Pending
    );
    for(Order order : orderList) {
      baseQuantity += order.getQuantity();
      if (baseQuantity >= demandQuantity) {
        //we have enough after a certain order arrives
        localInventory.setAvailableQuantity(0);
        inventoryRepository.update(localInventory);

      }
    }
    return null;
  }

  private Integer getDemandQuantity(ServiceHistory serviceHistory, BasicServicePart basicServicePart) {
    Integer demandQuantity = basicServicePart.getQuantity();
    //not finished yet
    return demandQuantity;
  }

}
