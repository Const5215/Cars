package edu.ncsu.csc.repository;

import edu.ncsu.csc.entity.Distributor;

import java.sql.DriverManager;

public class DistributorRepository extends AbstractRepository {

    public Distributor getDistributor(long distributorId) {
        String sql = "SELECT * FROM distributor WHERE id = ? ";
        Distributor distributor = null;
        try{
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, distributorId);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                distributor = new Distributor(
                        resultSet.getLong("ID"),
                        resultSet.getString("NAME")
                );
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return distributor;
    }
}
