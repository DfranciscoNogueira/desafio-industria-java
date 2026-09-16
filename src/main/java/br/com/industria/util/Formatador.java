package br.com.industria.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public final class Formatador {

    private static final DateTimeFormatter DATA = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final DecimalFormat NUMERO;

    static {
        DecimalFormatSymbols s = DecimalFormatSymbols.getInstance(new Locale("pt", "BR"));
        s.setGroupingSeparator('.');
        s.setDecimalSeparator(',');
        NUMERO = new DecimalFormat("#,##0.00", s);
    }

    private Formatador() {
    }

    public static String data(LocalDate valor) {
        return valor.format(DATA);
    }

    public static synchronized String numero(BigDecimal valor) {
        return NUMERO.format(valor);
    }

}
