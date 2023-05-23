package javier.correa.block11uploaddownloadfiles.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@AllArgsConstructor
public class FileInputDto {
    private String ruta;
    private MultipartFile document;
}