package edu.sanekas.wrapper.impl.wrappers;

import edu.sanekas.wrapper.api.OutputWrapper;

public class OutputWrapperImpl<T> implements OutputWrapper<T> {
    private T wrappedEntity;

    @Override
    public T getWrappedEntity() {
        return wrappedEntity;
    }

    @Override
    public void setWrappedEntity(T entity) {
        this.wrappedEntity = entity;
    }
}
