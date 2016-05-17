package com.redhat.customer.translate.test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.apache.activemq.broker.Broker;
import org.apache.camel.Exchange;
import org.apache.camel.Predicate;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.component.mock.AssertionClause;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.spring.CamelSpringTestSupport;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TypeConverterTest extends CamelSpringTestSupport {

	// TODO Create test message bodies that work for the route(s) being tested
	// Expected message bodies
	protected Object[] expectedBodies = {
			"<something id='1'>expectedBody1</something>",
			"<something id='2'>expectedBody2</something>" };
	// Templates to send to input endpoints

	protected ProducerTemplate inputEndpoint;

	protected Broker broker = null;

	@Test
	public void testConverterRoute() throws Exception{
		MockEndpoint mockEndpoint = getMockEndpoint("mock:convertEndpoint");
		template.sendBody("activeMq:queue:customerconvert",getDataFromFile());
		AssertionClause expectedBodyReceived = mockEndpoint.expectedBodyReceived();
		expectedBodyReceived.body().isInstanceOf(com.sun.mdm.index.webservice.ExecuteMatchUpdate.class);
		mockEndpoint.assertIsSatisfied();
	}
	

	
	@Test()
	public void testAnotherRoute() throws Exception{
		
		MockEndpoint mockEndpoint = getMockEndpoint("mock:soapEndPoint");
		template.sendBody("activeMq:queue:customersoap",getDataFromFile());
		
		mockEndpoint.expectedMessageCount(1);
		mockEndpoint.assertIsSatisfied();
	}

	@Override
	protected ClassPathXmlApplicationContext createApplicationContext() {
		return new ClassPathXmlApplicationContext("camelTestContext.xml");
	}

	@SuppressWarnings("resource")
	public String getDataFromFile() {
		String finalLine = "";
		try {
			FileReader reader = new FileReader("src/test/data/Person.xml");
			BufferedReader bufferedReader = new BufferedReader(reader);
			String currentLine = null;

			while ((currentLine = bufferedReader.readLine()) != null) {
				finalLine += currentLine;
			}
		} catch (IOException io) {
			System.out.println(io.getMessage());
		}
		return finalLine;
	}

}
