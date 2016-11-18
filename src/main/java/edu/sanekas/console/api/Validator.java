package edu.sanekas.console.api;

import edu.sanekas.api.Operation;
import edu.sanekas.wrapper.api.InputWrapper;

public interface Validator {
    InputWrapper validateOptions(Operation operation, String[] options);
}
