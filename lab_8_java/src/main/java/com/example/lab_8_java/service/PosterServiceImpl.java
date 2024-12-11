package com.example.lab_8_java.service;

import org.beko.lab8.dto.PosterDto;
import org.beko.lab8.entity.Poster;
import org.beko.lab8.repository.PosterRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class PosterServiceImpl implements PosterService {
    private final PosterRepository posterRepository;

    private final FileService fileService;

    public PosterServiceImpl(PosterRepository posterRepository, FileService fileService) {
        this.posterRepository = posterRepository;
        this.fileService = fileService;
    }

    @Value("${project.poster}")
    private String path;

    @Value("${base.url}")
    private String baseUrl;

    @Override
    public PosterDto addPoster(PosterDto posterDto, MultipartFile file) throws IOException {

        String uploadedFile = fileService.uploadFile(path, file);

        posterDto.setPoster(uploadedFile);

        Poster poster = new Poster(
                posterDto.getPosterId(),
                posterDto.getPoster()
        );

        Poster savedPoster = posterRepository.save(poster);

        String posterUrl = baseUrl + "/file/" + uploadedFile;

        return new PosterDto(
                savedPoster.getPosterId(),
                savedPoster.getPoster(),
                posterUrl
        );
    }

    @Override
    public PosterDto updatePoster(Long posterId, PosterDto posterDto) throws IOException {

        Poster poster = posterRepository.findById(posterId).orElseThrow(() -> new RuntimeException("Movie not found"));

        poster.setPoster(posterDto.getPoster());

        Poster updatedPoster = posterRepository.save(poster);
        String posterUrl = baseUrl + "/file/" + updatedPoster.getPoster();

        return new PosterDto(
                updatedPoster.getPosterId(),
                updatedPoster.getPoster(),
                posterUrl
        );
    }

    @Override
    public PosterDto getPoster(Long posterId) {

        Poster poster = posterRepository.findById(posterId).orElseThrow(() -> new RuntimeException("Poster not found"));

        String posterUrl = baseUrl + "/file/" + poster.getPoster();

        return new PosterDto(
                poster.getPosterId(),
                poster.getPoster(),
                posterUrl
        );
    }

    @Override
    public List<PosterDto> getAllPosters() {

        List<Poster> posters = posterRepository.findAll();

        List<PosterDto> posterDtos = new ArrayList<>();
        for (Poster poster : posters) {
            String posterUrl = baseUrl + "/file/" + poster.getPoster();
            PosterDto posterDto = new PosterDto(
                    poster.getPosterId(),
                    poster.getPoster(),
                    posterUrl
            );

            posterDtos.add(posterDto);
        }

        return posterDtos;
    }

    @Override
    public void deletePoster(Long posterId) {

        Poster posterToDelete = posterRepository.findById(posterId)
                .orElseThrow(() -> new RuntimeException("Poster not found"));

        posterRepository.delete(posterToDelete);
    }
}
