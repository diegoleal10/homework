package org.globex.usecase.service;

import org.globex.globex.Account;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

@Path("/customerservice/")
public interface CustomerRest {

    @POST
    @Path("/enrich")
    @Consumes("application/json") Account enrich(Account customer);

}
