package edu.sanekas.cashmachine.impl;

import edu.sanekas.api.GeneralDefaults;
import edu.sanekas.api.Nominal;
import edu.sanekas.cashmachine.api.CashManipulator;
import edu.sanekas.wrapper.api.InputWrapper;
import org.springframework.stereotype.Component;

import java.util.EnumMap;
import java.util.Map;

@Component
public class CashManipulatorImpl implements CashManipulator {
    private final Map<Nominal, Integer> cash = new EnumMap<>(Nominal.class);
    private int state = 0;

    @Override
    public int putCash(InputWrapper inputWrapper) {
        cash.merge(inputWrapper.getNominal(), inputWrapper.getCash(), Integer::sum);
        int receivedCash = inputWrapper.getCash() * inputWrapper.getNominal().getNominal();
        if (receivedCash < 0 || state + receivedCash >= Integer.MAX_VALUE || state + receivedCash < 0) {
            throw new RuntimeException(GeneralDefaults.TOO_MUCH_MONEY);
        }
        state += receivedCash;
        return state;
    }

    @Override
    public Map<Nominal, Integer> getCash(InputWrapper inputWrapper) {
        Map<Nominal, Integer> removableCash = new EnumMap<>(Nominal.class);
        Integer requiredCash = inputWrapper.getCash();
        if (requiredCash >= Integer.MAX_VALUE) {
            throw new IllegalArgumentException(GeneralDefaults.TOO_MUCH_MONEY);
        }
        if (requiredCash <= state) {
            for (Map.Entry<Nominal, Integer> cashPair : cash.entrySet()) {
                int removablePart = requiredCash / cashPair.getKey().getNominal();
                int availablePart = (cashPair.getValue() - removablePart >= 0) ? removablePart : cashPair.getValue();
                cash.merge(cashPair.getKey(), -availablePart, Integer::sum);
                removableCash.merge(cashPair.getKey(), availablePart, Integer::sum);
                requiredCash -= availablePart * cashPair.getKey().getNominal();
                state -= cashPair.getKey().getNominal() * availablePart;
            }
        }
        return removableCash;
    }

    @Override
    public Map<Nominal, Integer> dump() {
        return new EnumMap<>(cash);
    }

    @Override
    public int state() {
        return state;
    }
}
