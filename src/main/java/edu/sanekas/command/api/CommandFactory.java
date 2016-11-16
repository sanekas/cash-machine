package edu.sanekas.command.api;

import edu.sanekas.api.Operation;

public interface CommandFactory {
    Command createCommand(Operation operation);
}
