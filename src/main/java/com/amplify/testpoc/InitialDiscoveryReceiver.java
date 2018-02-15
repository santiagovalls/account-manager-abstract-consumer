package com.amplify.testpoc;

import com.amplify.abstractconsumer.model.UnitOfWork;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Santiago J. Valls.
 */
public class InitialDiscoveryReceiver {

  @Autowired
  InitialDiscoveryConsumer consumer;

  public void receive(UnitOfWork unitOfWork) {
    consumer.consume(unitOfWork);
  }
}