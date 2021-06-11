package com.test.dao;

import com.test.domain.MongoEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MongoDao extends MongoRepository<MongoEntity,String> {

}
