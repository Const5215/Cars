package edu.ncsu.csc.repository;

import edu.ncsu.csc.entity.Distributor;

public interface IDistributorRepository {
    Distributor getDistributor(long distributorId);
}

