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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OutputWrapperImpl)) return false;

        OutputWrapperImpl<?> that = (OutputWrapperImpl<?>) o;

        return getWrappedEntity() != null ? getWrappedEntity().equals(that.getWrappedEntity()) :
                that.getWrappedEntity() == null &&
                        (outputRepresentation != null ? outputRepresentation.equals(that.outputRepresentation) :
                                that.outputRepresentation == null);

    }

    @Override
    public int hashCode() {
        int result = getWrappedEntity() != null ? getWrappedEntity().hashCode() : 0;
        result = 31 * result + (outputRepresentation != null ? outputRepresentation.hashCode() : 0);
        return result;
    }
}
