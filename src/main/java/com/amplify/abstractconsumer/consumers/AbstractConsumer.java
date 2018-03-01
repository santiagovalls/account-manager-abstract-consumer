package com.amplify.abstractconsumer.consumers;

import com.amplify.abstractconsumer.exceptions.ClaimAccountException;
import com.amplify.abstractconsumer.exceptions.ReturnToQueueException;
import com.amplify.abstractconsumer.model.Account;
import com.amplify.abstractconsumer.model.ClaimType;
import com.amplify.abstractconsumer.model.SocialNetwork;
import com.amplify.abstractconsumer.model.UnitOfWork;
import com.amplify.abstractconsumer.services.AccountService;
import java.util.Map;
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
      implementLogic(unitOfWork, accountService.getNextValidAccounts());
    } catch (ClaimAccountException e) {
      if (ClaimType.NO_ACCOUNT_PRESENT.equals(e.getClaimType())) {
        stopContainer();
      }
      accountService.claimAccount(e.getAccount(), e.getClaimType());
      throw new ReturnToQueueException();
    }
  }

  public void enableAccount(Account account) {
    startContainer();
  }

  public abstract void implementLogic(UnitOfWork unitOfWork, Map<SocialNetwork, Account> accounts)
      throws ClaimAccountException;

  private void startContainer() {
    statusContainer.start();
  }

  private void stopContainer() {
    statusContainer.stop();
  }
}
