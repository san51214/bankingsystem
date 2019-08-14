package com.bankingsystem.clientstransactions.utils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CommonUtils {

    public static ObjectMapper getObjectMapper(boolean failOnUnknownProperties)
    {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, failOnUnknownProperties);

        return objectMapper;
    }
}
