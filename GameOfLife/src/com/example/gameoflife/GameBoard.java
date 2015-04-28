package com.example.gameoflife;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;



import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TableLayout.LayoutParams;
import android.widget.Toast;

public class GameBoard extends ActionBarActivity {

	String[] topLeftMap, topRightMap, bottomLeftMap, bottomRightMap;
	int player1Image, player2Image, player3Image, player4Image;
	String player1Name, player2Name, player3Name, player4Name;
	Sprite player1, player2, player3, player4;
	int numPlayers = 0;
	int playerTurn;
	Sprite currentPlayer;
	static Sprite winner;

	static ArrayList<Sprite> players;

	private static int winTotal = 1000;

	Button pong, roll;

	Board board;
	RelativeLayout boardLayout;

	ImageView player1View, player2View, player3View, player4View;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.game_board);

		roll = (Button) findViewById(R.id.roll);

		// Create views for icons
		boardLayout = (RelativeLayout) findViewById(R.id.boardLa);

		player1View = new ImageView(this);

		player2View = new ImageView(this);

		player3View = new ImageView(this);

		player4View = new ImageView(this);

		roll.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				if (isWinner()) {
					Toast.makeText(getApplicationContext(),
							"YOU HAVE WON, " + winner.name, Toast.LENGTH_LONG)
							.show();
				}
				roll();
				playerTurn = (playerTurn + 1) % numPlayers;
				currentPlayer = players.get(playerTurn);
				updateScores();

			}
		});

		// check for passed intent if yes - do not create board again, add new
		// scores from minigames, and return to next players turn
		Intent intentReturn = getIntent();
		if (!(intentReturn.getBooleanExtra("miniGameResult", false))) {
			// set up game here

			// Sets up board
			board = new Board();

			// Set up players
			players = new ArrayList<Sprite>();

			player1Name = intentReturn.getStringExtra("player1Name");
			player2Name = intentReturn.getStringExtra("player2Name");
			player3Name = intentReturn.getStringExtra("player3Name");
			player4Name = intentReturn.getStringExtra("player4Name");

			if (player1Name != null) {
				player1Image = intentReturn.getIntExtra("player1Image", 0);
				player1 = new Sprite(
						intentReturn.getStringExtra("player1Image"),
						player1Name);
				players.add(0, player1);
				player1View.setImageDrawable(getResources().getDrawable(
						player1Image));
				drawAvatar(player1View, player1);
				numPlayers++;
			} else {
				Toast.makeText(this, "You must have at least one player.",
						Toast.LENGTH_LONG).show();
				Intent returnToCreator = new Intent(GameBoard.this,
						CreateGame.class);
				startActivity(returnToCreator);
			}

			if (player2Name != null) {
				player2Image = intentReturn.getIntExtra("player2Image", 0);
				player2 = new Sprite(
						intentReturn.getStringExtra("player2Image"),
						player2Name);
				players.add(numPlayers, player2);
				player2View.setImageDrawable(getResources().getDrawable(
						player2Image));
				drawAvatar(player2View, player2);
				numPlayers++;
			}

			if (player3Name != null) {
				player3 = new Sprite(
						intentReturn.getStringExtra("player3Image"),
						player3Name);
				players.add(numPlayers, player3);
				player3View.setImageDrawable(getResources().getDrawable(
						player3Image));
				drawAvatar(player3View, player3);
				numPlayers++;
			}

			if (player4Name != null) {
				player4 = new Sprite(
						intentReturn.getStringExtra("player4Image"),
						player4Name);
				players.add(numPlayers, player4);
				player4View.setImageDrawable(getResources().getDrawable(
						player4Image));
				drawAvatar(player4View, player4);
				numPlayers++;
			}

			// Chooses random first player
			Random r = new Random();
			playerTurn = r.nextInt(numPlayers);
			currentPlayer = players.get(playerTurn);

			// Sets up score board
			updateScores();

		}

		// Player is returning from mini game --- maybe put in on resume
		else {
			// get saved state

			int returnPlayer = intentReturn.getIntExtra("playerNum", 0);
			int prize = intentReturn.getIntExtra("prize", 0);
			players.get(returnPlayer).addMoney(prize);
			playerTurn = returnPlayer;
			currentPlayer = players.get(returnPlayer);
		}

	}

	private void drawAvatar(ImageView playerView, Sprite player) {
		RelativeLayout.LayoutParams paramsPl = new RelativeLayout.LayoutParams(
				ViewGroup.LayoutParams.WRAP_CONTENT,
				ViewGroup.LayoutParams.WRAP_CONTENT);
		int[] margin = new int[4];
		if (player.getIsland().equals("topLeft")) {
			margin = board.topLeftSt;
			player.setDirection("right");
		} else if (player.getIsland().equals("topRight")) {
			margin = board.topRightSt;
			player.setDirection("left");
		} else if (player.getIsland().equals("bottomRight")) {
			margin = board.bottomRightSt;
			player.setDirection("left");
		} else if (player.getIsland().equals("bottomLeft")) {
			margin = board.bottomLeftSt;
			player.setDirection("up");
		}

		paramsPl.setMargins(margin[0], margin[1], margin[2], margin[3]);
		playerView.setLayoutParams(paramsPl);
		boardLayout.addView(playerView);
		player.setMargins(margin);

	}

	protected void roll() {
		// begin dice animation here --- as a thread that must complete first
		Random r = new Random();
		int diceRoll = r.nextInt(6) + 1;
		// begin dice animation here --- as a thread that must complete first,
		// gets number as input for thread
		String space = board.getMovement(currentPlayer.getIsland(),
				currentPlayer.getPosition(), diceRoll);

		// insert animation movement here -- probably as a thread
		Toast.makeText(getApplicationContext(), String.valueOf(diceRoll),
				Toast.LENGTH_SHORT).show();

		move(currentPlayer, diceRoll);

		// set for correct board colors, right now assumes
		// red - lose money green - gain money yellow - teleport blue - nothing
		if (space.equals("red")) {
			loseMoney();
		} else if (space.equals("green")) {
			gainMoney();
		} else if (space.equals("yellow")) {
			teleportIsland();
		} else if (space.equals("purple")) {
			Toast.makeText(getApplicationContext(),
					"Get ready to play a game!", Toast.LENGTH_SHORT).show();
			startMiniGame();
		} else if (space.equals("mini")) {
			Toast.makeText(getApplicationContext(),
					"Get ready to play a game!", Toast.LENGTH_SHORT).show();
		}

	}

	// pick a random minigame to begin
	private void startMiniGame() {
		// TODO Auto-generated method stub

	}

	// move to another teleport space
	private void teleportIsland() {
		String[] newSpot = board.teleport(currentPlayer.getIsland());
		players.get(playerTurn).setIsland(newSpot[0]);
		players.get(playerTurn).setPosition(Integer.parseInt(newSpot[1]));
		// change position of player sprite on board with animation
		Toast.makeText(getApplicationContext(),
				"You have teleported to a new location!", Toast.LENGTH_LONG)
				.show();
	}

	private void gainMoney() {
		Random r = new Random();
		int win = r.nextInt(1000) + 1;
		players.get(playerTurn).addMoney(win);
		String Context = getWinMoneyContext();
		String Location = getLocation();
		Toast.makeText(getApplicationContext(),
				Context + "of $" + win + Location, Toast.LENGTH_LONG).show();
	}

	private void loseMoney() {
		Random r = new Random();
		int loss = r.nextInt(1000) + 1;
		players.get(playerTurn).loseMoney(loss);
		String Context = getLoseMoneyContext();
		String Location = getLocation();
		Toast.makeText(getApplicationContext(),
				Context + "of $" + loss + Location, Toast.LENGTH_LONG).show();
	}

	private String getWinMoneyContext() {
		String WinContext;
		Random r = new Random();
		int Win = r.nextInt(10) + 1;
		switch (Win) {
		case 1:
			WinContext = "Recieved cash prize ";
			break;
		case 2:
			WinContext = "Investment profts ";
			break;
		case 3:
			WinContext = "Grand prize ";
			break;
		case 4:
			WinContext = "Paycheck ";
			break;
		case 5:
			WinContext = "Won a cash bet ";
			break;
		case 6:
			WinContext = "Unearthed treasure ";
			break;
		case 7:
			WinContext = "Tutoring Profits ";
			break;
		case 8:
			WinContext = "Recieved odd job payment ";
			break;
		case 9:
			WinContext = "Unsanctioned surgery profits ";
			break;
		case 10:
			WinContext = "Duck walking revenues ";
			break;
		default:
			WinContext = "Cookie sales ";
			break;
		}
		return (WinContext);
	}

	private String getLoseMoneyContext() {
		String LoseContext;
		Random r = new Random();
		int lose = r.nextInt(10) + 1;
		switch (lose) {
		case 1:
			LoseContext = "Gambling loss";
			break;
		case 2:
			LoseContext = "spent sum";
			break;
		case 3:
			LoseContext = "disposed";
			break;
		case 4:
			LoseContext = "Investment loss";
			break;
		case 5:
			LoseContext = "mind crime";
			break;
		case 6:
			LoseContext = "buried a treasure";
			break;
		case 7:
			LoseContext = "made it a rain a sum";
			break;
		case 8:
			LoseContext = "charitable donation";
			break;
		case 9:
			LoseContext = "SHOES! loss";
			break;
		default:
			LoseContext = "hole in pocket. loss";
			break;
		}
		return (LoseContext);
	}

	private String getLocation() {
		String adj, loc;
		Random r = new Random();
		int a = r.nextInt(12) + 1;
		int b = r.nextInt(10) + 1;
		switch (a) {
		case 1:
			adj = "rather stinky";
			break;
		case 2:
			adj = "derelict";
			break;
		case 3:
			adj = "dutch";
			break;
		case 4:
			adj = "new york";
			break;
		case 5:
			adj = "underground";
			break;
		case 6:
			adj = "most precious";
			break;
		case 7:
			adj = "perfectly symmetric";
			break;
		case 8:
			adj = "crowded";
			break;
		case 9:
			adj = "distiguished";
			break;
		case 10:
			adj = "homely";
			break;
		case 11:
			adj = "successful";
			break;
		case 12:
			adj = "rinkidink";
			break;
		default:
			adj = "A-Ok";
			break;
		}
		switch (b) {
		case 1:
			loc = " hospital";
			break;
		case 2:
			loc = " stock exchange";
			break;
		case 3:
			loc = " back alley";
			break;
		case 4:
			loc = " library";
			break;
		case 5:
			loc = " truffle house";
			break;
		case 6:
			loc = " trash can";
			break;
		case 7:
			loc = " nail salon";
			break;
		case 8:
			loc = " conglomorate";
			break;
		case 9:
			loc = " water fall";
			break;
		case 10:
			loc = " place of festitude";
			break;
		default:
			loc = " statue";
			break;
		}
		String Location = " from the " + a + b + ".";
		return (Location);
	}

	// if activity is destroyed
	@Override
	public void onSaveInstanceState(Bundle savedInstanceState) {
		// add sprite object array and board object
	}

	private void updateScores() {
		String scores = "SCORES          ";
		for (Sprite player : players) {
			if (player != null) {
				scores = scores + player.name + ": $" + player.getMoney()
						+ "       ";
			}
		}
		scores = scores + "CURRENT PLAYER:   " + currentPlayer.name;
		getSupportActionBar().setTitle(scores);
	}

	// Returning from mini game
	@Override
	protected void onRestart() {

	}

	private static boolean isWinner() {
		for (Sprite i : players) {
			if (i.getMoney() >= winTotal) {
				// set winner
				winner = i;
				return true;
			}
		}
		return false;
	}

	@Override
	public void onBackPressed() {
		// disable back button
	}

	private void move(Sprite player, int diceRoll) {
		RelativeLayout.LayoutParams paramsPl = new RelativeLayout.LayoutParams(
				ViewGroup.LayoutParams.WRAP_CONTENT,
				ViewGroup.LayoutParams.WRAP_CONTENT);
		int[] newMargins = player.getMargins();
		for (int i = 1; i <= diceRoll; i++) {
			if (board.isEdge(player.getIsland(), (player.getPosition() + i))) {
				changeDirection(player);
			}

			if (player.getDirection().equals("up")) {
				newMargins[1] = newMargins[1] - 90;
			} else if (player.getDirection().equals("down")) {
				newMargins[1] = newMargins[1] + 90;
			} else if (player.getDirection().equals("right")) {
				newMargins[0] = newMargins[0] + 120;
			} else if (player.getDirection().equals("left")) {
				newMargins[0] = newMargins[0] - 120;
			}
		}

		paramsPl.setMargins(newMargins[0], newMargins[1], newMargins[2],
				newMargins[3]);
		if (playerTurn == 0) {
			boardLayout.removeView(player1View);
			player1View.setLayoutParams(paramsPl);
			boardLayout.addView(player1View);
		} else if (playerTurn == 1) {
			boardLayout.removeView(player2View);
			player2View.setLayoutParams(paramsPl);
			boardLayout.addView(player2View);
		} else if (playerTurn == 2) {
			boardLayout.removeView(player3View);
			player3View.setLayoutParams(paramsPl);
			boardLayout.addView(player3View);
		} else if (playerTurn == 3) {
			boardLayout.removeView(player4View);
			player1View.setLayoutParams(paramsPl);
			boardLayout.addView(player4View);
		}

		player.setMargins(newMargins);

	}

	private void changeDirection(Sprite player) {
		if ((player.getDirection().equals("up") && player.getIsland().equals(
				"topLeft"))
				|| (player.getDirection().equals("up") && player.getIsland()
						.equals("bottomLeft"))
				|| (player.getDirection().equals("down") && player.getIsland()
						.equals("topRight"))
				|| (player.getDirection().equals("up") && player.getIsland()
						.equals("bottomRight"))) {
			player.setDirection("right");
		} else if ((player.getDirection().equals("left") && player.getIsland()
				.equals("topLeft"))
				|| (player.getDirection().equals("left") && player.getIsland()
						.equals("bottomLeft"))
				|| (player.getDirection().equals("right") && player.getIsland()
						.equals("topRight"))
				|| (player.getDirection().equals("left") && player.getIsland()
						.equals("bottomRight"))) {
			player.setDirection("up");
		} else if ((player.getDirection().equals("right") && player.getIsland()
				.equals("topLeft"))
				|| (player.getDirection().equals("right") && player.getIsland()
						.equals("bottomLeft"))
				|| (player.getDirection().equals("left") && player.getIsland()
						.equals("topRight"))
				|| (player.getDirection().equals("right") && player.getIsland()
						.equals("bottomRight"))) {
			player.setDirection("down");
		} else if ((player.getDirection().equals("down") && player.getIsland()
				.equals("topLeft"))
				|| (player.getDirection().equals("down") && player.getIsland()
						.equals("bottomLeft"))
				|| (player.getDirection().equals("up") && player.getIsland()
						.equals("topRight"))
				|| (player.getDirection().equals("down") && player.getIsland()
						.equals("bottomRight"))) {
			player.setDirection("left");
		}

	}

}
