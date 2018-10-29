package edu.ncsu.csc.pages.employee.manager;

import edu.ncsu.csc.entity.Payroll;
import edu.ncsu.csc.entity.User;

import java.util.List;

public interface IPayrollRepository {

    List<Payroll> getPayroll(User employee);
}
