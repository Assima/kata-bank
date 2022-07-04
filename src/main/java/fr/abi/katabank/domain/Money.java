package fr.abi.katabank.domain;

import java.text.DecimalFormat;

public class Money {
    private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("#.00");
    private final int value;

    public int getValue() {
        return value;
    }

    public String getFormattedValue() {
        return DECIMAL_FORMAT.format(value);
    }

    public Money(int value) {
        this.value = value;
    }

    public static Money of(int value) {
        return new Money(value);
    }

    public Money plus(Money otherMoney) {
        return of(this.value + Math.abs(otherMoney.value));
    }

    public Money minus(Money otherMoney) {
        return of(this.value - Math.abs(otherMoney.value));
    }
}
