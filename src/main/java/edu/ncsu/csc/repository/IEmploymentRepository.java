package edu.ncsu.csc.repository;



import edu.ncsu.csc.entity.User;

public interface IEmploymentRepository {

    long getCenterId(User employee);

}
