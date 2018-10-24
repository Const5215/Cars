package edu.ncsu.csc.pages.customer;

import edu.ncsu.csc.entity.User;
import edu.ncsu.csc.pages.AbstractPage;
import edu.ncsu.csc.pages.Page;

public class CustomerInvoice extends AbstractPage {
    private User user;

    CustomerInvoice(User user) {
        this.user = user;
    }
}
