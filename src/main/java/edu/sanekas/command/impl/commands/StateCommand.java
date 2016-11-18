package edu.sanekas.command.impl.commands;

import edu.sanekas.api.Nominal;
import edu.sanekas.cashmachine.api.CashManipulator;
import edu.sanekas.command.api.Command;
import edu.sanekas.wrapper.api.OutputWrapper;
import edu.sanekas.wrapper.api.WrapperFactory;

import java.util.Map;

public class StateCommand implements Command {
    private final CashManipulator cashManipulator;
    private final WrapperFactory wrapperFactory;

    public StateCommand(CashManipulator cashManipulator, WrapperFactory wrapperFactory) {
        this.cashManipulator = cashManipulator;
        this.wrapperFactory = wrapperFactory;
    }

    @Override
    public OutputWrapper execute(Map<Nominal, Integer> commandOptions) {
        int cash = cashManipulator.state();
        OutputWrapper<Integer> outputWrapper = wrapperFactory.createOutputWrapper();
        outputWrapper.setWrappedEntity(cash);
        prepareOutput(outputWrapper);
        return outputWrapper;
    }

    private void prepareOutput(OutputWrapper<Integer> outputWrapper) {
        outputWrapper.setOutputRepresentation(outputWrapper.getWrappedEntity().toString().trim());
    }
}
