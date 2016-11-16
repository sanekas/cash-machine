package edu.sanekas.wrapper.impl.wrappers;

import edu.sanekas.api.Nominal;
import edu.sanekas.api.Operation;
import edu.sanekas.wrapper.api.InputWrapper;

import java.util.Map;

public class InputWrapperImpl implements InputWrapper {
    private final Operation operation;
    private final Map<Nominal, Integer> commandOptions;

    public InputWrapperImpl(Operation operation, Map<Nominal, Integer> commandOptions) {
        this.operation = operation;
        this.commandOptions = commandOptions;
    }

    public Operation getOperation() {
        return operation;
    }

    public Map<Nominal, Integer> getCommandOptions() {
        return commandOptions;
    }
}
