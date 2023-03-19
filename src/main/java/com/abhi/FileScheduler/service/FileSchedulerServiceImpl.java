package com.abhi.FileScheduler.service;

import com.abhi.FileScheduler.dto.FileDTO;
import com.abhi.FileScheduler.entity.FileConfig;
import com.abhi.FileScheduler.externalsvc.FineNameGenerator;
import com.abhi.FileScheduler.mapper.FileMapper;
import com.abhi.FileScheduler.repo.FileRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.util.List;

@Service
@Slf4j
public class FileSchedulerServiceImpl {
    @Autowired
    private FineNameGenerator fineNameGenerator;
    @Autowired
    private FileMapper filemapper;
    @Autowired
    private FileRepository fileRepository;
    private FileDTO fileDTO;

    private String filename;
    private FileConfig fileConfig;
    private int DayOfm;
    private int DayOfWeek;
    private int month;
    private int minutes;
    private int hour;
    private int Day;


    @Scheduled(cron = "0 * * * * *")
    public void fetchFromfileConfig() throws FileNotFoundException, JsonProcessingException {
        List<FileConfig> fileDTOS;
        fileDTOS = fileRepository.findAll();
        for (FileConfig fileConfig : fileDTOS) {
            if (fileConfig.getSchedule().toString().equals("HOURLY")) {
                generatefileHourly();
                filename = fileConfig.getFileName();
                fineNameGenerator.sendFileName(filename);
                log.info(filename);
            } else if (fileConfig.getSchedule().toString().equals("MONTHLY")) {
//                    DayOfm = fileConfig.getMonthly().getDayOfMonth();
//                    month = fileConfig.getMonthly().getMonth();
//                    minutes = fileConfig.getMonthly().getMinutes();
//                    hour = fileConfig.getMonthly().getHour();
                generatefileMonthly();
                filename = fileConfig.getFileName();
                fineNameGenerator.sendFileName(filename);
                log.info(filename);
            } else if (fileConfig.getSchedule().toString().equals("WEEKLY")) {
//                    DayOfWeek = fileDTO1.getWeekly().getDayOfWeek();
//                    hour = fileDTO1.getWeekly().getHour();
//                    minutes = fileDTO1.getWeekly().getMinutes();
//
                generatefileWeekly();
                filename = fileConfig.getFileName();
                fineNameGenerator.sendFileName(filename);
                log.info(filename);

            } else if (fileConfig.getSchedule().toString().equals("DAILY")) {
//                    Day = fileDTO1.getDaily().getDay();
//                    hour = fileDTO1.getDaily().getHour();
//                    minutes = fileDTO1.getDaily().getMinutes();
                generatefileDaily();
                filename = fileConfig.getFileName();
                fineNameGenerator.sendFileName(filename);
                log.info(filename);
            } else {
                throw new FileNotFoundException("Invalid file and was not found");
            }
        }
    }


    @Scheduled(cron = "0 * * * * *")
    private void generatefileDaily() {
    }

    @Scheduled(cron = "0 * * * * *")
    private void generatefileWeekly() {

    }

    @Scheduled(cron = "0 * * * * *")
    private void generatefileMonthly() {

    }

    @Scheduled(cron = "0 * * * * *")
    public void generatefileHourly() {

    }

}
