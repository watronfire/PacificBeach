package com.nate.pacificbeach;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.nate.pacificbeach.procedure.Procedure;
import com.nate.pacificbeach.procedure.ProcedureStep;
import com.nate.pacificbeach.procedure.Reagent;
import com.nate.pacificbeach.procedure.ReagentType;

import java.util.ArrayList;

public class PacificBeach extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	BitmapFont font;
	
	@Override
	public void create() {

		Procedure proc = new Procedure( "Gel Electrophoresis", "00:30:00" );
		proc.setDescription( "Size seperation of DNA using electrical current applied to an agar gel." );

		// Create some stupid steps.
		ArrayList<ProcedureStep> steps = new ArrayList<>();
		ProcedureStep step = new ProcedureStep( "Do something", "The first thing you do" );
		ProcedureStep substep1 = new ProcedureStep( "Do something: Part 1", "The first-first thing you do" );
		substep1.addReagent( new Reagent( "Water", "200", ReagentType.LIQUID ) );
		step.addSubstep( substep1 );

		steps.add( step );
		proc.setSteps( steps );
		// Report all that stupid stuff.
		proc.setReagents( step.getReagents() );
		proc.reportProcedure();

		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
		font = new BitmapFont();
	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);



		batch.begin();
		batch.draw(img, 0, 0);
		batch.setColor( 1,1,1,1 );
		font.draw( batch, "Step 1 ", 300, 300 );
		batch.end();
	}
	
	@Override
	public void dispose() {
		batch.dispose();
		img.dispose();
	}
}
