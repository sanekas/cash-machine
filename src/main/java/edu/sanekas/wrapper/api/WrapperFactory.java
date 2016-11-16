package edu.sanekas.wrapper.api;

import edu.sanekas.api.Nominal;
import edu.sanekas.api.Operation;

import java.util.Map;

public interface WrapperFactory<T> {
    InputWrapper createInputWrapper(Operation operation, Map<Nominal, Integer> commandOptions);
    OutputWrapper<T> createOutputWrapper();
}
