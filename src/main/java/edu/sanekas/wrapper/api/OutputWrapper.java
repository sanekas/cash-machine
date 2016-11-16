package edu.sanekas.wrapper.api;

public interface OutputWrapper<T> {
    T getWrappedEntity();
    void setWrappedEntity(T entity);
}
