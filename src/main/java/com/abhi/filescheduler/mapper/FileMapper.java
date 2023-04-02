package com.abhi.filescheduler.mapper;

import com.abhi.filescheduler.dto.FileDTO;
import com.abhi.filescheduler.entity.FileConfig;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FileMapper {

    FileDTO convertFileConfigToFileDTO(FileConfig a);
}
