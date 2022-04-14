package pl.com.chrzanowski.scaffolding.logic.currency;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

//all major currencies her for select field when new contractor is created
public enum CurrencyCodes {
    AMERICAN_DOLLAR("Dolar Amerykański", "American Dollar", "USD"),
    POLSKI_ZLOTY("Polski Złoty", "Polish Zloty", "PLN"),
    SWISS_FRANK("Frank szwajcarski", "Swiss Frank", "CHF");


    private String namePL;
    private String nameEN;
    private String code;

    CurrencyCodes(String namePL, String nameEN, String code) {
        this.namePL = namePL;
        this.nameEN = nameEN;
        this.code = code;
    }

    public String getNamePL() {
        return namePL;
    }

    public String getNameEN() {
        return nameEN;
    }

    public String getCode() {
        return code;
    }

    public static Map<String, String> LOAD_CURRENCY_PL = Stream.of(values()).collect(Collectors.toMap(k -> k.namePL, v -> v.code));

    public static Map<String, String> LOAD_CURRENCY_EN = Stream.of(values()).collect(Collectors.toMap(k -> k.nameEN, v -> v.code));
}
