package edu.sanekas.cashmachine.api;

import edu.sanekas.api.Nominal;

import java.util.Map;

public interface CashManipulator {
    int putCash(Map<Nominal, Integer> commandOptions);
    Map<Nominal, Integer> getCash(Map<Nominal, Integer> commandOptions);
    Map<Nominal, Integer> dump();
    int state();
}
