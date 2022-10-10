package com.example.climatechangeapi.models;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@ToString
public class ShippingRequest {
    private String type;
    private int weight_value;
    private String weight_unit;
    private int distance_value;
    private String distance_unit;
    private String transport_method;
}
