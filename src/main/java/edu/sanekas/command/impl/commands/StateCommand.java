package edu.sanekas.command.impl.commands;

import edu.sanekas.cashmachine.api.CashManipulator;
import edu.sanekas.command.api.Command;
import edu.sanekas.wrapper.api.InputWrapper;
import edu.sanekas.wrapper.api.OutputWrapper;
import edu.sanekas.wrapper.api.WrapperFactory;

public class StateCommand implements Command {
    private final CashManipulator cashManipulator;
    private final WrapperFactory wrapperFactory;

    public StateCommand(CashManipulator cashManipulator, WrapperFactory wrapperFactory) {
        this.cashManipulator = cashManipulator;
        this.wrapperFactory = wrapperFactory;
    }

    @Override
    public OutputWrapper execute(InputWrapper inputWrapper) {
        long cash = cashManipulator.state();
        OutputWrapper<Long> outputWrapper = wrapperFactory.createOutputWrapper();
        outputWrapper.setWrappedEntity(cash);
        prepareOutput(outputWrapper);
        return outputWrapper;
    }

    private void prepareOutput(OutputWrapper<Long> outputWrapper) {
        outputWrapper.setOutputRepresentation(outputWrapper.getWrappedEntity().toString().trim());
    }
}
