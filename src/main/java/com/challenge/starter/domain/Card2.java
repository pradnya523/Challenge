package com.challenge.starter.domain;

public class Card2 extends CardParent{
	
	private Orange orange;
	
	public Card2(){
		
	}
	
	public Card2(String id1, String red1, String blue1, Orange orange){
		super();
		setId(id1);
		setRed(red1);
		setBlue(blue1);
		this.orange = orange;
	}

	public Orange getOrange() {
		return orange;
	}

	public void setOrange(Orange orange) {
		this.orange = orange;
	}
	
	
	
	
}
