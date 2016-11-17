package edu.sanekas.command.impl.commands;

import edu.sanekas.api.Nominal;
import edu.sanekas.cashmachine.api.CashManipulator;
import edu.sanekas.command.api.Command;
import edu.sanekas.wrapper.api.OutputWrapper;
import edu.sanekas.wrapper.api.WrapperFactory;

import java.util.Map;

public class PutCommand implements Command {
    private final CashManipulator cashManipulator;
    private final WrapperFactory wrapperFactory;

    public PutCommand(CashManipulator cashManipulator, WrapperFactory wrapperFactory) {
        this.cashManipulator = cashManipulator;
        this.wrapperFactory = wrapperFactory;
    }

    @Override
    public OutputWrapper execute(Map<Nominal, Integer> commandOptions) {
        int cash = cashManipulator.putCash(commandOptions);
        OutputWrapper<Integer> outputWrapper = wrapperFactory.createOutputWrapper();
        outputWrapper.setWrappedEntity(cash);
        return outputWrapper;
    }
}
