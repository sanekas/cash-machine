package edu.sanekas.command.impl.commands;

import edu.sanekas.command.api.Command;
import edu.sanekas.wrapper.api.InputWrapper;
import edu.sanekas.wrapper.api.OutputWrapper;

/**
 * This is a mock class, for now it's not used, but it can be used later
 */
public class QuitCommand implements Command {
    @Override
    public OutputWrapper execute(InputWrapper inputWrapper) {
        return null;
    }
}
