package com.redhat.outbound.test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.jms.Connection;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.camel.CamelContext;
import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.model.RouteDefinition;
import org.apache.camel.test.spring.CamelSpringTestSupport;
import org.apache.camel.test.spring.MockEndpoints;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class CamelContextXmlTest extends CamelSpringTestSupport {

	@Produce(uri = "activeMq:queue:outbound")
	protected ProducerTemplate inputEndpoint;
	@Test
	public void testCamelRoute() throws Exception {
	}
	
	@Override
	protected ClassPathXmlApplicationContext createApplicationContext() {
		return new ClassPathXmlApplicationContext("camelTestContext.xml");
	}

	@SuppressWarnings("resource")
	public String getDataFromFile() {
		String finalLine = "";
		try {
			FileReader reader = new FileReader("src/test/data/soapText.xml");
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
