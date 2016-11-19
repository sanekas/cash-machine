package edu.sanekas.command.impl;

import edu.sanekas.api.GeneralDefaults;
import edu.sanekas.api.Operation;
import edu.sanekas.cashmachine.api.CashManipulator;
import edu.sanekas.command.api.Command;
import edu.sanekas.command.api.CommandFactory;
import edu.sanekas.command.impl.commands.*;
import edu.sanekas.wrapper.api.WrapperFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CommandFactoryImpl implements CommandFactory {
    private final CashManipulator cashManipulator;
    private final WrapperFactory wrapperFactory;

    @Autowired
    public CommandFactoryImpl(CashManipulator cashManipulator, WrapperFactory wrapperFactory) {
        this.cashManipulator = cashManipulator;
        this.wrapperFactory = wrapperFactory;
    }

    @Override
    public Command createCommand(Operation operation) {
        switch (operation) {
            case PUT: return new PutCommand(cashManipulator, wrapperFactory);
            case GET: return new GetCommand(cashManipulator, wrapperFactory);
            case STATE: return new StateCommand(cashManipulator, wrapperFactory);
            case DUMP: return new DumpCommand(cashManipulator, wrapperFactory);
            case QUIT: return new QuitCommand(); // mock
        }

        throw new IllegalArgumentException(String.format(GeneralDefaults.OPERATION_NOT_EXIST, operation));
    }
}
