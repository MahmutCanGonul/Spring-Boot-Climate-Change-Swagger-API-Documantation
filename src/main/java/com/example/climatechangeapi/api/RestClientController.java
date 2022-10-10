package com.example.climatechangeapi.api;


import com.example.climatechangeapi.models.*;

import  java.net.http.HttpRequest;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.models.Response;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpResponse;
import java.util.*;

@Controller
@RequestMapping("climate")
public class RestClientController {

    private RestTemplate restTemplate = new RestTemplate();
    private HttpRequest request;
    @GetMapping("electricity")
    protected ResponseEntity<String> GetCarbonFootprintByElectricity(String electricityUnit,int electricityValue,String country,String state) throws IOException, InterruptedException{
        String isoCodes = "US CA AT BE BG HR CY CZ DK EU-27 EU-27+1 EE FI FR DE GR GU IE IT LV LT LU MT NL PL PT RO SK SI ES SE GB".toLowerCase(Locale.ROOT);
        String[] split_iso = isoCodes.split(" ");
        boolean isAccess=false;
        for (String code: split_iso) {
            if(code.equals(country)){
                isAccess =true;
            }
        }
        if(!isAccess)
            return ResponseEntity.ok("Not Found ISO-CODE! ISO-CODES: ["+isoCodes+"]");
        ElectricityRequest electricityRequest = new ElectricityRequest(
                 "electricity",
                 electricityUnit,
                 electricityValue,
                 country,
                 state
         );
        var data = new HashMap<String,Object>();
        data.put("type",electricityRequest.getType());
        data.put("electricity_unit",electricityRequest.getElectricity_unit());
        data.put("electricity_value",electricityRequest.getElectricity_value());
        data.put("country",electricityRequest.getCountry());
        data.put("state",electricityRequest.getState());
        JSONObject jsonObject = new JSONObject(data);
        request = HttpRequest.newBuilder()
                .uri(URI.create("https://www.carboninterface.com/api/v1/estimates?type="+electricityRequest.getType()+"&electricity_unit="+electricityRequest.getElectricity_unit()+"&electricity_value="+electricityRequest.getElectricity_value()+"&country="+electricityRequest.getCountry()+"&state="+electricityRequest.getState()))
                .header("Authorization", "Bearer 2r7lxwG5rrxJz1MNGihJg")
                 .header("Content-Type","application/json")
                 .method("POST",HttpRequest.BodyPublishers.ofString(jsonObject.toString()))
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        return ResponseEntity.ok(response.body());
    }

    @GetMapping("shipping")
    public ResponseEntity<String> GetCarbonFootprintByShipping(int weightValue,String weightUnit,int distanceValue,String distanceUnit,String transportMethod)throws  IOException,InterruptedException{
        List<String> transporters = new ArrayList<>(){{add("ship"); add("truck"); add("train"); add("plane");}};
        if(!transporters.contains(transportMethod)){
            String warningMessage = "";
            for (String transporter:transporters) {
                warningMessage+=transporter+" ";
            }
            return ResponseEntity.ok("Not Valid Transporter! Transporters: ["+warningMessage.toLowerCase(Locale.ROOT)+"]");
        }
        ShippingRequest shippingRequest = new ShippingRequest(
               "shipping",
                 weightValue,
                weightUnit,
                distanceValue,
                distanceUnit,
                transportMethod
        );
        var data = new HashMap<String,Object>();
        data.put("type",shippingRequest.getType());
        data.put("weight_value",shippingRequest.getWeight_value());
        data.put("weight_unit",shippingRequest.getWeight_unit());
        data.put("distance_value",shippingRequest.getDistance_value());
        data.put("distance_unit",shippingRequest.getDistance_unit());
        data.put("transport_method",shippingRequest.getTransport_method());
        JSONObject jsonObject = new JSONObject(data);
        request = HttpRequest.newBuilder()
                .uri(URI.create("https://www.carboninterface.com/api/v1/estimates?type=shipping&weight_unit="+weightUnit+"&weight_value="+weightValue+"&distance_unit="+distanceUnit+"&transport_method="+transportMethod))
                .header("Authorization", "Bearer 2r7lxwG5rrxJz1MNGihJg")
                .header("Content-Type","application/json")
                .method("POST", HttpRequest.BodyPublishers.ofString(jsonObject.toString()))
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        return ResponseEntity.ok(response.body());
    }
    @GetMapping("vehicle")
    public ResponseEntity<String> GetCarbonFootprintByVehicle(String carName,String distanceUnit,int distanceValue)throws  IOException,InterruptedException{
        var vehicles = new HashMap<String,String>();
        vehicles.put("Ferrari","4c1e16e1-7967-4394-b3cb-15f4577dffa1");
        vehicles.put("Dodge","c0d79b67-76e8-442d-b105-2c73501948a9");
        vehicles.put("Subaru","c983d62d-e823-4bfb-92a8-45d161be1fe1");
        vehicles.put("Toyota","7268a9b7-17e8-4c8d-acca-57059252afe9");
        vehicles.put("Alfa-Romeo","5f266411-5bb1-4b91-b044-9707426df630");
        if(!vehicles.containsKey(carName)){
            return ResponseEntity.ok("Not Valid Car Name! CAR_NAMES: [Ferrari,Dodge,Subaru,Toyota]");
        }
        VehicleRequest vehicleRequest = new VehicleRequest(
                "vehicle",
                distanceUnit,
                distanceValue,
                vehicles.get(carName)
        );
        var data = new HashMap<String,Object>();
        data.put("type",vehicleRequest.getType());
        data.put("distance_unit",vehicleRequest.getDistance_unit());
        data.put("distance_value",vehicleRequest.getDistance_value());
        data.put("vehicle_model_id",vehicleRequest.getVehicle_model_id());
        JSONObject jsonObject = new JSONObject(data);
        request = HttpRequest.newBuilder()
                .uri(URI.create("https://www.carboninterface.com/api/v1/estimates?type=vehicle&distance_unit="+vehicleRequest.getDistance_unit()+"&distance_value="+vehicleRequest.getDistance_value()+"&vehicle_model_id="+vehicleRequest.getVehicle_model_id()))
                .header("Authorization", "Bearer 2r7lxwG5rrxJz1MNGihJg")
                .header("Content-Type","application/json")
                .method("POST", HttpRequest.BodyPublishers.ofString(jsonObject.toString()))
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        return ResponseEntity.ok(response.body());
    }
    @GetMapping("getvehicleids")
    public ResponseEntity<String> GetVehicleIds()throws  IOException,InterruptedException{
        request = HttpRequest.newBuilder()
                .uri(URI.create("https://www.carboninterface.com/api/v1/vehicle_makes"))
                .header("Authorization", "Bearer 2r7lxwG5rrxJz1MNGihJg")
                .header("Content-Type","application/json")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        return ResponseEntity.ok(response.body());
    }

