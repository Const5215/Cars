package edu.ncsu.csc.pages.employee.manager;

import edu.ncsu.csc.entity.Inventory;
import edu.ncsu.csc.entity.User;

import java.util.List;

public interface IInventoryRepository {

    List<Inventory> getInventory(long centerId);
}
