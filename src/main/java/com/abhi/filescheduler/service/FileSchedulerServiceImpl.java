package com.abhi.filescheduler.service;

import com.abhi.filescheduler.dto.FileDTO;
import com.abhi.filescheduler.entity.FileConfig;
import com.abhi.filescheduler.externalsvc.FineNameGenerator;
import com.abhi.filescheduler.mapper.FileMapper;
import com.abhi.filescheduler.repo.FileRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

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


    @Scheduled(cron = "0 */1 * * * *")/* This is used to create  files every one minute*/
    public void FileScheduler() throws FileNotFoundException, JsonProcessingException {
        List<FileConfig> fileDTOS=fileRepository.findAll();
        log.info(String.valueOf(fileDTOS.size()));
        log.info("before for loop");
        for (FileConfig fileConfig : fileDTOS) {
            log.info("inside for loop");
//            log.info(String.valueOf(!fileConfig.getIsHourly()));
            if (Objects.nonNull(fileConfig.getDaily().getHour()))
                log.info("hourly ");{
                generatefileHourly(fileConfig);
            }
            if (Objects.nonNull(fileConfig.getMonthly())) {
                log.info("monthly ");
                generatefileMonthly(fileConfig);
            }
            if (Objects.nonNull(fileConfig.getWeekly())) {
                log.info("weekly");
                generatefileWeekly(fileConfig);
            }
            if (Objects.nonNull(fileConfig.getDaily())) {
                log.info("daily");
                generatefileDaily(fileConfig);
            }
        }
    }


    private void generatefileDaily(FileConfig fileConfig) throws JsonProcessingException {
        log.info("daily");
        LocalDateTime localDateTime=LocalDateTime.now();
        log.info(String.valueOf(localDateTime));
                if(localDateTime.getHour()==fileConfig.getDaily().getHour() && localDateTime.getMinute()==fileConfig.getDaily().getMinutes()){
                    filename = fileConfig.getFileName();
                    log.info(filename);
                    FileDTO fileDTO1=filemapper.convertFileConfTOFileDTO(fileConfig);
                    fineNameGenerator.sendFile(fileDTO1);
                }

    }

    private void generatefileWeekly(FileConfig fileConfig) throws JsonProcessingException {
        log.info("weekly");
        LocalDateTime localDateTime=LocalDateTime.now();
                if(localDateTime.getDayOfWeek().getValue() == fileConfig.getWeekly().getDayOfWeek() && localDateTime.getHour() == fileConfig.getWeekly().getHour() && localDateTime.getMinute() ==fileConfig.getWeekly().getMinutes() ){
                    filename = fileConfig.getFileName();
                    log.info(filename);
                    FileDTO fileDTO1=filemapper.convertFileConfTOFileDTO(fileConfig);
                    fineNameGenerator.sendFile(fileDTO1);
                }
    }

    private void generatefileMonthly(FileConfig fileConfig) throws JsonProcessingException {
        log.info("monthly");
        LocalDateTime localDateTime=LocalDateTime.now();
                if (localDateTime.getDayOfMonth() == fileConfig.getMonthly().getDayOfMonth() && localDateTime.getHour() == fileConfig.getMonthly().getHour() && localDateTime.getMinute() == fileConfig.getMonthly().getMinutes()) {
                    filename = fileConfig.getFileName();
                    log.info(filename);
                    FileDTO fileDTO1=filemapper.convertFileConfTOFileDTO(fileConfig);
                    fineNameGenerator.sendFile(fileDTO1);
                }

    }

    public void generatefileHourly(FileConfig fileConfig) throws JsonProcessingException {
        log.info("Hourly");
            LocalDateTime localDateTime=LocalDateTime.now();
                if ( localDateTime.getHour() == fileConfig.getDaily().getHour()) {
                    filename = fileConfig.getFileName();
                    log.info(filename);
                    FileDTO fileDTO1=filemapper.convertFileConfTOFileDTO(fileConfig);
                    fineNameGenerator.sendFile(fileDTO1);
                }

    }

}
