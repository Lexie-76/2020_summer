package com.test.service;

import com.test.domain.MongoEntity;

import java.util.List;

public interface MongoService {
    List<MongoEntity> findAll();
}
