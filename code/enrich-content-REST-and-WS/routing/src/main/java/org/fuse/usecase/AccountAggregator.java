package org.fuse.usecase;

import org.apache.camel.Exchange;
import org.apache.camel.impl.DefaultExchange;
import org.apache.camel.processor.aggregate.AggregationStrategy;
import org.globex.Account;
import org.globex.CorporateAccount;

/**
 * Aggregator implementation which extract the id and salescontact from
 * CorporateAccount and update the Account
 */
public class AccountAggregator implements AggregationStrategy {

	@Override
	public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {

		if(oldExchange == null){
			Exchange exchange = new DefaultExchange(newExchange);
			exchange.getIn().setBody(newExchange.getIn().getBody());
			return exchange;
		}else{
			Account account = oldExchange.getIn().getBody(Account.class);

	        CorporateAccount ca = newExchange.getIn().getBody(CorporateAccount.class);

	        account.setClientId(ca.getId());

	        account.setSalesRepresentative(ca.getSalesContact());
	        
	        oldExchange.getIn().setBody(account);
	        
	        return oldExchange;
		}
		
	}

}