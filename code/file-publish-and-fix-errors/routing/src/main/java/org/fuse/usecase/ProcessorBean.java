package org.fuse.usecase;

import java.util.HashMap;
import java.util.Map;

import org.apache.camel.Exchange;

public class ProcessorBean {

    public void debug(Exchange exchange) {
        Object body = (Object) exchange.getIn().getBody();
        Exception cause = exchange.getProperty(Exchange.EXCEPTION_CAUGHT, Exception.class);
        
        Map<String, Object> headers = (Map<String, Object>) exchange.getIn().getHeaders();
        headers.put("error-code", "111");
        headers.put("error-message", cause.getMessage());
        exchange.getIn().setHeaders(headers);
        
        System.out.println(">> CAUSE MESSAGE >>" + cause.getMessage());
        System.out.println(">> HEADERS >>" + headers);
        System.out.println(">> BODY >>" + body);
    }
    
    public Map<String, Object> defineNamedParameters(Exchange exchange) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("ERROR_CODE",exchange.getIn().getHeader("error-code"));
        map.put("ERROR_MESSAGE",exchange.getIn().getHeader("error-message"));
        map.put("MESSAGE",exchange.getIn().getBody());
        map.put("STATUS","ERROR");
        return map;
    }
    
    public void getMessage(Exchange exchange){
    	
    	exchange.getIn().setBody(exchange.getIn().getBody(Map.class).get("MESSAGE"));
    	
    }
}
