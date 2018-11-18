package edu.ncsu.csc.pages.employee;

import edu.ncsu.csc.entity.Car;
import edu.ncsu.csc.entity.CarModel;
import edu.ncsu.csc.entity.User;
import edu.ncsu.csc.pages.AbstractPage;
import edu.ncsu.csc.pages.Page;
import edu.ncsu.csc.pages.employee.manager.ManagerLanding;
import edu.ncsu.csc.pages.employee.receptionist.ReceptionistLanding;
import edu.ncsu.csc.repository.CarModelRepository;
import edu.ncsu.csc.repository.CarRepository;
import edu.ncsu.csc.repository.CustomerRepository;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ViewCustomerProfile extends AbstractPage {

  private User employee;

  public ViewCustomerProfile(User employee) {
    this.employee = employee;
    choices.add("Go Back");
  }

  @Override
  public void run() {
    System.out.println("# View Customer Profile");

    CustomerRepository customerRepository = new CustomerRepository();
    User customer;

    while (true) {
      System.out.print("Enter email address: ");
      String email = scanner.nextLine().toLowerCase();
      Pattern pattern = Pattern.compile("^[\\w.%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$");
      Matcher matcher = pattern.matcher(email);

      if (matcher.matches()) {
        customer = customerRepository.getCustomerByEmail(email);
        break;
      } else {
        System.out.println("Invalid email address");
      }
    }

    if (customer == null) {
      System.out.println("User not found");
    } else {
      CarRepository carRepository = new CarRepository();
      CarModelRepository carModelRepository = new CarModelRepository();
      List<Car> cars = carRepository.getAllCarsByCustomerId(customer.getId());
      CarModel carModel;

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
    }

    displayChoices();
    getChoiceFromInput();
    goBack();
  }

  private void goBack() {
    switch (employee.getRole()) {
      case Manager:
        Page managerLanding = new ManagerLanding(employee);
        managerLanding.run();
        break;
      case Receptionist:
        Page receptionistLanding = new ReceptionistLanding(employee);
        receptionistLanding.run();
        break;
    }
  }
}
