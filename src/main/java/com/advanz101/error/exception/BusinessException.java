 package com.advanz101.error.exception;


public class BusinessException extends Exception {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -3892116817542890495L;


	private Integer statusCode;


    public BusinessException(String message,Integer statusCode) {
        super(message);
        this.statusCode = statusCode;
    }

    public Integer getStatusCode() {
        return this.statusCode;
    }/**
 * 
 * @param e Exception
 */
	public BusinessException(Exception e)
	{
		super(e.getMessage(),e);
	}
	
/**
 * 
 * @param string
 */
	public BusinessException(String message)
	{
		super(message);
		
	}

}
