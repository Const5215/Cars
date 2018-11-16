package edu.ncsu.csc.pages.customer;

import edu.ncsu.csc.entity.Car;
import edu.ncsu.csc.entity.User;
import edu.ncsu.csc.pages.AbstractPage;
import edu.ncsu.csc.pages.Page;
import edu.ncsu.csc.repository.CarModelRepository;
import edu.ncsu.csc.repository.CarRepository;

class RegisterCar extends AbstractPage {

  private CarRepository carRepository;
  private User customer;
  private Car car;

  RegisterCar(User customer) {
    carRepository = new CarRepository();
    this.customer = customer;
    car = new Car();
    choices.add("Register");
    choices.add("Cancel");
  }

  @Override
  public void run() {
    System.out.println("# Register Car");

    car.setPlate(getCarPlateFromInput());

    while (true) {
      String make = getStringFromInput("Enter make (Honda/Nissan/Toyota): ");
      String model = getStringFromInput("Enter model: ");

      CarModelRepository carModelRepository = new CarModelRepository();
      Long carModelId = carModelRepository.getIdByMakeAndModel(make, model);

      if (carModelId == null) {
        System.out.println("Invalid car model");
      } else {
        car.setCarModelId(carModelId);
        break;
      }
    }

    car.setPurchaseDate(getDateFromInput("Enter purchased date (MM/DD/YYYY): "));

    displayChoices();
    switch (getChoiceFromInput()) {
      case 1:
        register();
      case 2:
        cancel();
    }
  }

  private void register() {
    carRepository.add(car);
    System.out.println("Successfully registered");
  }

  private void cancel() {
    Page customerLanding = new CustomerLanding(customer);
    customerLanding.run();
  }

  private String getCarPlateFromInput() {
    while (true) {
      String plate = getStringFromInput("Enter licence plate: ").toUpperCase();

      if (carRepository.getCarByPlate(plate) == null) {
        return plate;
      } else {
        System.out.println("This car has already been registered");
      }
    }
  }

}
