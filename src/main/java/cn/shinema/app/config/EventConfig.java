package cn.shinema.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import cn.shinema.core.event.EventScan;
import cn.shinema.core.event.process.EventListenerProcessor;
import cn.shinema.core.notification.NotificationGatewayService;
import cn.shinema.core.notification.NotificationPublisher;
import cn.shinema.core.port.adapter.messaging.KafkaConsumerProcessor;
import cn.shinema.core.port.adapter.publisher.EventPublisherProcessor;
import cn.shinema.core.port.adapter.publisher.GenericNotificationPublisher;
import cn.shinema.core.port.adapter.publisher.KafkaNotificationGatewayService;

@Configuration
@EventScan("cn.shinema")
public class EventConfig {
	@Bean
	public EventListenerProcessor eventListenerProcessor() {
		return new EventListenerProcessor();
	}

	@Bean
	public EventPublisherProcessor eventPublisherProcessor() {
		return new EventPublisherProcessor();
	}

	@Bean
	public NotificationGatewayService notificationGatewayService() {
		return new KafkaNotificationGatewayService();
	}

	@Bean
	public KafkaConsumerProcessor kafkaConsumerProcessor() {
		return new KafkaConsumerProcessor();
	}

	@Bean
	public NotificationPublisher notificationPublisher() {
		return new GenericNotificationPublisher();
	}

}
