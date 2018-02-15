package com.amplify.abstractconsumer.todo;

import com.amplify.abstractconsumer.model.Account;

/**
 * @author Santiago J. Valls.
 */
public class TODOClass {

  // TODO See RabbitMQ configuration (hostname, port)

  public static String getClaimQueueName() {
    return "claim-queue";
  }

  public static String getStatusQueueName() {
    return "status-queue";
  }

  public static String getStatusExchangeName() {
    return "status-exchange";
  }

  public static String getInitialDiscoveryQueueName() {
    return "initialDiscovery-queue";
  }

  public static String getInitialDiscoveryExchangeName() {
    return "initialDiscovery-exchange";
  }


  public static void callGoogleAPI(Account account) {
  }

  public static boolean isAccountDisabled() {
    return true;
  }
}
