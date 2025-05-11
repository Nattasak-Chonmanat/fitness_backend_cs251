package com.example.demo.DTOs;

import com.example.demo.model.Trainer;
import io.swagger.v3.oas.annotations.media.Schema;

public class TrainerSummaryDTO {

    @Schema(description = "Trainer's id", example = "101")
    private Long id;
    @Schema(description = "Trainer's full name.", example = "นายเทรนเนอร์ ชอบโดดงาน")
    private String fullName;
    @Schema(description = "Trainer's speciality.", example = "Cardio, Weight training")
    private String speciality;
    @Schema(description = "Trainer's average rating.", example = "4.2")
    private Float rating;


    public TrainerSummaryDTO() {
    }

    public TrainerSummaryDTO(Trainer t) {
        this.rating = t.getRating();
        this.fullName = t.getFullName();
        this.speciality = t.getSpeciality();
        this.id = t.getId();
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public Float getRating() {
        return rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Long getTrainerId() {
        return id;
    }

    public void setTrainerId(Long id) {
        this.id = id;
    }
}


