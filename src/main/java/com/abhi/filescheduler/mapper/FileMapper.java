package com.abhi.filescheduler.mapper;

import com.abhi.filescheduler.dto.FileConfigDTO;
import com.abhi.filescheduler.entity.FileConfig;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FileMapper {

    FileConfigDTO convertFileConfTOFileDTO(FileConfig fileConfig);
}
