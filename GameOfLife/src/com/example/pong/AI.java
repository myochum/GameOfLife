package com.example.pong;





import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;

import com.example.pong.Pong;

public class AI {
	public int width = 20;
	public int height = 50;

	public Rect bounds;

	public int x, y;

	public AI(int x, int y) {
		this.x = x;
		this.y = y;

		bounds = new Rect(x, y, width, height);
		//bounds.setBounds(this.x, this.y, width, height);
	}

	public void render(Canvas g) {
//		g.setColor(Color.WHITE);
//		g.fillRect(x, y, width, height);
		
		ShapeDrawable aiRect = new ShapeDrawable(new RectShape());
		aiRect.getPaint().setColor(android.graphics.Color.WHITE);
		aiRect.setBounds(x, y, width, height);
		aiRect.draw(g);
	}

	public void update(Ball b) {
		bounds.set(x, y, width, height);

		if (b.y > Pong.height - height) {
			y = Pong.height - height;
		} else {
			y = b.y;
		}
	}
}
