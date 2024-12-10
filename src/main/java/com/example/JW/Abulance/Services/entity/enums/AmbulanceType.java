package com.example.JW.Abulance.Services.entity.enums;

public enum AmbulanceType {
    BASIC_LIFE_SUPPORT("BLS"),
    ADVANCED_LIFE_SUPPORT("ALS");
    
    private String ambulance_type;

    AmbulanceType(String ambulance_type) {
        this.ambulance_type = ambulance_type;
    }

    public String getAmbulance_type() {
        return ambulance_type;
    }
}
