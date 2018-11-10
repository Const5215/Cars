package edu.ncsu.csc.pages.employee;

import edu.ncsu.csc.entity.Car;
import edu.ncsu.csc.entity.MatchType;
import edu.ncsu.csc.entity.User;
import edu.ncsu.csc.pages.AbstractPage;
import edu.ncsu.csc.repository.CarRepository;
import edu.ncsu.csc.repository.CustomerRepository;

import java.util.List;

public class ViewCustomerProfile extends AbstractPage {

  private User employee;

  public ViewCustomerProfile(User employee) {
    this.employee = employee;
    choices.add("Go Back");
  }

  @Override
  public void run() {
    System.out.println("#viewCustomerProfile");
    User customer = getCustomer();
    printCustomerProfile(customer);
    displayChoices();
    getChoiceFromInput();
    Profile.appropriateLandingPage(employee);
  }

  private User getCustomer() {
    User customer;
    CustomerRepository customerRepository = new CustomerRepository();
    do {
      String customerEmail = getInfo("Enter customer email:", MatchType.Email);
      customer = customerRepository.getCustomerByEmail(customerEmail);
    } while (customer == null);
    return customer;
  }

  private void printCustomerProfile(User customer) {
    CarRepository carRepository = new CarRepository();
    List<Car> customerCarList = carRepository.getCarListByCustomerId(customer.getId());
    System.out.println("Customer ID: " + customer.getId());
    System.out.println("Name: " + customer.getName());
    System.out.println("Address: " + customer.getAddress());
    System.out.println("Email Address: " + customer.getEmail());
    System.out.println("Phone Number: " + customer.getPhone());
    System.out.printf("Customer has %d car(s) in total.\n", customerCarList.size());
    carRepository.printCarList(customerCarList);
  }

}
