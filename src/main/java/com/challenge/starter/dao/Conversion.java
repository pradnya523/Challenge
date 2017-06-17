package com.challenge.starter.dao;

import com.challenge.starter.domain.Card;
import com.challenge.starter.domain.Card2;
import com.challenge.starter.domain.Orange;

public class Conversion {
	
	/**
     * This API is to GET a single card object
     * 
     * @param card  takes version 1 of the object card    
     * @return Card2 - returns version 2 of the object card
     */
	
	public static Card2 convertV1ToV2(Card card){
		Card2 c2 = new Card2();	
		
		c2.setId(card.getId());
		c2.setRed(card.getRed());
		c2.setBlue(card.getBlue());
		c2.setOrange(new Orange(card.getOrange()));
		
		return c2;
		
	}
	
	/**
     * This API is to GET a single card object
     * 
     * @param card  takes version 2 of the object card    
     * @return Card2 - returns version 1 of the object card
     */
	
	public static Card convertV2ToV1(Card2 card2){
		Card c1 = new Card();	
		
		c1.setId(card2.getId());
		c1.setRed(card2.getRed());
		c1.setOrange(card2.getOrange().getId());
		c1.setYellow(card2.getYellow());
		
		return c1;
		
	}
	
	
}
