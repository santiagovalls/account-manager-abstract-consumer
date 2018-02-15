package com.amplify.testpoc;

import com.amplify.abstractconsumer.consumers.AbstractConsumer;
import com.amplify.abstractconsumer.exceptions.ClaimAccountException;
import com.amplify.abstractconsumer.model.Account;
import com.amplify.abstractconsumer.model.ClaimType;
import com.amplify.abstractconsumer.model.SocialNetwork;
import com.amplify.abstractconsumer.model.UnitOfWork;
import com.amplify.abstractconsumer.todo.TODOClass;

/**
 * @author Santiago J. Valls.
 */
public class InitialDiscoveryConsumer extends AbstractConsumer {

  @Override
  public void implementLogic(UnitOfWork unitOfWork, Account account)
      throws ClaimAccountException {
    TODOClass.callGoogleAPI(account);
    if (TODOClass.isAccountDisabled()) {
      throw new ClaimAccountException(account, ClaimType.DISABLED_ACCOUNT);
    }
  }

  @Override
  public SocialNetwork configureAccountNetwork() {
    return SocialNetwork.GOOGLE;
  }
}
