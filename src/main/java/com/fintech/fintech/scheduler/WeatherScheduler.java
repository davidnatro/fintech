package com.fintech.fintech.scheduler;

import com.fintech.fintech.client.WeatherClient;
import com.fintech.fintech.kafka.KafkaProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class WeatherScheduler {

  private final KafkaProducer producer;
  private final WeatherClient weatherClient;

  @Scheduled(cron = "${application.weather.moscow.cron}")
  public void getMoscowWeather() {
    producer.sendEvent(weatherClient.getCurrentWeather("Moscow", "ru"));
  }

  @Scheduled(cron = "${application.weather.saint-petersburg.cron}")
  public void getSaintPetersburgWeather() {
    producer.sendEvent(weatherClient.getCurrentWeather("Saint Petersburg", "ru"));
  }

  @Scheduled(cron = "${application.weather.kazan.cron}")
  public void getKazanWeather() {
    producer.sendEvent(weatherClient.getCurrentWeather("Kazan", "ru"));
  }

  @Scheduled(cron = "${application.weather.sochi.cron}")
  public void getSochiWeather() {
    producer.sendEvent(weatherClient.getCurrentWeather("Sochi", "ru"));
  }

  @Scheduled(cron = "${application.weather.krasnodar.cron}")
  public void getKrasnodarWeather() {
    producer.sendEvent(weatherClient.getCurrentWeather("Krasnodar", "ru"));
  }
}
