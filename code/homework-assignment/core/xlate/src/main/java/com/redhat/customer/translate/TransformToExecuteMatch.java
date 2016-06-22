package com.redhat.customer.translate;

import org.apache.camel.Converter;
import org.apache.camel.Exchange;
import org.apache.camel.TypeConversionException;

import com.customer.app.Person;
import com.sun.mdm.index.webservice.CallerInfo;
import com.sun.mdm.index.webservice.ExecuteMatchUpdate;
import com.sun.mdm.index.webservice.PersonBean;
import com.sun.mdm.index.webservice.SystemPerson;

@Converter
public class TransformToExecuteMatch {

@Converter
public ExecuteMatchUpdate convertTo(Object value,Exchange e)
			throws TypeConversionException {
		ExecuteMatchUpdate executeMatchUpdate = new ExecuteMatchUpdate();
		return executeMatchUpdate;
	}

}
