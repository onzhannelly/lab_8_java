package com.example.lab_8_java.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Poster {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long posterId;

    @NotBlank(message = "Movie's poster couldn't be empty!")
    private String poster;

    public Poster(Long posterId, String poster) {
        this.posterId = posterId;
        this.poster = poster;
    }

    public Poster() {

    }

    public Long getPosterId() {
        return posterId;
    }

    public void setPosterId(Long posterId) {
        this.posterId = posterId;
    }

    public @NotBlank(message = "poster couldn't be empty!") String getPoster() {
        return poster;
    }

    public void setPoster(@NotBlank(message = "poster couldn't be empty!") String poster) {
        this.poster = poster;
    }
}
