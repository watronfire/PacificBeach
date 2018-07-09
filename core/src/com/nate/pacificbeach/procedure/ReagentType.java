package com.nate.pacificbeach.procedure;

public enum ReagentType {
    SOLID   ( "g" ),
    LIQUID  ( "ml" ),
    GAS     ( "l" ),
    OTHER   ( "?" );

    private final String unit;

    ReagentType( String unit ) {
        this.unit = unit;
    }

    public String getUnit() {
        return unit;
    }
}
