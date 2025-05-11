package com.example.demo.DTOs;

import com.example.demo.model.MembershipPlan;
import io.swagger.v3.oas.annotations.media.Schema;

public class MemberShipplanDTO {

    @Schema(description = "Id for membership plan.", example = "101")
    private Long id;

    @Schema(description = "Duration of membership plan", example = "30")
    private Integer duration;

    @Schema(description = "Price of membership plan.", example = "1200")
    private Float price;

    @Schema(description = "Description of membership plan.", example = "This class is about Cardio and some weight training.")
    private String description;

    @Schema(description = "Name of membership plan.",example = "Gold")
    private String planName;

    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }

    public MemberShipplanDTO(MembershipPlan m) {
        this.id = m.getId();
        this.description = m.getDescription();
        this.price = m.getPrice();
        this.duration = m.getDuration();
        this.planName = m.getPlanName();
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }
}
