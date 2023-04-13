package com.kushal.backend.models;

import com.kushal.backend.entities.Image;
import com.kushal.backend.entities.Socials;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateProjectModel {
    private String title;
    private String service;
    private String author;
    private String client;
    private String startDate;
    private String endDate;
    private String url;
    private Socials socials;
    private List<Image> images;
}
