package com.example.climatechangeapi.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@AllArgsConstructor
@ToString
public class VehicleRequest {
    private String type;
    private String distance_unit;
    private int distance_value;
    private String vehicle_model_id;
}
