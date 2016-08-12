package com.redhat.usecase.service.impl;

import java.util.GregorianCalendar;
import java.util.UUID;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.camel.CamelContext;
import org.apache.camel.CamelExecutionException;
import org.apache.camel.Exchange;
import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.impl.DefaultExchange;
import org.apache.cxf.jaxrs.impl.ResponseBuilderImpl;

import com.customer.app.Person;
import com.customer.app.response.ESBResponse;
import com.redhat.usecase.service.DEIMService;

@Path("/deim/")
public class DEIMServiceImpl implements DEIMService {

	@Produce(uri = "direct:enqueueMessage")
	ProducerTemplate template;

	@Override
	@POST
	@Path("/add")
	@Consumes(MediaType.APPLICATION_XML)
	public Response addPerson(Person person) {

		ESBResponse esbResponse = new ESBResponse();
		ResponseBuilderImpl builder = new ResponseBuilderImpl();
		esbResponse.setEUID(UUID.randomUUID().toString());
		esbResponse.setMessageType("addPerson");

		try {
			XMLGregorianCalendar value = DatatypeFactory.newInstance().newXMLGregorianCalendar(new GregorianCalendar());
			esbResponse.setESBProcessDate(value);

			builder.status(Status.OK);

			CamelContext ctx = new DefaultCamelContext(); 
			Exchange exchange = new DefaultExchange(ctx);
			exchange.getIn().setBody(person);
			Exchange respExchange = template.send(exchange);

			esbResponse.setComment("addPerson in process...");
			esbResponse.setMessageId("200");
			esbResponse.setPublished(respExchange.getIn().getBody().toString().equalsIgnoreCase("OK"));
			
		} catch (CamelExecutionException e) {
			e.printStackTrace();
			esbResponse.setComment("Error JMS processing message for addPerson: " + e.getCause());
			esbResponse.setMessageId("500");
			esbResponse.setPublished(false);
			builder.status(Status.INTERNAL_SERVER_ERROR);
		}catch (Exception e) {
			e.printStackTrace();
			esbResponse.setComment("Error General processing message for addPerson: " + e.getCause());
			esbResponse.setMessageId("500");
			esbResponse.setPublished(false);
			builder.status(Status.INTERNAL_SERVER_ERROR);
		}

		builder.entity(esbResponse);

		return builder.build();
	}

	@Override
	@POST
	@Path("/update")
	@Consumes(MediaType.APPLICATION_XML)
	public Response updatePerson(Person person) {

		ESBResponse esbResponse = new ESBResponse();
		ResponseBuilderImpl builder = new ResponseBuilderImpl();
		esbResponse.setEUID(UUID.randomUUID().toString());
		esbResponse.setMessageType("updatePerson");

		try {
			XMLGregorianCalendar value = DatatypeFactory.newInstance().newXMLGregorianCalendar(new GregorianCalendar());
			esbResponse.setESBProcessDate(value);

			builder.status(Status.OK);

			CamelContext ctx = new DefaultCamelContext(); 
			Exchange exchange = new DefaultExchange(ctx);
			exchange.getIn().setBody(person);
			Exchange respExchange = template.send(exchange);

			esbResponse.setComment("updatePerson in process...");
			esbResponse.setMessageId("200");
			esbResponse.setPublished(respExchange.getIn().getBody().toString().equalsIgnoreCase("OK"));
			
		} catch (CamelExecutionException e) {
			e.printStackTrace();
			esbResponse.setComment("Error JMS processing message for updatePerson: " + e.getCause());
			esbResponse.setMessageId("500");
			esbResponse.setPublished(false);
			builder.status(Status.INTERNAL_SERVER_ERROR);
		}catch (Exception e) {
			e.printStackTrace();
			esbResponse.setComment("Error General processing message for updatePerson: " + e.getCause());
			esbResponse.setMessageId("500");
			esbResponse.setPublished(false);
			builder.status(Status.INTERNAL_SERVER_ERROR);
		}

		builder.entity(esbResponse);

		return builder.build();
	}

	@Override
	@POST
	@Path("/search")
	@Consumes(MediaType.APPLICATION_XML)
	public Response searchPerson(Person person) {

		ESBResponse esbResponse = new ESBResponse();
		ResponseBuilderImpl builder = new ResponseBuilderImpl();
		esbResponse.setEUID(UUID.randomUUID().toString());
		esbResponse.setMessageType("searchPerson");

		try {
			XMLGregorianCalendar value = DatatypeFactory.newInstance().newXMLGregorianCalendar(new GregorianCalendar());
			esbResponse.setESBProcessDate(value);

			builder.status(Status.OK);

			CamelContext ctx = new DefaultCamelContext(); 
			Exchange exchange = new DefaultExchange(ctx);
			exchange.getIn().setBody(person);
			Exchange respExchange = template.send(exchange);

			esbResponse.setComment("searchPerson in process...");
			esbResponse.setMessageId("200");
			esbResponse.setPublished(respExchange.getIn().getBody().toString().equalsIgnoreCase("OK"));
			
		} catch (CamelExecutionException e) {
			e.printStackTrace();
			esbResponse.setComment("Error JMS processing message for searchPerson: " + e.getCause());
			esbResponse.setMessageId("500");
			esbResponse.setPublished(false);
			builder.status(Status.INTERNAL_SERVER_ERROR);
		}catch (Exception e) {
			e.printStackTrace();
			esbResponse.setComment("Error General processing message for searchPerson: " + e.getCause());
			esbResponse.setMessageId("500");
			esbResponse.setPublished(false);
			builder.status(Status.INTERNAL_SERVER_ERROR);
		}

		builder.entity(esbResponse);

		return builder.build();
	}

}