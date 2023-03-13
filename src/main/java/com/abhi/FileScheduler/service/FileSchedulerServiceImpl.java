package com.abhi.FileScheduler.service;

import com.abhi.FileScheduler.externalsvc.fileconfigsvc.FileConfigService;
import com.abhi.FileScheduler.externalsvc.fileconfigsvc.FileDTO;
import com.abhi.FileScheduler.repo.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDate;
import java.util.List;

public class FileSchedulerServiceImpl {
    @Autowired
    FileConfigService fileConfigService;
    @Autowired
    FileRepository fileRepository;

    private FileDTO filedaily;


    @Scheduled(cron = "0 * * * * *")
    public void fetchFromfileConfig() {

        List<FileDTO> fileDTOS = fileConfigService.getAllAccounts();
        for (FileDTO fileDTO : fileDTOS) {
            if (fileDTO.getSchedule().equals("HOURLY")) {
                int hour = fileDTO.getDaily().getHour();
                 filedaily = fileRepository.findByDaily(LocalDate.ofEpochDay(hour));
                generatefileHourly();


            }
        }


    }

    @Scheduled(fixedRate = 60 * 60 * 1000)
    public void generatefileHourly() {
        String filename = filedaily.getFileName();


    }
}
