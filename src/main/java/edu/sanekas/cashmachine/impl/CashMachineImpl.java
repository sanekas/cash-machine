package edu.sanekas.cashmachine.impl;

import edu.sanekas.api.Operation;
import edu.sanekas.cashmachine.api.CashMachine;
import edu.sanekas.command.api.CommandExecutor;
import edu.sanekas.console.api.ConsoleProcesser;
import edu.sanekas.console.api.InputProcesser;
import edu.sanekas.wrapper.api.InputWrapper;
import edu.sanekas.wrapper.api.OutputWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CashMachineImpl implements CashMachine {
    private final CommandExecutor commandExecutor;
    private final InputProcesser inputProcesser;
    private final ConsoleProcesser consoleProcesser;

    @Autowired
    public CashMachineImpl(CommandExecutor commandExecutor, InputProcesser inputProcesser,
                           ConsoleProcesser consoleProcesser) {
        this.commandExecutor = commandExecutor;
        this.inputProcesser = inputProcesser;
        this.consoleProcesser = consoleProcesser;
    }

    public InputWrapper processInput() {
        try {
            String input = consoleProcesser.readInput();
            InputWrapper inputWrapper = inputProcesser.processInput(input);
            if (inputWrapper.getOperation() == Operation.QUIT) {
                consoleProcesser.closeInputStream();
            }
            return inputWrapper;
        } catch (Exception e) {
            consoleProcesser.printError(e);
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    public OutputWrapper executeCommand(InputWrapper inputWrapper) {
        return commandExecutor.execute(inputWrapper);
    }

    public void processOutput(OutputWrapper outputWrapper) {
        consoleProcesser.printOutput(outputWrapper);
    }
}
