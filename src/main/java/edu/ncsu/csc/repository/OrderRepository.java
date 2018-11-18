package edu.ncsu.csc.repository;

import edu.ncsu.csc.entity.Order;
import edu.ncsu.csc.entity.OrderStatus;
import edu.ncsu.csc.entity.ServiceStatus;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class OrderRepository extends AbstractRepository {

  public void add(Order order) {
    try {
      connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
      if (order.getDeliveryOrder()){
        preparedStatement = connection
            .prepareStatement("insert into EXTERNAL_ORDER values (ORDER_ID_SEQ.nextval,?,?,?,?,?,?,?,?,?)");
        preparedStatement.setLong(1, order.getPartId());
        preparedStatement.setLong(2, order.getQuantity());
        preparedStatement.setFloat(3, order.getTotal());
        preparedStatement.setLong(4, order.getFromId());
        preparedStatement.setLong(5, order.getToId());
        preparedStatement.setDate(6, new java.sql.Date(order.getOrderDate().getTime()));
        preparedStatement.setDate(7, new java.sql.Date(order.getExpectedDeliveryDate().getTime()));
        preparedStatement.setNull(8, Types.DATE);
        preparedStatement.setInt(9, order.getStatus().ordinal());
        preparedStatement.executeUpdate();
      }
      else {
        preparedStatement = connection
          .prepareStatement("insert into INTERNAL_ORDER values (ORDER_ID_SEQ.nextval,?,?,?,?,?,?,?,?,?)");
        preparedStatement.setLong(1, order.getPartId());
        preparedStatement.setLong(2, order.getQuantity());
        preparedStatement.setFloat(3, order.getTotal());
        preparedStatement.setLong(4, order.getFromId());
        preparedStatement.setLong(5, order.getToId());
        preparedStatement.setDate(6, new java.sql.Date(order.getOrderDate().getTime()));
        preparedStatement.setDate(7, new java.sql.Date(order.getExpectedDeliveryDate().getTime()));
        preparedStatement.setNull(8, Types.DATE);
        preparedStatement.setInt(9, order.getStatus().ordinal());
        preparedStatement.executeUpdate();
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      closeSqlConnection();
    }
  }

  public List<Order> getOrdersByToIdAndPartIdAndStatus(Long centerId, Long partId, ServiceStatus serviceStatus) {
    List<Order> orderList = new ArrayList<Order>();
    try {
      connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
      preparedStatement = connection
          .prepareStatement("select * from EXTERNAL_ORDER where CENTER_ID=? and PART_ID=? and STATUS=?");
      preparedStatement.setLong(1, centerId);
      preparedStatement.setLong(2, partId);
      preparedStatement.setLong(3, serviceStatus.ordinal());
      resultSet = preparedStatement.executeQuery();
      while(resultSet.next()) {
        Order order = new Order(resultSet.getLong("ID"),
            resultSet.getLong("PART_ID"),
            resultSet.getInt("QUANTITY"),
            resultSet.getFloat("TOTAL"),
            resultSet.getLong("DISTRIBUTOR_ID"),
            resultSet.getLong("CENTER_ID"),
            resultSet.getDate("ORDER_DATE"),
            resultSet.getDate("EXPECTED_DELIVERY_DATE"),
            resultSet.getDate("ACTUAL_DELIVERY_DATE"),
            OrderStatus.values()[resultSet.getInt("STATUS")],
            true);
        orderList.add(order);
      }
      preparedStatement = connection
          .prepareStatement("select  * from INTERNAL_ORDER where TO_ID=? and PART_ID=? and STATUS=?");
      preparedStatement.setLong(1, centerId);
      preparedStatement.setLong(2, partId);
      preparedStatement.setLong(3, serviceStatus.ordinal());
      resultSet = preparedStatement.executeQuery();
      while(resultSet.next()) {
        Order order = new Order(resultSet.getLong("ID"),
            resultSet.getLong("PART_ID"),
            resultSet.getInt("QUANTITY"),
            resultSet.getFloat("TOTAL"),
            resultSet.getLong("FROM_ID"),
            resultSet.getLong("TO_ID"),
            resultSet.getDate("ORDER_DATE"),
            resultSet.getDate("EXPECTED_DELIVERY_DATE"),
            resultSet.getDate("ACTUAL_DELIVERY_DATE"),
            OrderStatus.values()[resultSet.getInt("STATUS")],
            false);
        orderList.add(order);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      closeSqlConnection();
    }
    Collections.sort(orderList, new Comparator<Order>() {
      @Override
      public int compare(Order o1, Order o2) {
        return o1.getExpectedDeliveryDate().before(o2.getExpectedDeliveryDate()) ? -1 :
            (o1.getExpectedDeliveryDate().after(o2.getExpectedDeliveryDate()) ? 1 : 0);
      }
    });
    return orderList;
  }
}
