package javier.correa.block11uploaddownloadfiles.application;

import javier.correa.block11uploaddownloadfiles.controller.dto.FileInputDto;
import javier.correa.block11uploaddownloadfiles.controller.dto.FileOutputDto;
import javier.correa.block11uploaddownloadfiles.repositories.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
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
import java.util.Optional;

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

    @Override
    public Resource downloadFileById(int id) {
        Optional<javier.correa.block11uploaddownloadfiles.domain.File> fileOpt = fileRepository.findById(id);
        javier.correa.block11uploaddownloadfiles.domain.File file = fileOpt.orElseThrow(() -> new RuntimeException("No se ha podido encontrar el archivo"));

        Resource resource = new FileSystemResource(file.getRuta() + "\\" + file.getFileName());
        if (!resource.exists()) throw new RuntimeException("No se ha podido encontrar el archivo");
        return resource;
    }

    @Override
    public Resource downloadFileByName(String name) {
        Optional<javier.correa.block11uploaddownloadfiles.domain.File> fileOpt = fileRepository.findByFileName(name);
        javier.correa.block11uploaddownloadfiles.domain.File file = fileOpt.orElseThrow(() -> new RuntimeException("No se ha podido encontrar el archivo"));

        Resource resource = new FileSystemResource(file.getRuta() + "\\" + name);
        if (!resource.exists()) throw new RuntimeException("No se ha podido encontrar el archivo");
        return resource;
    }

    @Override
    public FileOutputDto getFileByName(String name) {
        Optional<javier.correa.block11uploaddownloadfiles.domain.File> fileOpt = fileRepository.findByFileName(name);
        javier.correa.block11uploaddownloadfiles.domain.File file = fileOpt.orElseThrow(() -> new RuntimeException("No se ha podido encontrar el archivo"));

        FileOutputDto fileDto = file.fileToOutputDto();
        return fileDto;
    }

    @Override
    public FileOutputDto getFileById(int id) {
        Optional<javier.correa.block11uploaddownloadfiles.domain.File> fileOpt = fileRepository.findById(id);
        javier.correa.block11uploaddownloadfiles.domain.File file = fileOpt.orElseThrow(() -> new RuntimeException("No se ha podido encontrar el archivo"));

        FileOutputDto fileDto = file.fileToOutputDto();
        return fileDto;
    }

}
