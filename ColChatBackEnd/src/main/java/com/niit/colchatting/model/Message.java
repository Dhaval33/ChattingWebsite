package com.niit.colchatting.model;

public class Message {
	
	 private String message;
	  private int id;
	  
	  
	  public Message(int id, String message) {
			super();
			this.message = message;
			this.id = id;
		}
		public Message(){}
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
