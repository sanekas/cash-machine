package edu.sanekas.cashmachine.api;

import edu.sanekas.api.Nominal;
import edu.sanekas.wrapper.api.InputWrapper;

import java.util.Map;

public interface CashManipulator {
    int putCash(InputWrapper inputWrapper);
    Map<Nominal, Integer> getCash(InputWrapper inputWrapper);
    Map<Nominal, Integer> dump();
    int state();
}
