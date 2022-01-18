package com.travelapp.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class CustomerRestExceptionHandler {
	
	// add an exception handler
		@ExceptionHandler
		public ResponseEntity<CustomerErrorResponse> handleException(CustomerNotFoundException exc){
			
			//create CustomerResponse
			CustomerErrorResponse error=new CustomerErrorResponse(exc.getMessage(),System.currentTimeMillis());
			
			//return ResponseEntity
			return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
		}
}
