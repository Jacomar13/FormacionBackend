package javier.correa.block11uploaddownloadfiles.application;


import javier.correa.block11uploaddownloadfiles.controller.dto.FileOutputDto;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    Object addFile(String ruta, MultipartFile file) throws Exception;

    Resource downloadFileById(int id);

    Resource downloadFileByName(String name);

    FileOutputDto getFileByName(String name);

    FileOutputDto getFileById(int id);
}
