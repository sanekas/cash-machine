package edu.sanekas.command.impl;

import edu.sanekas.api.Operation;
import edu.sanekas.command.api.Command;
import edu.sanekas.command.api.CommandFactory;
import edu.sanekas.command.impl.commands.*;
import org.springframework.stereotype.Service;


@Service
public class CommandFactoryImpl implements CommandFactory {
    @Override
    public Command createCommand(Operation operation) {
        switch (operation) {
            case PUT: return new PutCommand();
            case GET: return new GetCommand();
            case DUMP: return new DumpCommand();
            case STATE: return new StateCommand();
            case QUIT: return new QuitCommand();
        }
        throw new IllegalArgumentException("Such operation doesn't exist: " + operation);
    }
}
