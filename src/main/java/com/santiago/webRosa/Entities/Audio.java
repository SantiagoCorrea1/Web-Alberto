package com.santiago.webRosa.Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Audio {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String publisherName;
    @Column(name = "date")
    private String publicationDate;
    private String ulrAudio;
}