package com.amplify.testpoc;

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
public class InitialDiscoveryRabbitConfig {

  @Value("${spring.rabbitmq.concurrentConsumers}")
  private Integer BROKER_CONCURRENT_CONSUMERS;

  @Value("${spring.rabbitmq.maxConcurrentConsumers}")
  private Integer BROKER_MAX_CONCURRENT_CONSUMERS;

  @Bean
  @Qualifier("initialDiscoveryContainer")
  SimpleMessageListenerContainer initialDiscoveryContainer(ConnectionFactory connectionFactory) {
    SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
    container.setConnectionFactory(connectionFactory);
    container.setQueueNames(TODOClass.getInitialDiscoveryQueueName());
    container
        .setMessageListener(new MessageListenerAdapter(new InitialDiscoveryReceiver(), "receive"));
    container.setConcurrentConsumers(BROKER_CONCURRENT_CONSUMERS);
    container.setMaxConcurrentConsumers(BROKER_MAX_CONCURRENT_CONSUMERS);
    return container;
  }
}
