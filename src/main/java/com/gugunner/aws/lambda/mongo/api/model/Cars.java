package com.gugunner.aws.lambda.mongo.api.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "cars")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Cars {

    @Id
    private String id;
    private String modelName;
    private String brand;
    private double engineCapacity;
    private String fuel;
    private int noOfSeats;
    private String colour;
    private String year;
    private int kmsDriven;
}
