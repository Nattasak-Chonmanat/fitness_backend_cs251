package com.example.demo.DTOs;

import com.example.demo.model.Trainer;
import com.example.demo.model.TrainerSchedule;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;


@Schema(description = "ข้อมูลเทรนเนอร์แบบย่อ")
public class TrainerDTO {
    @Schema(description = "รหัสเทรนเนอร์", example = "1")
    private Long id;

    @Schema(description = "ชื่อ-สกุล", example = "สมชาย ใจดี")
    private String fullName;

    @Schema(description = "ความถนัดพิเศษ", example = "Yoga")
    private String speciality;

    @Schema(description = "คะแนนรีวิว", example = "4.5")
    private Float rating;

    @Schema(description = "รหัสสาขาที่สังกัด", example = "101")
    private Long branchId;

    @ArraySchema(schema = @Schema(implementation = ClassSummaryDTO.class))
    @Schema(description = "คลาสที่เทรนเนอร์เป็นผู้สอน")
    private List<ClassSummaryDTO> classes;

    @Schema(hidden = true)
    private List<TrainerReviewDTO> reviews;

    @Schema(hidden = true)
    private List<TrainerSchedule> schedules;

    public TrainerDTO(Trainer t) {
        this.id = t.getId();
        this.fullName = t.getFullName();
        this.speciality = t.getSpeciality();
        this.rating = t.getRating();
        this.classes = t.getClasses().stream().map(ClassSummaryDTO::new).toList();
        this.branchId = t.getBranch() != null ? t.getBranch().getId() : null;
        this.reviews = t.getTrainerRatings().stream().map(review -> {
            return new TrainerReviewDTO(review, t.getFullName());
        }).toList();
    }

    public List<TrainerReviewDTO> getReviews() {
        return reviews;
    }

    public void setReviews(List<TrainerReviewDTO> reviews) {
        this.reviews = reviews;
    }

    public Long getId() {
        return id;
    }

    public Long getBranchId() {
        return branchId;
    }

    public void setBranchId(Long branchId) {
        this.branchId = branchId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<ClassSummaryDTO> getClasses() {
        return classes;
    }

    public void setClasses(List<ClassSummaryDTO> classList) {
        this.classes = classList;
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
}

