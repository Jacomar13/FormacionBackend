package javier.correa.block11uploaddownloadfiles.application;


import javier.correa.block11uploaddownloadfiles.controller.dto.FileInputDto;
import javier.correa.block11uploaddownloadfiles.controller.dto.FileOutputDto;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    Object addFile(String ruta, MultipartFile file) throws Exception;
}
