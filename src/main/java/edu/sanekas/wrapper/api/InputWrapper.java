package edu.sanekas.wrapper.api;

import edu.sanekas.api.Nominal;
import edu.sanekas.api.Operation;

public interface InputWrapper {
    Operation getOperation();
    Nominal getNominal();
    Long getCash();
    void setOperation(Operation operation);
    void setNominal(Nominal nominal);
    void setCash(Long cash);
}
