package edu.sanekas.console.impl;

import edu.sanekas.api.Nominal;
import edu.sanekas.api.Operation;
import edu.sanekas.console.api.Validator;
import edu.sanekas.wrapper.api.InputWrapper;
import edu.sanekas.wrapper.api.WrapperFactory;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class OptionsValidator implements Validator {
    private final WrapperFactory wrapperFactory;

    @Autowired
    public OptionsValidator(WrapperFactory wrapperFactory) {
        this.wrapperFactory = wrapperFactory;
    }

    @Nullable
    @Override
    public InputWrapper validateOptions(Operation operation, String[] options) {
        switch (operation) {
            case PUT: return validatePutOptions(options);
            case GET: return validateGetOptions(options);
            default: return wrapperFactory.createInputWrapper(operation);
        }
    }

    /**
     * @param options, raw input parameters, options[0] - nominal money,
     *                 options[1] - money for deposit specified by nominal
     * @return processed "PUT" command options
     */
    private InputWrapper validatePutOptions(String[] options) {
        Nominal nominal = Nominal.getValue(Integer.parseInt(options[0]));
        Integer numberOfRequestedMoney = Integer.parseInt(options[1]);

        if (numberOfRequestedMoney > 0) {
            return wrapperFactory.createInputWrapper(Operation.PUT, nominal, numberOfRequestedMoney);
        } else {
            throw new IllegalArgumentException("Options are invalid!");
        }
    }

    /**
     * @param options, raw input parameters, options[0] - requested money
     * @return processed "GET" command options
     */
    private InputWrapper validateGetOptions(String[] options) {
        Integer numberOfRequestedMoney = Integer.parseInt(options[0]);
        if (numberOfRequestedMoney > 0) {
            return wrapperFactory.createInputWrapper(Operation.GET, Nominal.ANY, numberOfRequestedMoney);
        } else {
            throw new IllegalArgumentException("Options are invalid!");
        }
    }

}
