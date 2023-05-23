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
            // Verificar si se proporcionó un archivo
            if (file.isEmpty()) {
                return new ResponseEntity<>("No se proporcionó ningún archivo.", HttpStatus.BAD_REQUEST);
            }

            // Guardar el archivo
            Path directorioDestino = Paths.get(ruta);
            File directorio = directorioDestino.toFile();

            // Crear el directorio si no existe
            if (!directorio.exists()) {
                directorio.mkdirs();
            }
            file.transferTo(Path.of(directorio + "/" + file.getOriginalFilename()));

            /*file.transferTo(new File("/guardarficheros/" + ruta));*/
            contador = contador + 1;
            javier.correa.block11uploaddownloadfiles.domain.File archivoDatos = new javier.correa.block11uploaddownloadfiles.domain.File();
            archivoDatos.setFileName(file.getOriginalFilename());
            archivoDatos.setFecha(LocalDateTime.now().toString());
            archivoDatos.setCategoria(StringUtils.getFilenameExtension(archivoDatos.getFileName()));
            archivoDatos.setId(contador);
            archivoDatos.setRuta(ruta);
            fileRepository.save(archivoDatos);
            return ResponseEntity.status(HttpStatus.CREATED).body(archivoDatos);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Error al subir el fichero");
        }
    }
}
