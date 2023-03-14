package com.abhi.FileScheduler.vo;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class Daily {
    private int day;
    private int minutes;
    private int hour;

}
