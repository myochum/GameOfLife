package com.example.pong;



import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.graphics.drawable.shapes.RectShape;

import com.example.pong.Pong;

public class Ball {

	public int x, y;
	public int size = 10;

	public Rect bounds;

	public boolean up = false;
	public boolean down = false;
	public boolean left = false;
	public boolean right = false;

	public int speed = 2;

	public Ball(int x, int y) {
		this.x = x;
		this.y = y;

		up = true;
		right = true;

		bounds = new Rect(x, y, size, size);
		//bounds.setBounds(this.x, this.y, size, size);
	}

	public void collision(Pong game) {
		if (bounds.intersects(game.ai.bounds.left, game.ai.bounds.top, game.ai.bounds.right, game.ai.bounds.bottom) && right) {
			left = true;
			right = false;
			System.out.println(right);
		} else if (bounds.intersects(game.player.bounds.left, game.player.bounds.top, game.player.bounds.right, game.player.bounds.bottom) && left) {
			right = true;
			left = false;
			speed++;
			game.score += 1000;
		}
	}

	public void render(Canvas g) {
//		g.setColor(Color.WHITE);
//		g.fillOval(x, y, size, size);
		
		ShapeDrawable ballRect = new ShapeDrawable(new OvalShape());
		ballRect.getPaint().setColor(android.graphics.Color.WHITE);
		ballRect.setBounds(x, y, x + size, y + size);
		ballRect.draw(g);

	}

	public void update(Pong game) {
		bounds.set(x, y, size, size);

		if (up) {
			if (y <= 0) {
				up = false;
				down = true;
			} else {
				y -= speed;
			}
		}
		if (down) {
			if (y >= game.getHeight() - size) {
				down = false;
				up = true;
			} else {
				y += speed;
			}
		}
		if (left) {
			if (x <= 0) {
				left = false;
				right = true;
			} else {
				x -= speed;
			}
		}
		if (right) {
			if (x >= game.getWidth() - size) {
				right = false;
				left = true;
			} else {
				x += speed;
			}
		}

		collision(game);

		if (x <= 0) {
			game.lives--;
		}

	}

}
