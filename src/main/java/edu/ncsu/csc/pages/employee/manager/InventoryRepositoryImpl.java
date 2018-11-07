package edu.ncsu.csc.pages.employee.manager;

import edu.ncsu.csc.entity.Inventory;
import edu.ncsu.csc.pages.AbstractPage;
import edu.ncsu.csc.util.ResultSetUtils;

import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;

public class InventoryRepositoryImpl extends AbstractPage implements IInventoryRepository {

    @Override
    public List<Inventory> getInventory(long centerId) {
        List<Inventory> inventoryList = new ArrayList<>();
        String sql ="SELECT * FROM inventory WHERE center_id = ?";
        try{
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, String.valueOf(centerId));
            resultSet = preparedStatement.executeQuery();
            inventoryList = (List<Inventory>) ResultSetUtils.convertToList(resultSet, Inventory.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return inventoryList;
    }
}
