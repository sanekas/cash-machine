package edu.sanekas.wrapper.impl.wrappers;

import edu.sanekas.api.Nominal;
import edu.sanekas.api.Operation;
import edu.sanekas.wrapper.api.InputWrapper;

public class InputWrapperImpl implements InputWrapper {
    private final Operation operation;
    private final Nominal nominal;
    private final Integer cash;

    //TODO: Replace by builder

    public InputWrapperImpl(Operation operation) {
        this.operation = operation;
        this.nominal = null;
        this.cash = null;
    }

    public InputWrapperImpl(Operation operation, Nominal nominal, Integer cash) {
        this.operation = operation;
        this.nominal = nominal;
        this.cash = cash;
    }

    @Override
    public Operation getOperation() {
        return operation;
    }

    @Override
    public Nominal getNominal() {
        return nominal;
    }

    @Override
    public Integer getCash() {
        return cash;
    }
}