    @GetMapping("flight")
    public ResponseEntity<String> GetCarbonFootprintByFlight(int passengers, @RequestBody ArrayList<Leg> legs)throws  IOException,InterruptedException{
        if(passengers < 0)
            return ResponseEntity.ok("Not Valid Passenger Count!");
        FlightRequest flightRequest = new FlightRequest("flight",passengers,legs);
        var data = new HashMap<String,Object>();
        data.put("type",flightRequest.getType());
        data.put("passengers",flightRequest.getPassengers());
        data.put("legs",legs);
        JSONObject jsonObject = new JSONObject(data);
        System.out.println(jsonObject.toString());
        request = HttpRequest.newBuilder()
                .uri(URI.create("https://www.carboninterface.com/api/v1/estimates?type=flight"))
                .header("Authorization", "Bearer 2r7lxwG5rrxJz1MNGihJg")
                .header("Content-Type","application/json")
                .method("POST", HttpRequest.BodyPublishers.ofString(jsonObject.toString()))
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        return ResponseEntity.ok(response.body());
    }

    @GetMapping("fuel")
    public ResponseEntity<String> GetCarbonFootprintByFuel(String fuel,int fuelValue)throws  IOException,InterruptedException{
        String fuels = "bit dfo jf ker lig msw ng pc pg rfo sub tdf wo";
        String[] split_fuels = fuels.split(" ");
        boolean isAccess = false;
        for (String f: split_fuels) {
            if(f.equals(fuel)){
                isAccess =true;
                break;
            }
        }
        if(!isAccess)
            return ResponseEntity.ok("Not Valid Fuel! Fuels: ["+fuels+"]");
        FuelRequest fuelRequest = new FuelRequest(
                "fuel_combustion",
                fuel,
                "btu",
                fuelValue
        );
        var data = new HashMap<String,Object>();
        data.put("type",fuelRequest.getType());
        data.put("fuel_source_type",fuelRequest.getFuel_source_type());
        data.put("fuel_source_unit",fuelRequest.getFuel_source_unit());
        data.put("fuel_source_value",fuelRequest.getFuel_source_value());
        JSONObject jsonObject = new JSONObject(data);
        request = HttpRequest.newBuilder()
                .uri(URI.create("https://www.carboninterface.com/api/v1/estimates?type=fuel_combustion&fuel_source_type="+fuelRequest.getFuel_source_type()+"&fuel_source_unit="+fuelRequest.getFuel_source_unit()+"&fuel_source_value="+fuelRequest.getFuel_source_value()))
                .header("Authorization", "Bearer 2r7lxwG5rrxJz1MNGihJg")
                .header("Content-Type","application/json")
                .method("POST", HttpRequest.BodyPublishers.ofString(jsonObject.toString()))
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        return ResponseEntity.ok(response.body());

    }


}
