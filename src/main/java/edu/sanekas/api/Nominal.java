package edu.sanekas.api;


/**
 * The class represents different nominal values
 */
public enum Nominal {
    FIVE_THOUSAND(5000),
    ONE_THOUSAND(1000),
    FIVE_HUNDRED(500),
    ONE_HUNDRED(100),
    FIFTY(50),
    TWENTY_FIVE(25),
    TEN(10),
    FIVE(5),
    THREE(3),
    ONE(1),

    ANY(-1);

    private final int nominal;

    Nominal(int nominal) {
        this.nominal = nominal;
    }

    public static Nominal getValue(int nominal) {
        switch (nominal) {
            case 5000: return FIVE_THOUSAND;
            case 1000: return ONE_THOUSAND;
            case 500: return FIVE_HUNDRED;
            case 100: return ONE_HUNDRED;
            case 50: return FIFTY;
            case 25: return TWENTY_FIVE;
            case 10: return TEN;
            case 5: return FIVE;
            case 3: return THREE;
            case 1: return ONE;
        }
        throw new IllegalArgumentException("Invalid nominal!");
    }

    public int getNominal() {
        return nominal;
    }
}
