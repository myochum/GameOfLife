package com.example.gameoflife;

import java.util.Random;

public class Board {
	
	//Might consider encapsulating islands into a separate class
	
	//List of space colors
	String[] topLeftMap, topRightMap, bottomLeftMap, bottomRightMap = new String[12];
	
	//Where the turns are for drawing sprite animation
	int downTL, upTL, rightTL, leftTL, downTR, upTR, rightTR, leftTR, downBL, upBL, rightBL, leftBL,
	downBR, upBR, rightBR, leftBR;
	
	//Teleport spaces in each island;
	int teleportTL, teleportTR, teleportBL, teleportBR;
	
	public Board(){
		//populate above variables with game board information
	}
	
	//Return new space color after dice roll
	public String getMovement(String island, int start, int diceRoll){
		
		//mod by size of island
		
		if(island.equalsIgnoreCase("topLeft")){
			return topLeftMap[(start + diceRoll) %12];
		}
		else if(island.equalsIgnoreCase("topRight")){
			return topRightMap[(start + diceRoll) %12];
		}
		else if(island.equalsIgnoreCase("bottomLeft")){
			return bottomLeftMap[(start + diceRoll) %12];
		}
		else if(island.equalsIgnoreCase("bottomRight")){
			return bottomRightMap[(start + diceRoll) %12];
		}
		
		return "";
	}

	//for draw method - test to see if movement is edge
	public boolean isEdge(String island, int position){
		if(island.equalsIgnoreCase("topLeft")){
			if(position == downTL | position == upTL | position == rightTL | position == leftTL){
				return true;
			}
		}
		else if(island.equalsIgnoreCase("topRight")){
			if(position == downTR | position == upTR | position == rightTR | position == leftTR){
				return true;
			}
		}
		else if(island.equalsIgnoreCase("bottomLeft")){
			if(position == downBL | position == upBL | position == rightBL | position == leftBL){
				return true;
			}
		}
		else if(island.equalsIgnoreCase("bottomRight")){
			if(position == downBR | position == upBR | position == rightBR | position == leftBR){
				return true;
			}
		}
		return false;
	}
	
	//Move to a different island -- gives a slightly higher probability of landing on one island
	public String[] teleport(String currentIsland){
		String [] newSpot = new String[2];
		String [] islands = {"topRight", "bottomRight", "bottomLeft", "topLeft"};
		Random r = new Random();
		int islandNum = r.nextInt(islands.length);
		if(currentIsland.equalsIgnoreCase("topRight") && islandNum == 0){
			islandNum++;
		}
		else if(currentIsland.equalsIgnoreCase("bottomRightMap") && islandNum == 1){
			islandNum++;
		}
		else if(currentIsland.equalsIgnoreCase("bottomLeftMap") && islandNum == 2){
			islandNum++;
		}
		else if(currentIsland.equalsIgnoreCase("topLeftMap") && islandNum == 3){
			islandNum++;
		}
		newSpot[0] = islands[islandNum];
		
		//this will be the number of the teleport space on the new island 
		newSpot[1] = "11";
		return newSpot;
		
	}
}
