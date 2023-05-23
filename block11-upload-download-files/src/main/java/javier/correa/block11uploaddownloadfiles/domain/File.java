package javier.correa.block11uploaddownloadfiles.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import javier.correa.block11uploaddownloadfiles.controller.dto.FileOutputDto;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class File {
    @Id
    @GeneratedValue
    private int id;
    private String fileName;
    private String categoria;
    private String fecha;

    public FileOutputDto fileToOutputDto(){
        return new FileOutputDto(this.id, this.fileName, this.categoria, this.fecha);
    }
}
