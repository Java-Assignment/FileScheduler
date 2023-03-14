package com.abhi.FileScheduler.dto;

import com.abhi.FileScheduler.vo.Daily;
import com.abhi.FileScheduler.vo.Monthly;
import com.abhi.FileScheduler.vo.Schedule;
import com.abhi.FileScheduler.vo.Weekly;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FileDTO {

    private String fileName;

    private Schedule schedule;

    private Daily daily;

    private Monthly monthly;
    private Weekly weekly;
}
