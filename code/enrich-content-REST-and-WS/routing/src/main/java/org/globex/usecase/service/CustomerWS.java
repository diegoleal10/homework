package org.globex.usecase.service;

import org.globex.globex.Account;
import org.globex.globex.CorporateAccount;

import javax.jws.WebService;

@WebService
public interface CustomerWS {

    CorporateAccount updateAccount(Account account);

}
