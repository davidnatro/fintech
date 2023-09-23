package com.fintech.fintech.util;

import com.fintech.fintech.data.entity.Weather;
import com.fintech.fintech.repository.WeatherRepository;
import com.fintech.fintech.repository.impl.WeatherRepositoryImpl;
import com.fintech.fintech.service.WeatherService;
import com.fintech.fintech.service.impl.WeatherServiceImpl;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;

public class AppStarter {

    private static final BufferedReader reader = new BufferedReader(
            new InputStreamReader(System.in));
    private static final String RESULT_DELIMITER = "===";

    private final WeatherService weatherService;

    public AppStarter() {
        WeatherRepository weatherRepository = new WeatherRepositoryImpl();
        this.weatherService = new WeatherServiceImpl(weatherRepository);
    }

    public void run() throws IOException {
        System.out.println(getInstructions());
        while (true) {
            String[] input = reader.readLine().split(" ");
            switch (input[0]) {
                case "avg" -> {
                    System.out.println("Average temperature of all regions: "
                                               + weatherService.calculateAverageTemperature());
                    System.out.println(RESULT_DELIMITER);
                }
                case "gt" -> {
                    if (input.length != 2) {
                        System.out.println("Incorrect input size");
                        continue;
                    }

                    handleGreaterThanRequest(input[1]);
                }
                case "toMapIdTemperature" -> {
                    handleToMapIdTemperatureRequest();
                }
                case "toMapTemperatureWeatherList" -> {
                    handleToMapTemperatureWeatherListRequest();
                }
                case "h" -> {
                    System.out.println(getInstructions());
                    System.out.println(RESULT_DELIMITER);
                }
                case "q" -> {
                    return;
                }
                default -> {
                    System.out.println("Incorrect input!");
                    System.out.println(RESULT_DELIMITER);
                }
            }
        }
    }

    private static String getInstructions() {
        return """
                avg -> prints average temperature of all regions
                gt <temperature> -> prints all regions with temperature greater than <temperature>
                toMapIdTemperature -> print map of ids and its temperatures
                toMapTemperatureWeatherList -> prints map of temperatures and its weathers
                h -> prints instructions
                q -> quit the app""";
    }

    private void handleToMapIdTemperatureRequest() {
        Map<Long, Double> idsTemperatures = weatherService.getIdsAndTemperatures();
        idsTemperatures.forEach((key, value) -> {
            System.out.println("id: " + key + "; temperature: " + value);
        });
        System.out.println(RESULT_DELIMITER);
    }

    private void handleToMapTemperatureWeatherListRequest() {
        Map<Double, List<Weather>> temperatureWeathers = weatherService.getAllWeathersByTemperature();
        temperatureWeathers.forEach((key, value) -> {
            System.out.print("temperature: " + key + "; weathers: ");
            value.forEach(w -> System.out.print(w.getRegionName() + "; "));
            System.out.println();
        });
        System.out.println(RESULT_DELIMITER);
    }

    private void handleGreaterThanRequest(String inputTemperature) {
        double temperature;

        try {
            temperature = Double.parseDouble(inputTemperature);
        } catch (NumberFormatException exception) {
            System.out.println("Cannot parse temperature -> input: " + inputTemperature);
            return;
        }

        System.out.println("Regions with temperature greater than '" + inputTemperature + "' : ");
        weatherService.findAllByTemperatureGreaterThan(temperature).forEach(System.out::println);
        System.out.println(RESULT_DELIMITER);
    }
}
