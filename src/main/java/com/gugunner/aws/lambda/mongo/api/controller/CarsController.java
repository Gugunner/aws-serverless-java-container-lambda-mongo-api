package com.gugunner.aws.lambda.mongo.api.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gugunner.aws.lambda.mongo.api.model.Cars;
import com.gugunner.aws.lambda.mongo.api.model.GroupCarBrandResponse;
import com.gugunner.aws.lambda.mongo.api.model.GroupCarFuelResponse;
import com.gugunner.aws.lambda.mongo.api.repository.CarsRepository;
import com.gugunner.aws.lambda.mongo.api.service.IServiceCarResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/cars")
public class CarsController {
    @Autowired
    CarsRepository repository;

    @Autowired
    IServiceCarResponse iServiceCarResponse;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String getAllCars() {
        ObjectMapper mapper = new ObjectMapper();
        List<Cars> cars = repository.findAll();
        String response = "No cars found";
        if(!cars.isEmpty()) {
            try {
                response = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(cars);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        System.out.println("RESPONSE " + response);
        return response;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String getCarById(@PathVariable("id") String id) {
        ObjectMapper mapper = new ObjectMapper();
        Optional<Cars> car = repository.findById(id);
        String response = "No car found with id " + id;
        if(car.isPresent()) {
            try {
                response = mapper.writeValueAsString(car.get());
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        return response;
    }

    @RequestMapping(value = "/group/brands", method = RequestMethod.GET)
    public String getCarsGroupedByBrands() {
        ObjectMapper mapper = new ObjectMapper();
        List<GroupCarBrandResponse> groupCarBrandResponses = iServiceCarResponse.groupCarsByBrand();
        String result = "Not working my friend";
        try {
            result = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(groupCarBrandResponses);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return result;
    }

    @RequestMapping(value = "/group/fuel", method = RequestMethod.GET)
    public String getCarsGroupedByFuel() {
        ObjectMapper mapper = new ObjectMapper();
        List<GroupCarFuelResponse> groupCarFuelResponses = iServiceCarResponse.groupCarsByFuel();
        String result = "Not working my friend";
        try {
            result = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(groupCarFuelResponses);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return result;
    }
}
