package edu.ncsu.csc.pages.employee.receptionist;

import edu.ncsu.csc.entity.Role;
import edu.ncsu.csc.entity.User;
import edu.ncsu.csc.pages.AbstractPage;
import edu.ncsu.csc.pages.Page;
import edu.ncsu.csc.repository.EmploymentRepository;

import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * DailyTaskUpdateInventory class used for Receptionist to manually update the counts of parts
 * will be removed and actually used that day.
 * <p>
 * Parts might be transfer to other center due to part order, and be used due to service
 *
 * TODO test
 */
public class DailyTaskUpdateInventory extends AbstractPage {
    private User receptionist;
    private long centerId;
    /**
     * TESTING DATA
     */
    private SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
    private Date today;

    {
        try {
            today = (Date) format.parse("2018/10/04");
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public DailyTaskUpdateInventory(User receptionist) {
        this.receptionist = receptionist;
        centerId = new EmploymentRepository().getCenterIdByEmployeeId(receptionist.getId());
        choices.add("Go Back");
    }

    @Override
    public void run() {
        System.out.println("# Daily Task-Update Inventory");

        runDailyTaskUpdateInventory();

        displayChoices();
        getChoiceFromInput();
        goBack();
    }

    /**
     * partOrdered = num In INTERNAL_ORDER with FROM_ID == this.receptionist.CenterID
     * partUsed = num In MANTENANCE AND num In REPAIRE
     *
     * @return counts = countsInDB - num
     */
    private boolean runDailyTaskUpdateInventory() {

        countPartsOrderedToday();

        return true;

    }

    /**
     * @return bool: if the decrementing successful
     */
    private boolean countPartsOrderedToday() {

        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            preparedStatement = connection.prepareStatement(
                    "SELECT PART_ID, QUANTITY FROM INTERNAL_ORDER WHERE FROM_ID=? AND ORDER_DATE=?");
            preparedStatement.setLong(1, centerId);
            preparedStatement.setDate(2, today);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                long partId = resultSet.getLong("PART_ID");
                long quantity = resultSet.getLong("QUANTITY");

                if (!decrementParts(partId, quantity)) {
                    return false;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeSqlConnection();
        }

        return true;
    }

    private boolean countPartsUsedToday() {

        //TODO

        return true;
    }

    private boolean decrementParts(long partId, long quantity) {

        if (canBeDecrement(partId, quantity)) {
            try {
                connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
                preparedStatement = connection.prepareStatement(
                        "UPDATE INVENTORY SET CURRENT_QUANTITY = CURRENT_QUANTITY-? where PART_ID=?");
                preparedStatement.setLong(1, quantity);
                preparedStatement.setLong(2, partId);

            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                closeSqlConnection();
            }
        } else {
            return false;
        }

        return true;
    }

    /**
     * Test if current part quantity is less than used at today
     *
     * @param partId
     * @param quantity
     * @return
     */
    private boolean canBeDecrement(long partId, long quantity) {
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            preparedStatement = connection.prepareStatement(
                    "SELECT CURRENT_QUANTITY FROM INVENTORY WHERE PART_ID=?");
            preparedStatement.setLong(1, partId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                long currentQuantity = resultSet.getLong("CURRENT_QUANTITY");
                if (currentQuantity < quantity) {
                    return false;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeSqlConnection();
        }

        return true;
    }

    private void goBack() {
        Page receptionistLanding = new ReceptionistLanding(receptionist);
        receptionistLanding.run();
    }
}
