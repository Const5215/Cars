package edu.ncsu.csc.pages.employee.manager;

import edu.ncsu.csc.entity.ExternalNotification;

import java.util.List;

public interface IExternalNotificationRepository {

    List<ExternalNotification> getExternalNotification(long orderId);

}
