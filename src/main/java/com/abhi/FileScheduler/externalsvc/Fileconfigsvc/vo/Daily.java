package com.abhi.FileScheduler.externalsvc.Fileconfigsvc.vo;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class Daily {
    private String day;
    private int minutes;
    private int seconds;
    private int hour;
    @DateTimeFormat()
    private LocalDate createDate;

}
