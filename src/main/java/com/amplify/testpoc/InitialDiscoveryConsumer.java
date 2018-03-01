package com.amplify.testpoc;

import com.amplify.abstractconsumer.consumers.AbstractConsumer;
import com.amplify.abstractconsumer.exceptions.ClaimAccountException;
import com.amplify.abstractconsumer.model.Account;
import com.amplify.abstractconsumer.model.ClaimType;
import com.amplify.abstractconsumer.model.SocialNetwork;
import com.amplify.abstractconsumer.model.UnitOfWork;
import com.amplify.abstractconsumer.todo.TODOClass;
import java.util.Map;

/**
 * @author Santiago J. Valls.
 */
public class InitialDiscoveryConsumer extends AbstractConsumer {

  @Override
  public void implementLogic(UnitOfWork unitOfWork, Map<SocialNetwork, Account> accounts)
      throws ClaimAccountException {
    Account googleAccount = accounts.get(SocialNetwork.GOOGLE);
    if (googleAccount != null) {
      TODOClass.callGoogleAPI(accounts.get(SocialNetwork.GOOGLE));
      if (TODOClass.isAccountDisabled()) {
        throw new ClaimAccountException(googleAccount, ClaimType.DISABLED_ACCOUNT);
      }
    } else {
      throw new ClaimAccountException(googleAccount, ClaimType.NO_ACCOUNT_PRESENT);
    }
  }
}
