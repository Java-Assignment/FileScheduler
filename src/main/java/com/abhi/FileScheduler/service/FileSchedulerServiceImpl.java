package com.abhi.FileScheduler.service;

import com.abhi.FileScheduler.dto.FileDTO;
import com.abhi.FileScheduler.mapper.FileMapper;
import com.abhi.FileScheduler.repo.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FileSchedulerServiceImpl {
    @Autowired
    private FileMapper filemapper;
    @Autowired
    private FileRepository fileRepository;
    private FileDTO fileDTO;

    private String filename;
    private FileDTO fileDTO1;
    private int DayOfm;
    private int DayOfweek;
    private int month;
    private int minutes;
    private int hour;
    private int Day;


    @Scheduled(cron = "0 * * * * *")
    public void fetchFromfileConfig() throws FileNotFoundException {
        List<FileDTO> fileDTOS;
        if (fileDTO.getSchedule().equals("HOURLY")) {
            fileDTOS = fileRepository.findAll().stream().map(a -> filemapper.convertFileConfigToFileDTO(a)).collect(Collectors.toList());
            for (FileDTO fileDTO1 : fileDTOS) {
                generatefileHourly();
            }
        } else if (fileDTO.getSchedule().equals("MONTHLY")) {
            fileDTOS = fileRepository.findAll().stream().map(a -> filemapper.convertFileConfigToFileDTO(a)).collect(Collectors.toList());
            for (FileDTO fileDTO1 : fileDTOS) {
                DayOfm = fileDTO1.getMonthly().getDayOfMonth();
                month = fileDTO1.getMonthly().getMonth();
                minutes = fileDTO1.getMonthly().getMinutes();
                hour = fileDTO1.getMonthly().getHour();
                generatefileMonthly();
            }
        } else if (fileDTO.getSchedule().equals("WEEKLY")) {
            fileDTOS = fileRepository.findAll().stream().map(a -> filemapper.convertFileConfigToFileDTO(a)).collect(Collectors.toList());
            for (FileDTO fileDTO1 : fileDTOS) {
                DayOfweek = fileDTO1.getWeekly().getDayOfWeek();
                hour = fileDTO1.getWeekly().getHour();
                minutes = fileDTO1.getWeekly().getMinutes();
                generatefileWeekly();
            }
        } else if (fileDTO.getSchedule().equals("DAILY")) {
            fileDTOS = fileRepository.findAll().stream().map(a -> filemapper.convertFileConfigToFileDTO(a)).collect(Collectors.toList());
            for (FileDTO fileDTO1 : fileDTOS) {
                Day = fileDTO1.getDaily().getDay();
                hour = fileDTO1.getDaily().getHour();
                minutes = fileDTO1.getDaily().getMinutes();
                generatefileDaily();
            }
        } else {
            throw new FileNotFoundException("Ivalid file and was not found");

        }

    }

    @Scheduled(cron = "0 minutes hour * * Day")
    private void generatefileDaily() {
        filename = fileDTO1.getFileName();
    }

    @Scheduled(cron = "0 minutes hour * * DayOfweek")
    private void generatefileWeekly() {
        filename = fileDTO1.getFileName();
    }

    @Scheduled(cron = "0 minutes hour DayOfm month * ")
    private void generatefileMonthly() {
        filename = fileDTO1.getFileName();
    }


    @Scheduled(fixedRate = 60 * 60 * 1000)
    public void generatefileHourly() {
        filename = fileDTO1.getFileName();
    }

}
