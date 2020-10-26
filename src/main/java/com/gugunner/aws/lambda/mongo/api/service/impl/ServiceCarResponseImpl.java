package com.gugunner.aws.lambda.mongo.api.service.impl;

import com.gugunner.aws.lambda.mongo.api.service.IServiceCarResponse;
import com.gugunner.aws.lambda.mongo.api.model.GroupCarBrandResponse;
import com.gugunner.aws.lambda.mongo.api.model.GroupCarFuelResponse;
import com.gugunner.aws.lambda.mongo.api.repository.ICarsMongoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceCarResponseImpl implements IServiceCarResponse {
    @Autowired
    ICarsMongoRepository iCarsMongoRepository;

    @Override
    public List<GroupCarBrandResponse> groupCarsByBrand() {
        return iCarsMongoRepository.getCarsGroupByBrand();
    }

    @Override
    public List<GroupCarFuelResponse> groupCarsByFuel() {
        return iCarsMongoRepository.getCarsGroupByFuel();
    }
}
