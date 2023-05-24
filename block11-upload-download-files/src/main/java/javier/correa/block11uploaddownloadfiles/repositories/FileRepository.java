package javier.correa.block11uploaddownloadfiles.repositories;

import javier.correa.block11uploaddownloadfiles.domain.File;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface FileRepository extends JpaRepository<File, Integer> {
    Optional<File> findByFileName(String name);
}
