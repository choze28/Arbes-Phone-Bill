package com.phonecompany.billing;

import org.junit.Test;

import java.text.ParseException;

public class TelephoneBillCalculatorImplTest {

    @Test
    void testOneLine() throws ParseException {
        TelephoneBillCalculatorImpl t = new TelephoneBillCalculatorImpl();

        t.calculate("420774577453,13-01-2020 18:10:15,13-01-2020 18:12:57");


    }
}
