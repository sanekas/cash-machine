package edu.sanekas.wrapper.api;

import edu.sanekas.api.Nominal;
import edu.sanekas.api.Operation;

import java.util.Map;

public interface InputWrapper {
    Operation getOperation();
    Map<Nominal, Integer> getCommandOptions();
}
