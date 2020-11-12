package com.phonecompany.billing;

import com.phonecompany.billing.billrules.BillRule;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.StringTokenizer;

public class TelephoneBillCalculatorImpl implements TelephoneBillCalculator {

    // TODO naplneni treba resit pres DI v Spring nebo jinak dle
    List<BillRule> rules;

    public BigDecimal calculate(String phoneLog) throws ParseException {
        List<PhoneCall> calls = parseCsv(phoneLog);

        return calculate(calls);
    }


    /**
     * Parse CSV file
     * @param phoneLog CSV
     * @return list of phone calls objects
     * @throws ParseException if data are not valid
     */
    private List<PhoneCall> parseCsv(String phoneLog) throws ParseException {
        int PHONE_TOKEN = 0;
        int START_DATE_TOKEN = 1;
        int END_DATE_TOKEN = 2;

        String DATE_FORMAT = "dd-MM-yyyy HH:mm:ss";
        final SimpleDateFormat dateFormater = new SimpleDateFormat(DATE_FORMAT);

        final DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("DATE_FORMAT", Locale.US);

        StringTokenizer st = new StringTokenizer(phoneLog, "\n");
        List<PhoneCall> calls = new ArrayList<PhoneCall>();

        // parse csv
        for (int i = 0; st.hasMoreTokens(); i++) {
            String[] lineTokens = st.nextToken().split(",");
            calls.add(new PhoneCall(
                    lineTokens[PHONE_TOKEN],
                    LocalDateTime.parse(lineTokens[START_DATE_TOKEN], formatter),
                    LocalDateTime.parse(lineTokens[END_DATE_TOKEN], formatter)));
        }

        return calls;
    }


    /**
     * Minutová sazba v intervalu <8:00:00,16:00:00) je zpoplatněna 1 Kč za každou započatou minutu.
     * Mimo uvedený interval platí snížená sazba 0,50 Kč za každou započatou minutu.
     * Pro každou minutu hovoru je pro stanovení sazby určující čas započetí dané minuty.
     *
     * Pro hovory delší, než pět minut platí pro každou další započatou minutu nad rámec prvních pěti minut
     * snížená sazba 0,20 Kč bez ohledu na dobu kdy telefonní hovor probíhá.

     * V rámci promo akce operátora dále platí, že hovory na nejčastěji volané číslo v rámci výpisu
     * nebudou zpoplatněny. V případě, že by výpis obsahoval dvě nebo více takových čísel,
     * zpoplatněny nebudou hovory na číslo s aritmeticky nejvyšší hodnotou.
     *
     * @param calls phone calls
     * @return amont to be payed by custommer
     */
    private BigDecimal calculate(List<PhoneCall> calls) {
        BigDecimal amount = BigDecimal.ZERO;

        for (PhoneCall call : calls) {
            for (BillRule rule : rules) {

                // TODO tady je jenom nastrel jak by jsem to resil.
                // Reseni by bylo jeste slozitejsi protoze pravidla se neaplikuji jenom na radek,
                // nybrz take na vice radku (nejcastejsi volane cislo)
                amount = amount.add(rule.apply(call));
            }
        }

        return amount;
    }
}
