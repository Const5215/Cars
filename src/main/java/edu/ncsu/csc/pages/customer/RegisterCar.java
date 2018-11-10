package edu.ncsu.csc.pages.customer;

import edu.ncsu.csc.entity.User;
import edu.ncsu.csc.pages.AbstractPage;

class RegisterCar extends AbstractPage {
    private User user;

    RegisterCar(User user) {
      this.user = user;
      choices.add("Register");
      choices.add("Cancel");
    }
    /*
      -- This page is under rework --
    @Override
    public void run() {
      System.out.println("#Register Car");

      String carMake, carModel, strPurchaseDate, strLastServiceDate;

      long carYear, lastMileage;
      Car car = new Car();

      System.out.print("Enter Licence Plate:");
      car.setPlate(scanner.nextLine());
      car.setCustomerId(user.getId());

      strPurchaseDate = getInfo("Enter purchase date(dd/mm/yyyy):", MatchType.Date);
      try {
        car.setPurchaseDate(dateFormat.parse(strPurchaseDate));
      } catch (ParseException e) {
        e.printStackTrace();
      }
      do {
        try {
          System.out.print("Enter Make:");
          carMake = scanner.nextLine();
          System.out.print("Enter Model:");
          carModel = scanner.nextLine();
          carYear = Long.parseLong(getInfo("Enter Year:", MatchType.Number));
          checkCarModel();
          connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
          preparedStatement = connection
              .prepareStatement("select ID from CAR_MODEL where MAKE=? AND MODEL=? AND YEAR=?");
          preparedStatement.setString(1, carMake);
          preparedStatement.setString(2, carModel);
          preparedStatement.setLong(3, carYear);
          resultSet = preparedStatement.executeQuery();
          if (resultSet.next()) {
            car.setCarModelId(resultSet.getLong("ID"));
            break;
          } else {
            System.out.println("Invalid Car Model");
          }
        } catch (SQLException e) {
          e.printStackTrace();
        } finally {
          closeSqlConnection();
        }
      } while (true);

      lastMileage = Long.parseLong(getInfo("Enter last mileage:", MatchType.Number));
      car.setLastMileage(lastMileage);

      do {
        System.out.println("Do you want to Enter last service date?(y/n)");
        String tmp = scanner.nextLine();
        if (tmp.equals("y")) {
          strLastServiceDate = getInfo("Enter Last Service Date(dd/mm/yyyy, Enter for omitted):", MatchType.Date);
          break;
        } else if (tmp.equals("n")) {
          strLastServiceDate = "";
          break;
        }
      } while (true);

      displayChoices();
      switch (getChoiceFromInput()) {
        case 1:
          register(car);
        case 2:
          cancel();
      }
    }

  private void checkCarModel() {

  }

  private void cancel() {
    Page customerLanding = new CustomerLanding(user);
    customerLanding.run();
  }


  private void register(Car car) {

  }
  */
}
