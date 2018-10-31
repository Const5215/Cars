package edu.ncsu.csc.pages.customer;

import edu.ncsu.csc.entity.User;
import edu.ncsu.csc.pages.AbstractPage;
import edu.ncsu.csc.pages.Page;

public class ViewInvoiceDetails extends AbstractPage {
  private User customer;

  ViewInvoiceDetails(User customer) {
    this.customer = customer;
    choices.add("Go Back");
  }

  @Override
  public void run() {
    System.out.println("#viewInvoiceDetails");

    displayChoices();
    getChoiceFromInput();
    goBack();
  }

  private void goBack() {
    Page invoice = new Invoice(customer);
    invoice.run();
  }
}
