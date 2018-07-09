package com.nate.pacificbeach.procedure;

import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;

// This class will need to be deprecated and combined with the Procedure class. In essence, I would like a procedure to
// be factorial. A procedure can be a step in it self, or it can be a step which is made from other steps.

public class ProcedureStep {
    private String name;
    private Time duration = new Time( 0, 0, 0 );
    private String description = "";
    private HashMap<String,String> characteristics;
    private ArrayList<ProcedureStep> substeps;
    private ArrayList<Reagent> reagents;

    // Constructors
    public ProcedureStep( String name ) {
        this.name = name;
        characteristics = new HashMap<>();
        substeps = new ArrayList<>();
        reagents = new ArrayList<>();
    }
    public ProcedureStep( String name, String description ) {
        this.name = name;
        this.description = description;
        characteristics = new HashMap<>();
        substeps = new ArrayList<>();
        reagents = new ArrayList<>();
    }

    // Updaters
    private void updateReagents() {
        // Clear reagents list
        reagents.clear();

        // Iterate through the substeps and collect reagents.
        for( ProcedureStep pc : substeps ) {
            addReagents( pc.getReagents() );
        }
    }

    // Getters
    public ArrayList<Reagent> getReagents() {
        return reagents;
    }
    // Setters and Adders
    public void setTime( String time ) {
        int hours = Integer.valueOf( time.split( ":" )[0] );
        int minutes = Integer.valueOf( time.split( ":" )[1] );
        int seconds = Integer.valueOf( time.split( ":" )[2] );

        // This function is depracated but the output is fine, so I'm going to keep using it.
        //noinspection deprecation
        this.duration = new Time( hours, minutes, seconds );
    }
    public void setDescription( String description ) {
        this.description = description;
    }
    public void addCharacteristic( String characteristic, String value ) {
        characteristics.put( characteristic, value );
    }
    public void addSubstep( ProcedureStep ps ) {
        substeps.add( ps );
        updateReagents();
    }
    public void addReagent( Reagent r ) {
        reagents.add( r );
    }
    public void addReagents( ArrayList<Reagent> r ) {
        reagents.addAll( r );
    }

    // Reporters
    public String report() {
        String result = name;
        result += " ; " + description;
        result += " (" + duration + ") ";
        result += characteristics.size() + " Characteristics, ";
        result += reagents.size() + " Reagents";
        for( ProcedureStep ps : substeps ) {
            result += "\n - Substep: " + ps.report();
        }

        return result;
    }
}
