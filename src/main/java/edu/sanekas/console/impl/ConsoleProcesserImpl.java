package edu.sanekas.console.impl;

import edu.sanekas.api.Nominal;
import edu.sanekas.api.Operation;
import edu.sanekas.console.api.ConsoleProcesser;
import edu.sanekas.console.api.Validator;
import edu.sanekas.wrapper.api.InputWrapper;
import edu.sanekas.wrapper.api.OutputWrapper;
import edu.sanekas.wrapper.api.WrapperFactory;
import edu.sanekas.wrapper.impl.wrappers.InputWrapperImpl;
import org.apache.log4j.Logger;
import org.jetbrains.annotations.Contract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Map;

/**
 * The class implements basic validation of input data and encapsulates work with command line
 */
@Service
public final class ConsoleProcesserImpl implements ConsoleProcesser {

    private static final Logger LOGGER = Logger.getLogger(ConsoleProcesserImpl.class);

    private static final String PREFIX = ">";

    private final BufferedReader reader;

    private final Validator optionsValidator;
    private final WrapperFactory wrapperFactory;

    @Autowired
    public ConsoleProcesserImpl(Validator optionsValidator, WrapperFactory wrapperFactory) {
        this.optionsValidator = optionsValidator;
        this.wrapperFactory = wrapperFactory;
        this.reader = new BufferedReader(new InputStreamReader(System.in));
    }

    @Override
    public InputWrapper getProcessedInput() {
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
        System.out.println(outputWrapper);
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
                throw new IllegalArgumentException("Command is invalid!");
            }

            //Validate operation
            Operation operation;
            try {
                operation = Operation.valueOf(command[0].toUpperCase());
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("Operation is invalid!", e);
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
            Map<Nominal, Integer> validatedOptions;
            try {
                validatedOptions = optionsValidator.
                        validateOptions(operation, Arrays.copyOfRange(command, 1, command.length));
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("Invalid options!", e);
            }

            InputWrapper inputWrapper = wrapperFactory.createInputWrapper(operation, validatedOptions);
            LOGGER.info("Input command is processed: " + inputWrapper);

            return inputWrapper;
        } else {
            throw new IllegalArgumentException("Command is invalid!");
        }
    }


    private void printString(String s) {
        printPrefix();
        System.out.println(s);
    }

    private void printPrefix() {
        System.out.print(PREFIX);
    }

}
