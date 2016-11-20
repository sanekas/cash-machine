package edu.sanekas.cashmachine.api;

import edu.sanekas.api.Nominal;
import edu.sanekas.wrapper.api.InputWrapper;

import java.util.Map;

public interface CashManipulator {
    long putCash(InputWrapper inputWrapper);
    Map<Nominal, Long> getCash(InputWrapper inputWrapper);
    Map<Nominal, Long> dump();
    long state();
}
