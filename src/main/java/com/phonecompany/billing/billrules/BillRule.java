package com.phonecompany.billing.billrules;

import com.phonecompany.billing.PhoneCall;

import java.math.BigDecimal;

public interface BillRule {
    BigDecimal apply(PhoneCall phoneCall);
}
