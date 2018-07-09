package com.nate.pacificbeach.procedure;

import java.sql.Time;
import java.util.ArrayList;

public class Procedure {
    private String name = "";
    private String description = "";
    private Time duration;
    private ArrayList<ProcedureStep> steps;
    private ArrayList<Reagent> reagents;

    public Procedure( String name, String duration ) {
        this.name = name;
        int hours = Integer.valueOf( duration.split( ":" )[0] );
        int minutes = Integer.valueOf( duration.split( ":" )[1] );
        int seconds = Integer.valueOf( duration.split( ":" )[2] );

        // This function is depracated but the output is fine, so I'm going to keep using it.
        //noinspection deprecation
        this.duration = new Time( hours, minutes, seconds );
    }

    public Procedure( String name, String duration, ArrayList<ProcedureStep> steps ) {
        this.name = name;
        this.steps = steps;
        int hours = Integer.valueOf( duration.split( ":" )[0] );
        int minutes = Integer.valueOf( duration.split( ":" )[1] );
        int seconds = Integer.valueOf( duration.split( ":" )[2] );

        // This function is depracated but the output is fine, so I'm going to keep using it.
        this.duration = new Time( hours, minutes, seconds );

    }

    public String getName() {
        return name;
    }
    public ArrayList<ProcedureStep> getSteps() {
        return steps;
    }

    public void reportProcedure() {
        System.out.println( "Name: " + name );
        System.out.println( "Duration: " + duration + " (hh:mm:ss)" );
        System.out.println( "Description: " + description );
        System.out.println();
        int count = 1;
        for( ProcedureStep step : steps )  {
            System.out.println( "Step " + count + ": " + step.report() );
            count += 1;
        }

        System.out.println();

        count = 1;
        for( Reagent reagent : reagents ) {
            System.out.println( "Reagent " + count + ": " + reagent.report() );
            count += 1;
        }
    }

    public void setDescription( String description ) {
        this.description = description;
    }
    public void setReagents( ArrayList<Reagent> reagents ) {
        this.reagents = reagents;
    }
    public void setSteps( ArrayList<ProcedureStep> steps ) {
        this.steps = steps;
    }
}
