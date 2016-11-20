package edu.sanekas.wrapper.impl;

import edu.sanekas.api.Nominal;
import edu.sanekas.api.Operation;
import edu.sanekas.wrapper.api.InputWrapper;
import edu.sanekas.wrapper.api.OutputWrapper;
import edu.sanekas.wrapper.api.WrapperFactory;
import edu.sanekas.wrapper.impl.wrappers.InputWrapperImpl;
import edu.sanekas.wrapper.impl.wrappers.OutputWrapperImpl;
import org.springframework.stereotype.Service;


@Service
public class WrapperFactoryImpl implements WrapperFactory {

    @Override
    public InputWrapper createInputWrapper(Operation operation, Nominal nominal, Long cash) {
        return new InputWrapperImpl(operation, nominal, cash);
    }

    @Override
    public InputWrapper createInputWrapper(Operation operation) {
        return new InputWrapperImpl(operation);
    }

    @Override
    public <T> OutputWrapper<T> createOutputWrapper() {
        return new OutputWrapperImpl<>();
    }
}
