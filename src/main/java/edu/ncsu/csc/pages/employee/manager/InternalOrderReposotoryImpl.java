package edu.ncsu.csc.pages.employee.manager;

import edu.ncsu.csc.entity.InternalOrder;
import edu.ncsu.csc.pages.AbstractPage;
import edu.ncsu.csc.util.ResultSetUtils;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
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
}
