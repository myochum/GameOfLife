package com.example.pong;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.*;
import android.os.Bundle;



import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.TextView;

import com.example.gameoflife.R;
import com.example.pong.AI;
import com.example.pong.Ball;
import com.example.pong.Player;
import com.example.pong.Screen;
import com.example.pong.InputHandler;


public class Pong extends Activity implements OnTouchListener{

	private static final long serialVersionUID = 1L;

	//Changed to be the size of device instead 
	public static int width;
	public static int height;
	public static final String title = "Pong";
	private boolean running = false;

	public int score = 0;
	public int lives = 3;
	
	public String prize;
	public String playerNum;

	private Thread gameThread;
	private Screen screen;
	public Player player;
	public AI ai;
	private Ball ball;
	private InputHandler key;
	
	private Canvas pongBoard;
	
	private boolean win;
	
	private Button startGame;
	private TextView pongScore;
	
	

	
	
	
	//ANDROID
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.pong_start);

		startGame = (Button) findViewById(R.id.startPong);
		pongScore = (TextView) findViewById(R.id.pongScore);
		
		Intent boardInfo = getIntent();
		playerNum = boardInfo.getStringExtra("playerNum");

		final gameThread gameThread = new gameThread(this);

		startGame.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				//Remove button
				startGame.setVisibility(View.GONE);
				pongScore.setText(score + "     " + 0);
				
				//Start the game
				gameThread.run();
				
			}
		});

		
		//Wait for game over
		try {
			gameThread.wait();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Alert Dialog to explain win or loss
		 AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
		 
		 
		 if(win){
			 prize = String.valueOf(score*1000);
			 alertDialogBuilder.setMessage("You Won $" + prize + "In Prizes!");
		 }
		 else{
			 alertDialogBuilder.setMessage("GAME OVER - You Lost");
		 }

	      alertDialogBuilder.setPositiveButton("Ok", 
	      new DialogInterface.OnClickListener() {
			
	         @Override
	         public void onClick(DialogInterface arg0, int arg1) {
	            Intent returnToBoard = new Intent(getApplicationContext(), com.example.gameoflife.GameBoard.class);
	            if(win){
	            	returnToBoard.putExtra("prize",prize);
	            	returnToBoard.putExtra("miniGameResult", true);
	            	returnToBoard.putExtra("playerNum", playerNum);
	            }           
	            startActivity(returnToBoard);			
	         }
	      });
	      
	      AlertDialog alertDialog = alertDialogBuilder.create();
	      alertDialog.show();
		
		

	}
	
	//User cannot click back to board game
	@Override
	public void onBackPressed() {
	}


	//Using a method with new API, instead of lowest target from manifest file
	@SuppressLint("NewApi")
	public class gameThread implements Runnable{
		
		private Pong gameContext; 
		
		public gameThread(Pong gameContext){
			this.gameContext = gameContext;
		}

		@Override
		public void run() {
			
			//Get the size of the player screen
			Display mdisp = getWindowManager().getDefaultDisplay();
			Point mdispSize = new Point();
			mdisp.getSize(mdispSize);
			width = mdispSize.x;
			height = mdispSize.y;
			
			
			Bitmap b = Bitmap.createBitmap(100, 100, Bitmap.Config.ARGB_8888);
			pongBoard = new Canvas(b);
			
			player = new Player(width / 30, 30);
			ai = new AI(28 * width / 30 + 10, 30);
			ball = new Ball(width / 2, height / 2);

			key = new InputHandler();
			
			long lastTime = System.nanoTime();
			long timer = System.currentTimeMillis();
			final double ns = 1000000000.0 / 60.0; // 60 to limit updates to 60
													// times a second
			double delta = 0;
			running = true;
			
			while (running) {
				long now = System.nanoTime();
				delta += (now - lastTime) / ns;
				lastTime = now;
				while (delta >= 1) {
					update();
					delta--;
				}
				render();
				if (System.currentTimeMillis() - timer > 1000) {
					timer += 1000;
				}
			}
			
			//GAME OVER CODE 
			this.notifyAll();
			
		}
		

		//set up return to UI thread message and return to game board
		public synchronized void stop() {
			if (!running) {
				return;
			}
			running = false;
	
		}

		

		public void update() {
			key.update();
			if (key.up && player.y >= 0) {
				player.y -= 5;
			}
			if (key.down && player.y < height - player.height) {
				player.y += 5;
			}
			player.update();
			ai.update(ball);
			ball.update(gameContext);

		};

		public void render() {


			screen.clear();


			
			//ANDROID
			player.render(pongBoard);
			ai.render(pongBoard);
			ball.render(pongBoard);
			

			pongScore.setText(score + "   " + (3 - lives));
			

			if (lives < 0) {
				stop();
			}
		}
		
	}


	@Override
	public boolean onTouch(View v, MotionEvent event) {
//		for(int i = 0; i < key.getTouchCount(mo); i++) {
//			int tx = (int) key.getX(mo, i);
//			int ty = (int) key.getY(mo, i);
//			
//			// Bottom paddle moves when we are playing in one or two player mode and the touch
//			// was in the lower quartile of the screen.
//			if(mBlue.player && mBlue.inTouchbox(tx,ty)) {
//				mBlue.destination = tx;
//			}
//			else if(mRed.player && mRed.inTouchbox(tx,ty)) {
//				mRed.destination = tx;
//			}
//			else if(mo.getAction() == MotionEvent.ACTION_DOWN && mPauseTouchBox.contains(tx, ty)) {
//				if(mCurrentState != State.Stopped) {
//					mLastState = mCurrentState;
//					mCurrentState = State.Stopped;
//				}
//				else {
//					mCurrentState = mLastState;
//					mLastState = State.Stopped;
//				}
//			}
//			
//			// In case a player wants to join in...
//			if(mo.getAction() == MotionEvent.ACTION_DOWN) {
//				if(!mBlue.player && mBlue.inTouchbox(tx,ty)) {
//					mBlue.player = true;
//				}
//				else if(!mRed.player && mRed.inTouchbox(tx,ty)) {
//					mRed.player = true;
//				}
//			}
//		}
		
		return true;
	}





}


