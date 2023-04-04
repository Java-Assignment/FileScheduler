package com.abhi.filescheduler.mapper;

import com.abhi.filescheduler.dto.FileDTO;
import com.abhi.filescheduler.entity.FileConfig;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-04-04T15:13:14+0530",
    comments = "version: 1.5.0.Final, compiler: javac, environment: Java 17.0.6 (Oracle Corporation)"
)
@Component
public class FileMapperImpl implements FileMapper {

    @Override
    public FileDTO convertFileConfigToFileDTO(FileConfig a) {
        if ( a == null ) {
            return null;
        }

        FileDTO fileDTO = new FileDTO();

        fileDTO.setFileName( a.getFileName() );
        fileDTO.setMonthly( a.getMonthly() );
        fileDTO.setWeekly( a.getWeekly() );
        fileDTO.setDaily( a.getDaily() );

        return fileDTO;
    }

    @Override
    public FileDTO convertFileConfTOFileDTO(FileConfig fileConfig) {
        if ( fileConfig == null ) {
            return null;
        }

        FileDTO fileDTO = new FileDTO();

        fileDTO.setFileName( fileConfig.getFileName() );
        fileDTO.setMonthly( fileConfig.getMonthly() );
        fileDTO.setWeekly( fileConfig.getWeekly() );
        fileDTO.setDaily( fileConfig.getDaily() );

        return fileDTO;
    }
}
