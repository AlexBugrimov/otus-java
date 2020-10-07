package ru.otus.bson.jsonTypes;

import java.math.BigDecimal;
import java.math.BigInteger;

public class JsonNumber implements javax.json.JsonNumber {

    private final Number number;

    public JsonNumber(Number number) {
        this.number = number;
    }

    @Override
    public boolean isIntegral() {
        return false;
    }

    @Override
    public int intValue() {
        return number.intValue();
    }

    @Override
    public int intValueExact() {
        return intValue();
    }

    @Override
    public long longValue() {
        return number.longValue();
    }

    @Override
    public long longValueExact() {
        return longValue();
    }

    @Override
    public BigInteger bigIntegerValue() {
        return BigInteger.valueOf(number.longValue());
    }

    @Override
    public BigInteger bigIntegerValueExact() {
        return bigIntegerValue();
    }

    @Override
    public double doubleValue() {
        return number.doubleValue();
    }

    @Override
    public BigDecimal bigDecimalValue() {
        return BigDecimal.valueOf(number.doubleValue());
    }

    @Override
    public ValueType getValueType() {
        return ValueType.NUMBER;
    }

    @Override
    public String toString() {
        return String.valueOf(number);
    }
}
