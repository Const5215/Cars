package edu.ncsu.csc.repository;

import edu.ncsu.csc.entity.ExternalNotification;

import java.util.List;

public interface IExternalNotificationRepository {

    List<ExternalNotification> getExternalNotification(long orderId);

}
