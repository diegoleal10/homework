package org.globex.usecase.service;

import org.globex.globex.Account;
import org.globex.globex.Company;

public class CustomerRestImpl implements CustomerRest {

    private static final String NA_REGION = "NORTH_AMERICA";
    private static final String SA_REGION = "SOUTH_AMERICA";
    private static final String WE_REGION = "WEST_AMERICA";
    private static final String EAST_REGION = "EAST_AMERICA";

    @Override
    public Account enrich(Account account) {
        Company company = account.getCompany();
        String region = company.getGeo();


        return account;
    }
}
