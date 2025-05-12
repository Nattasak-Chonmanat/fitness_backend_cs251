package com.example.demo.DTOs;

import com.example.demo.model.TrainerSchedule;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalTime;

public class TrainerScheduleDTO {

    @Schema(hidden = true)
    private Long id;

    @NotNull(message = "Trainer id is required.")
    private Long trainerId;

    @Schema(hidden = true)
    private LocalDate workDate;

    @Schema(hidden = true)
    private LocalTime startTime;

    @Schema(hidden = true)
    private LocalTime endTime;

    public TrainerScheduleDTO() {
    }

    public TrainerScheduleDTO(TrainerSchedule trainerSchedule) {
        this.id = trainerSchedule.getId();
        this.endTime = trainerSchedule.getEndTime();
        this.startTime = trainerSchedule.getStartTime();
        this.workDate = trainerSchedule.getWorkDate();
        this.trainerId = trainerSchedule.getTrainer().getId();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalDate getWorkDate() {
        return workDate;
    }

    public void setWorkDate(LocalDate workDate) {
        this.workDate = workDate;
    }

    public @NotNull(message = "Trainer id is required.") Long getTrainerId() {
        return trainerId;
    }

    public void setTrainerId(@NotNull(message = "Trainer id is required.") Long trainerId) {
        this.trainerId = trainerId;
    }
}
