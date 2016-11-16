package edu.sanekas.console.api;

import edu.sanekas.wrapper.api.InputWrapper;
import edu.sanekas.wrapper.api.OutputWrapper;

public interface ConsoleProcesser {
    InputWrapper getProcessedInput();
    void printResult(OutputWrapper outputWrapper);
}
