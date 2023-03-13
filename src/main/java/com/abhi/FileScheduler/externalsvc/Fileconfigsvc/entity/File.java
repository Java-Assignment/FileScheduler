package com.abhi.FileScheduler.externalsvc.Fileconfigsvc.entity;

import com.abhi.FileScheduler.externalsvc.Fileconfigsvc.vo.Daily;
import com.abhi.FileScheduler.externalsvc.Fileconfigsvc.vo.Monthly;
import com.abhi.FileScheduler.externalsvc.Fileconfigsvc.vo.Schedule;
import com.abhi.FileScheduler.externalsvc.Fileconfigsvc.vo.Weekly;
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