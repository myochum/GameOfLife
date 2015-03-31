package com.example.pong;






import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;

public class Player {

	public int width = 20;
	public int height = 50;

	public Rect bounds;

	public int x, y;

	public Player(int x, int y) {
		this.x = x;
		this.y = y;

		bounds = new Rect(x, y, width, height);
		//bounds.setBounds(this.x, this.y, width, height);

	}

	public void update() {
		bounds.set(x, y, width, height);
	}

	public void render(Canvas g) {
		ShapeDrawable playerRect = new ShapeDrawable(new RectShape());
		playerRect.getPaint().setColor(android.graphics.Color.WHITE);
		playerRect.setBounds(x, y, width, height);
		playerRect.draw(g);
		
		//g.setColor(Color.WHITE);
		//g.fillRect(x, y, width, height);
	}

}
