package com.gugunner.aws.lambda.mongo.api.model.data;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "cars")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CarFuelResponse {

    @Id
    private String id;
    private String fuel;
    private String modelName;
    private String color;
    private int year;
    private String date;

}