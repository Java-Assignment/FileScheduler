package com.abhi.filescheduler.service;

import com.abhi.filescheduler.dto.FileConfigDTO;
import com.abhi.filescheduler.entity.FileConfig;
import com.abhi.filescheduler.externalsvc.FineNameGenerator;
import com.abhi.filescheduler.mapper.FileMapper;
import com.abhi.filescheduler.repo.FileRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

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
    private FileConfigDTO fileConfigDTO;

    private String filename;
    private FileConfig fileConfig;


    @Scheduled(cron = "0 */1 * * * *")/* This is used to create  files every one minute*/
    public void FileScheduler() throws JsonProcessingException {
        List<FileConfig> fileDTOS = fileRepository.findAll();
        log.info("fileDTOS count:" + fileDTOS.size());
        for (FileConfig fileConfig : fileDTOS) {
            log.info("checking file: " + fileConfig);

            if (Objects.nonNull(fileConfig.getMonthly())) {
                log.info("fileConfig: "+fileConfig.getFileName()+" has monthly schedule.");
                checkTimeToRunMonthly(fileConfig);
            }
            if (Objects.nonNull(fileConfig.getWeekly())) {
                log.info("weekly");
                generatefileWeekly(fileConfig);
            }
            if (Objects.nonNull(fileConfig.getDaily())) {
                log.info("daily");
                generatefileDaily(fileConfig);
            }
            if (Objects.nonNull(fileConfig.getHourly())){
                log.info("fileConfig has hourly schedule. check if file needs to be generated. ");
                checkTimeToRunHourly(fileConfig);
            }
        }
    }


    private void generatefileDaily(FileConfig fileConfig) throws JsonProcessingException {
        log.info("daily");
        LocalDateTime localDateTime = LocalDateTime.now();
        log.info(String.valueOf(localDateTime));
        if (localDateTime.getHour() == fileConfig.getDaily().getHour() && localDateTime.getMinute() == fileConfig.getDaily().getMinutes()) {
            filename = fileConfig.getFileName();
            log.info(filename);
            FileConfigDTO fileConfigDTO1 = filemapper.convertFileConfTOFileDTO(fileConfig);
            fineNameGenerator.callFileGenerationService(fileConfigDTO1);
        }

    }

    private void generatefileWeekly(FileConfig fileConfig) throws JsonProcessingException {
        log.info("weekly");
        LocalDateTime localDateTime = LocalDateTime.now();
        if (localDateTime.getDayOfWeek().getValue() == fileConfig.getWeekly().getDayOfWeek() && localDateTime.getHour() == fileConfig.getWeekly().getHour() && localDateTime.getMinute() == fileConfig.getWeekly().getMinutes()) {
            filename = fileConfig.getFileName();
            log.info(filename);
            FileConfigDTO fileConfigDTO1 = filemapper.convertFileConfTOFileDTO(fileConfig);
            fineNameGenerator.callFileGenerationService(fileConfigDTO1);
        }
    }

    private void checkTimeToRunMonthly(FileConfig fileConfig) throws JsonProcessingException {
        log.info("checking if its time to run file : "+fileConfig.getFileName()+" file config details:"+fileConfig);

        LocalDateTime localDateTime = LocalDateTime.now();
        if (localDateTime.getDayOfMonth() == fileConfig.getMonthly().getDayOfMonth()
                && localDateTime.getHour() == fileConfig.getMonthly().getHour()
                && localDateTime.getMinute() == fileConfig.getMonthly().getMinutes()) {
            log.info("Generating file: "+fileConfig.getFileName());
            FileConfigDTO fileConfigDTO = filemapper.convertFileConfTOFileDTO(fileConfig);
            fineNameGenerator.callFileGenerationService(fileConfigDTO);
        }

    }

    public void checkTimeToRunHourly(FileConfig fileConfig) throws JsonProcessingException {
        LocalDateTime localDateTime = LocalDateTime.now();
        if (localDateTime.getMinute() == fileConfig.getHourly().getMin()) {
            log.info("file: " + filename + " ready to be generated. ");
            filename = fileConfig.getFileName();
            FileConfigDTO fileConfigDTO1 = filemapper.convertFileConfTOFileDTO(fileConfig);
            fineNameGenerator.callFileGenerationService(fileConfigDTO1);
        }

    }

}
