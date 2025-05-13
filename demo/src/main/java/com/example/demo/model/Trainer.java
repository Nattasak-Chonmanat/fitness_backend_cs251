package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "trainer")
@Schema(description = "Trainer")
public class Trainer extends Employee{


    @Column(name = "speciality", nullable = false)
    @NotBlank(message = "Speciality is required.")
    @Schema(description = "Speciality", example = "Yoga")
    private String speciality;


    @Column(name = "rating")
    @Schema(hidden = true)
    @JsonIgnore
    private Float rating;

    @ManyToMany
    @JoinTable(
            name = "intendant",
            joinColumns = @JoinColumn(name = "trainer_id"),
            inverseJoinColumns = @JoinColumn(name = "class_id")
    )
    @Schema(hidden = true)
    private List<Class> classes = new ArrayList<>();

    @Schema(hidden = true)
    @OneToMany(mappedBy = "trainer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TRating> trainerRatings = new ArrayList<>();

    @OneToMany(mappedBy = "trainer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TrainerSchedule> trainerSchedules = new ArrayList<>();

    @Schema(hidden = true)
    @JsonIgnore
    public List<TrainerSchedule> getTrainerSchedules() {
        return trainerSchedules;
    }

    public void setTrainerSchedules(List<TrainerSchedule> trainerSchedules) {
        this.trainerSchedules = trainerSchedules;
    }

    public List<TRating> getTrainerRatings() {
        return trainerRatings;
    }

    public void setTrainerRatings(List<TRating> trainerRatings) {
        this.trainerRatings = trainerRatings;
    }

    public Trainer(String speciality, List<TRating> trainerRatings, List<Class> classes, Float rating) {
        this.speciality = speciality;
        this.trainerRatings = trainerRatings;
        this.classes = classes;
        this.rating = rating;
    }

    public Trainer() {
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public List<Class> getClasses() {
        return classes;
    }

    public void setClasses(List<Class> classes) {
        this.classes = classes;
    }

    public Float getRating() {
        return rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }
}
