package com.advanz101.response;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Arpit A
 */
public class ValidationErrorDto {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<FieldErrorDto> fieldErrors = new ArrayList<>();

   

    public ValidationErrorDto() {
		
	}

	public void addFieldError(String path, String message) {
        FieldErrorDto error = new FieldErrorDto(path, message);
        fieldErrors.add(error);
    }

    public List<FieldErrorDto> getFieldErrors() {
        return fieldErrors;
    }
}
