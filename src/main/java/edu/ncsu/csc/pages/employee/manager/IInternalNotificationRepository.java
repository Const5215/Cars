package edu.ncsu.csc.pages.employee.manager;

import edu.ncsu.csc.entity.InternalNotification;

import java.util.List;

public interface IInternalNotificationRepository {

    List<InternalNotification> getInternalNotification(long orderId);

}
