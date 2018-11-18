package edu.ncsu.csc.repository;

import edu.ncsu.csc.entity.Distributor;
import edu.ncsu.csc.pages.AbstractPage;
import edu.ncsu.csc.util.ResultSetUtils;

import java.sql.DriverManager;

public class DistributorRepositoryImpl extends AbstractPage implements IDistributorRepository {


    @Override
    public Distributor getDistributor(long distributorId) {
        String sql = "SELECT * FROM distributor WHERE id = ? ";
        Distributor distributor = new Distributor();
        try{
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, distributorId);
            resultSet = preparedStatement.executeQuery();
            distributor= (Distributor) ResultSetUtils.convertToObject(resultSet, Distributor.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return distributor;
    }
}
