package com.financials.canopus.utils;

import org.apache.commons.lang3.RandomStringUtils;

public class IDUtils {
    public static String generateExternalId(String prefix) {
        return prefix + RandomStringUtils.randomAlphanumeric(20);
    }
}
