package edu.sanekas.wrapper.impl.wrappers;

import edu.sanekas.wrapper.api.OutputWrapper;

public class OutputWrapperImpl<T> implements OutputWrapper<T> {
    private T wrappedEntity;
    private String outputRepresentation;

    @Override
    public T getWrappedEntity() {
        return wrappedEntity;
    }

    @Override
    public void setWrappedEntity(T entity) {
        this.wrappedEntity = entity;
    }

    public void setOutputRepresentation(String outputRepresentation) {
        this.outputRepresentation = outputRepresentation;
    }

    @Override
    public String toString() {
        return outputRepresentation;
    }
}
