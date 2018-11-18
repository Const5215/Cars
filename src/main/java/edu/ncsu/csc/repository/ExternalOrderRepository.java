package edu.ncsu.csc.repository;

import edu.ncsu.csc.entity.Order;
import edu.ncsu.csc.entity.OrderStatus;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ExternalOrderRepository extends AbstractRepository{

    public List<Order> getExternalOrder(long centerId) {
        List<Order> orderList = new ArrayList<>();
        String sql = "SELECT * FROM external_order WHERE center_id = ?";
        try{
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, String.valueOf(centerId));
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Order order = new Order();
                order.setId(resultSet.getLong("ID"));
                order.setPartId(resultSet.getLong("PART_ID"));
                order.setQuantity(resultSet.getInt("QUANTITY"));
                order.setTotal(resultSet.getFloat("TOTAL"));
                order.setOrderDate(resultSet.getDate("ORDER_DATE"));
                order.setExpectedDeliveryDate(resultSet.getDate("EXPECTED_DELIVERY_DATE"));
                order.setActualDeliveryDate(resultSet.getDate("ACTUAL_DELIVERY_DATE"));
                order.setStatus(resultSet.getInt("STATUS"));
                order.setDistributorId(resultSet.getLong("DISTRIBUTOR_ID"));
                order.setCenterId(resultSet.getLong("CENTER_ID"));
                orderList.add(order);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return orderList;
    }

    public int saveExternalOrder(Order externalOrder) {
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
            preparedStatement.setDate(7, new java.sql.Date(externalOrder.getExpectedDeliveryDate().getTime()));
            preparedStatement.setString(8, String.valueOf(externalOrder.getStatus()));

            return preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public Long lastExternalOrderId(Long centerId){
        String sql = "select id from (select id from EXTERNAL_ORDER  WHERE CENTER_ID = ? ORDER BY ID DESC) where ROWNUM = 1 ";
        try{
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, centerId);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getLong("ID");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;


    }
}
