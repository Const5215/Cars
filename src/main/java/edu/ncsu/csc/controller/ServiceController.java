package edu.ncsu.csc.controller;

import edu.ncsu.csc.entity.*;
import edu.ncsu.csc.pages.AbstractPage;
import edu.ncsu.csc.pages.Page;
import edu.ncsu.csc.pages.customer.ScheduleService;
import edu.ncsu.csc.repository.*;

import java.util.*;

import static java.lang.Integer.max;

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
    DiagnosisRepository diagnosisRepository = new DiagnosisRepository();
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
    if (serviceHistory.getServiceType().equals(ServiceType.Repair)) {
      totalServiceCost += diagnosisRepository.getDiagnosisById(serviceHistory.getDiagnosisId()).getFee();
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

    List<ServiceHistory> serviceHistoryList =
        serviceHistoryRepository.getServiceHistoriesByCarPlate(targetServiceHistory.getCarPlate());
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
    DiagnosisRepository diagnosisRepository = new DiagnosisRepository();

    String mechanicName = employeeRepository
        .getNameById(serviceHistory.getMechanicId());
    System.out.println("Service ID: " + serviceHistory.getId());
    System.out.println("Service Start Time: " + timeFormat.format(serviceHistory.getStartTime()));
    System.out.println("Service End Time: " + timeFormat.format(serviceHistory.getEndTime()));
    System.out.println("Licence Plate: " + serviceHistory.getCarPlate());
    System.out.println("Service Type: " + serviceHistory.getServiceType().toString());
    System.out.println("Mechanic Name:" + mechanicName);
    float totalServiceCost = printPartDetails(serviceHistory);
    if (serviceHistory.getServiceType().equals(ServiceType.Repair)) {
      System.out.println("Diagnosis Fee: " + diagnosisRepository.getDiagnosisById(serviceHistory.getDiagnosisId()).getFee());
    }
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

  public java.sql.Date getTomorrow(java.util.Date date) {
    Calendar cal = Calendar.getInstance();
    cal.setTime(date);
    cal.add(Calendar.DATE, 1);
    cal.set(Calendar.HOUR_OF_DAY, 8);
    cal.set(Calendar.MINUTE, 0);
    cal.set(Calendar.SECOND, 0);
    cal.set(Calendar.MILLISECOND, 0);
    return new java.sql.Date(cal.getTimeInMillis());
  }

  public java.sql.Date getBasicServiceDate(ServiceHistory serviceHistory, BasicServicePart basicServicePart) {
    InventoryRepository inventoryRepository = new InventoryRepository();
    OrderRepository orderRepository = new OrderRepository();
    PartRepository partRepository = new PartRepository();

    //first see if we can do without orders - both internal and external ones - by checking local inventory and ongoing orders
    //demand quantity is the number of parts in this request
    Integer demandQuantity = basicServicePart.getQuantity();
    Inventory localInventory = inventoryRepository.getInventoryByCenterIdAndPartId(
        serviceHistory.getCenterId(), basicServicePart.getPartId()
    );
    if (localInventory.getAvailableQuantity() >= demandQuantity) {
      //we have enough right now
      return getTomorrow(new Date());
    }

    Integer baseQuantity = localInventory.getAvailableQuantity();
    List<Order> orderList = orderRepository.getOrdersByToIdAndPartIdAndStatus(
        serviceHistory.getCenterId(), basicServicePart.getPartId(), ServiceStatus.Pending
    );

    java.sql.Date lastOrderDate = getTomorrow(new Date());
    for(Order order : orderList) {
      baseQuantity += order.getQuantity();
      lastOrderDate = getTomorrow(order.getExpectedDeliveryDate());
      if (baseQuantity >= demandQuantity) {
        //we have enough after a certain order arrives
        return lastOrderDate;
      }
    }

    //else place orders
    //first try internal order
    Inventory otherInventory = inventoryRepository.getInventoryByCenterIdAndPartId(
        3-serviceHistory.getCenterId(), basicServicePart.getPartId()
    );
    if (baseQuantity + otherInventory.getAvailableQuantity() >= demandQuantity) {
      Order internalOrder = new Order();
      Part part = partRepository.getPartById(basicServicePart.getPartId());

      internalOrder.setPartId(basicServicePart.getPartId());
      internalOrder.setQuantity(demandQuantity-baseQuantity);
      internalOrder.setTotal(part.getUnitPrice()*internalOrder.getQuantity());
      internalOrder.setFromId(3-serviceHistory.getCenterId());
      internalOrder.setToId(serviceHistory.getCenterId());
      internalOrder.setOrderDate(new Date());
      internalOrder.setExpectedDeliveryDate(getTomorrow(new Date()));
      internalOrder.setStatus(OrderStatus.Pending);
      internalOrder.setDeliveryOrder(false);
      orderRepository.add(internalOrder);
      return lastOrderDate;
    }
    else {
      //finally have external order
      Order externalOrder = new Order();
      demandQuantity -= (baseQuantity + otherInventory.getAvailableQuantity());
      Part part = partRepository.getPartById(basicServicePart.getPartId());
      Integer orderQuantity = max(demandQuantity, localInventory.getMinOrderQuantity());
      Date expectedDeliveryDate = new Date();
      Calendar cal = Calendar.getInstance();
      cal.setTime(expectedDeliveryDate);
      cal.add(Calendar.DATE, part.getDeliveryWindow());
      cal.set(Calendar.HOUR_OF_DAY, 8);
      cal.set(Calendar.MINUTE, 0);
      cal.set(Calendar.SECOND, 0);
      cal.set(Calendar.MILLISECOND, 0);
      expectedDeliveryDate = cal.getTime();

      externalOrder.setPartId(basicServicePart.getPartId());
      externalOrder.setQuantity(orderQuantity);
      externalOrder.setTotal(part.getUnitPrice() * externalOrder.getQuantity());
      externalOrder.setFromId(part.getDistributorId());
      externalOrder.setToId(serviceHistory.getCenterId());
      externalOrder.setOrderDate(new Date());
      externalOrder.setExpectedDeliveryDate(expectedDeliveryDate);
      externalOrder.setStatus(OrderStatus.Pending);
      externalOrder.setDeliveryOrder(true);
      orderRepository.add(externalOrder);

      return lastOrderDate.after(expectedDeliveryDate) ?
          lastOrderDate : new java.sql.Date(expectedDeliveryDate.getTime());
    }
  }

  private Integer[] initializeTimeSlot(Date chooseDate) {
    ServiceHistoryRepository serviceHistoryRepository = new ServiceHistoryRepository();
    Integer[] timeSlot = new Integer[22];
    List<ServiceHistory> serviceHistoryList =
        serviceHistoryRepository.getServiceHistoriesBetweenDate(
            new java.sql.Date(chooseDate.getTime()), getTomorrow(chooseDate)
        );
    for (ServiceHistory serviceHistory : serviceHistoryList) {
      Date baseTime = chooseDate;
      Calendar cal = Calendar.getInstance();
      cal.setTime(baseTime);
      for (int i = 0; i < 22; i++) {
        if (cal.getTime().before(serviceHistory.getStartTime())) continue;
        if (!cal.getTime().before(serviceHistory.getEndTime())) break;
        timeSlot[i] = serviceHistory.getServiceType().ordinal() + 1;
        cal.add(Calendar.MINUTE, 30);
      }
    }
    return timeSlot;
  }

  public List<Date> findCandidateRepairDate(float totalLaborHour) {
    List<Date> candidateDate = new ArrayList<Date>();
    //serviceLength indicate how many slot(30 min) will this service take
    Integer serviceLength = (int) Math.ceil(totalLaborHour*2);
    Date chooseDate = new Date();
    do{
      chooseDate = getTomorrow(chooseDate);
      Integer[] timeSlot = initializeTimeSlot(chooseDate);
      Calendar cal = Calendar.getInstance();
      cal.setTime(chooseDate);
      //check within a day
      for (int i = 0; i < 22 && candidateDate.size() < 2; i++) {
        boolean timeAvailable = true;
        for (int j = 0; j < serviceLength; j++) {
          if (i+j >= 22 || timeSlot[i+j] != 0) {
            timeAvailable = false;
            break;
          }
        }
        if (timeAvailable) {
          candidateDate.add(cal.getTime());
        }
        cal.add(Calendar.MINUTE, 30);
      }
    }while(candidateDate.size() < 2);

    return candidateDate;
  }

  private Float getTotalLaborHour(ServiceHistory serviceHistory) {
    BasicServiceRepository basicServiceRepository = new BasicServiceRepository();
    float totalLaborHour = 0f;
    List<BasicServicePart> basicServicePartList = getAllBasicServicePartsByServiceHistory(serviceHistory);
    for (BasicServicePart basicServicePart : basicServicePartList) {
      totalLaborHour += basicServiceRepository.getBasicServiceById(basicServicePart.getBasicServiceId()).getLaborHour();
    }
    return totalLaborHour;
  }

  private void addServiceHistory(Date startTime, float totalLaborHour, ServiceHistory serviceHistory) {
    ServiceHistoryRepository serviceHistoryRepository = new ServiceHistoryRepository();
    serviceHistory.setStartTime(startTime);
    Calendar cal = Calendar.getInstance();
    cal.setTime(startTime);
    cal.add(Calendar.MINUTE, (int) (totalLaborHour*60));
    serviceHistory.setEndTime(cal.getTime());
    serviceHistoryRepository.add(serviceHistory);
  }

  public void repairOnDate(ServiceHistory serviceHistory) {
    float totalLaborHour = getTotalLaborHour(serviceHistory);
    List<Date> candidateDate = findCandidateRepairDate(totalLaborHour);
    System.out.println("Candidate repair date #1: " + timeFormat.format(candidateDate.get(0)));
    System.out.println("Candidate repair date #2: " + timeFormat.format(candidateDate.get(1)));
    do {
      System.out.println("Enter your choice: (1/2)");
      String choice = scanner.nextLine();
      if (choice.equals("1")) {
        addServiceHistory(candidateDate.get(0), totalLaborHour, serviceHistory);
        break;
      }
      else if (choice.equals("2")) {
        addServiceHistory(candidateDate.get(1), totalLaborHour, serviceHistory);
        break;
      }
      else {
        System.out.println("Invalid input");
      }
    }while(true);
    updateInventory(serviceHistory);
  }

  private void updateInventory(ServiceHistory serviceHistory) {
    CustomerRepository customerRepository = new CustomerRepository();
    InventoryRepository inventoryRepository = new InventoryRepository();

    User customer = customerRepository.getCustomerById(serviceHistory.getCustomerId());
    List<BasicServicePart> basicServicePartList = getAllBasicServicePartsByServiceHistory(serviceHistory);
    for (BasicServicePart basicServicePart : basicServicePartList) {
      Inventory inventory = inventoryRepository.getInventoryByCenterIdAndPartId(
          getCustomerServiceCenterId(customer), basicServicePart.getPartId()
      );
      inventory.setAvailableQuantity(inventory.getAvailableQuantity()-basicServicePart.getQuantity());
      inventoryRepository.update(inventory);
    }
  }

}
