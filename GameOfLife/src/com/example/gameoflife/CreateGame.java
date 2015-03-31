package com.example.gameoflife;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CreateGame extends ActionBarActivity {
	
	Button startGame, redPlayer1, bluePlayer1, greenPlayer1, yellowPlayer1, 
	redPlayer2, bluePlayer2, greenPlayer2, yellowPlayer2, redPlayer3, bluePlayer3, greenPlayer3, yellowPlayer3,
	redPlayer4, bluePlayer4, greenPlayer4, yellowPlayer4;
	
	String player1Name, player2Name, player3Name, player4Name;
	
	EditText player1, player2, player3, player4;
	
	Uri player1Image, player2Image, player3Image, player4Image;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.create_game);
		

		
		startGame = (Button) findViewById(R.id.startGameBoard);
		redPlayer1 = (Button) findViewById(R.id.redPlayer1);
		redPlayer2 = (Button) findViewById(R.id.redPlayer2);
		redPlayer3 = (Button) findViewById(R.id.redPlayer3);
		redPlayer4 = (Button) findViewById(R.id.redPlayer4);
		bluePlayer1 = (Button) findViewById(R.id.bluePlayer1);
		bluePlayer2 = (Button) findViewById(R.id.bluePlayer2);
		bluePlayer3 = (Button) findViewById(R.id.bluePlayer3);
		bluePlayer4 = (Button) findViewById(R.id.bluePlayer4);
		yellowPlayer1 = (Button) findViewById(R.id.yellowPlayer1);
		yellowPlayer2 = (Button) findViewById(R.id.yellowPlayer2);
		yellowPlayer3 = (Button) findViewById(R.id.yellowPlayer3);
		yellowPlayer4 = (Button) findViewById(R.id.yellowPlayer4);
		greenPlayer1 = (Button) findViewById(R.id.greenPlayer1);
		greenPlayer2 = (Button) findViewById(R.id.greenPlayer2);
		greenPlayer3 = (Button) findViewById(R.id.greenPlayer3);
		greenPlayer4 = (Button) findViewById(R.id.greenPlayer4);
		
		player1 = (EditText) findViewById(R.id.player1Name);
		player2 = (EditText) findViewById(R.id.player2Name);
		player3 = (EditText) findViewById(R.id.player3Name);
		player4 = (EditText) findViewById(R.id.player4Name);
		
		
		
		startGame.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent startGame = new Intent (CreateGame.this, GameBoard.class);
				//Add chosen values, player names to intent to pass to game board
				if(player1.getText().length() != 0){
					startGame.putExtra("player1Name", player1.getText());
					startGame.putExtra("player1Image", player1Image.toString());
					}
				if(player2.getText().length() != 0){
					startGame.putExtra("player2Name", player2.getText());
					startGame.putExtra("player2Image", player2Image.toString());
				}
				if(player3.getText().length() != 0){
					startGame.putExtra("player3Name", player3.getText());
					startGame.putExtra("player3Image", player3Image.toString());
				}
				if(player4.getText().length() != 0){
					startGame.putExtra("player4Name", player4.getText());
					startGame.putExtra("player4Image", player4Image.toString());
				}
				startActivity(startGame);
			}
		});
		
		
		
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.player4) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	@Override
	public void onBackPressed(){
	//disable back button	
	}
	
	public void onClick(View v){
		switch(v.getId()){
		
		case R.id.redPlayer1:
			player1Image = Uri.parse("android.resource://" + "GameOfLife" + "/drawable/" + R.drawable.red_token);
		case R.id.redPlayer2:
			player2Image = Uri.parse("android.resource://" + "GameOfLife" + "/drawable/" + R.drawable.red_token);
		case R.id.redPlayer3:
			player3Image = Uri.parse("android.resource://" + "GameOfLife" + "/drawable/" + R.drawable.red_token);
		case R.id.redPlayer4:
			player4Image = Uri.parse("android.resource://" + "GameOfLife" + "/drawable/" + R.drawable.red_token);
			
		case R.id.greenPlayer1:
			player1Image = Uri.parse("android.resource://" + "GameOfLife" + "/drawable/" + R.drawable.green_token);
		case R.id.greenPlayer2:
			player2Image = Uri.parse("android.resource://" + "GameOfLife" + "/drawable/" + R.drawable.green_token);
		case R.id.greenPlayer3:
			player3Image = Uri.parse("android.resource://" + "GameOfLife" + "/drawable/" + R.drawable.green_token);
		case R.id.greenPlayer4:
			player4Image = Uri.parse("android.resource://" + "GameOfLife" + "/drawable/" + R.drawable.green_token);
			
		case R.id.bluePlayer1:
			player1Image = Uri.parse("android.resource://" + "GameOfLife" + "/drawable/" + R.drawable.blue_token);
		case R.id.bluePlayer2:
			player2Image = Uri.parse("android.resource://" + "GameOfLife" + "/drawable/" + R.drawable.blue_token);
		case R.id.bluePlayer3:
			player3Image = Uri.parse("android.resource://" + "GameOfLife" + "/drawable/" + R.drawable.blue_token);
		case R.id.bluePlayer4:
			player4Image = Uri.parse("android.resource://" + "GameOfLife" + "/drawable/" + R.drawable.blue_token);
			
		case R.id.yellowPlayer1:
			player1Image = Uri.parse("android.resource://" + "GameOfLife" + "/drawable/" + R.drawable.yellow_token);
		case R.id.yellowPlayer2:
			player2Image = Uri.parse("android.resource://" + "GameOfLife" + "/drawable/" + R.drawable.yellow_token);
		case R.id.yellowPlayer3:
			player3Image = Uri.parse("android.resource://" + "GameOfLife" + "/drawable/" + R.drawable.yellow_token);
		case R.id.yellowPlayer4:
			player4Image = Uri.parse("android.resource://" + "GameOfLife" + "/drawable/" + R.drawable.yellow_token);
		}
	}
}
