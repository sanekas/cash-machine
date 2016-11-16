package edu.sanekas.wrapper.impl;

import edu.sanekas.api.Nominal;
import edu.sanekas.api.Operation;
import edu.sanekas.wrapper.api.InputWrapper;
import edu.sanekas.wrapper.api.OutputWrapper;
import edu.sanekas.wrapper.api.WrapperFactory;
import edu.sanekas.wrapper.impl.wrappers.InputWrapperImpl;
import edu.sanekas.wrapper.impl.wrappers.OutputWrapperImpl;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service
public class WrapperFactoryImpl<T> implements WrapperFactory<T> {

    @Override
    public InputWrapper createInputWrapper(Operation operation, Map<Nominal, Integer> commandOptions) {
        return new InputWrapperImpl(operation, commandOptions);
    }

    @Override
    public OutputWrapper<T> createOutputWrapper() {
        return new OutputWrapperImpl<>();
    }
}
