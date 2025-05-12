package com.example.demo.model;

import java.time.LocalDate;

public class GetPromotionStatus {


    public GetPromotionStatus() {

    }

    public static String execute(Promotion promotion) {
        LocalDate today = LocalDate.now();

        if (!today.isBefore(promotion.getStartDate()) && !today.isAfter(promotion.getEndDate())) {
            return "Active";
        }

        if (today.isAfter(promotion.getEndDate())) {
            return "Expired";
        }

        return "Upcoming";
    }

}
