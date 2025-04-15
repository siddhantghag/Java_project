package com.springboot.Main.Payload;

public class JwtAuthResponces 
{
	private String token;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public JwtAuthResponces(String token) {
		super();
		this.token = token;
	}

	public JwtAuthResponces() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "JwtAuthResponces [token=" + token + "]";
	}
}
