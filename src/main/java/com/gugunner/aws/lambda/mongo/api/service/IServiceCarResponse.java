package com.gugunner.aws.lambda.mongo.api.service;

import com.gugunner.aws.lambda.mongo.api.model.GroupCarBrandResponse;
import com.gugunner.aws.lambda.mongo.api.model.GroupCarFuelResponse;

import java.util.List;

public interface IServiceCarResponse {
    List<GroupCarBrandResponse> groupCarsByBrand();

    List<GroupCarFuelResponse> groupCarsByFuel();
}