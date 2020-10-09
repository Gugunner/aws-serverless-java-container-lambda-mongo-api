package com.gugunner.aws.lambda.mongo.api;

import com.gugunner.aws.lambda.mongo.api.repository.CarsRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Import;

import com.gugunner.aws.lambda.mongo.api.controller.PingController;


@SpringBootApplication
// We use direct @Import instead of @ComponentScan to speed up cold starts
// @ComponentScan(basePackages = "com.gugunner.aws.lambda.mongo.api.controller")
@Import({ PingController.class})
@EntityScan
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}