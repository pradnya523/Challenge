package com.challenge.starter.service;

import org.springframework.stereotype.Service;

import com.challenge.starter.dao.Conversion;
import com.challenge.starter.dao.Database;
import com.challenge.starter.domain.Card;
import com.challenge.starter.domain.Card2;
import com.challenge.starter.domain.CardParent;

/**
 * This is a Service class that is responsible for any pre processing of data
 * before persistence pre processing is converting versions if the incoming
 * object is v1 i.e. Card then convert it to v2 i.e. Card2 Assuming that only v2
 * (Card2) is persisted in db
 * 
 * @author pradnya
 *
 */
@Service
public class ApiService {

	/**
	 * 
	 * @param id
	 *            the id of the object to be retrieved
	 * @param version
	 *            version of the object v1 or v2
	 * @return CardParent
	 * @throws Exception
	 */
	public CardParent getCard(String id, String version) throws Exception {
		Card2 v2 = null;
		v2 = Database.getCard(id);
		if (v2 == null) {
			throw new Exception("Card not found");
		} else {
			if (version != null && version.equalsIgnoreCase("v1")) {
				Card v1 = Conversion.convertV2ToV1(v2);
				return v1;
			} else if (version != null && version.equalsIgnoreCase("v2")) {
				return v2;
			}
		}
		return v2;
	}

	/**
	 * 
	 * @param card
	 * @param version
	 * @throws Exception
	 * 
	 */
	public void addCard(CardParent card) throws Exception {
		Card2 card2 = null;
		if (card instanceof Card) {
			card2 = Conversion.convertV1ToV2((Card) card);

		} else {
			card2 = (Card2) card;
		}
		Database.insertCard(card2);

	}

	/**
	 * 
	 * @param card
	 * @param id
	 * @throws Exception
	 * 
	 */
	public void modifyCard(CardParent card, String id) throws Exception {
		Card2 card2 = null;
		if (card instanceof Card) 
			card2 = Conversion.convertV1ToV2((Card) card);
		else 
			card2 = (Card2) card;		
		Database.modifyCard(card2, id);
	}

	/**
	 * @param id
	 * @throws Exception
	 * 
	 */
	public void deleteCard(String id) throws Exception {
		Database.deleteCard(id);
	}

}
