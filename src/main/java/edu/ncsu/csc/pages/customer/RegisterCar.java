package edu.ncsu.csc.pages.customer;

import edu.ncsu.csc.entity.Car;
import edu.ncsu.csc.entity.User;
import edu.ncsu.csc.pages.AbstractPage;
import edu.ncsu.csc.pages.Page;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterCar extends AbstractPage {
    private User user;

    RegisterCar(User user) {
        this.user = user;
    }

    @Override
    public void run() {
        String carModel,strPurchaseDate;
        SimpleDateFormat dateFormat;
        Long lastMileage;
        dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Car car = new Car();
        System.out.println("#RegisterCar");
        System.out.print("Enter Licence Plate:");
        car.setPlate(scanner.nextLine());
        car.setCustomerId(user.getId());

        do {
            System.out.print("Enter Car Model:");
            try{
                carModel = scanner.nextLine();
                connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
                preparedStatement = connection
                    .prepareStatement("select ID from CAR_MODEL where MODEL=?");
                preparedStatement.setString(1, carModel);
                resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    car.setCarModelId(resultSet.getLong("ID"));
                    break;
                }
                else {
                    System.out.println("Invalid Car Model");
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
            } finally {
                closeSqlConnection();
            }
            break;
        }while(true);
        do {
            System.out.print("Enter purchase date(dd/mm/yyyy):");
            strPurchaseDate = scanner.nextLine();
            Pattern pattern = Pattern
                    .compile("^[0-3][0-9]/[0-3][0-9]/(?:[0-9][0-9])?[0-9][0-9]$");
            Matcher matcher = pattern.matcher(strPurchaseDate);
            if (matcher.matches()) {
                try {
                    car.setPurchaseDate(dateFormat.parse(strPurchaseDate));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                break;
            }
            else {
                System.out.println("Invalid date format");
            }
        }while(true);
        System.out.print("Enter Last Mileage:");
        lastMileage = Long.valueOf(0);
        do {
            try{
                lastMileage = Long.parseLong(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid Mileage");
                continue;
            } finally {
                break;
            }
        }while(true);
        car.setLastMileage(lastMileage);
        car.setLastServiceType(Long.valueOf(0));
        try {
            car.setLastServiceDate(dateFormat.parse("00/00/0000"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            preparedStatement = connection
                    .prepareStatement("insert into CAR values (?, ?, ?, ?, ?, ?, ?)");
            preparedStatement.setString(1, car.getPlate());
            preparedStatement.setLong(2, car.getCustomerId());
            preparedStatement.setLong(3, car.getCarModelId());
            preparedStatement.setDate(4, new java.sql.Date(car.getPurchaseDate().getTime()));
            preparedStatement.setLong(5, car.getLastMileage());
            preparedStatement.setLong(6, car.getLastServiceType());
            preparedStatement.setDate(7, new java.sql.Date(car.getLastServiceDate().getTime()));
            preparedStatement.executeUpdate();

            preparedStatement = connection.prepareStatement("select ID from CUSTOMER where EMAIL=?");
            preparedStatement.setString(1, user.getEmail());
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                user.setId(resultSet.getLong("ID"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeSqlConnection();
        }
        System.out.println("Register Success.");
        Page customerLanding = new CustomerLanding(user);
        customerLanding.run();
    }
}
