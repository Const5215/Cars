package edu.ncsu.csc.repository;

import edu.ncsu.csc.entity.ExternalOrder;

import java.util.List;

public interface IExternalOrderRepository {

    List<ExternalOrder> getExternalOrder(long centerId);

    int saveExternalOrder(ExternalOrder externalOrder);

}

