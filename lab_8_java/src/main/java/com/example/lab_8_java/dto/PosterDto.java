package com.example.lab_8_java.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PosterDto {
    private Long posterId;

    @NotBlank(message = "Please private poster!")
    private String poster;

    @NotBlank(message = "Please private poster's URL!")
    private String posterUrl;

    public PosterDto(Long posterId, String poster, String posterUrl) {
        this.posterId = posterId;
        this.poster = poster;
        this.posterUrl = posterUrl;
    }
    public PosterDto(){}

    public Long getPosterId() {
        return posterId;
    }

    public void setPosterId(Long posterId) {
        this.posterId = posterId;
    }

    public @com.example.lab_8_java.dto.NotBlank(message = "Please private poster!") String getPoster() {
        return poster;
    }

    public void setPoster(@NotBlank(message = "Please private poster!") String poster) {
        this.poster = poster;
    }

    public @NotBlank(message = "Please private poster's URL!") String getPosterUrl() {
        return posterUrl;
    }

    public void setPosterUrl(@NotBlank(message = "Please private poster's URL!") String posterUrl) {
        this.posterUrl = posterUrl;
    }
}
