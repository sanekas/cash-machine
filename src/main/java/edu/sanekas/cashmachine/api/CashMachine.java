package edu.sanekas.cashmachine.api;

import edu.sanekas.wrapper.api.InputWrapper;
import edu.sanekas.wrapper.api.OutputWrapper;

/**
 * The interface represents basic API of cash-machine
 */
public interface CashMachine {
    /**
     *
     * @return processed input string
     */
    InputWrapper processInput();

    /**
     * @param inputWrapper represents input object with command and parameters
     * @return processed command
     */
    OutputWrapper executeCommand(InputWrapper inputWrapper);

    /**
     * @param outputWrapper represents output object
     */
    void processOutput(OutputWrapper outputWrapper);
}
