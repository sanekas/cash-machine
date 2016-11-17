package edu.sanekas.wrapper.api;

import edu.sanekas.api.Nominal;
import edu.sanekas.api.Operation;

import java.util.Map;

public interface WrapperFactory {
    InputWrapper createInputWrapper(Operation operation, Map<Nominal, Integer> commandOptions);
    <T> OutputWrapper<T> createOutputWrapper();
}
