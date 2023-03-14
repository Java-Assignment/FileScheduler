package com.abhi.FileScheduler.vo;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;


@Data
@NoArgsConstructor
public class Monthly {
    private  int  DayOfMonth;
    //    @Schema(description = "indicates which month",defaultValue = "0",allowableValues = "[0,1,2,3,4,5,6,7,8,9,10,11,12]")
    private  int month;
    private int minutes;
    private int hour;

}
