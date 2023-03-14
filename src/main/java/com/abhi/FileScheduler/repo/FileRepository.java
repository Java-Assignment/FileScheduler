package com.abhi.FileScheduler.repo;

import com.abhi.FileScheduler.entity.FileConfig;
import org.springframework.data.mongodb.repository.MongoRepository;



public interface FileRepository extends MongoRepository<FileConfig,String> {
}
