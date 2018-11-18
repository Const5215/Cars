package edu.ncsu.csc.repository;

import edu.ncsu.csc.entity.Payroll;
import edu.ncsu.csc.entity.User;

import java.util.List;

public interface IPayrollRepository {

    List<Payroll> getPayroll(User employee);
}
