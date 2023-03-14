package com.abhi.FileScheduler.mapper;

import com.abhi.FileScheduler.dto.FileDTO;
import com.abhi.FileScheduler.entity.FileConfig;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FileMapper {

    FileDTO convertFileConfigToFileDTO(FileConfig a);
}
