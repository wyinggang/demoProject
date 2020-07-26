package com.webservice;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

@WebService(name = "WeatherSOAP",portName = "WeatherSOAPPort"
        ,serviceName = "WeatherWSS",targetNamespace = "http://service.cad.com")
public class WeatherInterfaceImpl implements WeatherInterface{

    @Override
    @WebMethod(operationName = "getWeather")
    public @WebResult(name = "result") String queryWeather(@WebParam(name = "cityName") String cityName) {
        return cityName;
    }
}
