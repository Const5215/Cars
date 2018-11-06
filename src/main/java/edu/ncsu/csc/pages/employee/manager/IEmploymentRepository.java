package edu.ncsu.csc.pages.employee.manager;



import edu.ncsu.csc.entity.User;

public interface IEmploymentRepository {

    long getCenterId(User employee);

}
