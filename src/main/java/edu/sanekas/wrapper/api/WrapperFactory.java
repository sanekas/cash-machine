package edu.sanekas.wrapper.api;

import edu.sanekas.api.Nominal;
import edu.sanekas.api.Operation;

public interface WrapperFactory {
    InputWrapper createInputWrapper(Operation operation, Nominal nominal, Integer cash);
    InputWrapper createInputWrapper(Operation operation);
    <T> OutputWrapper<T> createOutputWrapper();
}
