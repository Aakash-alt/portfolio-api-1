package com.kushal.backend.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Project {

    @Id
    @SequenceGenerator(
            name = "project_sequence",
            sequenceName = "project_sequence",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "project_sequence")
    private Long projectId;
    private String title;
    private String description;
    private String author;
    private String client;
    private String startDate;
    private String endDate;
    private String url;

    public Project(String title, String description, String author, String client, String startDate, String endDate, String url) {
        this.title = title;
        this.description = description;
        this.author = author;
        this.client = client;
        this.startDate = startDate;
        this.endDate = endDate;
        this.url = url;
    }

    @OneToMany(
            cascade = CascadeType.ALL
    )
    @JoinColumn(
            name = "project_id",
            referencedColumnName = "projectId"
    )
    private List<Image> images;
}
