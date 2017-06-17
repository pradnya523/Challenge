package com.challenge.starter.controller;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.challenge.starter.domain.Card;
import com.challenge.starter.domain.Card2;
import com.challenge.starter.domain.CardParent;
import com.challenge.starter.domain.ErrorMessage;
import com.challenge.starter.domain.UserException;
import com.challenge.starter.service.ApiService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@Validated
public class ApiController {

	@Autowired
	private ApiService apiService;

	/**
	 * This API is to GET a single card object
	 * 
	 * @param id
	 *            the id of the object to be retrieved
	 * @param version
	 *            version of the object v1 or v2
	 * @return ResponseEntity
	 */

	@RequestMapping(value = "/cards/{id}", method = RequestMethod.GET)
	public ResponseEntity<CardParent> getCard(@PathVariable(name = "id") String id,
			@NotEmpty @RequestParam(name = "version") String version) throws UserException {
		CardParent cp = null;
		try {
			cp = apiService.getCard(id, version);

			if (cp == null)
				throw new UserException("Card not found");
		} catch (Exception e) {
			throw new UserException(e.getMessage());
		}

		return new ResponseEntity<CardParent>(cp, HttpStatus.OK);
	}

	/**
	 * This API is to CREATE a single card object
	 * 
	 * @param json
	 *            - the response body is taken as a String
	 * @param version
	 *            version of the object v1 or v2
	 * @throws UserException
	 * 
	 */

	@RequestMapping(value = "/cards", method = RequestMethod.POST)
	public void addCard(@RequestBody String json, @RequestParam(name = "version") String version) throws UserException {

		ObjectMapper mapper = new ObjectMapper();
		try {
			if (version != null && version.equalsIgnoreCase("v1")) {
				Card card = mapper.readValue(json, Card.class);
				apiService.addCard(card);
			} else if (version != null && version.equalsIgnoreCase("v2")) {
				Card2 card2 = mapper.readValue(json, Card2.class);
				apiService.addCard(card2);
			}
		} catch (Exception ex) {
			throw new UserException(ex.getMessage());
		}
	}

	/**
	 * This API is to MODIFY a single card object
	 * 
	 * @param json
	 *            - the response body is taken as a String
	 * @param id
	 *            - the id of the object to be retrieved
	 * @param version
	 *            - version of the object v1 or v2
	 * @throws UserException
	 * 
	 */

	@RequestMapping(value = "/cards/{id}", method = RequestMethod.PUT)
	public void modify(@RequestBody String json, @PathVariable(name = "id") String id,
			@RequestParam(name = "version") String version) throws UserException {

		ObjectMapper mapper = new ObjectMapper();
		try {
			if (version != null && version.equalsIgnoreCase("v1")) {
				Card card = mapper.readValue(json, Card.class);
				apiService.modifyCard(card, id);
			} else if (version != null && version.equalsIgnoreCase("v2")) {
				Card2 card2 = mapper.readValue(json, Card2.class);
				apiService.modifyCard(card2, id);
			}
		} catch (Exception ex) {
			throw new UserException(ex.getMessage());
		}

	}

	/**
	 * This API is to DELETE a single card object
	 * 
	 * @param id
	 *            - the id of the object to be retrieved
	 * @throws UserException
	 */

	@RequestMapping(value = "/cards/{id}", method = RequestMethod.DELETE)
	public void deleteCard(@PathVariable(name = "id") String id) throws UserException {
		try {
			apiService.deleteCard(id);
		} catch (Exception ex) {
			throw new UserException(ex.getMessage());
		}
	}

	/**
	 * This function will handle UserException thrown in application.
	 * 
	 */

	@ExceptionHandler(UserException.class)
	public ResponseEntity<ErrorMessage> exceptionHandler(UserException ex) {

		ErrorMessage error = new ErrorMessage();

		error.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());

		error.setErrMessage("Exception occured. ");

		error.setText(ex.getMessage());

		return new ResponseEntity<ErrorMessage>(error, HttpStatus.INTERNAL_SERVER_ERROR);

	}

	public ApiService getApiService() {
		return apiService;
	}

	public void setApiService(ApiService apiService) {
		this.apiService = apiService;
	}
}
