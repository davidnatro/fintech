package com.fintech.fintech.kafka;

import com.fintech.fintech.data.model.WeatherModel;
import com.fintech.fintech.scheduler.Average;
import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class KafkaConsumer {

  private final Map<String, Average> averages = new HashMap<>();

  @KafkaListener(topics = "${spring.kafka.consumer.topic}",
      groupId = "${spring.kafka.consumer.group-id}")
  public void consume(WeatherModel weatherModel) {
    log.info("consumer: {}", weatherModel.getName());
    Average average = averages.computeIfAbsent(weatherModel.getName(), a -> new Average(30));
    log.info("average: {}", average.calculate(weatherModel.getCelsius()));
  }
}
