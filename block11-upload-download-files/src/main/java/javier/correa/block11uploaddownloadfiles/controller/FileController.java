package javier.correa.block11uploaddownloadfiles.controller;

import javier.correa.block11uploaddownloadfiles.application.FileService;
import javier.correa.block11uploaddownloadfiles.controller.dto.FileOutputDto;
import javier.correa.block11uploaddownloadfiles.repositories.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping("/")
public class FileController {
    @Autowired
    FileService fileService;
    @Autowired
    FileRepository fileRepository;
    int contador= 0;

    @RequestMapping(method = POST, consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity addFile(@RequestParam(name = "ruta", defaultValue = "guardarficheros", required = false) String ruta, @RequestParam("file")MultipartFile file){
        try {
            return new ResponseEntity<>(fileService.addFile(ruta, file), HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
