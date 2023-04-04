package com.abhi.filescheduler.repo;

import com.abhi.filescheduler.entity.FileConfig;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface FileRepository extends MongoRepository<FileConfig,String> {
}
