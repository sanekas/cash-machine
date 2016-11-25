package edu.sanekas.cashmachine.api;

import edu.sanekas.api.Nominal;
import edu.sanekas.wrapper.api.InputWrapper;

import java.util.Map;

/**
 * The interface represents direct interaction API with core of cash-machine
 */
public interface CashManipulator {
    /**
     * @param inputWrapper represents processed command with parameters: put <nominal> <number>
     * @return current state of cash-machine
     */
    long putCash(InputWrapper inputWrapper);

    /**
     * @param inputWrapper represents processed command with parameters: get <number>
     * @return
     */
    Map<Nominal, Long> getCash(InputWrapper inputWrapper);

    /**
     * @return expanded state of cash-machine
     */
    Map<Nominal, Long> dump();

    /**
     * @return current state of cah-machine
     */
    long state();
}
