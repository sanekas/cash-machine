package edu.sanekas.cashmachine;

import edu.sanekas.api.Nominal;
import edu.sanekas.cashmachine.api.CashManipulator;
import org.springframework.stereotype.Component;

import java.util.EnumMap;
import java.util.Map;

@Component
public class CashManipulatorImpl implements CashManipulator {
    private final Map<Nominal, Integer> cash = new EnumMap<>(Nominal.class);
    private int state = 0;

    @Override
    public int putCash(Map<Nominal, Integer> commandOptions) {
        for (Map.Entry<Nominal, Integer> cashPair : commandOptions.entrySet()) {
            cash.merge(cashPair.getKey(), cashPair.getValue(), Integer::sum);
            state += cashPair.getValue() * cashPair.getKey().getNominal();
        }
        return state;
    }

    @Override
    public void getCash(Map<Nominal, Integer> commandOptions) {
        Integer requiredCash = commandOptions.get(Nominal.ANY);
        if (requiredCash >= state) {

        }

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
