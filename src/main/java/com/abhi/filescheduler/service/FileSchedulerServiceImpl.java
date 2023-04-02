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


    @Scheduled(cron = "0 */1 * * * *")
    public void FileScheduler() throws FileNotFoundException, JsonProcessingException {
        List<FileConfig> fileDTOS=fileRepository.findAll();
        for (FileConfig fileConfig : fileDTOS) {
            if (Objects.nonNull(fileConfig.isHourly())) {
                generatefileHourly(fileConfig);

            } else if (Objects.nonNull(fileConfig.isMonthly())) {
                generatefileMonthly(fileConfig);
            } else if (Objects.nonNull(fileConfig.isWeekly())) {
                generatefileWeekly(fileConfig);
            } else if (Objects.nonNull(fileConfig.isDaily())) {
                generatefileDaily(fileConfig);
            } else {
                throw new FileNotFoundException("Invalid file and was not found");
            }
        }
    }


    private void generatefileDaily(FileConfig fileConfig) throws JsonProcessingException {
        LocalDateTime lastDateTime=null;
        while (true){
            LocalDateTime localDateTime=LocalDateTime.now();
            if(localDateTime==null || !localDateTime.equals(lastDateTime)){
                if(localDateTime.getHour()==fileConfig.getDaily().getHour() && localDateTime.getMinute()==fileConfig.getDaily().getMinutes()){
                    filename = fileConfig.getFileName();
                    fineNameGenerator.sendFile(fileDTO);
                    log.info(filename);
                }
            }
            lastDateTime=localDateTime;
        }
    }

    private void generatefileWeekly(FileConfig fileConfig) throws JsonProcessingException {
        LocalDateTime lastDateTime=null;
        while (true){
            LocalDateTime localDateTime=LocalDateTime.now();
            if(lastDateTime==null||!localDateTime.equals(lastDateTime)){
                if(localDateTime.getDayOfWeek().getValue() == fileConfig.getWeekly().getDayOfWeek() && localDateTime.getHour() == fileConfig.getWeekly().getHour() && localDateTime.getMinute() ==fileConfig.getWeekly().getMinutes() ){
                    filename = fileConfig.getFileName();
                    fineNameGenerator.sendFile(fileDTO);
                    log.info(filename);
                }
            }
            lastDateTime=localDateTime;
        }

    }

    private void generatefileMonthly(FileConfig fileConfig) throws JsonProcessingException {
        LocalDateTime lastDateTime=null;
        while (true){
            LocalDateTime localDateTime=LocalDateTime.now();
            if(lastDateTime==null || !localDateTime.equals(lastDateTime)) {
                if (localDateTime.getDayOfMonth() == fileConfig.getMonthly().getDayOfMonth() && localDateTime.getHour() == fileConfig.getMonthly().getHour() && localDateTime.getMinute() == fileConfig.getMonthly().getMinutes()) {
                    filename = fileConfig.getFileName();
                    fineNameGenerator.sendFile(fileDTO);
                    log.info(filename);
                }
            }
            lastDateTime=localDateTime;
        }

    }

    public void generatefileHourly(FileConfig fileConfig) throws JsonProcessingException {
        LocalDateTime lastDateTime=null;
        while (true){
            LocalDateTime localDateTime=LocalDateTime.now();
            if(lastDateTime==null || !localDateTime.equals(lastDateTime)) {
                if ( localDateTime.getHour() == fileConfig.getDaily().getHour()) {
                    filename = fileConfig.getFileName();
                    fineNameGenerator.sendFile(fileDTO);
                    log.info(filename);
                }
            }
            lastDateTime=localDateTime;
        }
    }

}
