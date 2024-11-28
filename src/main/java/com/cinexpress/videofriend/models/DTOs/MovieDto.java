package com.cinexpress.videofriend.models.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MovieDto {
    private String title;
    private String format;
    private String genre;
    private String language;
    private Boolean availability;
    private Integer year;
    
}
