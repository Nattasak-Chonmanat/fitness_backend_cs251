package com.example.demo.DTOs;

import com.example.demo.model.Class;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

public class ClassDTO {

    @Schema(description = "Class's id", example = "101")
    private Long classId;

    @Schema(description = "Class name.", example = "Yoga 101")
    private String className;
    @Schema(description = "Class's schedule", example = "Mon/Wed 6 PM")
    private String schedule;
    @Schema(description = "Class's capacity", example = "200")
    private Integer capacity;
    @Schema(description = "Class's average rating.", example = "2.3")
    private Float rating;

    @ArraySchema(schema = @Schema(implementation = TrainerSummaryDTO.class))
    @Schema(description = "Class's trainers")
    private List<TrainerSummaryDTO> trainers;

    public ClassDTO() {
    }

    public ClassDTO(Class c) {
        this.classId = c.getId();
        this.className = c.getClassName();
        this.rating = c.getRating();
        this.capacity = c.getCapacity();
        this.schedule = c.getSchedule();
        this.trainers = c.getTrainers().stream().map(TrainerSummaryDTO::new).toList();
    }


    public List<TrainerSummaryDTO> getTrainers() {
        return trainers;
    }

    public void setTrainers(List<TrainerSummaryDTO> trainerSummaryDTOS) {
        this.trainers = trainerSummaryDTOS;
    }

    public Long getClassId() {
        return classId;
    }

    public void setClassId(Long classId) {
        this.classId = classId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Float getRating() {
        return rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }
}
