package com.gear.test.util;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

public class TimeUtil {

    public static String getTimeFromXMLGregorianCalendar(XMLGregorianCalendar xgc) throws DatatypeConfigurationException {
        return DatatypeFactory.newInstance()
                .newXMLGregorianCalendarTime(xgc.getHour(), xgc.getMinute(), xgc.getSecond(),
                        xgc.getFractionalSecond(), xgc.getTimezone()).toString();
    }

}
