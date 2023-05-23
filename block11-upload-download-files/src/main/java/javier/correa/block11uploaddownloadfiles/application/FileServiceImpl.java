package javier.correa.block11uploaddownloadfiles.application;

import javier.correa.block11uploaddownloadfiles.controller.dto.FileInputDto;
import javier.correa.block11uploaddownloadfiles.controller.dto.FileOutputDto;
import javier.correa.block11uploaddownloadfiles.repositories.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;

@Service
public class FileServiceImpl implements FileService{
    @Autowired
    FileRepository fileRepository;


    @Override
    public Object addFile(String ruta, MultipartFile file) throws Exception {

            if (file.isEmpty()) {
                return new String ("No se proporcionó ningún archivo.");
            }

            // Guardar el archivo
            Path directorioDestino = Paths.get(ruta);
            File directorio = directorioDestino.toFile();

            // Crear el directorio si no existe
            if (!directorio.exists()) {
                directorio.mkdirs();
            }
            file.transferTo(Path.of(directorio + "/" + file.getOriginalFilename()));


            javier.correa.block11uploaddownloadfiles.domain.File archivoDatos = new javier.correa.block11uploaddownloadfiles.domain.File();
            archivoDatos.setFileName(file.getOriginalFilename());
            archivoDatos.setFecha(LocalDateTime.now().toString());
            archivoDatos.setCategoria(StringUtils.getFilenameExtension(archivoDatos.getFileName()));
            archivoDatos.setRuta(ruta);
            fileRepository.save(archivoDatos);
            return archivoDatos;
    }
}
