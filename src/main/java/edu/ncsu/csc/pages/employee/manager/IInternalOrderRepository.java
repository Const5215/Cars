package edu.ncsu.csc.pages.employee.manager;

import edu.ncsu.csc.entity.InternalOrder;
import edu.ncsu.csc.entity.User;

import java.util.List;

public interface IInternalOrderRepository {

    List<InternalOrder> getInternalOrder(long centerId);

}
