package com.abhi.FileScheduler.externalsvc.Fileconfigsvc;

import com.abhi.FileScheduler.externalsvc.Fileconfigsvc.vo.Daily;
import com.abhi.FileScheduler.externalsvc.Fileconfigsvc.vo.Monthly;
import com.abhi.FileScheduler.externalsvc.Fileconfigsvc.vo.Schedule;
import com.abhi.FileScheduler.externalsvc.Fileconfigsvc.vo.Weekly;
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
