package com.springboot.Main.Exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.springboot.Main.Payload.Apiresponces;

@RestControllerAdvice
public class GlobalExceptionHandler 
{
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<Apiresponces> resourceNotFoundExcetionHandler(ResourceNotFoundException exception)
	{
		String message =exception.getMessage();
		Apiresponces apiresponces =  new Apiresponces(message , false);
		return new ResponseEntity<Apiresponces>(apiresponces,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String,String>> handlerMethodArgumentNotValidException(MethodArgumentNotValidException ex)
	{
		Map<String ,String> resp = new HashMap<>();
		 ex.getBindingResult().getAllErrors().forEach((error)->{
			String fieldname =((FieldError)error).getField();
			String message =error.getDefaultMessage();
			resp.put(fieldname, message);			 
		 });
		return  new ResponseEntity<Map<String,String>>(resp ,HttpStatus.BAD_REQUEST);
	}
}
