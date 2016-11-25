package edu.sanekas.command.api;

import edu.sanekas.wrapper.api.InputWrapper;
import edu.sanekas.wrapper.api.OutputWrapper;

/**
 * The interface represents different commands
 */
public interface Command {
    OutputWrapper execute(InputWrapper inputWrapper);
}
