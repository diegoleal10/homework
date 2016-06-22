package com.redhat.usecase.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.net.URI;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPathExpressionException;

import org.apache.activemq.broker.BrokerService;
import org.apache.activemq.broker.TransportConnector;
import org.apache.activemq.camel.component.ActiveMQComponent;
import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.test.spring.CamelSpringTestSupport;
import org.apache.camel.util.KeyValueHolder;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.customer.app.Person;
import com.customer.app.PersonName;
import com.customer.app.response.ESBResponse;

public class CamelContextXmlTest extends CamelSpringTestSupport {

	// TODO Create test message bodies that work for the route(s) being tested
	// Expected message bodies
	protected Object[] expectedBodies = {
			"<something id='1'>expectedBody1</something>",
			"<something id='2'>expectedBody2</something>" };
	// Templates to send to input endpoints
	@Produce(uri = "direct:integrateRoute")
	protected ProducerTemplate inputEndpoint;
	BrokerService broker = null;

	@Before
	public void initialize() throws Exception {
		broker = new BrokerService();
		TransportConnector connector = new TransportConnector();
		connector.setUri(new URI("tcp://localhost:61617"));
		broker.addConnector(connector);
		broker.start();
		broker.waitUntilStarted();
		KeyValueHolder serviceHolder = new KeyValueHolder(new ActiveMQComponent(), null);
	}

	@Test
	public void testCamelRoute() throws Exception {
	}

	@After
	public void destroy() {
		try {
			if (broker != null)
				broker.stop();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	protected ClassPathXmlApplicationContext createApplicationContext() {
		ClassPathXmlApplicationContext classPathXmlApplicationContext = null;

		classPathXmlApplicationContext = new ClassPathXmlApplicationContext(
				new String[] { "bundleContext.xml", "camelTestContext.xml" });

		return classPathXmlApplicationContext;
	}

	public static String replaceOSGiPropertyLoader(String springXmlLocation)
			throws ParserConfigurationException, IOException, SAXException,
			XPathExpressionException, TransformerException {
		StringWriter writer = new StringWriter();
		return writer.toString();
	}

}
