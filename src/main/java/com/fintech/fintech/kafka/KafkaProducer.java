package com.fintech.fintech.kafka;

import com.fintech.fintech.data.dto.kafka.KafkaMessageDto;
import com.fintech.fintech.data.model.WeatherModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class KafkaProducer {

  private final String topic;
  private final KafkaTemplate<String, WeatherModel> kafkaTemplate;

  public KafkaProducer(KafkaTemplate<String, WeatherModel> kafkaTemplate,
                       @Value("${spring.kafka.consumer.topic}") String topic) {
    this.kafkaTemplate = kafkaTemplate;
    this.topic = topic;
  }

  public void sendEvent(WeatherModel weatherModel) {
    kafkaTemplate.send(topic, weatherModel);
    log.info("producer: {}", weatherModel.getName());
  }
}
