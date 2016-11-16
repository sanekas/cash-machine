package edu.sanekas.console.api;

import edu.sanekas.api.Nominal;
import edu.sanekas.api.Operation;

import java.util.Map;

public interface Validator {
    Map<Nominal, Integer> validateOptions(Operation operation, String[] options);
}
