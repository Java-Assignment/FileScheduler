package com.abhi.filescheduler.mapper;

import com.abhi.filescheduler.dto.FileDTO;
import com.abhi.filescheduler.entity.FileConfig;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-04-03T20:49:47+0530",
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
        fileDTO.setIsHourly( a.getIsHourly() );
        fileDTO.setIsMonthly( a.getIsMonthly() );
        fileDTO.setIsWeekly( a.getIsWeekly() );
        fileDTO.setIsDaily( a.getIsDaily() );
        fileDTO.setMonthly( a.getMonthly() );
        fileDTO.setWeekly( a.getWeekly() );
        fileDTO.setDaily( a.getDaily() );

        return fileDTO;
    }
}
