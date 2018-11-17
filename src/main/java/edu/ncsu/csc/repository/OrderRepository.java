package edu.ncsu.csc.repository;

import edu.ncsu.csc.entity.Order;
import edu.ncsu.csc.entity.OrderStatus;
import edu.ncsu.csc.entity.ServiceStatus;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class OrderRepository extends AbstractRepository {

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
