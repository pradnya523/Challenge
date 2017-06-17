package com.challenge.starter.domain;

public class ErrorMessage {
	private String errMessage;
	private int status;
	private String text;
	
	public ErrorMessage(){
		
	}
	
	public ErrorMessage(String errMessage, int status, String text) {
		this.errMessage = errMessage;
		this.status = status;
		this.text = text;
	}
	public String getErrMessage() {
		return errMessage;
	}
	public void setErrMessage(String errMessage) {
		this.errMessage = errMessage;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	

}
