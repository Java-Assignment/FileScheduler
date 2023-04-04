package com.abhi.filescheduler.entity;


import com.abhi.filescheduler.vo.Daily;
import com.abhi.filescheduler.vo.Hourly;
import com.abhi.filescheduler.vo.Monthly;
import com.abhi.filescheduler.vo.Weekly;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "FileConfigure")
@Data
public class FileConfig {
    @Id
    private String fileName;
    private Monthly monthly;
    private Weekly weekly;
    private Daily daily;
    private Hourly hourly;
}
