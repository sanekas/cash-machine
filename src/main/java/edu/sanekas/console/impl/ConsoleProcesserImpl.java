package edu.sanekas.console.impl;

import edu.sanekas.api.Operation;
import edu.sanekas.console.api.ConsoleProcesser;
import edu.sanekas.console.api.Validator;
import edu.sanekas.wrapper.api.InputWrapper;
import edu.sanekas.wrapper.api.OutputWrapper;
import edu.sanekas.wrapper.impl.wrappers.InputWrapperImpl;
import org.apache.log4j.Logger;
import org.jetbrains.annotations.Contract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

import static edu.sanekas.api.GeneralDefaults.*;

/**
 * The class implements basic validation of input data and encapsulates work with command line
 */
@Service
public final class ConsoleProcesserImpl implements ConsoleProcesser {

    private static final Logger LOGGER = Logger.getLogger(ConsoleProcesserImpl.class);

    private BufferedReader reader;

    private final Validator optionsValidator;

    @Autowired
    public ConsoleProcesserImpl(Validator optionsValidator) {
        this.optionsValidator = optionsValidator;
    }

    @Override
    public InputWrapper getProcessedInput() {
        if (reader == null) {
            reader = new BufferedReader(new InputStreamReader(System.in));
        }
        printPrefix();
        String input;
        try {
            input = reader.readLine();
            return processInput(input);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void printResult(OutputWrapper outputWrapper) {
        if (outputWrapper != null && !outputWrapper.toString().isEmpty()) {
            printString(outputWrapper.toString());
        }
    }

    /**
     * @param input, user's input string
     * @return processed wrapper object with processed input - {@link InputWrapperImpl}
     */
    @Contract("null -> fail")
    private InputWrapper processInput(String input) {
        if (input != null && !input.isEmpty()) {

            //Validate empty command
            String[] command = input.trim().split("[\\s]+");
            if (command.length == 0) {
                printString(INVALID_COMMAND);
                throw new IllegalArgumentException(INVALID_COMMAND);
            }

            //Validate operation
            Operation operation;
            try {
                operation = Operation.valueOf(command[0].toUpperCase());
            } catch (IllegalArgumentException e) {
                printString(INVALID_OPERATION);
                throw new IllegalArgumentException(INVALID_OPERATION, e);
            }

            //Close reader, if user wants to quit
            if (operation == Operation.QUIT) {
                try {
                    reader.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            //Validate options
            InputWrapper inputWrapper;
            try {
                inputWrapper = optionsValidator.
                        validateOptions(operation, Arrays.copyOfRange(command, 1, command.length));
            } catch (RuntimeException e) {
                printString(INVALID_OPTIONS);
                throw new IllegalArgumentException(INVALID_OPTIONS, e);
            }
            LOGGER.info("Input command is processed: " + inputWrapper);

            return inputWrapper;
        } else {
            printString(INVALID_COMMAND);
            throw new IllegalArgumentException(INVALID_COMMAND);
        }
    }


    private void printString(String s) {
        System.out.println(s);
    }

    private void printPrefix() {
        System.out.print(PREFIX);
    }

}
