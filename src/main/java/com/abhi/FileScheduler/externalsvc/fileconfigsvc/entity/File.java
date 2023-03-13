package com.abhi.FileScheduler.externalsvc.fileconfigsvc.entity;

import com.abhi.FileScheduler.externalsvc.fileconfigsvc.vo.Daily;
import com.abhi.FileScheduler.externalsvc.fileconfigsvc.vo.Monthly;
import com.abhi.FileScheduler.externalsvc.fileconfigsvc.vo.Schedule;
import com.abhi.FileScheduler.externalsvc.fileconfigsvc.vo.Weekly;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "FileConfigure")
@Data
public class File {
    @Id
    private String fileName;

    private Schedule schedule;

    private Daily daily;

    private Monthly monthly;
    private Weekly weekly;
}