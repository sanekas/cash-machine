package edu.sanekas.wrapper.impl.wrappers;

import edu.sanekas.api.Nominal;
import edu.sanekas.api.Operation;
import edu.sanekas.wrapper.api.InputWrapper;

public class InputWrapperImpl implements InputWrapper {
    private Operation operation;
    private Nominal nominal;
    private Long cash;

    public InputWrapperImpl(Operation operation) {
        this.operation = operation;
        this.nominal = null;
        this.cash = null;
    }

    public InputWrapperImpl(Operation operation, Nominal nominal, Long cash) {
        this.operation = operation;
        this.nominal = nominal;
        this.cash = cash;
    }

    @Override
    public Operation getOperation() {
        return operation;
    }

    @Override
    public Nominal getNominal() {
        return nominal;
    }

    @Override
    public Long getCash() {
        return cash;
    }

    @Override
    public void setOperation(Operation operation) {
        this.operation = operation;
    }

    @Override
    public void setNominal(Nominal nominal) {
        this.nominal = nominal;
    }

    @Override
    public void setCash(Long cash) {
        this.cash = cash;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof InputWrapperImpl)) return false;

        InputWrapperImpl that = (InputWrapperImpl) o;

        return getOperation() == that.getOperation() && getNominal() == that.getNominal() &&
                (getCash() != null ? getCash().equals(that.getCash()) : that.getCash() == null);

    }

    @Override
    public int hashCode() {
        int result = getOperation() != null ? getOperation().hashCode() : 0;
        result = 31 * result + (getNominal() != null ? getNominal().hashCode() : 0);
        result = 31 * result + (getCash() != null ? getCash().hashCode() : 0);
        return result;
    }
}
