package edu.ncsu.csc.controller;

import edu.ncsu.csc.entity.BasicService;
import edu.ncsu.csc.entity.BasicServicePart;
import edu.ncsu.csc.entity.Employment;
import edu.ncsu.csc.entity.Payroll;
import edu.ncsu.csc.entity.Role;
import edu.ncsu.csc.entity.ServiceHistory;
import edu.ncsu.csc.repository.BasicServiceRepository;
import edu.ncsu.csc.repository.ServiceHistoryRepository;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class PayrollController {

  static public List<Payroll> getAllPayrollsByEmployment(Employment employment) {
    return employment.getPosition() == Role.Mechanic ? getAllHourlyPayrollsByEmployment(employment)
        : getAllMonthlyPayrollsByEmployment(employment);
  }

  static private List<Payroll> getAllMonthlyPayrollsByEmployment(Employment employment) {
    List<Payroll> payrolls = new ArrayList<Payroll>();
    Calendar startDate = Calendar.getInstance();
    Calendar endDate = Calendar.getInstance();
    startDate.setTime(employment.getStartDate());
    endDate.setTime(employment.getStartDate());

    while (startDate.before(new Date())) {
      if (startDate.get(Calendar.DAY_OF_MONTH) >= 15) {
        endDate.add(Calendar.MONTH, 1);
        endDate.set(Calendar.DAY_OF_MONTH, 1);
      } else {
        endDate.set(Calendar.DAY_OF_MONTH, 15);
      }

      if (endDate.before(new Date())) {
        payrolls.add(new Payroll(
            employment.getEmployeeId(),
            startDate.getTime(),
            endDate.getTime(),
            endDate.getTime(),
            (endDate.getTime().getTime() - startDate.getTime().getTime()) / 86400f,
            employment.getCompensation() / 2
        ));
      }

      startDate.set(Calendar.MONTH, endDate.get(Calendar.MONTH));
      startDate.set(Calendar.DAY_OF_MONTH, endDate.get(Calendar.DAY_OF_MONTH));
    }

    return payrolls;
  }

  static private List<Payroll> getAllHourlyPayrollsByEmployment(Employment employment) {
    ServiceHistoryRepository serviceHistoryRepository = new ServiceHistoryRepository();
    BasicServiceRepository basicServiceRepository = new BasicServiceRepository();
    List<Payroll> payrolls = new ArrayList<Payroll>();
    Calendar startDate = Calendar.getInstance();
    Calendar endDate = Calendar.getInstance();
    startDate.setTime(employment.getStartDate());
    endDate.setTime(employment.getStartDate());

    while (startDate.before(new Date())) {
      if (startDate.get(Calendar.DAY_OF_MONTH) >= 15) {
        endDate.add(Calendar.MONTH, 1);
        endDate.set(Calendar.DAY_OF_MONTH, 1);
      } else {
        endDate.set(Calendar.DAY_OF_MONTH, 15);
      }

      if (endDate.before(new Date())) {
        List<ServiceHistory> serviceHistories = serviceHistoryRepository
            .getAllServiceHistoriesByMechanicIdAndStartTimeAndEndTime(employment.getCenterId(),
                startDate.getTime(), endDate.getTime());
        List<BasicServicePart> basicServiceParts = new ArrayList<BasicServicePart>();
        Float unit = 0f;

        for (ServiceHistory serviceHistory : serviceHistories) {
          basicServiceParts
              .addAll(ServiceController.getAllBasicServicePartsByServiceHistory(serviceHistory));
        }

        for (BasicServicePart basicServicePart : basicServiceParts) {
          BasicService basicService = basicServiceRepository
              .getBasicServiceById(basicServicePart.getBasicServiceId());
          unit += basicService.getLaborHour();
        }

        payrolls.add(new Payroll(
            employment.getEmployeeId(),
            startDate.getTime(),
            endDate.getTime(),
            endDate.getTime(),
            unit,
            unit * employment.getCompensation()
        ));
      }

      startDate.set(Calendar.MONTH, endDate.get(Calendar.MONTH));
      startDate.set(Calendar.DAY_OF_MONTH, endDate.get(Calendar.DAY_OF_MONTH));
    }

    return payrolls;
  }
}
