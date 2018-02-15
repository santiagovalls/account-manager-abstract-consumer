package com.amplify.abstractconsumer.consumers;

import com.amplify.abstractconsumer.exceptions.ClaimAccountException;
import com.amplify.abstractconsumer.exceptions.ReturnToQueueException;
import com.amplify.abstractconsumer.model.Account;
import com.amplify.abstractconsumer.model.SocialNetwork;
import com.amplify.abstractconsumer.model.UnitOfWork;
import com.amplify.abstractconsumer.services.AccountService;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * @author Santiago J. Valls.
 */
public abstract class AbstractConsumer {

  @Autowired
  private AccountService accountService;

  @Autowired
  @Qualifier("statusContainer")
  SimpleMessageListenerContainer statusContainer;

  public void consume(UnitOfWork unitOfWork) {
    try {
      Account account = accountService.getNextValidAccount(configureAccountNetwork());
      if (account != null) {
        implementLogic(unitOfWork, account);
      } else {
        stopContainer();
        throw new ReturnToQueueException();
      }
    } catch (ClaimAccountException e) {
      accountService.claimAccount(e.getAccount(), e.getClaimType());
      throw new ReturnToQueueException();
    }
  }

  public void enableAccount(Account account) {
    if (account.getNetwork().equals(configureAccountNetwork())) {
      startContainer();
    }
  }

  public abstract void implementLogic(UnitOfWork unitOfWork, Account account)
      throws ClaimAccountException;

  public abstract SocialNetwork configureAccountNetwork();

  private void startContainer() {
    statusContainer.start();
  }

  private void stopContainer() {
    statusContainer.stop();
  }
}
