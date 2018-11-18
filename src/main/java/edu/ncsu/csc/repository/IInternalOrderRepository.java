package edu.ncsu.csc.repository;

import edu.ncsu.csc.entity.InternalOrder;
import edu.ncsu.csc.entity.User;

import java.util.List;

public interface IInternalOrderRepository {

    List<InternalOrder> getInternalOrder(long centerId);

    int saveInternalOrder(InternalOrder internalOrder);

}
