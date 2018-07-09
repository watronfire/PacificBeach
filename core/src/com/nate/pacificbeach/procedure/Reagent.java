package com.nate.pacificbeach.procedure;

import java.util.HashMap;

public class Reagent {

    // TODO implement reagent IDs so that they're easier to keep track of.
    private static int globalReagentID = 0;

    private String name;
    private float amount;
    private ReagentType type;
    private HashMap<String,String> characteristics;
    private int ReagentID;

    public Reagent( String name, String amount, ReagentType rt ) {
        this.name = name;
        this.amount = Float.valueOf( amount );
        type = rt;
        characteristics = new HashMap<>();
    }

    public void addCharacteristic( String characteristic, String value ) {
        characteristics.put( characteristic, value );
    }

    public String getCharacteristic( String characteristic ) {
        return characteristics.get( characteristic );
    }

    public String report() {
        return name + " - " + amount + " " + type.getUnit() + ". " + characteristics.size() + " Characteristics";
    }
}
