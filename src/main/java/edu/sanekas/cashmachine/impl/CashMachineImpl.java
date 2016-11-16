package edu.sanekas.cashmachine.impl;

import edu.sanekas.cashmachine.api.CashMachine;
import edu.sanekas.command.api.CommandExecutor;
import edu.sanekas.console.api.ConsoleProcesser;
import edu.sanekas.wrapper.api.InputWrapper;
import edu.sanekas.wrapper.api.OutputWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CashMachineImpl implements CashMachine {
    private final CommandExecutor commandExecutor;
    private final ConsoleProcesser consoleProcesser;

    @Autowired
    public CashMachineImpl(CommandExecutor commandExecutor, ConsoleProcesser consoleProcesser) {
        this.commandExecutor = commandExecutor;
        this.consoleProcesser = consoleProcesser;
    }

    public InputWrapper processInput() {
        return consoleProcesser.getProcessedInput();
    }

    public OutputWrapper executeCommand(InputWrapper inputWrapper) {
        return commandExecutor.execute(inputWrapper);
    }

    public void processOutput(OutputWrapper outputWrapper) {
        consoleProcesser.printResult(outputWrapper);
    }
}
