package com.santiago.webRosa.Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Publication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String publisherName;
    @Column(name = "date")
    private String publicationDate;
    private String urlImage;
}