package com.gugunner.aws.lambda.mongo.api.repository;

import com.gugunner.aws.lambda.mongo.api.model.GroupCarBrandResponse;
import com.gugunner.aws.lambda.mongo.api.model.GroupCarFuelResponse;

import java.util.List;

public interface ICarsMongoRepository {
    List<GroupCarBrandResponse> getCarsGroupByBrand();

    List<GroupCarFuelResponse> getCarsGroupByFuel();
}
