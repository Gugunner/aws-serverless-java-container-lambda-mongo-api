package com.gugunner.aws.lambda.mongo.api.repository;

import com.gugunner.aws.lambda.mongo.api.model.Cars;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CarsRepository extends MongoRepository<Cars, String> {
}
