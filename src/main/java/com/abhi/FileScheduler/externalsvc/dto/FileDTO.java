package com.abhi.FileScheduler.externalsvc.dto;

import com.abhi.FileScheduler.externalsvc.vo.Schedule;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class FileDTO {

    private String fileName;

    private Schedule schedule;
    private LocalDate createDate;
}
