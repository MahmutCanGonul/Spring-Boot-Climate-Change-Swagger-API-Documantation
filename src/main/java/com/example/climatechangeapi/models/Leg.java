package com.example.climatechangeapi.models;

import lombok.*;

@AllArgsConstructor
@ToString
@NoArgsConstructor
@Getter
@Setter
public class Leg{
    public String departure_airport;
    public String destination_airport;
}
