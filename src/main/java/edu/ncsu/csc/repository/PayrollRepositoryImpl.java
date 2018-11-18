package edu.ncsu.csc.repository;

import edu.ncsu.csc.entity.Payroll;
import edu.ncsu.csc.entity.User;
import edu.ncsu.csc.pages.AbstractPage;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PayrollRepositoryImpl extends AbstractPage implements IPayrollRepository {

    @Override
    public List<Payroll> getPayroll(User employee) {
        List<Payroll> payrollList = new ArrayList<>();
        String sql = "SELECT EMPLOYEE_ID, FROM_DATE, TO_DATE, PAYCHECK_DATE, UNIT, EARNING" +
                " FROM PAYROLL" +
                " WHERE EMPLOYEE_ID = ? ";
        String employeeId = employee.getId().toString();
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, employeeId);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Payroll payroll = new Payroll();
                payroll.setId(employee.getId());
                payroll.setFromDate(resultSet.getDate("from_date"));
                payroll.setToDate(resultSet.getDate("to_date"));
                payroll.setPaycheckDate(resultSet.getDate("paycheck_date"));
                payroll.setUnit(resultSet.getInt("unit"));
                payroll.setEarning(resultSet.getFloat("earning"));
                payrollList.add(payroll);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return payrollList;
    }


}
