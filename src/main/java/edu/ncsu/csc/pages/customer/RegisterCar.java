package edu.ncsu.csc.pages.customer;

import edu.ncsu.csc.entity.Car;
import edu.ncsu.csc.entity.MatchType;
import edu.ncsu.csc.entity.User;
import edu.ncsu.csc.pages.AbstractPage;
import edu.ncsu.csc.pages.Page;
import edu.ncsu.csc.repository.CarModelRepository;

import java.text.ParseException;

class RegisterCar extends AbstractPage {
  private User customer;

  RegisterCar(User customer) {
    this.customer = customer;
    choices.add("Register");
    choices.add("Cancel");
  }

    @Override
    public void run() {
      System.out.println("#Register Car");
      Car car = getCar();

      displayChoices();
      switch (getChoiceFromInput()) {
        case 1:
          register(car);
        case 2:
          cancel();
      }
    }

  private Car getCar() {
    Car car = new Car();
    System.out.print("Enter Licence Plate:");
    car.setPlate(scanner.nextLine());
    String strPurchaseDate = getInfo("Enter Purchase Date(YYYY-MM-DD):", MatchType.Date);
    try {
      car.setPurchaseDate(dateFormat.parse(strPurchaseDate));
    } catch (ParseException e) {
      e.printStackTrace();
    }
    do {
      System.out.print("Enter Make:");
      String make = scanner.nextLine();
      System.out.print("Enter Model:");
      String model = scanner.nextLine();
      CarModelRepository carModelRepository = new CarModelRepository();
      long carModelId = carModelRepository.getCarModelIdByMakeAndModel(make, model);
      if (carModelId == -1) {
        System.out.println("Invalid Car Model");
      } else {
        car.setCarModelId(carModelId);
        break;
      }
    } while (true);
    car.setCustomerId(customer.getId());
    car.setYear(Long.parseLong(getInfo("Enter Year:", MatchType.Number)));
    long mileage = Long.parseLong(getInfo("Enter Current Mileage:", MatchType.Number));
    System.out.println("Do you want to provide last service Date?(y/n)");
    do {
      String choice = scanner.nextLine();
      if (choice.equals("y")) {
        String strLastServiceDate = getInfo("Enter last service date:", MatchType.Date);
        java.util.Date lastServiceDate;
        try {
          lastServiceDate = dateFormat.parse(strLastServiceDate);
        } catch (ParseException e) {
          e.printStackTrace();
        }
        AddLastServiceType();
        break;
      } else if (choice.equals("n")) {
        break;
      } else {
        System.out.println("Invalid Input");
      }
    } while (true);
    return car;
  }

  private void AddLastServiceType() {

  }


  private void cancel() {
    Page customerLanding = new CustomerLanding(customer);
    customerLanding.run();
  }


  private void register(Car car) {

  }
}
