package pl.com.chrzanowski.scaffolding.logic;

import pl.com.chrzanowski.scaffolding.domain.currency.CurrencyData;
import pl.com.chrzanowski.scaffolding.domain.currency.CurrencyFilter;

import java.util.List;

public interface ICurrency {
    Long create(CurrencyData data);
    void update(CurrencyData data);
    void remove(CurrencyData data);
    List<CurrencyData>find(CurrencyFilter filter);
}
