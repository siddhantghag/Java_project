package com.springboot.Main.Payload;


public class Apiresponces 
{
	private String message;
	private boolean sucess;
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public boolean isSucess() {
		return sucess;
	}
	public void setSucess(boolean sucess) {
		this.sucess = sucess;
	}
	public Apiresponces(String message, boolean sucess) {
		super();
		this.message = message;
		this.sucess = sucess;
	}
	public Apiresponces() {
		super();
	}
	@Override
	public String toString() {
		return "Apiresponces [message=" + message + ", sucess=" + sucess + "]";
	}
}
