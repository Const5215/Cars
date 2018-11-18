package edu.ncsu.csc.repository;

import edu.ncsu.csc.entity.ExternalOrder;
import edu.ncsu.csc.entity.OrderStatus;
import edu.ncsu.csc.pages.AbstractPage;

import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ExternalOrderRepositoryImpl extends AbstractPage implements IExternalOrderRepository {


    @Override
    public List<ExternalOrder> getExternalOrder(long centerId) {
        List<ExternalOrder> externalOrderList = new ArrayList<>();
        String sql = "SELECT * FROM external_order WHERE center_id = ?";
        try{
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, String.valueOf(centerId));
            resultSet = preparedStatement.executeQuery();
            externalOrderList = (List<ExternalOrder>) ResultSetUtils.convertToList(resultSet, ExternalOrder.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return externalOrderList;
    }

    @Override
    public int saveExternalOrder(ExternalOrder externalOrder) {
        String sql = "INSERT INTO external_order " +
                "(part_id, center_id, total, quantity , distributor_id, order_date, EXPECTED_DELIVERY_DATE, status) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try{
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, externalOrder.getPartId());
            preparedStatement.setLong(2, externalOrder.getCenterId());
            preparedStatement.setFloat(3, externalOrder.getTotal());
            preparedStatement.setString(4, String.valueOf(externalOrder.getQuantity()));
            preparedStatement.setString(5, String.valueOf(externalOrder.getDistributorId()));
            preparedStatement.setDate(6, new java.sql.Date(new Date().getTime()));
            preparedStatement.setDate(7, new java.sql.Date(externalOrder.getExpectDeliveryDate().getTime()));
            preparedStatement.setString(8, String.valueOf(externalOrder.getStatus()));

            return preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}
