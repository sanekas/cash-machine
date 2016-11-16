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
    public Map<Nominal, Integer> getCash(Map<Nominal, Integer> commandOptions) {
        Map<Nominal, Integer> removableCash = new EnumMap<>(Nominal.class);
        Integer requiredCash = commandOptions.get(Nominal.ANY);
        if (requiredCash >= state) {
            for (Map.Entry<Nominal, Integer> cashPair : cash.entrySet()) {
                int removablePart = requiredCash / cashPair.getKey().getNominal();
                cash.merge(cashPair.getKey(), -removablePart, Integer::sum);
                removableCash.merge(cashPair.getKey(), removablePart, Integer::sum);
                requiredCash -= removablePart * cashPair.getKey().getNominal();
                state -= cashPair.getKey().getNominal();
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
