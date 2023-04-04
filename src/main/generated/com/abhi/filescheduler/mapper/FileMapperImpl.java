package com.abhi.filescheduler.mapper;

import com.abhi.filescheduler.dto.FileConfigDTO;
import com.abhi.filescheduler.entity.FileConfig;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-04-04T23:34:42+0530",
    comments = "version: 1.5.0.Final, compiler: javac, environment: Java 17.0.6 (Oracle Corporation)"
)
@Component
public class FileMapperImpl implements FileMapper {

    @Override
    public FileConfigDTO convertFileConfTOFileDTO(FileConfig fileConfig) {
        if ( fileConfig == null ) {
            return null;
        }

        FileConfigDTO fileConfigDTO = new FileConfigDTO();

        fileConfigDTO.setFileName( fileConfig.getFileName() );
        fileConfigDTO.setMonthly( fileConfig.getMonthly() );
        fileConfigDTO.setWeekly( fileConfig.getWeekly() );
        fileConfigDTO.setDaily( fileConfig.getDaily() );
        fileConfigDTO.setHourly( fileConfig.getHourly() );

        return fileConfigDTO;
    }
}
