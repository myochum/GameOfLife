package com.example.gameoflife;

import java.util.Random;

import android.graphics.Bitmap;
import android.net.Uri;

public class Sprite {
	
	private int money, position;
	private String island, direction, name;
	private String[] islands = new String[4];
	
	//image attribute - use later for drawing
	private String imageUri;
	
	public Sprite(String imageUri, String name){
		this.imageUri = imageUri;
		this.name = name;
		
		islands[0] = "topRight"; islands[1] = "topLeft";
		islands[2] = "bottomRight"; islands[3] = "bottomLeft";
		
		Random r = new Random();
		int position = r.nextInt(12);
		this.position = position;
		
		int islandNum = r.nextInt(4);
		this.island = islands[islandNum];
		
		money = 0;
		
	}
	
	public int getMoney(){
		return this.money;
	}
	
	public int getPosition(){
		return this.position;
	}
	
	public String getIsland(){
		return this.island;
	}
	
	public void addMoney(int win){
		money = money + win;
	}

	//Cannot have negative money value
	public void loseMoney(int loss) {
		if(loss > money){
			money = 0;
		}
		else{
		money = money - loss;
		}
		
	}
	
	public void setIsland(String newIsland){
		this.island = newIsland;
	}
	
	public void setPosition(int newPostion){
		this.position = newPostion;
	}


}
