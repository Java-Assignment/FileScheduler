package com.abhi.filescheduler.dto;

import com.abhi.filescheduler.vo.Daily;
import com.abhi.filescheduler.vo.Hourly;
import com.abhi.filescheduler.vo.Monthly;
import com.abhi.filescheduler.vo.Weekly;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FileConfigDTO {
    private String fileName;
    private Monthly monthly;
    private Weekly weekly;
    private Daily daily;
    private Hourly hourly;
}
