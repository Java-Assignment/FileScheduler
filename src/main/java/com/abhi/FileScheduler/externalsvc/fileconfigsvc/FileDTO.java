package com.abhi.FileScheduler.externalsvc.fileconfigsvc;

import com.abhi.FileScheduler.externalsvc.fileconfigsvc.vo.Daily;
import com.abhi.FileScheduler.externalsvc.fileconfigsvc.vo.Monthly;
import com.abhi.FileScheduler.externalsvc.fileconfigsvc.vo.Schedule;
import com.abhi.FileScheduler.externalsvc.fileconfigsvc.vo.Weekly;
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
