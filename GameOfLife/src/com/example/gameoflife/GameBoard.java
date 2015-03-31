package com.example.gameoflife;

import java.util.ArrayList;
import java.util.Random;

import com.example.pong.Pong;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class GameBoard extends ActionBarActivity {
	
	String[] topLeftMap, topRightMap, bottomLeftMap, bottomRightMap;
	Uri player1Image, player2Image, player3Image, player4Image;
	String player1Name, player2Name, player3Name, player4Name;
	Sprite player1, player2, player3, player4;
	int numPlayers = 0;
	int playerTurn;
	Sprite currentPlayer;
	static Sprite winner;
	
	static ArrayList<Sprite> players;
	
	private static int winTotal;
	
	Button pong, roll;
	
	Board board;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.game_board);
		
		pong = (Button) findViewById(R.id.pongPlayTest);
		roll = (Button) findViewById(R.id.roll);
		
		pong.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent startPong = new Intent (GameBoard.this, Pong.class);
				startPong.putExtra("playerNum", playerTurn);
				startActivity(startPong);
			}
		});
		
		roll.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				roll();
				playerTurn = (playerTurn + 1 ) % numPlayers;
			}
		});
		
		
		
		//put game board image in view here
		
		//check for passed intent if yes - do not create board again, add new scores from minigames, and return to next players turn 
		Intent intentReturn = getIntent();
		if(!(intentReturn.getBooleanExtra("miniGameResult", false))){
			//set up game here
			
			//Set up players
			players = new ArrayList<Sprite>();
			
			player1Name = intentReturn.getStringExtra("player1Name");
			player2Name = intentReturn.getStringExtra("player2Name");
			player3Name = intentReturn.getStringExtra("player3Name");
			player4Name = intentReturn.getStringExtra("player4Name");
			
			if(player1Name !=null){
			player1Image = Uri.parse(intentReturn.getStringExtra("player1Image"));
			player1 = new Sprite(intentReturn.getStringExtra("player1Image"), player1Name);
			players.add(0, player1);
			numPlayers++;
			}
			else{
				Toast.makeText(this,"You must have at least one player.", Toast.LENGTH_LONG).show();
				Intent returnToCreator = new Intent(GameBoard.this, CreateGame.class);
				startActivity(returnToCreator);
			}
			
			if(player2Name !=null){
			player2Image = Uri.parse(intentReturn.getStringExtra("player2Image"));
			player2 = new Sprite(intentReturn.getStringExtra("player2Image"), player2Name);
			players.add(1, player2);
			numPlayers++;
			}
			
			if(player3Name !=null){
			player3Image = Uri.parse(intentReturn.getStringExtra("player3Image"));
			player3 = new Sprite(intentReturn.getStringExtra("player3Image"), player3Name);
			players.add(2, player3);
			numPlayers++;
			}
			
			if(player4Name !=null){
			player4Image = Uri.parse(intentReturn.getStringExtra("player4Image"));
			player4 = new Sprite(intentReturn.getStringExtra("player4Image"), player4Name);
			players.add(3, player4);
			numPlayers++;
			}
			
			//Sets up board
			board = new Board();
			
			//Get winning total from create game??
			
			//Chooses random first player
			Random r = new Random();
			playerTurn = r.nextInt(numPlayers);
			currentPlayer = players.get(playerTurn);
			
		}
		
		//Player is returning from mini game --- maybe put in on resume
		else{
			//get saved state
			
			
			int returnPlayer = intentReturn.getIntExtra("playerNum", 0);
			int prize = intentReturn.getIntExtra("prize", 0 );
			players.get(returnPlayer).addMoney(prize);
			playerTurn = returnPlayer;
			currentPlayer = players.get(returnPlayer);			
		}
		
		//game loop
		while(!isWinner()){
			
			
		}
		
		//winner found
		
		
		
	}

	protected void roll() {
		//begin dice animation here --- as a thread that must complete first 
		Random r = new Random();
		int diceRoll = r.nextInt(6) + 1;
		//begin dice animation here --- as a thread that must complete first, gets number as input for thread
		String space = board.getMovement(currentPlayer.getIsland(), currentPlayer.getPosition(), diceRoll);
		
		//insert animation movement here -- probably as a thread
		
		
		//set for correct board colors, right now assumes 
		// red - lose money       green - gain money     yellow - teleport    blue - minigame
		if(space.equals("red")){
			loseMoney();
		}
		else if(space.equals("green")){
			gainMoney();
		}
		else if(space.equals("yellow")){
			teleportIsland();
		}
		else{
			startMiniGame();
		}
	}

	//pick a random minigame to begin
	private void startMiniGame() {
		// TODO Auto-generated method stub
		
	}

	//move to another teleport space 
	private void teleportIsland() {
		String[] newSpot = board.teleport(currentPlayer.getIsland());
		players.get(playerTurn).setIsland(newSpot[0]);
		players.get(playerTurn).setPosition(Integer.parseInt(newSpot[1]));
		//change position of player sprite on board with animation
		Toast.makeText(getApplicationContext(), "You have teleported to a new location!", Toast.LENGTH_LONG).show();
	}
	
	
	private void gainMoney() {
		Random r = new Random();
		int win = r.nextInt(1000) + 1;
		players.get(playerTurn).addMoney(win);
		Toast.makeText(getApplicationContext(), "You win $" + win, Toast.LENGTH_LONG).show();
	}

	private void loseMoney() {
		Random r = new Random();
		int loss = r.nextInt(1000) + 1;
		players.get(playerTurn).loseMoney(loss);
		Toast.makeText(getApplicationContext(), "You lost $" + loss, Toast.LENGTH_LONG).show();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	//if activity is destroyed
	@Override
	public void onSaveInstanceState(Bundle savedInstanceState) {
	//add sprite object array and board object
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
	
	//Returning from mini game
	@Override
	protected void onRestart() {
   
	    
	}
	
	private static boolean isWinner(){
		for(Sprite i : players){
			if(i.getMoney() >= winTotal){
				//set winner
				return true;
			}
		}
		return false;
	}
	
	
	@Override
	public void onBackPressed(){
	//disable back button	
	}
	
}
