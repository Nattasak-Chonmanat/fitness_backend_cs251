package com.example.demo.model;

public class Intendant {
    private Long trainerId;
    private Long classId;

    public Intendant(Long trainerId, Long classId) {
        this.trainerId = trainerId;
        this.classId = classId;
    }

    public Long getTrainerId() {
        return trainerId;
    }

    public void setTrainerId(Long trainerId) {
        this.trainerId = trainerId;
    }

    public Long getClassId() {
        return classId;
    }

    public void setClassId(Long classId) {
        this.classId = classId;
    }
}
