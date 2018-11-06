package edu.ncsu.csc.pages.employee.manager;

import edu.ncsu.csc.entity.ExternalOrder;
import edu.ncsu.csc.pages.AbstractPage;
import edu.ncsu.csc.util.ResultSetUtils;

import java.sql.DriverManager;
import java.util.ArrayList;
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

    public static void main(String[] args){
        IExternalOrderRepository externalOrderRepository = new ExternalOrderRepositoryImpl();
        externalOrderRepository.getExternalOrder(1L);
    }
}
