package edu.ncsu.csc.pages.customer;

import edu.ncsu.csc.entity.Car;
import edu.ncsu.csc.entity.User;
import edu.ncsu.csc.pages.AbstractPage;
import edu.ncsu.csc.pages.Page;
import edu.ncsu.csc.repository.CarRepository;

import java.util.List;

public class ViewProfile extends AbstractPage {
  private User customer;
  private CarRepository carRepository;
  ViewProfile(User customer) {
    this.customer = customer;
    carRepository = new CarRepository();
    choices.add("Go Back");
  }

  @Override
  public void run() {
    System.out.println("#ViewProfile");
    printProfile();

    List<Car> customerCarList = carRepository.getCarListByCustomerId(customer.getId());
    System.out.printf("You have %d car(s) in total.\n", customerCarList.size());
    carRepository.printCarList(customerCarList);

    displayChoices();
    getChoiceFromInput();
    goBack();
  }

  private void printProfile() {
    System.out.println("Customer ID: " + customer.getId());
    System.out.println("Name: " + customer.getName());
    System.out.println("Address: " + customer.getAddress());
    System.out.println("Email Address: " + customer.getEmail());
    System.out.println("Phone: " + customer.getPhone());
  }

  private void goBack() {
    Page profile = new Profile(customer);
    profile.run();
  }

}

