package com.phonecompany.billing.billrules;

import com.phonecompany.billing.PhoneCall;

import java.math.BigDecimal;

/**
 * Minutová sazba v intervalu <8:00:00,16:00:00) je zpoplatněna 1 Kč za každou započatou minutu.
 * Mimo uvedený interval platí snížená sazba 0,50 Kč za každou započatou minutu.
 * Pro každou minutu hovoru je pro stanovení sazby určující čas započetí dané minuty.
 *
 * Pro hovory delší, než pět minut platí pro každou další započatou minutu nad rámec prvních pěti minut
 * snížená sazba 0,20 Kč bez ohledu na dobu kdy telefonní hovor probíhá.
 */
public class BillRule0816 implements BillRule {
    @Override
    public BigDecimal apply(PhoneCall phoneCall) {

        // todo implementovat
        return null;
    }
}
