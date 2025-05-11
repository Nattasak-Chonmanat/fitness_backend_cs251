package com.example.demo.DTOs;

import com.example.demo.model.Class;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

@Schema(description = "ข้อมูลคลาสออกกําลังกายแบบย่อ")
public class ClassSummaryDTO {

    @Schema(description = "รหัสคลาสออกกําลังกาย" , example = "101")
    private Long classId;

    @Schema(description = "รหัสคลาสออกกําลังกาย" , example = "21")
    @NotBlank(message = "Class name is required.")
    private String className;

    @Schema(description = "ตารางของคลาสออกกําลังกาย" , example = "Mon/Wed 6 PM")
    @NotBlank(message = "Schedule is required.")
    private String schedule;

    @Schema(description = "จํานวนที่ว่าง" , example = "20")
    @NotNull(message = "Capacity is required.")
    private Integer capacity;

    @Schema(description = "เรตติ้งคลาสออกกําลังกาย" , example = "0.0")
    @PositiveOrZero(message = "New rating must not be positive.")
    private Float rating;

    public ClassSummaryDTO(Class c) {
        this.classId = c.getId();
        this.rating = c.getRating();
        this.capacity = c.getCapacity();
        this.className = c.getClassName();
        this.schedule = c.getSchedule();
    }

    public Long getClassId() {
        return classId;
    }

    public void setClassId(Long classId) {
        this.classId = classId;
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

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
}
