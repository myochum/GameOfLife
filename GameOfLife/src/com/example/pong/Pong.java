package com.example.pong;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.*;
import android.os.Bundle;



import android.view.View;
import android.widget.Button;

import com.example.gameoflife.R;
import com.example.pong.AI;
import com.example.pong.Ball;
import com.example.pong.Player;
import com.example.pong.Screen;
import com.example.pong.InputHandler;


public class Pong extends Activity{

	private static final long serialVersionUID = 1L;

	public static int width = 800;
	public static int height = width / 16 * 9;
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
	
	
	
	//ANDROID
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.pong_start);

		Button startGame = (Button) findViewById(R.id.startPong);
		
		Intent boardInfo = getIntent();
		playerNum = boardInfo.getStringExtra("playerNum");

		final gameThread gameThread = new gameThread();

		startGame.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
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



	public class gameThread implements Runnable{

		@Override
		public void run() {
			
			
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
			ball.update(this);

		};

		public void render() {
//			BufferStrategy bs = getBufferStrategy();
//			if (bs == null) {
//				createBufferStrategy(3);
//				return;
//			}

			screen.clear();

//			for (int i = 0; i < pixels.length; i++) {
//				pixels[i] = screen.pixels[i];
//			}
	//
//			Graphics g = bs.getDrawGraphics();
	//
//			g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
//			player.render(g);
//			ai.render(g);
//			ball.render(g);
			
			//ANDROID
			player.render(pongBoard);
			ai.render(pongBoard);
			ball.render(pongBoard);
			

			if (lives < 0) {
				g.drawString("GAME OVER", getWidth() / 2, getHeight() / 2);
				g.drawString("Score: " + score, getWidth() / 2,
						getHeight() / 2 + 20);

			} else {
				g.drawString("Score: " + score + " --- Lives: " + lives, 0,
						getHeight());
			}
//			g.dispose();
//			bs.show();
			if (lives < 0) {
				stop();
			}
		}
		
	}

}


