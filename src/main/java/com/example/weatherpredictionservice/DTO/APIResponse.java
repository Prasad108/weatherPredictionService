package com.example.weatherpredictionservice.DTO;

import com.example.weatherpredictionservice.DTO.openweather.Main;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
public class APIResponse {
    Double minTemp;
    Double MaxTemp;
    Set<String> messages;
}
