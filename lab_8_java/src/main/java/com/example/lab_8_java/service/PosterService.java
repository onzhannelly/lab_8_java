package com.example.lab_8_java.service;

import com.example.lab_8_java.dto.PosterDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface PosterService {
    PosterDto addPoster(PosterDto posterDto, MultipartFile file) throws IOException;
    PosterDto updatePoster(Long posterId, PosterDto posterDto) throws IOException;
    PosterDto getPoster(Long posterId);
    List<PosterDto> getAllPosters();
    void deletePoster(Long posterId);

}
