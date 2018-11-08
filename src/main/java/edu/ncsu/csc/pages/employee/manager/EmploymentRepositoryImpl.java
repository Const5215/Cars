package edu.ncsu.csc.pages.employee.manager;

import edu.ncsu.csc.entity.Employment;
import edu.ncsu.csc.entity.User;
import edu.ncsu.csc.pages.AbstractPage;
import edu.ncsu.csc.util.ResultSetUtils;

import java.sql.DriverManager;
import java.sql.SQLException;

public class EmploymentRepositoryImpl extends AbstractPage implements IEmploymentRepository {
    @Override
    public long getCenterId(User employee) {
        String sql = "SELECT center_id FROM employment WHERE employee_id = ?";
        String employeeId = employee.getId().toString();
        Employment employment = null;
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, employeeId);
            resultSet = preparedStatement.executeQuery();
            employment = (Employment) ResultSetUtils.convertToObject(resultSet, Employment.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (employment == null) return -1;
        return employment.getCenterId();
    }
}
