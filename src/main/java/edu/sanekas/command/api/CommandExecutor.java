package edu.sanekas.command.api;

import edu.sanekas.wrapper.api.InputWrapper;
import edu.sanekas.wrapper.api.OutputWrapper;

/**
 * The interface represents API for command execution
 */
public interface CommandExecutor {
    OutputWrapper execute(InputWrapper inputWrapper);
}
