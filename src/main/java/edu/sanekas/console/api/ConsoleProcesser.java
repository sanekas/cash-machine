package edu.sanekas.console.api;

import edu.sanekas.wrapper.api.OutputWrapper;

public interface ConsoleProcesser {
    String readInput();
    String printOutput(OutputWrapper outputWrapper);
    String printError(Throwable throwable);
    void closeInputStream();
}
