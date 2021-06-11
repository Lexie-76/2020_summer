package test.service;

import test.domain.MongoEntity;

import java.util.List;

public interface MongoService {
    List<MongoEntity> findAll();
}
