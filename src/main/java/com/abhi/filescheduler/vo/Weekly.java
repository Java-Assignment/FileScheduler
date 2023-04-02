package com.abhi.filescheduler.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Weekly {
    private int DayOfWeek;
    private int minutes;

    private int hour;
}

