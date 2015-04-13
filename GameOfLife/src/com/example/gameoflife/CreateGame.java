package com.example.gameoflife;

import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class CreateGame extends ActionBarActivity {
	
	Button startGame;
	
	ImageButton applePlayer1, bananaPlayer1, crabPlayer1, facePlayer1, footPlayer1, greenPlayer1, hatPlayer1, needlePlayer1, omegaPlayer1, smileyPlayer1, sunPlayer1, 
	applePlayer2, bananaPlayer2, crabPlayer2, facePlayer2, footPlayer2, greenPlayer2, hatPlayer2, needlePlayer2, omegaPlayer2, smileyPlayer2, sunPlayer2,
	applePlayer3, bananaPlayer3, crabPlayer3, facePlayer3, footPlayer3, greenPlayer3, hatPlayer3, needlePlayer3, omegaPlayer3, smileyPlayer3, sunPlayer3,
	applePlayer4, bananaPlayer4, crabPlayer4, facePlayer4, footPlayer4, greenPlayer4, hatPlayer4, needlePlayer4, omegaPlayer4, smileyPlayer4, sunPlayer4;
	
	String player1Name, player2Name, player3Name, player4Name;
	
	EditText player1, player2, player3, player4;
	
	TextView gameSetup, playText1, playText2, playText3, playText4;
	
	Uri player1Image, player2Image, player3Image, player4Image;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.create_game);
		
		gameSetup = (TextView) findViewById(R.id.gameSetup);
		playText1 = (TextView) findViewById(R.id.player1);
		playText2 = (TextView) findViewById(R.id.player2);
		playText3 = (TextView) findViewById(R.id.player3);
		playText4 = (TextView) findViewById(R.id.player4);
		
		
		//must set up buttons to show when clicked
		//through the icons
		startGame = (Button) findViewById(R.id.startGameBoard);
		applePlayer1 = (ImageButton) findViewById(R.id.applePlayer1);
		bananaPlayer1 = (ImageButton) findViewById(R.id.bananaPlayer1);
		crabPlayer1 = (ImageButton) findViewById(R.id.crabPlayer1);
		facePlayer1 = (ImageButton) findViewById(R.id.facePlayer1);
		footPlayer1 = (ImageButton) findViewById(R.id.footPlayer1);
		greenPlayer1 = (ImageButton) findViewById(R.id.greenPlayer1);
		hatPlayer1 = (ImageButton) findViewById(R.id.hatPlayer1);
		needlePlayer1 = (ImageButton) findViewById(R.id.needlePlayer1);
		omegaPlayer1 = (ImageButton) findViewById(R.id.omegaPlayer1);
		smileyPlayer1 = (ImageButton) findViewById(R.id.smileyPlayer1);
		sunPlayer1 = (ImageButton) findViewById(R.id.sunPlayer1);
		
		applePlayer2 = (ImageButton) findViewById(R.id.applePlayer2);
		bananaPlayer2 = (ImageButton) findViewById(R.id.bananaPlayer2);
		crabPlayer2 = (ImageButton) findViewById(R.id.crabPlayer2);
		facePlayer2 = (ImageButton) findViewById(R.id.facePlayer2);
		footPlayer2 = (ImageButton) findViewById(R.id.footPlayer2);
		greenPlayer2 = (ImageButton) findViewById(R.id.greenPlayer2);
		hatPlayer2 = (ImageButton) findViewById(R.id.hatPlayer2);
		needlePlayer2 = (ImageButton) findViewById(R.id.needlePlayer2);
		omegaPlayer2 = (ImageButton) findViewById(R.id.omegaPlayer2);
		smileyPlayer2 = (ImageButton) findViewById(R.id.smileyPlayer2);
		sunPlayer2 = (ImageButton) findViewById(R.id.sunPlayer2);
		
		applePlayer3 = (ImageButton) findViewById(R.id.applePlayer3);
		bananaPlayer3 = (ImageButton) findViewById(R.id.bananaPlayer3);
		crabPlayer3 = (ImageButton) findViewById(R.id.crabPlayer3);
		facePlayer3 = (ImageButton) findViewById(R.id.facePlayer3);
		footPlayer3 = (ImageButton) findViewById(R.id.footPlayer3);
		greenPlayer3 = (ImageButton) findViewById(R.id.greenPlayer3);
		hatPlayer3 = (ImageButton) findViewById(R.id.hatPlayer3);
		needlePlayer3 = (ImageButton) findViewById(R.id.needlePlayer3);
		omegaPlayer3 = (ImageButton) findViewById(R.id.omegaPlayer3);
		smileyPlayer3 = (ImageButton) findViewById(R.id.smileyPlayer3);
		sunPlayer3 = (ImageButton) findViewById(R.id.sunPlayer3);
		
		applePlayer4 = (ImageButton) findViewById(R.id.applePlayer4);
		bananaPlayer4 = (ImageButton) findViewById(R.id.bananaPlayer4);
		crabPlayer4 = (ImageButton) findViewById(R.id.crabPlayer4);
		facePlayer4 = (ImageButton) findViewById(R.id.facePlayer4);
		footPlayer4 = (ImageButton) findViewById(R.id.footPlayer4);
		greenPlayer4 = (ImageButton) findViewById(R.id.greenPlayer4);
		hatPlayer4 = (ImageButton) findViewById(R.id.hatPlayer4);
		needlePlayer4 = (ImageButton) findViewById(R.id.needlePlayer4);
		omegaPlayer4 = (ImageButton) findViewById(R.id.omegaPlayer4);
		smileyPlayer4 = (ImageButton) findViewById(R.id.smileyPlayer4);
		sunPlayer4 = (ImageButton) findViewById(R.id.sunPlayer4);
		
		player1 = (EditText) findViewById(R.id.player1Name);
		player2 = (EditText) findViewById(R.id.player2Name);
		player3 = (EditText) findViewById(R.id.player3Name);
		player4 = (EditText) findViewById(R.id.player4Name);
		
		Typeface font = Typeface.createFromAsset(getAssets(), "fonts/Captain_Howdy.ttf");
		gameSetup.setTypeface(font);
		playText1.setTypeface(font);
		playText2.setTypeface(font);
		playText3.setTypeface(font);
		playText4.setTypeface(font);
		
		
		
		startGame.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent startGame = new Intent (CreateGame.this, GameBoard.class);
				//Add chosen values, player names to intent to pass to game board
				int numPlayers = 0;
				if(player1.getText().length() != 0){
					startGame.putExtra("player1Name", player1.getText().toString());
					startGame.putExtra("player1Image", player1Image.toString());
					numPlayers++;
					}
				if(player2.getText().length() != 0){
					startGame.putExtra("player2Name", player2.getText().toString());
					startGame.putExtra("player2Image", player2Image.toString());
					numPlayers++;
				}
				if(player3.getText().length() != 0){
					startGame.putExtra("player3Name", player3.getText().toString());
					startGame.putExtra("player3Image", player3Image.toString());
					numPlayers++;
				}
				if(player4.getText().length() != 0){
					startGame.putExtra("player4Name", player4.getText().toString());
					startGame.putExtra("player4Image", player4Image.toString());
					numPlayers++;
				}
				if(numPlayers < 2){
					Toast.makeText(getApplicationContext(), "You must have at least two players!", Toast.LENGTH_LONG).show();
				}
				else{
				startActivity(startGame);
				}
			}
		});
		
		
		
		
		
	}


	@Override
	public void onBackPressed(){
	//disable back button	
	}
	
	public void onClick(View v){
		switch(v.getId()){
		
		case R.id.applePlayer1:
			player1Image = Uri.parse("android.resource://" + "GameOfLife" + "/drawable/" + R.drawable.apple_icon);
		case R.id.bananaPlayer1:
			player1Image = Uri.parse("android.resource://" + "GameOfLife" + "/drawable/" + R.drawable.banana_icon);
		case R.id.crabPlayer1:
			player1Image = Uri.parse("android.resource://" + "GameOfLife" + "/drawable/" + R.drawable.crab_icon);
		case R.id.facePlayer1:
			player1Image = Uri.parse("android.resource://" + "GameOfLife" + "/drawable/" + R.drawable.face_icon);
		case R.id.footPlayer1:
			player1Image = Uri.parse("android.resource://" + "GameOfLife" + "/drawable/" + R.drawable.foot_icon);
		case R.id.greenPlayer1:
			player1Image = Uri.parse("android.resource://" + "GameOfLife" + "/drawable/" + R.drawable.green_icon);
		case R.id.hatPlayer1:
			player1Image = Uri.parse("android.resource://" + "GameOfLife" + "/drawable/" + R.drawable.hat_icon);
		case R.id.needlePlayer1:
			player1Image = Uri.parse("android.resource://" + "GameOfLife" + "/drawable/" + R.drawable.needle_icon);
		case R.id.omegaPlayer1:
			player1Image = Uri.parse("android.resource://" + "GameOfLife" + "/drawable/" + R.drawable.omega_icon);
		case R.id.smileyPlayer1:
			player1Image = Uri.parse("android.resource://" + "GameOfLife" + "/drawable/" + R.drawable.smiley_icon);
		case R.id.sunPlayer1:
			player1Image = Uri.parse("android.resource://" + "GameOfLife" + "/drawable/" + R.drawable.sun_icon);
			
		case R.id.applePlayer2:
			player2Image = Uri.parse("android.resource://" + "GameOfLife" + "/drawable/" + R.drawable.apple_icon);
		case R.id.bananaPlayer2:
			player2Image = Uri.parse("android.resource://" + "GameOfLife" + "/drawable/" + R.drawable.banana_icon);
		case R.id.crabPlayer2:
			player2Image = Uri.parse("android.resource://" + "GameOfLife" + "/drawable/" + R.drawable.crab_icon);
		case R.id.facePlayer2:
			player2Image = Uri.parse("android.resource://" + "GameOfLife" + "/drawable/" + R.drawable.face_icon);
		case R.id.footPlayer2:
			player2Image = Uri.parse("android.resource://" + "GameOfLife" + "/drawable/" + R.drawable.foot_icon);
		case R.id.greenPlayer2:
			player2Image = Uri.parse("android.resource://" + "GameOfLife" + "/drawable/" + R.drawable.green_icon);
		case R.id.hatPlayer2:
			player2Image = Uri.parse("android.resource://" + "GameOfLife" + "/drawable/" + R.drawable.hat_icon);
		case R.id.needlePlayer2:
			player2Image = Uri.parse("android.resource://" + "GameOfLife" + "/drawable/" + R.drawable.needle_icon);
		case R.id.omegaPlayer2:
			player2Image = Uri.parse("android.resource://" + "GameOfLife" + "/drawable/" + R.drawable.omega_icon);
		case R.id.smileyPlayer2:
			player2Image = Uri.parse("android.resource://" + "GameOfLife" + "/drawable/" + R.drawable.smiley_icon);
		case R.id.sunPlayer2:
			player2Image = Uri.parse("android.resource://" + "GameOfLife" + "/drawable/" + R.drawable.sun_icon);

		case R.id.applePlayer3:
			player3Image = Uri.parse("android.resource://" + "GameOfLife" + "/drawable/" + R.drawable.apple_icon);
		case R.id.bananaPlayer3:
			player3Image = Uri.parse("android.resource://" + "GameOfLife" + "/drawable/" + R.drawable.banana_icon);
		case R.id.crabPlayer3:
			player3Image = Uri.parse("android.resource://" + "GameOfLife" + "/drawable/" + R.drawable.crab_icon);
		case R.id.facePlayer3:
			player3Image = Uri.parse("android.resource://" + "GameOfLife" + "/drawable/" + R.drawable.face_icon);
		case R.id.footPlayer3:
			player3Image = Uri.parse("android.resource://" + "GameOfLife" + "/drawable/" + R.drawable.foot_icon);
		case R.id.greenPlayer3:
			player3Image = Uri.parse("android.resource://" + "GameOfLife" + "/drawable/" + R.drawable.green_icon);
		case R.id.hatPlayer3:
			player3Image = Uri.parse("android.resource://" + "GameOfLife" + "/drawable/" + R.drawable.hat_icon);
		case R.id.needlePlayer3:
			player3Image = Uri.parse("android.resource://" + "GameOfLife" + "/drawable/" + R.drawable.needle_icon);
		case R.id.omegaPlayer3:
			player3Image = Uri.parse("android.resource://" + "GameOfLife" + "/drawable/" + R.drawable.omega_icon);
		case R.id.smileyPlayer3:
			player3Image = Uri.parse("android.resource://" + "GameOfLife" + "/drawable/" + R.drawable.smiley_icon);
		case R.id.sunPlayer3:
			player3Image = Uri.parse("android.resource://" + "GameOfLife" + "/drawable/" + R.drawable.sun_icon);
		
		case R.id.applePlayer4:
			player4Image = Uri.parse("android.resource://" + "GameOfLife" + "/drawable/" + R.drawable.apple_icon);
		case R.id.bananaPlayer4:
			player4Image = Uri.parse("android.resource://" + "GameOfLife" + "/drawable/" + R.drawable.banana_icon);
		case R.id.crabPlayer4:
			player4Image = Uri.parse("android.resource://" + "GameOfLife" + "/drawable/" + R.drawable.crab_icon);
		case R.id.facePlayer4:
			player4Image = Uri.parse("android.resource://" + "GameOfLife" + "/drawable/" + R.drawable.face_icon);
		case R.id.footPlayer4:
			player4Image = Uri.parse("android.resource://" + "GameOfLife" + "/drawable/" + R.drawable.foot_icon);
		case R.id.greenPlayer4:
			player4Image = Uri.parse("android.resource://" + "GameOfLife" + "/drawable/" + R.drawable.green_icon);
		case R.id.hatPlayer4:
			player4Image = Uri.parse("android.resource://" + "GameOfLife" + "/drawable/" + R.drawable.hat_icon);
		case R.id.needlePlayer4:
			player4Image = Uri.parse("android.resource://" + "GameOfLife" + "/drawable/" + R.drawable.needle_icon);
		case R.id.omegaPlayer4:
			player4Image = Uri.parse("android.resource://" + "GameOfLife" + "/drawable/" + R.drawable.omega_icon);
		case R.id.smileyPlayer4:
			player4Image = Uri.parse("android.resource://" + "GameOfLife" + "/drawable/" + R.drawable.smiley_icon);
		case R.id.sunPlayer4:
			player4Image = Uri.parse("android.resource://" + "GameOfLife" + "/drawable/" + R.drawable.sun_icon);
			
		
		}
	}
}
