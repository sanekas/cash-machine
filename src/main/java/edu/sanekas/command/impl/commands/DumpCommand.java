package edu.sanekas.command.impl.commands;

import edu.sanekas.api.Nominal;
import edu.sanekas.cashmachine.api.CashManipulator;
import edu.sanekas.command.api.Command;
import edu.sanekas.wrapper.api.InputWrapper;
import edu.sanekas.wrapper.api.OutputWrapper;
import edu.sanekas.wrapper.api.WrapperFactory;

import java.util.Map;

public class DumpCommand implements Command {

    private final CashManipulator cashManipulator;
    private final WrapperFactory wrapperFactory;

    public DumpCommand(CashManipulator cashManipulator, WrapperFactory wrapperFactory) {
        this.cashManipulator = cashManipulator;
        this.wrapperFactory = wrapperFactory;
    }

    @Override
    public OutputWrapper execute(InputWrapper inputWrapper) {
        Map<Nominal, Integer> dump = cashManipulator.dump();
        OutputWrapper<Map<Nominal, Integer>> outputWrapper = wrapperFactory.createOutputWrapper();
        outputWrapper.setWrappedEntity(dump);
        prepareOutput(outputWrapper);
        return outputWrapper;
    }

    private void prepareOutput(OutputWrapper<Map<Nominal, Integer>>  outputWrapper) {
        StringBuilder outputRepresentation = new StringBuilder();
        outputWrapper.getWrappedEntity().entrySet().stream()
                .filter(cashPair -> cashPair.getValue() != 0).forEach(cashPair ->
                outputRepresentation.append(cashPair.getKey()).append(" ").append(cashPair.getValue()).append("\n"));
        outputWrapper.setOutputRepresentation(outputRepresentation.toString().trim());
    }
}
