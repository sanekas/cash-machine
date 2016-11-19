package edu.sanekas.command.impl.commands;

import edu.sanekas.api.Nominal;
import edu.sanekas.cashmachine.api.CashManipulator;
import edu.sanekas.command.api.Command;
import edu.sanekas.wrapper.api.InputWrapper;
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
    public OutputWrapper execute(InputWrapper inputWrapper) {
        Map<Nominal, Integer> cash = cashManipulator.getCash(inputWrapper);
        OutputWrapper<Map<Nominal, Integer>> outputWrapper = wrapperFactory.createOutputWrapper();
        int receivedCash = 0;
        for (Map.Entry<Nominal, Integer> cashPair : cash.entrySet()) {
            receivedCash += cashPair.getValue() * cashPair.getKey().getNominal();
        }
        cash.put(Nominal.ANY, receivedCash);
        outputWrapper.setWrappedEntity(cash);
        prepareOutput(outputWrapper, inputWrapper.getCash());
        return outputWrapper;
    }

    private void prepareOutput(OutputWrapper<Map<Nominal, Integer>> outputWrapper, int requestedCash) {
        StringBuilder result = new StringBuilder();
        outputWrapper.getWrappedEntity().entrySet().stream()
                .filter(cashPair -> cashPair.getKey() != Nominal.ANY && cashPair.getValue() != 0)
                .forEach(cashPair -> result.append(cashPair.getKey()).append("=").append(cashPair.getValue()).append(","));

        int totalReceivedCash = outputWrapper.getWrappedEntity().get(Nominal.ANY);
        result.append(" total ").append(totalReceivedCash);

        int dif = totalReceivedCash - requestedCash;

        if (dif < 0) {
            result.append("\n" + "without ").append(String.valueOf(-dif));
        }
        outputWrapper.setOutputRepresentation(result.toString().trim());

    }
}
