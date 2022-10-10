package com.example.climatechangeapi.models;

import lombok.*;

import java.util.ArrayList;

@AllArgsConstructor
@ToString
@NoArgsConstructor
@Getter
@Setter
public class FlightRequest {
    public String type;
    public int passengers;
    public ArrayList<Leg> legs;


}

