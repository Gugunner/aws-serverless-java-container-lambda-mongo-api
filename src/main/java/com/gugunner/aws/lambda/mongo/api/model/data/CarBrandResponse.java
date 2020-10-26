package com.gugunner.aws.lambda.mongo.api.model.data;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "cars")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CarBrandResponse {

    private String modelName;
    private String brand;
    private int year;
    private String fuel;
    private double engineCapacity;
    private int kmsDriven;
}