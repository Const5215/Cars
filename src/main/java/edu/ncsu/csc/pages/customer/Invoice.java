package edu.ncsu.csc.pages.customer;

import edu.ncsu.csc.entity.User;
import edu.ncsu.csc.pages.AbstractPage;
import edu.ncsu.csc.pages.Page;

public class Invoice extends AbstractPage {
  private User customer;

  Invoice(User customer) {
    this.customer = customer;
    choices.add("View Invoice Details");
    choices.add("Go Back");
  }

  @Override
  public void run() {
    System.out.println("#Invoice");

    displayChoices();
    switch (getChoiceFromInput()) {
      case 1:
        Page viewInvoiceDetails = new ViewInvoiceDetails(customer);
        viewInvoiceDetails.run();
        break;
      case 2:
        Page customerLanding = new CustomerLanding(customer);
        customerLanding.run();
    }
  }
}
