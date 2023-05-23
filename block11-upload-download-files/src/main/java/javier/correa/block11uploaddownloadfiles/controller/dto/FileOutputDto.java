package javier.correa.block11uploaddownloadfiles.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@AllArgsConstructor
public class FileOutputDto {
    private int id;
    private String fileName;
    private String categoria;
    private String fecha;
    private String ruta;
}
