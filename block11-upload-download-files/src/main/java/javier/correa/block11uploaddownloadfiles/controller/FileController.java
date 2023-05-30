package javier.correa.block11uploaddownloadfiles.controller;

import javier.correa.block11uploaddownloadfiles.application.FileService;
import javier.correa.block11uploaddownloadfiles.controller.dto.FileOutputDto;
import javier.correa.block11uploaddownloadfiles.domain.File;
import javier.correa.block11uploaddownloadfiles.repositories.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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


    @GetMapping("/downloadFileById/{id}")
    public ResponseEntity obtainFileById(@PathVariable int id){
       try{
           Resource resource = fileService.downloadFileById(id);
           return ResponseEntity.ok()
                   .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + resource.getFilename())
                   .contentType(MediaType.APPLICATION_OCTET_STREAM)
                   .body(resource);
       } catch (Exception e) {
           throw new RuntimeException(e);
       }
    }
    @GetMapping("/downloadFileByName/{name}")
    public ResponseEntity obtainFileByName(@PathVariable String name){
        try{
            Resource resource = fileService.downloadFileByName(name);
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + resource.getFilename())
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .body(resource);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/getFileById/{id}")
    public ResponseEntity<?> getFileById(@PathVariable int id) {
        return new ResponseEntity<>(fileService.getFileById(id), HttpStatus.OK);
    }
    @GetMapping("/getFileByName/{name}")
    public ResponseEntity<FileOutputDto> getFileByName(@PathVariable String name) {
        return new ResponseEntity<>(fileService.getFileByName(name), HttpStatus.OK);
    }

}
