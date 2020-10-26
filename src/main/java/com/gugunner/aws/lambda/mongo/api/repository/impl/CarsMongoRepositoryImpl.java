package com.gugunner.aws.lambda.mongo.api.repository.impl;

import com.gugunner.aws.lambda.mongo.api.model.GroupCarBrandResponse;
import com.gugunner.aws.lambda.mongo.api.model.GroupCarFuelResponse;
import com.gugunner.aws.lambda.mongo.api.model.data.CarBrandResponse;
import com.gugunner.aws.lambda.mongo.api.model.data.CarFuelResponse;
import com.gugunner.aws.lambda.mongo.api.repository.ICarsMongoRepository;
import com.mongodb.BasicDBObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Repository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.group;

@Repository
public class CarsMongoRepositoryImpl implements ICarsMongoRepository {

    @Autowired
    MongoTemplate mongoTemplate;

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS\'Z\'");

    @Override
    public List<GroupCarBrandResponse> getCarsGroupByBrand() {
        Aggregation agg = newAggregation(
                group("brand").addToSet(new BasicDBObject() {
                    {
                        put("modelName", "$modelName");
                        put("year", "$year");
                        put("fuel", "$fuel");
                        put("engineCapacity", "$engineCapacity");
                        put("kmsDriven", "$kmsDriven");
                    }
                }).as("data").avg("kmsDriven").as("avgKmsDriven")
        );
        AggregationResults<GroupCarBrandResponse> aggregationResults = mongoTemplate.aggregate(agg, CarBrandResponse.class, GroupCarBrandResponse.class);
        return aggregationResults.getMappedResults();
    }

    @Override
    public List<GroupCarFuelResponse> getCarsGroupByFuel() {
        Date from = new Date();
        Date to = new Date();
        try {
            from = sdf.parse("2020-05-12T23:33:16.868Z");
            to = sdf.parse("2020-05-19T23:33:16.868Z");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Aggregation agg = newAggregation(
                match(new Criteria("date")
                        .gte(from)
                        .lt(to)),
                group("fuel").addToSet(new BasicDBObject() {
                    {
                        put("_id", new BasicDBObject("$toString", "$_id"));
                        put("modelName", "$modelName");
                        put("color", "$colour");
                        put("year", "$year");
                        put("date", new BasicDBObject("$toString", "$date"));
                    }
                }).as("data").count().as("count")
        );
        AggregationResults<GroupCarFuelResponse> aggregationResults = mongoTemplate.aggregate(agg, CarFuelResponse.class, GroupCarFuelResponse.class);
        return aggregationResults.getMappedResults();
    }
}
