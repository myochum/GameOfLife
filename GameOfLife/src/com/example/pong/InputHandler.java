package com.example.pong;



import android.text.Editable;
import android.view.View;
import android.text.method.*;
import android.view.KeyEvent;

public class InputHandler implements KeyListener {

	private boolean[] keys = new boolean[120];
	public boolean up, down;

	public void update() {
		//up = keys[KeyEvent.VK_UP] || keys[KeyEvent.VK_W];
		//down = keys[KeyEvent.VK_DOWN] || keys[KeyEvent.VK_S];

	}

	public void keyPressed(KeyEvent e) {
		keys[e.getKeyCode()] = true;

	}

	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()] = false;
	}

	public void keyTyped(KeyEvent e) {

	}

	@Override
	public int getInputType() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean onKeyDown(View view, Editable text, int keyCode,
			android.view.KeyEvent event) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean onKeyUp(View view, Editable text, int keyCode,
			android.view.KeyEvent event) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean onKeyOther(View view, Editable text,
			android.view.KeyEvent event) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void clearMetaKeyState(View view, Editable content, int states) {
		// TODO Auto-generated method stub
		
	}

}
