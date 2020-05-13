package com.gear.test.service;

import com.gear.test.soap.GetTimeResponse;
import com.gear.test.soap.GetTimeRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

import javax.xml.datatype.XMLGregorianCalendar;

@Service
public class SoapService extends WebServiceGatewaySupport {

    private static final Logger log = LoggerFactory.getLogger(SoapService.class);

    public XMLGregorianCalendar getTime() {

        GetTimeRequest request = new GetTimeRequest();

        log.info("Requesting time");

        GetTimeResponse response = (GetTimeResponse) getWebServiceTemplate()
                .marshalSendAndReceive("http://localhost:8081/ws/time", request,
                        new SoapActionCallback(
                                "http://test.com/gear/GetTimeRequest"));

        return response.getDateTime();
    }

}
