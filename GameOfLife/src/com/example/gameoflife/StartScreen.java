package com.example.gameoflife;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class StartScreen extends ActionBarActivity {
	
	Button buttonGameCreate;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.start_screen);
		
		buttonGameCreate = (Button) findViewById(R.id.newGame);
		
		buttonGameCreate.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent startCreateGame = new Intent(StartScreen.this, CreateGame.class);
				startActivity(startCreateGame);
				
			}
		});
		
	}

	
	
	@Override
	public void onBackPressed(){
	//disable back button	
	}
}
