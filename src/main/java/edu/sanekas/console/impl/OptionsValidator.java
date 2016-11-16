package edu.sanekas.console.impl;

import edu.sanekas.api.Nominal;
import edu.sanekas.api.Operation;
import edu.sanekas.console.api.Validator;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;


@Service
public class OptionsValidator implements Validator {
    @Override
    public Map<Nominal, Integer> validateOptions(Operation operation, String[] options) {
        switch (operation) {
            case PUT: return validatePutOptions(options);
            case GET: return validateGetOptions(options);
            default: return Collections.emptyMap();
        }
    }

    /**
     * @param options, raw input parameters, options[0] - nominal money,
     *                 options[1] - money for deposit specified by nominal
     * @return processed "PUT" command options
     */
    private Map<Nominal, Integer> validatePutOptions(String[] options) {
        Nominal nominal = Nominal.getValue(Integer.parseInt(options[0]));
        Integer numberOfRequestedMoney = Integer.parseInt(options[1]);

        if (numberOfRequestedMoney > 0) {
            Map<Nominal, Integer> processedOptions = new EnumMap<>(Nominal.class);
            processedOptions.put(nominal, numberOfRequestedMoney);
            return processedOptions;
        } else {
            throw new IllegalArgumentException("Options are invalid!");
        }
    }

    /**
     * @param options, raw input parameters, options[0] - requested money
     * @return processed "GET" command options
     */
    private Map<Nominal, Integer> validateGetOptions(String[] options) {
        Integer numberOfRequestedMoney = Integer.parseInt(options[0]);
        if (numberOfRequestedMoney > 0) {
            Map<Nominal, Integer> processedOptions = new EnumMap<>(Nominal.class);
            processedOptions.put(Nominal.ANY, numberOfRequestedMoney);
            return processedOptions;
        } else {
            throw new IllegalArgumentException("Options are invalid!");
        }
    }

}
