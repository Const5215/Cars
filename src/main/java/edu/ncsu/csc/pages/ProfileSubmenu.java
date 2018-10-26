package edu.ncsu.csc.pages;

import edu.ncsu.csc.entity.User;
import edu.ncsu.csc.pages.customer.CustomerLanding;

public class ProfileSubmenu extends AbstractPage {

    private User user;

    public ProfileSubmenu(User user) {
        this.user = user;
        choices.add("View Profile");
        choices.add("Update Profile");
        choices.add("Go back");
    }

    @Override
    public void run() {
        int choice;
        do{
            System.out.println("# ProfileSubmenu");
            displayChoices();
            choice = getChoiceFromInput();
            switch (choice) {
                case 1:
                    viewProfile();
                    break;
                case 2:
                    updateProfile();
                    break;
                case 3:
                    Page customer = new CustomerLanding(user);
                    customer.run();
            }
        }while(choice != 3);
    }

    private void viewProfile() {
        System.out.println();
        System.out.println("#ViewProfile");
        System.out.println("user ID: " + user.getId());
        System.out.println("Password: " + user.getPassword());
        System.out.println("Name: " + user.getName());
        System.out.println("Email: " + user.getEmail());
        System.out.println("Phone: " + user.getPhone());
        System.out.println("Address: " + user.getAddress());
        System.out.println();
    }

    private void updateProfile() {
        Page updateProfile = new UpdateProfile(user);
        updateProfile.run();
    }
}
