package edu.sanekas.console.impl;

import edu.sanekas.api.Operation;
import edu.sanekas.console.api.InputProcesser;
import edu.sanekas.console.api.Validator;
import edu.sanekas.wrapper.api.InputWrapper;
import edu.sanekas.wrapper.impl.wrappers.InputWrapperImpl;
import org.apache.log4j.Logger;
import org.jetbrains.annotations.Contract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

import static edu.sanekas.api.GeneralDefaults.*;

/**
 * The class implements basic validation of input data and encapsulates work with command line
 */
@Service
public final class InputProcesserImpl implements InputProcesser {

    private static final Logger LOGGER = Logger.getLogger(InputProcesserImpl.class);

    private final Validator optionsValidator;

    @Autowired
    public InputProcesserImpl(Validator optionsValidator) {
        this.optionsValidator = optionsValidator;
    }

    /**
     * @param input, user's input string
     * @return processed wrapper object with processed input - {@link InputWrapperImpl}
     */
    @Contract("null -> fail")
    public InputWrapper processInput(String input) {
        if (input != null && !input.isEmpty()) {

            //Validate empty command
            String[] command = input.trim().split("[\\s]+");
            if (command.length == 0) {
                throw new IllegalArgumentException(INVALID_COMMAND);
            }

            //Validate operation
            Operation operation;
            try {
                operation = Operation.valueOf(command[0].toUpperCase());
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException(INVALID_OPERATION, e);
            }

            //Validate options
            InputWrapper inputWrapper;
            try {
                inputWrapper = optionsValidator.
                        validateOptions(operation, Arrays.copyOfRange(command, 1, command.length));
            } catch (RuntimeException e) {
                throw new IllegalArgumentException(INVALID_OPTIONS, e);
            }

            LOGGER.info("Input command is processed: " + inputWrapper);

            return inputWrapper;
        } else {
            throw new IllegalArgumentException(INVALID_COMMAND);
        }
    }
}
