package javier.correa.block7crudvalidation.domain;

public class Technology {
    private int id_technology;

    private enum branch {
        BACK,
        FRONT,
        DEVOPS,
        UNASIGNED
    }
    private String description;
}
