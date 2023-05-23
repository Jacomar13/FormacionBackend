package javier.correa.block11uploaddownloadfiles.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class File {

    private int id;
    private String fileName;
    private String fecha;

}
