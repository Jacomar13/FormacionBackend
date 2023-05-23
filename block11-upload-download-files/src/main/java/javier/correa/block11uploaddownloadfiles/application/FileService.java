package javier.correa.block11uploaddownloadfiles.application;


import javier.correa.block11uploaddownloadfiles.controller.dto.FileInputDto;
import javier.correa.block11uploaddownloadfiles.controller.dto.FileOutputDto;

public interface FileService {
    FileOutputDto addFile(FileInputDto fileInputDto) throws Exception;
}
