package edu.ncsu.csc.repository;

import edu.ncsu.csc.entity.InternalOrder;
import edu.ncsu.csc.entity.OrderStatus;
import edu.ncsu.csc.pages.AbstractPage;
import edu.ncsu.csc.util.ResultSetUtils;

import java.sql.DriverManager;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class InternalOrderReposotoryImpl extends AbstractPage implements IInternalOrderRepository {

    @Override
    public List<InternalOrder> getInternalOrder(long centerId) {
        List<InternalOrder> internalOrderList = new ArrayList<>();
        String sql = "SELECT * FROM internal_order WHERE to_id = ?";
        try{
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, String.valueOf(centerId));
            resultSet = preparedStatement.executeQuery();
            internalOrderList = (List<InternalOrder>) ResultSetUtils.convertToList(resultSet, InternalOrder.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return internalOrderList;
    }

    @Override
    public int saveInternalOrder(InternalOrder internalOrder) {
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

    public static void main(String[] args){
//        IInternalOrderRepository internalOrderRepository = new InternalOrderReposotoryImpl();
//        InternalOrder internalOrder = new InternalOrder();
//        internalOrder.setPartId(1L);
//        internalOrder.setFromId(1L);
//        internalOrder.setTotal(1F);
//        internalOrder.setQuantity(1);
//        internalOrder.setToId(1L);
//        internalOrder.setOrderDate(new Date());
//        internalOrder.setExpectedDeliveryDate(new Date());
//        internalOrder.setStatus(OrderStatus.Pending.getStatus());
//        internalOrderRepository.saveInternalOrder(internalOrder);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        System.out.println(simpleDateFormat.format(new Date()));
    }


}
