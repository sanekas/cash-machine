package edu.sanekas.command.impl.commands;

import edu.sanekas.api.Nominal;
import edu.sanekas.cashmachine.api.CashManipulator;
import edu.sanekas.command.api.Command;
import edu.sanekas.wrapper.api.OutputWrapper;
import edu.sanekas.wrapper.api.WrapperFactory;

import java.util.Map;

public class GetCommand implements Command {
    private final CashManipulator cashManipulator;
    private final WrapperFactory wrapperFactory;

    public GetCommand(CashManipulator cashManipulator, WrapperFactory wrapperFactory) {
        this.cashManipulator = cashManipulator;
        this.wrapperFactory = wrapperFactory;
    }

    @Override
    public OutputWrapper execute(Map<Nominal, Integer> commandOptions) {
        Map<Nominal, Integer> cash = cashManipulator.getCash(commandOptions);
        OutputWrapper<Map<Nominal, Integer>> outputWrapper = wrapperFactory.createOutputWrapper();
        int issuedMoney = cash.values().stream().mapToInt(Integer::intValue).sum();
        if (commandOptions.get(Nominal.ANY) - issuedMoney > 0) {
            cash.put(Nominal.ANY, issuedMoney);
        }
        outputWrapper.setWrappedEntity(cash);
        return outputWrapper;
    }
}
