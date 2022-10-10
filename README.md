# Spring-Boot-Climate-Change-Swagger-API-Documantation
Calculating the Carbon Footprint by consumptions with Carbon Interface API and Java Spring Boot


![image](https://user-images.githubusercontent.com/75094927/194902436-f366a52e-d76c-41b2-b191-81dc1096e39b.png)


CARBON INTERFACE API

Carbon Interface’s APIs are based on REST and rely on JSON for sending and receiving payloads.

The API will work with programming languages and frameworks that can make standard HTTP requests.



Response Data Examples:

Electricity Response:


     {
    "data": {
    "id": "2d968fce-859d-4dc1-9489-987e795f42bb",
    "type": "estimate",
    "attributes": {
      "country": "us",
      "state": "fl",
      "electricity_unit": "mwh",
      "electricity_value": "42.0",
      "estimated_at": "2020-07-24T02:23:24.441Z",
      "carbon_g": 18051428,
      "carbon_lb": 39796,
      "carbon_kg": 18051,
      "carbon_mt": 18
      }
    }
  }
  
  
  Flight Response:
  
    {
    "data": {
    "id": "d60edacc-cf6c-4da7-b5de-c538de4ce5ee",
    "type": "estimate",
    "attributes": {
      "passengers": 2,
      "legs": [
        {
          "departure_airport": "SFO",
          "destination_airport": "YYZ"
        },
        {
          "departure_airport": "YYZ",
          "destination_airport": "SFO"
        }
      ],
      "estimated_at": "2020-07-24T02:25:50.837Z",
      "carbon_g": 1077098,
      "carbon_lb": 2374,
      "carbon_kg": 1077,
      "carbon_mt": 1,
      "distance_unit": "km",
      "distance_value": 7454.15
      }
    }
     }
     
     
     
     
 Shipping Response:
 
     {
      "data": {
    "id": "4746e4ba-6605-4acc-802b-fd229a9503b4",
    "type": "estimate",
    "attributes": {
      "distance_value": "2000.0",
      "distance_unit": "km",
      "weight_value": "200.0",
      "weight_unit": "g",
      "transport_method": "truck",
      "estimated_at": "2020-07-31T13:00:04.446Z",
      "carbon_g": 25,
      "carbon_lb": 0.06,
      "carbon_kg": 0.03,
      "carbon_mt": 0.0
       }
     }
   }


Vehicle Response:

     {
      "data": {
      "id": "6108d711-be04-4dc4-93f9-43d969fd5273",
    "type": "estimate",
    "attributes": {
      "distance_value": 100.0,
      "vehicle_make": "Toyota",
      "vehicle_model": "Corolla",
      "vehicle_year": 1993,
      "vehicle_model_id": "7268a9b7-17e8-4c8d-acca-57059252afe9",
      "distance_unit": "mi",
      "estimated_at": "2021-01-10T15:24:32.568Z",
      "carbon_g": 37029,
      "carbon_lb": 81.64,
      "carbon_kg": 37.03,
      "carbon_mt": 0.04
       }
      }
     }


UI RESPONSE:

  ![Screenshot 2022-10-10 183432](https://user-images.githubusercontent.com/75094927/194903484-5d6af8ed-b32d-4fd3-9b12-c77971b5e8bd.png)

  
  



