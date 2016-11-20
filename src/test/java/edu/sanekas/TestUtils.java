package edu.sanekas;

import edu.sanekas.api.Nominal;
import edu.sanekas.api.Operation;
import edu.sanekas.command.api.Command;
import edu.sanekas.command.api.CommandFactory;
import edu.sanekas.wrapper.api.InputWrapper;

public class TestUtils {
    private static final Long CASH = 26263260L;
    public static void putCash(CommandFactory commandFactory, InputWrapper inputWrapper) {
        Command putCommand = commandFactory.createCommand(Operation.PUT);
        for (Nominal nominal : Nominal.values()) {
            inputWrapper.setNominal(nominal);
            if (nominal != Nominal.ANY) {
                inputWrapper.setCash((long) nominal.getNominal());
                putCommand.execute(inputWrapper);
            }
        }
    }

    public static void removeCash(CommandFactory commandFactory, InputWrapper inputWrapper) {
        Command getCommand = commandFactory.createCommand(Operation.GET);
        inputWrapper.setOperation(Operation.GET);
        inputWrapper.setNominal(Nominal.ANY);
        inputWrapper.setCash(CASH);
        getCommand.execute(inputWrapper);
    }

}
