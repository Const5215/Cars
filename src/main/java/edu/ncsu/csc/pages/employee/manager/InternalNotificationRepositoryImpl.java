package edu.ncsu.csc.pages.employee.manager;

import edu.ncsu.csc.entity.InternalNotification;
import edu.ncsu.csc.pages.AbstractPage;
import edu.ncsu.csc.util.ResultSetUtils;

import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;

public class InternalNotificationRepositoryImpl extends AbstractPage implements IInternalNotificationRepository {

    @Override
    public List<InternalNotification> getInternalNotification(long orderId) {
        List<InternalNotification> internalNotification = new ArrayList<>();
        String sql = "SELECT * FROM internal_notification WHERE order_id = ?";
        try{
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, String.valueOf(orderId));
            resultSet = preparedStatement.executeQuery();
            internalNotification = (List<InternalNotification>) ResultSetUtils.convertToList(resultSet, InternalNotification.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return internalNotification;
    }
}
