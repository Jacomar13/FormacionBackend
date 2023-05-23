package javier.correa.block11uploaddownloadfiles.repositories;

import javier.correa.block11uploaddownloadfiles.domain.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRepository extends JpaRepository<File, Integer> {
}
