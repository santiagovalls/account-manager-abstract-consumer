package com.amplify.abstractconsumer.config;

import com.amplify.abstractconsumer.receivers.StatusReceiver;
import com.amplify.abstractconsumer.todo.TODOClass;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Santiago J. Valls.
 */
@Configuration
public class StatusRabbitConfig {

  @Value("${spring.rabbitmq.concurrentConsumers}")
  private Integer BROKER_CONCURRENT_CONSUMERS;

  @Value("${spring.rabbitmq.maxConcurrentConsumers}")
  private Integer BROKER_MAX_CONCURRENT_CONSUMERS;

  @Bean
  @Qualifier("statusContainer")
  SimpleMessageListenerContainer statusContainer(ConnectionFactory connectionFactory) {
    SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
    container.setConnectionFactory(connectionFactory);
    container.setQueueNames(TODOClass.getStatusQueueName());
    container
        .setMessageListener(new MessageListenerAdapter(new StatusReceiver(), "receive"));
    container.setConcurrentConsumers(BROKER_CONCURRENT_CONSUMERS);
    container.setMaxConcurrentConsumers(BROKER_MAX_CONCURRENT_CONSUMERS);
    return container;
  }
}
