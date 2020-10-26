package com.gugunner.aws.lambda.mongo.api.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.gugunner.aws.lambda.mongo.api.model.data.CarBrandResponse;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document(collection = "cars")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GroupCarBrandResponse {

    @Id
    private String id;
    private double avgKmsDriven;
    private List<CarBrandResponse> data;

}
