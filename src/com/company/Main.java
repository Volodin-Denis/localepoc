package com.company;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

public class Main {

    public static void main(String[] args) throws IOException {
        LocalDate ukDate = getDate("en-GB", Locale.UK, FormatStyle.MEDIUM);
        LocalDate gerDate = getDate("de-DE", Locale.GERMANY, FormatStyle.LONG);

        System.out.println(ukDate.equals(gerDate));
    }

    private static LocalDate getDate(String fileName, Locale locale, FormatStyle aLong) throws IOException {
        String dateString = getDateStringFromFile(fileName);
        DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDate(aLong).withLocale(locale);
        return LocalDate.parse(dateString, formatter);
    }

    private static String getDateStringFromFile(String fileName) throws IOException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(fileName);
        return new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
    }
}
