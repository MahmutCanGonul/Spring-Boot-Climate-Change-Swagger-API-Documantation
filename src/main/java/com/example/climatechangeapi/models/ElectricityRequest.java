package com.example.climatechangeapi.models;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@ToString
public class ElectricityRequest {
    private String type;
    private String electricity_unit;
    private int electricity_value;
    private String country;
    private String state;
}
