package com.abhi.filescheduler.repo;

import com.abhi.filescheduler.entity.FileConfig;
import org.springframework.data.mongodb.repository.MongoRepository;



public interface FileRepository extends MongoRepository<FileConfig,String> {
}
