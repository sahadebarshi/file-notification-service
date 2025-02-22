package com.file.notify.model;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "FILE_ACCESS")
@Data
public class FileAccessModel implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    @Column(name = "file_name")
    String fileName;
    @Column(name = "access_level")
    String accessLevel;
}
