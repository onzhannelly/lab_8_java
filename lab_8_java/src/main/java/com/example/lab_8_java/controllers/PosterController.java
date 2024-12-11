package com.example.lab_8_java.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.lab_8_java.dto.PosterDto;
import com.example.lab_8_java.service.PosterService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/posters/")
public class PosterController {
    private final PosterService posterService;


    public PosterController(PosterService posterService) {
        this.posterService = posterService;
    }

    @PostMapping
    public ResponseEntity<PosterDto> createPoster(@RequestPart MultipartFile file,
                                                  @RequestPart String posterDto) throws IOException {
        PosterDto dto = convertToPosterDto(posterDto);
        return new ResponseEntity<>(posterService.addPoster(dto, file), HttpStatus.CREATED);
    }

    @GetMapping("/{posterId}")
    public ResponseEntity<PosterDto> getPosterHandler(@PathVariable Integer posterId) {

        return ResponseEntity.ok(posterService.getPoster(Long.valueOf(posterId)));
    }

    @GetMapping("/")
    public ResponseEntity<List<PosterDto>> getAllPostersHandler() {
        return ResponseEntity.ok(posterService.getAllPosters());
    }

    @PutMapping("/{posterId}")
    public ResponseEntity<PosterDto> updatePosterHandler(@PathVariable Long posterId,
                                                       @RequestPart String posterDto) throws IOException {
        PosterDto dto = convertToPosterDto(posterDto);
        return ResponseEntity.ok(posterService.updatePoster(posterId, dto));
    }

    private PosterDto convertToPosterDto(String posterDtoObj) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(posterDtoObj, PosterDto.class);
    }

    @DeleteMapping("/{posterId}")
    public ResponseEntity<Void> deletePosterHandler(@PathVariable Long posterId) {
        posterService.deletePoster(posterId);
        return ResponseEntity.noContent().build();
    }

}
