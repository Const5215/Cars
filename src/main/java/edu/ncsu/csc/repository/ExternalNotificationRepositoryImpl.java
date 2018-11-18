package edu.ncsu.csc.repository;

import edu.ncsu.csc.entity.ExternalNotification;
import edu.ncsu.csc.pages.AbstractPage;
import edu.ncsu.csc.util.ResultSetUtils;

import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;

public class ExternalNotificationRepositoryImpl extends AbstractPage implements IExternalNotificationRepository {
    @Override
    public List<ExternalNotification> getExternalNotification(long orderId) {
        List<ExternalNotification> externaNotificationRepositoryList = new ArrayList<>();
        String sql = "SELECT * FROM external_notification WHERE order_id = ? ";
        try{
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, String.valueOf(orderId));
            resultSet = preparedStatement.executeQuery();
            externaNotificationRepositoryList = (List<ExternalNotification>) ResultSetUtils.convertToList(resultSet, ExternalNotification.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return externaNotificationRepositoryList;
    }
}
