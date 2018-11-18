package edu.ncsu.csc.repository;

import edu.ncsu.csc.entity.Order;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;

public class InternalOrderRepository extends AbstractRepository {

    public List<Order> getInternalOrder(long centerId) {
        List<Order> orderList = new ArrayList<>();
        String sql = "SELECT * FROM internal_order WHERE to_id = ?";
        try{
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, centerId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Order order = new Order();
                order.setId(resultSet.getLong("ID"));
                order.setPartId(resultSet.getLong("PART_ID"));
                order.setQuantity(resultSet.getInt("QUANTITY"));
                order.setTotal(resultSet.getFloat("TOTAL"));
                order.setOrderDate(resultSet.getDate("ORDER_DATE"));
                order.setExpectedDeliveryDate(resultSet.getDate("EXPECTED_DELIVERY_DATE"));
                order.setActualDeliveryDate(resultSet.getDate("ACTUAL_DELIVERY_DATE"));
                order.setStatus(resultSet.getInt("STATUS"));
                order.setFromId(resultSet.getLong("FROM_ID"));
                order.setToId(resultSet.getLong("TO_ID"));
                orderList.add(order);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return orderList;
    }

    public int saveInternalOrder(Order internalOrder) {
        String sql = "INSERT INTO internal_order " +
                "(part_id, from_id, total, quantity , to_id, order_date, EXPECTED_DELIVERY_DATE, status) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try{
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, internalOrder.getPartId());
            preparedStatement.setLong(2, internalOrder.getFromId());
            preparedStatement.setFloat(3, internalOrder.getTotal());
            preparedStatement.setString(4, String.valueOf(internalOrder.getQuantity()));
            preparedStatement.setString(5, String.valueOf(internalOrder.getToId()));
            preparedStatement.setDate(6, new java.sql.Date(internalOrder.getOrderDate().getTime()));
            preparedStatement.setDate(7, new java.sql.Date(internalOrder.getExpectedDeliveryDate().getTime()));
            preparedStatement.setString(8, String.valueOf(internalOrder.getStatus()));

            return preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }



}
