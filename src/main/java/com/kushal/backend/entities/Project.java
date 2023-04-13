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
    private String service;
    private String author;
    private String client;
    private String startDate;
    private String endDate;
    private String url;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "social_id", referencedColumnName = "id")
    private Socials socials;

    public Project(
            String title,
            String service,
            String author,
            String client,
            String startDate,
            String endDate,
            String url,
            Socials socials
    ) {
        this.title = title;
        this.service = service;
        this.author = author;
        this.client = client;
        this.startDate = startDate;
        this.endDate = endDate;
        this.url = url;
        this.socials = socials;
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
