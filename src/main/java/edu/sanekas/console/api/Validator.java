package edu.sanekas.console.api;

import edu.sanekas.api.Operation;
import edu.sanekas.wrapper.api.InputWrapper;

/**
 * Different implementations of this class validate I/O
 */
public interface Validator {
    InputWrapper validateOptions(Operation operation, String[] options);
}
