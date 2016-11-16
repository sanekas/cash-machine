package edu.sanekas.command.impl;

import edu.sanekas.api.Operation;
import edu.sanekas.command.api.Command;
import edu.sanekas.command.api.CommandExecutor;
import edu.sanekas.command.api.CommandFactory;
import edu.sanekas.wrapper.api.InputWrapper;
import edu.sanekas.wrapper.api.OutputWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.Map;

@Service
public final class CommandExecutorImpl implements CommandExecutor {

    private static final Map<Operation, Command> operationCommandMap = new EnumMap<>(Operation.class);

    private final CommandFactory commandFactory;

    @Autowired
    public CommandExecutorImpl(CommandFactory commandFactory) {
        this.commandFactory = commandFactory;
        initCommandMap();
    }

    private void initCommandMap() {
        Arrays.asList(Operation.values()).forEach((Operation operation) ->
                                operationCommandMap.put(operation, commandFactory.createCommand(operation)));
    }


    @Override
    public final OutputWrapper execute(InputWrapper inputWrapper) {
        return operationCommandMap.get(inputWrapper.getOperation()).execute(inputWrapper.getCommandOptions());
    }
}
