package com.kushal.backend.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String path;
    private String name;
    private String contentType;

    public Image(String path, String name, String contentType) {
        this.path = path;
        this.name = name;
        this.contentType = contentType;
    }
}
