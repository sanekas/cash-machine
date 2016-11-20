package edu.sanekas.cashmachine.impl;

import edu.sanekas.api.Nominal;
import edu.sanekas.cashmachine.api.CashManipulator;
import edu.sanekas.wrapper.api.InputWrapper;
import org.springframework.stereotype.Component;

import java.util.EnumMap;
import java.util.Map;

@Component
public class CashManipulatorImpl implements CashManipulator {
    private final Map<Nominal, Long> cash = new EnumMap<>(Nominal.class);
    private int state = 0;

    @Override
    public long putCash(InputWrapper inputWrapper) {
        cash.merge(inputWrapper.getNominal(), inputWrapper.getCash(), Long::sum);
        long receivedCash = inputWrapper.getCash() * inputWrapper.getNominal().getNominal();
        state += receivedCash;
        return state;
    }

    @Override
    public Map<Nominal, Long> getCash(InputWrapper inputWrapper) {
        Map<Nominal, Long> removableCash = new EnumMap<>(Nominal.class);
        Long requiredCash = inputWrapper.getCash();
        for (Map.Entry<Nominal, Long> cashPair : cash.entrySet()) {
            long removablePart = requiredCash / cashPair.getKey().getNominal();
            long availablePart = (cashPair.getValue() - removablePart >= 0) ? removablePart : cashPair.getValue();
            cash.merge(cashPair.getKey(), -availablePart, Long::sum);
            removableCash.merge(cashPair.getKey(), availablePart, Long::sum);
            requiredCash -= availablePart * cashPair.getKey().getNominal();
            state -= cashPair.getKey().getNominal() * availablePart;
        }
        return removableCash;
    }

    @Override
    public Map<Nominal, Long> dump() {
        return new EnumMap<>(cash);
    }

    @Override
    public long state() {
        return state;
    }
}
