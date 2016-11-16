package edu.sanekas.command.impl.commands;

import edu.sanekas.api.Nominal;
import edu.sanekas.cashmachine.api.CashManipulator;
import edu.sanekas.command.api.Command;
import edu.sanekas.wrapper.api.OutputWrapper;
import edu.sanekas.wrapper.api.WrapperFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class DumpCommand implements Command {

    private final CashManipulator cashManipulator;
    private final WrapperFactory<Map<Nominal, Integer>> wrapperFactory;

    @Autowired
    public DumpCommand(CashManipulator cashManipulator, WrapperFactory<Map<Nominal, Integer>> wrapperFactory) {
        this.cashManipulator = cashManipulator;
        this.wrapperFactory = wrapperFactory;
    }

    @Override
    public OutputWrapper execute(Map<Nominal, Integer> commandOptions) {
        Map<Nominal, Integer> dump = cashManipulator.dump();
        OutputWrapper<Map<Nominal, Integer>> outputWrapper = wrapperFactory.createOutputWrapper();
        outputWrapper.setWrappedEntity(dump);
        return outputWrapper;
    }
}
