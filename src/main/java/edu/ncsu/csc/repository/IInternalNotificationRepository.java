package edu.ncsu.csc.repository;

import edu.ncsu.csc.entity.InternalNotification;

import java.util.List;

public interface IInternalNotificationRepository {

    List<InternalNotification> getInternalNotification(long orderId);

}
