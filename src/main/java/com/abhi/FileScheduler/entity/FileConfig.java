package com.abhi.FileScheduler.entity;


import com.abhi.FileScheduler.vo.Daily;
import com.abhi.FileScheduler.vo.Monthly;
import com.abhi.FileScheduler.vo.Schedule;
import com.abhi.FileScheduler.vo.Weekly;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "FileConfigure")
@Data
public class FileConfig {
    @Id
    private String fileName;

    private Schedule schedule;

    private Daily daily;

    private Monthly monthly;
    private Weekly weekly;
}
