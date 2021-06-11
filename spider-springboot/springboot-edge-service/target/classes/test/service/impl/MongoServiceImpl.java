package test.service.impl;

import test.dao.MongoDao;
import test.domain.MongoEntity;
import test.service.MongoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class MongoServiceImpl implements MongoService {
    
    @Resource
    private MongoDao mongoDao;

    @Override
    public List<MongoEntity> findAll() {
        List<MongoEntity> result = mongoDao.findAll();
        return result;
    }
}
