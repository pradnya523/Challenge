package com.challenge.starter.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import com.challenge.starter.domain.Card2;
import com.challenge.starter.domain.Orange;


/**
 * This class is the DAO layer which persist the
 * It is assumed that among the two versions of the object v1 and v2 the data will be persisted as v2 i.e. Card2 
 * Any conversions from v1 to v2 are done at Service layer
 * 
 * @author pradnya
 *
 */
public class Database {
	
	public static List<Card2> v2List = new ArrayList<Card2>(Arrays.asList(
			new Card2("1V2", "red1V2", "blue1V2" , new Orange("orange1V2"))));
	
	public static void insertCard(Card2 card2) throws Exception{
			v2List.add(card2);
	}
	
	public static void modifyCard(Card2 card2, String id) throws Exception{
		for(Card2 c2 : v2List){
			if(c2.getId() != null && c2.getId().equals(id)){
				c2.setBlue(card2.getBlue());
				c2.setOrange(card2.getOrange());
				c2.setRed(card2.getRed());
				c2.setYellow(card2.getYellow());
				break;
			}
		}
		
	}
	
	public static void deleteCard(String id) throws Exception{
		Iterator<Card2> itr = v2List.iterator();
		while(itr.hasNext()){
			Card2 c2 = itr.next();
			if(c2.getId() != null && c2.getId().equals(id)){
				itr.remove();
			}
		}
		
	}
	
	public static Card2 getCard(String id) throws Exception{
		for(Card2 v2 : v2List){
			if(v2.getId().equals(id)){
				return v2;
			}
		}
		return null;
		
	}

}
