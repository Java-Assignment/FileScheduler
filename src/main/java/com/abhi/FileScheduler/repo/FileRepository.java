package com.abhi.FileScheduler.repo;

import com.abhi.FileScheduler.externalsvc.Fileconfigsvc.FileDTO;
import com.abhi.FileScheduler.externalsvc.Fileconfigsvc.entity.File;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDate;

public interface FileRepository extends MongoRepository<File,String> {
    FileDTO findByDaily(LocalDate date);
}