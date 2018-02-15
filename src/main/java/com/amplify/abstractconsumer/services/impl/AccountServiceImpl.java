package com.amplify.abstractconsumer.services.impl;


import com.amplify.abstractconsumer.model.Account;
import com.amplify.abstractconsumer.model.Claim;
import com.amplify.abstractconsumer.model.ClaimType;
import com.amplify.abstractconsumer.model.SocialNetwork;
import com.amplify.abstractconsumer.services.AccountService;
import com.amplify.abstractconsumer.todo.TODOClass;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Santiago J. Valls.
 */
@Service
public class AccountServiceImpl extends AbstractService implements AccountService {

  @Autowired
  RabbitTemplate rabbitTemplate;

  @Override
  public Account getNextValidAccount(SocialNetwork socialNetwork) {
    return null;
  }

  @Override
  public void claimAccount(Account account, ClaimType claimType) {
    rabbitTemplate.convertAndSend(TODOClass.getClaimQueueName(), new Claim(account, claimType));
  }
}
