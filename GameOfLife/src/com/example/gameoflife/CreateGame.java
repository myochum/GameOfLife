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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class CreateGame extends ActionBarActivity {
	
	Button startGame;
	
	ImageButton p1icon1, p1icon2,p1icon3,p1icon4,p1icon5,p1icon6,p1icon7,p1icon8,
	p2icon1, p2icon2,p2icon3,p2icon4,p2icon5,p2icon6,p2icon7,p2icon8,
	p3icon1, p3icon2,p3icon3,p3icon4,p3icon5,p3icon6,p3icon7,p3icon8,
	p4icon1, p4icon2,p4icon3,p4icon4,p4icon5,p4icon6,p4icon7,p4icon8;
	
	ImageView p1Selected, p2Selected, p3Selected, p4Selected;
	
	String player1Name, player2Name, player3Name, player4Name;
	
	EditText player1, player2, player3, player4;
	
	TextView gameSetup, playText1, playText2, playText3, playText4;
	


	int player1Image, player2Image, player3Image, player4Image;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.game_setup);
		
		//Set up fonts
		Typeface font = Typeface.createFromAsset(getAssets(),
				"fonts/androidnation.ttf");
		getSupportActionBar().setTitle("Create Your Players");
		playText1 = (TextView) findViewById(R.id.player1);
		playText2 = (TextView) findViewById(R.id.player2);
		playText3 = (TextView) findViewById(R.id.player3);
		playText4 = (TextView) findViewById(R.id.player4);
		playText1.setTypeface(font);
		playText2.setTypeface(font);
		playText3.setTypeface(font);
		playText4.setTypeface(font);
		

		startGame = (Button) findViewById(R.id.startGameBoard);
		
		//Initialize buttons
		p1icon1 = (ImageButton) findViewById(R.id.p1icon1);
		p1icon2 = (ImageButton) findViewById(R.id.p1icon2);
		p1icon3 = (ImageButton) findViewById(R.id.p1icon3);
		p1icon4 = (ImageButton) findViewById(R.id.p1icon4);
		p1icon5 = (ImageButton) findViewById(R.id.p1icon5);
		p1icon6 = (ImageButton) findViewById(R.id.p1icon6);
		p1icon7 = (ImageButton) findViewById(R.id.p1icon7);
		p1icon8 = (ImageButton) findViewById(R.id.p1icon8);
		
		p2icon1 = (ImageButton) findViewById(R.id.p2icon1);
		p2icon2 = (ImageButton) findViewById(R.id.p2icon2);
		p2icon3 = (ImageButton) findViewById(R.id.p2icon3);
		p2icon4 = (ImageButton) findViewById(R.id.p2icon4);
		p2icon5 = (ImageButton) findViewById(R.id.p2icon5);
		p2icon6 = (ImageButton) findViewById(R.id.p2icon6);
		p2icon7 = (ImageButton) findViewById(R.id.p2icon7);
		p2icon8 = (ImageButton) findViewById(R.id.p2icon8);
		
		p3icon1 = (ImageButton) findViewById(R.id.p3icon1);
		p3icon2 = (ImageButton) findViewById(R.id.p3icon2);
		p3icon3 = (ImageButton) findViewById(R.id.p3icon3);
		p3icon4 = (ImageButton) findViewById(R.id.p3icon4);
		p3icon5 = (ImageButton) findViewById(R.id.p3icon5);
		p3icon6 = (ImageButton) findViewById(R.id.p3icon6);
		p3icon7 = (ImageButton) findViewById(R.id.p3icon7);
		p3icon8 = (ImageButton) findViewById(R.id.p3icon8);
		
		p4icon1 = (ImageButton) findViewById(R.id.p4icon1);
		p4icon2 = (ImageButton) findViewById(R.id.p4icon2);
		p4icon3 = (ImageButton) findViewById(R.id.p4icon3);
		p4icon4 = (ImageButton) findViewById(R.id.p4icon4);
		p4icon5 = (ImageButton) findViewById(R.id.p4icon5);
		p4icon6 = (ImageButton) findViewById(R.id.p4icon6);
		p4icon7 = (ImageButton) findViewById(R.id.p4icon7);
		p4icon8 = (ImageButton) findViewById(R.id.p4icon8);
		
		
		//Initialize player name texts
		player1 = (EditText) findViewById(R.id.player1Name);
		player2 = (EditText) findViewById(R.id.player2Name);
		player3 = (EditText) findViewById(R.id.player3Name);
		player4 = (EditText) findViewById(R.id.player4Name);
		
		//Initialize button displays
		p1Selected = (ImageView) findViewById(R.id.p1iconSelect);
		p2Selected = (ImageView) findViewById(R.id.p2iconSelect);
		p3Selected = (ImageView) findViewById(R.id.p3iconSelect);
		p4Selected = (ImageView) findViewById(R.id.p4iconSelect);

		
		startGame.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent startGame = new Intent (CreateGame.this, GameBoard.class);
				//Add chosen values, player names to intent to pass to game board
				int numPlayers = 0;
				if(player1.getText().length() != 0){
					startGame.putExtra("player1Name", player1.getText().toString());
					startGame.putExtra("player1Image", player1Image);
					numPlayers++;
					}
				if(player2.getText().length() != 0){
					startGame.putExtra("player2Name", player2.getText().toString());
					startGame.putExtra("player2Image", player2Image);
					numPlayers++;
				}
				if(player3.getText().length() != 0){
					startGame.putExtra("player3Name", player3.getText().toString());
					startGame.putExtra("player3Image", player3Image);
					numPlayers++;
				}
				if(player4.getText().length() != 0){
					startGame.putExtra("player4Name", player4.getText().toString());
					startGame.putExtra("player4Image", player4Image);
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
		
		case R.id.p1icon1:
			player1Image =R.drawable.icon1;
			p1Selected.setImageResource(R.drawable.icon1);
			break;
		case R.id.p1icon2:
			player1Image =R.drawable.icon2;
			p1Selected.setImageResource(R.drawable.icon2);
			break;
		case R.id.p1icon3:
			player1Image = R.drawable.icon3;
			p1Selected.setImageResource(R.drawable.icon3);
			break;
		case R.id.p1icon4:
			player1Image =R.drawable.icon4;
			p1Selected.setImageResource(R.drawable.icon4);
			break;
		case R.id.p1icon5:
			player1Image =R.drawable.icon5;
			p1Selected.setImageResource(R.drawable.icon5);
			break;
		case R.id.p1icon6:
			player1Image = R.drawable.icon6;
			p1Selected.setImageResource(R.drawable.icon6);
			break;
		case R.id.p1icon7:
			player1Image = R.drawable.icon7;
			p1Selected.setImageResource(R.drawable.icon7);
			break;
		case R.id.p1icon8:
			player1Image = R.drawable.icon8;
			p1Selected.setImageResource(R.drawable.icon8);
			break;
			
		case R.id.p2icon1:
			player2Image =R.drawable.icon1;
			p2Selected.setImageResource(R.drawable.icon1);
			break;
		case R.id.p2icon2:
			player2Image =R.drawable.icon2;
			p2Selected.setImageResource(R.drawable.icon2);
			break;
		case R.id.p2icon3:
			player2Image = R.drawable.icon3;
			p2Selected.setImageResource(R.drawable.icon3);
			break;
		case R.id.p2icon4:
			player2Image = R.drawable.icon4;
			p2Selected.setImageResource(R.drawable.icon4);
			break;
		case R.id.p2icon5:
			player2Image =R.drawable.icon5;
			p2Selected.setImageResource(R.drawable.icon5);
			break;
		case R.id.p2icon6:
			player2Image =R.drawable.icon6;
			p2Selected.setImageResource(R.drawable.icon6);
			break;
		case R.id.p2icon7:
			player2Image = R.drawable.icon7;
			p2Selected.setImageResource(R.drawable.icon7);
			break;
		case R.id.p2icon8:
			player2Image = R.drawable.icon8;
			p2Selected.setImageResource(R.drawable.icon8);
			break;
			
		case R.id.p3icon1:
			player3Image = R.drawable.icon1;
			p3Selected.setImageResource(R.drawable.icon1);
			break;
		case R.id.p3icon2:
			player3Image = R.drawable.icon2;
			p3Selected.setImageResource(R.drawable.icon2);
			break;
		case R.id.p3icon3:
			player3Image = R.drawable.icon3;
			p3Selected.setImageResource(R.drawable.icon3);
			break;
		case R.id.p3icon4:
			player3Image = R.drawable.icon4;
			p3Selected.setImageResource(R.drawable.icon4);
			break;
		case R.id.p3icon5:
			player3Image = R.drawable.icon5;
			p3Selected.setImageResource(R.drawable.icon5);
			break;
		case R.id.p3icon6:
			player3Image = R.drawable.icon6;
			p3Selected.setImageResource(R.drawable.icon6);
			break;
		case R.id.p3icon7:
			player3Image =R.drawable.icon7;
			p3Selected.setImageResource(R.drawable.icon7);
			break;
		case R.id.p3icon8:
			player3Image = R.drawable.icon8;
			p3Selected.setImageResource(R.drawable.icon8);
			break;
			
		case R.id.p4icon1:
			player4Image = R.drawable.icon1;
			p4Selected.setImageResource(R.drawable.icon1);
			break;
		case R.id.p4icon2:
			player4Image = R.drawable.icon2;
			p4Selected.setImageResource(R.drawable.icon2);
			break;
		case R.id.p4icon3:
			player4Image = R.drawable.icon3;
			p4Selected.setImageResource(R.drawable.icon3);
			break;
		case R.id.p4icon4:
			player4Image = R.drawable.icon4;
			p4Selected.setImageResource(R.drawable.icon4);
			break;
		case R.id.p4icon5:
			player4Image = R.drawable.icon5;
			p4Selected.setImageResource(R.drawable.icon5);
			break;
		case R.id.p4icon6:
			player4Image = R.drawable.icon6;
			p4Selected.setImageResource(R.drawable.icon6);
			break;
		case R.id.p4icon7:
			player4Image =  R.drawable.icon7;
			p4Selected.setImageResource(R.drawable.icon7);
			break;
		case R.id.p4icon8:
			player4Image = R.drawable.icon8;
			p4Selected.setImageResource(R.drawable.icon8);
			break;
		
		
			

			
		
		}
	}
}
