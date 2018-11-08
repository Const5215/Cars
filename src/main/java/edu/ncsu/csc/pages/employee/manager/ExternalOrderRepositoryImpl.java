package edu.ncsu.csc.pages.employee.manager;

import edu.ncsu.csc.entity.ExternalOrder;
import edu.ncsu.csc.entity.OrderStatus;
import edu.ncsu.csc.pages.AbstractPage;
import edu.ncsu.csc.util.ResultSetUtils;

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
//        String sql = "INSERT INTO external_order " +
//                "(part_id, quantity, total, center_id, distributor_id, order_date, delivery_date, status) " +
//                "VALUES (?, ?, ?, ?, ?, ?, ?, ? )";
                String sql = "insert into EXTERNAL_ORDER (PART_ID, CENTER_ID, TOTAL, QUANTITY, DISTRIBUTOR_ID, STATUS) values ( ?,?,?,?,?,?)";
//        String sql = "insert into EXTERNAL_ORDER (PART_ID, quantity, total, CENTER_ID, DISTRIBUTOR_ID, ORDER_DATE, DELIVERY_DATE, STATUS) values ( ?,?,?,?,?,?,?,?)";

        try{
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, String.valueOf(externalOrder.getPartId()));
            preparedStatement.setString(2, String.valueOf(externalOrder.getCenterId()));
            preparedStatement.setString(3, String.valueOf(externalOrder.getTotal()));

            preparedStatement.setString(4, String.valueOf(externalOrder.getQuantity()));
            preparedStatement.setString(5, String.valueOf(externalOrder.getDistributorId()));
//            preparedStatement.setString(6, String.valueOf(externalOrder.getOrderDate()));
//            preparedStatement.setString(7, String.valueOf(externalOrder.getDeliveryDate()));
            preparedStatement.setString(6, String.valueOf(externalOrder.getStatus()));

            return preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static void main(String[] args){
        IExternalOrderRepository externalOrderRepository = new ExternalOrderRepositoryImpl();
        ExternalOrder externalOrder = new ExternalOrder();
        externalOrder.setPartId(1L);
        externalOrder.setCenterId(1L);
        externalOrder.setTotal(1F);

        externalOrder.setQuantity(1);
        externalOrder.setDistributorId(1L);
//        externalOrder.setOrderDate(new Date());
//        externalOrder.setDeliveryDate(new Date());
        externalOrder.setStatus(OrderStatus.Pending);

        externalOrderRepository.saveExternalOrder(externalOrder);
    }
}
