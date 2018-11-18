package edu.ncsu.csc.pages.customer;

import edu.ncsu.csc.entity.Car;
import edu.ncsu.csc.entity.CarModel;
import edu.ncsu.csc.entity.User;
import edu.ncsu.csc.pages.AbstractPage;
import edu.ncsu.csc.pages.Page;
import edu.ncsu.csc.repository.CarModelRepository;
import edu.ncsu.csc.repository.CarRepository;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

public class ViewProfile extends AbstractPage {

  private User customer;

  ViewProfile(User customer) {
    this.customer = customer;
    choices.add("Go Back");
  }

  @Override
  public void run() {
    System.out.println("# ViewProfile");

    CarRepository carRepository = new CarRepository();
    CarModelRepository carModelRepository = new CarModelRepository();
    List<Car> cars = carRepository.getAllCarsByCustomerId(customer.getId());
    CarModel carModel;
    DateFormat dateFormat = new SimpleDateFormat();

    System.out.printf("Customer ID: %d\n", customer.getId());
    System.out.printf("Name: %s\n", customer.getName());
    System.out.printf("Address: %s\n", customer.getAddress());
    System.out.printf("Email address: %s\n", customer.getEmail());
    System.out.printf("Phone number: %s\n", customer.getPhone());

    for (Car car : cars) {
      carModel = carModelRepository.getCarModelById(car.getCarModelId());
      System.out.printf("\tLicense plate: %s\n", car.getPlate());
      System.out
          .printf("\tModel: %d %s %s\n", car.getYear(), carModel.getModel(), carModel.getMake());
      System.out.printf("\tPurchase date: %s\n", dateFormat.format(car.getPurchaseDate()));
    }

    displayChoices();
    getChoiceFromInput();
    goBack();
  }

  private void goBack() {
    Page profile = new Profile(customer);
    profile.run();
  }
}

