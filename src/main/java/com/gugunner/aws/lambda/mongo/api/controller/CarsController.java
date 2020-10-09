package com.gugunner.aws.lambda.mongo.api.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gugunner.aws.lambda.mongo.api.model.Cars;
import com.gugunner.aws.lambda.mongo.api.repository.CarsRepository;
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
}
