package com.amplify.abstractconsumer.receivers;

import com.amplify.abstractconsumer.consumers.AbstractConsumer;
import com.amplify.abstractconsumer.model.Account;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Santiago J. Valls.
 */
public class StatusReceiver {

  @Autowired
  AbstractConsumer[] consumers;

  public void receive(Account account) {
    for (AbstractConsumer consumer : consumers) {
      consumer.enableAccount(account);
    }
  }
}