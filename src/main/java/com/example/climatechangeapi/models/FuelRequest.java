package com.example.climatechangeapi.models;

import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@ToString
public class FuelRequest {
    private String type;
    private String fuel_source_type;
    private String fuel_source_unit;
    private int fuel_source_value;
}
