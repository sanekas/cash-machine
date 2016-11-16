package edu.sanekas.cashmachine.api;

import edu.sanekas.wrapper.api.InputWrapper;
import edu.sanekas.wrapper.api.OutputWrapper;

public interface CashMachine {
    InputWrapper processInput();
    OutputWrapper executeCommand(InputWrapper inputWrapper);
    void processOutput(OutputWrapper outputWrapper);
}
