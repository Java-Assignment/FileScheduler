package com.abhi.FileScheduler.service;

import com.abhi.FileScheduler.externalsvc.FileConfigService;
import com.abhi.FileScheduler.externalsvc.dto.FileDTO;
import org.springframework.beans.factory.annotation.Autowired;

public class FileSchedulerServiceImpl {
    @Autowired
    FileConfigService fileConfigService;

     private String date_string = "2023-03-09";

    public void fetchFromfileConfig(){

        FileDTO fileDTO=fileConfigService.getAccountByDate(date_string);
    }
}
